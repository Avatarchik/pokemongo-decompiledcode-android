package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.cfg.ConfigFeature;

public enum DeserializationFeature
  implements ConfigFeature
{
  private final boolean _defaultState;
  private final int _mask;
  
  static
  {
    USE_JAVA_ARRAY_FOR_JSON_ARRAY = new DeserializationFeature("USE_JAVA_ARRAY_FOR_JSON_ARRAY", 3, false);
    READ_ENUMS_USING_TO_STRING = new DeserializationFeature("READ_ENUMS_USING_TO_STRING", 4, false);
    FAIL_ON_UNKNOWN_PROPERTIES = new DeserializationFeature("FAIL_ON_UNKNOWN_PROPERTIES", 5, true);
    FAIL_ON_NULL_FOR_PRIMITIVES = new DeserializationFeature("FAIL_ON_NULL_FOR_PRIMITIVES", 6, false);
    FAIL_ON_NUMBERS_FOR_ENUMS = new DeserializationFeature("FAIL_ON_NUMBERS_FOR_ENUMS", 7, false);
    FAIL_ON_INVALID_SUBTYPE = new DeserializationFeature("FAIL_ON_INVALID_SUBTYPE", 8, true);
    FAIL_ON_READING_DUP_TREE_KEY = new DeserializationFeature("FAIL_ON_READING_DUP_TREE_KEY", 9, false);
    FAIL_ON_IGNORED_PROPERTIES = new DeserializationFeature("FAIL_ON_IGNORED_PROPERTIES", 10, false);
    FAIL_ON_UNRESOLVED_OBJECT_IDS = new DeserializationFeature("FAIL_ON_UNRESOLVED_OBJECT_IDS", 11, true);
    FAIL_ON_MISSING_CREATOR_PROPERTIES = new DeserializationFeature("FAIL_ON_MISSING_CREATOR_PROPERTIES", 12, false);
    WRAP_EXCEPTIONS = new DeserializationFeature("WRAP_EXCEPTIONS", 13, true);
    ACCEPT_SINGLE_VALUE_AS_ARRAY = new DeserializationFeature("ACCEPT_SINGLE_VALUE_AS_ARRAY", 14, false);
    UNWRAP_SINGLE_VALUE_ARRAYS = new DeserializationFeature("UNWRAP_SINGLE_VALUE_ARRAYS", 15, false);
    UNWRAP_ROOT_VALUE = new DeserializationFeature("UNWRAP_ROOT_VALUE", 16, false);
    ACCEPT_EMPTY_STRING_AS_NULL_OBJECT = new DeserializationFeature("ACCEPT_EMPTY_STRING_AS_NULL_OBJECT", 17, false);
    ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT = new DeserializationFeature("ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT", 18, false);
    ACCEPT_FLOAT_AS_INT = new DeserializationFeature("ACCEPT_FLOAT_AS_INT", 19, true);
    READ_UNKNOWN_ENUM_VALUES_AS_NULL = new DeserializationFeature("READ_UNKNOWN_ENUM_VALUES_AS_NULL", 20, false);
    READ_DATE_TIMESTAMPS_AS_NANOSECONDS = new DeserializationFeature("READ_DATE_TIMESTAMPS_AS_NANOSECONDS", 21, true);
    ADJUST_DATES_TO_CONTEXT_TIME_ZONE = new DeserializationFeature("ADJUST_DATES_TO_CONTEXT_TIME_ZONE", 22, true);
    EAGER_DESERIALIZER_FETCH = new DeserializationFeature("EAGER_DESERIALIZER_FETCH", 23, true);
    DeserializationFeature[] arrayOfDeserializationFeature = new DeserializationFeature[24];
    arrayOfDeserializationFeature[0] = USE_BIG_DECIMAL_FOR_FLOATS;
    arrayOfDeserializationFeature[1] = USE_BIG_INTEGER_FOR_INTS;
    arrayOfDeserializationFeature[2] = USE_LONG_FOR_INTS;
    arrayOfDeserializationFeature[3] = USE_JAVA_ARRAY_FOR_JSON_ARRAY;
    arrayOfDeserializationFeature[4] = READ_ENUMS_USING_TO_STRING;
    arrayOfDeserializationFeature[5] = FAIL_ON_UNKNOWN_PROPERTIES;
    arrayOfDeserializationFeature[6] = FAIL_ON_NULL_FOR_PRIMITIVES;
    arrayOfDeserializationFeature[7] = FAIL_ON_NUMBERS_FOR_ENUMS;
    arrayOfDeserializationFeature[8] = FAIL_ON_INVALID_SUBTYPE;
    arrayOfDeserializationFeature[9] = FAIL_ON_READING_DUP_TREE_KEY;
    arrayOfDeserializationFeature[10] = FAIL_ON_IGNORED_PROPERTIES;
    arrayOfDeserializationFeature[11] = FAIL_ON_UNRESOLVED_OBJECT_IDS;
    arrayOfDeserializationFeature[12] = FAIL_ON_MISSING_CREATOR_PROPERTIES;
    arrayOfDeserializationFeature[13] = WRAP_EXCEPTIONS;
    arrayOfDeserializationFeature[14] = ACCEPT_SINGLE_VALUE_AS_ARRAY;
    arrayOfDeserializationFeature[15] = UNWRAP_SINGLE_VALUE_ARRAYS;
    arrayOfDeserializationFeature[16] = UNWRAP_ROOT_VALUE;
    arrayOfDeserializationFeature[17] = ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
    arrayOfDeserializationFeature[18] = ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT;
    arrayOfDeserializationFeature[19] = ACCEPT_FLOAT_AS_INT;
    arrayOfDeserializationFeature[20] = READ_UNKNOWN_ENUM_VALUES_AS_NULL;
    arrayOfDeserializationFeature[21] = READ_DATE_TIMESTAMPS_AS_NANOSECONDS;
    arrayOfDeserializationFeature[22] = ADJUST_DATES_TO_CONTEXT_TIME_ZONE;
    arrayOfDeserializationFeature[23] = EAGER_DESERIALIZER_FETCH;
    $VALUES = arrayOfDeserializationFeature;
  }
  
  private DeserializationFeature(boolean paramBoolean)
  {
    this._defaultState = paramBoolean;
    this._mask = (1 << ordinal());
  }
  
  public boolean enabledByDefault()
  {
    return this._defaultState;
  }
  
  public boolean enabledIn(int paramInt)
  {
    if ((paramInt & this._mask) != 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int getMask()
  {
    return this._mask;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/DeserializationFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */