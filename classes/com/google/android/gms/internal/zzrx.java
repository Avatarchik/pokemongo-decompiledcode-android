package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzrx
{
  private final ByteBuffer zzbij;
  
  private zzrx(ByteBuffer paramByteBuffer)
  {
    this.zzbij = paramByteBuffer;
    this.zzbij.order(ByteOrder.LITTLE_ENDIAN);
  }
  
  private zzrx(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this(ByteBuffer.wrap(paramArrayOfByte, paramInt1, paramInt2));
  }
  
  public static int zzA(int paramInt1, int paramInt2)
  {
    return zzlM(paramInt1) + zzlJ(paramInt2);
  }
  
  public static int zzB(int paramInt1, int paramInt2)
  {
    return zzlM(paramInt1) + zzlK(paramInt2);
  }
  
  public static zzrx zzC(byte[] paramArrayOfByte)
  {
    return zzb(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static int zzE(byte[] paramArrayOfByte)
  {
    return zzlO(paramArrayOfByte.length) + paramArrayOfByte.length;
  }
  
  private static int zza(CharSequence paramCharSequence, int paramInt)
  {
    int i = paramCharSequence.length();
    int j = 0;
    int k = paramInt;
    if (k < i)
    {
      int m = paramCharSequence.charAt(k);
      if (m < 2048) {
        j += (127 - m >>> 31);
      }
      for (;;)
      {
        k++;
        break;
        j += 2;
        if ((55296 <= m) && (m <= 57343))
        {
          if (Character.codePointAt(paramCharSequence, k) < 65536) {
            throw new IllegalArgumentException("Unpaired surrogate at index " + k);
          }
          k++;
        }
      }
    }
    return j;
  }
  
  private static int zza(CharSequence paramCharSequence, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int i = paramCharSequence.length();
    int j = 0;
    int k = paramInt1 + paramInt2;
    while ((j < i) && (j + paramInt1 < k))
    {
      int i10 = paramCharSequence.charAt(j);
      if (i10 >= 128) {
        break;
      }
      paramArrayOfByte[(paramInt1 + j)] = ((byte)i10);
      j++;
    }
    if (j == i) {}
    int m;
    for (int n = paramInt1 + i;; n = m)
    {
      return n;
      m = paramInt1 + j;
      if (j < i)
      {
        int i1 = paramCharSequence.charAt(j);
        int i6;
        if ((i1 < 128) && (m < k))
        {
          i6 = m + 1;
          paramArrayOfByte[m] = ((byte)i1);
        }
        for (;;)
        {
          j++;
          m = i6;
          break;
          if ((i1 < 2048) && (m <= k - 2))
          {
            int i9 = m + 1;
            paramArrayOfByte[m] = ((byte)(0x3C0 | i1 >>> 6));
            i6 = i9 + 1;
            paramArrayOfByte[i9] = ((byte)(0x80 | i1 & 0x3F));
          }
          else if (((i1 < 55296) || (57343 < i1)) && (m <= k - 3))
          {
            int i7 = m + 1;
            paramArrayOfByte[m] = ((byte)(0x1E0 | i1 >>> 12));
            int i8 = i7 + 1;
            paramArrayOfByte[i7] = ((byte)(0x80 | 0x3F & i1 >>> 6));
            i6 = i8 + 1;
            paramArrayOfByte[i8] = ((byte)(0x80 | i1 & 0x3F));
          }
          else
          {
            if (m > k - 4) {
              break label464;
            }
            char c;
            if (j + 1 != paramCharSequence.length())
            {
              j++;
              c = paramCharSequence.charAt(j);
              if (Character.isSurrogatePair(i1, c)) {}
            }
            else
            {
              throw new IllegalArgumentException("Unpaired surrogate at index " + (j - 1));
            }
            int i2 = Character.toCodePoint(i1, c);
            int i3 = m + 1;
            paramArrayOfByte[m] = ((byte)(0xF0 | i2 >>> 18));
            int i4 = i3 + 1;
            paramArrayOfByte[i3] = ((byte)(0x80 | 0x3F & i2 >>> 12));
            int i5 = i4 + 1;
            paramArrayOfByte[i4] = ((byte)(0x80 | 0x3F & i2 >>> 6));
            i6 = i5 + 1;
            paramArrayOfByte[i5] = ((byte)(0x80 | i2 & 0x3F));
          }
        }
        label464:
        if ((55296 <= i1) && (i1 <= 57343) && ((j + 1 == paramCharSequence.length()) || (!Character.isSurrogatePair(i1, paramCharSequence.charAt(j + 1))))) {
          throw new IllegalArgumentException("Unpaired surrogate at index " + j);
        }
        throw new ArrayIndexOutOfBoundsException("Failed writing " + i1 + " at index " + m);
      }
    }
  }
  
  private static void zza(CharSequence paramCharSequence, ByteBuffer paramByteBuffer)
  {
    if (paramByteBuffer.isReadOnly()) {
      throw new ReadOnlyBufferException();
    }
    if (paramByteBuffer.hasArray()) {}
    for (;;)
    {
      try
      {
        paramByteBuffer.position(zza(paramCharSequence, paramByteBuffer.array(), paramByteBuffer.arrayOffset() + paramByteBuffer.position(), paramByteBuffer.remaining()) - paramByteBuffer.arrayOffset());
        return;
      }
      catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
      {
        BufferOverflowException localBufferOverflowException = new BufferOverflowException();
        localBufferOverflowException.initCause(localArrayIndexOutOfBoundsException);
        throw localBufferOverflowException;
      }
      zzb(paramCharSequence, paramByteBuffer);
    }
  }
  
  public static int zzaa(long paramLong)
  {
    return zzad(paramLong);
  }
  
  public static int zzab(long paramLong)
  {
    return zzad(zzaf(paramLong));
  }
  
  public static int zzad(long paramLong)
  {
    int i;
    if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L) {
      i = 1;
    }
    for (;;)
    {
      return i;
      if ((0xFFFFFFFFFFFFC000 & paramLong) == 0L) {
        i = 2;
      } else if ((0xFFFFFFFFFFE00000 & paramLong) == 0L) {
        i = 3;
      } else if ((0xFFFFFFFFF0000000 & paramLong) == 0L) {
        i = 4;
      } else if ((0xFFFFFFF800000000 & paramLong) == 0L) {
        i = 5;
      } else if ((0xFFFFFC0000000000 & paramLong) == 0L) {
        i = 6;
      } else if ((0xFFFE000000000000 & paramLong) == 0L) {
        i = 7;
      } else if ((0xFF00000000000000 & paramLong) == 0L) {
        i = 8;
      } else if ((0x8000000000000000 & paramLong) == 0L) {
        i = 9;
      } else {
        i = 10;
      }
    }
  }
  
  public static long zzaf(long paramLong)
  {
    return paramLong << 1 ^ paramLong >> 63;
  }
  
  public static int zzav(boolean paramBoolean)
  {
    return 1;
  }
  
  public static int zzb(int paramInt, double paramDouble)
  {
    return zzlM(paramInt) + zzk(paramDouble);
  }
  
  public static int zzb(int paramInt, zzse paramzzse)
  {
    return 2 * zzlM(paramInt) + zzd(paramzzse);
  }
  
  public static int zzb(int paramInt, byte[] paramArrayOfByte)
  {
    return zzlM(paramInt) + zzE(paramArrayOfByte);
  }
  
  public static zzrx zzb(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new zzrx(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  private static void zzb(CharSequence paramCharSequence, ByteBuffer paramByteBuffer)
  {
    int i = paramCharSequence.length();
    int j = 0;
    if (j < i)
    {
      int k = paramCharSequence.charAt(j);
      if (k < 128) {
        paramByteBuffer.put((byte)k);
      }
      for (;;)
      {
        j++;
        break;
        if (k < 2048)
        {
          paramByteBuffer.put((byte)(0x3C0 | k >>> 6));
          paramByteBuffer.put((byte)(0x80 | k & 0x3F));
        }
        else if ((k < 55296) || (57343 < k))
        {
          paramByteBuffer.put((byte)(0x1E0 | k >>> 12));
          paramByteBuffer.put((byte)(0x80 | 0x3F & k >>> 6));
          paramByteBuffer.put((byte)(0x80 | k & 0x3F));
        }
        else
        {
          char c;
          if (j + 1 != paramCharSequence.length())
          {
            j++;
            c = paramCharSequence.charAt(j);
            if (Character.isSurrogatePair(k, c)) {}
          }
          else
          {
            throw new IllegalArgumentException("Unpaired surrogate at index " + (j - 1));
          }
          int m = Character.toCodePoint(k, c);
          paramByteBuffer.put((byte)(0xF0 | m >>> 18));
          paramByteBuffer.put((byte)(0x80 | 0x3F & m >>> 12));
          paramByteBuffer.put((byte)(0x80 | 0x3F & m >>> 6));
          paramByteBuffer.put((byte)(0x80 | m & 0x3F));
        }
      }
    }
  }
  
  public static int zzc(int paramInt, float paramFloat)
  {
    return zzlM(paramInt) + zzj(paramFloat);
  }
  
  public static int zzc(int paramInt, zzse paramzzse)
  {
    return zzlM(paramInt) + zze(paramzzse);
  }
  
  public static int zzc(int paramInt, boolean paramBoolean)
  {
    return zzlM(paramInt) + zzav(paramBoolean);
  }
  
  private static int zzc(CharSequence paramCharSequence)
  {
    int i = paramCharSequence.length();
    int j = 0;
    while ((j < i) && (paramCharSequence.charAt(j) < '')) {
      j += 1;
    }
    for (;;)
    {
      int k;
      int m;
      if (k < i)
      {
        int n = paramCharSequence.charAt(k);
        if (n < 2048)
        {
          int i1 = m + (127 - n >>> 31);
          k += 1;
          m = i1;
        }
        else
        {
          m += zza(paramCharSequence, k);
        }
      }
      else
      {
        if (m < i) {
          throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (4294967296L + m));
        }
        return m;
        k = j;
        m = i;
      }
    }
  }
  
  public static int zzd(int paramInt, long paramLong)
  {
    return zzlM(paramInt) + zzaa(paramLong);
  }
  
  public static int zzd(zzse paramzzse)
  {
    return paramzzse.zzFR();
  }
  
  public static int zze(int paramInt, long paramLong)
  {
    return zzlM(paramInt) + zzab(paramLong);
  }
  
  public static int zze(zzse paramzzse)
  {
    int i = paramzzse.zzFR();
    return i + zzlO(i);
  }
  
  public static int zzfA(String paramString)
  {
    int i = zzc(paramString);
    return i + zzlO(i);
  }
  
  public static int zzj(float paramFloat)
  {
    return 4;
  }
  
  public static int zzk(double paramDouble)
  {
    return 8;
  }
  
  public static int zzlJ(int paramInt)
  {
    if (paramInt >= 0) {}
    for (int i = zzlO(paramInt);; i = 10) {
      return i;
    }
  }
  
  public static int zzlK(int paramInt)
  {
    return zzlO(zzlQ(paramInt));
  }
  
  public static int zzlM(int paramInt)
  {
    return zzlO(zzsh.zzD(paramInt, 0));
  }
  
  public static int zzlO(int paramInt)
  {
    int i;
    if ((paramInt & 0xFFFFFF80) == 0) {
      i = 1;
    }
    for (;;)
    {
      return i;
      if ((paramInt & 0xC000) == 0) {
        i = 2;
      } else if ((0xFFE00000 & paramInt) == 0) {
        i = 3;
      } else if ((0xF0000000 & paramInt) == 0) {
        i = 4;
      } else {
        i = 5;
      }
    }
  }
  
  public static int zzlQ(int paramInt)
  {
    return paramInt << 1 ^ paramInt >> 31;
  }
  
  public static int zzn(int paramInt, String paramString)
  {
    return zzlM(paramInt) + zzfA(paramString);
  }
  
  public void zzC(int paramInt1, int paramInt2)
    throws IOException
  {
    zzlN(zzsh.zzD(paramInt1, paramInt2));
  }
  
  public void zzD(byte[] paramArrayOfByte)
    throws IOException
  {
    zzlN(paramArrayOfByte.length);
    zzF(paramArrayOfByte);
  }
  
  public void zzF(byte[] paramArrayOfByte)
    throws IOException
  {
    zzc(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int zzFD()
  {
    return this.zzbij.remaining();
  }
  
  public void zzFE()
  {
    if (zzFD() != 0) {
      throw new IllegalStateException("Did not write as much data as expected.");
    }
  }
  
  public void zzY(long paramLong)
    throws IOException
  {
    zzac(paramLong);
  }
  
  public void zzZ(long paramLong)
    throws IOException
  {
    zzac(zzaf(paramLong));
  }
  
  public void zza(int paramInt, double paramDouble)
    throws IOException
  {
    zzC(paramInt, 1);
    zzj(paramDouble);
  }
  
  public void zza(int paramInt, zzse paramzzse)
    throws IOException
  {
    zzC(paramInt, 2);
    zzc(paramzzse);
  }
  
  public void zza(int paramInt, byte[] paramArrayOfByte)
    throws IOException
  {
    zzC(paramInt, 2);
    zzD(paramArrayOfByte);
  }
  
  public void zzac(long paramLong)
    throws IOException
  {
    for (;;)
    {
      if ((0xFFFFFFFFFFFFFF80 & paramLong) == 0L)
      {
        zzlL((int)paramLong);
        return;
      }
      zzlL(0x80 | 0x7F & (int)paramLong);
      paramLong >>>= 7;
    }
  }
  
  public void zzae(long paramLong)
    throws IOException
  {
    if (this.zzbij.remaining() < 8) {
      throw new zza(this.zzbij.position(), this.zzbij.limit());
    }
    this.zzbij.putLong(paramLong);
  }
  
  public void zzau(boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      zzlL(i);
      return;
    }
  }
  
  public void zzb(byte paramByte)
    throws IOException
  {
    if (!this.zzbij.hasRemaining()) {
      throw new zza(this.zzbij.position(), this.zzbij.limit());
    }
    this.zzbij.put(paramByte);
  }
  
  public void zzb(int paramInt, float paramFloat)
    throws IOException
  {
    zzC(paramInt, 5);
    zzi(paramFloat);
  }
  
  public void zzb(int paramInt, long paramLong)
    throws IOException
  {
    zzC(paramInt, 0);
    zzY(paramLong);
  }
  
  public void zzb(int paramInt, String paramString)
    throws IOException
  {
    zzC(paramInt, 2);
    zzfz(paramString);
  }
  
  public void zzb(int paramInt, boolean paramBoolean)
    throws IOException
  {
    zzC(paramInt, 0);
    zzau(paramBoolean);
  }
  
  public void zzb(zzse paramzzse)
    throws IOException
  {
    paramzzse.zza(this);
  }
  
  public void zzc(int paramInt, long paramLong)
    throws IOException
  {
    zzC(paramInt, 0);
    zzZ(paramLong);
  }
  
  public void zzc(zzse paramzzse)
    throws IOException
  {
    zzlN(paramzzse.zzFQ());
    paramzzse.zza(this);
  }
  
  public void zzc(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this.zzbij.remaining() >= paramInt2)
    {
      this.zzbij.put(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    throw new zza(this.zzbij.position(), this.zzbij.limit());
  }
  
  public void zzfz(String paramString)
    throws IOException
  {
    int i;
    int j;
    try
    {
      i = zzlO(paramString.length());
      if (i != zzlO(3 * paramString.length())) {
        break label160;
      }
      j = this.zzbij.position();
      if (this.zzbij.remaining() < i) {
        throw new zza(i + j, this.zzbij.limit());
      }
    }
    catch (BufferOverflowException localBufferOverflowException)
    {
      zza localzza = new zza(this.zzbij.position(), this.zzbij.limit());
      localzza.initCause(localBufferOverflowException);
      throw localzza;
    }
    this.zzbij.position(j + i);
    zza(paramString, this.zzbij);
    int k = this.zzbij.position();
    this.zzbij.position(j);
    zzlN(k - j - i);
    this.zzbij.position(k);
    return;
    label160:
    zzlN(zzc(paramString));
    zza(paramString, this.zzbij);
  }
  
  public void zzi(float paramFloat)
    throws IOException
  {
    zzlP(Float.floatToIntBits(paramFloat));
  }
  
  public void zzj(double paramDouble)
    throws IOException
  {
    zzae(Double.doubleToLongBits(paramDouble));
  }
  
  public void zzlH(int paramInt)
    throws IOException
  {
    if (paramInt >= 0) {
      zzlN(paramInt);
    }
    for (;;)
    {
      return;
      zzac(paramInt);
    }
  }
  
  public void zzlI(int paramInt)
    throws IOException
  {
    zzlN(zzlQ(paramInt));
  }
  
  public void zzlL(int paramInt)
    throws IOException
  {
    zzb((byte)paramInt);
  }
  
  public void zzlN(int paramInt)
    throws IOException
  {
    for (;;)
    {
      if ((paramInt & 0xFFFFFF80) == 0)
      {
        zzlL(paramInt);
        return;
      }
      zzlL(0x80 | paramInt & 0x7F);
      paramInt >>>= 7;
    }
  }
  
  public void zzlP(int paramInt)
    throws IOException
  {
    if (this.zzbij.remaining() < 4) {
      throw new zza(this.zzbij.position(), this.zzbij.limit());
    }
    this.zzbij.putInt(paramInt);
  }
  
  public void zzy(int paramInt1, int paramInt2)
    throws IOException
  {
    zzC(paramInt1, 0);
    zzlH(paramInt2);
  }
  
  public void zzz(int paramInt1, int paramInt2)
    throws IOException
  {
    zzC(paramInt1, 0);
    zzlI(paramInt2);
  }
  
  public static class zza
    extends IOException
  {
    zza(int paramInt1, int paramInt2)
    {
      super();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzrx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */