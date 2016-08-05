package com.upsight.android.managedvariables.internal;

import com.upsight.android.managedvariables.UpsightManagedVariablesComponent;
import dagger.Component;
import javax.inject.Singleton;

@Component(modules={ManagedVariablesModule.class})
@Singleton
public abstract interface ManagedVariablesComponent
  extends UpsightManagedVariablesComponent
{}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/ManagedVariablesComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */