package com.upsight.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class Upsight
{
  private static final String CORE_COMPONENT_FACTORY = "com.upsight.core";
  private static final String EXTENSION_PREFIX = "com.upsight.extension.";
  public static final String LOG_TAG = "Upsight";
  private static final int MIN_ANDROID_API_LEVEL = 14;
  private static UpsightContext sUpsight;
  
  static UpsightContext create(Context paramContext)
  {
    Object localObject;
    if (Build.VERSION.SDK_INT < 14) {
      localObject = new UpsightContextCompat(paramContext);
    }
    for (;;)
    {
      return (UpsightContext)localObject;
      UpsightCoreComponent localUpsightCoreComponent = loadCoreComponent(paramContext);
      Map localMap = loadExtensions(paramContext);
      localObject = localUpsightCoreComponent.upsightContext();
      ((UpsightContext)localObject).onCreate(localUpsightCoreComponent, localMap);
    }
  }
  
  /**
   * @deprecated
   */
  public static UpsightContext createContext(Context paramContext)
  {
    try
    {
      if (sUpsight == null) {
        sUpsight = create(paramContext);
      }
      UpsightContext localUpsightContext = sUpsight;
      return localUpsightContext;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private static UpsightCoreComponent loadCoreComponent(Context paramContext)
  {
    Pair localPair = loadMetadataByName(paramContext, "com.upsight.core");
    if (localPair != null) {}
    for (;;)
    {
      try
      {
        localClass = Class.forName((String)localPair.second);
        if (!UpsightCoreComponent.Factory.class.isAssignableFrom(localClass))
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = localClass.getName();
          arrayOfObject[1] = UpsightCoreComponent.Factory.class.getName();
          throw new IllegalStateException(String.format("Class %s must implement %s", arrayOfObject));
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        Class localClass;
        throw new IllegalStateException(localClassNotFoundException.getMessage(), localClassNotFoundException);
        UpsightCoreComponent localUpsightCoreComponent2 = ((UpsightCoreComponent.Factory)localClass.newInstance()).create(paramContext);
        localUpsightCoreComponent1 = localUpsightCoreComponent2;
        return localUpsightCoreComponent1;
      }
      catch (InstantiationException localInstantiationException)
      {
        throw new IllegalStateException(localInstantiationException.getMessage(), localInstantiationException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new IllegalStateException(localIllegalAccessException.getMessage(), localIllegalAccessException);
      }
      UpsightCoreComponent localUpsightCoreComponent1 = null;
    }
  }
  
  private static Map<String, UpsightExtension> loadExtensions(Context paramContext)
    throws IllegalStateException
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = loadMetadataByPrefix(paramContext, "com.upsight.extension.").entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      try
      {
        localClass = Class.forName((String)localEntry.getValue());
        if (!UpsightExtension.class.isAssignableFrom(localClass))
        {
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = localClass.getName();
          arrayOfObject[1] = UpsightExtension.class.getName();
          throw new IllegalStateException(String.format("Class %s must implement %s", arrayOfObject));
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        Class localClass;
        throw new IllegalStateException("Unable to load extension: " + (String)localEntry.getKey(), localClassNotFoundException);
        localHashMap.put(localEntry.getKey(), (UpsightExtension)localClass.newInstance());
      }
      catch (InstantiationException localInstantiationException)
      {
        throw new IllegalStateException("Unable to load extension: " + (String)localEntry.getKey(), localInstantiationException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new IllegalStateException("Unable to load extension: " + (String)localEntry.getKey(), localIllegalAccessException);
      }
    }
    return localHashMap;
  }
  
  private static Pair<String, String> loadMetadataByName(Context paramContext, String paramString)
  {
    Object localObject = null;
    try
    {
      Bundle localBundle = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
      if (localBundle != null)
      {
        Iterator localIterator = localBundle.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str1 = (String)localIterator.next();
          if ((!TextUtils.isEmpty(str1)) && (str1.equals(paramString)))
          {
            String str2 = localBundle.getString(str1);
            if (!TextUtils.isEmpty(str2))
            {
              Pair localPair = new Pair(str1, str2);
              localObject = localPair;
            }
          }
        }
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Log.e("Upsight", "Unexpected error: Package name missing", localNameNotFoundException);
      }
    }
    return (Pair<String, String>)localObject;
  }
  
  private static Map<String, String> loadMetadataByPrefix(Context paramContext, String paramString)
  {
    localHashMap = new HashMap();
    try
    {
      Bundle localBundle = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
      if (localBundle != null)
      {
        Iterator localIterator = localBundle.keySet().iterator();
        while (localIterator.hasNext())
        {
          String str1 = (String)localIterator.next();
          if ((!TextUtils.isEmpty(str1)) && (str1.startsWith(paramString)))
          {
            String str2 = localBundle.getString(str1);
            if (!TextUtils.isEmpty(str2)) {
              localHashMap.put(str1, str2);
            }
          }
        }
      }
      return localHashMap;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Log.e("Upsight", "Unexpected error: Package name missing", localNameNotFoundException);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/Upsight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */