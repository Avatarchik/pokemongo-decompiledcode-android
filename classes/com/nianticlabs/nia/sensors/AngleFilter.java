package com.nianticlabs.nia.sensors;

public class AngleFilter
{
  private static final float FRICTION_COEFFICIENT_1 = 1.0F;
  private static final float FRICTION_COEFFICIENT_2 = 0.5F;
  private static final float MAX_DT = 10.0F;
  private static final float SIGNAL_LEVEL = 10.0F;
  private static final float STEP_SIZE = 0.5F;
  private static final float TIME_NORMALIZATION_MS = 100.0F;
  protected float currentValue;
  private long lastReadingTime = 0L;
  private float speed = 0.0F;
  private final boolean wrap;
  
  public AngleFilter(boolean paramBoolean)
  {
    this.wrap = paramBoolean;
  }
  
  private void step(float paramFloat1, float paramFloat2)
  {
    float f1 = (paramFloat2 - this.currentValue) / 10.0F;
    this.speed += paramFloat1 * (f1 * Math.abs(f1));
    float f3;
    if (this.speed != 0.0F)
    {
      float f2 = f1 * 10.0F / this.speed;
      f3 = (float)(0.5D + 1.0D * Math.exp(f2 * -f2));
      if (f3 * paramFloat1 < 1.0F) {
        break label81;
      }
      this.speed = 0.0F;
    }
    for (;;)
    {
      return;
      label81:
      this.speed -= paramFloat1 * (f3 * this.speed);
      this.currentValue += paramFloat1 * this.speed;
    }
  }
  
  public float filter(long paramLong, float paramFloat)
  {
    if ((this.lastReadingTime == 0L) || (paramLong < this.lastReadingTime)) {
      this.currentValue = paramFloat;
    }
    for (;;)
    {
      this.lastReadingTime = paramLong;
      if (!this.wrap) {
        break label197;
      }
      while (this.currentValue >= 360.0F) {
        this.currentValue -= 360.0F;
      }
      if ((this.wrap) && (2.0F * Math.abs(paramFloat - this.currentValue) > 360.0F)) {
        if (paramFloat >= this.currentValue) {
          break label137;
        }
      }
      float f;
      label137:
      for (paramFloat += 360.0F;; paramFloat -= 360.0F)
      {
        f = (float)(paramLong - this.lastReadingTime) / 100.0F;
        if ((f <= 10.0F) && (f >= 0.0F)) {
          break label145;
        }
        this.currentValue = paramFloat;
        this.speed = 0.0F;
        break;
      }
      label145:
      while (f > 0.0F)
      {
        step(Math.min(0.5F, f), paramFloat);
        f -= 0.5F;
      }
    }
    while (this.currentValue < 0.0F) {
      this.currentValue = (360.0F + this.currentValue);
    }
    label197:
    return this.currentValue;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/sensors/AngleFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */