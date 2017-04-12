// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: outland.proto

package outland.feature.proto;

public interface GroupOrBuilder extends
    // @@protoc_insertion_point(interface_extends:outland.Group)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string type = 1;</code>
   */
  java.lang.String getType();
  /**
   * <code>optional string type = 1;</code>
   */
  com.google.protobuf.ByteString
      getTypeBytes();

  /**
   * <code>optional string id = 2;</code>
   */
  java.lang.String getId();
  /**
   * <code>optional string id = 2;</code>
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>optional string created = 3;</code>
   */
  java.lang.String getCreated();
  /**
   * <code>optional string created = 3;</code>
   */
  com.google.protobuf.ByteString
      getCreatedBytes();

  /**
   * <code>optional string updated = 4;</code>
   */
  java.lang.String getUpdated();
  /**
   * <code>optional string updated = 4;</code>
   */
  com.google.protobuf.ByteString
      getUpdatedBytes();

  /**
   * <code>map&lt;string, string&gt; properties = 5;</code>
   */
  int getPropertiesCount();
  /**
   * <code>map&lt;string, string&gt; properties = 5;</code>
   */
  boolean containsProperties(
      java.lang.String key);
  /**
   * Use {@link #getPropertiesMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, java.lang.String>
  getProperties();
  /**
   * <code>map&lt;string, string&gt; properties = 5;</code>
   */
  java.util.Map<java.lang.String, java.lang.String>
  getPropertiesMap();
  /**
   * <code>map&lt;string, string&gt; properties = 5;</code>
   */

  java.lang.String getPropertiesOrDefault(
      java.lang.String key,
      java.lang.String defaultValue);
  /**
   * <code>map&lt;string, string&gt; properties = 5;</code>
   */

  java.lang.String getPropertiesOrThrow(
      java.lang.String key);

  /**
   * <code>optional string key = 10;</code>
   */
  java.lang.String getKey();
  /**
   * <code>optional string key = 10;</code>
   */
  com.google.protobuf.ByteString
      getKeyBytes();

  /**
   * <code>optional string name = 11;</code>
   */
  java.lang.String getName();
  /**
   * <code>optional string name = 11;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>optional .outland.OwnerCollection owners = 12;</code>
   */
  boolean hasOwners();
  /**
   * <code>optional .outland.OwnerCollection owners = 12;</code>
   */
  outland.feature.proto.OwnerCollection getOwners();
  /**
   * <code>optional .outland.OwnerCollection owners = 12;</code>
   */
  outland.feature.proto.OwnerCollectionOrBuilder getOwnersOrBuilder();

  /**
   * <code>optional .outland.AccessCollection granted = 13;</code>
   */
  boolean hasGranted();
  /**
   * <code>optional .outland.AccessCollection granted = 13;</code>
   */
  outland.feature.proto.AccessCollection getGranted();
  /**
   * <code>optional .outland.AccessCollection granted = 13;</code>
   */
  outland.feature.proto.AccessCollectionOrBuilder getGrantedOrBuilder();
}
