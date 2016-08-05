package rx.internal.operators;

import java.io.Serializable;
import rx.Notification.Kind;
import rx.Observer;

public final class NotificationLite<T>
{
  private static final NotificationLite INSTANCE = new NotificationLite();
  private static final Object ON_COMPLETED_SENTINEL = new Serializable()
  {
    private static final long serialVersionUID = 1L;
    
    public String toString()
    {
      return "Notification=>Completed";
    }
  };
  private static final Object ON_NEXT_NULL_SENTINEL = new Serializable()
  {
    private static final long serialVersionUID = 2L;
    
    public String toString()
    {
      return "Notification=>NULL";
    }
  };
  
  public static <T> NotificationLite<T> instance()
  {
    return INSTANCE;
  }
  
  public boolean accept(Observer<? super T> paramObserver, Object paramObject)
  {
    boolean bool = true;
    if (paramObject == ON_COMPLETED_SENTINEL) {
      paramObserver.onCompleted();
    }
    for (;;)
    {
      return bool;
      if (paramObject == ON_NEXT_NULL_SENTINEL)
      {
        paramObserver.onNext(null);
        bool = false;
      }
      else
      {
        if (paramObject == null) {
          break;
        }
        if (paramObject.getClass() == OnErrorSentinel.class)
        {
          paramObserver.onError(((OnErrorSentinel)paramObject).e);
        }
        else
        {
          paramObserver.onNext(paramObject);
          bool = false;
        }
      }
    }
    throw new IllegalArgumentException("The lite notification can not be null");
  }
  
  public Object completed()
  {
    return ON_COMPLETED_SENTINEL;
  }
  
  public Object error(Throwable paramThrowable)
  {
    return new OnErrorSentinel(paramThrowable);
  }
  
  public Throwable getError(Object paramObject)
  {
    return ((OnErrorSentinel)paramObject).e;
  }
  
  public T getValue(Object paramObject)
  {
    if (paramObject == ON_NEXT_NULL_SENTINEL) {
      paramObject = null;
    }
    return (T)paramObject;
  }
  
  public boolean isCompleted(Object paramObject)
  {
    if (paramObject == ON_COMPLETED_SENTINEL) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isError(Object paramObject)
  {
    return paramObject instanceof OnErrorSentinel;
  }
  
  public boolean isNext(Object paramObject)
  {
    if ((paramObject != null) && (!isError(paramObject)) && (!isCompleted(paramObject))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isNull(Object paramObject)
  {
    if (paramObject == ON_NEXT_NULL_SENTINEL) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Notification.Kind kind(Object paramObject)
  {
    if (paramObject == null) {
      throw new IllegalArgumentException("The lite notification can not be null");
    }
    Notification.Kind localKind;
    if (paramObject == ON_COMPLETED_SENTINEL) {
      localKind = Notification.Kind.OnCompleted;
    }
    for (;;)
    {
      return localKind;
      if ((paramObject instanceof OnErrorSentinel)) {
        localKind = Notification.Kind.OnError;
      } else {
        localKind = Notification.Kind.OnNext;
      }
    }
  }
  
  public Object next(T paramT)
  {
    if (paramT == null) {
      paramT = ON_NEXT_NULL_SENTINEL;
    }
    return paramT;
  }
  
  private static class OnErrorSentinel
    implements Serializable
  {
    private static final long serialVersionUID = 3L;
    private final Throwable e;
    
    public OnErrorSentinel(Throwable paramThrowable)
    {
      this.e = paramThrowable;
    }
    
    public String toString()
    {
      return "Notification=>Error:" + this.e;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/internal/operators/NotificationLite.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */