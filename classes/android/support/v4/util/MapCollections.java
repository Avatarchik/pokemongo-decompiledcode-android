package android.support.v4.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

abstract class MapCollections<K, V>
{
  MapCollections<K, V>.EntrySet mEntrySet;
  MapCollections<K, V>.KeySet mKeySet;
  MapCollections<K, V>.ValuesCollection mValues;
  
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
        catch (NullPointerException localNullPointerException) {}catch (ClassCastException localClassCastException) {}
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
    if (this.mEntrySet == null) {
      this.mEntrySet = new EntrySet();
    }
    return this.mEntrySet;
  }
  
  public Set<K> getKeySet()
  {
    if (this.mKeySet == null) {
      this.mKeySet = new KeySet();
    }
    return this.mKeySet;
  }
  
  public Collection<V> getValues()
  {
    if (this.mValues == null) {
      this.mValues = new ValuesCollection();
    }
    return this.mValues;
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
  
  final class ValuesCollection
    implements Collection<V>
  {
    ValuesCollection() {}
    
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
      MapCollections.this.colClear();
    }
    
    public boolean contains(Object paramObject)
    {
      if (MapCollections.this.colIndexOfValue(paramObject) >= 0) {}
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
      if (MapCollections.this.colGetSize() == 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public Iterator<V> iterator()
    {
      return new MapCollections.ArrayIterator(MapCollections.this, 1);
    }
    
    public boolean remove(Object paramObject)
    {
      int i = MapCollections.this.colIndexOfValue(paramObject);
      if (i >= 0) {
        MapCollections.this.colRemoveAt(i);
      }
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean removeAll(Collection<?> paramCollection)
    {
      int i = MapCollections.this.colGetSize();
      boolean bool = false;
      for (int j = 0; j < i; j++) {
        if (paramCollection.contains(MapCollections.this.colGetEntry(j, 1)))
        {
          MapCollections.this.colRemoveAt(j);
          j--;
          i--;
          bool = true;
        }
      }
      return bool;
    }
    
    public boolean retainAll(Collection<?> paramCollection)
    {
      int i = MapCollections.this.colGetSize();
      boolean bool = false;
      for (int j = 0; j < i; j++) {
        if (!paramCollection.contains(MapCollections.this.colGetEntry(j, 1)))
        {
          MapCollections.this.colRemoveAt(j);
          j--;
          i--;
          bool = true;
        }
      }
      return bool;
    }
    
    public int size()
    {
      return MapCollections.this.colGetSize();
    }
    
    public Object[] toArray()
    {
      return MapCollections.this.toArrayHelper(1);
    }
    
    public <T> T[] toArray(T[] paramArrayOfT)
    {
      return MapCollections.this.toArrayHelper(paramArrayOfT, 1);
    }
  }
  
  final class KeySet
    implements Set<K>
  {
    KeySet() {}
    
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
      MapCollections.this.colClear();
    }
    
    public boolean contains(Object paramObject)
    {
      if (MapCollections.this.colIndexOfKey(paramObject) >= 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean containsAll(Collection<?> paramCollection)
    {
      return MapCollections.containsAllHelper(MapCollections.this.colGetMap(), paramCollection);
    }
    
    public boolean equals(Object paramObject)
    {
      return MapCollections.equalsSetHelper(this, paramObject);
    }
    
    public int hashCode()
    {
      int i = 0;
      int j = -1 + MapCollections.this.colGetSize();
      if (j >= 0)
      {
        Object localObject = MapCollections.this.colGetEntry(j, 0);
        if (localObject == null) {}
        for (int k = 0;; k = localObject.hashCode())
        {
          i += k;
          j--;
          break;
        }
      }
      return i;
    }
    
    public boolean isEmpty()
    {
      if (MapCollections.this.colGetSize() == 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public Iterator<K> iterator()
    {
      return new MapCollections.ArrayIterator(MapCollections.this, 0);
    }
    
    public boolean remove(Object paramObject)
    {
      int i = MapCollections.this.colIndexOfKey(paramObject);
      if (i >= 0) {
        MapCollections.this.colRemoveAt(i);
      }
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean removeAll(Collection<?> paramCollection)
    {
      return MapCollections.removeAllHelper(MapCollections.this.colGetMap(), paramCollection);
    }
    
    public boolean retainAll(Collection<?> paramCollection)
    {
      return MapCollections.retainAllHelper(MapCollections.this.colGetMap(), paramCollection);
    }
    
    public int size()
    {
      return MapCollections.this.colGetSize();
    }
    
    public Object[] toArray()
    {
      return MapCollections.this.toArrayHelper(0);
    }
    
    public <T> T[] toArray(T[] paramArrayOfT)
    {
      return MapCollections.this.toArrayHelper(paramArrayOfT, 0);
    }
  }
  
  final class EntrySet
    implements Set<Map.Entry<K, V>>
  {
    EntrySet() {}
    
    public boolean add(Map.Entry<K, V> paramEntry)
    {
      throw new UnsupportedOperationException();
    }
    
    public boolean addAll(Collection<? extends Map.Entry<K, V>> paramCollection)
    {
      int i = MapCollections.this.colGetSize();
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        MapCollections.this.colPut(localEntry.getKey(), localEntry.getValue());
      }
      if (i != MapCollections.this.colGetSize()) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public void clear()
    {
      MapCollections.this.colClear();
    }
    
    public boolean contains(Object paramObject)
    {
      boolean bool = false;
      if (!(paramObject instanceof Map.Entry)) {}
      for (;;)
      {
        return bool;
        Map.Entry localEntry = (Map.Entry)paramObject;
        int i = MapCollections.this.colIndexOfKey(localEntry.getKey());
        if (i >= 0) {
          bool = ContainerHelpers.equal(MapCollections.this.colGetEntry(i, 1), localEntry.getValue());
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
      return MapCollections.equalsSetHelper(this, paramObject);
    }
    
    public int hashCode()
    {
      int i = 0;
      int j = -1 + MapCollections.this.colGetSize();
      if (j >= 0)
      {
        Object localObject1 = MapCollections.this.colGetEntry(j, 0);
        Object localObject2 = MapCollections.this.colGetEntry(j, 1);
        int k;
        if (localObject1 == null)
        {
          k = 0;
          label45:
          if (localObject2 != null) {
            break label76;
          }
        }
        label76:
        for (int m = 0;; m = localObject2.hashCode())
        {
          i += (m ^ k);
          j--;
          break;
          k = localObject1.hashCode();
          break label45;
        }
      }
      return i;
    }
    
    public boolean isEmpty()
    {
      if (MapCollections.this.colGetSize() == 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public Iterator<Map.Entry<K, V>> iterator()
    {
      return new MapCollections.MapIterator(MapCollections.this);
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
      return MapCollections.this.colGetSize();
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
  
  final class MapIterator
    implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V>
  {
    int mEnd = -1 + MapCollections.this.colGetSize();
    boolean mEntryValid = false;
    int mIndex = -1;
    
    MapIterator() {}
    
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
      if ((ContainerHelpers.equal(localEntry.getKey(), MapCollections.this.colGetEntry(this.mIndex, 0))) && (ContainerHelpers.equal(localEntry.getValue(), MapCollections.this.colGetEntry(this.mIndex, i)))) {}
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
      return (K)MapCollections.this.colGetEntry(this.mIndex, 0);
    }
    
    public V getValue()
    {
      if (!this.mEntryValid) {
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
      }
      return (V)MapCollections.this.colGetEntry(this.mIndex, 1);
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
      Object localObject1 = MapCollections.this.colGetEntry(this.mIndex, 0);
      Object localObject2 = MapCollections.this.colGetEntry(this.mIndex, 1);
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
      MapCollections.this.colRemoveAt(this.mIndex);
      this.mIndex = (-1 + this.mIndex);
      this.mEnd = (-1 + this.mEnd);
      this.mEntryValid = false;
    }
    
    public V setValue(V paramV)
    {
      if (!this.mEntryValid) {
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
      }
      return (V)MapCollections.this.colSetValue(this.mIndex, paramV);
    }
    
    public final String toString()
    {
      return getKey() + "=" + getValue();
    }
  }
  
  final class ArrayIterator<T>
    implements Iterator<T>
  {
    boolean mCanRemove = false;
    int mIndex;
    final int mOffset;
    int mSize;
    
    ArrayIterator(int paramInt)
    {
      this.mOffset = paramInt;
      this.mSize = MapCollections.this.colGetSize();
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
      Object localObject = MapCollections.this.colGetEntry(this.mIndex, this.mOffset);
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
      MapCollections.this.colRemoveAt(this.mIndex);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/util/MapCollections.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */