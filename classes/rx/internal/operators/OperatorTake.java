package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;

public final class OperatorTake<T>
  implements Observable.Operator<T, T>
{
  final int limit;
  
  public OperatorTake(int paramInt)
  {
    this.limit = paramInt;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    Subscriber local1 = new Subscriber()
    {
      boolean completed = false;
      int count = 0;
      
      public void onCompleted()
      {
        if (!this.completed) {
          paramSubscriber.onCompleted();
        }
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        if (!this.completed) {
          paramSubscriber.onError(paramAnonymousThrowable);
        }
      }
      
      public void onNext(T paramAnonymousT)
      {
        if (!isUnsubscribed())
        {
          int i = 1 + this.count;
          this.count = i;
          if (i >= OperatorTake.this.limit) {
            this.completed = true;
          }
          paramSubscriber.onNext(paramAnonymousT);
          if (this.completed)
          {
            paramSubscriber.onCompleted();
            unsubscribe();
          }
        }
      }
      
      public void setProducer(final Producer paramAnonymousProducer)
      {
        paramSubscriber.setProducer(new Producer()
        {
          final AtomicLong requested = new AtomicLong(0L);
          
          public void request(long paramAnonymous2Long)
          {
            if ((paramAnonymous2Long > 0L) && (!OperatorTake.1.this.completed)) {}
            for (;;)
            {
              long l1 = this.requested.get();
              long l2 = Math.min(paramAnonymous2Long, OperatorTake.this.limit - l1);
              if (l2 == 0L) {}
              for (;;)
              {
                return;
                if (!this.requested.compareAndSet(l1, l1 + l2)) {
                  break;
                }
                paramAnonymousProducer.request(l2);
              }
            }
          }
        });
      }
    };
    if (this.limit == 0)
    {
      paramSubscriber.onCompleted();
      local1.unsubscribe();
    }
    paramSubscriber.add(local1);
    return local1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorTake.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */