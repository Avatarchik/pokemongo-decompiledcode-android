package com.google.android.gms.internal;

import android.app.Activity;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.File;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@zzgr
public class zzie
{
  public static zzie zzM(int paramInt)
  {
    Object localObject;
    if (paramInt >= 19) {
      localObject = new zzg();
    }
    for (;;)
    {
      return (zzie)localObject;
      if (paramInt >= 18) {
        localObject = new zze();
      } else if (paramInt >= 17) {
        localObject = new zzd();
      } else if (paramInt >= 16) {
        localObject = new zzf();
      } else if (paramInt >= 14) {
        localObject = new zzc();
      } else if (paramInt >= 11) {
        localObject = new zzb();
      } else if (paramInt >= 9) {
        localObject = new zza();
      } else {
        localObject = new zzie();
      }
    }
  }
  
  public String getDefaultUserAgent(Context paramContext)
  {
    return "";
  }
  
  public boolean isAttachedToWindow(View paramView)
  {
    if ((paramView.getWindowToken() != null) || (paramView.getWindowVisibility() != 8)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Drawable zza(Context paramContext, Bitmap paramBitmap, boolean paramBoolean, float paramFloat)
  {
    return new BitmapDrawable(paramContext.getResources(), paramBitmap);
  }
  
  public String zza(SslError paramSslError)
  {
    return "";
  }
  
  public void zza(View paramView, Drawable paramDrawable)
  {
    paramView.setBackgroundDrawable(paramDrawable);
  }
  
  public void zza(ViewTreeObserver paramViewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener paramOnGlobalLayoutListener)
  {
    paramViewTreeObserver.removeGlobalOnLayoutListener(paramOnGlobalLayoutListener);
  }
  
  public boolean zza(DownloadManager.Request paramRequest)
  {
    return false;
  }
  
  public boolean zza(Context paramContext, WebSettings paramWebSettings)
  {
    return false;
  }
  
  public boolean zza(Window paramWindow)
  {
    return false;
  }
  
  public boolean zza(WebView paramWebView)
  {
    if (paramWebView == null) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      paramWebView.onPause();
    }
  }
  
  public zzja zzb(zziz paramzziz, boolean paramBoolean)
  {
    return new zzja(paramzziz, paramBoolean);
  }
  
  public void zzb(Activity paramActivity, ViewTreeObserver.OnGlobalLayoutListener paramOnGlobalLayoutListener)
  {
    Window localWindow = paramActivity.getWindow();
    if ((localWindow != null) && (localWindow.getDecorView() != null) && (localWindow.getDecorView().getViewTreeObserver() != null)) {
      zza(localWindow.getDecorView().getViewTreeObserver(), paramOnGlobalLayoutListener);
    }
  }
  
  public boolean zzb(WebView paramWebView)
  {
    if (paramWebView == null) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      paramWebView.onResume();
    }
  }
  
  public WebChromeClient zzf(zziz paramzziz)
  {
    return null;
  }
  
  public Set<String> zzf(Uri paramUri)
  {
    Set localSet;
    if (paramUri.isOpaque()) {
      localSet = Collections.emptySet();
    }
    for (;;)
    {
      return localSet;
      String str = paramUri.getEncodedQuery();
      if (str == null)
      {
        localSet = Collections.emptySet();
      }
      else
      {
        LinkedHashSet localLinkedHashSet = new LinkedHashSet();
        int i = 0;
        do
        {
          int j = str.indexOf('&', i);
          if (j == -1) {
            j = str.length();
          }
          int k = str.indexOf('=', i);
          if ((k > j) || (k == -1)) {
            k = j;
          }
          localLinkedHashSet.add(Uri.decode(str.substring(i, k)));
          i = j + 1;
        } while (i < str.length());
        localSet = Collections.unmodifiableSet(localLinkedHashSet);
      }
    }
  }
  
  public int zzgG()
  {
    return 0;
  }
  
  public int zzgH()
  {
    return 1;
  }
  
  public int zzgI()
  {
    return 5;
  }
  
  public ViewGroup.LayoutParams zzgJ()
  {
    return new ViewGroup.LayoutParams(-2, -2);
  }
  
  public boolean zzl(View paramView)
  {
    return false;
  }
  
  public boolean zzm(View paramView)
  {
    return false;
  }
  
  public static class zzg
    extends zzie.zze
  {
    public boolean isAttachedToWindow(View paramView)
    {
      return paramView.isAttachedToWindow();
    }
    
    public ViewGroup.LayoutParams zzgJ()
    {
      return new ViewGroup.LayoutParams(-1, -1);
    }
  }
  
  public static class zze
    extends zzie.zzd
  {
    public boolean isAttachedToWindow(View paramView)
    {
      if ((super.isAttachedToWindow(paramView)) || (paramView.getWindowId() != null)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public int zzgI()
    {
      return 14;
    }
  }
  
  public static class zzd
    extends zzie.zzf
  {
    public String getDefaultUserAgent(Context paramContext)
    {
      return WebSettings.getDefaultUserAgent(paramContext);
    }
    
    public Drawable zza(Context paramContext, Bitmap paramBitmap, boolean paramBoolean, float paramFloat)
    {
      BitmapDrawable localBitmapDrawable;
      if ((!paramBoolean) || (paramFloat <= 0.0F) || (paramFloat > 25.0F)) {
        localBitmapDrawable = new BitmapDrawable(paramContext.getResources(), paramBitmap);
      }
      for (;;)
      {
        return localBitmapDrawable;
        try
        {
          Bitmap localBitmap1 = Bitmap.createScaledBitmap(paramBitmap, paramBitmap.getWidth(), paramBitmap.getHeight(), false);
          Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1);
          RenderScript localRenderScript = RenderScript.create(paramContext);
          ScriptIntrinsicBlur localScriptIntrinsicBlur = ScriptIntrinsicBlur.create(localRenderScript, Element.U8_4(localRenderScript));
          Allocation localAllocation1 = Allocation.createFromBitmap(localRenderScript, localBitmap1);
          Allocation localAllocation2 = Allocation.createFromBitmap(localRenderScript, localBitmap2);
          localScriptIntrinsicBlur.setRadius(paramFloat);
          localScriptIntrinsicBlur.setInput(localAllocation1);
          localScriptIntrinsicBlur.forEach(localAllocation2);
          localAllocation2.copyTo(localBitmap2);
          localBitmapDrawable = new BitmapDrawable(paramContext.getResources(), localBitmap2);
        }
        catch (RuntimeException localRuntimeException)
        {
          localBitmapDrawable = new BitmapDrawable(paramContext.getResources(), paramBitmap);
        }
      }
    }
    
    public boolean zza(Context paramContext, WebSettings paramWebSettings)
    {
      super.zza(paramContext, paramWebSettings);
      paramWebSettings.setMediaPlaybackRequiresUserGesture(false);
      return true;
    }
  }
  
  public static class zzf
    extends zzie.zzc
  {
    public void zza(View paramView, Drawable paramDrawable)
    {
      paramView.setBackground(paramDrawable);
    }
    
    public void zza(ViewTreeObserver paramViewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener paramOnGlobalLayoutListener)
    {
      paramViewTreeObserver.removeOnGlobalLayoutListener(paramOnGlobalLayoutListener);
    }
    
    public void zzb(Activity paramActivity, ViewTreeObserver.OnGlobalLayoutListener paramOnGlobalLayoutListener)
    {
      Window localWindow = paramActivity.getWindow();
      if ((localWindow != null) && (localWindow.getDecorView() != null) && (localWindow.getDecorView().getViewTreeObserver() != null)) {
        zza(localWindow.getDecorView().getViewTreeObserver(), paramOnGlobalLayoutListener);
      }
    }
  }
  
  public static class zzc
    extends zzie.zzb
  {
    public String zza(SslError paramSslError)
    {
      return paramSslError.getUrl();
    }
    
    public WebChromeClient zzf(zziz paramzziz)
    {
      return new zzjh(paramzziz);
    }
  }
  
  public static class zzb
    extends zzie.zza
  {
    public boolean zza(DownloadManager.Request paramRequest)
    {
      paramRequest.allowScanningByMediaScanner();
      paramRequest.setNotificationVisibility(1);
      return true;
    }
    
    public boolean zza(Context paramContext, WebSettings paramWebSettings)
    {
      if (paramContext.getCacheDir() != null)
      {
        paramWebSettings.setAppCachePath(paramContext.getCacheDir().getAbsolutePath());
        paramWebSettings.setAppCacheMaxSize(0L);
        paramWebSettings.setAppCacheEnabled(true);
      }
      paramWebSettings.setDatabasePath(paramContext.getDatabasePath("com.google.android.gms.ads.db").getAbsolutePath());
      paramWebSettings.setDatabaseEnabled(true);
      paramWebSettings.setDomStorageEnabled(true);
      paramWebSettings.setDisplayZoomControls(false);
      paramWebSettings.setBuiltInZoomControls(true);
      paramWebSettings.setSupportZoom(true);
      return true;
    }
    
    public boolean zza(Window paramWindow)
    {
      paramWindow.setFlags(16777216, 16777216);
      return true;
    }
    
    public zzja zzb(zziz paramzziz, boolean paramBoolean)
    {
      return new zzjg(paramzziz, paramBoolean);
    }
    
    public WebChromeClient zzf(zziz paramzziz)
    {
      return new zzjf(paramzziz);
    }
    
    public Set<String> zzf(Uri paramUri)
    {
      return paramUri.getQueryParameterNames();
    }
    
    public boolean zzl(View paramView)
    {
      paramView.setLayerType(0, null);
      return true;
    }
    
    public boolean zzm(View paramView)
    {
      paramView.setLayerType(1, null);
      return true;
    }
  }
  
  public static class zza
    extends zzie
  {
    public zza()
    {
      super();
    }
    
    public boolean zza(DownloadManager.Request paramRequest)
    {
      paramRequest.setShowRunningNotification(true);
      return true;
    }
    
    public int zzgG()
    {
      return 6;
    }
    
    public int zzgH()
    {
      return 7;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */