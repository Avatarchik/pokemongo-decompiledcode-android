package com.upsight.android.internal;

import android.content.Context;
import dagger.internal.Factory;

public final class ContextModule_ProvideApplicationContextFactory
  implements Factory<Context>
{
  private final ContextModule module;
  
  static
  {
    if (!ContextModule_ProvideApplicationContextFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ContextModule_ProvideApplicationContextFactory(ContextModule paramContextModule)
  {
    assert (paramContextModule != null);
    this.module = paramContextModule;
  }
  
  public static Factory<Context> create(ContextModule paramContextModule)
  {
    return new ContextModule_ProvideApplicationContextFactory(paramContextModule);
  }
  
  public Context get()
  {
    Context localContext = this.module.provideApplicationContext();
    if (localContext == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localContext;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/ContextModule_ProvideApplicationContextFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */