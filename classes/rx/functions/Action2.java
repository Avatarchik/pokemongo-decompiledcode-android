package rx.functions;

public abstract interface Action2<T1, T2>
  extends Action
{
  public abstract void call(T1 paramT1, T2 paramT2);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/rx/functions/Action2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */