package com.upsight.android.marketing.internal;

import com.upsight.android.marketing.UpsightBillboardManager;
import com.upsight.android.marketing.UpsightMarketingContentStore;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class Marketing_Factory
  implements Factory<Marketing>
{
  private final Provider<UpsightBillboardManager> billboardManagerProvider;
  private final Provider<UpsightMarketingContentStore> marketingContentStoreProvider;
  
  static
  {
    if (!Marketing_Factory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public Marketing_Factory(Provider<UpsightBillboardManager> paramProvider, Provider<UpsightMarketingContentStore> paramProvider1)
  {
    assert (paramProvider != null);
    this.billboardManagerProvider = paramProvider;
    assert (paramProvider1 != null);
    this.marketingContentStoreProvider = paramProvider1;
  }
  
  public static Factory<Marketing> create(Provider<UpsightBillboardManager> paramProvider, Provider<UpsightMarketingContentStore> paramProvider1)
  {
    return new Marketing_Factory(paramProvider, paramProvider1);
  }
  
  public Marketing get()
  {
    return new Marketing((UpsightBillboardManager)this.billboardManagerProvider.get(), (UpsightMarketingContentStore)this.marketingContentStoreProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/Marketing_Factory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */