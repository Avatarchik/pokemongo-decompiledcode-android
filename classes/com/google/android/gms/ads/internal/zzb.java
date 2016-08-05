package com.google.android.gms.ads.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzv;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.purchase.GInAppPurchaseManagerInfoParcel;
import com.google.android.gms.ads.internal.purchase.zzc;
import com.google.android.gms.ads.internal.purchase.zzf;
import com.google.android.gms.ads.internal.purchase.zzi;
import com.google.android.gms.ads.internal.purchase.zzj;
import com.google.android.gms.ads.internal.purchase.zzk;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza;
import com.google.android.gms.ads.internal.request.CapabilityParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzay;
import com.google.android.gms.internal.zzby;
import com.google.android.gms.internal.zzcg;
import com.google.android.gms.internal.zzdm;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzef;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzej;
import com.google.android.gms.internal.zzem;
import com.google.android.gms.internal.zzen;
import com.google.android.gms.internal.zzfp;
import com.google.android.gms.internal.zzfs;
import com.google.android.gms.internal.zzfw;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzhs;
import com.google.android.gms.internal.zzht;
import com.google.android.gms.internal.zzhu;
import com.google.android.gms.internal.zzhx;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zzie;
import com.google.android.gms.internal.zziz;
import com.google.android.gms.internal.zzmi;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@zzgr
public abstract class zzb
  extends zza
  implements com.google.android.gms.ads.internal.overlay.zzg, zzj, zzdm, zzef
{
  private final Messenger mMessenger;
  protected final zzem zzox;
  protected transient boolean zzoy;
  
  public zzb(Context paramContext, AdSizeParcel paramAdSizeParcel, String paramString, zzem paramzzem, VersionInfoParcel paramVersionInfoParcel, zzd paramzzd)
  {
    this(new zzq(paramContext, paramAdSizeParcel, paramString, paramVersionInfoParcel), paramzzem, null, paramzzd);
  }
  
  zzb(zzq paramzzq, zzem paramzzem, zzo paramzzo, zzd paramzzd)
  {
    super(paramzzq, paramzzo, paramzzd);
    this.zzox = paramzzem;
    this.mMessenger = new Messenger(new zzfp(this.zzot.context));
    this.zzoy = false;
  }
  
  private AdRequestInfoParcel.zza zza(AdRequestParcel paramAdRequestParcel, Bundle paramBundle)
  {
    ApplicationInfo localApplicationInfo = this.zzot.context.getApplicationInfo();
    PackageInfo localPackageInfo1;
    try
    {
      PackageInfo localPackageInfo2 = this.zzot.context.getPackageManager().getPackageInfo(localApplicationInfo.packageName, 0);
      localPackageInfo1 = localPackageInfo2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        int[] arrayOfInt;
        int m;
        int n;
        int i1;
        int i2;
        int i3;
        localPackageInfo1 = null;
      }
    }
    DisplayMetrics localDisplayMetrics = this.zzot.context.getResources().getDisplayMetrics();
    Bundle localBundle1 = null;
    if ((this.zzot.zzqk != null) && (this.zzot.zzqk.getParent() != null))
    {
      arrayOfInt = new int[2];
      this.zzot.zzqk.getLocationOnScreen(arrayOfInt);
      m = arrayOfInt[0];
      n = arrayOfInt[1];
      i1 = this.zzot.zzqk.getWidth();
      i2 = this.zzot.zzqk.getHeight();
      i3 = 0;
      if ((this.zzot.zzqk.isShown()) && (m + i1 > 0) && (n + i2 > 0) && (m <= localDisplayMetrics.widthPixels) && (n <= localDisplayMetrics.heightPixels)) {
        i3 = 1;
      }
      localBundle1 = new Bundle(5);
      localBundle1.putInt("x", m);
      localBundle1.putInt("y", n);
      localBundle1.putInt("width", i1);
      localBundle1.putInt("height", i2);
      localBundle1.putInt("visible", i3);
    }
    String str1 = zzp.zzby().zzgm();
    this.zzot.zzqq = new zzht(str1, this.zzot.zzqh);
    this.zzot.zzqq.zzi(paramAdRequestParcel);
    String str2 = zzp.zzbv().zza(this.zzot.context, this.zzot.zzqk, this.zzot.zzqn);
    long l1 = 0L;
    if (this.zzot.zzqu != null) {}
    try
    {
      long l2 = this.zzot.zzqu.getValue();
      l1 = l2;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        int i;
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Cannot get correlation id, default to 0.");
      }
    }
    String str3 = UUID.randomUUID().toString();
    Bundle localBundle2 = zzp.zzby().zza(this.zzot.context, this, str1);
    ArrayList localArrayList = new ArrayList();
    for (i = 0; i < this.zzot.zzqA.size(); i++) {
      localArrayList.add(this.zzot.zzqA.keyAt(i));
    }
    boolean bool1;
    if (this.zzot.zzqv != null)
    {
      bool1 = true;
      if ((this.zzot.zzqw == null) || (!zzp.zzby().zzgv())) {
        break label656;
      }
    }
    label656:
    for (boolean bool2 = true;; bool2 = false)
    {
      AdSizeParcel localAdSizeParcel = this.zzot.zzqn;
      String str4 = this.zzot.zzqh;
      String str5 = zzp.zzby().getSessionId();
      VersionInfoParcel localVersionInfoParcel = this.zzot.zzqj;
      List localList1 = this.zzot.zzqD;
      boolean bool3 = zzp.zzby().zzgq();
      Messenger localMessenger = this.mMessenger;
      int j = localDisplayMetrics.widthPixels;
      int k = localDisplayMetrics.heightPixels;
      float f = localDisplayMetrics.density;
      List localList2 = zzby.zzdf();
      String str6 = this.zzot.zzqg;
      NativeAdOptionsParcel localNativeAdOptionsParcel = this.zzot.zzqB;
      CapabilityParcel localCapabilityParcel = new CapabilityParcel(bool1, bool2);
      return new AdRequestInfoParcel.zza(localBundle1, paramAdRequestParcel, localAdSizeParcel, str4, localApplicationInfo, localPackageInfo1, str1, str5, localVersionInfoParcel, localBundle2, localList1, localArrayList, paramBundle, bool3, localMessenger, j, k, f, str2, l1, str3, localList2, str6, localNativeAdOptionsParcel, localCapabilityParcel, this.zzot.zzbR());
      bool1 = false;
      break;
    }
  }
  
  public String getMediationAdapterClassName()
  {
    if (this.zzot.zzqo == null) {}
    for (String str = null;; str = this.zzot.zzqo.zzzw) {
      return str;
    }
  }
  
  public void onAdClicked()
  {
    if (this.zzot.zzqo == null) {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("Ad state was null when trying to ping click URLs.");
    }
    for (;;)
    {
      return;
      if ((this.zzot.zzqo.zzHx != null) && (this.zzot.zzqo.zzHx.zzyY != null)) {
        zzp.zzbH().zza(this.zzot.context, this.zzot.zzqj.zzJu, this.zzot.zzqo, this.zzot.zzqh, false, this.zzot.zzqo.zzHx.zzyY);
      }
      if ((this.zzot.zzqo.zzzu != null) && (this.zzot.zzqo.zzzu.zzyR != null)) {
        zzp.zzbH().zza(this.zzot.context, this.zzot.zzqj.zzJu, this.zzot.zzqo, this.zzot.zzqh, false, this.zzot.zzqo.zzzu.zzyR);
      }
      super.onAdClicked();
    }
  }
  
  public void pause()
  {
    zzx.zzci("pause must be called on the main UI thread.");
    if ((this.zzot.zzqo != null) && (this.zzot.zzqo.zzBD != null) && (this.zzot.zzbN())) {
      zzp.zzbx().zza(this.zzot.zzqo.zzBD.getWebView());
    }
    if ((this.zzot.zzqo != null) && (this.zzot.zzqo.zzzv != null)) {}
    try
    {
      this.zzot.zzqo.zzzv.pause();
      this.zzov.zzg(this.zzot.zzqo);
      this.zzos.pause();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Could not pause mediation adapter.");
      }
    }
  }
  
  public void resume()
  {
    zzx.zzci("resume must be called on the main UI thread.");
    if ((this.zzot.zzqo != null) && (this.zzot.zzqo.zzBD != null) && (this.zzot.zzbN())) {
      zzp.zzbx().zzb(this.zzot.zzqo.zzBD.getWebView());
    }
    if ((this.zzot.zzqo != null) && (this.zzot.zzqo.zzzv != null)) {}
    try
    {
      this.zzot.zzqo.zzzv.resume();
      this.zzos.resume();
      this.zzov.zzh(this.zzot.zzqo);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Could not resume mediation adapter.");
      }
    }
  }
  
  public void showInterstitial()
  {
    throw new IllegalStateException("showInterstitial is not supported for current ad type");
  }
  
  public void zza(zzfs paramzzfs)
  {
    zzx.zzci("setInAppPurchaseListener must be called on the main UI thread.");
    this.zzot.zzqv = paramzzfs;
  }
  
  public void zza(zzfw paramzzfw, String paramString)
  {
    zzx.zzci("setPlayStorePurchaseParams must be called on the main UI thread.");
    this.zzot.zzqE = new zzk(paramString);
    this.zzot.zzqw = paramzzfw;
    if ((!zzp.zzby().zzgp()) && (paramzzfw != null)) {
      new zzc(this.zzot.context, this.zzot.zzqw, this.zzot.zzqE).zzgz();
    }
  }
  
  protected void zza(zzhs paramzzhs, boolean paramBoolean)
  {
    if (paramzzhs == null) {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("Ad state was null when trying to ping impression URLs.");
    }
    for (;;)
    {
      return;
      super.zzc(paramzzhs);
      if ((paramzzhs.zzHx != null) && (paramzzhs.zzHx.zzyZ != null)) {
        zzp.zzbH().zza(this.zzot.context, this.zzot.zzqj.zzJu, paramzzhs, this.zzot.zzqh, paramBoolean, paramzzhs.zzHx.zzyZ);
      }
      if ((paramzzhs.zzzu != null) && (paramzzhs.zzzu.zzyS != null)) {
        zzp.zzbH().zza(this.zzot.context, this.zzot.zzqj.zzJu, paramzzhs, this.zzot.zzqh, paramBoolean, paramzzhs.zzzu.zzyS);
      }
    }
  }
  
  public void zza(String paramString, ArrayList<String> paramArrayList)
  {
    com.google.android.gms.ads.internal.purchase.zzd localzzd = new com.google.android.gms.ads.internal.purchase.zzd(paramString, paramArrayList, this.zzot.context, this.zzot.zzqj.zzJu);
    if (this.zzot.zzqv == null)
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("InAppPurchaseListener is not set. Try to launch default purchase flow.");
      if (!zzl.zzcF().zzR(this.zzot.context)) {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Google Play Service unavailable, cannot launch default purchase flow.");
      }
    }
    for (;;)
    {
      return;
      if (this.zzot.zzqw == null)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("PlayStorePurchaseListener is not set.");
      }
      else if (this.zzot.zzqE == null)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("PlayStorePurchaseVerifier is not initialized.");
      }
      else if (this.zzot.zzqI)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("An in-app purchase request is already in progress, abort");
      }
      else
      {
        this.zzot.zzqI = true;
        try
        {
          if (this.zzot.zzqw.isValidPurchase(paramString)) {
            break label177;
          }
          this.zzot.zzqI = false;
        }
        catch (RemoteException localRemoteException2)
        {
          com.google.android.gms.ads.internal.util.client.zzb.zzaH("Could not start In-App purchase.");
          this.zzot.zzqI = false;
        }
        continue;
        label177:
        zzp.zzbF().zza(this.zzot.context, this.zzot.zzqj.zzJx, new GInAppPurchaseManagerInfoParcel(this.zzot.context, this.zzot.zzqE, localzzd, this));
        continue;
        try
        {
          this.zzot.zzqv.zza(localzzd);
        }
        catch (RemoteException localRemoteException1)
        {
          com.google.android.gms.ads.internal.util.client.zzb.zzaH("Could not start In-App purchase.");
        }
      }
    }
  }
  
  public void zza(String paramString, boolean paramBoolean, int paramInt, final Intent paramIntent, zzf paramzzf)
  {
    try
    {
      if (this.zzot.zzqw != null) {
        this.zzot.zzqw.zza(new com.google.android.gms.ads.internal.purchase.zzg(this.zzot.context, paramString, paramBoolean, paramInt, paramIntent, paramzzf));
      }
      zzid.zzIE.postDelayed(new Runnable()
      {
        public void run()
        {
          int i = zzp.zzbF().zzd(paramIntent);
          zzp.zzbF();
          if ((i == 0) && (zzb.this.zzot.zzqo != null) && (zzb.this.zzot.zzqo.zzBD != null) && (zzb.this.zzot.zzqo.zzBD.zzhc() != null)) {
            zzb.this.zzot.zzqo.zzBD.zzhc().close();
          }
          zzb.this.zzot.zzqI = false;
        }
      }, 500L);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzaH("Fail to invoke PlayStorePurchaseListener.");
      }
    }
  }
  
  public boolean zza(AdRequestParcel paramAdRequestParcel, zzcg paramzzcg)
  {
    boolean bool = false;
    if (!zzaU()) {}
    for (;;)
    {
      return bool;
      Bundle localBundle = zza(zzp.zzby().zzE(this.zzot.context));
      this.zzos.cancel();
      this.zzot.zzqH = 0;
      AdRequestInfoParcel.zza localzza = zza(paramAdRequestParcel, localBundle);
      paramzzcg.zze("seq_num", localzza.zzEq);
      paramzzcg.zze("request_id", localzza.zzEC);
      paramzzcg.zze("session_id", localzza.zzEr);
      if (localzza.zzEo != null) {
        paramzzcg.zze("app_version", String.valueOf(localzza.zzEo.versionCode));
      }
      this.zzot.zzql = zzp.zzbr().zza(this.zzot.context, localzza, this.zzot.zzqi, this);
      bool = true;
    }
  }
  
  protected boolean zza(AdRequestParcel paramAdRequestParcel, zzhs paramzzhs, boolean paramBoolean)
  {
    if ((!paramBoolean) && (this.zzot.zzbN()))
    {
      if (paramzzhs.zzzc <= 0L) {
        break label43;
      }
      this.zzos.zza(paramAdRequestParcel, paramzzhs.zzzc);
    }
    for (;;)
    {
      return this.zzos.zzbp();
      label43:
      if ((paramzzhs.zzHx != null) && (paramzzhs.zzHx.zzzc > 0L)) {
        this.zzos.zza(paramAdRequestParcel, paramzzhs.zzHx.zzzc);
      } else if ((!paramzzhs.zzEK) && (paramzzhs.errorCode == 2)) {
        this.zzos.zzg(paramAdRequestParcel);
      }
    }
  }
  
  boolean zza(zzhs paramzzhs)
  {
    boolean bool = false;
    AdRequestParcel localAdRequestParcel;
    if (this.zzou != null)
    {
      localAdRequestParcel = this.zzou;
      this.zzou = null;
    }
    for (;;)
    {
      return zza(localAdRequestParcel, paramzzhs, bool);
      localAdRequestParcel = paramzzhs.zzEn;
      if (localAdRequestParcel.extras != null) {
        bool = localAdRequestParcel.extras.getBoolean("_noRefresh", false);
      }
    }
  }
  
  protected boolean zza(zzhs paramzzhs1, zzhs paramzzhs2)
  {
    int i = 0;
    if ((paramzzhs1 != null) && (paramzzhs1.zzzx != null)) {
      paramzzhs1.zzzx.zza(null);
    }
    if (paramzzhs2.zzzx != null) {
      paramzzhs2.zzzx.zza(this);
    }
    int j;
    if (paramzzhs2.zzHx != null)
    {
      j = paramzzhs2.zzHx.zzzf;
      i = paramzzhs2.zzHx.zzzg;
    }
    for (;;)
    {
      this.zzot.zzqF.zzf(j, i);
      return true;
      j = 0;
    }
  }
  
  protected boolean zzaU()
  {
    boolean bool = true;
    if ((!zzp.zzbv().zza(this.zzot.context.getPackageManager(), this.zzot.context.getPackageName(), "android.permission.INTERNET")) || (!zzp.zzbv().zzH(this.zzot.context))) {
      bool = false;
    }
    return bool;
  }
  
  public void zzaV()
  {
    this.zzov.zze(this.zzot.zzqo);
    this.zzoy = false;
    zzaQ();
    this.zzot.zzqq.zzgh();
  }
  
  public void zzaW()
  {
    this.zzoy = true;
    zzaS();
  }
  
  public void zzaX()
  {
    onAdClicked();
  }
  
  public void zzaY()
  {
    zzaV();
  }
  
  public void zzaZ()
  {
    zzaO();
  }
  
  public void zzb(zzhs paramzzhs)
  {
    super.zzb(paramzzhs);
    if ((paramzzhs.errorCode == 3) && (paramzzhs.zzHx != null) && (paramzzhs.zzHx.zzza != null))
    {
      com.google.android.gms.ads.internal.util.client.zzb.zzaF("Pinging no fill URLs.");
      zzp.zzbH().zza(this.zzot.context, this.zzot.zzqj.zzJu, paramzzhs, this.zzot.zzqh, false, paramzzhs.zzHx.zzza);
    }
  }
  
  public void zzba()
  {
    zzaW();
  }
  
  public void zzbb()
  {
    if (this.zzot.zzqo != null) {
      com.google.android.gms.ads.internal.util.client.zzb.zzaH("Mediation adapter " + this.zzot.zzqo.zzzw + " refreshed, but mediation adapters should never refresh.");
    }
    zza(this.zzot.zzqo, true);
    zzaT();
  }
  
  protected boolean zzc(AdRequestParcel paramAdRequestParcel)
  {
    if ((super.zzc(paramAdRequestParcel)) && (!this.zzoy)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */