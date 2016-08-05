package com.fasterxml.jackson.databind.ser.std;

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
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import java.io.IOException;
import java.lang.reflect.Type;

public abstract class AsArraySerializerBase<T>
  extends ContainerSerializer<T>
  implements ContextualSerializer
{
  protected PropertySerializerMap _dynamicSerializers;
  protected final JsonSerializer<Object> _elementSerializer;
  protected final JavaType _elementType;
  protected final BeanProperty _property;
  protected final boolean _staticTyping;
  protected final Boolean _unwrapSingle;
  protected final TypeSerializer _valueTypeSerializer;
  
  @Deprecated
  protected AsArraySerializerBase(AsArraySerializerBase<?> paramAsArraySerializerBase, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    this(paramAsArraySerializerBase, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramAsArraySerializerBase._unwrapSingle);
  }
  
  protected AsArraySerializerBase(AsArraySerializerBase<?> paramAsArraySerializerBase, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramAsArraySerializerBase);
    this._elementType = paramAsArraySerializerBase._elementType;
    this._staticTyping = paramAsArraySerializerBase._staticTyping;
    this._valueTypeSerializer = paramTypeSerializer;
    this._property = paramBeanProperty;
    this._elementSerializer = paramJsonSerializer;
    this._dynamicSerializers = paramAsArraySerializerBase._dynamicSerializers;
    this._unwrapSingle = paramBoolean;
  }
  
  @Deprecated
  protected AsArraySerializerBase(Class<?> paramClass, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer<Object> paramJsonSerializer)
  {
    super(paramClass, false);
    this._elementType = paramJavaType;
    if ((paramBoolean) || ((paramJavaType != null) && (paramJavaType.isFinal()))) {
      bool = true;
    }
    this._staticTyping = bool;
    this._valueTypeSerializer = paramTypeSerializer;
    this._property = paramBeanProperty;
    this._elementSerializer = paramJsonSerializer;
    this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
    this._unwrapSingle = null;
  }
  
  protected AsArraySerializerBase(Class<?> paramClass, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    super(paramClass, false);
    this._elementType = paramJavaType;
    if ((paramBoolean) || ((paramJavaType != null) && (paramJavaType.isFinal()))) {
      bool = true;
    }
    this._staticTyping = bool;
    this._valueTypeSerializer = paramTypeSerializer;
    this._property = null;
    this._elementSerializer = paramJsonSerializer;
    this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
    this._unwrapSingle = null;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, JavaType paramJavaType, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    PropertySerializerMap.SerializerAndMapResult localSerializerAndMapResult = paramPropertySerializerMap.findAndAddSecondarySerializer(paramJavaType, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != localSerializerAndMapResult.map) {
      this._dynamicSerializers = localSerializerAndMapResult.map;
    }
    return localSerializerAndMapResult.serializer;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    PropertySerializerMap.SerializerAndMapResult localSerializerAndMapResult = paramPropertySerializerMap.findAndAddSecondarySerializer(paramClass, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != localSerializerAndMapResult.map) {
      this._dynamicSerializers = localSerializerAndMapResult.map;
    }
    return localSerializerAndMapResult.serializer;
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper == null) {}
    for (JsonArrayFormatVisitor localJsonArrayFormatVisitor = null;; localJsonArrayFormatVisitor = paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType))
    {
      if (localJsonArrayFormatVisitor != null)
      {
        JsonSerializer localJsonSerializer = this._elementSerializer;
        if (localJsonSerializer == null) {
          localJsonSerializer = paramJsonFormatVisitorWrapper.getProvider().findValueSerializer(this._elementType, this._property);
        }
        localJsonArrayFormatVisitor.itemsFormat(localJsonSerializer, this._elementType);
      }
      return;
    }
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    TypeSerializer localTypeSerializer = this._valueTypeSerializer;
    if (localTypeSerializer != null) {
      localTypeSerializer = localTypeSerializer.forProperty(paramBeanProperty);
    }
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
    if (localJsonSerializer2 == null) {
      if ((this._elementType == null) || (((!this._staticTyping) || (this._elementType.getRawClass() == Object.class)) && (!hasContentTypeAnnotation(paramSerializerProvider, paramBeanProperty)))) {}
    }
    for (localJsonSerializer2 = paramSerializerProvider.findValueSerializer(this._elementType, paramBeanProperty);; localJsonSerializer2 = paramSerializerProvider.handleSecondaryContextualization(localJsonSerializer2, paramBeanProperty))
    {
      if ((localJsonSerializer2 != this._elementSerializer) || (paramBeanProperty != this._property) || (this._valueTypeSerializer != localTypeSerializer) || (this._unwrapSingle != localBoolean)) {
        this = withResolved(paramBeanProperty, localTypeSerializer, localJsonSerializer2, localBoolean);
      }
      return this;
    }
  }
  
  public JsonSerializer<?> getContentSerializer()
  {
    return this._elementSerializer;
  }
  
  public JavaType getContentType()
  {
    return this._elementType;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    ObjectNode localObjectNode = createSchemaNode("array", true);
    JavaType localJavaType = this._elementType;
    if (localJavaType != null)
    {
      JsonNode localJsonNode = null;
      if (localJavaType.getRawClass() != Object.class)
      {
        JsonSerializer localJsonSerializer = paramSerializerProvider.findValueSerializer(localJavaType, this._property);
        if ((localJsonSerializer instanceof SchemaAware)) {
          localJsonNode = ((SchemaAware)localJsonSerializer).getSchema(paramSerializerProvider, null);
        }
      }
      if (localJsonNode == null) {
        localJsonNode = JsonSchema.getDefaultSchemaNode();
      }
      localObjectNode.set("items", localJsonNode);
    }
    return localObjectNode;
  }
  
  public void serialize(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if ((paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && (hasSingleElement(paramT))) {
      serializeContents(paramT, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartArray();
      paramJsonGenerator.setCurrentValue(paramT);
      serializeContents(paramT, paramJsonGenerator, paramSerializerProvider);
      paramJsonGenerator.writeEndArray();
    }
  }
  
  protected abstract void serializeContents(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException;
  
  public void serializeWithType(T paramT, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    paramTypeSerializer.writeTypePrefixForArray(paramT, paramJsonGenerator);
    paramJsonGenerator.setCurrentValue(paramT);
    serializeContents(paramT, paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForArray(paramT, paramJsonGenerator);
  }
  
  @Deprecated
  public final AsArraySerializerBase<T> withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer)
  {
    return withResolved(paramBeanProperty, paramTypeSerializer, paramJsonSerializer, this._unwrapSingle);
  }
  
  public abstract AsArraySerializerBase<T> withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/AsArraySerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */