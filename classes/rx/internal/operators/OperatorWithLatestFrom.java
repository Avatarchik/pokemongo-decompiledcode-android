package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func2;
import rx.observers.SerializedSubscriber;

public final class OperatorWithLatestFrom<T, U, R>
  implements Observable.Operator<R, T>
{
  static final Object EMPTY = new Object();
  final Observable<? extends U> other;
  final Func2<? super T, ? super U, ? extends R> resultSelector;
  
  public OperatorWithLatestFrom(Observable<? extends U> paramObservable, Func2<? super T, ? super U, ? extends R> paramFunc2)
  {
    this.other = paramObservable;
    this.resultSelector = paramFunc2;
  }
  
  public Subscriber<? super T> call(Subscriber<? super R> paramSubscriber)
  {
    final SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber, false);
    paramSubscriber.add(localSerializedSubscriber);
    final AtomicReference localAtomicReference = new AtomicReference(EMPTY);
    Subscriber local1 = new Subscriber(localSerializedSubscriber, true)
    {
      public void onCompleted()
      {
        localSerializedSubscriber.onCompleted();
        localSerializedSubscriber.unsubscribe();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        localSerializedSubscriber.onError(paramAnonymousThrowable);
        localSerializedSubscriber.unsubscribe();
      }
      
      public void onNext(T paramAnonymousT)
      {
        Object localObject1 = localAtomicReference.get();
        if (localObject1 != OperatorWithLatestFrom.EMPTY) {}
        try
        {
          Object localObject2 = OperatorWithLatestFrom.this.resultSelector.call(paramAnonymousT, localObject1);
          localSerializedSubscriber.onNext(localObject2);
          return;
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            onError(localThrowable);
          }
        }
      }
    };
    Subscriber local2 = new Subscriber()
    {
      public void onCompleted()
      {
        if (localAtomicReference.get() == OperatorWithLatestFrom.EMPTY)
        {
          localSerializedSubscriber.onCompleted();
          localSerializedSubscriber.unsubscribe();
        }
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        localSerializedSubscriber.onError(paramAnonymousThrowable);
        localSerializedSubscriber.unsubscribe();
      }
      
      public void onNext(U paramAnonymousU)
      {
        localAtomicReference.set(paramAnonymousU);
      }
    };
    localSerializedSubscriber.add(local1);
    localSerializedSubscriber.add(local2);
    this.other.unsafeSubscribe(local2);
    return local1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorWithLatestFrom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */