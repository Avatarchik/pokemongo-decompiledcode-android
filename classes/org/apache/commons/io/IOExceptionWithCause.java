package org.apache.commons.io;

import java.io.IOException;

public class IOExceptionWithCause
  extends IOException
{
  private static final long serialVersionUID = 1L;
  
  public IOExceptionWithCause(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    initCause(paramThrowable);
  }
  
  public IOExceptionWithCause(Throwable paramThrowable) {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/org/apache/commons/io/IOExceptionWithCause.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */