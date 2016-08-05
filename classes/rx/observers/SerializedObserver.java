package rx.observers;

import rx.Observer;
import rx.exceptions.Exceptions;

public class SerializedObserver<T>
  implements Observer<T>
{
  private static final Object COMPLETE_SENTINEL = new Object();
  private static final int MAX_DRAIN_ITERATION = Integer.MAX_VALUE;
  private static final Object NULL_SENTINEL = new Object();
  private final Observer<? super T> actual;
  private boolean emitting = false;
  private FastList queue;
  private boolean terminated = false;
  
  public SerializedObserver(Observer<? super T> paramObserver)
  {
    this.actual = paramObserver;
  }
  
  void drainQueue(FastList paramFastList)
  {
    if ((paramFastList == null) || (paramFastList.size == 0)) {}
    int j;
    label23:
    Object localObject;
    do
    {
      return;
      Object[] arrayOfObject = paramFastList.array;
      int i = arrayOfObject.length;
      j = 0;
      if (j >= i) {
        break;
      }
      localObject = arrayOfObject[j];
    } while (localObject == null);
    if (localObject == NULL_SENTINEL) {
      this.actual.onNext(null);
    }
    for (;;)
    {
      j++;
      break label23;
      break;
      if (localObject == COMPLETE_SENTINEL) {
        this.actual.onCompleted();
      } else if (localObject.getClass() == ErrorSentinel.class) {
        this.actual.onError(((ErrorSentinel)localObject).e);
      } else {
        this.actual.onNext(localObject);
      }
    }
  }
  
  public void onCompleted()
  {
    FastList localFastList;
    try
    {
      if (this.terminated) {
        return;
      }
      this.terminated = true;
      if (this.emitting)
      {
        if (this.queue == null) {
          this.queue = new FastList();
        }
        this.queue.add(COMPLETE_SENTINEL);
      }
    }
    finally
    {
      throw ((Throwable)localObject);
      this.emitting = true;
      localFastList = this.queue;
      this.queue = null;
    }
  }
  
  public void onError(Throwable paramThrowable)
  {
    Exceptions.throwIfFatal(paramThrowable);
    try
    {
      if (this.terminated) {
        return;
      }
      if (this.emitting)
      {
        if (this.queue == null) {
          this.queue = new FastList();
        }
        this.queue.add(new ErrorSentinel(paramThrowable));
      }
    }
    finally
    {
      throw ((Throwable)localObject1);
      this.emitting = true;
      FastList localFastList = this.queue;
      this.queue = null;
      drainQueue(localFastList);
      this.actual.onError(paramThrowable);
    }
  }
  
  /* Error */
  public void onNext(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 41	rx/observers/SerializedObserver:terminated	Z
    //   6: ifeq +8 -> 14
    //   9: aload_0
    //   10: monitorexit
    //   11: goto +295 -> 306
    //   14: aload_0
    //   15: getfield 39	rx/observers/SerializedObserver:emitting	Z
    //   18: ifeq +54 -> 72
    //   21: aload_0
    //   22: getfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   25: ifnonnull +14 -> 39
    //   28: aload_0
    //   29: new 12	rx/observers/SerializedObserver$FastList
    //   32: dup
    //   33: invokespecial 74	rx/observers/SerializedObserver$FastList:<init>	()V
    //   36: putfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   39: aload_0
    //   40: getfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   43: astore 13
    //   45: aload_1
    //   46: ifnull +19 -> 65
    //   49: aload 13
    //   51: aload_1
    //   52: invokevirtual 77	rx/observers/SerializedObserver$FastList:add	(Ljava/lang/Object;)V
    //   55: aload_0
    //   56: monitorexit
    //   57: goto +249 -> 306
    //   60: astore_2
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_2
    //   64: athrow
    //   65: getstatic 34	rx/observers/SerializedObserver:NULL_SENTINEL	Ljava/lang/Object;
    //   68: astore_1
    //   69: goto -20 -> 49
    //   72: aload_0
    //   73: iconst_1
    //   74: putfield 39	rx/observers/SerializedObserver:emitting	Z
    //   77: aload_0
    //   78: getfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   81: astore_3
    //   82: aload_0
    //   83: aconst_null
    //   84: putfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   87: aload_0
    //   88: monitorexit
    //   89: iconst_0
    //   90: istore 4
    //   92: ldc 18
    //   94: istore 5
    //   96: aload_0
    //   97: aload_3
    //   98: invokevirtual 79	rx/observers/SerializedObserver:drainQueue	(Lrx/observers/SerializedObserver$FastList;)V
    //   101: iload 5
    //   103: ldc 18
    //   105: if_icmpne +13 -> 118
    //   108: aload_0
    //   109: getfield 43	rx/observers/SerializedObserver:actual	Lrx/Observer;
    //   112: aload_1
    //   113: invokeinterface 56 2 0
    //   118: iinc 5 -1
    //   121: iload 5
    //   123: ifle +75 -> 198
    //   126: aload_0
    //   127: monitorenter
    //   128: aload_0
    //   129: getfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   132: astore_3
    //   133: aload_0
    //   134: aconst_null
    //   135: putfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   138: aload_3
    //   139: ifnonnull +57 -> 196
    //   142: aload_0
    //   143: iconst_0
    //   144: putfield 39	rx/observers/SerializedObserver:emitting	Z
    //   147: iconst_1
    //   148: istore 4
    //   150: aload_0
    //   151: monitorexit
    //   152: iload 4
    //   154: ifne +152 -> 306
    //   157: aload_0
    //   158: monitorenter
    //   159: aload_0
    //   160: getfield 41	rx/observers/SerializedObserver:terminated	Z
    //   163: ifeq +25 -> 188
    //   166: aload_0
    //   167: getfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   170: pop
    //   171: aload_0
    //   172: aconst_null
    //   173: putfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   176: aload_0
    //   177: monitorexit
    //   178: goto +128 -> 306
    //   181: astore 11
    //   183: aload_0
    //   184: monitorexit
    //   185: aload 11
    //   187: athrow
    //   188: aload_0
    //   189: iconst_0
    //   190: putfield 39	rx/observers/SerializedObserver:emitting	Z
    //   193: goto -17 -> 176
    //   196: aload_0
    //   197: monitorexit
    //   198: iload 5
    //   200: ifgt -104 -> 96
    //   203: iconst_0
    //   204: ifne +24 -> 228
    //   207: aload_0
    //   208: monitorenter
    //   209: aload_0
    //   210: getfield 41	rx/observers/SerializedObserver:terminated	Z
    //   213: ifeq +61 -> 274
    //   216: aload_0
    //   217: getfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   220: astore_3
    //   221: aload_0
    //   222: aconst_null
    //   223: putfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   226: aload_0
    //   227: monitorexit
    //   228: aload_0
    //   229: aload_3
    //   230: invokevirtual 79	rx/observers/SerializedObserver:drainQueue	(Lrx/observers/SerializedObserver$FastList;)V
    //   233: goto +73 -> 306
    //   236: astore 10
    //   238: aload_0
    //   239: monitorexit
    //   240: aload 10
    //   242: athrow
    //   243: astore 6
    //   245: iload 4
    //   247: ifne +24 -> 271
    //   250: aload_0
    //   251: monitorenter
    //   252: aload_0
    //   253: getfield 41	rx/observers/SerializedObserver:terminated	Z
    //   256: ifeq +35 -> 291
    //   259: aload_0
    //   260: getfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   263: pop
    //   264: aload_0
    //   265: aconst_null
    //   266: putfield 73	rx/observers/SerializedObserver:queue	Lrx/observers/SerializedObserver$FastList;
    //   269: aload_0
    //   270: monitorexit
    //   271: aload 6
    //   273: athrow
    //   274: aload_0
    //   275: iconst_0
    //   276: putfield 39	rx/observers/SerializedObserver:emitting	Z
    //   279: aconst_null
    //   280: astore_3
    //   281: goto -55 -> 226
    //   284: astore 9
    //   286: aload_0
    //   287: monitorexit
    //   288: aload 9
    //   290: athrow
    //   291: aload_0
    //   292: iconst_0
    //   293: putfield 39	rx/observers/SerializedObserver:emitting	Z
    //   296: goto -27 -> 269
    //   299: astore 7
    //   301: aload_0
    //   302: monitorexit
    //   303: aload 7
    //   305: athrow
    //   306: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	307	0	this	SerializedObserver
    //   0	307	1	paramT	T
    //   60	4	2	localObject1	Object
    //   81	200	3	localFastList1	FastList
    //   90	156	4	i	int
    //   94	105	5	j	int
    //   243	29	6	localObject2	Object
    //   299	5	7	localObject3	Object
    //   284	5	9	localObject4	Object
    //   236	5	10	localObject5	Object
    //   181	5	11	localObject6	Object
    //   43	7	13	localFastList2	FastList
    // Exception table:
    //   from	to	target	type
    //   2	63	60	finally
    //   65	89	60	finally
    //   159	185	181	finally
    //   188	193	181	finally
    //   128	152	236	finally
    //   196	198	236	finally
    //   238	240	236	finally
    //   96	128	243	finally
    //   240	243	243	finally
    //   209	228	284	finally
    //   274	288	284	finally
    //   252	271	299	finally
    //   291	303	299	finally
  }
  
  private static final class ErrorSentinel
  {
    final Throwable e;
    
    ErrorSentinel(Throwable paramThrowable)
    {
      this.e = paramThrowable;
    }
  }
  
  static final class FastList
  {
    Object[] array;
    int size;
    
    public void add(Object paramObject)
    {
      int i = this.size;
      Object localObject = this.array;
      if (localObject == null)
      {
        localObject = new Object[16];
        this.array = ((Object[])localObject);
      }
      for (;;)
      {
        localObject[i] = paramObject;
        this.size = (i + 1);
        return;
        if (i == localObject.length)
        {
          Object[] arrayOfObject = new Object[i + (i >> 2)];
          System.arraycopy(localObject, 0, arrayOfObject, 0, i);
          localObject = arrayOfObject;
          this.array = ((Object[])localObject);
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/observers/SerializedObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */