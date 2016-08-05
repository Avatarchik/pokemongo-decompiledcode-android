package rx.observers;

import java.io.PrintStream;
import java.util.Arrays;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

public class SafeSubscriber<T>
  extends Subscriber<T>
{
  private final Subscriber<? super T> actual;
  boolean done = false;
  
  public SafeSubscriber(Subscriber<? super T> paramSubscriber)
  {
    super(paramSubscriber);
    this.actual = paramSubscriber;
  }
  
  private void handlePluginException(Throwable paramThrowable)
  {
    System.err.println("RxJavaErrorHandler threw an Exception. It shouldn't. => " + paramThrowable.getMessage());
    paramThrowable.printStackTrace();
  }
  
  protected void _onError(Throwable paramThrowable)
  {
    try
    {
      RxJavaPlugins.getInstance().getErrorHandler().handleError(paramThrowable);
    }
    catch (Throwable localThrowable3)
    {
      for (;;)
      {
        try
        {
          this.actual.onError(paramThrowable);
        }
        catch (Throwable localThrowable2)
        {
          if ((localThrowable2 instanceof OnErrorNotImplementedException))
          {
            try
            {
              unsubscribe();
              throw ((OnErrorNotImplementedException)localThrowable2);
            }
            catch (Throwable localThrowable6) {}
            try
            {
              RxJavaPlugins.getInstance().getErrorHandler().handleError(localThrowable6);
              arrayOfThrowable3 = new Throwable[2];
              arrayOfThrowable3[0] = paramThrowable;
              arrayOfThrowable3[1] = localThrowable6;
              throw new RuntimeException("Observer.onError not implemented and error while unsubscribing.", new CompositeException(Arrays.asList(arrayOfThrowable3)));
            }
            catch (Throwable localThrowable7)
            {
              handlePluginException(localThrowable7);
              continue;
            }
          }
          try
          {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(localThrowable2);
          }
          catch (Throwable localThrowable3)
          {
            try
            {
              unsubscribe();
              arrayOfThrowable2 = new Throwable[2];
              arrayOfThrowable2[0] = paramThrowable;
              arrayOfThrowable2[1] = localThrowable2;
              throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError", new CompositeException(Arrays.asList(arrayOfThrowable2)));
            }
            catch (Throwable localThrowable4)
            {
              try
              {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(localThrowable4);
                arrayOfThrowable1 = new Throwable[3];
                arrayOfThrowable1[0] = paramThrowable;
                arrayOfThrowable1[1] = localThrowable2;
                arrayOfThrowable1[2] = localThrowable4;
                throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(arrayOfThrowable1)));
              }
              catch (Throwable localThrowable5)
              {
                for (;;)
                {
                  handlePluginException(localThrowable5);
                }
              }
            }
            localThrowable3 = localThrowable3;
            handlePluginException(localThrowable3);
            continue;
          }
        }
        try
        {
          unsubscribe();
          return;
        }
        catch (RuntimeException localRuntimeException) {}
        localThrowable1 = localThrowable1;
        handlePluginException(localThrowable1);
      }
    }
    try
    {
      Throwable[] arrayOfThrowable3;
      Throwable[] arrayOfThrowable2;
      Throwable[] arrayOfThrowable1;
      RxJavaPlugins.getInstance().getErrorHandler().handleError(localRuntimeException);
      throw new OnErrorFailedException(localRuntimeException);
    }
    catch (Throwable localThrowable8)
    {
      for (;;)
      {
        handlePluginException(localThrowable8);
      }
    }
  }
  
  public Subscriber<? super T> getActual()
  {
    return this.actual;
  }
  
  public void onCompleted()
  {
    if (!this.done) {
      this.done = true;
    }
    try
    {
      this.actual.onCompleted();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Exceptions.throwIfFatal(localThrowable);
        _onError(localThrowable);
        unsubscribe();
      }
    }
    finally
    {
      unsubscribe();
    }
  }
  
  public void onError(Throwable paramThrowable)
  {
    Exceptions.throwIfFatal(paramThrowable);
    if (!this.done)
    {
      this.done = true;
      _onError(paramThrowable);
    }
  }
  
  public void onNext(T paramT)
  {
    try
    {
      if (!this.done) {
        this.actual.onNext(paramT);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        Exceptions.throwIfFatal(localThrowable);
        onError(localThrowable);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/observers/SafeSubscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */