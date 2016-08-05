package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import com.google.android.gms.common.internal.zzw;

public final class zzlv
  extends zzmg<zza, Drawable>
{
  public zzlv()
  {
    super(10);
  }
  
  public static final class zza
  {
    public final int zzaeE;
    public final int zzaeF;
    
    public zza(int paramInt1, int paramInt2)
    {
      this.zzaeE = paramInt1;
      this.zzaeF = paramInt2;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (!(paramObject instanceof zza)) {
        bool = false;
      }
      for (;;)
      {
        return bool;
        if (this != paramObject)
        {
          zza localzza = (zza)paramObject;
          if ((localzza.zzaeE != this.zzaeE) || (localzza.zzaeF != this.zzaeF)) {
            bool = false;
          }
        }
      }
    }
    
    public int hashCode()
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(this.zzaeE);
      arrayOfObject[1] = Integer.valueOf(this.zzaeF);
      return zzw.hashCode(arrayOfObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzlv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */