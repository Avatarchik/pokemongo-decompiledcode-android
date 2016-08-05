package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.internal.zzgr;

@zzgr
public final class zzk
{
  private final String zzpa;
  private final AdSize[] zztk;
  
  public zzk(Context paramContext, AttributeSet paramAttributeSet)
  {
    TypedArray localTypedArray = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.AdsAttrs);
    String str1 = localTypedArray.getString(R.styleable.AdsAttrs_adSize);
    String str2 = localTypedArray.getString(R.styleable.AdsAttrs_adSizes);
    int j;
    if (!TextUtils.isEmpty(str1))
    {
      j = i;
      if (TextUtils.isEmpty(str2)) {
        break label114;
      }
      label58:
      if ((j == 0) || (i != 0)) {
        break label119;
      }
    }
    for (this.zztk = zzE(str1);; this.zztk = zzE(str2))
    {
      this.zzpa = localTypedArray.getString(R.styleable.AdsAttrs_adUnitId);
      if (!TextUtils.isEmpty(this.zzpa)) {
        return;
      }
      throw new IllegalArgumentException("Required XML attribute \"adUnitId\" was missing.");
      j = 0;
      break;
      label114:
      i = 0;
      break label58;
      label119:
      if ((j != 0) || (i == 0)) {
        break label140;
      }
    }
    label140:
    if ((j != 0) && (i != 0)) {
      throw new IllegalArgumentException("Either XML attribute \"adSize\" or XML attribute \"supportedAdSizes\" should be specified, but not both.");
    }
    throw new IllegalArgumentException("Required XML attribute \"adSize\" was missing.");
  }
  
  private static AdSize[] zzE(String paramString)
  {
    String[] arrayOfString1 = paramString.split("\\s*,\\s*");
    AdSize[] arrayOfAdSize = new AdSize[arrayOfString1.length];
    int i = 0;
    if (i < arrayOfString1.length)
    {
      String str = arrayOfString1[i].trim();
      String[] arrayOfString2;
      if (str.matches("^(\\d+|FULL_WIDTH)\\s*[xX]\\s*(\\d+|AUTO_HEIGHT)$"))
      {
        arrayOfString2 = str.split("[xX]");
        arrayOfString2[0] = arrayOfString2[0].trim();
        arrayOfString2[1] = arrayOfString2[1].trim();
      }
      for (;;)
      {
        try
        {
          if ("FULL_WIDTH".equals(arrayOfString2[0]))
          {
            j = -1;
            boolean bool = "AUTO_HEIGHT".equals(arrayOfString2[1]);
            if (!bool) {
              continue;
            }
            m = -2;
            arrayOfAdSize[i] = new AdSize(j, m);
            i++;
            break;
          }
          int j = Integer.parseInt(arrayOfString2[0]);
          continue;
          int k = Integer.parseInt(arrayOfString2[1]);
          int m = k;
          continue;
          if (!"BANNER".equals(str)) {
            break label203;
          }
        }
        catch (NumberFormatException localNumberFormatException)
        {
          throw new IllegalArgumentException("Could not parse XML attribute \"adSize\": " + str);
        }
        arrayOfAdSize[i] = AdSize.BANNER;
        continue;
        label203:
        if ("LARGE_BANNER".equals(str))
        {
          arrayOfAdSize[i] = AdSize.LARGE_BANNER;
        }
        else if ("FULL_BANNER".equals(str))
        {
          arrayOfAdSize[i] = AdSize.FULL_BANNER;
        }
        else if ("LEADERBOARD".equals(str))
        {
          arrayOfAdSize[i] = AdSize.LEADERBOARD;
        }
        else if ("MEDIUM_RECTANGLE".equals(str))
        {
          arrayOfAdSize[i] = AdSize.MEDIUM_RECTANGLE;
        }
        else if ("SMART_BANNER".equals(str))
        {
          arrayOfAdSize[i] = AdSize.SMART_BANNER;
        }
        else if ("WIDE_SKYSCRAPER".equals(str))
        {
          arrayOfAdSize[i] = AdSize.WIDE_SKYSCRAPER;
        }
        else
        {
          if (!"FLUID".equals(str)) {
            break label336;
          }
          arrayOfAdSize[i] = AdSize.FLUID;
        }
      }
      label336:
      throw new IllegalArgumentException("Could not parse XML attribute \"adSize\": " + str);
    }
    if (arrayOfAdSize.length == 0) {
      throw new IllegalArgumentException("Could not parse XML attribute \"adSize\": " + paramString);
    }
    return arrayOfAdSize;
  }
  
  public String getAdUnitId()
  {
    return this.zzpa;
  }
  
  public AdSize[] zzi(boolean paramBoolean)
  {
    if ((!paramBoolean) && (this.zztk.length != 1)) {
      throw new IllegalArgumentException("The adSizes XML attribute is only allowed on PublisherAdViews.");
    }
    return this.zztk;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */