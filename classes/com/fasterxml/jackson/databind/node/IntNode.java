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

public class IntNode
  extends NumericNode
{
  private static final IntNode[] CANONICALS = new IntNode[12];
  static final int MAX_CANONICAL = 10;
  static final int MIN_CANONICAL = -1;
  protected final int _value;
  
  static
  {
    for (int i = 0; i < 12; i++) {
      CANONICALS[i] = new IntNode(i - 1);
    }
  }
  
  public IntNode(int paramInt)
  {
    this._value = paramInt;
  }
  
  public static IntNode valueOf(int paramInt)
  {
    if ((paramInt > 10) || (paramInt < -1)) {}
    for (IntNode localIntNode = new IntNode(paramInt);; localIntNode = CANONICALS[(paramInt + 1)]) {
      return localIntNode;
    }
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
      } else if ((paramObject instanceof IntNode))
      {
        if (((IntNode)paramObject)._value != this._value) {
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
  
  public boolean isInt()
  {
    return true;
  }
  
  public boolean isIntegralNumber()
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
    return Integer.valueOf(this._value);
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException
  {
    paramJsonGenerator.writeNumber(this._value);
  }
  
  public short shortValue()
  {
    return (short)this._value;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/IntNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */