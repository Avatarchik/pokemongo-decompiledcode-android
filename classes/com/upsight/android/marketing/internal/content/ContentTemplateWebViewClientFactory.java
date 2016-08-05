package com.upsight.android.marketing.internal.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.otto.Bus;
import com.upsight.android.logger.UpsightLogger;

public class ContentTemplateWebViewClientFactory
{
  protected final Bus mBus;
  protected final UpsightLogger mLogger;
  protected final ObjectMapper mMapper;
  
  public ContentTemplateWebViewClientFactory(Bus paramBus, ObjectMapper paramObjectMapper, UpsightLogger paramUpsightLogger)
  {
    this.mBus = paramBus;
    this.mMapper = paramObjectMapper;
    this.mLogger = paramUpsightLogger;
  }
  
  public ContentTemplateWebViewClient create(MarketingContent paramMarketingContent)
  {
    return new ContentTemplateWebViewClient(paramMarketingContent, this.mBus, this.mMapper, this.mLogger);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/ContentTemplateWebViewClientFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */