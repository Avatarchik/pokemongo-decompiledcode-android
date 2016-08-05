package com.fasterxml.jackson.databind.jsonFormatVisitors;

public abstract interface JsonStringFormatVisitor
  extends JsonValueFormatVisitor
{
  public static class Base
    extends JsonValueFormatVisitor.Base
    implements JsonStringFormatVisitor
  {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsonFormatVisitors/JsonStringFormatVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */