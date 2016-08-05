package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;

public class OperatorSubscribeOn<T>
  implements Observable.Operator<T, Observable<T>>
{
  private final Scheduler scheduler;
  
  public OperatorSubscribeOn(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super Observable<T>> call(final Subscriber<? super T> paramSubscriber)
  {
    final Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    new Subscriber(paramSubscriber)
    {
      public void onCompleted() {}
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(final Observable<T> paramAnonymousObservable)
      {
        localWorker.schedule(new Action0()
        {
          public void call()
          {
            final Thread localThread = Thread.currentThread();
            paramAnonymousObservable.unsafeSubscribe(new Subscriber(OperatorSubscribeOn.1.this.val$subscriber)
            {
              public void onCompleted()
              {
                OperatorSubscribeOn.1.this.val$subscriber.onCompleted();
              }
              
              public void onError(Throwable paramAnonymous3Throwable)
              {
                OperatorSubscribeOn.1.this.val$subscriber.onError(paramAnonymous3Throwable);
              }
              
              public void onNext(T paramAnonymous3T)
              {
                OperatorSubscribeOn.1.this.val$subscriber.onNext(paramAnonymous3T);
              }
              
              public void setProducer(final Producer paramAnonymous3Producer)
              {
                OperatorSubscribeOn.1.this.val$subscriber.setProducer(new Producer()
                {
                  public void request(final long paramAnonymous4Long)
                  {
                    if (Thread.currentThread() == OperatorSubscribeOn.1.1.1.this.val$t) {
                      paramAnonymous3Producer.request(paramAnonymous4Long);
                    }
                    for (;;)
                    {
                      return;
                      OperatorSubscribeOn.1.this.val$inner.schedule(new Action0()
                      {
                        public void call()
                        {
                          OperatorSubscribeOn.1.1.1.1.this.val$producer.request(paramAnonymous4Long);
                        }
                      });
                    }
                  }
                });
              }
            });
          }
        });
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorSubscribeOn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */