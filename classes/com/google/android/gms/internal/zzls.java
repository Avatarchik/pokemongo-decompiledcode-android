package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;

public final class zzls
  extends Drawable
  implements Drawable.Callback
{
  private int mFrom;
  private long zzNY;
  private boolean zzaea = true;
  private int zzaeh = 0;
  private int zzaei;
  private int zzaej = 255;
  private int zzaek;
  private int zzael = 0;
  private boolean zzaem;
  private zzb zzaen;
  private Drawable zzaeo;
  private Drawable zzaep;
  private boolean zzaeq;
  private boolean zzaer;
  private boolean zzaes;
  private int zzaet;
  
  public zzls(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    this(null);
    if (paramDrawable1 == null) {
      paramDrawable1 = zza.zzoG();
    }
    this.zzaeo = paramDrawable1;
    paramDrawable1.setCallback(this);
    zzb localzzb1 = this.zzaen;
    localzzb1.zzaex |= paramDrawable1.getChangingConfigurations();
    if (paramDrawable2 == null) {
      paramDrawable2 = zza.zzoG();
    }
    this.zzaep = paramDrawable2;
    paramDrawable2.setCallback(this);
    zzb localzzb2 = this.zzaen;
    localzzb2.zzaex |= paramDrawable2.getChangingConfigurations();
  }
  
  zzls(zzb paramzzb)
  {
    this.zzaen = new zzb(paramzzb);
  }
  
  public boolean canConstantState()
  {
    if (!this.zzaeq) {
      if ((this.zzaeo.getConstantState() == null) || (this.zzaep.getConstantState() == null)) {
        break label44;
      }
    }
    label44:
    for (boolean bool = true;; bool = false)
    {
      this.zzaer = bool;
      this.zzaeq = true;
      return this.zzaer;
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    int i = 1;
    int j = 0;
    label34:
    int k;
    boolean bool;
    Drawable localDrawable1;
    Drawable localDrawable2;
    switch (this.zzaeh)
    {
    default: 
      j = i;
      k = this.zzael;
      bool = this.zzaea;
      localDrawable1 = this.zzaeo;
      localDrawable2 = this.zzaep;
      if (j != 0)
      {
        if ((!bool) || (k == 0)) {
          localDrawable1.draw(paramCanvas);
        }
        if (k == this.zzaej)
        {
          localDrawable2.setAlpha(this.zzaej);
          localDrawable2.draw(paramCanvas);
        }
      }
      break;
    }
    for (;;)
    {
      return;
      this.zzNY = SystemClock.uptimeMillis();
      this.zzaeh = 2;
      break label34;
      if (this.zzNY < 0L) {
        break;
      }
      float f1 = (float)(SystemClock.uptimeMillis() - this.zzNY) / this.zzaek;
      if (f1 >= 1.0F) {}
      for (;;)
      {
        if (i != 0) {
          this.zzaeh = 0;
        }
        float f2 = Math.min(f1, 1.0F);
        this.zzael = ((int)(this.mFrom + f2 * (this.zzaei - this.mFrom)));
        break;
        i = 0;
      }
      if (bool) {
        localDrawable1.setAlpha(this.zzaej - k);
      }
      localDrawable1.draw(paramCanvas);
      if (bool) {
        localDrawable1.setAlpha(this.zzaej);
      }
      if (k > 0)
      {
        localDrawable2.setAlpha(k);
        localDrawable2.draw(paramCanvas);
        localDrawable2.setAlpha(this.zzaej);
      }
      invalidateSelf();
    }
  }
  
  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | this.zzaen.zzaew | this.zzaen.zzaex;
  }
  
  public Drawable.ConstantState getConstantState()
  {
    if (canConstantState()) {
      this.zzaen.zzaew = getChangingConfigurations();
    }
    for (zzb localzzb = this.zzaen;; localzzb = null) {
      return localzzb;
    }
  }
  
  public int getIntrinsicHeight()
  {
    return Math.max(this.zzaeo.getIntrinsicHeight(), this.zzaep.getIntrinsicHeight());
  }
  
  public int getIntrinsicWidth()
  {
    return Math.max(this.zzaeo.getIntrinsicWidth(), this.zzaep.getIntrinsicWidth());
  }
  
  public int getOpacity()
  {
    if (!this.zzaes)
    {
      this.zzaet = Drawable.resolveOpacity(this.zzaeo.getOpacity(), this.zzaep.getOpacity());
      this.zzaes = true;
    }
    return this.zzaet;
  }
  
  public void invalidateDrawable(Drawable paramDrawable)
  {
    if (zzmx.zzqu())
    {
      Drawable.Callback localCallback = getCallback();
      if (localCallback != null) {
        localCallback.invalidateDrawable(this);
      }
    }
  }
  
  public Drawable mutate()
  {
    if ((!this.zzaem) && (super.mutate() == this))
    {
      if (!canConstantState()) {
        throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
      }
      this.zzaeo.mutate();
      this.zzaep.mutate();
      this.zzaem = true;
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    this.zzaeo.setBounds(paramRect);
    this.zzaep.setBounds(paramRect);
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    if (zzmx.zzqu())
    {
      Drawable.Callback localCallback = getCallback();
      if (localCallback != null) {
        localCallback.scheduleDrawable(this, paramRunnable, paramLong);
      }
    }
  }
  
  public void setAlpha(int paramInt)
  {
    if (this.zzael == this.zzaej) {
      this.zzael = paramInt;
    }
    this.zzaej = paramInt;
    invalidateSelf();
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.zzaeo.setColorFilter(paramColorFilter);
    this.zzaep.setColorFilter(paramColorFilter);
  }
  
  public void startTransition(int paramInt)
  {
    this.mFrom = 0;
    this.zzaei = this.zzaej;
    this.zzael = 0;
    this.zzaek = paramInt;
    this.zzaeh = 1;
    invalidateSelf();
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    if (zzmx.zzqu())
    {
      Drawable.Callback localCallback = getCallback();
      if (localCallback != null) {
        localCallback.unscheduleDrawable(this, paramRunnable);
      }
    }
  }
  
  public Drawable zzoF()
  {
    return this.zzaep;
  }
  
  private static final class zza
    extends Drawable
  {
    private static final zza zzaeu = new zza();
    private static final zza zzaev = new zza(null);
    
    public void draw(Canvas paramCanvas) {}
    
    public Drawable.ConstantState getConstantState()
    {
      return zzaev;
    }
    
    public int getOpacity()
    {
      return -2;
    }
    
    public void setAlpha(int paramInt) {}
    
    public void setColorFilter(ColorFilter paramColorFilter) {}
    
    private static final class zza
      extends Drawable.ConstantState
    {
      public int getChangingConfigurations()
      {
        return 0;
      }
      
      public Drawable newDrawable()
      {
        return zzls.zza.zzoG();
      }
    }
  }
  
  static final class zzb
    extends Drawable.ConstantState
  {
    int zzaew;
    int zzaex;
    
    zzb(zzb paramzzb)
    {
      if (paramzzb != null)
      {
        this.zzaew = paramzzb.zzaew;
        this.zzaex = paramzzb.zzaex;
      }
    }
    
    public int getChangingConfigurations()
    {
      return this.zzaew;
    }
    
    public Drawable newDrawable()
    {
      return new zzls(this);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */