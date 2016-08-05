package com.fasterxml.jackson.databind.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class HierarchicType
{
  protected final Type _actualType;
  protected final ParameterizedType _genericType;
  protected final Class<?> _rawClass;
  protected HierarchicType _subType;
  protected HierarchicType _superType;
  
  public HierarchicType(Type paramType)
  {
    this._actualType = paramType;
    if ((paramType instanceof Class))
    {
      this._rawClass = ((Class)paramType);
      this._genericType = null;
    }
    for (;;)
    {
      return;
      if (!(paramType instanceof ParameterizedType)) {
        break;
      }
      this._genericType = ((ParameterizedType)paramType);
      this._rawClass = ((Class)this._genericType.getRawType());
    }
    throw new IllegalArgumentException("Type " + paramType.getClass().getName() + " can not be used to construct HierarchicType");
  }
  
  private HierarchicType(Type paramType, Class<?> paramClass, ParameterizedType paramParameterizedType, HierarchicType paramHierarchicType1, HierarchicType paramHierarchicType2)
  {
    this._actualType = paramType;
    this._rawClass = paramClass;
    this._genericType = paramParameterizedType;
    this._superType = paramHierarchicType1;
    this._subType = paramHierarchicType2;
  }
  
  public final ParameterizedType asGeneric()
  {
    return this._genericType;
  }
  
  public HierarchicType deepCloneWithoutSubtype()
  {
    if (this._superType == null) {}
    for (HierarchicType localHierarchicType1 = null;; localHierarchicType1 = this._superType.deepCloneWithoutSubtype())
    {
      HierarchicType localHierarchicType2 = new HierarchicType(this._actualType, this._rawClass, this._genericType, localHierarchicType1, null);
      if (localHierarchicType1 != null) {
        localHierarchicType1.setSubType(localHierarchicType2);
      }
      return localHierarchicType2;
    }
  }
  
  public final Class<?> getRawClass()
  {
    return this._rawClass;
  }
  
  public final HierarchicType getSubType()
  {
    return this._subType;
  }
  
  public final HierarchicType getSuperType()
  {
    return this._superType;
  }
  
  public final boolean isGeneric()
  {
    if (this._genericType != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void setSubType(HierarchicType paramHierarchicType)
  {
    this._subType = paramHierarchicType;
  }
  
  public void setSuperType(HierarchicType paramHierarchicType)
  {
    this._superType = paramHierarchicType;
  }
  
  public String toString()
  {
    if (this._genericType != null) {}
    for (String str = this._genericType.toString();; str = this._rawClass.getName()) {
      return str;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/type/HierarchicType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */