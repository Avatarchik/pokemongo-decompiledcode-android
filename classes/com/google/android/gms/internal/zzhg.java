package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.zzx;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

@zzgr
public class zzhg
  extends com.google.android.gms.ads.internal.zzb
  implements zzhk
{
  private zzd zzGX;
  private String zzGY;
  private boolean zzGZ;
  private HashMap<String, zzhh> zzHa = new HashMap();
  
  public zzhg(Context paramContext, AdSizeParcel paramAdSizeParcel, zzem paramzzem, VersionInfoParcel paramVersionInfoParcel)
  {
    super(paramContext, paramAdSizeParcel, null, paramzzem, paramVersionInfoParcel, null);
  }
  
  public void destroy()
  {
    zzx.zzci("destroy must be called on the main UI thread.");
    Iterator localIterator = this.zzHa.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      try
      {
        zzhh localzzhh = (zzhh)this.zzHa.get(str);
        if ((localzzhh != null) && (localzzhh.zzgc() != null)) {
          localzzhh.zzgc().destroy();
        }
      }
      catch (RemoteException localRemoteException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Fail to destroy adapter: " + str);
      }
    }
  }
  
  public boolean isLoaded()
  {
    zzx.zzci("isLoaded must be called on the main UI thread.");
    if ((this.zzot.zzql == null) && (this.zzot.zzqm == null) && (this.zzot.zzqo != null) && (!this.zzGZ)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void onRewardedVideoAdClosed()
  {
    if (this.zzGX == null) {}
    for (;;)
    {
      return;
      try
      {
        this.zzGX.onRewardedVideoAdClosed();
      }
      catch (RemoteException localRemoteException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call RewardedVideoAdListener.onAdClosed().", localRemoteException);
      }
    }
  }
  
  public void onRewardedVideoAdLeftApplication()
  {
    if (this.zzGX == null) {}
    for (;;)
    {
      return;
      try
      {
        this.zzGX.onRewardedVideoAdLeftApplication();
      }
      catch (RemoteException localRemoteException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call RewardedVideoAdListener.onAdLeftApplication().", localRemoteException);
      }
    }
  }
  
  public void onRewardedVideoAdOpened()
  {
    zza(this.zzot.zzqo, false);
    if (this.zzGX == null) {}
    for (;;)
    {
      return;
      try
      {
        this.zzGX.onRewardedVideoAdOpened();
      }
      catch (RemoteException localRemoteException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call RewardedVideoAdListener.onAdOpened().", localRemoteException);
      }
    }
  }
  
  public void onRewardedVideoStarted()
  {
    zzp.zzbH().zza(this.zzot.context, this.zzot.zzqj.zzJu, this.zzot.zzqo, this.zzot.zzqh, false, this.zzot.zzqo.zzzu.zzyU);
    if (this.zzGX == null) {}
    for (;;)
    {
      return;
      try
      {
        this.zzGX.onRewardedVideoStarted();
      }
      catch (RemoteException localRemoteException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call RewardedVideoAdListener.onVideoStarted().", localRemoteException);
      }
    }
  }
  
  public void pause()
  {
    zzx.zzci("pause must be called on the main UI thread.");
    Iterator localIterator = this.zzHa.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      try
      {
        zzhh localzzhh = (zzhh)this.zzHa.get(str);
        if ((localzzhh != null) && (localzzhh.zzgc() != null)) {
          localzzhh.zzgc().pause();
        }
      }
      catch (RemoteException localRemoteException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Fail to pause adapter: " + str);
      }
    }
  }
  
  public void resume()
  {
    zzx.zzci("resume must be called on the main UI thread.");
    Iterator localIterator = this.zzHa.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      try
      {
        zzhh localzzhh = (zzhh)this.zzHa.get(str);
        if ((localzzhh != null) && (localzzhh.zzgc() != null)) {
          localzzhh.zzgc().resume();
        }
      }
      catch (RemoteException localRemoteException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Fail to resume adapter: " + str);
      }
    }
  }
  
  public void setUserId(String paramString)
  {
    zzx.zzci("setUserId must be called on the main UI thread.");
    this.zzGY = paramString;
  }
  
  public void zza(RewardedVideoAdRequestParcel paramRewardedVideoAdRequestParcel)
  {
    zzx.zzci("loadAd must be called on the main UI thread.");
    if (TextUtils.isEmpty(paramRewardedVideoAdRequestParcel.zzqh)) {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("Invalid ad unit id. Aborting.");
    }
    for (;;)
    {
      return;
      this.zzGZ = false;
      this.zzot.zzqh = paramRewardedVideoAdRequestParcel.zzqh;
      super.zzb(paramRewardedVideoAdRequestParcel.zzEn);
    }
  }
  
  public void zza(zzd paramzzd)
  {
    zzx.zzci("setRewardedVideoAdListener must be called on the main UI thread.");
    this.zzGX = paramzzd;
  }
  
  public void zza(RewardItemParcel paramRewardItemParcel)
  {
    zzp.zzbH().zza(this.zzot.context, this.zzot.zzqj.zzJu, this.zzot.zzqo, this.zzot.zzqh, false, this.zzot.zzqo.zzzu.zzyV);
    if (this.zzGX == null) {}
    for (;;)
    {
      return;
      try
      {
        if ((this.zzot.zzqo == null) || (this.zzot.zzqo.zzHx == null) || (TextUtils.isEmpty(this.zzot.zzqo.zzHx.zzzd))) {
          break label157;
        }
        this.zzGX.zza(new zzhe(this.zzot.zzqo.zzHx.zzzd, this.zzot.zzqo.zzHx.zzze));
      }
      catch (RemoteException localRemoteException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call RewardedVideoAdListener.onRewarded().", localRemoteException);
      }
      continue;
      label157:
      this.zzGX.zza(new zzhe(paramRewardItemParcel.type, paramRewardItemParcel.zzHv));
    }
  }
  
  public void zza(final zzhs.zza paramzza, zzcg paramzzcg)
  {
    if (paramzza.errorCode != -2) {
      zzid.zzIE.post(new Runnable()
      {
        public void run()
        {
          zzhg.this.zzb(new zzhs(paramzza, null, null, null, null, null, null));
        }
      });
    }
    for (;;)
    {
      return;
      this.zzot.zzqH = 0;
      this.zzot.zzqm = new zzhn(this.zzot.context, this.zzGY, paramzza, this);
      com.google.android.gms.ads.internal.util.client.zzb.zzaF("AdRenderer: " + this.zzot.zzqm.getClass().getName());
      this.zzot.zzqm.zzfu();
    }
  }
  
  public boolean zza(zzhs paramzzhs1, zzhs paramzzhs2)
  {
    if (this.zzGX != null) {}
    try
    {
      this.zzGX.onRewardedVideoAdLoaded();
      return true;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call RewardedVideoAdListener.onAdLoaded().", localRemoteException);
      }
    }
  }
  
  /* Error */
  public zzhh zzau(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 30	com/google/android/gms/internal/zzhg:zzHa	Ljava/util/HashMap;
    //   4: aload_1
    //   5: invokevirtual 67	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   8: checkcast 69	com/google/android/gms/internal/zzhh
    //   11: astore_2
    //   12: aload_2
    //   13: ifnonnull +34 -> 47
    //   16: new 69	com/google/android/gms/internal/zzhh
    //   19: dup
    //   20: aload_0
    //   21: getfield 332	com/google/android/gms/internal/zzhg:zzox	Lcom/google/android/gms/internal/zzem;
    //   24: aload_1
    //   25: invokeinterface 338 2 0
    //   30: aload_0
    //   31: invokespecial 341	com/google/android/gms/internal/zzhh:<init>	(Lcom/google/android/gms/internal/zzen;Lcom/google/android/gms/internal/zzhk;)V
    //   34: astore_3
    //   35: aload_0
    //   36: getfield 30	com/google/android/gms/internal/zzhg:zzHa	Ljava/util/HashMap;
    //   39: aload_1
    //   40: aload_3
    //   41: invokevirtual 345	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   44: pop
    //   45: aload_3
    //   46: astore_2
    //   47: aload_2
    //   48: areturn
    //   49: astore 5
    //   51: new 79	java/lang/StringBuilder
    //   54: dup
    //   55: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   58: ldc_w 347
    //   61: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: aload_1
    //   65: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: invokevirtual 90	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   71: aload 5
    //   73: invokestatic 131	com/google/android/gms/ads/internal/util/client/zzb:zzd	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   76: goto -29 -> 47
    //   79: astore 4
    //   81: aload_3
    //   82: astore_2
    //   83: aload 4
    //   85: astore 5
    //   87: goto -36 -> 51
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	90	0	this	zzhg
    //   0	90	1	paramString	String
    //   11	72	2	localObject1	Object
    //   34	48	3	localzzhh	zzhh
    //   79	5	4	localException1	Exception
    //   49	23	5	localException2	Exception
    //   85	1	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   16	35	49	java/lang/Exception
    //   35	45	79	java/lang/Exception
  }
  
  protected boolean zze(int paramInt)
  {
    boolean bool = false;
    com.google.android.gms.ads.internal.util.client.zzb.zzaH("Failed to load ad: " + paramInt);
    if (this.zzGX == null) {}
    for (;;)
    {
      return bool;
      try
      {
        this.zzGX.onRewardedVideoAdFailedToLoad(paramInt);
        bool = true;
      }
      catch (RemoteException localRemoteException)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call RewardedVideoAdListener.onAdFailedToLoad().", localRemoteException);
      }
    }
  }
  
  public void zzga()
  {
    zzx.zzci("showAd must be called on the main UI thread.");
    if (!isLoaded()) {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("The reward video has not loaded.");
    }
    for (;;)
    {
      return;
      this.zzGZ = true;
      zzhh localzzhh = zzau(this.zzot.zzqo.zzzw);
      if ((localzzhh != null) && (localzzhh.zzgc() != null)) {
        try
        {
          localzzhh.zzgc().showVideo();
        }
        catch (RemoteException localRemoteException)
        {
          com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not call showVideo.", localRemoteException);
        }
      }
    }
  }
  
  public void zzgb()
  {
    onAdClicked();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzhg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */