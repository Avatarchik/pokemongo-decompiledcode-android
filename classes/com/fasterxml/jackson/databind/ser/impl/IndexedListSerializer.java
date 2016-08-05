package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import java.io.IOException;
import java.util.List;

@JacksonStdImpl
public final class IndexedListSerializer
  extends AsArraySerializerBase<List<?>>
{
  private static final long serialVersionUID = 1L;
  
  public IndexedListSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    super(List.class, paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer);
  }
  
  public IndexedListSerializer(IndexedListSerializer paramIndexedListSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramIndexedListSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new IndexedListSerializer(this, this._property, paramTypeSerializer, this._elementSerializer, this._unwrapSingle);
  }
  
  public boolean hasSingleElement(List<?> paramList)
  {
    int i = 1;
    if (paramList.size() == i) {}
    for (;;)
    {
      return i;
      int j = 0;
    }
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, List<?> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final void serialize(List<?> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramList.size();
    if ((i == 1) && (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || (this._unwrapSingle == Boolean.TRUE))) {
      serializeContents(paramList, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartArray(i);
      serializeContents(paramList, paramJsonGenerator, paramSerializerProvider);
      paramJsonGenerator.writeEndArray();
    }
  }
  
  public void serializeContents(List<?> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (this._elementSerializer != null) {
      serializeContentsUsing(paramList, paramJsonGenerator, paramSerializerProvider, this._elementSerializer);
    }
    int i;
    do
    {
      for (;;)
      {
        return;
        if (this._valueTypeSerializer == null) {
          break;
        }
        serializeTypedContents(paramList, paramJsonGenerator, paramSerializerProvider);
      }
      i = paramList.size();
    } while (i == 0);
    for (int j = 0;; j++) {
      for (;;)
      {
        PropertySerializerMap localPropertySerializerMap;
        Class localClass;
        try
        {
          localPropertySerializerMap = this._dynamicSerializers;
          if (j >= i) {
            break;
          }
          Object localObject1 = paramList.get(j);
          if (localObject1 == null)
          {
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          }
          else
          {
            localClass = localObject1.getClass();
            localObject2 = localPropertySerializerMap.serializerFor(localClass);
            if (localObject2 == null)
            {
              if (this._elementType.hasGenericTypes())
              {
                localObject2 = _findAndAddDynamic(localPropertySerializerMap, paramSerializerProvider.constructSpecializedType(this._elementType, localClass), paramSerializerProvider);
                localPropertySerializerMap = this._dynamicSerializers;
              }
            }
            else {
              ((JsonSerializer)localObject2).serialize(localObject1, paramJsonGenerator, paramSerializerProvider);
            }
          }
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramList, j);
        }
        break;
        JsonSerializer localJsonSerializer = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
        Object localObject2 = localJsonSerializer;
      }
    }
  }
  
  public void serializeContentsUsing(List<?> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException
  {
    int i = paramList.size();
    if (i == 0) {}
    for (;;)
    {
      return;
      TypeSerializer localTypeSerializer = this._valueTypeSerializer;
      for (int j = 0; j < i; j++)
      {
        Object localObject = paramList.get(j);
        if (localObject == null) {}
        try
        {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramList, j);
        }
        if (localTypeSerializer == null) {
          paramJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
        } else {
          paramJsonSerializer.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
      }
    }
  }
  
  public void serializeTypedContents(List<?> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramList.size();
    if (i == 0) {
      return;
    }
    for (int j = 0;; j++) {
      for (;;)
      {
        PropertySerializerMap localPropertySerializerMap;
        Class localClass;
        try
        {
          TypeSerializer localTypeSerializer = this._valueTypeSerializer;
          localPropertySerializerMap = this._dynamicSerializers;
          if (j >= i) {
            break;
          }
          Object localObject1 = paramList.get(j);
          if (localObject1 == null)
          {
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          }
          else
          {
            localClass = localObject1.getClass();
            localObject2 = localPropertySerializerMap.serializerFor(localClass);
            if (localObject2 == null)
            {
              if (this._elementType.hasGenericTypes())
              {
                localObject2 = _findAndAddDynamic(localPropertySerializerMap, paramSerializerProvider.constructSpecializedType(this._elementType, localClass), paramSerializerProvider);
                localPropertySerializerMap = this._dynamicSerializers;
              }
            }
            else {
              ((JsonSerializer)localObject2).serializeWithType(localObject1, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
            }
          }
        }
        catch (Exception localException)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramList, j);
        }
        break;
        JsonSerializer localJsonSerializer = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
        Object localObject2 = localJsonSerializer;
      }
    }
  }
  
  public IndexedListSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    return new IndexedListSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/IndexedListSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */