package com.upsight.android.managedvariables.internal.type;

import java.util.Observable;

public abstract class ManagedVariable<T>
  extends Observable
{
  private T mDefaultValue;
  private String mTag;
  private T mValue;
  
  protected ManagedVariable(String paramString, T paramT1, T paramT2)
  {
    this.mTag = paramString;
    this.mDefaultValue = paramT1;
    if (paramT2 != null) {}
    for (;;)
    {
      this.mValue = paramT2;
      return;
      paramT2 = paramT1;
    }
  }
  
  /**
   * @deprecated
   */
  public T get()
  {
    try
    {
      Object localObject2 = this.mValue;
      return (T)localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw ((Throwable)localObject1);
    }
  }
  
  public String getTag()
  {
    return this.mTag;
  }
  
  /* Error */
  /**
   * @deprecated
   */
  void set(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: aload_0
    //   4: getfield 22	com/upsight/android/managedvariables/internal/type/ManagedVariable:mValue	Ljava/lang/Object;
    //   7: if_acmpeq +20 -> 27
    //   10: aload_1
    //   11: ifnull +19 -> 30
    //   14: aload_0
    //   15: aload_1
    //   16: putfield 22	com/upsight/android/managedvariables/internal/type/ManagedVariable:mValue	Ljava/lang/Object;
    //   19: aload_0
    //   20: invokevirtual 31	com/upsight/android/managedvariables/internal/type/ManagedVariable:setChanged	()V
    //   23: aload_0
    //   24: invokevirtual 34	com/upsight/android/managedvariables/internal/type/ManagedVariable:notifyObservers	()V
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: aload_0
    //   31: aload_0
    //   32: getfield 20	com/upsight/android/managedvariables/internal/type/ManagedVariable:mDefaultValue	Ljava/lang/Object;
    //   35: putfield 22	com/upsight/android/managedvariables/internal/type/ManagedVariable:mValue	Ljava/lang/Object;
    //   38: goto -19 -> 19
    //   41: astore_2
    //   42: aload_0
    //   43: monitorexit
    //   44: aload_2
    //   45: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	46	0	this	ManagedVariable
    //   0	46	1	paramT	T
    //   41	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	27	41	finally
    //   30	38	41	finally
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/ManagedVariable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */