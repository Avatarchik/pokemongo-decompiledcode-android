package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.util.TypeKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class ReadOnlyClassToSerializerMap
{
  private final Bucket[] _buckets;
  private final int _mask;
  private final int _size;
  
  public ReadOnlyClassToSerializerMap(Map<TypeKey, JsonSerializer<Object>> paramMap)
  {
    int i = findSize(paramMap.size());
    this._size = i;
    this._mask = (i - 1);
    Bucket[] arrayOfBucket = new Bucket[i];
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      TypeKey localTypeKey = (TypeKey)localEntry.getKey();
      int j = localTypeKey.hashCode() & this._mask;
      arrayOfBucket[j] = new Bucket(arrayOfBucket[j], localTypeKey, (JsonSerializer)localEntry.getValue());
    }
    this._buckets = arrayOfBucket;
  }
  
  private static final int findSize(int paramInt)
  {
    if (paramInt <= 64) {}
    int j;
    for (int i = paramInt + paramInt;; i = paramInt + (paramInt >> 2))
    {
      j = 8;
      while (j < i) {
        j += j;
      }
    }
    return j;
  }
  
  public static ReadOnlyClassToSerializerMap from(HashMap<TypeKey, JsonSerializer<Object>> paramHashMap)
  {
    return new ReadOnlyClassToSerializerMap(paramHashMap);
  }
  
  public int size()
  {
    return this._size;
  }
  
  public JsonSerializer<Object> typedValueSerializer(JavaType paramJavaType)
  {
    Object localObject = null;
    Bucket localBucket = this._buckets[(TypeKey.typedHash(paramJavaType) & this._mask)];
    if (localBucket == null) {}
    for (;;)
    {
      return (JsonSerializer<Object>)localObject;
      if (localBucket.matchesTyped(paramJavaType))
      {
        localObject = localBucket.value;
      }
      else
      {
        do
        {
          localBucket = localBucket.next;
          if (localBucket == null) {
            break;
          }
        } while (!localBucket.matchesTyped(paramJavaType));
        localObject = localBucket.value;
      }
    }
  }
  
  public JsonSerializer<Object> typedValueSerializer(Class<?> paramClass)
  {
    Object localObject = null;
    Bucket localBucket = this._buckets[(TypeKey.typedHash(paramClass) & this._mask)];
    if (localBucket == null) {}
    for (;;)
    {
      return (JsonSerializer<Object>)localObject;
      if (localBucket.matchesTyped(paramClass))
      {
        localObject = localBucket.value;
      }
      else
      {
        do
        {
          localBucket = localBucket.next;
          if (localBucket == null) {
            break;
          }
        } while (!localBucket.matchesTyped(paramClass));
        localObject = localBucket.value;
      }
    }
  }
  
  public JsonSerializer<Object> untypedValueSerializer(JavaType paramJavaType)
  {
    Object localObject = null;
    Bucket localBucket = this._buckets[(TypeKey.untypedHash(paramJavaType) & this._mask)];
    if (localBucket == null) {}
    for (;;)
    {
      return (JsonSerializer<Object>)localObject;
      if (localBucket.matchesUntyped(paramJavaType))
      {
        localObject = localBucket.value;
      }
      else
      {
        do
        {
          localBucket = localBucket.next;
          if (localBucket == null) {
            break;
          }
        } while (!localBucket.matchesUntyped(paramJavaType));
        localObject = localBucket.value;
      }
    }
  }
  
  public JsonSerializer<Object> untypedValueSerializer(Class<?> paramClass)
  {
    Object localObject = null;
    Bucket localBucket = this._buckets[(TypeKey.untypedHash(paramClass) & this._mask)];
    if (localBucket == null) {}
    for (;;)
    {
      return (JsonSerializer<Object>)localObject;
      if (localBucket.matchesUntyped(paramClass))
      {
        localObject = localBucket.value;
      }
      else
      {
        do
        {
          localBucket = localBucket.next;
          if (localBucket == null) {
            break;
          }
        } while (!localBucket.matchesUntyped(paramClass));
        localObject = localBucket.value;
      }
    }
  }
  
  private static final class Bucket
  {
    protected final Class<?> _class;
    protected final boolean _isTyped;
    protected final JavaType _type;
    public final Bucket next;
    public final JsonSerializer<Object> value;
    
    public Bucket(Bucket paramBucket, TypeKey paramTypeKey, JsonSerializer<Object> paramJsonSerializer)
    {
      this.next = paramBucket;
      this.value = paramJsonSerializer;
      this._isTyped = paramTypeKey.isTyped();
      this._class = paramTypeKey.getRawType();
      this._type = paramTypeKey.getType();
    }
    
    public boolean matchesTyped(JavaType paramJavaType)
    {
      if ((this._isTyped) && (paramJavaType.equals(this._type))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean matchesTyped(Class<?> paramClass)
    {
      if ((this._class == paramClass) && (this._isTyped)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean matchesUntyped(JavaType paramJavaType)
    {
      if ((!this._isTyped) && (paramJavaType.equals(this._type))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean matchesUntyped(Class<?> paramClass)
    {
      if ((this._class == paramClass) && (!this._isTyped)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/ReadOnlyClassToSerializerMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */