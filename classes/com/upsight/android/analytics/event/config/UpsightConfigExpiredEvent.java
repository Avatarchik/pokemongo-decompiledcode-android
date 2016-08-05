package com.upsight.android.analytics.event.config;

import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.UpsightPublisherData.Builder;
import com.upsight.android.analytics.internal.AnalyticsEvent;
import com.upsight.android.analytics.internal.AnalyticsEvent.Builder;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.config.expired")
public class UpsightConfigExpiredEvent
  extends AnalyticsEvent<UpsightData>
{
  protected UpsightConfigExpiredEvent() {}
  
  protected UpsightConfigExpiredEvent(String paramString, UpsightData paramUpsightData, UpsightPublisherData paramUpsightPublisherData)
  {
    super(paramString, paramUpsightData, paramUpsightPublisherData);
  }
  
  public static Builder createBuilder()
  {
    return new Builder();
  }
  
  public static class Builder
    extends AnalyticsEvent.Builder<UpsightConfigExpiredEvent, UpsightConfigExpiredEvent.UpsightData>
  {
    protected UpsightConfigExpiredEvent build()
    {
      return new UpsightConfigExpiredEvent("upsight.config.expired", new UpsightConfigExpiredEvent.UpsightData(this), this.mPublisherDataBuilder.build());
    }
  }
  
  static class UpsightData
  {
    protected UpsightData() {}
    
    protected UpsightData(UpsightConfigExpiredEvent.Builder paramBuilder) {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/config/UpsightConfigExpiredEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */