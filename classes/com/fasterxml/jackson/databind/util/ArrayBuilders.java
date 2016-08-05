package com.fasterxml.jackson.databind.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ArrayBuilders
{
  private BooleanBuilder _booleanBuilder = null;
  private ByteBuilder _byteBuilder = null;
  private DoubleBuilder _doubleBuilder = null;
  private FloatBuilder _floatBuilder = null;
  private IntBuilder _intBuilder = null;
  private LongBuilder _longBuilder = null;
  private ShortBuilder _shortBuilder = null;
  
  public static <T> List<T> addToList(List<T> paramList, T paramT)
  {
    if (paramList == null) {
      paramList = new ArrayList();
    }
    paramList.add(paramT);
    return paramList;
  }
  
  public static <T> ArrayList<T> arrayToList(T[] paramArrayOfT)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramArrayOfT != null)
    {
      int i = paramArrayOfT.length;
      for (int j = 0; j < i; j++) {
        localArrayList.add(paramArrayOfT[j]);
      }
    }
    return localArrayList;
  }
  
  public static <T> HashSet<T> arrayToSet(T[] paramArrayOfT)
  {
    HashSet localHashSet = new HashSet();
    if (paramArrayOfT != null)
    {
      int i = paramArrayOfT.length;
      for (int j = 0; j < i; j++) {
        localHashSet.add(paramArrayOfT[j]);
      }
    }
    return localHashSet;
  }
  
  public static Object getArrayComparator(final Object paramObject)
  {
    final int i = Array.getLength(paramObject);
    new Object()
    {
      public boolean equals(Object paramAnonymousObject)
      {
        boolean bool = true;
        if (paramAnonymousObject == this) {}
        for (;;)
        {
          return bool;
          if ((paramAnonymousObject == null) || (paramAnonymousObject.getClass() != ArrayBuilders.this))
          {
            bool = false;
          }
          else if (Array.getLength(paramAnonymousObject) != i)
          {
            bool = false;
          }
          else
          {
            int i = 0;
            label47:
            Object localObject1;
            Object localObject2;
            if (i < i)
            {
              localObject1 = Array.get(paramObject, i);
              localObject2 = Array.get(paramAnonymousObject, i);
              if (localObject1 != localObject2) {
                break label85;
              }
            }
            label85:
            while ((localObject1 == null) || (localObject1.equals(localObject2)))
            {
              i++;
              break label47;
              break;
            }
            bool = false;
          }
        }
      }
    };
  }
  
  public static <T> T[] insertInList(T[] paramArrayOfT, T paramT)
  {
    int i = paramArrayOfT.length;
    Object[] arrayOfObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), i + 1);
    if (i > 0) {
      System.arraycopy(paramArrayOfT, 0, arrayOfObject, 1, i);
    }
    arrayOfObject[0] = paramT;
    return arrayOfObject;
  }
  
  public static <T> T[] insertInListNoDup(T[] paramArrayOfT, T paramT)
  {
    int i = paramArrayOfT.length;
    int j = 0;
    Object localObject;
    if (j < i) {
      if (paramArrayOfT[j] == paramT) {
        if (j == 0) {
          localObject = paramArrayOfT;
        }
      }
    }
    for (;;)
    {
      return (T[])localObject;
      localObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), i);
      System.arraycopy(paramArrayOfT, 0, localObject, 1, j);
      localObject[0] = paramT;
      int k = j + 1;
      int m = i - k;
      if (m > 0)
      {
        System.arraycopy(paramArrayOfT, k, localObject, k, m);
        continue;
        j++;
        break;
        localObject = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), i + 1);
        if (i > 0) {
          System.arraycopy(paramArrayOfT, 0, localObject, 1, i);
        }
        localObject[0] = paramT;
      }
    }
  }
  
  public static <T> HashSet<T> setAndArray(Set<T> paramSet, T[] paramArrayOfT)
  {
    HashSet localHashSet = new HashSet();
    if (paramSet != null) {
      localHashSet.addAll(paramSet);
    }
    if (paramArrayOfT != null)
    {
      int i = paramArrayOfT.length;
      for (int j = 0; j < i; j++) {
        localHashSet.add(paramArrayOfT[j]);
      }
    }
    return localHashSet;
  }
  
  public BooleanBuilder getBooleanBuilder()
  {
    if (this._booleanBuilder == null) {
      this._booleanBuilder = new BooleanBuilder();
    }
    return this._booleanBuilder;
  }
  
  public ByteBuilder getByteBuilder()
  {
    if (this._byteBuilder == null) {
      this._byteBuilder = new ByteBuilder();
    }
    return this._byteBuilder;
  }
  
  public DoubleBuilder getDoubleBuilder()
  {
    if (this._doubleBuilder == null) {
      this._doubleBuilder = new DoubleBuilder();
    }
    return this._doubleBuilder;
  }
  
  public FloatBuilder getFloatBuilder()
  {
    if (this._floatBuilder == null) {
      this._floatBuilder = new FloatBuilder();
    }
    return this._floatBuilder;
  }
  
  public IntBuilder getIntBuilder()
  {
    if (this._intBuilder == null) {
      this._intBuilder = new IntBuilder();
    }
    return this._intBuilder;
  }
  
  public LongBuilder getLongBuilder()
  {
    if (this._longBuilder == null) {
      this._longBuilder = new LongBuilder();
    }
    return this._longBuilder;
  }
  
  public ShortBuilder getShortBuilder()
  {
    if (this._shortBuilder == null) {
      this._shortBuilder = new ShortBuilder();
    }
    return this._shortBuilder;
  }
  
  public static final class DoubleBuilder
    extends PrimitiveArrayBuilder<double[]>
  {
    public final double[] _constructArray(int paramInt)
    {
      return new double[paramInt];
    }
  }
  
  public static final class FloatBuilder
    extends PrimitiveArrayBuilder<float[]>
  {
    public final float[] _constructArray(int paramInt)
    {
      return new float[paramInt];
    }
  }
  
  public static final class LongBuilder
    extends PrimitiveArrayBuilder<long[]>
  {
    public final long[] _constructArray(int paramInt)
    {
      return new long[paramInt];
    }
  }
  
  public static final class IntBuilder
    extends PrimitiveArrayBuilder<int[]>
  {
    public final int[] _constructArray(int paramInt)
    {
      return new int[paramInt];
    }
  }
  
  public static final class ShortBuilder
    extends PrimitiveArrayBuilder<short[]>
  {
    public final short[] _constructArray(int paramInt)
    {
      return new short[paramInt];
    }
  }
  
  public static final class ByteBuilder
    extends PrimitiveArrayBuilder<byte[]>
  {
    public final byte[] _constructArray(int paramInt)
    {
      return new byte[paramInt];
    }
  }
  
  public static final class BooleanBuilder
    extends PrimitiveArrayBuilder<boolean[]>
  {
    public final boolean[] _constructArray(int paramInt)
    {
      return new boolean[paramInt];
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/ArrayBuilders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */