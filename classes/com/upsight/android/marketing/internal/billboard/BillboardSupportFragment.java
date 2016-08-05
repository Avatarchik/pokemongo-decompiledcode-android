package com.upsight.android.marketing.internal.billboard;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import com.upsight.android.marketing.R.layout;
import com.upsight.android.marketing.UpsightBillboard.Dimensions;
import java.util.Iterator;
import java.util.Set;

public final class BillboardSupportFragment
  extends DialogFragment
{
  private static final String BUNDLE_KEY_LANDSCAPE_HEIGHT = "landscapeHeight";
  private static final String BUNDLE_KEY_LANDSCAPE_WIDTH = "landscapeWidth";
  private static final String BUNDLE_KEY_PORTRAIT_HEIGHT = "portraitHeight";
  private static final String BUNDLE_KEY_PORTRAIT_WIDTH = "portraitWidth";
  private ViewGroup mContentViewContainer = null;
  private ViewGroup mRootView = null;
  
  public static BillboardSupportFragment newInstance(Context paramContext, Set<UpsightBillboard.Dimensions> paramSet)
  {
    BillboardSupportFragment localBillboardSupportFragment = new BillboardSupportFragment();
    Bundle localBundle = new Bundle();
    if (paramSet != null)
    {
      Iterator localIterator = paramSet.iterator();
      while (localIterator.hasNext())
      {
        UpsightBillboard.Dimensions localDimensions = (UpsightBillboard.Dimensions)localIterator.next();
        if ((localDimensions.width > 0) && (localDimensions.height > 0)) {
          switch (localDimensions.layout)
          {
          default: 
            break;
          case ???: 
            localBundle.putInt("portraitWidth", localDimensions.width);
            localBundle.putInt("portraitHeight", localDimensions.height);
            break;
          case ???: 
            localBundle.putInt("landscapeWidth", localDimensions.width);
            localBundle.putInt("landscapeHeight", localDimensions.height);
          }
        }
      }
    }
    localBillboardSupportFragment.setArguments(localBundle);
    localBillboardSupportFragment.setRetainInstance(true);
    localBillboardSupportFragment.mContentViewContainer = new LinearLayout(paramContext.getApplicationContext());
    return localBillboardSupportFragment;
  }
  
  private void setDialogSize(int paramInt1, int paramInt2)
  {
    Window localWindow = getDialog().getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    localLayoutParams.width = paramInt1;
    localLayoutParams.height = paramInt2;
    localWindow.setAttributes(localLayoutParams);
  }
  
  public ViewGroup getContentViewContainer()
  {
    return this.mContentViewContainer;
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    getFragmentManager().beginTransaction().detach(this).attach(this).commit();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.mContentViewContainer != null)
    {
      this.mRootView = ((ViewGroup)paramLayoutInflater.inflate(R.layout.upsight_fragment_billboard, paramViewGroup, false));
      this.mRootView.addView(this.mContentViewContainer, new ViewGroup.LayoutParams(-1, -1));
    }
    return this.mRootView;
  }
  
  public void onDestroyView()
  {
    if (this.mContentViewContainer != null) {
      this.mRootView.removeView(this.mContentViewContainer);
    }
    Dialog localDialog = getDialog();
    if ((localDialog != null) && (getRetainInstance())) {
      localDialog.setDismissMessage(null);
    }
    super.onDestroyView();
  }
  
  public void onResume()
  {
    int i = getResources().getConfiguration().orientation;
    Bundle localBundle = getArguments();
    if ((i == 1) && (localBundle.containsKey("portraitWidth"))) {
      setDialogSize(localBundle.getInt("portraitWidth"), localBundle.getInt("portraitHeight"));
    }
    for (;;)
    {
      super.onResume();
      return;
      if ((i == 2) && (localBundle.containsKey("landscapeWidth"))) {
        setDialogSize(localBundle.getInt("landscapeWidth"), localBundle.getInt("landscapeHeight"));
      }
    }
  }
  
  public void onStart()
  {
    super.onStart();
    if (this.mContentViewContainer == null) {
      dismiss();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/billboard/BillboardSupportFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */