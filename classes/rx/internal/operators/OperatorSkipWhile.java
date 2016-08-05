package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

public final class OperatorSkipWhile<T>
  implements Observable.Operator<T, T>
{
  private final Func2<? super T, Integer, Boolean> predicate;
  
  public OperatorSkipWhile(Func2<? super T, Integer, Boolean> paramFunc2)
  {
    this.predicate = paramFunc2;
  }
  
  public static <T> Func2<T, Integer, Boolean> toPredicate2(Func1<? super T, Boolean> paramFunc1)
  {
    new Func2()
    {
      public Boolean call(T paramAnonymousT, Integer paramAnonymousInteger)
      {
        return (Boolean)OperatorSkipWhile.this.call(paramAnonymousT);
      }
    };
  }
  
  public Subscriber<? super T> call(final Subscriber<? super T> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
      int index;
      boolean skipping = true;
      
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
        if (!this.skipping) {
          paramSubscriber.onNext(paramAnonymousT);
        }
        for (;;)
        {
          return;
          Func2 localFunc2 = OperatorSkipWhile.this.predicate;
          int i = this.index;
          this.index = (i + 1);
          if (!((Boolean)localFunc2.call(paramAnonymousT, Integer.valueOf(i))).booleanValue())
          {
            this.skipping = false;
            paramSubscriber.onNext(paramAnonymousT);
          }
          else
          {
            request(1L);
          }
        }
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorSkipWhile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */