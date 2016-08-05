package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class EventHandler
{
  private final int hashCode;
  private final Method method;
  private final Object target;
  private boolean valid = true;
  
  EventHandler(Object paramObject, Method paramMethod)
  {
    if (paramObject == null) {
      throw new NullPointerException("EventHandler target cannot be null.");
    }
    if (paramMethod == null) {
      throw new NullPointerException("EventHandler method cannot be null.");
    }
    this.target = paramObject;
    this.method = paramMethod;
    paramMethod.setAccessible(true);
    this.hashCode = (31 * (31 + paramMethod.hashCode()) + paramObject.hashCode());
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if (paramObject == null)
      {
        bool = false;
      }
      else if (getClass() != paramObject.getClass())
      {
        bool = false;
      }
      else
      {
        EventHandler localEventHandler = (EventHandler)paramObject;
        if ((!this.method.equals(localEventHandler.method)) || (this.target != localEventHandler.target)) {
          bool = false;
        }
      }
    }
  }
  
  public void handleEvent(Object paramObject)
    throws InvocationTargetException
  {
    if (!this.valid) {
      throw new IllegalStateException(toString() + " has been invalidated and can no longer handle events.");
    }
    try
    {
      Method localMethod = this.method;
      Object localObject = this.target;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramObject;
      localMethod.invoke(localObject, arrayOfObject);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new AssertionError(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      if ((localInvocationTargetException.getCause() instanceof Error)) {
        throw ((Error)localInvocationTargetException.getCause());
      }
      throw localInvocationTargetException;
    }
  }
  
  public int hashCode()
  {
    return this.hashCode;
  }
  
  public void invalidate()
  {
    this.valid = false;
  }
  
  public boolean isValid()
  {
    return this.valid;
  }
  
  public String toString()
  {
    return "[EventHandler " + this.method + "]";
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/squareup/otto/EventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */