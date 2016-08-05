package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import rx.Observable;
import rx.Observable.Operator;
import rx.Producer;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;

public final class OperatorSwitch<T>
  implements Observable.Operator<T, Observable<? extends T>>
{
  public static <T> OperatorSwitch<T> instance()
  {
    return Holder.INSTANCE;
  }
  
  public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> paramSubscriber)
  {
    SwitchSubscriber localSwitchSubscriber = new SwitchSubscriber(paramSubscriber);
    paramSubscriber.add(localSwitchSubscriber);
    return localSwitchSubscriber;
  }
  
  private static final class SwitchSubscriber<T>
    extends Subscriber<Observable<? extends T>>
  {
    boolean active;
    SwitchSubscriber<T>.InnerSubscriber currentSubscriber;
    boolean emitting;
    final Object guard = new Object();
    int index;
    volatile boolean infinite = false;
    long initialRequested;
    boolean mainDone;
    final NotificationLite<?> nl = NotificationLite.instance();
    List<Object> queue;
    final SerializedSubscriber<T> s;
    final SerialSubscription ssub;
    
    public SwitchSubscriber(Subscriber<? super T> paramSubscriber)
    {
      this.s = new SerializedSubscriber(paramSubscriber);
      this.ssub = new SerialSubscription();
      paramSubscriber.add(this.ssub);
      paramSubscriber.setProducer(new Producer()
      {
        public void request(long paramAnonymousLong)
        {
          if (OperatorSwitch.SwitchSubscriber.this.infinite) {}
          for (;;)
          {
            return;
            if (paramAnonymousLong == Long.MAX_VALUE) {
              OperatorSwitch.SwitchSubscriber.this.infinite = true;
            }
            OperatorSwitch.SwitchSubscriber.InnerSubscriber localInnerSubscriber;
            for (;;)
            {
              synchronized (OperatorSwitch.SwitchSubscriber.this.guard)
              {
                localInnerSubscriber = OperatorSwitch.SwitchSubscriber.this.currentSubscriber;
                if (OperatorSwitch.SwitchSubscriber.this.currentSubscriber == null)
                {
                  long l2 = paramAnonymousLong + OperatorSwitch.SwitchSubscriber.this.initialRequested;
                  if (l2 < 0L)
                  {
                    OperatorSwitch.SwitchSubscriber.this.infinite = true;
                    if (localInnerSubscriber == null) {
                      break;
                    }
                    if (!OperatorSwitch.SwitchSubscriber.this.infinite) {
                      break label177;
                    }
                    localInnerSubscriber.requestMore(Long.MAX_VALUE);
                    break;
                  }
                  OperatorSwitch.SwitchSubscriber.this.initialRequested = l2;
                }
              }
              long l1 = paramAnonymousLong + OperatorSwitch.SwitchSubscriber.InnerSubscriber.access$100(OperatorSwitch.SwitchSubscriber.this.currentSubscriber);
              if (l1 < 0L) {
                OperatorSwitch.SwitchSubscriber.this.infinite = true;
              } else {
                OperatorSwitch.SwitchSubscriber.InnerSubscriber.access$102(OperatorSwitch.SwitchSubscriber.this.currentSubscriber, l1);
              }
            }
            label177:
            localInnerSubscriber.requestMore(paramAnonymousLong);
          }
        }
      });
    }
    
    void complete(int paramInt)
    {
      List localList;
      synchronized (this.guard)
      {
        if (paramInt == this.index)
        {
          this.active = false;
          if (this.mainDone) {}
        }
      }
    }
    
    void drain(List<Object> paramList)
    {
      if (paramList == null) {}
      label87:
      for (;;)
      {
        return;
        Iterator localIterator = paramList.iterator();
        for (;;)
        {
          if (!localIterator.hasNext()) {
            break label87;
          }
          Object localObject = localIterator.next();
          if (this.nl.isCompleted(localObject))
          {
            this.s.onCompleted();
            break;
          }
          if (this.nl.isError(localObject))
          {
            this.s.onError(this.nl.getError(localObject));
            break;
          }
          this.s.onNext(localObject);
        }
      }
    }
    
    void emit(T paramT, int paramInt, SwitchSubscriber<T>.InnerSubscriber paramSwitchSubscriber)
    {
      List localList;
      int i;
      int j;
      boolean bool;
      synchronized (this.guard)
      {
        if (paramInt == this.index) {
          if (this.emitting)
          {
            if (this.queue == null) {
              this.queue = new ArrayList();
            }
            if (paramSwitchSubscriber.requested != Long.MAX_VALUE) {
              InnerSubscriber.access$110(paramSwitchSubscriber);
            }
            this.queue.add(paramT);
          }
        }
      }
    }
    
    void error(Throwable paramThrowable, int paramInt)
    {
      List localList;
      synchronized (this.guard)
      {
        if (paramInt == this.index) {
          if (this.emitting)
          {
            if (this.queue == null) {
              this.queue = new ArrayList();
            }
            this.queue.add(this.nl.error(paramThrowable));
          }
        }
      }
    }
    
    public void onCompleted()
    {
      List localList;
      synchronized (this.guard)
      {
        this.mainDone = true;
        if (!this.active) {
          if (this.emitting)
          {
            if (this.queue == null) {
              this.queue = new ArrayList();
            }
            this.queue.add(this.nl.completed());
          }
        }
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.s.onError(paramThrowable);
      unsubscribe();
    }
    
    public void onNext(Observable<? extends T> paramObservable)
    {
      for (;;)
      {
        synchronized (this.guard)
        {
          int i = 1 + this.index;
          this.index = i;
          this.active = true;
          long l;
          if (this.infinite)
          {
            l = Long.MAX_VALUE;
            this.currentSubscriber = new InnerSubscriber(i, l);
            InnerSubscriber.access$102(this.currentSubscriber, l);
            this.ssub.set(this.currentSubscriber);
            paramObservable.unsafeSubscribe(this.currentSubscriber);
            return;
          }
          if (this.currentSubscriber == null) {
            l = this.initialRequested;
          } else {
            l = this.currentSubscriber.requested;
          }
        }
      }
    }
    
    final class InnerSubscriber
      extends Subscriber<T>
    {
      private final int id;
      private final long initialRequested;
      private long requested = 0L;
      
      public InnerSubscriber(int paramInt, long paramLong)
      {
        this.id = paramInt;
        this.initialRequested = paramLong;
      }
      
      public void onCompleted()
      {
        OperatorSwitch.SwitchSubscriber.this.complete(this.id);
      }
      
      public void onError(Throwable paramThrowable)
      {
        OperatorSwitch.SwitchSubscriber.this.error(paramThrowable, this.id);
      }
      
      public void onNext(T paramT)
      {
        OperatorSwitch.SwitchSubscriber.this.emit(paramT, this.id, this);
      }
      
      public void onStart()
      {
        requestMore(this.initialRequested);
      }
      
      public void requestMore(long paramLong)
      {
        request(paramLong);
      }
    }
  }
  
  private static final class Holder
  {
    static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch(null);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorSwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */