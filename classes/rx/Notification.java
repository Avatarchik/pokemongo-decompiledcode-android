package rx;

public final class Notification<T>
{
  private static final Notification<Void> ON_COMPLETED = new Notification(Kind.OnCompleted, null, null);
  private final Kind kind;
  private final Throwable throwable;
  private final T value;
  
  private Notification(Kind paramKind, T paramT, Throwable paramThrowable)
  {
    this.value = paramT;
    this.throwable = paramThrowable;
    this.kind = paramKind;
  }
  
  public static <T> Notification<T> createOnCompleted()
  {
    return ON_COMPLETED;
  }
  
  public static <T> Notification<T> createOnCompleted(Class<T> paramClass)
  {
    return ON_COMPLETED;
  }
  
  public static <T> Notification<T> createOnError(Throwable paramThrowable)
  {
    return new Notification(Kind.OnError, null, paramThrowable);
  }
  
  public static <T> Notification<T> createOnNext(T paramT)
  {
    return new Notification(Kind.OnNext, paramT, null);
  }
  
  public void accept(Observer<? super T> paramObserver)
  {
    if (isOnNext()) {
      paramObserver.onNext(getValue());
    }
    for (;;)
    {
      return;
      if (isOnCompleted()) {
        paramObserver.onCompleted();
      } else if (isOnError()) {
        paramObserver.onError(getThrowable());
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (paramObject == null) {}
    for (;;)
    {
      return bool;
      if (this == paramObject)
      {
        bool = true;
      }
      else if (paramObject.getClass() == getClass())
      {
        Notification localNotification = (Notification)paramObject;
        if ((localNotification.getKind() == getKind()) && ((!hasValue()) || (getValue().equals(localNotification.getValue()))) && ((!hasThrowable()) || (getThrowable().equals(localNotification.getThrowable())))) {
          bool = true;
        }
      }
    }
  }
  
  public Kind getKind()
  {
    return this.kind;
  }
  
  public Throwable getThrowable()
  {
    return this.throwable;
  }
  
  public T getValue()
  {
    return (T)this.value;
  }
  
  public boolean hasThrowable()
  {
    if ((isOnError()) && (this.throwable != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean hasValue()
  {
    if ((isOnNext()) && (this.value != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    int i = getKind().hashCode();
    if (hasValue()) {
      i = i * 31 + getValue().hashCode();
    }
    if (hasThrowable()) {
      i = i * 31 + getThrowable().hashCode();
    }
    return i;
  }
  
  public boolean isOnCompleted()
  {
    if (getKind() == Kind.OnCompleted) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isOnError()
  {
    if (getKind() == Kind.OnError) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isOnNext()
  {
    if (getKind() == Kind.OnNext) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("[").append(super.toString()).append(" ").append(getKind());
    if (hasValue()) {
      localStringBuilder.append(" ").append(getValue());
    }
    if (hasThrowable()) {
      localStringBuilder.append(" ").append(getThrowable().getMessage());
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public static enum Kind
  {
    static
    {
      OnError = new Kind("OnError", 1);
      OnCompleted = new Kind("OnCompleted", 2);
      Kind[] arrayOfKind = new Kind[3];
      arrayOfKind[0] = OnNext;
      arrayOfKind[1] = OnError;
      arrayOfKind[2] = OnCompleted;
      $VALUES = arrayOfKind;
    }
    
    private Kind() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/Notification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */