package com.upsight.android.marketing.internal.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.marketing.UpsightPurchase;
import java.io.IOException;

public final class Purchase
  implements UpsightPurchase
{
  @JsonProperty("product")
  String product;
  @JsonProperty("quantity")
  int quantity;
  
  static UpsightPurchase from(JsonNode paramJsonNode, ObjectMapper paramObjectMapper)
    throws IOException
  {
    return (UpsightPurchase)paramObjectMapper.treeToValue(paramJsonNode, Purchase.class);
  }
  
  public String getProduct()
  {
    return this.product;
  }
  
  public int getQuantity()
  {
    return this.quantity;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/Purchase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */