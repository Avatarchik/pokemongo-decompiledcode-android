package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class NoClassDefFoundDeserializer<T>
  extends JsonDeserializer<T>
{
  private final NoClassDefFoundError _cause;
  
  public NoClassDefFoundDeserializer(NoClassDefFoundError paramNoClassDefFoundError)
  {
    this._cause = paramNoClassDefFoundError;
  }
  
  public T deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext)
    throws IOException
  {
    throw this._cause;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/deser/impl/NoClassDefFoundDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */