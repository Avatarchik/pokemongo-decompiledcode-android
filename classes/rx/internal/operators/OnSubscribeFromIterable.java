package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Subscriber;

public final class OnSubscribeFromIterable<T>
  implements Observable.OnSubscribe<T>
{
  final Iterable<? extends T> is;
  
  public OnSubscribeFromIterable(Iterable<? extends T> paramIterable)
  {
    if (paramIterable == null) {
      throw new NullPointerException("iterable must not be null");
    }
    this.is = paramIterable;
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    Iterator localIterator = this.is.iterator();
    if ((!localIterator.hasNext()) && (!paramSubscriber.isUnsubscribed())) {
      paramSubscriber.onCompleted();
    }
    for (;;)
    {
      return;
      paramSubscriber.setProducer(new IterableProducer(paramSubscriber, localIterator, null));
    }
  }
  
  private static final class IterableProducer<T>
    implements Producer
  {
    private static final AtomicLongFieldUpdater<IterableProducer> REQUESTED_UPDATER = AtomicLongFieldUpdater.newUpdater(IterableProducer.class, "requested");
    private final Iterator<? extends T> it;
    private final Subscriber<? super T> o;
    private volatile long requested = 0L;
    
    private IterableProducer(Subscriber<? super T> paramSubscriber, Iterator<? extends T> paramIterator)
    {
      this.o = paramSubscriber;
      this.it = paramIterator;
    }
    
    public void request(long paramLong)
    {
      if (this.requested == Long.MAX_VALUE) {}
      for (;;)
      {
        return;
        if ((paramLong == Long.MAX_VALUE) && (REQUESTED_UPDATER.compareAndSet(this, 0L, Long.MAX_VALUE)))
        {
          while (!this.o.isUnsubscribed())
          {
            if (!this.it.hasNext()) {
              break label75;
            }
            this.o.onNext(this.it.next());
          }
          continue;
          label75:
          if (!this.o.isUnsubscribed()) {
            this.o.onCompleted();
          }
        }
        else if ((paramLong > 0L) && (BackpressureUtils.getAndAddRequest(REQUESTED_UPDATER, this, paramLong) == 0L))
        {
          long l1;
          label176:
          label196:
          do
          {
            l1 = this.requested;
            long l2 = l1;
            while (!this.o.isUnsubscribed())
            {
              if (!this.it.hasNext()) {
                break label176;
              }
              l2 -= 1L;
              if (l2 < 0L) {
                break label196;
              }
              this.o.onNext(this.it.next());
            }
            break;
            if (this.o.isUnsubscribed()) {
              break;
            }
            this.o.onCompleted();
            break;
          } while (REQUESTED_UPDATER.addAndGet(this, -l1) != 0L);
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OnSubscribeFromIterable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */