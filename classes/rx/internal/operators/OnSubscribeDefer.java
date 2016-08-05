package rx.internal.operators;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Func0;
import rx.observers.Subscribers;

public final class OnSubscribeDefer<T>
  implements Observable.OnSubscribe<T>
{
  final Func0<? extends Observable<? extends T>> observableFactory;
  
  public OnSubscribeDefer(Func0<? extends Observable<? extends T>> paramFunc0)
  {
    this.observableFactory = paramFunc0;
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    try
    {
      Observable localObservable = (Observable)this.observableFactory.call();
      localObservable.unsafeSubscribe(Subscribers.wrap(paramSubscriber));
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OnSubscribeDefer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */