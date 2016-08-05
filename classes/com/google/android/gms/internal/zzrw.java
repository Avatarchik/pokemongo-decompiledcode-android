package com.google.android.gms.internal;

import java.io.IOException;

public final class zzrw
{
  private final byte[] buffer;
  private int zzbia;
  private int zzbib;
  private int zzbic;
  private int zzbid;
  private int zzbie;
  private int zzbif = Integer.MAX_VALUE;
  private int zzbig;
  private int zzbih = 64;
  private int zzbii = 67108864;
  
  private zzrw(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.buffer = paramArrayOfByte;
    this.zzbia = paramInt1;
    this.zzbib = (paramInt1 + paramInt2);
    this.zzbid = paramInt1;
  }
  
  public static zzrw zzB(byte[] paramArrayOfByte)
  {
    return zza(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  private void zzFz()
  {
    this.zzbib += this.zzbic;
    int i = this.zzbib;
    if (i > this.zzbif)
    {
      this.zzbic = (i - this.zzbif);
      this.zzbib -= this.zzbic;
    }
    for (;;)
    {
      return;
      this.zzbic = 0;
    }
  }
  
  public static long zzX(long paramLong)
  {
    return paramLong >>> 1 ^ -(1L & paramLong);
  }
  
  public static zzrw zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new zzrw(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public static int zzlB(int paramInt)
  {
    return paramInt >>> 1 ^ -(paramInt & 0x1);
  }
  
  public int getPosition()
  {
    return this.zzbid - this.zzbia;
  }
  
  public byte[] readBytes()
    throws IOException
  {
    int i = zzFv();
    byte[] arrayOfByte;
    if ((i <= this.zzbib - this.zzbid) && (i > 0))
    {
      arrayOfByte = new byte[i];
      System.arraycopy(this.buffer, this.zzbid, arrayOfByte, 0, i);
      this.zzbid = (i + this.zzbid);
    }
    for (;;)
    {
      return arrayOfByte;
      if (i == 0) {
        arrayOfByte = zzsh.zzbiE;
      } else {
        arrayOfByte = zzlF(i);
      }
    }
  }
  
  public double readDouble()
    throws IOException
  {
    return Double.longBitsToDouble(zzFy());
  }
  
  public float readFloat()
    throws IOException
  {
    return Float.intBitsToFloat(zzFx());
  }
  
  public String readString()
    throws IOException
  {
    int i = zzFv();
    String str;
    if ((i <= this.zzbib - this.zzbid) && (i > 0))
    {
      str = new String(this.buffer, this.zzbid, i, "UTF-8");
      this.zzbid = (i + this.zzbid);
    }
    for (;;)
    {
      return str;
      str = new String(zzlF(i), "UTF-8");
    }
  }
  
  public int zzFA()
  {
    if (this.zzbif == Integer.MAX_VALUE) {}
    int i;
    for (int j = -1;; j = this.zzbif - i)
    {
      return j;
      i = this.zzbid;
    }
  }
  
  public boolean zzFB()
  {
    if (this.zzbid == this.zzbib) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public byte zzFC()
    throws IOException
  {
    if (this.zzbid == this.zzbib) {
      throw zzsd.zzFJ();
    }
    byte[] arrayOfByte = this.buffer;
    int i = this.zzbid;
    this.zzbid = (i + 1);
    return arrayOfByte[i];
  }
  
  public int zzFo()
    throws IOException
  {
    int i = 0;
    if (zzFB()) {
      this.zzbie = 0;
    }
    for (;;)
    {
      return i;
      this.zzbie = zzFv();
      if (this.zzbie == 0) {
        throw zzsd.zzFM();
      }
      i = this.zzbie;
    }
  }
  
  public void zzFp()
    throws IOException
  {
    int i;
    do
    {
      i = zzFo();
    } while ((i != 0) && (zzlA(i)));
  }
  
  public long zzFq()
    throws IOException
  {
    return zzFw();
  }
  
  public int zzFr()
    throws IOException
  {
    return zzFv();
  }
  
  public boolean zzFs()
    throws IOException
  {
    if (zzFv() != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int zzFt()
    throws IOException
  {
    return zzlB(zzFv());
  }
  
  public long zzFu()
    throws IOException
  {
    return zzX(zzFw());
  }
  
  public int zzFv()
    throws IOException
  {
    int i = zzFC();
    if (i >= 0) {}
    int i4;
    do
    {
      int i1;
      int i2;
      for (;;)
      {
        return i;
        int j = i & 0x7F;
        int k = zzFC();
        if (k >= 0)
        {
          i = j | k << 7;
        }
        else
        {
          int m = j | (k & 0x7F) << 7;
          int n = zzFC();
          if (n >= 0)
          {
            i = m | n << 14;
          }
          else
          {
            i1 = m | (n & 0x7F) << 14;
            i2 = zzFC();
            if (i2 < 0) {
              break;
            }
            i = i1 | i2 << 21;
          }
        }
      }
      int i3 = i1 | (i2 & 0x7F) << 21;
      i4 = zzFC();
      i = i3 | i4 << 28;
    } while (i4 >= 0);
    for (int i5 = 0;; i5++)
    {
      if (i5 >= 5) {
        break label160;
      }
      if (zzFC() >= 0) {
        break;
      }
    }
    label160:
    throw zzsd.zzFL();
  }
  
  public long zzFw()
    throws IOException
  {
    int i = 0;
    long l = 0L;
    while (i < 64)
    {
      int j = zzFC();
      l |= (j & 0x7F) << i;
      if ((j & 0x80) == 0) {
        return l;
      }
      i += 7;
    }
    throw zzsd.zzFL();
  }
  
  public int zzFx()
    throws IOException
  {
    int i = zzFC();
    int j = zzFC();
    int k = zzFC();
    int m = zzFC();
    return i & 0xFF | (j & 0xFF) << 8 | (k & 0xFF) << 16 | (m & 0xFF) << 24;
  }
  
  public long zzFy()
    throws IOException
  {
    int i = zzFC();
    int j = zzFC();
    int k = zzFC();
    int m = zzFC();
    int n = zzFC();
    int i1 = zzFC();
    int i2 = zzFC();
    int i3 = zzFC();
    return 0xFF & i | (0xFF & j) << 8 | (0xFF & k) << 16 | (0xFF & m) << 24 | (0xFF & n) << 32 | (0xFF & i1) << 40 | (0xFF & i2) << 48 | (0xFF & i3) << 56;
  }
  
  public void zza(zzse paramzzse)
    throws IOException
  {
    int i = zzFv();
    if (this.zzbig >= this.zzbih) {
      throw zzsd.zzFP();
    }
    int j = zzlC(i);
    this.zzbig = (1 + this.zzbig);
    paramzzse.zzb(this);
    zzlz(0);
    this.zzbig = (-1 + this.zzbig);
    zzlD(j);
  }
  
  public void zza(zzse paramzzse, int paramInt)
    throws IOException
  {
    if (this.zzbig >= this.zzbih) {
      throw zzsd.zzFP();
    }
    this.zzbig = (1 + this.zzbig);
    paramzzse.zzb(this);
    zzlz(zzsh.zzD(paramInt, 4));
    this.zzbig = (-1 + this.zzbig);
  }
  
  public boolean zzlA(int paramInt)
    throws IOException
  {
    boolean bool = true;
    switch (zzsh.zzlU(paramInt))
    {
    default: 
      throw zzsd.zzFO();
    case 0: 
      zzFr();
    }
    for (;;)
    {
      return bool;
      zzFy();
      continue;
      zzlG(zzFv());
      continue;
      zzFp();
      zzlz(zzsh.zzD(zzsh.zzlV(paramInt), 4));
      continue;
      bool = false;
      continue;
      zzFx();
    }
  }
  
  public int zzlC(int paramInt)
    throws zzsd
  {
    if (paramInt < 0) {
      throw zzsd.zzFK();
    }
    int i = paramInt + this.zzbid;
    int j = this.zzbif;
    if (i > j) {
      throw zzsd.zzFJ();
    }
    this.zzbif = i;
    zzFz();
    return j;
  }
  
  public void zzlD(int paramInt)
  {
    this.zzbif = paramInt;
    zzFz();
  }
  
  public void zzlE(int paramInt)
  {
    if (paramInt > this.zzbid - this.zzbia) {
      throw new IllegalArgumentException("Position " + paramInt + " is beyond current " + (this.zzbid - this.zzbia));
    }
    if (paramInt < 0) {
      throw new IllegalArgumentException("Bad position " + paramInt);
    }
    this.zzbid = (paramInt + this.zzbia);
  }
  
  public byte[] zzlF(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw zzsd.zzFK();
    }
    if (paramInt + this.zzbid > this.zzbif)
    {
      zzlG(this.zzbif - this.zzbid);
      throw zzsd.zzFJ();
    }
    if (paramInt <= this.zzbib - this.zzbid)
    {
      byte[] arrayOfByte = new byte[paramInt];
      System.arraycopy(this.buffer, this.zzbid, arrayOfByte, 0, paramInt);
      this.zzbid = (paramInt + this.zzbid);
      return arrayOfByte;
    }
    throw zzsd.zzFJ();
  }
  
  public void zzlG(int paramInt)
    throws IOException
  {
    if (paramInt < 0) {
      throw zzsd.zzFK();
    }
    if (paramInt + this.zzbid > this.zzbif)
    {
      zzlG(this.zzbif - this.zzbid);
      throw zzsd.zzFJ();
    }
    if (paramInt <= this.zzbib - this.zzbid)
    {
      this.zzbid = (paramInt + this.zzbid);
      return;
    }
    throw zzsd.zzFJ();
  }
  
  public void zzlz(int paramInt)
    throws zzsd
  {
    if (this.zzbie != paramInt) {
      throw zzsd.zzFN();
    }
  }
  
  public byte[] zzx(int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte;
    if (paramInt2 == 0) {
      arrayOfByte = zzsh.zzbiE;
    }
    for (;;)
    {
      return arrayOfByte;
      arrayOfByte = new byte[paramInt2];
      int i = paramInt1 + this.zzbia;
      System.arraycopy(this.buffer, i, arrayOfByte, 0, paramInt2);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzrw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */