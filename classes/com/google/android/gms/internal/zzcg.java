package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzp;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class zzcg
{
  private final Object zzpd = new Object();
  boolean zzvA;
  private final List<zzce> zzvR = new LinkedList();
  private final Map<String, String> zzvS = new LinkedHashMap();
  private String zzvT;
  private zzce zzvU;
  private zzcg zzvV;
  
  public zzcg(boolean paramBoolean, String paramString1, String paramString2)
  {
    this.zzvA = paramBoolean;
    this.zzvS.put("action", paramString1);
    this.zzvS.put("ad_format", paramString2);
  }
  
  public void zzT(String paramString)
  {
    if (!this.zzvA) {}
    for (;;)
    {
      return;
      synchronized (this.zzpd)
      {
        this.zzvT = paramString;
      }
    }
  }
  
  public boolean zza(zzce paramzzce, long paramLong, String... paramVarArgs)
  {
    synchronized (this.zzpd)
    {
      int i = paramVarArgs.length;
      for (int j = 0; j < i; j++)
      {
        zzce localzzce = new zzce(paramLong, paramVarArgs[j], paramzzce);
        this.zzvR.add(localzzce);
      }
      return true;
    }
  }
  
  public boolean zza(zzce paramzzce, String... paramVarArgs)
  {
    if ((!this.zzvA) || (paramzzce == null)) {}
    for (boolean bool = false;; bool = zza(paramzzce, zzp.zzbz().elapsedRealtime(), paramVarArgs)) {
      return bool;
    }
  }
  
  public zzce zzb(long paramLong)
  {
    zzce localzzce = null;
    if (!this.zzvA) {}
    for (;;)
    {
      return localzzce;
      localzzce = new zzce(paramLong, null, null);
    }
  }
  
  public void zzc(zzcg paramzzcg)
  {
    synchronized (this.zzpd)
    {
      this.zzvV = paramzzcg;
      return;
    }
  }
  
  public zzce zzdn()
  {
    return zzb(zzp.zzbz().elapsedRealtime());
  }
  
  public void zzdo()
  {
    synchronized (this.zzpd)
    {
      this.zzvU = zzdn();
      return;
    }
  }
  
  public String zzdp()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str1;
    synchronized (this.zzpd)
    {
      Iterator localIterator = this.zzvR.iterator();
      long l1;
      String str2;
      zzce localzzce2;
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        zzce localzzce1 = (zzce)localIterator.next();
        l1 = localzzce1.getTime();
        str2 = localzzce1.zzdk();
        localzzce2 = localzzce1.zzdl();
      } while ((localzzce2 == null) || (l1 <= 0L));
      long l2 = l1 - localzzce2.getTime();
      localStringBuilder.append(str2).append('.').append(l2).append(',');
    }
  }
  
  public zzce zzdq()
  {
    synchronized (this.zzpd)
    {
      zzce localzzce = this.zzvU;
      return localzzce;
    }
  }
  
  public void zze(String paramString1, String paramString2)
  {
    if ((!this.zzvA) || (TextUtils.isEmpty(paramString2))) {}
    for (;;)
    {
      return;
      zzca localzzca = zzp.zzby().zzgo();
      if (localzzca == null) {
        continue;
      }
      synchronized (this.zzpd)
      {
        localzzca.zzR(paramString1).zza(this.zzvS, paramString1, paramString2);
      }
    }
  }
  
  Map<String, String> zzn()
  {
    Map localMap;
    synchronized (this.zzpd)
    {
      zzca localzzca = zzp.zzby().zzgo();
      if ((localzzca == null) || (this.zzvV == null)) {
        localMap = this.zzvS;
      } else {
        localMap = localzzca.zza(this.zzvS, this.zzvV.zzn());
      }
    }
    return localMap;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */