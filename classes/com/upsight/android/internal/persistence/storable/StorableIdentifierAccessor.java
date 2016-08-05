package com.upsight.android.internal.persistence.storable;

import com.upsight.android.UpsightException;

public abstract interface StorableIdentifierAccessor
{
  public abstract String getId(Object paramObject)
    throws UpsightException;
  
  public abstract void setId(Object paramObject, String paramString)
    throws UpsightException;
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/storable/StorableIdentifierAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */