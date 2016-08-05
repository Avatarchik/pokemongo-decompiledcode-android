package rx.internal.util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.functions.Action0;
import rx.internal.util.unsafe.MpmcArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.schedulers.Schedulers;

public abstract class ObjectPool<T>
{
  private final int maxSize;
  private Queue<T> pool;
  private Scheduler.Worker schedulerWorker;
  
  public ObjectPool()
  {
    this(0, 0, 67L);
  }
  
  private ObjectPool(final int paramInt1, final int paramInt2, long paramLong)
  {
    this.maxSize = paramInt2;
    initialize(paramInt1);
    this.schedulerWorker = Schedulers.computation().createWorker();
    this.schedulerWorker.schedulePeriodically(new Action0()
    {
      public void call()
      {
        int i = ObjectPool.this.pool.size();
        if (i < paramInt1)
        {
          int m = paramInt2 - i;
          for (int n = 0; n < m; n++) {
            ObjectPool.this.pool.add(ObjectPool.this.createObject());
          }
        }
        if (i > paramInt2)
        {
          int j = i - paramInt2;
          for (int k = 0; k < j; k++) {
            ObjectPool.this.pool.poll();
          }
        }
      }
    }, paramLong, paramLong, TimeUnit.SECONDS);
  }
  
  private void initialize(int paramInt)
  {
    if (UnsafeAccess.isUnsafeAvailable()) {}
    for (this.pool = new MpmcArrayQueue(Math.max(this.maxSize, 1024));; this.pool = new ConcurrentLinkedQueue()) {
      for (int i = 0; i < paramInt; i++) {
        this.pool.add(createObject());
      }
    }
  }
  
  public T borrowObject()
  {
    Object localObject = this.pool.poll();
    if (localObject == null) {
      localObject = createObject();
    }
    return (T)localObject;
  }
  
  protected abstract T createObject();
  
  public void returnObject(T paramT)
  {
    if (paramT == null) {}
    for (;;)
    {
      return;
      this.pool.offer(paramT);
    }
  }
  
  public void shutdown()
  {
    this.schedulerWorker.unsubscribe();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/ObjectPool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */