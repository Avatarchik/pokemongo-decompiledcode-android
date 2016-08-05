package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.util.EmptyIterator;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.Iterator;

public abstract class JsonSerializer<T>
  implements JsonFormatVisitable
{
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper != null) {
      paramJsonFormatVisitorWrapper.expectAnyFormat(paramJavaType);
    }
  }
  
  public JsonSerializer<?> getDelegatee()
  {
    return null;
  }
  
  public Class<T> handledType()
  {
    return null;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, T paramT)
  {
    if (paramT == null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  @Deprecated
  public boolean isEmpty(T paramT)
  {
    return isEmpty(null, paramT);
  }
  
  public boolean isUnwrappingSerializer()
  {
    return false;
  }
  
  public Iterator<PropertyWriter> properties()
  {
    return EmptyIterator.instance();
  }
  
  public JsonSerializer<T> replaceDelegatee(JsonSerializer<?> paramJsonSerializer)
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract void serialize(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException;
  
  public void serializeWithType(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    Class localClass = handledType();
    if (localClass == null) {
      localClass = paramT.getClass();
    }
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = localClass.getName();
    arrayOfObject[1] = getClass().getName();
    throw paramSerializerProvider.mappingException("Type id handling not implemented for type %s (by serializer of type %s)", arrayOfObject);
  }
  
  public JsonSerializer<T> unwrappingSerializer(NameTransformer paramNameTransformer)
  {
    return this;
  }
  
  public boolean usesObjectId()
  {
    return false;
  }
  
  public JsonSerializer<?> withFilterId(Object paramObject)
  {
    return this;
  }
  
  public static abstract class None
    extends JsonSerializer<Object>
  {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/JsonSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */