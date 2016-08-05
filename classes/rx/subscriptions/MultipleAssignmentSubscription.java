package rx.subscriptions;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import rx.Subscription;

public final class MultipleAssignmentSubscription
  implements Subscription
{
  static final AtomicReferenceFieldUpdater<MultipleAssignmentSubscription, State> STATE_UPDATER = AtomicReferenceFieldUpdater.newUpdater(MultipleAssignmentSubscription.class, State.class, "state");
  volatile State state = new State(false, Subscriptions.empty());
  
  public Subscription get()
  {
    return this.state.subscription;
  }
  
  public boolean isUnsubscribed()
  {
    return this.state.isUnsubscribed;
  }
  
  public void set(Subscription paramSubscription)
  {
    if (paramSubscription == null) {
      throw new IllegalArgumentException("Subscription can not be null");
    }
    State localState1 = this.state;
    if (localState1.isUnsubscribed) {
      paramSubscription.unsubscribe();
    }
    for (;;)
    {
      return;
      State localState2 = localState1.set(paramSubscription);
      if (!STATE_UPDATER.compareAndSet(this, localState1, localState2)) {
        break;
      }
    }
  }
  
  public void unsubscribe()
  {
    State localState1 = this.state;
    if (localState1.isUnsubscribed) {}
    for (;;)
    {
      return;
      State localState2 = localState1.unsubscribe();
      if (!STATE_UPDATER.compareAndSet(this, localState1, localState2)) {
        break;
      }
      localState1.subscription.unsubscribe();
    }
  }
  
  private static final class State
  {
    final boolean isUnsubscribed;
    final Subscription subscription;
    
    State(boolean paramBoolean, Subscription paramSubscription)
    {
      this.isUnsubscribed = paramBoolean;
      this.subscription = paramSubscription;
    }
    
    State set(Subscription paramSubscription)
    {
      return new State(this.isUnsubscribed, paramSubscription);
    }
    
    State unsubscribe()
    {
      return new State(true, this.subscription);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/subscriptions/MultipleAssignmentSubscription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */