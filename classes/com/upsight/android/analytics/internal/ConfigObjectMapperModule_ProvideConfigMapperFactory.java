package com.upsight.android.analytics.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.UpsightContext;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ConfigObjectMapperModule_ProvideConfigMapperFactory
  implements Factory<ObjectMapper>
{
  private final ConfigObjectMapperModule module;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!ConfigObjectMapperModule_ProvideConfigMapperFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ConfigObjectMapperModule_ProvideConfigMapperFactory(ConfigObjectMapperModule paramConfigObjectMapperModule, Provider<UpsightContext> paramProvider)
  {
    assert (paramConfigObjectMapperModule != null);
    this.module = paramConfigObjectMapperModule;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
  }
  
  public static Factory<ObjectMapper> create(ConfigObjectMapperModule paramConfigObjectMapperModule, Provider<UpsightContext> paramProvider)
  {
    return new ConfigObjectMapperModule_ProvideConfigMapperFactory(paramConfigObjectMapperModule, paramProvider);
  }
  
  public ObjectMapper get()
  {
    ObjectMapper localObjectMapper = this.module.provideConfigMapper((UpsightContext)this.upsightProvider.get());
    if (localObjectMapper == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localObjectMapper;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/ConfigObjectMapperModule_ProvideConfigMapperFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */