package rx.internal.operators;

import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;
import rx.subscriptions.SerialSubscription;

public final class OperatorOnErrorResumeNextViaFunction<T>
  implements Observable.Operator<T, T>
{
  private final Func1<Throwable, ? extends Observable<? extends T>> resumeFunction;
  
  public OperatorOnErrorResumeNextViaFunction(Func1<Throwable, ? extends Observable<? extends T>> paramFunc1)
  {
    this.resumeFunction = paramFunc1;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    final ProducerArbiter localProducerArbiter = new ProducerArbiter();
    final SerialSubscription localSerialSubscription = new SerialSubscription();
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
          try
          {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(paramAnonymousThrowable);
            unsubscribe();
            Subscriber local1 = new Subscriber()
            {
              public void onCompleted()
              {
                OperatorOnErrorResumeNextViaFunction.1.this.val$child.onCompleted();
              }
              
              public void onError(Throwable paramAnonymous2Throwable)
              {
                OperatorOnErrorResumeNextViaFunction.1.this.val$child.onError(paramAnonymous2Throwable);
              }
              
              public void onNext(T paramAnonymous2T)
              {
                OperatorOnErrorResumeNextViaFunction.1.this.val$child.onNext(paramAnonymous2T);
              }
              
              public void setProducer(Producer paramAnonymous2Producer)
              {
                OperatorOnErrorResumeNextViaFunction.1.this.val$pa.setProducer(paramAnonymous2Producer);
              }
            };
            localSerialSubscription.set(local1);
            ((Observable)OperatorOnErrorResumeNextViaFunction.this.resumeFunction.call(paramAnonymousThrowable)).unsafeSubscribe(local1);
          }
          catch (Throwable localThrowable)
          {
            paramSubscriber.onError(localThrowable);
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
      
      public void setProducer(Producer paramAnonymousProducer)
      {
        localProducerArbiter.setProducer(paramAnonymousProducer);
      }
    };
    paramSubscriber.add(localSerialSubscription);
    localSerialSubscription.set(local1);
    paramSubscriber.setProducer(localProducerArbiter);
    return local1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorOnErrorResumeNextViaFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */