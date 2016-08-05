package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;

abstract class BaseNodeDeserializer<T extends JsonNode>
  extends StdDeserializer<T>
{
  public BaseNodeDeserializer(Class<T> paramClass)
  {
    super(paramClass);
  }
  
  protected final JsonNode _fromEmbedded(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory)
    throws IOException
  {
    Object localObject1 = paramJsonParser.getEmbeddedObject();
    Object localObject2;
    if (localObject1 == null) {
      localObject2 = paramJsonNodeFactory.nullNode();
    }
    for (;;)
    {
      return (JsonNode)localObject2;
      if (localObject1.getClass() == byte[].class) {
        localObject2 = paramJsonNodeFactory.binaryNode((byte[])localObject1);
      } else if ((localObject1 instanceof RawValue)) {
        localObject2 = paramJsonNodeFactory.rawValueNode((RawValue)localObject1);
      } else if ((localObject1 instanceof JsonNode)) {
        localObject2 = (JsonNode)localObject1;
      } else {
        localObject2 = paramJsonNodeFactory.pojoNode(localObject1);
      }
    }
  }
  
  protected final JsonNode _fromFloat(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory)
    throws IOException
  {
    if ((paramJsonParser.getNumberType() == JsonParser.NumberType.BIG_DECIMAL) || (paramDeserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))) {}
    for (NumericNode localNumericNode = paramJsonNodeFactory.numberNode(paramJsonParser.getDecimalValue());; localNumericNode = paramJsonNodeFactory.numberNode(paramJsonParser.getDoubleValue())) {
      return localNumericNode;
    }
  }
  
  protected final JsonNode _fromInt(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory)
    throws IOException
  {
    int i = paramDeserializationContext.getDeserializationFeatures();
    JsonParser.NumberType localNumberType;
    NumericNode localNumericNode;
    if ((i & F_MASK_INT_COERCIONS) != 0) {
      if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(i))
      {
        localNumberType = JsonParser.NumberType.BIG_INTEGER;
        if (localNumberType != JsonParser.NumberType.INT) {
          break label89;
        }
        localNumericNode = paramJsonNodeFactory.numberNode(paramJsonParser.getIntValue());
      }
    }
    for (;;)
    {
      return localNumericNode;
      if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(i))
      {
        localNumberType = JsonParser.NumberType.LONG;
        break;
      }
      localNumberType = paramJsonParser.getNumberType();
      break;
      localNumberType = paramJsonParser.getNumberType();
      break;
      label89:
      if (localNumberType == JsonParser.NumberType.LONG) {
        localNumericNode = paramJsonNodeFactory.numberNode(paramJsonParser.getLongValue());
      } else {
        localNumericNode = paramJsonNodeFactory.numberNode(paramJsonParser.getBigIntegerValue());
      }
    }
  }
  
  protected void _handleDuplicateField(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory, String paramString, ObjectNode paramObjectNode, JsonNode paramJsonNode1, JsonNode paramJsonNode2)
    throws JsonProcessingException
  {
    if (paramDeserializationContext.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY)) {
      _reportProblem(paramJsonParser, "Duplicate field '" + paramString + "' for ObjectNode: not allowed when FAIL_ON_READING_DUP_TREE_KEY enabled");
    }
  }
  
  protected void _reportProblem(JsonParser paramJsonParser, String paramString)
    throws JsonMappingException
  {
    throw new JsonMappingException(paramString, paramJsonParser.getTokenLocation());
  }
  
  protected final JsonNode deserializeAny(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory)
    throws IOException
  {
    Object localObject;
    switch (paramJsonParser.getCurrentTokenId())
    {
    case 4: 
    default: 
      throw paramDeserializationContext.mappingException(handledType());
    case 1: 
    case 2: 
      localObject = deserializeObject(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
    }
    for (;;)
    {
      return (JsonNode)localObject;
      localObject = deserializeArray(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      continue;
      localObject = deserializeObject(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      continue;
      localObject = _fromEmbedded(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      continue;
      localObject = paramJsonNodeFactory.textNode(paramJsonParser.getText());
      continue;
      localObject = _fromInt(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      continue;
      localObject = _fromFloat(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      continue;
      localObject = paramJsonNodeFactory.booleanNode(true);
      continue;
      localObject = paramJsonNodeFactory.booleanNode(false);
      continue;
      localObject = paramJsonNodeFactory.nullNode();
    }
  }
  
  protected final ArrayNode deserializeArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory)
    throws IOException
  {
    ArrayNode localArrayNode = paramJsonNodeFactory.arrayNode();
    for (;;)
    {
      JsonToken localJsonToken = paramJsonParser.nextToken();
      if (localJsonToken == null) {
        throw paramDeserializationContext.mappingException("Unexpected end-of-input when binding data into ArrayNode");
      }
      switch (localJsonToken.id())
      {
      case 2: 
      case 5: 
      case 8: 
      default: 
        localArrayNode.add(deserializeAny(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory));
        break;
      case 1: 
        localArrayNode.add(deserializeObject(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory));
        break;
      case 3: 
        localArrayNode.add(deserializeArray(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory));
        break;
      case 12: 
        localArrayNode.add(_fromEmbedded(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory));
      case 6: 
        localArrayNode.add(paramJsonNodeFactory.textNode(paramJsonParser.getText()));
        break;
      case 7: 
        localArrayNode.add(_fromInt(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory));
        break;
      case 9: 
        localArrayNode.add(paramJsonNodeFactory.booleanNode(true));
        break;
      case 10: 
        localArrayNode.add(paramJsonNodeFactory.booleanNode(false));
        break;
      case 11: 
        localArrayNode.add(paramJsonNodeFactory.nullNode());
      }
    }
    return localArrayNode;
  }
  
  protected final ObjectNode deserializeObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JsonNodeFactory paramJsonNodeFactory)
    throws IOException
  {
    ObjectNode localObjectNode = paramJsonNodeFactory.objectNode();
    String str;
    Object localObject;
    if (paramJsonParser.isExpectedStartObjectToken())
    {
      str = paramJsonParser.nextFieldName();
      if (str == null) {
        break label155;
      }
      switch (paramJsonParser.nextToken().id())
      {
      case 2: 
      case 4: 
      case 5: 
      case 8: 
      default: 
        localObject = deserializeAny(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      }
    }
    for (;;)
    {
      JsonNode localJsonNode = localObjectNode.replace(str, (JsonNode)localObject);
      if (localJsonNode != null) {
        _handleDuplicateField(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory, str, localObjectNode, localJsonNode, (JsonNode)localObject);
      }
      str = paramJsonParser.nextFieldName();
      break;
      JsonToken localJsonToken = paramJsonParser.getCurrentToken();
      if (localJsonToken == JsonToken.END_OBJECT) {
        label155:
        return localObjectNode;
      }
      if (localJsonToken != JsonToken.FIELD_NAME) {
        throw paramDeserializationContext.mappingException(handledType(), paramJsonParser.getCurrentToken());
      }
      str = paramJsonParser.getCurrentName();
      break;
      localObject = deserializeObject(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      continue;
      localObject = deserializeArray(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      continue;
      localObject = _fromEmbedded(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      continue;
      localObject = paramJsonNodeFactory.textNode(paramJsonParser.getText());
      continue;
      localObject = _fromInt(paramJsonParser, paramDeserializationContext, paramJsonNodeFactory);
      continue;
      localObject = paramJsonNodeFactory.booleanNode(true);
      continue;
      localObject = paramJsonNodeFactory.booleanNode(false);
      continue;
      localObject = paramJsonNodeFactory.nullNode();
    }
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException
  {
    return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
  }
  
  public boolean isCachable()
  {
    return true;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/BaseNodeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */