package com.upsight.android.managedvariables.internal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightManagedVariablesExtension;
import com.upsight.android.UpsightManagedVariablesExtension_MembersInjector;
import com.upsight.android.managedvariables.UpsightManagedVariablesApi;
import com.upsight.android.managedvariables.experience.UpsightUserExperience;
import com.upsight.android.managedvariables.internal.experience.UserExperienceModule;
import com.upsight.android.managedvariables.internal.experience.UserExperienceModule_ProvideUserExperienceFactory;
import com.upsight.android.managedvariables.internal.type.ManagedVariableManager;
import com.upsight.android.managedvariables.internal.type.UxmBlockProvider;
import com.upsight.android.managedvariables.internal.type.UxmContentFactory;
import com.upsight.android.managedvariables.internal.type.UxmModule;
import com.upsight.android.managedvariables.internal.type.UxmModule_ProvideManagedVariableManagerFactory;
import com.upsight.android.managedvariables.internal.type.UxmModule_ProvideUxmBlockProviderFactory;
import com.upsight.android.managedvariables.internal.type.UxmModule_ProvideUxmContentFactoryFactory;
import com.upsight.android.managedvariables.internal.type.UxmModule_ProvideUxmSchemaFactory;
import com.upsight.android.managedvariables.internal.type.UxmModule_ProvideUxmSchemaMapperFactory;
import com.upsight.android.managedvariables.internal.type.UxmModule_ProvideUxmSchemaRawStringFactory;
import com.upsight.android.managedvariables.internal.type.UxmSchema;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.inject.Provider;
import rx.Scheduler;

public final class DaggerManagedVariablesComponent
  implements ManagedVariablesComponent
{
  private Provider<Scheduler> provideMainSchedulerProvider;
  private Provider<ManagedVariableManager> provideManagedVariableManagerProvider;
  private Provider<UpsightManagedVariablesApi> provideManagedVariablesApiProvider;
  private Provider<UpsightContext> provideUpsightContextProvider;
  private Provider<UpsightUserExperience> provideUserExperienceProvider;
  private Provider<UxmBlockProvider> provideUxmBlockProvider;
  private Provider<UxmContentFactory> provideUxmContentFactoryProvider;
  private Provider<ObjectMapper> provideUxmSchemaMapperProvider;
  private Provider<UxmSchema> provideUxmSchemaProvider;
  private Provider<String> provideUxmSchemaRawStringProvider;
  private Provider<Integer> provideUxmSchemaResourceProvider;
  private MembersInjector<UpsightManagedVariablesExtension> upsightManagedVariablesExtensionMembersInjector;
  
  static
  {
    if (!DaggerManagedVariablesComponent.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  private DaggerManagedVariablesComponent(Builder paramBuilder)
  {
    assert (paramBuilder != null);
    initialize(paramBuilder);
  }
  
  public static Builder builder()
  {
    return new Builder(null);
  }
  
  private void initialize(Builder paramBuilder)
  {
    this.provideUpsightContextProvider = ScopedProvider.create(BaseManagedVariablesModule_ProvideUpsightContextFactory.create(paramBuilder.baseManagedVariablesModule));
    this.provideMainSchedulerProvider = ScopedProvider.create(BaseManagedVariablesModule_ProvideMainSchedulerFactory.create(paramBuilder.baseManagedVariablesModule));
    this.provideUxmSchemaMapperProvider = ScopedProvider.create(UxmModule_ProvideUxmSchemaMapperFactory.create(paramBuilder.uxmModule, this.provideUpsightContextProvider));
    this.provideUxmSchemaResourceProvider = ScopedProvider.create(ResourceModule_ProvideUxmSchemaResourceFactory.create(paramBuilder.resourceModule));
    this.provideUxmSchemaRawStringProvider = ScopedProvider.create(UxmModule_ProvideUxmSchemaRawStringFactory.create(paramBuilder.uxmModule, this.provideUpsightContextProvider, this.provideUxmSchemaResourceProvider));
    this.provideUxmSchemaProvider = ScopedProvider.create(UxmModule_ProvideUxmSchemaFactory.create(paramBuilder.uxmModule, this.provideUpsightContextProvider, this.provideUxmSchemaMapperProvider, this.provideUxmSchemaRawStringProvider));
    this.provideManagedVariableManagerProvider = ScopedProvider.create(UxmModule_ProvideManagedVariableManagerFactory.create(paramBuilder.uxmModule, this.provideUpsightContextProvider, this.provideMainSchedulerProvider, this.provideUxmSchemaProvider));
    this.provideUserExperienceProvider = ScopedProvider.create(UserExperienceModule_ProvideUserExperienceFactory.create(paramBuilder.userExperienceModule, this.provideUpsightContextProvider));
    this.provideManagedVariablesApiProvider = ScopedProvider.create(BaseManagedVariablesModule_ProvideManagedVariablesApiFactory.create(paramBuilder.baseManagedVariablesModule, this.provideManagedVariableManagerProvider, this.provideUserExperienceProvider));
    this.provideUxmContentFactoryProvider = ScopedProvider.create(UxmModule_ProvideUxmContentFactoryFactory.create(paramBuilder.uxmModule, this.provideUpsightContextProvider, this.provideMainSchedulerProvider, this.provideUserExperienceProvider));
    this.provideUxmBlockProvider = ScopedProvider.create(UxmModule_ProvideUxmBlockProviderFactory.create(paramBuilder.uxmModule, this.provideUpsightContextProvider, this.provideUxmSchemaRawStringProvider, this.provideUxmSchemaProvider));
    this.upsightManagedVariablesExtensionMembersInjector = UpsightManagedVariablesExtension_MembersInjector.create(MembersInjectors.noOp(), this.provideManagedVariablesApiProvider, this.provideUxmContentFactoryProvider, this.provideUxmBlockProvider);
  }
  
  public void inject(UpsightManagedVariablesExtension paramUpsightManagedVariablesExtension)
  {
    this.upsightManagedVariablesExtensionMembersInjector.injectMembers(paramUpsightManagedVariablesExtension);
  }
  
  public UxmSchema uxmSchema()
  {
    return (UxmSchema)this.provideUxmSchemaProvider.get();
  }
  
  public static final class Builder
  {
    private BaseManagedVariablesModule baseManagedVariablesModule;
    private ManagedVariablesModule managedVariablesModule;
    private ResourceModule resourceModule;
    private UserExperienceModule userExperienceModule;
    private UxmModule uxmModule;
    
    public Builder baseManagedVariablesModule(BaseManagedVariablesModule paramBaseManagedVariablesModule)
    {
      if (paramBaseManagedVariablesModule == null) {
        throw new NullPointerException("baseManagedVariablesModule");
      }
      this.baseManagedVariablesModule = paramBaseManagedVariablesModule;
      return this;
    }
    
    public ManagedVariablesComponent build()
    {
      if (this.managedVariablesModule == null) {
        this.managedVariablesModule = new ManagedVariablesModule();
      }
      if (this.resourceModule == null) {
        this.resourceModule = new ResourceModule();
      }
      if (this.uxmModule == null) {
        this.uxmModule = new UxmModule();
      }
      if (this.userExperienceModule == null) {
        this.userExperienceModule = new UserExperienceModule();
      }
      if (this.baseManagedVariablesModule == null) {
        throw new IllegalStateException("baseManagedVariablesModule must be set");
      }
      return new DaggerManagedVariablesComponent(this, null);
    }
    
    public Builder managedVariablesModule(ManagedVariablesModule paramManagedVariablesModule)
    {
      if (paramManagedVariablesModule == null) {
        throw new NullPointerException("managedVariablesModule");
      }
      this.managedVariablesModule = paramManagedVariablesModule;
      return this;
    }
    
    public Builder resourceModule(ResourceModule paramResourceModule)
    {
      if (paramResourceModule == null) {
        throw new NullPointerException("resourceModule");
      }
      this.resourceModule = paramResourceModule;
      return this;
    }
    
    public Builder userExperienceModule(UserExperienceModule paramUserExperienceModule)
    {
      if (paramUserExperienceModule == null) {
        throw new NullPointerException("userExperienceModule");
      }
      this.userExperienceModule = paramUserExperienceModule;
      return this;
    }
    
    public Builder uxmModule(UxmModule paramUxmModule)
    {
      if (paramUxmModule == null) {
        throw new NullPointerException("uxmModule");
      }
      this.uxmModule = paramUxmModule;
      return this;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/DaggerManagedVariablesComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */