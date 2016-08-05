package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@zzgr
public class zzjg
  extends zzja
{
  public zzjg(zziz paramzziz, boolean paramBoolean)
  {
    super(paramzziz, paramBoolean);
  }
  
  public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
  {
    try
    {
      WebResourceResponse localWebResourceResponse;
      if (!"mraid.js".equalsIgnoreCase(new File(paramString).getName()))
      {
        localWebResourceResponse = super.shouldInterceptRequest(paramWebView, paramString);
      }
      else if (!(paramWebView instanceof zziz))
      {
        zzb.zzaH("Tried to intercept request from a WebView that wasn't an AdWebView.");
        localWebResourceResponse = super.shouldInterceptRequest(paramWebView, paramString);
      }
      else
      {
        zziz localzziz = (zziz)paramWebView;
        localzziz.zzhe().zzeG();
        String str;
        if (localzziz.zzaN().zztf) {
          str = (String)zzby.zzuP.get();
        }
        for (;;)
        {
          zzb.v("shouldInterceptRequest(" + str + ")");
          localWebResourceResponse = zzd(localzziz.getContext(), this.zzoM.zzhh().zzJu, str);
          break;
          if (localzziz.zzhi()) {
            str = (String)zzby.zzuO.get();
          } else {
            str = (String)zzby.zzuN.get();
          }
        }
      }
      return localWebResourceResponse;
    }
    catch (InterruptedException localInterruptedException)
    {
      zzb.zzaH("Could not fetch MRAID JS. " + localInterruptedException.getMessage());
      localWebResourceResponse = super.shouldInterceptRequest(paramWebView, paramString);
    }
    catch (ExecutionException localExecutionException)
    {
      for (;;) {}
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
    catch (TimeoutException localTimeoutException)
    {
      for (;;) {}
    }
  }
  
  protected WebResourceResponse zzd(Context paramContext, String paramString1, String paramString2)
    throws IOException, ExecutionException, InterruptedException, TimeoutException
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("User-Agent", zzp.zzbv().zzf(paramContext, paramString1));
    localHashMap.put("Cache-Control", "max-stale=3600");
    String str = (String)new zzih(paramContext).zza(paramString2, localHashMap).get(60L, TimeUnit.SECONDS);
    if (str == null) {}
    for (WebResourceResponse localWebResourceResponse = null;; localWebResourceResponse = new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(str.getBytes("UTF-8")))) {
      return localWebResourceResponse;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzjg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */