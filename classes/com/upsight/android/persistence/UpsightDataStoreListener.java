package com.upsight.android.persistence;

import com.upsight.android.UpsightException;

public abstract interface UpsightDataStoreListener<T>
{
  public abstract void onFailure(UpsightException paramUpsightException);
  
  public abstract void onSuccess(T paramT);
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/persistence/UpsightDataStoreListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */