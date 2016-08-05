package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import rx.Observable.Operator;
import rx.Producer;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.SynchronizedQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.schedulers.ImmediateScheduler;
import rx.schedulers.TrampolineScheduler;

public final class OperatorObserveOn<T>
  implements Observable.Operator<T, T>
{
  private final Scheduler scheduler;
  
  public OperatorObserveOn(Scheduler paramScheduler)
  {
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    if ((this.scheduler instanceof ImmediateScheduler)) {}
    for (;;)
    {
      return paramSubscriber;
      if (!(this.scheduler instanceof TrampolineScheduler))
      {
        ObserveOnSubscriber localObserveOnSubscriber = new ObserveOnSubscriber(this.scheduler, paramSubscriber);
        localObserveOnSubscriber.init();
        paramSubscriber = localObserveOnSubscriber;
      }
    }
  }
  
  static final class ScheduledUnsubscribe
    implements Subscription
  {
    static final AtomicIntegerFieldUpdater<ScheduledUnsubscribe> ONCE_UPDATER = AtomicIntegerFieldUpdater.newUpdater(ScheduledUnsubscribe.class, "once");
    volatile int once;
    volatile boolean unsubscribed = false;
    final Scheduler.Worker worker;
    
    public ScheduledUnsubscribe(Scheduler.Worker paramWorker)
    {
      this.worker = paramWorker;
    }
    
    public boolean isUnsubscribed()
    {
      return this.unsubscribed;
    }
    
    public void unsubscribe()
    {
      if (ONCE_UPDATER.getAndSet(this, 1) == 0) {
        this.worker.schedule(new Action0()
        {
          public void call()
          {
            OperatorObserveOn.ScheduledUnsubscribe.this.worker.unsubscribe();
            OperatorObserveOn.ScheduledUnsubscribe.this.unsubscribed = true;
          }
        });
      }
    }
  }
  
  private static final class ObserveOnSubscriber<T>
    extends Subscriber<T>
  {
    static final AtomicLongFieldUpdater<ObserveOnSubscriber> COUNTER_UPDATER = AtomicLongFieldUpdater.newUpdater(ObserveOnSubscriber.class, "counter");
    static final AtomicLongFieldUpdater<ObserveOnSubscriber> REQUESTED = AtomicLongFieldUpdater.newUpdater(ObserveOnSubscriber.class, "requested");
    final Action0 action = new Action0()
    {
      public void call()
      {
        OperatorObserveOn.ObserveOnSubscriber.this.pollQueue();
      }
    };
    final Subscriber<? super T> child;
    volatile long counter;
    volatile Throwable error;
    volatile boolean finished = false;
    final NotificationLite<T> on = NotificationLite.instance();
    final Queue<Object> queue;
    final Scheduler.Worker recursiveScheduler;
    volatile long requested = 0L;
    final OperatorObserveOn.ScheduledUnsubscribe scheduledUnsubscribe;
    
    public ObserveOnSubscriber(Scheduler paramScheduler, Subscriber<? super T> paramSubscriber)
    {
      this.child = paramSubscriber;
      this.recursiveScheduler = paramScheduler.createWorker();
      if (UnsafeAccess.isUnsafeAvailable()) {}
      for (this.queue = new SpscArrayQueue(RxRingBuffer.SIZE);; this.queue = new SynchronizedQueue(RxRingBuffer.SIZE))
      {
        this.scheduledUnsubscribe = new OperatorObserveOn.ScheduledUnsubscribe(this.recursiveScheduler);
        return;
      }
    }
    
    void init()
    {
      this.child.add(this.scheduledUnsubscribe);
      this.child.setProducer(new Producer()
      {
        public void request(long paramAnonymousLong)
        {
          BackpressureUtils.getAndAddRequest(OperatorObserveOn.ObserveOnSubscriber.REQUESTED, OperatorObserveOn.ObserveOnSubscriber.this, paramAnonymousLong);
          OperatorObserveOn.ObserveOnSubscriber.this.schedule();
        }
      });
      this.child.add(this.recursiveScheduler);
      this.child.add(this);
    }
    
    public void onCompleted()
    {
      if ((isUnsubscribed()) || (this.finished)) {}
      for (;;)
      {
        return;
        this.finished = true;
        schedule();
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      if ((isUnsubscribed()) || (this.finished)) {}
      for (;;)
      {
        return;
        this.error = paramThrowable;
        unsubscribe();
        this.finished = true;
        schedule();
      }
    }
    
    public void onNext(T paramT)
    {
      if (isUnsubscribed()) {}
      for (;;)
      {
        return;
        if (!this.queue.offer(this.on.next(paramT))) {
          onError(new MissingBackpressureException());
        } else {
          schedule();
        }
      }
    }
    
    public void onStart()
    {
      request(RxRingBuffer.SIZE);
    }
    
    void pollQueue()
    {
      int i = 0;
      this.counter = 1L;
      long l1 = 0L;
      long l2 = this.requested;
      label15:
      if (this.child.isUnsubscribed()) {}
      for (;;)
      {
        return;
        if (this.finished)
        {
          Throwable localThrowable = this.error;
          if (localThrowable != null)
          {
            this.queue.clear();
            this.child.onError(localThrowable);
            continue;
          }
          if (this.queue.isEmpty())
          {
            this.child.onCompleted();
            continue;
          }
        }
        if (l2 > 0L)
        {
          Object localObject = this.queue.poll();
          if (localObject != null)
          {
            this.child.onNext(this.on.getValue(localObject));
            l2 -= 1L;
            i++;
            l1 += 1L;
            break label15;
          }
        }
        if ((l1 > 0L) && (this.requested != Long.MAX_VALUE)) {
          REQUESTED.addAndGet(this, -l1);
        }
        if (COUNTER_UPDATER.decrementAndGet(this) > 0L) {
          break;
        }
        if (i > 0) {
          request(i);
        }
      }
    }
    
    protected void schedule()
    {
      if (COUNTER_UPDATER.getAndIncrement(this) == 0L) {
        this.recursiveScheduler.schedule(this.action);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorObserveOn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */