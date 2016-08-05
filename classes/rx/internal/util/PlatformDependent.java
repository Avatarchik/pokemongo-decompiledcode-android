package rx.internal.util;

import java.security.AccessController;
import java.security.PrivilegedAction;

public final class PlatformDependent
{
  private static final boolean IS_ANDROID = ;
  
  static ClassLoader getSystemClassLoader()
  {
    if (System.getSecurityManager() == null) {}
    for (ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();; localClassLoader = (ClassLoader)AccessController.doPrivileged(new PrivilegedAction()
        {
          public ClassLoader run()
          {
            return ClassLoader.getSystemClassLoader();
          }
        })) {
      return localClassLoader;
    }
  }
  
  public static boolean isAndroid()
  {
    return IS_ANDROID;
  }
  
  private static boolean isAndroid0()
  {
    try
    {
      Class.forName("android.app.Application", false, getSystemClassLoader());
      bool = true;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        boolean bool = false;
      }
    }
    return bool;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/PlatformDependent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */