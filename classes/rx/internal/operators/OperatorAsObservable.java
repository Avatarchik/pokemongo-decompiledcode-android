package rx.internal.operators;

import rx.Observable.Operator;
import rx.Subscriber;

public final class OperatorAsObservable<T>
  implements Observable.Operator<T, T>
{
  public static <T> OperatorAsObservable<T> instance()
  {
    return Holder.INSTANCE;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    return paramSubscriber;
  }
  
  private static final class Holder
  {
    static final OperatorAsObservable<Object> INSTANCE = new OperatorAsObservable(null);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorAsObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */