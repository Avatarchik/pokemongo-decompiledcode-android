package com.upsight.android.analytics.event.campaign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.UpsightPublisherData.Builder;
import com.upsight.android.analytics.internal.AnalyticsEvent;
import com.upsight.android.analytics.internal.AnalyticsEvent.Builder;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.campaign.impression")
public class UpsightCampaignImpressionEvent
  extends AnalyticsEvent<UpsightData>
{
  protected UpsightCampaignImpressionEvent() {}
  
  protected UpsightCampaignImpressionEvent(String paramString, UpsightData paramUpsightData, UpsightPublisherData paramUpsightPublisherData)
  {
    super(paramString, paramUpsightData, paramUpsightPublisherData);
  }
  
  public static Builder createBuilder(String paramString, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3)
  {
    return new Builder(paramString, paramInteger1, paramInteger2, paramInteger3);
  }
  
  public static class Builder
    extends AnalyticsEvent.Builder<UpsightCampaignImpressionEvent, UpsightCampaignImpressionEvent.UpsightData>
  {
    private Integer adGameId;
    private Integer adTypeId;
    private Integer campaignId;
    private Integer contentId;
    private Integer contentTypeId;
    private Integer creativeId;
    private Integer ordinal;
    private String scope;
    private String streamId;
    private String streamStartTs;
    
    protected Builder(String paramString, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3)
    {
      this.streamId = paramString;
      this.campaignId = paramInteger1;
      this.creativeId = paramInteger2;
      this.contentId = paramInteger3;
    }
    
    protected UpsightCampaignImpressionEvent build()
    {
      return new UpsightCampaignImpressionEvent("upsight.campaign.impression", new UpsightCampaignImpressionEvent.UpsightData(this), this.mPublisherDataBuilder.build());
    }
    
    public Builder setAdGameId(Integer paramInteger)
    {
      this.adGameId = paramInteger;
      return this;
    }
    
    public Builder setAdTypeId(Integer paramInteger)
    {
      this.adTypeId = paramInteger;
      return this;
    }
    
    public Builder setContentTypeId(Integer paramInteger)
    {
      this.contentTypeId = paramInteger;
      return this;
    }
    
    public Builder setOrdinal(Integer paramInteger)
    {
      this.ordinal = paramInteger;
      return this;
    }
    
    public Builder setScope(String paramString)
    {
      this.scope = paramString;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("ad_game_id")
    Integer adGameId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("ad_type_id")
    Integer adTypeId;
    @JsonProperty("campaign_id")
    Integer campaignId;
    @JsonProperty("content_id")
    Integer contentId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("content_type_id")
    Integer contentTypeId;
    @JsonProperty("creative_id")
    Integer creativeId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("ordinal")
    Integer ordinal;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("scope")
    String scope;
    @JsonProperty("stream_id")
    String streamId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("stream_start_ts")
    String streamStartTs;
    
    protected UpsightData() {}
    
    protected UpsightData(UpsightCampaignImpressionEvent.Builder paramBuilder)
    {
      this.ordinal = paramBuilder.ordinal;
      this.contentTypeId = paramBuilder.contentTypeId;
      this.creativeId = paramBuilder.creativeId;
      this.campaignId = paramBuilder.campaignId;
      this.adTypeId = paramBuilder.adTypeId;
      this.streamId = paramBuilder.streamId;
      this.adGameId = paramBuilder.adGameId;
      this.streamStartTs = paramBuilder.streamStartTs;
      this.scope = paramBuilder.scope;
      this.contentId = paramBuilder.contentId;
    }
    
    public Integer getAdGameId()
    {
      return this.adGameId;
    }
    
    public Integer getAdTypeId()
    {
      return this.adTypeId;
    }
    
    public Integer getCampaignId()
    {
      return this.campaignId;
    }
    
    public Integer getContentId()
    {
      return this.contentId;
    }
    
    public Integer getContentTypeId()
    {
      return this.contentTypeId;
    }
    
    public Integer getCreativeId()
    {
      return this.creativeId;
    }
    
    public Integer getOrdinal()
    {
      return this.ordinal;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/campaign/UpsightCampaignImpressionEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */