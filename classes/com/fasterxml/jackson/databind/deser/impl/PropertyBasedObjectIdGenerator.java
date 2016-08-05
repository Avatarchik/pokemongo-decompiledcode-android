package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

public class PropertyBasedObjectIdGenerator
  extends ObjectIdGenerators.PropertyGenerator
{
  private static final long serialVersionUID = 1L;
  
  public PropertyBasedObjectIdGenerator(Class<?> paramClass)
  {
    super(paramClass);
  }
  
  public ObjectIdGenerator<Object> forScope(Class<?> paramClass)
  {
    if (paramClass == this._scope) {}
    for (;;)
    {
      return this;
      this = new PropertyBasedObjectIdGenerator(paramClass);
    }
  }
  
  public Object generateId(Object paramObject)
  {
    throw new UnsupportedOperationException();
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/PropertyBasedObjectIdGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */