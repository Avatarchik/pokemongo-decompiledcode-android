package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;

public class OperatorTakeLastOne<T>
  implements Observable.Operator<T, T>
{
  public static <T> OperatorTakeLastOne<T> instance()
  {
    return Holder.INSTANCE;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    final ParentSubscriber localParentSubscriber = new ParentSubscriber(paramSubscriber);
    paramSubscriber.setProducer(new Producer()
    {
      public void request(long paramAnonymousLong)
      {
        localParentSubscriber.requestMore(paramAnonymousLong);
      }
    });
    paramSubscriber.add(localParentSubscriber);
    return localParentSubscriber;
  }
  
  private static class ParentSubscriber<T>
    extends Subscriber<T>
  {
    private static final Object ABSENT = new Object();
    private static final int NOT_REQUESTED_COMPLETED = 1;
    private static final int NOT_REQUESTED_NOT_COMPLETED = 0;
    private static final int REQUESTED_COMPLETED = 3;
    private static final int REQUESTED_NOT_COMPLETED = 2;
    private final Subscriber<? super T> child;
    private T last = ABSENT;
    private final AtomicInteger state = new AtomicInteger(0);
    
    ParentSubscriber(Subscriber<? super T> paramSubscriber)
    {
      this.child = paramSubscriber;
    }
    
    private void emit()
    {
      if (isUnsubscribed()) {
        this.last = null;
      }
      for (;;)
      {
        return;
        Object localObject = this.last;
        this.last = null;
        if (localObject != ABSENT) {}
        try
        {
          this.child.onNext(localObject);
          if (!isUnsubscribed()) {
            this.child.onCompleted();
          }
        }
        catch (Throwable localThrowable)
        {
          this.child.onError(localThrowable);
        }
      }
    }
    
    public void onCompleted()
    {
      if (this.last == ABSENT) {
        this.child.onCompleted();
      }
      for (;;)
      {
        return;
        do
        {
          int i;
          do
          {
            i = this.state.get();
            if (i != 0) {
              break;
            }
          } while (!this.state.compareAndSet(0, 1));
          break;
          if (i != 2) {
            break;
          }
        } while (!this.state.compareAndSet(2, 3));
        emit();
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.child.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      this.last = paramT;
    }
    
    void requestMore(long paramLong)
    {
      int i;
      if (paramLong > 0L) {
        do
        {
          i = this.state.get();
          if (i != 0) {
            break;
          }
        } while (!this.state.compareAndSet(0, 2));
      }
      for (;;)
      {
        return;
        if (i == 1)
        {
          if (!this.state.compareAndSet(1, 3)) {
            break;
          }
          emit();
        }
      }
    }
  }
  
  private static class Holder
  {
    static final OperatorTakeLastOne<Object> INSTANCE = new OperatorTakeLastOne(null);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorTakeLastOne.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */