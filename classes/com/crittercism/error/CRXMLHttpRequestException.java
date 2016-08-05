package com.crittercism.error;

public class CRXMLHttpRequestException
  extends Exception
{
  private static final long serialVersionUID = 1515011187293165939L;
  
  public CRXMLHttpRequestException(String paramString)
  {
    this(paramString, null);
  }
  
  public CRXMLHttpRequestException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public CRXMLHttpRequestException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/crittercism/error/CRXMLHttpRequestException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */