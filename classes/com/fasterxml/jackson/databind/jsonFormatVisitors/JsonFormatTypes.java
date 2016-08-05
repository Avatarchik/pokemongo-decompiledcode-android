package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.HashMap;
import java.util.Map;

public enum JsonFormatTypes
{
  private static final Map<String, JsonFormatTypes> _byLCName;
  
  static
  {
    NUMBER = new JsonFormatTypes("NUMBER", 1);
    INTEGER = new JsonFormatTypes("INTEGER", 2);
    BOOLEAN = new JsonFormatTypes("BOOLEAN", 3);
    OBJECT = new JsonFormatTypes("OBJECT", 4);
    ARRAY = new JsonFormatTypes("ARRAY", 5);
    NULL = new JsonFormatTypes("NULL", 6);
    ANY = new JsonFormatTypes("ANY", 7);
    JsonFormatTypes[] arrayOfJsonFormatTypes1 = new JsonFormatTypes[8];
    arrayOfJsonFormatTypes1[0] = STRING;
    arrayOfJsonFormatTypes1[1] = NUMBER;
    arrayOfJsonFormatTypes1[2] = INTEGER;
    arrayOfJsonFormatTypes1[3] = BOOLEAN;
    arrayOfJsonFormatTypes1[4] = OBJECT;
    arrayOfJsonFormatTypes1[5] = ARRAY;
    arrayOfJsonFormatTypes1[6] = NULL;
    arrayOfJsonFormatTypes1[7] = ANY;
    $VALUES = arrayOfJsonFormatTypes1;
    _byLCName = new HashMap();
    for (JsonFormatTypes localJsonFormatTypes : values()) {
      _byLCName.put(localJsonFormatTypes.name().toLowerCase(), localJsonFormatTypes);
    }
  }
  
  private JsonFormatTypes() {}
  
  @JsonCreator
  public static JsonFormatTypes forValue(String paramString)
  {
    return (JsonFormatTypes)_byLCName.get(paramString);
  }
  
  @JsonValue
  public String value()
  {
    return name().toLowerCase();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/jsonFormatVisitors/JsonFormatTypes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */