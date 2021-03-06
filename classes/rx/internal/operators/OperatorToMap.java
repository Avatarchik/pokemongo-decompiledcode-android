package rx.internal.operators;

import java.util.HashMap;
import java.util.Map;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;

public final class OperatorToMap<T, K, V>
  implements Observable.Operator<Map<K, V>, T>
{
  private final Func1<? super T, ? extends K> keySelector;
  private final Func0<? extends Map<K, V>> mapFactory;
  private final Func1<? super T, ? extends V> valueSelector;
  
  public OperatorToMap(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends V> paramFunc11)
  {
    this(paramFunc1, paramFunc11, new DefaultToMapFactory());
  }
  
  public OperatorToMap(Func1<? super T, ? extends K> paramFunc1, Func1<? super T, ? extends V> paramFunc11, Func0<? extends Map<K, V>> paramFunc0)
  {
    this.keySelector = paramFunc1;
    this.valueSelector = paramFunc11;
    this.mapFactory = paramFunc0;
  }
  
  public Subscriber<? super T> call(final Subscriber<? super Map<K, V>> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
      private Map<K, V> map = (Map)OperatorToMap.this.mapFactory.call();
      
      public void onCompleted()
      {
        Map localMap = this.map;
        this.map = null;
        paramSubscriber.onNext(localMap);
        paramSubscriber.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        this.map = null;
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        Object localObject1 = OperatorToMap.this.keySelector.call(paramAnonymousT);
        Object localObject2 = OperatorToMap.this.valueSelector.call(paramAnonymousT);
        this.map.put(localObject1, localObject2);
      }
      
      public void onStart()
      {
        request(Long.MAX_VALUE);
      }
    };
  }
  
  public static final class DefaultToMapFactory<K, V>
    implements Func0<Map<K, V>>
  {
    public Map<K, V> call()
    {
      return new HashMap();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorToMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */