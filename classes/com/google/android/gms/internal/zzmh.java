package com.google.android.gms.internal;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

abstract class zzmh<K, V>
{
  zzmh<K, V>.zzb zzagI;
  zzmh<K, V>.zzc zzagJ;
  zzmh<K, V>.zze zzagK;
  
  public static <K, V> boolean containsAllHelper(Map<K, V> paramMap, Collection<?> paramCollection)
  {
    Iterator localIterator = paramCollection.iterator();
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
    } while (paramMap.containsKey(localIterator.next()));
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  public static <T> boolean equalsSetHelper(Set<T> paramSet, Object paramObject)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramSet == paramObject) {
      bool2 = bool1;
    }
    for (;;)
    {
      return bool2;
      if ((paramObject instanceof Set))
      {
        Set localSet = (Set)paramObject;
        try
        {
          if (paramSet.size() == localSet.size())
          {
            boolean bool3 = paramSet.containsAll(localSet);
            if (!bool3) {}
          }
          for (;;)
          {
            bool2 = bool1;
            break;
            bool1 = false;
          }
        }
        catch (ClassCastException localClassCastException) {}catch (NullPointerException localNullPointerException) {}
      }
    }
  }
  
  public static <K, V> boolean removeAllHelper(Map<K, V> paramMap, Collection<?> paramCollection)
  {
    int i = paramMap.size();
    Iterator localIterator = paramCollection.iterator();
    while (localIterator.hasNext()) {
      paramMap.remove(localIterator.next());
    }
    if (i != paramMap.size()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static <K, V> boolean retainAllHelper(Map<K, V> paramMap, Collection<?> paramCollection)
  {
    int i = paramMap.size();
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext()) {
      if (!paramCollection.contains(localIterator.next())) {
        localIterator.remove();
      }
    }
    if (i != paramMap.size()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected abstract void colClear();
  
  protected abstract Object colGetEntry(int paramInt1, int paramInt2);
  
  protected abstract Map<K, V> colGetMap();
  
  protected abstract int colGetSize();
  
  protected abstract int colIndexOfKey(Object paramObject);
  
  protected abstract int colIndexOfValue(Object paramObject);
  
  protected abstract void colPut(K paramK, V paramV);
  
  protected abstract void colRemoveAt(int paramInt);
  
  protected abstract V colSetValue(int paramInt, V paramV);
  
  public Set<Map.Entry<K, V>> getEntrySet()
  {
    if (this.zzagI == null) {
      this.zzagI = new zzb();
    }
    return this.zzagI;
  }
  
  public Set<K> getKeySet()
  {
    if (this.zzagJ == null) {
      this.zzagJ = new zzc();
    }
    return this.zzagJ;
  }
  
  public Collection<V> getValues()
  {
    if (this.zzagK == null) {
      this.zzagK = new zze();
    }
    return this.zzagK;
  }
  
  public Object[] toArrayHelper(int paramInt)
  {
    int i = colGetSize();
    Object[] arrayOfObject = new Object[i];
    for (int j = 0; j < i; j++) {
      arrayOfObject[j] = colGetEntry(j, paramInt);
    }
    return arrayOfObject;
  }
  
  public <T> T[] toArrayHelper(T[] paramArrayOfT, int paramInt)
  {
    int i = colGetSize();
    if (paramArrayOfT.length < i) {
      paramArrayOfT = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), i);
    }
    for (int j = 0; j < i; j++) {
      paramArrayOfT[j] = colGetEntry(j, paramInt);
    }
    if (paramArrayOfT.length > i) {
      paramArrayOfT[i] = null;
    }
    return paramArrayOfT;
  }
  
  final class zze
    implements Collection<V>
  {
    zze() {}
    
    public boolean add(V paramV)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends V> paramCollection)
    {
      throw new UnsupportedOperationException();
    }
    
    public void clear()
    {
      zzmh.this.colClear();
    }
    
    public boolean contains(Object paramObject)
    {
      if (zzmh.this.colIndexOfValue(paramObject) >= 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean containsAll(Collection<?> paramCollection)
    {
      Iterator localIterator = paramCollection.iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
      } while (contains(localIterator.next()));
      for (boolean bool = false;; bool = true) {
        return bool;
      }
    }
    
    public boolean isEmpty()
    {
      if (zzmh.this.colGetSize() == 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public Iterator<V> iterator()
    {
      return new zzmh.zza(zzmh.this, 1);
    }
    
    public boolean remove(Object paramObject)
    {
      int i = zzmh.this.colIndexOfValue(paramObject);
      if (i >= 0) {
        zzmh.this.colRemoveAt(i);
      }
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean removeAll(Collection<?> paramCollection)
    {
      int i = 0;
      int j = zzmh.this.colGetSize();
      boolean bool = false;
      while (i < j)
      {
        if (paramCollection.contains(zzmh.this.colGetEntry(i, 1)))
        {
          zzmh.this.colRemoveAt(i);
          i--;
          j--;
          bool = true;
        }
        i++;
      }
      return bool;
    }
    
    public boolean retainAll(Collection<?> paramCollection)
    {
      int i = 0;
      int j = zzmh.this.colGetSize();
      boolean bool = false;
      while (i < j)
      {
        if (!paramCollection.contains(zzmh.this.colGetEntry(i, 1)))
        {
          zzmh.this.colRemoveAt(i);
          i--;
          j--;
          bool = true;
        }
        i++;
      }
      return bool;
    }
    
    public int size()
    {
      return zzmh.this.colGetSize();
    }
    
    public Object[] toArray()
    {
      return zzmh.this.toArrayHelper(1);
    }
    
    public <T> T[] toArray(T[] paramArrayOfT)
    {
      return zzmh.this.toArrayHelper(paramArrayOfT, 1);
    }
  }
  
  final class zzc
    implements Set<K>
  {
    zzc() {}
    
    public boolean add(K paramK)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends K> paramCollection)
    {
      throw new UnsupportedOperationException();
    }
    
    public void clear()
    {
      zzmh.this.colClear();
    }
    
    public boolean contains(Object paramObject)
    {
      if (zzmh.this.colIndexOfKey(paramObject) >= 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean containsAll(Collection<?> paramCollection)
    {
      return zzmh.containsAllHelper(zzmh.this.colGetMap(), paramCollection);
    }
    
    public boolean equals(Object paramObject)
    {
      return zzmh.equalsSetHelper(this, paramObject);
    }
    
    public int hashCode()
    {
      int i = -1 + zzmh.this.colGetSize();
      int j = 0;
      if (i >= 0)
      {
        Object localObject = zzmh.this.colGetEntry(i, 0);
        if (localObject == null) {}
        for (int k = 0;; k = localObject.hashCode())
        {
          j += k;
          i--;
          break;
        }
      }
      return j;
    }
    
    public boolean isEmpty()
    {
      if (zzmh.this.colGetSize() == 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public Iterator<K> iterator()
    {
      return new zzmh.zza(zzmh.this, 0);
    }
    
    public boolean remove(Object paramObject)
    {
      int i = zzmh.this.colIndexOfKey(paramObject);
      if (i >= 0) {
        zzmh.this.colRemoveAt(i);
      }
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean removeAll(Collection<?> paramCollection)
    {
      return zzmh.removeAllHelper(zzmh.this.colGetMap(), paramCollection);
    }
    
    public boolean retainAll(Collection<?> paramCollection)
    {
      return zzmh.retainAllHelper(zzmh.this.colGetMap(), paramCollection);
    }
    
    public int size()
    {
      return zzmh.this.colGetSize();
    }
    
    public Object[] toArray()
    {
      return zzmh.this.toArrayHelper(0);
    }
    
    public <T> T[] toArray(T[] paramArrayOfT)
    {
      return zzmh.this.toArrayHelper(paramArrayOfT, 0);
    }
  }
  
  final class zzb
    implements Set<Map.Entry<K, V>>
  {
    zzb() {}
    
    public boolean add(Map.Entry<K, V> paramEntry)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends Map.Entry<K, V>> paramCollection)
    {
      int i = zzmh.this.colGetSize();
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        zzmh.this.colPut(localEntry.getKey(), localEntry.getValue());
      }
      if (i != zzmh.this.colGetSize()) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public void clear()
    {
      zzmh.this.colClear();
    }
    
    public boolean contains(Object paramObject)
    {
      boolean bool = false;
      if (!(paramObject instanceof Map.Entry)) {}
      for (;;)
      {
        return bool;
        Map.Entry localEntry = (Map.Entry)paramObject;
        int i = zzmh.this.colIndexOfKey(localEntry.getKey());
        if (i >= 0) {
          bool = zzmf.equal(zzmh.this.colGetEntry(i, 1), localEntry.getValue());
        }
      }
    }
    
    public boolean containsAll(Collection<?> paramCollection)
    {
      Iterator localIterator = paramCollection.iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
      } while (contains(localIterator.next()));
      for (boolean bool = false;; bool = true) {
        return bool;
      }
    }
    
    public boolean equals(Object paramObject)
    {
      return zzmh.equalsSetHelper(this, paramObject);
    }
    
    public int hashCode()
    {
      int i = -1 + zzmh.this.colGetSize();
      int j = 0;
      if (i >= 0)
      {
        Object localObject1 = zzmh.this.colGetEntry(i, 0);
        Object localObject2 = zzmh.this.colGetEntry(i, 1);
        int k;
        if (localObject1 == null)
        {
          k = 0;
          label45:
          if (localObject2 != null) {
            break label80;
          }
        }
        label80:
        for (int m = 0;; m = localObject2.hashCode())
        {
          int n = j + (m ^ k);
          i--;
          j = n;
          break;
          k = localObject1.hashCode();
          break label45;
        }
      }
      return j;
    }
    
    public boolean isEmpty()
    {
      if (zzmh.this.colGetSize() == 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public Iterator<Map.Entry<K, V>> iterator()
    {
      return new zzmh.zzd(zzmh.this);
    }
    
    public boolean remove(Object paramObject)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean removeAll(Collection<?> paramCollection)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean retainAll(Collection<?> paramCollection)
    {
      throw new UnsupportedOperationException();
    }
    
    public int size()
    {
      return zzmh.this.colGetSize();
    }
    
    public Object[] toArray()
    {
      throw new UnsupportedOperationException();
    }
    
    public <T> T[] toArray(T[] paramArrayOfT)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  final class zzd
    implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V>
  {
    int mEnd = -1 + zzmh.this.colGetSize();
    boolean mEntryValid = false;
    int mIndex = -1;
    
    zzd() {}
    
    public final boolean equals(Object paramObject)
    {
      int i = 1;
      boolean bool = false;
      if (!this.mEntryValid) {
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
      }
      if (!(paramObject instanceof Map.Entry)) {
        return bool;
      }
      Map.Entry localEntry = (Map.Entry)paramObject;
      if ((zzmf.equal(localEntry.getKey(), zzmh.this.colGetEntry(this.mIndex, 0))) && (zzmf.equal(localEntry.getValue(), zzmh.this.colGetEntry(this.mIndex, i)))) {}
      for (;;)
      {
        bool = i;
        break;
        i = 0;
      }
    }
    
    public K getKey()
    {
      if (!this.mEntryValid) {
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
      }
      return (K)zzmh.this.colGetEntry(this.mIndex, 0);
    }
    
    public V getValue()
    {
      if (!this.mEntryValid) {
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
      }
      return (V)zzmh.this.colGetEntry(this.mIndex, 1);
    }
    
    public boolean hasNext()
    {
      if (this.mIndex < this.mEnd) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public final int hashCode()
    {
      int i = 0;
      if (!this.mEntryValid) {
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
      }
      Object localObject1 = zzmh.this.colGetEntry(this.mIndex, 0);
      Object localObject2 = zzmh.this.colGetEntry(this.mIndex, 1);
      int j;
      if (localObject1 == null)
      {
        j = 0;
        if (localObject2 != null) {
          break label70;
        }
      }
      for (;;)
      {
        return i ^ j;
        j = localObject1.hashCode();
        break;
        label70:
        i = localObject2.hashCode();
      }
    }
    
    public Map.Entry<K, V> next()
    {
      this.mIndex = (1 + this.mIndex);
      this.mEntryValid = true;
      return this;
    }
    
    public void remove()
    {
      if (!this.mEntryValid) {
        throw new IllegalStateException();
      }
      zzmh.this.colRemoveAt(this.mIndex);
      this.mIndex = (-1 + this.mIndex);
      this.mEnd = (-1 + this.mEnd);
      this.mEntryValid = false;
    }
    
    public V setValue(V paramV)
    {
      if (!this.mEntryValid) {
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
      }
      return (V)zzmh.this.colSetValue(this.mIndex, paramV);
    }
    
    public final String toString()
    {
      return getKey() + "=" + getValue();
    }
  }
  
  final class zza<T>
    implements Iterator<T>
  {
    boolean mCanRemove = false;
    int mIndex;
    final int mOffset;
    int mSize;
    
    zza(int paramInt)
    {
      this.mOffset = paramInt;
      this.mSize = zzmh.this.colGetSize();
    }
    
    public boolean hasNext()
    {
      if (this.mIndex < this.mSize) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public T next()
    {
      Object localObject = zzmh.this.colGetEntry(this.mIndex, this.mOffset);
      this.mIndex = (1 + this.mIndex);
      this.mCanRemove = true;
      return (T)localObject;
    }
    
    public void remove()
    {
      if (!this.mCanRemove) {
        throw new IllegalStateException();
      }
      this.mIndex = (-1 + this.mIndex);
      this.mSize = (-1 + this.mSize);
      this.mCanRemove = false;
      zzmh.this.colRemoveAt(this.mIndex);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzmh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */