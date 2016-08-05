package android.support.v4.text;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public class ICUCompatApi23
{
  private static final String TAG = "ICUCompatIcs";
  private static Method sAddLikelySubtagsMethod;
  
  static
  {
    try
    {
      Class localClass = Class.forName("libcore.icu.ICU");
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Locale.class;
      sAddLikelySubtagsMethod = localClass.getMethod("addLikelySubtags", arrayOfClass);
      return;
    }
    catch (Exception localException)
    {
      throw new IllegalStateException(localException);
    }
  }
  
  public static String maximizeAndGetScript(Locale paramLocale)
  {
    try
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramLocale;
      String str2 = ((Locale)sAddLikelySubtagsMethod.invoke(null, arrayOfObject)).getScript();
      str1 = str2;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        Log.w("ICUCompatIcs", localInvocationTargetException);
        String str1 = paramLocale.getScript();
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        Log.w("ICUCompatIcs", localIllegalAccessException);
      }
    }
    return str1;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/text/ICUCompatApi23.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */