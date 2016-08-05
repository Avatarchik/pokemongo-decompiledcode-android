package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzry<M extends zzry<M>>
  extends zzse
{
  protected zzsa zzbik;
  
  protected int zzB()
  {
    int i = 0;
    if (this.zzbik != null)
    {
      j = 0;
      while (i < this.zzbik.size())
      {
        j += this.zzbik.zzlS(i).zzB();
        i++;
      }
    }
    int j = 0;
    return j;
  }
  
  public M zzFF()
    throws CloneNotSupportedException
  {
    zzry localzzry = (zzry)super.zzFG();
    zzsc.zza(this, localzzry);
    return localzzry;
  }
  
  public final <T> T zza(zzrz<M, T> paramzzrz)
  {
    Object localObject = null;
    if (this.zzbik == null) {}
    for (;;)
    {
      return (T)localObject;
      zzsb localzzsb = this.zzbik.zzlR(zzsh.zzlV(paramzzrz.tag));
      if (localzzsb != null) {
        localObject = localzzsb.zzb(paramzzrz);
      }
    }
  }
  
  public void zza(zzrx paramzzrx)
    throws IOException
  {
    if (this.zzbik == null) {}
    for (;;)
    {
      return;
      for (int i = 0; i < this.zzbik.size(); i++) {
        this.zzbik.zzlS(i).zza(paramzzrx);
      }
    }
  }
  
  protected final boolean zza(zzrw paramzzrw, int paramInt)
    throws IOException
  {
    int i = paramzzrw.getPosition();
    boolean bool;
    if (!paramzzrw.zzlA(paramInt))
    {
      bool = false;
      return bool;
    }
    int j = zzsh.zzlV(paramInt);
    zzsg localzzsg = new zzsg(paramInt, paramzzrw.zzx(i, paramzzrw.getPosition() - i));
    zzsb localzzsb = null;
    if (this.zzbik == null) {
      this.zzbik = new zzsa();
    }
    for (;;)
    {
      if (localzzsb == null)
      {
        localzzsb = new zzsb();
        this.zzbik.zza(j, localzzsb);
      }
      localzzsb.zza(localzzsg);
      bool = true;
      break;
      localzzsb = this.zzbik.zzlR(j);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */