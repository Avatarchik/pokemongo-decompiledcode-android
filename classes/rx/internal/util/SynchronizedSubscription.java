package rx.internal.util;

import rx.Subscription;

public class SynchronizedSubscription
  implements Subscription
{
  private final Subscription s;
  
  public SynchronizedSubscription(Subscription paramSubscription)
  {
    this.s = paramSubscription;
  }
  
  /**
   * @deprecated
   */
  public boolean isUnsubscribed()
  {
    try
    {
      boolean bool = this.s.isUnsubscribed();
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void unsubscribe()
  {
    try
    {
      this.s.unsubscribe();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/SynchronizedSubscription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */