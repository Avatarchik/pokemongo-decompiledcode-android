package dagger.internal;

import javax.inject.Provider;

public final class ScopedProvider<T>
  implements Provider<T>
{
  private static final Object UNINITIALIZED;
  private final Factory<T> factory;
  private volatile Object instance = UNINITIALIZED;
  
  static
  {
    if (!ScopedProvider.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      UNINITIALIZED = new Object();
      return;
    }
  }
  
  private ScopedProvider(Factory<T> paramFactory)
  {
    assert (paramFactory != null);
    this.factory = paramFactory;
  }
  
  public static <T> Provider<T> create(Factory<T> paramFactory)
  {
    if (paramFactory == null) {
      throw new NullPointerException();
    }
    return new ScopedProvider(paramFactory);
  }
  
  public T get()
  {
    Object localObject1 = this.instance;
    if (localObject1 == UNINITIALIZED) {
      try
      {
        localObject1 = this.instance;
        if (localObject1 == UNINITIALIZED)
        {
          localObject1 = this.factory.get();
          this.instance = localObject1;
        }
      }
      finally
      {
        localObject2 = finally;
        throw ((Throwable)localObject2);
      }
    }
    return (T)localObject1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/dagger/internal/ScopedProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */