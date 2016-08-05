package rx.exceptions;

public class OnErrorNotImplementedException
  extends RuntimeException
{
  private static final long serialVersionUID = -6298857009889503852L;
  
  public OnErrorNotImplementedException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public OnErrorNotImplementedException(Throwable paramThrowable)
  {
    super(paramThrowable.getMessage(), paramThrowable);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/exceptions/OnErrorNotImplementedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */