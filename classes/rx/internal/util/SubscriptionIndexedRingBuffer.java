package rx.internal.util;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import rx.Subscription;
import rx.functions.Func1;

public final class SubscriptionIndexedRingBuffer<T extends Subscription>
  implements Subscription
{
  private static final Func1<Subscription, Boolean> UNSUBSCRIBE = new Func1()
  {
    public Boolean call(Subscription paramAnonymousSubscription)
    {
      paramAnonymousSubscription.unsubscribe();
      return Boolean.TRUE;
    }
  };
  private static final AtomicIntegerFieldUpdater<SubscriptionIndexedRingBuffer> UNSUBSCRIBED = AtomicIntegerFieldUpdater.newUpdater(SubscriptionIndexedRingBuffer.class, "unsubscribed");
  private volatile IndexedRingBuffer<T> subscriptions = IndexedRingBuffer.getInstance();
  private volatile int unsubscribed = 0;
  
  private static void unsubscribeFromAll(IndexedRingBuffer<? extends Subscription> paramIndexedRingBuffer)
  {
    if (paramIndexedRingBuffer == null) {}
    for (;;)
    {
      return;
      paramIndexedRingBuffer.forEach(UNSUBSCRIBE);
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public int add(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 47	rx/internal/util/SubscriptionIndexedRingBuffer:unsubscribed	I
    //   6: iconst_1
    //   7: if_icmpeq +10 -> 17
    //   10: aload_0
    //   11: getfield 45	rx/internal/util/SubscriptionIndexedRingBuffer:subscriptions	Lrx/internal/util/IndexedRingBuffer;
    //   14: ifnonnull +16 -> 30
    //   17: aload_1
    //   18: invokeinterface 58 1 0
    //   23: bipush -1
    //   25: istore_3
    //   26: aload_0
    //   27: monitorexit
    //   28: iload_3
    //   29: ireturn
    //   30: aload_0
    //   31: getfield 45	rx/internal/util/SubscriptionIndexedRingBuffer:subscriptions	Lrx/internal/util/IndexedRingBuffer;
    //   34: aload_1
    //   35: invokevirtual 61	rx/internal/util/IndexedRingBuffer:add	(Ljava/lang/Object;)I
    //   38: istore_3
    //   39: aload_0
    //   40: getfield 47	rx/internal/util/SubscriptionIndexedRingBuffer:unsubscribed	I
    //   43: iconst_1
    //   44: if_icmpne -18 -> 26
    //   47: aload_1
    //   48: invokeinterface 58 1 0
    //   53: goto -27 -> 26
    //   56: astore_2
    //   57: aload_0
    //   58: monitorexit
    //   59: aload_2
    //   60: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	this	SubscriptionIndexedRingBuffer
    //   0	61	1	paramT	T
    //   56	4	2	localObject	Object
    //   25	14	3	i	int
    // Exception table:
    //   from	to	target	type
    //   2	23	56	finally
    //   30	53	56	finally
  }
  
  public int forEach(Func1<T, Boolean> paramFunc1)
  {
    return forEach(paramFunc1, 0);
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public int forEach(Func1<T, Boolean> paramFunc1, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 47	rx/internal/util/SubscriptionIndexedRingBuffer:unsubscribed	I
    //   6: iconst_1
    //   7: if_icmpeq +14 -> 21
    //   10: aload_0
    //   11: getfield 45	rx/internal/util/SubscriptionIndexedRingBuffer:subscriptions	Lrx/internal/util/IndexedRingBuffer;
    //   14: astore 5
    //   16: aload 5
    //   18: ifnonnull +11 -> 29
    //   21: iconst_0
    //   22: istore 4
    //   24: aload_0
    //   25: monitorexit
    //   26: iload 4
    //   28: ireturn
    //   29: aload_0
    //   30: getfield 45	rx/internal/util/SubscriptionIndexedRingBuffer:subscriptions	Lrx/internal/util/IndexedRingBuffer;
    //   33: aload_1
    //   34: iload_2
    //   35: invokevirtual 65	rx/internal/util/IndexedRingBuffer:forEach	(Lrx/functions/Func1;I)I
    //   38: istore 6
    //   40: iload 6
    //   42: istore 4
    //   44: goto -20 -> 24
    //   47: astore_3
    //   48: aload_0
    //   49: monitorexit
    //   50: aload_3
    //   51: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	52	0	this	SubscriptionIndexedRingBuffer
    //   0	52	1	paramFunc1	Func1<T, Boolean>
    //   0	52	2	paramInt	int
    //   47	4	3	localObject	Object
    //   22	21	4	i	int
    //   14	3	5	localIndexedRingBuffer	IndexedRingBuffer
    //   38	3	6	j	int
    // Exception table:
    //   from	to	target	type
    //   2	16	47	finally
    //   29	40	47	finally
  }
  
  public boolean isUnsubscribed()
  {
    int i = 1;
    if (this.unsubscribed == i) {}
    for (;;)
    {
      return i;
      int j = 0;
    }
  }
  
  public void remove(int paramInt)
  {
    if ((this.unsubscribed == 1) || (this.subscriptions == null) || (paramInt < 0)) {}
    for (;;)
    {
      return;
      Subscription localSubscription = (Subscription)this.subscriptions.remove(paramInt);
      if ((localSubscription != null) && (localSubscription != null)) {
        localSubscription.unsubscribe();
      }
    }
  }
  
  public void removeSilently(int paramInt)
  {
    if ((this.unsubscribed == 1) || (this.subscriptions == null) || (paramInt < 0)) {}
    for (;;)
    {
      return;
      this.subscriptions.remove(paramInt);
    }
  }
  
  public void unsubscribe()
  {
    if ((UNSUBSCRIBED.compareAndSet(this, 0, 1)) && (this.subscriptions != null))
    {
      unsubscribeFromAll(this.subscriptions);
      IndexedRingBuffer localIndexedRingBuffer = this.subscriptions;
      this.subscriptions = null;
      localIndexedRingBuffer.unsubscribe();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/SubscriptionIndexedRingBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */