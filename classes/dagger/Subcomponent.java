package dagger;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;

@Documented
@Target({java.lang.annotation.ElementType.TYPE})
public @interface Subcomponent
{
  Class<?>[] modules();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/dagger/Subcomponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */