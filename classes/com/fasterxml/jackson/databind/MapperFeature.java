package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.cfg.ConfigFeature;

public enum MapperFeature
  implements ConfigFeature
{
  private final boolean _defaultState;
  private final int _mask;
  
  static
  {
    AUTO_DETECT_CREATORS = new MapperFeature("AUTO_DETECT_CREATORS", 1, true);
    AUTO_DETECT_FIELDS = new MapperFeature("AUTO_DETECT_FIELDS", 2, true);
    AUTO_DETECT_GETTERS = new MapperFeature("AUTO_DETECT_GETTERS", 3, true);
    AUTO_DETECT_IS_GETTERS = new MapperFeature("AUTO_DETECT_IS_GETTERS", 4, true);
    AUTO_DETECT_SETTERS = new MapperFeature("AUTO_DETECT_SETTERS", 5, true);
    REQUIRE_SETTERS_FOR_GETTERS = new MapperFeature("REQUIRE_SETTERS_FOR_GETTERS", 6, false);
    USE_GETTERS_AS_SETTERS = new MapperFeature("USE_GETTERS_AS_SETTERS", 7, true);
    CAN_OVERRIDE_ACCESS_MODIFIERS = new MapperFeature("CAN_OVERRIDE_ACCESS_MODIFIERS", 8, true);
    INFER_PROPERTY_MUTATORS = new MapperFeature("INFER_PROPERTY_MUTATORS", 9, true);
    ALLOW_FINAL_FIELDS_AS_MUTATORS = new MapperFeature("ALLOW_FINAL_FIELDS_AS_MUTATORS", 10, true);
    PROPAGATE_TRANSIENT_MARKER = new MapperFeature("PROPAGATE_TRANSIENT_MARKER", 11, false);
    USE_STATIC_TYPING = new MapperFeature("USE_STATIC_TYPING", 12, false);
    DEFAULT_VIEW_INCLUSION = new MapperFeature("DEFAULT_VIEW_INCLUSION", 13, true);
    SORT_PROPERTIES_ALPHABETICALLY = new MapperFeature("SORT_PROPERTIES_ALPHABETICALLY", 14, false);
    ACCEPT_CASE_INSENSITIVE_PROPERTIES = new MapperFeature("ACCEPT_CASE_INSENSITIVE_PROPERTIES", 15, false);
    USE_WRAPPER_NAME_AS_PROPERTY_NAME = new MapperFeature("USE_WRAPPER_NAME_AS_PROPERTY_NAME", 16, false);
    USE_STD_BEAN_NAMING = new MapperFeature("USE_STD_BEAN_NAMING", 17, false);
    IGNORE_DUPLICATE_MODULE_REGISTRATIONS = new MapperFeature("IGNORE_DUPLICATE_MODULE_REGISTRATIONS", 18, true);
    MapperFeature[] arrayOfMapperFeature = new MapperFeature[19];
    arrayOfMapperFeature[0] = USE_ANNOTATIONS;
    arrayOfMapperFeature[1] = AUTO_DETECT_CREATORS;
    arrayOfMapperFeature[2] = AUTO_DETECT_FIELDS;
    arrayOfMapperFeature[3] = AUTO_DETECT_GETTERS;
    arrayOfMapperFeature[4] = AUTO_DETECT_IS_GETTERS;
    arrayOfMapperFeature[5] = AUTO_DETECT_SETTERS;
    arrayOfMapperFeature[6] = REQUIRE_SETTERS_FOR_GETTERS;
    arrayOfMapperFeature[7] = USE_GETTERS_AS_SETTERS;
    arrayOfMapperFeature[8] = CAN_OVERRIDE_ACCESS_MODIFIERS;
    arrayOfMapperFeature[9] = INFER_PROPERTY_MUTATORS;
    arrayOfMapperFeature[10] = ALLOW_FINAL_FIELDS_AS_MUTATORS;
    arrayOfMapperFeature[11] = PROPAGATE_TRANSIENT_MARKER;
    arrayOfMapperFeature[12] = USE_STATIC_TYPING;
    arrayOfMapperFeature[13] = DEFAULT_VIEW_INCLUSION;
    arrayOfMapperFeature[14] = SORT_PROPERTIES_ALPHABETICALLY;
    arrayOfMapperFeature[15] = ACCEPT_CASE_INSENSITIVE_PROPERTIES;
    arrayOfMapperFeature[16] = USE_WRAPPER_NAME_AS_PROPERTY_NAME;
    arrayOfMapperFeature[17] = USE_STD_BEAN_NAMING;
    arrayOfMapperFeature[18] = IGNORE_DUPLICATE_MODULE_REGISTRATIONS;
    $VALUES = arrayOfMapperFeature;
  }
  
  private MapperFeature(boolean paramBoolean)
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/MapperFeature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */