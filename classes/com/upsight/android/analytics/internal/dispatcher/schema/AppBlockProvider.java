package com.upsight.android.analytics.internal.dispatcher.schema;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.provider.UpsightDataProvider;
import com.upsight.android.logger.UpsightLogger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AppBlockProvider
  extends UpsightDataProvider
{
  public static final String BUNDLEID_KEY = "app.bundleid";
  public static final String TOKEN_KEY = "app.token";
  public static final String VERSION_KEY = "app.version";
  private final UpsightLogger mLogger;
  
  AppBlockProvider(UpsightContext paramUpsightContext)
  {
    put("app.token", paramUpsightContext.getApplicationToken());
    this.mLogger = paramUpsightContext.getLogger();
    PackageInfo localPackageInfo = getPackageInfo(paramUpsightContext);
    if (localPackageInfo != null)
    {
      put("app.version", localPackageInfo.versionName);
      put("app.bundleid", localPackageInfo.packageName);
    }
  }
  
  private PackageInfo getPackageInfo(Context paramContext)
  {
    Object localObject = null;
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      localObject = localPackageInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        UpsightLogger localUpsightLogger = this.mLogger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localNameNotFoundException;
        localUpsightLogger.e("AppBlock", "Could not get package info", arrayOfObject);
      }
    }
    return (PackageInfo)localObject;
  }
  
  public Set<String> availableKeys()
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "app.token";
    arrayOfString[1] = "app.version";
    arrayOfString[2] = "app.bundleid";
    return new HashSet(Arrays.asList(arrayOfString));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/schema/AppBlockProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */