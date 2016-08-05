package com.google.android.gms.internal;

import android.os.Handler;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.common.api.Releasable;
import java.util.HashMap;
import java.util.Map;

@zzgr
public abstract class zzdv
  implements Releasable
{
  protected zziz zzoM;
  
  public zzdv(zziz paramzziz)
  {
    this.zzoM = paramzziz;
  }
  
  private String zzad(String paramString)
  {
    String str = "internal";
    int i = -1;
    switch (paramString.hashCode())
    {
    default: 
      switch (i)
      {
      }
      break;
    }
    for (;;)
    {
      return str;
      if (!paramString.equals("error")) {
        break;
      }
      i = 0;
      break;
      if (!paramString.equals("playerFailed")) {
        break;
      }
      i = 1;
      break;
      if (!paramString.equals("inProgress")) {
        break;
      }
      i = 2;
      break;
      if (!paramString.equals("contentLengthMissing")) {
        break;
      }
      i = 3;
      break;
      if (!paramString.equals("noCacheDir")) {
        break;
      }
      i = 4;
      break;
      if (!paramString.equals("expireFailed")) {
        break;
      }
      i = 5;
      break;
      if (!paramString.equals("badUrl")) {
        break;
      }
      i = 6;
      break;
      if (!paramString.equals("downloadTimeout")) {
        break;
      }
      i = 7;
      break;
      if (!paramString.equals("sizeExceeded")) {
        break;
      }
      i = 8;
      break;
      if (!paramString.equals("externalAbort")) {
        break;
      }
      i = 9;
      break;
      str = "internal";
      continue;
      str = "io";
      continue;
      str = "network";
      continue;
      str = "policy";
    }
  }
  
  public abstract void abort();
  
  public void release() {}
  
  protected void zza(final String paramString1, final String paramString2, final int paramInt)
  {
    zza.zzJt.post(new Runnable()
    {
      public void run()
      {
        HashMap localHashMap = new HashMap();
        localHashMap.put("event", "precacheComplete");
        localHashMap.put("src", paramString1);
        localHashMap.put("cachedSrc", paramString2);
        localHashMap.put("totalBytes", Integer.toString(paramInt));
        zzdv.this.zzoM.zzb("onPrecacheEvent", localHashMap);
      }
    });
  }
  
  protected void zza(final String paramString1, final String paramString2, final int paramInt1, final int paramInt2, final boolean paramBoolean)
  {
    zza.zzJt.post(new Runnable()
    {
      public void run()
      {
        HashMap localHashMap = new HashMap();
        localHashMap.put("event", "precacheProgress");
        localHashMap.put("src", paramString1);
        localHashMap.put("cachedSrc", paramString2);
        localHashMap.put("bytesLoaded", Integer.toString(paramInt1));
        localHashMap.put("totalBytes", Integer.toString(paramInt2));
        if (paramBoolean) {}
        for (String str = "1";; str = "0")
        {
          localHashMap.put("cacheReady", str);
          zzdv.this.zzoM.zzb("onPrecacheEvent", localHashMap);
          return;
        }
      }
    });
  }
  
  protected void zza(final String paramString1, final String paramString2, final String paramString3, final String paramString4)
  {
    zza.zzJt.post(new Runnable()
    {
      public void run()
      {
        HashMap localHashMap = new HashMap();
        localHashMap.put("event", "precacheCanceled");
        localHashMap.put("src", paramString1);
        if (!TextUtils.isEmpty(paramString2)) {
          localHashMap.put("cachedSrc", paramString2);
        }
        localHashMap.put("type", zzdv.zza(zzdv.this, paramString3));
        localHashMap.put("reason", paramString3);
        if (!TextUtils.isEmpty(paramString4)) {
          localHashMap.put("message", paramString4);
        }
        zzdv.this.zzoM.zzb("onPrecacheEvent", localHashMap);
      }
    });
  }
  
  public abstract boolean zzab(String paramString);
  
  protected String zzac(String paramString)
  {
    return zzl.zzcF().zzaE(paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */