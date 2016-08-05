package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import rx.Observable;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func0;
import rx.observers.SerializedSubscriber;
import rx.observers.Subscribers;

public final class OperatorBufferWithSingleObservable<T, TClosing>
  implements Observable.Operator<List<T>, T>
{
  final Func0<? extends Observable<? extends TClosing>> bufferClosingSelector;
  final int initialCapacity;
  
  public OperatorBufferWithSingleObservable(final Observable<? extends TClosing> paramObservable, int paramInt)
  {
    this.bufferClosingSelector = new Func0()
    {
      public Observable<? extends TClosing> call()
      {
        return paramObservable;
      }
    };
    this.initialCapacity = paramInt;
  }
  
  public OperatorBufferWithSingleObservable(Func0<? extends Observable<? extends TClosing>> paramFunc0, int paramInt)
  {
    this.bufferClosingSelector = paramFunc0;
    this.initialCapacity = paramInt;
  }
  
  public Subscriber<? super T> call(Subscriber<? super List<T>> paramSubscriber)
  {
    try
    {
      Observable localObservable = (Observable)this.bufferClosingSelector.call();
      localObject = new BufferingSubscriber(new SerializedSubscriber(paramSubscriber));
      Subscriber local2 = new Subscriber()
      {
        public void onCompleted()
        {
          localObject.onCompleted();
        }
        
        public void onError(Throwable paramAnonymousThrowable)
        {
          localObject.onError(paramAnonymousThrowable);
        }
        
        public void onNext(TClosing paramAnonymousTClosing)
        {
          localObject.emit();
        }
      };
      paramSubscriber.add(local2);
      paramSubscriber.add((Subscription)localObject);
      localObservable.unsafeSubscribe(local2);
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        paramSubscriber.onError(localThrowable);
        final Object localObject = Subscribers.empty();
      }
    }
    return (Subscriber<? super T>)localObject;
  }
  
  final class BufferingSubscriber
    extends Subscriber<T>
  {
    final Subscriber<? super List<T>> child;
    List<T> chunk;
    boolean done;
    
    public BufferingSubscriber()
    {
      Subscriber localSubscriber;
      this.child = localSubscriber;
      this.chunk = new ArrayList(OperatorBufferWithSingleObservable.this.initialCapacity);
    }
    
    /* Error */
    void emit()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 43	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:done	Z
      //   6: ifeq +8 -> 14
      //   9: aload_0
      //   10: monitorexit
      //   11: goto +85 -> 96
      //   14: aload_0
      //   15: getfield 38	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:chunk	Ljava/util/List;
      //   18: astore_2
      //   19: aload_0
      //   20: new 29	java/util/ArrayList
      //   23: dup
      //   24: aload_0
      //   25: getfield 22	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:this$0	Lrx/internal/operators/OperatorBufferWithSingleObservable;
      //   28: getfield 33	rx/internal/operators/OperatorBufferWithSingleObservable:initialCapacity	I
      //   31: invokespecial 36	java/util/ArrayList:<init>	(I)V
      //   34: putfield 38	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:chunk	Ljava/util/List;
      //   37: aload_0
      //   38: monitorexit
      //   39: aload_0
      //   40: getfield 27	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:child	Lrx/Subscriber;
      //   43: aload_2
      //   44: invokevirtual 47	rx/Subscriber:onNext	(Ljava/lang/Object;)V
      //   47: goto +49 -> 96
      //   50: astore_3
      //   51: aload_0
      //   52: invokevirtual 50	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:unsubscribe	()V
      //   55: aload_0
      //   56: monitorenter
      //   57: aload_0
      //   58: getfield 43	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:done	Z
      //   61: ifeq +20 -> 81
      //   64: aload_0
      //   65: monitorexit
      //   66: goto +30 -> 96
      //   69: astore 4
      //   71: aload_0
      //   72: monitorexit
      //   73: aload 4
      //   75: athrow
      //   76: astore_1
      //   77: aload_0
      //   78: monitorexit
      //   79: aload_1
      //   80: athrow
      //   81: aload_0
      //   82: iconst_1
      //   83: putfield 43	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:done	Z
      //   86: aload_0
      //   87: monitorexit
      //   88: aload_0
      //   89: getfield 27	rx/internal/operators/OperatorBufferWithSingleObservable$BufferingSubscriber:child	Lrx/Subscriber;
      //   92: aload_3
      //   93: invokevirtual 54	rx/Subscriber:onError	(Ljava/lang/Throwable;)V
      //   96: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	97	0	this	BufferingSubscriber
      //   76	4	1	localObject1	Object
      //   18	26	2	localList	List
      //   50	43	3	localThrowable	Throwable
      //   69	5	4	localObject2	Object
      // Exception table:
      //   from	to	target	type
      //   39	47	50	java/lang/Throwable
      //   57	73	69	finally
      //   81	88	69	finally
      //   2	39	76	finally
      //   77	79	76	finally
    }
    
    public void onCompleted()
    {
      try
      {
        try
        {
          if (this.done) {
            return;
          }
          this.done = true;
          List localList = this.chunk;
          this.chunk = null;
          this.child.onNext(localList);
          this.child.onCompleted();
          unsubscribe();
        }
        finally {}
        return;
      }
      catch (Throwable localThrowable)
      {
        this.child.onError(localThrowable);
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
        this.chunk = null;
        this.child.onError(paramThrowable);
        unsubscribe();
      }
      finally {}
    }
    
    public void onNext(T paramT)
    {
      try
      {
        if (this.done) {
          return;
        }
        this.chunk.add(paramT);
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorBufferWithSingleObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */