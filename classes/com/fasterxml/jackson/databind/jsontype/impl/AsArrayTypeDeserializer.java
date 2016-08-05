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

public class AsArrayTypeDeserializer
  extends TypeDeserializerBase
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  public AsArrayTypeDeserializer(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, String paramString, boolean paramBoolean, Class<?> paramClass)
  {
    super(paramJavaType, paramTypeIdResolver, paramString, paramBoolean, paramClass);
  }
  
  public AsArrayTypeDeserializer(AsArrayTypeDeserializer paramAsArrayTypeDeserializer, BeanProperty paramBeanProperty)
  {
    super(paramAsArrayTypeDeserializer, paramBeanProperty);
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
    boolean bool;
    do
    {
      return localObject1;
      bool = paramJsonParser.isExpectedStartArrayToken();
      String str = _locateTypeId(paramJsonParser, paramDeserializationContext);
      JsonDeserializer localJsonDeserializer = _findDeserializer(paramDeserializationContext, str);
      if ((this._typeIdVisible) && (!_usesExternalId()) && (paramJsonParser.getCurrentToken() == JsonToken.START_OBJECT))
      {
        TokenBuffer localTokenBuffer = new TokenBuffer(null, false);
        localTokenBuffer.writeStartObject();
        localTokenBuffer.writeFieldName(this._typePropertyName);
        localTokenBuffer.writeString(str);
        paramJsonParser = JsonParserSequence.createFlattened(localTokenBuffer.asParser(paramJsonParser), paramJsonParser);
        paramJsonParser.nextToken();
      }
      localObject1 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
    } while ((!bool) || (paramJsonParser.nextToken() == JsonToken.END_ARRAY));
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "expected closing END_ARRAY after type information and deserialized value");
  }
  
  protected String _locateTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    String str;
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      if (this._defaultImpl != null) {
        str = this._idResolver.idFromBaseType();
      }
    }
    for (;;)
    {
      return str;
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.START_ARRAY, "need JSON Array to contain As.WRAPPER_ARRAY type information for class " + baseTypeName());
      if (paramJsonParser.nextToken() == JsonToken.VALUE_STRING)
      {
        str = paramJsonParser.getText();
        paramJsonParser.nextToken();
      }
      else
      {
        if (this._defaultImpl == null) {
          break;
        }
        str = this._idResolver.idFromBaseType();
      }
    }
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.VALUE_STRING, "need JSON String that contains type id (for subtype of " + baseTypeName() + ")");
  }
  
  protected boolean _usesExternalId()
  {
    return false;
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
      this = new AsArrayTypeDeserializer(this, paramBeanProperty);
    }
  }
  
  public JsonTypeInfo.As getTypeInclusion()
  {
    return JsonTypeInfo.As.WRAPPER_ARRAY;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/impl/AsArrayTypeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */