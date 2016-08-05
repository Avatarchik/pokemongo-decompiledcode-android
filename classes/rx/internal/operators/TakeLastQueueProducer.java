package rx.internal.operators;

import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import rx.Producer;
import rx.Subscriber;

final class TakeLastQueueProducer<T>
  implements Producer
{
  private static final AtomicLongFieldUpdater<TakeLastQueueProducer> REQUESTED_UPDATER = AtomicLongFieldUpdater.newUpdater(TakeLastQueueProducer.class, "requested");
  private final Deque<Object> deque;
  private volatile boolean emittingStarted = false;
  private final NotificationLite<T> notification;
  private volatile long requested = 0L;
  private final Subscriber<? super T> subscriber;
  
  public TakeLastQueueProducer(NotificationLite<T> paramNotificationLite, Deque<Object> paramDeque, Subscriber<? super T> paramSubscriber)
  {
    this.notification = paramNotificationLite;
    this.deque = paramDeque;
    this.subscriber = paramSubscriber;
  }
  
  void emit(long paramLong)
  {
    if (this.requested == Long.MAX_VALUE) {
      if (paramLong != 0L) {}
    }
    for (;;)
    {
      try
      {
        Iterator localIterator = this.deque.iterator();
        if (!localIterator.hasNext()) {
          continue;
        }
        localObject3 = localIterator.next();
        boolean bool = this.subscriber.isUnsubscribed();
        if (bool) {
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        Object localObject3;
        this.subscriber.onError(localThrowable);
        this.deque.clear();
        continue;
        this.deque.clear();
        continue;
      }
      finally
      {
        this.deque.clear();
      }
      this.notification.accept(this.subscriber, localObject3);
      continue;
      if (paramLong == 0L)
      {
        label209:
        long l3;
        do
        {
          long l1 = this.requested;
          for (int i = 0;; i++)
          {
            l1 -= 1L;
            if (l1 < 0L) {
              break label209;
            }
            Object localObject1 = this.deque.poll();
            if (localObject1 == null) {
              break label209;
            }
            if ((this.subscriber.isUnsubscribed()) || (this.notification.accept(this.subscriber, localObject1))) {
              break;
            }
          }
          long l2;
          do
          {
            l2 = this.requested;
            l3 = l2 - i;
            if (l2 == Long.MAX_VALUE) {
              break;
            }
          } while (!REQUESTED_UPDATER.compareAndSet(this, l2, l3));
        } while (l3 != 0L);
      }
    }
  }
  
  public void request(long paramLong)
  {
    if (this.requested == Long.MAX_VALUE) {}
    label56:
    for (;;)
    {
      return;
      if (paramLong == Long.MAX_VALUE) {}
      for (long l = REQUESTED_UPDATER.getAndSet(this, Long.MAX_VALUE);; l = BackpressureUtils.getAndAddRequest(REQUESTED_UPDATER, this, paramLong))
      {
        if (!this.emittingStarted) {
          break label56;
        }
        emit(l);
        break;
      }
    }
  }
  
  void startEmitting()
  {
    if (!this.emittingStarted)
    {
      this.emittingStarted = true;
      emit(0L);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/TakeLastQueueProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */