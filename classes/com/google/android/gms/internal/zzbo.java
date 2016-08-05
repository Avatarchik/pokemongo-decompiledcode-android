package com.google.android.gms.internal;

import java.security.MessageDigest;

public class zzbo
  extends zzbl
{
  private MessageDigest zzsw;
  
  byte[] zza(String[] paramArrayOfString)
  {
    byte[] arrayOfByte = new byte[paramArrayOfString.length];
    for (int i = 0; i < paramArrayOfString.length; i++) {
      arrayOfByte[i] = zzj(zzbn.zzC(paramArrayOfString[i]));
    }
    return arrayOfByte;
  }
  
  byte zzj(int paramInt)
  {
    return (byte)(paramInt & 0xFF ^ (0xFF00 & paramInt) >> 8 ^ (0xFF0000 & paramInt) >> 16 ^ (0xFF000000 & paramInt) >> 24);
  }
  
  public byte[] zzz(String paramString)
  {
    byte[] arrayOfByte1 = zza(paramString.split(" "));
    this.zzsw = zzcy();
    byte[] arrayOfByte3;
    byte[] arrayOfByte2;
    int i;
    synchronized (this.zzpd)
    {
      if (this.zzsw == null)
      {
        arrayOfByte3 = new byte[0];
      }
      else
      {
        this.zzsw.reset();
        this.zzsw.update(arrayOfByte1);
        arrayOfByte2 = this.zzsw.digest();
        i = 4;
        if (arrayOfByte2.length > i)
        {
          arrayOfByte3 = new byte[i];
          System.arraycopy(arrayOfByte2, 0, arrayOfByte3, 0, arrayOfByte3.length);
        }
      }
    }
    return arrayOfByte3;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzbo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */