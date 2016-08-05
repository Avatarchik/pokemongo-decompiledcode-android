package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzx;
import java.util.Iterator;
import java.util.List;

public class GcmNetworkManager
{
  public static final int RESULT_FAILURE = 2;
  public static final int RESULT_RESCHEDULE = 1;
  public static final int RESULT_SUCCESS;
  private static GcmNetworkManager zzaCh;
  private Context mContext;
  private final PendingIntent mPendingIntent;
  
  private GcmNetworkManager(Context paramContext)
  {
    this.mContext = paramContext;
    this.mPendingIntent = PendingIntent.getBroadcast(this.mContext, 0, new Intent(), 0);
  }
  
  public static GcmNetworkManager getInstance(Context paramContext)
  {
    if (zzaCh == null) {
      zzaCh = new GcmNetworkManager(paramContext.getApplicationContext());
    }
    return zzaCh;
  }
  
  static void zzdh(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("Must provide a valid tag.");
    }
    if (100 < paramString.length()) {
      throw new IllegalArgumentException("Tag is larger than max permissible tag length (100)");
    }
  }
  
  private void zzdi(String paramString)
  {
    boolean bool1 = true;
    zzx.zzb(paramString, "GcmTaskService must not be null.");
    Intent localIntent = new Intent("com.google.android.gms.gcm.ACTION_TASK_READY");
    localIntent.setPackage(this.mContext.getPackageName());
    List localList = this.mContext.getPackageManager().queryIntentServices(localIntent, 0);
    boolean bool2;
    if ((localList != null) && (localList.size() != 0))
    {
      bool2 = bool1;
      zzx.zzb(bool2, "There is no GcmTaskService component registered within this package. Have you extended GcmTaskService correctly?");
      Iterator localIterator = localList.iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
      } while (!((ResolveInfo)localIterator.next()).serviceInfo.name.equals(paramString));
    }
    for (;;)
    {
      zzx.zzb(bool1, "The GcmTaskService class you provided " + paramString + " does not seem to support receiving" + " com.google.android.gms.gcm.ACTION_TASK_READY.");
      return;
      bool2 = false;
      break;
      bool1 = false;
    }
  }
  
  private Intent zzvV()
  {
    int i = GoogleCloudMessaging.zzaB(this.mContext);
    Intent localIntent;
    if (i < GoogleCloudMessaging.zzaCs)
    {
      Log.e("GcmNetworkManager", "Google Play Services is not available, dropping GcmNetworkManager request. code=" + i);
      localIntent = null;
    }
    for (;;)
    {
      return localIntent;
      localIntent = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE");
      localIntent.setPackage(GoogleCloudMessaging.zzaA(this.mContext));
      localIntent.putExtra("app", this.mPendingIntent);
    }
  }
  
  public void cancelAllTasks(Class<? extends GcmTaskService> paramClass)
  {
    zzdi(paramClass.getName());
    Intent localIntent = zzvV();
    if (localIntent == null) {}
    for (;;)
    {
      return;
      localIntent.putExtra("scheduler_action", "CANCEL_ALL");
      localIntent.putExtra("component", new ComponentName(this.mContext, paramClass));
      this.mContext.sendBroadcast(localIntent);
    }
  }
  
  public void cancelTask(String paramString, Class<? extends GcmTaskService> paramClass)
  {
    zzdh(paramString);
    zzdi(paramClass.getName());
    Intent localIntent = zzvV();
    if (localIntent == null) {}
    for (;;)
    {
      return;
      localIntent.putExtra("scheduler_action", "CANCEL_TASK");
      localIntent.putExtra("tag", paramString);
      localIntent.putExtra("component", new ComponentName(this.mContext, paramClass));
      this.mContext.sendBroadcast(localIntent);
    }
  }
  
  public void schedule(Task paramTask)
  {
    zzdi(paramTask.getServiceName());
    Intent localIntent = zzvV();
    if (localIntent == null) {}
    for (;;)
    {
      return;
      Bundle localBundle = localIntent.getExtras();
      localBundle.putString("scheduler_action", "SCHEDULE_TASK");
      paramTask.toBundle(localBundle);
      localIntent.putExtras(localBundle);
      this.mContext.sendBroadcast(localIntent);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/gcm/GcmNetworkManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */