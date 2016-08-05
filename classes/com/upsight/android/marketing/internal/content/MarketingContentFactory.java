package com.upsight.android.marketing.internal.content;

import android.text.TextUtils;
import com.upsight.android.analytics.internal.action.ActionMap;
import com.upsight.android.analytics.internal.action.ActionMapResponse;

public final class MarketingContentFactory
{
  private static final MarketingContentActions.MarketingContentActionFactory sMarketingContentActionFactory = new MarketingContentActions.MarketingContentActionFactory();
  private MarketingContentActions.MarketingContentActionContext mActionContext;
  
  public MarketingContentFactory(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext)
  {
    this.mActionContext = paramMarketingContentActionContext;
  }
  
  public MarketingContent create(ActionMapResponse paramActionMapResponse)
  {
    MarketingContent localMarketingContent = null;
    String str = paramActionMapResponse.getActionMapId();
    if ((!TextUtils.isEmpty(str)) && ("marketing_content_factory".equals(paramActionMapResponse.getActionFactory()))) {
      localMarketingContent = MarketingContent.create(str, new ActionMap(sMarketingContentActionFactory, this.mActionContext, paramActionMapResponse.getActionMap()));
    }
    return localMarketingContent;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/MarketingContentFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */