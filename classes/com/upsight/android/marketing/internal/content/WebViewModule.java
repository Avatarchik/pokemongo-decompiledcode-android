package com.upsight.android.marketing.internal.content;

import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightCoreComponent;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public final class WebViewModule
{
  @Provides
  @Singleton
  ContentTemplateWebViewClientFactory provideContentTemplateWebViewClientFactory(UpsightContext paramUpsightContext)
  {
    UpsightCoreComponent localUpsightCoreComponent = paramUpsightContext.getCoreComponent();
    return new ContentTemplateWebViewClientFactory(localUpsightCoreComponent.bus(), localUpsightCoreComponent.objectMapper(), paramUpsightContext.getLogger());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/WebViewModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */