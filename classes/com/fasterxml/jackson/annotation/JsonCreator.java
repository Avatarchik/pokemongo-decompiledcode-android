package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.CONSTRUCTOR})
public @interface JsonCreator
{
  Mode mode();
  
  public static enum Mode
  {
    static
    {
      DISABLED = new Mode("DISABLED", 3);
      Mode[] arrayOfMode = new Mode[4];
      arrayOfMode[0] = DEFAULT;
      arrayOfMode[1] = DELEGATING;
      arrayOfMode[2] = PROPERTIES;
      arrayOfMode[3] = DISABLED;
      $VALUES = arrayOfMode;
    }
    
    private Mode() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/annotation/JsonCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */