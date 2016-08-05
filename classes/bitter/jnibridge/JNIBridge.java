package bitter.jnibridge;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JNIBridge
{
  static native void delete(long paramLong);
  
  static void disableInterfaceProxy(Object paramObject)
  {
    ((a)Proxy.getInvocationHandler(paramObject)).a();
  }
  
  static native Object invoke(long paramLong, Class paramClass, Method paramMethod, Object[] paramArrayOfObject);
  
  static Object newInterfaceProxy(long paramLong, Class[] paramArrayOfClass)
  {
    return Proxy.newProxyInstance(JNIBridge.class.getClassLoader(), paramArrayOfClass, new a(paramLong));
  }
  
  private static class a
    implements InvocationHandler
  {
    private Object a = new Object[0];
    private long b;
    
    public a(long paramLong)
    {
      this.b = paramLong;
    }
    
    public final void a()
    {
      synchronized (this.a)
      {
        this.b = 0L;
        return;
      }
    }
    
    public final void finalize()
    {
      synchronized (this.a)
      {
        if (this.b != 0L) {
          JNIBridge.delete(this.b);
        }
      }
    }
    
    public final Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
    {
      Object localObject3;
      synchronized (this.a)
      {
        if (this.b == 0L) {
          localObject3 = null;
        } else {
          localObject3 = JNIBridge.invoke(this.b, paramMethod.getDeclaringClass(), paramMethod, paramArrayOfObject);
        }
      }
      return localObject3;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/bitter/jnibridge/JNIBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */