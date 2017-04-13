package outland.feature.server;

import java.time.OffsetDateTime;
import outland.feature.server.features.Ulid;

public class Names {

  public static String owner() {
    return "own_" + Ulid.random();
  }

  public static String service() {
    return "svc_" + Ulid.random();
  }

  public static String namespaceFeature() {
    return "nsfeat_" + Ulid.random();
  }

  public static String option() {
    return "opt_" + Ulid.random();
  }

  public static String member() {
    return "mbr_" + Ulid.random();
  }

  public static String feature(OffsetDateTime time) {
    return "feat_" + Ulid.random(time.toInstant().toEpochMilli());
  }

  public static String group(OffsetDateTime time) {
    return "grp_" + Ulid.random(time.toInstant().toEpochMilli());
  }

  public static String ownerType() {
    return "owner";
  }

  public static String versionType() {
    return "version";
  }

  public static String memberType() {
    return "member";
  }

  public static String serviceType() {
    return "service";
  }

  public static String featureType() {
    return "feature";
  }

  public static String namespaceFeatureType() {
    return "nsfeature";
  }

  public static String optionType() {
    return "option";
  }

  public static String namespaceFeatureCollectionType() {
    return "namespace.feature.collection";
  }

  public static String accessCollectionType() {
    return "access.collection";
  }

  public static String optionCollectionType() {
    return "option.collection";
  }

  public static String featureCollectionType() {
    return "feature.collection";
  }

  public static String ownerCollectionType() {
    return "owner.collection";
  }
}