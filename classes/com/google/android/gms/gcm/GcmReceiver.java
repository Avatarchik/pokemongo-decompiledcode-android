package com.google.android.gms.gcm;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Base64;

public class GcmReceiver
  extends WakefulBroadcastReceiver
{
  private static String zzaCn = "gcm.googleapis.com/refresh";
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramIntent.setComponent(null);
    paramIntent.setPackage(paramContext.getPackageName());
    if (Build.VERSION.SDK_INT <= 18) {
      paramIntent.removeCategory(paramContext.getPackageName());
    }
    String str1 = paramIntent.getStringExtra("from");
    if (("com.google.android.c2dm.intent.REGISTRATION".equals(paramIntent.getAction())) || ("google.com/iid".equals(str1)) || (zzaCn.equals(str1))) {
      paramIntent.setAction("com.google.android.gms.iid.InstanceID");
    }
    String str2 = paramIntent.getStringExtra("gcm.rawData64");
    if (str2 != null)
    {
      paramIntent.putExtra("rawData", Base64.decode(str2, 0));
      paramIntent.removeExtra("gcm.rawData64");
    }
    if ("com.google.android.c2dm.intent.RECEIVE".equals(paramIntent.getAction())) {
      zze(paramContext, paramIntent);
    }
    for (;;)
    {
      if (isOrderedBroadcast()) {
        setResultCode(-1);
      }
      return;
      startWakefulService(paramContext, paramIntent);
    }
  }
  
  public void zze(Context paramContext, Intent paramIntent)
  {
    startWakefulService(paramContext, paramIntent);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/gcm/GcmReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */