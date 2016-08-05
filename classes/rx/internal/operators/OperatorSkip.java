package rx.internal.operators;

import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;

public final class OperatorSkip<T>
  implements Observable.Operator<T, T>
{
  final int toSkip;
  
  public OperatorSkip(int paramInt)
  {
    this.toSkip = paramInt;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
      int skipped = 0;
      
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
        if (this.skipped >= OperatorSkip.this.toSkip) {
          paramSubscriber.onNext(paramAnonymousT);
        }
        for (;;)
        {
          return;
          this.skipped = (1 + this.skipped);
        }
      }
      
      public void setProducer(Producer paramAnonymousProducer)
      {
        paramSubscriber.setProducer(paramAnonymousProducer);
        paramAnonymousProducer.request(OperatorSkip.this.toSkip);
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorSkip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */