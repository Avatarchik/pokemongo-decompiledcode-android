package com.upsight.android.analytics.event.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.UpsightPublisherData.Builder;
import com.upsight.android.analytics.internal.AnalyticsEvent;
import com.upsight.android.analytics.internal.AnalyticsEvent.Builder;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.content.view")
public class UpsightContentViewEvent
  extends AnalyticsEvent<UpsightData>
{
  protected UpsightContentViewEvent() {}
  
  protected UpsightContentViewEvent(String paramString, UpsightData paramUpsightData, UpsightPublisherData paramUpsightPublisherData)
  {
    super(paramString, paramUpsightData, paramUpsightPublisherData);
  }
  
  public static Builder createBuilder(String paramString, Integer paramInteger)
  {
    return new Builder(paramString, paramInteger);
  }
  
  public static class Builder
    extends AnalyticsEvent.Builder<UpsightContentViewEvent, UpsightContentViewEvent.UpsightData>
  {
    private Integer contentId;
    private String scope;
    private String streamId;
    private String streamStartTs;
    
    protected Builder(String paramString, Integer paramInteger)
    {
      this.streamId = paramString;
      this.contentId = paramInteger;
    }
    
    protected UpsightContentViewEvent build()
    {
      return new UpsightContentViewEvent("upsight.content.view", new UpsightContentViewEvent.UpsightData(this), this.mPublisherDataBuilder.build());
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
    @JsonProperty("content_id")
    Integer contentId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("scope")
    String scope;
    @JsonProperty("stream_id")
    String streamId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("stream_start_ts")
    String streamStartTs;
    
    protected UpsightData() {}
    
    protected UpsightData(UpsightContentViewEvent.Builder paramBuilder)
    {
      this.streamStartTs = paramBuilder.streamStartTs;
      this.scope = paramBuilder.scope;
      this.contentId = paramBuilder.contentId;
      this.streamId = paramBuilder.streamId;
    }
    
    public Integer getContentId()
    {
      return this.contentId;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/content/UpsightContentViewEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */