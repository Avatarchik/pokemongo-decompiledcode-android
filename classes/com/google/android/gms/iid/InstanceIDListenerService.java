package com.google.android.gms.iid;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.gcm.GcmReceiver;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import java.io.IOException;

public class InstanceIDListenerService
  extends Service
{
  static String ACTION = "action";
  private static String zzaCn = "gcm.googleapis.com/refresh";
  private static String zzaDF = "google.com/iid";
  private static String zzaDG = "CMD";
  MessengerCompat zzaDD = new MessengerCompat(new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      InstanceIDListenerService.zza(InstanceIDListenerService.this, paramAnonymousMessage, MessengerCompat.zzc(paramAnonymousMessage));
    }
  });
  BroadcastReceiver zzaDE = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (Log.isLoggable("InstanceID", 3))
      {
        paramAnonymousIntent.getStringExtra("registration_id");
        Log.d("InstanceID", "Received GSF callback using dynamic receiver: " + paramAnonymousIntent.getExtras());
      }
      InstanceIDListenerService.this.zzn(paramAnonymousIntent);
      InstanceIDListenerService.this.stop();
    }
  };
  int zzaDH;
  int zzaDI;
  
  static void zza(Context paramContext, zzd paramzzd)
  {
    paramzzd.zzwt();
    Intent localIntent = new Intent("com.google.android.gms.iid.InstanceID");
    localIntent.putExtra(zzaDG, "RST");
    localIntent.setPackage(paramContext.getPackageName());
    paramContext.startService(localIntent);
  }
  
  private void zza(Message paramMessage, int paramInt)
  {
    zzc.zzaE(this);
    getPackageManager();
    if ((paramInt != zzc.zzaDP) && (paramInt != zzc.zzaDO)) {
      Log.w("InstanceID", "Message from unexpected caller " + paramInt + " mine=" + zzc.zzaDO + " appid=" + zzc.zzaDP);
    }
    for (;;)
    {
      return;
      zzn((Intent)paramMessage.obj);
    }
  }
  
  static void zzaD(Context paramContext)
  {
    Intent localIntent = new Intent("com.google.android.gms.iid.InstanceID");
    localIntent.setPackage(paramContext.getPackageName());
    localIntent.putExtra(zzaDG, "SYNC");
    paramContext.startService(localIntent);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    if ((paramIntent != null) && ("com.google.android.gms.iid.InstanceID".equals(paramIntent.getAction()))) {}
    for (IBinder localIBinder = this.zzaDD.getBinder();; localIBinder = null) {
      return localIBinder;
    }
  }
  
  public void onCreate()
  {
    IntentFilter localIntentFilter = new IntentFilter("com.google.android.c2dm.intent.REGISTRATION");
    localIntentFilter.addCategory(getPackageName());
    registerReceiver(this.zzaDE, localIntentFilter, "com.google.android.c2dm.permission.RECEIVE", null);
  }
  
  public void onDestroy()
  {
    unregisterReceiver(this.zzaDE);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    zzgI(paramInt2);
    int i;
    if (paramIntent == null)
    {
      stop();
      i = 2;
    }
    for (;;)
    {
      return i;
      try
      {
        if ("com.google.android.gms.iid.InstanceID".equals(paramIntent.getAction()))
        {
          if (Build.VERSION.SDK_INT <= 18)
          {
            Intent localIntent = (Intent)paramIntent.getParcelableExtra("GSF");
            if (localIntent != null)
            {
              startService(localIntent);
              i = 1;
              stop();
              continue;
            }
          }
          zzn(paramIntent);
        }
        stop();
        if (paramIntent.getStringExtra("from") != null) {
          GcmReceiver.completeWakefulIntent(paramIntent);
        }
        i = 2;
      }
      finally
      {
        stop();
      }
    }
  }
  
  public void onTokenRefresh() {}
  
  void stop()
  {
    try
    {
      this.zzaDH = (-1 + this.zzaDH);
      if (this.zzaDH == 0) {
        stopSelf(this.zzaDI);
      }
      if (Log.isLoggable("InstanceID", 3)) {
        Log.d("InstanceID", "Stop " + this.zzaDH + " " + this.zzaDI);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void zzag(boolean paramBoolean)
  {
    onTokenRefresh();
  }
  
  void zzgI(int paramInt)
  {
    try
    {
      this.zzaDH = (1 + this.zzaDH);
      if (paramInt > this.zzaDI) {
        this.zzaDI = paramInt;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void zzn(Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("subtype");
    InstanceID localInstanceID;
    String str2;
    if (str1 == null)
    {
      localInstanceID = InstanceID.getInstance(this);
      str2 = paramIntent.getStringExtra(zzaDG);
      if ((paramIntent.getStringExtra("error") == null) && (paramIntent.getStringExtra("registration_id") == null)) {
        break label116;
      }
      if (Log.isLoggable("InstanceID", 3)) {
        Log.d("InstanceID", "Register result in service " + str1);
      }
      localInstanceID.zzwp().zzr(paramIntent);
    }
    for (;;)
    {
      return;
      Bundle localBundle = new Bundle();
      localBundle.putString("subtype", str1);
      localInstanceID = InstanceID.zza(this, localBundle);
      break;
      label116:
      if (Log.isLoggable("InstanceID", 3)) {
        Log.d("InstanceID", "Service command " + str1 + " " + str2 + " " + paramIntent.getExtras());
      }
      if (paramIntent.getStringExtra("unregistered") != null)
      {
        zzd localzzd = localInstanceID.zzwo();
        if (str1 == null) {
          str1 = "";
        }
        localzzd.zzds(str1);
        localInstanceID.zzwp().zzr(paramIntent);
      }
      else if (zzaCn.equals(paramIntent.getStringExtra("from")))
      {
        localInstanceID.zzwo().zzds(str1);
        zzag(false);
      }
      else if ("RST".equals(str2))
      {
        localInstanceID.zzwn();
        zzag(true);
      }
      else if ("RST_FULL".equals(str2))
      {
        if (!localInstanceID.zzwo().isEmpty())
        {
          localInstanceID.zzwo().zzwt();
          zzag(true);
        }
      }
      else if ("SYNC".equals(str2))
      {
        localInstanceID.zzwo().zzds(str1);
        zzag(false);
      }
      else if ("PING".equals(str2))
      {
        try
        {
          GoogleCloudMessaging.getInstance(this).send(zzaDF, zzc.zzws(), 0L, paramIntent.getExtras());
        }
        catch (IOException localIOException)
        {
          Log.w("InstanceID", "Failed to send ping response");
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/iid/InstanceIDListenerService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */