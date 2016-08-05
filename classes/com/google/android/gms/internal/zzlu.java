package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.Path;
import android.net.Uri;
import android.widget.ImageView;

public final class zzlu
  extends ImageView
{
  private int zzaeA;
  private zza zzaeB;
  private int zzaeC;
  private float zzaeD;
  private Uri zzaey;
  private int zzaez;
  
  protected void onDraw(Canvas paramCanvas)
  {
    if (this.zzaeB != null) {
      paramCanvas.clipPath(this.zzaeB.zzk(getWidth(), getHeight()));
    }
    super.onDraw(paramCanvas);
    if (this.zzaeA != 0) {
      paramCanvas.drawColor(this.zzaeA);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int j;
    int i;
    switch (this.zzaeC)
    {
    default: 
      return;
    case 1: 
      j = getMeasuredHeight();
      i = (int)(j * this.zzaeD);
    }
    for (;;)
    {
      setMeasuredDimension(i, j);
      break;
      i = getMeasuredWidth();
      j = (int)(i / this.zzaeD);
    }
  }
  
  public void zzbA(int paramInt)
  {
    this.zzaez = paramInt;
  }
  
  public void zzj(Uri paramUri)
  {
    this.zzaey = paramUri;
  }
  
  public int zzoH()
  {
    return this.zzaez;
  }
  
  public static abstract interface zza
  {
    public abstract Path zzk(int paramInt1, int paramInt2);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzlu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */