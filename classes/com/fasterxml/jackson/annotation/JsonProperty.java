package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER})
public @interface JsonProperty
{
  public static final int INDEX_UNKNOWN = -1;
  public static final String USE_DEFAULT_NAME = "";
  
  Access access();
  
  String defaultValue() default "";
  
  int index() default -1;
  
  boolean required() default false;
  
  String value() default "";
  
  public static enum Access
  {
    static
    {
      READ_WRITE = new Access("READ_WRITE", 3);
      Access[] arrayOfAccess = new Access[4];
      arrayOfAccess[0] = AUTO;
      arrayOfAccess[1] = READ_ONLY;
      arrayOfAccess[2] = WRITE_ONLY;
      arrayOfAccess[3] = READ_WRITE;
      $VALUES = arrayOfAccess;
    }
    
    private Access() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/annotation/JsonProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */