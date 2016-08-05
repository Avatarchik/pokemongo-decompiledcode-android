package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

public class BeanUtil
{
  protected static boolean isCglibGetCallbacks(AnnotatedMethod paramAnnotatedMethod)
  {
    boolean bool = false;
    Class localClass = paramAnnotatedMethod.getRawType();
    if ((localClass == null) || (!localClass.isArray())) {}
    for (;;)
    {
      return bool;
      Package localPackage = localClass.getComponentType().getPackage();
      if (localPackage != null)
      {
        String str = localPackage.getName();
        if ((str.contains(".cglib")) && ((str.startsWith("net.sf.cglib")) || (str.startsWith("org.hibernate.repackage.cglib")) || (str.startsWith("org.springframework.cglib")))) {
          bool = true;
        }
      }
    }
  }
  
  protected static boolean isGroovyMetaClassGetter(AnnotatedMethod paramAnnotatedMethod)
  {
    boolean bool = false;
    Class localClass = paramAnnotatedMethod.getRawType();
    if ((localClass == null) || (localClass.isArray())) {}
    for (;;)
    {
      return bool;
      Package localPackage = localClass.getPackage();
      if ((localPackage != null) && (localPackage.getName().startsWith("groovy.lang"))) {
        bool = true;
      }
    }
  }
  
  protected static boolean isGroovyMetaClassSetter(AnnotatedMethod paramAnnotatedMethod)
  {
    boolean bool = false;
    Package localPackage = paramAnnotatedMethod.getRawParameterType(0).getPackage();
    if ((localPackage != null) && (localPackage.getName().startsWith("groovy.lang"))) {
      bool = true;
    }
    return bool;
  }
  
  protected static String legacyManglePropertyName(String paramString, int paramInt)
  {
    int i = paramString.length();
    String str;
    if (i == paramInt) {
      str = null;
    }
    for (;;)
    {
      return str;
      StringBuilder localStringBuilder = null;
      for (int j = paramInt;; j++)
      {
        char c2;
        if (j < i)
        {
          char c1 = paramString.charAt(j);
          c2 = Character.toLowerCase(c1);
          if (c1 != c2) {}
        }
        else
        {
          if (localStringBuilder != null) {
            break label102;
          }
          str = paramString.substring(paramInt);
          break;
        }
        if (localStringBuilder == null)
        {
          localStringBuilder = new StringBuilder(i - paramInt);
          localStringBuilder.append(paramString, paramInt, i);
        }
        localStringBuilder.setCharAt(j - paramInt, c2);
      }
      label102:
      str = localStringBuilder.toString();
    }
  }
  
  @Deprecated
  public static String okNameForGetter(AnnotatedMethod paramAnnotatedMethod)
  {
    return okNameForGetter(paramAnnotatedMethod, false);
  }
  
  public static String okNameForGetter(AnnotatedMethod paramAnnotatedMethod, boolean paramBoolean)
  {
    String str1 = paramAnnotatedMethod.getName();
    String str2 = okNameForIsGetter(paramAnnotatedMethod, str1, paramBoolean);
    if (str2 == null) {
      str2 = okNameForRegularGetter(paramAnnotatedMethod, str1, paramBoolean);
    }
    return str2;
  }
  
  @Deprecated
  public static String okNameForIsGetter(AnnotatedMethod paramAnnotatedMethod, String paramString)
  {
    return okNameForIsGetter(paramAnnotatedMethod, paramString, false);
  }
  
  public static String okNameForIsGetter(AnnotatedMethod paramAnnotatedMethod, String paramString, boolean paramBoolean)
  {
    String str;
    if (paramString.startsWith("is"))
    {
      Class localClass = paramAnnotatedMethod.getRawType();
      if ((localClass == Boolean.class) || (localClass == Boolean.TYPE)) {
        if (paramBoolean) {
          str = stdManglePropertyName(paramString, 2);
        }
      }
    }
    for (;;)
    {
      return str;
      str = legacyManglePropertyName(paramString, 2);
      continue;
      str = null;
    }
  }
  
  @Deprecated
  public static String okNameForMutator(AnnotatedMethod paramAnnotatedMethod, String paramString)
  {
    return okNameForMutator(paramAnnotatedMethod, paramString, false);
  }
  
  public static String okNameForMutator(AnnotatedMethod paramAnnotatedMethod, String paramString, boolean paramBoolean)
  {
    String str1 = paramAnnotatedMethod.getName();
    String str2;
    if (str1.startsWith(paramString)) {
      if (paramBoolean) {
        str2 = stdManglePropertyName(str1, paramString.length());
      }
    }
    for (;;)
    {
      return str2;
      str2 = legacyManglePropertyName(str1, paramString.length());
      continue;
      str2 = null;
    }
  }
  
  @Deprecated
  public static String okNameForRegularGetter(AnnotatedMethod paramAnnotatedMethod, String paramString)
  {
    return okNameForRegularGetter(paramAnnotatedMethod, paramString, false);
  }
  
  public static String okNameForRegularGetter(AnnotatedMethod paramAnnotatedMethod, String paramString, boolean paramBoolean)
  {
    String str = null;
    if (paramString.startsWith("get"))
    {
      if (!"getCallbacks".equals(paramString)) {
        break label29;
      }
      if (!isCglibGetCallbacks(paramAnnotatedMethod)) {
        break label45;
      }
    }
    for (;;)
    {
      return str;
      label29:
      if ((!"getMetaClass".equals(paramString)) || (!isGroovyMetaClassGetter(paramAnnotatedMethod))) {
        label45:
        if (paramBoolean) {
          str = stdManglePropertyName(paramString, 3);
        } else {
          str = legacyManglePropertyName(paramString, 3);
        }
      }
    }
  }
  
  @Deprecated
  public static String okNameForSetter(AnnotatedMethod paramAnnotatedMethod)
  {
    return okNameForSetter(paramAnnotatedMethod, false);
  }
  
  public static String okNameForSetter(AnnotatedMethod paramAnnotatedMethod, boolean paramBoolean)
  {
    String str = okNameForMutator(paramAnnotatedMethod, "set", paramBoolean);
    if ((str != null) && ((!"metaClass".equals(str)) || (!isGroovyMetaClassSetter(paramAnnotatedMethod)))) {}
    for (;;)
    {
      return str;
      str = null;
    }
  }
  
  protected static String stdManglePropertyName(String paramString, int paramInt)
  {
    int i = paramString.length();
    String str;
    if (i == paramInt) {
      str = null;
    }
    for (;;)
    {
      return str;
      char c1 = paramString.charAt(paramInt);
      char c2 = Character.toLowerCase(c1);
      if (c1 == c2)
      {
        str = paramString.substring(paramInt);
      }
      else if ((paramInt + 1 < i) && (Character.isUpperCase(paramString.charAt(paramInt + 1))))
      {
        str = paramString.substring(paramInt);
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder(i - paramInt);
        localStringBuilder.append(c2);
        localStringBuilder.append(paramString, paramInt + 1, i);
        str = localStringBuilder.toString();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/BeanUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */