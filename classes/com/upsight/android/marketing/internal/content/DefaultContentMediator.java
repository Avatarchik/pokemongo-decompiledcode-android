package com.upsight.android.marketing.internal.content;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.upsight.android.marketing.UpsightContentMediator;

public final class DefaultContentMediator
  implements UpsightContentMediator
{
  public void displayContent(MarketingContent paramMarketingContent, ViewGroup paramViewGroup)
  {
    ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, -1);
    paramViewGroup.addView(paramMarketingContent.getContentView(), localLayoutParams);
  }
  
  public String getContentProvider()
  {
    return "upsight";
  }
  
  public void hideContent(MarketingContent paramMarketingContent, ViewGroup paramViewGroup)
  {
    paramViewGroup.removeAllViews();
  }
  
  public boolean isAvailable()
  {
    return true;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/DefaultContentMediator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */