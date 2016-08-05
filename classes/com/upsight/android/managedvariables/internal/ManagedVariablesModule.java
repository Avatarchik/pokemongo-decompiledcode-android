package com.upsight.android.managedvariables.internal;

import com.upsight.android.managedvariables.internal.experience.UserExperienceModule;
import com.upsight.android.managedvariables.internal.type.UxmModule;
import dagger.Module;

@Module(includes={ResourceModule.class, UxmModule.class, UserExperienceModule.class, BaseManagedVariablesModule.class})
public class ManagedVariablesModule {}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/ManagedVariablesModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */