package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsExistingPropertyTypeSerializer
  extends AsPropertyTypeSerializer
{
  public AsExistingPropertyTypeSerializer(TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty, String paramString)
  {
    super(paramTypeIdResolver, paramBeanProperty, paramString);
  }
  
  public AsExistingPropertyTypeSerializer forProperty(BeanProperty paramBeanProperty)
  {
    if (this._property == paramBeanProperty) {}
    for (;;)
    {
      return this;
      this = new AsExistingPropertyTypeSerializer(this._idResolver, paramBeanProperty, this._typePropertyName);
    }
  }
  
  public JsonTypeInfo.As getTypeInclusion()
  {
    return JsonTypeInfo.As.EXISTING_PROPERTY;
  }
  
  public void writeCustomTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator, String paramString)
    throws IOException
  {
    if ((paramString != null) && (paramJsonGenerator.canWriteTypeId())) {
      paramJsonGenerator.writeTypeId(paramString);
    }
    paramJsonGenerator.writeStartObject();
  }
  
  public void writeTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator)
    throws IOException
  {
    String str = idFromValue(paramObject);
    if ((str != null) && (paramJsonGenerator.canWriteTypeId())) {
      paramJsonGenerator.writeTypeId(str);
    }
    paramJsonGenerator.writeStartObject();
  }
  
  public void writeTypePrefixForObject(Object paramObject, JsonGenerator paramJsonGenerator, Class<?> paramClass)
    throws IOException
  {
    String str = idFromValueAndType(paramObject, paramClass);
    if ((str != null) && (paramJsonGenerator.canWriteTypeId())) {
      paramJsonGenerator.writeTypeId(str);
    }
    paramJsonGenerator.writeStartObject();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/impl/AsExistingPropertyTypeSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */