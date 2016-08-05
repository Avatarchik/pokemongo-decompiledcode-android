package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

public abstract class StdNodeBasedDeserializer<T>
  extends StdDeserializer<T>
  implements ResolvableDeserializer
{
  private static final long serialVersionUID = 1L;
  protected JsonDeserializer<Object> _treeDeserializer;
  
  protected StdNodeBasedDeserializer(JavaType paramJavaType)
  {
    super(paramJavaType);
  }
  
  protected StdNodeBasedDeserializer(StdNodeBasedDeserializer<?> paramStdNodeBasedDeserializer)
  {
    super(paramStdNodeBasedDeserializer);
    this._treeDeserializer = paramStdNodeBasedDeserializer._treeDeserializer;
  }
  
  protected StdNodeBasedDeserializer(Class<T> paramClass)
  {
    super(paramClass);
  }
  
  public abstract T convert(JsonNode paramJsonNode, DeserializationContext paramDeserializationContext)
    throws IOException;
  
  public T deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    return (T)convert((JsonNode)this._treeDeserializer.deserialize(paramJsonParser, paramDeserializationContext), paramDeserializationContext);
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer)
    throws IOException, JsonProcessingException
  {
    return convert((JsonNode)this._treeDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, paramTypeDeserializer), paramDeserializationContext);
  }
  
  public void resolve(DeserializationContext paramDeserializationContext)
    throws JsonMappingException
  {
    this._treeDeserializer = paramDeserializationContext.findRootValueDeserializer(paramDeserializationContext.constructType(JsonNode.class));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/std/StdNodeBasedDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */