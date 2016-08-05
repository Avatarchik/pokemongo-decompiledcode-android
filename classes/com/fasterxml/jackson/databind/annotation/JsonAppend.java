package com.fasterxml.jackson.databind.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ser.VirtualBeanPropertyWriter;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.TYPE})
public @interface JsonAppend
{
  Attr[] attrs();
  
  boolean prepend() default false;
  
  Prop[] props();
  
  public static @interface Prop
  {
    JsonInclude.Include include();
    
    String name() default "";
    
    String namespace() default "";
    
    boolean required() default false;
    
    Class<?> type() default Object.class;
    
    Class<? extends VirtualBeanPropertyWriter> value();
  }
  
  public static @interface Attr
  {
    JsonInclude.Include include();
    
    String propName() default "";
    
    String propNamespace() default "";
    
    boolean required() default false;
    
    String value();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/annotation/JsonAppend.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */