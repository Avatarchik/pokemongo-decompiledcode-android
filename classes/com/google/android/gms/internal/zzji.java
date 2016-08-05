package com.google.android.gms.internal;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzw;
import java.net.URI;
import java.net.URISyntaxException;

@zzgr
public class zzji
  extends WebViewClient
{
  private final zzgd zzDt;
  private final String zzKH = zzaO(paramString);
  private boolean zzKI = false;
  private final zziz zzoM;
  
  public zzji(zzgd paramzzgd, zziz paramzziz, String paramString)
  {
    this.zzoM = paramzziz;
    this.zzDt = paramzzgd;
  }
  
  private String zzaO(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return paramString;
      try
      {
        if (paramString.endsWith("/"))
        {
          String str = paramString.substring(0, -1 + paramString.length());
          paramString = str;
        }
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
        zzb.e(localIndexOutOfBoundsException.getMessage());
      }
    }
  }
  
  public void onLoadResource(WebView paramWebView, String paramString)
  {
    zzb.zzaF("JavascriptAdWebViewClient::onLoadResource: " + paramString);
    if (!zzaN(paramString)) {
      this.zzoM.zzhe().onLoadResource(this.zzoM.getWebView(), paramString);
    }
  }
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    zzb.zzaF("JavascriptAdWebViewClient::onPageFinished: " + paramString);
    if (!this.zzKI)
    {
      this.zzDt.zzfv();
      this.zzKI = true;
    }
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    zzb.zzaF("JavascriptAdWebViewClient::shouldOverrideUrlLoading: " + paramString);
    if (zzaN(paramString)) {
      zzb.zzaF("shouldOverrideUrlLoading: received passback url");
    }
    for (boolean bool = true;; bool = this.zzoM.zzhe().shouldOverrideUrlLoading(this.zzoM.getWebView(), paramString)) {
      return bool;
    }
  }
  
  protected boolean zzaN(String paramString)
  {
    boolean bool = false;
    String str1 = zzaO(paramString);
    if (TextUtils.isEmpty(str1)) {}
    for (;;)
    {
      return bool;
      try
      {
        URI localURI1 = new URI(str1);
        if ("passback".equals(localURI1.getScheme()))
        {
          zzb.zzaF("Passback received");
          this.zzDt.zzfw();
          bool = true;
        }
        else if (!TextUtils.isEmpty(this.zzKH))
        {
          URI localURI2 = new URI(this.zzKH);
          String str2 = localURI2.getHost();
          String str3 = localURI1.getHost();
          String str4 = localURI2.getPath();
          String str5 = localURI1.getPath();
          if ((zzw.equal(str2, str3)) && (zzw.equal(str4, str5)))
          {
            zzb.zzaF("Passback received");
            this.zzDt.zzfw();
            bool = true;
          }
        }
      }
      catch (URISyntaxException localURISyntaxException)
      {
        zzb.e(localURISyntaxException.getMessage());
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzji.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */