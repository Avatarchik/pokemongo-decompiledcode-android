package rx.subscriptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import rx.Subscription;
import rx.exceptions.Exceptions;

public final class CompositeSubscription
  implements Subscription
{
  private Set<Subscription> subscriptions;
  private volatile boolean unsubscribed;
  
  public CompositeSubscription() {}
  
  public CompositeSubscription(Subscription... paramVarArgs)
  {
    this.subscriptions = new HashSet(Arrays.asList(paramVarArgs));
  }
  
  private static void unsubscribeFromAll(Collection<Subscription> paramCollection)
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
  
  public void add(Subscription paramSubscription)
  {
    if (paramSubscription.isUnsubscribed()) {}
    for (;;)
    {
      return;
      if (!this.unsubscribed) {}
      try
      {
        if (!this.unsubscribed)
        {
          if (this.subscriptions == null) {
            this.subscriptions = new HashSet(4);
          }
          this.subscriptions.add(paramSubscription);
        }
      }
      finally
      {
        throw ((Throwable)localObject);
      }
    }
  }
  
  public void clear()
  {
    if (!this.unsubscribed) {
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
  }
  
  public boolean hasSubscriptions()
  {
    boolean bool = false;
    if (!this.unsubscribed) {
      try
      {
        if ((!this.unsubscribed) && (this.subscriptions != null) && (!this.subscriptions.isEmpty())) {
          bool = true;
        }
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    return bool;
  }
  
  public boolean isUnsubscribed()
  {
    return this.unsubscribed;
  }
  
  public void remove(Subscription paramSubscription)
  {
    if (!this.unsubscribed) {
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
  }
  
  public void unsubscribe()
  {
    if (!this.unsubscribed) {
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
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/subscriptions/CompositeSubscription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */