package com.google.android.gms.internal;

import java.io.IOException;

public final class zzru
  extends zzry<zzru>
{
  public String[] zzbhU;
  public int[] zzbhV;
  public byte[][] zzbhW;
  
  public zzru()
  {
    zzFn();
  }
  
  public static zzru zzz(byte[] paramArrayOfByte)
    throws zzsd
  {
    return (zzru)zzse.zza(new zzru(), paramArrayOfByte);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (paramObject == this) {
      bool = true;
    }
    for (;;)
    {
      return bool;
      if ((paramObject instanceof zzru))
      {
        zzru localzzru = (zzru)paramObject;
        if ((zzsc.equals(this.zzbhU, localzzru.zzbhU)) && (zzsc.equals(this.zzbhV, localzzru.zzbhV)) && (zzsc.zza(this.zzbhW, localzzru.zzbhW))) {
          if ((this.zzbik == null) || (this.zzbik.isEmpty()))
          {
            if ((localzzru.zzbik == null) || (localzzru.zzbik.isEmpty())) {
              bool = true;
            }
          }
          else {
            bool = this.zzbik.equals(localzzru.zzbik);
          }
        }
      }
    }
  }
  
  public int hashCode()
  {
    int i = 31 * (31 * (31 * (31 * (527 + getClass().getName().hashCode()) + zzsc.hashCode(this.zzbhU)) + zzsc.hashCode(this.zzbhV)) + zzsc.zza(this.zzbhW));
    if ((this.zzbik == null) || (this.zzbik.isEmpty())) {}
    for (int j = 0;; j = this.zzbik.hashCode()) {
      return j + i;
    }
  }
  
  protected int zzB()
  {
    int i = 0;
    int j = super.zzB();
    int i4;
    int i5;
    if ((this.zzbhU != null) && (this.zzbhU.length > 0))
    {
      int i3 = 0;
      i4 = 0;
      i5 = 0;
      while (i3 < this.zzbhU.length)
      {
        String str = this.zzbhU[i3];
        if (str != null)
        {
          i5++;
          i4 += zzrx.zzfA(str);
        }
        i3++;
      }
    }
    for (int k = j + i4 + i5 * 1;; k = j)
    {
      if ((this.zzbhV != null) && (this.zzbhV.length > 0))
      {
        int i1 = 0;
        int i2 = 0;
        while (i1 < this.zzbhV.length)
        {
          i2 += zzrx.zzlJ(this.zzbhV[i1]);
          i1++;
        }
        k = k + i2 + 1 * this.zzbhV.length;
      }
      if ((this.zzbhW != null) && (this.zzbhW.length > 0))
      {
        int m = 0;
        int n = 0;
        while (i < this.zzbhW.length)
        {
          byte[] arrayOfByte = this.zzbhW[i];
          if (arrayOfByte != null)
          {
            n++;
            m += zzrx.zzE(arrayOfByte);
          }
          i++;
        }
        k = k + m + n * 1;
      }
      return k;
    }
  }
  
  public zzru zzE(zzrw paramzzrw)
    throws IOException
  {
    for (;;)
    {
      int i = paramzzrw.zzFo();
      switch (i)
      {
      default: 
        if (zza(paramzzrw, i)) {}
        break;
      case 0: 
        return this;
      case 10: 
        int i5 = zzsh.zzc(paramzzrw, 10);
        if (this.zzbhU == null) {}
        String[] arrayOfString;
        for (int i6 = 0;; i6 = this.zzbhU.length)
        {
          arrayOfString = new String[i5 + i6];
          if (i6 != 0) {
            System.arraycopy(this.zzbhU, 0, arrayOfString, 0, i6);
          }
          while (i6 < -1 + arrayOfString.length)
          {
            arrayOfString[i6] = paramzzrw.readString();
            paramzzrw.zzFo();
            i6++;
          }
        }
        arrayOfString[i6] = paramzzrw.readString();
        this.zzbhU = arrayOfString;
        break;
      case 16: 
        int i3 = zzsh.zzc(paramzzrw, 16);
        if (this.zzbhV == null) {}
        int[] arrayOfInt2;
        for (int i4 = 0;; i4 = this.zzbhV.length)
        {
          arrayOfInt2 = new int[i3 + i4];
          if (i4 != 0) {
            System.arraycopy(this.zzbhV, 0, arrayOfInt2, 0, i4);
          }
          while (i4 < -1 + arrayOfInt2.length)
          {
            arrayOfInt2[i4] = paramzzrw.zzFr();
            paramzzrw.zzFo();
            i4++;
          }
        }
        arrayOfInt2[i4] = paramzzrw.zzFr();
        this.zzbhV = arrayOfInt2;
        break;
      case 18: 
        int m = paramzzrw.zzlC(paramzzrw.zzFv());
        int n = paramzzrw.getPosition();
        for (int i1 = 0; paramzzrw.zzFA() > 0; i1++) {
          paramzzrw.zzFr();
        }
        paramzzrw.zzlE(n);
        if (this.zzbhV == null) {}
        int[] arrayOfInt1;
        for (int i2 = 0;; i2 = this.zzbhV.length)
        {
          arrayOfInt1 = new int[i1 + i2];
          if (i2 != 0) {
            System.arraycopy(this.zzbhV, 0, arrayOfInt1, 0, i2);
          }
          while (i2 < arrayOfInt1.length)
          {
            arrayOfInt1[i2] = paramzzrw.zzFr();
            i2++;
          }
        }
        this.zzbhV = arrayOfInt1;
        paramzzrw.zzlD(m);
        break;
      case 26: 
        int j = zzsh.zzc(paramzzrw, 26);
        if (this.zzbhW == null) {}
        byte[][] arrayOfByte;
        for (int k = 0;; k = this.zzbhW.length)
        {
          arrayOfByte = new byte[j + k][];
          if (k != 0) {
            System.arraycopy(this.zzbhW, 0, arrayOfByte, 0, k);
          }
          while (k < -1 + arrayOfByte.length)
          {
            arrayOfByte[k] = paramzzrw.readBytes();
            paramzzrw.zzFo();
            k++;
          }
        }
        arrayOfByte[k] = paramzzrw.readBytes();
        this.zzbhW = arrayOfByte;
      }
    }
  }
  
  public zzru zzFn()
  {
    this.zzbhU = zzsh.zzbiC;
    this.zzbhV = zzsh.zzbix;
    this.zzbhW = zzsh.zzbiD;
    this.zzbik = null;
    this.zzbiv = -1;
    return this;
  }
  
  public void zza(zzrx paramzzrx)
    throws IOException
  {
    int i = 0;
    if ((this.zzbhU != null) && (this.zzbhU.length > 0)) {
      for (int k = 0; k < this.zzbhU.length; k++)
      {
        String str = this.zzbhU[k];
        if (str != null) {
          paramzzrx.zzb(1, str);
        }
      }
    }
    if ((this.zzbhV != null) && (this.zzbhV.length > 0)) {
      for (int j = 0; j < this.zzbhV.length; j++) {
        paramzzrx.zzy(2, this.zzbhV[j]);
      }
    }
    if ((this.zzbhW != null) && (this.zzbhW.length > 0)) {
      while (i < this.zzbhW.length)
      {
        byte[] arrayOfByte = this.zzbhW[i];
        if (arrayOfByte != null) {
          paramzzrx.zza(3, arrayOfByte);
        }
        i++;
      }
    }
    super.zza(paramzzrx);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzru.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */