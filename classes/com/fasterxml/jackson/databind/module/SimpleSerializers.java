package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SimpleSerializers
  extends Serializers.Base
  implements Serializable
{
  private static final long serialVersionUID = 8531646511998456779L;
  protected HashMap<ClassKey, JsonSerializer<?>> _classMappings = null;
  protected boolean _hasEnumSerializer = false;
  protected HashMap<ClassKey, JsonSerializer<?>> _interfaceMappings = null;
  
  public SimpleSerializers() {}
  
  public SimpleSerializers(List<JsonSerializer<?>> paramList)
  {
    addSerializers(paramList);
  }
  
  protected void _addSerializer(Class<?> paramClass, JsonSerializer<?> paramJsonSerializer)
  {
    ClassKey localClassKey = new ClassKey(paramClass);
    if (paramClass.isInterface())
    {
      if (this._interfaceMappings == null) {
        this._interfaceMappings = new HashMap();
      }
      this._interfaceMappings.put(localClassKey, paramJsonSerializer);
    }
    for (;;)
    {
      return;
      if (this._classMappings == null) {
        this._classMappings = new HashMap();
      }
      this._classMappings.put(localClassKey, paramJsonSerializer);
      if (paramClass == Enum.class) {
        this._hasEnumSerializer = true;
      }
    }
  }
  
  protected JsonSerializer<?> _findInterfaceMapping(Class<?> paramClass, ClassKey paramClassKey)
  {
    Class[] arrayOfClass = paramClass.getInterfaces();
    int i = arrayOfClass.length;
    int j = 0;
    Class localClass;
    JsonSerializer localJsonSerializer;
    if (j < i)
    {
      localClass = arrayOfClass[j];
      paramClassKey.reset(localClass);
      localJsonSerializer = (JsonSerializer)this._interfaceMappings.get(paramClassKey);
      if (localJsonSerializer == null) {}
    }
    for (;;)
    {
      return localJsonSerializer;
      localJsonSerializer = _findInterfaceMapping(localClass, paramClassKey);
      if (localJsonSerializer == null)
      {
        j++;
        break;
        localJsonSerializer = null;
      }
    }
  }
  
  public void addSerializer(JsonSerializer<?> paramJsonSerializer)
  {
    Class localClass = paramJsonSerializer.handledType();
    if ((localClass == null) || (localClass == Object.class)) {
      throw new IllegalArgumentException("JsonSerializer of type " + paramJsonSerializer.getClass().getName() + " does not define valid handledType() -- must either register with method that takes type argument " + " or make serializer extend 'com.fasterxml.jackson.databind.ser.std.StdSerializer'");
    }
    _addSerializer(localClass, paramJsonSerializer);
  }
  
  public <T> void addSerializer(Class<? extends T> paramClass, JsonSerializer<T> paramJsonSerializer)
  {
    _addSerializer(paramClass, paramJsonSerializer);
  }
  
  public void addSerializers(List<JsonSerializer<?>> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext()) {
      addSerializer((JsonSerializer)localIterator.next());
    }
  }
  
  public JsonSerializer<?> findArraySerializer(SerializationConfig paramSerializationConfig, ArrayType paramArrayType, BeanDescription paramBeanDescription, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    return findSerializer(paramSerializationConfig, paramArrayType, paramBeanDescription);
  }
  
  public JsonSerializer<?> findCollectionLikeSerializer(SerializationConfig paramSerializationConfig, CollectionLikeType paramCollectionLikeType, BeanDescription paramBeanDescription, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    return findSerializer(paramSerializationConfig, paramCollectionLikeType, paramBeanDescription);
  }
  
  public JsonSerializer<?> findCollectionSerializer(SerializationConfig paramSerializationConfig, CollectionType paramCollectionType, BeanDescription paramBeanDescription, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    return findSerializer(paramSerializationConfig, paramCollectionType, paramBeanDescription);
  }
  
  public JsonSerializer<?> findMapLikeSerializer(SerializationConfig paramSerializationConfig, MapLikeType paramMapLikeType, BeanDescription paramBeanDescription, JsonSerializer<Object> paramJsonSerializer1, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer2)
  {
    return findSerializer(paramSerializationConfig, paramMapLikeType, paramBeanDescription);
  }
  
  public JsonSerializer<?> findMapSerializer(SerializationConfig paramSerializationConfig, MapType paramMapType, BeanDescription paramBeanDescription, JsonSerializer<Object> paramJsonSerializer1, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer2)
  {
    return findSerializer(paramSerializationConfig, paramMapType, paramBeanDescription);
  }
  
  public JsonSerializer<?> findSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription)
  {
    Class localClass1 = paramJavaType.getRawClass();
    ClassKey localClassKey = new ClassKey(localClass1);
    Object localObject;
    if (localClass1.isInterface())
    {
      if (this._interfaceMappings == null) {
        break label190;
      }
      JsonSerializer localJsonSerializer6 = (JsonSerializer)this._interfaceMappings.get(localClassKey);
      if (localJsonSerializer6 == null) {
        break label190;
      }
      localObject = localJsonSerializer6;
    }
    for (;;)
    {
      return (JsonSerializer<?>)localObject;
      if (this._classMappings != null)
      {
        JsonSerializer localJsonSerializer3 = (JsonSerializer)this._classMappings.get(localClassKey);
        if (localJsonSerializer3 != null)
        {
          localObject = localJsonSerializer3;
        }
        else
        {
          if ((this._hasEnumSerializer) && (paramJavaType.isEnumType()))
          {
            localClassKey.reset(Enum.class);
            JsonSerializer localJsonSerializer5 = (JsonSerializer)this._classMappings.get(localClassKey);
            if (localJsonSerializer5 != null)
            {
              localObject = localJsonSerializer5;
              continue;
            }
          }
          for (Class localClass2 = localClass1;; localClass2 = localClass2.getSuperclass())
          {
            if (localClass2 == null) {
              break label190;
            }
            localClassKey.reset(localClass2);
            JsonSerializer localJsonSerializer4 = (JsonSerializer)this._classMappings.get(localClassKey);
            if (localJsonSerializer4 != null)
            {
              localObject = localJsonSerializer4;
              break;
            }
          }
        }
      }
      else
      {
        label190:
        if (this._interfaceMappings != null)
        {
          JsonSerializer localJsonSerializer1 = _findInterfaceMapping(localClass1, localClassKey);
          if (localJsonSerializer1 != null)
          {
            localObject = localJsonSerializer1;
            continue;
          }
          if (!localClass1.isInterface())
          {
            JsonSerializer localJsonSerializer2;
            do
            {
              localClass1 = localClass1.getSuperclass();
              if (localClass1 == null) {
                break;
              }
              localJsonSerializer2 = _findInterfaceMapping(localClass1, localClassKey);
            } while (localJsonSerializer2 == null);
            localObject = localJsonSerializer2;
            continue;
          }
        }
        localObject = null;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/module/SimpleSerializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */