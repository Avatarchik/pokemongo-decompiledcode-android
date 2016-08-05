package rx.internal.operators;

import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public class OperatorUnsubscribeOn<T>
  implements Observable.Operator<T, T>
{
  private final Scheduler scheduler;
  
  public OperatorUnsubscribeOn(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    final Subscriber local1 = new Subscriber()
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
        paramSubscriber.onNext(paramAnonymousT);
      }
    };
    paramSubscriber.add(Subscriptions.create(new Action0()
    {
      public void call()
      {
        final Scheduler.Worker localWorker = OperatorUnsubscribeOn.this.scheduler.createWorker();
        localWorker.schedule(new Action0()
        {
          public void call()
          {
            OperatorUnsubscribeOn.2.this.val$parent.unsubscribe();
            localWorker.unsubscribe();
          }
        });
      }
    }));
    return local1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorUnsubscribeOn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */