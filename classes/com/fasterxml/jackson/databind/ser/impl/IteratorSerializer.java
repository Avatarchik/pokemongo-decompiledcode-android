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
import java.util.Iterator;

@JacksonStdImpl
public class IteratorSerializer
  extends AsArraySerializerBase<Iterator<?>>
{
  public IteratorSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer)
  {
    super(Iterator.class, paramJavaType, paramBoolean, paramTypeSerializer, null);
  }
  
  public IteratorSerializer(IteratorSerializer paramIteratorSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramIteratorSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new IteratorSerializer(this, this._property, paramTypeSerializer, this._elementSerializer, this._unwrapSingle);
  }
  
  public boolean hasSingleElement(Iterator<?> paramIterator)
  {
    return false;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, Iterator<?> paramIterator)
  {
    if ((paramIterator == null) || (!paramIterator.hasNext())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final void serialize(Iterator<?> paramIterator, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || ((this._unwrapSingle == Boolean.TRUE) && (hasSingleElement(paramIterator)))) {
      serializeContents(paramIterator, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartArray();
      serializeContents(paramIterator, paramJsonGenerator, paramSerializerProvider);
      paramJsonGenerator.writeEndArray();
    }
  }
  
  public void serializeContents(Iterator<?> paramIterator, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    TypeSerializer localTypeSerializer;
    Object localObject1;
    Object localObject2;
    if (paramIterator.hasNext())
    {
      localTypeSerializer = this._valueTypeSerializer;
      localObject1 = null;
      localObject2 = null;
    }
    for (;;)
    {
      Object localObject3 = paramIterator.next();
      if (localObject3 == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      }
      while (!paramIterator.hasNext())
      {
        return;
        Object localObject4 = this._elementSerializer;
        Class localClass;
        if (localObject4 == null)
        {
          localClass = localObject3.getClass();
          if (localClass != localObject2) {
            break label95;
          }
          localObject4 = localObject1;
        }
        for (;;)
        {
          if (localTypeSerializer != null) {
            break label118;
          }
          ((JsonSerializer)localObject4).serialize(localObject3, paramJsonGenerator, paramSerializerProvider);
          break;
          label95:
          localObject4 = paramSerializerProvider.findValueSerializer(localClass, this._property);
          localObject1 = localObject4;
          localObject2 = localClass;
        }
        label118:
        ((JsonSerializer)localObject4).serializeWithType(localObject3, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
      }
    }
  }
  
  public IteratorSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    return new IteratorSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/IteratorSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */