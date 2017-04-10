// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: outland.proto

package outland.feature.proto;

/**
 * Protobuf type {@code outland.NamespaceFeature}
 */
public  final class NamespaceFeature extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:outland.NamespaceFeature)
    NamespaceFeatureOrBuilder {
  // Use NamespaceFeature.newBuilder() to construct.
  private NamespaceFeature(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private NamespaceFeature() {
    type_ = "";
    namespace_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private NamespaceFeature(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            type_ = s;
            break;
          }
          case 82: {
            java.lang.String s = input.readStringRequireUtf8();

            namespace_ = s;
            break;
          }
          case 90: {
            outland.feature.proto.FeatureData.Builder subBuilder = null;
            if (feature_ != null) {
              subBuilder = feature_.toBuilder();
            }
            feature_ = input.readMessage(outland.feature.proto.FeatureData.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(feature_);
              feature_ = subBuilder.buildPartial();
            }

            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return outland.feature.proto.FeatureMessage.internal_static_outland_NamespaceFeature_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return outland.feature.proto.FeatureMessage.internal_static_outland_NamespaceFeature_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            outland.feature.proto.NamespaceFeature.class, outland.feature.proto.NamespaceFeature.Builder.class);
  }

  public static final int TYPE_FIELD_NUMBER = 1;
  private volatile java.lang.Object type_;
  /**
   * <code>optional string type = 1;</code>
   */
  public java.lang.String getType() {
    java.lang.Object ref = type_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      type_ = s;
      return s;
    }
  }
  /**
   * <code>optional string type = 1;</code>
   */
  public com.google.protobuf.ByteString
      getTypeBytes() {
    java.lang.Object ref = type_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      type_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int NAMESPACE_FIELD_NUMBER = 10;
  private volatile java.lang.Object namespace_;
  /**
   * <code>optional string namespace = 10;</code>
   */
  public java.lang.String getNamespace() {
    java.lang.Object ref = namespace_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      namespace_ = s;
      return s;
    }
  }
  /**
   * <code>optional string namespace = 10;</code>
   */
  public com.google.protobuf.ByteString
      getNamespaceBytes() {
    java.lang.Object ref = namespace_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      namespace_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int FEATURE_FIELD_NUMBER = 11;
  private outland.feature.proto.FeatureData feature_;
  /**
   * <code>optional .outland.FeatureData feature = 11;</code>
   */
  public boolean hasFeature() {
    return feature_ != null;
  }
  /**
   * <code>optional .outland.FeatureData feature = 11;</code>
   */
  public outland.feature.proto.FeatureData getFeature() {
    return feature_ == null ? outland.feature.proto.FeatureData.getDefaultInstance() : feature_;
  }
  /**
   * <code>optional .outland.FeatureData feature = 11;</code>
   */
  public outland.feature.proto.FeatureDataOrBuilder getFeatureOrBuilder() {
    return getFeature();
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getTypeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, type_);
    }
    if (!getNamespaceBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 10, namespace_);
    }
    if (feature_ != null) {
      output.writeMessage(11, getFeature());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getTypeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, type_);
    }
    if (!getNamespaceBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(10, namespace_);
    }
    if (feature_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(11, getFeature());
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof outland.feature.proto.NamespaceFeature)) {
      return super.equals(obj);
    }
    outland.feature.proto.NamespaceFeature other = (outland.feature.proto.NamespaceFeature) obj;

    boolean result = true;
    result = result && getType()
        .equals(other.getType());
    result = result && getNamespace()
        .equals(other.getNamespace());
    result = result && (hasFeature() == other.hasFeature());
    if (hasFeature()) {
      result = result && getFeature()
          .equals(other.getFeature());
    }
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType().hashCode();
    hash = (37 * hash) + NAMESPACE_FIELD_NUMBER;
    hash = (53 * hash) + getNamespace().hashCode();
    if (hasFeature()) {
      hash = (37 * hash) + FEATURE_FIELD_NUMBER;
      hash = (53 * hash) + getFeature().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static outland.feature.proto.NamespaceFeature parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static outland.feature.proto.NamespaceFeature parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static outland.feature.proto.NamespaceFeature parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static outland.feature.proto.NamespaceFeature parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static outland.feature.proto.NamespaceFeature parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static outland.feature.proto.NamespaceFeature parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static outland.feature.proto.NamespaceFeature parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static outland.feature.proto.NamespaceFeature parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static outland.feature.proto.NamespaceFeature parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static outland.feature.proto.NamespaceFeature parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(outland.feature.proto.NamespaceFeature prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code outland.NamespaceFeature}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:outland.NamespaceFeature)
      outland.feature.proto.NamespaceFeatureOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return outland.feature.proto.FeatureMessage.internal_static_outland_NamespaceFeature_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return outland.feature.proto.FeatureMessage.internal_static_outland_NamespaceFeature_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              outland.feature.proto.NamespaceFeature.class, outland.feature.proto.NamespaceFeature.Builder.class);
    }

    // Construct using outland.feature.proto.NamespaceFeature.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      type_ = "";

      namespace_ = "";

      if (featureBuilder_ == null) {
        feature_ = null;
      } else {
        feature_ = null;
        featureBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return outland.feature.proto.FeatureMessage.internal_static_outland_NamespaceFeature_descriptor;
    }

    public outland.feature.proto.NamespaceFeature getDefaultInstanceForType() {
      return outland.feature.proto.NamespaceFeature.getDefaultInstance();
    }

    public outland.feature.proto.NamespaceFeature build() {
      outland.feature.proto.NamespaceFeature result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public outland.feature.proto.NamespaceFeature buildPartial() {
      outland.feature.proto.NamespaceFeature result = new outland.feature.proto.NamespaceFeature(this);
      result.type_ = type_;
      result.namespace_ = namespace_;
      if (featureBuilder_ == null) {
        result.feature_ = feature_;
      } else {
        result.feature_ = featureBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof outland.feature.proto.NamespaceFeature) {
        return mergeFrom((outland.feature.proto.NamespaceFeature)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(outland.feature.proto.NamespaceFeature other) {
      if (other == outland.feature.proto.NamespaceFeature.getDefaultInstance()) return this;
      if (!other.getType().isEmpty()) {
        type_ = other.type_;
        onChanged();
      }
      if (!other.getNamespace().isEmpty()) {
        namespace_ = other.namespace_;
        onChanged();
      }
      if (other.hasFeature()) {
        mergeFeature(other.getFeature());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      outland.feature.proto.NamespaceFeature parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (outland.feature.proto.NamespaceFeature) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object type_ = "";
    /**
     * <code>optional string type = 1;</code>
     */
    public java.lang.String getType() {
      java.lang.Object ref = type_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        type_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string type = 1;</code>
     */
    public com.google.protobuf.ByteString
        getTypeBytes() {
      java.lang.Object ref = type_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        type_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string type = 1;</code>
     */
    public Builder setType(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string type = 1;</code>
     */
    public Builder clearType() {
      
      type_ = getDefaultInstance().getType();
      onChanged();
      return this;
    }
    /**
     * <code>optional string type = 1;</code>
     */
    public Builder setTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      type_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object namespace_ = "";
    /**
     * <code>optional string namespace = 10;</code>
     */
    public java.lang.String getNamespace() {
      java.lang.Object ref = namespace_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        namespace_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>optional string namespace = 10;</code>
     */
    public com.google.protobuf.ByteString
        getNamespaceBytes() {
      java.lang.Object ref = namespace_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        namespace_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>optional string namespace = 10;</code>
     */
    public Builder setNamespace(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      namespace_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>optional string namespace = 10;</code>
     */
    public Builder clearNamespace() {
      
      namespace_ = getDefaultInstance().getNamespace();
      onChanged();
      return this;
    }
    /**
     * <code>optional string namespace = 10;</code>
     */
    public Builder setNamespaceBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      namespace_ = value;
      onChanged();
      return this;
    }

    private outland.feature.proto.FeatureData feature_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        outland.feature.proto.FeatureData, outland.feature.proto.FeatureData.Builder, outland.feature.proto.FeatureDataOrBuilder> featureBuilder_;
    /**
     * <code>optional .outland.FeatureData feature = 11;</code>
     */
    public boolean hasFeature() {
      return featureBuilder_ != null || feature_ != null;
    }
    /**
     * <code>optional .outland.FeatureData feature = 11;</code>
     */
    public outland.feature.proto.FeatureData getFeature() {
      if (featureBuilder_ == null) {
        return feature_ == null ? outland.feature.proto.FeatureData.getDefaultInstance() : feature_;
      } else {
        return featureBuilder_.getMessage();
      }
    }
    /**
     * <code>optional .outland.FeatureData feature = 11;</code>
     */
    public Builder setFeature(outland.feature.proto.FeatureData value) {
      if (featureBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        feature_ = value;
        onChanged();
      } else {
        featureBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>optional .outland.FeatureData feature = 11;</code>
     */
    public Builder setFeature(
        outland.feature.proto.FeatureData.Builder builderForValue) {
      if (featureBuilder_ == null) {
        feature_ = builderForValue.build();
        onChanged();
      } else {
        featureBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>optional .outland.FeatureData feature = 11;</code>
     */
    public Builder mergeFeature(outland.feature.proto.FeatureData value) {
      if (featureBuilder_ == null) {
        if (feature_ != null) {
          feature_ =
            outland.feature.proto.FeatureData.newBuilder(feature_).mergeFrom(value).buildPartial();
        } else {
          feature_ = value;
        }
        onChanged();
      } else {
        featureBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>optional .outland.FeatureData feature = 11;</code>
     */
    public Builder clearFeature() {
      if (featureBuilder_ == null) {
        feature_ = null;
        onChanged();
      } else {
        feature_ = null;
        featureBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>optional .outland.FeatureData feature = 11;</code>
     */
    public outland.feature.proto.FeatureData.Builder getFeatureBuilder() {
      
      onChanged();
      return getFeatureFieldBuilder().getBuilder();
    }
    /**
     * <code>optional .outland.FeatureData feature = 11;</code>
     */
    public outland.feature.proto.FeatureDataOrBuilder getFeatureOrBuilder() {
      if (featureBuilder_ != null) {
        return featureBuilder_.getMessageOrBuilder();
      } else {
        return feature_ == null ?
            outland.feature.proto.FeatureData.getDefaultInstance() : feature_;
      }
    }
    /**
     * <code>optional .outland.FeatureData feature = 11;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        outland.feature.proto.FeatureData, outland.feature.proto.FeatureData.Builder, outland.feature.proto.FeatureDataOrBuilder> 
        getFeatureFieldBuilder() {
      if (featureBuilder_ == null) {
        featureBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            outland.feature.proto.FeatureData, outland.feature.proto.FeatureData.Builder, outland.feature.proto.FeatureDataOrBuilder>(
                getFeature(),
                getParentForChildren(),
                isClean());
        feature_ = null;
      }
      return featureBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:outland.NamespaceFeature)
  }

  // @@protoc_insertion_point(class_scope:outland.NamespaceFeature)
  private static final outland.feature.proto.NamespaceFeature DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new outland.feature.proto.NamespaceFeature();
  }

  public static outland.feature.proto.NamespaceFeature getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<NamespaceFeature>
      PARSER = new com.google.protobuf.AbstractParser<NamespaceFeature>() {
    public NamespaceFeature parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new NamespaceFeature(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<NamespaceFeature> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<NamespaceFeature> getParserForType() {
    return PARSER;
  }

  public outland.feature.proto.NamespaceFeature getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

