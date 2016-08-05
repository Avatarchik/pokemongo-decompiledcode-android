package rx.internal.operators;

import rx.Observable;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class OperatorTimeoutWithSelector<T, U, V>
  extends OperatorTimeoutBase<T>
{
  public OperatorTimeoutWithSelector(Func0<? extends Observable<U>> paramFunc0, Func1<? super T, ? extends Observable<V>> paramFunc1, Observable<? extends T> paramObservable)
  {
    super(new OperatorTimeoutBase.FirstTimeoutStub()new OperatorTimeoutBase.TimeoutStub
    {
      public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber<T> paramAnonymousTimeoutSubscriber, final Long paramAnonymousLong, Scheduler.Worker paramAnonymousWorker)
      {
        if (OperatorTimeoutWithSelector.this != null) {}
        for (;;)
        {
          try
          {
            Observable localObservable = (Observable)OperatorTimeoutWithSelector.this.call();
            localSubscription = localObservable.unsafeSubscribe(new Subscriber()
            {
              public void onCompleted()
              {
                paramAnonymousTimeoutSubscriber.onTimeout(paramAnonymousLong.longValue());
              }
              
              public void onError(Throwable paramAnonymous2Throwable)
              {
                paramAnonymousTimeoutSubscriber.onError(paramAnonymous2Throwable);
              }
              
              public void onNext(U paramAnonymous2U)
              {
                paramAnonymousTimeoutSubscriber.onTimeout(paramAnonymousLong.longValue());
              }
            });
          }
          catch (Throwable localThrowable)
          {
            Exceptions.throwIfFatal(localThrowable);
            paramAnonymousTimeoutSubscriber.onError(localThrowable);
            localSubscription = Subscriptions.unsubscribed();
            continue;
          }
          return localSubscription;
          Subscription localSubscription = Subscriptions.unsubscribed();
        }
      }
    }, new OperatorTimeoutBase.TimeoutStub()
    {
      public Subscription call(final OperatorTimeoutBase.TimeoutSubscriber<T> paramAnonymousTimeoutSubscriber, final Long paramAnonymousLong, T paramAnonymousT, Scheduler.Worker paramAnonymousWorker)
      {
        try
        {
          Observable localObservable = (Observable)OperatorTimeoutWithSelector.this.call(paramAnonymousT);
          localSubscription = localObservable.unsafeSubscribe(new Subscriber()
          {
            public void onCompleted()
            {
              paramAnonymousTimeoutSubscriber.onTimeout(paramAnonymousLong.longValue());
            }
            
            public void onError(Throwable paramAnonymous2Throwable)
            {
              paramAnonymousTimeoutSubscriber.onError(paramAnonymous2Throwable);
            }
            
            public void onNext(V paramAnonymous2V)
            {
              paramAnonymousTimeoutSubscriber.onTimeout(paramAnonymousLong.longValue());
            }
          });
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            Exceptions.throwIfFatal(localThrowable);
            paramAnonymousTimeoutSubscriber.onError(localThrowable);
            Subscription localSubscription = Subscriptions.unsubscribed();
          }
        }
        return localSubscription;
      }
    }, paramObservable, Schedulers.immediate());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorTimeoutWithSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */