package com.upsight.android.managedvariables;

import com.upsight.android.UpsightExtension.BaseComponent;
import com.upsight.android.UpsightManagedVariablesExtension;
import com.upsight.android.managedvariables.internal.type.UxmSchema;

public abstract interface UpsightManagedVariablesComponent
  extends UpsightExtension.BaseComponent<UpsightManagedVariablesExtension>
{
  public abstract UxmSchema uxmSchema();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/UpsightManagedVariablesComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */