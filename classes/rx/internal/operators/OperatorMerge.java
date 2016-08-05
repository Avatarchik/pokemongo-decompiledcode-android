package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.subscriptions.CompositeSubscription;

public final class OperatorMerge<T>
  implements Observable.Operator<T, Observable<? extends T>>
{
  final boolean delayErrors;
  final int maxConcurrent;
  
  private OperatorMerge(boolean paramBoolean, int paramInt)
  {
    this.delayErrors = paramBoolean;
    this.maxConcurrent = paramInt;
  }
  
  public static <T> OperatorMerge<T> instance(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (OperatorMerge localOperatorMerge = HolderDelayErrors.INSTANCE;; localOperatorMerge = HolderNoDelay.INSTANCE) {
      return localOperatorMerge;
    }
  }
  
  public static <T> OperatorMerge<T> instance(boolean paramBoolean, int paramInt)
  {
    if (paramInt == Integer.MAX_VALUE) {}
    for (OperatorMerge localOperatorMerge = instance(paramBoolean);; localOperatorMerge = new OperatorMerge(paramBoolean, paramInt)) {
      return localOperatorMerge;
    }
  }
  
  public Subscriber<Observable<? extends T>> call(Subscriber<? super T> paramSubscriber)
  {
    MergeSubscriber localMergeSubscriber = new MergeSubscriber(paramSubscriber, this.delayErrors, this.maxConcurrent);
    MergeProducer localMergeProducer = new MergeProducer(localMergeSubscriber);
    localMergeSubscriber.producer = localMergeProducer;
    paramSubscriber.add(localMergeSubscriber);
    paramSubscriber.setProducer(localMergeProducer);
    return localMergeSubscriber;
  }
  
  static final class InnerSubscriber<T>
    extends Subscriber<T>
  {
    static final int limit = RxRingBuffer.SIZE / 4;
    volatile boolean done;
    final long id;
    int outstanding;
    final OperatorMerge.MergeSubscriber<T> parent;
    volatile RxRingBuffer queue;
    
    public InnerSubscriber(OperatorMerge.MergeSubscriber<T> paramMergeSubscriber, long paramLong)
    {
      this.parent = paramMergeSubscriber;
      this.id = paramLong;
    }
    
    public void onCompleted()
    {
      this.done = true;
      this.parent.emit();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.done = true;
      this.parent.getOrCreateErrorQueue().offer(paramThrowable);
      this.parent.emit();
    }
    
    public void onNext(T paramT)
    {
      this.parent.tryEmit(this, paramT);
    }
    
    public void onStart()
    {
      this.outstanding = RxRingBuffer.SIZE;
      request(RxRingBuffer.SIZE);
    }
    
    public void requestMore(long paramLong)
    {
      int i = this.outstanding - (int)paramLong;
      if (i > limit) {
        this.outstanding = i;
      }
      for (;;)
      {
        return;
        this.outstanding = RxRingBuffer.SIZE;
        int j = RxRingBuffer.SIZE - i;
        if (j > 0) {
          request(j);
        }
      }
    }
  }
  
  static final class MergeSubscriber<T>
    extends Subscriber<Observable<? extends T>>
  {
    static final OperatorMerge.InnerSubscriber<?>[] EMPTY = new OperatorMerge.InnerSubscriber[0];
    final Subscriber<? super T> child;
    final boolean delayErrors;
    volatile boolean done;
    boolean emitting;
    volatile ConcurrentLinkedQueue<Throwable> errors;
    final Object innerGuard;
    volatile OperatorMerge.InnerSubscriber<?>[] innerSubscribers;
    long lastId;
    int lastIndex;
    final int maxConcurrent;
    boolean missed;
    final NotificationLite<T> nl;
    OperatorMerge.MergeProducer<T> producer;
    volatile RxRingBuffer queue;
    volatile CompositeSubscription subscriptions;
    long uniqueId;
    
    public MergeSubscriber(Subscriber<? super T> paramSubscriber, boolean paramBoolean, int paramInt)
    {
      this.child = paramSubscriber;
      this.delayErrors = paramBoolean;
      this.maxConcurrent = paramInt;
      this.nl = NotificationLite.instance();
      this.innerGuard = new Object();
      this.innerSubscribers = EMPTY;
      request(Math.min(paramInt, RxRingBuffer.SIZE));
    }
    
    private void reportError()
    {
      ArrayList localArrayList = new ArrayList(this.errors);
      if (localArrayList.size() == 1) {
        this.child.onError((Throwable)localArrayList.get(0));
      }
      for (;;)
      {
        return;
        this.child.onError(new CompositeException(localArrayList));
      }
    }
    
    void addInner(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber)
    {
      getOrCreateComposite().add(paramInnerSubscriber);
      synchronized (this.innerGuard)
      {
        OperatorMerge.InnerSubscriber[] arrayOfInnerSubscriber1 = this.innerSubscribers;
        int i = arrayOfInnerSubscriber1.length;
        OperatorMerge.InnerSubscriber[] arrayOfInnerSubscriber2 = new OperatorMerge.InnerSubscriber[i + 1];
        System.arraycopy(arrayOfInnerSubscriber1, 0, arrayOfInnerSubscriber2, 0, i);
        arrayOfInnerSubscriber2[i] = paramInnerSubscriber;
        this.innerSubscribers = arrayOfInnerSubscriber2;
        return;
      }
    }
    
    /* Error */
    boolean checkTerminate()
    {
      // Byte code:
      //   0: iconst_1
      //   1: istore_1
      //   2: aload_0
      //   3: getfield 53	rx/internal/operators/OperatorMerge$MergeSubscriber:child	Lrx/Subscriber;
      //   6: invokevirtual 137	rx/Subscriber:isUnsubscribed	()Z
      //   9: ifeq +5 -> 14
      //   12: iload_1
      //   13: ireturn
      //   14: aload_0
      //   15: getfield 92	rx/internal/operators/OperatorMerge$MergeSubscriber:errors	Ljava/util/concurrent/ConcurrentLinkedQueue;
      //   18: astore_2
      //   19: aload_0
      //   20: getfield 55	rx/internal/operators/OperatorMerge$MergeSubscriber:delayErrors	Z
      //   23: ifne +34 -> 57
      //   26: aload_2
      //   27: ifnull +30 -> 57
      //   30: aload_2
      //   31: invokeinterface 142 1 0
      //   36: ifne +21 -> 57
      //   39: aload_0
      //   40: invokespecial 144	rx/internal/operators/OperatorMerge$MergeSubscriber:reportError	()V
      //   43: aload_0
      //   44: invokevirtual 147	rx/internal/operators/OperatorMerge$MergeSubscriber:unsubscribe	()V
      //   47: goto -35 -> 12
      //   50: astore_3
      //   51: aload_0
      //   52: invokevirtual 147	rx/internal/operators/OperatorMerge$MergeSubscriber:unsubscribe	()V
      //   55: aload_3
      //   56: athrow
      //   57: iconst_0
      //   58: istore_1
      //   59: goto -47 -> 12
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	62	0	this	MergeSubscriber
      //   1	58	1	bool	boolean
      //   18	13	2	localConcurrentLinkedQueue	ConcurrentLinkedQueue
      //   50	6	3	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   39	43	50	finally
    }
    
    void emit()
    {
      try
      {
        if (this.emitting)
        {
          this.missed = true;
        }
        else
        {
          this.emitting = true;
          emitLoop();
        }
      }
      finally {}
    }
    
    void emitLoop()
    {
      i = 0;
      try
      {
        Subscriber localSubscriber = this.child;
        for (;;)
        {
          boolean bool1 = checkTerminate();
          RxRingBuffer localRxRingBuffer1;
          long l1;
          int j;
          Object localObject13;
          int i7;
          label146:
          label158:
          OperatorMerge.InnerSubscriber[] arrayOfInnerSubscriber;
          int m;
          label230:
          int k;
          if (bool1)
          {
            if (1 == 0) {
              try
              {
                this.emitting = false;
                break label929;
              }
              finally
              {
                localObject17 = finally;
                throw ((Throwable)localObject17);
              }
            }
          }
          else
          {
            localRxRingBuffer1 = this.queue;
            l1 = this.producer.get();
            if (l1 == Long.MAX_VALUE)
            {
              j = 1;
              break label930;
            }
            for (;;)
            {
              if (l1 > 0L)
              {
                localObject13 = localRxRingBuffer1.poll();
                boolean bool7 = checkTerminate();
                if (bool7)
                {
                  if (1 != 0) {
                    break label929;
                  }
                  try
                  {
                    this.emitting = false;
                    break label929;
                  }
                  finally
                  {
                    localObject16 = finally;
                    throw ((Throwable)localObject16);
                  }
                  j = 0;
                  break label930;
                }
                if (localObject13 != null) {}
              }
              else
              {
                if (i7 > 0)
                {
                  if (j == 0) {
                    break label377;
                  }
                  l1 = Long.MAX_VALUE;
                }
                if ((l1 != 0L) && (localObject13 != null)) {
                  break label938;
                }
                boolean bool2 = this.done;
                RxRingBuffer localRxRingBuffer2 = this.queue;
                arrayOfInnerSubscriber = this.innerSubscribers;
                m = arrayOfInnerSubscriber.length;
                if ((!bool2) || ((localRxRingBuffer2 != null) && (!localRxRingBuffer2.isEmpty())) || (m != 0)) {
                  break label398;
                }
                ConcurrentLinkedQueue localConcurrentLinkedQueue = this.errors;
                if ((localConcurrentLinkedQueue != null) && (!localConcurrentLinkedQueue.isEmpty())) {
                  break label391;
                }
                localSubscriber.onCompleted();
                if (localRxRingBuffer2 != null) {
                  localRxRingBuffer2.release();
                }
                if (1 != 0) {
                  break label929;
                }
                try
                {
                  this.emitting = false;
                  break label929;
                }
                finally
                {
                  localObject12 = finally;
                  throw ((Throwable)localObject12);
                }
              }
              Object localObject14 = this.nl.getValue(localObject13);
              try
              {
                localSubscriber.onNext(localObject14);
                k++;
                i7++;
                l1 -= 1L;
              }
              catch (Throwable localThrowable2)
              {
                for (;;)
                {
                  if (!this.delayErrors)
                  {
                    Exceptions.throwIfFatal(localThrowable2);
                    i = 1;
                    unsubscribe();
                    localSubscriber.onError(localThrowable2);
                    if (i != 0) {
                      break;
                    }
                    try
                    {
                      this.emitting = false;
                      break;
                    }
                    finally
                    {
                      localObject15 = finally;
                      throw ((Throwable)localObject15);
                    }
                  }
                  getOrCreateErrorQueue().offer(localThrowable2);
                }
              }
            }
          }
          try
          {
            this.emitting = false;
            throw ((Throwable)localObject1);
            l1 = this.producer.produced(i7);
            break label146;
            reportError();
            break label230;
            n = 0;
            if (m > 0)
            {
              long l2 = this.lastId;
              i1 = this.lastIndex;
              if (m > i1)
              {
                if (arrayOfInnerSubscriber[i1].id == l2) {
                  break label967;
                }
                break label947;
                for (;;)
                {
                  if ((i3 >= m) || (arrayOfInnerSubscriber[i2].id == l2))
                  {
                    i1 = i2;
                    this.lastIndex = i2;
                    this.lastId = arrayOfInnerSubscriber[i2].id;
                    break label967;
                    if (i5 >= m) {
                      break label812;
                    }
                    boolean bool3 = checkTerminate();
                    if (!bool3) {
                      break;
                    }
                    if (1 != 0) {
                      break label929;
                    }
                    try
                    {
                      this.emitting = false;
                      break label929;
                    }
                    finally
                    {
                      localObject11 = finally;
                      throw ((Throwable)localObject11);
                    }
                  }
                  i2++;
                  if (i2 == m) {
                    i2 = 0;
                  }
                  i3++;
                }
                OperatorMerge.InnerSubscriber localInnerSubscriber = arrayOfInnerSubscriber[i4];
                localObject5 = null;
                break label977;
                for (;;)
                {
                  RxRingBuffer localRxRingBuffer4;
                  if (l1 > 0L)
                  {
                    boolean bool6 = checkTerminate();
                    if (bool6)
                    {
                      if (1 != 0) {
                        break label929;
                      }
                      try
                      {
                        this.emitting = false;
                        break label929;
                      }
                      finally
                      {
                        localObject10 = finally;
                        throw ((Throwable)localObject10);
                      }
                    }
                    localRxRingBuffer4 = localInnerSubscriber.queue;
                    if (localRxRingBuffer4 != null) {
                      break label718;
                    }
                  }
                  do
                  {
                    if (i6 <= 0) {
                      break label983;
                    }
                    if (j != 0) {
                      break label998;
                    }
                    l1 = this.producer.produced(i6);
                    localInnerSubscriber.requestMore(i6);
                    break label983;
                    boolean bool4 = localInnerSubscriber.done;
                    RxRingBuffer localRxRingBuffer3 = localInnerSubscriber.queue;
                    if ((!bool4) || ((localRxRingBuffer3 != null) && (!localRxRingBuffer3.isEmpty()))) {
                      break label1012;
                    }
                    removeInner(localInnerSubscriber);
                    boolean bool5 = checkTerminate();
                    if (!bool5) {
                      break label1006;
                    }
                    if (1 != 0) {
                      break;
                    }
                    try
                    {
                      this.emitting = false;
                      break;
                    }
                    finally
                    {
                      localObject6 = finally;
                      throw ((Throwable)localObject6);
                    }
                    localObject5 = localRxRingBuffer4.poll();
                  } while (localObject5 == null);
                  Object localObject7 = this.nl.getValue(localObject5);
                  try
                  {
                    localSubscriber.onNext(localObject7);
                    l1 -= 1L;
                    i6++;
                  }
                  catch (Throwable localThrowable1)
                  {
                    i = 1;
                    Exceptions.throwIfFatal(localThrowable1);
                    try
                    {
                      localSubscriber.onError(localThrowable1);
                      unsubscribe();
                      if (i != 0) {
                        break label929;
                      }
                      try
                      {
                        this.emitting = false;
                        break label929;
                      }
                      finally
                      {
                        localObject9 = finally;
                        throw ((Throwable)localObject9);
                      }
                      this.lastIndex = i4;
                    }
                    finally
                    {
                      unsubscribe();
                    }
                  }
                }
                this.lastId = arrayOfInnerSubscriber[i4].id;
              }
            }
            else
            {
              if (k > 0) {
                request(k);
              }
              if (n != 0) {
                continue;
              }
              try
              {
                if (!this.missed)
                {
                  i = 1;
                  this.emitting = false;
                  if (i != 0) {
                    break label929;
                  }
                  try
                  {
                    this.emitting = false;
                    break label929;
                  }
                  finally
                  {
                    localObject4 = finally;
                    throw ((Throwable)localObject4);
                  }
                  i4++;
                  if (i4 == m) {
                    i4 = 0;
                  }
                  i5++;
                  break label488;
                }
                this.missed = false;
              }
              finally {}
            }
          }
          finally
          {
            for (;;)
            {
              int i1;
              Object localObject5;
              int i6;
              throw ((Throwable)localObject2);
              return;
              k = 0;
              if (localRxRingBuffer1 == null) {
                break label158;
              }
              i7 = 0;
              localObject13 = null;
              break;
              if (m <= i1) {
                i1 = 0;
              }
              int i2 = i1;
              int i3 = 0;
              continue;
              int i4 = i1;
              int i5 = 0;
              continue;
              do
              {
                i6 = 0;
                break;
                if (l1 == 0L) {
                  break label646;
                }
              } while (localObject5 != null);
              continue;
              l1 = Long.MAX_VALUE;
            }
            k++;
            int n = 1;
          }
        }
      }
      finally
      {
        if (i != 0) {}
      }
    }
    
    /* Error */
    protected void emitScalar(T paramT, long paramLong)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore 4
      //   3: aload_0
      //   4: getfield 53	rx/internal/operators/OperatorMerge$MergeSubscriber:child	Lrx/Subscriber;
      //   7: aload_1
      //   8: invokevirtual 189	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   11: lload_2
      //   12: ldc2_w 167
      //   15: lcmp
      //   16: ifeq +12 -> 28
      //   19: aload_0
      //   20: getfield 161	rx/internal/operators/OperatorMerge$MergeSubscriber:producer	Lrx/internal/operators/OperatorMerge$MergeProducer;
      //   23: iconst_1
      //   24: invokevirtual 206	rx/internal/operators/OperatorMerge$MergeProducer:produced	(I)J
      //   27: pop2
      //   28: aload_0
      //   29: lconst_1
      //   30: invokevirtual 224	rx/internal/operators/OperatorMerge$MergeSubscriber:requestMore	(J)V
      //   33: aload_0
      //   34: monitorenter
      //   35: iconst_1
      //   36: istore 4
      //   38: aload_0
      //   39: getfield 152	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   42: ifne +117 -> 159
      //   45: aload_0
      //   46: iconst_0
      //   47: putfield 150	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   50: aload_0
      //   51: monitorexit
      //   52: iload 4
      //   54: ifne +12 -> 66
      //   57: aload_0
      //   58: monitorenter
      //   59: aload_0
      //   60: iconst_0
      //   61: putfield 150	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   64: aload_0
      //   65: monitorexit
      //   66: return
      //   67: astore 7
      //   69: aload_0
      //   70: getfield 55	rx/internal/operators/OperatorMerge$MergeSubscriber:delayErrors	Z
      //   73: ifne +45 -> 118
      //   76: aload 7
      //   78: invokestatic 194	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   81: iconst_1
      //   82: istore 4
      //   84: aload_0
      //   85: invokevirtual 147	rx/internal/operators/OperatorMerge$MergeSubscriber:unsubscribe	()V
      //   88: aload_0
      //   89: aload 7
      //   91: invokevirtual 225	rx/internal/operators/OperatorMerge$MergeSubscriber:onError	(Ljava/lang/Throwable;)V
      //   94: iload 4
      //   96: ifne -30 -> 66
      //   99: aload_0
      //   100: monitorenter
      //   101: aload_0
      //   102: iconst_0
      //   103: putfield 150	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   106: aload_0
      //   107: monitorexit
      //   108: goto -42 -> 66
      //   111: astore 14
      //   113: aload_0
      //   114: monitorexit
      //   115: aload 14
      //   117: athrow
      //   118: aload_0
      //   119: invokevirtual 198	rx/internal/operators/OperatorMerge$MergeSubscriber:getOrCreateErrorQueue	()Ljava/util/Queue;
      //   122: aload 7
      //   124: invokeinterface 202 2 0
      //   129: pop
      //   130: goto -119 -> 11
      //   133: astore 5
      //   135: iload 4
      //   137: ifne +12 -> 149
      //   140: aload_0
      //   141: monitorenter
      //   142: aload_0
      //   143: iconst_0
      //   144: putfield 150	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   147: aload_0
      //   148: monitorexit
      //   149: aload 5
      //   151: athrow
      //   152: astore 11
      //   154: aload_0
      //   155: monitorexit
      //   156: aload 11
      //   158: athrow
      //   159: aload_0
      //   160: iconst_0
      //   161: putfield 152	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   164: aload_0
      //   165: monitorexit
      //   166: iload 4
      //   168: ifne +12 -> 180
      //   171: aload_0
      //   172: monitorenter
      //   173: aload_0
      //   174: iconst_0
      //   175: putfield 150	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   178: aload_0
      //   179: monitorexit
      //   180: aload_0
      //   181: invokevirtual 155	rx/internal/operators/OperatorMerge$MergeSubscriber:emitLoop	()V
      //   184: goto -118 -> 66
      //   187: astore 9
      //   189: aload_0
      //   190: monitorexit
      //   191: aload 9
      //   193: athrow
      //   194: astore 10
      //   196: aload_0
      //   197: monitorexit
      //   198: aload 10
      //   200: athrow
      //   201: astore 6
      //   203: aload_0
      //   204: monitorexit
      //   205: aload 6
      //   207: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	208	0	this	MergeSubscriber
      //   0	208	1	paramT	T
      //   0	208	2	paramLong	long
      //   1	166	4	i	int
      //   133	17	5	localObject1	Object
      //   201	5	6	localObject2	Object
      //   67	56	7	localThrowable	Throwable
      //   187	5	9	localObject3	Object
      //   194	5	10	localObject4	Object
      //   152	5	11	localObject5	Object
      //   111	5	14	localObject6	Object
      // Exception table:
      //   from	to	target	type
      //   3	11	67	java/lang/Throwable
      //   101	115	111	finally
      //   3	11	133	finally
      //   19	35	133	finally
      //   69	94	133	finally
      //   118	130	133	finally
      //   191	194	133	finally
      //   59	66	152	finally
      //   154	156	152	finally
      //   38	52	187	finally
      //   159	166	187	finally
      //   189	191	187	finally
      //   173	180	194	finally
      //   196	198	194	finally
      //   142	149	201	finally
      //   203	205	201	finally
    }
    
    /* Error */
    protected void emitScalar(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber, T paramT, long paramLong)
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore 5
      //   3: aload_0
      //   4: getfield 53	rx/internal/operators/OperatorMerge$MergeSubscriber:child	Lrx/Subscriber;
      //   7: aload_2
      //   8: invokevirtual 189	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   11: lload_3
      //   12: ldc2_w 167
      //   15: lcmp
      //   16: ifeq +12 -> 28
      //   19: aload_0
      //   20: getfield 161	rx/internal/operators/OperatorMerge$MergeSubscriber:producer	Lrx/internal/operators/OperatorMerge$MergeProducer;
      //   23: iconst_1
      //   24: invokevirtual 206	rx/internal/operators/OperatorMerge$MergeProducer:produced	(I)J
      //   27: pop2
      //   28: aload_1
      //   29: lconst_1
      //   30: invokevirtual 217	rx/internal/operators/OperatorMerge$InnerSubscriber:requestMore	(J)V
      //   33: aload_0
      //   34: monitorenter
      //   35: iconst_1
      //   36: istore 5
      //   38: aload_0
      //   39: getfield 152	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   42: ifne +117 -> 159
      //   45: aload_0
      //   46: iconst_0
      //   47: putfield 150	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   50: aload_0
      //   51: monitorexit
      //   52: iload 5
      //   54: ifne +12 -> 66
      //   57: aload_0
      //   58: monitorenter
      //   59: aload_0
      //   60: iconst_0
      //   61: putfield 150	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   64: aload_0
      //   65: monitorexit
      //   66: return
      //   67: astore 8
      //   69: aload_0
      //   70: getfield 55	rx/internal/operators/OperatorMerge$MergeSubscriber:delayErrors	Z
      //   73: ifne +45 -> 118
      //   76: aload 8
      //   78: invokestatic 194	rx/exceptions/Exceptions:throwIfFatal	(Ljava/lang/Throwable;)V
      //   81: iconst_1
      //   82: istore 5
      //   84: aload_1
      //   85: invokevirtual 227	rx/internal/operators/OperatorMerge$InnerSubscriber:unsubscribe	()V
      //   88: aload_1
      //   89: aload 8
      //   91: invokevirtual 228	rx/internal/operators/OperatorMerge$InnerSubscriber:onError	(Ljava/lang/Throwable;)V
      //   94: iload 5
      //   96: ifne -30 -> 66
      //   99: aload_0
      //   100: monitorenter
      //   101: aload_0
      //   102: iconst_0
      //   103: putfield 150	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   106: aload_0
      //   107: monitorexit
      //   108: goto -42 -> 66
      //   111: astore 15
      //   113: aload_0
      //   114: monitorexit
      //   115: aload 15
      //   117: athrow
      //   118: aload_0
      //   119: invokevirtual 198	rx/internal/operators/OperatorMerge$MergeSubscriber:getOrCreateErrorQueue	()Ljava/util/Queue;
      //   122: aload 8
      //   124: invokeinterface 202 2 0
      //   129: pop
      //   130: goto -119 -> 11
      //   133: astore 6
      //   135: iload 5
      //   137: ifne +12 -> 149
      //   140: aload_0
      //   141: monitorenter
      //   142: aload_0
      //   143: iconst_0
      //   144: putfield 150	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   147: aload_0
      //   148: monitorexit
      //   149: aload 6
      //   151: athrow
      //   152: astore 12
      //   154: aload_0
      //   155: monitorexit
      //   156: aload 12
      //   158: athrow
      //   159: aload_0
      //   160: iconst_0
      //   161: putfield 152	rx/internal/operators/OperatorMerge$MergeSubscriber:missed	Z
      //   164: aload_0
      //   165: monitorexit
      //   166: iload 5
      //   168: ifne +12 -> 180
      //   171: aload_0
      //   172: monitorenter
      //   173: aload_0
      //   174: iconst_0
      //   175: putfield 150	rx/internal/operators/OperatorMerge$MergeSubscriber:emitting	Z
      //   178: aload_0
      //   179: monitorexit
      //   180: aload_0
      //   181: invokevirtual 155	rx/internal/operators/OperatorMerge$MergeSubscriber:emitLoop	()V
      //   184: goto -118 -> 66
      //   187: astore 10
      //   189: aload_0
      //   190: monitorexit
      //   191: aload 10
      //   193: athrow
      //   194: astore 11
      //   196: aload_0
      //   197: monitorexit
      //   198: aload 11
      //   200: athrow
      //   201: astore 7
      //   203: aload_0
      //   204: monitorexit
      //   205: aload 7
      //   207: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	208	0	this	MergeSubscriber
      //   0	208	1	paramInnerSubscriber	OperatorMerge.InnerSubscriber<T>
      //   0	208	2	paramT	T
      //   0	208	3	paramLong	long
      //   1	166	5	i	int
      //   133	17	6	localObject1	Object
      //   201	5	7	localObject2	Object
      //   67	56	8	localThrowable	Throwable
      //   187	5	10	localObject3	Object
      //   194	5	11	localObject4	Object
      //   152	5	12	localObject5	Object
      //   111	5	15	localObject6	Object
      // Exception table:
      //   from	to	target	type
      //   3	11	67	java/lang/Throwable
      //   101	115	111	finally
      //   3	11	133	finally
      //   19	35	133	finally
      //   69	94	133	finally
      //   118	130	133	finally
      //   191	194	133	finally
      //   59	66	152	finally
      //   154	156	152	finally
      //   38	52	187	finally
      //   159	166	187	finally
      //   189	191	187	finally
      //   173	180	194	finally
      //   196	198	194	finally
      //   142	149	201	finally
      //   203	205	201	finally
    }
    
    CompositeSubscription getOrCreateComposite()
    {
      Object localObject1 = this.subscriptions;
      int i;
      if (localObject1 == null) {
        i = 0;
      }
      try
      {
        localObject1 = this.subscriptions;
        if (localObject1 == null) {
          localCompositeSubscription = new CompositeSubscription();
        }
      }
      finally
      {
        try
        {
          CompositeSubscription localCompositeSubscription;
          this.subscriptions = localCompositeSubscription;
          i = 1;
          localObject1 = localCompositeSubscription;
          if (i != 0) {
            add((Subscription)localObject1);
          }
          return (CompositeSubscription)localObject1;
        }
        finally {}
        localObject2 = finally;
      }
      throw ((Throwable)localObject2);
    }
    
    Queue<Throwable> getOrCreateErrorQueue()
    {
      Object localObject1 = this.errors;
      if (localObject1 == null)
      {
        for (;;)
        {
          try
          {
            localObject1 = this.errors;
            if (localObject1 == null) {
              localConcurrentLinkedQueue = new ConcurrentLinkedQueue();
            }
          }
          finally
          {
            ConcurrentLinkedQueue localConcurrentLinkedQueue;
            Object localObject2;
            continue;
          }
          try
          {
            this.errors = localConcurrentLinkedQueue;
            localObject1 = localConcurrentLinkedQueue;
          }
          finally {}
        }
        throw ((Throwable)localObject2);
      }
      return (Queue<Throwable>)localObject1;
    }
    
    public void onCompleted()
    {
      this.done = true;
      emit();
    }
    
    public void onError(Throwable paramThrowable)
    {
      getOrCreateErrorQueue().offer(paramThrowable);
      this.done = true;
      emit();
    }
    
    public void onNext(Observable<? extends T> paramObservable)
    {
      if (paramObservable == null) {}
      for (;;)
      {
        return;
        if ((paramObservable instanceof ScalarSynchronousObservable))
        {
          tryEmit(((ScalarSynchronousObservable)paramObservable).get());
        }
        else
        {
          long l = this.uniqueId;
          this.uniqueId = (1L + l);
          OperatorMerge.InnerSubscriber localInnerSubscriber = new OperatorMerge.InnerSubscriber(this, l);
          addInner(localInnerSubscriber);
          paramObservable.unsafeSubscribe(localInnerSubscriber);
          emit();
        }
      }
    }
    
    protected void queueScalar(T paramT)
    {
      RxRingBuffer localRxRingBuffer = this.queue;
      if (localRxRingBuffer == null)
      {
        localRxRingBuffer = RxRingBuffer.getSpscInstance();
        add(localRxRingBuffer);
        this.queue = localRxRingBuffer;
      }
      try
      {
        localRxRingBuffer.onNext(this.nl.next(paramT));
        emit();
        return;
      }
      catch (MissingBackpressureException localMissingBackpressureException)
      {
        for (;;)
        {
          unsubscribe();
          onError(localMissingBackpressureException);
        }
      }
      catch (IllegalStateException localIllegalStateException)
      {
        for (;;)
        {
          if (!isUnsubscribed())
          {
            unsubscribe();
            onError(localIllegalStateException);
          }
        }
      }
    }
    
    protected void queueScalar(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber, T paramT)
    {
      RxRingBuffer localRxRingBuffer = paramInnerSubscriber.queue;
      if (localRxRingBuffer == null)
      {
        localRxRingBuffer = RxRingBuffer.getSpscInstance();
        paramInnerSubscriber.add(localRxRingBuffer);
        paramInnerSubscriber.queue = localRxRingBuffer;
      }
      try
      {
        localRxRingBuffer.onNext(this.nl.next(paramT));
        emit();
        return;
      }
      catch (MissingBackpressureException localMissingBackpressureException)
      {
        for (;;)
        {
          paramInnerSubscriber.unsubscribe();
          paramInnerSubscriber.onError(localMissingBackpressureException);
        }
      }
      catch (IllegalStateException localIllegalStateException)
      {
        for (;;)
        {
          if (!paramInnerSubscriber.isUnsubscribed())
          {
            paramInnerSubscriber.unsubscribe();
            paramInnerSubscriber.onError(localIllegalStateException);
          }
        }
      }
    }
    
    void removeInner(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber)
    {
      RxRingBuffer localRxRingBuffer = paramInnerSubscriber.queue;
      if (localRxRingBuffer != null) {
        localRxRingBuffer.release();
      }
      this.subscriptions.remove(paramInnerSubscriber);
      for (;;)
      {
        OperatorMerge.InnerSubscriber[] arrayOfInnerSubscriber1;
        int i;
        int j;
        int k;
        int m;
        OperatorMerge.InnerSubscriber[] arrayOfInnerSubscriber2;
        synchronized (this.innerGuard)
        {
          arrayOfInnerSubscriber1 = this.innerSubscribers;
          i = arrayOfInnerSubscriber1.length;
          j = -1;
          k = 0;
          if (k < i)
          {
            if (!paramInnerSubscriber.equals(arrayOfInnerSubscriber1[k])) {
              break label158;
            }
            j = k;
          }
          if (j >= 0) {
            if (i == 1) {
              this.innerSubscribers = EMPTY;
            }
          }
        }
        return;
        label158:
        k++;
      }
    }
    
    public void requestMore(long paramLong)
    {
      request(paramLong);
    }
    
    void tryEmit(T paramT)
    {
      int i = 0;
      long l = this.producer.get();
      if (l != 0L) {}
      for (;;)
      {
        try
        {
          if (!this.emitting)
          {
            this.emitting = true;
            i = 1;
          }
          if (i != 0)
          {
            emitScalar(paramT, l);
            return;
          }
        }
        finally {}
        queueScalar(paramT);
      }
    }
    
    void tryEmit(OperatorMerge.InnerSubscriber<T> paramInnerSubscriber, T paramT)
    {
      int i = 0;
      long l = this.producer.get();
      if (l != 0L) {}
      for (;;)
      {
        try
        {
          if (!this.emitting)
          {
            this.emitting = true;
            i = 1;
          }
          if (i != 0)
          {
            emitScalar(paramInnerSubscriber, paramT, l);
            return;
          }
        }
        finally {}
        queueScalar(paramInnerSubscriber, paramT);
      }
    }
  }
  
  static final class MergeProducer<T>
    extends AtomicLong
    implements Producer
  {
    private static final long serialVersionUID = -1214379189873595503L;
    final OperatorMerge.MergeSubscriber<T> subscriber;
    
    public MergeProducer(OperatorMerge.MergeSubscriber<T> paramMergeSubscriber)
    {
      this.subscriber = paramMergeSubscriber;
    }
    
    public long produced(int paramInt)
    {
      return addAndGet(-paramInt);
    }
    
    public void request(long paramLong)
    {
      if (paramLong > 0L) {
        if (get() != Long.MAX_VALUE) {}
      }
      while (paramLong >= 0L) {
        for (;;)
        {
          return;
          BackpressureUtils.getAndAddRequest(this, paramLong);
          this.subscriber.emit();
        }
      }
      throw new IllegalArgumentException("n >= 0 required");
    }
  }
  
  private static final class HolderDelayErrors
  {
    static final OperatorMerge<Object> INSTANCE = new OperatorMerge(true, Integer.MAX_VALUE, null);
  }
  
  private static final class HolderNoDelay
  {
    static final OperatorMerge<Object> INSTANCE = new OperatorMerge(false, Integer.MAX_VALUE, null);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorMerge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */