package rx.functions;

public abstract interface Func3<T1, T2, T3, R>
  extends Function
{
  public abstract R call(T1 paramT1, T2 paramT2, T3 paramT3);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/functions/Func3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */