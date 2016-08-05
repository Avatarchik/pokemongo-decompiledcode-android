package com.upsight.android.internal;

import com.upsight.android.internal.logger.LogWriter;
import dagger.internal.Factory;

public final class ContextModule_ProvideLogWriterFactory
  implements Factory<LogWriter>
{
  private final ContextModule module;
  
  static
  {
    if (!ContextModule_ProvideLogWriterFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ContextModule_ProvideLogWriterFactory(ContextModule paramContextModule)
  {
    assert (paramContextModule != null);
    this.module = paramContextModule;
  }
  
  public static Factory<LogWriter> create(ContextModule paramContextModule)
  {
    return new ContextModule_ProvideLogWriterFactory(paramContextModule);
  }
  
  public LogWriter get()
  {
    LogWriter localLogWriter = this.module.provideLogWriter();
    if (localLogWriter == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localLogWriter;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/ContextModule_ProvideLogWriterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */