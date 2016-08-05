package com.upsight.android.internal.persistence.subscription;

import com.upsight.android.UpsightException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class SubscriptionHandler
{
  private final DataStoreEvent.Action mAction;
  private final Method mMethod;
  private final Object mTarget;
  private final String mType;
  
  SubscriptionHandler(Object paramObject, Method paramMethod, DataStoreEvent.Action paramAction, String paramString)
  {
    this.mTarget = paramObject;
    this.mMethod = paramMethod;
    this.mAction = paramAction;
    this.mType = paramString;
  }
  
  public void handle(DataStoreEvent paramDataStoreEvent)
    throws UpsightException
  {
    try
    {
      Method localMethod = this.mMethod;
      Object localObject = this.mTarget;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramDataStoreEvent.source;
      localMethod.invoke(localObject, arrayOfObject2);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = this.mTarget.getClass();
      arrayOfObject1[1] = this.mMethod.getName();
      throw new UpsightException(localIllegalAccessException, "Failed to invoke subscription method %s.%s: ", arrayOfObject1);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;) {}
    }
  }
  
  public boolean matches(DataStoreEvent.Action paramAction, String paramString)
  {
    if ((this.mAction.equals(paramAction)) && (this.mType.equals(paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/subscription/SubscriptionHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */