package com.upsight.android.analytics.event.datacollection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.UpsightPublisherData.Builder;
import com.upsight.android.analytics.internal.AnalyticsEvent;
import com.upsight.android.analytics.internal.AnalyticsEvent.Builder;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.data_collection")
public class UpsightDataCollectionEvent
  extends AnalyticsEvent<UpsightData>
{
  protected UpsightDataCollectionEvent() {}
  
  protected UpsightDataCollectionEvent(String paramString, UpsightData paramUpsightData, UpsightPublisherData paramUpsightPublisherData)
  {
    super(paramString, paramUpsightData, paramUpsightPublisherData);
  }
  
  public static Builder createBuilder(String paramString1, String paramString2)
  {
    return new Builder(paramString1, paramString2);
  }
  
  public static class Builder
    extends AnalyticsEvent.Builder<UpsightDataCollectionEvent, UpsightDataCollectionEvent.UpsightData>
  {
    private String dataBundle;
    private String format;
    private String streamId;
    private String streamStartTs;
    
    protected Builder(String paramString1, String paramString2)
    {
      this.dataBundle = paramString1;
      this.streamId = paramString2;
    }
    
    protected UpsightDataCollectionEvent build()
    {
      return new UpsightDataCollectionEvent("upsight.data_collection", new UpsightDataCollectionEvent.UpsightData(this), this.mPublisherDataBuilder.build());
    }
    
    public Builder setFormat(String paramString)
    {
      this.format = paramString;
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
    @JsonProperty("data_bundle")
    String dataBundle;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("format")
    String format;
    @JsonProperty("stream_id")
    String streamId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("stream_start_ts")
    String streamStartTs;
    
    protected UpsightData() {}
    
    protected UpsightData(UpsightDataCollectionEvent.Builder paramBuilder)
    {
      this.streamStartTs = paramBuilder.streamStartTs;
      this.streamId = paramBuilder.streamId;
      this.dataBundle = paramBuilder.dataBundle;
      this.format = paramBuilder.format;
    }
    
    public String getDataBundle()
    {
      return this.dataBundle;
    }
    
    public String getFormat()
    {
      return this.format;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/datacollection/UpsightDataCollectionEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */