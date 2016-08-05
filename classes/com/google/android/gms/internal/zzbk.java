package com.google.android.gms.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public class zzbk
  extends Thread
{
  private boolean mStarted = false;
  private boolean zzam = false;
  private final Object zzpd;
  private final int zzrN;
  private final int zzrP;
  private boolean zzrZ = false;
  private final zzbj zzsa;
  private final zzbi zzsb;
  private final zzgq zzsc;
  private final int zzsd;
  private final int zzse;
  private final int zzsf;
  
  public zzbk(zzbj paramzzbj, zzbi paramzzbi, zzgq paramzzgq)
  {
    this.zzsa = paramzzbj;
    this.zzsb = paramzzbi;
    this.zzsc = paramzzgq;
    this.zzpd = new Object();
    this.zzrN = ((Integer)zzby.zzuU.get()).intValue();
    this.zzse = ((Integer)zzby.zzuV.get()).intValue();
    this.zzrP = ((Integer)zzby.zzuW.get()).intValue();
    this.zzsf = ((Integer)zzby.zzuX.get()).intValue();
    this.zzsd = ((Integer)zzby.zzuY.get()).intValue();
    setName("ContentFetchTask");
  }
  
  public void run()
  {
    while (!this.zzam) {
      try
      {
        if (zzcu())
        {
          localActivity = this.zzsa.getActivity();
          if (localActivity == null) {
            zzb.zzaF("ContentFetchThread: no activity");
          }
        }
      }
      catch (Throwable localThrowable)
      {
        Activity localActivity;
        zzb.zzb("Error in ContentFetchTask", localThrowable);
        this.zzsc.zza(localThrowable, true);
        synchronized (this.zzpd)
        {
          for (;;)
          {
            boolean bool = this.zzrZ;
            if (!bool) {
              break;
            }
            try
            {
              zzb.zzaF("ContentFetchTask: waiting");
              this.zzpd.wait();
            }
            catch (InterruptedException localInterruptedException) {}
          }
          zza(localActivity);
          for (;;)
          {
            Thread.sleep(1000 * this.zzsd);
            break;
            zzb.zzaF("ContentFetchTask: sleeping");
            zzcw();
          }
        }
      }
    }
  }
  
  public void wakeup()
  {
    synchronized (this.zzpd)
    {
      this.zzrZ = false;
      this.zzpd.notifyAll();
      zzb.zzaF("ContentFetchThread: wakeup");
      return;
    }
  }
  
  zza zza(View paramView, zzbh paramzzbh)
  {
    int i = 0;
    zza localzza1;
    if (paramView == null) {
      localzza1 = new zza(0, 0);
    }
    for (;;)
    {
      return localzza1;
      if (((paramView instanceof TextView)) && (!(paramView instanceof EditText)))
      {
        CharSequence localCharSequence = ((TextView)paramView).getText();
        if (!TextUtils.isEmpty(localCharSequence))
        {
          paramzzbh.zzw(localCharSequence.toString());
          localzza1 = new zza(1, 0);
        }
        else
        {
          localzza1 = new zza(0, 0);
        }
      }
      else if (((paramView instanceof WebView)) && (!(paramView instanceof zziz)))
      {
        paramzzbh.zzcp();
        if (zza((WebView)paramView, paramzzbh)) {
          localzza1 = new zza(0, 1);
        } else {
          localzza1 = new zza(0, 0);
        }
      }
      else if ((paramView instanceof ViewGroup))
      {
        ViewGroup localViewGroup = (ViewGroup)paramView;
        int j = 0;
        int k = 0;
        while (i < localViewGroup.getChildCount())
        {
          zza localzza2 = zza(localViewGroup.getChildAt(i), paramzzbh);
          k += localzza2.zzsm;
          j += localzza2.zzsn;
          i++;
        }
        localzza1 = new zza(k, j);
      }
      else
      {
        localzza1 = new zza(0, 0);
      }
    }
  }
  
  void zza(Activity paramActivity)
  {
    if (paramActivity == null) {}
    for (;;)
    {
      return;
      View localView = null;
      if ((paramActivity.getWindow() != null) && (paramActivity.getWindow().getDecorView() != null)) {
        localView = paramActivity.getWindow().getDecorView().findViewById(16908290);
      }
      if (localView != null) {
        zzf(localView);
      }
    }
  }
  
  void zza(zzbh paramzzbh, WebView paramWebView, String paramString)
  {
    paramzzbh.zzco();
    try
    {
      String str;
      if (!TextUtils.isEmpty(paramString))
      {
        str = new JSONObject(paramString).optString("text");
        if (TextUtils.isEmpty(paramWebView.getTitle())) {
          break label87;
        }
        paramzzbh.zzv(paramWebView.getTitle() + "\n" + str);
      }
      while (paramzzbh.zzcl())
      {
        this.zzsb.zzb(paramzzbh);
        break;
        label87:
        paramzzbh.zzv(str);
      }
      return;
    }
    catch (JSONException localJSONException)
    {
      zzb.zzaF("Json string may be malformed.");
    }
    catch (Throwable localThrowable)
    {
      zzb.zza("Failed to get webview content.", localThrowable);
      this.zzsc.zza(localThrowable, true);
    }
  }
  
  boolean zza(ActivityManager.RunningAppProcessInfo paramRunningAppProcessInfo)
  {
    if (paramRunningAppProcessInfo.importance == 100) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  boolean zza(final WebView paramWebView, final zzbh paramzzbh)
  {
    if (!zzmx.zzqB()) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      paramzzbh.zzcp();
      paramWebView.post(new Runnable()
      {
        ValueCallback<String> zzsi = new ValueCallback()
        {
          public void zzy(String paramAnonymous2String)
          {
            zzbk.this.zza(zzbk.2.this.zzsj, zzbk.2.this.zzsk, paramAnonymous2String);
          }
        };
        
        public void run()
        {
          if (paramWebView.getSettings().getJavaScriptEnabled()) {}
          try
          {
            paramWebView.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.zzsi);
            return;
          }
          catch (Throwable localThrowable)
          {
            for (;;)
            {
              this.zzsi.onReceiveValue("");
            }
          }
        }
      });
    }
  }
  
  public void zzct()
  {
    synchronized (this.zzpd)
    {
      if (this.mStarted)
      {
        zzb.zzaF("Content hash thread already started, quiting...");
      }
      else
      {
        this.mStarted = true;
        start();
      }
    }
  }
  
  boolean zzcu()
  {
    boolean bool1;
    try
    {
      Context localContext = this.zzsa.getContext();
      if (localContext == null)
      {
        bool1 = false;
      }
      else
      {
        ActivityManager localActivityManager = (ActivityManager)localContext.getSystemService("activity");
        KeyguardManager localKeyguardManager = (KeyguardManager)localContext.getSystemService("keyguard");
        if ((localActivityManager == null) || (localKeyguardManager == null)) {
          break label157;
        }
        List localList = localActivityManager.getRunningAppProcesses();
        if (localList == null)
        {
          bool1 = false;
        }
        else
        {
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
            if (Process.myPid() == localRunningAppProcessInfo.pid) {
              if ((zza(localRunningAppProcessInfo)) && (!localKeyguardManager.inKeyguardRestrictedInputMode()))
              {
                boolean bool2 = zzr(localContext);
                if (bool2)
                {
                  bool1 = true;
                  break label155;
                }
              }
            }
          }
          bool1 = false;
        }
      }
    }
    catch (Throwable localThrowable)
    {
      bool1 = false;
    }
    for (;;)
    {
      label155:
      return bool1;
      label157:
      bool1 = false;
    }
  }
  
  public zzbh zzcv()
  {
    return this.zzsb.zzcs();
  }
  
  public void zzcw()
  {
    synchronized (this.zzpd)
    {
      this.zzrZ = true;
      zzb.zzaF("ContentFetchThread: paused, mPause = " + this.zzrZ);
      return;
    }
  }
  
  public boolean zzcx()
  {
    return this.zzrZ;
  }
  
  boolean zzf(final View paramView)
  {
    if (paramView == null) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      paramView.post(new Runnable()
      {
        public void run()
        {
          zzbk.this.zzg(paramView);
        }
      });
    }
  }
  
  void zzg(View paramView)
  {
    try
    {
      zzbh localzzbh = new zzbh(this.zzrN, this.zzse, this.zzrP, this.zzsf);
      zza localzza = zza(paramView, localzzbh);
      localzzbh.zzcq();
      if (((localzza.zzsm != 0) || (localzza.zzsn != 0)) && ((localzza.zzsn != 0) || (localzzbh.zzcr() != 0)) && ((localzza.zzsn != 0) || (!this.zzsb.zza(localzzbh)))) {
        this.zzsb.zzc(localzzbh);
      }
    }
    catch (Exception localException)
    {
      zzb.zzb("Exception in fetchContentOnUIThread", localException);
      this.zzsc.zza(localException, true);
    }
  }
  
  boolean zzr(Context paramContext)
  {
    PowerManager localPowerManager = (PowerManager)paramContext.getSystemService("power");
    if (localPowerManager == null) {}
    for (boolean bool = false;; bool = localPowerManager.isScreenOn()) {
      return bool;
    }
  }
  
  @zzgr
  class zza
  {
    final int zzsm;
    final int zzsn;
    
    zza(int paramInt1, int paramInt2)
    {
      this.zzsm = paramInt1;
      this.zzsn = paramInt2;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzbk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */