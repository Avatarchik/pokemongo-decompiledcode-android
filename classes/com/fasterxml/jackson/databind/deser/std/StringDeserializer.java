package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

@JacksonStdImpl
public final class StringDeserializer
  extends StdScalarDeserializer<String>
{
  public static final StringDeserializer instance = new StringDeserializer();
  private static final long serialVersionUID = 1L;
  
  public StringDeserializer()
  {
    super(String.class);
  }
  
  public String deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    Object localObject1;
    if (localJsonToken == JsonToken.VALUE_STRING) {
      localObject1 = paramJsonParser.getText();
    }
    for (;;)
    {
      return (String)localObject1;
      if ((localJsonToken == JsonToken.START_ARRAY) && (paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)))
      {
        paramJsonParser.nextToken();
        localObject1 = _parseString(paramJsonParser, paramDeserializationContext);
        if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
          throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'String' value but there was more than a single value in the array");
        }
      }
      else if (localJsonToken == JsonToken.VALUE_EMBEDDED_OBJECT)
      {
        Object localObject2 = paramJsonParser.getEmbeddedObject();
        if (localObject2 == null) {
          localObject1 = null;
        } else if ((localObject2 instanceof byte[])) {
          localObject1 = Base64Variants.getDefaultVariant().encode((byte[])localObject2, false);
        } else {
          localObject1 = localObject2.toString();
        }
      }
      else
      {
        String str = paramJsonParser.getValueAsString();
        if (str == null) {
          break;
        }
        localObject1 = str;
      }
    }
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken);
  }
  
  public String deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public boolean isCachable()
  {
    return true;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/StringDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */