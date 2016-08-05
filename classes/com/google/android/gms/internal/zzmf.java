package com.google.android.gms.internal;

public class zzmf
{
  public static final int[] EMPTY_INTS = new int[0];
  public static final long[] EMPTY_LONGS = new long[0];
  public static final Object[] EMPTY_OBJECTS = new Object[0];
  
  public static int binarySearch(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = paramInt1 - 1;
    int m;
    for (;;)
    {
      if (i > j) {
        break label63;
      }
      m = i + j >>> 1;
      int n = paramArrayOfInt[m];
      if (n < paramInt2)
      {
        i = m + 1;
      }
      else
      {
        if (n <= paramInt2) {
          break;
        }
        j = m - 1;
      }
    }
    label63:
    for (int k = m;; k = i ^ 0xFFFFFFFF) {
      return k;
    }
  }
  
  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzmf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */