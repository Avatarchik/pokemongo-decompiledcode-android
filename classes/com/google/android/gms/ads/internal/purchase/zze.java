package com.google.android.gms.ads.internal.purchase;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.zzfr;
import com.google.android.gms.internal.zzft.zza;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzie;

@zzgr
public class zze
  extends zzft.zza
  implements ServiceConnection
{
  private final Activity mActivity;
  private zzb zzCD;
  zzh zzCE;
  private zzk zzCG;
  private Context zzCL;
  private zzfr zzCM;
  private zzf zzCN;
  private zzj zzCO;
  private String zzCP = null;
  
  public zze(Activity paramActivity)
  {
    this.mActivity = paramActivity;
    this.zzCE = zzh.zzw(this.mActivity.getApplicationContext());
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    boolean bool;
    if (paramInt1 == 1001) {
      bool = false;
    }
    for (;;)
    {
      try
      {
        int i = zzp.zzbF().zzd(paramIntent);
        if (paramInt2 == -1)
        {
          zzp.zzbF();
          if (i == 0)
          {
            if (this.zzCG.zza(this.zzCP, paramInt2, paramIntent)) {
              bool = true;
            }
            this.zzCM.recordPlayBillingResolution(i);
            this.mActivity.finish();
            zza(this.zzCM.getProductId(), bool, paramInt2, paramIntent);
            return;
          }
        }
      }
      catch (RemoteException localRemoteException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Fail to process purchase result.");
        this.mActivity.finish();
        this.zzCP = null;
        continue;
      }
      finally
      {
        this.zzCP = null;
      }
      this.zzCE.zza(this.zzCN);
    }
  }
  
  public void onCreate()
  {
    GInAppPurchaseManagerInfoParcel localGInAppPurchaseManagerInfoParcel = GInAppPurchaseManagerInfoParcel.zzc(this.mActivity.getIntent());
    this.zzCO = localGInAppPurchaseManagerInfoParcel.zzCy;
    this.zzCG = localGInAppPurchaseManagerInfoParcel.zzqE;
    this.zzCM = localGInAppPurchaseManagerInfoParcel.zzCw;
    this.zzCD = new zzb(this.mActivity.getApplicationContext());
    this.zzCL = localGInAppPurchaseManagerInfoParcel.zzCx;
    if (this.mActivity.getResources().getConfiguration().orientation == 2) {
      this.mActivity.setRequestedOrientation(zzp.zzbx().zzgG());
    }
    for (;;)
    {
      Intent localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
      localIntent.setPackage("com.android.vending");
      this.mActivity.bindService(localIntent, this, 1);
      return;
      this.mActivity.setRequestedOrientation(zzp.zzbx().zzgH());
    }
  }
  
  public void onDestroy()
  {
    this.mActivity.unbindService(this);
    this.zzCD.destroy();
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    this.zzCD.zzN(paramIBinder);
    try
    {
      this.zzCP = this.zzCG.zzfq();
      Bundle localBundle = this.zzCD.zzb(this.mActivity.getPackageName(), this.zzCM.getProductId(), this.zzCP);
      PendingIntent localPendingIntent = (PendingIntent)localBundle.getParcelable("BUY_INTENT");
      if (localPendingIntent == null)
      {
        int i = zzp.zzbF().zzc(localBundle);
        this.zzCM.recordPlayBillingResolution(i);
        zza(this.zzCM.getProductId(), false, i, null);
        this.mActivity.finish();
      }
      else
      {
        this.zzCN = new zzf(this.zzCM.getProductId(), this.zzCP);
        this.zzCE.zzb(this.zzCN);
        this.mActivity.startIntentSenderForResult(localPendingIntent.getIntentSender(), 1001, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
      }
    }
    catch (RemoteException localRemoteException)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzd("Error when connecting in-app billing service", localRemoteException);
      this.mActivity.finish();
    }
    catch (IntentSender.SendIntentException localSendIntentException)
    {
      for (;;) {}
    }
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    com.google.android.gms.ads.internal.util.client.zzb.zzaG("In-app billing service disconnected.");
    this.zzCD.destroy();
  }
  
  protected void zza(String paramString, boolean paramBoolean, int paramInt, Intent paramIntent)
  {
    if (this.zzCO != null) {
      this.zzCO.zza(paramString, paramBoolean, paramInt, paramIntent, this.zzCN);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/purchase/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */