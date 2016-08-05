package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable.OnSubscribe;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;

public final class OnSubscribeTimerPeriodically
  implements Observable.OnSubscribe<Long>
{
  final long initialDelay;
  final long period;
  final Scheduler scheduler;
  final TimeUnit unit;
  
  public OnSubscribeTimerPeriodically(long paramLong1, long paramLong2, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.initialDelay = paramLong1;
    this.period = paramLong2;
    this.unit = paramTimeUnit;
    this.scheduler = paramScheduler;
  }
  
  public void call(final Subscriber<? super Long> paramSubscriber)
  {
    final Scheduler.Worker localWorker = this.scheduler.createWorker();
    paramSubscriber.add(localWorker);
    localWorker.schedulePeriodically(new Action0()
    {
      long counter;
      
      /* Error */
      public void call()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 25	rx/internal/operators/OnSubscribeTimerPeriodically$1:val$child	Lrx/Subscriber;
        //   4: astore_3
        //   5: aload_0
        //   6: getfield 34	rx/internal/operators/OnSubscribeTimerPeriodically$1:counter	J
        //   9: lstore 4
        //   11: aload_0
        //   12: lconst_1
        //   13: lload 4
        //   15: ladd
        //   16: putfield 34	rx/internal/operators/OnSubscribeTimerPeriodically$1:counter	J
        //   19: aload_3
        //   20: lload 4
        //   22: invokestatic 40	java/lang/Long:valueOf	(J)Ljava/lang/Long;
        //   25: invokevirtual 46	rx/Subscriber:onNext	(Ljava/lang/Object;)V
        //   28: return
        //   29: astore_1
        //   30: aload_0
        //   31: getfield 25	rx/internal/operators/OnSubscribeTimerPeriodically$1:val$child	Lrx/Subscriber;
        //   34: aload_1
        //   35: invokevirtual 50	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
        //   38: aload_0
        //   39: getfield 27	rx/internal/operators/OnSubscribeTimerPeriodically$1:val$worker	Lrx/Scheduler$Worker;
        //   42: invokevirtual 55	rx/Scheduler$Worker:unsubscribe	()V
        //   45: goto -17 -> 28
        //   48: astore_2
        //   49: aload_0
        //   50: getfield 27	rx/internal/operators/OnSubscribeTimerPeriodically$1:val$worker	Lrx/Scheduler$Worker;
        //   53: invokevirtual 55	rx/Scheduler$Worker:unsubscribe	()V
        //   56: aload_2
        //   57: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	58	0	this	1
        //   29	6	1	localThrowable	Throwable
        //   48	9	2	localObject	Object
        //   4	16	3	localSubscriber	Subscriber
        //   9	12	4	l	long
        // Exception table:
        //   from	to	target	type
        //   0	28	29	java/lang/Throwable
        //   30	38	48	finally
      }
    }, this.initialDelay, this.period, this.unit);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OnSubscribeTimerPeriodically.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */