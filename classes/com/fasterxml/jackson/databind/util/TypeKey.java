package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;

public class TypeKey
{
  protected Class<?> _class;
  protected int _hashCode;
  protected boolean _isTyped;
  protected JavaType _type;
  
  public TypeKey() {}
  
  public TypeKey(JavaType paramJavaType, boolean paramBoolean)
  {
    this._type = paramJavaType;
    this._class = null;
    this._isTyped = paramBoolean;
    if (paramBoolean) {}
    for (int i = typedHash(paramJavaType);; i = untypedHash(paramJavaType))
    {
      this._hashCode = i;
      return;
    }
  }
  
  public TypeKey(TypeKey paramTypeKey)
  {
    this._hashCode = paramTypeKey._hashCode;
    this._class = paramTypeKey._class;
    this._type = paramTypeKey._type;
    this._isTyped = paramTypeKey._isTyped;
  }
  
  public TypeKey(Class<?> paramClass, boolean paramBoolean)
  {
    this._class = paramClass;
    this._type = null;
    this._isTyped = paramBoolean;
    if (paramBoolean) {}
    for (int i = typedHash(paramClass);; i = untypedHash(paramClass))
    {
      this._hashCode = i;
      return;
    }
  }
  
  public static final int typedHash(JavaType paramJavaType)
  {
    return -2 + paramJavaType.hashCode();
  }
  
  public static final int typedHash(Class<?> paramClass)
  {
    return 1 + paramClass.getName().hashCode();
  }
  
  public static final int untypedHash(JavaType paramJavaType)
  {
    return -1 + paramJavaType.hashCode();
  }
  
  public static final int untypedHash(Class<?> paramClass)
  {
    return paramClass.getName().hashCode();
  }
  
  public final boolean equals(Object paramObject)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (paramObject == null) {}
    for (;;)
    {
      return bool2;
      if (paramObject == this)
      {
        bool2 = bool1;
      }
      else if (paramObject.getClass() == getClass())
      {
        TypeKey localTypeKey = (TypeKey)paramObject;
        if (localTypeKey._isTyped == this._isTyped)
        {
          if (this._class != null)
          {
            if (localTypeKey._class == this._class) {}
            for (;;)
            {
              bool2 = bool1;
              break;
              bool1 = false;
            }
          }
          bool2 = this._type.equals(localTypeKey._type);
        }
      }
    }
  }
  
  public Class<?> getRawType()
  {
    return this._class;
  }
  
  public JavaType getType()
  {
    return this._type;
  }
  
  public final int hashCode()
  {
    return this._hashCode;
  }
  
  public boolean isTyped()
  {
    return this._isTyped;
  }
  
  public final void resetTyped(JavaType paramJavaType)
  {
    this._type = paramJavaType;
    this._class = null;
    this._isTyped = true;
    this._hashCode = typedHash(paramJavaType);
  }
  
  public final void resetTyped(Class<?> paramClass)
  {
    this._type = null;
    this._class = paramClass;
    this._isTyped = true;
    this._hashCode = typedHash(paramClass);
  }
  
  public final void resetUntyped(JavaType paramJavaType)
  {
    this._type = paramJavaType;
    this._class = null;
    this._isTyped = false;
    this._hashCode = untypedHash(paramJavaType);
  }
  
  public final void resetUntyped(Class<?> paramClass)
  {
    this._type = null;
    this._class = paramClass;
    this._isTyped = false;
    this._hashCode = untypedHash(paramClass);
  }
  
  public final String toString()
  {
    if (this._class != null) {}
    for (String str = "{class: " + this._class.getName() + ", typed? " + this._isTyped + "}";; str = "{type: " + this._type + ", typed? " + this._isTyped + "}") {
      return str;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/TypeKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */