package com.upsight.android.internal.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public final class NetworkHelper
{
  public static final String NETWORK_OPERATOR_NONE = "none";
  public static final String NETWORK_TYPE_NONE = "no_network";
  
  public static String getActiveNetworkType(Context paramContext)
  {
    Object localObject = "no_network";
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (localConnectivityManager != null)
      {
        NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()))
        {
          String str = localNetworkInfo.getTypeName();
          boolean bool = TextUtils.isEmpty(str);
          if (!bool) {
            localObject = str;
          }
        }
      }
    }
    catch (SecurityException localSecurityException)
    {
      for (;;) {}
    }
    return (String)localObject;
  }
  
  public static String getNetworkOperatorName(Context paramContext)
  {
    Object localObject = "none";
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
      if (localTelephonyManager != null)
      {
        String str = localTelephonyManager.getNetworkOperatorName();
        localObject = str;
      }
    }
    catch (SecurityException localSecurityException)
    {
      for (;;) {}
    }
    return (String)localObject;
  }
  
  public static boolean isConnected(Context paramContext)
  {
    for (boolean bool1 = false;; bool1 = false)
    {
      try
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (localConnectivityManager != null)
        {
          NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
          if (localNetworkInfo == null) {
            continue;
          }
          boolean bool2 = localNetworkInfo.isConnected();
          if (!bool2) {
            continue;
          }
          bool1 = true;
        }
      }
      catch (SecurityException localSecurityException)
      {
        for (;;) {}
      }
      return bool1;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/util/NetworkHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */