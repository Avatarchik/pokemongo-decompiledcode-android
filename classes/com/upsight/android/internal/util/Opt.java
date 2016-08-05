package com.upsight.android.internal.util;

public class Opt<T>
{
  private T mObject;
  
  private Opt(T paramT)
  {
    this.mObject = paramT;
  }
  
  public static <T> Opt<T> absent()
  {
    return new Opt(null);
  }
  
  public static <T> Opt<T> from(T paramT)
  {
    return new Opt(paramT);
  }
  
  public T get()
  {
    return (T)this.mObject;
  }
  
  public boolean isPresent()
  {
    if (this.mObject != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/util/Opt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */