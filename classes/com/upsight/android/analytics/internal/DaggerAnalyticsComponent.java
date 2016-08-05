package com.upsight.android.analytics.internal;

import android.app.Application.ActivityLifecycleCallbacks;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.UpsightAnalyticsExtension;
import com.upsight.android.UpsightAnalyticsExtension_MembersInjector;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.UpsightAnalyticsApi;
import com.upsight.android.analytics.UpsightGooglePlayHelper;
import com.upsight.android.analytics.internal.association.AssociationManager;
import com.upsight.android.analytics.internal.association.AssociationModule;
import com.upsight.android.analytics.internal.association.AssociationModule_ProvideAssociationManagerFactory;
import com.upsight.android.analytics.internal.configuration.ConfigurationManager;
import com.upsight.android.analytics.internal.configuration.ConfigurationModule;
import com.upsight.android.analytics.internal.configuration.ConfigurationModule_ProvideConfigurationManagerFactory;
import com.upsight.android.analytics.internal.configuration.ConfigurationResponseParser;
import com.upsight.android.analytics.internal.configuration.ConfigurationResponseParser_Factory;
import com.upsight.android.analytics.internal.configuration.ManagerConfigParser;
import com.upsight.android.analytics.internal.configuration.ManagerConfigParser_Factory;
import com.upsight.android.analytics.internal.dispatcher.DispatchModule;
import com.upsight.android.analytics.internal.dispatcher.DispatchModule_ProvideDispatcherFactory;
import com.upsight.android.analytics.internal.dispatcher.Dispatcher;
import com.upsight.android.analytics.internal.dispatcher.delivery.DeliveryModule;
import com.upsight.android.analytics.internal.dispatcher.delivery.DeliveryModule_ProvideQueueBuilderFactory;
import com.upsight.android.analytics.internal.dispatcher.delivery.DeliveryModule_ProvideResponseVerifierFactory;
import com.upsight.android.analytics.internal.dispatcher.delivery.QueueBuilder;
import com.upsight.android.analytics.internal.dispatcher.delivery.ResponseParser_Factory;
import com.upsight.android.analytics.internal.dispatcher.delivery.SignatureVerifier;
import com.upsight.android.analytics.internal.dispatcher.routing.RouterBuilder;
import com.upsight.android.analytics.internal.dispatcher.routing.RoutingModule;
import com.upsight.android.analytics.internal.dispatcher.routing.RoutingModule_ProvideRouterBuilderFactory;
import com.upsight.android.analytics.internal.dispatcher.schema.SchemaModule;
import com.upsight.android.analytics.internal.dispatcher.schema.SchemaModule_ProvideSchemaSelectorBuilderFactory;
import com.upsight.android.analytics.internal.dispatcher.schema.SchemaSelectorBuilder;
import com.upsight.android.analytics.internal.provider.LocationTracker_Factory;
import com.upsight.android.analytics.internal.provider.OptOutStatus_Factory;
import com.upsight.android.analytics.internal.provider.ProviderModule;
import com.upsight.android.analytics.internal.provider.ProviderModule_ProvidesOptOutStatusFactory;
import com.upsight.android.analytics.internal.provider.ProviderModule_ProvidesUpsightLocationTrackerFactory;
import com.upsight.android.analytics.internal.provider.ProviderModule_ProvidesUpsightUserAttributesFactory;
import com.upsight.android.analytics.internal.provider.UserAttributes_Factory;
import com.upsight.android.analytics.internal.session.ActivityLifecycleTracker;
import com.upsight.android.analytics.internal.session.ActivityLifecycleTracker_Factory;
import com.upsight.android.analytics.internal.session.Clock;
import com.upsight.android.analytics.internal.session.LifecycleTrackerModule;
import com.upsight.android.analytics.internal.session.LifecycleTrackerModule_ProvideUpsightLifeCycleCallbacksFactory;
import com.upsight.android.analytics.internal.session.ManualTracker_Factory;
import com.upsight.android.analytics.internal.session.SessionManager;
import com.upsight.android.analytics.internal.session.SessionManagerImpl;
import com.upsight.android.analytics.internal.session.SessionModule;
import com.upsight.android.analytics.internal.session.SessionModule_ProvidesSessionManagerFactory;
import com.upsight.android.analytics.internal.session.SessionModule_ProvidesSessionManagerImplFactory;
import com.upsight.android.analytics.provider.UpsightLocationTracker;
import com.upsight.android.analytics.provider.UpsightOptOutStatus;
import com.upsight.android.analytics.provider.UpsightUserAttributes;
import com.upsight.android.internal.util.Opt;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import dagger.internal.ScopedProvider;
import javax.inject.Provider;
import rx.Scheduler;

public final class DaggerAnalyticsComponent
  implements AnalyticsComponent
{
  private Provider<ActivityLifecycleTracker> activityLifecycleTrackerProvider;
  private Provider<AnalyticsContext> analyticsContextProvider;
  private Provider<Analytics> analyticsProvider;
  private final com.upsight.android.analytics.internal.dispatcher.DaggerAnalyticsComponent_PackageProxy com_upsight_android_analytics_internal_dispatcher_Proxy = new com.upsight.android.analytics.internal.dispatcher.DaggerAnalyticsComponent_PackageProxy();
  private final com.upsight.android.analytics.internal.dispatcher.delivery.DaggerAnalyticsComponent_PackageProxy com_upsight_android_analytics_internal_dispatcher_delivery_Proxy = new com.upsight.android.analytics.internal.dispatcher.delivery.DaggerAnalyticsComponent_PackageProxy();
  private final com.upsight.android.analytics.internal.provider.DaggerAnalyticsComponent_PackageProxy com_upsight_android_analytics_internal_provider_Proxy = new com.upsight.android.analytics.internal.provider.DaggerAnalyticsComponent_PackageProxy();
  private final com.upsight.android.analytics.internal.session.DaggerAnalyticsComponent_PackageProxy com_upsight_android_analytics_internal_session_Proxy = new com.upsight.android.analytics.internal.session.DaggerAnalyticsComponent_PackageProxy();
  private Provider<ConfigurationResponseParser> configurationResponseParserProvider;
  private MembersInjector<DispatcherService> dispatcherServiceMembersInjector;
  private Provider<ManagerConfigParser> managerConfigParserProvider;
  private Provider<AssociationManager> provideAssociationManagerProvider;
  private Provider<Clock> provideClockProvider;
  private Provider<ObjectMapper> provideConfigMapperProvider;
  private Provider<ConfigurationManager> provideConfigurationManagerProvider;
  private Provider<Dispatcher> provideDispatcherProvider;
  private Provider<UpsightGooglePlayHelper> provideGooglePlayHelperProvider;
  private Provider<QueueBuilder> provideQueueBuilderProvider;
  private Provider<SignatureVerifier> provideResponseVerifierProvider;
  private Provider<RouterBuilder> provideRouterBuilderProvider;
  private Provider<Scheduler> provideSchedulingExecutorProvider;
  private Provider<SchemaSelectorBuilder> provideSchemaSelectorBuilderProvider;
  private Provider<Scheduler> provideSendingExecutorProvider;
  private Provider<Opt<Thread.UncaughtExceptionHandler>> provideUncaughtExceptionHandlerProvider;
  private Provider<UpsightAnalyticsApi> provideUpsightAnalyticsApiProvider;
  private Provider<UpsightContext> provideUpsightContextProvider;
  private Provider<Application.ActivityLifecycleCallbacks> provideUpsightLifeCycleCallbacksProvider;
  private Provider<UpsightOptOutStatus> providesOptOutStatusProvider;
  private Provider<SessionManagerImpl> providesSessionManagerImplProvider;
  private Provider<SessionManager> providesSessionManagerProvider;
  private Provider<UpsightLocationTracker> providesUpsightLocationTrackerProvider;
  private Provider<UpsightUserAttributes> providesUpsightUserAttributesProvider;
  private MembersInjector<UpsightAnalyticsExtension> upsightAnalyticsExtensionMembersInjector;
  
  static
  {
    if (!DaggerAnalyticsComponent.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  private DaggerAnalyticsComponent(Builder paramBuilder)
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
    this.provideUncaughtExceptionHandlerProvider = ScopedProvider.create(BaseAnalyticsModule_ProvideUncaughtExceptionHandlerFactory.create(paramBuilder.baseAnalyticsModule));
    this.provideUpsightContextProvider = ScopedProvider.create(BaseAnalyticsModule_ProvideUpsightContextFactory.create(paramBuilder.baseAnalyticsModule));
    this.provideConfigMapperProvider = ScopedProvider.create(ConfigObjectMapperModule_ProvideConfigMapperFactory.create(paramBuilder.configObjectMapperModule, this.provideUpsightContextProvider));
    this.com_upsight_android_analytics_internal_session_Proxy.configParserProvider = com.upsight.android.analytics.internal.session.ConfigParser_Factory.create(this.provideConfigMapperProvider);
    this.provideClockProvider = ScopedProvider.create(BaseAnalyticsModule_ProvideClockFactory.create(paramBuilder.baseAnalyticsModule));
    this.providesSessionManagerImplProvider = ScopedProvider.create(SessionModule_ProvidesSessionManagerImplFactory.create(paramBuilder.sessionModule, this.provideUpsightContextProvider, this.com_upsight_android_analytics_internal_session_Proxy.configParserProvider, this.provideClockProvider));
    this.providesSessionManagerProvider = ScopedProvider.create(SessionModule_ProvidesSessionManagerFactory.create(paramBuilder.sessionModule, this.providesSessionManagerImplProvider));
    this.provideSchemaSelectorBuilderProvider = ScopedProvider.create(SchemaModule_ProvideSchemaSelectorBuilderFactory.create(paramBuilder.schemaModule, this.provideUpsightContextProvider));
    this.provideAssociationManagerProvider = ScopedProvider.create(AssociationModule_ProvideAssociationManagerFactory.create(paramBuilder.associationModule, this.provideUpsightContextProvider, this.provideClockProvider));
    this.com_upsight_android_analytics_internal_provider_Proxy.optOutStatusProvider = ScopedProvider.create(OptOutStatus_Factory.create(MembersInjectors.noOp(), this.provideUpsightContextProvider));
    this.providesOptOutStatusProvider = ScopedProvider.create(ProviderModule_ProvidesOptOutStatusFactory.create(paramBuilder.providerModule, this.com_upsight_android_analytics_internal_provider_Proxy.optOutStatusProvider));
    this.com_upsight_android_analytics_internal_provider_Proxy.locationTrackerProvider = ScopedProvider.create(LocationTracker_Factory.create(MembersInjectors.noOp(), this.provideUpsightContextProvider));
    this.providesUpsightLocationTrackerProvider = ScopedProvider.create(ProviderModule_ProvidesUpsightLocationTrackerFactory.create(paramBuilder.providerModule, this.com_upsight_android_analytics_internal_provider_Proxy.locationTrackerProvider));
    this.com_upsight_android_analytics_internal_provider_Proxy.userAttributesProvider = ScopedProvider.create(UserAttributes_Factory.create(MembersInjectors.noOp(), this.provideUpsightContextProvider));
    this.providesUpsightUserAttributesProvider = ScopedProvider.create(ProviderModule_ProvidesUpsightUserAttributesFactory.create(paramBuilder.providerModule, this.com_upsight_android_analytics_internal_provider_Proxy.userAttributesProvider));
    this.provideGooglePlayHelperProvider = ScopedProvider.create(BaseAnalyticsModule_ProvideGooglePlayHelperFactory.create(paramBuilder.baseAnalyticsModule, this.provideUpsightContextProvider));
    this.analyticsProvider = ScopedProvider.create(Analytics_Factory.create(this.provideUpsightContextProvider, this.providesSessionManagerProvider, this.provideSchemaSelectorBuilderProvider, this.provideAssociationManagerProvider, this.providesOptOutStatusProvider, this.providesUpsightLocationTrackerProvider, this.providesUpsightUserAttributesProvider, this.provideGooglePlayHelperProvider));
    this.provideUpsightAnalyticsApiProvider = ScopedProvider.create(AnalyticsApiModule_ProvideUpsightAnalyticsApiFactory.create(paramBuilder.analyticsApiModule, this.analyticsProvider));
    this.com_upsight_android_analytics_internal_session_Proxy.manualTrackerProvider = ScopedProvider.create(ManualTracker_Factory.create(this.providesSessionManagerProvider, this.provideUpsightContextProvider));
    this.activityLifecycleTrackerProvider = ActivityLifecycleTracker_Factory.create(this.com_upsight_android_analytics_internal_session_Proxy.manualTrackerProvider);
    this.provideUpsightLifeCycleCallbacksProvider = ScopedProvider.create(LifecycleTrackerModule_ProvideUpsightLifeCycleCallbacksFactory.create(paramBuilder.lifecycleTrackerModule, this.activityLifecycleTrackerProvider));
    this.upsightAnalyticsExtensionMembersInjector = UpsightAnalyticsExtension_MembersInjector.create(MembersInjectors.noOp(), this.provideUncaughtExceptionHandlerProvider, this.provideUpsightAnalyticsApiProvider, this.provideClockProvider, this.provideUpsightLifeCycleCallbacksProvider, this.provideAssociationManagerProvider);
    this.configurationResponseParserProvider = ScopedProvider.create(ConfigurationResponseParser_Factory.create(this.provideConfigMapperProvider, this.providesSessionManagerProvider));
    this.managerConfigParserProvider = ScopedProvider.create(ManagerConfigParser_Factory.create(this.provideConfigMapperProvider));
    this.provideConfigurationManagerProvider = ScopedProvider.create(ConfigurationModule_ProvideConfigurationManagerFactory.create(paramBuilder.configurationModule, this.provideUpsightContextProvider, this.configurationResponseParserProvider, this.managerConfigParserProvider));
    this.analyticsContextProvider = AnalyticsContext_Factory.create(MembersInjectors.noOp(), this.provideUpsightContextProvider);
    this.com_upsight_android_analytics_internal_dispatcher_Proxy.configParserProvider = ScopedProvider.create(com.upsight.android.analytics.internal.dispatcher.ConfigParser_Factory.create(this.provideUpsightContextProvider, this.provideConfigMapperProvider));
    this.provideSendingExecutorProvider = ScopedProvider.create(AnalyticsSchedulersModule_ProvideSendingExecutorFactory.create(paramBuilder.analyticsSchedulersModule));
    this.provideSchedulingExecutorProvider = ScopedProvider.create(AnalyticsSchedulersModule_ProvideSchedulingExecutorFactory.create(paramBuilder.analyticsSchedulersModule));
    this.provideResponseVerifierProvider = ScopedProvider.create(DeliveryModule_ProvideResponseVerifierFactory.create(paramBuilder.deliveryModule, this.provideUpsightContextProvider));
    this.com_upsight_android_analytics_internal_dispatcher_delivery_Proxy.responseParserProvider = ResponseParser_Factory.create(this.provideConfigMapperProvider);
    this.provideQueueBuilderProvider = ScopedProvider.create(DeliveryModule_ProvideQueueBuilderFactory.create(paramBuilder.deliveryModule, this.provideUpsightContextProvider, this.provideClockProvider, this.provideSendingExecutorProvider, this.provideSchedulingExecutorProvider, this.provideResponseVerifierProvider, this.com_upsight_android_analytics_internal_dispatcher_delivery_Proxy.responseParserProvider));
    this.provideRouterBuilderProvider = ScopedProvider.create(RoutingModule_ProvideRouterBuilderFactory.create(paramBuilder.routingModule, this.provideUpsightContextProvider, this.provideQueueBuilderProvider));
    this.provideDispatcherProvider = ScopedProvider.create(DispatchModule_ProvideDispatcherFactory.create(paramBuilder.dispatchModule, this.provideUpsightContextProvider, this.providesSessionManagerProvider, this.analyticsContextProvider, this.com_upsight_android_analytics_internal_dispatcher_Proxy.configParserProvider, this.provideRouterBuilderProvider, this.provideSchemaSelectorBuilderProvider));
    this.dispatcherServiceMembersInjector = DispatcherService_MembersInjector.create(MembersInjectors.noOp(), this.provideConfigurationManagerProvider, this.provideDispatcherProvider);
  }
  
  public Clock clock()
  {
    return (Clock)this.provideClockProvider.get();
  }
  
  public void inject(UpsightAnalyticsExtension paramUpsightAnalyticsExtension)
  {
    this.upsightAnalyticsExtensionMembersInjector.injectMembers(paramUpsightAnalyticsExtension);
  }
  
  public void inject(DispatcherService paramDispatcherService)
  {
    this.dispatcherServiceMembersInjector.injectMembers(paramDispatcherService);
  }
  
  public SessionManager sessionManager()
  {
    return (SessionManager)this.providesSessionManagerProvider.get();
  }
  
  public static final class Builder
  {
    private AnalyticsApiModule analyticsApiModule;
    private AnalyticsModule analyticsModule;
    private AnalyticsSchedulersModule analyticsSchedulersModule;
    private AssociationModule associationModule;
    private BaseAnalyticsModule baseAnalyticsModule;
    private ConfigObjectMapperModule configObjectMapperModule;
    private ConfigurationModule configurationModule;
    private DeliveryModule deliveryModule;
    private DispatchModule dispatchModule;
    private LifecycleTrackerModule lifecycleTrackerModule;
    private ProviderModule providerModule;
    private RoutingModule routingModule;
    private SchemaModule schemaModule;
    private SessionModule sessionModule;
    
    public Builder analyticsApiModule(AnalyticsApiModule paramAnalyticsApiModule)
    {
      if (paramAnalyticsApiModule == null) {
        throw new NullPointerException("analyticsApiModule");
      }
      this.analyticsApiModule = paramAnalyticsApiModule;
      return this;
    }
    
    public Builder analyticsModule(AnalyticsModule paramAnalyticsModule)
    {
      if (paramAnalyticsModule == null) {
        throw new NullPointerException("analyticsModule");
      }
      this.analyticsModule = paramAnalyticsModule;
      return this;
    }
    
    public Builder analyticsSchedulersModule(AnalyticsSchedulersModule paramAnalyticsSchedulersModule)
    {
      if (paramAnalyticsSchedulersModule == null) {
        throw new NullPointerException("analyticsSchedulersModule");
      }
      this.analyticsSchedulersModule = paramAnalyticsSchedulersModule;
      return this;
    }
    
    public Builder associationModule(AssociationModule paramAssociationModule)
    {
      if (paramAssociationModule == null) {
        throw new NullPointerException("associationModule");
      }
      this.associationModule = paramAssociationModule;
      return this;
    }
    
    public Builder baseAnalyticsModule(BaseAnalyticsModule paramBaseAnalyticsModule)
    {
      if (paramBaseAnalyticsModule == null) {
        throw new NullPointerException("baseAnalyticsModule");
      }
      this.baseAnalyticsModule = paramBaseAnalyticsModule;
      return this;
    }
    
    public AnalyticsComponent build()
    {
      if (this.analyticsModule == null) {
        this.analyticsModule = new AnalyticsModule();
      }
      if (this.analyticsApiModule == null) {
        this.analyticsApiModule = new AnalyticsApiModule();
      }
      if (this.analyticsSchedulersModule == null) {
        this.analyticsSchedulersModule = new AnalyticsSchedulersModule();
      }
      if (this.configObjectMapperModule == null) {
        this.configObjectMapperModule = new ConfigObjectMapperModule();
      }
      if (this.dispatchModule == null) {
        this.dispatchModule = new DispatchModule();
      }
      if (this.deliveryModule == null) {
        this.deliveryModule = new DeliveryModule();
      }
      if (this.routingModule == null) {
        this.routingModule = new RoutingModule();
      }
      if (this.schemaModule == null) {
        this.schemaModule = new SchemaModule();
      }
      if (this.configurationModule == null) {
        this.configurationModule = new ConfigurationModule();
      }
      if (this.sessionModule == null) {
        this.sessionModule = new SessionModule();
      }
      if (this.lifecycleTrackerModule == null) {
        this.lifecycleTrackerModule = new LifecycleTrackerModule();
      }
      if (this.providerModule == null) {
        this.providerModule = new ProviderModule();
      }
      if (this.associationModule == null) {
        this.associationModule = new AssociationModule();
      }
      if (this.baseAnalyticsModule == null) {
        throw new IllegalStateException("baseAnalyticsModule must be set");
      }
      return new DaggerAnalyticsComponent(this, null);
    }
    
    public Builder configObjectMapperModule(ConfigObjectMapperModule paramConfigObjectMapperModule)
    {
      if (paramConfigObjectMapperModule == null) {
        throw new NullPointerException("configObjectMapperModule");
      }
      this.configObjectMapperModule = paramConfigObjectMapperModule;
      return this;
    }
    
    public Builder configurationModule(ConfigurationModule paramConfigurationModule)
    {
      if (paramConfigurationModule == null) {
        throw new NullPointerException("configurationModule");
      }
      this.configurationModule = paramConfigurationModule;
      return this;
    }
    
    public Builder deliveryModule(DeliveryModule paramDeliveryModule)
    {
      if (paramDeliveryModule == null) {
        throw new NullPointerException("deliveryModule");
      }
      this.deliveryModule = paramDeliveryModule;
      return this;
    }
    
    public Builder dispatchModule(DispatchModule paramDispatchModule)
    {
      if (paramDispatchModule == null) {
        throw new NullPointerException("dispatchModule");
      }
      this.dispatchModule = paramDispatchModule;
      return this;
    }
    
    public Builder lifecycleTrackerModule(LifecycleTrackerModule paramLifecycleTrackerModule)
    {
      if (paramLifecycleTrackerModule == null) {
        throw new NullPointerException("lifecycleTrackerModule");
      }
      this.lifecycleTrackerModule = paramLifecycleTrackerModule;
      return this;
    }
    
    public Builder providerModule(ProviderModule paramProviderModule)
    {
      if (paramProviderModule == null) {
        throw new NullPointerException("providerModule");
      }
      this.providerModule = paramProviderModule;
      return this;
    }
    
    public Builder routingModule(RoutingModule paramRoutingModule)
    {
      if (paramRoutingModule == null) {
        throw new NullPointerException("routingModule");
      }
      this.routingModule = paramRoutingModule;
      return this;
    }
    
    public Builder schemaModule(SchemaModule paramSchemaModule)
    {
      if (paramSchemaModule == null) {
        throw new NullPointerException("schemaModule");
      }
      this.schemaModule = paramSchemaModule;
      return this;
    }
    
    public Builder sessionModule(SessionModule paramSessionModule)
    {
      if (paramSessionModule == null) {
        throw new NullPointerException("sessionModule");
      }
      this.sessionModule = paramSessionModule;
      return this;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/DaggerAnalyticsComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */