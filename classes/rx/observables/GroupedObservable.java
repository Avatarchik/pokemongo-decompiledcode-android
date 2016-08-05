package rx.observables;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public class GroupedObservable<K, T>
  extends Observable<T>
{
  private final K key;
  
  protected GroupedObservable(K paramK, Observable.OnSubscribe<T> paramOnSubscribe)
  {
    super(paramOnSubscribe);
    this.key = paramK;
  }
  
  public static final <K, T> GroupedObservable<K, T> create(K paramK, Observable.OnSubscribe<T> paramOnSubscribe)
  {
    return new GroupedObservable(paramK, paramOnSubscribe);
  }
  
  public static <K, T> GroupedObservable<K, T> from(K paramK, Observable<T> paramObservable)
  {
    new GroupedObservable(paramK, new Observable.OnSubscribe()
    {
      public void call(Subscriber<? super T> paramAnonymousSubscriber)
      {
        GroupedObservable.this.unsafeSubscribe(paramAnonymousSubscriber);
      }
    });
  }
  
  public K getKey()
  {
    return (K)this.key;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/observables/GroupedObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */