package android.support.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.CONSTRUCTOR, java.lang.annotation.ElementType.FIELD})
public @interface RequiresPermission
{
  String[] allOf();
  
  String[] anyOf();
  
  boolean conditional() default false;
  
  String value() default "";
  
  @Target({java.lang.annotation.ElementType.FIELD})
  public static @interface Write
  {
    RequiresPermission value();
  }
  
  @Target({java.lang.annotation.ElementType.FIELD})
  public static @interface Read
  {
    RequiresPermission value();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/annotation/RequiresPermission.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */