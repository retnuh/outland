package outland.feature.server.apps;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.protobuf.TextFormat;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import outland.feature.proto.App;
import outland.feature.proto.Owner;
import outland.feature.proto.Service;
import outland.feature.server.features.MetricsTimer;
import outland.feature.server.features.Ulid;
import outland.feature.server.features.VersionService;

import static outland.feature.server.StructLog.kvp;

public class DefaultAppService implements AppService, MetricsTimer {

  private static final Logger logger = LoggerFactory.getLogger(DefaultAppService.class);

  private final AppStorage appStorage;
  private final VersionService versionService;

  private Timer saveAppTimer;
  private Timer saveServiceTimer;
  private Timer saveOwnerTimer;

  @Inject
  public DefaultAppService(
      AppStorage appStorage,
      VersionService versionService,
      MetricRegistry metrics
  ) {
    this.appStorage = appStorage;
    this.versionService = versionService;
    configureMetrics(metrics);
  }

  @Override public Optional<App> registerApp(App app) {
    logger.info("{} /app[{}]", kvp("op", "registerApp"), TextFormat.shortDebugString(app));
    return processRegistration(app);
  }

  @Override public boolean appHasOwner(String appKey, String username) {
    return appHasMemberRelation(appKey, AppService.OWNER, username);
  }

  @Override public boolean appHasService(String appKey, String serviceKey) {
    return appHasMemberRelation(appKey, AppService.SERVICE, serviceKey);
  }

  private Optional<App> processRegistration(App app) {
    OffsetDateTime now = OffsetDateTime.now();
    String id = "app_" + Ulid.random(now.toInstant().toEpochMilli());
    String created = AppService.asString(now);
    App.Builder builder = app.toBuilder();
    builder.setId(id);
    builder.setCreated(created);
    builder.setUpdated(created);

    List<Owner> ownersReady = Lists.newArrayList();
    app.getOwnersList().forEach(
        owner -> ownersReady.add(owner.toBuilder().setId("usr_" + Ulid.random()).build()));
    builder.clearOwners().addAllOwners(ownersReady);

    List<Service> servicesReady = Lists.newArrayList();
    app.getServicesList().forEach(
        service -> servicesReady.add(service.toBuilder().setId("svc_" + Ulid.random()).build()));
    builder.clearServices().addAllServices(servicesReady);

    final App registered = builder.build();

    // todo: the usual compensating write failure stuff
    registerAppInner(registered);
    registerOwners(registered);
    registerServices(registered);

    return Optional.of(registered);
  }

  private boolean appHasMemberRelation(String appKey, String relatedType, String relatedKey) {
    final String relation = "has_member";
    final String subjectType = "app";
    final String subjectKey = appKey;
    final String objectType = relatedType;
    final String objectKey = relatedKey;
    final String inv = "inv.";

    final String inverseRelationHashKey = objectType + "." + objectKey;
    final String inverseRelationRangeKey = inv + relation + "." + subjectType + "." + subjectKey;

    return appStorage.queryRelationExists(inverseRelationHashKey, inverseRelationRangeKey);
  }

  private void registerAppInner(App registered) {
    timed(saveAppTimer, () -> appStorage.saveApp(registered));
  }

  private void registerServices(App app) {
    app.getServicesList().forEach(service -> addServiceToGraph(app, service));
  }

  private void registerOwners(App app) {
    app.getOwnersList().forEach(service -> addOwnerToGraph(app, service));
  }

  private void addServiceToGraph(App app, Service service) {

    final String relation = "has_member";
    final String subjectType = "app";
    final String subjectKey = app.getKey();
    final String objectType = AppService.SERVICE;
    final String objectKey = service.getKey();

    saveGraphRelation(
        app, relation, subjectType, subjectKey, objectType, objectKey, saveServiceTimer);
  }

  private void addOwnerToGraph(App app, Owner owner) {
    final String relation = "has_member";
    final String subjectType = "app";
    final String subjectKey = app.getKey();
    final String objectType = AppService.OWNER;

    if(! Strings.isNullOrEmpty(owner.getUsername())) {
      final String objectKey = owner.getUsername();
      saveGraphRelation(
          app, relation, subjectType, subjectKey, objectType, objectKey, saveServiceTimer);
    }

    if(! Strings.isNullOrEmpty(owner.getEmail())) {
      final String objectKey = owner.getEmail();
      saveGraphRelation(
          app, relation, subjectType, subjectKey, objectType, objectKey, saveServiceTimer);
    }

  }

  private void saveGraphRelation(
      App app,
      String relation,
      String subjectType,
      String subjectKey,
      String objectType,
      String objectKey,
      Timer timer
  ) {
    /*
      Store two records, one subject to object relation, the other an inverse
      object to subject relation. The two are marked as "rel" and "inv" as that
      allows us to have one relationship term (eg "member") without defining
      an inverse term. The records' range keys also have a type; this
      allows queries about a relation in general, but also about a relation
      between a subject and a specific type (range queries in dynamo can use a
      prefix pattern). In our case we can use it to track the owner members of an
      app distinctly from the service members of an app, or just grab both membership
      kinds.

      +-----------------------------+------------------------------------------+
      | hash                        |   range                                  |
      +-----------------------------+------------------------------------------+
      | $subject_type.$subject_key  |   rel.$relation.$object_type.$object_key |
      +-----------------------------+------------------------------------------+
      | $object_type.$object_key    |   inv.$relation.$object_type.$object_key |
      +-----------------------------+------------------------------------------+
     */

    final String rel = "rel.";
    final String inv = "inv.";

    final String relationHashKey = subjectType + "." + subjectKey;
    final String relationRangeKey = rel + relation + "." + objectType + "." + objectKey;
    timed(timer,
        () -> appStorage.saveRelation(app, relationHashKey, relationRangeKey));

    final String inverseRelationHashKey = objectType + "." + objectKey;
    final String inverseRelationRangeKey = inv + relation + "." + subjectType + "." + subjectKey;
    timed(timer,
        () -> appStorage.saveRelation(app, inverseRelationHashKey, inverseRelationRangeKey));
  }

  private void configureMetrics(MetricRegistry metrics) {
    saveAppTimer = metrics.timer(MetricRegistry.name(DefaultAppService.class,
        "saveAppTimer"));
    saveOwnerTimer = metrics.timer(MetricRegistry.name(DefaultAppService.class,
        "saveOwnerTimer"));
    saveServiceTimer = metrics.timer(MetricRegistry.name(DefaultAppService.class,
        "saveServiceTimer"));
  }
}