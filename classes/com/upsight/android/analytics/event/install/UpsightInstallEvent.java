package com.upsight.android.analytics.event.install;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.UpsightPublisherData.Builder;
import com.upsight.android.analytics.internal.AnalyticsEvent;
import com.upsight.android.analytics.internal.AnalyticsEvent.Builder;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.install")
public class UpsightInstallEvent
  extends AnalyticsEvent<UpsightData>
{
  protected UpsightInstallEvent() {}
  
  protected UpsightInstallEvent(String paramString, UpsightData paramUpsightData, UpsightPublisherData paramUpsightPublisherData)
  {
    super(paramString, paramUpsightData, paramUpsightPublisherData);
  }
  
  public static Builder createBuilder()
  {
    return new Builder();
  }
  
  public static class Builder
    extends AnalyticsEvent.Builder<UpsightInstallEvent, UpsightInstallEvent.UpsightData>
  {
    private String referrer;
    private String sourceId;
    private String streamId;
    private String streamStartTs;
    
    protected UpsightInstallEvent build()
    {
      return new UpsightInstallEvent("upsight.install", new UpsightInstallEvent.UpsightData(this), this.mPublisherDataBuilder.build());
    }
    
    public Builder setReferrer(String paramString)
    {
      this.referrer = paramString;
      return this;
    }
    
    public Builder setSourceId(String paramString)
    {
      this.sourceId = paramString;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("referrer")
    String referrer;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("source_id")
    String sourceId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("stream_id")
    String streamId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("stream_start_ts")
    String streamStartTs;
    
    protected UpsightData() {}
    
    protected UpsightData(UpsightInstallEvent.Builder paramBuilder)
    {
      this.sourceId = paramBuilder.sourceId;
      this.referrer = paramBuilder.referrer;
      this.streamStartTs = paramBuilder.streamStartTs;
      this.streamId = paramBuilder.streamId;
    }
    
    public String getReferrer()
    {
      return this.referrer;
    }
    
    public String getSourceId()
    {
      return this.sourceId;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/install/UpsightInstallEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */