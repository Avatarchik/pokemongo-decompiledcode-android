package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.util.Log;
import java.lang.reflect.Method;

class DrawableCompatJellybeanMr1
{
  private static final String TAG = "DrawableCompatJellybeanMr1";
  private static Method sGetLayoutDirectionMethod;
  private static boolean sGetLayoutDirectionMethodFetched;
  private static Method sSetLayoutDirectionMethod;
  private static boolean sSetLayoutDirectionMethodFetched;
  
  public static int getLayoutDirection(Drawable paramDrawable)
  {
    if (!sGetLayoutDirectionMethodFetched) {}
    try
    {
      sGetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("getLayoutDirection", new Class[0]);
      sGetLayoutDirectionMethod.setAccessible(true);
      sGetLayoutDirectionMethodFetched = true;
      if (sGetLayoutDirectionMethod == null) {}
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        try
        {
          int j = ((Integer)sGetLayoutDirectionMethod.invoke(paramDrawable, new Object[0])).intValue();
          i = j;
          return i;
        }
        catch (Exception localException)
        {
          Log.i("DrawableCompatJellybeanMr1", "Failed to invoke getLayoutDirection() via reflection", localException);
          sGetLayoutDirectionMethod = null;
        }
        localNoSuchMethodException = localNoSuchMethodException;
        Log.i("DrawableCompatJellybeanMr1", "Failed to retrieve getLayoutDirection() method", localNoSuchMethodException);
        continue;
        int i = -1;
      }
    }
  }
  
  public static void setLayoutDirection(Drawable paramDrawable, int paramInt)
  {
    if (!sSetLayoutDirectionMethodFetched) {}
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Integer.TYPE;
      sSetLayoutDirectionMethod = Drawable.class.getDeclaredMethod("setLayoutDirection", arrayOfClass);
      sSetLayoutDirectionMethod.setAccessible(true);
      sSetLayoutDirectionMethodFetched = true;
      if (sSetLayoutDirectionMethod == null) {}
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      try
      {
        Method localMethod = sSetLayoutDirectionMethod;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        localMethod.invoke(paramDrawable, arrayOfObject);
        return;
        localNoSuchMethodException = localNoSuchMethodException;
        Log.i("DrawableCompatJellybeanMr1", "Failed to retrieve setLayoutDirection(int) method", localNoSuchMethodException);
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Log.i("DrawableCompatJellybeanMr1", "Failed to invoke setLayoutDirection(int) via reflection", localException);
          sSetLayoutDirectionMethod = null;
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/graphics/drawable/DrawableCompatJellybeanMr1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */