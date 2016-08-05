package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@JacksonStdImpl
public class SerializableSerializer
  extends StdSerializer<JsonSerializable>
{
  private static final AtomicReference<ObjectMapper> _mapperReference = new AtomicReference();
  public static final SerializableSerializer instance = new SerializableSerializer();
  
  protected SerializableSerializer()
  {
    super(JsonSerializable.class);
  }
  
  /**
   * @deprecated
   */
  private static final ObjectMapper _getObjectMapper()
  {
    try
    {
      ObjectMapper localObjectMapper = (ObjectMapper)_mapperReference.get();
      if (localObjectMapper == null)
      {
        localObjectMapper = new ObjectMapper();
        _mapperReference.set(localObjectMapper);
      }
      return localObjectMapper;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    paramJsonFormatVisitorWrapper.expectAnyFormat(paramJavaType);
  }
  
  /* Error */
  public com.fasterxml.jackson.databind.JsonNode getSchema(SerializerProvider paramSerializerProvider, java.lang.reflect.Type paramType)
    throws JsonMappingException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 59	com/fasterxml/jackson/databind/ser/std/SerializableSerializer:createObjectNode	()Lcom/fasterxml/jackson/databind/node/ObjectNode;
    //   4: astore_3
    //   5: ldc 61
    //   7: astore 4
    //   9: aconst_null
    //   10: astore 5
    //   12: aconst_null
    //   13: astore 6
    //   15: aload_2
    //   16: ifnull +88 -> 104
    //   19: aload_2
    //   20: invokestatic 67	com/fasterxml/jackson/databind/type/TypeFactory:rawClass	(Ljava/lang/reflect/Type;)Ljava/lang/Class;
    //   23: astore 12
    //   25: aload 12
    //   27: ldc 69
    //   29: invokevirtual 75	java/lang/Class:isAnnotationPresent	(Ljava/lang/Class;)Z
    //   32: ifeq +72 -> 104
    //   35: aload 12
    //   37: ldc 69
    //   39: invokevirtual 79	java/lang/Class:getAnnotation	(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   42: checkcast 69	com/fasterxml/jackson/databind/jsonschema/JsonSerializableSchema
    //   45: astore 13
    //   47: aload 13
    //   49: invokeinterface 83 1 0
    //   54: astore 4
    //   56: ldc 85
    //   58: aload 13
    //   60: invokeinterface 88 1 0
    //   65: invokevirtual 94	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   68: ifne +12 -> 80
    //   71: aload 13
    //   73: invokeinterface 88 1 0
    //   78: astore 5
    //   80: ldc 85
    //   82: aload 13
    //   84: invokeinterface 97 1 0
    //   89: invokevirtual 94	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   92: ifne +12 -> 104
    //   95: aload 13
    //   97: invokeinterface 97 1 0
    //   102: astore 6
    //   104: aload_3
    //   105: ldc 99
    //   107: aload 4
    //   109: invokevirtual 105	com/fasterxml/jackson/databind/node/ObjectNode:put	(Ljava/lang/String;Ljava/lang/String;)Lcom/fasterxml/jackson/databind/node/ObjectNode;
    //   112: pop
    //   113: aload 5
    //   115: ifnull +18 -> 133
    //   118: aload_3
    //   119: ldc 107
    //   121: invokestatic 109	com/fasterxml/jackson/databind/ser/std/SerializableSerializer:_getObjectMapper	()Lcom/fasterxml/jackson/databind/ObjectMapper;
    //   124: aload 5
    //   126: invokevirtual 113	com/fasterxml/jackson/databind/ObjectMapper:readTree	(Ljava/lang/String;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   129: invokevirtual 116	com/fasterxml/jackson/databind/node/ObjectNode:set	(Ljava/lang/String;Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   132: pop
    //   133: aload 6
    //   135: ifnull +18 -> 153
    //   138: aload_3
    //   139: ldc 118
    //   141: invokestatic 109	com/fasterxml/jackson/databind/ser/std/SerializableSerializer:_getObjectMapper	()Lcom/fasterxml/jackson/databind/ObjectMapper;
    //   144: aload 6
    //   146: invokevirtual 113	com/fasterxml/jackson/databind/ObjectMapper:readTree	(Ljava/lang/String;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   149: invokevirtual 116	com/fasterxml/jackson/databind/node/ObjectNode:set	(Ljava/lang/String;Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   152: pop
    //   153: aload_3
    //   154: areturn
    //   155: astore 10
    //   157: new 45	com/fasterxml/jackson/databind/JsonMappingException
    //   160: dup
    //   161: ldc 120
    //   163: invokespecial 123	com/fasterxml/jackson/databind/JsonMappingException:<init>	(Ljava/lang/String;)V
    //   166: athrow
    //   167: astore 8
    //   169: new 45	com/fasterxml/jackson/databind/JsonMappingException
    //   172: dup
    //   173: ldc 125
    //   175: invokespecial 123	com/fasterxml/jackson/databind/JsonMappingException:<init>	(Ljava/lang/String;)V
    //   178: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	179	0	this	SerializableSerializer
    //   0	179	1	paramSerializerProvider	SerializerProvider
    //   0	179	2	paramType	java.lang.reflect.Type
    //   4	150	3	localObjectNode	com.fasterxml.jackson.databind.node.ObjectNode
    //   7	101	4	str1	String
    //   10	115	5	str2	String
    //   13	132	6	str3	String
    //   167	1	8	localIOException1	IOException
    //   155	1	10	localIOException2	IOException
    //   23	13	12	localClass	Class
    //   45	51	13	localJsonSerializableSchema	com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema
    // Exception table:
    //   from	to	target	type
    //   118	133	155	java/io/IOException
    //   138	153	167	java/io/IOException
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, JsonSerializable paramJsonSerializable)
  {
    if ((paramJsonSerializable instanceof JsonSerializable.Base)) {}
    for (boolean bool = ((JsonSerializable.Base)paramJsonSerializable).isEmpty(paramSerializerProvider);; bool = false) {
      return bool;
    }
  }
  
  public void serialize(JsonSerializable paramJsonSerializable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    paramJsonSerializable.serialize(paramJsonGenerator, paramSerializerProvider);
  }
  
  public final void serializeWithType(JsonSerializable paramJsonSerializable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    paramJsonSerializable.serializeWithType(paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/SerializableSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */