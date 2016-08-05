package com.upsight.android.analytics.internal;

import android.app.Service;
import com.upsight.android.analytics.internal.configuration.ConfigurationManager;
import com.upsight.android.analytics.internal.dispatcher.Dispatcher;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DispatcherService_MembersInjector
  implements MembersInjector<DispatcherService>
{
  private final Provider<ConfigurationManager> mConfigurationManagerProvider;
  private final Provider<Dispatcher> mDispatcherProvider;
  private final MembersInjector<Service> supertypeInjector;
  
  static
  {
    if (!DispatcherService_MembersInjector.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public DispatcherService_MembersInjector(MembersInjector<Service> paramMembersInjector, Provider<ConfigurationManager> paramProvider, Provider<Dispatcher> paramProvider1)
  {
    assert (paramMembersInjector != null);
    this.supertypeInjector = paramMembersInjector;
    assert (paramProvider != null);
    this.mConfigurationManagerProvider = paramProvider;
    assert (paramProvider1 != null);
    this.mDispatcherProvider = paramProvider1;
  }
  
  public static MembersInjector<DispatcherService> create(MembersInjector<Service> paramMembersInjector, Provider<ConfigurationManager> paramProvider, Provider<Dispatcher> paramProvider1)
  {
    return new DispatcherService_MembersInjector(paramMembersInjector, paramProvider, paramProvider1);
  }
  
  public void injectMembers(DispatcherService paramDispatcherService)
  {
    if (paramDispatcherService == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    this.supertypeInjector.injectMembers(paramDispatcherService);
    paramDispatcherService.mConfigurationManager = ((ConfigurationManager)this.mConfigurationManagerProvider.get());
    paramDispatcherService.mDispatcher = ((Dispatcher)this.mDispatcherProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/DispatcherService_MembersInjector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */