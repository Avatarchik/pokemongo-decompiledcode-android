package com.fasterxml.jackson.annotation;

public abstract interface ObjectIdResolver
{
  public abstract void bindItem(ObjectIdGenerator.IdKey paramIdKey, Object paramObject);
  
  public abstract boolean canUseFor(ObjectIdResolver paramObjectIdResolver);
  
  public abstract ObjectIdResolver newForDeserialization(Object paramObject);
  
  public abstract Object resolveId(ObjectIdGenerator.IdKey paramIdKey);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/annotation/ObjectIdResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */