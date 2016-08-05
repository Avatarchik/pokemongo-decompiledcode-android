package com.google.android.gms.internal;

import java.io.IOException;

class zzas
  implements zzaq
{
  private zzrx zznG;
  private byte[] zznH;
  private final int zznI;
  
  public zzas(int paramInt)
  {
    this.zznI = paramInt;
    reset();
  }
  
  public void reset()
  {
    this.zznH = new byte[this.zznI];
    this.zznG = zzrx.zzC(this.zznH);
  }
  
  public byte[] zzac()
    throws IOException
  {
    int i = this.zznG.zzFD();
    if (i < 0) {
      throw new IOException();
    }
    byte[] arrayOfByte;
    if (i == 0) {
      arrayOfByte = this.zznH;
    }
    for (;;)
    {
      return arrayOfByte;
      arrayOfByte = new byte[this.zznH.length - i];
      System.arraycopy(this.zznH, 0, arrayOfByte, 0, arrayOfByte.length);
    }
  }
  
  public void zzb(int paramInt, long paramLong)
    throws IOException
  {
    this.zznG.zzb(paramInt, paramLong);
  }
  
  public void zzb(int paramInt, String paramString)
    throws IOException
  {
    this.zznG.zzb(paramInt, paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */