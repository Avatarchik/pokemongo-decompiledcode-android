package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;

public class PropertyBasedObjectIdGenerator
  extends ObjectIdGenerators.PropertyGenerator
{
  private static final long serialVersionUID = 1L;
  protected final BeanPropertyWriter _property;
  
  public PropertyBasedObjectIdGenerator(ObjectIdInfo paramObjectIdInfo, BeanPropertyWriter paramBeanPropertyWriter)
  {
    this(paramObjectIdInfo.getScope(), paramBeanPropertyWriter);
  }
  
  protected PropertyBasedObjectIdGenerator(Class<?> paramClass, BeanPropertyWriter paramBeanPropertyWriter)
  {
    super(paramClass);
    this._property = paramBeanPropertyWriter;
  }
  
  public boolean canUseFor(ObjectIdGenerator<?> paramObjectIdGenerator)
  {
    boolean bool = false;
    if (paramObjectIdGenerator.getClass() == getClass())
    {
      PropertyBasedObjectIdGenerator localPropertyBasedObjectIdGenerator = (PropertyBasedObjectIdGenerator)paramObjectIdGenerator;
      if ((localPropertyBasedObjectIdGenerator.getScope() == this._scope) && (localPropertyBasedObjectIdGenerator._property == this._property)) {
        bool = true;
      }
    }
    return bool;
  }
  
  public ObjectIdGenerator<Object> forScope(Class<?> paramClass)
  {
    if (paramClass == this._scope) {}
    for (;;)
    {
      return this;
      this = new PropertyBasedObjectIdGenerator(paramClass, this._property);
    }
  }
  
  public Object generateId(Object paramObject)
  {
    try
    {
      Object localObject = this._property.get(paramObject);
      return localObject;
    }
    catch (RuntimeException localRuntimeException)
    {
      throw localRuntimeException;
    }
    catch (Exception localException)
    {
      throw new IllegalStateException("Problem accessing property '" + this._property.getName() + "': " + localException.getMessage(), localException);
    }
  }
  
  public ObjectIdGenerator.IdKey key(Object paramObject)
  {
    if (paramObject == null) {}
    for (ObjectIdGenerator.IdKey localIdKey = null;; localIdKey = new ObjectIdGenerator.IdKey(getClass(), this._scope, paramObject)) {
      return localIdKey;
    }
  }
  
  public ObjectIdGenerator<Object> newForSerialization(Object paramObject)
  {
    return this;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/ser/impl/PropertyBasedObjectIdGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */