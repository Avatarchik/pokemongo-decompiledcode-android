package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.functions.Func1;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.CompositeSubscription;

public final class OperatorBufferWithStartEndObservable<T, TOpening, TClosing>
  implements Observable.Operator<List<T>, T>
{
  final Func1<? super TOpening, ? extends Observable<? extends TClosing>> bufferClosing;
  final Observable<? extends TOpening> bufferOpening;
  
  public OperatorBufferWithStartEndObservable(Observable<? extends TOpening> paramObservable, Func1<? super TOpening, ? extends Observable<? extends TClosing>> paramFunc1)
  {
    this.bufferOpening = paramObservable;
    this.bufferClosing = paramFunc1;
  }
  
  public Subscriber<? super T> call(Subscriber<? super List<T>> paramSubscriber)
  {
    final BufferingSubscriber localBufferingSubscriber = new BufferingSubscriber(new SerializedSubscriber(paramSubscriber));
    Subscriber local1 = new Subscriber()
    {
      public void onCompleted()
      {
        localBufferingSubscriber.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        localBufferingSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(TOpening paramAnonymousTOpening)
      {
        localBufferingSubscriber.startBuffer(paramAnonymousTOpening);
      }
    };
    paramSubscriber.add(local1);
    paramSubscriber.add(localBufferingSubscriber);
    this.bufferOpening.unsafeSubscribe(local1);
    return localBufferingSubscriber;
  }
  
  final class BufferingSubscriber
    extends Subscriber<T>
  {
    final Subscriber<? super List<T>> child;
    final List<List<T>> chunks;
    final CompositeSubscription closingSubscriptions;
    boolean done;
    
    public BufferingSubscriber()
    {
      Subscriber localSubscriber;
      this.child = localSubscriber;
      this.chunks = new LinkedList();
      this.closingSubscriptions = new CompositeSubscription();
      add(this.closingSubscriptions);
    }
    
    void endBuffer(List<T> paramList)
    {
      int i = 0;
      try
      {
        if (this.done) {
          return;
        }
        Iterator localIterator = this.chunks.iterator();
        while (localIterator.hasNext()) {
          if ((List)localIterator.next() == paramList)
          {
            i = 1;
            localIterator.remove();
          }
        }
        if (i != 0) {
          this.child.onNext(paramList);
        }
      }
      finally {}
    }
    
    public void onCompleted()
    {
      try
      {
        LinkedList localLinkedList;
        Iterator localIterator;
        List localList;
        return;
      }
      catch (Throwable localThrowable)
      {
        try
        {
          if (this.done) {
            return;
          }
          this.done = true;
          localLinkedList = new LinkedList(this.chunks);
          this.chunks.clear();
          localIterator = localLinkedList.iterator();
          while (localIterator.hasNext())
          {
            localList = (List)localIterator.next();
            this.child.onNext(localList);
            continue;
            localThrowable = localThrowable;
            this.child.onError(localThrowable);
          }
        }
        finally {}
        this.child.onCompleted();
        unsubscribe();
      }
    }
    
    public void onError(Throwable paramThrowable)
    {
      try
      {
        if (this.done) {
          return;
        }
        this.done = true;
        this.chunks.clear();
        this.child.onError(paramThrowable);
        unsubscribe();
      }
      finally {}
    }
    
    public void onNext(T paramT)
    {
      try
      {
        Iterator localIterator = this.chunks.iterator();
        if (localIterator.hasNext()) {
          ((List)localIterator.next()).add(paramT);
        }
      }
      finally {}
    }
    
    void startBuffer(TOpening paramTOpening)
    {
      final ArrayList localArrayList = new ArrayList();
      try
      {
        if (this.done) {
          return;
        }
        this.chunks.add(localArrayList);
        Observable localObservable;
        Subscriber local1;
        return;
      }
      finally
      {
        try
        {
          localObservable = (Observable)OperatorBufferWithStartEndObservable.this.bufferClosing.call(paramTOpening);
          local1 = new Subscriber()
          {
            public void onCompleted()
            {
              OperatorBufferWithStartEndObservable.BufferingSubscriber.this.closingSubscriptions.remove(this);
              OperatorBufferWithStartEndObservable.BufferingSubscriber.this.endBuffer(localArrayList);
            }
            
            public void onError(Throwable paramAnonymousThrowable)
            {
              OperatorBufferWithStartEndObservable.BufferingSubscriber.this.onError(paramAnonymousThrowable);
            }
            
            public void onNext(TClosing paramAnonymousTClosing)
            {
              OperatorBufferWithStartEndObservable.BufferingSubscriber.this.closingSubscriptions.remove(this);
              OperatorBufferWithStartEndObservable.BufferingSubscriber.this.endBuffer(localArrayList);
            }
          };
          this.closingSubscriptions.add(local1);
          localObservable.unsafeSubscribe(local1);
        }
        catch (Throwable localThrowable)
        {
          onError(localThrowable);
        }
        localObject = finally;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorBufferWithStartEndObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */