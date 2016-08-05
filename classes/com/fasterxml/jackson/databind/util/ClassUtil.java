package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.annotation.NoClass;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ClassUtil
{
  private static void _addSuperTypes(Class<?> paramClass1, Class<?> paramClass2, Collection<Class<?>> paramCollection, boolean paramBoolean)
  {
    if ((paramClass1 == paramClass2) || (paramClass1 == null) || (paramClass1 == Object.class)) {}
    for (;;)
    {
      return;
      if (paramBoolean)
      {
        if (!paramCollection.contains(paramClass1)) {
          paramCollection.add(paramClass1);
        }
      }
      else
      {
        Class[] arrayOfClass = paramClass1.getInterfaces();
        int i = arrayOfClass.length;
        for (int j = 0; j < i; j++) {
          _addSuperTypes(arrayOfClass[j], paramClass2, paramCollection, true);
        }
        _addSuperTypes(paramClass1.getSuperclass(), paramClass2, paramCollection, true);
      }
    }
  }
  
  public static String canBeABeanType(Class<?> paramClass)
  {
    String str;
    if (paramClass.isAnnotation()) {
      str = "annotation";
    }
    for (;;)
    {
      return str;
      if (paramClass.isArray()) {
        str = "array";
      } else if (paramClass.isEnum()) {
        str = "enum";
      } else if (paramClass.isPrimitive()) {
        str = "primitive";
      } else {
        str = null;
      }
    }
  }
  
  public static void checkAndFixAccess(Member paramMember)
  {
    AccessibleObject localAccessibleObject = (AccessibleObject)paramMember;
    try
    {
      localAccessibleObject.setAccessible(true);
      return;
    }
    catch (SecurityException localSecurityException)
    {
      while (localAccessibleObject.isAccessible()) {}
      Class localClass = paramMember.getDeclaringClass();
      throw new IllegalArgumentException("Can not access " + paramMember + " (from class " + localClass.getName() + "; failed to set access: " + localSecurityException.getMessage());
    }
  }
  
  public static <T> T createInstance(Class<T> paramClass, boolean paramBoolean)
    throws IllegalArgumentException
  {
    Constructor localConstructor = findConstructor(paramClass, paramBoolean);
    if (localConstructor == null) {
      throw new IllegalArgumentException("Class " + paramClass.getName() + " has no default (no arg) constructor");
    }
    try
    {
      Object localObject2 = localConstructor.newInstance(new Object[0]);
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        unwrapAndThrowAsIAE(localException, "Failed to instantiate class " + paramClass.getName() + ", problem: " + localException.getMessage());
        Object localObject1 = null;
      }
    }
    return (T)localObject1;
  }
  
  public static Object defaultValue(Class<?> paramClass)
  {
    Object localObject;
    if (paramClass == Integer.TYPE) {
      localObject = Integer.valueOf(0);
    }
    for (;;)
    {
      return localObject;
      if (paramClass == Long.TYPE)
      {
        localObject = Long.valueOf(0L);
      }
      else if (paramClass == Boolean.TYPE)
      {
        localObject = Boolean.FALSE;
      }
      else if (paramClass == Double.TYPE)
      {
        localObject = Double.valueOf(0.0D);
      }
      else if (paramClass == Float.TYPE)
      {
        localObject = Float.valueOf(0.0F);
      }
      else if (paramClass == Byte.TYPE)
      {
        localObject = Byte.valueOf((byte)0);
      }
      else if (paramClass == Short.TYPE)
      {
        localObject = Short.valueOf((short)0);
      }
      else
      {
        if (paramClass != Character.TYPE) {
          break;
        }
        localObject = Character.valueOf('\000');
      }
    }
    throw new IllegalArgumentException("Class " + paramClass.getName() + " is not a primitive type");
  }
  
  @Deprecated
  public static Class<?> findClass(String paramString)
    throws ClassNotFoundException
  {
    Object localObject;
    if (paramString.indexOf('.') < 0) {
      if ("int".equals(paramString)) {
        localObject = Integer.TYPE;
      }
    }
    for (;;)
    {
      return (Class<?>)localObject;
      if ("long".equals(paramString))
      {
        localObject = Long.TYPE;
        continue;
      }
      if ("float".equals(paramString))
      {
        localObject = Float.TYPE;
        continue;
      }
      if ("double".equals(paramString))
      {
        localObject = Double.TYPE;
        continue;
      }
      if ("boolean".equals(paramString))
      {
        localObject = Boolean.TYPE;
        continue;
      }
      if ("byte".equals(paramString))
      {
        localObject = Byte.TYPE;
        continue;
      }
      if ("char".equals(paramString))
      {
        localObject = Character.TYPE;
        continue;
      }
      if ("short".equals(paramString))
      {
        localObject = Short.TYPE;
        continue;
      }
      if ("void".equals(paramString))
      {
        localObject = Void.TYPE;
        continue;
      }
      Throwable localThrowable = null;
      ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
      if (localClassLoader != null) {
        try
        {
          Class localClass2 = Class.forName(paramString, true, localClassLoader);
          localObject = localClass2;
        }
        catch (Exception localException2)
        {
          localThrowable = getRootCause(localException2);
        }
      }
      try
      {
        Class localClass1 = Class.forName(paramString);
        localObject = localClass1;
      }
      catch (Exception localException1)
      {
        if (localThrowable == null) {
          localThrowable = getRootCause(localException1);
        }
        if ((localThrowable instanceof RuntimeException)) {
          throw ((RuntimeException)localThrowable);
        }
        throw new ClassNotFoundException(localThrowable.getMessage(), localThrowable);
      }
    }
  }
  
  public static <T> Constructor<T> findConstructor(Class<T> paramClass, boolean paramBoolean)
    throws IllegalArgumentException
  {
    Constructor localConstructor;
    try
    {
      localConstructor = paramClass.getDeclaredConstructor(new Class[0]);
      if (paramBoolean) {
        checkAndFixAccess(localConstructor);
      } else if (!Modifier.isPublic(localConstructor.getModifiers())) {
        throw new IllegalArgumentException("Default constructor for " + paramClass.getName() + " is not accessible (non-public?): not allowed to try modify access via Reflection: can not instantiate type");
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      localConstructor = null;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        unwrapAndThrowAsIAE(localException, "Failed to find default constructor of class " + paramClass.getName() + ", problem: " + localException.getMessage());
      }
    }
    return localConstructor;
  }
  
  public static Class<? extends Enum<?>> findEnumType(Class<?> paramClass)
  {
    if (paramClass.getSuperclass() != Enum.class) {
      paramClass = paramClass.getSuperclass();
    }
    return paramClass;
  }
  
  public static Class<? extends Enum<?>> findEnumType(Enum<?> paramEnum)
  {
    Class localClass = paramEnum.getClass();
    if (localClass.getSuperclass() != Enum.class) {
      localClass = localClass.getSuperclass();
    }
    return localClass;
  }
  
  public static Class<? extends Enum<?>> findEnumType(EnumMap<?, ?> paramEnumMap)
  {
    if (!paramEnumMap.isEmpty()) {}
    for (Class localClass = findEnumType((Enum)paramEnumMap.keySet().iterator().next());; localClass = EnumTypeLocator.instance.enumTypeFor(paramEnumMap)) {
      return localClass;
    }
  }
  
  public static Class<? extends Enum<?>> findEnumType(EnumSet<?> paramEnumSet)
  {
    if (!paramEnumSet.isEmpty()) {}
    for (Class localClass = findEnumType((Enum)paramEnumSet.iterator().next());; localClass = EnumTypeLocator.instance.enumTypeFor(paramEnumSet)) {
      return localClass;
    }
  }
  
  public static List<Class<?>> findSuperTypes(Class<?> paramClass1, Class<?> paramClass2)
  {
    return findSuperTypes(paramClass1, paramClass2, new ArrayList(8));
  }
  
  public static List<Class<?>> findSuperTypes(Class<?> paramClass1, Class<?> paramClass2, List<Class<?>> paramList)
  {
    _addSuperTypes(paramClass1, paramClass2, paramList, false);
    return paramList;
  }
  
  public static String getClassDescription(Object paramObject)
  {
    String str;
    if (paramObject == null)
    {
      str = "unknown";
      return str;
    }
    if ((paramObject instanceof Class)) {}
    for (Class localClass = (Class)paramObject;; localClass = paramObject.getClass())
    {
      str = localClass.getName();
      break;
    }
  }
  
  public static Class<?> getOuterClass(Class<?> paramClass)
  {
    Object localObject = null;
    try
    {
      if ((paramClass.getEnclosingMethod() == null) && (!Modifier.isStatic(paramClass.getModifiers())))
      {
        Class localClass = paramClass.getEnclosingClass();
        localObject = localClass;
      }
    }
    catch (NullPointerException localNullPointerException) {}catch (SecurityException localSecurityException) {}
    return (Class<?>)localObject;
  }
  
  public static Throwable getRootCause(Throwable paramThrowable)
  {
    while (paramThrowable.getCause() != null) {
      paramThrowable = paramThrowable.getCause();
    }
    return paramThrowable;
  }
  
  @Deprecated
  public static boolean hasGetterSignature(Method paramMethod)
  {
    boolean bool = false;
    if (Modifier.isStatic(paramMethod.getModifiers())) {}
    for (;;)
    {
      return bool;
      Class[] arrayOfClass = paramMethod.getParameterTypes();
      if (((arrayOfClass == null) || (arrayOfClass.length == 0)) && (Void.TYPE != paramMethod.getReturnType())) {
        bool = true;
      }
    }
  }
  
  public static boolean isBogusClass(Class<?> paramClass)
  {
    if ((paramClass == Void.class) || (paramClass == Void.TYPE) || (paramClass == NoClass.class)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isCollectionMapOrArray(Class<?> paramClass)
  {
    boolean bool = true;
    if (paramClass.isArray()) {}
    for (;;)
    {
      return bool;
      if ((!Collection.class.isAssignableFrom(paramClass)) && (!Map.class.isAssignableFrom(paramClass))) {
        bool = false;
      }
    }
  }
  
  public static boolean isConcrete(Class<?> paramClass)
  {
    if ((0x600 & paramClass.getModifiers()) == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isConcrete(Member paramMember)
  {
    if ((0x600 & paramMember.getModifiers()) == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isJacksonStdImpl(Class<?> paramClass)
  {
    if (paramClass.getAnnotation(JacksonStdImpl.class) != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isJacksonStdImpl(Object paramObject)
  {
    if ((paramObject != null) && (isJacksonStdImpl(paramObject.getClass()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static String isLocalType(Class<?> paramClass, boolean paramBoolean)
  {
    String str;
    try
    {
      if (paramClass.getEnclosingMethod() != null) {
        str = "local/anonymous";
      } else if ((!paramBoolean) && (paramClass.getEnclosingClass() != null) && (!Modifier.isStatic(paramClass.getModifiers()))) {
        str = "non-static member class";
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      str = null;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;) {}
    }
    return str;
  }
  
  public static boolean isNonStaticInnerClass(Class<?> paramClass)
  {
    if ((paramClass.getEnclosingClass() != null) && (!Modifier.isStatic(paramClass.getModifiers()))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isProxyType(Class<?> paramClass)
  {
    String str = paramClass.getName();
    if ((str.startsWith("net.sf.cglib.proxy.")) || (str.startsWith("org.hibernate.proxy."))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static void throwAsIAE(Throwable paramThrowable)
  {
    throwAsIAE(paramThrowable, paramThrowable.getMessage());
  }
  
  public static void throwAsIAE(Throwable paramThrowable, String paramString)
  {
    if ((paramThrowable instanceof RuntimeException)) {
      throw ((RuntimeException)paramThrowable);
    }
    if ((paramThrowable instanceof Error)) {
      throw ((Error)paramThrowable);
    }
    throw new IllegalArgumentException(paramString, paramThrowable);
  }
  
  public static void throwRootCause(Throwable paramThrowable)
    throws Exception
  {
    Throwable localThrowable = getRootCause(paramThrowable);
    if ((localThrowable instanceof Exception)) {
      throw ((Exception)localThrowable);
    }
    throw ((Error)localThrowable);
  }
  
  public static void unwrapAndThrowAsIAE(Throwable paramThrowable)
  {
    throwAsIAE(getRootCause(paramThrowable));
  }
  
  public static void unwrapAndThrowAsIAE(Throwable paramThrowable, String paramString)
  {
    throwAsIAE(getRootCause(paramThrowable), paramString);
  }
  
  public static Class<?> wrapperType(Class<?> paramClass)
  {
    Class localClass;
    if (paramClass == Integer.TYPE) {
      localClass = Integer.class;
    }
    for (;;)
    {
      return localClass;
      if (paramClass == Long.TYPE)
      {
        localClass = Long.class;
      }
      else if (paramClass == Boolean.TYPE)
      {
        localClass = Boolean.class;
      }
      else if (paramClass == Double.TYPE)
      {
        localClass = Double.class;
      }
      else if (paramClass == Float.TYPE)
      {
        localClass = Float.class;
      }
      else if (paramClass == Byte.TYPE)
      {
        localClass = Byte.class;
      }
      else if (paramClass == Short.TYPE)
      {
        localClass = Short.class;
      }
      else
      {
        if (paramClass != Character.TYPE) {
          break;
        }
        localClass = Character.class;
      }
    }
    throw new IllegalArgumentException("Class " + paramClass.getName() + " is not a primitive type");
  }
  
  private static class EnumTypeLocator
  {
    static final EnumTypeLocator instance = new EnumTypeLocator();
    private final Field enumMapTypeField = locateField(EnumMap.class, "elementType", Class.class);
    private final Field enumSetTypeField = locateField(EnumSet.class, "elementType", Class.class);
    
    private Object get(Object paramObject, Field paramField)
    {
      try
      {
        Object localObject = paramField.get(paramObject);
        return localObject;
      }
      catch (Exception localException)
      {
        throw new IllegalArgumentException(localException);
      }
    }
    
    private static Field locateField(Class<?> paramClass1, String paramString, Class<?> paramClass2)
    {
      Object localObject1 = null;
      Field[] arrayOfField = paramClass1.getDeclaredFields();
      int i = arrayOfField.length;
      int j = 0;
      int m;
      label66:
      Field localField1;
      Object localObject2;
      if (j < i)
      {
        Field localField2 = arrayOfField[j];
        if ((paramString.equals(localField2.getName())) && (localField2.getType() == paramClass2)) {
          localObject1 = localField2;
        }
      }
      else
      {
        if (localObject1 != null) {
          break label114;
        }
        int k = arrayOfField.length;
        m = 0;
        if (m >= k) {
          break label114;
        }
        localField1 = arrayOfField[m];
        if (localField1.getType() != paramClass2) {
          break label108;
        }
        if (localObject1 == null) {
          break label105;
        }
        localObject2 = null;
      }
      for (;;)
      {
        return (Field)localObject2;
        j++;
        break;
        label105:
        localObject1 = localField1;
        label108:
        m++;
        break label66;
        label114:
        if (localObject1 != null) {}
        try
        {
          ((Field)localObject1).setAccessible(true);
          localObject2 = localObject1;
        }
        catch (Throwable localThrowable)
        {
          for (;;) {}
        }
      }
    }
    
    public Class<? extends Enum<?>> enumTypeFor(EnumMap<?, ?> paramEnumMap)
    {
      if (this.enumMapTypeField != null) {
        return (Class)get(paramEnumMap, this.enumMapTypeField);
      }
      throw new IllegalStateException("Can not figure out type for EnumMap (odd JDK platform?)");
    }
    
    public Class<? extends Enum<?>> enumTypeFor(EnumSet<?> paramEnumSet)
    {
      if (this.enumSetTypeField != null) {
        return (Class)get(paramEnumSet, this.enumSetTypeField);
      }
      throw new IllegalStateException("Can not figure out type for EnumSet (odd JDK platform?)");
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/databind/util/ClassUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */