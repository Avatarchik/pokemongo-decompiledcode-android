package com.upsight.android.analytics.provider;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.UpsightAnalyticsExtension;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.UpsightAnalyticsApi;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.persistence.annotation.UpsightStorableIdentifier;
import com.upsight.android.persistence.annotation.UpsightStorableType;

public abstract class UpsightLocationTracker
{
  public static void purge(UpsightContext paramUpsightContext)
  {
    UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
    if (localUpsightAnalyticsExtension != null) {
      localUpsightAnalyticsExtension.getApi().purgeLocation();
    }
    for (;;)
    {
      return;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public static void track(UpsightContext paramUpsightContext, Data paramData)
  {
    UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
    if (localUpsightAnalyticsExtension != null) {
      localUpsightAnalyticsExtension.getApi().trackLocation(paramData);
    }
    for (;;)
    {
      return;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public abstract void purge();
  
  public abstract void track(Data paramData);
  
  @UpsightStorableType("upsight.model.location")
  public static final class Data
  {
    @UpsightStorableIdentifier
    String id;
    @JsonProperty
    double latitude;
    @JsonProperty
    double longitude;
    @JsonProperty
    String timeZone;
    
    Data() {}
    
    private Data(double paramDouble1, double paramDouble2, String paramString)
    {
      this.latitude = paramDouble1;
      this.longitude = paramDouble2;
      this.timeZone = paramString;
    }
    
    public static Data create(double paramDouble1, double paramDouble2)
    {
      return new Data(paramDouble1, paramDouble2, null);
    }
    
    public static Data create(double paramDouble1, double paramDouble2, String paramString)
    {
      return new Data(paramDouble1, paramDouble2, paramString);
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (this == paramObject) {}
      Data localData;
      do
      {
        for (;;)
        {
          return bool;
          if ((paramObject != null) && (getClass() == paramObject.getClass())) {
            break;
          }
          bool = false;
        }
        localData = (Data)paramObject;
        if (this.id == null) {
          break;
        }
      } while (this.id.equals(localData.id));
      for (;;)
      {
        bool = false;
        break;
        if (localData.id == null) {
          break;
        }
      }
    }
    
    public double getLatitude()
    {
      return this.latitude;
    }
    
    public double getLongitude()
    {
      return this.longitude;
    }
    
    public String getTimeZone()
    {
      return this.timeZone;
    }
    
    public int hashCode()
    {
      if (this.id != null) {}
      for (int i = this.id.hashCode();; i = 0) {
        return i;
      }
    }
    
    public void setLatitude(double paramDouble)
    {
      this.latitude = paramDouble;
    }
    
    public void setLongitude(double paramDouble)
    {
      this.longitude = paramDouble;
    }
    
    public void setTimeZone(String paramString)
    {
      this.timeZone = paramString;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/provider/UpsightLocationTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */