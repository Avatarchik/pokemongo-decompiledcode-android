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
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
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
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class ObjectArraySerializer
  extends ArraySerializerBase<Object[]>
  implements ContextualSerializer
{
  protected PropertySerializerMap _dynamicSerializers;
  protected JsonSerializer<Object> _elementSerializer;
  protected final JavaType _elementType;
  protected final boolean _staticTyping;
  protected final TypeSerializer _valueTypeSerializer;
  
  public ObjectArraySerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    super(Object[].class);
    this._elementType = paramJavaType;
    this._staticTyping = paramBoolean;
    this._valueTypeSerializer = paramTypeSerializer;
    this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
    this._elementSerializer = paramJsonSerializer;
  }
  
  public ObjectArraySerializer(ObjectArraySerializer paramObjectArraySerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    super(paramObjectArraySerializer, paramBeanProperty, paramBoolean);
    this._elementType = paramObjectArraySerializer._elementType;
    this._valueTypeSerializer = paramTypeSerializer;
    this._staticTyping = paramObjectArraySerializer._staticTyping;
    this._dynamicSerializers = paramObjectArraySerializer._dynamicSerializers;
    this._elementSerializer = paramJsonSerializer;
  }
  
  public ObjectArraySerializer(ObjectArraySerializer paramObjectArraySerializer, TypeSerializer paramTypeSerializer)
  {
    super(paramObjectArraySerializer);
    this._elementType = paramObjectArraySerializer._elementType;
    this._valueTypeSerializer = paramTypeSerializer;
    this._staticTyping = paramObjectArraySerializer._staticTyping;
    this._dynamicSerializers = paramObjectArraySerializer._dynamicSerializers;
    this._elementSerializer = paramObjectArraySerializer._elementSerializer;
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
  
  public JsonSerializer<?> _withResolved(BeanProperty paramBeanProperty, Boolean paramBoolean)
  {
    return new ObjectArraySerializer(this, paramBeanProperty, this._valueTypeSerializer, this._elementSerializer, paramBoolean);
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new ObjectArraySerializer(this._elementType, this._staticTyping, paramTypeSerializer, this._elementSerializer);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    JsonArrayFormatVisitor localJsonArrayFormatVisitor = paramJsonFormatVisitorWrapper.expectArrayFormat(paramJavaType);
    if (localJsonArrayFormatVisitor != null)
    {
      JavaType localJavaType = paramJsonFormatVisitorWrapper.getProvider().getTypeFactory().moreSpecificType(this._elementType, paramJavaType.getContentType());
      if (localJavaType == null) {
        throw new JsonMappingException("Could not resolve type");
      }
      JsonSerializer localJsonSerializer = this._elementSerializer;
      if (localJsonSerializer == null) {
        localJsonSerializer = paramJsonFormatVisitorWrapper.getProvider().findValueSerializer(localJavaType, this._property);
      }
      localJsonArrayFormatVisitor.itemsFormat(localJsonSerializer, localJavaType);
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
      AnnotatedMember localAnnotatedMember = paramBeanProperty.getMember();
      AnnotationIntrospector localAnnotationIntrospector = paramSerializerProvider.getAnnotationIntrospector();
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
      if ((this._elementType == null) || ((!this._staticTyping) && (!hasContentTypeAnnotation(paramSerializerProvider, paramBeanProperty)))) {}
    }
    for (localJsonSerializer2 = paramSerializerProvider.findValueSerializer(this._elementType, paramBeanProperty);; localJsonSerializer2 = paramSerializerProvider.handleSecondaryContextualization(localJsonSerializer2, paramBeanProperty)) {
      return withResolved(paramBeanProperty, localTypeSerializer, localJsonSerializer2, localBoolean);
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
    Class localClass;
    if (paramType != null)
    {
      JavaType localJavaType = paramSerializerProvider.constructType(paramType);
      if (localJavaType.isArrayType())
      {
        localClass = ((ArrayType)localJavaType).getContentType().getRawClass();
        if (localClass != Object.class) {
          break label59;
        }
        localObjectNode.set("items", JsonSchema.getDefaultSchemaNode());
      }
    }
    return localObjectNode;
    label59:
    JsonSerializer localJsonSerializer = paramSerializerProvider.findValueSerializer(localClass, this._property);
    if ((localJsonSerializer instanceof SchemaAware)) {}
    for (JsonNode localJsonNode = ((SchemaAware)localJsonSerializer).getSchema(paramSerializerProvider, null);; localJsonNode = JsonSchema.getDefaultSchemaNode())
    {
      localObjectNode.set("items", localJsonNode);
      break;
    }
  }
  
  public boolean hasSingleElement(Object[] paramArrayOfObject)
  {
    int i = 1;
    if (paramArrayOfObject.length == i) {}
    for (;;)
    {
      return i;
      int j = 0;
    }
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject == null) || (paramArrayOfObject.length == 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final void serialize(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramArrayOfObject.length;
    if ((i == 1) && (((this._unwrapSingle == null) && (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED))) || (this._unwrapSingle == Boolean.TRUE))) {
      serializeContents(paramArrayOfObject, paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartArray(i);
      serializeContents(paramArrayOfObject, paramJsonGenerator, paramSerializerProvider);
      paramJsonGenerator.writeEndArray();
    }
  }
  
  public void serializeContents(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramArrayOfObject.length;
    if (i == 0) {}
    for (;;)
    {
      return;
      if (this._elementSerializer != null)
      {
        serializeContentsUsing(paramArrayOfObject, paramJsonGenerator, paramSerializerProvider, this._elementSerializer);
      }
      else
      {
        if (this._valueTypeSerializer == null) {
          break;
        }
        serializeTypedContents(paramArrayOfObject, paramJsonGenerator, paramSerializerProvider);
      }
    }
    int j = 0;
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        localPropertySerializerMap = this._dynamicSerializers;
        if (j >= i) {
          break;
        }
        localObject1 = paramArrayOfObject[j];
        if (localObject1 == null)
        {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        }
        else
        {
          localClass = localObject1.getClass();
          localObject3 = localPropertySerializerMap.serializerFor(localClass);
          if (localObject3 == null)
          {
            if (this._elementType.hasGenericTypes()) {
              localObject3 = _findAndAddDynamic(localPropertySerializerMap, paramSerializerProvider.constructSpecializedType(this._elementType, localClass), paramSerializerProvider);
            }
          }
          else {
            ((JsonSerializer)localObject3).serialize(localObject1, paramJsonGenerator, paramSerializerProvider);
          }
        }
      }
      catch (IOException localIOException)
      {
        PropertySerializerMap localPropertySerializerMap;
        Class localClass;
        throw localIOException;
        JsonSerializer localJsonSerializer = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
        Object localObject3 = localJsonSerializer;
        continue;
      }
      catch (Exception localException)
      {
        Object localObject2 = localException;
        if (((localObject2 instanceof InvocationTargetException)) && (((Throwable)localObject2).getCause() != null))
        {
          localObject2 = ((Throwable)localObject2).getCause();
          continue;
        }
        if ((localObject2 instanceof Error)) {
          throw ((Error)localObject2);
        }
        throw JsonMappingException.wrapWithPath((Throwable)localObject2, localObject1, j);
      }
      j++;
    }
  }
  
  public void serializeContentsUsing(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException
  {
    int i = paramArrayOfObject.length;
    TypeSerializer localTypeSerializer = this._valueTypeSerializer;
    int j = 0;
    Object localObject1 = null;
    for (;;)
    {
      if (j < i) {
        try
        {
          localObject1 = paramArrayOfObject[j];
          if (localObject1 == null) {
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          } else if (localTypeSerializer == null) {
            paramJsonSerializer.serialize(localObject1, paramJsonGenerator, paramSerializerProvider);
          }
        }
        catch (IOException localIOException)
        {
          throw localIOException;
          paramJsonSerializer.serializeWithType(localObject1, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
        catch (Exception localException)
        {
          for (Object localObject2 = localException; ((localObject2 instanceof InvocationTargetException)) && (((Throwable)localObject2).getCause() != null); localObject2 = ((Throwable)localObject2).getCause()) {}
          if ((localObject2 instanceof Error)) {
            throw ((Error)localObject2);
          }
          throw JsonMappingException.wrapWithPath((Throwable)localObject2, localObject1, j);
        }
      }
      return;
      j++;
    }
  }
  
  public void serializeTypedContents(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    int i = paramArrayOfObject.length;
    TypeSerializer localTypeSerializer = this._valueTypeSerializer;
    int j = 0;
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        PropertySerializerMap localPropertySerializerMap = this._dynamicSerializers;
        if (j < i)
        {
          localObject1 = paramArrayOfObject[j];
          if (localObject1 == null)
          {
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          }
          else
          {
            Class localClass = localObject1.getClass();
            JsonSerializer localJsonSerializer = localPropertySerializerMap.serializerFor(localClass);
            if (localJsonSerializer == null) {
              localJsonSerializer = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
            }
            localJsonSerializer.serializeWithType(localObject1, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
          }
        }
      }
      catch (IOException localIOException)
      {
        throw localIOException;
      }
      catch (Exception localException)
      {
        Object localObject2 = localException;
        if (((localObject2 instanceof InvocationTargetException)) && (((Throwable)localObject2).getCause() != null))
        {
          localObject2 = ((Throwable)localObject2).getCause();
          continue;
        }
        if ((localObject2 instanceof Error)) {
          throw ((Error)localObject2);
        }
        throw JsonMappingException.wrapWithPath((Throwable)localObject2, localObject1, j);
      }
      return;
      j++;
    }
  }
  
  public ObjectArraySerializer withResolved(BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer, Boolean paramBoolean)
  {
    if ((this._property == paramBeanProperty) && (paramJsonSerializer == this._elementSerializer) && (this._valueTypeSerializer == paramTypeSerializer) && (this._unwrapSingle == paramBoolean)) {}
    for (;;)
    {
      return this;
      this = new ObjectArraySerializer(this, paramBeanProperty, paramTypeSerializer, paramJsonSerializer, paramBoolean);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/ObjectArraySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */