package com.upsight.android.persistence;

import com.upsight.android.UpsightException;

public abstract interface UpsightStorableSerializer<T>
{
  public abstract T fromString(String paramString)
    throws UpsightException;
  
  public abstract String toString(T paramT)
    throws UpsightException;
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/persistence/UpsightStorableSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */