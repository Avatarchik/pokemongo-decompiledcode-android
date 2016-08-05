package rx.internal.producers;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;

public final class SingleProducer<T>
  extends AtomicBoolean
  implements Producer
{
  private static final long serialVersionUID = -3353584923995471404L;
  final Subscriber<? super T> child;
  final T value;
  
  public SingleProducer(Subscriber<? super T> paramSubscriber, T paramT)
  {
    this.child = paramSubscriber;
    this.value = paramT;
  }
  
  public void request(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("n >= 0 required");
    }
    if (paramLong == 0L) {}
    for (;;)
    {
      return;
      if (compareAndSet(false, true))
      {
        Subscriber localSubscriber = this.child;
        Object localObject = this.value;
        if (!localSubscriber.isUnsubscribed()) {
          try
          {
            localSubscriber.onNext(localObject);
            if (localSubscriber.isUnsubscribed()) {
              continue;
            }
            localSubscriber.onCompleted();
          }
          catch (Throwable localThrowable)
          {
            Exceptions.throwIfFatal(localThrowable);
            localSubscriber.onError(OnErrorThrowable.addValueAsLastCause(localThrowable, localObject));
          }
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/producers/SingleProducer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */