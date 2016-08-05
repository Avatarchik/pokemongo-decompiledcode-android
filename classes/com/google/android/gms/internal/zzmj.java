package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzw;
import java.util.ArrayList;

public final class zzmj
{
  public static <T> int zza(T[] paramArrayOfT, T paramT)
  {
    int i = 0;
    int j;
    if (paramArrayOfT != null)
    {
      j = paramArrayOfT.length;
      if (i >= j) {
        break label41;
      }
      if (!zzw.equal(paramArrayOfT[i], paramT)) {
        break label35;
      }
    }
    label35:
    label41:
    for (int k = i;; k = -1)
    {
      return k;
      j = 0;
      break;
      i++;
      break;
    }
  }
  
  public static void zza(StringBuilder paramStringBuilder, double[] paramArrayOfDouble)
  {
    int i = paramArrayOfDouble.length;
    for (int j = 0; j < i; j++)
    {
      if (j != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append(Double.toString(paramArrayOfDouble[j]));
    }
  }
  
  public static void zza(StringBuilder paramStringBuilder, float[] paramArrayOfFloat)
  {
    int i = paramArrayOfFloat.length;
    for (int j = 0; j < i; j++)
    {
      if (j != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append(Float.toString(paramArrayOfFloat[j]));
    }
  }
  
  public static void zza(StringBuilder paramStringBuilder, int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    for (int j = 0; j < i; j++)
    {
      if (j != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append(Integer.toString(paramArrayOfInt[j]));
    }
  }
  
  public static void zza(StringBuilder paramStringBuilder, long[] paramArrayOfLong)
  {
    int i = paramArrayOfLong.length;
    for (int j = 0; j < i; j++)
    {
      if (j != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append(Long.toString(paramArrayOfLong[j]));
    }
  }
  
  public static <T> void zza(StringBuilder paramStringBuilder, T[] paramArrayOfT)
  {
    int i = paramArrayOfT.length;
    for (int j = 0; j < i; j++)
    {
      if (j != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append(paramArrayOfT[j].toString());
    }
  }
  
  public static void zza(StringBuilder paramStringBuilder, String[] paramArrayOfString)
  {
    int i = paramArrayOfString.length;
    for (int j = 0; j < i; j++)
    {
      if (j != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append("\"").append(paramArrayOfString[j]).append("\"");
    }
  }
  
  public static void zza(StringBuilder paramStringBuilder, boolean[] paramArrayOfBoolean)
  {
    int i = paramArrayOfBoolean.length;
    for (int j = 0; j < i; j++)
    {
      if (j != 0) {
        paramStringBuilder.append(",");
      }
      paramStringBuilder.append(Boolean.toString(paramArrayOfBoolean[j]));
    }
  }
  
  public static Integer[] zza(int[] paramArrayOfInt)
  {
    Integer[] arrayOfInteger;
    if (paramArrayOfInt == null) {
      arrayOfInteger = null;
    }
    for (;;)
    {
      return arrayOfInteger;
      int i = paramArrayOfInt.length;
      arrayOfInteger = new Integer[i];
      for (int j = 0; j < i; j++) {
        arrayOfInteger[j] = Integer.valueOf(paramArrayOfInt[j]);
      }
    }
  }
  
  public static <T> ArrayList<T> zzb(T[] paramArrayOfT)
  {
    int i = paramArrayOfT.length;
    ArrayList localArrayList = new ArrayList(i);
    for (int j = 0; j < i; j++) {
      localArrayList.add(paramArrayOfT[j]);
    }
    return localArrayList;
  }
  
  public static <T> boolean zzb(T[] paramArrayOfT, T paramT)
  {
    if (zza(paramArrayOfT, paramT) >= 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static <T> ArrayList<T> zzqs()
  {
    return new ArrayList();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzmj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */