package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.EnumSet;

public class EnumSetDeserializer
  extends StdDeserializer<EnumSet<?>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final Class<Enum> _enumClass;
  protected JsonDeserializer<Enum<?>> _enumDeserializer;
  protected final JavaType _enumType;
  
  public EnumSetDeserializer(JavaType paramJavaType, JsonDeserializer<?> paramJsonDeserializer)
  {
    super(EnumSet.class);
    this._enumType = paramJavaType;
    this._enumClass = paramJavaType.getRawClass();
    if (!this._enumClass.isEnum()) {
      throw new IllegalArgumentException("Type " + paramJavaType + " not Java Enum type");
    }
    this._enumDeserializer = paramJsonDeserializer;
  }
  
  private EnumSet constructSet()
  {
    return EnumSet.noneOf(this._enumClass);
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer1 = this._enumDeserializer;
    if (localJsonDeserializer1 == null) {}
    for (JsonDeserializer localJsonDeserializer2 = paramDeserializationContext.findContextualValueDeserializer(this._enumType, paramBeanProperty);; localJsonDeserializer2 = paramDeserializationContext.handleSecondaryContextualization(localJsonDeserializer1, paramBeanProperty, this._enumType)) {
      return withDeserializer(localJsonDeserializer2);
    }
  }
  
  public EnumSet<?> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      throw paramDeserializationContext.mappingException(EnumSet.class);
    }
    EnumSet localEnumSet = constructSet();
    for (;;)
    {
      try
      {
        JsonToken localJsonToken = paramJsonParser.nextToken();
        if (localJsonToken == JsonToken.END_ARRAY) {
          break;
        }
        if (localJsonToken == JsonToken.VALUE_NULL) {
          throw paramDeserializationContext.mappingException(this._enumClass);
        }
      }
      catch (Exception localException)
      {
        throw JsonMappingException.wrapWithPath(localException, localEnumSet, localEnumSet.size());
      }
      Enum localEnum = (Enum)this._enumDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      if (localEnum != null) {
        localEnumSet.add(localEnum);
      }
    }
    return localEnumSet;
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  public boolean isCachable()
  {
    if (this._enumType.getValueHandler() != null) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  public EnumSetDeserializer withDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    if (this._enumDeserializer == paramJsonDeserializer) {}
    for (;;)
    {
      return this;
      this = new EnumSetDeserializer(this._enumType, paramJsonDeserializer);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/EnumSetDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */