package com.upsight.android.internal.logger;

import com.upsight.android.logger.UpsightLogger.Level;
import com.upsight.android.persistence.annotation.UpsightStorableIdentifier;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("com.upsight.message.log")
public final class LogMessage
{
  @UpsightStorableIdentifier
  public String id;
  private UpsightLogger.Level level;
  private String message;
  private String tag;
  private String throwableString;
  
  LogMessage() {}
  
  LogMessage(LogMessage paramLogMessage)
  {
    this(paramLogMessage.tag, paramLogMessage.level, paramLogMessage.message, paramLogMessage.throwableString);
  }
  
  LogMessage(String paramString1, UpsightLogger.Level paramLevel, String paramString2, String paramString3)
  {
    this.tag = paramString1;
    this.level = paramLevel;
    this.message = paramString2;
    this.throwableString = paramString3;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    LogMessage localLogMessage;
    do
    {
      for (;;)
      {
        return bool;
        if ((paramObject != null) && (getClass() == paramObject.getClass())) {
          break;
        }
        bool = false;
      }
      localLogMessage = (LogMessage)paramObject;
      if (this.id == null) {
        break;
      }
    } while (this.id.equals(localLogMessage.id));
    for (;;)
    {
      bool = false;
      break;
      if (localLogMessage.id == null) {
        break;
      }
    }
  }
  
  public UpsightLogger.Level getLevel()
  {
    return this.level;
  }
  
  public String getMessage()
  {
    return this.message;
  }
  
  public String getTag()
  {
    return this.tag;
  }
  
  public String getThrowableString()
  {
    return this.throwableString;
  }
  
  public int hashCode()
  {
    if (this.id != null) {}
    for (int i = this.id.hashCode();; i = 0) {
      return i;
    }
  }
  
  public void setLevel(UpsightLogger.Level paramLevel)
  {
    this.level = paramLevel;
  }
  
  public void setMessage(String paramString)
  {
    this.message = paramString;
  }
  
  public void setTag(String paramString)
  {
    this.tag = paramString;
  }
  
  public void setThrowableString(String paramString)
  {
    this.throwableString = paramString;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/logger/LogMessage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */