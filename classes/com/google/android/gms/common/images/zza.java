package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.internal.zzls;
import com.google.android.gms.internal.zzlt;
import com.google.android.gms.internal.zzlu;
import com.google.android.gms.internal.zzlv;
import com.google.android.gms.internal.zzlv.zza;
import java.lang.ref.WeakReference;

public abstract class zza
{
  final zza zzadV;
  protected int zzadW = 0;
  protected int zzadX = 0;
  protected boolean zzadY = false;
  protected ImageManager.OnImageLoadedListener zzadZ;
  private boolean zzaea = true;
  private boolean zzaeb = false;
  private boolean zzaec = true;
  protected int zzaed;
  
  public zza(Uri paramUri, int paramInt)
  {
    this.zzadV = new zza(paramUri);
    this.zzadX = paramInt;
  }
  
  private Drawable zza(Context paramContext, zzlv paramzzlv, int paramInt)
  {
    Resources localResources = paramContext.getResources();
    Drawable localDrawable;
    if (this.zzaed > 0)
    {
      zzlv.zza localzza = new zzlv.zza(paramInt, this.zzaed);
      localDrawable = (Drawable)paramzzlv.get(localzza);
      if (localDrawable == null)
      {
        localDrawable = localResources.getDrawable(paramInt);
        if ((0x1 & this.zzaed) != 0) {
          localDrawable = zza(localResources, localDrawable);
        }
        paramzzlv.put(localzza, localDrawable);
      }
    }
    for (;;)
    {
      return localDrawable;
      localDrawable = localResources.getDrawable(paramInt);
    }
  }
  
  protected Drawable zza(Resources paramResources, Drawable paramDrawable)
  {
    return zzlt.zza(paramResources, paramDrawable);
  }
  
  protected zzls zza(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    if (paramDrawable1 != null) {
      if (!(paramDrawable1 instanceof zzls)) {}
    }
    for (paramDrawable1 = ((zzls)paramDrawable1).zzoF();; paramDrawable1 = null) {
      return new zzls(paramDrawable1, paramDrawable2);
    }
  }
  
  void zza(Context paramContext, Bitmap paramBitmap, boolean paramBoolean)
  {
    zzb.zzs(paramBitmap);
    if ((0x1 & this.zzaed) != 0) {
      paramBitmap = zzlt.zza(paramBitmap);
    }
    BitmapDrawable localBitmapDrawable = new BitmapDrawable(paramContext.getResources(), paramBitmap);
    if (this.zzadZ != null) {
      this.zzadZ.onImageLoaded(this.zzadV.uri, localBitmapDrawable, true);
    }
    zza(localBitmapDrawable, paramBoolean, false, true);
  }
  
  void zza(Context paramContext, zzlv paramzzlv)
  {
    if (this.zzaec)
    {
      Drawable localDrawable = null;
      if (this.zzadW != 0) {
        localDrawable = zza(paramContext, paramzzlv, this.zzadW);
      }
      zza(localDrawable, false, true, false);
    }
  }
  
  void zza(Context paramContext, zzlv paramzzlv, boolean paramBoolean)
  {
    Drawable localDrawable = null;
    if (this.zzadX != 0) {
      localDrawable = zza(paramContext, paramzzlv, this.zzadX);
    }
    if (this.zzadZ != null) {
      this.zzadZ.onImageLoaded(this.zzadV.uri, localDrawable, false);
    }
    zza(localDrawable, paramBoolean, false, false);
  }
  
  protected abstract void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
  
  protected boolean zzb(boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((this.zzaea) && (!paramBoolean2) && ((!paramBoolean1) || (this.zzaeb))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void zzby(int paramInt)
  {
    this.zzadX = paramInt;
  }
  
  static final class zza
  {
    public final Uri uri;
    
    public zza(Uri paramUri)
    {
      this.uri = paramUri;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool;
      if (!(paramObject instanceof zza)) {
        bool = false;
      }
      for (;;)
      {
        return bool;
        if (this == paramObject) {
          bool = true;
        } else {
          bool = zzw.equal(((zza)paramObject).uri, this.uri);
        }
      }
    }
    
    public int hashCode()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.uri;
      return zzw.hashCode(arrayOfObject);
    }
  }
  
  public static final class zzc
    extends zza
  {
    private WeakReference<ImageManager.OnImageLoadedListener> zzaef;
    
    public zzc(ImageManager.OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
    {
      super(0);
      zzb.zzs(paramOnImageLoadedListener);
      this.zzaef = new WeakReference(paramOnImageLoadedListener);
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool1 = false;
      if (!(paramObject instanceof zzc)) {}
      for (;;)
      {
        return bool1;
        if (this != paramObject) {
          break;
        }
        bool1 = true;
      }
      zzc localzzc = (zzc)paramObject;
      ImageManager.OnImageLoadedListener localOnImageLoadedListener1 = (ImageManager.OnImageLoadedListener)this.zzaef.get();
      ImageManager.OnImageLoadedListener localOnImageLoadedListener2 = (ImageManager.OnImageLoadedListener)localzzc.zzaef.get();
      if ((localOnImageLoadedListener2 != null) && (localOnImageLoadedListener1 != null) && (zzw.equal(localOnImageLoadedListener2, localOnImageLoadedListener1)) && (zzw.equal(localzzc.zzadV, this.zzadV))) {}
      for (boolean bool2 = true;; bool2 = false)
      {
        bool1 = bool2;
        break;
      }
    }
    
    public int hashCode()
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.zzadV;
      return zzw.hashCode(arrayOfObject);
    }
    
    protected void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      if (!paramBoolean2)
      {
        ImageManager.OnImageLoadedListener localOnImageLoadedListener = (ImageManager.OnImageLoadedListener)this.zzaef.get();
        if (localOnImageLoadedListener != null) {
          localOnImageLoadedListener.onImageLoaded(this.zzadV.uri, paramDrawable, paramBoolean3);
        }
      }
    }
  }
  
  public static final class zzb
    extends zza
  {
    private WeakReference<ImageView> zzaee;
    
    public zzb(ImageView paramImageView, int paramInt)
    {
      super(paramInt);
      zzb.zzs(paramImageView);
      this.zzaee = new WeakReference(paramImageView);
    }
    
    public zzb(ImageView paramImageView, Uri paramUri)
    {
      super(0);
      zzb.zzs(paramImageView);
      this.zzaee = new WeakReference(paramImageView);
    }
    
    private void zza(ImageView paramImageView, Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      if ((!paramBoolean2) && (!paramBoolean3)) {}
      for (int i = 1; (i != 0) && ((paramImageView instanceof zzlu)); i = 0)
      {
        int k = ((zzlu)paramImageView).zzoH();
        if ((this.zzadX == 0) || (k != this.zzadX)) {
          break;
        }
        return;
      }
      boolean bool = zzb(paramBoolean1, paramBoolean2);
      if ((this.zzadY) && (paramDrawable != null)) {}
      for (Object localObject = paramDrawable.getConstantState().newDrawable();; localObject = paramDrawable)
      {
        if (bool) {
          localObject = zza(paramImageView.getDrawable(), (Drawable)localObject);
        }
        paramImageView.setImageDrawable((Drawable)localObject);
        zzlu localzzlu;
        Uri localUri;
        if ((paramImageView instanceof zzlu))
        {
          localzzlu = (zzlu)paramImageView;
          if (!paramBoolean3) {
            break label180;
          }
          localUri = this.zzadV.uri;
          label136:
          localzzlu.zzj(localUri);
          if (i == 0) {
            break label186;
          }
        }
        label180:
        label186:
        for (int j = this.zzadX;; j = 0)
        {
          localzzlu.zzbA(j);
          if (!bool) {
            break;
          }
          ((zzls)localObject).startTransition(250);
          break;
          localUri = null;
          break label136;
        }
      }
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool1 = false;
      if (!(paramObject instanceof zzb)) {}
      for (;;)
      {
        return bool1;
        if (this != paramObject) {
          break;
        }
        bool1 = true;
      }
      zzb localzzb = (zzb)paramObject;
      ImageView localImageView1 = (ImageView)this.zzaee.get();
      ImageView localImageView2 = (ImageView)localzzb.zzaee.get();
      if ((localImageView2 != null) && (localImageView1 != null) && (zzw.equal(localImageView2, localImageView1))) {}
      for (boolean bool2 = true;; bool2 = false)
      {
        bool1 = bool2;
        break;
      }
    }
    
    public int hashCode()
    {
      return 0;
    }
    
    protected void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
    {
      ImageView localImageView = (ImageView)this.zzaee.get();
      if (localImageView != null) {
        zza(localImageView, paramDrawable, paramBoolean1, paramBoolean2, paramBoolean3);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/images/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */