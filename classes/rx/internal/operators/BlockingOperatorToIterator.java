package rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;

public final class BlockingOperatorToIterator
{
  private BlockingOperatorToIterator()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static <T> Iterator<T> toIterator(Observable<? extends T> paramObservable)
  {
    LinkedBlockingQueue localLinkedBlockingQueue = new LinkedBlockingQueue();
    new Iterator()
    {
      public void onCompleted() {}
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        BlockingOperatorToIterator.this.offer(Notification.createOnError(paramAnonymousThrowable));
      }
      
      public void onNext(Notification<? extends T> paramAnonymousNotification)
      {
        BlockingOperatorToIterator.this.offer(paramAnonymousNotification);
      }
    }
    {
      private Notification<? extends T> buf;
      
      private Notification<? extends T> take()
      {
        try
        {
          Notification localNotification = (Notification)BlockingOperatorToIterator.this.take();
          return localNotification;
        }
        catch (InterruptedException localInterruptedException)
        {
          this.val$subscription.unsubscribe();
          throw Exceptions.propagate(localInterruptedException);
        }
      }
      
      public boolean hasNext()
      {
        if (this.buf == null) {
          this.buf = take();
        }
        if (this.buf.isOnError()) {
          throw Exceptions.propagate(this.buf.getThrowable());
        }
        if (!this.buf.isOnCompleted()) {}
        for (boolean bool = true;; bool = false) {
          return bool;
        }
      }
      
      public T next()
      {
        if (hasNext())
        {
          Object localObject = this.buf.getValue();
          this.buf = null;
          return (T)localObject;
        }
        throw new NoSuchElementException();
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException("Read-only iterator");
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/BlockingOperatorToIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */