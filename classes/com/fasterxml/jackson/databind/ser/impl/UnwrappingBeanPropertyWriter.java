package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper.Base;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map.Entry;

public class UnwrappingBeanPropertyWriter
  extends BeanPropertyWriter
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final NameTransformer _nameTransformer;
  
  public UnwrappingBeanPropertyWriter(BeanPropertyWriter paramBeanPropertyWriter, NameTransformer paramNameTransformer)
  {
    super(paramBeanPropertyWriter);
    this._nameTransformer = paramNameTransformer;
  }
  
  protected UnwrappingBeanPropertyWriter(UnwrappingBeanPropertyWriter paramUnwrappingBeanPropertyWriter, NameTransformer paramNameTransformer, SerializedString paramSerializedString)
  {
    super(paramUnwrappingBeanPropertyWriter, paramSerializedString);
    this._nameTransformer = paramNameTransformer;
  }
  
  protected void _depositSchemaProperty(ObjectNode paramObjectNode, JsonNode paramJsonNode)
  {
    JsonNode localJsonNode = paramJsonNode.get("properties");
    if (localJsonNode != null)
    {
      Iterator localIterator = localJsonNode.fields();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        String str = (String)localEntry.getKey();
        if (this._nameTransformer != null) {
          str = this._nameTransformer.transform(str);
        }
        paramObjectNode.set(str, (JsonNode)localEntry.getValue());
      }
    }
  }
  
  protected JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class<?> paramClass, SerializerProvider paramSerializerProvider)
    throws JsonMappingException
  {
    if (this._nonTrivialBaseType != null) {}
    for (JsonSerializer localJsonSerializer1 = paramSerializerProvider.findValueSerializer(paramSerializerProvider.constructSpecializedType(this._nonTrivialBaseType, paramClass), this);; localJsonSerializer1 = paramSerializerProvider.findValueSerializer(paramClass, this))
    {
      NameTransformer localNameTransformer = this._nameTransformer;
      if (localJsonSerializer1.isUnwrappingSerializer()) {
        localNameTransformer = NameTransformer.chainedTransformer(localNameTransformer, ((UnwrappingBeanSerializer)localJsonSerializer1)._nameTransformer);
      }
      JsonSerializer localJsonSerializer2 = localJsonSerializer1.unwrappingSerializer(localNameTransformer);
      this._dynamicSerializers = this._dynamicSerializers.newWith(paramClass, localJsonSerializer2);
      return localJsonSerializer2;
    }
  }
  
  protected UnwrappingBeanPropertyWriter _new(NameTransformer paramNameTransformer, SerializedString paramSerializedString)
  {
    return new UnwrappingBeanPropertyWriter(this, paramNameTransformer, paramSerializedString);
  }
  
  public void assignSerializer(JsonSerializer<Object> paramJsonSerializer)
  {
    super.assignSerializer(paramJsonSerializer);
    if (this._serializer != null)
    {
      NameTransformer localNameTransformer = this._nameTransformer;
      if (this._serializer.isUnwrappingSerializer()) {
        localNameTransformer = NameTransformer.chainedTransformer(localNameTransformer, ((UnwrappingBeanSerializer)this._serializer)._nameTransformer);
      }
      this._serializer = this._serializer.unwrappingSerializer(localNameTransformer);
    }
  }
  
  public void depositSchemaProperty(final JsonObjectFormatVisitor paramJsonObjectFormatVisitor)
    throws JsonMappingException
  {
    SerializerProvider localSerializerProvider = paramJsonObjectFormatVisitor.getProvider();
    JsonSerializer localJsonSerializer = localSerializerProvider.findValueSerializer(getType(), this).unwrappingSerializer(this._nameTransformer);
    if (localJsonSerializer.isUnwrappingSerializer()) {
      localJsonSerializer.acceptJsonFormatVisitor(new JsonFormatVisitorWrapper.Base(localSerializerProvider)
      {
        public JsonObjectFormatVisitor expectObjectFormat(JavaType paramAnonymousJavaType)
          throws JsonMappingException
        {
          return paramJsonObjectFormatVisitor;
        }
      }, getType());
    }
    for (;;)
    {
      return;
      super.depositSchemaProperty(paramJsonObjectFormatVisitor);
    }
  }
  
  public boolean isUnwrapping()
  {
    return true;
  }
  
  public UnwrappingBeanPropertyWriter rename(NameTransformer paramNameTransformer)
  {
    String str = paramNameTransformer.transform(this._name.getValue());
    return _new(NameTransformer.chainedTransformer(paramNameTransformer, this._nameTransformer), new SerializedString(str));
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws Exception
  {
    Object localObject = get(paramObject);
    if (localObject == null) {
      return;
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
        if ((localObject == paramObject) && (_handleSelfReference(paramObject, paramJsonGenerator, paramSerializerProvider, localJsonSerializer))) {
          continue;
        }
        if (!localJsonSerializer.isUnwrappingSerializer()) {
          paramJsonGenerator.writeFieldName(this._name);
        }
        if (this._typeSerializer != null) {
          break label158;
        }
        localJsonSerializer.serialize(localObject, paramJsonGenerator, paramSerializerProvider);
        continue;
      }
      if (!this._suppressableValue.equals(localObject)) {
        break;
      }
      continue;
      label158:
      localJsonSerializer.serializeWithType(localObject, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/UnwrappingBeanPropertyWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */