package rx.internal.operators;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observers.Subscribers;

public final class OnSubscribeUsing<T, Resource>
  implements Observable.OnSubscribe<T>
{
  private final Action1<? super Resource> dispose;
  private final boolean disposeEagerly;
  private final Func1<? super Resource, ? extends Observable<? extends T>> observableFactory;
  private final Func0<Resource> resourceFactory;
  
  public OnSubscribeUsing(Func0<Resource> paramFunc0, Func1<? super Resource, ? extends Observable<? extends T>> paramFunc1, Action1<? super Resource> paramAction1, boolean paramBoolean)
  {
    this.resourceFactory = paramFunc0;
    this.observableFactory = paramFunc1;
    this.dispose = paramAction1;
    this.disposeEagerly = paramBoolean;
  }
  
  private Throwable disposeEagerlyIfRequested(Action0 paramAction0)
  {
    Throwable localThrowable1 = null;
    if (this.disposeEagerly) {}
    try
    {
      paramAction0.call();
      return localThrowable1;
    }
    catch (Throwable localThrowable2)
    {
      for (;;) {}
    }
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    for (;;)
    {
      try
      {
        Object localObject = this.resourceFactory.call();
        localDisposeAction = new DisposeAction(this.dispose, localObject, null);
        paramSubscriber.add(localDisposeAction);
        localObservable1 = (Observable)this.observableFactory.call(localObject);
        if (this.disposeEagerly)
        {
          Observable localObservable3 = localObservable1.doOnTerminate(localDisposeAction);
          localObservable2 = localObservable3;
        }
      }
      catch (Throwable localThrowable1)
      {
        DisposeAction localDisposeAction;
        Observable localObservable1;
        Observable localObservable2;
        Throwable localThrowable3;
        Throwable[] arrayOfThrowable;
        paramSubscriber.onError(localThrowable1);
        continue;
        paramSubscriber.onError(localThrowable2);
        continue;
      }
      try
      {
        localObservable2.unsafeSubscribe(Subscribers.wrap(paramSubscriber));
        return;
      }
      catch (Throwable localThrowable2)
      {
        localThrowable3 = disposeEagerlyIfRequested(localDisposeAction);
        if (localThrowable3 == null) {
          continue;
        }
      }
      localObservable2 = localObservable1;
      continue;
      arrayOfThrowable = new Throwable[2];
      arrayOfThrowable[0] = localThrowable2;
      arrayOfThrowable[1] = localThrowable3;
      paramSubscriber.onError(new CompositeException(Arrays.asList(arrayOfThrowable)));
    }
  }
  
  private static final class DisposeAction<Resource>
    extends AtomicBoolean
    implements Action0, Subscription
  {
    private static final long serialVersionUID = 4262875056400218316L;
    private Action1<? super Resource> dispose;
    private Resource resource;
    
    private DisposeAction(Action1<? super Resource> paramAction1, Resource paramResource)
    {
      this.dispose = paramAction1;
      this.resource = paramResource;
      lazySet(false);
    }
    
    public void call()
    {
      if (compareAndSet(false, true)) {}
      try
      {
        this.dispose.call(this.resource);
        return;
      }
      finally
      {
        this.resource = null;
        this.dispose = null;
      }
    }
    
    public boolean isUnsubscribed()
    {
      return get();
    }
    
    public void unsubscribe()
    {
      call();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OnSubscribeUsing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */