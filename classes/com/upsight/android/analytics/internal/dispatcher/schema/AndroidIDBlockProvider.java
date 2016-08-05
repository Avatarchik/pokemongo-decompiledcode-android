package com.upsight.android.analytics.internal.dispatcher.schema;

import android.content.Context;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.text.TextUtils;
import com.upsight.android.analytics.provider.UpsightDataProvider;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AndroidIDBlockProvider
  extends UpsightDataProvider
{
  public static final String ANDROID_ID_KEY = "ids.android_id";
  private static final String ANDROID_ID_NON_UNIQUE = "9774d56d682e549c";
  
  AndroidIDBlockProvider(Context paramContext)
  {
    put("ids.android_id", getAndroidID(paramContext));
  }
  
  private String getAndroidID(Context paramContext)
  {
    Object localObject = "9774d56d682e549c";
    String str1 = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    if ((!TextUtils.isEmpty(str1)) && (!((String)localObject).equals(str1))) {
      localObject = str1;
    }
    for (;;)
    {
      return (String)localObject;
      String str2 = Settings.System.getString(paramContext.getContentResolver(), "android_id");
      if (!TextUtils.isEmpty(str2)) {
        localObject = str2;
      }
    }
  }
  
  public Set<String> availableKeys()
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = "ids.android_id";
    return new HashSet(Arrays.asList(arrayOfString));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/schema/AndroidIDBlockProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */