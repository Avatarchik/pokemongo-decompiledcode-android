package com.crittercism.app;

import crittercism.android.az;
import crittercism.android.be;
import crittercism.android.bg;
import crittercism.android.dx;

public abstract class Transaction
{
  public az a;
  
  public static Transaction a(String paramString)
  {
    if ((paramString == null) || (paramString != null)) {}
    try
    {
      if (paramString.length() == 0)
      {
        dx.b("Transaction was created with a null/zero-length name. Returning no-op transaction", new IllegalStateException("Transaction created with null/zero-length name"));
        localObject = new be();
      }
      else
      {
        localaz = az.A();
        if (localaz.b) {
          if (localaz.B()) {
            localObject = new be();
          }
        }
      }
    }
    catch (ThreadDeath localThreadDeath)
    {
      az localaz;
      throw localThreadDeath;
      localObject = new bg(localaz, paramString);
    }
    catch (Throwable localThrowable)
    {
      dx.a(localThrowable);
      localObject = new be();
    }
    dx.b("Transaction was created before Crittercism.initialize() was called. Returning no-op transaction", new IllegalStateException("Transaction created before Crittercism.initialize()"));
    Object localObject = new be();
    return (Transaction)localObject;
  }
  
  public abstract void a();
  
  public abstract void a(int paramInt);
  
  public abstract void b();
  
  public abstract void c();
  
  public abstract int d();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/crittercism/app/Transaction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */