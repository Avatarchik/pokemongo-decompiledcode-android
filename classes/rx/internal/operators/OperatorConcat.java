package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.internal.producers.ProducerArbiter;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;

public final class OperatorConcat<T>
  implements Observable.Operator<T, Observable<? extends T>>
{
  public static <T> OperatorConcat<T> instance()
  {
    return Holder.INSTANCE;
  }
  
  public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    SerialSubscription localSerialSubscription = new SerialSubscription();
    paramSubscriber.add(localSerialSubscription);
    ConcatSubscriber localConcatSubscriber = new ConcatSubscriber(localSerializedSubscriber, localSerialSubscription);
    paramSubscriber.setProducer(new ConcatProducer(localConcatSubscriber));
    return localConcatSubscriber;
  }
  
  static class ConcatInnerSubscriber<T>
    extends Subscriber<T>
  {
    private static final AtomicIntegerFieldUpdater<ConcatInnerSubscriber> ONCE = AtomicIntegerFieldUpdater.newUpdater(ConcatInnerSubscriber.class, "once");
    private final ProducerArbiter arbiter;
    private final Subscriber<T> child;
    private volatile int once = 0;
    private final OperatorConcat.ConcatSubscriber<T> parent;
    
    public ConcatInnerSubscriber(OperatorConcat.ConcatSubscriber<T> paramConcatSubscriber, Subscriber<T> paramSubscriber, ProducerArbiter paramProducerArbiter)
    {
      this.parent = paramConcatSubscriber;
      this.child = paramSubscriber;
      this.arbiter = paramProducerArbiter;
    }
    
    public void onCompleted()
    {
      if (ONCE.compareAndSet(this, 0, 1)) {
        this.parent.completeInner();
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      if (ONCE.compareAndSet(this, 0, 1)) {
        this.parent.onError(paramThrowable);
      }
    }
    
    public void onNext(T paramT)
    {
      this.child.onNext(paramT);
      OperatorConcat.ConcatSubscriber.access$200(this.parent);
      this.arbiter.produced(1L);
    }
    
    public void setProducer(Producer paramProducer)
    {
      this.arbiter.setProducer(paramProducer);
    }
  }
  
  static final class ConcatSubscriber<T>
    extends Subscriber<Observable<? extends T>>
  {
    private static final AtomicLongFieldUpdater<ConcatSubscriber> REQUESTED = AtomicLongFieldUpdater.newUpdater(ConcatSubscriber.class, "requested");
    static final AtomicIntegerFieldUpdater<ConcatSubscriber> WIP = AtomicIntegerFieldUpdater.newUpdater(ConcatSubscriber.class, "wip");
    private final ProducerArbiter arbiter;
    private final Subscriber<T> child;
    private final SerialSubscription current;
    volatile OperatorConcat.ConcatInnerSubscriber<T> currentSubscriber;
    final NotificationLite<Observable<? extends T>> nl = NotificationLite.instance();
    final ConcurrentLinkedQueue<Object> queue;
    private volatile long requested;
    volatile int wip;
    
    public ConcatSubscriber(Subscriber<T> paramSubscriber, SerialSubscription paramSerialSubscription)
    {
      super();
      this.child = paramSubscriber;
      this.current = paramSerialSubscription;
      this.arbiter = new ProducerArbiter();
      this.queue = new ConcurrentLinkedQueue();
      add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          OperatorConcat.ConcatSubscriber.this.queue.clear();
        }
      }));
    }
    
    private void decrementRequested()
    {
      REQUESTED.decrementAndGet(this);
    }
    
    private void requestFromChild(long paramLong)
    {
      if (paramLong <= 0L) {}
      for (;;)
      {
        return;
        long l = BackpressureUtils.getAndAddRequest(REQUESTED, this, paramLong);
        this.arbiter.request(paramLong);
        if ((l == 0L) && (this.currentSubscriber == null) && (this.wip > 0)) {
          subscribeNext();
        }
      }
    }
    
    void completeInner()
    {
      this.currentSubscriber = null;
      if (WIP.decrementAndGet(this) > 0) {
        subscribeNext();
      }
      request(1L);
    }
    
    public void onCompleted()
    {
      this.queue.add(this.nl.completed());
      if (WIP.getAndIncrement(this) == 0) {
        subscribeNext();
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.child.onError(paramThrowable);
      unsubscribe();
    }
    
    public void onNext(Observable<? extends T> paramObservable)
    {
      this.queue.add(this.nl.next(paramObservable));
      if (WIP.getAndIncrement(this) == 0) {
        subscribeNext();
      }
    }
    
    public void onStart()
    {
      request(2L);
    }
    
    void subscribeNext()
    {
      Object localObject2;
      if (this.requested > 0L)
      {
        localObject2 = this.queue.poll();
        if (this.nl.isCompleted(localObject2)) {
          this.child.onCompleted();
        }
      }
      for (;;)
      {
        return;
        if (localObject2 != null)
        {
          Observable localObservable = (Observable)this.nl.getValue(localObject2);
          this.currentSubscriber = new OperatorConcat.ConcatInnerSubscriber(this, this.child, this.arbiter);
          this.current.set(this.currentSubscriber);
          localObservable.unsafeSubscribe(this.currentSubscriber);
          continue;
          Object localObject1 = this.queue.peek();
          if (this.nl.isCompleted(localObject1)) {
            this.child.onCompleted();
          }
        }
      }
    }
  }
  
  static final class ConcatProducer<T>
    implements Producer
  {
    final OperatorConcat.ConcatSubscriber<T> cs;
    
    ConcatProducer(OperatorConcat.ConcatSubscriber<T> paramConcatSubscriber)
    {
      this.cs = paramConcatSubscriber;
    }
    
    public void request(long paramLong)
    {
      this.cs.requestFromChild(paramLong);
    }
  }
  
  private static final class Holder
  {
    static final OperatorConcat<Object> INSTANCE = new OperatorConcat(null);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorConcat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */