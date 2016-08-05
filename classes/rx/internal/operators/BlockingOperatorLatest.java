package rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;

public final class BlockingOperatorLatest
{
  private BlockingOperatorLatest()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static <T> Iterable<T> latest(Observable<? extends T> paramObservable)
  {
    new Iterable()
    {
      public Iterator<T> iterator()
      {
        BlockingOperatorLatest.LatestObserverIterator localLatestObserverIterator = new BlockingOperatorLatest.LatestObserverIterator();
        BlockingOperatorLatest.this.materialize().subscribe(localLatestObserverIterator);
        return localLatestObserverIterator;
      }
    };
  }
  
  static final class LatestObserverIterator<T>
    extends Subscriber<Notification<? extends T>>
    implements Iterator<T>
  {
    static final AtomicReferenceFieldUpdater<LatestObserverIterator, Notification> REFERENCE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(LatestObserverIterator.class, Notification.class, "value");
    Notification<? extends T> iNotif;
    final Semaphore notify = new Semaphore(0);
    volatile Notification<? extends T> value;
    
    public boolean hasNext()
    {
      if ((this.iNotif != null) && (this.iNotif.isOnError())) {
        throw Exceptions.propagate(this.iNotif.getThrowable());
      }
      if (((this.iNotif == null) || (!this.iNotif.isOnCompleted())) && (this.iNotif == null)) {
        try
        {
          this.notify.acquire();
          this.iNotif = ((Notification)REFERENCE_UPDATER.getAndSet(this, null));
          if (this.iNotif.isOnError()) {
            throw Exceptions.propagate(this.iNotif.getThrowable());
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          unsubscribe();
          Thread.currentThread().interrupt();
          this.iNotif = Notification.createOnError(localInterruptedException);
          throw Exceptions.propagate(localInterruptedException);
        }
      }
      if (!this.iNotif.isOnCompleted()) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public T next()
    {
      if ((hasNext()) && (this.iNotif.isOnNext()))
      {
        Object localObject = this.iNotif.getValue();
        this.iNotif = null;
        return (T)localObject;
      }
      throw new NoSuchElementException();
    }
    
    public void onCompleted() {}
    
    public void onError(Throwable paramThrowable) {}
    
    public void onNext(Notification<? extends T> paramNotification)
    {
      if (REFERENCE_UPDATER.getAndSet(this, paramNotification) == null) {}
      for (int i = 1;; i = 0)
      {
        if (i != 0) {
          this.notify.release();
        }
        return;
      }
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException("Read-only iterator.");
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/BlockingOperatorLatest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */