package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.util.RxThreadFactory;
import rx.internal.util.SubscriptionList;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public class EventLoopsScheduler
  extends Scheduler
{
  static final String KEY_MAX_THREADS = "rx.scheduler.max-computation-threads";
  static final int MAX_THREADS = 0;
  private static final RxThreadFactory THREAD_FACTORY = new RxThreadFactory("RxComputationThreadPool-");
  private static final String THREAD_NAME_PREFIX = "RxComputationThreadPool-";
  final FixedSchedulerPool pool = new FixedSchedulerPool();
  
  static
  {
    int i = Integer.getInteger("rx.scheduler.max-computation-threads", 0).intValue();
    int j = Runtime.getRuntime().availableProcessors();
    if ((i <= 0) || (i > j)) {}
    for (int k = j;; k = i)
    {
      MAX_THREADS = k;
      return;
    }
  }
  
  public Scheduler.Worker createWorker()
  {
    return new EventLoopWorker(this.pool.getEventLoop());
  }
  
  public Subscription scheduleDirect(Action0 paramAction0)
  {
    return this.pool.getEventLoop().scheduleActual(paramAction0, -1L, TimeUnit.NANOSECONDS);
  }
  
  private static final class PoolWorker
    extends NewThreadWorker
  {
    PoolWorker(ThreadFactory paramThreadFactory)
    {
      super();
    }
  }
  
  private static class EventLoopWorker
    extends Scheduler.Worker
  {
    private final SubscriptionList both;
    private final EventLoopsScheduler.PoolWorker poolWorker;
    private final SubscriptionList serial = new SubscriptionList();
    private final CompositeSubscription timed = new CompositeSubscription();
    
    EventLoopWorker(EventLoopsScheduler.PoolWorker paramPoolWorker)
    {
      Subscription[] arrayOfSubscription = new Subscription[2];
      arrayOfSubscription[0] = this.serial;
      arrayOfSubscription[1] = this.timed;
      this.both = new SubscriptionList(arrayOfSubscription);
      this.poolWorker = paramPoolWorker;
    }
    
    public boolean isUnsubscribed()
    {
      return this.both.isUnsubscribed();
    }
    
    public Subscription schedule(Action0 paramAction0)
    {
      if (isUnsubscribed()) {}
      for (Object localObject = Subscriptions.unsubscribed();; localObject = this.poolWorker.scheduleActual(paramAction0, 0L, null, this.serial)) {
        return (Subscription)localObject;
      }
    }
    
    public Subscription schedule(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
    {
      if (isUnsubscribed()) {}
      for (Object localObject = Subscriptions.unsubscribed();; localObject = this.poolWorker.scheduleActual(paramAction0, paramLong, paramTimeUnit, this.timed)) {
        return (Subscription)localObject;
      }
    }
    
    public void unsubscribe()
    {
      this.both.unsubscribe();
    }
  }
  
  static final class FixedSchedulerPool
  {
    final int cores = EventLoopsScheduler.MAX_THREADS;
    final EventLoopsScheduler.PoolWorker[] eventLoops = new EventLoopsScheduler.PoolWorker[this.cores];
    long n;
    
    FixedSchedulerPool()
    {
      for (int i = 0; i < this.cores; i++) {
        this.eventLoops[i] = new EventLoopsScheduler.PoolWorker(EventLoopsScheduler.THREAD_FACTORY);
      }
    }
    
    public EventLoopsScheduler.PoolWorker getEventLoop()
    {
      EventLoopsScheduler.PoolWorker[] arrayOfPoolWorker = this.eventLoops;
      long l = this.n;
      this.n = (1L + l);
      return arrayOfPoolWorker[((int)(l % this.cores))];
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/schedulers/EventLoopsScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */