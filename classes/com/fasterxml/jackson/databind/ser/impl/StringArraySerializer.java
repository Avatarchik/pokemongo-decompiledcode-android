package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class StringArraySerializer
  extends ArraySerializerBase<String[]>
  implements ContextualSerializer
{
  private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(String.class);
  public static final StringArraySerializer instance = new StringArraySerializer();
  protected final JsonSerializer<Object> _elementSerializer;
  
  protected StringArraySerializer()
  {
    super(String[].class);
    this._elementSerializer = null;
  }
  
  public StringArraySerializer(StringArraySerializer paramStringArraySerializer, BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramStringArraySerializer, paramBeanProperty, paramBoolean);
    this._elementSerializer = paramJsonSerializer;
  }
  
  private void serializeContentsSlow(String[] paramArrayOfString, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException
  {
    int i = 0;
    int j = paramArrayOfString.length;
    if (i < j)
    {
      if (paramArrayOfString[i] == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      }
      for (;;)
      {
        i++;
        break;
        paramJsonSerializer.serialize(paramArrayOfString[i], paramJsonGenerator, paramSerializerProvider);
      }
    }
  }
  
  public JsonSerializer<?> _withResolved(BeanProperty paramBeanProperty, Boolean paramBoolean)
  {
    return new StringArraySerializer(this, paramBeanProperty, this._elementSerializer, paramBoolean);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return this;
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper != null)
    {
      JsonArrayFormatVisitor localJsonArrayFormatVisitor = paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType);
      if (localJsonArrayFormatVisitor != null) {
        localJsonArrayFormatVisitor.itemsFormat(JsonFormatTypes.STRING);
      }
    }
  }
  
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
      localJsonSerializer1 = this._elementSerializer;
    }
    JsonSerializer localJsonSerializer2 = findConvertingContentSerializer(paramSerializerProvider, paramBeanProperty, localJsonSerializer1);
    JsonSerializer localJsonSerializer3;
    if (localJsonSerializer2 == null)
    {
      localJsonSerializer3 = paramSerializerProvider.findValueSerializer(String.class, paramBeanProperty);
      if (isDefaultSerializer(localJsonSerializer3)) {
        localJsonSerializer3 = null;
      }
      if ((localJsonSerializer3 != this._elementSerializer) || (localBoolean != this._unwrapSingle)) {
        break label152;
      }
    }
    for (;;)
    {
      return this;
      localJsonSerializer3 = paramSerializerProvider.handleSecondaryContextualization(localJsonSerializer2, paramBeanProperty);
      break;
      label152:
      this = new StringArraySerializer(this, paramBeanProperty, localJsonSerializer3, localBoolean);
    }
  }
  
  public JsonSerializer<?> getContentSerializer()
  {
    return this._elementSerializer;
  }
  
  public JavaType getContentType()
  {
    return VALUE_TYPE;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    return createSchemaNode("array", true).set("items", createSchemaNode("string"));
  }
  
  public boolean hasSingleElement(String[] paramArrayOfString)
  {
    int i = 1;
    if (paramArrayOfString.length == i) {}
    for (;;)
    {
      return i;
      int j = 0;
    }
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final void serialize(String[] paramArrayOfString, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramArrayOfString.length;
    if ((i == 1) && (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || (this._unwrapSingle == Boolean.TRUE))) {
      serializeContents(paramArrayOfString, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartArray(i);
      serializeContents(paramArrayOfString, paramJsonGenerator, paramSerializerProvider);
      paramJsonGenerator.writeEndArray();
    }
  }
  
  public void serializeContents(String[] paramArrayOfString, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramArrayOfString.length;
    if (i == 0) {}
    for (;;)
    {
      return;
      if (this._elementSerializer == null) {
        break;
      }
      serializeContentsSlow(paramArrayOfString, paramJsonGenerator, paramSerializerProvider, this._elementSerializer);
    }
    int j = 0;
    label34:
    if (j < i)
    {
      if (paramArrayOfString[j] != null) {
        break label58;
      }
      paramJsonGenerator.writeNull();
    }
    for (;;)
    {
      j++;
      break label34;
      break;
      label58:
      paramJsonGenerator.writeString(paramArrayOfString[j]);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/StringArraySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */