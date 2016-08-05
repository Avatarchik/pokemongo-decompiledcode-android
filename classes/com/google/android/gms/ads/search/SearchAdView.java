package com.google.android.gms.ads.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.client.zzz;

public final class SearchAdView
  extends ViewGroup
{
  private final zzz zznT;
  
  public SearchAdView(Context paramContext)
  {
    super(paramContext);
    this.zznT = new zzz(this);
  }
  
  public SearchAdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.zznT = new zzz(this, paramAttributeSet, false);
  }
  
  public SearchAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.zznT = new zzz(this, paramAttributeSet, false);
  }
  
  public void destroy()
  {
    this.zznT.destroy();
  }
  
  public AdListener getAdListener()
  {
    return this.zznT.getAdListener();
  }
  
  public AdSize getAdSize()
  {
    return this.zznT.getAdSize();
  }
  
  public String getAdUnitId()
  {
    return this.zznT.getAdUnitId();
  }
  
  public void loadAd(SearchAdRequest paramSearchAdRequest)
  {
    this.zznT.zza(paramSearchAdRequest.zzaF());
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    View localView = getChildAt(0);
    if ((localView != null) && (localView.getVisibility() != 8))
    {
      int i = localView.getMeasuredWidth();
      int j = localView.getMeasuredHeight();
      int k = (paramInt3 - paramInt1 - i) / 2;
      int m = (paramInt4 - paramInt2 - j) / 2;
      localView.layout(k, m, i + k, j + m);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = 0;
    View localView = getChildAt(0);
    int j;
    if ((localView != null) && (localView.getVisibility() != 8))
    {
      measureChild(localView, paramInt1, paramInt2);
      j = localView.getMeasuredWidth();
      i = localView.getMeasuredHeight();
    }
    for (;;)
    {
      int k = Math.max(j, getSuggestedMinimumWidth());
      int m = Math.max(i, getSuggestedMinimumHeight());
      setMeasuredDimension(View.resolveSize(k, paramInt1), View.resolveSize(m, paramInt2));
      return;
      AdSize localAdSize = getAdSize();
      if (localAdSize != null)
      {
        Context localContext = getContext();
        j = localAdSize.getWidthInPixels(localContext);
        i = localAdSize.getHeightInPixels(localContext);
      }
      else
      {
        j = 0;
      }
    }
  }
  
  public void pause()
  {
    this.zznT.pause();
  }
  
  public void resume()
  {
    this.zznT.resume();
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    this.zznT.setAdListener(paramAdListener);
  }
  
  public void setAdSize(AdSize paramAdSize)
  {
    zzz localzzz = this.zznT;
    AdSize[] arrayOfAdSize = new AdSize[1];
    arrayOfAdSize[0] = paramAdSize;
    localzzz.setAdSizes(arrayOfAdSize);
  }
  
  public void setAdUnitId(String paramString)
  {
    this.zznT.setAdUnitId(paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/search/SearchAdView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */