package com.fasterxml.jackson.databind.type;

import java.io.Serializable;

public final class ClassKey
  implements Comparable<ClassKey>, Serializable
{
  private static final long serialVersionUID = 1L;
  private Class<?> _class;
  private String _className;
  private int _hashCode;
  
  public ClassKey()
  {
    this._class = null;
    this._className = null;
    this._hashCode = 0;
  }
  
  public ClassKey(Class<?> paramClass)
  {
    this._class = paramClass;
    this._className = paramClass.getName();
    this._hashCode = this._className.hashCode();
  }
  
  public int compareTo(ClassKey paramClassKey)
  {
    return this._className.compareTo(paramClassKey._className);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (paramObject == null) {
        bool = false;
      } else if (paramObject.getClass() != getClass()) {
        bool = false;
      } else if (((ClassKey)paramObject)._class != this._class) {
        bool = false;
      }
    }
  }
  
  public int hashCode()
  {
    return this._hashCode;
  }
  
  public void reset(Class<?> paramClass)
  {
    this._class = paramClass;
    this._className = paramClass.getName();
    this._hashCode = this._className.hashCode();
  }
  
  public String toString()
  {
    return this._className;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/type/ClassKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */