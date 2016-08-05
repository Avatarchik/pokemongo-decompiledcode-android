package com.fasterxml.jackson.databind.jsonschema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Deprecated
public class JsonSchema
{
  private final ObjectNode schema;
  
  @JsonCreator
  public JsonSchema(ObjectNode paramObjectNode)
  {
    this.schema = paramObjectNode;
  }
  
  public static JsonNode getDefaultSchemaNode()
  {
    ObjectNode localObjectNode = JsonNodeFactory.instance.objectNode();
    localObjectNode.put("type", "any");
    return localObjectNode;
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
      else if (!(paramObject instanceof JsonSchema))
      {
        bool = false;
      }
      else
      {
        JsonSchema localJsonSchema = (JsonSchema)paramObject;
        if (this.schema == null)
        {
          if (localJsonSchema.schema != null) {
            bool = false;
          }
        }
        else {
          bool = this.schema.equals(localJsonSchema.schema);
        }
      }
    }
  }
  
  @JsonValue
  public ObjectNode getSchemaNode()
  {
    return this.schema;
  }
  
  public int hashCode()
  {
    return this.schema.hashCode();
  }
  
  public String toString()
  {
    return this.schema.toString();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsonschema/JsonSchema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */