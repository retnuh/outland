package outland.feature.server.features;

import java.util.function.Consumer;
import org.junit.Test;
import outland.feature.proto.Feature;
import outland.feature.proto.FeatureOption;
import outland.feature.proto.OptionType;
import outland.feature.proto.Owner;
import outland.feature.server.ServiceException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class FeatureValidatorTest {

  @Test
  public void validateFeatureThrowingFlagOptionOk() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1");

    try {
      new FeatureValidator().validateFeatureRegistrationThrowing(builder.build());
    } catch (ServiceException e) {
      fail();
    }
  }

  @Test
  public void validateFeatureThrowingBoolOptionOk() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1")
        .setOption(OptionType.bool)
        .addOptions(FeatureOption.newBuilder().setValue("true").setName("true").setWeight(5500))
        .addOptions(FeatureOption.newBuilder().setValue("false").setName("false").setWeight(4500))
    ;

    try {
      new FeatureValidator().validateFeatureRegistrationThrowing(builder.build());
    } catch (ServiceException e) {
      fail();
    }
  }

  @Test
  public void validateFeatureThrowingBoolOptionUpdateOk() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1")
        .setOption(OptionType.bool)
        .addOptions(
            FeatureOption.newBuilder()
                .setValue("true")
                .setName("true")
                .setWeight(5500)
                .setId("a"))
        .addOptions(FeatureOption.newBuilder()
            .setValue("false")
            .setName("false")
            .setWeight(4500)
            .setId("b"))
    ;

    try {
      new FeatureValidator().validateFeatureUpdateThrowing(builder.build());
    } catch (ServiceException e) {
      fail();
    }
  }

  @Test
  public void validateFeatureThrowingNoOwner() {
    Feature.Builder builder = Feature.newBuilder();

    callValidate(builder, "no_owner_for_feature", 422);
  }

  @Test
  public void validateFeatureThrowingJunkOwner() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne"));

    callValidate(builder, "incomplete_owner_for_app", 422);
  }

  @Test
  public void validateFeatureThrowingNoKey() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"));

    callValidate(builder, "no_key_for_feature", 422);
  }

  @Test
  public void validateFeatureThrowingNoAppKey() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1");

    callValidate(builder, "no_appkey_for_feature", 422);
  }

  @Test
  public void validateFeatureThrowingBoolWrongOptions() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1")
        .setOption(OptionType.bool)
        .addOptions(FeatureOption.newBuilder().setValue("true").setName("true"));

    callValidate(builder, "wrong_options_for_bool_feature", 422);
  }

  @Test
  public void validateFeatureThrowingBoolWrongNames() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1")
        .setOption(OptionType.bool)
        .addOptions(FeatureOption.newBuilder().setValue("true").setName("true"))
        .addOptions(FeatureOption.newBuilder().setValue("false").setName("alse"))
    ;

    callValidate(builder, "wrong_name_for_bool_feature", 422);
  }

  @Test
  public void validateFeatureThrowingBoolWrongValues() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1")
        .setOption(OptionType.bool)
        .addOptions(FeatureOption.newBuilder().setValue("rue").setName("true"))
        .addOptions(FeatureOption.newBuilder().setValue("false").setName("false"))
    ;

    callValidate(builder, "wrong_value_for_bool_feature", 422);
  }

  @Test
  public void validateFeatureThrowingBoolMismatchedValues() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1")
        .setOption(OptionType.bool)
        .addOptions(FeatureOption.newBuilder().setValue("true").setName("false"))
        .addOptions(FeatureOption.newBuilder().setValue("false").setName("false"))
    ;

    callValidate(builder, "mismatched_name_value_for_bool_feature", 422);

    builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1")
        .setOption(OptionType.bool)
        .addOptions(FeatureOption.newBuilder().setValue("true").setName("true"))
        .addOptions(FeatureOption.newBuilder().setValue("false").setName("true"))
    ;

    callValidate(builder, "mismatched_name_value_for_bool_feature", 422);
  }

  @Test
  public void validateFeatureThrowingBoolWrongWeights() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1")
        .setOption(OptionType.bool)
        .addOptions(FeatureOption.newBuilder().setValue("true").setName("true"))
        .addOptions(FeatureOption.newBuilder().setValue("false").setName("false"))
    ;

    // weights are 0 and 0

    callValidate(builder, "weights_wrong_total", 422);
  }

  @Test
  public void validateFeatureThrowingBoolWeightsOutOfBounds() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1")
        .setOption(OptionType.bool)
        .addOptions(FeatureOption.newBuilder().setValue("true").setName("true"))
        .addOptions(FeatureOption.newBuilder().setValue("false").setName("false").setWeight(-1))
    ;

    // weights are 0 and -1

    callValidate(builder, "weights_out_of_bounds", 422);


    builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1")
        .setOption(OptionType.bool)
        .addOptions(FeatureOption.newBuilder().setValue("true").setName("true"))
        .addOptions(FeatureOption.newBuilder().setValue("false").setName("false").setWeight(10_001))
    ;

    // weights are 0 and 10001

    callValidate(builder, "weights_out_of_bounds", 422);
  }

  @Test
  public void validateFeatureThrowingBoolWeightsOutOfTotalBounds() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1")
        .setOption(OptionType.bool)
        .addOptions(FeatureOption.newBuilder().setValue("true").setName("true").setWeight(5000))
        .addOptions(FeatureOption.newBuilder().setValue("false").setName("false").setWeight(5001))
    ;

    // weights are 5000 and 5001

    callValidate(builder, "weights_wrong_total", 422);
  }

  @Test
  public void validateFeatureThrowingBoolOptionUpdateNoIds() {
    Feature.Builder builder = Feature.newBuilder();
    builder.setOwner(Owner.newBuilder().setName("Jayne").setUsername("jayne"))
        .setKey("key1")
        .setAppkey("app1")
        .setOption(OptionType.bool)
        .addOptions(
            FeatureOption.newBuilder()
                .setValue("true")
                .setName("true")
                .setWeight(5500)) // no id here
        .addOptions(FeatureOption.newBuilder()
            .setValue("false")
            .setName("false")
            .setWeight(4500)
            .setId("b"))
    ;

    callValidateUpdate(builder, "missing_id_for_option", 422);
  }

  private void callValidateUpdate(Feature.Builder builder, String expectedTitle, int expectedCode) {
    callValidator(builder, expectedTitle, expectedCode,
        feature -> new FeatureValidator().validateFeatureUpdateThrowing(feature));
  }

  private void callValidate(Feature.Builder builder, String expectedTitle, int expectedCode) {
    callValidator(builder, expectedTitle, expectedCode,
        feature -> new FeatureValidator().validateFeatureRegistrationThrowing(feature));
  }

  private void callValidator(Feature.Builder builder, String expectedTitle, int expectedCode,
      Consumer<Feature> v) {
    try {
      v.accept(builder.build());
      fail();
    } catch (ServiceException e) {
      assertEquals(expectedCode, e.problem().status());
      assertSame(expectedTitle, e.problem().title());
    }
  }
}