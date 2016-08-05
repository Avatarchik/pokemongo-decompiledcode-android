package android.support.v4.content.res;

import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;

public class ResourcesCompat
{
  public static Drawable getDrawable(Resources paramResources, int paramInt, Resources.Theme paramTheme)
    throws Resources.NotFoundException
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    for (Drawable localDrawable = ResourcesCompatApi21.getDrawable(paramResources, paramInt, paramTheme);; localDrawable = paramResources.getDrawable(paramInt)) {
      return localDrawable;
    }
  }
  
  public static Drawable getDrawableForDensity(Resources paramResources, int paramInt1, int paramInt2, Resources.Theme paramTheme)
    throws Resources.NotFoundException
  {
    int i = Build.VERSION.SDK_INT;
    Drawable localDrawable;
    if (i >= 21) {
      localDrawable = ResourcesCompatApi21.getDrawableForDensity(paramResources, paramInt1, paramInt2, paramTheme);
    }
    for (;;)
    {
      return localDrawable;
      if (i >= 15) {
        localDrawable = ResourcesCompatIcsMr1.getDrawableForDensity(paramResources, paramInt1, paramInt2);
      } else {
        localDrawable = paramResources.getDrawable(paramInt1);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/content/res/ResourcesCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */