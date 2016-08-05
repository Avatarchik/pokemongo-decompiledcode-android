package rx.observers;

import rx.Observer;
import rx.Subscriber;

public class SerializedSubscriber<T>
  extends Subscriber<T>
{
  private final Observer<T> s;
  
  public SerializedSubscriber(Subscriber<? super T> paramSubscriber)
  {
    this(paramSubscriber, true);
  }
  
  public SerializedSubscriber(Subscriber<? super T> paramSubscriber, boolean paramBoolean)
  {
    super(paramSubscriber, paramBoolean);
    this.s = new SerializedObserver(paramSubscriber);
  }
  
  public void onCompleted()
  {
    this.s.onCompleted();
  }
  
  public void onError(Throwable paramThrowable)
  {
    this.s.onError(paramThrowable);
  }
  
  public void onNext(T paramT)
  {
    this.s.onNext(paramT);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/observers/SerializedSubscriber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */