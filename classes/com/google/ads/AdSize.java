package com.google.ads;

import android.content.Context;

@Deprecated
public final class AdSize
{
  public static final int AUTO_HEIGHT = -2;
  public static final AdSize BANNER;
  public static final int FULL_WIDTH = -1;
  public static final AdSize IAB_BANNER = new AdSize(468, 60, "as");
  public static final AdSize IAB_LEADERBOARD = new AdSize(728, 90, "as");
  public static final AdSize IAB_MRECT;
  public static final AdSize IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");
  public static final int LANDSCAPE_AD_HEIGHT = 32;
  public static final int LARGE_AD_HEIGHT = 90;
  public static final int PORTRAIT_AD_HEIGHT = 50;
  public static final AdSize SMART_BANNER = new AdSize(-1, -2, "mb");
  private final com.google.android.gms.ads.AdSize zzaJ;
  
  static
  {
    BANNER = new AdSize(320, 50, "mb");
    IAB_MRECT = new AdSize(300, 250, "as");
  }
  
  public AdSize(int paramInt1, int paramInt2)
  {
    this(new com.google.android.gms.ads.AdSize(paramInt1, paramInt2));
  }
  
  private AdSize(int paramInt1, int paramInt2, String paramString)
  {
    this(new com.google.android.gms.ads.AdSize(paramInt1, paramInt2));
  }
  
  public AdSize(com.google.android.gms.ads.AdSize paramAdSize)
  {
    this.zzaJ = paramAdSize;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof AdSize)) {}
    AdSize localAdSize;
    for (boolean bool = false;; bool = this.zzaJ.equals(localAdSize.zzaJ))
    {
      return bool;
      localAdSize = (AdSize)paramObject;
    }
  }
  
  public AdSize findBestSize(AdSize... paramVarArgs)
  {
    Object localObject1 = null;
    if (paramVarArgs == null) {
      return (AdSize)localObject1;
    }
    float f1 = 0.0F;
    int i = getWidth();
    int j = getHeight();
    int k = paramVarArgs.length;
    int m = 0;
    label29:
    AdSize localAdSize;
    float f2;
    if (m < k)
    {
      localAdSize = paramVarArgs[m];
      int n = localAdSize.getWidth();
      int i1 = localAdSize.getHeight();
      if (!isSizeAppropriate(n, i1)) {
        break label118;
      }
      f2 = n * i1 / (i * j);
      if (f2 > 1.0F) {
        f2 = 1.0F / f2;
      }
      if (f2 <= f1) {
        break label118;
      }
    }
    for (Object localObject2 = localAdSize;; localObject2 = localObject1)
    {
      m++;
      localObject1 = localObject2;
      f1 = f2;
      break label29;
      break;
      label118:
      f2 = f1;
    }
  }
  
  public int getHeight()
  {
    return this.zzaJ.getHeight();
  }
  
  public int getHeightInPixels(Context paramContext)
  {
    return this.zzaJ.getHeightInPixels(paramContext);
  }
  
  public int getWidth()
  {
    return this.zzaJ.getWidth();
  }
  
  public int getWidthInPixels(Context paramContext)
  {
    return this.zzaJ.getWidthInPixels(paramContext);
  }
  
  public int hashCode()
  {
    return this.zzaJ.hashCode();
  }
  
  public boolean isAutoHeight()
  {
    return this.zzaJ.isAutoHeight();
  }
  
  public boolean isCustomAdSize()
  {
    return false;
  }
  
  public boolean isFullWidth()
  {
    return this.zzaJ.isFullWidth();
  }
  
  public boolean isSizeAppropriate(int paramInt1, int paramInt2)
  {
    int i = getWidth();
    int j = getHeight();
    if ((paramInt1 <= 1.25F * i) && (paramInt1 >= 0.8F * i) && (paramInt2 <= 1.25F * j) && (paramInt2 >= 0.8F * j)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String toString()
  {
    return this.zzaJ.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/ads/AdSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */