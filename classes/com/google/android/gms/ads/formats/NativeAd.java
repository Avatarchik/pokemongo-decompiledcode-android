package com.google.android.gms.ads.formats;

import android.graphics.drawable.Drawable;
import android.net.Uri;

public abstract class NativeAd
{
  protected abstract Object zzaH();
  
  public static abstract class Image
  {
    public abstract Drawable getDrawable();
    
    public abstract double getScale();
    
    public abstract Uri getUri();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/formats/NativeAd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */