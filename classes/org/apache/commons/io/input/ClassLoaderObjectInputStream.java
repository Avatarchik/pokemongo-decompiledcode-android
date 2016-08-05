package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.StreamCorruptedException;
import java.lang.reflect.Proxy;

public class ClassLoaderObjectInputStream
  extends ObjectInputStream
{
  private final ClassLoader classLoader;
  
  public ClassLoaderObjectInputStream(ClassLoader paramClassLoader, InputStream paramInputStream)
    throws IOException, StreamCorruptedException
  {
    super(paramInputStream);
    this.classLoader = paramClassLoader;
  }
  
  protected Class<?> resolveClass(ObjectStreamClass paramObjectStreamClass)
    throws IOException, ClassNotFoundException
  {
    Class localClass = Class.forName(paramObjectStreamClass.getName(), false, this.classLoader);
    if (localClass != null) {}
    for (;;)
    {
      return localClass;
      localClass = super.resolveClass(paramObjectStreamClass);
    }
  }
  
  protected Class<?> resolveProxyClass(String[] paramArrayOfString)
    throws IOException, ClassNotFoundException
  {
    Class[] arrayOfClass = new Class[paramArrayOfString.length];
    for (int i = 0; i < paramArrayOfString.length; i++) {
      arrayOfClass[i] = Class.forName(paramArrayOfString[i], false, this.classLoader);
    }
    try
    {
      Class localClass2 = Proxy.getProxyClass(this.classLoader, arrayOfClass);
      localClass1 = localClass2;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;)
      {
        Class localClass1 = super.resolveProxyClass(paramArrayOfString);
      }
    }
    return localClass1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/input/ClassLoaderObjectInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */