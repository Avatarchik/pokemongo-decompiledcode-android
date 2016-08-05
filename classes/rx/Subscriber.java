package rx;

import rx.internal.util.SubscriptionList;

public abstract class Subscriber<T>
  implements Observer<T>, Subscription
{
  private static final Long NOT_SET = Long.valueOf(Long.MIN_VALUE);
  private Producer producer;
  private long requested = NOT_SET.longValue();
  private final Subscriber<?> subscriber;
  private final SubscriptionList subscriptions;
  
  protected Subscriber()
  {
    this(null, false);
  }
  
  protected Subscriber(Subscriber<?> paramSubscriber)
  {
    this(paramSubscriber, true);
  }
  
  protected Subscriber(Subscriber<?> paramSubscriber, boolean paramBoolean)
  {
    this.subscriber = paramSubscriber;
    if ((paramBoolean) && (paramSubscriber != null)) {}
    for (SubscriptionList localSubscriptionList = paramSubscriber.subscriptions;; localSubscriptionList = new SubscriptionList())
    {
      this.subscriptions = localSubscriptionList;
      return;
    }
  }
  
  private void addToRequested(long paramLong)
  {
    if (this.requested == NOT_SET.longValue()) {
      this.requested = paramLong;
    }
    for (;;)
    {
      return;
      long l = paramLong + this.requested;
      if (l < 0L) {
        this.requested = Long.MAX_VALUE;
      } else {
        this.requested = l;
      }
    }
  }
  
  public final void add(Subscription paramSubscription)
  {
    this.subscriptions.add(paramSubscription);
  }
  
  public final boolean isUnsubscribed()
  {
    return this.subscriptions.isUnsubscribed();
  }
  
  public void onStart() {}
  
  /* Error */
  protected final void request(long paramLong)
  {
    // Byte code:
    //   0: lload_1
    //   1: lconst_0
    //   2: lcmp
    //   3: ifge +30 -> 33
    //   6: new 68	java/lang/IllegalArgumentException
    //   9: dup
    //   10: new 70	java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial 71	java/lang/StringBuilder:<init>	()V
    //   17: ldc 73
    //   19: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   22: lload_1
    //   23: invokevirtual 80	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   26: invokevirtual 84	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: invokespecial 87	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   32: athrow
    //   33: aload_0
    //   34: monitorenter
    //   35: aload_0
    //   36: getfield 89	rx/Subscriber:producer	Lrx/Producer;
    //   39: ifnull +20 -> 59
    //   42: aload_0
    //   43: getfield 89	rx/Subscriber:producer	Lrx/Producer;
    //   46: astore 4
    //   48: aload_0
    //   49: monitorexit
    //   50: aload 4
    //   52: lload_1
    //   53: invokeinterface 93 3 0
    //   58: return
    //   59: aload_0
    //   60: lload_1
    //   61: invokespecial 95	rx/Subscriber:addToRequested	(J)V
    //   64: aload_0
    //   65: monitorexit
    //   66: goto -8 -> 58
    //   69: astore_3
    //   70: aload_0
    //   71: monitorexit
    //   72: aload_3
    //   73: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	74	0	this	Subscriber
    //   0	74	1	paramLong	long
    //   69	4	3	localObject	Object
    //   46	5	4	localProducer	Producer
    // Exception table:
    //   from	to	target	type
    //   35	50	69	finally
    //   59	72	69	finally
  }
  
  public void setProducer(Producer paramProducer)
  {
    int i = 0;
    for (;;)
    {
      long l;
      try
      {
        l = this.requested;
        this.producer = paramProducer;
        if ((this.subscriber != null) && (l == NOT_SET.longValue())) {
          i = 1;
        }
        if (i != 0)
        {
          this.subscriber.setProducer(this.producer);
          return;
        }
      }
      finally {}
      if (l == NOT_SET.longValue()) {
        this.producer.request(Long.MAX_VALUE);
      } else {
        this.producer.request(l);
      }
    }
  }
  
  public final void unsubscribe()
  {
    this.subscriptions.unsubscribe();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/Subscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */