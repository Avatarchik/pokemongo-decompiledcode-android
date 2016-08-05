package rx.internal.operators;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import rx.Observable.Operator;
import rx.Subscriber;
import rx.internal.util.BackpressureDrainManager;
import rx.internal.util.BackpressureDrainManager.BackpressureQueueCallback;

public class OperatorOnBackpressureBlock<T>
  implements Observable.Operator<T, T>
{
  final int max;
  
  public OperatorOnBackpressureBlock(int paramInt)
  {
    this.max = paramInt;
  }
  
  public Subscriber<? super T> call(Subscriber<? super T> paramSubscriber)
  {
    BlockingSubscriber localBlockingSubscriber = new BlockingSubscriber(this.max, paramSubscriber);
    localBlockingSubscriber.init();
    return localBlockingSubscriber;
  }
  
  static final class BlockingSubscriber<T>
    extends Subscriber<T>
    implements BackpressureDrainManager.BackpressureQueueCallback
  {
    final Subscriber<? super T> child;
    final BackpressureDrainManager manager;
    final NotificationLite<T> nl = NotificationLite.instance();
    final BlockingQueue<Object> queue;
    
    public BlockingSubscriber(int paramInt, Subscriber<? super T> paramSubscriber)
    {
      this.queue = new ArrayBlockingQueue(paramInt);
      this.child = paramSubscriber;
      this.manager = new BackpressureDrainManager(this);
    }
    
    public boolean accept(Object paramObject)
    {
      return this.nl.accept(this.child, paramObject);
    }
    
    public void complete(Throwable paramThrowable)
    {
      if (paramThrowable != null) {
        this.child.onError(paramThrowable);
      }
      for (;;)
      {
        return;
        this.child.onCompleted();
      }
    }
    
    void init()
    {
      this.child.add(this);
      this.child.setProducer(this.manager);
    }
    
    public void onCompleted()
    {
      this.manager.terminateAndDrain();
    }
    
    public void onError(Throwable paramThrowable)
    {
      this.manager.terminateAndDrain(paramThrowable);
    }
    
    public void onNext(T paramT)
    {
      try
      {
        this.queue.put(this.nl.next(paramT));
        this.manager.drain();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          if (!isUnsubscribed()) {
            onError(localInterruptedException);
          }
        }
      }
    }
    
    public Object peek()
    {
      return this.queue.peek();
    }
    
    public Object poll()
    {
      return this.queue.poll();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/OperatorOnBackpressureBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */