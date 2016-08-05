package rx;

public abstract interface Observer<T>
{
  public abstract void onCompleted();
  
  public abstract void onError(Throwable paramThrowable);
  
  public abstract void onNext(T paramT);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/Observer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */