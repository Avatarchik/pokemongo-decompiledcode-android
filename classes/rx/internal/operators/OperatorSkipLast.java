package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import rx.Observable.Operator;
import rx.Subscriber;

public class OperatorSkipLast<T>
  implements Observable.Operator<T, T>
{
  private final int count;
  
  public OperatorSkipLast(int paramInt)
  {
    if (paramInt < 0) {
      throw new IndexOutOfBoundsException("count could not be negative");
    }
    this.count = paramInt;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
      private final Deque<Object> deque = new ArrayDeque();
      private final NotificationLite<T> on = NotificationLite.instance();
      
      public void onCompleted()
      {
        paramSubscriber.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        if (OperatorSkipLast.this.count == 0)
        {
          paramSubscriber.onNext(paramAnonymousT);
          return;
        }
        if (this.deque.size() == OperatorSkipLast.this.count) {
          paramSubscriber.onNext(this.on.getValue(this.deque.removeFirst()));
        }
        for (;;)
        {
          this.deque.offerLast(this.on.next(paramAnonymousT));
          break;
          request(1L);
        }
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorSkipLast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */