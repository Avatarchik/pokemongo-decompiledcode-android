package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;
import java.util.TimeZone;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.TYPE})
public @interface JsonFormat
{
  public static final String DEFAULT_LOCALE = "##default";
  public static final String DEFAULT_TIMEZONE = "##default";
  
  String locale() default "##default";
  
  String pattern() default "";
  
  Shape shape();
  
  String timezone() default "##default";
  
  Feature[] with();
  
  Feature[] without();
  
  public static class Value
    implements JacksonAnnotationValue<JsonFormat>
  {
    private TimeZone _timezone;
    private final JsonFormat.Features features;
    private final Locale locale;
    private final String pattern;
    private final JsonFormat.Shape shape;
    private final String timezoneStr;
    
    public Value()
    {
      this("", JsonFormat.Shape.ANY, "", "", JsonFormat.Features.empty());
    }
    
    public Value(JsonFormat paramJsonFormat)
    {
      this(paramJsonFormat.pattern(), paramJsonFormat.shape(), paramJsonFormat.locale(), paramJsonFormat.timezone(), JsonFormat.Features.construct(paramJsonFormat));
    }
    
    @Deprecated
    public Value(String paramString1, JsonFormat.Shape paramShape, String paramString2, String paramString3)
    {
      this(paramString1, paramShape, paramString2, paramString3, JsonFormat.Features.empty());
    }
    
    public Value(String paramString1, JsonFormat.Shape paramShape, String paramString2, String paramString3, JsonFormat.Features paramFeatures) {}
    
    @Deprecated
    public Value(String paramString1, JsonFormat.Shape paramShape, Locale paramLocale, String paramString2, TimeZone paramTimeZone)
    {
      this(paramString1, paramShape, paramLocale, paramString2, paramTimeZone, JsonFormat.Features.empty());
    }
    
    public Value(String paramString1, JsonFormat.Shape paramShape, Locale paramLocale, String paramString2, TimeZone paramTimeZone, JsonFormat.Features paramFeatures)
    {
      this.pattern = paramString1;
      if (paramShape == null) {
        paramShape = JsonFormat.Shape.ANY;
      }
      this.shape = paramShape;
      this.locale = paramLocale;
      this._timezone = paramTimeZone;
      this.timezoneStr = paramString2;
      if (paramFeatures == null) {
        paramFeatures = JsonFormat.Features.empty();
      }
      this.features = paramFeatures;
    }
    
    @Deprecated
    public Value(String paramString, JsonFormat.Shape paramShape, Locale paramLocale, TimeZone paramTimeZone)
    {
      this(paramString, paramShape, paramLocale, paramTimeZone, JsonFormat.Features.empty());
    }
    
    public Value(String paramString, JsonFormat.Shape paramShape, Locale paramLocale, TimeZone paramTimeZone, JsonFormat.Features paramFeatures)
    {
      this.pattern = paramString;
      if (paramShape == null) {
        paramShape = JsonFormat.Shape.ANY;
      }
      this.shape = paramShape;
      this.locale = paramLocale;
      this._timezone = paramTimeZone;
      this.timezoneStr = null;
      if (paramFeatures == null) {
        paramFeatures = JsonFormat.Features.empty();
      }
      this.features = paramFeatures;
    }
    
    public static Value forPattern(String paramString)
    {
      return new Value(paramString, null, null, null, null, JsonFormat.Features.empty());
    }
    
    public Boolean getFeature(JsonFormat.Feature paramFeature)
    {
      return this.features.get(paramFeature);
    }
    
    public Locale getLocale()
    {
      return this.locale;
    }
    
    public String getPattern()
    {
      return this.pattern;
    }
    
    public JsonFormat.Shape getShape()
    {
      return this.shape;
    }
    
    public TimeZone getTimeZone()
    {
      TimeZone localTimeZone1 = this._timezone;
      if (localTimeZone1 == null) {
        if (this.timezoneStr != null) {}
      }
      for (TimeZone localTimeZone2 = null;; localTimeZone2 = localTimeZone1)
      {
        return localTimeZone2;
        localTimeZone1 = TimeZone.getTimeZone(this.timezoneStr);
        this._timezone = localTimeZone1;
      }
    }
    
    public boolean hasLocale()
    {
      if (this.locale != null) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean hasPattern()
    {
      if ((this.pattern != null) && (this.pattern.length() > 0)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean hasShape()
    {
      if (this.shape != JsonFormat.Shape.ANY) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean hasTimeZone()
    {
      if ((this._timezone != null) || ((this.timezoneStr != null) && (!this.timezoneStr.isEmpty()))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public String timeZoneAsString()
    {
      if (this._timezone != null) {}
      for (String str = this._timezone.getID();; str = this.timezoneStr) {
        return str;
      }
    }
    
    public Class<JsonFormat> valueFor()
    {
      return JsonFormat.class;
    }
    
    public Value withFeature(JsonFormat.Feature paramFeature)
    {
      JsonFormat.Features localFeatures1 = this.features;
      JsonFormat.Feature[] arrayOfFeature = new JsonFormat.Feature[1];
      arrayOfFeature[0] = paramFeature;
      JsonFormat.Features localFeatures2 = localFeatures1.with(arrayOfFeature);
      if (localFeatures2 == this.features) {}
      for (;;)
      {
        return this;
        this = new Value(this.pattern, this.shape, this.locale, this.timezoneStr, this._timezone, localFeatures2);
      }
    }
    
    public Value withLocale(Locale paramLocale)
    {
      return new Value(this.pattern, this.shape, paramLocale, this.timezoneStr, this._timezone, this.features);
    }
    
    public Value withPattern(String paramString)
    {
      return new Value(paramString, this.shape, this.locale, this.timezoneStr, this._timezone, this.features);
    }
    
    public Value withShape(JsonFormat.Shape paramShape)
    {
      return new Value(this.pattern, paramShape, this.locale, this.timezoneStr, this._timezone, this.features);
    }
    
    public Value withTimeZone(TimeZone paramTimeZone)
    {
      return new Value(this.pattern, this.shape, this.locale, null, paramTimeZone, this.features);
    }
    
    public Value withoutFeature(JsonFormat.Feature paramFeature)
    {
      JsonFormat.Features localFeatures1 = this.features;
      JsonFormat.Feature[] arrayOfFeature = new JsonFormat.Feature[1];
      arrayOfFeature[0] = paramFeature;
      JsonFormat.Features localFeatures2 = localFeatures1.without(arrayOfFeature);
      if (localFeatures2 == this.features) {}
      for (;;)
      {
        return this;
        this = new Value(this.pattern, this.shape, this.locale, this.timezoneStr, this._timezone, localFeatures2);
      }
    }
  }
  
  public static class Features
  {
    private static final Features EMPTY = new Features(0, 0);
    private final int disabled;
    private final int enabled;
    
    private Features(int paramInt1, int paramInt2)
    {
      this.enabled = paramInt1;
      this.disabled = paramInt2;
    }
    
    public static Features construct(JsonFormat paramJsonFormat)
    {
      return construct(paramJsonFormat.with(), paramJsonFormat.without());
    }
    
    public static Features construct(JsonFormat.Feature[] paramArrayOfFeature1, JsonFormat.Feature[] paramArrayOfFeature2)
    {
      int i = 0;
      int j = paramArrayOfFeature1.length;
      for (int k = 0; k < j; k++) {
        i |= 1 << paramArrayOfFeature1[k].ordinal();
      }
      int m = 0;
      int n = paramArrayOfFeature2.length;
      for (int i1 = 0; i1 < n; i1++) {
        m |= 1 << paramArrayOfFeature2[i1].ordinal();
      }
      return new Features(i, m);
    }
    
    public static Features empty()
    {
      return EMPTY;
    }
    
    public Boolean get(JsonFormat.Feature paramFeature)
    {
      int i = 1 << paramFeature.ordinal();
      Boolean localBoolean;
      if ((i & this.disabled) != 0) {
        localBoolean = Boolean.FALSE;
      }
      for (;;)
      {
        return localBoolean;
        if ((i & this.enabled) != 0) {
          localBoolean = Boolean.TRUE;
        } else {
          localBoolean = null;
        }
      }
    }
    
    public Features with(JsonFormat.Feature... paramVarArgs)
    {
      int i = this.enabled;
      int j = paramVarArgs.length;
      for (int k = 0; k < j; k++) {
        i |= 1 << paramVarArgs[k].ordinal();
      }
      if (i == this.enabled) {}
      for (;;)
      {
        return this;
        this = new Features(i, this.disabled);
      }
    }
    
    public Features without(JsonFormat.Feature... paramVarArgs)
    {
      int i = this.disabled;
      int j = paramVarArgs.length;
      for (int k = 0; k < j; k++) {
        i |= 1 << paramVarArgs[k].ordinal();
      }
      if (i == this.disabled) {}
      for (;;)
      {
        return this;
        this = new Features(this.enabled, i);
      }
    }
  }
  
  public static enum Feature
  {
    static
    {
      WRITE_DATES_WITH_ZONE_ID = new Feature("WRITE_DATES_WITH_ZONE_ID", 2);
      WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED = new Feature("WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED", 3);
      WRITE_SORTED_MAP_ENTRIES = new Feature("WRITE_SORTED_MAP_ENTRIES", 4);
      Feature[] arrayOfFeature = new Feature[5];
      arrayOfFeature[0] = ACCEPT_SINGLE_VALUE_AS_ARRAY;
      arrayOfFeature[1] = WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS;
      arrayOfFeature[2] = WRITE_DATES_WITH_ZONE_ID;
      arrayOfFeature[3] = WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED;
      arrayOfFeature[4] = WRITE_SORTED_MAP_ENTRIES;
      $VALUES = arrayOfFeature;
    }
    
    private Feature() {}
  }
  
  public static enum Shape
  {
    static
    {
      ARRAY = new Shape("ARRAY", 2);
      OBJECT = new Shape("OBJECT", 3);
      NUMBER = new Shape("NUMBER", 4);
      NUMBER_FLOAT = new Shape("NUMBER_FLOAT", 5);
      NUMBER_INT = new Shape("NUMBER_INT", 6);
      STRING = new Shape("STRING", 7);
      BOOLEAN = new Shape("BOOLEAN", 8);
      Shape[] arrayOfShape = new Shape[9];
      arrayOfShape[0] = ANY;
      arrayOfShape[1] = SCALAR;
      arrayOfShape[2] = ARRAY;
      arrayOfShape[3] = OBJECT;
      arrayOfShape[4] = NUMBER;
      arrayOfShape[5] = NUMBER_FLOAT;
      arrayOfShape[6] = NUMBER_INT;
      arrayOfShape[7] = STRING;
      arrayOfShape[8] = BOOLEAN;
      $VALUES = arrayOfShape;
    }
    
    private Shape() {}
    
    public boolean isNumeric()
    {
      if ((this == NUMBER) || (this == NUMBER_INT) || (this == NUMBER_FLOAT)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public boolean isStructured()
    {
      if ((this == OBJECT) || (this == ARRAY)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/annotation/JsonFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */