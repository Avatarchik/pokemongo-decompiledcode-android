package com.upsight.android.managedvariables.internal.type;

import com.upsight.android.managedvariables.type.UpsightManagedString;
import com.upsight.android.persistence.annotation.UpsightStorableType;

class ManagedString
  extends UpsightManagedString
{
  static final String MODEL_TYPE = "com.upsight.uxm.string";
  
  ManagedString(String paramString1, String paramString2, String paramString3)
  {
    super(paramString1, paramString2, paramString3);
  }
  
  @UpsightStorableType("com.upsight.uxm.string")
  static class Model
    extends ManagedVariableModel<String>
  {}
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/ManagedString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */