package com.nianticlabs.nia.sensors;

public final class MathUtil
{
  public static final float DEGREES_TO_RADIANS = 0.017453292F;
  public static final float HALF_PI = 1.5707964F;
  public static final double NANOSECONDS_PER_SECOND = 1.0E9D;
  public static final long NANOSECONDS_PER_SECOND_AS_LONG = 1000000000L;
  public static final float PI = 3.1415927F;
  public static final float RADIANS_TO_DEGREES = 57.29578F;
  public static final float TWO_PI = 6.2831855F;
  
  public static float clamp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return Math.max(paramFloat2, Math.min(paramFloat3, paramFloat1));
  }
  
  public static int clamp(int paramInt1, int paramInt2, int paramInt3)
  {
    return Math.max(paramInt2, Math.min(paramInt3, paramInt1));
  }
  
  public static float degToRad(float paramFloat)
  {
    return 0.017453292F * paramFloat;
  }
  
  public static float ease(float paramFloat)
  {
    return (float)Math.sin(3.141592653589793D * Math.max(Math.min(1.0F, paramFloat), 0.0F) / 2.0D);
  }
  
  public static float lerp(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return paramFloat1 + paramFloat3 * (paramFloat2 - paramFloat1);
  }
  
  public static float linearStep(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return saturate((paramFloat3 - paramFloat1) / (paramFloat2 - paramFloat1));
  }
  
  public static long nextPowerOf2(long paramLong)
  {
    long l1 = Math.max(paramLong, 0L) - 1L;
    long l2 = l1 | l1 >> 1;
    long l3 = l2 | l2 >> 2;
    long l4 = l3 | l3 >> 4;
    long l5 = l4 | l4 >> 8;
    return 1L + (l5 | l5 >> 16);
  }
  
  public static double normalizeAngle(double paramDouble)
  {
    while (paramDouble > 3.141592653589793D) {
      paramDouble -= 6.283185307179586D;
    }
    while (paramDouble <= -3.141592653589793D) {
      paramDouble += 6.283185307179586D;
    }
    return paramDouble;
  }
  
  public static float normalizeAngle(float paramFloat)
  {
    while (paramFloat > 3.1415927F) {
      paramFloat -= 6.2831855F;
    }
    while (paramFloat <= -3.1415927F) {
      paramFloat += 6.2831855F;
    }
    return paramFloat;
  }
  
  public static float[] quadraticBezier(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, int paramInt)
  {
    float[] arrayOfFloat = new float[paramInt * 2];
    for (int i = 0; i < paramInt; i++)
    {
      float f = i * (1.0F / (paramInt - 1));
      arrayOfFloat[(i * 2)] = (paramFloat1 * ((1.0F - f) * (1.0F - f)) + paramFloat3 * (f * (2.0F * (1.0F - f))) + paramFloat5 * (f * f));
      arrayOfFloat[(1 + i * 2)] = (paramFloat2 * ((1.0F - f) * (1.0F - f)) + paramFloat4 * (f * (2.0F * (1.0F - f))) + paramFloat6 * (f * f));
    }
    return arrayOfFloat;
  }
  
  public static float radToDeg(float paramFloat)
  {
    return 57.29578F * paramFloat;
  }
  
  public static float randomRange(float paramFloat1, float paramFloat2)
  {
    return paramFloat1 + (paramFloat2 - paramFloat1) * (float)Math.random();
  }
  
  public static float saturate(float paramFloat)
  {
    return clamp(paramFloat, 0.0F, 1.0F);
  }
  
  public static double wrapAngle(double paramDouble)
  {
    while (paramDouble >= 6.283185307179586D) {
      paramDouble -= 6.283185307179586D;
    }
    while (paramDouble < 0.0D) {
      paramDouble += 6.283185307179586D;
    }
    return paramDouble;
  }
  
  public static float wrapAngle(float paramFloat)
  {
    while (paramFloat >= 6.2831855F) {
      paramFloat -= 6.2831855F;
    }
    while (paramFloat < 0.0F) {
      paramFloat += 6.2831855F;
    }
    return paramFloat;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/nianticlabs/nia/sensors/MathUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */