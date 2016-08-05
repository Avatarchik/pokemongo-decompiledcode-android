package com.fasterxml.jackson.annotation;

import java.io.Serializable;

public abstract class ObjectIdGenerator<T>
  implements Serializable
{
  public abstract boolean canUseFor(ObjectIdGenerator<?> paramObjectIdGenerator);
  
  public abstract ObjectIdGenerator<T> forScope(Class<?> paramClass);
  
  public abstract T generateId(Object paramObject);
  
  public abstract Class<?> getScope();
  
  public boolean isValidReferencePropertyName(String paramString, Object paramObject)
  {
    return false;
  }
  
  public abstract IdKey key(Object paramObject);
  
  public boolean maySerializeAsObject()
  {
    return false;
  }
  
  public abstract ObjectIdGenerator<T> newForSerialization(Object paramObject);
  
  public static final class IdKey
    implements Serializable
  {
    private static final long serialVersionUID = 1L;
    private final int hashCode;
    public final Object key;
    public final Class<?> scope;
    public final Class<?> type;
    
    public IdKey(Class<?> paramClass1, Class<?> paramClass2, Object paramObject)
    {
      if (paramObject == null) {
        throw new IllegalArgumentException("Can not construct IdKey for null key");
      }
      this.type = paramClass1;
      this.scope = paramClass2;
      this.key = paramObject;
      int i = paramObject.hashCode() + paramClass1.getName().hashCode();
      if (paramClass2 != null) {
        i ^= paramClass2.getName().hashCode();
      }
      this.hashCode = i;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (paramObject == this) {}
      for (;;)
      {
        return bool;
        if (paramObject == null)
        {
          bool = false;
        }
        else if (paramObject.getClass() != getClass())
        {
          bool = false;
        }
        else
        {
          IdKey localIdKey = (IdKey)paramObject;
          if ((!localIdKey.key.equals(this.key)) || (localIdKey.type != this.type) || (localIdKey.scope != this.scope)) {
            bool = false;
          }
        }
      }
    }
    
    public int hashCode()
    {
      return this.hashCode;
    }
    
    public String toString()
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = this.key;
      String str1;
      if (this.type == null)
      {
        str1 = "NONE";
        arrayOfObject[1] = str1;
        if (this.scope != null) {
          break label58;
        }
      }
      label58:
      for (String str2 = "NONE";; str2 = this.scope.getName())
      {
        arrayOfObject[2] = str2;
        return String.format("[ObjectId: key=%s, type=%s, scope=%s]", arrayOfObject);
        str1 = this.type.getName();
        break;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/annotation/ObjectIdGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */