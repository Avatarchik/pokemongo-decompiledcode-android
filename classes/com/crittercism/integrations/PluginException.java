package com.crittercism.integrations;

import crittercism.android.dx;

public class PluginException
  extends Throwable
{
  private static final String EMPTY_STRING = "";
  private static final long serialVersionUID = -1947260712494608235L;
  private String exceptionName = null;
  
  private PluginException(String paramString)
  {
    super(paramString);
  }
  
  public PluginException(String paramString1, String paramString2)
  {
    this("", paramString1, paramString2);
  }
  
  public PluginException(String paramString1, String paramString2, String paramString3)
  {
    super(paramString2);
    setExceptionName(checkString(paramString1), checkString(paramString2));
    setStackTrace(createStackTraceArrayFromStack(checkStringStack(paramString3)));
  }
  
  public PluginException(String paramString1, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int[] paramArrayOfInt)
  {
    super(paramString2);
    setExceptionName(checkString(paramString1), checkString(paramString2));
    setStackTrace(createStackTraceArrayFromStack(paramArrayOfString1, paramArrayOfString2, paramArrayOfString3, paramArrayOfInt));
  }
  
  private static String checkString(String paramString)
  {
    if (paramString == null) {
      paramString = "";
    }
    return paramString;
  }
  
  private static String[] checkStringStack(String paramString)
  {
    if (paramString == null) {}
    for (String[] arrayOfString = new String[0];; arrayOfString = paramString.split("\\r?\\n")) {
      return arrayOfString;
    }
  }
  
  private StackTraceElement[] createStackTraceArrayFromStack(String[] paramArrayOfString)
  {
    int i = 0;
    StackTraceElement[] arrayOfStackTraceElement2;
    int j;
    if ((paramArrayOfString.length >= 2) && (paramArrayOfString[0] != null) && (paramArrayOfString[1] != null) && (paramArrayOfString[0].equals(paramArrayOfString[1])))
    {
      arrayOfStackTraceElement2 = new StackTraceElement[-1 + paramArrayOfString.length];
      j = 1;
    }
    for (StackTraceElement[] arrayOfStackTraceElement1 = arrayOfStackTraceElement2;; arrayOfStackTraceElement1 = null)
    {
      if (j == 0) {
        arrayOfStackTraceElement1 = new StackTraceElement[paramArrayOfString.length];
      }
      if (i < paramArrayOfString.length)
      {
        if ((i != 0) || (j == 0)) {
          if (j == 0) {
            break label109;
          }
        }
        label109:
        for (int k = i - 1;; k = i)
        {
          arrayOfStackTraceElement1[k] = new StackTraceElement("", paramArrayOfString[i], "", -1);
          i++;
          break;
        }
      }
      return arrayOfStackTraceElement1;
      j = 0;
    }
  }
  
  private StackTraceElement[] createStackTraceArrayFromStack(String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3, int[] paramArrayOfInt)
  {
    int i = paramArrayOfString1.length;
    StackTraceElement[] arrayOfStackTraceElement = new StackTraceElement[i];
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfInt[j];
      if (k <= 0) {
        k = -1;
      }
      arrayOfStackTraceElement[j] = new StackTraceElement(checkString(paramArrayOfString1[j]), checkString(paramArrayOfString2[j]), checkString(paramArrayOfString3[j]), k);
    }
    return arrayOfStackTraceElement;
  }
  
  public static PluginException createUnityException(String paramString1, String paramString2)
  {
    Object localObject = null;
    try
    {
      PluginException localPluginException = unsafeCreateUnityException(paramString1, paramString2);
      localObject = localPluginException;
    }
    catch (ThreadDeath localThreadDeath)
    {
      throw localThreadDeath;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        dx.a("Unable to log unity exception <" + paramString1 + "> " + paramString2, localThrowable);
      }
    }
    return (PluginException)localObject;
  }
  
  private static PluginException unsafeCreateUnityException(String paramString1, String paramString2)
  {
    if (paramString1 == null) {
      paramString1 = "";
    }
    if (paramString2 == null) {
      paramString2 = "";
    }
    int i = paramString1.indexOf(":");
    String str1;
    if (i != -1)
    {
      str1 = paramString1.substring(0, i).trim();
      if (i + 1 < paramString1.length()) {
        paramString1 = paramString1.substring(i + 1).trim();
      }
    }
    for (;;)
    {
      PluginException localPluginException1 = new PluginException(paramString1);
      localPluginException1.exceptionName = str1;
      String str2 = paramString2.trim();
      PluginException localPluginException2;
      if (str2.length() == 0)
      {
        localPluginException1.setStackTrace(new StackTraceElement[0]);
        localPluginException2 = localPluginException1;
      }
      for (;;)
      {
        return localPluginException2;
        String[] arrayOfString1 = str2.split("\\r?\\n");
        StackTraceElement[] arrayOfStackTraceElement = new StackTraceElement[arrayOfString1.length];
        int j = 0;
        if (j < arrayOfString1.length)
        {
          String str3 = arrayOfString1[j].trim();
          String str5;
          String str6;
          Object localObject;
          int m;
          String str7;
          String[] arrayOfString2;
          if (str3.length() != 0)
          {
            String str4 = str3.split(" ")[0];
            int k = str4.lastIndexOf(".");
            if (k == -1)
            {
              dx.b("Unable to parse unity exception.  No class and method found for frame frame <" + str3 + ">" + str2);
              localPluginException2 = null;
              continue;
            }
            if (k == -1 + str4.length())
            {
              dx.b("Unable to parse unity exception.  Method is zero length for frame <" + str3 + ">" + str2);
              localPluginException2 = null;
              continue;
            }
            str5 = str4.substring(0, k);
            str6 = str4.substring(k + 1);
            localObject = null;
            m = -1;
            int n = str3.indexOf(" (at ");
            if (n != -1)
            {
              str7 = str3.substring(n + 5, -1 + arrayOfString1[j].length());
              arrayOfString2 = str7.split(":");
              if (arrayOfString2.length < 2) {
                break label445;
              }
              localObject = arrayOfString2[0];
            }
          }
          for (;;)
          {
            try
            {
              String str8 = arrayOfString2[(-1 + arrayOfString2.length)];
              m = Integer.parseInt(str8);
              String str9 = str7.substring(0, -1 + (str7.length() - str8.length()));
              localObject = str9;
            }
            catch (NumberFormatException localNumberFormatException)
            {
              new StringBuilder("Couldn't parse integer: ").append(arrayOfString2[1]);
              dx.a();
              continue;
            }
            arrayOfStackTraceElement[j] = new StackTraceElement(str5, str6, (String)localObject, m);
            j++;
            break;
            label445:
            new StringBuilder("Unable to parse file & line out of Unity stack trace frame: ").append(arrayOfString2).append(" ::: ").append(arrayOfString1[j]);
            dx.b();
          }
        }
        else
        {
          localPluginException1.setStackTrace(arrayOfStackTraceElement);
          localPluginException2 = localPluginException1;
        }
      }
      str1 = paramString1;
    }
  }
  
  public String getExceptionName()
  {
    return this.exceptionName;
  }
  
  public void setExceptionName(String paramString1, String paramString2)
  {
    if (paramString1.length() > 0) {}
    for (this.exceptionName = paramString1;; this.exceptionName = "JavaScript Exception") {
      return;
    }
  }
  
  public final String toString()
  {
    String str1 = getLocalizedMessage();
    String str2 = getExceptionName();
    if (str1 == null) {}
    for (;;)
    {
      return str2;
      str2 = str2 + ": " + str1;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/crittercism/integrations/PluginException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */