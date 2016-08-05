package com.upsight.android.analytics.internal.provider;

import com.upsight.android.analytics.provider.UpsightOptOutStatus;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ProviderModule_ProvidesOptOutStatusFactory
  implements Factory<UpsightOptOutStatus>
{
  private final ProviderModule module;
  private final Provider<OptOutStatus> optOutStatusProvider;
  
  static
  {
    if (!ProviderModule_ProvidesOptOutStatusFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ProviderModule_ProvidesOptOutStatusFactory(ProviderModule paramProviderModule, Provider<OptOutStatus> paramProvider)
  {
    assert (paramProviderModule != null);
    this.module = paramProviderModule;
    assert (paramProvider != null);
    this.optOutStatusProvider = paramProvider;
  }
  
  public static Factory<UpsightOptOutStatus> create(ProviderModule paramProviderModule, Provider<OptOutStatus> paramProvider)
  {
    return new ProviderModule_ProvidesOptOutStatusFactory(paramProviderModule, paramProvider);
  }
  
  public UpsightOptOutStatus get()
  {
    UpsightOptOutStatus localUpsightOptOutStatus = this.module.providesOptOutStatus((OptOutStatus)this.optOutStatusProvider.get());
    if (localUpsightOptOutStatus == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUpsightOptOutStatus;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/provider/ProviderModule_ProvidesOptOutStatusFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */