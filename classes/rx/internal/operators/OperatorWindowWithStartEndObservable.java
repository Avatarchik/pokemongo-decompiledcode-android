package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Observable;
import rx.Observable.Operator;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func1;
import rx.observers.SerializedObserver;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.CompositeSubscription;

public final class OperatorWindowWithStartEndObservable<T, U, V>
  implements Observable.Operator<Observable<T>, T>
{
  final Func1<? super U, ? extends Observable<? extends V>> windowClosingSelector;
  final Observable<? extends U> windowOpenings;
  
  public OperatorWindowWithStartEndObservable(Observable<? extends U> paramObservable, Func1<? super U, ? extends Observable<? extends V>> paramFunc1)
  {
    this.windowOpenings = paramObservable;
    this.windowClosingSelector = paramFunc1;
  }
  
  public Subscriber<? super T> call(Subscriber<? super Observable<T>> paramSubscriber)
  {
    CompositeSubscription localCompositeSubscription = new CompositeSubscription();
    paramSubscriber.add(localCompositeSubscription);
    final SourceSubscriber localSourceSubscriber = new SourceSubscriber(paramSubscriber, localCompositeSubscription);
    Subscriber local1 = new Subscriber()
    {
      public void onCompleted()
      {
        localSourceSubscriber.onCompleted();
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        localSourceSubscriber.onError(paramAnonymousThrowable);
      }
      
      public void onNext(U paramAnonymousU)
      {
        localSourceSubscriber.beginWindow(paramAnonymousU);
      }
      
      public void onStart()
      {
        request(Long.MAX_VALUE);
      }
    };
    localCompositeSubscription.add(localSourceSubscriber);
    localCompositeSubscription.add(local1);
    this.windowOpenings.unsafeSubscribe(local1);
    return localSourceSubscriber;
  }
  
  final class SourceSubscriber
    extends Subscriber<T>
  {
    final Subscriber<? super Observable<T>> child;
    final List<OperatorWindowWithStartEndObservable.SerializedSubject<T>> chunks;
    final CompositeSubscription csub;
    boolean done;
    final Object guard;
    
    public SourceSubscriber(CompositeSubscription paramCompositeSubscription)
    {
      this.child = new SerializedSubscriber(paramCompositeSubscription);
      this.guard = new Object();
      this.chunks = new LinkedList();
      CompositeSubscription localCompositeSubscription;
      this.csub = localCompositeSubscription;
    }
    
    void beginWindow(U paramU)
    {
      final OperatorWindowWithStartEndObservable.SerializedSubject localSerializedSubject = createSerializedSubject();
      Observable localObservable;
      Subscriber local1;
      synchronized (this.guard)
      {
        if (!this.done)
        {
          this.chunks.add(localSerializedSubject);
          this.child.onNext(localSerializedSubject.producer);
        }
      }
    }
    
    OperatorWindowWithStartEndObservable.SerializedSubject<T> createSerializedSubject()
    {
      BufferUntilSubscriber localBufferUntilSubscriber = BufferUntilSubscriber.create();
      return new OperatorWindowWithStartEndObservable.SerializedSubject(localBufferUntilSubscriber, localBufferUntilSubscriber);
    }
    
    void endWindow(OperatorWindowWithStartEndObservable.SerializedSubject<T> paramSerializedSubject)
    {
      int i = 0;
      synchronized (this.guard)
      {
        if (!this.done)
        {
          Iterator localIterator = this.chunks.iterator();
          while (localIterator.hasNext()) {
            if ((OperatorWindowWithStartEndObservable.SerializedSubject)localIterator.next() == paramSerializedSubject)
            {
              i = 1;
              localIterator.remove();
            }
          }
          if (i != 0) {
            paramSerializedSubject.consumer.onCompleted();
          }
        }
      }
    }
    
    /* Error */
    public void onCompleted()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 43	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:guard	Ljava/lang/Object;
      //   4: astore_2
      //   5: aload_2
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 60	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:done	Z
      //   11: ifeq +13 -> 24
      //   14: aload_2
      //   15: monitorexit
      //   16: aload_0
      //   17: getfield 50	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:csub	Lrx/subscriptions/CompositeSubscription;
      //   20: invokevirtual 143	rx/subscriptions/CompositeSubscription:unsubscribe	()V
      //   23: return
      //   24: aload_0
      //   25: iconst_1
      //   26: putfield 60	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:done	Z
      //   29: new 145	java/util/ArrayList
      //   32: dup
      //   33: aload_0
      //   34: getfield 48	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:chunks	Ljava/util/List;
      //   37: invokespecial 148	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
      //   40: astore 4
      //   42: aload_0
      //   43: getfield 48	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:chunks	Ljava/util/List;
      //   46: invokeinterface 151 1 0
      //   51: aload_2
      //   52: monitorexit
      //   53: aload 4
      //   55: invokeinterface 118 1 0
      //   60: astore 5
      //   62: aload 5
      //   64: invokeinterface 124 1 0
      //   69: ifeq +39 -> 108
      //   72: aload 5
      //   74: invokeinterface 128 1 0
      //   79: checkcast 68	rx/internal/operators/OperatorWindowWithStartEndObservable$SerializedSubject
      //   82: getfield 135	rx/internal/operators/OperatorWindowWithStartEndObservable$SerializedSubject:consumer	Lrx/Observer;
      //   85: invokeinterface 140 1 0
      //   90: goto -28 -> 62
      //   93: astore_1
      //   94: aload_0
      //   95: getfield 50	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:csub	Lrx/subscriptions/CompositeSubscription;
      //   98: invokevirtual 143	rx/subscriptions/CompositeSubscription:unsubscribe	()V
      //   101: aload_1
      //   102: athrow
      //   103: astore_3
      //   104: aload_2
      //   105: monitorexit
      //   106: aload_3
      //   107: athrow
      //   108: aload_0
      //   109: getfield 38	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:child	Lrx/Subscriber;
      //   112: invokevirtual 152	rx/Subscriber:onCompleted	()V
      //   115: aload_0
      //   116: getfield 50	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:csub	Lrx/subscriptions/CompositeSubscription;
      //   119: invokevirtual 143	rx/subscriptions/CompositeSubscription:unsubscribe	()V
      //   122: goto -99 -> 23
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	125	0	this	SourceSubscriber
      //   93	9	1	localObject1	Object
      //   103	4	3	localObject3	Object
      //   40	14	4	localArrayList	ArrayList
      //   60	13	5	localIterator	Iterator
      // Exception table:
      //   from	to	target	type
      //   0	7	93	finally
      //   53	90	93	finally
      //   106	115	93	finally
      //   7	16	103	finally
      //   24	53	103	finally
      //   104	106	103	finally
    }
    
    /* Error */
    public void onError(Throwable paramThrowable)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 43	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:guard	Ljava/lang/Object;
      //   4: astore_3
      //   5: aload_3
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 60	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:done	Z
      //   11: ifeq +13 -> 24
      //   14: aload_3
      //   15: monitorexit
      //   16: aload_0
      //   17: getfield 50	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:csub	Lrx/subscriptions/CompositeSubscription;
      //   20: invokevirtual 143	rx/subscriptions/CompositeSubscription:unsubscribe	()V
      //   23: return
      //   24: aload_0
      //   25: iconst_1
      //   26: putfield 60	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:done	Z
      //   29: new 145	java/util/ArrayList
      //   32: dup
      //   33: aload_0
      //   34: getfield 48	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:chunks	Ljava/util/List;
      //   37: invokespecial 148	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
      //   40: astore 5
      //   42: aload_0
      //   43: getfield 48	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:chunks	Ljava/util/List;
      //   46: invokeinterface 151 1 0
      //   51: aload_3
      //   52: monitorexit
      //   53: aload 5
      //   55: invokeinterface 118 1 0
      //   60: astore 6
      //   62: aload 6
      //   64: invokeinterface 124 1 0
      //   69: ifeq +42 -> 111
      //   72: aload 6
      //   74: invokeinterface 128 1 0
      //   79: checkcast 68	rx/internal/operators/OperatorWindowWithStartEndObservable$SerializedSubject
      //   82: getfield 135	rx/internal/operators/OperatorWindowWithStartEndObservable$SerializedSubject:consumer	Lrx/Observer;
      //   85: aload_1
      //   86: invokeinterface 153 2 0
      //   91: goto -29 -> 62
      //   94: astore_2
      //   95: aload_0
      //   96: getfield 50	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:csub	Lrx/subscriptions/CompositeSubscription;
      //   99: invokevirtual 143	rx/subscriptions/CompositeSubscription:unsubscribe	()V
      //   102: aload_2
      //   103: athrow
      //   104: astore 4
      //   106: aload_3
      //   107: monitorexit
      //   108: aload 4
      //   110: athrow
      //   111: aload_0
      //   112: getfield 38	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:child	Lrx/Subscriber;
      //   115: aload_1
      //   116: invokevirtual 154	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   119: aload_0
      //   120: getfield 50	rx/internal/operators/OperatorWindowWithStartEndObservable$SourceSubscriber:csub	Lrx/subscriptions/CompositeSubscription;
      //   123: invokevirtual 143	rx/subscriptions/CompositeSubscription:unsubscribe	()V
      //   126: goto -103 -> 23
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	129	0	this	SourceSubscriber
      //   0	129	1	paramThrowable	Throwable
      //   94	9	2	localObject1	Object
      //   104	5	4	localObject3	Object
      //   40	14	5	localArrayList	ArrayList
      //   60	13	6	localIterator	Iterator
      // Exception table:
      //   from	to	target	type
      //   0	7	94	finally
      //   53	91	94	finally
      //   108	119	94	finally
      //   7	16	104	finally
      //   24	53	104	finally
      //   106	108	104	finally
    }
    
    public void onNext(T paramT)
    {
      synchronized (this.guard)
      {
        if (!this.done)
        {
          ArrayList localArrayList = new ArrayList(this.chunks);
          Iterator localIterator = localArrayList.iterator();
          if (localIterator.hasNext()) {
            ((OperatorWindowWithStartEndObservable.SerializedSubject)localIterator.next()).consumer.onNext(paramT);
          }
        }
      }
    }
    
    public void onStart()
    {
      request(Long.MAX_VALUE);
    }
  }
  
  static final class SerializedSubject<T>
  {
    final Observer<T> consumer;
    final Observable<T> producer;
    
    public SerializedSubject(Observer<T> paramObserver, Observable<T> paramObservable)
    {
      this.consumer = new SerializedObserver(paramObserver);
      this.producer = paramObservable;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorWindowWithStartEndObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */