package com.upsight.android.marketing.internal.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.otto.Bus;
import com.upsight.android.UpsightAnalyticsExtension;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightCoreComponent;
import com.upsight.android.analytics.UpsightAnalyticsComponent;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.marketing.UpsightMarketingContentStore;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.Scheduler;

@Module
public final class ContentModule
{
  @Provides
  @Singleton
  DefaultContentMediator provideDefaultContentMediator()
  {
    return new DefaultContentMediator();
  }
  
  @Provides
  @Singleton
  MarketingContentFactory provideMarketingContentFactory(UpsightContext paramUpsightContext, @Named("main") Scheduler paramScheduler, MarketingContentStore paramMarketingContentStore, ContentTemplateWebViewClientFactory paramContentTemplateWebViewClientFactory)
  {
    UpsightCoreComponent localUpsightCoreComponent = paramUpsightContext.getCoreComponent();
    Bus localBus = localUpsightCoreComponent.bus();
    ObjectMapper localObjectMapper = localUpsightCoreComponent.objectMapper();
    UpsightLogger localUpsightLogger = paramUpsightContext.getLogger();
    return new MarketingContentFactory(new MarketingContentActions.MarketingContentActionContext(paramUpsightContext, localBus, localObjectMapper, ((UpsightAnalyticsComponent)((UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics")).getComponent()).clock(), paramScheduler.createWorker(), localUpsightLogger, paramMarketingContentStore, paramContentTemplateWebViewClientFactory));
  }
  
  @Provides
  @Singleton
  MarketingContentStore provideMarketingContentStore(MarketingContentStoreImpl paramMarketingContentStoreImpl)
  {
    return paramMarketingContentStoreImpl;
  }
  
  @Provides
  @Singleton
  MarketingContentStoreImpl provideMarketingContentStoreImpl(UpsightContext paramUpsightContext)
  {
    return new MarketingContentStoreImpl(paramUpsightContext.getCoreComponent().bus(), ((UpsightAnalyticsComponent)((UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics")).getComponent()).clock());
  }
  
  @Provides
  @Singleton
  UpsightMarketingContentStore provideUpsightMarketingContentStore(MarketingContentStoreImpl paramMarketingContentStoreImpl)
  {
    return paramMarketingContentStoreImpl;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/ContentModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */