package com.upsight.android.analytics.internal.action;

import java.util.HashMap;
import java.util.Map;

public abstract interface ActionableStore<T extends Actionable>
{
  public abstract T get(String paramString);
  
  public abstract boolean put(String paramString, T paramT);
  
  public abstract boolean remove(String paramString);
  
  public static class DefaultImpl<T extends Actionable>
    implements ActionableStore<T>
  {
    private Map<String, T> mMap = new HashMap();
    
    /**
     * @deprecated
     */
    public T get(String paramString)
    {
      try
      {
        Actionable localActionable = (Actionable)this.mMap.get(paramString);
        return localActionable;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    /**
     * @deprecated
     */
    public boolean put(String paramString, T paramT)
    {
      try
      {
        this.mMap.put(paramString, paramT);
        return true;
      }
      finally
      {
        localObject = finally;
        throw ((Throwable)localObject);
      }
    }
    
    /* Error */
    /**
     * @deprecated
     */
    public boolean remove(String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 20	com/upsight/android/analytics/internal/action/ActionableStore$DefaultImpl:mMap	Ljava/util/Map;
      //   6: aload_1
      //   7: invokeinterface 38 2 0
      //   12: astore_3
      //   13: aload_3
      //   14: ifnull +11 -> 25
      //   17: iconst_1
      //   18: istore 4
      //   20: aload_0
      //   21: monitorexit
      //   22: iload 4
      //   24: ireturn
      //   25: iconst_0
      //   26: istore 4
      //   28: goto -8 -> 20
      //   31: astore_2
      //   32: aload_0
      //   33: monitorexit
      //   34: aload_2
      //   35: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	36	0	this	DefaultImpl
      //   0	36	1	paramString	String
      //   31	4	2	localObject1	Object
      //   12	2	3	localObject2	Object
      //   18	9	4	bool	boolean
      // Exception table:
      //   from	to	target	type
      //   2	13	31	finally
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/action/ActionableStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */