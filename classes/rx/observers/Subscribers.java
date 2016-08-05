package rx.observers;

import rx.Observer;
import rx.Subscriber;
import rx.annotations.Experimental;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.functions.Action1;

public final class Subscribers
{
  private Subscribers()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static final <T> Subscriber<T> create(Action1<? super T> paramAction1)
  {
    if (paramAction1 == null) {
      throw new IllegalArgumentException("onNext can not be null");
    }
    new Subscriber()
    {
      public final void onCompleted() {}
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        throw new OnErrorNotImplementedException(paramAnonymousThrowable);
      }
      
      public final void onNext(T paramAnonymousT)
      {
        Subscribers.this.call(paramAnonymousT);
      }
    };
  }
  
  public static final <T> Subscriber<T> create(final Action1<? super T> paramAction1, Action1<Throwable> paramAction11)
  {
    if (paramAction1 == null) {
      throw new IllegalArgumentException("onNext can not be null");
    }
    if (paramAction11 == null) {
      throw new IllegalArgumentException("onError can not be null");
    }
    new Subscriber()
    {
      public final void onCompleted() {}
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        Subscribers.this.call(paramAnonymousThrowable);
      }
      
      public final void onNext(T paramAnonymousT)
      {
        paramAction1.call(paramAnonymousT);
      }
    };
  }
  
  public static final <T> Subscriber<T> create(final Action1<? super T> paramAction1, final Action1<Throwable> paramAction11, Action0 paramAction0)
  {
    if (paramAction1 == null) {
      throw new IllegalArgumentException("onNext can not be null");
    }
    if (paramAction11 == null) {
      throw new IllegalArgumentException("onError can not be null");
    }
    if (paramAction0 == null) {
      throw new IllegalArgumentException("onComplete can not be null");
    }
    new Subscriber()
    {
      public final void onCompleted()
      {
        Subscribers.this.call();
      }
      
      public final void onError(Throwable paramAnonymousThrowable)
      {
        paramAction11.call(paramAnonymousThrowable);
      }
      
      public final void onNext(T paramAnonymousT)
      {
        paramAction1.call(paramAnonymousT);
      }
    };
  }
  
  public static <T> Subscriber<T> empty()
  {
    return from(Observers.empty());
  }
  
  public static <T> Subscriber<T> from(Observer<? super T> paramObserver)
  {
    new Subscriber()
    {
      public void onCompleted()
      {
        Subscribers.this.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        Subscribers.this.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        Subscribers.this.onNext(paramAnonymousT);
      }
    };
  }
  
  @Experimental
  public static <T> Subscriber<T> wrap(final Subscriber<? super T> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
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
        paramSubscriber.onNext(paramAnonymousT);
      }
    };
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/observers/Subscribers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */