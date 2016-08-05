package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.gms.ads.internal.InterstitialAdParameterParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.zzbu;
import com.google.android.gms.internal.zzby;
import com.google.android.gms.internal.zzfk.zza;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzhz;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zzie;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zziz;
import com.google.android.gms.internal.zzja;
import com.google.android.gms.internal.zzja.zza;
import com.google.android.gms.internal.zzjb;
import java.util.Collections;

@zzgr
public class zzd
  extends zzfk.zza
  implements zzo
{
  static final int zzBh = Color.argb(0, 0, 0, 0);
  private final Activity mActivity;
  RelativeLayout zzAn;
  AdOverlayInfoParcel zzBi;
  zzc zzBj;
  zzm zzBk;
  boolean zzBl = false;
  FrameLayout zzBm;
  WebChromeClient.CustomViewCallback zzBn;
  boolean zzBo = false;
  boolean zzBp = false;
  boolean zzBq = false;
  int zzBr = 0;
  private boolean zzBs;
  private boolean zzBt = false;
  private boolean zzBu = true;
  zziz zzoM;
  
  public zzd(Activity paramActivity)
  {
    this.mActivity = paramActivity;
  }
  
  public void close()
  {
    this.zzBr = 2;
    this.mActivity.finish();
  }
  
  public void onBackPressed()
  {
    this.zzBr = 0;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    boolean bool = false;
    if (paramBundle != null) {
      bool = paramBundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
    }
    this.zzBo = bool;
    try
    {
      this.zzBi = AdOverlayInfoParcel.zzb(this.mActivity.getIntent());
      if (this.zzBi == null) {
        throw new zza("Could not get info for ad overlay.");
      }
    }
    catch (zza localzza)
    {
      zzb.zzaH(localzza.getMessage());
      this.zzBr = 3;
      this.mActivity.finish();
    }
    for (;;)
    {
      return;
      if (this.zzBi.zzqj.zzJw > 7500000) {
        this.zzBr = 3;
      }
      if (this.mActivity.getIntent() != null) {
        this.zzBu = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
      }
      if (this.zzBi.zzBM != null) {}
      for (this.zzBp = this.zzBi.zzBM.zzpt;; this.zzBp = false)
      {
        if ((((Boolean)zzby.zzvz.get()).booleanValue()) && (this.zzBp) && (this.zzBi.zzBM.zzpv != null)) {
          new zzd(null).zzgz();
        }
        if (paramBundle == null)
        {
          if ((this.zzBi.zzBC != null) && (this.zzBu)) {
            this.zzBi.zzBC.zzaW();
          }
          if ((this.zzBi.zzBJ != 1) && (this.zzBi.zzBB != null)) {
            this.zzBi.zzBB.onAdClicked();
          }
        }
        this.zzAn = new zzb(this.mActivity, this.zzBi.zzBL);
        switch (this.zzBi.zzBJ)
        {
        default: 
          throw new zza("Could not determine ad overlay type.");
        }
      }
      zzv(false);
      continue;
      this.zzBj = new zzc(this.zzBi.zzBD);
      zzv(false);
      continue;
      zzv(true);
      continue;
      if (this.zzBo)
      {
        this.zzBr = 3;
        this.mActivity.finish();
      }
      else if (!zzp.zzbs().zza(this.mActivity, this.zzBi.zzBA, this.zzBi.zzBI))
      {
        this.zzBr = 3;
        this.mActivity.finish();
      }
    }
  }
  
  public void onDestroy()
  {
    if (this.zzoM != null) {
      this.zzAn.removeView(this.zzoM.getView());
    }
    zzeH();
  }
  
  public void onPause()
  {
    zzeD();
    if ((this.zzoM != null) && ((!this.mActivity.isFinishing()) || (this.zzBj == null))) {
      zzp.zzbx().zza(this.zzoM.getWebView());
    }
    zzeH();
  }
  
  public void onRestart() {}
  
  public void onResume()
  {
    if ((this.zzBi != null) && (this.zzBi.zzBJ == 4))
    {
      if (this.zzBo)
      {
        this.zzBr = 3;
        this.mActivity.finish();
      }
    }
    else
    {
      if ((this.zzoM == null) || (this.zzoM.isDestroyed())) {
        break label81;
      }
      zzp.zzbx().zzb(this.zzoM.getWebView());
    }
    for (;;)
    {
      return;
      this.zzBo = true;
      break;
      label81:
      zzb.zzaH("The webview does not exit. Ignoring action.");
    }
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzBo);
  }
  
  public void onStart() {}
  
  public void onStop()
  {
    zzeH();
  }
  
  public void setRequestedOrientation(int paramInt)
  {
    this.mActivity.setRequestedOrientation(paramInt);
  }
  
  public void zza(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
  {
    this.zzBm = new FrameLayout(this.mActivity);
    this.zzBm.setBackgroundColor(-16777216);
    this.zzBm.addView(paramView, -1, -1);
    this.mActivity.setContentView(this.zzBm);
    zzaE();
    this.zzBn = paramCustomViewCallback;
    this.zzBl = true;
  }
  
  public void zza(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (this.zzBk != null) {
      this.zzBk.zza(paramBoolean1, paramBoolean2);
    }
  }
  
  public void zzaE()
  {
    this.zzBs = true;
  }
  
  public void zzeD()
  {
    if ((this.zzBi != null) && (this.zzBl)) {
      setRequestedOrientation(this.zzBi.orientation);
    }
    if (this.zzBm != null)
    {
      this.mActivity.setContentView(this.zzAn);
      zzaE();
      this.zzBm.removeAllViews();
      this.zzBm = null;
    }
    if (this.zzBn != null)
    {
      this.zzBn.onCustomViewHidden();
      this.zzBn = null;
    }
    this.zzBl = false;
  }
  
  public void zzeE()
  {
    this.zzBr = 1;
    this.mActivity.finish();
  }
  
  public boolean zzeF()
  {
    this.zzBr = 0;
    boolean bool;
    if (this.zzoM == null) {
      bool = true;
    }
    for (;;)
    {
      return bool;
      bool = this.zzoM.zzhk();
      if (!bool) {
        this.zzoM.zzb("onbackblocked", Collections.emptyMap());
      }
    }
  }
  
  public void zzeG()
  {
    this.zzAn.removeView(this.zzBk);
    zzu(true);
  }
  
  protected void zzeH()
  {
    if ((!this.mActivity.isFinishing()) || (this.zzBt)) {}
    for (;;)
    {
      return;
      this.zzBt = true;
      if (this.zzoM != null)
      {
        zzv(this.zzBr);
        this.zzAn.removeView(this.zzoM.getView());
        if (this.zzBj != null)
        {
          this.zzoM.setContext(this.zzBj.context);
          this.zzoM.zzC(false);
          this.zzBj.zzBx.addView(this.zzoM.getView(), this.zzBj.index, this.zzBj.zzBw);
          this.zzBj = null;
        }
        this.zzoM = null;
      }
      if ((this.zzBi != null) && (this.zzBi.zzBC != null)) {
        this.zzBi.zzBC.zzaV();
      }
    }
  }
  
  public void zzeI()
  {
    if (this.zzBq)
    {
      this.zzBq = false;
      zzeJ();
    }
  }
  
  protected void zzeJ()
  {
    this.zzoM.zzeJ();
  }
  
  public void zzu(boolean paramBoolean)
  {
    int i;
    RelativeLayout.LayoutParams localLayoutParams;
    if (paramBoolean)
    {
      i = 50;
      this.zzBk = new zzm(this.mActivity, i, this);
      localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams.addRule(10);
      if (!paramBoolean) {
        break label90;
      }
    }
    label90:
    for (int j = 11;; j = 9)
    {
      localLayoutParams.addRule(j);
      this.zzBk.zza(paramBoolean, this.zzBi.zzBG);
      this.zzAn.addView(this.zzBk, localLayoutParams);
      return;
      i = 32;
      break;
    }
  }
  
  protected void zzv(int paramInt)
  {
    this.zzoM.zzv(paramInt);
  }
  
  protected void zzv(boolean paramBoolean)
    throws zzd.zza
  {
    if (!this.zzBs) {
      this.mActivity.requestWindowFeature(1);
    }
    Window localWindow = this.mActivity.getWindow();
    if (localWindow == null) {
      throw new zza("Invalid activity, no window available.");
    }
    if ((!this.zzBp) || ((this.zzBi.zzBM != null) && (this.zzBi.zzBM.zzpu))) {
      localWindow.setFlags(1024, 1024);
    }
    boolean bool1 = this.zzBi.zzBD.zzhe().zzbY();
    this.zzBq = false;
    boolean bool3;
    if (bool1)
    {
      if (this.zzBi.orientation != zzp.zzbx().zzgG()) {
        break label528;
      }
      if (this.mActivity.getResources().getConfiguration().orientation == 1)
      {
        bool3 = true;
        this.zzBq = bool3;
      }
    }
    else
    {
      label146:
      zzb.zzaF("Delay onShow to next orientation change: " + this.zzBq);
      setRequestedOrientation(this.zzBi.orientation);
      if (zzp.zzbx().zza(localWindow)) {
        zzb.zzaF("Hardware acceleration on the AdActivity window enabled.");
      }
      if (this.zzBp) {
        break label579;
      }
      this.zzAn.setBackgroundColor(-16777216);
      label216:
      this.mActivity.setContentView(this.zzAn);
      zzaE();
      if (!paramBoolean) {
        break label646;
      }
      this.zzoM = zzp.zzbw().zza(this.mActivity, this.zzBi.zzBD.zzaN(), true, bool1, null, this.zzBi.zzqj);
      this.zzoM.zzhe().zzb(null, null, this.zzBi.zzBE, this.zzBi.zzBI, true, this.zzBi.zzBK, null, this.zzBi.zzBD.zzhe().zzhq(), null);
      this.zzoM.zzhe().zza(new zzja.zza()
      {
        public void zza(zziz paramAnonymouszziz, boolean paramAnonymousBoolean)
        {
          paramAnonymouszziz.zzeJ();
        }
      });
      if (this.zzBi.url == null) {
        break label592;
      }
      this.zzoM.loadUrl(this.zzBi.url);
      label370:
      if (this.zzBi.zzBD != null) {
        this.zzBi.zzBD.zzc(this);
      }
    }
    for (;;)
    {
      this.zzoM.zzb(this);
      ViewParent localViewParent = this.zzoM.getParent();
      if ((localViewParent != null) && ((localViewParent instanceof ViewGroup))) {
        ((ViewGroup)localViewParent).removeView(this.zzoM.getView());
      }
      if (this.zzBp) {
        this.zzoM.setBackgroundColor(zzBh);
      }
      this.zzAn.addView(this.zzoM.getView(), -1, -1);
      if ((!paramBoolean) && (!this.zzBq)) {
        zzeJ();
      }
      zzu(bool1);
      if (this.zzoM.zzhf()) {
        zza(bool1, true);
      }
      return;
      bool3 = false;
      break;
      label528:
      if (this.zzBi.orientation != zzp.zzbx().zzgH()) {
        break label146;
      }
      if (this.mActivity.getResources().getConfiguration().orientation == 2) {}
      for (boolean bool2 = true;; bool2 = false)
      {
        this.zzBq = bool2;
        break;
      }
      label579:
      this.zzAn.setBackgroundColor(zzBh);
      break label216;
      label592:
      if (this.zzBi.zzBH != null)
      {
        this.zzoM.loadDataWithBaseURL(this.zzBi.zzBF, this.zzBi.zzBH, "text/html", "UTF-8", null);
        break label370;
      }
      throw new zza("No URL or HTML to display in ad overlay.");
      label646:
      this.zzoM = this.zzBi.zzBD;
      this.zzoM.setContext(this.mActivity);
    }
  }
  
  @zzgr
  private class zzd
    extends zzhz
  {
    private zzd() {}
    
    public void onStop() {}
    
    public void zzbn()
    {
      Bitmap localBitmap = zzp.zzbv().zzg(zzd.zza(zzd.this), zzd.this.zzBi.zzBM.zzpv);
      if (localBitmap != null)
      {
        final Drawable localDrawable = zzp.zzbx().zza(zzd.zza(zzd.this), localBitmap, zzd.this.zzBi.zzBM.zzpw, zzd.this.zzBi.zzBM.zzpx);
        zzid.zzIE.post(new Runnable()
        {
          public void run()
          {
            zzd.zza(zzd.this).getWindow().setBackgroundDrawable(localDrawable);
          }
        });
      }
    }
  }
  
  @zzgr
  static final class zzb
    extends RelativeLayout
  {
    zzif zzqQ;
    
    public zzb(Context paramContext, String paramString)
    {
      super();
      this.zzqQ = new zzif(paramContext, paramString);
    }
    
    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
      this.zzqQ.zze(paramMotionEvent);
      return false;
    }
  }
  
  @zzgr
  public static class zzc
  {
    public final Context context;
    public final int index;
    public final ViewGroup.LayoutParams zzBw;
    public final ViewGroup zzBx;
    
    public zzc(zziz paramzziz)
      throws zzd.zza
    {
      this.zzBw = paramzziz.getLayoutParams();
      ViewParent localViewParent = paramzziz.getParent();
      this.context = paramzziz.zzha();
      if ((localViewParent != null) && ((localViewParent instanceof ViewGroup)))
      {
        this.zzBx = ((ViewGroup)localViewParent);
        this.index = this.zzBx.indexOfChild(paramzziz.getView());
        this.zzBx.removeView(paramzziz.getView());
        paramzziz.zzC(true);
        return;
      }
      throw new zzd.zza("Could not get the parent of the WebView for an overlay.");
    }
  }
  
  @zzgr
  private static final class zza
    extends Exception
  {
    public zza(String paramString)
    {
      super();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/overlay/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */