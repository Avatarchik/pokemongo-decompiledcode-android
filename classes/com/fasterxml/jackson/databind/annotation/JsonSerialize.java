package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.JsonSerializer.None;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.Converter.None;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.PARAMETER})
public @interface JsonSerialize
{
  Class<?> as() default Void.class;
  
  Class<?> contentAs() default Void.class;
  
  Class<? extends Converter> contentConverter() default Converter.None.class;
  
  Class<? extends JsonSerializer> contentUsing() default JsonSerializer.None.class;
  
  Class<? extends Converter> converter() default Converter.None.class;
  
  @Deprecated
  Inclusion include();
  
  Class<?> keyAs() default Void.class;
  
  Class<? extends JsonSerializer> keyUsing() default JsonSerializer.None.class;
  
  Class<? extends JsonSerializer> nullsUsing() default JsonSerializer.None.class;
  
  Typing typing();
  
  Class<? extends JsonSerializer> using() default JsonSerializer.None.class;
  
  public static enum Typing
  {
    static
    {
      DEFAULT_TYPING = new Typing("DEFAULT_TYPING", 2);
      Typing[] arrayOfTyping = new Typing[3];
      arrayOfTyping[0] = DYNAMIC;
      arrayOfTyping[1] = STATIC;
      arrayOfTyping[2] = DEFAULT_TYPING;
      $VALUES = arrayOfTyping;
    }
    
    private Typing() {}
  }
  
  @Deprecated
  public static enum Inclusion
  {
    static
    {
      NON_DEFAULT = new Inclusion("NON_DEFAULT", 2);
      NON_EMPTY = new Inclusion("NON_EMPTY", 3);
      DEFAULT_INCLUSION = new Inclusion("DEFAULT_INCLUSION", 4);
      Inclusion[] arrayOfInclusion = new Inclusion[5];
      arrayOfInclusion[0] = ALWAYS;
      arrayOfInclusion[1] = NON_NULL;
      arrayOfInclusion[2] = NON_DEFAULT;
      arrayOfInclusion[3] = NON_EMPTY;
      arrayOfInclusion[4] = DEFAULT_INCLUSION;
      $VALUES = arrayOfInclusion;
    }
    
    private Inclusion() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/annotation/JsonSerialize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */