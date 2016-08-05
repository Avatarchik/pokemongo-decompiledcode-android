package com.fasterxml.jackson.core;

import java.io.IOException;

public class JsonProcessingException
  extends IOException
{
  static final long serialVersionUID = 123L;
  protected JsonLocation _location;
  
  protected JsonProcessingException(String paramString)
  {
    super(paramString);
  }
  
  protected JsonProcessingException(String paramString, JsonLocation paramJsonLocation)
  {
    this(paramString, paramJsonLocation, null);
  }
  
  protected JsonProcessingException(String paramString, JsonLocation paramJsonLocation, Throwable paramThrowable)
  {
    super(paramString);
    if (paramThrowable != null) {
      initCause(paramThrowable);
    }
    this._location = paramJsonLocation;
  }
  
  protected JsonProcessingException(String paramString, Throwable paramThrowable)
  {
    this(paramString, null, paramThrowable);
  }
  
  protected JsonProcessingException(Throwable paramThrowable)
  {
    this(null, null, paramThrowable);
  }
  
  public JsonLocation getLocation()
  {
    return this._location;
  }
  
  public String getMessage()
  {
    String str1 = super.getMessage();
    if (str1 == null) {
      str1 = "N/A";
    }
    JsonLocation localJsonLocation = getLocation();
    String str2 = getMessageSuffix();
    if ((localJsonLocation != null) || (str2 != null))
    {
      StringBuilder localStringBuilder = new StringBuilder(100);
      localStringBuilder.append(str1);
      if (str2 != null) {
        localStringBuilder.append(str2);
      }
      if (localJsonLocation != null)
      {
        localStringBuilder.append('\n');
        localStringBuilder.append(" at ");
        localStringBuilder.append(localJsonLocation.toString());
      }
      str1 = localStringBuilder.toString();
    }
    return str1;
  }
  
  protected String getMessageSuffix()
  {
    return null;
  }
  
  public String getOriginalMessage()
  {
    return super.getMessage();
  }
  
  public String toString()
  {
    return getClass().getName() + ": " + getMessage();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/fasterxml/jackson/core/JsonProcessingException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */