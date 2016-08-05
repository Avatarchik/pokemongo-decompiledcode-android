package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public class RawValue
  implements JsonSerializable
{
  protected Object _value;
  
  public RawValue(SerializableString paramSerializableString)
  {
    this._value = paramSerializableString;
  }
  
  public RawValue(JsonSerializable paramJsonSerializable)
  {
    this._value = paramJsonSerializable;
  }
  
  protected RawValue(Object paramObject, boolean paramBoolean)
  {
    this._value = paramObject;
  }
  
  public RawValue(String paramString)
  {
    this._value = paramString;
  }
  
  protected void _serialize(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if ((this._value instanceof SerializableString)) {
      paramJsonGenerator.writeRawValue((SerializableString)this._value);
    }
    for (;;)
    {
      return;
      paramJsonGenerator.writeRawValue(String.valueOf(this._value));
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof RawValue))
      {
        bool = false;
      }
      else
      {
        RawValue localRawValue = (RawValue)paramObject;
        if ((this._value != localRawValue._value) && ((this._value == null) || (!this._value.equals(localRawValue._value)))) {
          bool = false;
        }
      }
    }
  }
  
  public int hashCode()
  {
    if (this._value == null) {}
    for (int i = 0;; i = this._value.hashCode()) {
      return i;
    }
  }
  
  public Object rawValue()
  {
    return this._value;
  }
  
  public void serialize(JsonGenerator paramJsonGenerator)
    throws IOException
  {
    if ((this._value instanceof JsonSerializable)) {
      paramJsonGenerator.writeObject(this._value);
    }
    for (;;)
    {
      return;
      _serialize(paramJsonGenerator);
    }
  }
  
  public void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
    throws IOException
  {
    if ((this._value instanceof JsonSerializable)) {
      ((JsonSerializable)this._value).serialize(paramJsonGenerator, paramSerializerProvider);
    }
    for (;;)
    {
      return;
      _serialize(paramJsonGenerator);
    }
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer)
    throws IOException
  {
    if ((this._value instanceof JsonSerializable)) {
      ((JsonSerializable)this._value).serializeWithType(paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
    }
    for (;;)
    {
      return;
      if ((this._value instanceof SerializableString)) {
        serialize(paramJsonGenerator, paramSerializerProvider);
      }
    }
  }
  
  public String toString()
  {
    Object[] arrayOfObject = new Object[1];
    if (this._value == null) {}
    for (String str = "NULL";; str = this._value.getClass().getName())
    {
      arrayOfObject[0] = str;
      return String.format("[RawValue of type %s]", arrayOfObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/RawValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */