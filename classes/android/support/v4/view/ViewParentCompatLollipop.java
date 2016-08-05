package android.support.v4.view;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;

class ViewParentCompatLollipop
{
  private static final String TAG = "ViewParentCompat";
  
  public static boolean onNestedFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    try
    {
      boolean bool2 = paramViewParent.onNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
      bool1 = bool2;
    }
    catch (AbstractMethodError localAbstractMethodError)
    {
      for (;;)
      {
        Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedFling", localAbstractMethodError);
        boolean bool1 = false;
      }
    }
    return bool1;
  }
  
  public static boolean onNestedPreFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2)
  {
    try
    {
      boolean bool2 = paramViewParent.onNestedPreFling(paramView, paramFloat1, paramFloat2);
      bool1 = bool2;
    }
    catch (AbstractMethodError localAbstractMethodError)
    {
      for (;;)
      {
        Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedPreFling", localAbstractMethodError);
        boolean bool1 = false;
      }
    }
    return bool1;
  }
  
  public static void onNestedPreScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    try
    {
      paramViewParent.onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfInt);
      return;
    }
    catch (AbstractMethodError localAbstractMethodError)
    {
      for (;;)
      {
        Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedPreScroll", localAbstractMethodError);
      }
    }
  }
  
  public static void onNestedScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      paramViewParent.onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    catch (AbstractMethodError localAbstractMethodError)
    {
      for (;;)
      {
        Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedScroll", localAbstractMethodError);
      }
    }
  }
  
  public static void onNestedScrollAccepted(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
  {
    try
    {
      paramViewParent.onNestedScrollAccepted(paramView1, paramView2, paramInt);
      return;
    }
    catch (AbstractMethodError localAbstractMethodError)
    {
      for (;;)
      {
        Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedScrollAccepted", localAbstractMethodError);
      }
    }
  }
  
  public static boolean onStartNestedScroll(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt)
  {
    try
    {
      boolean bool2 = paramViewParent.onStartNestedScroll(paramView1, paramView2, paramInt);
      bool1 = bool2;
    }
    catch (AbstractMethodError localAbstractMethodError)
    {
      for (;;)
      {
        Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onStartNestedScroll", localAbstractMethodError);
        boolean bool1 = false;
      }
    }
    return bool1;
  }
  
  public static void onStopNestedScroll(ViewParent paramViewParent, View paramView)
  {
    try
    {
      paramViewParent.onStopNestedScroll(paramView);
      return;
    }
    catch (AbstractMethodError localAbstractMethodError)
    {
      for (;;)
      {
        Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onStopNestedScroll", localAbstractMethodError);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/view/ViewParentCompatLollipop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */