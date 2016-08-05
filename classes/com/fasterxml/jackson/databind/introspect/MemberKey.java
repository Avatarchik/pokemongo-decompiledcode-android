package com.fasterxml.jackson.databind.introspect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class MemberKey
{
  static final Class<?>[] NO_CLASSES = new Class[0];
  final Class<?>[] _argTypes;
  final String _name;
  
  public MemberKey(String paramString, Class<?>[] paramArrayOfClass)
  {
    this._name = paramString;
    if (paramArrayOfClass == null) {
      paramArrayOfClass = NO_CLASSES;
    }
    this._argTypes = paramArrayOfClass;
  }
  
  public MemberKey(Constructor<?> paramConstructor)
  {
    this("", paramConstructor.getParameterTypes());
  }
  
  public MemberKey(Method paramMethod)
  {
    this(paramMethod.getName(), paramMethod.getParameterTypes());
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
        MemberKey localMemberKey = (MemberKey)paramObject;
        if (!this._name.equals(localMemberKey._name))
        {
          bool = false;
        }
        else
        {
          Class[] arrayOfClass = localMemberKey._argTypes;
          int i = this._argTypes.length;
          if (arrayOfClass.length != i)
          {
            bool = false;
          }
          else
          {
            for (int j = 0; j < i; j++) {
              if (arrayOfClass[j] != this._argTypes[j]) {
                break label115;
              }
            }
            continue;
            label115:
            bool = false;
          }
        }
      }
    }
  }
  
  public int hashCode()
  {
    return this._name.hashCode() + this._argTypes.length;
  }
  
  public String toString()
  {
    return this._name + "(" + this._argTypes.length + "-args)";
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/introspect/MemberKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */