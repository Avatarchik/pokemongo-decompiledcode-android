package com.upsight.android.internal.persistence.storable;

import com.upsight.android.UpsightException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class StorableMethodTypeAccessor<T>
  implements StorableTypeAccessor<T>
{
  private final Method mMethod;
  
  public StorableMethodTypeAccessor(Method paramMethod)
  {
    this.mMethod = paramMethod;
  }
  
  public String getType()
    throws UpsightException
  {
    return null;
  }
  
  public String getType(T paramT)
    throws UpsightException
  {
    try
    {
      String str = (String)this.mMethod.invoke(paramT, new Object[0]);
      return str;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new UpsightException(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;) {}
    }
  }
  
  public boolean isDynamic()
  {
    return true;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/storable/StorableMethodTypeAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */