package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.zzfv.zza;
import com.google.android.gms.internal.zzgr;

@zzgr
public final class zzg
  extends zzfv.zza
  implements ServiceConnection
{
  private Context mContext;
  zzb zzCD;
  private String zzCJ;
  private zzf zzCN;
  private boolean zzCT = false;
  private int zzCU;
  private Intent zzCV;
  
  public zzg(Context paramContext, String paramString, boolean paramBoolean, int paramInt, Intent paramIntent, zzf paramzzf)
  {
    this.zzCJ = paramString;
    this.zzCU = paramInt;
    this.zzCV = paramIntent;
    this.zzCT = paramBoolean;
    this.mContext = paramContext;
    this.zzCN = paramzzf;
  }
  
  public void finishPurchase()
  {
    int i = zzp.zzbF().zzd(this.zzCV);
    if ((this.zzCU != -1) || (i != 0)) {}
    for (;;)
    {
      return;
      this.zzCD = new zzb(this.mContext);
      Intent localIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
      localIntent.setPackage("com.android.vending");
      com.google.android.gms.common.stats.zzb.zzqh().zza(this.mContext, localIntent, this, 1);
    }
  }
  
  public String getProductId()
  {
    return this.zzCJ;
  }
  
  public Intent getPurchaseData()
  {
    return this.zzCV;
  }
  
  public int getResultCode()
  {
    return this.zzCU;
  }
  
  public boolean isVerified()
  {
    return this.zzCT;
  }
  
  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    com.google.android.gms.ads.internal.util.client.zzb.zzaG("In-app billing service connected.");
    this.zzCD.zzN(paramIBinder);
    String str1 = zzp.zzbF().zze(this.zzCV);
    String str2 = zzp.zzbF().zzap(str1);
    if (str2 == null) {}
    for (;;)
    {
      return;
      if (this.zzCD.zzi(this.mContext.getPackageName(), str2) == 0) {
        zzh.zzw(this.mContext).zza(this.zzCN);
      }
      com.google.android.gms.common.stats.zzb.zzqh().zza(this.mContext, this);
      this.zzCD.destroy();
    }
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    com.google.android.gms.ads.internal.util.client.zzb.zzaG("In-app billing service disconnected.");
    this.zzCD.destroy();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/purchase/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */