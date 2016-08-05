package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import java.io.Serializable;

public abstract class PropertyNamingStrategy
  implements Serializable
{
  public static final PropertyNamingStrategy CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES = new LowerCaseWithUnderscoresStrategy();
  public static final PropertyNamingStrategy LOWER_CASE = new LowerCaseStrategy();
  public static final PropertyNamingStrategy PASCAL_CASE_TO_CAMEL_CASE = new PascalCaseStrategy();
  
  public String nameForConstructorParameter(MapperConfig<?> paramMapperConfig, AnnotatedParameter paramAnnotatedParameter, String paramString)
  {
    return paramString;
  }
  
  public String nameForField(MapperConfig<?> paramMapperConfig, AnnotatedField paramAnnotatedField, String paramString)
  {
    return paramString;
  }
  
  public String nameForGetterMethod(MapperConfig<?> paramMapperConfig, AnnotatedMethod paramAnnotatedMethod, String paramString)
  {
    return paramString;
  }
  
  public String nameForSetterMethod(MapperConfig<?> paramMapperConfig, AnnotatedMethod paramAnnotatedMethod, String paramString)
  {
    return paramString;
  }
  
  public static class LowerCaseStrategy
    extends PropertyNamingStrategy.PropertyNamingStrategyBase
  {
    public String translate(String paramString)
    {
      return paramString.toLowerCase();
    }
  }
  
  public static class PascalCaseStrategy
    extends PropertyNamingStrategy.PropertyNamingStrategyBase
  {
    public String translate(String paramString)
    {
      if ((paramString == null) || (paramString.length() == 0)) {}
      for (;;)
      {
        return paramString;
        char c1 = paramString.charAt(0);
        char c2 = Character.toUpperCase(c1);
        if (c1 != c2)
        {
          StringBuilder localStringBuilder = new StringBuilder(paramString);
          localStringBuilder.setCharAt(0, c2);
          paramString = localStringBuilder.toString();
        }
      }
    }
  }
  
  public static class LowerCaseWithUnderscoresStrategy
    extends PropertyNamingStrategy.PropertyNamingStrategyBase
  {
    public String translate(String paramString)
    {
      if (paramString == null) {}
      for (;;)
      {
        return paramString;
        int i = paramString.length();
        StringBuilder localStringBuilder = new StringBuilder(i * 2);
        int j = 0;
        int k = 0;
        int m = 0;
        if (m < i)
        {
          char c = paramString.charAt(m);
          if ((m > 0) || (c != '_'))
          {
            if (!Character.isUpperCase(c)) {
              break label124;
            }
            if ((k == 0) && (j > 0) && (localStringBuilder.charAt(j - 1) != '_'))
            {
              localStringBuilder.append('_');
              j++;
            }
            c = Character.toLowerCase(c);
          }
          label124:
          for (k = 1;; k = 0)
          {
            localStringBuilder.append(c);
            j++;
            m++;
            break;
          }
        }
        if (j > 0) {
          paramString = localStringBuilder.toString();
        }
      }
    }
  }
  
  public static abstract class PropertyNamingStrategyBase
    extends PropertyNamingStrategy
  {
    public String nameForConstructorParameter(MapperConfig<?> paramMapperConfig, AnnotatedParameter paramAnnotatedParameter, String paramString)
    {
      return translate(paramString);
    }
    
    public String nameForField(MapperConfig<?> paramMapperConfig, AnnotatedField paramAnnotatedField, String paramString)
    {
      return translate(paramString);
    }
    
    public String nameForGetterMethod(MapperConfig<?> paramMapperConfig, AnnotatedMethod paramAnnotatedMethod, String paramString)
    {
      return translate(paramString);
    }
    
    public String nameForSetterMethod(MapperConfig<?> paramMapperConfig, AnnotatedMethod paramAnnotatedMethod, String paramString)
    {
      return translate(paramString);
    }
    
    public abstract String translate(String paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/PropertyNamingStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */