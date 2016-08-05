package com.upsight.android.internal.persistence.storable;

import com.upsight.android.UpsightException;

class StorableStaticTypeAccessor<T>
  implements StorableTypeAccessor<T>
{
  private final String mType;
  
  public StorableStaticTypeAccessor(String paramString)
  {
    this.mType = paramString;
  }
  
  public String getType()
    throws UpsightException
  {
    return this.mType;
  }
  
  public String getType(T paramT)
    throws UpsightException
  {
    return this.mType;
  }
  
  public boolean isDynamic()
  {
    return false;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/persistence/storable/StorableStaticTypeAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */