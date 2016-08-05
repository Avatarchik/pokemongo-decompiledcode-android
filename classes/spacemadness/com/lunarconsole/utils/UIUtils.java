package spacemadness.com.lunarconsole.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;
import spacemadness.com.lunarconsole.debug.Assert;

public class UIUtils
{
  public static float dpToPx(Context paramContext, float paramFloat)
  {
    return paramFloat * getScreenDensity(paramContext);
  }
  
  public static FrameLayout getRootLayout(Activity paramActivity)
  {
    ViewGroup localViewGroup = getRootViewGroup(paramActivity);
    Assert.IsTrue(localViewGroup instanceof FrameLayout);
    return (FrameLayout)ObjectUtils.as(localViewGroup, FrameLayout.class);
  }
  
  public static ViewGroup getRootViewGroup(Activity paramActivity)
  {
    if (paramActivity == null) {
      throw new NullPointerException("Activity is null");
    }
    View localView = paramActivity.getWindow().findViewById(16908290);
    Assert.IsTrue(localView instanceof ViewGroup);
    return (ViewGroup)ObjectUtils.as(localView, ViewGroup.class);
  }
  
  private static float getScreenDensity(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().density;
  }
  
  public static float pxToDp(Context paramContext, float paramFloat)
  {
    return paramFloat / getScreenDensity(paramContext);
  }
  
  public static void showToast(Context paramContext, String paramString)
  {
    Assert.IsNotNull(paramContext);
    if (paramContext != null) {
      Toast.makeText(paramContext, paramString, 0).show();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/utils/UIUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */