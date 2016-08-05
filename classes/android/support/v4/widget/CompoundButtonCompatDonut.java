package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

class CompoundButtonCompatDonut
{
  private static final String TAG = "CompoundButtonCompatDonut";
  private static Field sButtonDrawableField;
  private static boolean sButtonDrawableFieldFetched;
  
  static Drawable getButtonDrawable(CompoundButton paramCompoundButton)
  {
    if (!sButtonDrawableFieldFetched) {}
    try
    {
      sButtonDrawableField = CompoundButton.class.getDeclaredField("mButtonDrawable");
      sButtonDrawableField.setAccessible(true);
      sButtonDrawableFieldFetched = true;
      if (sButtonDrawableField == null) {}
    }
    catch (NoSuchFieldException localNoSuchFieldException)
    {
      for (;;)
      {
        try
        {
          localDrawable = (Drawable)sButtonDrawableField.get(paramCompoundButton);
          return localDrawable;
        }
        catch (IllegalAccessException localIllegalAccessException)
        {
          Log.i("CompoundButtonCompatDonut", "Failed to get button drawable via reflection", localIllegalAccessException);
          sButtonDrawableField = null;
        }
        localNoSuchFieldException = localNoSuchFieldException;
        Log.i("CompoundButtonCompatDonut", "Failed to retrieve mButtonDrawable field", localNoSuchFieldException);
        continue;
        Drawable localDrawable = null;
      }
    }
  }
  
  static ColorStateList getButtonTintList(CompoundButton paramCompoundButton)
  {
    if ((paramCompoundButton instanceof TintableCompoundButton)) {}
    for (ColorStateList localColorStateList = ((TintableCompoundButton)paramCompoundButton).getSupportButtonTintList();; localColorStateList = null) {
      return localColorStateList;
    }
  }
  
  static PorterDuff.Mode getButtonTintMode(CompoundButton paramCompoundButton)
  {
    if ((paramCompoundButton instanceof TintableCompoundButton)) {}
    for (PorterDuff.Mode localMode = ((TintableCompoundButton)paramCompoundButton).getSupportButtonTintMode();; localMode = null) {
      return localMode;
    }
  }
  
  static void setButtonTintList(CompoundButton paramCompoundButton, ColorStateList paramColorStateList)
  {
    if ((paramCompoundButton instanceof TintableCompoundButton)) {
      ((TintableCompoundButton)paramCompoundButton).setSupportButtonTintList(paramColorStateList);
    }
  }
  
  static void setButtonTintMode(CompoundButton paramCompoundButton, PorterDuff.Mode paramMode)
  {
    if ((paramCompoundButton instanceof TintableCompoundButton)) {
      ((TintableCompoundButton)paramCompoundButton).setSupportButtonTintMode(paramMode);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/widget/CompoundButtonCompatDonut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */