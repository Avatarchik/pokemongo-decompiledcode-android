package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.io.IOException;

public class StackTraceElementDeserializer
  extends StdScalarDeserializer<StackTraceElement>
{
  private static final long serialVersionUID = 1L;
  
  public StackTraceElementDeserializer()
  {
    super(StackTraceElement.class);
  }
  
  public StackTraceElement deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    JsonToken localJsonToken1 = paramJsonParser.getCurrentToken();
    StackTraceElement localStackTraceElement;
    if (localJsonToken1 == JsonToken.START_OBJECT)
    {
      String str1 = "";
      String str2 = "";
      String str3 = "";
      int i = -1;
      for (;;)
      {
        JsonToken localJsonToken2 = paramJsonParser.nextValue();
        if (localJsonToken2 == JsonToken.END_OBJECT) {
          break;
        }
        String str4 = paramJsonParser.getCurrentName();
        if ("className".equals(str4)) {
          str1 = paramJsonParser.getText();
        } else if ("fileName".equals(str4)) {
          str3 = paramJsonParser.getText();
        } else if ("lineNumber".equals(str4))
        {
          if (localJsonToken2.isNumeric()) {
            i = paramJsonParser.getIntValue();
          } else {
            throw JsonMappingException.from(paramJsonParser, "Non-numeric token (" + localJsonToken2 + ") for property 'lineNumber'");
          }
        }
        else if ("methodName".equals(str4)) {
          str2 = paramJsonParser.getText();
        } else if (!"nativeMethod".equals(str4)) {
          handleUnknownProperty(paramJsonParser, paramDeserializationContext, this._valueClass, str4);
        }
      }
      localStackTraceElement = new StackTraceElement(str1, str2, str3, i);
    }
    do
    {
      return localStackTraceElement;
      if ((localJsonToken1 != JsonToken.START_ARRAY) || (!paramDeserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS))) {
        break;
      }
      paramJsonParser.nextToken();
      localStackTraceElement = deserialize(paramJsonParser, paramDeserializationContext);
    } while (paramJsonParser.nextToken() == JsonToken.END_ARRAY);
    throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'java.lang.StackTraceElement' value but there was more than a single value in the array");
    throw paramDeserializationContext.mappingException(this._valueClass, localJsonToken1);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/StackTraceElementDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */