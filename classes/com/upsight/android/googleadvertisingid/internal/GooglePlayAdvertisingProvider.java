package com.upsight.android.googleadvertisingid.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.upsight.android.analytics.provider.UpsightDataProvider;
import com.upsight.android.logger.UpsightLogger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class GooglePlayAdvertisingProvider
  extends UpsightDataProvider
{
  public static final String AID_KEY = "ids.aid";
  public static final String LIMITED_AD_TRACKING_KEY = "device.limit_ad_tracking";
  public static final String LOG_TAG = GooglePlayAdvertisingProvider.class.getSimpleName();
  private final Context mContext;
  private final UpsightLogger mLogger;
  
  public GooglePlayAdvertisingProvider(Context paramContext, UpsightLogger paramUpsightLogger)
  {
    this.mContext = paramContext;
    this.mLogger = paramUpsightLogger;
  }
  
  public Set<String> availableKeys()
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "ids.aid";
    arrayOfString[1] = "device.limit_ad_tracking";
    return new HashSet(Arrays.asList(arrayOfString));
  }
  
  /**
   * @deprecated
   */
  public Object get(String paramString)
  {
    Object localObject1 = null;
    int i = 0;
    for (;;)
    {
      try
      {
        switch (paramString.hashCode())
        {
        case 1669192966: 
          Object localObject5 = super.get(paramString);
          localObject1 = localObject5;
          return localObject1;
        }
      }
      finally {}
      if (!paramString.equals("ids.aid")) {
        break label232;
      }
      break label235;
      boolean bool = paramString.equals("device.limit_ad_tracking");
      if (!bool) {
        break label232;
      }
      i = 1;
      break label235;
      Object localObject4 = null;
      try
      {
        AdvertisingIdClient.Info localInfo2 = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
        localObject4 = localInfo2;
      }
      catch (Exception localException2)
      {
        UpsightLogger localUpsightLogger2 = this.mLogger;
        String str2 = LOG_TAG;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = localException2;
        localUpsightLogger2.w(str2, "Unable to resolve Google Advertising ID", arrayOfObject2);
        continue;
      }
      Object localObject3;
      if (localObject4 != null)
      {
        localObject1 = ((AdvertisingIdClient.Info)localObject4).getId();
        continue;
        localObject3 = null;
      }
      try
      {
        AdvertisingIdClient.Info localInfo1 = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
        localObject3 = localInfo1;
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          UpsightLogger localUpsightLogger1 = this.mLogger;
          String str1 = LOG_TAG;
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = localException1;
          localUpsightLogger1.w(str1, "Unable to resolve Google limited ad tracking status", arrayOfObject1);
        }
      }
      if (localObject3 != null) {
        localObject1 = Boolean.valueOf(((AdvertisingIdClient.Info)localObject3).isLimitAdTrackingEnabled());
      }
    }
    label232:
    i = -1;
    label235:
    switch (i)
    {
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googleadvertisingid/internal/GooglePlayAdvertisingProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */