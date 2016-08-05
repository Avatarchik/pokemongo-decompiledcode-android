package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public final class NullNode
  extends ValueNode
{
  public static final NullNode instance = new NullNode();
  
  public static NullNode getInstance()
  {
    return instance;
  }
  
  public String asText()
  {
    return "null";
  }
  
  public String asText(String paramString)
  {
    return paramString;
  }
  
  public JsonToken asToken()
  {
    return JsonToken.VALUE_NULL;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public JsonNodeType getNodeType()
  {
    return JsonNodeType.NULL;
  }
  
  public int hashCode()
  {
    return JsonNodeType.NULL.ordinal();
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/NullNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */