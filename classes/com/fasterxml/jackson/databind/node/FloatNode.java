package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FloatNode
  extends NumericNode
{
  protected final float _value;
  
  public FloatNode(float paramFloat)
  {
    this._value = paramFloat;
  }
  
  public static FloatNode valueOf(float paramFloat)
  {
    return new FloatNode(paramFloat);
  }
  
  public String asText()
  {
    return Float.toString(this._value);
  }
  
  public JsonToken asToken()
  {
    return JsonToken.VALUE_NUMBER_FLOAT;
  }
  
  public BigInteger bigIntegerValue()
  {
    return decimalValue().toBigInteger();
  }
  
  public boolean canConvertToInt()
  {
    if ((this._value >= -2.14748365E9F) && (this._value <= 2.14748365E9F)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean canConvertToLong()
  {
    if ((this._value >= -9.223372E18F) && (this._value <= 9.223372E18F)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
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
      if (paramObject == null)
      {
        bool = false;
      }
      else if ((paramObject instanceof FloatNode))
      {
        float f = ((FloatNode)paramObject)._value;
        if (Float.compare(this._value, f) != 0) {
          bool = false;
        }
      }
      else
      {
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
    return Float.floatToIntBits(this._value);
  }
  
  public int intValue()
  {
    return (int)this._value;
  }
  
  public boolean isFloat()
  {
    return true;
  }
  
  public boolean isFloatingPointNumber()
  {
    return true;
  }
  
  public long longValue()
  {
    return this._value;
  }
  
  public JsonParser.NumberType numberType()
  {
    return JsonParser.NumberType.FLOAT;
  }
  
  public Number numberValue()
  {
    return Float.valueOf(this._value);
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    paramJsonGenerator.writeNumber(this._value);
  }
  
  public short shortValue()
  {
    return (short)(int)this._value;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/FloatNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */