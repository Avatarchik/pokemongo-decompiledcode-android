package com.upsight.android.analytics.event.partner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.UpsightPublisherData.Builder;
import com.upsight.android.analytics.internal.AnalyticsEvent;
import com.upsight.android.analytics.internal.AnalyticsEvent.Builder;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.partner.impression")
public class UpsightPartnerImpressionEvent
  extends AnalyticsEvent<UpsightData>
{
  protected UpsightPartnerImpressionEvent() {}
  
  protected UpsightPartnerImpressionEvent(String paramString, UpsightData paramUpsightData, UpsightPublisherData paramUpsightPublisherData)
  {
    super(paramString, paramUpsightData, paramUpsightPublisherData);
  }
  
  public static Builder createBuilder(Integer paramInteger1, String paramString1, String paramString2, Integer paramInteger2)
  {
    return new Builder(paramInteger1, paramString1, paramString2, paramInteger2);
  }
  
  public static class Builder
    extends AnalyticsEvent.Builder<UpsightPartnerImpressionEvent, UpsightPartnerImpressionEvent.UpsightData>
  {
    private Integer contentId;
    private Integer partnerId;
    private String partnerName;
    private String scope;
    private String streamId;
    private String streamStartTs;
    
    protected Builder(Integer paramInteger1, String paramString1, String paramString2, Integer paramInteger2)
    {
      this.partnerId = paramInteger1;
      this.scope = paramString1;
      this.streamId = paramString2;
      this.contentId = paramInteger2;
    }
    
    protected UpsightPartnerImpressionEvent build()
    {
      return new UpsightPartnerImpressionEvent("upsight.partner.impression", new UpsightPartnerImpressionEvent.UpsightData(this), this.mPublisherDataBuilder.build());
    }
    
    public Builder setPartnerName(String paramString)
    {
      this.partnerName = paramString;
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
    @JsonProperty("content_id")
    Integer contentId;
    @JsonProperty("partner_id")
    Integer partnerId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("partner_name")
    String partnerName;
    @JsonProperty("scope")
    String scope;
    @JsonProperty("stream_id")
    String streamId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("stream_start_ts")
    String streamStartTs;
    
    protected UpsightData() {}
    
    protected UpsightData(UpsightPartnerImpressionEvent.Builder paramBuilder)
    {
      this.partnerName = paramBuilder.partnerName;
      this.streamId = paramBuilder.streamId;
      this.streamStartTs = paramBuilder.streamStartTs;
      this.scope = paramBuilder.scope;
      this.contentId = paramBuilder.contentId;
      this.partnerId = paramBuilder.partnerId;
    }
    
    public Integer getContentId()
    {
      return this.contentId;
    }
    
    public Integer getPartnerId()
    {
      return this.partnerId;
    }
    
    public String getPartnerName()
    {
      return this.partnerName;
    }
    
    public String getScope()
    {
      return this.scope;
    }
    
    public String getStreamId()
    {
      return this.streamId;
    }
    
    public String getStreamStartTs()
    {
      return this.streamStartTs;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/partner/UpsightPartnerImpressionEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */