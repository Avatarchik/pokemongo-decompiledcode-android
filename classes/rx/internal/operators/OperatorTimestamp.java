package rx.internal.operators;

import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Timestamped;

public final class OperatorTimestamp<T>
  implements Observable.Operator<Timestamped<T>, T>
{
  private final Scheduler scheduler;
  
  public OperatorTimestamp(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super Timestamped<T>> paramSubscriber)
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
        paramSubscriber.onNext(new Timestamped(OperatorTimestamp.this.scheduler.now(), paramAnonymousT));
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorTimestamp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */