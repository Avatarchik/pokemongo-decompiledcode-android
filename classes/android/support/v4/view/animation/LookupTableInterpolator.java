package android.support.v4.view.animation;

import android.view.animation.Interpolator;

abstract class LookupTableInterpolator
  implements Interpolator
{
  private final float mStepSize;
  private final float[] mValues;
  
  public LookupTableInterpolator(float[] paramArrayOfFloat)
  {
    this.mValues = paramArrayOfFloat;
    this.mStepSize = (1.0F / (-1 + this.mValues.length));
  }
  
  public float getInterpolation(float paramFloat)
  {
    float f1 = 1.0F;
    if (paramFloat >= f1) {}
    for (;;)
    {
      return f1;
      if (paramFloat <= 0.0F)
      {
        f1 = 0.0F;
      }
      else
      {
        int i = Math.min((int)(paramFloat * (-1 + this.mValues.length)), -2 + this.mValues.length);
        float f2 = (paramFloat - i * this.mStepSize) / this.mStepSize;
        f1 = this.mValues[i] + f2 * (this.mValues[(i + 1)] - this.mValues[i]);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/view/animation/LookupTableInterpolator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */