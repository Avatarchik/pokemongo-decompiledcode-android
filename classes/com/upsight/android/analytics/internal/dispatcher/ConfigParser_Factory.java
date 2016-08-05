package com.upsight.android.analytics.internal.dispatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.UpsightContext;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ConfigParser_Factory
  implements Factory<ConfigParser>
{
  private final Provider<ObjectMapper> mapperProvider;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!ConfigParser_Factory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ConfigParser_Factory(Provider<UpsightContext> paramProvider, Provider<ObjectMapper> paramProvider1)
  {
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
    assert (paramProvider1 != null);
    this.mapperProvider = paramProvider1;
  }
  
  public static Factory<ConfigParser> create(Provider<UpsightContext> paramProvider, Provider<ObjectMapper> paramProvider1)
  {
    return new ConfigParser_Factory(paramProvider, paramProvider1);
  }
  
  public ConfigParser get()
  {
    return new ConfigParser((UpsightContext)this.upsightProvider.get(), (ObjectMapper)this.mapperProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/ConfigParser_Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */