package com.fasterxml.jackson.databind.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class CompactStringObjectMap
  implements Serializable
{
  private static final CompactStringObjectMap EMPTY = new CompactStringObjectMap(1, 0, new Object[4]);
  private static final long serialVersionUID = 1L;
  private final Object[] _hashArea;
  private final int _hashMask;
  private final int _spillCount;
  
  private CompactStringObjectMap(int paramInt1, int paramInt2, Object[] paramArrayOfObject)
  {
    this._hashMask = paramInt1;
    this._spillCount = paramInt2;
    this._hashArea = paramArrayOfObject;
  }
  
  private final Object _find2(String paramString, int paramInt, Object paramObject)
  {
    Object localObject1 = null;
    if (paramObject == null) {}
    label134:
    for (;;)
    {
      return localObject1;
      int i = 1 + this._hashMask;
      int j = i + (paramInt >> 1) << 1;
      Object localObject2 = this._hashArea[j];
      if (paramString.equals(localObject2))
      {
        localObject1 = this._hashArea[(j + 1)];
      }
      else if (localObject2 != null)
      {
        int k = i + (i >> 1) << 1;
        int m = k + this._spillCount;
        for (;;)
        {
          if (k >= m) {
            break label134;
          }
          Object localObject3 = this._hashArea[k];
          if ((localObject3 == paramString) || (paramString.equals(localObject3)))
          {
            localObject1 = this._hashArea[(k + 1)];
            break;
          }
          k += 2;
        }
      }
    }
  }
  
  public static <T> CompactStringObjectMap construct(Map<String, T> paramMap)
  {
    if (paramMap.isEmpty()) {}
    int j;
    Object[] arrayOfObject;
    int k;
    for (CompactStringObjectMap localCompactStringObjectMap = EMPTY;; localCompactStringObjectMap = new CompactStringObjectMap(j, k, arrayOfObject))
    {
      return localCompactStringObjectMap;
      int i = findSize(paramMap.size());
      j = i - 1;
      arrayOfObject = new Object[2 * (i + (i >> 1))];
      k = 0;
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        String str = (String)localEntry.getKey();
        int m = j & str.hashCode();
        int n = m + m;
        if (arrayOfObject[n] != null)
        {
          n = i + (m >> 1) << 1;
          if (arrayOfObject[n] != null)
          {
            n = k + (i + (i >> 1) << 1);
            k += 2;
            if (n >= arrayOfObject.length) {
              arrayOfObject = Arrays.copyOf(arrayOfObject, 4 + arrayOfObject.length);
            }
          }
        }
        arrayOfObject[n] = str;
        arrayOfObject[(n + 1)] = localEntry.getValue();
      }
    }
  }
  
  private static final int findSize(int paramInt)
  {
    int j;
    if (paramInt <= 5) {
      j = 8;
    }
    for (;;)
    {
      return j;
      if (paramInt <= 12)
      {
        j = 16;
      }
      else
      {
        int i = paramInt + (paramInt >> 2);
        j = 32;
        while (j < i) {
          j += j;
        }
      }
    }
  }
  
  public Object find(String paramString)
  {
    int i = paramString.hashCode() & this._hashMask;
    int j = i << 1;
    Object localObject1 = this._hashArea[j];
    if ((localObject1 == paramString) || (paramString.equals(localObject1))) {}
    for (Object localObject2 = this._hashArea[(j + 1)];; localObject2 = _find2(paramString, i, localObject1)) {
      return localObject2;
    }
  }
  
  public List<String> keys()
  {
    int i = this._hashArea.length;
    ArrayList localArrayList = new ArrayList(i >> 2);
    for (int j = 0; j < i; j += 2)
    {
      Object localObject = this._hashArea[j];
      if (localObject != null) {
        localArrayList.add((String)localObject);
      }
    }
    return localArrayList;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/CompactStringObjectMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */