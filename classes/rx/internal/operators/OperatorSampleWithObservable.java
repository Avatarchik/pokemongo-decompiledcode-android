package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;

public final class OperatorSampleWithObservable<T, U>
  implements Observable.Operator<T, T>
{
  static final Object EMPTY_TOKEN = new Object();
  final Observable<U> sampler;
  
  public OperatorSampleWithObservable(Observable<U> paramObservable)
  {
    this.sampler = paramObservable;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    final SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    final AtomicReference localAtomicReference = new AtomicReference(EMPTY_TOKEN);
    Subscriber local1 = new Subscriber(paramSubscriber)
    {
      public void onCompleted()
      {
        localSerializedSubscriber.onCompleted();
        unsubscribe();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        localSerializedSubscriber.onError(paramAnonymousThrowable);
        unsubscribe();
      }
      
      public void onNext(U paramAnonymousU)
      {
        Object localObject = localAtomicReference.getAndSet(OperatorSampleWithObservable.EMPTY_TOKEN);
        if (localObject != OperatorSampleWithObservable.EMPTY_TOKEN) {
          localSerializedSubscriber.onNext(localObject);
        }
      }
    };
    Subscriber local2 = new Subscriber(paramSubscriber)
    {
      public void onCompleted()
      {
        localSerializedSubscriber.onCompleted();
        unsubscribe();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        localSerializedSubscriber.onError(paramAnonymousThrowable);
        unsubscribe();
      }
      
      public void onNext(T paramAnonymousT)
      {
        localAtomicReference.set(paramAnonymousT);
      }
    };
    this.sampler.unsafeSubscribe(local1);
    return local2;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorSampleWithObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */