package com.upsight.android.analytics.provider;

import com.upsight.android.UpsightAnalyticsExtension;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.UpsightAnalyticsApi;
import com.upsight.android.logger.UpsightLogger;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class UpsightDataProvider
{
  private final Map<String, Object> mCachedValues = new HashMap();
  
  public static void register(UpsightContext paramUpsightContext, UpsightDataProvider paramUpsightDataProvider)
  {
    UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
    if (localUpsightAnalyticsExtension != null) {
      localUpsightAnalyticsExtension.getApi().registerDataProvider(paramUpsightDataProvider);
    }
    for (;;)
    {
      return;
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
    }
  }
  
  public abstract Set<String> availableKeys();
  
  /**
   * @deprecated
   */
  public Object get(String paramString)
  {
    try
    {
      Object localObject2 = this.mCachedValues.get(paramString);
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw ((Throwable)localObject1);
    }
  }
  
  /**
   * @deprecated
   */
  protected void put(String paramString, Object paramObject)
  {
    try
    {
      this.mCachedValues.put(paramString, paramObject);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/provider/UpsightDataProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */