package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.observables.ConnectableObservable;
import rx.observers.Subscribers;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

public final class OperatorMulticast<T, R>
  extends ConnectableObservable<R>
{
  final AtomicReference<Subject<? super T, ? extends R>> connectedSubject;
  final Object guard;
  private Subscription guardedSubscription;
  final Observable<? extends T> source;
  final Func0<? extends Subject<? super T, ? extends R>> subjectFactory;
  private Subscriber<T> subscription;
  final List<Subscriber<? super R>> waitingForConnect;
  
  private OperatorMulticast(Object paramObject, final AtomicReference<Subject<? super T, ? extends R>> paramAtomicReference, final List<Subscriber<? super R>> paramList, Observable<? extends T> paramObservable, Func0<? extends Subject<? super T, ? extends R>> paramFunc0)
  {
    super(new Observable.OnSubscribe()
    {
      public void call(Subscriber<? super R> paramAnonymousSubscriber)
      {
        synchronized (OperatorMulticast.this)
        {
          if (paramAtomicReference.get() == null)
          {
            paramList.add(paramAnonymousSubscriber);
            return;
          }
          ((Subject)paramAtomicReference.get()).unsafeSubscribe(paramAnonymousSubscriber);
        }
      }
    });
    this.guard = paramObject;
    this.connectedSubject = paramAtomicReference;
    this.waitingForConnect = paramList;
    this.source = paramObservable;
    this.subjectFactory = paramFunc0;
  }
  
  public OperatorMulticast(Observable<? extends T> paramObservable, Func0<? extends Subject<? super T, ? extends R>> paramFunc0)
  {
    this(new Object(), new AtomicReference(), new ArrayList(), paramObservable, paramFunc0);
  }
  
  public void connect(Action1<? super Subscription> paramAction1)
  {
    Subject localSubject;
    Subscriber localSubscriber1;
    synchronized (this.guard)
    {
      if (this.subscription != null)
      {
        paramAction1.call(this.guardedSubscription);
      }
      else
      {
        localSubject = (Subject)this.subjectFactory.call();
        this.subscription = Subscribers.from(localSubject);
        final AtomicReference localAtomicReference = new AtomicReference();
        localAtomicReference.set(Subscriptions.create(new Action0()
        {
          public void call()
          {
            synchronized (OperatorMulticast.this.guard)
            {
              if (OperatorMulticast.this.guardedSubscription == localAtomicReference.get())
              {
                Subscriber localSubscriber = OperatorMulticast.this.subscription;
                OperatorMulticast.access$102(OperatorMulticast.this, null);
                OperatorMulticast.access$002(OperatorMulticast.this, null);
                OperatorMulticast.this.connectedSubject.set(null);
                if (localSubscriber != null) {
                  localSubscriber.unsubscribe();
                }
                return;
              }
            }
          }
        }));
        this.guardedSubscription = ((Subscription)localAtomicReference.get());
        Iterator localIterator = this.waitingForConnect.iterator();
        if (localIterator.hasNext())
        {
          final Subscriber localSubscriber2 = (Subscriber)localIterator.next();
          localSubject.unsafeSubscribe(new Subscriber(localSubscriber2)
          {
            public void onCompleted()
            {
              localSubscriber2.onCompleted();
            }
            
            public void onError(Throwable paramAnonymousThrowable)
            {
              localSubscriber2.onError(paramAnonymousThrowable);
            }
            
            public void onNext(R paramAnonymousR)
            {
              localSubscriber2.onNext(paramAnonymousR);
            }
          });
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorMulticast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */