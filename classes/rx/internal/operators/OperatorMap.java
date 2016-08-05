package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;

public final class OperatorMap<T, R>
  implements Observable.Operator<R, T>
{
  private final Func1<? super T, ? extends R> transformer;
  
  public OperatorMap(Func1<? super T, ? extends R> paramFunc1)
  {
    this.transformer = paramFunc1;
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
          paramSubscriber.onNext(OperatorMap.this.transformer.call(paramAnonymousT));
          return;
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            Exceptions.throwIfFatal(localThrowable);
            onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramAnonymousT));
          }
        }
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */