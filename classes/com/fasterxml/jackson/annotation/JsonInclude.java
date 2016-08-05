package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.PARAMETER})
public @interface JsonInclude
{
  Include content();
  
  Include value();
  
  public static class Value
    implements JacksonAnnotationValue<JsonInclude>
  {
    protected static final Value EMPTY = new Value(JsonInclude.Include.USE_DEFAULTS, JsonInclude.Include.USE_DEFAULTS);
    protected final JsonInclude.Include contentInclusion;
    protected final JsonInclude.Include valueInclusion;
    
    protected Value(JsonInclude.Include paramInclude1, JsonInclude.Include paramInclude2)
    {
      if (paramInclude1 == null) {
        paramInclude1 = JsonInclude.Include.USE_DEFAULTS;
      }
      this.valueInclusion = paramInclude1;
      if (paramInclude2 == null) {
        paramInclude2 = JsonInclude.Include.USE_DEFAULTS;
      }
      this.contentInclusion = paramInclude2;
    }
    
    public Value(JsonInclude paramJsonInclude)
    {
      this(paramJsonInclude.value(), paramJsonInclude.content());
    }
    
    public static Value construct(JsonInclude.Include paramInclude1, JsonInclude.Include paramInclude2)
    {
      if ((paramInclude1 == JsonInclude.Include.USE_DEFAULTS) && (paramInclude2 == JsonInclude.Include.USE_DEFAULTS)) {}
      for (Value localValue = EMPTY;; localValue = new Value(paramInclude1, paramInclude2)) {
        return localValue;
      }
    }
    
    public static Value empty()
    {
      return EMPTY;
    }
    
    public static Value from(JsonInclude paramJsonInclude)
    {
      if (paramJsonInclude == null) {}
      for (Value localValue = null;; localValue = new Value(paramJsonInclude)) {
        return localValue;
      }
    }
    
    public JsonInclude.Include getContentInclusion()
    {
      return this.contentInclusion;
    }
    
    public JsonInclude.Include getValueInclusion()
    {
      return this.valueInclusion;
    }
    
    public Class<JsonInclude> valueFor()
    {
      return JsonInclude.class;
    }
    
    public Value withContentInclusion(JsonInclude.Include paramInclude)
    {
      if (paramInclude == this.contentInclusion) {}
      for (;;)
      {
        return this;
        this = new Value(this.valueInclusion, paramInclude);
      }
    }
    
    public Value withOverrides(Value paramValue)
    {
      if (paramValue == null) {}
      for (;;)
      {
        return this;
        this = withValueInclusion(paramValue.valueInclusion).withContentInclusion(paramValue.contentInclusion);
      }
    }
    
    public Value withValueInclusion(JsonInclude.Include paramInclude)
    {
      if (paramInclude == this.valueInclusion) {}
      for (;;)
      {
        return this;
        this = new Value(paramInclude, this.contentInclusion);
      }
    }
  }
  
  public static enum Include
  {
    static
    {
      NON_ABSENT = new Include("NON_ABSENT", 2);
      NON_EMPTY = new Include("NON_EMPTY", 3);
      NON_DEFAULT = new Include("NON_DEFAULT", 4);
      USE_DEFAULTS = new Include("USE_DEFAULTS", 5);
      Include[] arrayOfInclude = new Include[6];
      arrayOfInclude[0] = ALWAYS;
      arrayOfInclude[1] = NON_NULL;
      arrayOfInclude[2] = NON_ABSENT;
      arrayOfInclude[3] = NON_EMPTY;
      arrayOfInclude[4] = NON_DEFAULT;
      arrayOfInclude[5] = USE_DEFAULTS;
      $VALUES = arrayOfInclude;
    }
    
    private Include() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/annotation/JsonInclude.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */