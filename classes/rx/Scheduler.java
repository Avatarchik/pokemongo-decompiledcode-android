package rx;

import java.util.concurrent.TimeUnit;
import rx.functions.Action0;
import rx.subscriptions.MultipleAssignmentSubscription;

public abstract class Scheduler
{
  public abstract Worker createWorker();
  
  public long now()
  {
    return System.currentTimeMillis();
  }
  
  public static abstract class Worker
    implements Subscription
  {
    public long now()
    {
      return System.currentTimeMillis();
    }
    
    public abstract Subscription schedule(Action0 paramAction0);
    
    public abstract Subscription schedule(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit);
    
    public Subscription schedulePeriodically(final Action0 paramAction0, long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      long l1 = paramTimeUnit.toNanos(paramLong2);
      final long l2 = TimeUnit.MILLISECONDS.toNanos(now()) + paramTimeUnit.toNanos(paramLong1);
      final MultipleAssignmentSubscription localMultipleAssignmentSubscription1 = new MultipleAssignmentSubscription();
      Action0 local1 = new Action0()
      {
        long count = 0L;
        
        public void call()
        {
          if (!localMultipleAssignmentSubscription1.isUnsubscribed())
          {
            paramAction0.call();
            long l1 = l2;
            long l2 = 1L + this.count;
            this.count = l2;
            long l3 = l1 + l2 * this.val$periodInNanos;
            localMultipleAssignmentSubscription1.set(Scheduler.Worker.this.schedule(this, l3 - TimeUnit.MILLISECONDS.toNanos(Scheduler.Worker.this.now()), TimeUnit.NANOSECONDS));
          }
        }
      };
      MultipleAssignmentSubscription localMultipleAssignmentSubscription2 = new MultipleAssignmentSubscription();
      localMultipleAssignmentSubscription1.set(localMultipleAssignmentSubscription2);
      localMultipleAssignmentSubscription2.set(schedule(local1, paramLong1, paramTimeUnit));
      return localMultipleAssignmentSubscription1;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/Scheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */