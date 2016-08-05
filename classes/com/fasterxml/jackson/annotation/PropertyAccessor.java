package com.fasterxml.jackson.annotation;

public enum PropertyAccessor
{
  static
  {
    CREATOR = new PropertyAccessor("CREATOR", 2);
    FIELD = new PropertyAccessor("FIELD", 3);
    IS_GETTER = new PropertyAccessor("IS_GETTER", 4);
    NONE = new PropertyAccessor("NONE", 5);
    ALL = new PropertyAccessor("ALL", 6);
    PropertyAccessor[] arrayOfPropertyAccessor = new PropertyAccessor[7];
    arrayOfPropertyAccessor[0] = GETTER;
    arrayOfPropertyAccessor[1] = SETTER;
    arrayOfPropertyAccessor[2] = CREATOR;
    arrayOfPropertyAccessor[3] = FIELD;
    arrayOfPropertyAccessor[4] = IS_GETTER;
    arrayOfPropertyAccessor[5] = NONE;
    arrayOfPropertyAccessor[6] = ALL;
    $VALUES = arrayOfPropertyAccessor;
  }
  
  private PropertyAccessor() {}
  
  public boolean creatorEnabled()
  {
    if ((this == CREATOR) || (this == ALL)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean fieldEnabled()
  {
    if ((this == FIELD) || (this == ALL)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean getterEnabled()
  {
    if ((this == GETTER) || (this == ALL)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isGetterEnabled()
  {
    if ((this == IS_GETTER) || (this == ALL)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean setterEnabled()
  {
    if ((this == SETTER) || (this == ALL)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/annotation/PropertyAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */