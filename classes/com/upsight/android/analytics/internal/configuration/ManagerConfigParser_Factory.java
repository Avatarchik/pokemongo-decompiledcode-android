package com.upsight.android.analytics.internal.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ManagerConfigParser_Factory
  implements Factory<ManagerConfigParser>
{
  private final Provider<ObjectMapper> mapperProvider;
  
  static
  {
    if (!ManagerConfigParser_Factory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ManagerConfigParser_Factory(Provider<ObjectMapper> paramProvider)
  {
    assert (paramProvider != null);
    this.mapperProvider = paramProvider;
  }
  
  public static Factory<ManagerConfigParser> create(Provider<ObjectMapper> paramProvider)
  {
    return new ManagerConfigParser_Factory(paramProvider);
  }
  
  public ManagerConfigParser get()
  {
    return new ManagerConfigParser((ObjectMapper)this.mapperProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/configuration/ManagerConfigParser_Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */