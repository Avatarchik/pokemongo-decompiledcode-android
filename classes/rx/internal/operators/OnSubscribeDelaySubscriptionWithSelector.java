package rx.internal.operators;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Func0;
import rx.observers.Subscribers;

public final class OnSubscribeDelaySubscriptionWithSelector<T, U>
  implements Observable.OnSubscribe<T>
{
  final Observable<? extends T> source;
  final Func0<? extends Observable<U>> subscriptionDelay;
  
  public OnSubscribeDelaySubscriptionWithSelector(Observable<? extends T> paramObservable, Func0<? extends Observable<U>> paramFunc0)
  {
    this.source = paramObservable;
    this.subscriptionDelay = paramFunc0;
  }
  
  public void call(final Subscriber<? super T> paramSubscriber)
  {
    try
    {
      ((Observable)this.subscriptionDelay.call()).take(1).unsafeSubscribe(new Subscriber()
      {
        public void onCompleted()
        {
          OnSubscribeDelaySubscriptionWithSelector.this.source.unsafeSubscribe(Subscribers.wrap(paramSubscriber));
        }
        
        public void onError(Throwable paramAnonymousThrowable)
        {
          paramSubscriber.onError(paramAnonymousThrowable);
        }
        
        public void onNext(U paramAnonymousU) {}
      });
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        paramSubscriber.onError(localThrowable);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OnSubscribeDelaySubscriptionWithSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */