package com.upsight.android.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import com.upsight.android.logger.UpsightLogger;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public final class PropertiesModule
{
  public static final String KEY_APP_TOKEN = "com.upsight.app_token";
  public static final String KEY_PUBLIC_KEY = "com.upsight.public_key";
  public static final String KEY_SDK_PLUGIN = "com.upsight.sdk_plugin";
  
  @Provides
  @Named("com.upsight.app_token")
  @Singleton
  String provideApplicationToken(Context paramContext, UpsightLogger paramUpsightLogger)
  {
    Object localObject = null;
    try
    {
      Bundle localBundle = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
      if (localBundle != null)
      {
        String str = localBundle.getString("com.upsight.app_token");
        localObject = str;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localNameNotFoundException;
        paramUpsightLogger.e("Upsight", "Unexpected error: Package name missing", arrayOfObject);
      }
    }
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      throw new IllegalStateException("App token must be set in the Android Manifest with <meta-data android:name=\"com.upsight.app_token\" android:value=\"UPSIGHT_APPLICATION_TOKEN\" />");
    }
    return (String)localObject;
  }
  
  @Provides
  @Named("com.upsight.public_key")
  @Singleton
  String providePublicKey(Context paramContext, UpsightLogger paramUpsightLogger)
  {
    Object localObject = null;
    try
    {
      Bundle localBundle = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
      if (localBundle != null)
      {
        String str = localBundle.getString("com.upsight.public_key");
        localObject = str;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localNameNotFoundException;
        paramUpsightLogger.e("Upsight", "Unexpected error: Package name missing", arrayOfObject);
      }
    }
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      throw new IllegalStateException("Public key must be set in the Android Manifest with <meta-data android:name=\"com.upsight.public_key\" android:value=\"UPSIGHT_PUBLIC_KEY\" />");
    }
    return (String)localObject;
  }
  
  @Provides
  @Named("com.upsight.sdk_plugin")
  @Singleton
  String provideSdkPlugin(Context paramContext, UpsightLogger paramUpsightLogger)
  {
    Object localObject = "";
    try
    {
      Bundle localBundle = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
      if (localBundle != null)
      {
        String str = localBundle.getString("com.upsight.sdk_plugin", "");
        localObject = str;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localNameNotFoundException;
        paramUpsightLogger.e("Upsight", "Unexpected error: Package name missing", arrayOfObject);
      }
    }
    return (String)localObject;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/PropertiesModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */