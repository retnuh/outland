package outland.feature.server.features;

import com.google.protobuf.util.JsonFormat;
import org.junit.Test;
import outland.feature.proto.Feature;

import static org.junit.Assert.*;

public class FeatureSupportTest {

  @Test
  public void testToFeature() throws Exception {

    Feature feature = Feature.newBuilder()
        .setId("id1")
        .setKey("key1")
        .setNamespace("app1")
        .setDescription("desc1")
        .setState(Feature.State.off)
        .build();

    final String json = JsonFormat.printer().print(feature);

    final Feature feature1 = FeatureSupport.toFeature(json);

    assertEquals(feature.getId(), feature1.getId());
    assertEquals(feature.getKey(), feature1.getKey());
    assertEquals(feature.getNamespace(), feature1.getNamespace());
    assertEquals(feature.getDescription(), feature1.getDescription());
    assertEquals(feature.getState(), feature1.getState());

  }

}