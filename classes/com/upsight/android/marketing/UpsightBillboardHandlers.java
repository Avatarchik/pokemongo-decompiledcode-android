package com.upsight.android.marketing;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.ViewGroup;
import com.upsight.android.marketing.internal.billboard.BillboardFragment;
import java.util.List;
import java.util.Set;

public final class UpsightBillboardHandlers
{
  private static final int STYLE_DIALOG = R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_UpsightDialog;
  private static final int STYLE_FULLSCREEN = 16974122;
  
  public static UpsightBillboard.Handler forDefault(Activity paramActivity)
  {
    return new DefaultHandler(paramActivity);
  }
  
  public static UpsightBillboard.Handler forDialog(Activity paramActivity)
  {
    return new DialogHandler(paramActivity);
  }
  
  private static UpsightBillboard.Handler forEmbedded(Activity paramActivity, int paramInt)
  {
    return new EmbeddedHandler(paramActivity, paramInt);
  }
  
  public static UpsightBillboard.Handler forFullscreen(Activity paramActivity)
  {
    return new FullscreenHandler(paramActivity);
  }
  
  private static abstract class SimpleHandler
    implements UpsightBillboard.Handler
  {
    protected Activity mActivity;
    protected BillboardFragment mFragment = null;
    
    protected SimpleHandler(Activity paramActivity)
    {
      this.mActivity = paramActivity;
    }
    
    public void onDetach()
    {
      BillboardFragment localBillboardFragment = this.mFragment;
      if (localBillboardFragment != null)
      {
        localBillboardFragment.dismissAllowingStateLoss();
        this.mFragment = null;
      }
    }
    
    public void onNextView() {}
    
    public void onPurchases(List<UpsightPurchase> paramList) {}
    
    public void onRewards(List<UpsightReward> paramList) {}
  }
  
  public static class DefaultHandler
    extends UpsightBillboardHandlers.SimpleHandler
  {
    public DefaultHandler(Activity paramActivity)
    {
      super();
    }
    
    public ViewGroup onAttach(String paramString, UpsightBillboard.PresentationStyle paramPresentationStyle, Set<UpsightBillboard.Dimensions> paramSet)
    {
      ViewGroup localViewGroup = null;
      if ((this.mActivity == null) || (this.mActivity.isFinishing())) {
        return localViewGroup;
      }
      Object localObject = null;
      switch (UpsightBillboardHandlers.1.$SwitchMap$com$upsight$android$marketing$UpsightBillboard$PresentationStyle[paramPresentationStyle.ordinal()])
      {
      }
      for (int i = 16974122;; i = UpsightBillboardHandlers.STYLE_DIALOG)
      {
        this.mFragment = BillboardFragment.newInstance(this.mActivity, (Set)localObject);
        this.mFragment.setStyle(1, i);
        this.mFragment.setCancelable(false);
        this.mFragment.show(this.mActivity.getFragmentManager(), null);
        localViewGroup = this.mFragment.getContentViewContainer();
        break;
        localObject = paramSet;
      }
    }
  }
  
  public static class FullscreenHandler
    extends UpsightBillboardHandlers.SimpleHandler
  {
    public FullscreenHandler(Activity paramActivity)
    {
      super();
    }
    
    public ViewGroup onAttach(String paramString, UpsightBillboard.PresentationStyle paramPresentationStyle, Set<UpsightBillboard.Dimensions> paramSet)
    {
      ViewGroup localViewGroup = null;
      if ((this.mActivity == null) || (this.mActivity.isFinishing())) {}
      for (;;)
      {
        return localViewGroup;
        this.mFragment = BillboardFragment.newInstance(this.mActivity, null);
        this.mFragment.setStyle(1, 16974122);
        this.mFragment.setCancelable(false);
        this.mFragment.show(this.mActivity.getFragmentManager(), null);
        localViewGroup = this.mFragment.getContentViewContainer();
      }
    }
  }
  
  public static class DialogHandler
    extends UpsightBillboardHandlers.SimpleHandler
  {
    public DialogHandler(Activity paramActivity)
    {
      super();
    }
    
    public ViewGroup onAttach(String paramString, UpsightBillboard.PresentationStyle paramPresentationStyle, Set<UpsightBillboard.Dimensions> paramSet)
    {
      ViewGroup localViewGroup = null;
      if ((this.mActivity == null) || (this.mActivity.isFinishing())) {}
      for (;;)
      {
        return localViewGroup;
        this.mFragment = BillboardFragment.newInstance(this.mActivity, paramSet);
        this.mFragment.setStyle(1, UpsightBillboardHandlers.STYLE_DIALOG);
        this.mFragment.setCancelable(false);
        this.mFragment.show(this.mActivity.getFragmentManager(), null);
        localViewGroup = this.mFragment.getContentViewContainer();
      }
    }
  }
  
  private static class EmbeddedHandler
    extends UpsightBillboardHandlers.SimpleHandler
  {
    protected int mContainerViewId;
    
    public EmbeddedHandler(Activity paramActivity, int paramInt)
    {
      super();
      this.mContainerViewId = paramInt;
    }
    
    public ViewGroup onAttach(String paramString, UpsightBillboard.PresentationStyle paramPresentationStyle, Set<UpsightBillboard.Dimensions> paramSet)
    {
      if ((this.mActivity == null) || (this.mActivity.isFinishing())) {}
      for (ViewGroup localViewGroup = null;; localViewGroup = this.mFragment.getContentViewContainer())
      {
        return localViewGroup;
        this.mFragment = BillboardFragment.newInstance(this.mActivity, paramSet);
        this.mActivity.getFragmentManager().beginTransaction().add(this.mContainerViewId, this.mFragment).commit();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/UpsightBillboardHandlers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */