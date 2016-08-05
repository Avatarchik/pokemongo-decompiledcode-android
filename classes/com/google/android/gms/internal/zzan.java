package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.view.MotionEvent;

public class zzan
{
  private static final String[] zznC;
  private String zznA = "ad.doubleclick.net";
  private String[] zznB;
  private zzaj zznD;
  private String zzny = "googleads.g.doubleclick.net";
  private String zznz = "/pagead/ads";
  
  static
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "/aclk";
    arrayOfString[1] = "/pcs/click";
    zznC = arrayOfString;
  }
  
  public zzan(zzaj paramzzaj)
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = ".doubleclick.net";
    arrayOfString[1] = ".googleadservices.com";
    arrayOfString[2] = ".googlesyndication.com";
    this.zznB = arrayOfString;
    this.zznD = paramzzaj;
  }
  
  private Uri zza(Uri paramUri, Context paramContext, String paramString, boolean paramBoolean)
    throws zzao
  {
    boolean bool;
    try
    {
      bool = zza(paramUri);
      if (bool)
      {
        if (!paramUri.toString().contains("dc_ms=")) {
          break label65;
        }
        throw new zzao("Parameter already exists: dc_ms");
      }
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      throw new zzao("Provided Uri is not in a valid state");
    }
    if (paramUri.getQueryParameter("ms") != null) {
      throw new zzao("Query parameter already exists: ms");
    }
    label65:
    if (paramBoolean) {}
    for (String str = this.zznD.zzb(paramContext, paramString); bool; str = this.zznD.zzb(paramContext))
    {
      localObject = zzb(paramUri, "dc_ms", str);
      break label132;
    }
    Uri localUri = zza(paramUri, "ms", str);
    Object localObject = localUri;
    label132:
    return (Uri)localObject;
  }
  
  private Uri zza(Uri paramUri, String paramString1, String paramString2)
    throws UnsupportedOperationException
  {
    String str = paramUri.toString();
    int i = str.indexOf("&adurl");
    if (i == -1) {
      i = str.indexOf("?adurl");
    }
    if (i != -1) {}
    for (Uri localUri = Uri.parse(str.substring(0, i + 1) + paramString1 + "=" + paramString2 + "&" + str.substring(i + 1));; localUri = paramUri.buildUpon().appendQueryParameter(paramString1, paramString2).build()) {
      return localUri;
    }
  }
  
  private Uri zzb(Uri paramUri, String paramString1, String paramString2)
  {
    String str1 = paramUri.toString();
    int i = str1.indexOf(";adurl");
    if (i != -1) {}
    String str2;
    int j;
    for (Uri localUri = Uri.parse(str1.substring(0, i + 1) + paramString1 + "=" + paramString2 + ";" + str1.substring(i + 1));; localUri = Uri.parse(str1.substring(0, j + str2.length()) + ";" + paramString1 + "=" + paramString2 + ";" + str1.substring(j + str2.length())))
    {
      return localUri;
      str2 = paramUri.getEncodedPath();
      j = str1.indexOf(str2);
    }
  }
  
  public Uri zza(Uri paramUri, Context paramContext)
    throws zzao
  {
    try
    {
      Uri localUri = zza(paramUri, paramContext, paramUri.getQueryParameter("ai"), true);
      return localUri;
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      throw new zzao("Provided Uri is not in a valid state");
    }
  }
  
  public void zza(MotionEvent paramMotionEvent)
  {
    this.zznD.zza(paramMotionEvent);
  }
  
  public boolean zza(Uri paramUri)
  {
    if (paramUri == null) {
      throw new NullPointerException();
    }
    try
    {
      boolean bool2 = paramUri.getHost().equals(this.zznA);
      bool1 = bool2;
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;)
      {
        boolean bool1 = false;
      }
    }
    return bool1;
  }
  
  public zzaj zzab()
  {
    return this.zznD;
  }
  
  public boolean zzb(Uri paramUri)
  {
    boolean bool1 = false;
    if (paramUri == null) {
      throw new NullPointerException();
    }
    for (;;)
    {
      try
      {
        String str = paramUri.getHost();
        String[] arrayOfString = this.zznB;
        int i = arrayOfString.length;
        j = 0;
        if (j < i)
        {
          boolean bool2 = str.endsWith(arrayOfString[j]);
          if (!bool2) {
            continue;
          }
          bool1 = true;
        }
      }
      catch (NullPointerException localNullPointerException)
      {
        int j;
        continue;
      }
      return bool1;
      j++;
    }
  }
  
  public boolean zzc(Uri paramUri)
  {
    boolean bool = false;
    String[] arrayOfString;
    int i;
    if (zzb(paramUri))
    {
      arrayOfString = zznC;
      i = arrayOfString.length;
    }
    for (int j = 0;; j++) {
      if (j < i)
      {
        String str = arrayOfString[j];
        if (paramUri.getPath().endsWith(str)) {
          bool = true;
        }
      }
      else
      {
        return bool;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */