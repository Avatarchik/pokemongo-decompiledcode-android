package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class UnsafeAllocator
{
  public static UnsafeAllocator create()
  {
    try
    {
      Class localClass = Class.forName("sun.misc.Unsafe");
      Field localField = localClass.getDeclaredField("theUnsafe");
      localField.setAccessible(true);
      final Object localObject2 = localField.get(null);
      Class[] arrayOfClass4 = new Class[1];
      arrayOfClass4[0] = Class.class;
      localObject1 = new UnsafeAllocator()
      {
        public <T> T newInstance(Class<T> paramAnonymousClass)
          throws Exception
        {
          Method localMethod = UnsafeAllocator.this;
          Object localObject = localObject2;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = paramAnonymousClass;
          return (T)localMethod.invoke(localObject, arrayOfObject);
        }
      };
      return (UnsafeAllocator)localObject1;
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        try
        {
          Class[] arrayOfClass3 = new Class[2];
          arrayOfClass3[0] = Class.class;
          arrayOfClass3[1] = Class.class;
          Method localMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", arrayOfClass3);
          localMethod3.setAccessible(true);
          localObject1 = new UnsafeAllocator()
          {
            public <T> T newInstance(Class<T> paramAnonymousClass)
              throws Exception
            {
              Method localMethod = UnsafeAllocator.this;
              Object[] arrayOfObject = new Object[2];
              arrayOfObject[0] = paramAnonymousClass;
              arrayOfObject[1] = Object.class;
              return (T)localMethod.invoke(null, arrayOfObject);
            }
          };
        }
        catch (Exception localException2)
        {
          try
          {
            Class[] arrayOfClass1 = new Class[1];
            arrayOfClass1[0] = Class.class;
            Method localMethod1 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", arrayOfClass1);
            localMethod1.setAccessible(true);
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = Object.class;
            final int i = ((Integer)localMethod1.invoke(null, arrayOfObject)).intValue();
            Class[] arrayOfClass2 = new Class[2];
            arrayOfClass2[0] = Class.class;
            arrayOfClass2[1] = Integer.TYPE;
            Method localMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", arrayOfClass2);
            localMethod2.setAccessible(true);
            localObject1 = new UnsafeAllocator()
            {
              public <T> T newInstance(Class<T> paramAnonymousClass)
                throws Exception
              {
                Method localMethod = UnsafeAllocator.this;
                Object[] arrayOfObject = new Object[2];
                arrayOfObject[0] = paramAnonymousClass;
                arrayOfObject[1] = Integer.valueOf(i);
                return (T)localMethod.invoke(null, arrayOfObject);
              }
            };
          }
          catch (Exception localException3)
          {
            Object localObject1 = new UnsafeAllocator()
            {
              public <T> T newInstance(Class<T> paramAnonymousClass)
              {
                throw new UnsupportedOperationException("Cannot allocate " + paramAnonymousClass);
              }
            };
          }
        }
      }
    }
  }
  
  public abstract <T> T newInstance(Class<T> paramClass)
    throws Exception;
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/gson/internal/UnsafeAllocator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */