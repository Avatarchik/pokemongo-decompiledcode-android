package spacemadness.com.lunarconsole.utils;

import spacemadness.com.lunarconsole.debug.Log;

public class StackTrace
{
  public static final String MARKER_ASSETS = "/Assets/";
  public static final String MARKER_AT = " (at ";
  
  public static String optimize(String paramString)
  {
    if (paramString != null) {}
    try
    {
      if (paramString.length() > 0)
      {
        String[] arrayOfString1 = paramString.split("\n");
        String[] arrayOfString2 = new String[arrayOfString1.length];
        for (int i = 0; i < arrayOfString1.length; i++) {
          arrayOfString2[i] = optimizeLine(arrayOfString1[i]);
        }
        String str = StringUtils.Join(arrayOfString2, "\n");
        paramString = str;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramString;
        Log.e(localException, "Error while optimizing stacktrace: %s", arrayOfObject);
      }
    }
    return paramString;
  }
  
  private static String optimizeLine(String paramString)
  {
    int i = paramString.indexOf(" (at ");
    if (i == -1) {}
    for (;;)
    {
      return paramString;
      int j = paramString.lastIndexOf("/Assets/");
      if (j != -1) {
        paramString = paramString.substring(0, i + " (at ".length()) + paramString.substring(j + 1);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/utils/StackTrace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */