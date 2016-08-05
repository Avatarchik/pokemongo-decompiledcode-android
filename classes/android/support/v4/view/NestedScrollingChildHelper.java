package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;

public class NestedScrollingChildHelper
{
  private boolean mIsNestedScrollingEnabled;
  private ViewParent mNestedScrollingParent;
  private int[] mTempNestedScrollConsumed;
  private final View mView;
  
  public NestedScrollingChildHelper(View paramView)
  {
    this.mView = paramView;
  }
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    if ((isNestedScrollingEnabled()) && (this.mNestedScrollingParent != null)) {}
    for (boolean bool = ViewParentCompat.onNestedFling(this.mNestedScrollingParent, this.mView, paramFloat1, paramFloat2, paramBoolean);; bool = false) {
      return bool;
    }
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
  {
    if ((isNestedScrollingEnabled()) && (this.mNestedScrollingParent != null)) {}
    for (boolean bool = ViewParentCompat.onNestedPreFling(this.mNestedScrollingParent, this.mView, paramFloat1, paramFloat2);; bool = false) {
      return bool;
    }
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    boolean bool = false;
    if ((isNestedScrollingEnabled()) && (this.mNestedScrollingParent != null))
    {
      if ((paramInt1 == 0) && (paramInt2 == 0)) {
        break label158;
      }
      int i = 0;
      int j = 0;
      if (paramArrayOfInt2 != null)
      {
        this.mView.getLocationInWindow(paramArrayOfInt2);
        i = paramArrayOfInt2[0];
        j = paramArrayOfInt2[1];
      }
      if (paramArrayOfInt1 == null)
      {
        if (this.mTempNestedScrollConsumed == null) {
          this.mTempNestedScrollConsumed = new int[2];
        }
        paramArrayOfInt1 = this.mTempNestedScrollConsumed;
      }
      paramArrayOfInt1[bool] = 0;
      paramArrayOfInt1[1] = 0;
      ViewParentCompat.onNestedPreScroll(this.mNestedScrollingParent, this.mView, paramInt1, paramInt2, paramArrayOfInt1);
      if (paramArrayOfInt2 != null)
      {
        this.mView.getLocationInWindow(paramArrayOfInt2);
        paramArrayOfInt2[bool] = (paramArrayOfInt2[0] - i);
        paramArrayOfInt2[1] -= j;
      }
      if ((paramArrayOfInt1[0] != 0) || (paramArrayOfInt1[1] != 0)) {
        bool = true;
      }
    }
    for (;;)
    {
      return bool;
      label158:
      if (paramArrayOfInt2 != null)
      {
        paramArrayOfInt2[bool] = 0;
        paramArrayOfInt2[1] = 0;
      }
    }
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    if ((isNestedScrollingEnabled()) && (this.mNestedScrollingParent != null)) {
      if ((paramInt1 != 0) || (paramInt2 != 0) || (paramInt3 != 0) || (paramInt4 != 0))
      {
        int i = 0;
        int j = 0;
        if (paramArrayOfInt != null)
        {
          this.mView.getLocationInWindow(paramArrayOfInt);
          i = paramArrayOfInt[0];
          j = paramArrayOfInt[1];
        }
        ViewParentCompat.onNestedScroll(this.mNestedScrollingParent, this.mView, paramInt1, paramInt2, paramInt3, paramInt4);
        if (paramArrayOfInt != null)
        {
          this.mView.getLocationInWindow(paramArrayOfInt);
          paramArrayOfInt[0] -= i;
          paramArrayOfInt[1] -= j;
        }
      }
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      if (paramArrayOfInt != null)
      {
        paramArrayOfInt[0] = 0;
        paramArrayOfInt[1] = 0;
      }
    }
  }
  
  public boolean hasNestedScrollingParent()
  {
    if (this.mNestedScrollingParent != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isNestedScrollingEnabled()
  {
    return this.mIsNestedScrollingEnabled;
  }
  
  public void onDetachedFromWindow()
  {
    ViewCompat.stopNestedScroll(this.mView);
  }
  
  public void onStopNestedScroll(View paramView)
  {
    ViewCompat.stopNestedScroll(this.mView);
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean)
  {
    if (this.mIsNestedScrollingEnabled) {
      ViewCompat.stopNestedScroll(this.mView);
    }
    this.mIsNestedScrollingEnabled = paramBoolean;
  }
  
  public boolean startNestedScroll(int paramInt)
  {
    boolean bool = true;
    if (hasNestedScrollingParent()) {}
    for (;;)
    {
      return bool;
      if (isNestedScrollingEnabled())
      {
        ViewParent localViewParent = this.mView.getParent();
        View localView = this.mView;
        for (;;)
        {
          if (localViewParent == null) {
            break label92;
          }
          if (ViewParentCompat.onStartNestedScroll(localViewParent, localView, this.mView, paramInt))
          {
            this.mNestedScrollingParent = localViewParent;
            ViewParentCompat.onNestedScrollAccepted(localViewParent, localView, this.mView, paramInt);
            break;
          }
          if ((localViewParent instanceof View)) {
            localView = (View)localViewParent;
          }
          localViewParent = localViewParent.getParent();
        }
      }
      label92:
      bool = false;
    }
  }
  
  public void stopNestedScroll()
  {
    if (this.mNestedScrollingParent != null)
    {
      ViewParentCompat.onStopNestedScroll(this.mNestedScrollingParent, this.mView);
      this.mNestedScrollingParent = null;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/view/NestedScrollingChildHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */