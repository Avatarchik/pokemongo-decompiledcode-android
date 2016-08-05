package com.google.android.gms.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
class zzjd
  extends WebView
  implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zziz
{
  private int zzAD = -1;
  private int zzAE = -1;
  private int zzAG = -1;
  private int zzAH = -1;
  private String zzBY = "";
  private Boolean zzHZ;
  private Map<String, zzdv> zzKA;
  private final zza zzKm;
  private zzja zzKn;
  private com.google.android.gms.ads.internal.overlay.zzd zzKo;
  private boolean zzKp;
  private boolean zzKq;
  private boolean zzKr;
  private boolean zzKs;
  private int zzKt;
  private boolean zzKu = true;
  private zzce zzKv;
  private zzce zzKw;
  private zzce zzKx;
  private zzcf zzKy;
  private com.google.android.gms.ads.internal.overlay.zzd zzKz;
  private final com.google.android.gms.ads.internal.zzd zzow;
  private final VersionInfoParcel zzpb;
  private final Object zzpd = new Object();
  private zzim zzqR;
  private final WindowManager zzri;
  private final zzan zzwL;
  private AdSizeParcel zzzm;
  
  protected zzjd(zza paramzza, AdSizeParcel paramAdSizeParcel, boolean paramBoolean1, boolean paramBoolean2, zzan paramzzan, VersionInfoParcel paramVersionInfoParcel, zzcg paramzzcg, com.google.android.gms.ads.internal.zzd paramzzd)
  {
    super(paramzza);
    this.zzKm = paramzza;
    this.zzzm = paramAdSizeParcel;
    this.zzKr = paramBoolean1;
    this.zzKt = -1;
    this.zzwL = paramzzan;
    this.zzpb = paramVersionInfoParcel;
    this.zzow = paramzzd;
    this.zzri = ((WindowManager)getContext().getSystemService("window"));
    setBackgroundColor(0);
    WebSettings localWebSettings = getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setSavePassword(false);
    localWebSettings.setSupportMultipleWindows(true);
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    if (Build.VERSION.SDK_INT >= 21) {
      localWebSettings.setMixedContentMode(0);
    }
    zzp.zzbv().zza(paramzza, paramVersionInfoParcel.zzJu, localWebSettings);
    zzp.zzbx().zza(getContext(), localWebSettings);
    setDownloadListener(this);
    zzhz();
    if (zzmx.zzqz()) {
      addJavascriptInterface(new zzje(this), "googleAdsJsInterface");
    }
    this.zzqR = new zzim(this.zzKm.zzgZ(), this, null);
    zzd(paramzzcg);
  }
  
  static zzjd zzb(Context paramContext, AdSizeParcel paramAdSizeParcel, boolean paramBoolean1, boolean paramBoolean2, zzan paramzzan, VersionInfoParcel paramVersionInfoParcel, zzcg paramzzcg, com.google.android.gms.ads.internal.zzd paramzzd)
  {
    return new zzjd(new zza(paramContext), paramAdSizeParcel, paramBoolean1, paramBoolean2, paramzzan, paramVersionInfoParcel, paramzzcg, paramzzd);
  }
  
  private void zzd(zzcg paramzzcg)
  {
    zzhD();
    this.zzKy = new zzcf(new zzcg(true, "make_wv", this.zzzm.zzte));
    this.zzKy.zzdm().zzc(paramzzcg);
    this.zzKw = zzcc.zzb(this.zzKy.zzdm());
    this.zzKy.zza("native:view_create", this.zzKw);
    this.zzKx = null;
    this.zzKv = null;
  }
  
  private void zzhA()
  {
    synchronized (this.zzpd)
    {
      if (!this.zzKs) {
        zzp.zzbx().zzm(this);
      }
      this.zzKs = true;
      return;
    }
  }
  
  private void zzhB()
  {
    synchronized (this.zzpd)
    {
      if (this.zzKs) {
        zzp.zzbx().zzl(this);
      }
      this.zzKs = false;
      return;
    }
  }
  
  private void zzhC()
  {
    synchronized (this.zzpd)
    {
      if (this.zzKA != null)
      {
        Iterator localIterator = this.zzKA.values().iterator();
        if (localIterator.hasNext()) {
          ((zzdv)localIterator.next()).release();
        }
      }
    }
  }
  
  private void zzhD()
  {
    if (this.zzKy == null) {}
    for (;;)
    {
      return;
      zzcg localzzcg = this.zzKy.zzdm();
      if ((localzzcg != null) && (zzp.zzby().zzgo() != null)) {
        zzp.zzby().zzgo().zza(localzzcg);
      }
    }
  }
  
  private void zzhy()
  {
    synchronized (this.zzpd)
    {
      this.zzHZ = zzp.zzby().zzgs();
      Boolean localBoolean = this.zzHZ;
      if (localBoolean == null) {}
      try
      {
        evaluateJavascript("(function(){})()", null);
        zzb(Boolean.valueOf(true));
        return;
      }
      catch (IllegalStateException localIllegalStateException)
      {
        for (;;)
        {
          zzb(Boolean.valueOf(false));
        }
      }
    }
  }
  
  private void zzhz()
  {
    synchronized (this.zzpd)
    {
      if ((this.zzKr) || (this.zzzm.zztf))
      {
        if (Build.VERSION.SDK_INT < 14)
        {
          zzb.zzaF("Disabling hardware acceleration on an overlay.");
          zzhA();
          return;
        }
        zzb.zzaF("Enabling hardware acceleration on an overlay.");
        zzhB();
      }
    }
  }
  
  public void destroy()
  {
    synchronized (this.zzpd)
    {
      zzhD();
      this.zzqR.zzgP();
      if (this.zzKo != null)
      {
        this.zzKo.close();
        this.zzKo.onDestroy();
        this.zzKo = null;
      }
      this.zzKn.reset();
      if (!this.zzKq)
      {
        zzp.zzbI().zza(this);
        zzhC();
        this.zzKq = true;
        zzb.v("Initiating WebView self destruct sequence in 3...");
        this.zzKn.zzhs();
      }
    }
  }
  
  public void evaluateJavascript(String paramString, ValueCallback<String> paramValueCallback)
  {
    synchronized (this.zzpd)
    {
      if (isDestroyed())
      {
        zzb.zzaH("The webview is destroyed. Ignoring action.");
        if (paramValueCallback != null) {
          paramValueCallback.onReceiveValue(null);
        }
      }
      else
      {
        super.evaluateJavascript(paramString, paramValueCallback);
      }
    }
  }
  
  public String getRequestId()
  {
    synchronized (this.zzpd)
    {
      String str = this.zzBY;
      return str;
    }
  }
  
  public int getRequestedOrientation()
  {
    synchronized (this.zzpd)
    {
      int i = this.zzKt;
      return i;
    }
  }
  
  public View getView()
  {
    return this;
  }
  
  public WebView getWebView()
  {
    return this;
  }
  
  public boolean isDestroyed()
  {
    synchronized (this.zzpd)
    {
      boolean bool = this.zzKq;
      return bool;
    }
  }
  
  public void loadData(String paramString1, String paramString2, String paramString3)
  {
    synchronized (this.zzpd)
    {
      if (!isDestroyed())
      {
        super.loadData(paramString1, paramString2, paramString3);
        return;
      }
      zzb.zzaH("The webview is destroyed. Ignoring action.");
    }
  }
  
  public void loadDataWithBaseURL(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    synchronized (this.zzpd)
    {
      if (!isDestroyed())
      {
        super.loadDataWithBaseURL(paramString1, paramString2, paramString3, paramString4, paramString5);
        return;
      }
      zzb.zzaH("The webview is destroyed. Ignoring action.");
    }
  }
  
  public void loadUrl(String paramString)
  {
    for (;;)
    {
      synchronized (this.zzpd)
      {
        boolean bool = isDestroyed();
        if (!bool) {
          try
          {
            super.loadUrl(paramString);
            return;
          }
          catch (Throwable localThrowable)
          {
            zzb.zzaH("Could not call loadUrl. " + localThrowable);
            continue;
          }
        }
      }
      zzb.zzaH("The webview is destroyed. Ignoring action.");
    }
  }
  
  protected void onAttachedToWindow()
  {
    synchronized (this.zzpd)
    {
      super.onAttachedToWindow();
      if (!isDestroyed()) {
        this.zzqR.onAttachedToWindow();
      }
      return;
    }
  }
  
  protected void onDetachedFromWindow()
  {
    synchronized (this.zzpd)
    {
      if (!isDestroyed()) {
        this.zzqR.onDetachedFromWindow();
      }
      super.onDetachedFromWindow();
      return;
    }
  }
  
  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.parse(paramString1), paramString4);
      zzp.zzbv().zzb(getContext(), localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;)
      {
        zzb.zzaF("Couldn't find an Activity to view url/mimetype: " + paramString1 + " / " + paramString4);
      }
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (isDestroyed()) {}
    for (;;)
    {
      return;
      if ((Build.VERSION.SDK_INT != 21) || (!paramCanvas.isHardwareAccelerated()) || (isAttachedToWindow())) {
        super.onDraw(paramCanvas);
      }
    }
  }
  
  public void onGlobalLayout()
  {
    boolean bool = zzhx();
    com.google.android.gms.ads.internal.overlay.zzd localzzd = zzhc();
    if ((localzzd != null) && (bool)) {
      localzzd.zzeI();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int n;
    for (int i = Integer.MAX_VALUE;; i = n)
    {
      int m;
      do
      {
        DisplayMetrics localDisplayMetrics;
        int j;
        int k;
        float f;
        synchronized (this.zzpd)
        {
          if (isDestroyed()) {
            setMeasuredDimension(0, 0);
          } else if ((isInEditMode()) || (this.zzKr) || (this.zzzm.zzth) || (this.zzzm.zzti)) {
            super.onMeasure(paramInt1, paramInt2);
          }
        }
        return;
        int i1 = k;
      } while ((m != Integer.MIN_VALUE) && (m != 1073741824));
    }
  }
  
  public void onPause()
  {
    if (isDestroyed()) {}
    for (;;)
    {
      return;
      try
      {
        if (zzmx.zzqu()) {
          super.onPause();
        }
      }
      catch (Exception localException)
      {
        zzb.zzb("Could not pause webview.", localException);
      }
    }
  }
  
  public void onResume()
  {
    if (isDestroyed()) {}
    for (;;)
    {
      return;
      try
      {
        if (zzmx.zzqu()) {
          super.onResume();
        }
      }
      catch (Exception localException)
      {
        zzb.zzb("Could not resume webview.", localException);
      }
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.zzwL != null) {
      this.zzwL.zza(paramMotionEvent);
    }
    if (isDestroyed()) {}
    for (boolean bool = false;; bool = super.onTouchEvent(paramMotionEvent)) {
      return bool;
    }
  }
  
  public void setContext(Context paramContext)
  {
    this.zzKm.setBaseContext(paramContext);
    this.zzqR.zzk(this.zzKm.zzgZ());
  }
  
  public void setRequestedOrientation(int paramInt)
  {
    synchronized (this.zzpd)
    {
      this.zzKt = paramInt;
      if (this.zzKo != null) {
        this.zzKo.setRequestedOrientation(this.zzKt);
      }
      return;
    }
  }
  
  public void setWebViewClient(WebViewClient paramWebViewClient)
  {
    super.setWebViewClient(paramWebViewClient);
    if ((paramWebViewClient instanceof zzja)) {
      this.zzKn = ((zzja)paramWebViewClient);
    }
  }
  
  public void stopLoading()
  {
    if (isDestroyed()) {}
    for (;;)
    {
      return;
      try
      {
        super.stopLoading();
      }
      catch (Exception localException)
      {
        zzb.zzb("Could not stop loading webview.", localException);
      }
    }
  }
  
  public void zzC(boolean paramBoolean)
  {
    synchronized (this.zzpd)
    {
      this.zzKr = paramBoolean;
      zzhz();
      return;
    }
  }
  
  public void zzD(boolean paramBoolean)
  {
    synchronized (this.zzpd)
    {
      if (this.zzKo != null)
      {
        this.zzKo.zza(this.zzKn.zzbY(), paramBoolean);
        return;
      }
      this.zzKp = paramBoolean;
    }
  }
  
  public void zzE(boolean paramBoolean)
  {
    synchronized (this.zzpd)
    {
      this.zzKu = paramBoolean;
      return;
    }
  }
  
  public void zza(Context paramContext, AdSizeParcel paramAdSizeParcel, zzcg paramzzcg)
  {
    synchronized (this.zzpd)
    {
      this.zzqR.zzgP();
      setContext(paramContext);
      this.zzKo = null;
      this.zzzm = paramAdSizeParcel;
      this.zzKr = false;
      this.zzKp = false;
      this.zzBY = "";
      this.zzKt = -1;
      zzp.zzbx().zzb(this);
      loadUrl("about:blank");
      this.zzKn.reset();
      setOnTouchListener(null);
      setOnClickListener(null);
      this.zzKu = true;
      zzd(paramzzcg);
      return;
    }
  }
  
  public void zza(AdSizeParcel paramAdSizeParcel)
  {
    synchronized (this.zzpd)
    {
      this.zzzm = paramAdSizeParcel;
      requestLayout();
      return;
    }
  }
  
  public void zza(zzaz paramzzaz, boolean paramBoolean)
  {
    HashMap localHashMap = new HashMap();
    if (paramBoolean) {}
    for (String str = "1";; str = "0")
    {
      localHashMap.put("isVisible", str);
      zzb("onAdVisibilityChanged", localHashMap);
      return;
    }
  }
  
  protected void zza(String paramString, ValueCallback<String> paramValueCallback)
  {
    synchronized (this.zzpd)
    {
      if (!isDestroyed()) {
        evaluateJavascript(paramString, paramValueCallback);
      }
      do
      {
        return;
        zzb.zzaH("The webview is destroyed. Ignoring action.");
      } while (paramValueCallback == null);
      paramValueCallback.onReceiveValue(null);
    }
  }
  
  public void zza(String paramString1, String paramString2)
  {
    zzaM(paramString1 + "(" + paramString2 + ");");
  }
  
  public void zza(String paramString, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      paramJSONObject = new JSONObject();
    }
    zza(paramString, paramJSONObject.toString());
  }
  
  public void zzaI(String paramString)
  {
    synchronized (this.zzpd)
    {
      try
      {
        super.loadUrl(paramString);
        return;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          zzb.zzaH("Could not call loadUrl. " + localThrowable);
        }
      }
    }
  }
  
  public void zzaJ(String paramString)
  {
    Object localObject1 = this.zzpd;
    if (paramString == null) {}
    try
    {
      paramString = "";
      this.zzBY = paramString;
      return;
    }
    finally
    {
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
  }
  
  protected void zzaL(String paramString)
  {
    synchronized (this.zzpd)
    {
      if (!isDestroyed())
      {
        loadUrl(paramString);
        return;
      }
      zzb.zzaH("The webview is destroyed. Ignoring action.");
    }
  }
  
  protected void zzaM(String paramString)
  {
    if (zzmx.zzqB())
    {
      if (zzgs() == null) {
        zzhy();
      }
      if (zzgs().booleanValue()) {
        zza(paramString, null);
      }
    }
    for (;;)
    {
      return;
      zzaL("javascript:" + paramString);
      continue;
      zzaL("javascript:" + paramString);
    }
  }
  
  public AdSizeParcel zzaN()
  {
    synchronized (this.zzpd)
    {
      AdSizeParcel localAdSizeParcel = this.zzzm;
      return localAdSizeParcel;
    }
  }
  
  public void zzb(com.google.android.gms.ads.internal.overlay.zzd paramzzd)
  {
    synchronized (this.zzpd)
    {
      this.zzKo = paramzzd;
      return;
    }
  }
  
  void zzb(Boolean paramBoolean)
  {
    this.zzHZ = paramBoolean;
    zzp.zzby().zzb(paramBoolean);
  }
  
  public void zzb(String paramString, Map<String, ?> paramMap)
  {
    try
    {
      JSONObject localJSONObject = zzp.zzbv().zzz(paramMap);
      zzb(paramString, localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        zzb.zzaH("Could not convert parameters to JSON.");
      }
    }
  }
  
  public void zzb(String paramString, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null) {
      paramJSONObject = new JSONObject();
    }
    String str = paramJSONObject.toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("AFMA_ReceiveMessage('");
    localStringBuilder.append(paramString);
    localStringBuilder.append("'");
    localStringBuilder.append(",");
    localStringBuilder.append(str);
    localStringBuilder.append(");");
    zzb.v("Dispatching AFMA event: " + localStringBuilder.toString());
    zzaM(localStringBuilder.toString());
  }
  
  public void zzc(com.google.android.gms.ads.internal.overlay.zzd paramzzd)
  {
    synchronized (this.zzpd)
    {
      this.zzKz = paramzzd;
      return;
    }
  }
  
  public void zzeJ()
  {
    if (this.zzKv != null)
    {
      zzcg localzzcg = this.zzKy.zzdm();
      zzce localzzce = this.zzKx;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = "aes";
      zzcc.zza(localzzcg, localzzce, arrayOfString);
      this.zzKv = zzcc.zzb(this.zzKy.zzdm());
      this.zzKy.zza("native:view_show", this.zzKx);
    }
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("version", this.zzpb.zzJu);
    zzb("onshow", localHashMap);
  }
  
  public void zzgY()
  {
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("version", this.zzpb.zzJu);
    zzb("onhide", localHashMap);
  }
  
  public Activity zzgZ()
  {
    return this.zzKm.zzgZ();
  }
  
  Boolean zzgs()
  {
    synchronized (this.zzpd)
    {
      Boolean localBoolean = this.zzHZ;
      return localBoolean;
    }
  }
  
  public Context zzha()
  {
    return this.zzKm.zzha();
  }
  
  public com.google.android.gms.ads.internal.zzd zzhb()
  {
    return this.zzow;
  }
  
  public com.google.android.gms.ads.internal.overlay.zzd zzhc()
  {
    synchronized (this.zzpd)
    {
      com.google.android.gms.ads.internal.overlay.zzd localzzd = this.zzKo;
      return localzzd;
    }
  }
  
  public com.google.android.gms.ads.internal.overlay.zzd zzhd()
  {
    synchronized (this.zzpd)
    {
      com.google.android.gms.ads.internal.overlay.zzd localzzd = this.zzKz;
      return localzzd;
    }
  }
  
  public zzja zzhe()
  {
    return this.zzKn;
  }
  
  public boolean zzhf()
  {
    return this.zzKp;
  }
  
  public zzan zzhg()
  {
    return this.zzwL;
  }
  
  public VersionInfoParcel zzhh()
  {
    return this.zzpb;
  }
  
  public boolean zzhi()
  {
    synchronized (this.zzpd)
    {
      boolean bool = this.zzKr;
      return bool;
    }
  }
  
  public void zzhj()
  {
    synchronized (this.zzpd)
    {
      zzb.v("Destroying WebView!");
      zzid.zzIE.post(new Runnable()
      {
        public void run()
        {
          zzjd.zza(zzjd.this);
        }
      });
      return;
    }
  }
  
  public boolean zzhk()
  {
    synchronized (this.zzpd)
    {
      boolean bool = this.zzKu;
      return bool;
    }
  }
  
  public zziy zzhl()
  {
    return null;
  }
  
  public zzce zzhm()
  {
    return this.zzKx;
  }
  
  public zzcf zzhn()
  {
    return this.zzKy;
  }
  
  public void zzho()
  {
    this.zzqR.zzgO();
  }
  
  public void zzhp()
  {
    if ((this.zzKx == null) && (!"about:blank".equals(getUrl())))
    {
      this.zzKx = zzcc.zzb(this.zzKy.zzdm());
      this.zzKy.zza("native:view_load", this.zzKx);
    }
  }
  
  public boolean zzhx()
  {
    boolean bool1 = false;
    if (!zzhe().zzbY()) {
      return bool1;
    }
    DisplayMetrics localDisplayMetrics = zzp.zzbv().zza(this.zzri);
    int i = zzl.zzcF().zzb(localDisplayMetrics, localDisplayMetrics.widthPixels);
    int j = zzl.zzcF().zzb(localDisplayMetrics, localDisplayMetrics.heightPixels);
    Activity localActivity = zzgZ();
    int k;
    int m;
    if ((localActivity == null) || (localActivity.getWindow() == null))
    {
      k = j;
      m = i;
      label76:
      if ((this.zzAD == i) && (this.zzAE == j) && (this.zzAG == m) && (this.zzAH == k)) {
        break label231;
      }
      if ((this.zzAD == i) && (this.zzAE == j)) {
        break label233;
      }
    }
    label231:
    label233:
    for (boolean bool2 = true;; bool2 = false)
    {
      this.zzAD = i;
      this.zzAE = j;
      this.zzAG = m;
      this.zzAH = k;
      new zzfh(this).zza(i, j, m, k, localDisplayMetrics.density, this.zzri.getDefaultDisplay().getRotation());
      bool1 = bool2;
      break;
      int[] arrayOfInt = zzp.zzbv().zzg(localActivity);
      m = zzl.zzcF().zzb(localDisplayMetrics, arrayOfInt[0]);
      k = zzl.zzcF().zzb(localDisplayMetrics, arrayOfInt[1]);
      break label76;
      break;
    }
  }
  
  public void zzv(int paramInt)
  {
    HashMap localHashMap = new HashMap(2);
    localHashMap.put("closetype", String.valueOf(paramInt));
    localHashMap.put("version", this.zzpb.zzJu);
    zzb("onhide", localHashMap);
  }
  
  @zzgr
  public static class zza
    extends MutableContextWrapper
  {
    private Activity zzJn;
    private Context zzKC;
    private Context zzqZ;
    
    public zza(Context paramContext)
    {
      super();
      setBaseContext(paramContext);
    }
    
    public Object getSystemService(String paramString)
    {
      return this.zzKC.getSystemService(paramString);
    }
    
    public void setBaseContext(Context paramContext)
    {
      this.zzqZ = paramContext.getApplicationContext();
      if ((paramContext instanceof Activity)) {}
      for (Activity localActivity = (Activity)paramContext;; localActivity = null)
      {
        this.zzJn = localActivity;
        this.zzKC = paramContext;
        super.setBaseContext(this.zzqZ);
        return;
      }
    }
    
    public void startActivity(Intent paramIntent)
    {
      if ((this.zzJn != null) && (!zzmx.isAtLeastL())) {
        this.zzJn.startActivity(paramIntent);
      }
      for (;;)
      {
        return;
        paramIntent.setFlags(268435456);
        this.zzqZ.startActivity(paramIntent);
      }
    }
    
    public Activity zzgZ()
    {
      return this.zzJn;
    }
    
    public Context zzha()
    {
      return this.zzKC;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzjd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */