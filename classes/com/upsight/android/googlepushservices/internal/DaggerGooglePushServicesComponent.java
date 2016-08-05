package com.upsight.android.googlepushservices.internal;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightGooglePushServicesExtension;
import com.upsight.android.UpsightGooglePushServicesExtension_MembersInjector;
import com.upsight.android.analytics.internal.session.SessionManager;
import com.upsight.android.googlepushservices.UpsightGooglePushServicesApi;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.inject.Provider;

public final class DaggerGooglePushServicesComponent
  implements GooglePushServicesComponent
{
  private Provider<GooglePushServices> googlePushServicesProvider;
  private Provider<GoogleCloudMessaging> provideGoogleCloudMessagingProvider;
  private Provider<UpsightGooglePushServicesApi> provideGooglePushServicesApiProvider;
  private Provider<SessionManager> provideSessionManagerProvider;
  private Provider<UpsightContext> provideUpsightContextProvider;
  private MembersInjector<PushClickIntentService> pushClickIntentServiceMembersInjector;
  private MembersInjector<PushIntentService> pushIntentServiceMembersInjector;
  private MembersInjector<UpsightGooglePushServicesExtension> upsightGooglePushServicesExtensionMembersInjector;
  
  static
  {
    if (!DaggerGooglePushServicesComponent.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  private DaggerGooglePushServicesComponent(Builder paramBuilder)
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
    this.provideUpsightContextProvider = ScopedProvider.create(PushModule_ProvideUpsightContextFactory.create(paramBuilder.pushModule));
    this.googlePushServicesProvider = ScopedProvider.create(GooglePushServices_Factory.create(this.provideUpsightContextProvider));
    this.provideGooglePushServicesApiProvider = ScopedProvider.create(PushModule_ProvideGooglePushServicesApiFactory.create(paramBuilder.pushModule, this.googlePushServicesProvider));
    this.upsightGooglePushServicesExtensionMembersInjector = UpsightGooglePushServicesExtension_MembersInjector.create(MembersInjectors.noOp(), this.provideGooglePushServicesApiProvider);
    this.provideGoogleCloudMessagingProvider = ScopedProvider.create(GoogleCloudMessagingModule_ProvideGoogleCloudMessagingFactory.create(paramBuilder.googleCloudMessagingModule, this.provideUpsightContextProvider));
    this.pushIntentServiceMembersInjector = PushIntentService_MembersInjector.create(MembersInjectors.noOp(), this.provideGoogleCloudMessagingProvider);
    this.provideSessionManagerProvider = ScopedProvider.create(PushModule_ProvideSessionManagerFactory.create(paramBuilder.pushModule, this.provideUpsightContextProvider));
    this.pushClickIntentServiceMembersInjector = PushClickIntentService_MembersInjector.create(MembersInjectors.noOp(), this.provideSessionManagerProvider);
  }
  
  public void inject(UpsightGooglePushServicesExtension paramUpsightGooglePushServicesExtension)
  {
    this.upsightGooglePushServicesExtensionMembersInjector.injectMembers(paramUpsightGooglePushServicesExtension);
  }
  
  public void inject(PushClickIntentService paramPushClickIntentService)
  {
    this.pushClickIntentServiceMembersInjector.injectMembers(paramPushClickIntentService);
  }
  
  public void inject(PushIntentService paramPushIntentService)
  {
    this.pushIntentServiceMembersInjector.injectMembers(paramPushIntentService);
  }
  
  public static final class Builder
  {
    private GoogleCloudMessagingModule googleCloudMessagingModule;
    private GooglePushServicesModule googlePushServicesModule;
    private PushModule pushModule;
    
    public GooglePushServicesComponent build()
    {
      if (this.googlePushServicesModule == null) {
        this.googlePushServicesModule = new GooglePushServicesModule();
      }
      if (this.pushModule == null) {
        throw new IllegalStateException("pushModule must be set");
      }
      if (this.googleCloudMessagingModule == null) {
        this.googleCloudMessagingModule = new GoogleCloudMessagingModule();
      }
      return new DaggerGooglePushServicesComponent(this, null);
    }
    
    public Builder googleCloudMessagingModule(GoogleCloudMessagingModule paramGoogleCloudMessagingModule)
    {
      if (paramGoogleCloudMessagingModule == null) {
        throw new NullPointerException("googleCloudMessagingModule");
      }
      this.googleCloudMessagingModule = paramGoogleCloudMessagingModule;
      return this;
    }
    
    public Builder googlePushServicesModule(GooglePushServicesModule paramGooglePushServicesModule)
    {
      if (paramGooglePushServicesModule == null) {
        throw new NullPointerException("googlePushServicesModule");
      }
      this.googlePushServicesModule = paramGooglePushServicesModule;
      return this;
    }
    
    public Builder pushModule(PushModule paramPushModule)
    {
      if (paramPushModule == null) {
        throw new NullPointerException("pushModule");
      }
      this.pushModule = paramPushModule;
      return this;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/googlepushservices/internal/DaggerGooglePushServicesComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */