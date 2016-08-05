package com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class ContextAttributes
{
  public static ContextAttributes getEmpty()
  {
    return Impl.getEmpty();
  }
  
  public abstract Object getAttribute(Object paramObject);
  
  public abstract ContextAttributes withPerCallAttribute(Object paramObject1, Object paramObject2);
  
  public abstract ContextAttributes withSharedAttribute(Object paramObject1, Object paramObject2);
  
  public abstract ContextAttributes withSharedAttributes(Map<Object, Object> paramMap);
  
  public abstract ContextAttributes withoutSharedAttribute(Object paramObject);
  
  public static class Impl
    extends ContextAttributes
    implements Serializable
  {
    protected static final Impl EMPTY = new Impl(Collections.emptyMap());
    protected static final Object NULL_SURROGATE = new Object();
    private static final long serialVersionUID = 1L;
    protected transient Map<Object, Object> _nonShared;
    protected final Map<Object, Object> _shared;
    
    protected Impl(Map<Object, Object> paramMap)
    {
      this._shared = paramMap;
      this._nonShared = null;
    }
    
    protected Impl(Map<Object, Object> paramMap1, Map<Object, Object> paramMap2)
    {
      this._shared = paramMap1;
      this._nonShared = paramMap2;
    }
    
    private Map<Object, Object> _copy(Map<Object, Object> paramMap)
    {
      return new HashMap(paramMap);
    }
    
    public static ContextAttributes getEmpty()
    {
      return EMPTY;
    }
    
    public Object getAttribute(Object paramObject)
    {
      if (this._nonShared != null)
      {
        localObject = this._nonShared.get(paramObject);
        if (localObject != null) {
          if (localObject != NULL_SURROGATE) {}
        }
      }
      for (Object localObject = null;; localObject = this._shared.get(paramObject)) {
        return localObject;
      }
    }
    
    protected ContextAttributes nonSharedInstance(Object paramObject1, Object paramObject2)
    {
      HashMap localHashMap = new HashMap();
      if (paramObject2 == null) {
        paramObject2 = NULL_SURROGATE;
      }
      localHashMap.put(paramObject1, paramObject2);
      return new Impl(this._shared, localHashMap);
    }
    
    public ContextAttributes withPerCallAttribute(Object paramObject1, Object paramObject2)
    {
      if (paramObject2 == null)
      {
        if (this._shared.containsKey(paramObject1)) {
          paramObject2 = NULL_SURROGATE;
        }
      }
      else
      {
        if (this._nonShared != null) {
          break label37;
        }
        this = nonSharedInstance(paramObject1, paramObject2);
      }
      for (;;)
      {
        return this;
        label37:
        this._nonShared.put(paramObject1, paramObject2);
      }
    }
    
    public ContextAttributes withSharedAttribute(Object paramObject1, Object paramObject2)
    {
      if (this == EMPTY) {}
      for (Object localObject = new HashMap(8);; localObject = _copy(this._shared))
      {
        ((Map)localObject).put(paramObject1, paramObject2);
        return new Impl((Map)localObject);
      }
    }
    
    public ContextAttributes withSharedAttributes(Map<Object, Object> paramMap)
    {
      return new Impl(paramMap);
    }
    
    public ContextAttributes withoutSharedAttribute(Object paramObject)
    {
      if (this._shared.isEmpty()) {}
      for (;;)
      {
        return this;
        if (this._shared.containsKey(paramObject)) {
          if (this._shared.size() == 1)
          {
            this = EMPTY;
          }
          else
          {
            Map localMap = _copy(this._shared);
            localMap.remove(paramObject);
            this = new Impl(localMap);
          }
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/cfg/ContextAttributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */