package com.fasterxml.jackson.annotation;

import java.util.HashMap;
import java.util.Map;

public class SimpleObjectIdResolver
  implements ObjectIdResolver
{
  protected Map<ObjectIdGenerator.IdKey, Object> _items;
  
  public void bindItem(ObjectIdGenerator.IdKey paramIdKey, Object paramObject)
  {
    if (this._items == null) {
      this._items = new HashMap();
    }
    while (!this._items.containsKey(paramIdKey))
    {
      this._items.put(paramIdKey, paramObject);
      return;
    }
    throw new IllegalStateException("Already had POJO for id (" + paramIdKey.key.getClass().getName() + ") [" + paramIdKey + "]");
  }
  
  public boolean canUseFor(ObjectIdResolver paramObjectIdResolver)
  {
    if (paramObjectIdResolver.getClass() == getClass()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public ObjectIdResolver newForDeserialization(Object paramObject)
  {
    return new SimpleObjectIdResolver();
  }
  
  public Object resolveId(ObjectIdGenerator.IdKey paramIdKey)
  {
    if (this._items == null) {}
    for (Object localObject = null;; localObject = this._items.get(paramIdKey)) {
      return localObject;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/annotation/SimpleObjectIdResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */