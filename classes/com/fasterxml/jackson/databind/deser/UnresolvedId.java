package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonLocation;

public class UnresolvedId
{
  private final Object _id;
  private final JsonLocation _location;
  private final Class<?> _type;
  
  public UnresolvedId(Object paramObject, Class<?> paramClass, JsonLocation paramJsonLocation)
  {
    this._id = paramObject;
    this._type = paramClass;
    this._location = paramJsonLocation;
  }
  
  public Object getId()
  {
    return this._id;
  }
  
  public JsonLocation getLocation()
  {
    return this._location;
  }
  
  public Class<?> getType()
  {
    return this._type;
  }
  
  public String toString()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this._id;
    if (this._type == null) {}
    for (String str = "NULL";; str = this._type.getName())
    {
      arrayOfObject[1] = str;
      arrayOfObject[2] = this._location;
      return String.format("Object id [%s] (for %s) at %s", arrayOfObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/UnresolvedId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */