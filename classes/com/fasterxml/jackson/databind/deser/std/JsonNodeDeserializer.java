package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

public class JsonNodeDeserializer
  extends BaseNodeDeserializer<JsonNode>
{
  private static final JsonNodeDeserializer instance = new JsonNodeDeserializer();
  
  protected JsonNodeDeserializer()
  {
    super(JsonNode.class);
  }
  
  public static JsonDeserializer<? extends JsonNode> getDeserializer(Class<?> paramClass)
  {
    Object localObject;
    if (paramClass == ObjectNode.class) {
      localObject = ObjectDeserializer.getInstance();
    }
    for (;;)
    {
      return (JsonDeserializer<? extends JsonNode>)localObject;
      if (paramClass == ArrayNode.class) {
        localObject = ArrayDeserializer.getInstance();
      } else {
        localObject = instance;
      }
    }
  }
  
  public JsonNode deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    Object localObject;
    switch (paramJsonParser.getCurrentTokenId())
    {
    case 2: 
    default: 
      localObject = deserializeAny(paramJsonParser, paramDeserializationContext, paramDeserializationContext.getNodeFactory());
    }
    for (;;)
    {
      return (JsonNode)localObject;
      localObject = deserializeObject(paramJsonParser, paramDeserializationContext, paramDeserializationContext.getNodeFactory());
      continue;
      localObject = deserializeArray(paramJsonParser, paramDeserializationContext, paramDeserializationContext.getNodeFactory());
    }
  }
  
  @Deprecated
  public JsonNode getNullValue()
  {
    return NullNode.getInstance();
  }
  
  public JsonNode getNullValue(DeserializationContext paramDeserializationContext)
  {
    return NullNode.getInstance();
  }
  
  static final class ArrayDeserializer
    extends BaseNodeDeserializer<ArrayNode>
  {
    protected static final ArrayDeserializer _instance = new ArrayDeserializer();
    private static final long serialVersionUID = 1L;
    
    protected ArrayDeserializer()
    {
      super();
    }
    
    public static ArrayDeserializer getInstance()
    {
      return _instance;
    }
    
    public ArrayNode deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      if (paramJsonParser.isExpectedStartArrayToken()) {
        return deserializeArray(paramJsonParser, paramDeserializationContext, paramDeserializationContext.getNodeFactory());
      }
      throw paramDeserializationContext.mappingException(ArrayNode.class);
    }
  }
  
  static final class ObjectDeserializer
    extends BaseNodeDeserializer<ObjectNode>
  {
    protected static final ObjectDeserializer _instance = new ObjectDeserializer();
    private static final long serialVersionUID = 1L;
    
    protected ObjectDeserializer()
    {
      super();
    }
    
    public static ObjectDeserializer getInstance()
    {
      return _instance;
    }
    
    public ObjectNode deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
      throws IOException
    {
      if ((paramJsonParser.isExpectedStartObjectToken()) || (paramJsonParser.hasToken(JsonToken.FIELD_NAME))) {}
      for (ObjectNode localObjectNode = deserializeObject(paramJsonParser, paramDeserializationContext, paramDeserializationContext.getNodeFactory());; localObjectNode = paramDeserializationContext.getNodeFactory().objectNode())
      {
        return localObjectNode;
        if (!paramJsonParser.hasToken(JsonToken.END_OBJECT)) {
          break;
        }
      }
      throw paramDeserializationContext.mappingException(ObjectNode.class);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/JsonNodeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */