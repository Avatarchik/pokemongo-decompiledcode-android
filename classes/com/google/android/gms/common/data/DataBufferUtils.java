package com.google.android.gms.common.data;

import android.os.Bundle;

public final class DataBufferUtils
{
  /* Error */
  public static <T, E extends Freezable<T>> java.util.ArrayList<T> freezeAndClose(DataBuffer<E> paramDataBuffer)
  {
    // Byte code:
    //   0: new 12	java/util/ArrayList
    //   3: dup
    //   4: aload_0
    //   5: invokeinterface 18 1 0
    //   10: invokespecial 21	java/util/ArrayList:<init>	(I)V
    //   13: astore_1
    //   14: aload_0
    //   15: invokeinterface 25 1 0
    //   20: astore_3
    //   21: aload_3
    //   22: invokeinterface 31 1 0
    //   27: ifeq +34 -> 61
    //   30: aload_1
    //   31: aload_3
    //   32: invokeinterface 35 1 0
    //   37: checkcast 37	com/google/android/gms/common/data/Freezable
    //   40: invokeinterface 40 1 0
    //   45: invokevirtual 44	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   48: pop
    //   49: goto -28 -> 21
    //   52: astore_2
    //   53: aload_0
    //   54: invokeinterface 47 1 0
    //   59: aload_2
    //   60: athrow
    //   61: aload_0
    //   62: invokeinterface 47 1 0
    //   67: aload_1
    //   68: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	paramDataBuffer	DataBuffer<E>
    //   13	55	1	localArrayList	java.util.ArrayList
    //   52	8	2	localObject	Object
    //   20	12	3	localIterator	java.util.Iterator
    // Exception table:
    //   from	to	target	type
    //   14	49	52	finally
  }
  
  public static boolean hasData(DataBuffer<?> paramDataBuffer)
  {
    if ((paramDataBuffer != null) && (paramDataBuffer.getCount() > 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean hasNextPage(DataBuffer<?> paramDataBuffer)
  {
    Bundle localBundle = paramDataBuffer.zzor();
    if ((localBundle != null) && (localBundle.getString("next_page_token") != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean hasPrevPage(DataBuffer<?> paramDataBuffer)
  {
    Bundle localBundle = paramDataBuffer.zzor();
    if ((localBundle != null) && (localBundle.getString("prev_page_token") != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/data/DataBufferUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */