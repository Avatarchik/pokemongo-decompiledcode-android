package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
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
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;

@JacksonStdImpl
public final class StringArrayDeserializer
  extends StdDeserializer<String[]>
  implements ContextualDeserializer
{
  public static final StringArrayDeserializer instance = new StringArrayDeserializer();
  private static final long serialVersionUID = 1L;
  protected JsonDeserializer<String> _elementDeserializer;
  
  public StringArrayDeserializer()
  {
    super(String[].class);
    this._elementDeserializer = null;
  }
  
  protected StringArrayDeserializer(JsonDeserializer<?> paramJsonDeserializer)
  {
    super(String[].class);
    this._elementDeserializer = paramJsonDeserializer;
  }
  
  private final String[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject = null;
    if (!paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
    {
      if ((paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) && (paramDeserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) && (paramJsonParser.getText().length() == 0)) {
        return (String[])localObject;
      }
      throw paramDeserializationContext.mappingException(this._valueClass);
    }
    String[] arrayOfString = new String[1];
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {}
    for (;;)
    {
      arrayOfString[0] = localObject;
      localObject = arrayOfString;
      break;
      localObject = _parseString(paramJsonParser, paramDeserializationContext);
    }
  }
  
  protected final String[] _deserializeCustom(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject = localObjectBuffer.resetAndStart();
    JsonDeserializer localJsonDeserializer = this._elementDeserializer;
    int i = 0;
    for (;;)
    {
      try
      {
        if (paramJsonParser.nextTextValue() != null) {
          break label149;
        }
        JsonToken localJsonToken1 = paramJsonParser.getCurrentToken();
        JsonToken localJsonToken2 = JsonToken.END_ARRAY;
        if (localJsonToken1 == localJsonToken2)
        {
          String[] arrayOfString = (String[])localObjectBuffer.completeAndClearBuffer(arrayOfObject, i, String.class);
          paramDeserializationContext.returnObjectBuffer(localObjectBuffer);
          return arrayOfString;
        }
        if (localJsonToken1 == JsonToken.VALUE_NULL)
        {
          str = (String)localJsonDeserializer.getNullValue(paramDeserializationContext);
          if (i < arrayOfObject.length) {
            break label164;
          }
          arrayOfObject = localObjectBuffer.appendCompletedChunk(arrayOfObject);
          j = 0;
          i = j + 1;
          arrayOfObject[j] = str;
          continue;
        }
        str = (String)localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      }
      catch (Exception localException)
      {
        throw JsonMappingException.wrapWithPath(localException, String.class, i);
      }
      continue;
      label149:
      String str = (String)localJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      continue;
      label164:
      int j = i;
    }
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext paramDeserializationContext, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonDeserializer localJsonDeserializer1 = findConvertingContentDeserializer(paramDeserializationContext, paramBeanProperty, this._elementDeserializer);
    JavaType localJavaType = paramDeserializationContext.constructType(String.class);
    if (localJsonDeserializer1 == null) {}
    for (JsonDeserializer localJsonDeserializer2 = paramDeserializationContext.findContextualValueDeserializer(localJavaType, paramBeanProperty);; localJsonDeserializer2 = paramDeserializationContext.handleSecondaryContextualization(localJsonDeserializer1, paramBeanProperty, localJavaType))
    {
      if ((localJsonDeserializer2 != null) && (isDefaultDeserializer(localJsonDeserializer2))) {
        localJsonDeserializer2 = null;
      }
      if (this._elementDeserializer != localJsonDeserializer2) {
        this = new StringArrayDeserializer(localJsonDeserializer2);
      }
      return this;
    }
  }
  
  public String[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    if (!paramJsonParser.isExpectedStartArrayToken()) {}
    for (String[] arrayOfString = handleNonArray(paramJsonParser, paramDeserializationContext);; arrayOfString = _deserializeCustom(paramJsonParser, paramDeserializationContext))
    {
      return arrayOfString;
      if (this._elementDeserializer == null) {
        break;
      }
    }
    ObjectBuffer localObjectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject = localObjectBuffer.resetAndStart();
    int i = 0;
    for (;;)
    {
      try
      {
        String str = paramJsonParser.nextTextValue();
        if (str == null)
        {
          JsonToken localJsonToken1 = paramJsonParser.getCurrentToken();
          JsonToken localJsonToken2 = JsonToken.END_ARRAY;
          if (localJsonToken1 == localJsonToken2)
          {
            arrayOfString = (String[])localObjectBuffer.completeAndClearBuffer(arrayOfObject, i, String.class);
            paramDeserializationContext.returnObjectBuffer(localObjectBuffer);
            break;
          }
          if (localJsonToken1 != JsonToken.VALUE_NULL) {
            str = _parseString(paramJsonParser, paramDeserializationContext);
          }
        }
        int j;
        if (i >= arrayOfObject.length)
        {
          arrayOfObject = localObjectBuffer.appendCompletedChunk(arrayOfObject);
          j = 0;
          i = j + 1;
          arrayOfObject[j] = str;
        }
        else
        {
          j = i;
        }
      }
      catch (Exception localException)
      {
        throw JsonMappingException.wrapWithPath(localException, arrayOfObject, i + localObjectBuffer.bufferedSize());
      }
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/StringArrayDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */