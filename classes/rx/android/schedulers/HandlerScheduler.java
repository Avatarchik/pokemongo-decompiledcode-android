package rx.android.schedulers;

import android.os.Handler;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.functions.Action0;
import rx.internal.schedulers.ScheduledAction;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public final class HandlerScheduler
  extends Scheduler
{
  private final Handler handler;
  
  HandlerScheduler(Handler paramHandler)
  {
    this.handler = paramHandler;
  }
  
  public static HandlerScheduler from(Handler paramHandler)
  {
    if (paramHandler == null) {
      throw new NullPointerException("handler == null");
    }
    return new HandlerScheduler(paramHandler);
  }
  
  public Scheduler.Worker createWorker()
  {
    return new HandlerWorker(this.handler);
  }
  
  static class HandlerWorker
    extends Scheduler.Worker
  {
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();
    private final Handler handler;
    
    HandlerWorker(Handler paramHandler)
    {
      this.handler = paramHandler;
    }
    
    public boolean isUnsubscribed()
    {
      return this.compositeSubscription.isUnsubscribed();
    }
    
    public Subscription schedule(Action0 paramAction0)
    {
      return schedule(paramAction0, 0L, TimeUnit.MILLISECONDS);
    }
    
    public Subscription schedule(Action0 paramAction0, long paramLong, TimeUnit paramTimeUnit)
    {
      final Object localObject;
      if (this.compositeSubscription.isUnsubscribed()) {
        localObject = Subscriptions.unsubscribed();
      }
      for (;;)
      {
        return (Subscription)localObject;
        localObject = new ScheduledAction(RxAndroidPlugins.getInstance().getSchedulersHook().onSchedule(paramAction0));
        ((ScheduledAction)localObject).addParent(this.compositeSubscription);
        this.compositeSubscription.add((Subscription)localObject);
        this.handler.postDelayed((Runnable)localObject, paramTimeUnit.toMillis(paramLong));
        ((ScheduledAction)localObject).add(Subscriptions.create(new Action0()
        {
          public void call()
          {
            HandlerScheduler.HandlerWorker.this.handler.removeCallbacks(localObject);
          }
        }));
      }
    }
    
    public void unsubscribe()
    {
      this.compositeSubscription.unsubscribe();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/android/schedulers/HandlerScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */