package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;

public class EnumSetSerializer
  extends AsArraySerializerBase<EnumSet<? extends Enum<?>>>
{
  public EnumSetSerializer(JavaType paramJavaType)
  {
    super(EnumSet.class, paramJavaType, true, null, null);
  }
  
  @Deprecated
  public EnumSetSerializer(JavaType paramJavaType, BeanProperty paramBeanProperty)
  {
    this(paramJavaType);
  }
  
  public EnumSetSerializer(EnumSetSerializer paramEnumSetSerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramEnumSetSerializer, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
  
  public EnumSetSerializer _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return this;
  }
  
  public boolean hasSingleElement(EnumSet<? extends Enum<?>> paramEnumSet)
  {
    int i = 1;
    if (paramEnumSet.size() == i) {}
    for (;;)
    {
      return i;
      int j = 0;
    }
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, EnumSet<? extends Enum<?>> paramEnumSet)
  {
    if ((paramEnumSet == null) || (paramEnumSet.isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final void serialize(EnumSet<? extends Enum<?>> paramEnumSet, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramEnumSet.size();
    if ((i == 1) && (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || (this._unwrapSingle == Boolean.TRUE))) {
      serializeContents(paramEnumSet, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartArray(i);
      serializeContents(paramEnumSet, paramJsonGenerator, paramSerializerProvider);
      paramJsonGenerator.writeEndArray();
    }
  }
  
  public void serializeContents(EnumSet<? extends Enum<?>> paramEnumSet, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    JsonSerializer localJsonSerializer = this._elementSerializer;
    Iterator localIterator = paramEnumSet.iterator();
    while (localIterator.hasNext())
    {
      Enum localEnum = (Enum)localIterator.next();
      if (localJsonSerializer == null) {
        localJsonSerializer = paramSerializerProvider.findValueSerializer(localEnum.getDeclaringClass(), this._property);
      }
      localJsonSerializer.serialize(localEnum, paramJsonGenerator, paramSerializerProvider);
    }
  }
  
  public EnumSetSerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    return new EnumSetSerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/EnumSetSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */