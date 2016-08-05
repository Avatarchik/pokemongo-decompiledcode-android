package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@JacksonStdImpl
public class EnumSerializer
  extends StdScalarSerializer<Enum<?>>
  implements ContextualSerializer
{
  private static final long serialVersionUID = 1L;
  protected final Boolean _serializeAsIndex;
  protected final EnumValues _values;
  
  @Deprecated
  public EnumSerializer(EnumValues paramEnumValues)
  {
    this(paramEnumValues, null);
  }
  
  public EnumSerializer(EnumValues paramEnumValues, Boolean paramBoolean)
  {
    super(paramEnumValues.getEnumClass(), false);
    this._values = paramEnumValues;
    this._serializeAsIndex = paramBoolean;
  }
  
  protected static Boolean _isShapeWrittenUsingIndex(Class<?> paramClass, JsonFormat.Value paramValue, boolean paramBoolean)
  {
    Boolean localBoolean = null;
    JsonFormat.Shape localShape;
    if (paramValue == null)
    {
      localShape = null;
      if (localShape != null) {
        break label25;
      }
    }
    for (;;)
    {
      return localBoolean;
      localShape = paramValue.getShape();
      break;
      label25:
      if ((localShape != JsonFormat.Shape.ANY) && (localShape != JsonFormat.Shape.SCALAR)) {
        if (localShape == JsonFormat.Shape.STRING)
        {
          localBoolean = Boolean.FALSE;
        }
        else
        {
          if ((!localShape.isNumeric()) && (localShape != JsonFormat.Shape.ARRAY)) {
            break label79;
          }
          localBoolean = Boolean.TRUE;
        }
      }
    }
    label79:
    StringBuilder localStringBuilder = new StringBuilder().append("Unsupported serialization shape (").append(localShape).append(") for Enum ").append(paramClass.getName()).append(", not supported as ");
    if (paramBoolean) {}
    for (String str = "class";; str = "property") {
      throw new IllegalArgumentException(str + " annotation");
    }
  }
  
  public static EnumSerializer construct(Class<?> paramClass, SerializationConfig paramSerializationConfig, BeanDescription paramBeanDescription, JsonFormat.Value paramValue)
  {
    return new EnumSerializer(EnumValues.constructFromName(paramSerializationConfig, paramClass), _isShapeWrittenUsingIndex(paramClass, paramValue, true));
  }
  
  protected final boolean _serializeAsIndex(SerializerProvider paramSerializerProvider)
  {
    if (this._serializeAsIndex != null) {}
    for (boolean bool = this._serializeAsIndex.booleanValue();; bool = paramSerializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX)) {
      return bool;
    }
  }
  
  public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper paramJsonFormatVisitorWrapper, JavaType paramJavaType)
    throws JsonMappingException
  {
    SerializerProvider localSerializerProvider = paramJsonFormatVisitorWrapper.getProvider();
    if (_serializeAsIndex(localSerializerProvider))
    {
      JsonIntegerFormatVisitor localJsonIntegerFormatVisitor = paramJsonFormatVisitorWrapper.expectIntegerFormat(paramJavaType);
      if (localJsonIntegerFormatVisitor != null) {
        localJsonIntegerFormatVisitor.numberType(JsonParser.NumberType.INT);
      }
    }
    for (;;)
    {
      return;
      JsonStringFormatVisitor localJsonStringFormatVisitor = paramJsonFormatVisitorWrapper.expectStringFormat(paramJavaType);
      if (localJsonStringFormatVisitor != null)
      {
        LinkedHashSet localLinkedHashSet = new LinkedHashSet();
        Iterator localIterator2;
        if ((localSerializerProvider != null) && (localSerializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING))) {
          localIterator2 = this._values.enums().iterator();
        }
        while (localIterator2.hasNext())
        {
          localLinkedHashSet.add(((Enum)localIterator2.next()).toString());
          continue;
          Iterator localIterator1 = this._values.values().iterator();
          while (localIterator1.hasNext()) {
            localLinkedHashSet.add(((SerializableString)localIterator1.next()).getValue());
          }
        }
        localJsonStringFormatVisitor.enumTypes(localLinkedHashSet);
      }
    }
  }
  
  public JsonSerializer<?> createContextual(SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty)
    throws JsonMappingException
  {
    if (paramBeanProperty != null)
    {
      JsonFormat.Value localValue = paramSerializerProvider.getAnnotationIntrospector().findFormat(paramBeanProperty.getMember());
      if (localValue != null)
      {
        Boolean localBoolean = _isShapeWrittenUsingIndex(paramBeanProperty.getType().getRawClass(), localValue, false);
        if (localBoolean != this._serializeAsIndex) {
          this = new EnumSerializer(this._values, localBoolean);
        }
      }
    }
    return this;
  }
  
  public EnumValues getEnumValues()
  {
    return this._values;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType)
  {
    ObjectNode localObjectNode;
    if (_serializeAsIndex(paramSerializerProvider)) {
      localObjectNode = createSchemaNode("integer", true);
    }
    for (;;)
    {
      return localObjectNode;
      localObjectNode = createSchemaNode("string", true);
      if ((paramType != null) && (paramSerializerProvider.constructType(paramType).isEnumType()))
      {
        ArrayNode localArrayNode = localObjectNode.putArray("enum");
        Iterator localIterator = this._values.values().iterator();
        while (localIterator.hasNext()) {
          localArrayNode.add(((SerializableString)localIterator.next()).getValue());
        }
      }
    }
  }
  
  public final void serialize(Enum<?> paramEnum, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if (_serializeAsIndex(paramSerializerProvider)) {
      paramJsonGenerator.writeNumber(paramEnum.ordinal());
    }
    for (;;)
    {
      return;
      if (paramSerializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
        paramJsonGenerator.writeString(paramEnum.toString());
      } else {
        paramJsonGenerator.writeString(this._values.serializedValueFor(paramEnum));
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/std/EnumSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */