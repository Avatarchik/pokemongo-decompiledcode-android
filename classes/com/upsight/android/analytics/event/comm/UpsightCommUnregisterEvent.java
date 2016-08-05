package com.upsight.android.analytics.event.comm;

import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.event.UpsightPublisherData.Builder;
import com.upsight.android.analytics.internal.AnalyticsEvent;
import com.upsight.android.analytics.internal.AnalyticsEvent.Builder;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.comm.unregister")
public class UpsightCommUnregisterEvent
  extends AnalyticsEvent<UpsightData>
{
  protected UpsightCommUnregisterEvent() {}
  
  protected UpsightCommUnregisterEvent(String paramString, UpsightData paramUpsightData, UpsightPublisherData paramUpsightPublisherData)
  {
    super(paramString, paramUpsightData, paramUpsightPublisherData);
  }
  
  public static Builder createBuilder()
  {
    return new Builder();
  }
  
  public static class Builder
    extends AnalyticsEvent.Builder<UpsightCommUnregisterEvent, UpsightCommUnregisterEvent.UpsightData>
  {
    protected UpsightCommUnregisterEvent build()
    {
      return new UpsightCommUnregisterEvent("upsight.comm.unregister", new UpsightCommUnregisterEvent.UpsightData(this), this.mPublisherDataBuilder.build());
    }
  }
  
  static class UpsightData
  {
    protected UpsightData() {}
    
    protected UpsightData(UpsightCommUnregisterEvent.Builder paramBuilder) {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/comm/UpsightCommUnregisterEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */