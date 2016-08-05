package com.unity3d.player;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;

final class ReflectionHelper
{
  protected static boolean LOG = false;
  protected static final boolean LOGV;
  private static a[] a = new a['က'];
  
  private static float a(Class paramClass1, Class paramClass2)
  {
    if (paramClass1.equals(paramClass2)) {}
    for (float f = 1.0F;; f = 0.0F) {
      for (;;)
      {
        return f;
        if ((!paramClass1.isPrimitive()) && (!paramClass2.isPrimitive())) {
          try
          {
            Class localClass2 = paramClass1.asSubclass(paramClass2);
            if (localClass2 != null) {
              f = 0.5F;
            }
          }
          catch (ClassCastException localClassCastException1)
          {
            try
            {
              Class localClass1 = paramClass2.asSubclass(paramClass1);
              if (localClass1 != null) {
                f = 0.1F;
              }
            }
            catch (ClassCastException localClassCastException2) {}
          }
        }
      }
    }
  }
  
  private static float a(Class paramClass, Class[] paramArrayOfClass1, Class[] paramArrayOfClass2)
  {
    int i = 0;
    float f2;
    if (paramArrayOfClass2.length == 0) {
      f2 = 0.1F;
    }
    for (;;)
    {
      return f2;
      if (paramArrayOfClass1 == null) {}
      for (int j = 0;; j = paramArrayOfClass1.length)
      {
        if (j + 1 == paramArrayOfClass2.length) {
          break label43;
        }
        f2 = 0.0F;
        break;
      }
      label43:
      float f1 = 1.0F;
      if (paramArrayOfClass1 != null)
      {
        int k = paramArrayOfClass1.length;
        int n;
        for (int m = 0; i < k; m = n)
        {
          Class localClass = paramArrayOfClass1[i];
          n = m + 1;
          f1 *= a(localClass, paramArrayOfClass2[m]);
          i++;
        }
      }
      f2 = f1 * a(paramClass, paramArrayOfClass2[(-1 + paramArrayOfClass2.length)]);
    }
  }
  
  private static Class a(String paramString, int[] paramArrayOfInt)
  {
    char c;
    String str2;
    for (;;)
    {
      if (paramArrayOfInt[0] < paramString.length())
      {
        int i = paramArrayOfInt[0];
        paramArrayOfInt[0] = (i + 1);
        c = paramString.charAt(i);
        if ((c != '(') && (c != ')')) {
          if (c == 'L')
          {
            int j = paramString.indexOf(';', paramArrayOfInt[0]);
            if (j == -1) {
              break label268;
            }
            String str1 = paramString.substring(paramArrayOfInt[0], j);
            paramArrayOfInt[0] = (j + 1);
            str2 = str1.replace('/', '.');
          }
        }
      }
    }
    for (;;)
    {
      try
      {
        Class localClass2 = Class.forName(str2);
        localClass1 = localClass2;
        return localClass1;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        Class localClass1;
        label268:
        continue;
      }
      if (c == 'Z')
      {
        localClass1 = Boolean.TYPE;
      }
      else if (c == 'I')
      {
        localClass1 = Integer.TYPE;
      }
      else if (c == 'F')
      {
        localClass1 = Float.TYPE;
      }
      else if (c == 'V')
      {
        localClass1 = Void.TYPE;
      }
      else if (c == 'B')
      {
        localClass1 = Byte.TYPE;
      }
      else if (c == 'S')
      {
        localClass1 = Short.TYPE;
      }
      else if (c == 'J')
      {
        localClass1 = Long.TYPE;
      }
      else if (c == 'D')
      {
        localClass1 = Double.TYPE;
      }
      else if (c == '[')
      {
        localClass1 = Array.newInstance(a(paramString, paramArrayOfInt), 0).getClass();
      }
      else
      {
        m.Log(5, "! parseType; " + c + " is not known!");
        localClass1 = null;
      }
    }
  }
  
  private static void a(a parama, Member paramMember)
  {
    parama.a = paramMember;
    a[(parama.hashCode() & -1 + a.length)] = parama;
  }
  
  private static boolean a(a parama)
  {
    a locala = a[(parama.hashCode() & -1 + a.length)];
    if (!parama.equals(locala)) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      parama.a = locala.a;
    }
  }
  
  private static Class[] a(String paramString)
  {
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 0;
    ArrayList localArrayList = new ArrayList();
    while (arrayOfInt[0] < paramString.length())
    {
      Class localClass2 = a(paramString, arrayOfInt);
      if (localClass2 == null) {
        break;
      }
      localArrayList.add(localClass2);
    }
    Class[] arrayOfClass = new Class[localArrayList.size()];
    Iterator localIterator = localArrayList.iterator();
    int j;
    for (int i = 0; localIterator.hasNext(); i = j)
    {
      Class localClass1 = (Class)localIterator.next();
      j = i + 1;
      arrayOfClass[i] = localClass1;
    }
    return arrayOfClass;
  }
  
  protected static Constructor getConstructorID(Class paramClass, String paramString)
  {
    Object localObject1 = null;
    a locala = new a(paramClass, "", paramString);
    Object localObject3;
    float f1;
    int j;
    label94:
    Object localObject2;
    float f2;
    if (a(locala))
    {
      localObject3 = (Constructor)locala.a;
      if (localObject3 == null) {
        throw new NoSuchMethodError("<init>" + paramString + " in class " + paramClass.getName());
      }
    }
    else
    {
      Class[] arrayOfClass = a(paramString);
      f1 = 0.0F;
      Constructor[] arrayOfConstructor = paramClass.getConstructors();
      int i = arrayOfConstructor.length;
      j = 0;
      if (j < i)
      {
        localObject2 = arrayOfConstructor[j];
        f2 = a(Void.TYPE, ((Constructor)localObject2).getParameterTypes(), arrayOfClass);
        if (f2 <= f1) {
          break label174;
        }
        if (f2 == 1.0F) {
          break label158;
        }
      }
    }
    for (Object localObject4 = localObject2;; localObject4 = localObject1)
    {
      j++;
      localObject1 = localObject4;
      f1 = f2;
      break label94;
      localObject2 = localObject1;
      label158:
      a(locala, (Member)localObject2);
      localObject3 = localObject2;
      break;
      return (Constructor)localObject3;
      label174:
      f2 = f1;
    }
  }
  
  protected static Field getFieldID(Class paramClass, String paramString1, String paramString2, boolean paramBoolean)
  {
    a locala = new a(paramClass, paramString1, paramString2);
    String str;
    label49:
    float f1;
    label100:
    Object localObject2;
    Field localField;
    float f2;
    Object localObject3;
    if (a(locala))
    {
      localObject1 = (Field)locala.a;
      if (localObject1 != null) {
        break label280;
      }
      Object[] arrayOfObject = new Object[4];
      if (paramBoolean)
      {
        str = "non-static";
        arrayOfObject[0] = str;
        arrayOfObject[1] = paramString1;
        arrayOfObject[2] = paramString2;
        arrayOfObject[3] = paramClass.getName();
        throw new NoSuchFieldError(String.format("no %s field with name='%s' signature='%s' in class L%s;", arrayOfObject));
      }
    }
    else
    {
      Class[] arrayOfClass = a(paramString2);
      localObject1 = null;
      f1 = 0.0F;
      if (paramClass != null)
      {
        Field[] arrayOfField = paramClass.getDeclaredFields();
        int i = arrayOfField.length;
        int j = 0;
        localObject2 = localObject1;
        for (;;)
        {
          if (j < i)
          {
            localField = arrayOfField[j];
            if ((paramBoolean == Modifier.isStatic(localField.getModifiers())) && (localField.getName().compareTo(paramString1) == 0))
            {
              f2 = a(localField.getType(), null, arrayOfClass);
              if (f2 > f1)
              {
                if (f2 != 1.0F)
                {
                  localObject3 = localField;
                  label192:
                  j++;
                  localObject2 = localObject3;
                  f1 = f2;
                  continue;
                }
                f1 = f2;
              }
            }
          }
        }
      }
    }
    for (Object localObject1 = localField;; localObject1 = localObject2)
    {
      if ((f1 != 1.0F) && (!paramClass.isPrimitive()) && (!paramClass.isInterface()) && (!paramClass.equals(Object.class)) && (!paramClass.equals(Void.TYPE)))
      {
        paramClass = paramClass.getSuperclass();
        break label100;
      }
      a(locala, (Member)localObject1);
      break;
      str = "static";
      break label49;
      label280:
      return (Field)localObject1;
      f2 = f1;
      localObject3 = localObject2;
      break label192;
    }
  }
  
  protected static Method getMethodID(Class paramClass, String paramString1, String paramString2, boolean paramBoolean)
  {
    a locala = new a(paramClass, paramString1, paramString2);
    String str;
    label49:
    float f1;
    label101:
    Object localObject2;
    Method localMethod;
    float f2;
    Object localObject3;
    if (a(locala))
    {
      localObject1 = (Method)locala.a;
      if (localObject1 != null) {
        break label285;
      }
      Object[] arrayOfObject = new Object[4];
      if (paramBoolean)
      {
        str = "non-static";
        arrayOfObject[0] = str;
        arrayOfObject[1] = paramString1;
        arrayOfObject[2] = paramString2;
        arrayOfObject[3] = paramClass.getName();
        throw new NoSuchMethodError(String.format("no %s method with name='%s' signature='%s' in class L%s;", arrayOfObject));
      }
    }
    else
    {
      Class[] arrayOfClass = a(paramString2);
      localObject1 = null;
      f1 = 0.0F;
      if (paramClass != null)
      {
        Method[] arrayOfMethod = paramClass.getDeclaredMethods();
        int i = arrayOfMethod.length;
        int j = 0;
        localObject2 = localObject1;
        for (;;)
        {
          if (j < i)
          {
            localMethod = arrayOfMethod[j];
            if ((paramBoolean == Modifier.isStatic(localMethod.getModifiers())) && (localMethod.getName().compareTo(paramString1) == 0))
            {
              f2 = a(localMethod.getReturnType(), localMethod.getParameterTypes(), arrayOfClass);
              if (f2 > f1)
              {
                if (f2 != 1.0F)
                {
                  localObject3 = localMethod;
                  label197:
                  j++;
                  localObject2 = localObject3;
                  f1 = f2;
                  continue;
                }
                f1 = f2;
              }
            }
          }
        }
      }
    }
    for (Object localObject1 = localMethod;; localObject1 = localObject2)
    {
      if ((f1 != 1.0F) && (!paramClass.isPrimitive()) && (!paramClass.isInterface()) && (!paramClass.equals(Object.class)) && (!paramClass.equals(Void.TYPE)))
      {
        paramClass = paramClass.getSuperclass();
        break label101;
      }
      a(locala, (Member)localObject1);
      break;
      str = "static";
      break label49;
      label285:
      return (Method)localObject1;
      f2 = f1;
      localObject3 = localObject2;
      break label197;
    }
  }
  
  private static native void nativeProxyFinalize(int paramInt);
  
  private static native Object nativeProxyInvoke(int paramInt, String paramString, Object[] paramArrayOfObject);
  
  protected static Object newProxyInstance(int paramInt, Class paramClass)
  {
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = paramClass;
    return newProxyInstance(paramInt, arrayOfClass);
  }
  
  protected static Object newProxyInstance(int paramInt, final Class[] paramArrayOfClass)
  {
    Proxy.newProxyInstance(ReflectionHelper.class.getClassLoader(), paramArrayOfClass, new InvocationHandler()
    {
      protected final void finalize()
      {
        try
        {
          ReflectionHelper.a(this.a);
          return;
        }
        finally
        {
          super.finalize();
        }
      }
      
      public final Object invoke(Object paramAnonymousObject, Method paramAnonymousMethod, Object[] paramAnonymousArrayOfObject)
      {
        return ReflectionHelper.a(this.a, paramAnonymousMethod.getName(), paramAnonymousArrayOfObject);
      }
    });
  }
  
  private static class a
  {
    public volatile Member a;
    private final Class b;
    private final String c;
    private final String d;
    private final int e;
    
    a(Class paramClass, String paramString1, String paramString2)
    {
      this.b = paramClass;
      this.c = paramString1;
      this.d = paramString2;
      this.e = (31 * (31 * (527 + this.b.hashCode()) + this.c.hashCode()) + this.d.hashCode());
    }
    
    public final boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (paramObject == this) {}
      for (;;)
      {
        return bool;
        if ((paramObject instanceof a))
        {
          a locala = (a)paramObject;
          if ((this.e != locala.e) || (!this.d.equals(locala.d)) || (!this.c.equals(locala.c)) || (!this.b.equals(locala.b))) {
            bool = false;
          }
        }
        else
        {
          bool = false;
        }
      }
    }
    
    public final int hashCode()
    {
      return this.e;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/unity3d/player/ReflectionHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */