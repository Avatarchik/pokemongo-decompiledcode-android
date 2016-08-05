package com.upsight.android.analytics.internal.dispatcher.schema;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.provider.UpsightDataProvider;
import com.upsight.android.logger.UpsightLogger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ScreenBlockProvider
  extends UpsightDataProvider
{
  private static final float ANDROID_SCREEN_SCALE = 1.0F;
  public static final String DPI_KEY = "screen.dpi";
  public static final String HEIGHT_KEY = "screen.height";
  public static final String SCALE_KEY = "screen.scale";
  public static final String WIDTH_KEY = "screen.width";
  private final UpsightLogger mLogger;
  
  ScreenBlockProvider(UpsightContext paramUpsightContext)
  {
    put("screen.scale", Float.valueOf(1.0F));
    this.mLogger = paramUpsightContext.getLogger();
    DisplayMetrics localDisplayMetrics = getDefaultDisplayMetrics(paramUpsightContext);
    if (localDisplayMetrics != null)
    {
      put("screen.dpi", Integer.valueOf(localDisplayMetrics.densityDpi));
      put("screen.width", Integer.valueOf(localDisplayMetrics.widthPixels));
      put("screen.height", Integer.valueOf(localDisplayMetrics.heightPixels));
    }
  }
  
  private DisplayMetrics getDefaultDisplayMetrics(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    WindowManager localWindowManager = (WindowManager)paramContext.getSystemService("window");
    if (localWindowManager != null)
    {
      Display localDisplay = localWindowManager.getDefaultDisplay();
      if (localDisplay != null) {
        localDisplay.getMetrics(localDisplayMetrics);
      }
    }
    for (;;)
    {
      return localDisplayMetrics;
      this.mLogger.e("ScreenBlock", "Could not retrieve screen size, windowManager=null", new Object[0]);
    }
  }
  
  public Set<String> availableKeys()
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "screen.width";
    arrayOfString[1] = "screen.height";
    arrayOfString[2] = "screen.scale";
    arrayOfString[3] = "screen.dpi";
    return new HashSet(Arrays.asList(arrayOfString));
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/schema/ScreenBlockProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */