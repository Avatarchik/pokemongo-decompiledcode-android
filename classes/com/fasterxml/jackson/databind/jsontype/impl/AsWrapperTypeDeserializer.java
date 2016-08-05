package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;

public class AsWrapperTypeDeserializer
  extends TypeDeserializerBase
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  public AsWrapperTypeDeserializer(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, String paramString, boolean paramBoolean, Class<?> paramClass)
  {
    super(paramJavaType, paramTypeIdResolver, paramString, paramBoolean, paramClass);
  }
  
  protected AsWrapperTypeDeserializer(AsWrapperTypeDeserializer paramAsWrapperTypeDeserializer, BeanProperty paramBeanProperty)
  {
    super(paramAsWrapperTypeDeserializer, paramBeanProperty);
  }
  
  protected Object _deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject1;
    if (paramJsonParser.canReadTypeId())
    {
      Object localObject2 = paramJsonParser.getTypeId();
      if (localObject2 != null) {
        localObject1 = _deserializeWithNativeTypeId(paramJsonParser, paramDeserializationContext, localObject2);
      }
    }
    do
    {
      return localObject1;
      if (paramJsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.START_OBJECT, "need JSON Object to contain As.WRAPPER_OBJECT type information for class " + baseTypeName());
      }
      if (paramJsonParser.nextToken() != JsonToken.FIELD_NAME) {
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.FIELD_NAME, "need JSON String that contains type id (for subtype of " + baseTypeName() + ")");
      }
      String str = paramJsonParser.getText();
      JsonDeserializer localJsonDeserializer = _findDeserializer(paramDeserializationContext, str);
      paramJsonParser.nextToken();
      if ((this._typeIdVisible) && (paramJsonParser.getCurrentToken() == JsonToken.START_OBJECT))
      {
        TokenBuffer localTokenBuffer = new TokenBuffer(null, false);
        localTokenBuffer.writeStartObject();
        localTokenBuffer.writeFieldName(this._typePropertyName);
        localTokenBuffer.writeString(str);
        paramJsonParser = JsonParserSequence.createFlattened(localTokenBuffer.asParser(paramJsonParser), paramJsonParser);
        paramJsonParser.nextToken();
      }
      localObject1 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
    } while (paramJsonParser.nextToken() == JsonToken.END_OBJECT);
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_OBJECT, "expected closing END_OBJECT after type information and deserialized value");
  }
  
  public Object deserializeTypedFromAny(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return _deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserializeTypedFromArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return _deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserializeTypedFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return _deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserializeTypedFromScalar(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return _deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public TypeDeserializer forProperty(BeanProperty paramBeanProperty)
  {
    if (paramBeanProperty == this._property) {}
    for (;;)
    {
      return this;
      this = new AsWrapperTypeDeserializer(this, paramBeanProperty);
    }
  }
  
  public JsonTypeInfo.As getTypeInclusion()
  {
    return JsonTypeInfo.As.WRAPPER_OBJECT;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/impl/AsWrapperTypeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */