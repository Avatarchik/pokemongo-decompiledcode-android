package rx.internal.producers;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.internal.operators.BackpressureUtils;

public final class QueuedProducer<T>
  extends AtomicLong
  implements Producer, Observer<T>
{
  static final Object NULL_SENTINEL = new Object();
  private static final long serialVersionUID = 7277121710709137047L;
  final Subscriber<? super T> child;
  volatile boolean done;
  Throwable error;
  final Queue<Object> queue;
  final AtomicInteger wip;
  
  public QueuedProducer(Subscriber<? super T> paramSubscriber) {}
  
  public QueuedProducer(Subscriber<? super T> paramSubscriber, Queue<Object> paramQueue)
  {
    this.child = paramSubscriber;
    this.queue = paramQueue;
    this.wip = new AtomicInteger();
  }
  
  private boolean checkTerminated(boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool = true;
    if (this.child.isUnsubscribed()) {}
    for (;;)
    {
      return bool;
      if (paramBoolean1)
      {
        Throwable localThrowable = this.error;
        if (localThrowable != null)
        {
          this.queue.clear();
          this.child.onError(localThrowable);
          continue;
        }
        if (paramBoolean2)
        {
          this.child.onCompleted();
          continue;
        }
      }
      bool = false;
    }
  }
  
  private void drain()
  {
    Subscriber localSubscriber;
    Queue localQueue;
    if (this.wip.getAndIncrement() == 0)
    {
      localSubscriber = this.child;
      localQueue = this.queue;
      if (!checkTerminated(this.done, localQueue.isEmpty())) {}
    }
    else
    {
      label37:
      return;
    }
    this.wip.lazySet(1);
    long l1 = get();
    for (long l2 = 0L;; l2 += 1L)
    {
      boolean bool1;
      Object localObject;
      if (l1 != 0L)
      {
        bool1 = this.done;
        localObject = localQueue.poll();
        if (localObject != null) {
          break label137;
        }
      }
      label137:
      for (boolean bool2 = true;; bool2 = false)
      {
        if (checkTerminated(bool1, bool2)) {
          break label141;
        }
        if (localObject != null) {
          break label143;
        }
        if ((l2 != 0L) && (get() != Long.MAX_VALUE)) {
          addAndGet(-l2);
        }
        if (this.wip.decrementAndGet() != 0) {
          break;
        }
        break label37;
      }
      label141:
      break label37;
      try
      {
        label143:
        if (localObject == NULL_SENTINEL) {
          localSubscriber.onNext(null);
        } else {
          localSubscriber.onNext(localObject);
        }
      }
      catch (Throwable localThrowable)
      {
        Exceptions.throwIfFatal(localThrowable);
        if (localObject == NULL_SENTINEL) {}
      }
      for (;;)
      {
        localSubscriber.onError(OnErrorThrowable.addValueAsLastCause(localThrowable, localObject));
        break;
        localObject = null;
      }
      l1 -= 1L;
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
  
  public void onCompleted()
  {
    this.done = true;
    drain();
  }
  
  public void onError(Throwable paramThrowable)
  {
    this.error = paramThrowable;
    this.done = true;
    drain();
  }
  
  public void onNext(T paramT)
  {
    if (!offer(paramT)) {
      onError(new MissingBackpressureException());
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/producers/QueuedProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */