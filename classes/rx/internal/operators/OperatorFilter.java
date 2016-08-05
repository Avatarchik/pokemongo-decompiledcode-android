package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;

public final class OperatorFilter<T>
  implements Observable.Operator<T, T>
{
  private final Func1<? super T, Boolean> predicate;
  
  public OperatorFilter(Func1<? super T, Boolean> paramFunc1)
  {
    this.predicate = paramFunc1;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
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
          if (((Boolean)OperatorFilter.this.predicate.call(paramAnonymousT)).booleanValue()) {
            paramSubscriber.onNext(paramAnonymousT);
          } else {
            request(1L);
          }
        }
        catch (Throwable localThrowable)
        {
          paramSubscriber.onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramAnonymousT));
        }
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */