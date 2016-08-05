package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

@JacksonStdImpl
public class MapEntrySerializer
  extends ContainerSerializer<Map.Entry<?, ?>>
  implements ContextualSerializer
{
  protected PropertySerializerMap _dynamicValueSerializers;
  protected final JavaType _entryType;
  protected JsonSerializer<Object> _keySerializer;
  protected final JavaType _keyType;
  protected final BeanProperty _property;
  protected JsonSerializer<Object> _valueSerializer;
  protected final JavaType _valueType;
  protected final boolean _valueTypeIsStatic;
  protected final TypeSerializer _valueTypeSerializer;
  
  public MapEntrySerializer(JavaType paramJavaType1, JavaType paramJavaType2, JavaType paramJavaType3, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty)
  {
    super(paramJavaType1);
    this._entryType = paramJavaType1;
    this._keyType = paramJavaType2;
    this._valueType = paramJavaType3;
    this._valueTypeIsStatic = paramBoolean;
    this._valueTypeSerializer = paramTypeSerializer;
    this._property = paramBeanProperty;
    this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
  }
  
  protected MapEntrySerializer(MapEntrySerializer paramMapEntrySerializer, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer<?> paramJsonSerializer1, JsonSerializer<?> paramJsonSerializer2)
  {
    super(Map.class, false);
    this._entryType = paramMapEntrySerializer._entryType;
    this._keyType = paramMapEntrySerializer._keyType;
    this._valueType = paramMapEntrySerializer._valueType;
    this._valueTypeIsStatic = paramMapEntrySerializer._valueTypeIsStatic;
    this._valueTypeSerializer = paramMapEntrySerializer._valueTypeSerializer;
    this._keySerializer = paramJsonSerializer1;
    this._valueSerializer = paramJsonSerializer2;
    this._dynamicValueSerializers = paramMapEntrySerializer._dynamicValueSerializers;
    this._property = paramMapEntrySerializer._property;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, JavaType paramJavaType, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    PropertySerializerMap.SerializerAndMapResult localSerializerAndMapResult = paramPropertySerializerMap.findAndAddSecondarySerializer(paramJavaType, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != localSerializerAndMapResult.map) {
      this._dynamicValueSerializers = localSerializerAndMapResult.map;
    }
    return localSerializerAndMapResult.serializer;
  }
  
  protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    PropertySerializerMap.SerializerAndMapResult localSerializerAndMapResult = paramPropertySerializerMap.findAndAddSecondarySerializer(paramClass, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != localSerializerAndMapResult.map) {
      this._dynamicValueSerializers = localSerializerAndMapResult.map;
    }
    return localSerializerAndMapResult.serializer;
  }
  
  public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new MapEntrySerializer(this, this._property, paramTypeSerializer, this._keySerializer, this._valueSerializer);
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer1 = null;
    JsonSerializer localJsonSerializer2 = null;
    AnnotationIntrospector localAnnotationIntrospector = paramSerializerProvider.getAnnotationIntrospector();
    Object localObject1;
    JsonSerializer localJsonSerializer3;
    if (paramBeanProperty == null)
    {
      localObject1 = null;
      if ((localObject1 != null) && (localAnnotationIntrospector != null))
      {
        Object localObject2 = localAnnotationIntrospector.findKeySerializer((Annotated)localObject1);
        if (localObject2 != null) {
          localJsonSerializer2 = paramSerializerProvider.serializerInstance((Annotated)localObject1, localObject2);
        }
        Object localObject3 = localAnnotationIntrospector.findContentSerializer((Annotated)localObject1);
        if (localObject3 != null) {
          localJsonSerializer1 = paramSerializerProvider.serializerInstance((Annotated)localObject1, localObject3);
        }
      }
      if (localJsonSerializer1 == null) {
        localJsonSerializer1 = this._valueSerializer;
      }
      localJsonSerializer3 = findConvertingContentSerializer(paramSerializerProvider, paramBeanProperty, localJsonSerializer1);
      if (localJsonSerializer3 != null) {
        break label185;
      }
      if (((this._valueTypeIsStatic) && (this._valueType.getRawClass() != Object.class)) || (hasContentTypeAnnotation(paramSerializerProvider, paramBeanProperty))) {
        localJsonSerializer3 = paramSerializerProvider.findValueSerializer(this._valueType, paramBeanProperty);
      }
      label137:
      if (localJsonSerializer2 == null) {
        localJsonSerializer2 = this._keySerializer;
      }
      if (localJsonSerializer2 != null) {
        break label197;
      }
    }
    label185:
    label197:
    for (JsonSerializer localJsonSerializer4 = paramSerializerProvider.findKeySerializer(this._keyType, paramBeanProperty);; localJsonSerializer4 = paramSerializerProvider.handleSecondaryContextualization(localJsonSerializer2, paramBeanProperty))
    {
      return withResolved(paramBeanProperty, localJsonSerializer4, localJsonSerializer3);
      localObject1 = paramBeanProperty.getMember();
      break;
      localJsonSerializer3 = paramSerializerProvider.handleSecondaryContextualization(localJsonSerializer3, paramBeanProperty);
      break label137;
    }
  }
  
  public JsonSerializer<?> getContentSerializer()
  {
    return this._valueSerializer;
  }
  
  public JavaType getContentType()
  {
    return this._valueType;
  }
  
  public boolean hasSingleElement(Map.Entry<?, ?> paramEntry)
  {
    return true;
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, Map.Entry<?, ?> paramEntry)
  {
    if (paramEntry == null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void serialize(Map.Entry<?, ?> paramEntry, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    paramJsonGenerator.writeStartObject();
    paramJsonGenerator.setCurrentValue(paramEntry);
    if (this._valueSerializer != null) {
      serializeUsing(paramEntry, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
    }
    for (;;)
    {
      paramJsonGenerator.writeEndObject();
      return;
      serializeDynamic(paramEntry, paramJsonGenerator, paramSerializerProvider);
    }
  }
  
  protected void serializeDynamic(Map.Entry<?, ?> paramEntry, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    JsonSerializer localJsonSerializer1 = this._keySerializer;
    int i;
    TypeSerializer localTypeSerializer;
    PropertySerializerMap localPropertySerializerMap;
    Object localObject1;
    Object localObject2;
    if (!paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
    {
      i = 1;
      localTypeSerializer = this._valueTypeSerializer;
      localPropertySerializerMap = this._dynamicValueSerializers;
      localObject1 = paramEntry.getValue();
      localObject2 = paramEntry.getKey();
      if (localObject2 != null) {
        break label87;
      }
      paramSerializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, paramJsonGenerator, paramSerializerProvider);
      label70:
      if (localObject1 != null) {
        break label109;
      }
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      i = 0;
      break;
      label87:
      if ((i == 0) || (localObject1 != null))
      {
        localJsonSerializer1.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
        break label70;
        label109:
        Class localClass = localObject1.getClass();
        JsonSerializer localJsonSerializer2 = localPropertySerializerMap.serializerFor(localClass);
        if (localJsonSerializer2 == null) {
          if (!this._valueType.hasGenericTypes()) {
            break label214;
          }
        }
        label214:
        for (localJsonSerializer2 = _findAndAddDynamic(localPropertySerializerMap, paramSerializerProvider.constructSpecializedType(this._valueType, localClass), paramSerializerProvider);; localJsonSerializer2 = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider))
        {
          if (localTypeSerializer != null) {
            break label228;
          }
          try
          {
            localJsonSerializer2.serialize(localObject1, paramJsonGenerator, paramSerializerProvider);
          }
          catch (Exception localException)
          {
            wrapAndThrow(paramSerializerProvider, localException, paramEntry, "" + localObject2);
          }
          break;
        }
        label228:
        localJsonSerializer2.serializeWithType(localObject1, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
      }
    }
  }
  
  protected void serializeUsing(Map.Entry<?, ?> paramEntry, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException, JsonGenerationException
  {
    JsonSerializer localJsonSerializer = this._keySerializer;
    TypeSerializer localTypeSerializer = this._valueTypeSerializer;
    int i;
    Object localObject1;
    Object localObject2;
    if (!paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
    {
      i = 1;
      localObject1 = paramEntry.getValue();
      localObject2 = paramEntry.getKey();
      if (localObject2 != null) {
        break label81;
      }
      paramSerializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, paramJsonGenerator, paramSerializerProvider);
      label64:
      if (localObject1 != null) {
        break label103;
      }
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
    }
    for (;;)
    {
      return;
      i = 0;
      break;
      label81:
      if ((i == 0) || (localObject1 != null))
      {
        localJsonSerializer.serialize(localObject2, paramJsonGenerator, paramSerializerProvider);
        break label64;
        label103:
        if (localTypeSerializer == null) {
          try
          {
            paramJsonSerializer.serialize(localObject1, paramJsonGenerator, paramSerializerProvider);
          }
          catch (Exception localException)
          {
            wrapAndThrow(paramSerializerProvider, localException, paramEntry, "" + localObject2);
          }
        } else {
          paramJsonSerializer.serializeWithType(localObject1, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
      }
    }
  }
  
  public void serializeWithType(Map.Entry<?, ?> paramEntry, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    paramTypeSerializer.writeTypePrefixForObject(paramEntry, paramJsonGenerator);
    paramJsonGenerator.setCurrentValue(paramEntry);
    if (this._valueSerializer != null) {
      serializeUsing(paramEntry, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
    }
    for (;;)
    {
      paramTypeSerializer.writeTypeSuffixForObject(paramEntry, paramJsonGenerator);
      return;
      serializeDynamic(paramEntry, paramJsonGenerator, paramSerializerProvider);
    }
  }
  
  public MapEntrySerializer withResolved(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer1, JsonSerializer<?> paramJsonSerializer2)
  {
    return new MapEntrySerializer(this, paramBeanProperty, this._valueTypeSerializer, paramJsonSerializer1, paramJsonSerializer2);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/MapEntrySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */