package rx.internal.util;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.schedulers.EventLoopsScheduler;

public final class ScalarSynchronousObservable<T>
  extends Observable<T>
{
  private final T t;
  
  protected ScalarSynchronousObservable(T paramT)
  {
    super(new Observable.OnSubscribe()
    {
      public void call(Subscriber<? super T> paramAnonymousSubscriber)
      {
        paramAnonymousSubscriber.onNext(ScalarSynchronousObservable.this);
        paramAnonymousSubscriber.onCompleted();
      }
    });
    this.t = paramT;
  }
  
  public static final <T> ScalarSynchronousObservable<T> create(T paramT)
  {
    return new ScalarSynchronousObservable(paramT);
  }
  
  public T get()
  {
    return (T)this.t;
  }
  
  public <R> Observable<R> scalarFlatMap(final Func1<? super T, ? extends Observable<? extends R>> paramFunc1)
  {
    create(new Observable.OnSubscribe()
    {
      public void call(final Subscriber<? super R> paramAnonymousSubscriber)
      {
        Observable localObservable = (Observable)paramFunc1.call(ScalarSynchronousObservable.this.t);
        if (localObservable.getClass() == ScalarSynchronousObservable.class)
        {
          paramAnonymousSubscriber.onNext(((ScalarSynchronousObservable)localObservable).t);
          paramAnonymousSubscriber.onCompleted();
        }
        for (;;)
        {
          return;
          localObservable.unsafeSubscribe(new Subscriber(paramAnonymousSubscriber)
          {
            public void onCompleted()
            {
              paramAnonymousSubscriber.onCompleted();
            }
            
            public void onError(Throwable paramAnonymous2Throwable)
            {
              paramAnonymousSubscriber.onError(paramAnonymous2Throwable);
            }
            
            public void onNext(R paramAnonymous2R)
            {
              paramAnonymousSubscriber.onNext(paramAnonymous2R);
            }
          });
        }
      }
    });
  }
  
  public Observable<T> scalarScheduleOn(Scheduler paramScheduler)
  {
    if ((paramScheduler instanceof EventLoopsScheduler)) {}
    for (Observable localObservable = create(new DirectScheduledEmission((EventLoopsScheduler)paramScheduler, this.t));; localObservable = create(new NormalScheduledEmission(paramScheduler, this.t))) {
      return localObservable;
    }
  }
  
  static final class ScalarSynchronousAction<T>
    implements Action0
  {
    private final Subscriber<? super T> subscriber;
    private final T value;
    
    private ScalarSynchronousAction(Subscriber<? super T> paramSubscriber, T paramT)
    {
      this.subscriber = paramSubscriber;
      this.value = paramT;
    }
    
    public void call()
    {
      try
      {
        this.subscriber.onNext(this.value);
        this.subscriber.onCompleted();
        return;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          this.subscriber.onError(localThrowable);
        }
      }
    }
  }
  
  static final class NormalScheduledEmission<T>
    implements Observable.OnSubscribe<T>
  {
    private final Scheduler scheduler;
    private final T value;
    
    NormalScheduledEmission(Scheduler paramScheduler, T paramT)
    {
      this.scheduler = paramScheduler;
      this.value = paramT;
    }
    
    public void call(Subscriber<? super T> paramSubscriber)
    {
      Scheduler.Worker localWorker = this.scheduler.createWorker();
      paramSubscriber.add(localWorker);
      localWorker.schedule(new ScalarSynchronousObservable.ScalarSynchronousAction(paramSubscriber, this.value, null));
    }
  }
  
  static final class DirectScheduledEmission<T>
    implements Observable.OnSubscribe<T>
  {
    private final EventLoopsScheduler es;
    private final T value;
    
    DirectScheduledEmission(EventLoopsScheduler paramEventLoopsScheduler, T paramT)
    {
      this.es = paramEventLoopsScheduler;
      this.value = paramT;
    }
    
    public void call(Subscriber<? super T> paramSubscriber)
    {
      paramSubscriber.add(this.es.scheduleDirect(new ScalarSynchronousObservable.ScalarSynchronousAction(paramSubscriber, this.value, null)));
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/ScalarSynchronousObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */