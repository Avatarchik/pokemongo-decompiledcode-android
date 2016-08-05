package com.upsight.android.managedvariables.internal.type;

import com.upsight.android.managedvariables.type.UpsightManagedBoolean;
import com.upsight.android.persistence.annotation.UpsightStorableType;

class ManagedBoolean
  extends UpsightManagedBoolean
{
  static final String MODEL_TYPE = "com.upsight.uxm.boolean";
  
  ManagedBoolean(String paramString, Boolean paramBoolean1, Boolean paramBoolean2)
  {
    super(paramString, paramBoolean1, paramBoolean2);
  }
  
  @UpsightStorableType("com.upsight.uxm.boolean")
  static class Model
    extends ManagedVariableModel<Boolean>
  {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/ManagedBoolean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */