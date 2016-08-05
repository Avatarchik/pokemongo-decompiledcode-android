package rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Scheduler;
import rx.annotations.Experimental;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.operators.NotificationLite;
import rx.internal.util.UtilityFunctions;
import rx.schedulers.Timestamped;

public final class ReplaySubject<T>
  extends Subject<T, T>
{
  final SubjectSubscriptionManager<T> ssm;
  final ReplayState<T, ?> state;
  
  ReplaySubject(Observable.OnSubscribe<T> paramOnSubscribe, SubjectSubscriptionManager<T> paramSubjectSubscriptionManager, ReplayState<T, ?> paramReplayState)
  {
    super(paramOnSubscribe);
    this.ssm = paramSubjectSubscriptionManager;
    this.state = paramReplayState;
  }
  
  private boolean caughtUp(SubjectSubscriptionManager.SubjectObserver<? super T> paramSubjectObserver)
  {
    boolean bool = true;
    if (!paramSubjectObserver.caughtUp)
    {
      if (this.state.replayObserver(paramSubjectObserver))
      {
        paramSubjectObserver.caughtUp = bool;
        paramSubjectObserver.index(null);
      }
      bool = false;
    }
    return bool;
  }
  
  public static <T> ReplaySubject<T> create()
  {
    return create(16);
  }
  
  public static <T> ReplaySubject<T> create(int paramInt)
  {
    UnboundedReplayState localUnboundedReplayState = new UnboundedReplayState(paramInt);
    SubjectSubscriptionManager localSubjectSubscriptionManager = new SubjectSubscriptionManager();
    localSubjectSubscriptionManager.onStart = new Action1()
    {
      public void call(SubjectSubscriptionManager.SubjectObserver<T> paramAnonymousSubjectObserver)
      {
        paramAnonymousSubjectObserver.index(Integer.valueOf(ReplaySubject.this.replayObserverFromIndex(Integer.valueOf(0), paramAnonymousSubjectObserver).intValue()));
      }
    };
    localSubjectSubscriptionManager.onAdded = new Action1()
    {
      /* Error */
      public void call(SubjectSubscriptionManager.SubjectObserver<T> paramAnonymousSubjectObserver)
      {
        // Byte code:
        //   0: aload_1
        //   1: monitorenter
        //   2: aload_1
        //   3: getfield 32	rx/subjects/SubjectSubscriptionManager$SubjectObserver:first	Z
        //   6: ifeq +10 -> 16
        //   9: aload_1
        //   10: getfield 35	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
        //   13: ifeq +8 -> 21
        //   16: aload_1
        //   17: monitorexit
        //   18: goto +150 -> 168
        //   21: aload_1
        //   22: iconst_0
        //   23: putfield 32	rx/subjects/SubjectSubscriptionManager$SubjectObserver:first	Z
        //   26: aload_1
        //   27: iconst_1
        //   28: putfield 35	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
        //   31: aload_1
        //   32: monitorexit
        //   33: iconst_0
        //   34: istore_3
        //   35: aload_1
        //   36: invokevirtual 39	rx/subjects/SubjectSubscriptionManager$SubjectObserver:index	()Ljava/lang/Object;
        //   39: checkcast 41	java/lang/Integer
        //   42: invokevirtual 45	java/lang/Integer:intValue	()I
        //   45: istore 6
        //   47: aload_0
        //   48: getfield 18	rx/subjects/ReplaySubject$2:val$state	Lrx/subjects/ReplaySubject$UnboundedReplayState;
        //   51: getfield 50	rx/subjects/ReplaySubject$UnboundedReplayState:index	I
        //   54: istore 7
        //   56: iload 6
        //   58: iload 7
        //   60: if_icmpeq +20 -> 80
        //   63: aload_1
        //   64: aload_0
        //   65: getfield 18	rx/subjects/ReplaySubject$2:val$state	Lrx/subjects/ReplaySubject$UnboundedReplayState;
        //   68: iload 6
        //   70: invokestatic 54	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   73: aload_1
        //   74: invokevirtual 58	rx/subjects/ReplaySubject$UnboundedReplayState:replayObserverFromIndex	(Ljava/lang/Integer;Lrx/subjects/SubjectSubscriptionManager$SubjectObserver;)Ljava/lang/Integer;
        //   77: invokevirtual 60	rx/subjects/SubjectSubscriptionManager$SubjectObserver:index	(Ljava/lang/Object;)V
        //   80: aload_1
        //   81: monitorenter
        //   82: iload 7
        //   84: aload_0
        //   85: getfield 18	rx/subjects/ReplaySubject$2:val$state	Lrx/subjects/ReplaySubject$UnboundedReplayState;
        //   88: getfield 50	rx/subjects/ReplaySubject$UnboundedReplayState:index	I
        //   91: if_icmpne +40 -> 131
        //   94: aload_1
        //   95: iconst_0
        //   96: putfield 35	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
        //   99: iconst_1
        //   100: istore_3
        //   101: aload_1
        //   102: monitorexit
        //   103: iload_3
        //   104: ifne +64 -> 168
        //   107: aload_1
        //   108: monitorenter
        //   109: aload_1
        //   110: iconst_0
        //   111: putfield 35	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
        //   114: aload_1
        //   115: monitorexit
        //   116: goto +52 -> 168
        //   119: astore 9
        //   121: aload_1
        //   122: monitorexit
        //   123: aload 9
        //   125: athrow
        //   126: astore_2
        //   127: aload_1
        //   128: monitorexit
        //   129: aload_2
        //   130: athrow
        //   131: aload_1
        //   132: monitorexit
        //   133: goto -98 -> 35
        //   136: astore 8
        //   138: aload_1
        //   139: monitorexit
        //   140: aload 8
        //   142: athrow
        //   143: astore 4
        //   145: iload_3
        //   146: ifne +12 -> 158
        //   149: aload_1
        //   150: monitorenter
        //   151: aload_1
        //   152: iconst_0
        //   153: putfield 35	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
        //   156: aload_1
        //   157: monitorexit
        //   158: aload 4
        //   160: athrow
        //   161: astore 5
        //   163: aload_1
        //   164: monitorexit
        //   165: aload 5
        //   167: athrow
        //   168: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	169	0	this	2
        //   0	169	1	paramAnonymousSubjectObserver	SubjectSubscriptionManager.SubjectObserver<T>
        //   126	4	2	localObject1	Object
        //   34	112	3	i	int
        //   143	16	4	localObject2	Object
        //   161	5	5	localObject3	Object
        //   45	24	6	j	int
        //   54	38	7	k	int
        //   136	5	8	localObject4	Object
        //   119	5	9	localObject5	Object
        // Exception table:
        //   from	to	target	type
        //   109	123	119	finally
        //   2	33	126	finally
        //   127	129	126	finally
        //   82	103	136	finally
        //   131	140	136	finally
        //   35	82	143	finally
        //   140	143	143	finally
        //   151	158	161	finally
        //   163	165	161	finally
      }
    };
    localSubjectSubscriptionManager.onTerminated = new Action1()
    {
      public void call(SubjectSubscriptionManager.SubjectObserver<T> paramAnonymousSubjectObserver)
      {
        Integer localInteger = (Integer)paramAnonymousSubjectObserver.index();
        if (localInteger == null) {
          localInteger = Integer.valueOf(0);
        }
        ReplaySubject.this.replayObserverFromIndex(localInteger, paramAnonymousSubjectObserver);
      }
    };
    return new ReplaySubject(localSubjectSubscriptionManager, localSubjectSubscriptionManager, localUnboundedReplayState);
  }
  
  static <T> ReplaySubject<T> createUnbounded()
  {
    BoundedState localBoundedState = new BoundedState(new EmptyEvictionPolicy(), UtilityFunctions.identity(), UtilityFunctions.identity());
    return createWithState(localBoundedState, new DefaultOnAdd(localBoundedState));
  }
  
  public static <T> ReplaySubject<T> createWithSize(int paramInt)
  {
    BoundedState localBoundedState = new BoundedState(new SizeEvictionPolicy(paramInt), UtilityFunctions.identity(), UtilityFunctions.identity());
    return createWithState(localBoundedState, new DefaultOnAdd(localBoundedState));
  }
  
  static final <T> ReplaySubject<T> createWithState(BoundedState<T> paramBoundedState, Action1<SubjectSubscriptionManager.SubjectObserver<T>> paramAction1)
  {
    SubjectSubscriptionManager localSubjectSubscriptionManager = new SubjectSubscriptionManager();
    localSubjectSubscriptionManager.onStart = paramAction1;
    localSubjectSubscriptionManager.onAdded = new Action1()
    {
      /* Error */
      public void call(SubjectSubscriptionManager.SubjectObserver<T> paramAnonymousSubjectObserver)
      {
        // Byte code:
        //   0: aload_1
        //   1: monitorenter
        //   2: aload_1
        //   3: getfield 32	rx/subjects/SubjectSubscriptionManager$SubjectObserver:first	Z
        //   6: ifeq +10 -> 16
        //   9: aload_1
        //   10: getfield 35	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
        //   13: ifeq +8 -> 21
        //   16: aload_1
        //   17: monitorexit
        //   18: goto +144 -> 162
        //   21: aload_1
        //   22: iconst_0
        //   23: putfield 32	rx/subjects/SubjectSubscriptionManager$SubjectObserver:first	Z
        //   26: aload_1
        //   27: iconst_1
        //   28: putfield 35	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
        //   31: aload_1
        //   32: monitorexit
        //   33: iconst_0
        //   34: istore_3
        //   35: aload_1
        //   36: invokevirtual 39	rx/subjects/SubjectSubscriptionManager$SubjectObserver:index	()Ljava/lang/Object;
        //   39: checkcast 41	rx/subjects/ReplaySubject$NodeList$Node
        //   42: astore 6
        //   44: aload_0
        //   45: getfield 18	rx/subjects/ReplaySubject$4:val$state	Lrx/subjects/ReplaySubject$BoundedState;
        //   48: invokevirtual 47	rx/subjects/ReplaySubject$BoundedState:tail	()Lrx/subjects/ReplaySubject$NodeList$Node;
        //   51: astore 7
        //   53: aload 6
        //   55: aload 7
        //   57: if_acmpeq +17 -> 74
        //   60: aload_1
        //   61: aload_0
        //   62: getfield 18	rx/subjects/ReplaySubject$4:val$state	Lrx/subjects/ReplaySubject$BoundedState;
        //   65: aload 6
        //   67: aload_1
        //   68: invokevirtual 51	rx/subjects/ReplaySubject$BoundedState:replayObserverFromIndex	(Lrx/subjects/ReplaySubject$NodeList$Node;Lrx/subjects/SubjectSubscriptionManager$SubjectObserver;)Lrx/subjects/ReplaySubject$NodeList$Node;
        //   71: invokevirtual 53	rx/subjects/SubjectSubscriptionManager$SubjectObserver:index	(Ljava/lang/Object;)V
        //   74: aload_1
        //   75: monitorenter
        //   76: aload 7
        //   78: aload_0
        //   79: getfield 18	rx/subjects/ReplaySubject$4:val$state	Lrx/subjects/ReplaySubject$BoundedState;
        //   82: invokevirtual 47	rx/subjects/ReplaySubject$BoundedState:tail	()Lrx/subjects/ReplaySubject$NodeList$Node;
        //   85: if_acmpne +40 -> 125
        //   88: aload_1
        //   89: iconst_0
        //   90: putfield 35	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
        //   93: iconst_1
        //   94: istore_3
        //   95: aload_1
        //   96: monitorexit
        //   97: iload_3
        //   98: ifne +64 -> 162
        //   101: aload_1
        //   102: monitorenter
        //   103: aload_1
        //   104: iconst_0
        //   105: putfield 35	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
        //   108: aload_1
        //   109: monitorexit
        //   110: goto +52 -> 162
        //   113: astore 9
        //   115: aload_1
        //   116: monitorexit
        //   117: aload 9
        //   119: athrow
        //   120: astore_2
        //   121: aload_1
        //   122: monitorexit
        //   123: aload_2
        //   124: athrow
        //   125: aload_1
        //   126: monitorexit
        //   127: goto -92 -> 35
        //   130: astore 8
        //   132: aload_1
        //   133: monitorexit
        //   134: aload 8
        //   136: athrow
        //   137: astore 4
        //   139: iload_3
        //   140: ifne +12 -> 152
        //   143: aload_1
        //   144: monitorenter
        //   145: aload_1
        //   146: iconst_0
        //   147: putfield 35	rx/subjects/SubjectSubscriptionManager$SubjectObserver:emitting	Z
        //   150: aload_1
        //   151: monitorexit
        //   152: aload 4
        //   154: athrow
        //   155: astore 5
        //   157: aload_1
        //   158: monitorexit
        //   159: aload 5
        //   161: athrow
        //   162: return
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	163	0	this	4
        //   0	163	1	paramAnonymousSubjectObserver	SubjectSubscriptionManager.SubjectObserver<T>
        //   120	4	2	localObject1	Object
        //   34	106	3	i	int
        //   137	16	4	localObject2	Object
        //   155	5	5	localObject3	Object
        //   42	24	6	localNode1	ReplaySubject.NodeList.Node
        //   51	26	7	localNode2	ReplaySubject.NodeList.Node
        //   130	5	8	localObject4	Object
        //   113	5	9	localObject5	Object
        // Exception table:
        //   from	to	target	type
        //   103	117	113	finally
        //   2	33	120	finally
        //   121	123	120	finally
        //   76	97	130	finally
        //   125	134	130	finally
        //   35	76	137	finally
        //   134	137	137	finally
        //   145	152	155	finally
        //   157	159	155	finally
      }
    };
    localSubjectSubscriptionManager.onTerminated = new Action1()
    {
      public void call(SubjectSubscriptionManager.SubjectObserver<T> paramAnonymousSubjectObserver)
      {
        ReplaySubject.NodeList.Node localNode = (ReplaySubject.NodeList.Node)paramAnonymousSubjectObserver.index();
        if (localNode == null) {
          localNode = ReplaySubject.this.head();
        }
        ReplaySubject.this.replayObserverFromIndex(localNode, paramAnonymousSubjectObserver);
      }
    };
    return new ReplaySubject(localSubjectSubscriptionManager, localSubjectSubscriptionManager, paramBoundedState);
  }
  
  public static <T> ReplaySubject<T> createWithTime(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    BoundedState localBoundedState = new BoundedState(new TimeEvictionPolicy(paramTimeUnit.toMillis(paramLong), paramScheduler), new AddTimestamped(paramScheduler), new RemoveTimestamped());
    return createWithState(localBoundedState, new TimedOnAdd(localBoundedState, paramScheduler));
  }
  
  public static <T> ReplaySubject<T> createWithTimeAndSize(long paramLong, TimeUnit paramTimeUnit, int paramInt, Scheduler paramScheduler)
  {
    BoundedState localBoundedState = new BoundedState(new PairEvictionPolicy(new SizeEvictionPolicy(paramInt), new TimeEvictionPolicy(paramTimeUnit.toMillis(paramLong), paramScheduler)), new AddTimestamped(paramScheduler), new RemoveTimestamped());
    return createWithState(localBoundedState, new TimedOnAdd(localBoundedState, paramScheduler));
  }
  
  @Experimental
  public Throwable getThrowable()
  {
    NotificationLite localNotificationLite = this.ssm.nl;
    Object localObject = this.ssm.get();
    if (localNotificationLite.isError(localObject)) {}
    for (Throwable localThrowable = localNotificationLite.getError(localObject);; localThrowable = null) {
      return localThrowable;
    }
  }
  
  public T getValue()
  {
    return (T)this.state.latest();
  }
  
  @Experimental
  public T[] getValues(T[] paramArrayOfT)
  {
    return this.state.toArray(paramArrayOfT);
  }
  
  @Experimental
  public boolean hasAnyValue()
  {
    if (!this.state.isEmpty()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  @Experimental
  public boolean hasCompleted()
  {
    NotificationLite localNotificationLite = this.ssm.nl;
    Object localObject = this.ssm.get();
    if ((localObject != null) && (!localNotificationLite.isError(localObject))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasObservers()
  {
    if (this.ssm.observers().length > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  @Experimental
  public boolean hasThrowable()
  {
    return this.ssm.nl.isError(this.ssm.get());
  }
  
  @Experimental
  public boolean hasValue()
  {
    return hasAnyValue();
  }
  
  public void onCompleted()
  {
    if (this.ssm.active)
    {
      this.state.complete();
      for (SubjectSubscriptionManager.SubjectObserver localSubjectObserver : this.ssm.terminate(NotificationLite.instance().completed())) {
        if (caughtUp(localSubjectObserver)) {
          localSubjectObserver.onCompleted();
        }
      }
    }
  }
  
  public void onError(Throwable paramThrowable)
  {
    if (this.ssm.active)
    {
      this.state.error(paramThrowable);
      ArrayList localArrayList = null;
      SubjectSubscriptionManager.SubjectObserver[] arrayOfSubjectObserver = this.ssm.terminate(NotificationLite.instance().error(paramThrowable));
      int i = arrayOfSubjectObserver.length;
      int j = 0;
      for (;;)
      {
        if (j < i)
        {
          SubjectSubscriptionManager.SubjectObserver localSubjectObserver = arrayOfSubjectObserver[j];
          try
          {
            if (caughtUp(localSubjectObserver)) {
              localSubjectObserver.onError(paramThrowable);
            }
            j++;
          }
          catch (Throwable localThrowable)
          {
            for (;;)
            {
              if (localArrayList == null) {
                localArrayList = new ArrayList();
              }
              localArrayList.add(localThrowable);
            }
          }
        }
      }
      Exceptions.throwIfAny(localArrayList);
    }
  }
  
  public void onNext(T paramT)
  {
    if (this.ssm.active)
    {
      this.state.next(paramT);
      for (SubjectSubscriptionManager.SubjectObserver localSubjectObserver : this.ssm.observers()) {
        if (caughtUp(localSubjectObserver)) {
          localSubjectObserver.onNext(paramT);
        }
      }
    }
  }
  
  @Experimental
  public int size()
  {
    return this.state.size();
  }
  
  int subscriberCount()
  {
    return this.ssm.state.observers.length;
  }
  
  static final class EmptyEvictionPolicy
    implements ReplaySubject.EvictionPolicy
  {
    public void evict(ReplaySubject.NodeList<Object> paramNodeList) {}
    
    public void evictFinal(ReplaySubject.NodeList<Object> paramNodeList) {}
    
    public boolean test(Object paramObject, long paramLong)
    {
      return true;
    }
  }
  
  static final class NodeList<T>
  {
    final Node<T> head = new Node(null);
    int size;
    Node<T> tail = this.head;
    
    public void addLast(T paramT)
    {
      Node localNode1 = this.tail;
      Node localNode2 = new Node(paramT);
      localNode1.next = localNode2;
      this.tail = localNode2;
      this.size = (1 + this.size);
    }
    
    public void clear()
    {
      this.tail = this.head;
      this.size = 0;
    }
    
    public boolean isEmpty()
    {
      if (this.size == 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public T removeFirst()
    {
      if (this.head.next == null) {
        throw new IllegalStateException("Empty!");
      }
      Node localNode = this.head.next;
      this.head.next = localNode.next;
      if (this.head.next == null) {
        this.tail = this.head;
      }
      this.size = (-1 + this.size);
      return (T)localNode.value;
    }
    
    public int size()
    {
      return this.size;
    }
    
    static final class Node<T>
    {
      volatile Node<T> next;
      final T value;
      
      Node(T paramT)
      {
        this.value = paramT;
      }
    }
  }
  
  static final class TimedOnAdd<T>
    implements Action1<SubjectSubscriptionManager.SubjectObserver<T>>
  {
    final Scheduler scheduler;
    final ReplaySubject.BoundedState<T> state;
    
    public TimedOnAdd(ReplaySubject.BoundedState<T> paramBoundedState, Scheduler paramScheduler)
    {
      this.state = paramBoundedState;
      this.scheduler = paramScheduler;
    }
    
    public void call(SubjectSubscriptionManager.SubjectObserver<T> paramSubjectObserver)
    {
      if (!this.state.terminated) {}
      for (ReplaySubject.NodeList.Node localNode = this.state.replayObserverFromIndexTest(this.state.head(), paramSubjectObserver, this.scheduler.now());; localNode = this.state.replayObserverFromIndex(this.state.head(), paramSubjectObserver))
      {
        paramSubjectObserver.index(localNode);
        return;
      }
    }
  }
  
  static final class DefaultOnAdd<T>
    implements Action1<SubjectSubscriptionManager.SubjectObserver<T>>
  {
    final ReplaySubject.BoundedState<T> state;
    
    public DefaultOnAdd(ReplaySubject.BoundedState<T> paramBoundedState)
    {
      this.state = paramBoundedState;
    }
    
    public void call(SubjectSubscriptionManager.SubjectObserver<T> paramSubjectObserver)
    {
      paramSubjectObserver.index(this.state.replayObserverFromIndex(this.state.head(), paramSubjectObserver));
    }
  }
  
  static final class RemoveTimestamped
    implements Func1<Object, Object>
  {
    public Object call(Object paramObject)
    {
      return ((Timestamped)paramObject).getValue();
    }
  }
  
  static final class AddTimestamped
    implements Func1<Object, Object>
  {
    final Scheduler scheduler;
    
    public AddTimestamped(Scheduler paramScheduler)
    {
      this.scheduler = paramScheduler;
    }
    
    public Object call(Object paramObject)
    {
      return new Timestamped(this.scheduler.now(), paramObject);
    }
  }
  
  static final class PairEvictionPolicy
    implements ReplaySubject.EvictionPolicy
  {
    final ReplaySubject.EvictionPolicy first;
    final ReplaySubject.EvictionPolicy second;
    
    public PairEvictionPolicy(ReplaySubject.EvictionPolicy paramEvictionPolicy1, ReplaySubject.EvictionPolicy paramEvictionPolicy2)
    {
      this.first = paramEvictionPolicy1;
      this.second = paramEvictionPolicy2;
    }
    
    public void evict(ReplaySubject.NodeList<Object> paramNodeList)
    {
      this.first.evict(paramNodeList);
      this.second.evict(paramNodeList);
    }
    
    public void evictFinal(ReplaySubject.NodeList<Object> paramNodeList)
    {
      this.first.evictFinal(paramNodeList);
      this.second.evictFinal(paramNodeList);
    }
    
    public boolean test(Object paramObject, long paramLong)
    {
      if ((this.first.test(paramObject, paramLong)) || (this.second.test(paramObject, paramLong))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
  
  static final class TimeEvictionPolicy
    implements ReplaySubject.EvictionPolicy
  {
    final long maxAgeMillis;
    final Scheduler scheduler;
    
    public TimeEvictionPolicy(long paramLong, Scheduler paramScheduler)
    {
      this.maxAgeMillis = paramLong;
      this.scheduler = paramScheduler;
    }
    
    public void evict(ReplaySubject.NodeList<Object> paramNodeList)
    {
      long l = this.scheduler.now();
      while ((!paramNodeList.isEmpty()) && (test(paramNodeList.head.next.value, l))) {
        paramNodeList.removeFirst();
      }
    }
    
    public void evictFinal(ReplaySubject.NodeList<Object> paramNodeList)
    {
      long l = this.scheduler.now();
      while ((paramNodeList.size > 1) && (test(paramNodeList.head.next.value, l))) {
        paramNodeList.removeFirst();
      }
    }
    
    public boolean test(Object paramObject, long paramLong)
    {
      if (((Timestamped)paramObject).getTimestampMillis() <= paramLong - this.maxAgeMillis) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
  
  static final class SizeEvictionPolicy
    implements ReplaySubject.EvictionPolicy
  {
    final int maxSize;
    
    public SizeEvictionPolicy(int paramInt)
    {
      this.maxSize = paramInt;
    }
    
    public void evict(ReplaySubject.NodeList<Object> paramNodeList)
    {
      while (paramNodeList.size() > this.maxSize) {
        paramNodeList.removeFirst();
      }
    }
    
    public void evictFinal(ReplaySubject.NodeList<Object> paramNodeList)
    {
      while (paramNodeList.size() > 1 + this.maxSize) {
        paramNodeList.removeFirst();
      }
    }
    
    public boolean test(Object paramObject, long paramLong)
    {
      return false;
    }
  }
  
  static abstract interface EvictionPolicy
  {
    public abstract void evict(ReplaySubject.NodeList<Object> paramNodeList);
    
    public abstract void evictFinal(ReplaySubject.NodeList<Object> paramNodeList);
    
    public abstract boolean test(Object paramObject, long paramLong);
  }
  
  static abstract interface ReplayState<T, I>
  {
    public abstract void complete();
    
    public abstract void error(Throwable paramThrowable);
    
    public abstract boolean isEmpty();
    
    public abstract T latest();
    
    public abstract void next(T paramT);
    
    public abstract boolean replayObserver(SubjectSubscriptionManager.SubjectObserver<? super T> paramSubjectObserver);
    
    public abstract I replayObserverFromIndex(I paramI, SubjectSubscriptionManager.SubjectObserver<? super T> paramSubjectObserver);
    
    public abstract I replayObserverFromIndexTest(I paramI, SubjectSubscriptionManager.SubjectObserver<? super T> paramSubjectObserver, long paramLong);
    
    public abstract int size();
    
    public abstract boolean terminated();
    
    public abstract T[] toArray(T[] paramArrayOfT);
  }
  
  static final class BoundedState<T>
    implements ReplaySubject.ReplayState<T, ReplaySubject.NodeList.Node<Object>>
  {
    final Func1<Object, Object> enterTransform;
    final ReplaySubject.EvictionPolicy evictionPolicy;
    final Func1<Object, Object> leaveTransform;
    final ReplaySubject.NodeList<Object> list = new ReplaySubject.NodeList();
    final NotificationLite<T> nl = NotificationLite.instance();
    volatile ReplaySubject.NodeList.Node<Object> tail = this.list.tail;
    volatile boolean terminated;
    
    public BoundedState(ReplaySubject.EvictionPolicy paramEvictionPolicy, Func1<Object, Object> paramFunc11, Func1<Object, Object> paramFunc12)
    {
      this.evictionPolicy = paramEvictionPolicy;
      this.enterTransform = paramFunc11;
      this.leaveTransform = paramFunc12;
    }
    
    public void accept(Observer<? super T> paramObserver, ReplaySubject.NodeList.Node<Object> paramNode)
    {
      this.nl.accept(paramObserver, this.leaveTransform.call(paramNode.value));
    }
    
    public void acceptTest(Observer<? super T> paramObserver, ReplaySubject.NodeList.Node<Object> paramNode, long paramLong)
    {
      Object localObject = paramNode.value;
      if (!this.evictionPolicy.test(localObject, paramLong)) {
        this.nl.accept(paramObserver, this.leaveTransform.call(localObject));
      }
    }
    
    public void complete()
    {
      if (!this.terminated)
      {
        this.terminated = true;
        this.list.addLast(this.enterTransform.call(this.nl.completed()));
        this.evictionPolicy.evictFinal(this.list);
        this.tail = this.list.tail;
      }
    }
    
    public void error(Throwable paramThrowable)
    {
      if (!this.terminated)
      {
        this.terminated = true;
        this.list.addLast(this.enterTransform.call(this.nl.error(paramThrowable)));
        this.evictionPolicy.evictFinal(this.list);
        this.tail = this.list.tail;
      }
    }
    
    public ReplaySubject.NodeList.Node<Object> head()
    {
      return this.list.head;
    }
    
    public boolean isEmpty()
    {
      boolean bool = true;
      ReplaySubject.NodeList.Node localNode = head().next;
      if (localNode == null) {}
      for (;;)
      {
        return bool;
        Object localObject = this.leaveTransform.call(localNode.value);
        if ((!this.nl.isError(localObject)) && (!this.nl.isCompleted(localObject))) {
          bool = false;
        }
      }
    }
    
    public T latest()
    {
      Object localObject1 = null;
      ReplaySubject.NodeList.Node localNode1 = head().next;
      if (localNode1 == null) {}
      for (;;)
      {
        return (T)localObject1;
        ReplaySubject.NodeList.Node localNode2 = null;
        while (localNode1 != tail())
        {
          localNode2 = localNode1;
          localNode1 = localNode1.next;
        }
        Object localObject2 = this.leaveTransform.call(localNode1.value);
        if ((this.nl.isError(localObject2)) || (this.nl.isCompleted(localObject2)))
        {
          if (localNode2 != null)
          {
            Object localObject3 = this.leaveTransform.call(localNode2.value);
            localObject1 = this.nl.getValue(localObject3);
          }
        }
        else {
          localObject1 = this.nl.getValue(localObject2);
        }
      }
    }
    
    public void next(T paramT)
    {
      if (!this.terminated)
      {
        this.list.addLast(this.enterTransform.call(this.nl.next(paramT)));
        this.evictionPolicy.evict(this.list);
        this.tail = this.list.tail;
      }
    }
    
    public boolean replayObserver(SubjectSubscriptionManager.SubjectObserver<? super T> paramSubjectObserver)
    {
      boolean bool = false;
      try
      {
        paramSubjectObserver.first = false;
        if (paramSubjectObserver.emitting) {
          return bool;
        }
        paramSubjectObserver.index(replayObserverFromIndex((ReplaySubject.NodeList.Node)paramSubjectObserver.index(), paramSubjectObserver));
        bool = true;
      }
      finally {}
      return bool;
    }
    
    public ReplaySubject.NodeList.Node<Object> replayObserverFromIndex(ReplaySubject.NodeList.Node<Object> paramNode, SubjectSubscriptionManager.SubjectObserver<? super T> paramSubjectObserver)
    {
      while (paramNode != tail())
      {
        accept(paramSubjectObserver, paramNode.next);
        paramNode = paramNode.next;
      }
      return paramNode;
    }
    
    public ReplaySubject.NodeList.Node<Object> replayObserverFromIndexTest(ReplaySubject.NodeList.Node<Object> paramNode, SubjectSubscriptionManager.SubjectObserver<? super T> paramSubjectObserver, long paramLong)
    {
      while (paramNode != tail())
      {
        acceptTest(paramSubjectObserver, paramNode.next, paramLong);
        paramNode = paramNode.next;
      }
      return paramNode;
    }
    
    public int size()
    {
      int i = 0;
      Object localObject1 = head();
      for (ReplaySubject.NodeList.Node localNode = ((ReplaySubject.NodeList.Node)localObject1).next; localNode != null; localNode = localNode.next)
      {
        i++;
        localObject1 = localNode;
      }
      if (((ReplaySubject.NodeList.Node)localObject1).value != null)
      {
        Object localObject2 = this.leaveTransform.call(((ReplaySubject.NodeList.Node)localObject1).value);
        if ((localObject2 != null) && ((this.nl.isError(localObject2)) || (this.nl.isCompleted(localObject2)))) {
          i--;
        }
      }
      return i;
    }
    
    public ReplaySubject.NodeList.Node<Object> tail()
    {
      return this.tail;
    }
    
    public boolean terminated()
    {
      return this.terminated;
    }
    
    public T[] toArray(T[] paramArrayOfT)
    {
      ArrayList localArrayList = new ArrayList();
      for (ReplaySubject.NodeList.Node localNode = head().next;; localNode = localNode.next)
      {
        Object localObject;
        if (localNode != null)
        {
          localObject = this.leaveTransform.call(localNode.value);
          if ((localNode.next != null) || ((!this.nl.isError(localObject)) && (!this.nl.isCompleted(localObject)))) {}
        }
        else
        {
          return localArrayList.toArray(paramArrayOfT);
        }
        localArrayList.add(localObject);
      }
    }
  }
  
  static final class UnboundedReplayState<T>
    implements ReplaySubject.ReplayState<T, Integer>
  {
    static final AtomicIntegerFieldUpdater<UnboundedReplayState> INDEX_UPDATER = AtomicIntegerFieldUpdater.newUpdater(UnboundedReplayState.class, "index");
    volatile int index;
    private final ArrayList<Object> list;
    private final NotificationLite<T> nl = NotificationLite.instance();
    private volatile boolean terminated;
    
    public UnboundedReplayState(int paramInt)
    {
      this.list = new ArrayList(paramInt);
    }
    
    public void accept(Observer<? super T> paramObserver, int paramInt)
    {
      this.nl.accept(paramObserver, this.list.get(paramInt));
    }
    
    public void complete()
    {
      if (!this.terminated)
      {
        this.terminated = true;
        this.list.add(this.nl.completed());
        INDEX_UPDATER.getAndIncrement(this);
      }
    }
    
    public void error(Throwable paramThrowable)
    {
      if (!this.terminated)
      {
        this.terminated = true;
        this.list.add(this.nl.error(paramThrowable));
        INDEX_UPDATER.getAndIncrement(this);
      }
    }
    
    public boolean isEmpty()
    {
      if (size() == 0) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public T latest()
    {
      Object localObject1 = null;
      int i = this.index;
      Object localObject2;
      if (i > 0)
      {
        localObject2 = this.list.get(i - 1);
        if ((!this.nl.isCompleted(localObject2)) && (!this.nl.isError(localObject2))) {
          break label69;
        }
        if (i <= 1) {}
      }
      label69:
      for (localObject1 = this.nl.getValue(this.list.get(i - 2));; localObject1 = this.nl.getValue(localObject2)) {
        return (T)localObject1;
      }
    }
    
    public void next(T paramT)
    {
      if (!this.terminated)
      {
        this.list.add(this.nl.next(paramT));
        INDEX_UPDATER.getAndIncrement(this);
      }
    }
    
    public boolean replayObserver(SubjectSubscriptionManager.SubjectObserver<? super T> paramSubjectObserver)
    {
      boolean bool = false;
      try
      {
        paramSubjectObserver.first = false;
        if (paramSubjectObserver.emitting) {
          return bool;
        }
        Integer localInteger = (Integer)paramSubjectObserver.index();
        if (localInteger != null)
        {
          paramSubjectObserver.index(Integer.valueOf(replayObserverFromIndex(localInteger, paramSubjectObserver).intValue()));
          bool = true;
        }
      }
      finally {}
      throw new IllegalStateException("failed to find lastEmittedLink for: " + paramSubjectObserver);
      return bool;
    }
    
    public Integer replayObserverFromIndex(Integer paramInteger, SubjectSubscriptionManager.SubjectObserver<? super T> paramSubjectObserver)
    {
      for (int i = paramInteger.intValue(); i < this.index; i++) {
        accept(paramSubjectObserver, i);
      }
      return Integer.valueOf(i);
    }
    
    public Integer replayObserverFromIndexTest(Integer paramInteger, SubjectSubscriptionManager.SubjectObserver<? super T> paramSubjectObserver, long paramLong)
    {
      return replayObserverFromIndex(paramInteger, paramSubjectObserver);
    }
    
    public int size()
    {
      int i = this.index;
      if (i > 0)
      {
        Object localObject = this.list.get(i - 1);
        if ((this.nl.isCompleted(localObject)) || (this.nl.isError(localObject))) {
          i--;
        }
      }
      return i;
    }
    
    public boolean terminated()
    {
      return this.terminated;
    }
    
    public T[] toArray(T[] paramArrayOfT)
    {
      int i = size();
      if (i > 0)
      {
        if (i > paramArrayOfT.length) {
          paramArrayOfT = (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), i);
        }
        for (int j = 0; j < i; j++) {
          paramArrayOfT[j] = this.list.get(j);
        }
        if (paramArrayOfT.length > i) {
          paramArrayOfT[i] = null;
        }
      }
      for (;;)
      {
        return paramArrayOfT;
        if (paramArrayOfT.length > 0) {
          paramArrayOfT[0] = null;
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/subjects/ReplaySubject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */