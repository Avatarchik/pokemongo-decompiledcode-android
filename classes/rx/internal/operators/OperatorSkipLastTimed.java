package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Timestamped;

public class OperatorSkipLastTimed<T>
  implements Observable.Operator<T, T>
{
  private final Scheduler scheduler;
  private final long timeInMillis;
  
  public OperatorSkipLastTimed(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.timeInMillis = paramTimeUnit.toMillis(paramLong);
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
      private Deque<Timestamped<T>> buffer = new ArrayDeque();
      
      private void emitItemsOutOfWindow(long paramAnonymousLong)
      {
        long l = paramAnonymousLong - OperatorSkipLastTimed.this.timeInMillis;
        while (!this.buffer.isEmpty())
        {
          Timestamped localTimestamped = (Timestamped)this.buffer.getFirst();
          if (localTimestamped.getTimestampMillis() >= l) {
            break;
          }
          this.buffer.removeFirst();
          paramSubscriber.onNext(localTimestamped.getValue());
        }
      }
      
      public void onCompleted()
      {
        emitItemsOutOfWindow(OperatorSkipLastTimed.this.scheduler.now());
        paramSubscriber.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        long l = OperatorSkipLastTimed.this.scheduler.now();
        emitItemsOutOfWindow(l);
        this.buffer.offerLast(new Timestamped(l, paramAnonymousT));
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorSkipLastTimed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */