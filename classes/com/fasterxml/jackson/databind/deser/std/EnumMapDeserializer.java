package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.EnumMap;

public class EnumMapDeserializer
  extends ContainerDeserializerBase<EnumMap<?, ?>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final Class<?> _enumClass;
  protected KeyDeserializer _keyDeserializer;
  protected final JavaType _mapType;
  protected JsonDeserializer<Object> _valueDeserializer;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public EnumMapDeserializer(JavaType paramJavaType, KeyDeserializer paramKeyDeserializer, JsonDeserializer<?> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramJavaType);
    this._mapType = paramJavaType;
    this._enumClass = paramJavaType.getKeyType().getRawClass();
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
  }
  
  protected EnumMap<?, ?> constructMap()
  {
    return new EnumMap(this._enumClass);
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    KeyDeserializer localKeyDeserializer = this._keyDeserializer;
    if (localKeyDeserializer == null) {
      localKeyDeserializer = paramDeserializationContext.findKeyDeserializer(this._mapType.getKeyType(), paramBeanProperty);
    }
    JsonDeserializer localJsonDeserializer1 = this._valueDeserializer;
    JavaType localJavaType = this._mapType.getContentType();
    if (localJsonDeserializer1 == null) {}
    for (JsonDeserializer localJsonDeserializer2 = paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramBeanProperty);; localJsonDeserializer2 = paramDeserializationContext.handleSecondaryContextualization(localJsonDeserializer1, paramBeanProperty, localJavaType))
    {
      TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
      if (localTypeDeserializer != null) {
        localTypeDeserializer = localTypeDeserializer.forProperty(paramBeanProperty);
      }
      return withResolved(localKeyDeserializer, localJsonDeserializer2, localTypeDeserializer);
    }
  }
  
  public EnumMap<?, ?> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (paramJsonParser.getCurrentToken() != JsonToken.START_OBJECT) {}
    EnumMap localEnumMap;
    for (Object localObject1 = (EnumMap)_deserializeFromEmpty(paramJsonParser, paramDeserializationContext);; localObject1 = localEnumMap) {
      for (;;)
      {
        return (EnumMap<?, ?>)localObject1;
        localEnumMap = constructMap();
        JsonDeserializer localJsonDeserializer = this._valueDeserializer;
        TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
        for (;;)
        {
          if (paramJsonParser.nextToken() == JsonToken.FIELD_NAME)
          {
            String str = paramJsonParser.getCurrentName();
            Enum localEnum = (Enum)this._keyDeserializer.deserializeKey(str, paramDeserializationContext);
            if (localEnum == null)
            {
              if (!paramDeserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                throw paramDeserializationContext.weirdStringException(str, this._enumClass, "value not one of declared Enum instance names for " + this._mapType.getKeyType());
              }
              paramJsonParser.nextToken();
              paramJsonParser.skipChildren();
            }
            else
            {
              JsonToken localJsonToken = paramJsonParser.nextToken();
              try
              {
                Object localObject3;
                if (localJsonToken == JsonToken.VALUE_NULL)
                {
                  Object localObject4 = localJsonDeserializer.getNullValue(paramDeserializationContext);
                  localObject3 = localObject4;
                }
                for (;;)
                {
                  localEnumMap.put(localEnum, localObject3);
                  break;
                  if (localTypeDeserializer == null)
                  {
                    localObject3 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
                  }
                  else
                  {
                    Object localObject2 = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
                    localObject3 = localObject2;
                  }
                }
              }
              catch (Exception localException)
              {
                wrapAndThrow(localException, localEnumMap, str);
                localObject1 = null;
              }
            }
          }
        }
      }
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return this._valueDeserializer;
  }
  
  public JavaType getContentType()
  {
    return this._mapType.getContentType();
  }
  
  public boolean isCachable()
  {
    if ((this._valueDeserializer == null) && (this._keyDeserializer == null) && (this._valueTypeDeserializer == null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public EnumMapDeserializer withResolved(KeyDeserializer paramKeyDeserializer, JsonDeserializer<?> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    if ((paramKeyDeserializer == this._keyDeserializer) && (paramJsonDeserializer == this._valueDeserializer) && (paramTypeDeserializer == this._valueTypeDeserializer)) {}
    for (;;)
    {
      return this;
      this = new EnumMapDeserializer(this._mapType, paramKeyDeserializer, paramJsonDeserializer, this._valueTypeDeserializer);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/EnumMapDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */