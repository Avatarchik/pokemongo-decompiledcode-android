package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzse
{
  protected volatile int zzbiv = -1;
  
  public static final <T extends zzse> T zza(T paramT, byte[] paramArrayOfByte)
    throws zzsd
  {
    return zzb(paramT, paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static final void zza(zzse paramzzse, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      zzrx localzzrx = zzrx.zzb(paramArrayOfByte, paramInt1, paramInt2);
      paramzzse.zza(localzzrx);
      localzzrx.zzFE();
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", localIOException);
    }
  }
  
  public static final <T extends zzse> T zzb(T paramT, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws zzsd
  {
    try
    {
      zzrw localzzrw = zzrw.zza(paramArrayOfByte, paramInt1, paramInt2);
      paramT.zzb(localzzrw);
      localzzrw.zzlz(0);
      return paramT;
    }
    catch (zzsd localzzsd)
    {
      throw localzzsd;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
    }
  }
  
  public static final byte[] zzf(zzse paramzzse)
  {
    byte[] arrayOfByte = new byte[paramzzse.zzFR()];
    zza(paramzzse, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }
  
  public String toString()
  {
    return zzsf.zzg(this);
  }
  
  protected int zzB()
  {
    return 0;
  }
  
  public zzse zzFG()
    throws CloneNotSupportedException
  {
    return (zzse)super.clone();
  }
  
  public int zzFQ()
  {
    if (this.zzbiv < 0) {
      zzFR();
    }
    return this.zzbiv;
  }
  
  public int zzFR()
  {
    int i = zzB();
    this.zzbiv = i;
    return i;
  }
  
  public void zza(zzrx paramzzrx)
    throws IOException
  {}
  
  public abstract zzse zzb(zzrw paramzzrw)
    throws IOException;
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */