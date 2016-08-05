package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsWrapperTypeSerializer
  extends TypeSerializerBase
{
  public AsWrapperTypeSerializer(TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty)
  {
    super(paramTypeIdResolver, paramBeanProperty);
  }
  
  protected String _validTypeId(String paramString)
  {
    if (paramString == null) {
      paramString = "";
    }
    return paramString;
  }
  
  public AsWrapperTypeSerializer forProperty(BeanProperty paramBeanProperty)
  {
    if (this._property == paramBeanProperty) {}
    for (;;)
    {
      return this;
      this = new AsWrapperTypeSerializer(this._idResolver, paramBeanProperty);
    }
  }
  
  public JsonTypeInfo.As getTypeInclusion()
  {
    return JsonTypeInfo.As.WRAPPER_OBJECT;
  }
  
  public void writeCustomTypePrefixForArray(Object paramObject, JsonGenerator paramJsonGenerator, String paramString)
    throws IOException
  {
    if (paramJsonGenerator.canWriteTypeId())
    {
      if (paramString != null) {
        paramJsonGenerator.writeTypeId(paramString);
      }
      paramJsonGenerator.writeStartArray();
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartObject();
      paramJsonGenerator.writeArrayFieldStart(_validTypeId(paramString));
    }
  }
  
  public void writeCustomTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator, String paramString)
    throws IOException
  {
    if (paramJsonGenerator.canWriteTypeId())
    {
      if (paramString != null) {
        paramJsonGenerator.writeTypeId(paramString);
      }
      paramJsonGenerator.writeStartObject();
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartObject();
      paramJsonGenerator.writeObjectFieldStart(_validTypeId(paramString));
    }
  }
  
  public void writeCustomTypePrefixForScalar(Object paramObject, JsonGenerator paramJsonGenerator, String paramString)
    throws IOException
  {
    if (paramJsonGenerator.canWriteTypeId()) {
      if (paramString != null) {
        paramJsonGenerator.writeTypeId(paramString);
      }
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartObject();
      paramJsonGenerator.writeFieldName(_validTypeId(paramString));
    }
  }
  
  public void writeCustomTypeSuffixForArray(Object paramObject, JsonGenerator paramJsonGenerator, String paramString)
    throws IOException
  {
    if (!paramJsonGenerator.canWriteTypeId()) {
      writeTypeSuffixForArray(paramObject, paramJsonGenerator);
    }
  }
  
  public void writeCustomTypeSuffixForObject(Object paramObject, JsonGenerator paramJsonGenerator, String paramString)
    throws IOException
  {
    if (!paramJsonGenerator.canWriteTypeId()) {
      writeTypeSuffixForObject(paramObject, paramJsonGenerator);
    }
  }
  
  public void writeCustomTypeSuffixForScalar(Object paramObject, JsonGenerator paramJsonGenerator, String paramString)
    throws IOException
  {
    if (!paramJsonGenerator.canWriteTypeId()) {
      writeTypeSuffixForScalar(paramObject, paramJsonGenerator);
    }
  }
  
  public void writeTypePrefixForArray(Object paramObject, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    String str = idFromValue(paramObject);
    if (paramJsonGenerator.canWriteTypeId())
    {
      if (str != null) {
        paramJsonGenerator.writeTypeId(str);
      }
      paramJsonGenerator.writeStartArray();
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartObject();
      paramJsonGenerator.writeArrayFieldStart(_validTypeId(str));
    }
  }
  
  public void writeTypePrefixForArray(Object paramObject, JsonGenerator paramJsonGenerator, Class<?> paramClass)
    throws IOException
  {
    String str = idFromValueAndType(paramObject, paramClass);
    if (paramJsonGenerator.canWriteTypeId())
    {
      if (str != null) {
        paramJsonGenerator.writeTypeId(str);
      }
      paramJsonGenerator.writeStartArray();
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartObject();
      paramJsonGenerator.writeArrayFieldStart(_validTypeId(str));
    }
  }
  
  public void writeTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    String str = idFromValue(paramObject);
    if (paramJsonGenerator.canWriteTypeId())
    {
      if (str != null) {
        paramJsonGenerator.writeTypeId(str);
      }
      paramJsonGenerator.writeStartObject();
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartObject();
      paramJsonGenerator.writeObjectFieldStart(_validTypeId(str));
    }
  }
  
  public void writeTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator, Class<?> paramClass)
    throws IOException
  {
    String str = idFromValueAndType(paramObject, paramClass);
    if (paramJsonGenerator.canWriteTypeId())
    {
      if (str != null) {
        paramJsonGenerator.writeTypeId(str);
      }
      paramJsonGenerator.writeStartObject();
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartObject();
      paramJsonGenerator.writeObjectFieldStart(_validTypeId(str));
    }
  }
  
  public void writeTypePrefixForScalar(Object paramObject, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    String str = idFromValue(paramObject);
    if (paramJsonGenerator.canWriteTypeId()) {
      if (str != null) {
        paramJsonGenerator.writeTypeId(str);
      }
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartObject();
      paramJsonGenerator.writeFieldName(_validTypeId(str));
    }
  }
  
  public void writeTypePrefixForScalar(Object paramObject, JsonGenerator paramJsonGenerator, Class<?> paramClass)
    throws IOException
  {
    String str = idFromValueAndType(paramObject, paramClass);
    if (paramJsonGenerator.canWriteTypeId()) {
      if (str != null) {
        paramJsonGenerator.writeTypeId(str);
      }
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeStartObject();
      paramJsonGenerator.writeFieldName(_validTypeId(str));
    }
  }
  
  public void writeTypeSuffixForArray(Object paramObject, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    paramJsonGenerator.writeEndArray();
    if (!paramJsonGenerator.canWriteTypeId()) {
      paramJsonGenerator.writeEndObject();
    }
  }
  
  public void writeTypeSuffixForObject(Object paramObject, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    paramJsonGenerator.writeEndObject();
    if (!paramJsonGenerator.canWriteTypeId()) {
      paramJsonGenerator.writeEndObject();
    }
  }
  
  public void writeTypeSuffixForScalar(Object paramObject, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if (!paramJsonGenerator.canWriteTypeId()) {
      paramJsonGenerator.writeEndObject();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/impl/AsWrapperTypeSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */