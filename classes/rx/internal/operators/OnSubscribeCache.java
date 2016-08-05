package rx.internal.operators;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

public final class OnSubscribeCache<T>
  implements Observable.OnSubscribe<T>
{
  static final AtomicIntegerFieldUpdater<OnSubscribeCache> SRC_SUBSCRIBED_UPDATER = AtomicIntegerFieldUpdater.newUpdater(OnSubscribeCache.class, "sourceSubscribed");
  protected final Subject<? super T, ? extends T> cache;
  protected final Observable<? extends T> source;
  volatile int sourceSubscribed;
  
  public OnSubscribeCache(Observable<? extends T> paramObservable)
  {
    this(paramObservable, ReplaySubject.create());
  }
  
  public OnSubscribeCache(Observable<? extends T> paramObservable, int paramInt)
  {
    this(paramObservable, ReplaySubject.create(paramInt));
  }
  
  OnSubscribeCache(Observable<? extends T> paramObservable, Subject<? super T, ? extends T> paramSubject)
  {
    this.source = paramObservable;
    this.cache = paramSubject;
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    if (SRC_SUBSCRIBED_UPDATER.compareAndSet(this, 0, 1)) {
      this.source.subscribe(this.cache);
    }
    this.cache.unsafeSubscribe(paramSubscriber);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OnSubscribeCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */