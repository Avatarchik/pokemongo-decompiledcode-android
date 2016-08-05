package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class BooleanNode
  extends ValueNode
{
  public static final BooleanNode FALSE = new BooleanNode(false);
  public static final BooleanNode TRUE = new BooleanNode(true);
  private final boolean _value;
  
  private BooleanNode(boolean paramBoolean)
  {
    this._value = paramBoolean;
  }
  
  public static BooleanNode getFalse()
  {
    return FALSE;
  }
  
  public static BooleanNode getTrue()
  {
    return TRUE;
  }
  
  public static BooleanNode valueOf(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (BooleanNode localBooleanNode = TRUE;; localBooleanNode = FALSE) {
      return localBooleanNode;
    }
  }
  
  public boolean asBoolean()
  {
    return this._value;
  }
  
  public boolean asBoolean(boolean paramBoolean)
  {
    return this._value;
  }
  
  public double asDouble(double paramDouble)
  {
    if (this._value) {}
    for (double d = 1.0D;; d = 0.0D) {
      return d;
    }
  }
  
  public int asInt(int paramInt)
  {
    if (this._value) {}
    for (int i = 1;; i = 0) {
      return i;
    }
  }
  
  public long asLong(long paramLong)
  {
    if (this._value) {}
    for (long l = 1L;; l = 0L) {
      return l;
    }
  }
  
  public String asText()
  {
    if (this._value) {}
    for (String str = "true";; str = "false") {
      return str;
    }
  }
  
  public JsonToken asToken()
  {
    if (this._value) {}
    for (JsonToken localJsonToken = JsonToken.VALUE_TRUE;; localJsonToken = JsonToken.VALUE_FALSE) {
      return localJsonToken;
    }
  }
  
  public boolean booleanValue()
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
      } else if (!(paramObject instanceof BooleanNode)) {
        bool = false;
      } else if (this._value != ((BooleanNode)paramObject)._value) {
        bool = false;
      }
    }
  }
  
  public JsonNodeType getNodeType()
  {
    return JsonNodeType.BOOLEAN;
  }
  
  public int hashCode()
  {
    if (this._value) {}
    for (int i = 3;; i = 1) {
      return i;
    }
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    paramJsonGenerator.writeBoolean(this._value);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/BooleanNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */