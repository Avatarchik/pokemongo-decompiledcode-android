package crittercism.android;

public abstract class di
  implements Runnable
{
  public abstract void a();
  
  public final void run()
  {
    try
    {
      a();
      return;
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        dx.a(localThrowable);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/crittercism/android/di.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */