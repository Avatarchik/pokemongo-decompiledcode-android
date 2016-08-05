package rx.internal.operators;

import java.util.BitSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.functions.FuncN;
import rx.internal.util.RxRingBuffer;

public final class OnSubscribeCombineLatest<T, R>
  implements Observable.OnSubscribe<R>
{
  final FuncN<? extends R> combinator;
  final List<? extends Observable<? extends T>> sources;
  
  public OnSubscribeCombineLatest(List<? extends Observable<? extends T>> paramList, FuncN<? extends R> paramFuncN)
  {
    this.sources = paramList;
    this.combinator = paramFuncN;
    if (paramList.size() > RxRingBuffer.SIZE) {
      throw new IllegalArgumentException("More than RxRingBuffer.SIZE sources to combineLatest is not supported.");
    }
  }
  
  public void call(Subscriber<? super R> paramSubscriber)
  {
    if (this.sources.isEmpty()) {
      paramSubscriber.onCompleted();
    }
    for (;;)
    {
      return;
      if (this.sources.size() == 1) {
        paramSubscriber.setProducer(new SingleSourceProducer(paramSubscriber, (Observable)this.sources.get(0), this.combinator));
      } else {
        paramSubscriber.setProducer(new MultiSourceProducer(paramSubscriber, this.sources, this.combinator));
      }
    }
  }
  
  static final class SingleSourceRequestableSubscriber<T, R>
    extends Subscriber<T>
  {
    private final Subscriber<? super R> child;
    private final FuncN<? extends R> combinator;
    
    SingleSourceRequestableSubscriber(Subscriber<? super R> paramSubscriber, FuncN<? extends R> paramFuncN)
    {
      super();
      this.child = paramSubscriber;
      this.combinator = paramFuncN;
    }
    
    public void onCompleted()
    {
      this.child.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.child.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      Subscriber localSubscriber = this.child;
      FuncN localFuncN = this.combinator;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramT;
      localSubscriber.onNext(localFuncN.call(arrayOfObject));
    }
    
    public void requestMore(long paramLong)
    {
      request(paramLong);
    }
  }
  
  static final class SingleSourceProducer<T, R>
    implements Producer
  {
    final Subscriber<? super R> child;
    final FuncN<? extends R> combinator;
    final Observable<? extends T> source;
    final AtomicBoolean started = new AtomicBoolean();
    final OnSubscribeCombineLatest.SingleSourceRequestableSubscriber<T, R> subscriber;
    
    public SingleSourceProducer(Subscriber<? super R> paramSubscriber, Observable<? extends T> paramObservable, FuncN<? extends R> paramFuncN)
    {
      this.source = paramObservable;
      this.child = paramSubscriber;
      this.combinator = paramFuncN;
      this.subscriber = new OnSubscribeCombineLatest.SingleSourceRequestableSubscriber(paramSubscriber, paramFuncN);
    }
    
    public void request(long paramLong)
    {
      this.subscriber.requestMore(paramLong);
      if (this.started.compareAndSet(false, true)) {
        this.source.unsafeSubscribe(this.subscriber);
      }
    }
  }
  
  static final class MultiSourceRequestableSubscriber<T, R>
    extends Subscriber<T>
  {
    final AtomicLong emitted = new AtomicLong();
    boolean hasValue = false;
    final int index;
    final OnSubscribeCombineLatest.MultiSourceProducer<T, R> producer;
    
    public MultiSourceRequestableSubscriber(int paramInt1, int paramInt2, Subscriber<? super R> paramSubscriber, OnSubscribeCombineLatest.MultiSourceProducer<T, R> paramMultiSourceProducer)
    {
      super();
      this.index = paramInt1;
      this.producer = paramMultiSourceProducer;
      request(paramInt2);
    }
    
    public void onCompleted()
    {
      this.producer.onCompleted(this.index, this.hasValue);
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.producer.onError(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      this.hasValue = true;
      this.emitted.incrementAndGet();
      if (!this.producer.onNext(this.index, paramT)) {
        request(1L);
      }
    }
    
    public void requestUpTo(long paramLong)
    {
      long l1;
      long l2;
      do
      {
        l1 = this.emitted.get();
        l2 = Math.min(l1, paramLong);
      } while (!this.emitted.compareAndSet(l1, l1 - l2));
      request(l2);
    }
  }
  
  static final class MultiSourceProducer<T, R>
    implements Producer
  {
    private static final AtomicLongFieldUpdater<MultiSourceProducer> WIP = AtomicLongFieldUpdater.newUpdater(MultiSourceProducer.class, "counter");
    private final RxRingBuffer buffer = RxRingBuffer.getSpmcInstance();
    private final Subscriber<? super R> child;
    private final Object[] collectedValues;
    private final FuncN<? extends R> combinator;
    private final BitSet completion;
    private volatile int completionCount;
    private volatile long counter;
    private final BitSet haveValues;
    private volatile int haveValuesCount;
    private final AtomicLong requested = new AtomicLong();
    private final List<? extends Observable<? extends T>> sources;
    private final AtomicBoolean started = new AtomicBoolean();
    private final OnSubscribeCombineLatest.MultiSourceRequestableSubscriber<T, R>[] subscribers;
    
    public MultiSourceProducer(Subscriber<? super R> paramSubscriber, List<? extends Observable<? extends T>> paramList, FuncN<? extends R> paramFuncN)
    {
      this.sources = paramList;
      this.child = paramSubscriber;
      this.combinator = paramFuncN;
      int i = paramList.size();
      this.subscribers = new OnSubscribeCombineLatest.MultiSourceRequestableSubscriber[i];
      this.collectedValues = new Object[i];
      this.haveValues = new BitSet(i);
      this.completion = new BitSet(i);
    }
    
    /* Error */
    public void onCompleted(int paramInt, boolean paramBoolean)
    {
      // Byte code:
      //   0: iload_2
      //   1: ifne +11 -> 12
      //   4: aload_0
      //   5: getfield 78	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:child	Lrx/Subscriber;
      //   8: invokevirtual 107	rx/Subscriber:onCompleted	()V
      //   11: return
      //   12: iconst_0
      //   13: istore_3
      //   14: aload_0
      //   15: monitorenter
      //   16: aload_0
      //   17: getfield 101	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:completion	Ljava/util/BitSet;
      //   20: iload_1
      //   21: invokevirtual 111	java/util/BitSet:get	(I)Z
      //   24: ifne +35 -> 59
      //   27: aload_0
      //   28: getfield 101	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:completion	Ljava/util/BitSet;
      //   31: iload_1
      //   32: invokevirtual 114	java/util/BitSet:set	(I)V
      //   35: aload_0
      //   36: iconst_1
      //   37: aload_0
      //   38: getfield 116	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:completionCount	I
      //   41: iadd
      //   42: putfield 116	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:completionCount	I
      //   45: aload_0
      //   46: getfield 116	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:completionCount	I
      //   49: aload_0
      //   50: getfield 92	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:collectedValues	[Ljava/lang/Object;
      //   53: arraylength
      //   54: if_icmpne +25 -> 79
      //   57: iconst_1
      //   58: istore_3
      //   59: aload_0
      //   60: monitorexit
      //   61: iload_3
      //   62: ifeq -51 -> 11
      //   65: aload_0
      //   66: getfield 74	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:buffer	Lrx/internal/util/RxRingBuffer;
      //   69: invokevirtual 117	rx/internal/util/RxRingBuffer:onCompleted	()V
      //   72: aload_0
      //   73: invokevirtual 120	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:tick	()V
      //   76: goto -65 -> 11
      //   79: iconst_0
      //   80: istore_3
      //   81: goto -22 -> 59
      //   84: astore 4
      //   86: aload_0
      //   87: monitorexit
      //   88: aload 4
      //   90: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	91	0	this	MultiSourceProducer
      //   0	91	1	paramInt	int
      //   0	91	2	paramBoolean	boolean
      //   13	68	3	i	int
      //   84	5	4	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   16	61	84	finally
      //   86	88	84	finally
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.child.onError(paramThrowable);
    }
    
    /* Error */
    public boolean onNext(int paramInt, T paramT)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 99	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:haveValues	Ljava/util/BitSet;
      //   6: iload_1
      //   7: invokevirtual 111	java/util/BitSet:get	(I)Z
      //   10: ifne +21 -> 31
      //   13: aload_0
      //   14: getfield 99	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:haveValues	Ljava/util/BitSet;
      //   17: iload_1
      //   18: invokevirtual 114	java/util/BitSet:set	(I)V
      //   21: aload_0
      //   22: iconst_1
      //   23: aload_0
      //   24: getfield 132	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:haveValuesCount	I
      //   27: iadd
      //   28: putfield 132	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:haveValuesCount	I
      //   31: aload_0
      //   32: getfield 92	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:collectedValues	[Ljava/lang/Object;
      //   35: iload_1
      //   36: aload_2
      //   37: aastore
      //   38: aload_0
      //   39: getfield 132	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:haveValuesCount	I
      //   42: aload_0
      //   43: getfield 92	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:collectedValues	[Ljava/lang/Object;
      //   46: arraylength
      //   47: if_icmpeq +11 -> 58
      //   50: iconst_0
      //   51: istore 5
      //   53: aload_0
      //   54: monitorexit
      //   55: iload 5
      //   57: ireturn
      //   58: aload_0
      //   59: getfield 74	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:buffer	Lrx/internal/util/RxRingBuffer;
      //   62: aload_0
      //   63: getfield 80	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:combinator	Lrx/functions/FuncN;
      //   66: aload_0
      //   67: getfield 92	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:collectedValues	[Ljava/lang/Object;
      //   70: invokeinterface 138 2 0
      //   75: invokevirtual 141	rx/internal/util/RxRingBuffer:onNext	(Ljava/lang/Object;)V
      //   78: aload_0
      //   79: monitorexit
      //   80: aload_0
      //   81: invokevirtual 120	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:tick	()V
      //   84: iconst_1
      //   85: istore 5
      //   87: goto -32 -> 55
      //   90: astore 6
      //   92: aload_0
      //   93: aload 6
      //   95: invokevirtual 142	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:onError	(Ljava/lang/Throwable;)V
      //   98: goto -20 -> 78
      //   101: astore_3
      //   102: aload_0
      //   103: monitorexit
      //   104: aload_3
      //   105: athrow
      //   106: astore 4
      //   108: aload_0
      //   109: aload 4
      //   111: invokevirtual 142	rx/internal/operators/OnSubscribeCombineLatest$MultiSourceProducer:onError	(Ljava/lang/Throwable;)V
      //   114: goto -36 -> 78
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	117	0	this	MultiSourceProducer
      //   0	117	1	paramInt	int
      //   0	117	2	paramT	T
      //   101	4	3	localObject	Object
      //   106	4	4	localThrowable	Throwable
      //   51	35	5	bool	boolean
      //   90	4	6	localMissingBackpressureException	rx.exceptions.MissingBackpressureException
      // Exception table:
      //   from	to	target	type
      //   58	78	90	rx/exceptions/MissingBackpressureException
      //   2	55	101	finally
      //   58	78	101	finally
      //   78	80	101	finally
      //   92	104	101	finally
      //   108	114	101	finally
      //   58	78	106	java/lang/Throwable
    }
    
    public void request(long paramLong)
    {
      BackpressureUtils.getAndAddRequest(this.requested, paramLong);
      if ((!this.started.get()) && (this.started.compareAndSet(false, true)))
      {
        int i = RxRingBuffer.SIZE / this.sources.size();
        int j = RxRingBuffer.SIZE % this.sources.size();
        for (int k = 0; k < this.sources.size(); k++)
        {
          Observable localObservable = (Observable)this.sources.get(k);
          int m = i;
          if (k == -1 + this.sources.size()) {
            m += j;
          }
          OnSubscribeCombineLatest.MultiSourceRequestableSubscriber localMultiSourceRequestableSubscriber = new OnSubscribeCombineLatest.MultiSourceRequestableSubscriber(k, m, this.child, this);
          this.subscribers[k] = localMultiSourceRequestableSubscriber;
          localObservable.unsafeSubscribe(localMultiSourceRequestableSubscriber);
        }
      }
      tick();
    }
    
    void tick()
    {
      if (WIP.getAndIncrement(this) == 0L)
      {
        int i = 0;
        for (;;)
        {
          Object localObject;
          if (this.requested.get() > 0L)
          {
            localObject = this.buffer.poll();
            if (localObject != null)
            {
              if (!this.buffer.isCompleted(localObject)) {
                break label107;
              }
              this.child.onCompleted();
            }
          }
          while (WIP.decrementAndGet(this) <= 0L)
          {
            if (i <= 0) {
              return;
            }
            OnSubscribeCombineLatest.MultiSourceRequestableSubscriber[] arrayOfMultiSourceRequestableSubscriber = this.subscribers;
            int j = arrayOfMultiSourceRequestableSubscriber.length;
            for (int k = 0; k < j; k++) {
              arrayOfMultiSourceRequestableSubscriber[k].requestUpTo(i);
            }
            label107:
            this.buffer.accept(localObject, this.child);
            i++;
            this.requested.decrementAndGet();
          }
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OnSubscribeCombineLatest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */