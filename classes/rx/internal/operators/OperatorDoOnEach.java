package rx.internal.operators;

import rx.Observable.Operator;
import rx.Observer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;

public class OperatorDoOnEach<T>
  implements Observable.Operator<T, T>
{
  private final Observer<? super T> doOnEachObserver;
  
  public OperatorDoOnEach(Observer<? super T> paramObserver)
  {
    this.doOnEachObserver = paramObserver;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
      private boolean done = false;
      
      public void onCompleted()
      {
        if (this.done) {}
        for (;;)
        {
          return;
          try
          {
            OperatorDoOnEach.this.doOnEachObserver.onCompleted();
            this.done = true;
            paramSubscriber.onCompleted();
          }
          catch (Throwable localThrowable)
          {
            onError(localThrowable);
          }
        }
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        Exceptions.throwIfFatal(paramAnonymousThrowable);
        if (this.done) {}
        for (;;)
        {
          return;
          this.done = true;
          try
          {
            OperatorDoOnEach.this.doOnEachObserver.onError(paramAnonymousThrowable);
            paramSubscriber.onError(paramAnonymousThrowable);
          }
          catch (Throwable localThrowable)
          {
            paramSubscriber.onError(localThrowable);
          }
        }
      }
      
      public void onNext(T paramAnonymousT)
      {
        if (this.done) {}
        for (;;)
        {
          return;
          try
          {
            OperatorDoOnEach.this.doOnEachObserver.onNext(paramAnonymousT);
            paramSubscriber.onNext(paramAnonymousT);
          }
          catch (Throwable localThrowable)
          {
            onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramAnonymousT));
          }
        }
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorDoOnEach.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */