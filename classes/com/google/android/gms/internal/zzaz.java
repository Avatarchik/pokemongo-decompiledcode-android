package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public class zzaz
  implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener
{
  private boolean zzpJ = false;
  private final Object zzpd = new Object();
  private zzik zzqM;
  private final Context zzqZ;
  private final WeakReference<zzhs> zzrb;
  private WeakReference<ViewTreeObserver> zzrc;
  private final WeakReference<View> zzrd;
  private final zzax zzre;
  private final zzdz zzrf;
  private final zzdz.zzd zzrg;
  private boolean zzrh;
  private final WindowManager zzri;
  private final PowerManager zzrj;
  private final KeyguardManager zzrk;
  private zzba zzrl;
  private boolean zzrm;
  private boolean zzrn = false;
  private boolean zzro;
  private boolean zzrp;
  private BroadcastReceiver zzrq;
  private final HashSet<zzaw> zzrr = new HashSet();
  private final zzdk zzrs = new zzdk()
  {
    public void zza(zziz paramAnonymouszziz, Map<String, String> paramAnonymousMap)
    {
      if (!zzaz.this.zzb(paramAnonymousMap)) {}
      for (;;)
      {
        return;
        zzaz.this.zza(paramAnonymouszziz.getView(), paramAnonymousMap);
      }
    }
  };
  private final zzdk zzrt = new zzdk()
  {
    public void zza(zziz paramAnonymouszziz, Map<String, String> paramAnonymousMap)
    {
      if (!zzaz.this.zzb(paramAnonymousMap)) {}
      for (;;)
      {
        return;
        zzb.zzaF("Received request to untrack: " + zzaz.zzb(zzaz.this).zzbX());
        zzaz.this.destroy();
      }
    }
  };
  private final zzdk zzru = new zzdk()
  {
    public void zza(zziz paramAnonymouszziz, Map<String, String> paramAnonymousMap)
    {
      if (!zzaz.this.zzb(paramAnonymousMap)) {
        return;
      }
      if (paramAnonymousMap.containsKey("isVisible")) {
        if ((!"1".equals(paramAnonymousMap.get("isVisible"))) && (!"true".equals(paramAnonymousMap.get("isVisible")))) {
          break label78;
        }
      }
      label78:
      for (boolean bool = true;; bool = false)
      {
        Boolean localBoolean = Boolean.valueOf(bool);
        zzaz.this.zzg(localBoolean.booleanValue());
        break;
        break;
      }
    }
  };
  
  public zzaz(AdSizeParcel paramAdSizeParcel, zzhs paramzzhs, VersionInfoParcel paramVersionInfoParcel, View paramView, zzdz paramzzdz)
  {
    this.zzrf = paramzzdz;
    this.zzrb = new WeakReference(paramzzhs);
    this.zzrd = new WeakReference(paramView);
    this.zzrc = new WeakReference(null);
    this.zzro = true;
    this.zzqM = new zzik(200L);
    this.zzre = new zzax(UUID.randomUUID().toString(), paramVersionInfoParcel, paramAdSizeParcel.zzte, paramzzhs.zzHw, paramzzhs.zzbY());
    this.zzrg = this.zzrf.zzdO();
    this.zzri = ((WindowManager)paramView.getContext().getSystemService("window"));
    this.zzrj = ((PowerManager)paramView.getContext().getApplicationContext().getSystemService("power"));
    this.zzrk = ((KeyguardManager)paramView.getContext().getSystemService("keyguard"));
    this.zzqZ = paramView.getContext().getApplicationContext();
    try
    {
      final JSONObject localJSONObject = zzd(paramView);
      this.zzrg.zza(new zzis.zzc()new zzis.zza
      {
        public void zzb(zzbe paramAnonymouszzbe)
        {
          zzaz.this.zza(localJSONObject);
        }
      }, new zzis.zza()
      {
        public void run() {}
      });
      this.zzrg.zza(new zzis.zzc()new zzis.zza
      {
        public void zzb(zzbe paramAnonymouszzbe)
        {
          zzaz.zzb(zzaz.this, true);
          zzaz.this.zza(paramAnonymouszzbe);
          zzaz.this.zzbZ();
          zzaz.this.zzh(false);
        }
      }, new zzis.zza()
      {
        public void run()
        {
          zzaz.this.destroy();
        }
      });
      zzb.zzaF("Tracking ad unit: " + this.zzre.zzbX());
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        zzb.zzb("Failure while processing active view data.", localRuntimeException);
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  protected void destroy()
  {
    synchronized (this.zzpd)
    {
      zzcf();
      zzca();
      this.zzro = false;
      zzcc();
      this.zzrg.release();
      return;
    }
  }
  
  boolean isScreenOn()
  {
    return this.zzrj.isScreenOn();
  }
  
  public void onGlobalLayout()
  {
    zzh(false);
  }
  
  public void onScrollChanged()
  {
    zzh(true);
  }
  
  public void pause()
  {
    synchronized (this.zzpd)
    {
      this.zzpJ = true;
      zzh(false);
      return;
    }
  }
  
  public void resume()
  {
    synchronized (this.zzpd)
    {
      this.zzpJ = false;
      zzh(false);
      return;
    }
  }
  
  public void stop()
  {
    synchronized (this.zzpd)
    {
      this.zzrn = true;
      zzh(false);
      return;
    }
  }
  
  protected int zza(int paramInt, DisplayMetrics paramDisplayMetrics)
  {
    float f = paramDisplayMetrics.density;
    return (int)(paramInt / f);
  }
  
  protected void zza(View paramView, Map<String, String> paramMap)
  {
    zzh(false);
  }
  
  public void zza(zzaw paramzzaw)
  {
    this.zzrr.add(paramzzaw);
  }
  
  public void zza(zzba paramzzba)
  {
    synchronized (this.zzpd)
    {
      this.zzrl = paramzzba;
      return;
    }
  }
  
  protected void zza(zzbe paramzzbe)
  {
    paramzzbe.zza("/updateActiveView", this.zzrs);
    paramzzbe.zza("/untrackActiveViewUnit", this.zzrt);
    paramzzbe.zza("/visibilityChanged", this.zzru);
  }
  
  protected void zza(JSONObject paramJSONObject)
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      final JSONObject localJSONObject = new JSONObject();
      localJSONArray.put(paramJSONObject);
      localJSONObject.put("units", localJSONArray);
      this.zzrg.zza(new zzis.zzc()new zzis.zzb
      {
        public void zzb(zzbe paramAnonymouszzbe)
        {
          paramAnonymouszzbe.zza("AFMA_updateActiveView", localJSONObject);
        }
      }, new zzis.zzb());
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        zzb.zzb("Skipping active view message.", localThrowable);
      }
    }
  }
  
  protected boolean zzb(Map<String, String> paramMap)
  {
    boolean bool1 = false;
    if (paramMap == null) {
      return bool1;
    }
    String str = (String)paramMap.get("hashCode");
    if ((!TextUtils.isEmpty(str)) && (str.equals(this.zzre.zzbX()))) {}
    for (boolean bool2 = true;; bool2 = false)
    {
      bool1 = bool2;
      break;
    }
  }
  
  protected void zzbZ()
  {
    synchronized (this.zzpd)
    {
      if (this.zzrq == null)
      {
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.intent.action.SCREEN_ON");
        localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.zzrq = new BroadcastReceiver()
        {
          public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
          {
            zzaz.this.zzh(false);
          }
        };
        this.zzqZ.registerReceiver(this.zzrq, localIntentFilter);
      }
    }
  }
  
  protected void zzca()
  {
    synchronized (this.zzpd)
    {
      if (this.zzrq != null)
      {
        this.zzqZ.unregisterReceiver(this.zzrq);
        this.zzrq = null;
      }
      return;
    }
  }
  
  public void zzcb()
  {
    synchronized (this.zzpd)
    {
      if (this.zzro) {
        this.zzrp = true;
      }
    }
    try
    {
      zza(zzch());
      zzb.zzaF("Untracking ad unit: " + this.zzre.zzbX());
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        zzb.zzb("JSON failure while processing active view data.", localJSONException);
      }
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (RuntimeException localRuntimeException)
    {
      for (;;)
      {
        zzb.zzb("Failure while processing active view data.", localRuntimeException);
      }
    }
  }
  
  protected void zzcc()
  {
    if (this.zzrl != null) {
      this.zzrl.zza(this);
    }
  }
  
  public boolean zzcd()
  {
    synchronized (this.zzpd)
    {
      boolean bool = this.zzro;
      return bool;
    }
  }
  
  protected void zzce()
  {
    View localView = (View)this.zzrd.get();
    if (localView == null) {}
    for (;;)
    {
      return;
      ViewTreeObserver localViewTreeObserver1 = (ViewTreeObserver)this.zzrc.get();
      ViewTreeObserver localViewTreeObserver2 = localView.getViewTreeObserver();
      if (localViewTreeObserver2 != localViewTreeObserver1)
      {
        zzcf();
        if ((!this.zzrm) || ((localViewTreeObserver1 != null) && (localViewTreeObserver1.isAlive())))
        {
          this.zzrm = true;
          localViewTreeObserver2.addOnScrollChangedListener(this);
          localViewTreeObserver2.addOnGlobalLayoutListener(this);
        }
        this.zzrc = new WeakReference(localViewTreeObserver2);
      }
    }
  }
  
  protected void zzcf()
  {
    ViewTreeObserver localViewTreeObserver = (ViewTreeObserver)this.zzrc.get();
    if ((localViewTreeObserver == null) || (!localViewTreeObserver.isAlive())) {}
    for (;;)
    {
      return;
      localViewTreeObserver.removeOnScrollChangedListener(this);
      localViewTreeObserver.removeGlobalOnLayoutListener(this);
    }
  }
  
  protected JSONObject zzcg()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("afmaVersion", this.zzre.zzbV()).put("activeViewJSON", this.zzre.zzbW()).put("timestamp", zzp.zzbz().elapsedRealtime()).put("adFormat", this.zzre.zzbU()).put("hashCode", this.zzre.zzbX()).put("isMraid", this.zzre.zzbY());
    return localJSONObject;
  }
  
  protected JSONObject zzch()
    throws JSONException
  {
    JSONObject localJSONObject = zzcg();
    localJSONObject.put("doneReasonCode", "u");
    return localJSONObject;
  }
  
  protected JSONObject zzd(View paramView)
    throws JSONException
  {
    boolean bool1 = zzp.zzbx().isAttachedToWindow(paramView);
    int[] arrayOfInt1 = new int[2];
    int[] arrayOfInt2 = new int[2];
    try
    {
      paramView.getLocationOnScreen(arrayOfInt1);
      paramView.getLocationInWindow(arrayOfInt2);
      DisplayMetrics localDisplayMetrics = paramView.getContext().getResources().getDisplayMetrics();
      Rect localRect1 = new Rect();
      localRect1.left = arrayOfInt1[0];
      localRect1.top = arrayOfInt1[1];
      localRect1.right = (localRect1.left + paramView.getWidth());
      localRect1.bottom = (localRect1.top + paramView.getHeight());
      Rect localRect2 = new Rect();
      localRect2.right = this.zzri.getDefaultDisplay().getWidth();
      localRect2.bottom = this.zzri.getDefaultDisplay().getHeight();
      Rect localRect3 = new Rect();
      boolean bool2 = paramView.getGlobalVisibleRect(localRect3, null);
      Rect localRect4 = new Rect();
      boolean bool3 = paramView.getLocalVisibleRect(localRect4);
      Rect localRect5 = new Rect();
      paramView.getHitRect(localRect5);
      JSONObject localJSONObject = zzcg();
      localJSONObject.put("windowVisibility", paramView.getWindowVisibility()).put("isStopped", this.zzrn).put("isPaused", this.zzpJ).put("isScreenOn", isScreenOn()).put("isAttachedToWindow", bool1).put("viewBox", new JSONObject().put("top", zza(localRect2.top, localDisplayMetrics)).put("bottom", zza(localRect2.bottom, localDisplayMetrics)).put("left", zza(localRect2.left, localDisplayMetrics)).put("right", zza(localRect2.right, localDisplayMetrics))).put("adBox", new JSONObject().put("top", zza(localRect1.top, localDisplayMetrics)).put("bottom", zza(localRect1.bottom, localDisplayMetrics)).put("left", zza(localRect1.left, localDisplayMetrics)).put("right", zza(localRect1.right, localDisplayMetrics))).put("globalVisibleBox", new JSONObject().put("top", zza(localRect3.top, localDisplayMetrics)).put("bottom", zza(localRect3.bottom, localDisplayMetrics)).put("left", zza(localRect3.left, localDisplayMetrics)).put("right", zza(localRect3.right, localDisplayMetrics))).put("globalVisibleBoxVisible", bool2).put("localVisibleBox", new JSONObject().put("top", zza(localRect4.top, localDisplayMetrics)).put("bottom", zza(localRect4.bottom, localDisplayMetrics)).put("left", zza(localRect4.left, localDisplayMetrics)).put("right", zza(localRect4.right, localDisplayMetrics))).put("localVisibleBoxVisible", bool3).put("hitBox", new JSONObject().put("top", zza(localRect5.top, localDisplayMetrics)).put("bottom", zza(localRect5.bottom, localDisplayMetrics)).put("left", zza(localRect5.left, localDisplayMetrics)).put("right", zza(localRect5.right, localDisplayMetrics))).put("screenDensity", localDisplayMetrics.density).put("isVisible", zze(paramView));
      return localJSONObject;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        zzb.zzb("Failure getting view location.", localException);
      }
    }
  }
  
  protected boolean zze(View paramView)
  {
    if ((paramView.getVisibility() == 0) && (paramView.isShown()) && (isScreenOn()) && ((!this.zzrk.inKeyguardRestrictedInputMode()) || (zzp.zzbv().zzgB()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected void zzg(boolean paramBoolean)
  {
    Iterator localIterator = this.zzrr.iterator();
    while (localIterator.hasNext()) {
      ((zzaw)localIterator.next()).zza(this, paramBoolean);
    }
  }
  
  protected void zzh(boolean paramBoolean)
  {
    for (;;)
    {
      zzhs localzzhs;
      View localView;
      int i;
      synchronized (this.zzpd)
      {
        if ((!this.zzrh) || (this.zzro)) {
          if ((!paramBoolean) || (this.zzqM.tryAcquire())) {}
        }
      }
      try
      {
        zza(zzd(localView));
        for (;;)
        {
          zzce();
          zzcc();
          break;
          Throwable localThrowable;
          zzb.zza("Active view update failed.", localThrowable);
        }
      }
      catch (JSONException localJSONException)
      {
        for (;;) {}
        return;
        i = 1;
      }
      catch (RuntimeException localRuntimeException)
      {
        for (;;) {}
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzaz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */