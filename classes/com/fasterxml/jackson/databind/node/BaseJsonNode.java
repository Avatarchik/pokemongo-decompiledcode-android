package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public abstract class BaseJsonNode
  extends JsonNode
  implements JsonSerializable
{
  public abstract JsonToken asToken();
  
  public final JsonNode findPath(String paramString)
  {
    Object localObject = findValue(paramString);
    if (localObject == null) {
      localObject = MissingNode.getInstance();
    }
    return (JsonNode)localObject;
  }
  
  public abstract int hashCode();
  
  public JsonParser.NumberType numberType()
  {
    return null;
  }
  
  public abstract void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonProcessingException;
  
  public abstract void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonProcessingException;
  
  public JsonParser traverse()
  {
    return new TreeTraversingParser(this);
  }
  
  public JsonParser traverse(ObjectCodec paramObjectCodec)
  {
    return new TreeTraversingParser(this, paramObjectCodec);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/BaseJsonNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */