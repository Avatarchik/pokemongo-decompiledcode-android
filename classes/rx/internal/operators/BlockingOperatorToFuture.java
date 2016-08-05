package rx.internal.operators;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public final class BlockingOperatorToFuture
{
  private BlockingOperatorToFuture()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static <T> Future<T> toFuture(Observable<? extends T> paramObservable)
  {
    CountDownLatch localCountDownLatch = new CountDownLatch(1);
    final AtomicReference localAtomicReference1 = new AtomicReference();
    final AtomicReference localAtomicReference2 = new AtomicReference();
    new Future()
    {
      public void onCompleted()
      {
        BlockingOperatorToFuture.this.countDown();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        localAtomicReference2.compareAndSet(null, paramAnonymousThrowable);
        BlockingOperatorToFuture.this.countDown();
      }
      
      public void onNext(T paramAnonymousT)
      {
        localAtomicReference1.set(paramAnonymousT);
      }
    }
    {
      private volatile boolean cancelled = false;
      
      private T getValue()
        throws ExecutionException
      {
        if (localAtomicReference2.get() != null) {
          throw new ExecutionException("Observable onError", (Throwable)localAtomicReference2.get());
        }
        if (this.cancelled) {
          throw new CancellationException("Subscription unsubscribed");
        }
        return (T)localAtomicReference1.get();
      }
      
      public boolean cancel(boolean paramAnonymousBoolean)
      {
        boolean bool = true;
        if (BlockingOperatorToFuture.this.getCount() > 0L)
        {
          this.cancelled = bool;
          this.val$s.unsubscribe();
          BlockingOperatorToFuture.this.countDown();
        }
        for (;;)
        {
          return bool;
          bool = false;
        }
      }
      
      public T get()
        throws InterruptedException, ExecutionException
      {
        BlockingOperatorToFuture.this.await();
        return (T)getValue();
      }
      
      public T get(long paramAnonymousLong, TimeUnit paramAnonymousTimeUnit)
        throws InterruptedException, ExecutionException, TimeoutException
      {
        if (BlockingOperatorToFuture.this.await(paramAnonymousLong, paramAnonymousTimeUnit)) {
          return (T)getValue();
        }
        throw new TimeoutException("Timed out after " + paramAnonymousTimeUnit.toMillis(paramAnonymousLong) + "ms waiting for underlying Observable.");
      }
      
      public boolean isCancelled()
      {
        return this.cancelled;
      }
      
      public boolean isDone()
      {
        if (BlockingOperatorToFuture.this.getCount() == 0L) {}
        for (boolean bool = true;; bool = false) {
          return bool;
        }
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/BlockingOperatorToFuture.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */