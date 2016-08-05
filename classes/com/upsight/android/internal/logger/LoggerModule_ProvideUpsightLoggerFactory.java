package com.upsight.android.internal.logger;

import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.persistence.UpsightDataStore;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class LoggerModule_ProvideUpsightLoggerFactory
  implements Factory<UpsightLogger>
{
  private final Provider<UpsightDataStore> dataStoreProvider;
  private final LoggerModule module;
  private final Provider<LogWriter> writerProvider;
  
  static
  {
    if (!LoggerModule_ProvideUpsightLoggerFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public LoggerModule_ProvideUpsightLoggerFactory(LoggerModule paramLoggerModule, Provider<UpsightDataStore> paramProvider, Provider<LogWriter> paramProvider1)
  {
    assert (paramLoggerModule != null);
    this.module = paramLoggerModule;
    assert (paramProvider != null);
    this.dataStoreProvider = paramProvider;
    assert (paramProvider1 != null);
    this.writerProvider = paramProvider1;
  }
  
  public static Factory<UpsightLogger> create(LoggerModule paramLoggerModule, Provider<UpsightDataStore> paramProvider, Provider<LogWriter> paramProvider1)
  {
    return new LoggerModule_ProvideUpsightLoggerFactory(paramLoggerModule, paramProvider, paramProvider1);
  }
  
  public UpsightLogger get()
  {
    UpsightLogger localUpsightLogger = this.module.provideUpsightLogger((UpsightDataStore)this.dataStoreProvider.get(), (LogWriter)this.writerProvider.get());
    if (localUpsightLogger == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUpsightLogger;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/logger/LoggerModule_ProvideUpsightLoggerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */