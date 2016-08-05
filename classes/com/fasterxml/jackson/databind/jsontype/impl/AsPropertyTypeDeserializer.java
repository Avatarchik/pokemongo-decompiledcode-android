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

public class AsPropertyTypeDeserializer
  extends AsArrayTypeDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final JsonTypeInfo.As _inclusion;
  
  public AsPropertyTypeDeserializer(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, String paramString, boolean paramBoolean, Class<?> paramClass)
  {
    this(paramJavaType, paramTypeIdResolver, paramString, paramBoolean, paramClass, JsonTypeInfo.As.PROPERTY);
  }
  
  public AsPropertyTypeDeserializer(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, String paramString, boolean paramBoolean, Class<?> paramClass, JsonTypeInfo.As paramAs)
  {
    super(paramJavaType, paramTypeIdResolver, paramString, paramBoolean, paramClass);
    this._inclusion = paramAs;
  }
  
  public AsPropertyTypeDeserializer(AsPropertyTypeDeserializer paramAsPropertyTypeDeserializer, BeanProperty paramBeanProperty)
  {
    super(paramAsPropertyTypeDeserializer, paramBeanProperty);
    this._inclusion = paramAsPropertyTypeDeserializer._inclusion;
  }
  
  protected Object _deserializeTypedForId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TokenBuffer paramTokenBuffer)
    throws IOException
  {
    String str = paramJsonParser.getText();
    JsonDeserializer localJsonDeserializer = _findDeserializer(paramDeserializationContext, str);
    if (this._typeIdVisible)
    {
      if (paramTokenBuffer == null) {
        paramTokenBuffer = new TokenBuffer(paramJsonParser, paramDeserializationContext);
      }
      paramTokenBuffer.writeFieldName(paramJsonParser.getCurrentName());
      paramTokenBuffer.writeString(str);
    }
    if (paramTokenBuffer != null) {
      paramJsonParser = JsonParserSequence.createFlattened(paramTokenBuffer.asParser(paramJsonParser), paramJsonParser);
    }
    paramJsonParser.nextToken();
    return localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  protected Object _deserializeTypedUsingDefaultImpl(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TokenBuffer paramTokenBuffer)
    throws IOException
  {
    JsonDeserializer localJsonDeserializer = _findDefaultImplDeserializer(paramDeserializationContext);
    Object localObject;
    if (localJsonDeserializer != null)
    {
      if (paramTokenBuffer != null)
      {
        paramTokenBuffer.writeEndObject();
        paramJsonParser = paramTokenBuffer.asParser(paramJsonParser);
        paramJsonParser.nextToken();
      }
      localObject = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
    }
    for (;;)
    {
      return localObject;
      localObject = TypeDeserializer.deserializeIfNatural(paramJsonParser, paramDeserializationContext, this._baseType);
      if (localObject == null)
      {
        if (paramJsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
          break;
        }
        localObject = super.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
      }
    }
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.FIELD_NAME, "missing property '" + this._typePropertyName + "' that is to contain type id  (for class " + baseTypeName() + ")");
  }
  
  public Object deserializeTypedFromAny(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramJsonParser.getCurrentToken() == JsonToken.START_ARRAY) {}
    for (Object localObject = super.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);; localObject = deserializeTypedFromObject(paramJsonParser, paramDeserializationContext)) {
      return localObject;
    }
  }
  
  public Object deserializeTypedFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
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
    for (;;)
    {
      return localObject1;
      JsonToken localJsonToken = paramJsonParser.getCurrentToken();
      label48:
      TokenBuffer localTokenBuffer;
      if (localJsonToken == JsonToken.START_OBJECT)
      {
        localJsonToken = paramJsonParser.nextToken();
        localTokenBuffer = null;
      }
      for (;;)
      {
        if (localJsonToken != JsonToken.FIELD_NAME) {
          break label169;
        }
        String str = paramJsonParser.getCurrentName();
        paramJsonParser.nextToken();
        if (this._typePropertyName.equals(str))
        {
          localObject1 = _deserializeTypedForId(paramJsonParser, paramDeserializationContext, localTokenBuffer);
          break;
          if (localJsonToken == JsonToken.START_ARRAY)
          {
            localObject1 = _deserializeTypedUsingDefaultImpl(paramJsonParser, paramDeserializationContext, null);
            break;
          }
          if (localJsonToken == JsonToken.FIELD_NAME) {
            break label48;
          }
          localObject1 = _deserializeTypedUsingDefaultImpl(paramJsonParser, paramDeserializationContext, null);
          break;
        }
        if (localTokenBuffer == null) {
          localTokenBuffer = new TokenBuffer(paramJsonParser, paramDeserializationContext);
        }
        localTokenBuffer.writeFieldName(str);
        localTokenBuffer.copyCurrentStructure(paramJsonParser);
        localJsonToken = paramJsonParser.nextToken();
      }
      label169:
      localObject1 = _deserializeTypedUsingDefaultImpl(paramJsonParser, paramDeserializationContext, localTokenBuffer);
    }
  }
  
  public TypeDeserializer forProperty(BeanProperty paramBeanProperty)
  {
    if (paramBeanProperty == this._property) {}
    for (;;)
    {
      return this;
      this = new AsPropertyTypeDeserializer(this, paramBeanProperty);
    }
  }
  
  public JsonTypeInfo.As getTypeInclusion()
  {
    return this._inclusion;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/impl/AsPropertyTypeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */