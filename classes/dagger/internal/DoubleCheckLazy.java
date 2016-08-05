package dagger.internal;

import dagger.Lazy;
import javax.inject.Provider;

public final class DoubleCheckLazy<T>
  implements Lazy<T>
{
  private static final Object UNINITIALIZED;
  private volatile Object instance = UNINITIALIZED;
  private final Provider<T> provider;
  
  static
  {
    if (!DoubleCheckLazy.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      UNINITIALIZED = new Object();
      return;
    }
  }
  
  private DoubleCheckLazy(Provider<T> paramProvider)
  {
    assert (paramProvider != null);
    this.provider = paramProvider;
  }
  
  public static <T> Lazy<T> create(Provider<T> paramProvider)
  {
    if (paramProvider == null) {
      throw new NullPointerException();
    }
    return new DoubleCheckLazy(paramProvider);
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
          localObject1 = this.provider.get();
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/dagger/internal/DoubleCheckLazy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */