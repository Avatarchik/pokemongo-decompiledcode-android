package com.upsight.android.managedvariables.internal.experience;

import com.upsight.android.UpsightContext;
import com.upsight.android.managedvariables.experience.UpsightUserExperience;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UserExperienceModule_ProvideUserExperienceFactory
  implements Factory<UpsightUserExperience>
{
  private final UserExperienceModule module;
  private final Provider<UpsightContext> upsightProvider;
  
  static
  {
    if (!UserExperienceModule_ProvideUserExperienceFactory.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  public UserExperienceModule_ProvideUserExperienceFactory(UserExperienceModule paramUserExperienceModule, Provider<UpsightContext> paramProvider)
  {
    assert (paramUserExperienceModule != null);
    this.module = paramUserExperienceModule;
    assert (paramProvider != null);
    this.upsightProvider = paramProvider;
  }
  
  public static Factory<UpsightUserExperience> create(UserExperienceModule paramUserExperienceModule, Provider<UpsightContext> paramProvider)
  {
    return new UserExperienceModule_ProvideUserExperienceFactory(paramUserExperienceModule, paramProvider);
  }
  
  public UpsightUserExperience get()
  {
    UpsightUserExperience localUpsightUserExperience = this.module.provideUserExperience((UpsightContext)this.upsightProvider.get());
    if (localUpsightUserExperience == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return localUpsightUserExperience;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/experience/UserExperienceModule_ProvideUserExperienceFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */