package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@zzgr
public class zzja
  extends WebViewClient
{
  private static final String[] zzJU;
  private static final String[] zzJV;
  private zzfi zzAl;
  private zza zzDn;
  private final HashMap<String, List<zzdk>> zzJW = new HashMap();
  private zzg zzJX;
  private zzb zzJY;
  private boolean zzJZ = false;
  private boolean zzKa;
  private zzn zzKb;
  private final zzfg zzKc;
  private boolean zzKd;
  private boolean zzKe;
  private boolean zzKf;
  private boolean zzKg;
  private int zzKh;
  protected zziz zzoM;
  private final Object zzpd = new Object();
  private boolean zzqW;
  private zza zzsy;
  private zzdo zzxO;
  private com.google.android.gms.ads.internal.zze zzxQ;
  private zzfc zzxR;
  private zzdm zzxT;
  private zzdg zzxn;
  
  static
  {
    String[] arrayOfString1 = new String[15];
    arrayOfString1[0] = "UNKNOWN";
    arrayOfString1[1] = "HOST_LOOKUP";
    arrayOfString1[2] = "UNSUPPORTED_AUTH_SCHEME";
    arrayOfString1[3] = "AUTHENTICATION";
    arrayOfString1[4] = "PROXY_AUTHENTICATION";
    arrayOfString1[5] = "CONNECT";
    arrayOfString1[6] = "IO";
    arrayOfString1[7] = "TIMEOUT";
    arrayOfString1[8] = "REDIRECT_LOOP";
    arrayOfString1[9] = "UNSUPPORTED_SCHEME";
    arrayOfString1[10] = "FAILED_SSL_HANDSHAKE";
    arrayOfString1[11] = "BAD_URL";
    arrayOfString1[12] = "FILE";
    arrayOfString1[13] = "FILE_NOT_FOUND";
    arrayOfString1[14] = "TOO_MANY_REQUESTS";
    zzJU = arrayOfString1;
    String[] arrayOfString2 = new String[6];
    arrayOfString2[0] = "NOT_YET_VALID";
    arrayOfString2[1] = "EXPIRED";
    arrayOfString2[2] = "ID_MISMATCH";
    arrayOfString2[3] = "UNTRUSTED";
    arrayOfString2[4] = "DATE_INVALID";
    arrayOfString2[5] = "INVALID";
    zzJV = arrayOfString2;
  }
  
  public zzja(zziz paramzziz, boolean paramBoolean)
  {
    this(paramzziz, paramBoolean, new zzfg(paramzziz, paramzziz.zzha(), new zzbq(paramzziz.getContext())), null);
  }
  
  zzja(zziz paramzziz, boolean paramBoolean, zzfg paramzzfg, zzfc paramzzfc)
  {
    this.zzoM = paramzziz;
    this.zzqW = paramBoolean;
    this.zzKc = paramzzfg;
    this.zzxR = paramzzfc;
  }
  
  private void zza(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (!((Boolean)zzby.zzvp.get()).booleanValue()) {}
    for (;;)
    {
      return;
      Bundle localBundle = new Bundle();
      localBundle.putString("err", paramString1);
      localBundle.putString("code", paramString2);
      localBundle.putString("host", zzaK(paramString3));
      zzp.zzbv().zza(paramContext, this.zzoM.zzhh().zzJu, "gmob-apps", localBundle, true);
    }
  }
  
  private String zzaK(String paramString)
  {
    String str;
    if (TextUtils.isEmpty(paramString)) {
      str = "";
    }
    for (;;)
    {
      return str;
      Uri localUri = Uri.parse(paramString);
      if (localUri.getHost() != null) {
        str = localUri.getHost();
      } else {
        str = "";
      }
    }
  }
  
  private static boolean zzg(Uri paramUri)
  {
    String str = paramUri.getScheme();
    if (("http".equalsIgnoreCase(str)) || ("https".equalsIgnoreCase(str))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void zzht()
  {
    synchronized (this.zzpd)
    {
      this.zzKa = true;
      this.zzKh = (1 + this.zzKh);
      zzhw();
      return;
    }
  }
  
  private void zzhu()
  {
    this.zzKh = (-1 + this.zzKh);
    zzhw();
  }
  
  private void zzhv()
  {
    this.zzKg = true;
    zzhw();
  }
  
  public final void onLoadResource(WebView paramWebView, String paramString)
  {
    zzb.v("Loading resource: " + paramString);
    Uri localUri = Uri.parse(paramString);
    if (("gmsg".equalsIgnoreCase(localUri.getScheme())) && ("mobileads.google.com".equalsIgnoreCase(localUri.getHost()))) {
      zzh(localUri);
    }
  }
  
  public final void onPageFinished(WebView paramWebView, String paramString)
  {
    synchronized (this.zzpd)
    {
      if ((this.zzKe) && ("about:blank".equals(paramString)))
      {
        zzb.v("Blank page loaded, 1...");
        this.zzoM.zzhj();
      }
      else
      {
        this.zzKf = true;
        zzhw();
      }
    }
  }
  
  public final void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    if ((paramInt < 0) && (-1 + -paramInt < zzJU.length)) {}
    for (String str = zzJU[(-1 + -paramInt)];; str = String.valueOf(paramInt))
    {
      zza(this.zzoM.getContext(), "http_err", str, paramString2);
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      return;
    }
  }
  
  public final void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    int i;
    if (paramSslError != null)
    {
      i = paramSslError.getPrimaryError();
      if ((i < 0) || (i >= zzJV.length)) {
        break label65;
      }
    }
    label65:
    for (String str = zzJV[i];; str = String.valueOf(i))
    {
      zza(this.zzoM.getContext(), "ssl_err", str, zzp.zzbx().zza(paramSslError));
      super.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
      return;
    }
  }
  
  public final void reset()
  {
    synchronized (this.zzpd)
    {
      this.zzJW.clear();
      this.zzsy = null;
      this.zzJX = null;
      this.zzDn = null;
      this.zzxn = null;
      this.zzJZ = false;
      this.zzqW = false;
      this.zzKa = false;
      this.zzxT = null;
      this.zzKb = null;
      this.zzJY = null;
      if (this.zzxR != null)
      {
        this.zzxR.zzn(true);
        this.zzxR = null;
      }
      this.zzKd = false;
      return;
    }
  }
  
  public boolean shouldOverrideKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent)
  {
    switch (paramKeyEvent.getKeyCode())
    {
    }
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  public final boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    zzb.v("AdWebView shouldOverrideUrlLoading: " + paramString);
    Object localObject1 = Uri.parse(paramString);
    if (("gmsg".equalsIgnoreCase(((Uri)localObject1).getScheme())) && ("mobileads.google.com".equalsIgnoreCase(((Uri)localObject1).getHost()))) {
      zzh((Uri)localObject1);
    }
    for (;;)
    {
      for (boolean bool = true;; bool = super.shouldOverrideUrlLoading(paramWebView, paramString))
      {
        return bool;
        if ((!this.zzJZ) || (paramWebView != this.zzoM.getWebView()) || (!zzg((Uri)localObject1))) {
          break;
        }
        if (!this.zzKd)
        {
          this.zzKd = true;
          if ((this.zzsy != null) && (((Boolean)zzby.zzvd.get()).booleanValue())) {
            this.zzsy.onAdClicked();
          }
        }
      }
      if (!this.zzoM.getWebView().willNotDraw())
      {
        try
        {
          zzan localzzan = this.zzoM.zzhg();
          if ((localzzan != null) && (localzzan.zzb((Uri)localObject1)))
          {
            Uri localUri = localzzan.zza((Uri)localObject1, this.zzoM.getContext());
            localObject1 = localUri;
          }
          localObject2 = localObject1;
        }
        catch (zzao localzzao)
        {
          for (;;)
          {
            zzb.zzaH("Unable to append parameter to URL: " + paramString);
            Object localObject2 = localObject1;
          }
          this.zzxQ.zzp(paramString);
        }
        if ((this.zzxQ == null) || (this.zzxQ.zzbe())) {
          zza(new AdLauncherIntentInfoParcel("android.intent.action.VIEW", ((Uri)localObject2).toString(), null, null, null, null, null));
        }
      }
      else
      {
        zzb.zzaH("AdWebView unable to handle URL: " + paramString);
      }
    }
  }
  
  public void zzF(boolean paramBoolean)
  {
    this.zzJZ = paramBoolean;
  }
  
  public void zza(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.zzKc.zze(paramInt1, paramInt2);
    if (this.zzxR != null) {
      this.zzxR.zza(paramInt1, paramInt2, paramBoolean);
    }
  }
  
  public final void zza(AdLauncherIntentInfoParcel paramAdLauncherIntentInfoParcel)
  {
    zzg localzzg = null;
    boolean bool = this.zzoM.zzhi();
    zza localzza;
    if ((bool) && (!this.zzoM.zzaN().zztf))
    {
      localzza = null;
      if (!bool) {
        break label76;
      }
    }
    for (;;)
    {
      zza(new AdOverlayInfoParcel(paramAdLauncherIntentInfoParcel, localzza, localzzg, this.zzKb, this.zzoM.zzhh()));
      return;
      localzza = this.zzsy;
      break;
      label76:
      localzzg = this.zzJX;
    }
  }
  
  public void zza(AdOverlayInfoParcel paramAdOverlayInfoParcel)
  {
    boolean bool1 = false;
    if (this.zzxR != null) {}
    for (boolean bool2 = this.zzxR.zzef();; bool2 = false)
    {
      com.google.android.gms.ads.internal.overlay.zze localzze = zzp.zzbt();
      Context localContext = this.zzoM.getContext();
      if (!bool2) {
        bool1 = true;
      }
      localzze.zza(localContext, paramAdOverlayInfoParcel, bool1);
      return;
    }
  }
  
  public void zza(zza paramzza)
  {
    this.zzDn = paramzza;
  }
  
  public void zza(zzb paramzzb)
  {
    this.zzJY = paramzzb;
  }
  
  public final void zza(String paramString, zzdk paramzzdk)
  {
    synchronized (this.zzpd)
    {
      Object localObject3 = (List)this.zzJW.get(paramString);
      if (localObject3 == null)
      {
        localObject3 = new CopyOnWriteArrayList();
        this.zzJW.put(paramString, localObject3);
      }
      ((List)localObject3).add(paramzzdk);
      return;
    }
  }
  
  public final void zza(boolean paramBoolean, int paramInt)
  {
    if ((this.zzoM.zzhi()) && (!this.zzoM.zzaN().zztf)) {}
    for (zza localzza = null;; localzza = this.zzsy)
    {
      zza(new AdOverlayInfoParcel(localzza, this.zzJX, this.zzKb, this.zzoM, paramBoolean, paramInt, this.zzoM.zzhh()));
      return;
    }
  }
  
  public final void zza(boolean paramBoolean, int paramInt, String paramString)
  {
    Object localObject = null;
    boolean bool = this.zzoM.zzhi();
    zza localzza;
    if ((bool) && (!this.zzoM.zzaN().zztf))
    {
      localzza = null;
      if (!bool) {
        break label95;
      }
    }
    for (;;)
    {
      zza(new AdOverlayInfoParcel(localzza, (zzg)localObject, this.zzxn, this.zzKb, this.zzoM, paramBoolean, paramInt, paramString, this.zzoM.zzhh(), this.zzxT));
      return;
      localzza = this.zzsy;
      break;
      label95:
      localObject = new zzc(this.zzoM, this.zzJX);
    }
  }
  
  public final void zza(boolean paramBoolean, int paramInt, String paramString1, String paramString2)
  {
    boolean bool = this.zzoM.zzhi();
    zza localzza;
    if ((bool) && (!this.zzoM.zzaN().zztf))
    {
      localzza = null;
      if (!bool) {
        break label97;
      }
    }
    label97:
    for (Object localObject = null;; localObject = new zzc(this.zzoM, this.zzJX))
    {
      zza(new AdOverlayInfoParcel(localzza, (zzg)localObject, this.zzxn, this.zzKb, this.zzoM, paramBoolean, paramInt, paramString1, paramString2, this.zzoM.zzhh(), this.zzxT));
      return;
      localzza = this.zzsy;
      break;
    }
  }
  
  public void zzb(zza paramzza, zzg paramzzg, zzdg paramzzdg, zzn paramzzn, boolean paramBoolean, zzdm paramzzdm, zzdo paramzzdo, com.google.android.gms.ads.internal.zze paramzze, zzfi paramzzfi)
  {
    if (paramzze == null) {
      paramzze = new com.google.android.gms.ads.internal.zze(false);
    }
    this.zzxR = new zzfc(this.zzoM, paramzzfi);
    zza("/appEvent", new zzdf(paramzzdg));
    zza("/backButton", zzdj.zzxx);
    zza("/canOpenURLs", zzdj.zzxp);
    zza("/canOpenIntents", zzdj.zzxq);
    zza("/click", zzdj.zzxr);
    zza("/close", zzdj.zzxs);
    zza("/customClose", zzdj.zzxt);
    zza("/instrument", zzdj.zzxA);
    zza("/delayPageLoaded", new zzd(null));
    zza("/httpTrack", zzdj.zzxu);
    zza("/log", zzdj.zzxv);
    zza("/mraid", new zzdq(paramzze, this.zzxR));
    zza("/mraidLoaded", this.zzKc);
    zza("/open", new zzdr(paramzzdm, paramzze, this.zzxR));
    zza("/precache", zzdj.zzxz);
    zza("/touch", zzdj.zzxw);
    zza("/video", zzdj.zzxy);
    if (paramzzdo != null) {
      zza("/setInterstitialProperties", new zzdn(paramzzdo));
    }
    this.zzsy = paramzza;
    this.zzJX = paramzzg;
    this.zzxn = paramzzdg;
    this.zzxT = paramzzdm;
    this.zzKb = paramzzn;
    this.zzxQ = paramzze;
    this.zzAl = paramzzfi;
    this.zzxO = paramzzdo;
    zzF(paramBoolean);
    this.zzKd = false;
  }
  
  public final void zzb(String paramString, zzdk paramzzdk)
  {
    synchronized (this.zzpd)
    {
      List localList = (List)this.zzJW.get(paramString);
      if (localList != null) {
        localList.remove(paramzzdk);
      }
    }
  }
  
  public boolean zzbY()
  {
    synchronized (this.zzpd)
    {
      boolean bool = this.zzqW;
      return bool;
    }
  }
  
  public void zzd(int paramInt1, int paramInt2)
  {
    if (this.zzxR != null) {
      this.zzxR.zzd(paramInt1, paramInt2);
    }
  }
  
  public void zze(zziz paramzziz)
  {
    this.zzoM = paramzziz;
  }
  
  public final void zzeG()
  {
    synchronized (this.zzpd)
    {
      this.zzJZ = false;
      this.zzqW = true;
      zzid.runOnUiThread(new Runnable()
      {
        public void run()
        {
          zzja.this.zzoM.zzho();
          zzd localzzd = zzja.this.zzoM.zzhc();
          if (localzzd != null) {
            localzzd.zzeG();
          }
          if (zzja.zzd(zzja.this) != null)
          {
            zzja.zzd(zzja.this).zzbf();
            zzja.zza(zzja.this, null);
          }
        }
      });
      return;
    }
  }
  
  public void zzh(Uri paramUri)
  {
    String str1 = paramUri.getPath();
    List localList = (List)this.zzJW.get(str1);
    if (localList != null)
    {
      Map localMap = zzp.zzbv().zze(paramUri);
      if (zzb.zzN(2))
      {
        zzb.v("Received GMSG: " + str1);
        Iterator localIterator2 = localMap.keySet().iterator();
        while (localIterator2.hasNext())
        {
          String str2 = (String)localIterator2.next();
          zzb.v("  " + str2 + ": " + (String)localMap.get(str2));
        }
      }
      Iterator localIterator1 = localList.iterator();
      while (localIterator1.hasNext()) {
        ((zzdk)localIterator1.next()).zza(this.zzoM, localMap);
      }
    }
    zzb.v("No GMSG handler found for GMSG: " + paramUri);
  }
  
  public com.google.android.gms.ads.internal.zze zzhq()
  {
    return this.zzxQ;
  }
  
  public boolean zzhr()
  {
    synchronized (this.zzpd)
    {
      boolean bool = this.zzKa;
      return bool;
    }
  }
  
  public void zzhs()
  {
    synchronized (this.zzpd)
    {
      zzb.v("Loading blank page in WebView, 2...");
      this.zzKe = true;
      this.zzoM.zzaI("about:blank");
      return;
    }
  }
  
  public final void zzhw()
  {
    zza localzza;
    zziz localzziz;
    if ((this.zzDn != null) && (((this.zzKf) && (this.zzKh <= 0)) || (this.zzKg)))
    {
      localzza = this.zzDn;
      localzziz = this.zzoM;
      if (this.zzKg) {
        break label70;
      }
    }
    label70:
    for (boolean bool = true;; bool = false)
    {
      localzza.zza(localzziz, bool);
      this.zzDn = null;
      this.zzoM.zzhp();
      return;
    }
  }
  
  private static class zzc
    implements zzg
  {
    private zzg zzJX;
    private zziz zzKj;
    
    public zzc(zziz paramzziz, zzg paramzzg)
    {
      this.zzKj = paramzziz;
      this.zzJX = paramzzg;
    }
    
    public void zzaV()
    {
      this.zzJX.zzaV();
      this.zzKj.zzgY();
    }
    
    public void zzaW()
    {
      this.zzJX.zzaW();
      this.zzKj.zzeJ();
    }
  }
  
  private class zzd
    implements zzdk
  {
    private zzd() {}
    
    public void zza(zziz paramzziz, Map<String, String> paramMap)
    {
      if (paramMap.keySet().contains("start")) {
        zzja.zza(zzja.this);
      }
      for (;;)
      {
        return;
        if (paramMap.keySet().contains("stop")) {
          zzja.zzb(zzja.this);
        } else if (paramMap.keySet().contains("cancel")) {
          zzja.zzc(zzja.this);
        }
      }
    }
  }
  
  public static abstract interface zzb
  {
    public abstract void zzbf();
  }
  
  public static abstract interface zza
  {
    public abstract void zza(zziz paramzziz, boolean paramBoolean);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzja.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */