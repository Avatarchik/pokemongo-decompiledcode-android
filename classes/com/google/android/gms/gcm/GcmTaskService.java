package com.google.android.gms.gcm;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

public abstract class GcmTaskService
  extends Service
{
  public static final String SERVICE_ACTION_EXECUTE_TASK = "com.google.android.gms.gcm.ACTION_TASK_READY";
  public static final String SERVICE_ACTION_INITIALIZE = "com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE";
  public static final String SERVICE_PERMISSION = "com.google.android.gms.permission.BIND_NETWORK_TASK_SERVICE";
  private final Set<String> zzaCo = new HashSet();
  private int zzaCp;
  
  private void zzdm(String paramString)
  {
    synchronized (this.zzaCo)
    {
      this.zzaCo.remove(paramString);
      if (this.zzaCo.size() == 0) {
        stopSelf(this.zzaCp);
      }
      return;
    }
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onInitializeTasks() {}
  
  public abstract int onRunTask(TaskParams paramTaskParams);
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    paramIntent.setExtrasClassLoader(PendingCallback.class.getClassLoader());
    String str;
    Parcelable localParcelable;
    Bundle localBundle;
    if ("com.google.android.gms.gcm.ACTION_TASK_READY".equals(paramIntent.getAction()))
    {
      str = paramIntent.getStringExtra("tag");
      localParcelable = paramIntent.getParcelableExtra("callback");
      localBundle = (Bundle)paramIntent.getParcelableExtra("extras");
      if ((localParcelable == null) || (!(localParcelable instanceof PendingCallback))) {
        Log.e("GcmTaskService", getPackageName() + " " + str + ": Could not process request, invalid callback.");
      }
    }
    for (;;)
    {
      return 2;
      synchronized (this.zzaCo)
      {
        this.zzaCo.add(str);
        stopSelf(this.zzaCp);
        this.zzaCp = paramInt2;
        new zza(str, ((PendingCallback)localParcelable).getIBinder(), localBundle).start();
      }
      if (!"com.google.android.gms.gcm.SERVICE_ACTION_INITIALIZE".equals(paramIntent.getAction())) {
        continue;
      }
      onInitializeTasks();
      synchronized (this.zzaCo)
      {
        this.zzaCp = paramInt2;
        if (this.zzaCo.size() == 0) {
          stopSelf(this.zzaCp);
        }
      }
    }
  }
  
  private class zza
    extends Thread
  {
    private final Bundle mExtras;
    private final String mTag;
    private final zzb zzaCq;
    
    zza(String paramString, IBinder paramIBinder, Bundle paramBundle)
    {
      this.mTag = paramString;
      this.zzaCq = zzb.zza.zzbR(paramIBinder);
      this.mExtras = paramBundle;
    }
    
    public void run()
    {
      int i = GcmTaskService.this.onRunTask(new TaskParams(this.mTag, this.mExtras));
      try
      {
        this.zzaCq.zzgB(i);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          Log.e("GcmTaskService", "Error reporting result of operation to scheduler for " + this.mTag);
          GcmTaskService.zza(GcmTaskService.this, this.mTag);
        }
      }
      finally
      {
        GcmTaskService.zza(GcmTaskService.this, this.mTag);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/gcm/GcmTaskService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */