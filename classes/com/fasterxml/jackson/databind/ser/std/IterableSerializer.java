package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.Iterator;

@JacksonStdImpl
public class IterableSerializer
  extends AsArraySerializerBase<Iterable<?>>
{
  public IterableSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer)
  {
    super(Iterable.class, paramJavaType, paramBoolean, paramTypeSerializer, null);
  }
  
  public IterableSerializer(IterableSerializer paramIterableSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramIterableSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new IterableSerializer(this, this._property, paramTypeSerializer, this._elementSerializer, this._unwrapSingle);
  }
  
  public boolean hasSingleElement(Iterable<?> paramIterable)
  {
    if (paramIterable != null)
    {
      Iterator localIterator = paramIterable.iterator();
      if (localIterator.hasNext())
      {
        localIterator.next();
        if (localIterator.hasNext()) {}
      }
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, Iterable<?> paramIterable)
  {
    if ((paramIterable == null) || (!paramIterable.iterator().hasNext())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final void serialize(Iterable<?> paramIterable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || ((this._unwrapSingle == Boolean.TRUE) && (hasSingleElement(paramIterable)))) {
      serializeContents(paramIterable, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartArray();
      serializeContents(paramIterable, paramJsonGenerator, paramSerializerProvider);
      paramJsonGenerator.writeEndArray();
    }
  }
  
  public void serializeContents(Iterable<?> paramIterable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    Iterator localIterator = paramIterable.iterator();
    TypeSerializer localTypeSerializer;
    Object localObject1;
    Object localObject2;
    if (localIterator.hasNext())
    {
      localTypeSerializer = this._valueTypeSerializer;
      localObject1 = null;
      localObject2 = null;
    }
    for (;;)
    {
      Object localObject3 = localIterator.next();
      if (localObject3 == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      }
      while (!localIterator.hasNext())
      {
        return;
        Object localObject4 = this._elementSerializer;
        Class localClass;
        if (localObject4 == null)
        {
          localClass = localObject3.getClass();
          if (localClass != localObject2) {
            break label106;
          }
          localObject4 = localObject1;
        }
        for (;;)
        {
          if (localTypeSerializer != null) {
            break label129;
          }
          ((JsonSerializer)localObject4).serialize(localObject3, paramJsonGenerator, paramSerializerProvider);
          break;
          label106:
          localObject4 = paramSerializerProvider.findValueSerializer(localClass, this._property);
          localObject1 = localObject4;
          localObject2 = localClass;
        }
        label129:
        ((JsonSerializer)localObject4).serializeWithType(localObject3, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
      }
    }
  }
  
  public IterableSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    return new IterableSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/IterableSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */