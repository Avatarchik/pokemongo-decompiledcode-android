package android.support.v4.text;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

class ICUCompatIcs
{
  private static final String TAG = "ICUCompatIcs";
  private static Method sAddLikelySubtagsMethod;
  private static Method sGetScriptMethod;
  
  static
  {
    try
    {
      Class localClass = Class.forName("libcore.icu.ICU");
      if (localClass != null)
      {
        Class[] arrayOfClass1 = new Class[1];
        arrayOfClass1[0] = String.class;
        sGetScriptMethod = localClass.getMethod("getScript", arrayOfClass1);
        Class[] arrayOfClass2 = new Class[1];
        arrayOfClass2[0] = String.class;
        sAddLikelySubtagsMethod = localClass.getMethod("addLikelySubtags", arrayOfClass2);
      }
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        sGetScriptMethod = null;
        sAddLikelySubtagsMethod = null;
        Log.w("ICUCompatIcs", localException);
      }
    }
  }
  
  private static String addLikelySubtags(Locale paramLocale)
  {
    String str1 = paramLocale.toString();
    try
    {
      if (sAddLikelySubtagsMethod != null)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = str1;
        str2 = (String)sAddLikelySubtagsMethod.invoke(null, arrayOfObject);
        return str2;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        Log.w("ICUCompatIcs", localIllegalAccessException);
        String str2 = str1;
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        Log.w("ICUCompatIcs", localInvocationTargetException);
      }
    }
  }
  
  private static String getScript(String paramString)
  {
    try
    {
      if (sGetScriptMethod != null)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramString;
        str = (String)sGetScriptMethod.invoke(null, arrayOfObject);
        return str;
      }
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;)
      {
        Log.w("ICUCompatIcs", localIllegalAccessException);
        String str = null;
      }
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;)
      {
        Log.w("ICUCompatIcs", localInvocationTargetException);
      }
    }
  }
  
  public static String maximizeAndGetScript(Locale paramLocale)
  {
    String str1 = addLikelySubtags(paramLocale);
    if (str1 != null) {}
    for (String str2 = getScript(str1);; str2 = null) {
      return str2;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/android/support/v4/text/ICUCompatIcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */