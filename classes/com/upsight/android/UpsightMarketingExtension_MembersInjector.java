package com.upsight.android;

import com.upsight.android.marketing.UpsightBillboardManager;
import com.upsight.android.marketing.UpsightMarketingApi;
import com.upsight.android.marketing.UpsightMarketingComponent;
import com.upsight.android.marketing.internal.content.DefaultContentMediator;
import com.upsight.android.marketing.internal.content.MarketingContentFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class UpsightMarketingExtension_MembersInjector
  implements MembersInjector<UpsightMarketingExtension>
{
  private final Provider<UpsightBillboardManager> mBillboardManagerProvider;
  private final Provider<DefaultContentMediator> mDefaultContentMediatorProvider;
  private final Provider<MarketingContentFactory> mMarketingContentFactoryProvider;
  private final Provider<UpsightMarketingApi> mMarketingProvider;
  private final MembersInjector<UpsightExtension<UpsightMarketingComponent, UpsightMarketingApi>> supertypeInjector;
  
  static
  {
    if (!UpsightMarketingExtension_MembersInjector.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public UpsightMarketingExtension_MembersInjector(MembersInjector<UpsightExtension<UpsightMarketingComponent, UpsightMarketingApi>> paramMembersInjector, Provider<UpsightMarketingApi> paramProvider, Provider<MarketingContentFactory> paramProvider1, Provider<UpsightBillboardManager> paramProvider2, Provider<DefaultContentMediator> paramProvider3)
  {
    assert (paramMembersInjector != null);
    this.supertypeInjector = paramMembersInjector;
    assert (paramProvider != null);
    this.mMarketingProvider = paramProvider;
    assert (paramProvider1 != null);
    this.mMarketingContentFactoryProvider = paramProvider1;
    assert (paramProvider2 != null);
    this.mBillboardManagerProvider = paramProvider2;
    assert (paramProvider3 != null);
    this.mDefaultContentMediatorProvider = paramProvider3;
  }
  
  public static MembersInjector<UpsightMarketingExtension> create(MembersInjector<UpsightExtension<UpsightMarketingComponent, UpsightMarketingApi>> paramMembersInjector, Provider<UpsightMarketingApi> paramProvider, Provider<MarketingContentFactory> paramProvider1, Provider<UpsightBillboardManager> paramProvider2, Provider<DefaultContentMediator> paramProvider3)
  {
    return new UpsightMarketingExtension_MembersInjector(paramMembersInjector, paramProvider, paramProvider1, paramProvider2, paramProvider3);
  }
  
  public void injectMembers(UpsightMarketingExtension paramUpsightMarketingExtension)
  {
    if (paramUpsightMarketingExtension == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    this.supertypeInjector.injectMembers(paramUpsightMarketingExtension);
    paramUpsightMarketingExtension.mMarketing = ((UpsightMarketingApi)this.mMarketingProvider.get());
    paramUpsightMarketingExtension.mMarketingContentFactory = ((MarketingContentFactory)this.mMarketingContentFactoryProvider.get());
    paramUpsightMarketingExtension.mBillboardManager = ((UpsightBillboardManager)this.mBillboardManagerProvider.get());
    paramUpsightMarketingExtension.mDefaultContentMediator = ((DefaultContentMediator)this.mDefaultContentMediatorProvider.get());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/UpsightMarketingExtension_MembersInjector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */