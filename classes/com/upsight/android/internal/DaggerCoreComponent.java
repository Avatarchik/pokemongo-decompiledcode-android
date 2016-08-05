package com.upsight.android.internal;

import android.content.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.otto.Bus;
import com.upsight.android.UpsightContext;
import com.upsight.android.internal.logger.LogWriter;
import com.upsight.android.internal.logger.LoggerModule;
import com.upsight.android.internal.logger.LoggerModule_ProvideUpsightLoggerFactory;
import com.upsight.android.internal.persistence.PersistenceModule;
import com.upsight.android.internal.persistence.PersistenceModule_ProvideBackgroundDataStoreFactory;
import com.upsight.android.internal.persistence.PersistenceModule_ProvideDataStoreFactory;
import com.upsight.android.internal.persistence.storable.StorableIdFactory;
import com.upsight.android.internal.persistence.storable.StorableInfoCache;
import com.upsight.android.internal.persistence.storable.StorableModule;
import com.upsight.android.internal.persistence.storable.StorableModule_ProvideStorableInfoCacheFactory;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.persistence.UpsightDataStore;
import dagger.internal.ScopedProvider;
import javax.inject.Provider;
import rx.Scheduler;

public final class DaggerCoreComponent
  implements CoreComponent
{
  private Provider<Context> provideApplicationContextProvider;
  private Provider<String> provideApplicationTokenProvider;
  private Provider<UpsightDataStore> provideBackgroundDataStoreProvider;
  private Provider<Bus> provideBusProvider;
  private Provider<UpsightDataStore> provideDataStoreProvider;
  private Provider<LogWriter> provideLogWriterProvider;
  private Provider<ObjectMapper> provideObjectMapperProvider;
  private Provider<Scheduler> provideObserveOnSchedulerProvider;
  private Provider<String> providePublicKeyProvider;
  private Provider<String> provideSdkPluginProvider;
  private Provider<StorableInfoCache> provideStorableInfoCacheProvider;
  private Provider<Scheduler> provideSubscribeOnSchedulerProvider;
  private Provider<StorableIdFactory> provideTypeIdGeneratorProvider;
  private Provider<UpsightContext> provideUpsightContextProvider;
  private Provider<UpsightLogger> provideUpsightLoggerProvider;
  
  static
  {
    if (!DaggerCoreComponent.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  private DaggerCoreComponent(Builder paramBuilder)
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
    this.provideApplicationContextProvider = ScopedProvider.create(ContextModule_ProvideApplicationContextFactory.create(paramBuilder.contextModule));
    this.provideObjectMapperProvider = ScopedProvider.create(ObjectMapperModule_ProvideObjectMapperFactory.create(paramBuilder.objectMapperModule));
    this.provideStorableInfoCacheProvider = ScopedProvider.create(StorableModule_ProvideStorableInfoCacheFactory.create(paramBuilder.storableModule, this.provideObjectMapperProvider));
    this.provideTypeIdGeneratorProvider = ScopedProvider.create(ContextModule_ProvideTypeIdGeneratorFactory.create(paramBuilder.contextModule));
    this.provideSubscribeOnSchedulerProvider = ScopedProvider.create(SchedulersModule_ProvideSubscribeOnSchedulerFactory.create(paramBuilder.schedulersModule));
    this.provideObserveOnSchedulerProvider = ScopedProvider.create(SchedulersModule_ProvideObserveOnSchedulerFactory.create(paramBuilder.schedulersModule));
    this.provideBusProvider = ScopedProvider.create(ContextModule_ProvideBusFactory.create(paramBuilder.contextModule));
    this.provideDataStoreProvider = ScopedProvider.create(PersistenceModule_ProvideDataStoreFactory.create(paramBuilder.persistenceModule, this.provideApplicationContextProvider, this.provideStorableInfoCacheProvider, this.provideTypeIdGeneratorProvider, this.provideSubscribeOnSchedulerProvider, this.provideObserveOnSchedulerProvider, this.provideBusProvider));
    this.provideLogWriterProvider = ScopedProvider.create(ContextModule_ProvideLogWriterFactory.create(paramBuilder.contextModule));
    this.provideUpsightLoggerProvider = ScopedProvider.create(LoggerModule_ProvideUpsightLoggerFactory.create(paramBuilder.loggerModule, this.provideDataStoreProvider, this.provideLogWriterProvider));
    this.provideSdkPluginProvider = ScopedProvider.create(PropertiesModule_ProvideSdkPluginFactory.create(paramBuilder.propertiesModule, this.provideApplicationContextProvider, this.provideUpsightLoggerProvider));
    this.provideApplicationTokenProvider = ScopedProvider.create(PropertiesModule_ProvideApplicationTokenFactory.create(paramBuilder.propertiesModule, this.provideApplicationContextProvider, this.provideUpsightLoggerProvider));
    this.providePublicKeyProvider = ScopedProvider.create(PropertiesModule_ProvidePublicKeyFactory.create(paramBuilder.propertiesModule, this.provideApplicationContextProvider, this.provideUpsightLoggerProvider));
    this.provideUpsightContextProvider = ScopedProvider.create(UpsightContextModule_ProvideUpsightContextFactory.create(paramBuilder.upsightContextModule, this.provideApplicationContextProvider, this.provideSdkPluginProvider, this.provideApplicationTokenProvider, this.providePublicKeyProvider, this.provideDataStoreProvider, this.provideUpsightLoggerProvider));
    this.provideBackgroundDataStoreProvider = ScopedProvider.create(PersistenceModule_ProvideBackgroundDataStoreFactory.create(paramBuilder.persistenceModule, this.provideApplicationContextProvider, this.provideSubscribeOnSchedulerProvider, this.provideTypeIdGeneratorProvider, this.provideStorableInfoCacheProvider, this.provideBusProvider));
  }
  
  public Context applicationContext()
  {
    return (Context)this.provideApplicationContextProvider.get();
  }
  
  public UpsightDataStore backgroundDataStore()
  {
    return (UpsightDataStore)this.provideBackgroundDataStoreProvider.get();
  }
  
  public Bus bus()
  {
    return (Bus)this.provideBusProvider.get();
  }
  
  public ObjectMapper objectMapper()
  {
    return (ObjectMapper)this.provideObjectMapperProvider.get();
  }
  
  public Scheduler observeOnScheduler()
  {
    return (Scheduler)this.provideObserveOnSchedulerProvider.get();
  }
  
  public Scheduler subscribeOnScheduler()
  {
    return (Scheduler)this.provideSubscribeOnSchedulerProvider.get();
  }
  
  public UpsightContext upsightContext()
  {
    return (UpsightContext)this.provideUpsightContextProvider.get();
  }
  
  public static final class Builder
  {
    private ContextModule contextModule;
    private CoreModule coreModule;
    private LoggerModule loggerModule;
    private ObjectMapperModule objectMapperModule;
    private PersistenceModule persistenceModule;
    private PropertiesModule propertiesModule;
    private SchedulersModule schedulersModule;
    private StorableModule storableModule;
    private UpsightContextModule upsightContextModule;
    
    public CoreComponent build()
    {
      if (this.coreModule == null) {
        this.coreModule = new CoreModule();
      }
      if (this.upsightContextModule == null) {
        this.upsightContextModule = new UpsightContextModule();
      }
      if (this.contextModule == null) {
        throw new IllegalStateException("contextModule must be set");
      }
      if (this.propertiesModule == null) {
        this.propertiesModule = new PropertiesModule();
      }
      if (this.objectMapperModule == null) {
        this.objectMapperModule = new ObjectMapperModule();
      }
      if (this.schedulersModule == null) {
        this.schedulersModule = new SchedulersModule();
      }
      if (this.storableModule == null) {
        this.storableModule = new StorableModule();
      }
      if (this.persistenceModule == null) {
        this.persistenceModule = new PersistenceModule();
      }
      if (this.loggerModule == null) {
        this.loggerModule = new LoggerModule();
      }
      return new DaggerCoreComponent(this, null);
    }
    
    public Builder contextModule(ContextModule paramContextModule)
    {
      if (paramContextModule == null) {
        throw new NullPointerException("contextModule");
      }
      this.contextModule = paramContextModule;
      return this;
    }
    
    public Builder coreModule(CoreModule paramCoreModule)
    {
      if (paramCoreModule == null) {
        throw new NullPointerException("coreModule");
      }
      this.coreModule = paramCoreModule;
      return this;
    }
    
    public Builder loggerModule(LoggerModule paramLoggerModule)
    {
      if (paramLoggerModule == null) {
        throw new NullPointerException("loggerModule");
      }
      this.loggerModule = paramLoggerModule;
      return this;
    }
    
    public Builder objectMapperModule(ObjectMapperModule paramObjectMapperModule)
    {
      if (paramObjectMapperModule == null) {
        throw new NullPointerException("objectMapperModule");
      }
      this.objectMapperModule = paramObjectMapperModule;
      return this;
    }
    
    public Builder persistenceModule(PersistenceModule paramPersistenceModule)
    {
      if (paramPersistenceModule == null) {
        throw new NullPointerException("persistenceModule");
      }
      this.persistenceModule = paramPersistenceModule;
      return this;
    }
    
    public Builder propertiesModule(PropertiesModule paramPropertiesModule)
    {
      if (paramPropertiesModule == null) {
        throw new NullPointerException("propertiesModule");
      }
      this.propertiesModule = paramPropertiesModule;
      return this;
    }
    
    public Builder schedulersModule(SchedulersModule paramSchedulersModule)
    {
      if (paramSchedulersModule == null) {
        throw new NullPointerException("schedulersModule");
      }
      this.schedulersModule = paramSchedulersModule;
      return this;
    }
    
    public Builder storableModule(StorableModule paramStorableModule)
    {
      if (paramStorableModule == null) {
        throw new NullPointerException("storableModule");
      }
      this.storableModule = paramStorableModule;
      return this;
    }
    
    public Builder upsightContextModule(UpsightContextModule paramUpsightContextModule)
    {
      if (paramUpsightContextModule == null) {
        throw new NullPointerException("upsightContextModule");
      }
      this.upsightContextModule = paramUpsightContextModule;
      return this;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/internal/DaggerCoreComponent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */