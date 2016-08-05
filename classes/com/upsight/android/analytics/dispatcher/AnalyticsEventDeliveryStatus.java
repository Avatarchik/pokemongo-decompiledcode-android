package com.upsight.android.analytics.dispatcher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.persistence.annotation.UpsightStorableIdentifier;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.dispatcher.delivery.status")
public final class AnalyticsEventDeliveryStatus
{
  @JsonProperty("id")
  @UpsightStorableIdentifier
  String id;
  @JsonProperty("failure_reason")
  private String mFailureReason;
  @JsonProperty("source_event_id")
  private String mOriginEventId;
  @JsonProperty("status")
  private boolean mStatus;
  
  AnalyticsEventDeliveryStatus() {}
  
  AnalyticsEventDeliveryStatus(String paramString1, boolean paramBoolean, String paramString2)
  {
    this.mOriginEventId = paramString1;
    this.mStatus = paramBoolean;
    this.mFailureReason = paramString2;
  }
  
  public static AnalyticsEventDeliveryStatus fromFailure(String paramString1, String paramString2)
  {
    return new AnalyticsEventDeliveryStatus(paramString1, false, paramString2);
  }
  
  public static AnalyticsEventDeliveryStatus fromSuccess(String paramString)
  {
    return new AnalyticsEventDeliveryStatus(paramString, true, null);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
      {
        bool = false;
      }
      else
      {
        AnalyticsEventDeliveryStatus localAnalyticsEventDeliveryStatus = (AnalyticsEventDeliveryStatus)paramObject;
        if ((this.id == null) || (localAnalyticsEventDeliveryStatus.id == null) || (!this.id.equals(localAnalyticsEventDeliveryStatus.id))) {
          bool = false;
        }
      }
    }
  }
  
  public String getFailureReason()
  {
    return this.mFailureReason;
  }
  
  public String getSourceEventId()
  {
    return this.mOriginEventId;
  }
  
  public int hashCode()
  {
    if (this.id != null) {}
    for (int i = this.id.hashCode();; i = 0) {
      return i;
    }
  }
  
  public boolean wasDelivered()
  {
    return this.mStatus;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/dispatcher/AnalyticsEventDeliveryStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */