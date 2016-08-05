package rx.internal.producers;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.internal.operators.BackpressureUtils;

public final class QueuedValueProducer<T>
  extends AtomicLong
  implements Producer
{
  static final Object NULL_SENTINEL = new Object();
  private static final long serialVersionUID = 7277121710709137047L;
  final Subscriber<? super T> child;
  final Queue<Object> queue;
  final AtomicInteger wip;
  
  public QueuedValueProducer(Subscriber<? super T> paramSubscriber) {}
  
  public QueuedValueProducer(Subscriber<? super T> paramSubscriber, Queue<Object> paramQueue)
  {
    this.child = paramSubscriber;
    this.queue = paramQueue;
    this.wip = new AtomicInteger();
  }
  
  private void drain()
  {
    Subscriber localSubscriber;
    Queue localQueue;
    if (this.wip.getAndIncrement() == 0)
    {
      localSubscriber = this.child;
      localQueue = this.queue;
      if (!localSubscriber.isUnsubscribed()) {
        break label28;
      }
    }
    for (;;)
    {
      return;
      label28:
      this.wip.lazySet(1);
      long l1 = get();
      long l2 = 0L;
      label44:
      if (l1 != 0L)
      {
        Object localObject1 = localQueue.poll();
        if (localObject1 != null)
        {
          try
          {
            if (localObject1 == NULL_SENTINEL) {
              localSubscriber.onNext(null);
            }
            while (!localSubscriber.isUnsubscribed())
            {
              l1 -= 1L;
              l2 += 1L;
              break label44;
              Object localObject2 = localObject1;
              localSubscriber.onNext(localObject2);
            }
            localSubscriber.onError(OnErrorThrowable.addValueAsLastCause(localThrowable, localObject1));
          }
          catch (Throwable localThrowable)
          {
            Exceptions.throwIfFatal(localThrowable);
            if (localObject1 == NULL_SENTINEL) {}
          }
          for (;;)
          {
            break;
            localObject1 = null;
          }
        }
      }
      if ((l2 != 0L) && (get() != Long.MAX_VALUE)) {
        addAndGet(-l2);
      }
      if (this.wip.decrementAndGet() != 0) {
        break;
      }
    }
  }
  
  public boolean offer(T paramT)
  {
    boolean bool = false;
    if (paramT == null) {
      if (this.queue.offer(NULL_SENTINEL)) {
        break label36;
      }
    }
    for (;;)
    {
      return bool;
      if (this.queue.offer(paramT))
      {
        label36:
        drain();
        bool = true;
      }
    }
  }
  
  public void request(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("n >= 0 required");
    }
    if (paramLong > 0L)
    {
      BackpressureUtils.getAndAddRequest(this, paramLong);
      drain();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/producers/QueuedValueProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */