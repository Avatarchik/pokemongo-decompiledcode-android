package com.upsight.android.marketing.internal.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.upsight.android.analytics.internal.util.JacksonHelper.JSONObjectSerializer;
import com.upsight.android.marketing.UpsightReward;
import java.io.IOException;
import org.json.JSONObject;

public final class Reward
  implements UpsightReward
{
  @JsonProperty("product")
  String product;
  @JsonProperty("quantity")
  int quantity;
  @JsonProperty("signature_data")
  ObjectNode signatureData;
  
  static UpsightReward from(JsonNode paramJsonNode, ObjectMapper paramObjectMapper)
    throws IOException
  {
    return (UpsightReward)paramObjectMapper.treeToValue(paramJsonNode, Reward.class);
  }
  
  public String getProduct()
  {
    return this.product;
  }
  
  public int getQuantity()
  {
    return this.quantity;
  }
  
  public JSONObject getSignatureData()
  {
    return JacksonHelper.JSONObjectSerializer.fromObjectNode(this.signatureData);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/Reward.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */