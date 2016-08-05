package rx.internal.operators;

import rx.Notification;
import rx.Observable.Operator;
import rx.Subscriber;

public final class OperatorDematerialize<T>
  implements Observable.Operator<T, Notification<T>>
{
  public static OperatorDematerialize instance()
  {
    return Holder.INSTANCE;
  }
  
  public Subscriber<? super Notification<T>> call(final Subscriber<? super T> paramSubscriber)
  {
    new Subscriber(paramSubscriber)
    {
      boolean terminated;
      
      public void onCompleted()
      {
        if (!this.terminated)
        {
          this.terminated = true;
          paramSubscriber.onCompleted();
        }
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        if (!this.terminated)
        {
          this.terminated = true;
          paramSubscriber.onError(paramAnonymousThrowable);
        }
      }
      
      public void onNext(Notification<T> paramAnonymousNotification)
      {
        switch (OperatorDematerialize.2.$SwitchMap$rx$Notification$Kind[paramAnonymousNotification.getKind().ordinal()])
        {
        }
        for (;;)
        {
          return;
          if (!this.terminated)
          {
            paramSubscriber.onNext(paramAnonymousNotification.getValue());
            continue;
            onError(paramAnonymousNotification.getThrowable());
            continue;
            onCompleted();
          }
        }
      }
    };
  }
  
  private static final class Holder
  {
    static final OperatorDematerialize<Object> INSTANCE = new OperatorDematerialize(null);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorDematerialize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */