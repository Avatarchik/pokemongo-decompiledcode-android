package android.support.v4.animation;

import android.os.Build.VERSION;
import android.view.View;

public abstract class AnimatorCompatHelper
{
  static AnimatorProvider IMPL;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 12) {}
    for (IMPL = new HoneycombMr1AnimatorCompatProvider();; IMPL = new DonutAnimatorCompatProvider()) {
      return;
    }
  }
  
  public static void clearInterpolator(View paramView)
  {
    IMPL.clearInterpolator(paramView);
  }
  
  public static ValueAnimatorCompat emptyValueAnimator()
  {
    return IMPL.emptyValueAnimator();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/animation/AnimatorCompatHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */