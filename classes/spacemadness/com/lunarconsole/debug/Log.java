package spacemadness.com.lunarconsole.debug;

import spacemadness.com.lunarconsole.utils.StringUtils;

public class Log
{
  private static final String TAG = "LunarConsole";
  private static final LogLevel logLevel = LogLevel.Info;
  
  public static void d(String paramString, Object... paramVarArgs)
  {
    d(null, paramString, paramVarArgs);
  }
  
  public static void d(Tag paramTag, String paramString, Object... paramVarArgs)
  {
    log(LogLevel.Debug, paramTag, paramString, paramVarArgs);
  }
  
  public static void e(String paramString, Object... paramVarArgs)
  {
    e((Tag)null, paramString, paramVarArgs);
  }
  
  public static void e(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    e(paramString, paramVarArgs);
    if (paramThrowable != null) {
      paramThrowable.printStackTrace();
    }
  }
  
  public static void e(Tag paramTag, String paramString, Object... paramVarArgs)
  {
    log(LogLevel.Error, paramTag, paramString, paramVarArgs);
  }
  
  public static void i(String paramString, Object... paramVarArgs)
  {
    i(null, paramString, paramVarArgs);
  }
  
  public static void i(Tag paramTag, String paramString, Object... paramVarArgs)
  {
    log(LogLevel.Info, paramTag, paramString, paramVarArgs);
  }
  
  private static void log(LogLevel paramLogLevel, Tag paramTag, String paramString, Object... paramVarArgs)
  {
    if ((shouldLogLevel(paramLogLevel)) && (shouldLogTag(paramTag)))
    {
      if (paramString == null) {
        break label25;
      }
      logHelper(paramLogLevel, paramString, paramVarArgs);
    }
    for (;;)
    {
      return;
      label25:
      logHelper(paramLogLevel, "null", new Object[0]);
    }
  }
  
  private static void logHelper(LogLevel paramLogLevel, String paramString, Object... paramVarArgs)
  {
    int i = paramLogLevel.getAndroidLogPriority();
    String str1 = StringUtils.TryFormat(paramString, paramVarArgs);
    String str2 = Thread.currentThread().getName();
    android.util.Log.println(i, "LunarConsole/" + str2, str1);
  }
  
  private static boolean shouldLogLevel(LogLevel paramLogLevel)
  {
    if (paramLogLevel.ordinal() <= logLevel.ordinal()) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private static boolean shouldLogTag(Tag paramTag)
  {
    if ((paramTag == null) || (paramTag.enabled)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static void w(String paramString, Object... paramVarArgs)
  {
    w(null, paramString, paramVarArgs);
  }
  
  public static void w(Tag paramTag, String paramString, Object... paramVarArgs)
  {
    log(LogLevel.Warn, paramTag, paramString, paramVarArgs);
  }
  
  public static enum LogLevel
  {
    private int androidLogPriority;
    
    static
    {
      Info = new LogLevel("Info", 3, 4);
      Debug = new LogLevel("Debug", 4, 3);
      None = new LogLevel("None", 5, -1);
      LogLevel[] arrayOfLogLevel = new LogLevel[6];
      arrayOfLogLevel[0] = Crit;
      arrayOfLogLevel[1] = Error;
      arrayOfLogLevel[2] = Warn;
      arrayOfLogLevel[3] = Info;
      arrayOfLogLevel[4] = Debug;
      arrayOfLogLevel[5] = None;
      $VALUES = arrayOfLogLevel;
    }
    
    private LogLevel(int paramInt)
    {
      this.androidLogPriority = paramInt;
    }
    
    public int getAndroidLogPriority()
    {
      return this.androidLogPriority;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/spacemadness/com/lunarconsole/debug/Log.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */