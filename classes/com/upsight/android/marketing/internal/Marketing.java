package com.upsight.android.marketing.internal;

import com.upsight.android.marketing.UpsightBillboardManager;
import com.upsight.android.marketing.UpsightContentMediator;
import com.upsight.android.marketing.UpsightMarketingApi;
import com.upsight.android.marketing.UpsightMarketingContentStore;
import com.upsight.android.marketing.internal.billboard.Billboard;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class Marketing
  implements UpsightMarketingApi
{
  private UpsightBillboardManager mBillboardManager;
  private UpsightMarketingContentStore mMarketingContentStore;
  
  @Inject
  public Marketing(UpsightBillboardManager paramUpsightBillboardManager, UpsightMarketingContentStore paramUpsightMarketingContentStore)
  {
    this.mBillboardManager = paramUpsightBillboardManager;
    this.mMarketingContentStore = paramUpsightMarketingContentStore;
  }
  
  public boolean isContentReady(String paramString)
  {
    return this.mMarketingContentStore.isContentReady(paramString);
  }
  
  public boolean registerBillboard(Billboard paramBillboard)
  {
    return this.mBillboardManager.registerBillboard(paramBillboard);
  }
  
  public boolean registerContentMediator(UpsightContentMediator paramUpsightContentMediator)
  {
    return this.mBillboardManager.registerContentMediator(paramUpsightContentMediator);
  }
  
  public boolean unregisterBillboard(Billboard paramBillboard)
  {
    return this.mBillboardManager.unregisterBillboard(paramBillboard);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/Marketing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */