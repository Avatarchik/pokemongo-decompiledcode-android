package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.SynchronizedQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.observables.ConnectableObservable;
import rx.subscriptions.Subscriptions;

public final class OperatorPublish<T>
  extends ConnectableObservable<T>
{
  final AtomicReference<PublishSubscriber<T>> current;
  final Observable<? extends T> source;
  
  private OperatorPublish(Observable.OnSubscribe<T> paramOnSubscribe, Observable<? extends T> paramObservable, AtomicReference<PublishSubscriber<T>> paramAtomicReference)
  {
    super(paramOnSubscribe);
    this.source = paramObservable;
    this.current = paramAtomicReference;
  }
  
  public static <T, R> Observable<R> create(Observable<? extends T> paramObservable, final Func1<? super Observable<T>, ? extends Observable<R>> paramFunc1)
  {
    create(new Observable.OnSubscribe()
    {
      public void call(final Subscriber<? super R> paramAnonymousSubscriber)
      {
        ConnectableObservable localConnectableObservable = OperatorPublish.create(OperatorPublish.this);
        ((Observable)paramFunc1.call(localConnectableObservable)).unsafeSubscribe(paramAnonymousSubscriber);
        localConnectableObservable.connect(new Action1()
        {
          public void call(Subscription paramAnonymous2Subscription)
          {
            paramAnonymousSubscriber.add(paramAnonymous2Subscription);
          }
        });
      }
    });
  }
  
  public static <T> ConnectableObservable<T> create(Observable<? extends T> paramObservable)
  {
    AtomicReference localAtomicReference = new AtomicReference();
    new OperatorPublish(new Observable.OnSubscribe()
    {
      public void call(Subscriber<? super T> paramAnonymousSubscriber)
      {
        Object localObject;
        OperatorPublish.InnerProducer localInnerProducer;
        do
        {
          OperatorPublish.PublishSubscriber localPublishSubscriber;
          do
          {
            localObject = (OperatorPublish.PublishSubscriber)OperatorPublish.this.get();
            if ((localObject != null) && (!((OperatorPublish.PublishSubscriber)localObject).isUnsubscribed())) {
              break;
            }
            localPublishSubscriber = new OperatorPublish.PublishSubscriber(OperatorPublish.this);
            localPublishSubscriber.init();
          } while (!OperatorPublish.this.compareAndSet(localObject, localPublishSubscriber));
          localObject = localPublishSubscriber;
          localInnerProducer = new OperatorPublish.InnerProducer((OperatorPublish.PublishSubscriber)localObject, paramAnonymousSubscriber);
        } while (!((OperatorPublish.PublishSubscriber)localObject).add(localInnerProducer));
        paramAnonymousSubscriber.add(localInnerProducer);
        paramAnonymousSubscriber.setProducer(localInnerProducer);
      }
    }, paramObservable, localAtomicReference);
  }
  
  public void connect(Action1<? super Subscription> paramAction1)
  {
    PublishSubscriber localPublishSubscriber;
    do
    {
      localObject = (PublishSubscriber)this.current.get();
      if ((localObject != null) && (!((PublishSubscriber)localObject).isUnsubscribed())) {
        break;
      }
      localPublishSubscriber = new PublishSubscriber(this.current);
      localPublishSubscriber.init();
    } while (!this.current.compareAndSet(localObject, localPublishSubscriber));
    Object localObject = localPublishSubscriber;
    if ((!((PublishSubscriber)localObject).shouldConnect.get()) && (((PublishSubscriber)localObject).shouldConnect.compareAndSet(false, true))) {}
    for (int i = 1;; i = 0)
    {
      paramAction1.call(localObject);
      if (i != 0) {
        this.source.unsafeSubscribe((Subscriber)localObject);
      }
      return;
    }
  }
  
  static final class InnerProducer<T>
    extends AtomicLong
    implements Producer, Subscription
  {
    static final long NOT_REQUESTED = -4611686018427387904L;
    static final long UNSUBSCRIBED = Long.MIN_VALUE;
    private static final long serialVersionUID = -4453897557930727610L;
    final Subscriber<? super T> child;
    final OperatorPublish.PublishSubscriber<T> parent;
    
    public InnerProducer(OperatorPublish.PublishSubscriber<T> paramPublishSubscriber, Subscriber<? super T> paramSubscriber)
    {
      this.parent = paramPublishSubscriber;
      this.child = paramSubscriber;
      lazySet(-4611686018427387904L);
    }
    
    public boolean isUnsubscribed()
    {
      if (get() == Long.MIN_VALUE) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public long produced(long paramLong)
    {
      if (paramLong <= 0L) {
        throw new IllegalArgumentException("Cant produce zero or less");
      }
      long l1 = get();
      if (l1 == -4611686018427387904L) {
        throw new IllegalStateException("Produced without request");
      }
      long l2;
      if (l1 == Long.MIN_VALUE) {
        l2 = Long.MIN_VALUE;
      }
      for (;;)
      {
        return l2;
        l2 = l1 - paramLong;
        if (l2 < 0L) {
          throw new IllegalStateException("More produced (" + paramLong + ") than requested (" + l1 + ")");
        }
        if (!compareAndSet(l1, l2)) {
          break;
        }
      }
    }
    
    public void request(long paramLong)
    {
      if (paramLong < 0L) {
        return;
      }
      label81:
      for (;;)
      {
        long l1 = get();
        if ((l1 == Long.MIN_VALUE) || ((l1 >= 0L) && (paramLong == 0L))) {
          break;
        }
        long l2;
        if (l1 == -4611686018427387904L) {
          l2 = paramLong;
        }
        for (;;)
        {
          if (!compareAndSet(l1, l2)) {
            break label81;
          }
          this.parent.dispatch();
          break;
          l2 = l1 + paramLong;
          if (l2 < 0L) {
            l2 = Long.MAX_VALUE;
          }
        }
      }
    }
    
    public void unsubscribe()
    {
      if ((get() != Long.MIN_VALUE) && (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE))
      {
        this.parent.remove(this);
        this.parent.dispatch();
      }
    }
  }
  
  static final class PublishSubscriber<T>
    extends Subscriber<T>
    implements Subscription
  {
    static final OperatorPublish.InnerProducer[] EMPTY = new OperatorPublish.InnerProducer[0];
    static final OperatorPublish.InnerProducer[] TERMINATED = new OperatorPublish.InnerProducer[0];
    final AtomicReference<PublishSubscriber<T>> current;
    boolean emitting;
    boolean missed;
    final NotificationLite<T> nl;
    final AtomicReference<OperatorPublish.InnerProducer[]> producers;
    final Queue<Object> queue;
    final AtomicBoolean shouldConnect;
    volatile Object terminalEvent;
    
    public PublishSubscriber(AtomicReference<PublishSubscriber<T>> paramAtomicReference)
    {
      if (UnsafeAccess.isUnsafeAvailable()) {}
      for (Object localObject = new SpscArrayQueue(RxRingBuffer.SIZE);; localObject = new SynchronizedQueue(RxRingBuffer.SIZE))
      {
        this.queue = ((Queue)localObject);
        this.nl = NotificationLite.instance();
        this.producers = new AtomicReference(EMPTY);
        this.current = paramAtomicReference;
        this.shouldConnect = new AtomicBoolean();
        return;
      }
    }
    
    boolean add(OperatorPublish.InnerProducer<T> paramInnerProducer)
    {
      boolean bool = false;
      if (paramInnerProducer == null) {
        throw new NullPointerException();
      }
      OperatorPublish.InnerProducer[] arrayOfInnerProducer1 = (OperatorPublish.InnerProducer[])this.producers.get();
      if (arrayOfInnerProducer1 == TERMINATED) {}
      for (;;)
      {
        return bool;
        int i = arrayOfInnerProducer1.length;
        OperatorPublish.InnerProducer[] arrayOfInnerProducer2 = new OperatorPublish.InnerProducer[i + 1];
        System.arraycopy(arrayOfInnerProducer1, 0, arrayOfInnerProducer2, 0, i);
        arrayOfInnerProducer2[i] = paramInnerProducer;
        if (!this.producers.compareAndSet(arrayOfInnerProducer1, arrayOfInnerProducer2)) {
          break;
        }
        bool = true;
      }
    }
    
    /* Error */
    boolean checkTerminated(Object paramObject, boolean paramBoolean)
    {
      // Byte code:
      //   0: iconst_1
      //   1: istore_3
      //   2: aload_1
      //   3: ifnull +175 -> 178
      //   6: aload_0
      //   7: getfield 72	rx/internal/operators/OperatorPublish$PublishSubscriber:nl	Lrx/internal/operators/NotificationLite;
      //   10: aload_1
      //   11: invokevirtual 115	rx/internal/operators/NotificationLite:isCompleted	(Ljava/lang/Object;)Z
      //   14: ifeq +79 -> 93
      //   17: iload_2
      //   18: ifeq +160 -> 178
      //   21: aload_0
      //   22: getfield 81	rx/internal/operators/OperatorPublish$PublishSubscriber:current	Ljava/util/concurrent/atomic/AtomicReference;
      //   25: aload_0
      //   26: aconst_null
      //   27: invokevirtual 109	java/util/concurrent/atomic/AtomicReference:compareAndSet	(Ljava/lang/Object;Ljava/lang/Object;)Z
      //   30: pop
      //   31: aload_0
      //   32: getfield 79	rx/internal/operators/OperatorPublish$PublishSubscriber:producers	Ljava/util/concurrent/atomic/AtomicReference;
      //   35: getstatic 41	rx/internal/operators/OperatorPublish$PublishSubscriber:TERMINATED	[Lrx/internal/operators/OperatorPublish$InnerProducer;
      //   38: invokevirtual 119	java/util/concurrent/atomic/AtomicReference:getAndSet	(Ljava/lang/Object;)Ljava/lang/Object;
      //   41: checkcast 99	[Lrx/internal/operators/OperatorPublish$InnerProducer;
      //   44: astore 12
      //   46: aload 12
      //   48: arraylength
      //   49: istore 13
      //   51: iconst_0
      //   52: istore 14
      //   54: iload 14
      //   56: iload 13
      //   58: if_icmpge +20 -> 78
      //   61: aload 12
      //   63: iload 14
      //   65: aaload
      //   66: getfield 123	rx/internal/operators/OperatorPublish$InnerProducer:child	Lrx/Subscriber;
      //   69: invokevirtual 126	rx/Subscriber:onCompleted	()V
      //   72: iinc 14 1
      //   75: goto -21 -> 54
      //   78: aload_0
      //   79: invokevirtual 129	rx/internal/operators/OperatorPublish$PublishSubscriber:unsubscribe	()V
      //   82: iload_3
      //   83: ireturn
      //   84: astore 11
      //   86: aload_0
      //   87: invokevirtual 129	rx/internal/operators/OperatorPublish$PublishSubscriber:unsubscribe	()V
      //   90: aload 11
      //   92: athrow
      //   93: aload_0
      //   94: getfield 72	rx/internal/operators/OperatorPublish$PublishSubscriber:nl	Lrx/internal/operators/NotificationLite;
      //   97: aload_1
      //   98: invokevirtual 133	rx/internal/operators/NotificationLite:getError	(Ljava/lang/Object;)Ljava/lang/Throwable;
      //   101: astore 4
      //   103: aload_0
      //   104: getfield 81	rx/internal/operators/OperatorPublish$PublishSubscriber:current	Ljava/util/concurrent/atomic/AtomicReference;
      //   107: aload_0
      //   108: aconst_null
      //   109: invokevirtual 109	java/util/concurrent/atomic/AtomicReference:compareAndSet	(Ljava/lang/Object;Ljava/lang/Object;)Z
      //   112: pop
      //   113: aload_0
      //   114: getfield 79	rx/internal/operators/OperatorPublish$PublishSubscriber:producers	Ljava/util/concurrent/atomic/AtomicReference;
      //   117: getstatic 41	rx/internal/operators/OperatorPublish$PublishSubscriber:TERMINATED	[Lrx/internal/operators/OperatorPublish$InnerProducer;
      //   120: invokevirtual 119	java/util/concurrent/atomic/AtomicReference:getAndSet	(Ljava/lang/Object;)Ljava/lang/Object;
      //   123: checkcast 99	[Lrx/internal/operators/OperatorPublish$InnerProducer;
      //   126: astore 7
      //   128: aload 7
      //   130: arraylength
      //   131: istore 8
      //   133: iconst_0
      //   134: istore 9
      //   136: iload 9
      //   138: iload 8
      //   140: if_icmpge +22 -> 162
      //   143: aload 7
      //   145: iload 9
      //   147: aaload
      //   148: getfield 123	rx/internal/operators/OperatorPublish$InnerProducer:child	Lrx/Subscriber;
      //   151: aload 4
      //   153: invokevirtual 137	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   156: iinc 9 1
      //   159: goto -23 -> 136
      //   162: aload_0
      //   163: invokevirtual 129	rx/internal/operators/OperatorPublish$PublishSubscriber:unsubscribe	()V
      //   166: goto -84 -> 82
      //   169: astore 6
      //   171: aload_0
      //   172: invokevirtual 129	rx/internal/operators/OperatorPublish$PublishSubscriber:unsubscribe	()V
      //   175: aload 6
      //   177: athrow
      //   178: iconst_0
      //   179: istore_3
      //   180: goto -98 -> 82
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	183	0	this	PublishSubscriber
      //   0	183	1	paramObject	Object
      //   0	183	2	paramBoolean	boolean
      //   1	179	3	bool	boolean
      //   101	51	4	localThrowable	Throwable
      //   169	7	6	localObject1	Object
      //   126	18	7	arrayOfInnerProducer1	OperatorPublish.InnerProducer[]
      //   131	10	8	i	int
      //   134	23	9	j	int
      //   84	7	11	localObject2	Object
      //   44	18	12	arrayOfInnerProducer2	OperatorPublish.InnerProducer[]
      //   49	10	13	k	int
      //   52	21	14	m	int
      // Exception table:
      //   from	to	target	type
      //   31	72	84	finally
      //   113	156	169	finally
    }
    
    /* Error */
    void dispatch()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   6: ifeq +13 -> 19
      //   9: aload_0
      //   10: iconst_1
      //   11: putfield 144	rx/internal/operators/OperatorPublish$PublishSubscriber:missed	Z
      //   14: aload_0
      //   15: monitorexit
      //   16: goto +510 -> 526
      //   19: aload_0
      //   20: iconst_1
      //   21: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   24: aload_0
      //   25: iconst_0
      //   26: putfield 144	rx/internal/operators/OperatorPublish$PublishSubscriber:missed	Z
      //   29: aload_0
      //   30: monitorexit
      //   31: iconst_0
      //   32: istore_2
      //   33: aload_0
      //   34: getfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:terminalEvent	Ljava/lang/Object;
      //   37: astore 5
      //   39: aload_0
      //   40: getfield 64	rx/internal/operators/OperatorPublish$PublishSubscriber:queue	Ljava/util/Queue;
      //   43: invokeinterface 151 1 0
      //   48: istore 6
      //   50: aload_0
      //   51: aload 5
      //   53: iload 6
      //   55: invokevirtual 153	rx/internal/operators/OperatorPublish$PublishSubscriber:checkTerminated	(Ljava/lang/Object;Z)Z
      //   58: istore 7
      //   60: iload 7
      //   62: ifeq +31 -> 93
      //   65: iconst_1
      //   66: ifne +460 -> 526
      //   69: aload_0
      //   70: monitorenter
      //   71: aload_0
      //   72: iconst_0
      //   73: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   76: aload_0
      //   77: monitorexit
      //   78: goto +448 -> 526
      //   81: astore 39
      //   83: aload_0
      //   84: monitorexit
      //   85: aload 39
      //   87: athrow
      //   88: astore_1
      //   89: aload_0
      //   90: monitorexit
      //   91: aload_1
      //   92: athrow
      //   93: iload 6
      //   95: ifne +273 -> 368
      //   98: aload_0
      //   99: getfield 79	rx/internal/operators/OperatorPublish$PublishSubscriber:producers	Ljava/util/concurrent/atomic/AtomicReference;
      //   102: invokevirtual 98	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
      //   105: checkcast 99	[Lrx/internal/operators/OperatorPublish$InnerProducer;
      //   108: astore 10
      //   110: aload 10
      //   112: arraylength
      //   113: istore 11
      //   115: ldc2_w 154
      //   118: lstore 12
      //   120: iconst_0
      //   121: istore 14
      //   123: aload 10
      //   125: arraylength
      //   126: istore 15
      //   128: iconst_0
      //   129: istore 16
      //   131: iload 16
      //   133: iload 15
      //   135: if_icmpge +32 -> 167
      //   138: aload 10
      //   140: iload 16
      //   142: aaload
      //   143: invokevirtual 158	rx/internal/operators/OperatorPublish$InnerProducer:get	()J
      //   146: lstore 37
      //   148: lload 37
      //   150: lconst_0
      //   151: lcmp
      //   152: iflt +381 -> 533
      //   155: lload 12
      //   157: lload 37
      //   159: invokestatic 164	java/lang/Math:min	(JJ)J
      //   162: lstore 12
      //   164: goto +363 -> 527
      //   167: iload 11
      //   169: iload 14
      //   171: if_icmpne +92 -> 263
      //   174: aload_0
      //   175: getfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:terminalEvent	Ljava/lang/Object;
      //   178: astore 33
      //   180: aload_0
      //   181: getfield 64	rx/internal/operators/OperatorPublish$PublishSubscriber:queue	Ljava/util/Queue;
      //   184: invokeinterface 167 1 0
      //   189: ifnonnull +44 -> 233
      //   192: iconst_1
      //   193: istore 34
      //   195: aload_0
      //   196: aload 33
      //   198: iload 34
      //   200: invokevirtual 153	rx/internal/operators/OperatorPublish$PublishSubscriber:checkTerminated	(Ljava/lang/Object;Z)Z
      //   203: istore 35
      //   205: iload 35
      //   207: ifeq +32 -> 239
      //   210: iconst_1
      //   211: ifne +315 -> 526
      //   214: aload_0
      //   215: monitorenter
      //   216: aload_0
      //   217: iconst_0
      //   218: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   221: aload_0
      //   222: monitorexit
      //   223: goto +303 -> 526
      //   226: astore 36
      //   228: aload_0
      //   229: monitorexit
      //   230: aload 36
      //   232: athrow
      //   233: iconst_0
      //   234: istore 34
      //   236: goto -41 -> 195
      //   239: aload_0
      //   240: lconst_1
      //   241: invokevirtual 171	rx/internal/operators/OperatorPublish$PublishSubscriber:request	(J)V
      //   244: goto -211 -> 33
      //   247: astore_3
      //   248: iload_2
      //   249: ifne +12 -> 261
      //   252: aload_0
      //   253: monitorenter
      //   254: aload_0
      //   255: iconst_0
      //   256: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   259: aload_0
      //   260: monitorexit
      //   261: aload_3
      //   262: athrow
      //   263: iconst_0
      //   264: istore 17
      //   266: iload 17
      //   268: i2l
      //   269: lload 12
      //   271: lcmp
      //   272: ifge +77 -> 349
      //   275: aload_0
      //   276: getfield 146	rx/internal/operators/OperatorPublish$PublishSubscriber:terminalEvent	Ljava/lang/Object;
      //   279: astore 20
      //   281: aload_0
      //   282: getfield 64	rx/internal/operators/OperatorPublish$PublishSubscriber:queue	Ljava/util/Queue;
      //   285: invokeinterface 167 1 0
      //   290: astore 21
      //   292: aload 21
      //   294: ifnonnull +44 -> 338
      //   297: iconst_1
      //   298: istore 6
      //   300: aload_0
      //   301: aload 20
      //   303: iload 6
      //   305: invokevirtual 153	rx/internal/operators/OperatorPublish$PublishSubscriber:checkTerminated	(Ljava/lang/Object;Z)Z
      //   308: istore 22
      //   310: iload 22
      //   312: ifeq +32 -> 344
      //   315: iconst_1
      //   316: ifne +210 -> 526
      //   319: aload_0
      //   320: monitorenter
      //   321: aload_0
      //   322: iconst_0
      //   323: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   326: aload_0
      //   327: monitorexit
      //   328: goto +198 -> 526
      //   331: astore 32
      //   333: aload_0
      //   334: monitorexit
      //   335: aload 32
      //   337: athrow
      //   338: iconst_0
      //   339: istore 6
      //   341: goto -41 -> 300
      //   344: iload 6
      //   346: ifeq +63 -> 409
      //   349: iload 17
      //   351: ifle +197 -> 548
      //   354: iload 17
      //   356: i2l
      //   357: lstore 18
      //   359: aload_0
      //   360: lload 18
      //   362: invokevirtual 171	rx/internal/operators/OperatorPublish$PublishSubscriber:request	(J)V
      //   365: goto +183 -> 548
      //   368: aload_0
      //   369: monitorenter
      //   370: aload_0
      //   371: getfield 144	rx/internal/operators/OperatorPublish$PublishSubscriber:missed	Z
      //   374: ifne +128 -> 502
      //   377: aload_0
      //   378: iconst_0
      //   379: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   382: iconst_1
      //   383: istore_2
      //   384: aload_0
      //   385: monitorexit
      //   386: iload_2
      //   387: ifne +139 -> 526
      //   390: aload_0
      //   391: monitorenter
      //   392: aload_0
      //   393: iconst_0
      //   394: putfield 142	rx/internal/operators/OperatorPublish$PublishSubscriber:emitting	Z
      //   397: aload_0
      //   398: monitorexit
      //   399: goto +127 -> 526
      //   402: astore 9
      //   404: aload_0
      //   405: monitorexit
      //   406: aload 9
      //   408: athrow
      //   409: aload_0
      //   410: getfield 72	rx/internal/operators/OperatorPublish$PublishSubscriber:nl	Lrx/internal/operators/NotificationLite;
      //   413: aload 21
      //   415: invokevirtual 174	rx/internal/operators/NotificationLite:getValue	(Ljava/lang/Object;)Ljava/lang/Object;
      //   418: astore 23
      //   420: aload 10
      //   422: arraylength
      //   423: istore 24
      //   425: iconst_0
      //   426: istore 25
      //   428: iload 25
      //   430: iload 24
      //   432: if_icmpge +64 -> 496
      //   435: aload 10
      //   437: iload 25
      //   439: aaload
      //   440: astore 26
      //   442: aload 26
      //   444: invokevirtual 158	rx/internal/operators/OperatorPublish$InnerProducer:get	()J
      //   447: lstore 27
      //   449: lload 27
      //   451: lconst_0
      //   452: lcmp
      //   453: ifle +110 -> 563
      //   456: aload 26
      //   458: getfield 123	rx/internal/operators/OperatorPublish$InnerProducer:child	Lrx/Subscriber;
      //   461: aload 23
      //   463: invokevirtual 177	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   466: aload 26
      //   468: lconst_1
      //   469: invokevirtual 181	rx/internal/operators/OperatorPublish$InnerProducer:produced	(J)J
      //   472: pop2
      //   473: goto +90 -> 563
      //   476: astore 29
      //   478: aload 26
      //   480: invokevirtual 182	rx/internal/operators/OperatorPublish$InnerProducer:unsubscribe	()V
      //   483: aload 26
      //   485: getfield 123	rx/internal/operators/OperatorPublish$InnerProducer:child	Lrx/Subscriber;
      //   488: aload 29
      //   490: invokevirtual 137	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   493: goto +70 -> 563
      //   496: iinc 17 1
      //   499: goto -233 -> 266
      //   502: aload_0
      //   503: iconst_0
      //   504: putfield 144	rx/internal/operators/OperatorPublish$PublishSubscriber:missed	Z
      //   507: aload_0
      //   508: monitorexit
      //   509: goto -476 -> 33
      //   512: astore 8
      //   514: aload_0
      //   515: monitorexit
      //   516: aload 8
      //   518: athrow
      //   519: astore 4
      //   521: aload_0
      //   522: monitorexit
      //   523: aload 4
      //   525: athrow
      //   526: return
      //   527: iinc 16 1
      //   530: goto -399 -> 131
      //   533: lload 37
      //   535: ldc2_w 183
      //   538: lcmp
      //   539: ifne -12 -> 527
      //   542: iinc 14 1
      //   545: goto -18 -> 527
      //   548: lload 12
      //   550: lconst_0
      //   551: lcmp
      //   552: ifeq -184 -> 368
      //   555: iload 6
      //   557: ifeq -524 -> 33
      //   560: goto -192 -> 368
      //   563: iinc 25 1
      //   566: goto -138 -> 428
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	569	0	this	PublishSubscriber
      //   88	4	1	localObject1	Object
      //   32	355	2	i	int
      //   247	15	3	localObject2	Object
      //   519	5	4	localObject3	Object
      //   37	15	5	localObject4	Object
      //   48	508	6	bool1	boolean
      //   58	3	7	bool2	boolean
      //   512	5	8	localObject5	Object
      //   402	5	9	localObject6	Object
      //   108	328	10	arrayOfInnerProducer	OperatorPublish.InnerProducer[]
      //   113	59	11	j	int
      //   118	431	12	l1	long
      //   121	422	14	k	int
      //   126	10	15	m	int
      //   129	399	16	n	int
      //   264	233	17	i1	int
      //   357	4	18	l2	long
      //   279	23	20	localObject7	Object
      //   290	124	21	localObject8	Object
      //   308	3	22	bool3	boolean
      //   418	44	23	localObject9	Object
      //   423	10	24	i2	int
      //   426	138	25	i3	int
      //   440	44	26	localInnerProducer	OperatorPublish.InnerProducer
      //   447	3	27	l3	long
      //   476	13	29	localThrowable	Throwable
      //   331	5	32	localObject10	Object
      //   178	19	33	localObject11	Object
      //   193	42	34	bool4	boolean
      //   203	3	35	bool5	boolean
      //   226	5	36	localObject12	Object
      //   146	388	37	l4	long
      //   81	5	39	localObject13	Object
      // Exception table:
      //   from	to	target	type
      //   71	85	81	finally
      //   2	31	88	finally
      //   89	91	88	finally
      //   216	230	226	finally
      //   33	60	247	finally
      //   98	205	247	finally
      //   239	244	247	finally
      //   275	310	247	finally
      //   359	370	247	finally
      //   409	449	247	finally
      //   456	466	247	finally
      //   466	493	247	finally
      //   516	519	247	finally
      //   321	335	331	finally
      //   392	406	402	finally
      //   456	466	476	java/lang/Throwable
      //   370	386	512	finally
      //   502	516	512	finally
      //   254	261	519	finally
      //   521	523	519	finally
    }
    
    void init()
    {
      add(Subscriptions.create(new Action0()
      {
        public void call()
        {
          OperatorPublish.PublishSubscriber.this.producers.getAndSet(OperatorPublish.PublishSubscriber.TERMINATED);
          OperatorPublish.PublishSubscriber.this.current.compareAndSet(OperatorPublish.PublishSubscriber.this, null);
        }
      }));
    }
    
    public void onCompleted()
    {
      if (this.terminalEvent == null)
      {
        this.terminalEvent = this.nl.completed();
        dispatch();
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      if (this.terminalEvent == null)
      {
        this.terminalEvent = this.nl.error(paramThrowable);
        dispatch();
      }
    }
    
    public void onNext(T paramT)
    {
      if (!this.queue.offer(this.nl.next(paramT))) {
        onError(new MissingBackpressureException());
      }
      for (;;)
      {
        return;
        dispatch();
      }
    }
    
    public void onStart()
    {
      request(RxRingBuffer.SIZE);
    }
    
    void remove(OperatorPublish.InnerProducer<T> paramInnerProducer)
    {
      OperatorPublish.InnerProducer[] arrayOfInnerProducer1 = (OperatorPublish.InnerProducer[])this.producers.get();
      if ((arrayOfInnerProducer1 == EMPTY) || (arrayOfInnerProducer1 == TERMINATED)) {}
      label25:
      int i;
      int j;
      int k;
      label36:
      do
      {
        return;
        i = -1;
        j = arrayOfInnerProducer1.length;
        k = 0;
        if (k < j)
        {
          if (!arrayOfInnerProducer1[k].equals(paramInnerProducer)) {
            break;
          }
          i = k;
        }
      } while (i < 0);
      OperatorPublish.InnerProducer[] arrayOfInnerProducer2;
      if (j == 1) {
        arrayOfInnerProducer2 = EMPTY;
      }
      while (this.producers.compareAndSet(arrayOfInnerProducer1, arrayOfInnerProducer2))
      {
        break label25;
        k++;
        break label36;
        arrayOfInnerProducer2 = new OperatorPublish.InnerProducer[j - 1];
        System.arraycopy(arrayOfInnerProducer1, 0, arrayOfInnerProducer2, 0, i);
        System.arraycopy(arrayOfInnerProducer1, i + 1, arrayOfInnerProducer2, i, -1 + (j - i));
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorPublish.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */