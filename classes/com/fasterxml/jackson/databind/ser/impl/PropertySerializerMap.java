package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.util.Arrays;

public abstract class PropertySerializerMap
{
  protected final boolean _resetWhenFull;
  
  protected PropertySerializerMap(PropertySerializerMap paramPropertySerializerMap)
  {
    this._resetWhenFull = paramPropertySerializerMap._resetWhenFull;
  }
  
  protected PropertySerializerMap(boolean paramBoolean)
  {
    this._resetWhenFull = paramBoolean;
  }
  
  public static PropertySerializerMap emptyForProperties()
  {
    return Empty.FOR_PROPERTIES;
  }
  
  public static PropertySerializerMap emptyForRootValues()
  {
    return Empty.FOR_ROOT_VALUES;
  }
  
  @Deprecated
  public static PropertySerializerMap emptyMap()
  {
    return emptyForProperties();
  }
  
  public final SerializerAndMapResult addSerializer(JavaType paramJavaType, JsonSerializer<Object> paramJsonSerializer)
  {
    return new SerializerAndMapResult(paramJsonSerializer, newWith(paramJavaType.getRawClass(), paramJsonSerializer));
  }
  
  public final SerializerAndMapResult addSerializer(Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer)
  {
    return new SerializerAndMapResult(paramJsonSerializer, newWith(paramClass, paramJsonSerializer));
  }
  
  public final SerializerAndMapResult findAndAddKeySerializer(Class<?> paramClass, SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer = paramSerializerProvider.findKeySerializer(paramClass, paramBeanProperty);
    return new SerializerAndMapResult(localJsonSerializer, newWith(paramClass, localJsonSerializer));
  }
  
  public final SerializerAndMapResult findAndAddPrimarySerializer(JavaType paramJavaType, SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer = paramSerializerProvider.findPrimaryPropertySerializer(paramJavaType, paramBeanProperty);
    return new SerializerAndMapResult(localJsonSerializer, newWith(paramJavaType.getRawClass(), localJsonSerializer));
  }
  
  public final SerializerAndMapResult findAndAddPrimarySerializer(Class<?> paramClass, SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer = paramSerializerProvider.findPrimaryPropertySerializer(paramClass, paramBeanProperty);
    return new SerializerAndMapResult(localJsonSerializer, newWith(paramClass, localJsonSerializer));
  }
  
  public final SerializerAndMapResult findAndAddRootValueSerializer(JavaType paramJavaType, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer = paramSerializerProvider.findTypedValueSerializer(paramJavaType, false, null);
    return new SerializerAndMapResult(localJsonSerializer, newWith(paramJavaType.getRawClass(), localJsonSerializer));
  }
  
  public final SerializerAndMapResult findAndAddRootValueSerializer(Class<?> paramClass, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer = paramSerializerProvider.findTypedValueSerializer(paramClass, false, null);
    return new SerializerAndMapResult(localJsonSerializer, newWith(paramClass, localJsonSerializer));
  }
  
  public final SerializerAndMapResult findAndAddSecondarySerializer(JavaType paramJavaType, SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer = paramSerializerProvider.findValueSerializer(paramJavaType, paramBeanProperty);
    return new SerializerAndMapResult(localJsonSerializer, newWith(paramJavaType.getRawClass(), localJsonSerializer));
  }
  
  public final SerializerAndMapResult findAndAddSecondarySerializer(Class<?> paramClass, SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer = paramSerializerProvider.findValueSerializer(paramClass, paramBeanProperty);
    return new SerializerAndMapResult(localJsonSerializer, newWith(paramClass, localJsonSerializer));
  }
  
  public abstract PropertySerializerMap newWith(Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer);
  
  public abstract JsonSerializer<Object> serializerFor(Class<?> paramClass);
  
  private static final class Multi
    extends PropertySerializerMap
  {
    private static final int MAX_ENTRIES = 8;
    private final PropertySerializerMap.TypeAndSerializer[] _entries;
    
    public Multi(PropertySerializerMap paramPropertySerializerMap, PropertySerializerMap.TypeAndSerializer[] paramArrayOfTypeAndSerializer)
    {
      super();
      this._entries = paramArrayOfTypeAndSerializer;
    }
    
    public PropertySerializerMap newWith(Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer)
    {
      int i = this._entries.length;
      if (i == 8) {
        if (!this._resetWhenFull) {}
      }
      PropertySerializerMap.TypeAndSerializer[] arrayOfTypeAndSerializer;
      for (this = new PropertySerializerMap.Single(this, paramClass, paramJsonSerializer);; this = new Multi(this, arrayOfTypeAndSerializer))
      {
        return this;
        arrayOfTypeAndSerializer = (PropertySerializerMap.TypeAndSerializer[])Arrays.copyOf(this._entries, i + 1);
        arrayOfTypeAndSerializer[i] = new PropertySerializerMap.TypeAndSerializer(paramClass, paramJsonSerializer);
      }
    }
    
    public JsonSerializer<Object> serializerFor(Class<?> paramClass)
    {
      int i = 0;
      int j = this._entries.length;
      PropertySerializerMap.TypeAndSerializer localTypeAndSerializer;
      if (i < j)
      {
        localTypeAndSerializer = this._entries[i];
        if (localTypeAndSerializer.type != paramClass) {}
      }
      for (JsonSerializer localJsonSerializer = localTypeAndSerializer.serializer;; localJsonSerializer = null)
      {
        return localJsonSerializer;
        i++;
        break;
      }
    }
  }
  
  private static final class Double
    extends PropertySerializerMap
  {
    private final JsonSerializer<Object> _serializer1;
    private final JsonSerializer<Object> _serializer2;
    private final Class<?> _type1;
    private final Class<?> _type2;
    
    public Double(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass1, JsonSerializer<Object> paramJsonSerializer1, Class<?> paramClass2, JsonSerializer<Object> paramJsonSerializer2)
    {
      super();
      this._type1 = paramClass1;
      this._serializer1 = paramJsonSerializer1;
      this._type2 = paramClass2;
      this._serializer2 = paramJsonSerializer2;
    }
    
    public PropertySerializerMap newWith(Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer)
    {
      PropertySerializerMap.TypeAndSerializer[] arrayOfTypeAndSerializer = new PropertySerializerMap.TypeAndSerializer[3];
      arrayOfTypeAndSerializer[0] = new PropertySerializerMap.TypeAndSerializer(this._type1, this._serializer1);
      arrayOfTypeAndSerializer[1] = new PropertySerializerMap.TypeAndSerializer(this._type2, this._serializer2);
      arrayOfTypeAndSerializer[2] = new PropertySerializerMap.TypeAndSerializer(paramClass, paramJsonSerializer);
      return new PropertySerializerMap.Multi(this, arrayOfTypeAndSerializer);
    }
    
    public JsonSerializer<Object> serializerFor(Class<?> paramClass)
    {
      JsonSerializer localJsonSerializer;
      if (paramClass == this._type1) {
        localJsonSerializer = this._serializer1;
      }
      for (;;)
      {
        return localJsonSerializer;
        if (paramClass == this._type2) {
          localJsonSerializer = this._serializer2;
        } else {
          localJsonSerializer = null;
        }
      }
    }
  }
  
  private static final class Single
    extends PropertySerializerMap
  {
    private final JsonSerializer<Object> _serializer;
    private final Class<?> _type;
    
    public Single(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer)
    {
      super();
      this._type = paramClass;
      this._serializer = paramJsonSerializer;
    }
    
    public PropertySerializerMap newWith(Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer)
    {
      return new PropertySerializerMap.Double(this, this._type, this._serializer, paramClass, paramJsonSerializer);
    }
    
    public JsonSerializer<Object> serializerFor(Class<?> paramClass)
    {
      if (paramClass == this._type) {}
      for (JsonSerializer localJsonSerializer = this._serializer;; localJsonSerializer = null) {
        return localJsonSerializer;
      }
    }
  }
  
  private static final class Empty
    extends PropertySerializerMap
  {
    public static final Empty FOR_PROPERTIES = new Empty(false);
    public static final Empty FOR_ROOT_VALUES = new Empty(true);
    
    protected Empty(boolean paramBoolean)
    {
      super();
    }
    
    public PropertySerializerMap newWith(Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer)
    {
      return new PropertySerializerMap.Single(this, paramClass, paramJsonSerializer);
    }
    
    public JsonSerializer<Object> serializerFor(Class<?> paramClass)
    {
      return null;
    }
  }
  
  private static final class TypeAndSerializer
  {
    public final JsonSerializer<Object> serializer;
    public final Class<?> type;
    
    public TypeAndSerializer(Class<?> paramClass, JsonSerializer<Object> paramJsonSerializer)
    {
      this.type = paramClass;
      this.serializer = paramJsonSerializer;
    }
  }
  
  public static final class SerializerAndMapResult
  {
    public final PropertySerializerMap map;
    public final JsonSerializer<Object> serializer;
    
    public SerializerAndMapResult(JsonSerializer<Object> paramJsonSerializer, PropertySerializerMap paramPropertySerializerMap)
    {
      this.serializer = paramJsonSerializer;
      this.map = paramPropertySerializerMap;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/PropertySerializerMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */