package com.upsight.android.managedvariables.internal.experience;

import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightCoreComponent;
import com.upsight.android.managedvariables.experience.UpsightUserExperience;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class UserExperienceModule
{
  @Provides
  @Singleton
  UpsightUserExperience provideUserExperience(UpsightContext paramUpsightContext)
  {
    return new UserExperience(paramUpsightContext.getCoreComponent().bus());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/experience/UserExperienceModule.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */