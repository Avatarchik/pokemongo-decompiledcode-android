package com.upsight.android.analytics.internal.configuration;

import com.upsight.android.UpsightContext;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ConfigurationModule_ProvideConfigurationManagerFactory
  implements Factory<ConfigurationManager>
{
  private final Provider<ManagerConfigParser> managerConfigParserProvider;
  private final ConfigurationModule module;
  private final Provider<ConfigurationResponseParser> responseParserProvider;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!ConfigurationModule_ProvideConfigurationManagerFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ConfigurationModule_ProvideConfigurationManagerFactory(ConfigurationModule paramConfigurationModule, Provider<UpsightContext> paramProvider, Provider<ConfigurationResponseParser> paramProvider1, Provider<ManagerConfigParser> paramProvider2)
  {
    assert (paramConfigurationModule != null);
    this.module = paramConfigurationModule;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
    assert (paramProvider1 != null);
    this.responseParserProvider = paramProvider1;
    assert (paramProvider2 != null);
    this.managerConfigParserProvider = paramProvider2;
  }
  
  public static Factory<ConfigurationManager> create(ConfigurationModule paramConfigurationModule, Provider<UpsightContext> paramProvider, Provider<ConfigurationResponseParser> paramProvider1, Provider<ManagerConfigParser> paramProvider2)
  {
    return new ConfigurationModule_ProvideConfigurationManagerFactory(paramConfigurationModule, paramProvider, paramProvider1, paramProvider2);
  }
  
  public ConfigurationManager get()
  {
    ConfigurationManager localConfigurationManager = this.module.provideConfigurationManager((UpsightContext)this.upsightProvider.get(), (ConfigurationResponseParser)this.responseParserProvider.get(), (ManagerConfigParser)this.managerConfigParserProvider.get());
    if (localConfigurationManager == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localConfigurationManager;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/configuration/ConfigurationModule_ProvideConfigurationManagerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */