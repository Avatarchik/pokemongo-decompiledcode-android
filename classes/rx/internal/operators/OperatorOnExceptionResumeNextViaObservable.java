package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

public final class OperatorOnExceptionResumeNextViaObservable<T>
  implements Observable.Operator<T, T>
{
  final Observable<? extends T> resumeSequence;
  
  public OperatorOnExceptionResumeNextViaObservable(Observable<? extends T> paramObservable)
  {
    this.resumeSequence = paramObservable;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    Subscriber local1 = new Subscriber()
    {
      private boolean done = false;
      
      public void onCompleted()
      {
        if (this.done) {}
        for (;;)
        {
          return;
          this.done = true;
          paramSubscriber.onCompleted();
        }
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        if (this.done) {
          Exceptions.throwIfFatal(paramAnonymousThrowable);
        }
        for (;;)
        {
          return;
          this.done = true;
          if ((paramAnonymousThrowable instanceof Exception))
          {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(paramAnonymousThrowable);
            unsubscribe();
            OperatorOnExceptionResumeNextViaObservable.this.resumeSequence.unsafeSubscribe(paramSubscriber);
          }
          else
          {
            paramSubscriber.onError(paramAnonymousThrowable);
          }
        }
      }
      
      public void onNext(T paramAnonymousT)
      {
        if (this.done) {}
        for (;;)
        {
          return;
          paramSubscriber.onNext(paramAnonymousT);
        }
      }
      
      public void setProducer(final Producer paramAnonymousProducer)
      {
        paramSubscriber.setProducer(new Producer()
        {
          public void request(long paramAnonymous2Long)
          {
            paramAnonymousProducer.request(paramAnonymous2Long);
          }
        });
      }
    };
    paramSubscriber.add(local1);
    return local1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorOnExceptionResumeNextViaObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */