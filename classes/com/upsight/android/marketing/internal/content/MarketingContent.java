package com.upsight.android.marketing.internal.content;

import android.view.View;
import com.squareup.otto.Bus;
import com.upsight.android.analytics.internal.action.ActionMap;
import com.upsight.android.analytics.internal.action.Actionable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketingContent
  extends Actionable
{
  public static final String TRIGGER_APP_BACKGROUNDED = "app_backgrounded";
  public static final String TRIGGER_CONTENT_DISMISSED = "content_dismissed";
  public static final String TRIGGER_CONTENT_DISPLAYED = "content_displayed";
  public static final String TRIGGER_CONTENT_RECEIVED = "content_received";
  public static final String UPSIGHT_CONTENT_PROVIDER = "upsight";
  private AvailabilityEvent mAvailabilityEvent;
  private MarketingContentModel mContentModel = null;
  private View mContentView = null;
  private Map<String, String> mExtras = new HashMap();
  private boolean mIsConsumed = false;
  private boolean mIsLoaded = false;
  
  private MarketingContent(String paramString, ActionMap<MarketingContent, MarketingContentActions.MarketingContentActionContext> paramActionMap)
  {
    super(paramString, paramActionMap);
  }
  
  public static MarketingContent create(String paramString, ActionMap<MarketingContent, MarketingContentActions.MarketingContentActionContext> paramActionMap)
  {
    return new MarketingContent(paramString, paramActionMap);
  }
  
  private void notifyAvailability(Bus paramBus)
  {
    if (isAvailable()) {
      paramBus.post(this.mAvailabilityEvent);
    }
  }
  
  public MarketingContentModel getContentModel()
  {
    return this.mContentModel;
  }
  
  public String getContentProvider()
  {
    return "upsight";
  }
  
  public View getContentView()
  {
    return this.mContentView;
  }
  
  public String getExtra(String paramString)
  {
    return (String)this.mExtras.get(paramString);
  }
  
  public boolean isAvailable()
  {
    if ((isLoaded()) && (this.mAvailabilityEvent != null) && (!this.mIsConsumed)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  boolean isLoaded()
  {
    if ((this.mContentModel != null) && (this.mContentView != null) && (this.mIsLoaded)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void markConsumed()
  {
    this.mIsConsumed = true;
  }
  
  public void markLoaded(Bus paramBus)
  {
    this.mIsLoaded = true;
    paramBus.post(new ContentLoadedEvent(getId(), null));
    notifyAvailability(paramBus);
  }
  
  public void markPresentable(AvailabilityEvent paramAvailabilityEvent, Bus paramBus)
  {
    this.mAvailabilityEvent = paramAvailabilityEvent;
    notifyAvailability(paramBus);
  }
  
  public void putExtra(String paramString1, String paramString2)
  {
    this.mExtras.put(paramString1, paramString2);
  }
  
  public void setContentModel(MarketingContentModel paramMarketingContentModel)
  {
    this.mContentModel = paramMarketingContentModel;
  }
  
  public void setContentView(View paramView)
  {
    this.mContentView = paramView;
  }
  
  public static abstract class AvailabilityEvent
  {
    private final String mId;
    
    private AvailabilityEvent(String paramString)
    {
      this.mId = paramString;
    }
    
    public String getId()
    {
      return this.mId;
    }
  }
  
  public static class ScopelessAvailabilityEvent
    extends MarketingContent.AvailabilityEvent
  {
    private final String mParentId;
    
    public ScopelessAvailabilityEvent(String paramString1, String paramString2)
    {
      super(null);
      this.mParentId = paramString2;
    }
    
    public String getParentId()
    {
      return this.mParentId;
    }
  }
  
  public static class ScopedAvailabilityEvent
    extends MarketingContent.AvailabilityEvent
  {
    private final String[] mScopes;
    
    public ScopedAvailabilityEvent(String paramString, String[] paramArrayOfString)
    {
      super(null);
      this.mScopes = paramArrayOfString;
    }
    
    public List<String> getScopes()
    {
      return Arrays.asList(this.mScopes);
    }
  }
  
  public static class ContentLoadedEvent
  {
    private final String mId;
    
    private ContentLoadedEvent(String paramString)
    {
      this.mId = paramString;
    }
    
    public String getId()
    {
      return this.mId;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/MarketingContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */