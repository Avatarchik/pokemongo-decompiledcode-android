package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

@JacksonStdImpl
public class MapEntryDeserializer
  extends ContainerDeserializerBase<Map.Entry<Object, Object>>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final KeyDeserializer _keyDeserializer;
  protected final JavaType _type;
  protected final JsonDeserializer<Object> _valueDeserializer;
  protected final TypeDeserializer _valueTypeDeserializer;
  
  public MapEntryDeserializer(JavaType paramJavaType, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramJavaType);
    if (paramJavaType.containedTypeCount() != 2) {
      throw new IllegalArgumentException("Missing generic type information for " + paramJavaType);
    }
    this._type = paramJavaType;
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
  }
  
  protected MapEntryDeserializer(MapEntryDeserializer paramMapEntryDeserializer)
  {
    super(paramMapEntryDeserializer._type);
    this._type = paramMapEntryDeserializer._type;
    this._keyDeserializer = paramMapEntryDeserializer._keyDeserializer;
    this._valueDeserializer = paramMapEntryDeserializer._valueDeserializer;
    this._valueTypeDeserializer = paramMapEntryDeserializer._valueTypeDeserializer;
  }
  
  protected MapEntryDeserializer(MapEntryDeserializer paramMapEntryDeserializer, KeyDeserializer paramKeyDeserializer, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramMapEntryDeserializer._type);
    this._type = paramMapEntryDeserializer._type;
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    KeyDeserializer localKeyDeserializer = this._keyDeserializer;
    JsonDeserializer localJsonDeserializer1;
    JavaType localJavaType;
    if (localKeyDeserializer == null)
    {
      localKeyDeserializer = paramDeserializationContext.findKeyDeserializer(this._type.containedType(0), paramBeanProperty);
      localJsonDeserializer1 = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, this._valueDeserializer);
      localJavaType = this._type.containedType(1);
      if (localJsonDeserializer1 != null) {
        break label110;
      }
    }
    label110:
    for (JsonDeserializer localJsonDeserializer2 = paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramBeanProperty);; localJsonDeserializer2 = paramDeserializationContext.handleSecondaryContextualization(localJsonDeserializer1, paramBeanProperty, localJavaType))
    {
      TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
      if (localTypeDeserializer != null) {
        localTypeDeserializer = localTypeDeserializer.forProperty(paramBeanProperty);
      }
      return withResolved(localKeyDeserializer, localTypeDeserializer, localJsonDeserializer2);
      if (!(localKeyDeserializer instanceof ContextualKeyDeserializer)) {
        break;
      }
      localKeyDeserializer = ((ContextualKeyDeserializer)localKeyDeserializer).createContextual(paramDeserializationContext, paramBeanProperty);
      break;
    }
  }
  
  public Map.Entry<Object, Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken1 = paramJsonParser.getCurrentToken();
    if ((localJsonToken1 != JsonToken.START_OBJECT) && (localJsonToken1 != JsonToken.FIELD_NAME) && (localJsonToken1 != JsonToken.END_OBJECT)) {}
    Object localObject1;
    Object localObject2;
    label275:
    for (Object localObject3 = (Map.Entry)_deserializeFromEmpty(paramJsonParser, paramDeserializationContext);; localObject3 = new AbstractMap.SimpleEntry(localObject1, localObject2))
    {
      return (Map.Entry<Object, Object>)localObject3;
      if (localJsonToken1 == JsonToken.START_OBJECT) {
        localJsonToken1 = paramJsonParser.nextToken();
      }
      if (localJsonToken1 != JsonToken.FIELD_NAME)
      {
        if (localJsonToken1 == JsonToken.END_OBJECT) {
          throw paramDeserializationContext.mappingException("Can not deserialize a Map.Entry out of empty JSON Object");
        }
        throw paramDeserializationContext.mappingException(handledType(), localJsonToken1);
      }
      KeyDeserializer localKeyDeserializer = this._keyDeserializer;
      JsonDeserializer localJsonDeserializer = this._valueDeserializer;
      TypeDeserializer localTypeDeserializer = this._valueTypeDeserializer;
      String str = paramJsonParser.getCurrentName();
      localObject1 = localKeyDeserializer.deserializeKey(str, paramDeserializationContext);
      localObject2 = null;
      JsonToken localJsonToken2 = paramJsonParser.nextToken();
      JsonToken localJsonToken3;
      for (;;)
      {
        try
        {
          if (localJsonToken2 != JsonToken.VALUE_NULL) {
            continue;
          }
          Object localObject5 = localJsonDeserializer.getNullValue(paramDeserializationContext);
          localObject2 = localObject5;
        }
        catch (Exception localException)
        {
          Object localObject4;
          wrapAndThrow(localException, Map.Entry.class, str);
          continue;
        }
        localJsonToken3 = paramJsonParser.nextToken();
        if (localJsonToken3 == JsonToken.END_OBJECT) {
          break label275;
        }
        if (localJsonToken3 != JsonToken.FIELD_NAME) {
          break;
        }
        throw paramDeserializationContext.mappingException("Problem binding JSON into Map.Entry: more than one entry in JSON (second field: '" + paramJsonParser.getCurrentName() + "')");
        if (localTypeDeserializer == null)
        {
          localObject2 = localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        }
        else
        {
          localObject4 = localJsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
          localObject2 = localObject4;
        }
      }
      throw paramDeserializationContext.mappingException("Problem binding JSON into Map.Entry: unexpected content after JSON Object entry: " + localJsonToken3);
    }
  }
  
  public Map.Entry<Object, Object> deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map.Entry<Object, Object> paramEntry)
    throws IOException
  {
    throw new IllegalStateException("Can not update Map.Entry values");
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
    return this._type.containedType(1);
  }
  
  public JavaType getValueType()
  {
    return this._type;
  }
  
  protected MapEntryDeserializer withResolved(KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
  {
    if ((this._keyDeserializer == paramKeyDeserializer) && (this._valueDeserializer == paramJsonDeserializer) && (this._valueTypeDeserializer == paramTypeDeserializer)) {}
    for (;;)
    {
      return this;
      this = new MapEntryDeserializer(this, paramKeyDeserializer, paramJsonDeserializer, paramTypeDeserializer);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/MapEntryDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */