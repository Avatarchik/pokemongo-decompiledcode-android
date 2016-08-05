package rx.internal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action1;

public final class SubscriptionRandomList<T extends Subscription>
  implements Subscription
{
  private Set<T> subscriptions;
  private boolean unsubscribed = false;
  
  private static <T extends Subscription> void unsubscribeFromAll(Collection<T> paramCollection)
  {
    if (paramCollection == null) {}
    for (;;)
    {
      return;
      ArrayList localArrayList = null;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        Subscription localSubscription = (Subscription)localIterator.next();
        try
        {
          localSubscription.unsubscribe();
        }
        catch (Throwable localThrowable)
        {
          if (localArrayList == null) {
            localArrayList = new ArrayList();
          }
          localArrayList.add(localThrowable);
        }
      }
      Exceptions.throwIfAny(localArrayList);
    }
  }
  
  /* Error */
  public void add(T paramT)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: aload_0
    //   5: getfield 18	rx/internal/util/SubscriptionRandomList:unsubscribed	Z
    //   8: ifeq +18 -> 26
    //   11: aload_1
    //   12: astore_2
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_2
    //   16: ifnull +9 -> 25
    //   19: aload_2
    //   20: invokeinterface 41 1 0
    //   25: return
    //   26: aload_0
    //   27: getfield 59	rx/internal/util/SubscriptionRandomList:subscriptions	Ljava/util/Set;
    //   30: ifnonnull +15 -> 45
    //   33: aload_0
    //   34: new 61	java/util/HashSet
    //   37: dup
    //   38: iconst_4
    //   39: invokespecial 64	java/util/HashSet:<init>	(I)V
    //   42: putfield 59	rx/internal/util/SubscriptionRandomList:subscriptions	Ljava/util/Set;
    //   45: aload_0
    //   46: getfield 59	rx/internal/util/SubscriptionRandomList:subscriptions	Ljava/util/Set;
    //   49: aload_1
    //   50: invokeinterface 67 2 0
    //   55: pop
    //   56: goto -43 -> 13
    //   59: astore_3
    //   60: aload_0
    //   61: monitorexit
    //   62: aload_3
    //   63: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	64	0	this	SubscriptionRandomList
    //   0	64	1	paramT	T
    //   1	19	2	?	T
    //   59	4	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   4	15	59	finally
    //   26	62	59	finally
  }
  
  public void clear()
  {
    try
    {
      if ((this.unsubscribed) || (this.subscriptions == null)) {
        return;
      }
      Set localSet = this.subscriptions;
      this.subscriptions = null;
      unsubscribeFromAll(localSet);
    }
    finally {}
  }
  
  public void forEach(Action1<T> paramAction1)
  {
    try
    {
      if ((this.unsubscribed) || (this.subscriptions == null)) {
        return;
      }
      Subscription[] arrayOfSubscription = (Subscription[])this.subscriptions.toArray(null);
      int i = arrayOfSubscription.length;
      for (int j = 0; j < i; j++) {
        paramAction1.call(arrayOfSubscription[j]);
      }
      return;
    }
    finally {}
  }
  
  /**
   * @deprecated
   */
  public boolean isUnsubscribed()
  {
    try
    {
      boolean bool = this.unsubscribed;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void remove(Subscription paramSubscription)
  {
    try
    {
      if ((this.unsubscribed) || (this.subscriptions == null)) {
        return;
      }
      boolean bool = this.subscriptions.remove(paramSubscription);
      if (bool) {
        paramSubscription.unsubscribe();
      }
    }
    finally {}
  }
  
  public void unsubscribe()
  {
    try
    {
      if (this.unsubscribed) {
        return;
      }
      this.unsubscribed = true;
      Set localSet = this.subscriptions;
      this.subscriptions = null;
      unsubscribeFromAll(localSet);
    }
    finally {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/SubscriptionRandomList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */