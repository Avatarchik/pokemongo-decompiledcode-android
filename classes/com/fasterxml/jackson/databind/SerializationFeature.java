package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.cfg.ConfigFeature;

public enum SerializationFeature
  implements ConfigFeature
{
  private final boolean _defaultState;
  private final int _mask;
  
  static
  {
    INDENT_OUTPUT = new SerializationFeature("INDENT_OUTPUT", 1, false);
    FAIL_ON_EMPTY_BEANS = new SerializationFeature("FAIL_ON_EMPTY_BEANS", 2, true);
    FAIL_ON_SELF_REFERENCES = new SerializationFeature("FAIL_ON_SELF_REFERENCES", 3, true);
    WRAP_EXCEPTIONS = new SerializationFeature("WRAP_EXCEPTIONS", 4, true);
    FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS = new SerializationFeature("FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS", 5, true);
    CLOSE_CLOSEABLE = new SerializationFeature("CLOSE_CLOSEABLE", 6, false);
    FLUSH_AFTER_WRITE_VALUE = new SerializationFeature("FLUSH_AFTER_WRITE_VALUE", 7, true);
    WRITE_DATES_AS_TIMESTAMPS = new SerializationFeature("WRITE_DATES_AS_TIMESTAMPS", 8, true);
    WRITE_DATE_KEYS_AS_TIMESTAMPS = new SerializationFeature("WRITE_DATE_KEYS_AS_TIMESTAMPS", 9, false);
    WRITE_DATES_WITH_ZONE_ID = new SerializationFeature("WRITE_DATES_WITH_ZONE_ID", 10, false);
    WRITE_DURATIONS_AS_TIMESTAMPS = new SerializationFeature("WRITE_DURATIONS_AS_TIMESTAMPS", 11, true);
    WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS = new SerializationFeature("WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS", 12, false);
    WRITE_ENUMS_USING_TO_STRING = new SerializationFeature("WRITE_ENUMS_USING_TO_STRING", 13, false);
    WRITE_ENUMS_USING_INDEX = new SerializationFeature("WRITE_ENUMS_USING_INDEX", 14, false);
    WRITE_NULL_MAP_VALUES = new SerializationFeature("WRITE_NULL_MAP_VALUES", 15, true);
    WRITE_EMPTY_JSON_ARRAYS = new SerializationFeature("WRITE_EMPTY_JSON_ARRAYS", 16, true);
    WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED = new SerializationFeature("WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED", 17, false);
    WRITE_BIGDECIMAL_AS_PLAIN = new SerializationFeature("WRITE_BIGDECIMAL_AS_PLAIN", 18, false);
    WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS = new SerializationFeature("WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS", 19, true);
    ORDER_MAP_ENTRIES_BY_KEYS = new SerializationFeature("ORDER_MAP_ENTRIES_BY_KEYS", 20, false);
    EAGER_SERIALIZER_FETCH = new SerializationFeature("EAGER_SERIALIZER_FETCH", 21, true);
    USE_EQUALITY_FOR_OBJECT_ID = new SerializationFeature("USE_EQUALITY_FOR_OBJECT_ID", 22, false);
    SerializationFeature[] arrayOfSerializationFeature = new SerializationFeature[23];
    arrayOfSerializationFeature[0] = WRAP_ROOT_VALUE;
    arrayOfSerializationFeature[1] = INDENT_OUTPUT;
    arrayOfSerializationFeature[2] = FAIL_ON_EMPTY_BEANS;
    arrayOfSerializationFeature[3] = FAIL_ON_SELF_REFERENCES;
    arrayOfSerializationFeature[4] = WRAP_EXCEPTIONS;
    arrayOfSerializationFeature[5] = FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS;
    arrayOfSerializationFeature[6] = CLOSE_CLOSEABLE;
    arrayOfSerializationFeature[7] = FLUSH_AFTER_WRITE_VALUE;
    arrayOfSerializationFeature[8] = WRITE_DATES_AS_TIMESTAMPS;
    arrayOfSerializationFeature[9] = WRITE_DATE_KEYS_AS_TIMESTAMPS;
    arrayOfSerializationFeature[10] = WRITE_DATES_WITH_ZONE_ID;
    arrayOfSerializationFeature[11] = WRITE_DURATIONS_AS_TIMESTAMPS;
    arrayOfSerializationFeature[12] = WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS;
    arrayOfSerializationFeature[13] = WRITE_ENUMS_USING_TO_STRING;
    arrayOfSerializationFeature[14] = WRITE_ENUMS_USING_INDEX;
    arrayOfSerializationFeature[15] = WRITE_NULL_MAP_VALUES;
    arrayOfSerializationFeature[16] = WRITE_EMPTY_JSON_ARRAYS;
    arrayOfSerializationFeature[17] = WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED;
    arrayOfSerializationFeature[18] = WRITE_BIGDECIMAL_AS_PLAIN;
    arrayOfSerializationFeature[19] = WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS;
    arrayOfSerializationFeature[20] = ORDER_MAP_ENTRIES_BY_KEYS;
    arrayOfSerializationFeature[21] = EAGER_SERIALIZER_FETCH;
    arrayOfSerializationFeature[22] = USE_EQUALITY_FOR_OBJECT_ID;
    $VALUES = arrayOfSerializationFeature;
  }
  
  private SerializationFeature(boolean paramBoolean)
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/SerializationFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */