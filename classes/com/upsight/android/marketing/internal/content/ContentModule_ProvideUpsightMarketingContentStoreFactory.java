package com.upsight.android.marketing.internal.content;

import com.upsight.android.marketing.UpsightMarketingContentStore;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ContentModule_ProvideUpsightMarketingContentStoreFactory
  implements Factory<UpsightMarketingContentStore>
{
  private final Provider<MarketingContentStoreImpl> implProvider;
  private final ContentModule module;
  
  static
  {
    if (!ContentModule_ProvideUpsightMarketingContentStoreFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public ContentModule_ProvideUpsightMarketingContentStoreFactory(ContentModule paramContentModule, Provider<MarketingContentStoreImpl> paramProvider)
  {
    assert (paramContentModule != null);
    this.module = paramContentModule;
    assert (paramProvider != null);
    this.implProvider = paramProvider;
  }
  
  public static Factory<UpsightMarketingContentStore> create(ContentModule paramContentModule, Provider<MarketingContentStoreImpl> paramProvider)
  {
    return new ContentModule_ProvideUpsightMarketingContentStoreFactory(paramContentModule, paramProvider);
  }
  
  public UpsightMarketingContentStore get()
  {
    UpsightMarketingContentStore localUpsightMarketingContentStore = this.module.provideUpsightMarketingContentStore((MarketingContentStoreImpl)this.implProvider.get());
    if (localUpsightMarketingContentStore == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUpsightMarketingContentStore;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/ContentModule_ProvideUpsightMarketingContentStoreFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */