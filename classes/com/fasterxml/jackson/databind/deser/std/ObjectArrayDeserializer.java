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
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.lang.reflect.Array;

@JacksonStdImpl
public class ObjectArrayDeserializer
  extends ContainerDeserializerBase<Object[]>
  implements ContextualDeserializer
{
  private static final long serialVersionUID = 1L;
  protected final ArrayType _arrayType;
  protected final Class<?> _elementClass;
  protected JsonDeserializer<Object> _elementDeserializer;
  protected final TypeDeserializer _elementTypeDeserializer;
  protected final boolean _untyped;
  
  public ObjectArrayDeserializer(ArrayType paramArrayType, JsonDeserializer<Object> paramJsonDeserializer, TypeDeserializer paramTypeDeserializer)
  {
    super(paramArrayType);
    this._arrayType = paramArrayType;
    this._elementClass = paramArrayType.getContentType().getRawClass();
    if (this._elementClass == Object.class) {}
    for (boolean bool = true;; bool = false)
    {
      this._untyped = bool;
      this._elementDeserializer = paramJsonDeserializer;
      this._elementTypeDeserializer = paramTypeDeserializer;
      return;
    }
  }
  
  private final Object[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {}
    for (Object localObject2 = null;; localObject2 = deserializeFromBase64(paramJsonParser, paramDeserializationContext))
    {
      return (Object[])localObject2;
      if (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
        break label88;
      }
      if ((paramJsonParser.getCurrentToken() != JsonToken.VALUE_STRING) || (this._elementClass != Byte.class)) {
        break;
      }
    }
    throw paramDeserializationContext.mappingException(this._arrayType.getRawClass());
    label88:
    Object localObject1;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL)
    {
      localObject1 = this._elementDeserializer.getNullValue(paramDeserializationContext);
      label107:
      if (!this._untyped) {
        break label165;
      }
    }
    label165:
    for (localObject2 = new Object[1];; localObject2 = (Object[])Array.newInstance(this._elementClass, 1))
    {
      localObject2[0] = localObject1;
      break;
      if (this._elementTypeDeserializer == null)
      {
        localObject1 = this._elementDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        break label107;
      }
      localObject1 = this._elementDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, this._elementTypeDeserializer);
      break label107;
    }
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer1 = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, this._elementDeserializer);
    JavaType localJavaType = this._arrayType.getContentType();
    if (localJsonDeserializer1 == null) {}
    for (JsonDeserializer localJsonDeserializer2 = paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramBeanProperty);; localJsonDeserializer2 = paramDeserializationContext.handleSecondaryContextualization(localJsonDeserializer1, paramBeanProperty, localJavaType))
    {
      TypeDeserializer localTypeDeserializer = this._elementTypeDeserializer;
      if (localTypeDeserializer != null) {
        localTypeDeserializer = localTypeDeserializer.forProperty(paramBeanProperty);
      }
      return withDeserializer(localTypeDeserializer, localJsonDeserializer2);
    }
  }
  
  public Object[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    Object[] arrayOfObject2;
    if (!paramJsonParser.isExpectedStartArrayToken())
    {
      arrayOfObject2 = handleNonArray(paramJsonParser, paramDeserializationContext);
      return arrayOfObject2;
    }
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject1 = localObjectBuffer.resetAndStart();
    int i = 0;
    TypeDeserializer localTypeDeserializer = this._elementTypeDeserializer;
    for (;;)
    {
      try
      {
        JsonToken localJsonToken = paramJsonParser.nextToken();
        if (localJsonToken == JsonToken.END_ARRAY) {
          break label161;
        }
        if (localJsonToken == JsonToken.VALUE_NULL)
        {
          localObject2 = this._elementDeserializer.getNullValue(paramDeserializationContext);
          if (i < arrayOfObject1.length) {
            break label203;
          }
          arrayOfObject1 = localObjectBuffer.appendCompletedChunk(arrayOfObject1);
          j = 0;
          i = j + 1;
          arrayOfObject1[j] = localObject2;
          continue;
        }
        if (localTypeDeserializer != null) {
          break label141;
        }
      }
      catch (Exception localException)
      {
        throw JsonMappingException.wrapWithPath(localException, arrayOfObject1, i + localObjectBuffer.bufferedSize());
      }
      Object localObject2 = this._elementDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      continue;
      label141:
      Object localObject1 = this._elementDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, localTypeDeserializer);
      localObject2 = localObject1;
      continue;
      label161:
      if (this._untyped) {}
      for (arrayOfObject2 = localObjectBuffer.completeAndClearBuffer(arrayOfObject1, i);; arrayOfObject2 = localObjectBuffer.completeAndClearBuffer(arrayOfObject1, i, this._elementClass))
      {
        paramDeserializationContext.returnObjectBuffer(localObjectBuffer);
        break;
      }
      label203:
      int j = i;
    }
  }
  
  protected Byte[] deserializeFromBase64(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException, JsonProcessingException
  {
    byte[] arrayOfByte = paramJsonParser.getBinaryValue(paramDeserializationContext.getBase64Variant());
    Byte[] arrayOfByte1 = new Byte[arrayOfByte.length];
    int i = 0;
    int j = arrayOfByte.length;
    while (i < j)
    {
      arrayOfByte1[i] = Byte.valueOf(arrayOfByte[i]);
      i++;
    }
    return arrayOfByte1;
  }
  
  public Object[] deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return (Object[])paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer<Object> getContentDeserializer()
  {
    return this._elementDeserializer;
  }
  
  public JavaType getContentType()
  {
    return this._arrayType.getContentType();
  }
  
  public boolean isCachable()
  {
    if ((this._elementDeserializer == null) && (this._elementTypeDeserializer == null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public ObjectArrayDeserializer withDeserializer(TypeDeserializer paramTypeDeserializer, JsonDeserializer<?> paramJsonDeserializer)
  {
    if ((paramJsonDeserializer == this._elementDeserializer) && (paramTypeDeserializer == this._elementTypeDeserializer)) {}
    for (;;)
    {
      return this;
      this = new ObjectArrayDeserializer(this._arrayType, paramJsonDeserializer, paramTypeDeserializer);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/ObjectArrayDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */