package com.fasterxml.jackson.databind.jsontype;

import java.io.Serializable;

public final class NamedType
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  protected final Class<?> _class;
  protected final int _hashCode;
  protected String _name;
  
  public NamedType(Class<?> paramClass)
  {
    this(paramClass, null);
  }
  
  public NamedType(Class<?> paramClass, String paramString)
  {
    this._class = paramClass;
    this._hashCode = paramClass.getName().hashCode();
    setName(paramString);
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
      } else if (this._class != ((NamedType)paramObject)._class) {
        bool = false;
      }
    }
  }
  
  public String getName()
  {
    return this._name;
  }
  
  public Class<?> getType()
  {
    return this._class;
  }
  
  public boolean hasName()
  {
    if (this._name != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    return this._hashCode;
  }
  
  public void setName(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      paramString = null;
    }
    this._name = paramString;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("[NamedType, class ").append(this._class.getName()).append(", name: ");
    if (this._name == null) {}
    for (String str = "null";; str = "'" + this._name + "'") {
      return str + "]";
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsontype/NamedType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */