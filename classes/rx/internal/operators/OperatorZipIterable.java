package rx.internal.operators;

import java.util.Iterator;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func2;
import rx.observers.Subscribers;

public final class OperatorZipIterable<T1, T2, R>
  implements Observable.Operator<R, T1>
{
  final Iterable<? extends T2> iterable;
  final Func2<? super T1, ? super T2, ? extends R> zipFunction;
  
  public OperatorZipIterable(Iterable<? extends T2> paramIterable, Func2<? super T1, ? super T2, ? extends R> paramFunc2)
  {
    this.iterable = paramIterable;
    this.zipFunction = paramFunc2;
  }
  
  public Subscriber<? super T1> call(final Subscriber<? super R> paramSubscriber)
  {
    localIterator = this.iterable.iterator();
    try
    {
      if (localIterator.hasNext()) {
        break label41;
      }
      paramSubscriber.onCompleted();
      Subscriber localSubscriber = Subscribers.empty();
      localObject = localSubscriber;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        paramSubscriber.onError(localThrowable);
        Object localObject = new Subscriber(paramSubscriber)
        {
          boolean once;
          
          public void onCompleted()
          {
            if (this.once) {}
            for (;;)
            {
              return;
              this.once = true;
              paramSubscriber.onCompleted();
            }
          }
          
          public void onError(Throwable paramAnonymousThrowable)
          {
            paramSubscriber.onError(paramAnonymousThrowable);
          }
          
          public void onNext(T1 paramAnonymousT1)
          {
            try
            {
              paramSubscriber.onNext(OperatorZipIterable.this.zipFunction.call(paramAnonymousT1, localIterator.next()));
              if (!localIterator.hasNext()) {
                onCompleted();
              }
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
      }
    }
    return (Subscriber<? super T1>)localObject;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorZipIterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */