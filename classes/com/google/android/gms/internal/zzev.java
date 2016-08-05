package com.google.android.gms.internal;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@zzgr
public class zzev
  extends zzeq.zza
{
  private final NativeAppInstallAdMapper zzzN;
  
  public zzev(NativeAppInstallAdMapper paramNativeAppInstallAdMapper)
  {
    this.zzzN = paramNativeAppInstallAdMapper;
  }
  
  public String getBody()
  {
    return this.zzzN.getBody();
  }
  
  public String getCallToAction()
  {
    return this.zzzN.getCallToAction();
  }
  
  public Bundle getExtras()
  {
    return this.zzzN.getExtras();
  }
  
  public String getHeadline()
  {
    return this.zzzN.getHeadline();
  }
  
  public List getImages()
  {
    List localList = this.zzzN.getImages();
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
    return this.zzzN.getOverrideClickHandling();
  }
  
  public boolean getOverrideImpressionRecording()
  {
    return this.zzzN.getOverrideImpressionRecording();
  }
  
  public String getPrice()
  {
    return this.zzzN.getPrice();
  }
  
  public double getStarRating()
  {
    return this.zzzN.getStarRating();
  }
  
  public String getStore()
  {
    return this.zzzN.getStore();
  }
  
  public void recordImpression()
  {
    this.zzzN.recordImpression();
  }
  
  public void zzc(zzd paramzzd)
  {
    this.zzzN.handleClick((View)zze.zzp(paramzzd));
  }
  
  public void zzd(zzd paramzzd)
  {
    this.zzzN.trackView((View)zze.zzp(paramzzd));
  }
  
  public zzcm zzdw()
  {
    NativeAd.Image localImage = this.zzzN.getIcon();
    if (localImage != null) {}
    for (zzc localzzc = new zzc(localImage.getDrawable(), localImage.getUri(), localImage.getScale());; localzzc = null) {
      return localzzc;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzev.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */