package rx.internal.operators;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.util.UtilityFunctions;

public final class OperatorSequenceEqual
{
  private static final Object LOCAL_ONCOMPLETED = new Object();
  
  private OperatorSequenceEqual()
  {
    throw new IllegalStateException("No instances!");
  }
  
  static <T> Observable<Object> materializeLite(Observable<T> paramObservable)
  {
    Observable.concat(paramObservable.map(new Func1()
    {
      public Object call(T paramAnonymousT)
      {
        return paramAnonymousT;
      }
    }), Observable.just(LOCAL_ONCOMPLETED));
  }
  
  public static <T> Observable<Boolean> sequenceEqual(Observable<? extends T> paramObservable1, Observable<? extends T> paramObservable2, Func2<? super T, ? super T, Boolean> paramFunc2)
  {
    Observable.zip(materializeLite(paramObservable1), materializeLite(paramObservable2), new Func2()
    {
      public Boolean call(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        int i;
        int j;
        label19:
        Boolean localBoolean;
        if (paramAnonymousObject1 == OperatorSequenceEqual.LOCAL_ONCOMPLETED)
        {
          i = 1;
          if (paramAnonymousObject2 != OperatorSequenceEqual.LOCAL_ONCOMPLETED) {
            break label42;
          }
          j = 1;
          if ((i == 0) || (j == 0)) {
            break label48;
          }
          localBoolean = Boolean.valueOf(true);
        }
        for (;;)
        {
          return localBoolean;
          i = 0;
          break;
          label42:
          j = 0;
          break label19;
          label48:
          if ((i != 0) || (j != 0)) {
            localBoolean = Boolean.valueOf(false);
          } else {
            localBoolean = (Boolean)OperatorSequenceEqual.this.call(paramAnonymousObject1, paramAnonymousObject2);
          }
        }
      }
    }).all(UtilityFunctions.identity());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorSequenceEqual.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */