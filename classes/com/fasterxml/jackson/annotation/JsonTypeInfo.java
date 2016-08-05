package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.PARAMETER})
public @interface JsonTypeInfo
{
  Class<?> defaultImpl() default None.class;
  
  As include();
  
  String property() default "";
  
  Id use();
  
  boolean visible() default false;
  
  @Deprecated
  public static abstract class None {}
  
  public static enum As
  {
    static
    {
      WRAPPER_ARRAY = new As("WRAPPER_ARRAY", 2);
      EXTERNAL_PROPERTY = new As("EXTERNAL_PROPERTY", 3);
      EXISTING_PROPERTY = new As("EXISTING_PROPERTY", 4);
      As[] arrayOfAs = new As[5];
      arrayOfAs[0] = PROPERTY;
      arrayOfAs[1] = WRAPPER_OBJECT;
      arrayOfAs[2] = WRAPPER_ARRAY;
      arrayOfAs[3] = EXTERNAL_PROPERTY;
      arrayOfAs[4] = EXISTING_PROPERTY;
      $VALUES = arrayOfAs;
    }
    
    private As() {}
  }
  
  public static enum Id
  {
    private final String _defaultPropertyName;
    
    static
    {
      CLASS = new Id("CLASS", 1, "@class");
      MINIMAL_CLASS = new Id("MINIMAL_CLASS", 2, "@c");
      NAME = new Id("NAME", 3, "@type");
      CUSTOM = new Id("CUSTOM", 4, null);
      Id[] arrayOfId = new Id[5];
      arrayOfId[0] = NONE;
      arrayOfId[1] = CLASS;
      arrayOfId[2] = MINIMAL_CLASS;
      arrayOfId[3] = NAME;
      arrayOfId[4] = CUSTOM;
      $VALUES = arrayOfId;
    }
    
    private Id(String paramString)
    {
      this._defaultPropertyName = paramString;
    }
    
    public String getDefaultPropertyName()
    {
      return this._defaultPropertyName;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/annotation/JsonTypeInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */