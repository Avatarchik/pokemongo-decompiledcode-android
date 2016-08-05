package com.google.android.gms.internal;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public final class zzlt
{
  public static Bitmap zza(Bitmap paramBitmap)
  {
    int i = 0;
    Object localObject;
    if (paramBitmap == null)
    {
      localObject = null;
      return (Bitmap)localObject;
    }
    int j = paramBitmap.getWidth();
    int k = paramBitmap.getHeight();
    int m;
    if (j >= k)
    {
      i = (k - j) / 2;
      m = 0;
    }
    for (;;)
    {
      Bitmap localBitmap = Bitmap.createBitmap(k, k, Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint(1);
      localPaint.setColor(-16777216);
      localCanvas.drawCircle(k / 2, k / 2, k / 2, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(paramBitmap, i, m, localPaint);
      localObject = localBitmap;
      break;
      m = (j - k) / 2;
      k = j;
    }
  }
  
  private static Bitmap zza(Drawable paramDrawable)
  {
    Bitmap localBitmap;
    if (paramDrawable == null) {
      localBitmap = null;
    }
    for (;;)
    {
      return localBitmap;
      if ((paramDrawable instanceof BitmapDrawable))
      {
        localBitmap = ((BitmapDrawable)paramDrawable).getBitmap();
      }
      else
      {
        localBitmap = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(localBitmap);
        paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
        paramDrawable.draw(localCanvas);
      }
    }
  }
  
  public static Drawable zza(Resources paramResources, Drawable paramDrawable)
  {
    return new BitmapDrawable(paramResources, zza(zza(paramDrawable)));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzlt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */