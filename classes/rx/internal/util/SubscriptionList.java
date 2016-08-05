package rx.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Subscription;
import rx.exceptions.Exceptions;

public final class SubscriptionList
  implements Subscription
{
  private LinkedList<Subscription> subscriptions;
  private volatile boolean unsubscribed;
  
  public SubscriptionList() {}
  
  public SubscriptionList(Subscription paramSubscription)
  {
    this.subscriptions = new LinkedList();
    this.subscriptions.add(paramSubscription);
  }
  
  public SubscriptionList(Subscription... paramVarArgs)
  {
    this.subscriptions = new LinkedList(Arrays.asList(paramVarArgs));
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
          LinkedList localLinkedList = this.subscriptions;
          if (localLinkedList == null)
          {
            localLinkedList = new LinkedList();
            this.subscriptions = localLinkedList;
          }
          localLinkedList.add(paramSubscription);
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
    if (!this.unsubscribed) {}
    try
    {
      LinkedList localLinkedList = this.subscriptions;
      this.subscriptions = null;
      unsubscribeFromAll(localLinkedList);
      return;
    }
    finally {}
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
        LinkedList localLinkedList = this.subscriptions;
        if ((this.unsubscribed) || (localLinkedList == null)) {
          return;
        }
        boolean bool = localLinkedList.remove(paramSubscription);
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
        LinkedList localLinkedList = this.subscriptions;
        this.subscriptions = null;
        unsubscribeFromAll(localLinkedList);
      }
      finally {}
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/util/SubscriptionList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */