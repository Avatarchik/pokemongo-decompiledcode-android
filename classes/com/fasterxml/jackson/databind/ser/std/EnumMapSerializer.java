package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

@JacksonStdImpl
@Deprecated
public class EnumMapSerializer
  extends ContainerSerializer<EnumMap<? extends Enum<?>, ?>>
  implements ContextualSerializer
{
  protected final EnumValues _keyEnums;
  protected final BeanProperty _property;
  protected final boolean _staticTyping;
  protected final JsonSerializer<Object> _valueSerializer;
  protected final JavaType _valueType;
  protected final TypeSerializer _valueTypeSerializer;
  
  public EnumMapSerializer(JavaType paramJavaType, boolean paramBoolean, EnumValues paramEnumValues, TypeSerializer paramTypeSerializer, JsonSerializer<Object> paramJsonSerializer)
  {
    super(EnumMap.class, false);
    this._property = null;
    if ((paramBoolean) || ((paramJavaType != null) && (paramJavaType.isFinal()))) {
      bool = true;
    }
    this._staticTyping = bool;
    this._valueType = paramJavaType;
    this._keyEnums = paramEnumValues;
    this._valueTypeSerializer = paramTypeSerializer;
    this._valueSerializer = paramJsonSerializer;
  }
  
  public EnumMapSerializer(EnumMapSerializer paramEnumMapSerializer, BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer)
  {
    super(paramEnumMapSerializer);
    this._property = paramBeanProperty;
    this._staticTyping = paramEnumMapSerializer._staticTyping;
    this._valueType = paramEnumMapSerializer._valueType;
    this._keyEnums = paramEnumMapSerializer._keyEnums;
    this._valueTypeSerializer = paramEnumMapSerializer._valueTypeSerializer;
    this._valueSerializer = paramJsonSerializer;
  }
  
  public EnumMapSerializer _withValueTypeSerializer(TypeSerializer paramTypeSerializer)
  {
    return new EnumMapSerializer(this._valueType, this._staticTyping, this._keyEnums, paramTypeSerializer, this._valueSerializer);
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    if (paramJsonFormatVisitorWrapper == null) {}
    for (;;)
    {
      return;
      JsonObjectFormatVisitor localJsonObjectFormatVisitor = paramJsonFormatVisitorWrapper.expectObjectFormat(paramJavaType);
      if (localJsonObjectFormatVisitor != null)
      {
        JavaType localJavaType1 = paramJavaType.containedType(1);
        JsonSerializer localJsonSerializer1 = this._valueSerializer;
        if ((localJsonSerializer1 == null) && (localJavaType1 != null)) {
          localJsonSerializer1 = paramJsonFormatVisitorWrapper.getProvider().findValueSerializer(localJavaType1, this._property);
        }
        if (localJavaType1 == null) {
          localJavaType1 = paramJsonFormatVisitorWrapper.getProvider().constructType(Object.class);
        }
        EnumValues localEnumValues = this._keyEnums;
        if (localEnumValues == null)
        {
          JavaType localJavaType2 = paramJavaType.containedType(0);
          if (localJavaType2 == null) {
            throw new IllegalStateException("Can not resolve Enum type of EnumMap: " + paramJavaType);
          }
          JsonSerializer localJsonSerializer2 = paramJsonFormatVisitorWrapper.getProvider().findValueSerializer(localJavaType2, this._property);
          if (!(localJsonSerializer2 instanceof EnumSerializer)) {
            throw new IllegalStateException("Can not resolve Enum type of EnumMap: " + paramJavaType);
          }
          localEnumValues = ((EnumSerializer)localJsonSerializer2).getEnumValues();
        }
        Iterator localIterator = localEnumValues.internalMap().entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          String str = ((SerializableString)localEntry.getValue()).getValue();
          if (localJsonSerializer1 == null) {
            localJsonSerializer1 = paramJsonFormatVisitorWrapper.getProvider().findValueSerializer(localEntry.getKey().getClass(), this._property);
          }
          localJsonObjectFormatVisitor.property(str, localJsonSerializer1, localJavaType1);
        }
      }
    }
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    JsonSerializer localJsonSerializer1 = null;
    if (paramBeanProperty != null)
    {
      AnnotatedMember localAnnotatedMember = paramBeanProperty.getMember();
      if (localAnnotatedMember != null)
      {
        Object localObject = paramSerializerProvider.getAnnotationIntrospector().findContentSerializer(localAnnotatedMember);
        if (localObject != null) {
          localJsonSerializer1 = paramSerializerProvider.serializerInstance(localAnnotatedMember, localObject);
        }
      }
    }
    if (localJsonSerializer1 == null) {
      localJsonSerializer1 = this._valueSerializer;
    }
    JsonSerializer localJsonSerializer2 = findConvertingContentSerializer(paramSerializerProvider, paramBeanProperty, localJsonSerializer1);
    if (localJsonSerializer2 == null)
    {
      if (!this._staticTyping) {
        break label100;
      }
      this = withValueSerializer(paramBeanProperty, paramSerializerProvider.findValueSerializer(this._valueType, paramBeanProperty));
    }
    for (;;)
    {
      return this;
      localJsonSerializer2 = paramSerializerProvider.handleSecondaryContextualization(localJsonSerializer2, paramBeanProperty);
      label100:
      if (localJsonSerializer2 != this._valueSerializer) {
        this = withValueSerializer(paramBeanProperty, localJsonSerializer2);
      }
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
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
    throws JsonMappingException
  {
    ObjectNode localObjectNode1 = createSchemaNode("object", true);
    if ((paramType instanceof ParameterizedType))
    {
      Type[] arrayOfType = ((ParameterizedType)paramType).getActualTypeArguments();
      if (arrayOfType.length == 2)
      {
        JavaType localJavaType1 = paramSerializerProvider.constructType(arrayOfType[0]);
        JavaType localJavaType2 = paramSerializerProvider.constructType(arrayOfType[1]);
        ObjectNode localObjectNode2 = JsonNodeFactory.instance.objectNode();
        Enum[] arrayOfEnum = (Enum[])localJavaType1.getRawClass().getEnumConstants();
        int i = arrayOfEnum.length;
        int j = 0;
        if (j < i)
        {
          Enum localEnum = arrayOfEnum[j];
          JsonSerializer localJsonSerializer = paramSerializerProvider.findValueSerializer(localJavaType2.getRawClass(), this._property);
          if ((localJsonSerializer instanceof SchemaAware)) {}
          for (JsonNode localJsonNode = ((SchemaAware)localJsonSerializer).getSchema(paramSerializerProvider, null);; localJsonNode = JsonSchema.getDefaultSchemaNode())
          {
            localObjectNode2.set(paramSerializerProvider.getConfig().getAnnotationIntrospector().findEnumValue(localEnum), localJsonNode);
            j++;
            break;
          }
        }
        localObjectNode1.set("properties", localObjectNode2);
      }
    }
    return localObjectNode1;
  }
  
  public boolean hasSingleElement(EnumMap<? extends Enum<?>, ?> paramEnumMap)
  {
    int i = 1;
    if (paramEnumMap.size() == i) {}
    for (;;)
    {
      return i;
      int j = 0;
    }
  }
  
  public boolean isEmpty(SerializerProvider paramSerializerProvider, EnumMap<? extends Enum<?>, ?> paramEnumMap)
  {
    if ((paramEnumMap == null) || (paramEnumMap.isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void serialize(EnumMap<? extends Enum<?>, ?> paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    paramJsonGenerator.writeStartObject();
    if (!paramEnumMap.isEmpty()) {
      serializeContents(paramEnumMap, paramJsonGenerator, paramSerializerProvider);
    }
    paramJsonGenerator.writeEndObject();
  }
  
  protected void serializeContents(EnumMap<? extends Enum<?>, ?> paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException, JsonGenerationException
  {
    if (this._valueSerializer != null) {
      serializeContentsUsing(paramEnumMap, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
    }
    label70:
    label199:
    label283:
    label295:
    for (;;)
    {
      return;
      Object localObject1 = null;
      Object localObject2 = null;
      EnumValues localEnumValues = this._keyEnums;
      int i;
      boolean bool;
      TypeSerializer localTypeSerializer;
      Iterator localIterator;
      if (!paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
      {
        i = 1;
        bool = paramSerializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        localTypeSerializer = this._valueTypeSerializer;
        localIterator = paramEnumMap.entrySet().iterator();
      }
      for (;;)
      {
        if (!localIterator.hasNext()) {
          break label295;
        }
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        Object localObject3 = localEntry.getValue();
        if ((i == 0) || (localObject3 != null))
        {
          Enum localEnum = (Enum)localEntry.getKey();
          if (bool) {
            paramJsonGenerator.writeFieldName(localEnum.toString());
          }
          for (;;)
          {
            if (localObject3 != null) {
              break label199;
            }
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
            break label70;
            i = 0;
            break;
            if (localEnumValues == null) {
              localEnumValues = ((EnumSerializer)paramSerializerProvider.findValueSerializer(localEnum.getDeclaringClass(), this._property)).getEnumValues();
            }
            paramJsonGenerator.writeFieldName(localEnumValues.serializedValueFor(localEnum));
          }
          Class localClass = localObject3.getClass();
          Object localObject4;
          if (localClass == localObject2) {
            localObject4 = localObject1;
          }
          for (;;)
          {
            if (localTypeSerializer != null) {
              break label283;
            }
            try
            {
              ((JsonSerializer)localObject4).serialize(localObject3, paramJsonGenerator, paramSerializerProvider);
            }
            catch (Exception localException)
            {
              wrapAndThrow(paramSerializerProvider, localException, paramEnumMap, ((Enum)localEntry.getKey()).name());
            }
            break;
            localObject4 = paramSerializerProvider.findValueSerializer(localClass, this._property);
            localObject1 = localObject4;
            localObject2 = localClass;
          }
          ((JsonSerializer)localObject4).serializeWithType(localObject3, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
      }
    }
  }
  
  protected void serializeContentsUsing(EnumMap<? extends Enum<?>, ?> paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer<Object> paramJsonSerializer)
    throws IOException, JsonGenerationException
  {
    EnumValues localEnumValues = this._keyEnums;
    int i;
    boolean bool;
    TypeSerializer localTypeSerializer;
    Iterator localIterator;
    if (!paramSerializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES))
    {
      i = 1;
      bool = paramSerializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
      localTypeSerializer = this._valueTypeSerializer;
      localIterator = paramEnumMap.entrySet().iterator();
    }
    for (;;)
    {
      label45:
      if (!localIterator.hasNext()) {
        return;
      }
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Object localObject = localEntry.getValue();
      if ((i == 0) || (localObject != null))
      {
        Enum localEnum = (Enum)localEntry.getKey();
        if (bool) {
          paramJsonGenerator.writeFieldName(localEnum.toString());
        }
        for (;;)
        {
          if (localObject != null) {
            break label174;
          }
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          break label45;
          i = 0;
          break;
          if (localEnumValues == null) {
            localEnumValues = ((EnumSerializer)paramSerializerProvider.findValueSerializer(localEnum.getDeclaringClass(), this._property)).getEnumValues();
          }
          paramJsonGenerator.writeFieldName(localEnumValues.serializedValueFor(localEnum));
        }
        label174:
        if (localTypeSerializer == null) {
          try
          {
            paramJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
          }
          catch (Exception localException)
          {
            wrapAndThrow(paramSerializerProvider, localException, paramEnumMap, ((Enum)localEntry.getKey()).name());
          }
        } else {
          paramJsonSerializer.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, localTypeSerializer);
        }
      }
    }
  }
  
  public void serializeWithType(EnumMap<? extends Enum<?>, ?> paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException, JsonGenerationException
  {
    paramTypeSerializer.writeTypePrefixForObject(paramEnumMap, paramJsonGenerator);
    if (!paramEnumMap.isEmpty()) {
      serializeContents(paramEnumMap, paramJsonGenerator, paramSerializerProvider);
    }
    paramTypeSerializer.writeTypeSuffixForObject(paramEnumMap, paramJsonGenerator);
  }
  
  public EnumMapSerializer withValueSerializer(BeanProperty paramBeanProperty, JsonSerializer<?> paramJsonSerializer)
  {
    if ((this._property == paramBeanProperty) && (paramJsonSerializer == this._valueSerializer)) {}
    for (;;)
    {
      return this;
      this = new EnumMapSerializer(this, paramBeanProperty, paramJsonSerializer);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/EnumMapSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */