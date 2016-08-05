package com.upsight.android.marketing.internal.content;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.otto.Bus;
import com.upsight.android.logger.UpsightLogger;
import java.io.IOException;
import java.util.Iterator;

class ContentTemplateWebViewClient
  extends WebViewClient
{
  private static final String DISPATCH_CALLBACK = "javascript:PlayHaven.nativeAPI.callback(\"%s\", %s, %s, %s)";
  private static final String DISPATCH_CALLBACK_PROTOCOL = "javascript:window.PlayHavenDispatchProtocolVersion=7";
  private static final String DISPATCH_LOAD_CONTEXT = "ph://loadContext";
  private static final String DISPATCH_PARAM_KEY_CALLBACK_ID = "callback";
  private static final String DISPATCH_PARAM_KEY_CONTEXT = "context";
  private static final String DISPATCH_PARAM_KEY_TRIGGER = "trigger";
  private static final String DISPATCH_PARAM_KEY_VIEW_DATA = "view_data";
  private static final String DISPATCH_SCHEME = "ph://";
  private final Bus mBus;
  private boolean mIsTemplateLoaded = false;
  private final UpsightLogger mLogger;
  private final ObjectMapper mMapper;
  private final MarketingContent mMarketingContent;
  
  public ContentTemplateWebViewClient(MarketingContent paramMarketingContent, Bus paramBus, ObjectMapper paramObjectMapper, UpsightLogger paramUpsightLogger)
  {
    this.mMarketingContent = paramMarketingContent;
    this.mBus = paramBus;
    this.mMapper = paramObjectMapper;
    this.mLogger = paramUpsightLogger;
  }
  
  private boolean handleActionDispatch(String paramString)
  {
    bool = false;
    if ((paramString != null) && (paramString.startsWith("ph://")))
    {
      bool = true;
      String str1 = Uri.parse(paramString).getQueryParameter("context");
      if (!TextUtils.isEmpty(str1))
      {
        JsonNode localJsonNode1 = null;
        try
        {
          localJsonNode1 = this.mMapper.readTree(str1);
          if (localJsonNode1.hasNonNull("trigger"))
          {
            JsonNode localJsonNode3 = localJsonNode1.path("trigger");
            if (localJsonNode3.isTextual()) {
              this.mMarketingContent.executeActions(localJsonNode3.asText());
            }
          }
          else if (localJsonNode1.hasNonNull("view_data"))
          {
            JsonNode localJsonNode2 = localJsonNode1.path("view_data");
            Iterator localIterator = localJsonNode2.fieldNames();
            while (localIterator.hasNext())
            {
              String str2 = (String)localIterator.next();
              this.mMarketingContent.putExtra(str2, localJsonNode2.path(str2).toString());
            }
          }
          return bool;
        }
        catch (IOException localIOException)
        {
          this.mLogger.e(getClass().getSimpleName(), localIOException, "Failed to parse contextNode=" + localJsonNode1, new Object[0]);
        }
      }
    }
  }
  
  @TargetApi(19)
  private boolean handleLoadContextDispatch(WebView paramWebView, String paramString)
  {
    boolean bool = false;
    String str2;
    if ((paramString != null) && (paramString.startsWith("ph://loadContext")))
    {
      bool = true;
      String str1 = Uri.parse(paramString).getQueryParameter("callback");
      JsonNode localJsonNode = this.mMarketingContent.getContentModel().getContext();
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = str1;
      arrayOfObject[1] = localJsonNode;
      arrayOfObject[2] = null;
      arrayOfObject[3] = null;
      str2 = String.format("javascript:PlayHaven.nativeAPI.callback(\"%s\", %s, %s, %s)", arrayOfObject);
      paramWebView.loadUrl("javascript:window.PlayHavenDispatchProtocolVersion=7");
      if (Build.VERSION.SDK_INT < 19) {
        break label100;
      }
      paramWebView.evaluateJavascript(str2, null);
    }
    for (;;)
    {
      return bool;
      label100:
      paramWebView.loadUrl(str2);
    }
  }
  
  public void onPageFinished(WebView paramWebView, String paramString)
  {
    super.onPageFinished(paramWebView, paramString);
    if (!this.mIsTemplateLoaded)
    {
      this.mIsTemplateLoaded = true;
      this.mMarketingContent.markLoaded(this.mBus);
    }
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
  {
    if ((handleLoadContextDispatch(paramWebView, paramString)) || (handleActionDispatch(paramString)) || (super.shouldOverrideUrlLoading(paramWebView, paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/ContentTemplateWebViewClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */