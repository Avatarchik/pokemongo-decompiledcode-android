package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.io.Serializable;

public final class TypeWrappedDeserializer
  extends JsonDeserializer<Object>
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final JsonDeserializer<Object> _deserializer;
  protected final TypeDeserializer _typeDeserializer;
  
  public TypeWrappedDeserializer(TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
  {
    this._typeDeserializer = paramTypeDeserializer;
    this._deserializer = paramJsonDeserializer;
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return this._deserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, this._typeDeserializer);
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject)
    throws IOException
  {
    return this._deserializer.deserialize(paramJsonParser, paramDeserializationContext, paramObject);
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
  }
  
  public Class<?> handledType()
  {
    return this._deserializer.handledType();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/TypeWrappedDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */