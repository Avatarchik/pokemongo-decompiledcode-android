package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;

public class POJONode
  extends ValueNode
{
  protected final Object _value;
  
  public POJONode(Object paramObject)
  {
    this._value = paramObject;
  }
  
  protected boolean _pojoEquals(POJONode paramPOJONode)
  {
    boolean bool;
    if (this._value == null) {
      if (paramPOJONode._value == null) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      bool = false;
      continue;
      bool = this._value.equals(paramPOJONode._value);
    }
  }
  
  public boolean asBoolean(boolean paramBoolean)
  {
    if ((this._value != null) && ((this._value instanceof Boolean))) {
      paramBoolean = ((Boolean)this._value).booleanValue();
    }
    return paramBoolean;
  }
  
  public double asDouble(double paramDouble)
  {
    if ((this._value instanceof Number)) {
      paramDouble = ((Number)this._value).doubleValue();
    }
    return paramDouble;
  }
  
  public int asInt(int paramInt)
  {
    if ((this._value instanceof Number)) {
      paramInt = ((Number)this._value).intValue();
    }
    return paramInt;
  }
  
  public long asLong(long paramLong)
  {
    if ((this._value instanceof Number)) {
      paramLong = ((Number)this._value).longValue();
    }
    return paramLong;
  }
  
  public String asText()
  {
    if (this._value == null) {}
    for (String str = "null";; str = this._value.toString()) {
      return str;
    }
  }
  
  public String asText(String paramString)
  {
    if (this._value == null) {}
    for (;;)
    {
      return paramString;
      paramString = this._value.toString();
    }
  }
  
  public JsonToken asToken()
  {
    return JsonToken.VALUE_EMBEDDED_OBJECT;
  }
  
  public byte[] binaryValue()
    throws IOException
  {
    if ((this._value instanceof byte[])) {}
    for (byte[] arrayOfByte = (byte[])this._value;; arrayOfByte = super.binaryValue()) {
      return arrayOfByte;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (paramObject == this) {}
    for (bool = true;; bool = _pojoEquals((POJONode)paramObject)) {
      do
      {
        return bool;
      } while ((paramObject == null) || (!(paramObject instanceof POJONode)));
    }
  }
  
  public JsonNodeType getNodeType()
  {
    return JsonNodeType.POJO;
  }
  
  public Object getPojo()
  {
    return this._value;
  }
  
  public int hashCode()
  {
    return this._value.hashCode();
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (this._value == null) {
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      if ((this._value instanceof JsonSerializable)) {
        ((JsonSerializable)this._value).serialize(paramJsonGenerator, paramSerializerProvider);
      } else {
        paramJsonGenerator.writeObject(this._value);
      }
    }
  }
  
  public String toString()
  {
    String str;
    if ((this._value instanceof byte[]))
    {
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(((byte[])(byte[])this._value).length);
      str = String.format("(binary value of %d bytes)", arrayOfObject2);
    }
    for (;;)
    {
      return str;
      if ((this._value instanceof RawValue))
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = ((RawValue)this._value).toString();
        str = String.format("(raw value '%s')", arrayOfObject1);
      }
      else
      {
        str = String.valueOf(this._value);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/POJONode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */