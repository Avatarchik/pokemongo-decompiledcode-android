package dagger;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface Provides
{
  Type type();
  
  public static enum Type
  {
    static
    {
      SET = new Type("SET", 1);
      SET_VALUES = new Type("SET_VALUES", 2);
      MAP = new Type("MAP", 3);
      Type[] arrayOfType = new Type[4];
      arrayOfType[0] = UNIQUE;
      arrayOfType[1] = SET;
      arrayOfType[2] = SET_VALUES;
      arrayOfType[3] = MAP;
      $VALUES = arrayOfType;
    }
    
    private Type() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/dagger/Provides.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */