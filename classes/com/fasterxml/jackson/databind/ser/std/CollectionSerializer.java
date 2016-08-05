package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class CollectionSerializer
  extends AsArraySerializerBase<Collection<?>>
{
  private static final long serialVersionUID = 1L;
  
  @Deprecated
  public CollectionSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer<Object> paramJsonSerializer)
  {
    this(paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer);
  }
  
  public CollectionSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    super(Collection.class, paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer);
  }
  
  public CollectionSerializer(CollectionSerializer paramCollectionSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramCollectionSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new CollectionSerializer(this, this._property, paramTypeSerializer, this._elementSerializer, this._unwrapSingle);
  }
  
  public boolean hasSingleElement(Collection<?> paramCollection)
  {
    boolean bool = false;
    Iterator localIterator = paramCollection.iterator();
    if (!localIterator.hasNext()) {}
    for (;;)
    {
      return bool;
      localIterator.next();
      if (!localIterator.hasNext()) {
        bool = true;
      }
    }
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, Collection<?> paramCollection)
  {
    if ((paramCollection == null) || (paramCollection.isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final void serialize(Collection<?> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramCollection.size();
    if ((i == 1) && (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || (this._unwrapSingle == Boolean.TRUE))) {
      serializeContents(paramCollection, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartArray(i);
      serializeContents(paramCollection, paramJsonGenerator, paramSerializerProvider);
      paramJsonGenerator.writeEndArray();
    }
  }
  
  public void serializeContents(Collection<?> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (this._elementSerializer != null) {
      serializeContentsUsing(paramCollection, paramJsonGenerator, paramSerializerProvider, this._elementSerializer);
    }
    Iterator localIterator;
    do
    {
      return;
      localIterator = paramCollection.iterator();
    } while (!localIterator.hasNext());
    PropertySerializerMap localPropertySerializerMap = this._dynamicSerializers;
    TypeSerializer localTypeSerializer = this._valueTypeSerializer;
    int i = 0;
    for (;;)
    {
      Object localObject;
      Class localClass;
      try
      {
        localObject = localIterator.next();
        if (localObject == null)
        {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          i++;
          if (localIterator.hasNext()) {
            continue;
          }
          break;
        }
        localClass = localObject.getClass();
        localJsonSerializer = localPropertySerializerMap.serializerFor(localClass);
        if (localJsonSerializer == null)
        {
          if (this._elementType.hasGenericTypes())
          {
            localJsonSerializer = _findAndAddDynamic(localPropertySerializerMap, paramSerializerProvider.constructSpecializedType(this._elementType, localClass), paramSerializerProvider);
            localPropertySerializerMap = this._dynamicSerializers;
          }
        }
        else
        {
          if (localTypeSerializer != null) {
            break label189;
          }
          localJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
          continue;
        }
      }
      catch (Exception localException)
      {
        wrapAndThrow(paramSerializerProvider, localException, paramCollection, i);
      }
      JsonSerializer localJsonSerializer = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
      continue;
      label189:
      localJsonSerializer.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
    }
  }
  
  public void serializeContentsUsing(Collection<?> paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException, JsonGenerationException
  {
    Iterator localIterator = paramCollection.iterator();
    TypeSerializer localTypeSerializer;
    int i;
    if (localIterator.hasNext())
    {
      localTypeSerializer = this._valueTypeSerializer;
      i = 0;
    }
    for (;;)
    {
      Object localObject = localIterator.next();
      if (localObject == null) {}
      try
      {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        for (;;)
        {
          i++;
          if (localIterator.hasNext()) {
            break;
          }
          return;
          if (localTypeSerializer != null) {
            break label92;
          }
          paramJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          wrapAndThrow(paramSerializerProvider, localException, paramCollection, i);
          continue;
          label92:
          paramJsonSerializer.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
      }
    }
  }
  
  public CollectionSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    return new CollectionSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/CollectionSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */