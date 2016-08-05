package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.core.JsonParser.NumberType;

public abstract interface JsonNumberFormatVisitor
  extends JsonValueFormatVisitor
{
  public abstract void numberType(JsonParser.NumberType paramNumberType);
  
  public static class Base
    extends JsonValueFormatVisitor.Base
    implements JsonNumberFormatVisitor
  {
    public void numberType(JsonParser.NumberType paramNumberType) {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsonFormatVisitors/JsonNumberFormatVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */