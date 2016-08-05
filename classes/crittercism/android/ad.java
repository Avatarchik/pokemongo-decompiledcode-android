package crittercism.android;

import java.net.SocketImpl;
import java.net.SocketImplFactory;

public final class ad
  implements SocketImplFactory
{
  private Class a;
  private SocketImplFactory b;
  private e c;
  private d d;
  
  public ad(Class paramClass, e parame, d paramd)
  {
    this.c = parame;
    this.d = paramd;
    this.a = paramClass;
    Class localClass = this.a;
    if (localClass == null) {
      throw new cl("Class was null");
    }
    try
    {
      localClass.newInstance();
      return;
    }
    catch (Throwable localThrowable)
    {
      throw new cl("Unable to create new instance", localThrowable);
    }
  }
  
  public ad(SocketImplFactory paramSocketImplFactory, e parame, d paramd)
  {
    this.c = parame;
    this.d = paramd;
    this.b = paramSocketImplFactory;
    SocketImplFactory localSocketImplFactory = this.b;
    if (localSocketImplFactory == null) {
      throw new cl("Factory was null");
    }
    try
    {
      if (localSocketImplFactory.createSocketImpl() == null) {
        throw new cl("Factory does not work");
      }
    }
    catch (Throwable localThrowable)
    {
      throw new cl("Factory does not work", localThrowable);
    }
  }
  
  public final SocketImpl createSocketImpl()
  {
    Object localObject1 = null;
    if (this.b != null)
    {
      localObject1 = this.b.createSocketImpl();
      if (localObject1 == null) {
        break label85;
      }
    }
    label85:
    for (Object localObject2 = new ac(this.c, this.d, (SocketImpl)localObject1);; localObject2 = localObject1)
    {
      return (SocketImpl)localObject2;
      try
      {
        SocketImpl localSocketImpl = (SocketImpl)this.a.newInstance();
        localObject1 = localSocketImpl;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localIllegalAccessException.printStackTrace();
      }
      catch (InstantiationException localInstantiationException)
      {
        localInstantiationException.printStackTrace();
      }
      break;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/ad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */