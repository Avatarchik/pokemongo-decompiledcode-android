package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

public class StdKeySerializer
  extends StdSerializer<Object>
{
  public StdKeySerializer()
  {
    super(Object.class);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    return createSchemaNode("string");
  }
  
  public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    Class localClass = paramObject.getClass();
    String str;
    if (localClass == String.class) {
      str = (String)paramObject;
    }
    for (;;)
    {
      paramJsonGenerator.writeFieldName(str);
      for (;;)
      {
        return;
        if (localClass.isEnum())
        {
          Enum localEnum = (Enum)paramObject;
          if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING))
          {
            str = localEnum.toString();
            break;
          }
          str = localEnum.name();
          break;
        }
        if (!(paramObject instanceof Date)) {
          break label89;
        }
        paramSerializerProvider.defaultSerializeDateKey((Date)paramObject, paramJsonGenerator);
      }
      label89:
      if (localClass == Class.class) {
        str = ((Class)paramObject).getName();
      } else {
        str = paramObject.toString();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/StdKeySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */