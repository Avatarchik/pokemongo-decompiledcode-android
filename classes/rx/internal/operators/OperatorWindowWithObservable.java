package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import rx.Observable;
import rx.Observable.Operator;
import rx.Observer;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;

public final class OperatorWindowWithObservable<T, U>
  implements Observable.Operator<Observable<T>, T>
{
  static final Object NEXT_SUBJECT = new Object();
  static final NotificationLite<Object> nl = NotificationLite.instance();
  final Observable<U> other;
  
  public OperatorWindowWithObservable(Observable<U> paramObservable)
  {
    this.other = paramObservable;
  }
  
  public Subscriber<? super T> call(Subscriber<? super Observable<T>> paramSubscriber)
  {
    SourceSubscriber localSourceSubscriber = new SourceSubscriber(paramSubscriber);
    BoundarySubscriber localBoundarySubscriber = new BoundarySubscriber(paramSubscriber, localSourceSubscriber);
    paramSubscriber.add(localSourceSubscriber);
    paramSubscriber.add(localBoundarySubscriber);
    localSourceSubscriber.replaceWindow();
    this.other.unsafeSubscribe(localBoundarySubscriber);
    return localSourceSubscriber;
  }
  
  static final class BoundarySubscriber<T, U>
    extends Subscriber<U>
  {
    final OperatorWindowWithObservable.SourceSubscriber<T> sub;
    
    public BoundarySubscriber(Subscriber<?> paramSubscriber, OperatorWindowWithObservable.SourceSubscriber<T> paramSourceSubscriber)
    {
      this.sub = paramSourceSubscriber;
    }
    
    public void onCompleted()
    {
      this.sub.onCompleted();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.sub.onError(paramThrowable);
    }
    
    public void onNext(U paramU)
    {
      this.sub.replaceWindow();
    }
    
    public void onStart()
    {
      request(Long.MAX_VALUE);
    }
  }
  
  static final class SourceSubscriber<T>
    extends Subscriber<T>
  {
    final Subscriber<? super Observable<T>> child;
    Observer<T> consumer;
    boolean emitting;
    final Object guard;
    Observable<T> producer;
    List<Object> queue;
    
    public SourceSubscriber(Subscriber<? super Observable<T>> paramSubscriber)
    {
      this.child = new SerializedSubscriber(paramSubscriber);
      this.guard = new Object();
    }
    
    void complete()
    {
      Observer localObserver = this.consumer;
      this.consumer = null;
      this.producer = null;
      if (localObserver != null) {
        localObserver.onCompleted();
      }
      this.child.onCompleted();
      unsubscribe();
    }
    
    void createNewWindow()
    {
      BufferUntilSubscriber localBufferUntilSubscriber = BufferUntilSubscriber.create();
      this.consumer = localBufferUntilSubscriber;
      this.producer = localBufferUntilSubscriber;
    }
    
    void drain(List<Object> paramList)
    {
      if (paramList == null) {}
      label89:
      for (;;)
      {
        return;
        Iterator localIterator = paramList.iterator();
        for (;;)
        {
          if (!localIterator.hasNext()) {
            break label89;
          }
          Object localObject = localIterator.next();
          if (localObject == OperatorWindowWithObservable.NEXT_SUBJECT)
          {
            replaceSubject();
          }
          else
          {
            if (OperatorWindowWithObservable.nl.isError(localObject))
            {
              error(OperatorWindowWithObservable.nl.getError(localObject));
              break;
            }
            if (OperatorWindowWithObservable.nl.isCompleted(localObject))
            {
              complete();
              break;
            }
            emitValue(localObject);
          }
        }
      }
    }
    
    void emitValue(T paramT)
    {
      Observer localObserver = this.consumer;
      if (localObserver != null) {
        localObserver.onNext(paramT);
      }
    }
    
    void error(Throwable paramThrowable)
    {
      Observer localObserver = this.consumer;
      this.consumer = null;
      this.producer = null;
      if (localObserver != null) {
        localObserver.onError(paramThrowable);
      }
      this.child.onError(paramThrowable);
      unsubscribe();
    }
    
    public void onCompleted()
    {
      List localList;
      synchronized (this.guard)
      {
        if (this.emitting)
        {
          if (this.queue == null) {
            this.queue = new ArrayList();
          }
          this.queue.add(OperatorWindowWithObservable.nl.completed());
          return;
        }
        localList = this.queue;
        this.queue = null;
        this.emitting = true;
      }
      try
      {
        drain(localList);
        complete();
      }
      catch (Throwable localThrowable)
      {
        error(localThrowable);
      }
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    
    public void onError(Throwable paramThrowable)
    {
      synchronized (this.guard)
      {
        if (this.emitting)
        {
          this.queue = Collections.singletonList(OperatorWindowWithObservable.nl.error(paramThrowable));
        }
        else
        {
          this.queue = null;
          this.emitting = true;
          error(paramThrowable);
        }
      }
    }
    
    public void onNext(T paramT)
    {
      List localList;
      int i;
      int j;
      synchronized (this.guard)
      {
        if (this.emitting)
        {
          if (this.queue == null) {
            this.queue = new ArrayList();
          }
          this.queue.add(paramT);
          return;
        }
        localList = this.queue;
        this.queue = null;
        this.emitting = true;
        i = 1;
        j = 0;
      }
      try
      {
        for (;;)
        {
          drain(localList);
          if (i != 0)
          {
            i = 0;
            emitValue(paramT);
          }
          synchronized (this.guard)
          {
            localList = this.queue;
            this.queue = null;
            if (localList == null)
            {
              this.emitting = false;
              j = 1;
              if (j == 0)
              {
                synchronized (this.guard)
                {
                  this.emitting = false;
                }
                localObject2 = finally;
                throw ((Throwable)localObject2);
              }
            }
            else
            {
              boolean bool = this.child.isUnsubscribed();
              if (bool) {
                if (0 == 0) {
                  synchronized (this.guard)
                  {
                    this.emitting = false;
                  }
                }
              }
            }
          }
        }
        return;
      }
      finally
      {
        if (j == 0) {}
        synchronized (this.guard)
        {
          this.emitting = false;
          throw ((Throwable)localObject3);
        }
      }
    }
    
    public void onStart()
    {
      request(Long.MAX_VALUE);
    }
    
    void replaceSubject()
    {
      Observer localObserver = this.consumer;
      if (localObserver != null) {
        localObserver.onCompleted();
      }
      createNewWindow();
      this.child.onNext(this.producer);
    }
    
    void replaceWindow()
    {
      List localList;
      int i;
      int j;
      synchronized (this.guard)
      {
        if (this.emitting)
        {
          if (this.queue == null) {
            this.queue = new ArrayList();
          }
          this.queue.add(OperatorWindowWithObservable.NEXT_SUBJECT);
          return;
        }
        localList = this.queue;
        this.queue = null;
        this.emitting = true;
        i = 1;
        j = 0;
      }
      try
      {
        for (;;)
        {
          drain(localList);
          if (i != 0)
          {
            i = 0;
            replaceSubject();
          }
          synchronized (this.guard)
          {
            localList = this.queue;
            this.queue = null;
            if (localList == null)
            {
              this.emitting = false;
              j = 1;
              if (j == 0)
              {
                synchronized (this.guard)
                {
                  this.emitting = false;
                }
                localObject2 = finally;
                throw ((Throwable)localObject2);
              }
            }
            else
            {
              boolean bool = this.child.isUnsubscribed();
              if (bool) {
                if (0 == 0) {
                  synchronized (this.guard)
                  {
                    this.emitting = false;
                  }
                }
              }
            }
          }
        }
        return;
      }
      finally
      {
        if (j == 0) {}
        synchronized (this.guard)
        {
          this.emitting = false;
          throw ((Throwable)localObject3);
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorWindowWithObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */