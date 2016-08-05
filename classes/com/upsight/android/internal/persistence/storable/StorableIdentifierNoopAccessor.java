package com.upsight.android.internal.persistence.storable;

import com.upsight.android.UpsightException;

class StorableIdentifierNoopAccessor
  implements StorableIdentifierAccessor
{
  public String getId(Object paramObject)
    throws UpsightException
  {
    return null;
  }
  
  public void setId(Object paramObject, String paramString)
    throws UpsightException
  {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/storable/StorableIdentifierNoopAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */