package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.client.zzz;

public final class PublisherAdView
  extends ViewGroup
{
  private final zzz zznT;
  
  public PublisherAdView(Context paramContext)
  {
    super(paramContext);
    this.zznT = new zzz(this);
  }
  
  public PublisherAdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.zznT = new zzz(this, paramAttributeSet, true);
  }
  
  public PublisherAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.zznT = new zzz(this, paramAttributeSet, true);
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
  
  public AdSize[] getAdSizes()
  {
    return this.zznT.getAdSizes();
  }
  
  public String getAdUnitId()
  {
    return this.zznT.getAdUnitId();
  }
  
  public AppEventListener getAppEventListener()
  {
    return this.zznT.getAppEventListener();
  }
  
  public String getMediationAdapterClassName()
  {
    return this.zznT.getMediationAdapterClassName();
  }
  
  public OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener()
  {
    return this.zznT.getOnCustomRenderedAdLoadedListener();
  }
  
  public void loadAd(PublisherAdRequest paramPublisherAdRequest)
  {
    this.zznT.zza(paramPublisherAdRequest.zzaF());
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
  
  public void recordManualImpression()
  {
    this.zznT.recordManualImpression();
  }
  
  public void resume()
  {
    this.zznT.resume();
  }
  
  public void setAdListener(AdListener paramAdListener)
  {
    this.zznT.setAdListener(paramAdListener);
  }
  
  public void setAdSizes(AdSize... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length < 1)) {
      throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
    }
    this.zznT.zza(paramVarArgs);
  }
  
  public void setAdUnitId(String paramString)
  {
    this.zznT.setAdUnitId(paramString);
  }
  
  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    this.zznT.setAppEventListener(paramAppEventListener);
  }
  
  public void setManualImpressionsEnabled(boolean paramBoolean)
  {
    this.zznT.setManualImpressionsEnabled(paramBoolean);
  }
  
  public void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener paramOnCustomRenderedAdLoadedListener)
  {
    this.zznT.setOnCustomRenderedAdLoadedListener(paramOnCustomRenderedAdLoadedListener);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/doubleclick/PublisherAdView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */