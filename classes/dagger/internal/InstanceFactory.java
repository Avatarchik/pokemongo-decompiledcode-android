package dagger.internal;

public final class InstanceFactory<T>
  implements Factory<T>
{
  private final T instance;
  
  private InstanceFactory(T paramT)
  {
    this.instance = paramT;
  }
  
  public static <T> Factory<T> create(T paramT)
  {
    if (paramT == null) {
      throw new NullPointerException();
    }
    return new InstanceFactory(paramT);
  }
  
  public T get()
  {
    return (T)this.instance;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/dagger/internal/InstanceFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */