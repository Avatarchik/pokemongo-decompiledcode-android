package com.upsight.android.marketing.internal.billboard;

import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightMarketingExtension;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.marketing.UpsightBillboard;
import com.upsight.android.marketing.UpsightBillboard.Handler;
import com.upsight.android.marketing.UpsightBillboardManager;
import com.upsight.android.marketing.UpsightMarketingApi;
import com.upsight.android.marketing.internal.content.MarketingContent;

public class Billboard
  extends UpsightBillboard
{
  private UpsightBillboardManager mBillboardManager;
  private MarketingContent mContent = null;
  protected final UpsightBillboard.Handler mHandler;
  protected final String mScope;
  
  public Billboard(String paramString, UpsightBillboard.Handler paramHandler)
  {
    this.mScope = paramString;
    this.mHandler = paramHandler;
  }
  
  public final void destroy()
  {
    UpsightBillboardManager localUpsightBillboardManager = this.mBillboardManager;
    if (localUpsightBillboardManager != null)
    {
      localUpsightBillboardManager.unregisterBillboard(this);
      this.mBillboardManager = null;
    }
  }
  
  UpsightBillboard.Handler getHandler()
  {
    return this.mHandler;
  }
  
  MarketingContent getMarketingContent()
  {
    return this.mContent;
  }
  
  String getScope()
  {
    return this.mScope;
  }
  
  void setMarketingContent(MarketingContent paramMarketingContent)
  {
    this.mContent = paramMarketingContent;
  }
  
  public final UpsightBillboard setUp(UpsightContext paramUpsightContext)
    throws IllegalStateException
  {
    UpsightMarketingApi localUpsightMarketingApi = null;
    UpsightMarketingExtension localUpsightMarketingExtension = (UpsightMarketingExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.marketing");
    if (localUpsightMarketingExtension != null) {
      localUpsightMarketingApi = localUpsightMarketingExtension.getApi();
    }
    while (localUpsightMarketingApi != null)
    {
      this.mBillboardManager = localUpsightMarketingApi;
      if (this.mBillboardManager.registerBillboard(this)) {
        break;
      }
      String str = UpsightBillboard.class.getSimpleName();
      throw new IllegalStateException("An active " + str + " with the same scope already exists. A billboard remains active until either a content view is attached, or " + str + "#destroy() is called.");
      paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.marketing must be registered in your Android Manifest", new Object[0]);
    }
    return this;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/billboard/Billboard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */