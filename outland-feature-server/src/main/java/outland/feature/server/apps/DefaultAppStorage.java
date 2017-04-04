package outland.feature.server.apps;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.internal.IteratorSupport;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.codahale.metrics.MetricRegistry;
import java.util.Optional;
import java.util.function.Supplier;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import outland.feature.proto.App;
import outland.feature.server.Problem;
import outland.feature.server.ServiceException;
import outland.feature.server.app.AppSupport;
import outland.feature.server.features.DynamoDbCommand;
import outland.feature.server.features.TableConfiguration;
import outland.feature.server.hystrix.HystrixConfiguration;
import outland.feature.server.protobuf.Protobuf3Support;

import static outland.feature.server.StructLog.kvp;

public class DefaultAppStorage implements AppStorage {

  private static final Logger logger = LoggerFactory.getLogger(DefaultAppStorage.class);
  private static final String HASH_KEY = "app_key";

  private final DynamoDB dynamoDB;
  private final String appsTableName;
  private final String appsGraphTableName;
  private final HystrixConfiguration dynamodbAppWriteHystrix;
  private final HystrixConfiguration dynamodbAppGraphWriteHystrix;
  private final HystrixConfiguration dynamodbAppGraphQueryHystrix;
  private final MetricRegistry metrics;
  private final AmazonDynamoDB amazonDynamoDB;

  @Inject
  public DefaultAppStorage(
      AmazonDynamoDB amazonDynamoDB,
      TableConfiguration tableConfiguration,
      @Named("dynamodbAppWriteHystrix") HystrixConfiguration dynamodbAppWriteHystrix,
      @Named("dynamodbAppGraphWriteHystrix") HystrixConfiguration dynamodbAppGraphWriteHystrix,
      @Named("dynamodbAppGraphQueryHystrix") HystrixConfiguration dynamodbAppGraphQueryHystrix,
      MetricRegistry metrics
  ) {
    this.amazonDynamoDB = amazonDynamoDB;
    this.dynamoDB = new DynamoDB(this.amazonDynamoDB);
    this.appsTableName = tableConfiguration.outlandAppsTable;
    this.appsGraphTableName = tableConfiguration.outlandAppGraphTable;
    this.dynamodbAppWriteHystrix = dynamodbAppWriteHystrix;
    this.dynamodbAppGraphWriteHystrix = dynamodbAppGraphWriteHystrix;
    this.dynamodbAppGraphQueryHystrix = dynamodbAppGraphQueryHystrix;
    this.metrics = metrics;
  }

  @Override public Void createApp(App app) {
    Item item = preparePutItem(app);

    PutItemSpec putItemSpec = new PutItemSpec()
        .withItem(item)
        .withConditionExpression("attribute_not_exists(#appkey)")
        .withNameMap(new NameMap().with("#appkey", HASH_KEY));

    Table table = dynamoDB.getTable(appsTableName);
    final Supplier<PutItemOutcome> putItemOutcomeSupplier = () -> {
      try {
        return table.putItem(putItemSpec);
      } catch (ConditionalCheckFailedException e) {
        logger.error("err=conflict_app_already_exists appkey={} {}", app.getKey(), e.getMessage());
        throwConflictAlreadyExists(app);
        return null;
      }
    };
    return putItem(app, putItemOutcomeSupplier);
  }

  @Override public Void saveApp(App app) {
    Item item = preparePutItem(app);
    Table table = dynamoDB.getTable(appsTableName);
    final Supplier<PutItemOutcome> putItemOutcomeSupplier = () -> table.putItem(item);
    return putItem(app, putItemOutcomeSupplier);
  }

  private Item preparePutItem(App app) {
    String json = Protobuf3Support.toJsonString(app);

    return new Item()
        .withString("id", app.getId())
        .withString(HASH_KEY, app.getKey())
        .withString("name", app.getName())
        .withString("json", json)
        .withString("v", "1")
        .withString("created", app.getCreated())
        .withString("updated", app.getUpdated());
  }

  private Void putItem(App app, Supplier<PutItemOutcome> putItemOutcomeSupplier) {
    DynamoDbCommand<PutItemOutcome> cmd = new DynamoDbCommand<>("saveApp",
        putItemOutcomeSupplier,
        () -> {
          throw new RuntimeException("saveApp");
        },
        dynamodbAppWriteHystrix,
        metrics);

    PutItemOutcome outcome = cmd.execute();

    logger.info("{} /dynamodb_put_item_result=[{}]",
        kvp("op", "saveApp",
            "appid", app.getId(),
            HASH_KEY, app.getKey(),
            "result", "ok"),
        outcome.getPutItemResult().toString());

    return null;
  }

  @Override public Void saveRelation(App app, String relationHashKey, String relationRangeKey) {

    Item item = new Item()
        .withString(AppStorage.SUBJECT_KEY, relationHashKey)
        .withString(AppStorage.OBJECT_RELATION_KEY, relationRangeKey);

    Table table = dynamoDB.getTable(appsGraphTableName);

    DynamoDbCommand<PutItemOutcome> cmd = new DynamoDbCommand<>("saveRelation",
        () -> table.putItem(item),
        () -> {
          throw new RuntimeException("saveRelation");
        },
        dynamodbAppGraphWriteHystrix,
        metrics);

    PutItemOutcome outcome = cmd.execute();

    logger.info("{} /dynamodb_put_item_result=[{}]",
        kvp("op", "saveRelation",
            "appkey", app.getKey(),
            "hash_key", relationHashKey,
            "range_key", relationRangeKey,
            "result", "ok"),

        outcome.getPutItemResult().toString());

    return null;
  }

  @Override public Void removeRelation(App app, String relationHashKey, String relationRangeKey) {

    Table table = dynamoDB.getTable(appsGraphTableName);

    final PrimaryKey key = new PrimaryKey(
        AppStorage.SUBJECT_KEY, relationHashKey,
        AppStorage.OBJECT_RELATION_KEY, relationRangeKey
    );

    DynamoDbCommand<DeleteItemOutcome> cmd = new DynamoDbCommand<>("removeRelation",
        () -> table.deleteItem(key),
        () -> {
          throw new RuntimeException("removeRelation");
        },
        dynamodbAppGraphWriteHystrix,
        metrics);

    final DeleteItemOutcome deleteItemOutcome = cmd.execute();

    logger.info("{} /dynamodb_remove_item_result=[{}]",
        kvp("op", "removeRelation",
            "appkey", app.getKey(),
            "hash_key", relationHashKey,
            "range_key", relationRangeKey,
            "result", "ok"),
        deleteItemOutcome.getDeleteItemResult().toString());

    return null;
  }

  @Override public boolean queryRelationExists(String relationHashKey, String relationRangeKey) {

    Table table = dynamoDB.getTable(this.appsGraphTableName);

    QuerySpec querySpec = new QuerySpec()
        .withKeyConditionExpression("subject = :k_subject and object_relation = :k_object_relation")
        .withValueMap(new ValueMap()
            .withString(":k_subject", relationHashKey)
            .withString(":k_object_relation", relationRangeKey)
        )
        .withMaxResultSize(1)
        .withConsistentRead(true);

    DynamoDbCommand<ItemCollection<QueryOutcome>> cmd = new DynamoDbCommand<>("queryRelation",
        () -> queryTable(table, querySpec),
        () -> {
          throw new RuntimeException("queryRelation");
        },
        dynamodbAppGraphQueryHystrix,
        metrics);

    // can't use getLastLowLevelResult directly; it's false unless the outcome is iterated first :|
      return cmd.execute().iterator().hasNext();
  }

  @Override public Optional<App> loadAppByKey(String appKey) {
    Table table = dynamoDB.getTable(this.appsTableName);

    QuerySpec querySpec = new QuerySpec()
        .withKeyConditionExpression(HASH_KEY+" = :k_app_key")
        .withValueMap(new ValueMap()
            .withString(":k_app_key", appKey)
        )
        .withMaxResultSize(1)
        .withConsistentRead(true);

    DynamoDbCommand<ItemCollection<QueryOutcome>> cmd = new DynamoDbCommand<>("loadAppByKey",
        () -> queryTable(table, querySpec),
        () -> {
          throw new RuntimeException("loadAppByKey");
        },
        dynamodbAppGraphQueryHystrix,
        metrics);

    final ItemCollection<QueryOutcome> items = cmd.execute();
    final IteratorSupport<Item, QueryOutcome> iterator = items.iterator();
    if (iterator.hasNext()) {
      return Optional.of(AppSupport.toApp(iterator.next().getString("json")));
    }

    return Optional.empty();

  }

  private ItemCollection<QueryOutcome> queryTable(Table table, QuerySpec querySpec) {
    return table.query(querySpec);
  }
}
