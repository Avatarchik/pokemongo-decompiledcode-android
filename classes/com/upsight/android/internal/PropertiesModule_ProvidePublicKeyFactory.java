package com.upsight.android.internal;

import android.content.Context;
import com.upsight.android.logger.UpsightLogger;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PropertiesModule_ProvidePublicKeyFactory
  implements Factory<String>
{
  private final Provider<Context> contextProvider;
  private final Provider<UpsightLogger> loggerProvider;
  private final PropertiesModule module;
  
  static
  {
    if (!PropertiesModule_ProvidePublicKeyFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public PropertiesModule_ProvidePublicKeyFactory(PropertiesModule paramPropertiesModule, Provider<Context> paramProvider, Provider<UpsightLogger> paramProvider1)
  {
    assert (paramPropertiesModule != null);
    this.module = paramPropertiesModule;
    assert (paramProvider != null);
    this.contextProvider = paramProvider;
    assert (paramProvider1 != null);
    this.loggerProvider = paramProvider1;
  }
  
  public static Factory<String> create(PropertiesModule paramPropertiesModule, Provider<Context> paramProvider, Provider<UpsightLogger> paramProvider1)
  {
    return new PropertiesModule_ProvidePublicKeyFactory(paramPropertiesModule, paramProvider, paramProvider1);
  }
  
  public String get()
  {
    String str = this.module.providePublicKey((Context)this.contextProvider.get(), (UpsightLogger)this.loggerProvider.get());
    if (str == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return str;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/PropertiesModule_ProvidePublicKeyFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */