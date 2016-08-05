package android.support.v4.view.animation;

import android.graphics.Path;
import android.os.Build.VERSION;
import android.view.animation.Interpolator;

public class PathInterpolatorCompat
{
  public static Interpolator create(float paramFloat1, float paramFloat2)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (Interpolator localInterpolator = PathInterpolatorCompatApi21.create(paramFloat1, paramFloat2);; localInterpolator = PathInterpolatorCompatBase.create(paramFloat1, paramFloat2)) {
      return localInterpolator;
    }
  }
  
  public static Interpolator create(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (Interpolator localInterpolator = PathInterpolatorCompatApi21.create(paramFloat1, paramFloat2, paramFloat3, paramFloat4);; localInterpolator = PathInterpolatorCompatBase.create(paramFloat1, paramFloat2, paramFloat3, paramFloat4)) {
      return localInterpolator;
    }
  }
  
  public static Interpolator create(Path paramPath)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (Interpolator localInterpolator = PathInterpolatorCompatApi21.create(paramPath);; localInterpolator = PathInterpolatorCompatBase.create(paramPath)) {
      return localInterpolator;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/view/animation/PathInterpolatorCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */