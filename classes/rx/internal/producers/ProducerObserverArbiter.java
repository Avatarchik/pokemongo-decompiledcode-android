package rx.internal.producers;

import java.util.Iterator;
import java.util.List;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;

public final class ProducerObserverArbiter<T>
  implements Producer, Observer<T>
{
  static final Producer NULL_PRODUCER = new Producer()
  {
    public void request(long paramAnonymousLong) {}
  };
  final Subscriber<? super T> child;
  Producer currentProducer;
  boolean emitting;
  volatile boolean hasError;
  Producer missedProducer;
  long missedRequested;
  Object missedTerminal;
  List<T> queue;
  long requested;
  
  public ProducerObserverArbiter(Subscriber<? super T> paramSubscriber)
  {
    this.child = paramSubscriber;
  }
  
  void emitLoop()
  {
    Subscriber localSubscriber = this.child;
    for (;;)
    {
      long l1;
      Producer localProducer1;
      List localList;
      int i;
      for (;;)
      {
        try
        {
          l1 = this.missedRequested;
          localProducer1 = this.missedProducer;
          Object localObject2 = this.missedTerminal;
          localList = this.queue;
          if ((l1 == 0L) && (localProducer1 == null) && (localList == null) && (localObject2 == null))
          {
            this.emitting = false;
          }
          else
          {
            this.missedRequested = 0L;
            this.missedProducer = null;
            this.queue = null;
            this.missedTerminal = null;
            if ((localList == null) || (localList.isEmpty()))
            {
              i = 1;
              if (localObject2 == null) {
                break label149;
              }
              if (localObject2 == Boolean.TRUE) {
                break;
              }
              localSubscriber.onError((Throwable)localObject2);
            }
          }
        }
        finally {}
        i = 0;
      }
      if (i != 0)
      {
        localSubscriber.onCompleted();
        break;
      }
      label149:
      long l2 = 0L;
      if (localList != null)
      {
        Iterator localIterator = localList.iterator();
        for (;;)
        {
          if (localIterator.hasNext())
          {
            Object localObject3 = localIterator.next();
            if (localSubscriber.isUnsubscribed()) {
              return;
            }
            if (this.hasError) {
              break;
            }
            try
            {
              localSubscriber.onNext(localObject3);
            }
            catch (Throwable localThrowable)
            {
              Exceptions.throwIfFatal(localThrowable);
              localSubscriber.onError(OnErrorThrowable.addValueAsLastCause(localThrowable, localObject3));
              return;
            }
          }
        }
        l2 += localList.size();
      }
      long l3 = this.requested;
      if (l3 != Long.MAX_VALUE)
      {
        if (l1 != 0L)
        {
          long l5 = l3 + l1;
          if (l5 < 0L) {
            l5 = Long.MAX_VALUE;
          }
          l3 = l5;
        }
        if ((l2 != 0L) && (l3 != Long.MAX_VALUE))
        {
          long l4 = l3 - l2;
          if (l4 < 0L) {
            throw new IllegalStateException("More produced than requested");
          }
          l3 = l4;
        }
        this.requested = l3;
      }
      if (localProducer1 != null)
      {
        if (localProducer1 == NULL_PRODUCER)
        {
          this.currentProducer = null;
        }
        else
        {
          this.currentProducer = localProducer1;
          if (l3 != 0L) {
            localProducer1.request(l3);
          }
        }
      }
      else
      {
        Producer localProducer2 = this.currentProducer;
        if ((localProducer2 != null) && (l1 != 0L)) {
          localProducer2.request(l1);
        }
      }
    }
  }
  
  public void onCompleted()
  {
    try
    {
      if (this.emitting)
      {
        this.missedTerminal = Boolean.valueOf(true);
      }
      else
      {
        this.emitting = true;
        this.child.onCompleted();
      }
    }
    finally {}
  }
  
  public void onError(Throwable paramThrowable)
  {
    for (;;)
    {
      try
      {
        int i;
        if (this.emitting)
        {
          this.missedTerminal = paramThrowable;
          i = 0;
          if (i != 0) {
            this.child.onError(paramThrowable);
          }
        }
        else
        {
          this.emitting = true;
          i = 1;
          continue;
        }
        this.hasError = true;
      }
      finally {}
    }
  }
  
  /* Error */
  public void onNext(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   6: ifeq +44 -> 50
    //   9: aload_0
    //   10: getfield 51	rx/internal/producers/ProducerObserverArbiter:queue	Ljava/util/List;
    //   13: astore 8
    //   15: aload 8
    //   17: ifnonnull +19 -> 36
    //   20: new 134	java/util/ArrayList
    //   23: dup
    //   24: iconst_4
    //   25: invokespecial 137	java/util/ArrayList:<init>	(I)V
    //   28: astore 8
    //   30: aload_0
    //   31: aload 8
    //   33: putfield 51	rx/internal/producers/ProducerObserverArbiter:queue	Ljava/util/List;
    //   36: aload 8
    //   38: aload_1
    //   39: invokeinterface 141 2 0
    //   44: pop
    //   45: aload_0
    //   46: monitorexit
    //   47: goto +91 -> 138
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_0
    //   53: getfield 40	rx/internal/producers/ProducerObserverArbiter:child	Lrx/Subscriber;
    //   56: aload_1
    //   57: invokevirtual 96	rx/Subscriber:onNext	(Ljava/lang/Object;)V
    //   60: aload_0
    //   61: getfield 113	rx/internal/producers/ProducerObserverArbiter:requested	J
    //   64: lstore 5
    //   66: lload 5
    //   68: ldc2_w 114
    //   71: lcmp
    //   72: ifeq +11 -> 83
    //   75: aload_0
    //   76: lload 5
    //   78: lconst_1
    //   79: lsub
    //   80: putfield 113	rx/internal/producers/ProducerObserverArbiter:requested	J
    //   83: aload_0
    //   84: invokevirtual 143	rx/internal/producers/ProducerObserverArbiter:emitLoop	()V
    //   87: iconst_1
    //   88: ifne +50 -> 138
    //   91: aload_0
    //   92: monitorenter
    //   93: aload_0
    //   94: iconst_0
    //   95: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   98: aload_0
    //   99: monitorexit
    //   100: goto +38 -> 138
    //   103: astore 7
    //   105: aload_0
    //   106: monitorexit
    //   107: aload 7
    //   109: athrow
    //   110: astore_2
    //   111: aload_0
    //   112: monitorexit
    //   113: aload_2
    //   114: athrow
    //   115: astore_3
    //   116: iconst_0
    //   117: ifne +12 -> 129
    //   120: aload_0
    //   121: monitorenter
    //   122: aload_0
    //   123: iconst_0
    //   124: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   127: aload_0
    //   128: monitorexit
    //   129: aload_3
    //   130: athrow
    //   131: astore 4
    //   133: aload_0
    //   134: monitorexit
    //   135: aload 4
    //   137: athrow
    //   138: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	139	0	this	ProducerObserverArbiter
    //   0	139	1	paramT	T
    //   110	4	2	localObject1	Object
    //   115	15	3	localObject2	Object
    //   131	5	4	localObject3	Object
    //   64	13	5	l	long
    //   103	5	7	localObject4	Object
    //   13	24	8	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   93	107	103	finally
    //   2	52	110	finally
    //   111	113	110	finally
    //   52	87	115	finally
    //   122	129	131	finally
    //   133	135	131	finally
  }
  
  /* Error */
  public void request(long paramLong)
  {
    // Byte code:
    //   0: lload_1
    //   1: lconst_0
    //   2: lcmp
    //   3: ifge +13 -> 16
    //   6: new 145	java/lang/IllegalArgumentException
    //   9: dup
    //   10: ldc -109
    //   12: invokespecial 148	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   15: athrow
    //   16: lload_1
    //   17: lconst_0
    //   18: lcmp
    //   19: ifne +4 -> 23
    //   22: return
    //   23: aload_0
    //   24: monitorenter
    //   25: aload_0
    //   26: getfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   29: ifeq +23 -> 52
    //   32: aload_0
    //   33: lload_1
    //   34: aload_0
    //   35: getfield 45	rx/internal/producers/ProducerObserverArbiter:missedRequested	J
    //   38: ladd
    //   39: putfield 45	rx/internal/producers/ProducerObserverArbiter:missedRequested	J
    //   42: aload_0
    //   43: monitorexit
    //   44: goto -22 -> 22
    //   47: astore_3
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_3
    //   51: athrow
    //   52: aload_0
    //   53: iconst_1
    //   54: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   57: aload_0
    //   58: monitorexit
    //   59: lload_1
    //   60: aload_0
    //   61: getfield 113	rx/internal/producers/ProducerObserverArbiter:requested	J
    //   64: ladd
    //   65: lstore 6
    //   67: lload 6
    //   69: lconst_0
    //   70: lcmp
    //   71: ifge +8 -> 79
    //   74: ldc2_w 114
    //   77: lstore 6
    //   79: aload_0
    //   80: lload 6
    //   82: putfield 113	rx/internal/producers/ProducerObserverArbiter:requested	J
    //   85: aload_0
    //   86: getfield 124	rx/internal/producers/ProducerObserverArbiter:currentProducer	Lrx/Producer;
    //   89: astore 8
    //   91: aload 8
    //   93: ifnull +11 -> 104
    //   96: aload 8
    //   98: lload_1
    //   99: invokeinterface 128 3 0
    //   104: aload_0
    //   105: invokevirtual 143	rx/internal/producers/ProducerObserverArbiter:emitLoop	()V
    //   108: iconst_1
    //   109: ifne -87 -> 22
    //   112: aload_0
    //   113: monitorenter
    //   114: aload_0
    //   115: iconst_0
    //   116: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   119: aload_0
    //   120: monitorexit
    //   121: goto -99 -> 22
    //   124: astore 9
    //   126: aload_0
    //   127: monitorexit
    //   128: aload 9
    //   130: athrow
    //   131: astore 4
    //   133: iconst_0
    //   134: ifne +12 -> 146
    //   137: aload_0
    //   138: monitorenter
    //   139: aload_0
    //   140: iconst_0
    //   141: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   144: aload_0
    //   145: monitorexit
    //   146: aload 4
    //   148: athrow
    //   149: astore 5
    //   151: aload_0
    //   152: monitorexit
    //   153: aload 5
    //   155: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	156	0	this	ProducerObserverArbiter
    //   0	156	1	paramLong	long
    //   47	4	3	localObject1	Object
    //   131	16	4	localObject2	Object
    //   149	5	5	localObject3	Object
    //   65	16	6	l	long
    //   89	8	8	localProducer	Producer
    //   124	5	9	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   25	50	47	finally
    //   52	59	47	finally
    //   114	128	124	finally
    //   59	108	131	finally
    //   139	146	149	finally
    //   151	153	149	finally
  }
  
  /* Error */
  public void setProducer(Producer paramProducer)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   6: ifeq +24 -> 30
    //   9: aload_1
    //   10: ifnull +13 -> 23
    //   13: aload_0
    //   14: aload_1
    //   15: putfield 47	rx/internal/producers/ProducerObserverArbiter:missedProducer	Lrx/Producer;
    //   18: aload_0
    //   19: monitorexit
    //   20: goto +102 -> 122
    //   23: getstatic 36	rx/internal/producers/ProducerObserverArbiter:NULL_PRODUCER	Lrx/Producer;
    //   26: astore_1
    //   27: goto -14 -> 13
    //   30: aload_0
    //   31: iconst_1
    //   32: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_0
    //   38: aload_1
    //   39: putfield 124	rx/internal/producers/ProducerObserverArbiter:currentProducer	Lrx/Producer;
    //   42: aload_0
    //   43: getfield 113	rx/internal/producers/ProducerObserverArbiter:requested	J
    //   46: lstore 5
    //   48: aload_1
    //   49: ifnull +18 -> 67
    //   52: lload 5
    //   54: lconst_0
    //   55: lcmp
    //   56: ifeq +11 -> 67
    //   59: aload_1
    //   60: lload 5
    //   62: invokeinterface 128 3 0
    //   67: aload_0
    //   68: invokevirtual 143	rx/internal/producers/ProducerObserverArbiter:emitLoop	()V
    //   71: iconst_1
    //   72: ifne +50 -> 122
    //   75: aload_0
    //   76: monitorenter
    //   77: aload_0
    //   78: iconst_0
    //   79: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   82: aload_0
    //   83: monitorexit
    //   84: goto +38 -> 122
    //   87: astore 7
    //   89: aload_0
    //   90: monitorexit
    //   91: aload 7
    //   93: athrow
    //   94: astore_2
    //   95: aload_0
    //   96: monitorexit
    //   97: aload_2
    //   98: athrow
    //   99: astore_3
    //   100: iconst_0
    //   101: ifne +12 -> 113
    //   104: aload_0
    //   105: monitorenter
    //   106: aload_0
    //   107: iconst_0
    //   108: putfield 53	rx/internal/producers/ProducerObserverArbiter:emitting	Z
    //   111: aload_0
    //   112: monitorexit
    //   113: aload_3
    //   114: athrow
    //   115: astore 4
    //   117: aload_0
    //   118: monitorexit
    //   119: aload 4
    //   121: athrow
    //   122: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	123	0	this	ProducerObserverArbiter
    //   0	123	1	paramProducer	Producer
    //   94	4	2	localObject1	Object
    //   99	15	3	localObject2	Object
    //   115	5	4	localObject3	Object
    //   46	15	5	l	long
    //   87	5	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   77	91	87	finally
    //   2	37	94	finally
    //   95	97	94	finally
    //   37	71	99	finally
    //   106	113	115	finally
    //   117	119	115	finally
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/producers/ProducerObserverArbiter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */