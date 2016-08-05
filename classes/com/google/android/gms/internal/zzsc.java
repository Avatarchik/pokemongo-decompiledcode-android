package com.google.android.gms.internal;

import java.util.Arrays;

public final class zzsc
{
  public static final Object zzbiu = new Object();
  
  public static boolean equals(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    boolean bool;
    if ((paramArrayOfFloat1 == null) || (paramArrayOfFloat1.length == 0)) {
      if ((paramArrayOfFloat2 == null) || (paramArrayOfFloat2.length == 0)) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = Arrays.equals(paramArrayOfFloat1, paramArrayOfFloat2);
    }
  }
  
  public static boolean equals(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    boolean bool;
    if ((paramArrayOfInt1 == null) || (paramArrayOfInt1.length == 0)) {
      if ((paramArrayOfInt2 == null) || (paramArrayOfInt2.length == 0)) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = Arrays.equals(paramArrayOfInt1, paramArrayOfInt2);
    }
  }
  
  public static boolean equals(long[] paramArrayOfLong1, long[] paramArrayOfLong2)
  {
    boolean bool;
    if ((paramArrayOfLong1 == null) || (paramArrayOfLong1.length == 0)) {
      if ((paramArrayOfLong2 == null) || (paramArrayOfLong2.length == 0)) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = Arrays.equals(paramArrayOfLong1, paramArrayOfLong2);
    }
  }
  
  public static boolean equals(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    boolean bool = false;
    int i;
    if (paramArrayOfObject1 == null)
    {
      i = 0;
      if (paramArrayOfObject2 != null) {
        break label46;
      }
    }
    int k;
    int m;
    label46:
    for (int j = 0;; j = paramArrayOfObject2.length)
    {
      k = 0;
      for (m = 0; (m < i) && (paramArrayOfObject1[m] == null); m++) {}
      i = paramArrayOfObject1.length;
      break;
    }
    for (;;)
    {
      int n;
      if ((n < j) && (paramArrayOfObject2[n] == null))
      {
        n++;
      }
      else
      {
        int i1;
        int i2;
        if (m >= i)
        {
          i1 = 1;
          if (n < j) {
            break label112;
          }
          i2 = 1;
          label92:
          if ((i1 == 0) || (i2 == 0)) {
            break label118;
          }
          bool = true;
        }
        label112:
        label118:
        while ((i1 != i2) || (!paramArrayOfObject1[m].equals(paramArrayOfObject2[n])))
        {
          return bool;
          i1 = 0;
          break;
          i2 = 0;
          break label92;
        }
        int i3 = m + 1;
        k = n + 1;
        m = i3;
        break;
        n = k;
      }
    }
  }
  
  public static int hashCode(float[] paramArrayOfFloat)
  {
    if ((paramArrayOfFloat == null) || (paramArrayOfFloat.length == 0)) {}
    for (int i = 0;; i = Arrays.hashCode(paramArrayOfFloat)) {
      return i;
    }
  }
  
  public static int hashCode(int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0)) {}
    for (int i = 0;; i = Arrays.hashCode(paramArrayOfInt)) {
      return i;
    }
  }
  
  public static int hashCode(long[] paramArrayOfLong)
  {
    if ((paramArrayOfLong == null) || (paramArrayOfLong.length == 0)) {}
    for (int i = 0;; i = Arrays.hashCode(paramArrayOfLong)) {
      return i;
    }
  }
  
  public static int hashCode(Object[] paramArrayOfObject)
  {
    int i = 0;
    if (paramArrayOfObject == null) {}
    for (int j = 0;; j = paramArrayOfObject.length) {
      for (int k = 0; k < j; k++)
      {
        Object localObject = paramArrayOfObject[k];
        if (localObject != null) {
          i = i * 31 + localObject.hashCode();
        }
      }
    }
    return i;
  }
  
  public static int zza(byte[][] paramArrayOfByte)
  {
    int i = 0;
    if (paramArrayOfByte == null) {}
    for (int j = 0;; j = paramArrayOfByte.length) {
      for (int k = 0; k < j; k++)
      {
        byte[] arrayOfByte = paramArrayOfByte[k];
        if (arrayOfByte != null) {
          i = i * 31 + Arrays.hashCode(arrayOfByte);
        }
      }
    }
    return i;
  }
  
  public static void zza(zzry paramzzry1, zzry paramzzry2)
  {
    if (paramzzry1.zzbik != null) {
      paramzzry2.zzbik = paramzzry1.zzbik.zzFH();
    }
  }
  
  public static boolean zza(byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2)
  {
    boolean bool = false;
    int i;
    if (paramArrayOfByte1 == null)
    {
      i = 0;
      if (paramArrayOfByte2 != null) {
        break label46;
      }
    }
    int k;
    int m;
    label46:
    for (int j = 0;; j = paramArrayOfByte2.length)
    {
      k = 0;
      for (m = 0; (m < i) && (paramArrayOfByte1[m] == null); m++) {}
      i = paramArrayOfByte1.length;
      break;
    }
    for (;;)
    {
      int n;
      if ((n < j) && (paramArrayOfByte2[n] == null))
      {
        n++;
      }
      else
      {
        int i1;
        int i2;
        if (m >= i)
        {
          i1 = 1;
          if (n < j) {
            break label112;
          }
          i2 = 1;
          label92:
          if ((i1 == 0) || (i2 == 0)) {
            break label118;
          }
          bool = true;
        }
        label112:
        label118:
        while ((i1 != i2) || (!Arrays.equals(paramArrayOfByte1[m], paramArrayOfByte2[n])))
        {
          return bool;
          i1 = 0;
          break;
          i2 = 0;
          break label92;
        }
        int i3 = m + 1;
        k = n + 1;
        m = i3;
        break;
        n = k;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzsc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */