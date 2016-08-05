package rx.internal.operators;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Scheduler;
import rx.Subscriber;
import rx.subjects.Subject;

public final class OperatorReplay
{
  private OperatorReplay()
  {
    throw new IllegalStateException("No instances!");
  }
  
  public static <T> Subject<T, T> createScheduledSubject(Subject<T, T> paramSubject, Scheduler paramScheduler)
  {
    new SubjectWrapper(new Observable.OnSubscribe()
    {
      public void call(Subscriber<? super T> paramAnonymousSubscriber)
      {
        OperatorReplay.subscriberOf(OperatorReplay.this).call(paramAnonymousSubscriber);
      }
    }, paramSubject);
  }
  
  public static <T> Observable.OnSubscribe<T> subscriberOf(Observable<T> paramObservable)
  {
    new Observable.OnSubscribe()
    {
      public void call(Subscriber<? super T> paramAnonymousSubscriber)
      {
        OperatorReplay.this.unsafeSubscribe(paramAnonymousSubscriber);
      }
    };
  }
  
  public static final class SubjectWrapper<T>
    extends Subject<T, T>
  {
    final Subject<T, T> subject;
    
    public SubjectWrapper(Observable.OnSubscribe<T> paramOnSubscribe, Subject<T, T> paramSubject)
    {
      super();
      this.subject = paramSubject;
    }
    
    public boolean hasObservers()
    {
      return this.subject.hasObservers();
    }
    
    public void onCompleted()
    {
      this.subject.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.subject.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      this.subject.onNext(paramT);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorReplay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */