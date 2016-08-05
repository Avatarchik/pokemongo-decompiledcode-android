package rx.internal.operators;

import java.util.Arrays;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

public final class OperatorOnErrorReturn<T>
  implements Observable.Operator<T, T>
{
  final Func1<Throwable, ? extends T> resultFunction;
  
  public OperatorOnErrorReturn(Func1<Throwable, ? extends T> paramFunc1)
  {
    this.resultFunction = paramFunc1;
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
          try
          {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(paramAnonymousThrowable);
            unsubscribe();
            Object localObject = OperatorOnErrorReturn.this.resultFunction.call(paramAnonymousThrowable);
            paramSubscriber.onNext(localObject);
            paramSubscriber.onCompleted();
          }
          catch (Throwable localThrowable)
          {
            Subscriber localSubscriber = paramSubscriber;
            Throwable[] arrayOfThrowable = new Throwable[2];
            arrayOfThrowable[0] = paramAnonymousThrowable;
            arrayOfThrowable[1] = localThrowable;
            localSubscriber.onError(new CompositeException(Arrays.asList(arrayOfThrowable)));
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorOnErrorReturn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */