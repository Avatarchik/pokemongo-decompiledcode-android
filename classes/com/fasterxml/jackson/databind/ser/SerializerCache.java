package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.ReadOnlyClassToSerializerMap;
import com.fasterxml.jackson.databind.util.TypeKey;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public final class SerializerCache
{
  private final AtomicReference<ReadOnlyClassToSerializerMap> _readOnlyMap = new AtomicReference();
  private final HashMap<TypeKey, JsonSerializer<Object>> _sharedMap = new HashMap(64);
  
  /**
   * @deprecated
   */
  private final ReadOnlyClassToSerializerMap _makeReadOnlyLookupMap()
  {
    try
    {
      ReadOnlyClassToSerializerMap localReadOnlyClassToSerializerMap = (ReadOnlyClassToSerializerMap)this._readOnlyMap.get();
      if (localReadOnlyClassToSerializerMap == null)
      {
        localReadOnlyClassToSerializerMap = ReadOnlyClassToSerializerMap.from(this._sharedMap);
        this._readOnlyMap.set(localReadOnlyClassToSerializerMap);
      }
      return localReadOnlyClassToSerializerMap;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void addAndResolveNonTypedSerializer(JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    try
    {
      if (this._sharedMap.put(new TypeKey(paramJavaType, false), paramJsonSerializer) == null) {
        this._readOnlyMap.set(null);
      }
      if ((paramJsonSerializer instanceof ResolvableSerializer)) {
        ((ResolvableSerializer)paramJsonSerializer).resolve(paramSerializerProvider);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void addAndResolveNonTypedSerializer(Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    try
    {
      if (this._sharedMap.put(new TypeKey(paramClass, false), paramJsonSerializer) == null) {
        this._readOnlyMap.set(null);
      }
      if ((paramJsonSerializer instanceof ResolvableSerializer)) {
        ((ResolvableSerializer)paramJsonSerializer).resolve(paramSerializerProvider);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void addTypedSerializer(JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer)
  {
    try
    {
      if (this._sharedMap.put(new TypeKey(paramJavaType, true), paramJsonSerializer) == null) {
        this._readOnlyMap.set(null);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void addTypedSerializer(Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer)
  {
    try
    {
      if (this._sharedMap.put(new TypeKey(paramClass, true), paramJsonSerializer) == null) {
        this._readOnlyMap.set(null);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void flush()
  {
    try
    {
      this._sharedMap.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public ReadOnlyClassToSerializerMap getReadOnlyLookupMap()
  {
    ReadOnlyClassToSerializerMap localReadOnlyClassToSerializerMap = (ReadOnlyClassToSerializerMap)this._readOnlyMap.get();
    if (localReadOnlyClassToSerializerMap != null) {}
    for (;;)
    {
      return localReadOnlyClassToSerializerMap;
      localReadOnlyClassToSerializerMap = _makeReadOnlyLookupMap();
    }
  }
  
  /**
   * @deprecated
   */
  public int size()
  {
    try
    {
      int i = this._sharedMap.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public JsonSerializer<Object> typedValueSerializer(JavaType paramJavaType)
  {
    try
    {
      JsonSerializer localJsonSerializer = (JsonSerializer)this._sharedMap.get(new TypeKey(paramJavaType, true));
      return localJsonSerializer;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public JsonSerializer<Object> typedValueSerializer(Class<?> paramClass)
  {
    try
    {
      JsonSerializer localJsonSerializer = (JsonSerializer)this._sharedMap.get(new TypeKey(paramClass, true));
      return localJsonSerializer;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public JsonSerializer<Object> untypedValueSerializer(JavaType paramJavaType)
  {
    try
    {
      JsonSerializer localJsonSerializer = (JsonSerializer)this._sharedMap.get(new TypeKey(paramJavaType, false));
      return localJsonSerializer;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public JsonSerializer<Object> untypedValueSerializer(Class<?> paramClass)
  {
    try
    {
      JsonSerializer localJsonSerializer = (JsonSerializer)this._sharedMap.get(new TypeKey(paramClass, false));
      return localJsonSerializer;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  @Deprecated
  public static final class TypeKey
    extends TypeKey
  {
    public TypeKey(JavaType paramJavaType, boolean paramBoolean)
    {
      super(paramBoolean);
    }
    
    public TypeKey(Class<?> paramClass, boolean paramBoolean)
    {
      super(paramBoolean);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/SerializerCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */