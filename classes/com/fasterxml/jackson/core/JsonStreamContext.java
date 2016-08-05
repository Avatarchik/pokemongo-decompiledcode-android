package com.fasterxml.jackson.core;

public abstract class JsonStreamContext
{
  protected static final int TYPE_ARRAY = 1;
  protected static final int TYPE_OBJECT = 2;
  protected static final int TYPE_ROOT;
  protected int _index;
  protected int _type;
  
  public final int getCurrentIndex()
  {
    if (this._index < 0) {}
    for (int i = 0;; i = this._index) {
      return i;
    }
  }
  
  public abstract String getCurrentName();
  
  public Object getCurrentValue()
  {
    return null;
  }
  
  public final int getEntryCount()
  {
    return 1 + this._index;
  }
  
  public abstract JsonStreamContext getParent();
  
  public final String getTypeDesc()
  {
    String str;
    switch (this._type)
    {
    default: 
      str = "?";
    }
    for (;;)
    {
      return str;
      str = "ROOT";
      continue;
      str = "ARRAY";
      continue;
      str = "OBJECT";
    }
  }
  
  public final boolean inArray()
  {
    int i = 1;
    if (this._type == i) {}
    for (;;)
    {
      return i;
      int j = 0;
    }
  }
  
  public final boolean inObject()
  {
    if (this._type == 2) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean inRoot()
  {
    if (this._type == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void setCurrentValue(Object paramObject) {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/JsonStreamContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */