package android.support.v4.util;

import java.util.Map;

public class SimpleArrayMap<K, V>
{
  private static final int BASE_SIZE = 4;
  private static final int CACHE_SIZE = 10;
  private static final boolean DEBUG = false;
  private static final String TAG = "ArrayMap";
  static Object[] mBaseCache;
  static int mBaseCacheSize;
  static Object[] mTwiceBaseCache;
  static int mTwiceBaseCacheSize;
  Object[] mArray;
  int[] mHashes;
  int mSize;
  
  public SimpleArrayMap()
  {
    this.mHashes = ContainerHelpers.EMPTY_INTS;
    this.mArray = ContainerHelpers.EMPTY_OBJECTS;
    this.mSize = 0;
  }
  
  public SimpleArrayMap(int paramInt)
  {
    if (paramInt == 0)
    {
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
    }
    for (;;)
    {
      this.mSize = 0;
      return;
      allocArrays(paramInt);
    }
  }
  
  public SimpleArrayMap(SimpleArrayMap paramSimpleArrayMap)
  {
    this();
    if (paramSimpleArrayMap != null) {
      putAll(paramSimpleArrayMap);
    }
  }
  
  private void allocArrays(int paramInt)
  {
    if (paramInt == 8) {}
    for (;;)
    {
      try
      {
        if (mTwiceBaseCache != null)
        {
          Object[] arrayOfObject2 = mTwiceBaseCache;
          this.mArray = arrayOfObject2;
          mTwiceBaseCache = (Object[])arrayOfObject2[0];
          this.mHashes = ((int[])arrayOfObject2[1]);
          arrayOfObject2[1] = null;
          arrayOfObject2[0] = null;
          mTwiceBaseCacheSize = -1 + mTwiceBaseCacheSize;
        }
        else
        {
          this.mHashes = new int[paramInt];
          this.mArray = new Object[paramInt << 1];
        }
      }
      finally {}
      if (paramInt == 4) {
        try
        {
          if (mBaseCache != null)
          {
            Object[] arrayOfObject1 = mBaseCache;
            this.mArray = arrayOfObject1;
            mBaseCache = (Object[])arrayOfObject1[0];
            this.mHashes = ((int[])arrayOfObject1[1]);
            arrayOfObject1[1] = null;
            arrayOfObject1[0] = null;
            mBaseCacheSize = -1 + mBaseCacheSize;
          }
        }
        finally
        {
          throw ((Throwable)localObject1);
        }
      }
    }
  }
  
  private static void freeArrays(int[] paramArrayOfInt, Object[] paramArrayOfObject, int paramInt)
  {
    if (paramArrayOfInt.length == 8) {
      try
      {
        if (mTwiceBaseCacheSize < 10)
        {
          paramArrayOfObject[0] = mTwiceBaseCache;
          paramArrayOfObject[1] = paramArrayOfInt;
          for (int j = -1 + (paramInt << 1); j >= 2; j--) {
            paramArrayOfObject[j] = null;
          }
          mTwiceBaseCache = paramArrayOfObject;
          mTwiceBaseCacheSize = 1 + mTwiceBaseCacheSize;
        }
        return;
      }
      finally
      {
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
    } else if (paramArrayOfInt.length == 4) {
      try
      {
        if (mBaseCacheSize < 10)
        {
          paramArrayOfObject[0] = mBaseCache;
          paramArrayOfObject[1] = paramArrayOfInt;
          for (int i = -1 + (paramInt << 1); i >= 2; i--) {
            paramArrayOfObject[i] = null;
          }
          mBaseCache = paramArrayOfObject;
          mBaseCacheSize = 1 + mBaseCacheSize;
        }
      }
      finally
      {
        localObject1 = finally;
        throw ((Throwable)localObject1);
      }
    }
  }
  
  public void clear()
  {
    if (this.mSize != 0)
    {
      freeArrays(this.mHashes, this.mArray, this.mSize);
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
      this.mSize = 0;
    }
  }
  
  public boolean containsKey(Object paramObject)
  {
    if (indexOfKey(paramObject) >= 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean containsValue(Object paramObject)
  {
    if (indexOfValue(paramObject) >= 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void ensureCapacity(int paramInt)
  {
    if (this.mHashes.length < paramInt)
    {
      int[] arrayOfInt = this.mHashes;
      Object[] arrayOfObject = this.mArray;
      allocArrays(paramInt);
      if (this.mSize > 0)
      {
        System.arraycopy(arrayOfInt, 0, this.mHashes, 0, this.mSize);
        System.arraycopy(arrayOfObject, 0, this.mArray, 0, this.mSize << 1);
      }
      freeArrays(arrayOfInt, arrayOfObject, this.mSize);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool1;
      if ((paramObject instanceof Map))
      {
        Map localMap = (Map)paramObject;
        if (size() != localMap.size())
        {
          bool1 = false;
        }
        else
        {
          int i = 0;
          try
          {
            while (i < this.mSize)
            {
              Object localObject1 = keyAt(i);
              Object localObject2 = valueAt(i);
              Object localObject3 = localMap.get(localObject1);
              if (localObject2 == null)
              {
                if (localObject3 != null) {
                  break label145;
                }
                if (!localMap.containsKey(localObject1)) {
                  break label145;
                }
              }
              else
              {
                boolean bool2 = localObject2.equals(localObject3);
                if (!bool2)
                {
                  bool1 = false;
                  break;
                }
              }
              i++;
            }
          }
          catch (NullPointerException localNullPointerException)
          {
            bool1 = false;
          }
          catch (ClassCastException localClassCastException)
          {
            bool1 = false;
          }
        }
      }
      else
      {
        bool1 = false;
        continue;
        label145:
        bool1 = false;
      }
    }
  }
  
  public V get(Object paramObject)
  {
    int i = indexOfKey(paramObject);
    if (i >= 0) {}
    for (Object localObject = this.mArray[(1 + (i << 1))];; localObject = null) {
      return (V)localObject;
    }
  }
  
  public int hashCode()
  {
    int[] arrayOfInt = this.mHashes;
    Object[] arrayOfObject = this.mArray;
    int i = 0;
    int j = 0;
    int k = 1;
    int m = this.mSize;
    if (j < m)
    {
      Object localObject = arrayOfObject[k];
      int n = arrayOfInt[j];
      if (localObject == null) {}
      for (int i1 = 0;; i1 = localObject.hashCode())
      {
        i += (i1 ^ n);
        j++;
        k += 2;
        break;
      }
    }
    return i;
  }
  
  int indexOf(Object paramObject, int paramInt)
  {
    int i = this.mSize;
    int j;
    if (i == 0) {
      j = -1;
    }
    for (;;)
    {
      return j;
      j = ContainerHelpers.binarySearch(this.mHashes, i, paramInt);
      if ((j >= 0) && (!paramObject.equals(this.mArray[(j << 1)])))
      {
        for (int k = j + 1;; k++)
        {
          if ((k >= i) || (this.mHashes[k] != paramInt)) {
            break label100;
          }
          if (paramObject.equals(this.mArray[(k << 1)]))
          {
            j = k;
            break;
          }
        }
        label100:
        for (int m = j - 1;; m--)
        {
          if ((m < 0) || (this.mHashes[m] != paramInt)) {
            break label151;
          }
          if (paramObject.equals(this.mArray[(m << 1)]))
          {
            j = m;
            break;
          }
        }
        label151:
        j = k ^ 0xFFFFFFFF;
      }
    }
  }
  
  public int indexOfKey(Object paramObject)
  {
    if (paramObject == null) {}
    for (int i = indexOfNull();; i = indexOf(paramObject, paramObject.hashCode())) {
      return i;
    }
  }
  
  int indexOfNull()
  {
    int i = this.mSize;
    int j;
    if (i == 0) {
      j = -1;
    }
    for (;;)
    {
      return j;
      j = ContainerHelpers.binarySearch(this.mHashes, i, 0);
      if ((j >= 0) && (this.mArray[(j << 1)] != null))
      {
        for (int k = j + 1;; k++)
        {
          if ((k >= i) || (this.mHashes[k] != 0)) {
            break label79;
          }
          if (this.mArray[(k << 1)] == null)
          {
            j = k;
            break;
          }
        }
        label79:
        for (int m = j - 1;; m--)
        {
          if ((m < 0) || (this.mHashes[m] != 0)) {
            break label123;
          }
          if (this.mArray[(m << 1)] == null)
          {
            j = m;
            break;
          }
        }
        label123:
        j = k ^ 0xFFFFFFFF;
      }
    }
  }
  
  int indexOfValue(Object paramObject)
  {
    int i = 2 * this.mSize;
    Object[] arrayOfObject = this.mArray;
    int m;
    int k;
    if (paramObject == null)
    {
      m = 1;
      if (m >= i) {
        break label82;
      }
      if (arrayOfObject[m] == null) {
        k = m >> 1;
      }
    }
    for (;;)
    {
      return k;
      m += 2;
      break;
      for (int j = 1;; j += 2)
      {
        if (j >= i) {
          break label82;
        }
        if (paramObject.equals(arrayOfObject[j]))
        {
          k = j >> 1;
          break;
        }
      }
      label82:
      k = -1;
    }
  }
  
  public boolean isEmpty()
  {
    if (this.mSize <= 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public K keyAt(int paramInt)
  {
    return (K)this.mArray[(paramInt << 1)];
  }
  
  public V put(K paramK, V paramV)
  {
    int i = 8;
    int j;
    if (paramK == null) {
      j = 0;
    }
    Object localObject;
    for (int k = indexOfNull(); k >= 0; k = indexOf(paramK, j))
    {
      int n = 1 + (k << 1);
      localObject = this.mArray[n];
      this.mArray[n] = paramV;
      return (V)localObject;
      j = paramK.hashCode();
    }
    int m = k ^ 0xFFFFFFFF;
    if (this.mSize >= this.mHashes.length)
    {
      if (this.mSize < i) {
        break label280;
      }
      i = this.mSize + (this.mSize >> 1);
    }
    for (;;)
    {
      int[] arrayOfInt = this.mHashes;
      Object[] arrayOfObject = this.mArray;
      allocArrays(i);
      if (this.mHashes.length > 0)
      {
        System.arraycopy(arrayOfInt, 0, this.mHashes, 0, arrayOfInt.length);
        System.arraycopy(arrayOfObject, 0, this.mArray, 0, arrayOfObject.length);
      }
      freeArrays(arrayOfInt, arrayOfObject, this.mSize);
      if (m < this.mSize)
      {
        System.arraycopy(this.mHashes, m, this.mHashes, m + 1, this.mSize - m);
        System.arraycopy(this.mArray, m << 1, this.mArray, m + 1 << 1, this.mSize - m << 1);
      }
      this.mHashes[m] = j;
      this.mArray[(m << 1)] = paramK;
      this.mArray[(1 + (m << 1))] = paramV;
      this.mSize = (1 + this.mSize);
      localObject = null;
      break;
      label280:
      if (this.mSize < 4) {
        i = 4;
      }
    }
  }
  
  public void putAll(SimpleArrayMap<? extends K, ? extends V> paramSimpleArrayMap)
  {
    int i = paramSimpleArrayMap.mSize;
    ensureCapacity(i + this.mSize);
    if (this.mSize == 0) {
      if (i > 0)
      {
        System.arraycopy(paramSimpleArrayMap.mHashes, 0, this.mHashes, 0, i);
        System.arraycopy(paramSimpleArrayMap.mArray, 0, this.mArray, 0, i << 1);
        this.mSize = i;
      }
    }
    for (;;)
    {
      return;
      for (int j = 0; j < i; j++) {
        put(paramSimpleArrayMap.keyAt(j), paramSimpleArrayMap.valueAt(j));
      }
    }
  }
  
  public V remove(Object paramObject)
  {
    int i = indexOfKey(paramObject);
    if (i >= 0) {}
    for (Object localObject = removeAt(i);; localObject = null) {
      return (V)localObject;
    }
  }
  
  public V removeAt(int paramInt)
  {
    int i = 8;
    Object localObject = this.mArray[(1 + (paramInt << 1))];
    if (this.mSize <= 1)
    {
      freeArrays(this.mHashes, this.mArray, this.mSize);
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
      this.mSize = 0;
    }
    for (;;)
    {
      return (V)localObject;
      if ((this.mHashes.length > i) && (this.mSize < this.mHashes.length / 3))
      {
        if (this.mSize > i) {
          i = this.mSize + (this.mSize >> 1);
        }
        int[] arrayOfInt = this.mHashes;
        Object[] arrayOfObject = this.mArray;
        allocArrays(i);
        this.mSize = (-1 + this.mSize);
        if (paramInt > 0)
        {
          System.arraycopy(arrayOfInt, 0, this.mHashes, 0, paramInt);
          System.arraycopy(arrayOfObject, 0, this.mArray, 0, paramInt << 1);
        }
        if (paramInt < this.mSize)
        {
          System.arraycopy(arrayOfInt, paramInt + 1, this.mHashes, paramInt, this.mSize - paramInt);
          System.arraycopy(arrayOfObject, paramInt + 1 << 1, this.mArray, paramInt << 1, this.mSize - paramInt << 1);
        }
      }
      else
      {
        this.mSize = (-1 + this.mSize);
        if (paramInt < this.mSize)
        {
          System.arraycopy(this.mHashes, paramInt + 1, this.mHashes, paramInt, this.mSize - paramInt);
          System.arraycopy(this.mArray, paramInt + 1 << 1, this.mArray, paramInt << 1, this.mSize - paramInt << 1);
        }
        this.mArray[(this.mSize << 1)] = null;
        this.mArray[(1 + (this.mSize << 1))] = null;
      }
    }
  }
  
  public V setValueAt(int paramInt, V paramV)
  {
    int i = 1 + (paramInt << 1);
    Object localObject = this.mArray[i];
    this.mArray[i] = paramV;
    return (V)localObject;
  }
  
  public int size()
  {
    return this.mSize;
  }
  
  public String toString()
  {
    if (isEmpty()) {}
    StringBuilder localStringBuilder;
    for (String str = "{}";; str = localStringBuilder.toString())
    {
      return str;
      localStringBuilder = new StringBuilder(28 * this.mSize);
      localStringBuilder.append('{');
      int i = 0;
      if (i < this.mSize)
      {
        if (i > 0) {
          localStringBuilder.append(", ");
        }
        Object localObject1 = keyAt(i);
        if (localObject1 != this)
        {
          localStringBuilder.append(localObject1);
          label77:
          localStringBuilder.append('=');
          Object localObject2 = valueAt(i);
          if (localObject2 == this) {
            break label120;
          }
          localStringBuilder.append(localObject2);
        }
        for (;;)
        {
          i++;
          break;
          localStringBuilder.append("(this Map)");
          break label77;
          label120:
          localStringBuilder.append("(this Map)");
        }
      }
      localStringBuilder.append('}');
    }
  }
  
  public V valueAt(int paramInt)
  {
    return (V)this.mArray[(1 + (paramInt << 1))];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/util/SimpleArrayMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */