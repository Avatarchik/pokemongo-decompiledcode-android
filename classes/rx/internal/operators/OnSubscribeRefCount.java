package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public final class OnSubscribeRefCount<T>
  implements Observable.OnSubscribe<T>
{
  private volatile CompositeSubscription baseSubscription = new CompositeSubscription();
  private final ReentrantLock lock = new ReentrantLock();
  private final ConnectableObservable<? extends T> source;
  private final AtomicInteger subscriptionCount = new AtomicInteger(0);
  
  public OnSubscribeRefCount(ConnectableObservable<? extends T> paramConnectableObservable)
  {
    this.source = paramConnectableObservable;
  }
  
  private Subscription disconnect(final CompositeSubscription paramCompositeSubscription)
  {
    Subscriptions.create(new Action0()
    {
      public void call()
      {
        OnSubscribeRefCount.this.lock.lock();
        try
        {
          if ((OnSubscribeRefCount.this.baseSubscription == paramCompositeSubscription) && (OnSubscribeRefCount.this.subscriptionCount.decrementAndGet() == 0))
          {
            OnSubscribeRefCount.this.baseSubscription.unsubscribe();
            OnSubscribeRefCount.access$002(OnSubscribeRefCount.this, new CompositeSubscription());
          }
          return;
        }
        finally
        {
          OnSubscribeRefCount.this.lock.unlock();
        }
      }
    });
  }
  
  private Action1<Subscription> onSubscribe(final Subscriber<? super T> paramSubscriber, final AtomicBoolean paramAtomicBoolean)
  {
    new Action1()
    {
      public void call(Subscription paramAnonymousSubscription)
      {
        try
        {
          OnSubscribeRefCount.this.baseSubscription.add(paramAnonymousSubscription);
          OnSubscribeRefCount.this.doSubscribe(paramSubscriber, OnSubscribeRefCount.this.baseSubscription);
          return;
        }
        finally
        {
          OnSubscribeRefCount.this.lock.unlock();
          paramAtomicBoolean.set(false);
        }
      }
    };
  }
  
  /* Error */
  public void call(Subscriber<? super T> paramSubscriber)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 44	rx/internal/operators/OnSubscribeRefCount:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   4: invokevirtual 79	java/util/concurrent/locks/ReentrantLock:lock	()V
    //   7: aload_0
    //   8: getfield 39	rx/internal/operators/OnSubscribeRefCount:subscriptionCount	Ljava/util/concurrent/atomic/AtomicInteger;
    //   11: invokevirtual 83	java/util/concurrent/atomic/AtomicInteger:incrementAndGet	()I
    //   14: iconst_1
    //   15: if_icmpne +57 -> 72
    //   18: new 85	java/util/concurrent/atomic/AtomicBoolean
    //   21: dup
    //   22: iconst_1
    //   23: invokespecial 88	java/util/concurrent/atomic/AtomicBoolean:<init>	(Z)V
    //   26: astore_2
    //   27: aload_0
    //   28: getfield 46	rx/internal/operators/OnSubscribeRefCount:source	Lrx/observables/ConnectableObservable;
    //   31: aload_0
    //   32: aload_1
    //   33: aload_2
    //   34: invokespecial 90	rx/internal/operators/OnSubscribeRefCount:onSubscribe	(Lrx/Subscriber;Ljava/util/concurrent/atomic/AtomicBoolean;)Lrx/functions/Action1;
    //   37: invokevirtual 96	rx/observables/ConnectableObservable:connect	(Lrx/functions/Action1;)V
    //   40: aload_2
    //   41: invokevirtual 100	java/util/concurrent/atomic/AtomicBoolean:get	()Z
    //   44: ifeq +10 -> 54
    //   47: aload_0
    //   48: getfield 44	rx/internal/operators/OnSubscribeRefCount:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   51: invokevirtual 103	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   54: return
    //   55: astore_3
    //   56: aload_2
    //   57: invokevirtual 100	java/util/concurrent/atomic/AtomicBoolean:get	()Z
    //   60: ifeq +10 -> 70
    //   63: aload_0
    //   64: getfield 44	rx/internal/operators/OnSubscribeRefCount:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   67: invokevirtual 103	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   70: aload_3
    //   71: athrow
    //   72: aload_0
    //   73: aload_1
    //   74: aload_0
    //   75: getfield 32	rx/internal/operators/OnSubscribeRefCount:baseSubscription	Lrx/subscriptions/CompositeSubscription;
    //   78: invokevirtual 107	rx/internal/operators/OnSubscribeRefCount:doSubscribe	(Lrx/Subscriber;Lrx/subscriptions/CompositeSubscription;)V
    //   81: aload_0
    //   82: getfield 44	rx/internal/operators/OnSubscribeRefCount:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   85: invokevirtual 103	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   88: goto -34 -> 54
    //   91: astore 4
    //   93: aload_0
    //   94: getfield 44	rx/internal/operators/OnSubscribeRefCount:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   97: invokevirtual 103	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   100: aload 4
    //   102: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	103	0	this	OnSubscribeRefCount
    //   0	103	1	paramSubscriber	Subscriber<? super T>
    //   26	31	2	localAtomicBoolean	AtomicBoolean
    //   55	16	3	localObject1	Object
    //   91	10	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   27	40	55	finally
    //   72	81	91	finally
  }
  
  void doSubscribe(final Subscriber<? super T> paramSubscriber, final CompositeSubscription paramCompositeSubscription)
  {
    paramSubscriber.add(disconnect(paramCompositeSubscription));
    this.source.unsafeSubscribe(new Subscriber(paramSubscriber)
    {
      void cleanup()
      {
        OnSubscribeRefCount.this.lock.lock();
        try
        {
          if (OnSubscribeRefCount.this.baseSubscription == paramCompositeSubscription)
          {
            OnSubscribeRefCount.this.baseSubscription.unsubscribe();
            OnSubscribeRefCount.access$002(OnSubscribeRefCount.this, new CompositeSubscription());
            OnSubscribeRefCount.this.subscriptionCount.set(0);
          }
          return;
        }
        finally
        {
          OnSubscribeRefCount.this.lock.unlock();
        }
      }
      
      public void onCompleted()
      {
        cleanup();
        paramSubscriber.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        cleanup();
        paramSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(T paramAnonymousT)
      {
        paramSubscriber.onNext(paramAnonymousT);
      }
    });
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OnSubscribeRefCount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */