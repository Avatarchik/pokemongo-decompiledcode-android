package com.upsight.android.marketing;

import android.view.ViewGroup;
import com.upsight.android.marketing.internal.content.MarketingContent;

public abstract interface UpsightContentMediator
{
  public abstract void displayContent(MarketingContent paramMarketingContent, ViewGroup paramViewGroup);
  
  public abstract String getContentProvider();
  
  public abstract void hideContent(MarketingContent paramMarketingContent, ViewGroup paramViewGroup);
  
  public abstract boolean isAvailable();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/UpsightContentMediator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */