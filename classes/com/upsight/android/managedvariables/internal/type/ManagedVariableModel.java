package com.upsight.android.managedvariables.internal.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.persistence.annotation.UpsightStorableIdentifier;

abstract class ManagedVariableModel<T>
{
  @UpsightStorableIdentifier
  String id;
  @JsonProperty("tag")
  String tag;
  @JsonProperty("value")
  T value;
  
  public String getTag()
  {
    return this.tag;
  }
  
  public T getValue()
  {
    return (T)this.value;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/ManagedVariableModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */