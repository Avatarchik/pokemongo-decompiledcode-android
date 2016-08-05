package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.ANNOTATION_TYPE, java.lang.annotation.ElementType.TYPE})
public @interface JsonAutoDetect
{
  Visibility creatorVisibility();
  
  Visibility fieldVisibility();
  
  Visibility getterVisibility();
  
  Visibility isGetterVisibility();
  
  Visibility setterVisibility();
  
  public static enum Visibility
  {
    static
    {
      NONE = new Visibility("NONE", 4);
      DEFAULT = new Visibility("DEFAULT", 5);
      Visibility[] arrayOfVisibility = new Visibility[6];
      arrayOfVisibility[0] = ANY;
      arrayOfVisibility[1] = NON_PRIVATE;
      arrayOfVisibility[2] = PROTECTED_AND_PUBLIC;
      arrayOfVisibility[3] = PUBLIC_ONLY;
      arrayOfVisibility[4] = NONE;
      arrayOfVisibility[5] = DEFAULT;
      $VALUES = arrayOfVisibility;
    }
    
    private Visibility() {}
    
    public boolean isVisible(Member paramMember)
    {
      boolean bool = true;
      switch (JsonAutoDetect.1.$SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility[ordinal()])
      {
      default: 
        bool = false;
      }
      for (;;)
      {
        return bool;
        bool = false;
        continue;
        if (Modifier.isPrivate(paramMember.getModifiers()))
        {
          bool = false;
          continue;
          if (!Modifier.isProtected(paramMember.getModifiers())) {
            bool = Modifier.isPublic(paramMember.getModifiers());
          }
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/annotation/JsonAutoDetect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */