package com.upsight.android.internal.logger;

import com.upsight.android.logger.UpsightLogger.Level;

public abstract interface LogWriter
{
  public abstract void write(String paramString1, UpsightLogger.Level paramLevel, String paramString2);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/logger/LogWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */