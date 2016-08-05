package crittercism.android;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

public final class v
  implements URLStreamHandlerFactory
{
  private static final Object a = new Object();
  private static v b;
  private LinkedList c = new LinkedList();
  private boolean d = false;
  private boolean e = false;
  
  public v(a parama, e parame, d paramd)
  {
    if ((parama == a.c) || (parama == a.a)) {
      this.c.add(new o(parame, paramd));
    }
    if ((parama == a.c) || (parama == a.b)) {
      this.c.add(new q(parame, paramd));
    }
  }
  
  public static v a()
  {
    return b;
  }
  
  /* Error */
  /**
   * @deprecated
   */
  private boolean d()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: getstatic 24	crittercism/android/v:a	Ljava/lang/Object;
    //   7: astore_3
    //   8: aload_3
    //   9: monitorenter
    //   10: getstatic 56	crittercism/android/v:b	Lcrittercism/android/v;
    //   13: aload_0
    //   14: if_acmpeq +14 -> 28
    //   17: aload_0
    //   18: getfield 32	crittercism/android/v:d	Z
    //   21: pop
    //   22: aload_3
    //   23: monitorexit
    //   24: aload_0
    //   25: monitorexit
    //   26: iload_1
    //   27: ireturn
    //   28: aload_0
    //   29: getfield 32	crittercism/android/v:d	Z
    //   32: ifeq +18 -> 50
    //   35: invokestatic 59	crittercism/android/v:e	()Z
    //   38: ifeq +12 -> 50
    //   41: aload_0
    //   42: iconst_0
    //   43: putfield 32	crittercism/android/v:d	Z
    //   46: aconst_null
    //   47: putstatic 56	crittercism/android/v:b	Lcrittercism/android/v;
    //   50: aload_3
    //   51: monitorexit
    //   52: aload_0
    //   53: getfield 32	crittercism/android/v:d	Z
    //   56: istore_1
    //   57: goto -33 -> 24
    //   60: astore 4
    //   62: aload_3
    //   63: monitorexit
    //   64: aload 4
    //   66: athrow
    //   67: astore_2
    //   68: aload_0
    //   69: monitorexit
    //   70: aload_2
    //   71: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	72	0	this	v
    //   1	56	1	bool	boolean
    //   67	4	2	localObject1	Object
    //   60	5	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   10	24	60	finally
    //   28	52	60	finally
    //   4	10	67	finally
    //   52	67	67	finally
  }
  
  private static boolean e()
  {
    boolean bool = true;
    Field[] arrayOfField = URL.class.getDeclaredFields();
    int i = arrayOfField.length;
    int j = 0;
    Field localField;
    if (j < i)
    {
      localField = arrayOfField[j];
      if (!URLStreamHandlerFactory.class.isAssignableFrom(localField.getType())) {}
    }
    for (;;)
    {
      try
      {
        localField.setAccessible(true);
        localField.set(null, null);
        localField.setAccessible(false);
        URL.setURLStreamHandlerFactory(null);
        return bool;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        dx.c();
        j++;
      }
      catch (SecurityException localSecurityException)
      {
        dx.c();
        continue;
      }
      catch (Throwable localThrowable)
      {
        dx.c();
        continue;
      }
      bool = false;
    }
  }
  
  private static boolean f()
  {
    Field[] arrayOfField = URL.class.getDeclaredFields();
    int i = arrayOfField.length;
    int j = 0;
    Field localField;
    if (j < i)
    {
      localField = arrayOfField[j];
      if (Hashtable.class.isAssignableFrom(localField.getType()))
      {
        ParameterizedType localParameterizedType = (ParameterizedType)localField.getGenericType();
        Class localClass1 = (Class)localParameterizedType.getActualTypeArguments()[0];
        Class localClass2 = (Class)localParameterizedType.getActualTypeArguments()[1];
        if ((!String.class.isAssignableFrom(localClass1)) || (!URLStreamHandler.class.isAssignableFrom(localClass2))) {}
      }
    }
    for (;;)
    {
      try
      {
        localField.setAccessible(true);
        Hashtable localHashtable = (Hashtable)localField.get(null);
        if (localHashtable != null) {
          localHashtable.clear();
        }
        localField.setAccessible(false);
        bool = true;
        return bool;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        dx.c();
        j++;
      }
      catch (SecurityException localSecurityException)
      {
        dx.c();
        continue;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        dx.c();
        continue;
      }
      boolean bool = false;
    }
  }
  
  public final boolean b()
  {
    for (boolean bool1 = true;; bool1 = false)
    {
      synchronized (a)
      {
        if (b != null)
        {
          if (b != this) {
            continue;
          }
          break label77;
        }
        if (!this.d)
        {
          boolean bool2 = this.e;
          if (bool2) {}
        }
      }
      try
      {
        URL.setURLStreamHandlerFactory(this);
        this.d = true;
        b = this;
        bool1 = this.d;
        break label77;
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
      label77:
      return bool1;
    }
  }
  
  /**
   * @deprecated
   */
  public final boolean c()
  {
    boolean bool1 = false;
    for (;;)
    {
      try
      {
        d();
        if (this.d)
        {
          this.e = true;
          bool2 = f();
          boolean bool3 = this.d;
          if ((!bool3) || (bool2)) {
            bool1 = true;
          }
          return bool1;
        }
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
      boolean bool2 = false;
    }
  }
  
  public final URLStreamHandler createURLStreamHandler(String paramString)
  {
    for (;;)
    {
      try
      {
        if (this.e) {
          continue;
        }
        Iterator localIterator = this.c.iterator();
        if (!localIterator.hasNext()) {
          continue;
        }
        localm = (m)localIterator.next();
        boolean bool = localm.a().equals(paramString);
        if (!bool) {
          continue;
        }
      }
      catch (ThreadDeath localThreadDeath)
      {
        throw localThreadDeath;
      }
      catch (Throwable localThrowable)
      {
        this.e = true;
        dx.a(localThrowable);
        m localm = null;
        continue;
      }
      return localm;
      localm = null;
    }
  }
  
  public static enum a
  {
    static
    {
      a[] arrayOfa = new a[3];
      arrayOfa[0] = a;
      arrayOfa[1] = b;
      arrayOfa[2] = c;
      d = arrayOfa;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/v.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */