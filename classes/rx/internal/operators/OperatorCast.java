package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.OnErrorThrowable;

public class OperatorCast<T, R>
  implements Observable.Operator<R, T>
{
  private final Class<R> castClass;
  
  public OperatorCast(Class<R> paramClass)
  {
    this.castClass = paramClass;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super R> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
      public void onCompleted()
      {
        paramSubscriber.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        try
        {
          paramSubscriber.onNext(OperatorCast.this.castClass.cast(paramAnonymousT));
          return;
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramAnonymousT));
          }
        }
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorCast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */