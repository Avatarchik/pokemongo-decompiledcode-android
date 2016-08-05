package com.google.android.gms.ads.internal.formats;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import java.util.List;

public class zza
{
  private static final int zzwa = Color.rgb(12, 174, 206);
  private static final int zzwb = Color.rgb(204, 204, 204);
  static final int zzwc = zzwb;
  static final int zzwd = zzwa;
  private final int mTextColor;
  private final String zzwe;
  private final List<Drawable> zzwf;
  private final int zzwg;
  private final int zzwh;
  private final int zzwi;
  
  public zza(String paramString, List<Drawable> paramList, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, int paramInt)
  {
    this.zzwe = paramString;
    this.zzwf = paramList;
    int i;
    int j;
    if (paramInteger1 != null)
    {
      i = paramInteger1.intValue();
      this.zzwg = i;
      if (paramInteger2 == null) {
        break label81;
      }
      j = paramInteger2.intValue();
      label42:
      this.mTextColor = j;
      if (paramInteger3 == null) {
        break label89;
      }
    }
    label81:
    label89:
    for (int k = paramInteger3.intValue();; k = 12)
    {
      this.zzwh = k;
      this.zzwi = paramInt;
      return;
      i = zzwc;
      break;
      j = zzwd;
      break label42;
    }
  }
  
  public int getBackgroundColor()
  {
    return this.zzwg;
  }
  
  public String getText()
  {
    return this.zzwe;
  }
  
  public int getTextColor()
  {
    return this.mTextColor;
  }
  
  public int getTextSize()
  {
    return this.zzwh;
  }
  
  public List<Drawable> zzds()
  {
    return this.zzwf;
  }
  
  public int zzdt()
  {
    return this.zzwi;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/formats/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */