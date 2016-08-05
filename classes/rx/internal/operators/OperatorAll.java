package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.internal.producers.SingleDelayedProducer;

public final class OperatorAll<T>
  implements Observable.Operator<Boolean, T>
{
  private final Func1<? super T, Boolean> predicate;
  
  public OperatorAll(Func1<? super T, Boolean> paramFunc1)
  {
    this.predicate = paramFunc1;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super Boolean> paramSubscriber)
  {
    final SingleDelayedProducer localSingleDelayedProducer = new SingleDelayedProducer(paramSubscriber);
    Subscriber local1 = new Subscriber()
    {
      boolean done;
      
      public void onCompleted()
      {
        if (!this.done)
        {
          this.done = true;
          localSingleDelayedProducer.setValue(Boolean.valueOf(true));
        }
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        try
        {
          Boolean localBoolean = (Boolean)OperatorAll.this.predicate.call(paramAnonymousT);
          if ((!localBoolean.booleanValue()) && (!this.done))
          {
            this.done = true;
            localSingleDelayedProducer.setValue(Boolean.valueOf(false));
            unsubscribe();
          }
          return;
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            Exceptions.throwIfFatal(localThrowable);
            onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramAnonymousT));
          }
        }
      }
    };
    paramSubscriber.add(local1);
    paramSubscriber.setProducer(localSingleDelayedProducer);
    return local1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorAll.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */