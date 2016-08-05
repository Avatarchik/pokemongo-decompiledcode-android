package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.util.Annotations;
import java.io.Serializable;
import java.lang.reflect.Type;

public abstract class VirtualBeanPropertyWriter
  extends BeanPropertyWriter
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  protected VirtualBeanPropertyWriter() {}
  
  protected VirtualBeanPropertyWriter(BeanPropertyDefinition paramBeanPropertyDefinition, Annotations paramAnnotations, JavaType paramJavaType)
  {
    this(paramBeanPropertyDefinition, paramAnnotations, paramJavaType, null, null, null, paramBeanPropertyDefinition.findInclusion());
  }
  
  protected VirtualBeanPropertyWriter(BeanPropertyDefinition paramBeanPropertyDefinition, Annotations paramAnnotations, JavaType paramJavaType1, JsonSerializer<?> paramJsonSerializer, TypeSerializer paramTypeSerializer, JavaType paramJavaType2, JsonInclude.Include paramInclude)
  {
    super(paramBeanPropertyDefinition, paramBeanPropertyDefinition.getPrimaryMember(), paramAnnotations, paramJavaType1, paramJsonSerializer, paramTypeSerializer, paramJavaType2, _suppressNulls(paramInclude), _suppressableValue(paramInclude));
  }
  
  protected VirtualBeanPropertyWriter(VirtualBeanPropertyWriter paramVirtualBeanPropertyWriter)
  {
    super(paramVirtualBeanPropertyWriter);
  }
  
  protected VirtualBeanPropertyWriter(VirtualBeanPropertyWriter paramVirtualBeanPropertyWriter, PropertyName paramPropertyName)
  {
    super(paramVirtualBeanPropertyWriter, paramPropertyName);
  }
  
  protected static boolean _suppressNulls(JsonInclude.Include paramInclude)
  {
    if (paramInclude != JsonInclude.Include.ALWAYS) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  protected static Object _suppressableValue(JsonInclude.Include paramInclude)
  {
    if ((paramInclude == JsonInclude.Include.NON_EMPTY) || (paramInclude == JsonInclude.Include.NON_EMPTY)) {}
    for (Object localObject = MARKER_FOR_EMPTY;; localObject = null) {
      return localObject;
    }
  }
  
  public Type getGenericPropertyType()
  {
    return getPropertyType();
  }
  
  public Class<?> getPropertyType()
  {
    return this._declaredType.getRawClass();
  }
  
  public boolean isVirtual()
  {
    return true;
  }
  
  public void serializeAsElement(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    Object localObject = value(paramObject, paramJsonGenerator, paramSerializerProvider);
    if (localObject == null) {
      if (this._nullSerializer != null) {
        this._nullSerializer.serialize(null, paramJsonGenerator, paramSerializerProvider);
      }
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeNull();
      continue;
      JsonSerializer localJsonSerializer = this._serializer;
      if (localJsonSerializer == null)
      {
        Class localClass = localObject.getClass();
        PropertySerializerMap localPropertySerializerMap = this._dynamicSerializers;
        localJsonSerializer = localPropertySerializerMap.serializerFor(localClass);
        if (localJsonSerializer == null) {
          localJsonSerializer = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
        }
      }
      if (this._suppressableValue != null) {
        if (MARKER_FOR_EMPTY == this._suppressableValue)
        {
          if (localJsonSerializer.isEmpty(paramSerializerProvider, localObject)) {
            serializeAsPlaceholder(paramObject, paramJsonGenerator, paramSerializerProvider);
          }
        }
        else if (this._suppressableValue.equals(localObject))
        {
          serializeAsPlaceholder(paramObject, paramJsonGenerator, paramSerializerProvider);
          continue;
        }
      }
      if ((localObject != paramObject) || (!_handleSelfReference(paramObject, paramJsonGenerator, paramSerializerProvider, localJsonSerializer))) {
        if (this._typeSerializer == null) {
          localJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
        } else {
          localJsonSerializer.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
        }
      }
    }
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    Object localObject = value(paramObject, paramJsonGenerator, paramSerializerProvider);
    if (localObject == null)
    {
      if (this._nullSerializer != null)
      {
        paramJsonGenerator.writeFieldName(this._name);
        this._nullSerializer.serialize(null, paramJsonGenerator, paramSerializerProvider);
      }
      return;
      break label117;
    }
    for (;;)
    {
      JsonSerializer localJsonSerializer = this._serializer;
      if (localJsonSerializer == null)
      {
        Class localClass = localObject.getClass();
        PropertySerializerMap localPropertySerializerMap = this._dynamicSerializers;
        localJsonSerializer = localPropertySerializerMap.serializerFor(localClass);
        if (localJsonSerializer == null) {
          localJsonSerializer = _findAndAddDynamic(localPropertySerializerMap, localClass, paramSerializerProvider);
        }
      }
      if (this._suppressableValue != null)
      {
        if (MARKER_FOR_EMPTY == this._suppressableValue) {
          if (localJsonSerializer.isEmpty(paramSerializerProvider, localObject)) {
            continue;
          }
        }
      }
      else
      {
        label117:
        if ((localObject == paramObject) && (_handleSelfReference(paramObject, paramJsonGenerator, paramSerializerProvider, localJsonSerializer))) {
          continue;
        }
        paramJsonGenerator.writeFieldName(this._name);
        if (this._typeSerializer != null) {
          break label177;
        }
        localJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
        continue;
      }
      if (!this._suppressableValue.equals(localObject)) {
        break;
      }
      continue;
      label177:
      localJsonSerializer.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
    }
  }
  
  protected abstract Object value(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception;
  
  public abstract VirtualBeanPropertyWriter withConfig(MapperConfig<?> paramMapperConfig, AnnotatedClass paramAnnotatedClass, BeanPropertyDefinition paramBeanPropertyDefinition, JavaType paramJavaType);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/VirtualBeanPropertyWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */