package com.google.android.gms.internal;

import android.content.Context;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import java.util.List;

@zzgr
public class zzgk
  extends zzgf
{
  protected zzei zzDA;
  private zzec zzDz;
  private final zzcg zzoo;
  private zzem zzox;
  private zzee zzzA;
  
  zzgk(Context paramContext, zzhs.zza paramzza, zzem paramzzem, zzgg.zza paramzza1, zzcg paramzzcg)
  {
    super(paramContext, paramzza, paramzza1);
    this.zzox = paramzzem;
    this.zzzA = paramzza.zzHx;
    this.zzoo = paramzzcg;
  }
  
  public void onStop()
  {
    synchronized (this.zzDh)
    {
      super.onStop();
      if (this.zzDz != null) {
        this.zzDz.cancel();
      }
      return;
    }
  }
  
  protected zzhs zzA(int paramInt)
  {
    AdRequestInfoParcel localAdRequestInfoParcel = this.zzDe.zzHC;
    AdRequestParcel localAdRequestParcel = localAdRequestInfoParcel.zzEn;
    List localList1 = this.zzDf.zzyY;
    List localList2 = this.zzDf.zzyZ;
    List localList3 = this.zzDf.zzEM;
    int i = this.zzDf.orientation;
    long l = this.zzDf.zzzc;
    String str1 = localAdRequestInfoParcel.zzEq;
    boolean bool = this.zzDf.zzEK;
    zzed localzzed;
    zzen localzzen;
    label105:
    String str2;
    label121:
    zzee localzzee;
    if (this.zzDA != null)
    {
      localzzed = this.zzDA.zzzu;
      if (this.zzDA == null) {
        break label234;
      }
      localzzen = this.zzDA.zzzv;
      if (this.zzDA == null) {
        break label240;
      }
      str2 = this.zzDA.zzzw;
      localzzee = this.zzzA;
      if (this.zzDA == null) {
        break label250;
      }
    }
    label234:
    label240:
    label250:
    for (zzeg localzzeg = this.zzDA.zzzx;; localzzeg = null)
    {
      return new zzhs(localAdRequestParcel, null, localList1, paramInt, localList2, localList3, i, l, str1, bool, localzzed, localzzen, str2, localzzee, localzzeg, this.zzDf.zzEL, this.zzDe.zzqn, this.zzDf.zzEJ, this.zzDe.zzHz, this.zzDf.zzEO, this.zzDf.zzEP, this.zzDe.zzHw, null);
      localzzed = null;
      break;
      localzzen = null;
      break label105;
      str2 = AdMobAdapter.class.getName();
      break label121;
    }
  }
  
  protected void zzh(long paramLong)
    throws zzgf.zza
  {
    synchronized (this.zzDh)
    {
      this.zzDz = new zzek(this.mContext, this.zzDe.zzHC, this.zzox, this.zzzA, this.zzDf.zzth, paramLong, ((Long)zzby.zzvw.get()).longValue(), this.zzoo);
      this.zzDA = this.zzDz.zzc(this.zzzA.zzyW);
      switch (this.zzDA.zzzt)
      {
      default: 
        throw new zzgf.zza("Unexpected mediation result: " + this.zzDA.zzzt, 0);
      }
    }
    throw new zzgf.zza("No fill from any mediation ad networks.", 3);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */