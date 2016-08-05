package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;

public final class OperatorTakeLastTimed<T>
  implements Observable.Operator<T, T>
{
  private final long ageMillis;
  private final int count;
  private final Scheduler scheduler;
  
  public OperatorTakeLastTimed(int paramInt, long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    if (paramInt < 0) {
      throw new IndexOutOfBoundsException("count could not be negative");
    }
    this.ageMillis = paramTimeUnit.toMillis(paramLong);
    this.scheduler = paramScheduler;
    this.count = paramInt;
  }
  
  public OperatorTakeLastTimed(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.ageMillis = paramTimeUnit.toMillis(paramLong);
    this.scheduler = paramScheduler;
    this.count = -1;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    final ArrayDeque localArrayDeque1 = new ArrayDeque();
    final ArrayDeque localArrayDeque2 = new ArrayDeque();
    final NotificationLite localNotificationLite = NotificationLite.instance();
    final TakeLastQueueProducer localTakeLastQueueProducer = new TakeLastQueueProducer(localNotificationLite, localArrayDeque1, paramSubscriber);
    paramSubscriber.setProducer(localTakeLastQueueProducer);
    new Subscriber(paramSubscriber)
    {
      public void onCompleted()
      {
        runEvictionPolicy(OperatorTakeLastTimed.this.scheduler.now());
        localArrayDeque2.clear();
        localArrayDeque1.offer(localNotificationLite.completed());
        localTakeLastQueueProducer.startEmitting();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        localArrayDeque2.clear();
        localArrayDeque1.clear();
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        long l = OperatorTakeLastTimed.this.scheduler.now();
        localArrayDeque2.add(Long.valueOf(l));
        localArrayDeque1.add(localNotificationLite.next(paramAnonymousT));
        runEvictionPolicy(l);
      }
      
      public void onStart()
      {
        request(Long.MAX_VALUE);
      }
      
      protected void runEvictionPolicy(long paramAnonymousLong)
      {
        while ((OperatorTakeLastTimed.this.count >= 0) && (localArrayDeque1.size() > OperatorTakeLastTimed.this.count))
        {
          localArrayDeque2.pollFirst();
          localArrayDeque1.pollFirst();
        }
        while ((!localArrayDeque1.isEmpty()) && (((Long)localArrayDeque2.peekFirst()).longValue() < paramAnonymousLong - OperatorTakeLastTimed.this.ageMillis))
        {
          localArrayDeque2.pollFirst();
          localArrayDeque1.pollFirst();
        }
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorTakeLastTimed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */