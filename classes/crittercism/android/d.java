package crittercism.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class d
{
  private ConnectivityManager a;
  
  public d(Context paramContext)
  {
    if (paramContext == null) {
      dx.b("Given a null Context.");
    }
    for (;;)
    {
      return;
      if (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) == 0) {
        this.a = ((ConnectivityManager)paramContext.getSystemService("connectivity"));
      } else {
        dx.b("Add android.permission.ACCESS_NETWORK_STATE to AndroidManifest.xml to get more detailed OPTMZ data");
      }
    }
  }
  
  public final b a()
  {
    b localb;
    if (this.a == null) {
      localb = b.c;
    }
    for (;;)
    {
      return localb;
      NetworkInfo localNetworkInfo = this.a.getActiveNetworkInfo();
      if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected())) {
        localb = b.d;
      } else {
        localb = b.a(localNetworkInfo.getType());
      }
    }
  }
  
  public final String b()
  {
    String str;
    if (this.a == null) {
      str = "unknown";
    }
    for (;;)
    {
      return str;
      NetworkInfo localNetworkInfo = this.a.getActiveNetworkInfo();
      if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
      {
        str = "disconnected";
      }
      else
      {
        int i = localNetworkInfo.getType();
        if (i == 0) {
          switch (localNetworkInfo.getSubtype())
          {
          }
        }
        while (i != 1)
        {
          str = "unknown";
          break;
          str = "2G";
          break;
          str = "3G";
          break;
          str = "LTE";
          break;
        }
        str = "wifi";
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */