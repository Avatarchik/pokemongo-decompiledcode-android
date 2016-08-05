package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzgr
public class zzew
  extends zzer.zza
{
  private final NativeContentAdMapper zzzO;
  
  public zzew(NativeContentAdMapper paramNativeContentAdMapper)
  {
    this.zzzO = paramNativeContentAdMapper;
  }
  
  public String getAdvertiser()
  {
    return this.zzzO.getAdvertiser();
  }
  
  public String getBody()
  {
    return this.zzzO.getBody();
  }
  
  public String getCallToAction()
  {
    return this.zzzO.getCallToAction();
  }
  
  public Bundle getExtras()
  {
    return this.zzzO.getExtras();
  }
  
  public String getHeadline()
  {
    return this.zzzO.getHeadline();
  }
  
  public List getImages()
  {
    List localList = this.zzzO.getImages();
    ArrayList localArrayList1;
    if (localList != null)
    {
      localArrayList1 = new ArrayList();
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        NativeAd.Image localImage = (NativeAd.Image)localIterator.next();
        localArrayList1.add(new zzc(localImage.getDrawable(), localImage.getUri(), localImage.getScale()));
      }
    }
    for (ArrayList localArrayList2 = localArrayList1;; localArrayList2 = null) {
      return localArrayList2;
    }
  }
  
  public boolean getOverrideClickHandling()
  {
    return this.zzzO.getOverrideClickHandling();
  }
  
  public boolean getOverrideImpressionRecording()
  {
    return this.zzzO.getOverrideImpressionRecording();
  }
  
  public void recordImpression()
  {
    this.zzzO.recordImpression();
  }
  
  public void zzc(zzd paramzzd)
  {
    this.zzzO.handleClick((View)zze.zzp(paramzzd));
  }
  
  public void zzd(zzd paramzzd)
  {
    this.zzzO.trackView((View)zze.zzp(paramzzd));
  }
  
  public zzcm zzdA()
  {
    NativeAd.Image localImage = this.zzzO.getLogo();
    if (localImage != null) {}
    for (zzc localzzc = new zzc(localImage.getDrawable(), localImage.getUri(), localImage.getScale());; localzzc = null) {
      return localzzc;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzew.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */