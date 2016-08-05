package com.upsight.android.analytics.internal.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ConfigParser_Factory
  implements Factory<ConfigParser>
{
  private final Provider<ObjectMapper> mapperProvider;
  
  static
  {
    if (!ConfigParser_Factory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ConfigParser_Factory(Provider<ObjectMapper> paramProvider)
  {
    assert (paramProvider != null);
    this.mapperProvider = paramProvider;
  }
  
  public static Factory<ConfigParser> create(Provider<ObjectMapper> paramProvider)
  {
    return new ConfigParser_Factory(paramProvider);
  }
  
  public ConfigParser get()
  {
    return new ConfigParser((ObjectMapper)this.mapperProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/ConfigParser_Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */