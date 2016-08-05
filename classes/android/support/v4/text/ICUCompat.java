package android.support.v4.text;

import android.os.Build.VERSION;
import java.util.Locale;

public class ICUCompat
{
  private static final ICUCompatImpl IMPL;
  
  static
  {
    int i = Build.VERSION.SDK_INT;
    if (i >= 21) {
      IMPL = new ICUCompatImplLollipop();
    }
    for (;;)
    {
      return;
      if (i >= 14) {
        IMPL = new ICUCompatImplIcs();
      } else {
        IMPL = new ICUCompatImplBase();
      }
    }
  }
  
  public static String maximizeAndGetScript(Locale paramLocale)
  {
    return IMPL.maximizeAndGetScript(paramLocale);
  }
  
  static class ICUCompatImplLollipop
    implements ICUCompat.ICUCompatImpl
  {
    public String maximizeAndGetScript(Locale paramLocale)
    {
      return ICUCompatApi23.maximizeAndGetScript(paramLocale);
    }
  }
  
  static class ICUCompatImplIcs
    implements ICUCompat.ICUCompatImpl
  {
    public String maximizeAndGetScript(Locale paramLocale)
    {
      return ICUCompatIcs.maximizeAndGetScript(paramLocale);
    }
  }
  
  static class ICUCompatImplBase
    implements ICUCompat.ICUCompatImpl
  {
    public String maximizeAndGetScript(Locale paramLocale)
    {
      return null;
    }
  }
  
  static abstract interface ICUCompatImpl
  {
    public abstract String maximizeAndGetScript(Locale paramLocale);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/text/ICUCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */