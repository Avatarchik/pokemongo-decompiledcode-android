package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.operators.NotificationLite;
import rx.subscriptions.Subscriptions;

final class SubjectSubscriptionManager<T>
  implements Observable.OnSubscribe<T>
{
  static final AtomicReferenceFieldUpdater<SubjectSubscriptionManager, Object> LATEST_UPDATER = AtomicReferenceFieldUpdater.newUpdater(SubjectSubscriptionManager.class, Object.class, "latest");
  static final AtomicReferenceFieldUpdater<SubjectSubscriptionManager, State> STATE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(SubjectSubscriptionManager.class, State.class, "state");
  boolean active = true;
  volatile Object latest;
  public final NotificationLite<T> nl = NotificationLite.instance();
  Action1<SubjectObserver<T>> onAdded = Actions.empty();
  Action1<SubjectObserver<T>> onStart = Actions.empty();
  Action1<SubjectObserver<T>> onTerminated = Actions.empty();
  volatile State<T> state = State.EMPTY;
  
  boolean add(SubjectObserver<T> paramSubjectObserver)
  {
    State localState1 = this.state;
    if (localState1.terminated) {
      this.onTerminated.call(paramSubjectObserver);
    }
    for (boolean bool = false;; bool = true)
    {
      return bool;
      State localState2 = localState1.add(paramSubjectObserver);
      if (!STATE_UPDATER.compareAndSet(this, localState1, localState2)) {
        break;
      }
      this.onAdded.call(paramSubjectObserver);
    }
  }
  
  void addUnsubscriber(Subscriber<? super T> paramSubscriber, final SubjectObserver<T> paramSubjectObserver)
  {
    paramSubscriber.add(Subscriptions.create(new Action0()
    {
      public void call()
      {
        SubjectSubscriptionManager.this.remove(paramSubjectObserver);
      }
    }));
  }
  
  public void call(Subscriber<? super T> paramSubscriber)
  {
    SubjectObserver localSubjectObserver = new SubjectObserver(paramSubscriber);
    addUnsubscriber(paramSubscriber, localSubjectObserver);
    this.onStart.call(localSubjectObserver);
    if ((!paramSubscriber.isUnsubscribed()) && (add(localSubjectObserver)) && (paramSubscriber.isUnsubscribed())) {
      remove(localSubjectObserver);
    }
  }
  
  Object get()
  {
    return this.latest;
  }
  
  SubjectObserver<T>[] next(Object paramObject)
  {
    set(paramObject);
    return this.state.observers;
  }
  
  SubjectObserver<T>[] observers()
  {
    return this.state.observers;
  }
  
  void remove(SubjectObserver<T> paramSubjectObserver)
  {
    State localState1 = this.state;
    if (localState1.terminated) {}
    for (;;)
    {
      return;
      State localState2 = localState1.remove(paramSubjectObserver);
      if (localState2 != localState1) {
        if (!STATE_UPDATER.compareAndSet(this, localState1, localState2)) {
          break;
        }
      }
    }
  }
  
  void set(Object paramObject)
  {
    this.latest = paramObject;
  }
  
  SubjectObserver<T>[] terminate(Object paramObject)
  {
    set(paramObject);
    this.active = false;
    if (this.state.terminated) {}
    for (SubjectObserver[] arrayOfSubjectObserver = State.NO_OBSERVERS;; arrayOfSubjectObserver = ((State)STATE_UPDATER.getAndSet(this, State.TERMINATED)).observers) {
      return arrayOfSubjectObserver;
    }
  }
  
  protected static final class SubjectObserver<T>
    implements Observer<T>
  {
    final Observer<? super T> actual;
    protected volatile boolean caughtUp;
    boolean emitting;
    boolean fastPath;
    boolean first = true;
    private volatile Object index;
    List<Object> queue;
    
    public SubjectObserver(Observer<? super T> paramObserver)
    {
      this.actual = paramObserver;
    }
    
    protected void accept(Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      if (paramObject != null) {
        paramNotificationLite.accept(this.actual, paramObject);
      }
    }
    
    protected void emitFirst(Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      boolean bool = false;
      try
      {
        if ((!this.first) || (this.emitting)) {
          return;
        }
        this.first = false;
        if (paramObject != null) {
          bool = true;
        }
        this.emitting = bool;
        if (paramObject != null) {
          emitLoop(null, paramObject, paramNotificationLite);
        }
      }
      finally {}
    }
    
    /* Error */
    protected void emitLoop(List<Object> paramList, Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      // Byte code:
      //   0: iconst_1
      //   1: istore 4
      //   3: iconst_0
      //   4: istore 5
      //   6: aload_1
      //   7: ifnull +55 -> 62
      //   10: aload_1
      //   11: invokeinterface 52 1 0
      //   16: astore 10
      //   18: aload 10
      //   20: invokeinterface 58 1 0
      //   25: ifeq +37 -> 62
      //   28: aload_0
      //   29: aload 10
      //   31: invokeinterface 62 1 0
      //   36: aload_3
      //   37: invokevirtual 64	rx/subjects/SubjectSubscriptionManager$SubjectObserver:accept	(Ljava/lang/Object;Lrx/internal/operators/NotificationLite;)V
      //   40: goto -22 -> 18
      //   43: astore 7
      //   45: iload 5
      //   47: ifne +12 -> 59
      //   50: aload_0
      //   51: monitorenter
      //   52: aload_0
      //   53: iconst_0
      //   54: putfield 42	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
      //   57: aload_0
      //   58: monitorexit
      //   59: aload 7
      //   61: athrow
      //   62: iload 4
      //   64: ifeq +12 -> 76
      //   67: iconst_0
      //   68: istore 4
      //   70: aload_0
      //   71: aload_2
      //   72: aload_3
      //   73: invokevirtual 64	rx/subjects/SubjectSubscriptionManager$SubjectObserver:accept	(Ljava/lang/Object;Lrx/internal/operators/NotificationLite;)V
      //   76: aload_0
      //   77: monitorenter
      //   78: aload_0
      //   79: getfield 66	rx/subjects/SubjectSubscriptionManager$SubjectObserver:queue	Ljava/util/List;
      //   82: astore_1
      //   83: aload_0
      //   84: aconst_null
      //   85: putfield 66	rx/subjects/SubjectSubscriptionManager$SubjectObserver:queue	Ljava/util/List;
      //   88: aload_1
      //   89: ifnonnull +28 -> 117
      //   92: aload_0
      //   93: iconst_0
      //   94: putfield 42	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
      //   97: iconst_1
      //   98: istore 5
      //   100: aload_0
      //   101: monitorexit
      //   102: iload 5
      //   104: ifne +12 -> 116
      //   107: aload_0
      //   108: monitorenter
      //   109: aload_0
      //   110: iconst_0
      //   111: putfield 42	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
      //   114: aload_0
      //   115: monitorexit
      //   116: return
      //   117: aload_0
      //   118: monitorexit
      //   119: goto -113 -> 6
      //   122: astore 6
      //   124: aload_0
      //   125: monitorexit
      //   126: aload 6
      //   128: athrow
      //   129: astore 9
      //   131: aload_0
      //   132: monitorexit
      //   133: aload 9
      //   135: athrow
      //   136: astore 8
      //   138: aload_0
      //   139: monitorexit
      //   140: aload 8
      //   142: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	143	0	this	SubjectObserver
      //   0	143	1	paramList	List<Object>
      //   0	143	2	paramObject	Object
      //   0	143	3	paramNotificationLite	NotificationLite<T>
      //   1	68	4	i	int
      //   4	99	5	j	int
      //   122	5	6	localObject1	Object
      //   43	17	7	localObject2	Object
      //   136	5	8	localObject3	Object
      //   129	5	9	localObject4	Object
      //   16	14	10	localIterator	java.util.Iterator
      // Exception table:
      //   from	to	target	type
      //   10	40	43	finally
      //   70	78	43	finally
      //   126	129	43	finally
      //   78	102	122	finally
      //   117	126	122	finally
      //   109	116	129	finally
      //   131	133	129	finally
      //   52	59	136	finally
      //   138	140	136	finally
    }
    
    protected void emitNext(Object paramObject, NotificationLite<T> paramNotificationLite)
    {
      if (!this.fastPath) {}
      try
      {
        this.first = false;
        if (this.emitting)
        {
          if (this.queue == null) {
            this.queue = new ArrayList();
          }
          this.queue.add(paramObject);
        }
        else
        {
          this.fastPath = true;
          paramNotificationLite.accept(this.actual, paramObject);
        }
      }
      finally {}
    }
    
    protected Observer<? super T> getActual()
    {
      return this.actual;
    }
    
    public <I> I index()
    {
      return (I)this.index;
    }
    
    public void index(Object paramObject)
    {
      this.index = paramObject;
    }
    
    public void onCompleted()
    {
      this.actual.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.actual.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      this.actual.onNext(paramT);
    }
  }
  
  protected static final class State<T>
  {
    static final State EMPTY = new State(false, NO_OBSERVERS);
    static final SubjectSubscriptionManager.SubjectObserver[] NO_OBSERVERS = new SubjectSubscriptionManager.SubjectObserver[0];
    static final State TERMINATED = new State(true, NO_OBSERVERS);
    final SubjectSubscriptionManager.SubjectObserver[] observers;
    final boolean terminated;
    
    public State(boolean paramBoolean, SubjectSubscriptionManager.SubjectObserver[] paramArrayOfSubjectObserver)
    {
      this.terminated = paramBoolean;
      this.observers = paramArrayOfSubjectObserver;
    }
    
    public State add(SubjectSubscriptionManager.SubjectObserver paramSubjectObserver)
    {
      int i = this.observers.length;
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = new SubjectSubscriptionManager.SubjectObserver[i + 1];
      System.arraycopy(this.observers, 0, arrayOfSubjectObserver, 0, i);
      arrayOfSubjectObserver[i] = paramSubjectObserver;
      return new State(this.terminated, arrayOfSubjectObserver);
    }
    
    public State remove(SubjectSubscriptionManager.SubjectObserver paramSubjectObserver)
    {
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver1 = this.observers;
      int i = arrayOfSubjectObserver1.length;
      if ((i == 1) && (arrayOfSubjectObserver1[0] == paramSubjectObserver)) {
        this = EMPTY;
      }
      Object localObject;
      int j;
      int k;
      label44:
      SubjectSubscriptionManager.SubjectObserver localSubjectObserver;
      do
      {
        do
        {
          return this;
        } while (i == 0);
        localObject = new SubjectSubscriptionManager.SubjectObserver[i - 1];
        j = 0;
        k = 0;
        if (j >= i) {
          break;
        }
        localSubjectObserver = arrayOfSubjectObserver1[j];
        if (localSubjectObserver == paramSubjectObserver) {
          break label152;
        }
      } while (k == i - 1);
      int m = k + 1;
      localObject[k] = localSubjectObserver;
      for (;;)
      {
        j++;
        k = m;
        break label44;
        if (k == 0)
        {
          this = EMPTY;
          break;
        }
        if (k < i - 1)
        {
          SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver2 = new SubjectSubscriptionManager.SubjectObserver[k];
          System.arraycopy(localObject, 0, arrayOfSubjectObserver2, 0, k);
          localObject = arrayOfSubjectObserver2;
        }
        this = new State(this.terminated, (SubjectSubscriptionManager.SubjectObserver[])localObject);
        break;
        label152:
        m = k;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/subjects/SubjectSubscriptionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */