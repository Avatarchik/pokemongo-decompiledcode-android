package com.upsight.android.marketing.internal.billboard;

import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightCoreComponent;
import com.upsight.android.marketing.UpsightBillboardManager;
import com.upsight.android.marketing.internal.content.MarketingContentStore;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public final class BillboardModule
{
  @Provides
  @Singleton
  UpsightBillboardManager provideBillboardManager(UpsightContext paramUpsightContext, MarketingContentStore paramMarketingContentStore)
  {
    UpsightCoreComponent localUpsightCoreComponent = paramUpsightContext.getCoreComponent();
    return new BillboardManagerImpl(paramUpsightContext.getDataStore(), paramMarketingContentStore, localUpsightCoreComponent.bus());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/billboard/BillboardModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */