package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ShortNode
  extends NumericNode
{
  protected final short _value;
  
  public ShortNode(short paramShort)
  {
    this._value = paramShort;
  }
  
  public static ShortNode valueOf(short paramShort)
  {
    return new ShortNode(paramShort);
  }
  
  public boolean asBoolean(boolean paramBoolean)
  {
    if (this._value != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String asText()
  {
    return NumberOutput.toString(this._value);
  }
  
  public JsonToken asToken()
  {
    return JsonToken.VALUE_NUMBER_INT;
  }
  
  public BigInteger bigIntegerValue()
  {
    return BigInteger.valueOf(this._value);
  }
  
  public boolean canConvertToInt()
  {
    return true;
  }
  
  public boolean canConvertToLong()
  {
    return true;
  }
  
  public BigDecimal decimalValue()
  {
    return BigDecimal.valueOf(this._value);
  }
  
  public double doubleValue()
  {
    return this._value;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (paramObject == null) {
        bool = false;
      } else if ((paramObject instanceof ShortNode))
      {
        if (((ShortNode)paramObject)._value != this._value) {
          bool = false;
        }
      }
      else {
        bool = false;
      }
    }
  }
  
  public float floatValue()
  {
    return this._value;
  }
  
  public int hashCode()
  {
    return this._value;
  }
  
  public int intValue()
  {
    return this._value;
  }
  
  public boolean isIntegralNumber()
  {
    return true;
  }
  
  public boolean isShort()
  {
    return true;
  }
  
  public long longValue()
  {
    return this._value;
  }
  
  public JsonParser.NumberType numberType()
  {
    return JsonParser.NumberType.INT;
  }
  
  public Number numberValue()
  {
    return Short.valueOf(this._value);
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException
  {
    paramJsonGenerator.writeNumber(this._value);
  }
  
  public short shortValue()
  {
    return this._value;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/ShortNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */