package com.fasterxml.jackson.databind.jsontype;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import java.io.IOException;

public abstract class TypeDeserializer
{
  public static Object deserializeIfNatural(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JavaType paramJavaType)
    throws IOException
  {
    return deserializeIfNatural(paramJsonParser, paramDeserializationContext, paramJavaType.getRawClass());
  }
  
  public static Object deserializeIfNatural(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Class<?> paramClass)
    throws IOException
  {
    Object localObject = null;
    JsonToken localJsonToken = paramJsonParser.getCurrentToken();
    if (localJsonToken == null) {}
    for (;;)
    {
      return localObject;
      switch (localJsonToken)
      {
      default: 
        break;
      case ???: 
        if (paramClass.isAssignableFrom(String.class)) {
          localObject = paramJsonParser.getText();
        }
        break;
      case ???: 
        if (paramClass.isAssignableFrom(Integer.class)) {
          localObject = Integer.valueOf(paramJsonParser.getIntValue());
        }
        break;
      case ???: 
        if (paramClass.isAssignableFrom(Double.class)) {
          localObject = Double.valueOf(paramJsonParser.getDoubleValue());
        }
        break;
      case ???: 
        if (paramClass.isAssignableFrom(Boolean.class)) {
          localObject = Boolean.TRUE;
        }
        break;
      case ???: 
        if (paramClass.isAssignableFrom(Boolean.class)) {
          localObject = Boolean.FALSE;
        }
        break;
      }
    }
  }
  
  public abstract Object deserializeTypedFromAny(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException;
  
  public abstract Object deserializeTypedFromArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException;
  
  public abstract Object deserializeTypedFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException;
  
  public abstract Object deserializeTypedFromScalar(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException;
  
  public abstract TypeDeserializer forProperty(BeanProperty paramBeanProperty);
  
  public abstract Class<?> getDefaultImpl();
  
  public abstract String getPropertyName();
  
  public abstract TypeIdResolver getTypeIdResolver();
  
  public abstract JsonTypeInfo.As getTypeInclusion();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/TypeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */