package rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;

public final class BlockingOperatorNext
{
  private BlockingOperatorNext()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static <T> Iterable<T> next(Observable<? extends T> paramObservable)
  {
    new Iterable()
    {
      public Iterator<T> iterator()
      {
        BlockingOperatorNext.NextObserver localNextObserver = new BlockingOperatorNext.NextObserver(null);
        return new BlockingOperatorNext.NextIterator(BlockingOperatorNext.this, localNextObserver, null);
      }
    };
  }
  
  private static class NextObserver<T>
    extends Subscriber<Notification<? extends T>>
  {
    static final AtomicIntegerFieldUpdater<NextObserver> WAITING_UPDATER = AtomicIntegerFieldUpdater.newUpdater(NextObserver.class, "waiting");
    private final BlockingQueue<Notification<? extends T>> buf = new ArrayBlockingQueue(1);
    volatile int waiting;
    
    public void onCompleted() {}
    
    public void onError(Throwable paramThrowable) {}
    
    public void onNext(Notification<? extends T> paramNotification)
    {
      if ((WAITING_UPDATER.getAndSet(this, 0) == 1) || (!paramNotification.isOnNext()))
      {
        label21:
        Notification localNotification;
        for (Object localObject = paramNotification; !this.buf.offer(localObject); localObject = localNotification)
        {
          localNotification = (Notification)this.buf.poll();
          if ((localNotification == null) || (localNotification.isOnNext())) {
            break label21;
          }
        }
      }
    }
    
    void setWaiting(int paramInt)
    {
      this.waiting = paramInt;
    }
    
    public Notification<? extends T> takeNext()
      throws InterruptedException
    {
      setWaiting(1);
      return (Notification)this.buf.take();
    }
  }
  
  static final class NextIterator<T>
    implements Iterator<T>
  {
    private Throwable error = null;
    private boolean hasNext = true;
    private boolean isNextConsumed = true;
    private final Observable<? extends T> items;
    private T next;
    private final BlockingOperatorNext.NextObserver<T> observer;
    private boolean started = false;
    
    private NextIterator(Observable<? extends T> paramObservable, BlockingOperatorNext.NextObserver<T> paramNextObserver)
    {
      this.items = paramObservable;
      this.observer = paramNextObserver;
    }
    
    private boolean moveToNext()
    {
      boolean bool = true;
      try
      {
        if (!this.started)
        {
          this.started = true;
          this.observer.setWaiting(1);
          this.items.materialize().subscribe(this.observer);
        }
        Notification localNotification = this.observer.takeNext();
        if (localNotification.isOnNext())
        {
          this.isNextConsumed = false;
          this.next = localNotification.getValue();
          return bool;
        }
        this.hasNext = false;
        if (localNotification.isOnCompleted()) {
          return false;
        }
        if (localNotification.isOnError())
        {
          this.error = localNotification.getThrowable();
          throw Exceptions.propagate(this.error);
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        this.observer.unsubscribe();
        Thread.currentThread().interrupt();
        this.error = localInterruptedException;
        throw Exceptions.propagate(this.error);
      }
      throw new IllegalStateException("Should not reach here");
      return bool;
    }
    
    public boolean hasNext()
    {
      if (this.error != null) {
        throw Exceptions.propagate(this.error);
      }
      boolean bool;
      if (!this.hasNext) {
        bool = false;
      }
      for (;;)
      {
        return bool;
        if (!this.isNextConsumed) {
          bool = true;
        } else {
          bool = moveToNext();
        }
      }
    }
    
    public T next()
    {
      if (this.error != null) {
        throw Exceptions.propagate(this.error);
      }
      if (hasNext())
      {
        this.isNextConsumed = true;
        return (T)this.next;
      }
      throw new NoSuchElementException("No more elements");
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException("Read only iterator");
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/BlockingOperatorNext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */