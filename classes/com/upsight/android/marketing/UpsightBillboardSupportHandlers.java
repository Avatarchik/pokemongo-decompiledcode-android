package com.upsight.android.marketing;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;
import com.upsight.android.marketing.internal.billboard.BillboardSupportFragment;
import java.util.List;
import java.util.Set;

public final class UpsightBillboardSupportHandlers
{
  private static final int STYLE_DIALOG = R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_UpsightDialog;
  private static final int STYLE_FULLSCREEN = 16974122;
  
  public static UpsightBillboard.Handler forDefault(FragmentActivity paramFragmentActivity)
  {
    return new DefaultHandler(paramFragmentActivity);
  }
  
  public static UpsightBillboard.Handler forDialog(FragmentActivity paramFragmentActivity)
  {
    return new DialogHandler(paramFragmentActivity);
  }
  
  private static UpsightBillboard.Handler forEmbedded(FragmentActivity paramFragmentActivity, int paramInt)
  {
    return new EmbeddedHandler(paramFragmentActivity, paramInt);
  }
  
  public static UpsightBillboard.Handler forFullscreen(FragmentActivity paramFragmentActivity)
  {
    return new FullscreenHandler(paramFragmentActivity);
  }
  
  private static abstract class SimpleHandler
    implements UpsightBillboard.Handler
  {
    protected FragmentActivity mActivity;
    protected BillboardSupportFragment mFragment = null;
    
    protected SimpleHandler(FragmentActivity paramFragmentActivity)
    {
      this.mActivity = paramFragmentActivity;
    }
    
    public void onDetach()
    {
      BillboardSupportFragment localBillboardSupportFragment = this.mFragment;
      if (localBillboardSupportFragment != null)
      {
        localBillboardSupportFragment.dismissAllowingStateLoss();
        this.mFragment = null;
      }
    }
    
    public void onNextView() {}
    
    public void onPurchases(List<UpsightPurchase> paramList) {}
    
    public void onRewards(List<UpsightReward> paramList) {}
  }
  
  public static class DefaultHandler
    extends UpsightBillboardSupportHandlers.SimpleHandler
  {
    public DefaultHandler(FragmentActivity paramFragmentActivity)
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
      switch (UpsightBillboardSupportHandlers.1.$SwitchMap$com$upsight$android$marketing$UpsightBillboard$PresentationStyle[paramPresentationStyle.ordinal()])
      {
      }
      for (int i = 16974122;; i = UpsightBillboardSupportHandlers.STYLE_DIALOG)
      {
        this.mFragment = BillboardSupportFragment.newInstance(this.mActivity, (Set)localObject);
        this.mFragment.setStyle(1, i);
        this.mFragment.setCancelable(false);
        this.mFragment.show(this.mActivity.getSupportFragmentManager(), null);
        localViewGroup = this.mFragment.getContentViewContainer();
        break;
        localObject = paramSet;
      }
    }
  }
  
  public static class FullscreenHandler
    extends UpsightBillboardSupportHandlers.SimpleHandler
  {
    public FullscreenHandler(FragmentActivity paramFragmentActivity)
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
        this.mFragment = BillboardSupportFragment.newInstance(this.mActivity, null);
        this.mFragment.setStyle(1, 16974122);
        this.mFragment.setCancelable(false);
        this.mFragment.show(this.mActivity.getSupportFragmentManager(), null);
        localViewGroup = this.mFragment.getContentViewContainer();
      }
    }
  }
  
  public static class DialogHandler
    extends UpsightBillboardSupportHandlers.SimpleHandler
  {
    public DialogHandler(FragmentActivity paramFragmentActivity)
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
        this.mFragment = BillboardSupportFragment.newInstance(this.mActivity, paramSet);
        this.mFragment.setStyle(1, UpsightBillboardSupportHandlers.STYLE_DIALOG);
        this.mFragment.setCancelable(false);
        this.mFragment.show(this.mActivity.getSupportFragmentManager(), null);
        localViewGroup = this.mFragment.getContentViewContainer();
      }
    }
  }
  
  private static class EmbeddedHandler
    extends UpsightBillboardSupportHandlers.SimpleHandler
  {
    protected int mContainerViewId;
    
    public EmbeddedHandler(FragmentActivity paramFragmentActivity, int paramInt)
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
        this.mFragment = BillboardSupportFragment.newInstance(this.mActivity, paramSet);
        this.mActivity.getSupportFragmentManager().beginTransaction().add(this.mContainerViewId, this.mFragment).commit();
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/UpsightBillboardSupportHandlers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */