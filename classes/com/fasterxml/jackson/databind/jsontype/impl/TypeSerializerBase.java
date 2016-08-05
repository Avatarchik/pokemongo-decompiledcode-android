package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public abstract class TypeSerializerBase
  extends TypeSerializer
{
  protected final TypeIdResolver _idResolver;
  protected final BeanProperty _property;
  
  protected TypeSerializerBase(TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty)
  {
    this._idResolver = paramTypeIdResolver;
    this._property = paramBeanProperty;
  }
  
  public String getPropertyName()
  {
    return null;
  }
  
  public TypeIdResolver getTypeIdResolver()
  {
    return this._idResolver;
  }
  
  public abstract JsonTypeInfo.As getTypeInclusion();
  
  protected void handleMissingId(Object paramObject) {}
  
  protected String idFromValue(Object paramObject)
  {
    String str = this._idResolver.idFromValue(paramObject);
    if (str == null) {
      handleMissingId(paramObject);
    }
    return str;
  }
  
  protected String idFromValueAndType(Object paramObject, Class<?> paramClass)
  {
    String str = this._idResolver.idFromValueAndType(paramObject, paramClass);
    if (str == null) {
      handleMissingId(paramObject);
    }
    return str;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/impl/TypeSerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */