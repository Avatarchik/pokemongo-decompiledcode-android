package rx.internal.util;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import rx.Producer;
import rx.annotations.Experimental;

@Experimental
public final class BackpressureDrainManager
  implements Producer
{
  protected static final AtomicLongFieldUpdater<BackpressureDrainManager> REQUESTED_COUNT = AtomicLongFieldUpdater.newUpdater(BackpressureDrainManager.class, "requestedCount");
  protected final BackpressureQueueCallback actual;
  protected boolean emitting;
  protected Throwable exception;
  protected volatile long requestedCount;
  protected volatile boolean terminated;
  
  public BackpressureDrainManager(BackpressureQueueCallback paramBackpressureQueueCallback)
  {
    this.actual = paramBackpressureQueueCallback;
  }
  
  public final void drain()
  {
    boolean bool1;
    long l;
    int i;
    BackpressureQueueCallback localBackpressureQueueCallback;
    try
    {
      if (this.emitting) {
        break label351;
      }
      this.emitting = true;
      bool1 = this.terminated;
      l = this.requestedCount;
      i = 0;
    }
    finally {}
    try
    {
      localBackpressureQueueCallback = this.actual;
    }
    finally
    {
      label106:
      if (i != 0) {
        break label281;
      }
    }
    if (bool1)
    {
      if (localBackpressureQueueCallback.peek() == null)
      {
        i = 1;
        localBackpressureQueueCallback.complete(this.exception);
        if (i != 0) {
          break label351;
        }
        try
        {
          this.emitting = false;
          break label351;
        }
        finally
        {
          localObject9 = finally;
          throw ((Throwable)localObject9);
        }
      }
      if (l != 0L) {}
    }
    for (;;)
    {
      int k;
      int j;
      try
      {
        bool1 = this.terminated;
        if (localBackpressureQueueCallback.peek() != null)
        {
          k = 1;
          if (this.requestedCount != Long.MAX_VALUE) {
            break label284;
          }
          if ((k != 0) || (bool1)) {
            continue;
          }
          i = 1;
          this.emitting = false;
          if (i != 0) {
            break label351;
          }
          try
          {
            this.emitting = false;
            break label351;
          }
          finally
          {
            localObject8 = finally;
            throw ((Throwable)localObject8);
          }
          Object localObject4 = localBackpressureQueueCallback.poll();
          if (localObject4 == null) {
            break label106;
          }
          boolean bool2 = localBackpressureQueueCallback.accept(localObject4);
          if (bool2)
          {
            if (1 != 0) {
              break label351;
            }
            try
            {
              this.emitting = false;
              break label351;
            }
            finally
            {
              localObject5 = finally;
              throw ((Throwable)localObject5);
            }
          }
          l -= 1L;
          j++;
          break label355;
        }
        k = 0;
        continue;
        l = Long.MAX_VALUE;
      }
      finally {}
      try
      {
        this.emitting = false;
        label281:
        throw ((Throwable)localObject2);
        label284:
        l = REQUESTED_COUNT.addAndGet(this, -j);
        if (l != 0L)
        {
          if (k != 0) {
            continue;
          }
          break label368;
          i = 1;
          this.emitting = false;
          if (i == 0) {
            try
            {
              this.emitting = false;
              break label351;
            }
            finally
            {
              localObject7 = finally;
              throw ((Throwable)localObject7);
            }
          }
        }
      }
      finally
      {
        label351:
        label355:
        label368:
        do
        {
          throw ((Throwable)localObject3);
          return;
          j = 0;
          if (l > 0L) {
            break;
          }
          if (!bool1) {
            break label106;
          }
          break;
        } while (!bool1);
      }
    }
  }
  
  public final boolean isTerminated()
  {
    return this.terminated;
  }
  
  public final void request(long paramLong)
  {
    if (paramLong == 0L) {
      return;
    }
    label29:
    label47:
    label75:
    label101:
    for (;;)
    {
      long l1 = this.requestedCount;
      if (l1 == 0L) {}
      for (int i = 1;; i = 0)
      {
        if (l1 != Long.MAX_VALUE) {
          break label47;
        }
        if (i == 0) {
          break label75;
        }
        drain();
        break;
      }
      long l2;
      if (paramLong == Long.MAX_VALUE)
      {
        l2 = paramLong;
        i = 1;
      }
      for (;;)
      {
        if (!REQUESTED_COUNT.compareAndSet(this, l1, l2)) {
          break label101;
        }
        break label29;
        break;
        if (l1 > Long.MAX_VALUE - paramLong) {
          l2 = Long.MAX_VALUE;
        } else {
          l2 = l1 + paramLong;
        }
      }
    }
  }
  
  public final void terminate()
  {
    this.terminated = true;
  }
  
  public final void terminate(Throwable paramThrowable)
  {
    if (!this.terminated)
    {
      this.exception = paramThrowable;
      this.terminated = true;
    }
  }
  
  public final void terminateAndDrain()
  {
    this.terminated = true;
    drain();
  }
  
  public final void terminateAndDrain(Throwable paramThrowable)
  {
    if (!this.terminated)
    {
      this.exception = paramThrowable;
      this.terminated = true;
      drain();
    }
  }
  
  public static abstract interface BackpressureQueueCallback
  {
    public abstract boolean accept(Object paramObject);
    
    public abstract void complete(Throwable paramThrowable);
    
    public abstract Object peek();
    
    public abstract Object poll();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/BackpressureDrainManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */