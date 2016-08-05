package rx.plugins;

import rx.annotations.Experimental;
import rx.exceptions.Exceptions;

public abstract class RxJavaErrorHandler
{
  protected static final String ERROR_IN_RENDERING_SUFFIX = ".errorRendering";
  
  public void handleError(Throwable paramThrowable) {}
  
  @Experimental
  public final String handleOnNextValueRendering(Object paramObject)
  {
    try
    {
      String str2 = render(paramObject);
      str1 = str2;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        Thread.currentThread().interrupt();
        String str1 = paramObject.getClass().getName() + ".errorRendering";
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Exceptions.throwIfFatal(localThrowable);
      }
    }
    return str1;
  }
  
  @Experimental
  protected String render(Object paramObject)
    throws InterruptedException
  {
    return null;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/plugins/RxJavaErrorHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */