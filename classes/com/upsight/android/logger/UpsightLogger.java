package com.upsight.android.logger;

import java.util.EnumSet;

public abstract interface UpsightLogger
{
  public static final int MAX_LENGTH = 4000;
  
  public abstract void d(String paramString1, String paramString2, Object... paramVarArgs);
  
  public abstract void d(String paramString1, Throwable paramThrowable, String paramString2, Object... paramVarArgs);
  
  public abstract void e(String paramString1, String paramString2, Object... paramVarArgs);
  
  public abstract void e(String paramString1, Throwable paramThrowable, String paramString2, Object... paramVarArgs);
  
  public abstract void i(String paramString1, String paramString2, Object... paramVarArgs);
  
  public abstract void i(String paramString1, Throwable paramThrowable, String paramString2, Object... paramVarArgs);
  
  public abstract void setLogLevel(String paramString, EnumSet<Level> paramEnumSet);
  
  public abstract void v(String paramString1, String paramString2, Object... paramVarArgs);
  
  public abstract void v(String paramString1, Throwable paramThrowable, String paramString2, Object... paramVarArgs);
  
  public abstract void w(String paramString1, String paramString2, Object... paramVarArgs);
  
  public abstract void w(String paramString1, Throwable paramThrowable, String paramString2, Object... paramVarArgs);
  
  public static enum Level
  {
    static
    {
      DEBUG = new Level("DEBUG", 1);
      INFO = new Level("INFO", 2);
      WARN = new Level("WARN", 3);
      ERROR = new Level("ERROR", 4);
      Level[] arrayOfLevel = new Level[5];
      arrayOfLevel[0] = VERBOSE;
      arrayOfLevel[1] = DEBUG;
      arrayOfLevel[2] = INFO;
      arrayOfLevel[3] = WARN;
      arrayOfLevel[4] = ERROR;
      $VALUES = arrayOfLevel;
    }
    
    private Level() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/logger/UpsightLogger.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */