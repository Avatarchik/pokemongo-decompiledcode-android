package com.upsight.android.analytics.event.monetization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.UpsightPublisherData.Builder;
import com.upsight.android.analytics.internal.AnalyticsEvent;
import com.upsight.android.analytics.internal.AnalyticsEvent.Builder;
import com.upsight.android.analytics.internal.util.JacksonHelper.JSONObjectSerializer;
import com.upsight.android.persistence.annotation.UpsightStorableType;
import org.json.JSONObject;

@UpsightStorableType("upsight.monetization.iap")
public class UpsightMonetizationIapEvent
  extends AnalyticsEvent<UpsightData>
{
  protected UpsightMonetizationIapEvent() {}
  
  protected UpsightMonetizationIapEvent(String paramString, UpsightData paramUpsightData, UpsightPublisherData paramUpsightPublisherData)
  {
    super(paramString, paramUpsightData, paramUpsightPublisherData);
  }
  
  public static Builder createBuilder(String paramString1, JSONObject paramJSONObject, Double paramDouble1, Double paramDouble2, Integer paramInteger, String paramString2, String paramString3)
  {
    return new Builder(paramString1, paramJSONObject, paramDouble1, paramDouble2, paramInteger, paramString2, paramString3);
  }
  
  public static class Builder
    extends AnalyticsEvent.Builder<UpsightMonetizationIapEvent, UpsightMonetizationIapEvent.UpsightData>
  {
    private String currency;
    private ObjectNode iapBundle;
    private Double price;
    private String product;
    private Integer quantity;
    private String resolution;
    private String store;
    private String streamId;
    private String streamStartTs;
    private Double totalPrice;
    
    protected Builder(String paramString1, JSONObject paramJSONObject, Double paramDouble1, Double paramDouble2, Integer paramInteger, String paramString2, String paramString3)
    {
      this.store = paramString1;
      this.iapBundle = JacksonHelper.JSONObjectSerializer.toObjectNode(paramJSONObject);
      this.totalPrice = paramDouble1;
      this.price = paramDouble2;
      this.quantity = paramInteger;
      this.currency = paramString2;
      this.product = paramString3;
    }
    
    protected UpsightMonetizationIapEvent build()
    {
      return new UpsightMonetizationIapEvent("upsight.monetization.iap", new UpsightMonetizationIapEvent.UpsightData(this), this.mPublisherDataBuilder.build());
    }
    
    public Builder setResolution(String paramString)
    {
      this.resolution = paramString;
      return this;
    }
    
    public Builder setStreamId(String paramString)
    {
      this.streamId = paramString;
      return this;
    }
    
    public Builder setStreamStartTs(String paramString)
    {
      this.streamStartTs = paramString;
      return this;
    }
  }
  
  static class UpsightData
  {
    @JsonProperty("currency")
    String currency;
    @JsonProperty("iap_bundle")
    ObjectNode iapBundle;
    @JsonProperty("price")
    Double price;
    @JsonProperty("product")
    String product;
    @JsonProperty("quantity")
    Integer quantity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("resolution")
    String resolution;
    @JsonProperty("store")
    String store;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("stream_id")
    String streamId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("stream_start_ts")
    String streamStartTs;
    @JsonProperty("total_price")
    Double totalPrice;
    
    protected UpsightData() {}
    
    protected UpsightData(UpsightMonetizationIapEvent.Builder paramBuilder)
    {
      this.product = paramBuilder.product;
      this.totalPrice = paramBuilder.totalPrice;
      this.streamId = paramBuilder.streamId;
      this.price = paramBuilder.price;
      this.currency = paramBuilder.currency;
      this.iapBundle = paramBuilder.iapBundle;
      this.streamStartTs = paramBuilder.streamStartTs;
      this.resolution = paramBuilder.resolution;
      this.store = paramBuilder.store;
      this.quantity = paramBuilder.quantity;
    }
    
    public String getCurrency()
    {
      return this.currency;
    }
    
    public JSONObject getIapBundle()
    {
      return JacksonHelper.JSONObjectSerializer.fromObjectNode(this.iapBundle);
    }
    
    public Double getPrice()
    {
      return this.price;
    }
    
    public String getProduct()
    {
      return this.product;
    }
    
    public Integer getQuantity()
    {
      return this.quantity;
    }
    
    public String getResolution()
    {
      return this.resolution;
    }
    
    public String getStore()
    {
      return this.store;
    }
    
    public String getStreamId()
    {
      return this.streamId;
    }
    
    public String getStreamStartTs()
    {
      return this.streamStartTs;
    }
    
    public Double getTotalPrice()
    {
      return this.totalPrice;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/monetization/UpsightMonetizationIapEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */