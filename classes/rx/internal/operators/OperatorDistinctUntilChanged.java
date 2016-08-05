package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;

public final class OperatorDistinctUntilChanged<T, U>
  implements Observable.Operator<T, T>
{
  final Func1<? super T, ? extends U> keySelector;
  
  public OperatorDistinctUntilChanged(Func1<? super T, ? extends U> paramFunc1)
  {
    this.keySelector = paramFunc1;
  }
  
  public static <T> OperatorDistinctUntilChanged<T, T> instance()
  {
    return Holder.INSTANCE;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
      boolean hasPrevious;
      U previousKey;
      
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
        Object localObject1 = this.previousKey;
        Object localObject2 = OperatorDistinctUntilChanged.this.keySelector.call(paramAnonymousT);
        this.previousKey = localObject2;
        if (this.hasPrevious) {
          if ((localObject1 != localObject2) && ((localObject2 == null) || (!localObject2.equals(localObject1)))) {
            paramSubscriber.onNext(paramAnonymousT);
          }
        }
        for (;;)
        {
          return;
          request(1L);
          continue;
          this.hasPrevious = true;
          paramSubscriber.onNext(paramAnonymousT);
        }
      }
    };
  }
  
  private static class Holder
  {
    static final OperatorDistinctUntilChanged<?, ?> INSTANCE = new OperatorDistinctUntilChanged(UtilityFunctions.identity());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorDistinctUntilChanged.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */