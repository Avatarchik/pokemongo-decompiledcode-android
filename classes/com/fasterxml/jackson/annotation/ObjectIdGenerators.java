package com.fasterxml.jackson.annotation;

import java.util.UUID;

public class ObjectIdGenerators
{
  public static final class UUIDGenerator
    extends ObjectIdGenerators.Base<UUID>
  {
    private static final long serialVersionUID = 1L;
    
    public UUIDGenerator()
    {
      this(Object.class);
    }
    
    private UUIDGenerator(Class<?> paramClass)
    {
      super();
    }
    
    public boolean canUseFor(ObjectIdGenerator<?> paramObjectIdGenerator)
    {
      if (paramObjectIdGenerator.getClass() == getClass()) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public ObjectIdGenerator<UUID> forScope(Class<?> paramClass)
    {
      return this;
    }
    
    public UUID generateId(Object paramObject)
    {
      return UUID.randomUUID();
    }
    
    public ObjectIdGenerator.IdKey key(Object paramObject)
    {
      ObjectIdGenerator.IdKey localIdKey = null;
      if (paramObject == null) {}
      for (;;)
      {
        return localIdKey;
        localIdKey = new ObjectIdGenerator.IdKey(getClass(), null, paramObject);
      }
    }
    
    public ObjectIdGenerator<UUID> newForSerialization(Object paramObject)
    {
      return this;
    }
  }
  
  public static final class IntSequenceGenerator
    extends ObjectIdGenerators.Base<Integer>
  {
    private static final long serialVersionUID = 1L;
    protected transient int _nextValue;
    
    public IntSequenceGenerator()
    {
      this(Object.class, -1);
    }
    
    public IntSequenceGenerator(Class<?> paramClass, int paramInt)
    {
      super();
      this._nextValue = paramInt;
    }
    
    public ObjectIdGenerator<Integer> forScope(Class<?> paramClass)
    {
      if (this._scope == paramClass) {}
      for (;;)
      {
        return this;
        this = new IntSequenceGenerator(paramClass, this._nextValue);
      }
    }
    
    public Integer generateId(Object paramObject)
    {
      if (paramObject == null) {}
      int i;
      for (Integer localInteger = null;; localInteger = Integer.valueOf(i))
      {
        return localInteger;
        i = this._nextValue;
        this._nextValue = (1 + this._nextValue);
      }
    }
    
    protected int initialValue()
    {
      return 1;
    }
    
    public ObjectIdGenerator.IdKey key(Object paramObject)
    {
      if (paramObject == null) {}
      for (ObjectIdGenerator.IdKey localIdKey = null;; localIdKey = new ObjectIdGenerator.IdKey(getClass(), this._scope, paramObject)) {
        return localIdKey;
      }
    }
    
    public ObjectIdGenerator<Integer> newForSerialization(Object paramObject)
    {
      return new IntSequenceGenerator(this._scope, initialValue());
    }
  }
  
  public static abstract class PropertyGenerator
    extends ObjectIdGenerators.Base<Object>
  {
    private static final long serialVersionUID = 1L;
    
    protected PropertyGenerator(Class<?> paramClass)
    {
      super();
    }
  }
  
  public static abstract class None
    extends ObjectIdGenerator<Object>
  {}
  
  private static abstract class Base<T>
    extends ObjectIdGenerator<T>
  {
    protected final Class<?> _scope;
    
    protected Base(Class<?> paramClass)
    {
      this._scope = paramClass;
    }
    
    public boolean canUseFor(ObjectIdGenerator<?> paramObjectIdGenerator)
    {
      if ((paramObjectIdGenerator.getClass() == getClass()) && (paramObjectIdGenerator.getScope() == this._scope)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public abstract T generateId(Object paramObject);
    
    public final Class<?> getScope()
    {
      return this._scope;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/annotation/ObjectIdGenerators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */