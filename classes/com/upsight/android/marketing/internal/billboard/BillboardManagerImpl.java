package com.upsight.android.marketing.internal.billboard;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.upsight.android.analytics.internal.session.ApplicationStatus;
import com.upsight.android.analytics.internal.session.ApplicationStatus.State;
import com.upsight.android.marketing.UpsightBillboard.Dimensions;
import com.upsight.android.marketing.UpsightBillboard.Dimensions.LayoutOrientation;
import com.upsight.android.marketing.UpsightBillboard.Handler;
import com.upsight.android.marketing.UpsightBillboard.PresentationStyle;
import com.upsight.android.marketing.UpsightBillboardManager;
import com.upsight.android.marketing.UpsightContentMediator;
import com.upsight.android.marketing.internal.content.MarketingContent;
import com.upsight.android.marketing.internal.content.MarketingContent.ScopedAvailabilityEvent;
import com.upsight.android.marketing.internal.content.MarketingContent.ScopelessAvailabilityEvent;
import com.upsight.android.marketing.internal.content.MarketingContentActions.DestroyEvent;
import com.upsight.android.marketing.internal.content.MarketingContentActions.PurchasesEvent;
import com.upsight.android.marketing.internal.content.MarketingContentActions.RewardsEvent;
import com.upsight.android.marketing.internal.content.MarketingContentModel;
import com.upsight.android.marketing.internal.content.MarketingContentModel.Presentation.DialogLayout;
import com.upsight.android.marketing.internal.content.MarketingContentModel.Presentation.DialogLayout.Dimensions;
import com.upsight.android.marketing.internal.content.MarketingContentStore;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.annotation.Created;
import com.upsight.android.persistence.annotation.Updated;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class BillboardManagerImpl
  implements UpsightBillboardManager
{
  private final MarketingContentStore mContentStore;
  private final Map<String, Billboard> mFilledBillboards = new HashMap();
  private final Map<String, UpsightContentMediator> mMediators = new HashMap();
  private final Map<String, Billboard> mUnfilledBillboards = new HashMap();
  
  BillboardManagerImpl(UpsightDataStore paramUpsightDataStore, MarketingContentStore paramMarketingContentStore, Bus paramBus)
  {
    this.mContentStore = paramMarketingContentStore;
    paramBus.register(this);
    paramUpsightDataStore.subscribe(this);
  }
  
  /**
   * @deprecated
   */
  private void detachBillboard(String paramString)
  {
    try
    {
      Billboard localBillboard = (Billboard)this.mFilledBillboards.get(paramString);
      if (localBillboard != null)
      {
        localBillboard.getHandler().onDetach();
        this.mFilledBillboards.remove(paramString);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private Set<UpsightBillboard.Dimensions> getDimensions(MarketingContent paramMarketingContent)
  {
    HashSet localHashSet = new HashSet();
    MarketingContentModel.Presentation.DialogLayout localDialogLayout = paramMarketingContent.getContentModel().getDialogLayouts();
    if ((localDialogLayout != null) && (localDialogLayout.portrait != null) && (localDialogLayout.portrait.w > 0) && (localDialogLayout.portrait.h > 0)) {
      localHashSet.add(new UpsightBillboard.Dimensions(UpsightBillboard.Dimensions.LayoutOrientation.Portrait, localDialogLayout.portrait.w, localDialogLayout.portrait.h));
    }
    if ((localDialogLayout != null) && (localDialogLayout.landscape != null) && (localDialogLayout.landscape.w > 0) && (localDialogLayout.landscape.h > 0)) {
      localHashSet.add(new UpsightBillboard.Dimensions(UpsightBillboard.Dimensions.LayoutOrientation.Landscape, localDialogLayout.landscape.w, localDialogLayout.landscape.h));
    }
    return localHashSet;
  }
  
  private UpsightBillboard.PresentationStyle getPresentationStyle(MarketingContent paramMarketingContent)
  {
    String str = paramMarketingContent.getContentModel().getPresentationStyle();
    UpsightBillboard.PresentationStyle localPresentationStyle;
    if ("dialog".equals(str)) {
      localPresentationStyle = UpsightBillboard.PresentationStyle.Dialog;
    }
    for (;;)
    {
      return localPresentationStyle;
      if ("fullscreen".equals(str)) {
        localPresentationStyle = UpsightBillboard.PresentationStyle.Fullscreen;
      } else {
        localPresentationStyle = UpsightBillboard.PresentationStyle.None;
      }
    }
  }
  
  private boolean tryAttachBillboard(String paramString, Billboard paramBillboard)
  {
    boolean bool = false;
    MarketingContent localMarketingContent = (MarketingContent)this.mContentStore.get(paramString);
    if ((paramBillboard != null) && (paramBillboard.getMarketingContent() == null) && (localMarketingContent != null) && (localMarketingContent.isAvailable()))
    {
      UpsightContentMediator localUpsightContentMediator = (UpsightContentMediator)this.mMediators.get(localMarketingContent.getContentProvider());
      if ((localUpsightContentMediator != null) && (localUpsightContentMediator.isAvailable()))
      {
        ViewGroup localViewGroup = paramBillboard.getHandler().onAttach(paramBillboard.getScope(), getPresentationStyle(localMarketingContent), getDimensions(localMarketingContent));
        if (localViewGroup != null)
        {
          localMarketingContent.markConsumed();
          localUpsightContentMediator.displayContent(localMarketingContent, localViewGroup);
          paramBillboard.setMarketingContent(localMarketingContent);
          this.mUnfilledBillboards.remove(paramBillboard.getScope());
          this.mFilledBillboards.put(paramString, paramBillboard);
          localMarketingContent.executeActions("content_displayed");
          bool = true;
        }
      }
    }
    return bool;
  }
  
  /**
   * @deprecated
   */
  @Subscribe
  public void handleActionEvent(MarketingContentActions.DestroyEvent paramDestroyEvent)
  {
    try
    {
      detachBillboard(paramDestroyEvent.mId);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  @Subscribe
  public void handleActionEvent(MarketingContentActions.PurchasesEvent paramPurchasesEvent)
  {
    try
    {
      Billboard localBillboard = (Billboard)this.mFilledBillboards.get(paramPurchasesEvent.mId);
      if (localBillboard != null) {
        localBillboard.getHandler().onPurchases(paramPurchasesEvent.mPurchases);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  @Subscribe
  public void handleActionEvent(MarketingContentActions.RewardsEvent paramRewardsEvent)
  {
    try
    {
      Billboard localBillboard = (Billboard)this.mFilledBillboards.get(paramRewardsEvent.mId);
      if (localBillboard != null) {
        localBillboard.getHandler().onRewards(paramRewardsEvent.mRewards);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  @Subscribe
  public void handleAvailabilityEvent(MarketingContent.ScopedAvailabilityEvent paramScopedAvailabilityEvent)
  {
    try
    {
      Iterator localIterator = paramScopedAvailabilityEvent.getScopes().iterator();
      boolean bool;
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        String str = (String)localIterator.next();
        bool = tryAttachBillboard(paramScopedAvailabilityEvent.getId(), (Billboard)this.mUnfilledBillboards.get(str));
      } while (!bool);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  @Subscribe
  public void handleAvailabilityEvent(MarketingContent.ScopelessAvailabilityEvent paramScopelessAvailabilityEvent)
  {
    try
    {
      if (!TextUtils.isEmpty(paramScopelessAvailabilityEvent.getParentId()))
      {
        Billboard localBillboard = (Billboard)this.mFilledBillboards.get(paramScopelessAvailabilityEvent.getParentId());
        if (localBillboard != null)
        {
          MarketingContent localMarketingContent1 = localBillboard.getMarketingContent();
          MarketingContent localMarketingContent2 = (MarketingContent)this.mContentStore.get(paramScopelessAvailabilityEvent.getId());
          if ((localMarketingContent1 != null) && (localMarketingContent2 != null) && (localMarketingContent2.isAvailable()))
          {
            UpsightContentMediator localUpsightContentMediator1 = (UpsightContentMediator)this.mMediators.get(localMarketingContent1.getContentProvider());
            UpsightContentMediator localUpsightContentMediator2 = (UpsightContentMediator)this.mMediators.get(localMarketingContent2.getContentProvider());
            if ((localUpsightContentMediator1 != null) && (localUpsightContentMediator1.isAvailable()) && (localUpsightContentMediator2 != null) && (localUpsightContentMediator2.isAvailable()))
            {
              localMarketingContent2.markConsumed();
              localBillboard.getHandler().onNextView();
              ViewGroup localViewGroup = (ViewGroup)localBillboard.getMarketingContent().getContentView().getParent();
              localUpsightContentMediator1.hideContent(localMarketingContent1, localViewGroup);
              localUpsightContentMediator2.displayContent(localMarketingContent2, localViewGroup);
              localBillboard.setMarketingContent(localMarketingContent2);
              this.mFilledBillboards.remove(paramScopelessAvailabilityEvent.getParentId());
              this.mFilledBillboards.put(paramScopelessAvailabilityEvent.getId(), localBillboard);
              localMarketingContent2.executeActions("content_displayed");
            }
          }
        }
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  @Created
  @Updated
  public void onApplicationStatus(ApplicationStatus paramApplicationStatus)
  {
    if (paramApplicationStatus.getState() == ApplicationStatus.State.BACKGROUND)
    {
      Iterator localIterator = this.mFilledBillboards.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        MarketingContent localMarketingContent = (MarketingContent)this.mContentStore.get(str);
        if (localMarketingContent != null) {
          localMarketingContent.executeActions("app_backgrounded");
        }
      }
    }
  }
  
  /**
   * @deprecated
   */
  public boolean registerBillboard(Billboard paramBillboard)
  {
    boolean bool1 = false;
    if (paramBillboard != null) {}
    try
    {
      String str = paramBillboard.getScope();
      if ((!TextUtils.isEmpty(str)) && (paramBillboard.getHandler() != null) && (this.mUnfilledBillboards.get(str) == null))
      {
        bool1 = true;
        this.mUnfilledBillboards.put(str, paramBillboard);
        Iterator localIterator = this.mContentStore.getIdsForScope(str).iterator();
        boolean bool2;
        do
        {
          if (!localIterator.hasNext()) {
            break;
          }
          bool2 = tryAttachBillboard((String)localIterator.next(), paramBillboard);
        } while (!bool2);
      }
      return bool1;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean registerContentMediator(UpsightContentMediator paramUpsightContentMediator)
  {
    boolean bool = false;
    if ((paramUpsightContentMediator != null) && (!TextUtils.isEmpty(paramUpsightContentMediator.getContentProvider()))) {
      if (this.mMediators.put(paramUpsightContentMediator.getContentProvider(), paramUpsightContentMediator) != paramUpsightContentMediator) {
        break label42;
      }
    }
    label42:
    for (bool = true;; bool = false) {
      return bool;
    }
  }
  
  /* Error */
  /**
   * @deprecated
   */
  public boolean unregisterBillboard(Billboard paramBillboard)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual 169	com/upsight/android/marketing/internal/billboard/Billboard:getScope	()Ljava/lang/String;
    //   6: astore_3
    //   7: aload_0
    //   8: getfield 24	com/upsight/android/marketing/internal/billboard/BillboardManagerImpl:mUnfilledBillboards	Ljava/util/Map;
    //   11: aload_3
    //   12: invokeinterface 64 2 0
    //   17: astore 4
    //   19: aload 4
    //   21: ifnull +11 -> 32
    //   24: iconst_1
    //   25: istore 5
    //   27: aload_0
    //   28: monitorexit
    //   29: iload 5
    //   31: ireturn
    //   32: iconst_0
    //   33: istore 5
    //   35: goto -8 -> 27
    //   38: astore_2
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_2
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	this	BillboardManagerImpl
    //   0	43	1	paramBillboard	Billboard
    //   38	4	2	localObject1	Object
    //   6	6	3	str	String
    //   17	3	4	localObject2	Object
    //   25	9	5	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	19	38	finally
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/billboard/BillboardManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */