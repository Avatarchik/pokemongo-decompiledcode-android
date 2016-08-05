package rx.internal.operators;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;
import rx.observers.SafeSubscriber;
import rx.subjects.Subject;

public final class OnSubscribeMulticastSelector<TInput, TIntermediate, TResult>
  implements Observable.OnSubscribe<TResult>
{
  final Func1<? super Observable<TIntermediate>, ? extends Observable<TResult>> resultSelector;
  final Observable<? extends TInput> source;
  final Func0<? extends Subject<? super TInput, ? extends TIntermediate>> subjectFactory;
  
  public OnSubscribeMulticastSelector(Observable<? extends TInput> paramObservable, Func0<? extends Subject<? super TInput, ? extends TIntermediate>> paramFunc0, Func1<? super Observable<TIntermediate>, ? extends Observable<TResult>> paramFunc1)
  {
    this.source = paramObservable;
    this.subjectFactory = paramFunc0;
    this.resultSelector = paramFunc1;
  }
  
  public void call(Subscriber<? super TResult> paramSubscriber)
  {
    try
    {
      OperatorMulticast localOperatorMulticast = new OperatorMulticast(this.source, this.subjectFactory);
      Observable localObservable = (Observable)this.resultSelector.call(localOperatorMulticast);
      final SafeSubscriber localSafeSubscriber = new SafeSubscriber(paramSubscriber);
      localObservable.unsafeSubscribe(localSafeSubscriber);
      localOperatorMulticast.connect(new Action1()
      {
        public void call(Subscription paramAnonymousSubscription)
        {
          localSafeSubscriber.add(paramAnonymousSubscription);
        }
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OnSubscribeMulticastSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */