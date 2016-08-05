package rx.internal.operators;

import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action1;

public class OperatorDoOnRequest<T>
  implements Observable.Operator<T, T>
{
  private final Action1<Long> request;
  
  public OperatorDoOnRequest(Action1<Long> paramAction1)
  {
    this.request = paramAction1;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    final ParentSubscriber localParentSubscriber = new ParentSubscriber(paramSubscriber, null);
    paramSubscriber.setProducer(new Producer()
    {
      public void request(long paramAnonymousLong)
      {
        OperatorDoOnRequest.this.request.call(Long.valueOf(paramAnonymousLong));
        OperatorDoOnRequest.ParentSubscriber.access$200(localParentSubscriber, paramAnonymousLong);
      }
    });
    paramSubscriber.add(localParentSubscriber);
    return localParentSubscriber;
  }
  
  private static final class ParentSubscriber<T>
    extends Subscriber<T>
  {
    private final Subscriber<? super T> child;
    
    private ParentSubscriber(Subscriber<? super T> paramSubscriber)
    {
      this.child = paramSubscriber;
    }
    
    private void requestMore(long paramLong)
    {
      request(paramLong);
    }
    
    public void onCompleted()
    {
      this.child.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.child.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      this.child.onNext(paramT);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorDoOnRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */