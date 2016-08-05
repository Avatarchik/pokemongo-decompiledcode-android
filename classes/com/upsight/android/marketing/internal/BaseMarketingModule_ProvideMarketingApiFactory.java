package com.upsight.android.marketing.internal;

import com.upsight.android.marketing.UpsightBillboardManager;
import com.upsight.android.marketing.UpsightMarketingApi;
import com.upsight.android.marketing.UpsightMarketingContentStore;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BaseMarketingModule_ProvideMarketingApiFactory
  implements Factory<UpsightMarketingApi>
{
  private final Provider<UpsightBillboardManager> billboardManagerProvider;
  private final Provider<UpsightMarketingContentStore> marketingContentStoreProvider;
  private final BaseMarketingModule module;
  
  static
  {
    if (!BaseMarketingModule_ProvideMarketingApiFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public BaseMarketingModule_ProvideMarketingApiFactory(BaseMarketingModule paramBaseMarketingModule, Provider<UpsightBillboardManager> paramProvider, Provider<UpsightMarketingContentStore> paramProvider1)
  {
    assert (paramBaseMarketingModule != null);
    this.module = paramBaseMarketingModule;
    assert (paramProvider != null);
    this.billboardManagerProvider = paramProvider;
    assert (paramProvider1 != null);
    this.marketingContentStoreProvider = paramProvider1;
  }
  
  public static Factory<UpsightMarketingApi> create(BaseMarketingModule paramBaseMarketingModule, Provider<UpsightBillboardManager> paramProvider, Provider<UpsightMarketingContentStore> paramProvider1)
  {
    return new BaseMarketingModule_ProvideMarketingApiFactory(paramBaseMarketingModule, paramProvider, paramProvider1);
  }
  
  public UpsightMarketingApi get()
  {
    UpsightMarketingApi localUpsightMarketingApi = this.module.provideMarketingApi((UpsightBillboardManager)this.billboardManagerProvider.get(), (UpsightMarketingContentStore)this.marketingContentStoreProvider.get());
    if (localUpsightMarketingApi == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUpsightMarketingApi;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/BaseMarketingModule_ProvideMarketingApiFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */