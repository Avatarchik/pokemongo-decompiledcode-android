package com.fasterxml.jackson.annotation;

public enum OptBoolean
{
  static
  {
    FALSE = new OptBoolean("FALSE", 1);
    DEFAULT = new OptBoolean("DEFAULT", 2);
    OptBoolean[] arrayOfOptBoolean = new OptBoolean[3];
    arrayOfOptBoolean[0] = TRUE;
    arrayOfOptBoolean[1] = FALSE;
    arrayOfOptBoolean[2] = DEFAULT;
    $VALUES = arrayOfOptBoolean;
  }
  
  private OptBoolean() {}
  
  public static OptBoolean fromBoolean(Boolean paramBoolean)
  {
    OptBoolean localOptBoolean;
    if (paramBoolean == null) {
      localOptBoolean = DEFAULT;
    }
    for (;;)
    {
      return localOptBoolean;
      if (paramBoolean.booleanValue()) {
        localOptBoolean = TRUE;
      } else {
        localOptBoolean = FALSE;
      }
    }
  }
  
  public Boolean asBoolean()
  {
    Boolean localBoolean;
    if (this == DEFAULT) {
      localBoolean = null;
    }
    for (;;)
    {
      return localBoolean;
      if (this == TRUE) {
        localBoolean = Boolean.TRUE;
      } else {
        localBoolean = Boolean.FALSE;
      }
    }
  }
  
  public boolean asPrimitive()
  {
    if (this == TRUE) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/annotation/OptBoolean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */