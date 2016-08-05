package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.internal.producers.SingleDelayedProducer;

public final class OperatorAny<T>
  implements Observable.Operator<Boolean, T>
{
  private final Func1<? super T, Boolean> predicate;
  private final boolean returnOnEmpty;
  
  public OperatorAny(Func1<? super T, Boolean> paramFunc1, boolean paramBoolean)
  {
    this.predicate = paramFunc1;
    this.returnOnEmpty = paramBoolean;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super Boolean> paramSubscriber)
  {
    final SingleDelayedProducer localSingleDelayedProducer = new SingleDelayedProducer(paramSubscriber);
    Subscriber local1 = new Subscriber()
    {
      boolean done;
      boolean hasElements;
      
      public void onCompleted()
      {
        if (!this.done)
        {
          this.done = true;
          if (!this.hasElements) {
            break label31;
          }
          localSingleDelayedProducer.setValue(Boolean.valueOf(false));
        }
        for (;;)
        {
          return;
          label31:
          localSingleDelayedProducer.setValue(Boolean.valueOf(OperatorAny.this.returnOnEmpty));
        }
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        this.hasElements = true;
        try
        {
          boolean bool1 = ((Boolean)OperatorAny.this.predicate.call(paramAnonymousT)).booleanValue();
          if ((!bool1) || (this.done)) {
            break label74;
          }
          this.done = true;
          localSingleDelayedProducer = localSingleDelayedProducer;
          if (OperatorAny.this.returnOnEmpty) {
            break label92;
          }
          bool2 = true;
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            SingleDelayedProducer localSingleDelayedProducer;
            Exceptions.throwIfFatal(localThrowable);
            onError(OnErrorThrowable.addValueAsLastCause(localThrowable, paramAnonymousT));
            continue;
            boolean bool2 = false;
          }
        }
        localSingleDelayedProducer.setValue(Boolean.valueOf(bool2));
        unsubscribe();
        label74:
      }
    };
    paramSubscriber.add(local1);
    paramSubscriber.setProducer(localSingleDelayedProducer);
    return local1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorAny.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */