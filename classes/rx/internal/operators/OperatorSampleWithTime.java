package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observers.SerializedSubscriber;

public final class OperatorSampleWithTime<T>
  implements Observable.Operator<T, T>
{
  final Scheduler scheduler;
  final long time;
  final TimeUnit unit;
  
  public OperatorSampleWithTime(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.time = paramLong;
    this.unit = paramTimeUnit;
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    SamplerSubscriber localSamplerSubscriber = new SamplerSubscriber(localSerializedSubscriber);
    paramSubscriber.add(localSamplerSubscriber);
    localWorker.schedulePeriodically(localSamplerSubscriber, this.time, this.time, this.unit);
    return localSamplerSubscriber;
  }
  
  static final class SamplerSubscriber<T>
    extends Subscriber<T>
    implements Action0
  {
    private static final Object EMPTY_TOKEN = new Object();
    static final AtomicReferenceFieldUpdater<SamplerSubscriber, Object> VALUE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(SamplerSubscriber.class, Object.class, "value");
    private final Subscriber<? super T> subscriber;
    volatile Object value = EMPTY_TOKEN;
    
    public SamplerSubscriber(Subscriber<? super T> paramSubscriber)
    {
      this.subscriber = paramSubscriber;
    }
    
    public void call()
    {
      Object localObject = VALUE_UPDATER.getAndSet(this, EMPTY_TOKEN);
      if (localObject != EMPTY_TOKEN) {}
      try
      {
        this.subscriber.onNext(localObject);
        return;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          onError(localThrowable);
        }
      }
    }
    
    public void onCompleted()
    {
      this.subscriber.onCompleted();
      unsubscribe();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.subscriber.onError(paramThrowable);
      unsubscribe();
    }
    
    public void onNext(T paramT)
    {
      this.value = paramT;
    }
    
    public void onStart()
    {
      request(Long.MAX_VALUE);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorSampleWithTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */