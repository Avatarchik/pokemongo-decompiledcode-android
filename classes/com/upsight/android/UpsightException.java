package com.upsight.android;

import java.util.Locale;

public final class UpsightException
  extends Exception
{
  public UpsightException(String paramString, Object... paramVarArgs)
  {
    super(String.format(Locale.getDefault(), paramString, paramVarArgs));
  }
  
  public UpsightException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
  
  public UpsightException(Throwable paramThrowable, String paramString, Object... paramVarArgs)
  {
    super(String.format(Locale.getDefault(), paramString, paramVarArgs), paramThrowable);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/UpsightException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */