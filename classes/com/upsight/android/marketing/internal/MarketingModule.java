package com.upsight.android.marketing.internal;

import com.upsight.android.marketing.internal.billboard.BillboardModule;
import com.upsight.android.marketing.internal.content.ContentModule;
import com.upsight.android.marketing.internal.content.WebViewModule;
import dagger.Module;

@Module(includes={BillboardModule.class, ContentModule.class, WebViewModule.class, BaseMarketingModule.class})
public class MarketingModule {}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/MarketingModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */