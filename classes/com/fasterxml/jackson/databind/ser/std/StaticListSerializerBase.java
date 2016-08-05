package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.lang.reflect.Type;
import java.util.Collection;

public abstract class StaticListSerializerBase<T extends Collection<?>>
  extends StdSerializer<T>
  implements ContextualSerializer
{
  protected final JsonSerializer<String> _serializer;
  protected final Boolean _unwrapSingle;
  
  protected StaticListSerializerBase(StaticListSerializerBase<?> paramStaticListSerializerBase, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramStaticListSerializerBase);
    this._serializer = paramJsonSerializer;
    this._unwrapSingle = paramBoolean;
  }
  
  protected StaticListSerializerBase(Class<?> paramClass)
  {
    super(paramClass, false);
    this._serializer = null;
    this._unwrapSingle = null;
  }
  
  public abstract JsonSerializer<?> _withResolved(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean);
  
  protected abstract void acceptContentVisitor(JsonArrayFormatVisitor paramJsonArrayFormatVisitor)
    throws JsonMappingException;
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    acceptContentVisitor(paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType));
  }
  
  protected abstract JsonNode contentSchema();
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer1 = null;
    Boolean localBoolean = null;
    if (paramBeanProperty != null)
    {
      AnnotationIntrospector localAnnotationIntrospector = paramSerializerProvider.getAnnotationIntrospector();
      AnnotatedMember localAnnotatedMember = paramBeanProperty.getMember();
      if (localAnnotatedMember != null)
      {
        Object localObject = localAnnotationIntrospector.findContentSerializer(localAnnotatedMember);
        if (localObject != null) {
          localJsonSerializer1 = paramSerializerProvider.serializerInstance(localAnnotatedMember, localObject);
        }
      }
      JsonFormat.Value localValue = paramBeanProperty.findFormatOverrides(localAnnotationIntrospector);
      if (localValue != null) {
        localBoolean = localValue.getFeature(JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
      }
    }
    if (localJsonSerializer1 == null) {
      localJsonSerializer1 = this._serializer;
    }
    JsonSerializer localJsonSerializer2 = findConvertingContentSerializer(paramSerializerProvider, paramBeanProperty, localJsonSerializer1);
    JsonSerializer localJsonSerializer3;
    if (localJsonSerializer2 == null)
    {
      localJsonSerializer3 = paramSerializerProvider.findValueSerializer(String.class, paramBeanProperty);
      if (isDefaultSerializer(localJsonSerializer3)) {
        localJsonSerializer3 = null;
      }
      if ((localJsonSerializer3 != this._serializer) || (localBoolean != this._unwrapSingle)) {
        break label152;
      }
    }
    for (;;)
    {
      return this;
      localJsonSerializer3 = paramSerializerProvider.handleSecondaryContextualization(localJsonSerializer2, paramBeanProperty);
      break;
      label152:
      this = _withResolved(paramBeanProperty, localJsonSerializer3, localBoolean);
    }
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    return createSchemaNode("array", true).set("items", contentSchema());
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, T paramT)
  {
    if ((paramT == null) || (paramT.size() == 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  @Deprecated
  public boolean isEmpty(T paramT)
  {
    return isEmpty(null, paramT);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/StaticListSerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */