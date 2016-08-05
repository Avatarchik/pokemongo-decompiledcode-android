package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public class NumberSerializer
  extends StdScalarSerializer<Number>
{
  public static final NumberSerializer instance = new NumberSerializer(Number.class);
  protected final boolean _isInt;
  
  @Deprecated
  public NumberSerializer()
  {
    super(Number.class);
    this._isInt = false;
  }
  
  public NumberSerializer(Class<? extends Number> paramClass)
  {
    super(paramClass, false);
    if (paramClass == BigInteger.class) {
      bool = true;
    }
    this._isInt = bool;
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (this._isInt)
    {
      JsonIntegerFormatVisitor localJsonIntegerFormatVisitor = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (localJsonIntegerFormatVisitor != null) {
        localJsonIntegerFormatVisitor.numberType(JsonParser.NumberType.BIG_INTEGER);
      }
    }
    for (;;)
    {
      return;
      JsonNumberFormatVisitor localJsonNumberFormatVisitor = paramJsonFormatVisitorWrapper.expectNumberFormat(paramJavaType);
      if ((localJsonNumberFormatVisitor != null) && (handledType() == BigDecimal.class)) {
        localJsonNumberFormatVisitor.numberType(JsonParser.NumberType.BIG_DECIMAL);
      }
    }
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    if (this._isInt) {}
    for (String str = "integer";; str = "number") {
      return createSchemaNode(str, true);
    }
  }
  
  public void serialize(Number paramNumber, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if ((paramNumber instanceof BigDecimal)) {
      paramJsonGenerator.writeNumber((BigDecimal)paramNumber);
    }
    for (;;)
    {
      return;
      if ((paramNumber instanceof BigInteger)) {
        paramJsonGenerator.writeNumber((BigInteger)paramNumber);
      } else if ((paramNumber instanceof Integer)) {
        paramJsonGenerator.writeNumber(paramNumber.intValue());
      } else if ((paramNumber instanceof Long)) {
        paramJsonGenerator.writeNumber(paramNumber.longValue());
      } else if ((paramNumber instanceof Double)) {
        paramJsonGenerator.writeNumber(paramNumber.doubleValue());
      } else if ((paramNumber instanceof Float)) {
        paramJsonGenerator.writeNumber(paramNumber.floatValue());
      } else if (((paramNumber instanceof Byte)) || ((paramNumber instanceof Short))) {
        paramJsonGenerator.writeNumber(paramNumber.intValue());
      } else {
        paramJsonGenerator.writeNumber(paramNumber.toString());
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/NumberSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */