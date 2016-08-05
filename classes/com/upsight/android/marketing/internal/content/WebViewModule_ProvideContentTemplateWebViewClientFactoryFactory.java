package com.upsight.android.marketing.internal.content;

import com.upsight.android.UpsightContext;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class WebViewModule_ProvideContentTemplateWebViewClientFactoryFactory
  implements Factory<ContentTemplateWebViewClientFactory>
{
  private final WebViewModule module;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!WebViewModule_ProvideContentTemplateWebViewClientFactoryFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public WebViewModule_ProvideContentTemplateWebViewClientFactoryFactory(WebViewModule paramWebViewModule, Provider<UpsightContext> paramProvider)
  {
    assert (paramWebViewModule != null);
    this.module = paramWebViewModule;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
  }
  
  public static Factory<ContentTemplateWebViewClientFactory> create(WebViewModule paramWebViewModule, Provider<UpsightContext> paramProvider)
  {
    return new WebViewModule_ProvideContentTemplateWebViewClientFactoryFactory(paramWebViewModule, paramProvider);
  }
  
  public ContentTemplateWebViewClientFactory get()
  {
    ContentTemplateWebViewClientFactory localContentTemplateWebViewClientFactory = this.module.provideContentTemplateWebViewClientFactory((UpsightContext)this.upsightProvider.get());
    if (localContentTemplateWebViewClientFactory == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localContentTemplateWebViewClientFactory;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/WebViewModule_ProvideContentTemplateWebViewClientFactoryFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */