package com.google.android.gms.ads.internal.formats;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzcm;
import com.google.android.gms.internal.zzcu.zza;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzmi;
import java.util.Arrays;
import java.util.List;

@zzgr
public class zzf
  extends zzcu.zza
  implements zzh.zza
{
  private final Object zzpd = new Object();
  private final String zzwA;
  private final zzmi<String, zzc> zzwB;
  private final zzmi<String, String> zzwC;
  private final zza zzww;
  private zzh zzwx;
  
  public zzf(String paramString, zzmi<String, zzc> paramzzmi, zzmi<String, String> paramzzmi1, zza paramzza)
  {
    this.zzwA = paramString;
    this.zzwB = paramzzmi;
    this.zzwC = paramzzmi1;
    this.zzww = paramzza;
  }
  
  public List<String> getAvailableAssetNames()
  {
    int i = 0;
    String[] arrayOfString = new String[this.zzwB.size() + this.zzwC.size()];
    int j = 0;
    int k = 0;
    while (j < this.zzwB.size())
    {
      arrayOfString[k] = ((String)this.zzwB.keyAt(j));
      int m = j + 1;
      k++;
      j = m;
    }
    while (i < this.zzwC.size())
    {
      arrayOfString[k] = ((String)this.zzwC.keyAt(i));
      i++;
      k++;
    }
    return Arrays.asList(arrayOfString);
  }
  
  public String getCustomTemplateId()
  {
    return this.zzwA;
  }
  
  public void performClick(String paramString)
  {
    synchronized (this.zzpd)
    {
      if (this.zzwx == null) {
        zzb.e("Attempt to call performClick before ad initialized.");
      } else {
        this.zzwx.zza(paramString, null, null);
      }
    }
  }
  
  public void recordImpression()
  {
    synchronized (this.zzpd)
    {
      if (this.zzwx == null) {
        zzb.e("Attempt to perform recordImpression before ad initialized.");
      } else {
        this.zzwx.recordImpression();
      }
    }
  }
  
  public String zzU(String paramString)
  {
    return (String)this.zzwC.get(paramString);
  }
  
  public zzcm zzV(String paramString)
  {
    return (zzcm)this.zzwB.get(paramString);
  }
  
  public void zza(zzh paramzzh)
  {
    synchronized (this.zzpd)
    {
      this.zzwx = paramzzh;
      return;
    }
  }
  
  public String zzdy()
  {
    return "3";
  }
  
  public zza zzdz()
  {
    return this.zzww;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/formats/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */