package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import java.lang.reflect.Field;

class ViewCompatBase
{
  private static final String TAG = "ViewCompatBase";
  private static Field sMinHeightField;
  private static boolean sMinHeightFieldFetched;
  private static Field sMinWidthField;
  private static boolean sMinWidthFieldFetched;
  
  static ColorStateList getBackgroundTintList(View paramView)
  {
    if ((paramView instanceof TintableBackgroundView)) {}
    for (ColorStateList localColorStateList = ((TintableBackgroundView)paramView).getSupportBackgroundTintList();; localColorStateList = null) {
      return localColorStateList;
    }
  }
  
  static PorterDuff.Mode getBackgroundTintMode(View paramView)
  {
    if ((paramView instanceof TintableBackgroundView)) {}
    for (PorterDuff.Mode localMode = ((TintableBackgroundView)paramView).getSupportBackgroundTintMode();; localMode = null) {
      return localMode;
    }
  }
  
  static int getMinimumHeight(View paramView)
  {
    if (!sMinHeightFieldFetched) {}
    try
    {
      sMinHeightField = View.class.getDeclaredField("mMinHeight");
      sMinHeightField.setAccessible(true);
      sMinHeightFieldFetched = true;
      if (sMinHeightField != null) {}
      for (;;)
      {
        try
        {
          int j = ((Integer)sMinHeightField.get(paramView)).intValue();
          i = j;
          return i;
        }
        catch (Exception localException) {}
        int i = 0;
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;) {}
    }
  }
  
  static int getMinimumWidth(View paramView)
  {
    if (!sMinWidthFieldFetched) {}
    try
    {
      sMinWidthField = View.class.getDeclaredField("mMinWidth");
      sMinWidthField.setAccessible(true);
      sMinWidthFieldFetched = true;
      if (sMinWidthField != null) {}
      for (;;)
      {
        try
        {
          int j = ((Integer)sMinWidthField.get(paramView)).intValue();
          i = j;
          return i;
        }
        catch (Exception localException) {}
        int i = 0;
      }
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;) {}
    }
  }
  
  static boolean isAttachedToWindow(View paramView)
  {
    if (paramView.getWindowToken() != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  static boolean isLaidOut(View paramView)
  {
    if ((paramView.getWidth() > 0) && (paramView.getHeight() > 0)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  static void setBackgroundTintList(View paramView, ColorStateList paramColorStateList)
  {
    if ((paramView instanceof TintableBackgroundView)) {
      ((TintableBackgroundView)paramView).setSupportBackgroundTintList(paramColorStateList);
    }
  }
  
  static void setBackgroundTintMode(View paramView, PorterDuff.Mode paramMode)
  {
    if ((paramView instanceof TintableBackgroundView)) {
      ((TintableBackgroundView)paramView).setSupportBackgroundTintMode(paramMode);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/view/ViewCompatBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */