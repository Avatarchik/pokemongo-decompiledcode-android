package android.support.v4.widget;

import android.widget.PopupWindow;
import java.lang.reflect.Method;

class PopupWindowCompatGingerbread
{
  private static Method sGetWindowLayoutTypeMethod;
  private static boolean sGetWindowLayoutTypeMethodAttempted;
  private static Method sSetWindowLayoutTypeMethod;
  private static boolean sSetWindowLayoutTypeMethodAttempted;
  
  static int getWindowLayoutType(PopupWindow paramPopupWindow)
  {
    if (!sGetWindowLayoutTypeMethodAttempted) {}
    try
    {
      sGetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("getWindowLayoutType", new Class[0]);
      sGetWindowLayoutTypeMethod.setAccessible(true);
      sGetWindowLayoutTypeMethodAttempted = true;
      if (sGetWindowLayoutTypeMethod != null) {}
      for (;;)
      {
        try
        {
          int j = ((Integer)sGetWindowLayoutTypeMethod.invoke(paramPopupWindow, new Object[0])).intValue();
          i = j;
          return i;
        }
        catch (Exception localException1) {}
        int i = 0;
      }
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  static void setWindowLayoutType(PopupWindow paramPopupWindow, int paramInt)
  {
    if (!sSetWindowLayoutTypeMethodAttempted) {}
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Integer.TYPE;
      sSetWindowLayoutTypeMethod = PopupWindow.class.getDeclaredMethod("setWindowLayoutType", arrayOfClass);
      sSetWindowLayoutTypeMethod.setAccessible(true);
      sSetWindowLayoutTypeMethodAttempted = true;
      if (sSetWindowLayoutTypeMethod != null) {}
      try
      {
        Method localMethod = sSetWindowLayoutTypeMethod;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        localMethod.invoke(paramPopupWindow, arrayOfObject);
        return;
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/widget/PopupWindowCompatGingerbread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */