package com.fasterxml.jackson.databind.node;

public enum JsonNodeType
{
  static
  {
    JsonNodeType[] arrayOfJsonNodeType = new JsonNodeType[9];
    arrayOfJsonNodeType[0] = ARRAY;
    arrayOfJsonNodeType[1] = BINARY;
    arrayOfJsonNodeType[2] = BOOLEAN;
    arrayOfJsonNodeType[3] = MISSING;
    arrayOfJsonNodeType[4] = NULL;
    arrayOfJsonNodeType[5] = NUMBER;
    arrayOfJsonNodeType[6] = OBJECT;
    arrayOfJsonNodeType[7] = POJO;
    arrayOfJsonNodeType[8] = STRING;
    $VALUES = arrayOfJsonNodeType;
  }
  
  private JsonNodeType() {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/node/JsonNodeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */