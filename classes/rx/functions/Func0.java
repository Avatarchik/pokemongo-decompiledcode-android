package rx.functions;

import java.util.concurrent.Callable;

public abstract interface Func0<R>
  extends Function, Callable<R>
{
  public abstract R call();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/functions/Func0.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */