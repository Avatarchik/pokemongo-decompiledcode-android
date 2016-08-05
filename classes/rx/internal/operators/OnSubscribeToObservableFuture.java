package rx.internal.operators;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public final class OnSubscribeToObservableFuture
{
  private OnSubscribeToObservableFuture()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static <T> Observable.OnSubscribe<T> toObservableFuture(Future<? extends T> paramFuture)
  {
    return new ToObservableFuture(paramFuture);
  }
  
  public static <T> Observable.OnSubscribe<T> toObservableFuture(Future<? extends T> paramFuture, long paramLong, TimeUnit paramTimeUnit)
  {
    return new ToObservableFuture(paramFuture, paramLong, paramTimeUnit);
  }
  
  static class ToObservableFuture<T>
    implements Observable.OnSubscribe<T>
  {
    private final Future<? extends T> that;
    private final long time;
    private final TimeUnit unit;
    
    public ToObservableFuture(Future<? extends T> paramFuture)
    {
      this.that = paramFuture;
      this.time = 0L;
      this.unit = null;
    }
    
    public ToObservableFuture(Future<? extends T> paramFuture, long paramLong, TimeUnit paramTimeUnit)
    {
      this.that = paramFuture;
      this.time = paramLong;
      this.unit = paramTimeUnit;
    }
    
    public void call(Subscriber<? super T> paramSubscriber)
    {
      paramSubscriber.add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          OnSubscribeToObservableFuture.ToObservableFuture.this.that.cancel(true);
        }
      }));
      try
      {
        if (!paramSubscriber.isUnsubscribed()) {
          if (this.unit == null)
          {
            localObject2 = this.that.get();
            paramSubscriber.onNext(localObject2);
            paramSubscriber.onCompleted();
          }
        }
      }
      catch (Throwable localThrowable)
      {
        Object localObject2;
        while (!paramSubscriber.isUnsubscribed())
        {
          paramSubscriber.onError(localThrowable);
          break;
          Object localObject1 = this.that.get(this.time, this.unit);
          localObject2 = localObject1;
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OnSubscribeToObservableFuture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */