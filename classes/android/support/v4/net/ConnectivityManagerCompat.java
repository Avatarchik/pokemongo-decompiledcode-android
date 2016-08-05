package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;

public class ConnectivityManagerCompat
{
  private static final ConnectivityManagerCompatImpl IMPL;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 16) {
      IMPL = new JellyBeanConnectivityManagerCompatImpl();
    }
    for (;;)
    {
      return;
      if (Build.VERSION.SDK_INT >= 13) {
        IMPL = new HoneycombMR2ConnectivityManagerCompatImpl();
      } else if (Build.VERSION.SDK_INT >= 8) {
        IMPL = new GingerbreadConnectivityManagerCompatImpl();
      } else {
        IMPL = new BaseConnectivityManagerCompatImpl();
      }
    }
  }
  
  public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager paramConnectivityManager, Intent paramIntent)
  {
    NetworkInfo localNetworkInfo1 = (NetworkInfo)paramIntent.getParcelableExtra("networkInfo");
    if (localNetworkInfo1 != null) {}
    for (NetworkInfo localNetworkInfo2 = paramConnectivityManager.getNetworkInfo(localNetworkInfo1.getType());; localNetworkInfo2 = null) {
      return localNetworkInfo2;
    }
  }
  
  public static boolean isActiveNetworkMetered(ConnectivityManager paramConnectivityManager)
  {
    return IMPL.isActiveNetworkMetered(paramConnectivityManager);
  }
  
  static class JellyBeanConnectivityManagerCompatImpl
    implements ConnectivityManagerCompat.ConnectivityManagerCompatImpl
  {
    public boolean isActiveNetworkMetered(ConnectivityManager paramConnectivityManager)
    {
      return ConnectivityManagerCompatJellyBean.isActiveNetworkMetered(paramConnectivityManager);
    }
  }
  
  static class HoneycombMR2ConnectivityManagerCompatImpl
    implements ConnectivityManagerCompat.ConnectivityManagerCompatImpl
  {
    public boolean isActiveNetworkMetered(ConnectivityManager paramConnectivityManager)
    {
      return ConnectivityManagerCompatHoneycombMR2.isActiveNetworkMetered(paramConnectivityManager);
    }
  }
  
  static class GingerbreadConnectivityManagerCompatImpl
    implements ConnectivityManagerCompat.ConnectivityManagerCompatImpl
  {
    public boolean isActiveNetworkMetered(ConnectivityManager paramConnectivityManager)
    {
      return ConnectivityManagerCompatGingerbread.isActiveNetworkMetered(paramConnectivityManager);
    }
  }
  
  static class BaseConnectivityManagerCompatImpl
    implements ConnectivityManagerCompat.ConnectivityManagerCompatImpl
  {
    public boolean isActiveNetworkMetered(ConnectivityManager paramConnectivityManager)
    {
      boolean bool = true;
      NetworkInfo localNetworkInfo = paramConnectivityManager.getActiveNetworkInfo();
      if (localNetworkInfo == null) {}
      for (;;)
      {
        return bool;
        switch (localNetworkInfo.getType())
        {
        case 0: 
        default: 
          break;
        case 1: 
          bool = false;
        }
      }
    }
  }
  
  static abstract interface ConnectivityManagerCompatImpl
  {
    public abstract boolean isActiveNetworkMetered(ConnectivityManager paramConnectivityManager);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/net/ConnectivityManagerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */