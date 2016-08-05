package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable.Operator;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

public final class OperatorDebounceWithTime<T>
  implements Observable.Operator<T, T>
{
  final Scheduler scheduler;
  final long timeout;
  final TimeUnit unit;
  
  public OperatorDebounceWithTime(long paramLong, TimeUnit paramTimeUnit, Scheduler paramScheduler)
  {
    this.timeout = paramLong;
    this.unit = paramTimeUnit;
    this.scheduler = paramScheduler;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    final Scheduler.Worker localWorker = this.scheduler.createWorker();
    final SerializedSubscriber localSerializedSubscriber = new SerializedSubscriber(paramSubscriber);
    final SerialSubscription localSerialSubscription = new SerialSubscription();
    localSerializedSubscriber.add(localWorker);
    localSerializedSubscriber.add(localSerialSubscription);
    new Subscriber(paramSubscriber)
    {
      final Subscriber<?> self = this;
      final OperatorDebounceWithTime.DebounceState<T> state = new OperatorDebounceWithTime.DebounceState();
      
      public void onCompleted()
      {
        this.state.emitAndComplete(localSerializedSubscriber, this);
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        localSerializedSubscriber.onError(paramAnonymousThrowable);
        unsubscribe();
        this.state.clear();
      }
      
      public void onNext(T paramAnonymousT)
      {
        final int i = this.state.next(paramAnonymousT);
        localSerialSubscription.set(localWorker.schedule(new Action0()
        {
          public void call()
          {
            OperatorDebounceWithTime.1.this.state.emit(i, OperatorDebounceWithTime.1.this.val$s, OperatorDebounceWithTime.1.this.self);
          }
        }, OperatorDebounceWithTime.this.timeout, OperatorDebounceWithTime.this.unit));
      }
      
      public void onStart()
      {
        request(Long.MAX_VALUE);
      }
    };
  }
  
  static final class DebounceState<T>
  {
    boolean emitting;
    boolean hasValue;
    int index;
    boolean terminate;
    T value;
    
    /**
     * @deprecated
     */
    public void clear()
    {
      try
      {
        this.index = (1 + this.index);
        this.value = null;
        this.hasValue = false;
        return;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    /* Error */
    public void emit(int paramInt, Subscriber<T> paramSubscriber, Subscriber<?> paramSubscriber1)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 34	rx/internal/operators/OperatorDebounceWithTime$DebounceState:emitting	Z
      //   6: ifne +18 -> 24
      //   9: aload_0
      //   10: getfield 28	rx/internal/operators/OperatorDebounceWithTime$DebounceState:hasValue	Z
      //   13: ifeq +11 -> 24
      //   16: iload_1
      //   17: aload_0
      //   18: getfield 24	rx/internal/operators/OperatorDebounceWithTime$DebounceState:index	I
      //   21: if_icmpeq +8 -> 29
      //   24: aload_0
      //   25: monitorexit
      //   26: goto +82 -> 108
      //   29: aload_0
      //   30: getfield 26	rx/internal/operators/OperatorDebounceWithTime$DebounceState:value	Ljava/lang/Object;
      //   33: astore 5
      //   35: aload_0
      //   36: aconst_null
      //   37: putfield 26	rx/internal/operators/OperatorDebounceWithTime$DebounceState:value	Ljava/lang/Object;
      //   40: aload_0
      //   41: iconst_0
      //   42: putfield 28	rx/internal/operators/OperatorDebounceWithTime$DebounceState:hasValue	Z
      //   45: aload_0
      //   46: iconst_1
      //   47: putfield 34	rx/internal/operators/OperatorDebounceWithTime$DebounceState:emitting	Z
      //   50: aload_0
      //   51: monitorexit
      //   52: aload_2
      //   53: aload 5
      //   55: invokevirtual 40	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   58: aload_0
      //   59: monitorenter
      //   60: aload_0
      //   61: getfield 42	rx/internal/operators/OperatorDebounceWithTime$DebounceState:terminate	Z
      //   64: ifne +38 -> 102
      //   67: aload_0
      //   68: iconst_0
      //   69: putfield 34	rx/internal/operators/OperatorDebounceWithTime$DebounceState:emitting	Z
      //   72: aload_0
      //   73: monitorexit
      //   74: goto +34 -> 108
      //   77: astore 7
      //   79: aload_0
      //   80: monitorexit
      //   81: aload 7
      //   83: athrow
      //   84: astore 4
      //   86: aload_0
      //   87: monitorexit
      //   88: aload 4
      //   90: athrow
      //   91: astore 6
      //   93: aload_3
      //   94: aload 6
      //   96: invokevirtual 46	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   99: goto +9 -> 108
      //   102: aload_0
      //   103: monitorexit
      //   104: aload_2
      //   105: invokevirtual 49	rx/Subscriber:onCompleted	()V
      //   108: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	109	0	this	DebounceState
      //   0	109	1	paramInt	int
      //   0	109	2	paramSubscriber	Subscriber<T>
      //   0	109	3	paramSubscriber1	Subscriber<?>
      //   84	5	4	localObject1	Object
      //   33	21	5	localObject2	Object
      //   91	4	6	localThrowable	Throwable
      //   77	5	7	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   60	81	77	finally
      //   102	104	77	finally
      //   2	52	84	finally
      //   86	88	84	finally
      //   52	58	91	java/lang/Throwable
    }
    
    /* Error */
    public void emitAndComplete(Subscriber<T> paramSubscriber, Subscriber<?> paramSubscriber1)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 34	rx/internal/operators/OperatorDebounceWithTime$DebounceState:emitting	Z
      //   6: ifeq +13 -> 19
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield 42	rx/internal/operators/OperatorDebounceWithTime$DebounceState:terminate	Z
      //   14: aload_0
      //   15: monitorexit
      //   16: goto +63 -> 79
      //   19: aload_0
      //   20: getfield 26	rx/internal/operators/OperatorDebounceWithTime$DebounceState:value	Ljava/lang/Object;
      //   23: astore 4
      //   25: aload_0
      //   26: getfield 28	rx/internal/operators/OperatorDebounceWithTime$DebounceState:hasValue	Z
      //   29: istore 5
      //   31: aload_0
      //   32: aconst_null
      //   33: putfield 26	rx/internal/operators/OperatorDebounceWithTime$DebounceState:value	Ljava/lang/Object;
      //   36: aload_0
      //   37: iconst_0
      //   38: putfield 28	rx/internal/operators/OperatorDebounceWithTime$DebounceState:hasValue	Z
      //   41: aload_0
      //   42: iconst_1
      //   43: putfield 34	rx/internal/operators/OperatorDebounceWithTime$DebounceState:emitting	Z
      //   46: aload_0
      //   47: monitorexit
      //   48: iload 5
      //   50: ifeq +9 -> 59
      //   53: aload_1
      //   54: aload 4
      //   56: invokevirtual 40	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   59: aload_1
      //   60: invokevirtual 49	rx/Subscriber:onCompleted	()V
      //   63: goto +16 -> 79
      //   66: astore_3
      //   67: aload_0
      //   68: monitorexit
      //   69: aload_3
      //   70: athrow
      //   71: astore 6
      //   73: aload_2
      //   74: aload 6
      //   76: invokevirtual 46	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   79: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	80	0	this	DebounceState
      //   0	80	1	paramSubscriber	Subscriber<T>
      //   0	80	2	paramSubscriber1	Subscriber<?>
      //   66	4	3	localObject1	Object
      //   23	32	4	localObject2	Object
      //   29	20	5	bool	boolean
      //   71	4	6	localThrowable	Throwable
      // Exception table:
      //   from	to	target	type
      //   2	48	66	finally
      //   67	69	66	finally
      //   53	59	71	java/lang/Throwable
    }
    
    /**
     * @deprecated
     */
    public int next(T paramT)
    {
      try
      {
        this.value = paramT;
        this.hasValue = true;
        int i = 1 + this.index;
        this.index = i;
        return i;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorDebounceWithTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */