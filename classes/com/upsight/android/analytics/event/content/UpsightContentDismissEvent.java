package com.upsight.android.analytics.event.content;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.UpsightPublisherData.Builder;
import com.upsight.android.analytics.internal.AnalyticsEvent;
import com.upsight.android.analytics.internal.AnalyticsEvent.Builder;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.content.dismiss")
public class UpsightContentDismissEvent
  extends AnalyticsEvent<UpsightData>
{
  protected UpsightContentDismissEvent() {}
  
  protected UpsightContentDismissEvent(String paramString, UpsightData paramUpsightData, UpsightPublisherData paramUpsightPublisherData)
  {
    super(paramString, paramUpsightData, paramUpsightPublisherData);
  }
  
  public static Builder createBuilder(String paramString1, Integer paramInteger, String paramString2)
  {
    return new Builder(paramString1, paramInteger, paramString2);
  }
  
  public static class Builder
    extends AnalyticsEvent.Builder<UpsightContentDismissEvent, UpsightContentDismissEvent.UpsightData>
  {
    private String action;
    private Integer contentId;
    private String scope;
    private String streamId;
    private String streamStartTs;
    
    protected Builder(String paramString1, Integer paramInteger, String paramString2)
    {
      this.streamId = paramString1;
      this.contentId = paramInteger;
      this.action = paramString2;
    }
    
    protected UpsightContentDismissEvent build()
    {
      return new UpsightContentDismissEvent("upsight.content.dismiss", new UpsightContentDismissEvent.UpsightData(this), this.mPublisherDataBuilder.build());
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
    @JsonProperty("action")
    String action;
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
    
    protected UpsightData(UpsightContentDismissEvent.Builder paramBuilder)
    {
      this.action = paramBuilder.action;
      this.scope = paramBuilder.scope;
      this.contentId = paramBuilder.contentId;
      this.streamStartTs = paramBuilder.streamStartTs;
      this.streamId = paramBuilder.streamId;
    }
    
    public String getAction()
    {
      return this.action;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/content/UpsightContentDismissEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */