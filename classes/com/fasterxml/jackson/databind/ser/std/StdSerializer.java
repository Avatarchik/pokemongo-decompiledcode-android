package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public abstract class StdSerializer<T>
  extends JsonSerializer<T>
  implements JsonFormatVisitable, SchemaAware, Serializable
{
  private static final long serialVersionUID = 1L;
  protected final Class<T> _handledType;
  
  protected StdSerializer(JavaType paramJavaType)
  {
    this._handledType = paramJavaType.getRawClass();
  }
  
  protected StdSerializer(StdSerializer<?> paramStdSerializer)
  {
    this._handledType = paramStdSerializer._handledType;
  }
  
  protected StdSerializer(Class<T> paramClass)
  {
    this._handledType = paramClass;
  }
  
  protected StdSerializer(Class<?> paramClass, boolean paramBoolean)
  {
    this._handledType = paramClass;
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    paramJsonFormatVisitorWrapper.expectAnyFormat(paramJavaType);
  }
  
  protected ObjectNode createObjectNode()
  {
    return JsonNodeFactory.instance.objectNode();
  }
  
  protected ObjectNode createSchemaNode(String paramString)
  {
    ObjectNode localObjectNode = createObjectNode();
    localObjectNode.put("type", paramString);
    return localObjectNode;
  }
  
  protected ObjectNode createSchemaNode(String paramString, boolean paramBoolean)
  {
    ObjectNode localObjectNode = createSchemaNode(paramString);
    if (!paramBoolean) {
      if (paramBoolean) {
        break label28;
      }
    }
    label28:
    for (boolean bool = true;; bool = false)
    {
      localObjectNode.put("required", bool);
      return localObjectNode;
    }
  }
  
  protected JsonSerializer<?> findConvertingContentSerializer(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer)
    throws JsonMappingException
  {
    AnnotationIntrospector localAnnotationIntrospector = paramSerializerProvider.getAnnotationIntrospector();
    Converter localConverter;
    JavaType localJavaType;
    if ((localAnnotationIntrospector != null) && (paramBeanProperty != null))
    {
      AnnotatedMember localAnnotatedMember = paramBeanProperty.getMember();
      if (localAnnotatedMember != null)
      {
        Object localObject2 = localAnnotationIntrospector.findSerializationContentConverter(localAnnotatedMember);
        if (localObject2 != null)
        {
          localConverter = paramSerializerProvider.converterInstance(paramBeanProperty.getMember(), localObject2);
          localJavaType = localConverter.getOutputType(paramSerializerProvider.getTypeFactory());
          if ((paramJsonSerializer == null) && (!localJavaType.hasRawClass(Object.class))) {
            paramJsonSerializer = paramSerializerProvider.findValueSerializer(localJavaType);
          }
        }
      }
    }
    for (Object localObject1 = new StdDelegatingSerializer(localConverter, localJavaType, paramJsonSerializer);; localObject1 = paramJsonSerializer) {
      return (JsonSerializer<?>)localObject1;
    }
  }
  
  protected PropertyFilter findPropertyFilter(SerializerProvider paramSerializerProvider, Object paramObject1, Object paramObject2)
    throws JsonMappingException
  {
    FilterProvider localFilterProvider = paramSerializerProvider.getFilterProvider();
    if (localFilterProvider == null) {
      throw new JsonMappingException("Can not resolve PropertyFilter with id '" + paramObject1 + "'; no FilterProvider configured");
    }
    return localFilterProvider.findPropertyFilter(paramObject1, paramObject2);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    return createSchemaNode("string");
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType, boolean paramBoolean)
    throws JsonMappingException
  {
    ObjectNode localObjectNode = (ObjectNode)getSchema(paramSerializerProvider, paramType);
    if (!paramBoolean) {
      if (paramBoolean) {
        break label35;
      }
    }
    label35:
    for (boolean bool = true;; bool = false)
    {
      localObjectNode.put("required", bool);
      return localObjectNode;
    }
  }
  
  public Class<T> handledType()
  {
    return this._handledType;
  }
  
  protected boolean isDefaultSerializer(JsonSerializer<?> paramJsonSerializer)
  {
    return ClassUtil.isJacksonStdImpl(paramJsonSerializer);
  }
  
  public abstract void serialize(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException;
  
  public void wrapAndThrow(SerializerProvider paramSerializerProvider, Throwable paramThrowable, Object paramObject, int paramInt)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if ((paramSerializerProvider == null) || (paramSerializerProvider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS))) {}
    for (int i = 1; (paramThrowable instanceof IOException); i = 0)
    {
      if ((i != 0) && ((paramThrowable instanceof JsonMappingException))) {
        break label98;
      }
      throw ((IOException)paramThrowable);
    }
    if ((i == 0) && ((paramThrowable instanceof RuntimeException))) {
      throw ((RuntimeException)paramThrowable);
    }
    label98:
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, paramInt);
  }
  
  public void wrapAndThrow(SerializerProvider paramSerializerProvider, Throwable paramThrowable, Object paramObject, String paramString)
    throws IOException
  {
    while (((paramThrowable instanceof InvocationTargetException)) && (paramThrowable.getCause() != null)) {
      paramThrowable = paramThrowable.getCause();
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    if ((paramSerializerProvider == null) || (paramSerializerProvider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS))) {}
    for (int i = 1; (paramThrowable instanceof IOException); i = 0)
    {
      if ((i != 0) && ((paramThrowable instanceof JsonMappingException))) {
        break label98;
      }
      throw ((IOException)paramThrowable);
    }
    if ((i == 0) && ((paramThrowable instanceof RuntimeException))) {
      throw ((RuntimeException)paramThrowable);
    }
    label98:
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/StdSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */