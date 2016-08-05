package com.upsight.android.analytics.internal.configuration;

import android.content.res.Resources;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightException;
import com.upsight.android.analytics.R.raw;
import com.upsight.android.analytics.configuration.UpsightConfiguration;
import com.upsight.android.analytics.dispatcher.EndpointResponse;
import com.upsight.android.analytics.event.config.UpsightConfigExpiredEvent;
import com.upsight.android.analytics.event.config.UpsightConfigExpiredEvent.Builder;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.UpsightDataStoreListener;
import com.upsight.android.persistence.UpsightSubscription;
import com.upsight.android.persistence.annotation.Created;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.IOUtils;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;

public final class ConfigurationManager
{
  public static final String CONFIGURATION_RESPONSE_SUBTYPE = "upsight.configuration";
  public static final String CONFIGURATION_SUBTYPE = "upsight.configuration.configurationManager";
  private static final String LOG_TAG = "Configurator";
  private final ManagerConfigParser mConfigParser;
  private Config mCurrentConfig;
  private final UpsightDataStore mDataStore;
  private UpsightSubscription mDataStoreSubscription;
  private boolean mIsLaunched = false;
  private boolean mIsOutOfSync;
  private final UpsightLogger mLogger;
  private final ConfigurationResponseParser mResponseParser;
  private Action0 mSyncAction = new Action0()
  {
    public void call()
    {
      UpsightConfigExpiredEvent.createBuilder().record(ConfigurationManager.this.mUpsight);
    }
  };
  private final UpsightContext mUpsight;
  private final Scheduler.Worker mWorker;
  private Subscription mWorkerSubscription;
  
  ConfigurationManager(UpsightContext paramUpsightContext, UpsightDataStore paramUpsightDataStore, ConfigurationResponseParser paramConfigurationResponseParser, ManagerConfigParser paramManagerConfigParser, Scheduler paramScheduler, UpsightLogger paramUpsightLogger)
  {
    this.mUpsight = paramUpsightContext;
    this.mDataStore = paramUpsightDataStore;
    this.mResponseParser = paramConfigurationResponseParser;
    this.mConfigParser = paramManagerConfigParser;
    this.mLogger = paramUpsightLogger;
    this.mWorker = paramScheduler.createWorker();
  }
  
  private boolean applyConfiguration(String paramString)
  {
    try
    {
      Config localConfig = this.mConfigParser.parse(paramString);
      boolean bool;
      if ((localConfig == null) || (!localConfig.isValid()))
      {
        this.mLogger.w("Configurator", "Incoming config is invalid", new Object[0]);
        bool = false;
      }
      else if (localConfig.equals(this.mCurrentConfig))
      {
        this.mLogger.w("Configurator", "Current config is equals to incoming config, rejecting", new Object[0]);
        bool = true;
      }
      else
      {
        if ((this.mWorkerSubscription != null) && (!this.mWorkerSubscription.isUnsubscribed())) {
          this.mWorkerSubscription.unsubscribe();
        }
        if (this.mIsOutOfSync) {}
        for (long l = 0L;; l = localConfig.requestInterval)
        {
          this.mWorkerSubscription = this.mWorker.schedulePeriodically(this.mSyncAction, l, localConfig.requestInterval, TimeUnit.MILLISECONDS);
          this.mIsOutOfSync = false;
          this.mCurrentConfig = localConfig;
          bool = true;
          break;
        }
      }
      UpsightLogger localUpsightLogger;
      Object[] arrayOfObject;
      return bool;
    }
    catch (IOException localIOException)
    {
      localUpsightLogger = this.mLogger;
      arrayOfObject = new Object[1];
      arrayOfObject[0] = localIOException;
      localUpsightLogger.e("Configurator", "Could not parse incoming configuration", arrayOfObject);
      bool = false;
    }
  }
  
  private void applyDefaultConfiguration()
  {
    try
    {
      applyConfiguration(IOUtils.toString(this.mUpsight.getResources().openRawResource(R.raw.configurator_config)));
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        UpsightLogger localUpsightLogger = this.mLogger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localIOException;
        localUpsightLogger.e("Configurator", "Could not read default config", arrayOfObject);
      }
    }
  }
  
  private void fetchCurrentConfig()
  {
    this.mDataStore.fetch(UpsightConfiguration.class, new UpsightDataStoreListener()
    {
      public void onFailure(UpsightException paramAnonymousUpsightException)
      {
        UpsightLogger localUpsightLogger = ConfigurationManager.this.mLogger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramAnonymousUpsightException;
        localUpsightLogger.e("Configurator", "Could not fetch existing configs from datastore", arrayOfObject);
        if (ConfigurationManager.this.mCurrentConfig == null) {
          ConfigurationManager.this.applyDefaultConfiguration();
        }
      }
      
      public void onSuccess(Set<UpsightConfiguration> paramAnonymousSet)
      {
        if (ConfigurationManager.this.mCurrentConfig != null) {}
        for (;;)
        {
          return;
          boolean bool = false;
          if (paramAnonymousSet.size() > 0)
          {
            Iterator localIterator = paramAnonymousSet.iterator();
            while (localIterator.hasNext())
            {
              UpsightConfiguration localUpsightConfiguration = (UpsightConfiguration)localIterator.next();
              if (localUpsightConfiguration.getScope().equals("upsight.configuration.configurationManager")) {
                bool = ConfigurationManager.this.applyConfiguration(localUpsightConfiguration.getConfiguration());
              }
            }
          }
          if (!bool) {
            ConfigurationManager.this.applyDefaultConfiguration();
          }
        }
      }
    });
  }
  
  public void launch()
  {
    if (this.mIsLaunched) {}
    for (;;)
    {
      return;
      this.mIsLaunched = true;
      this.mIsOutOfSync = true;
      this.mCurrentConfig = null;
      this.mDataStoreSubscription = this.mDataStore.subscribe(this);
      this.mWorkerSubscription = null;
      fetchCurrentConfig();
    }
  }
  
  @Created
  public void onEndpointResponse(EndpointResponse paramEndpointResponse)
  {
    if (!"upsight.configuration".equals(paramEndpointResponse.getType())) {
      return;
    }
    for (;;)
    {
      UpsightConfiguration localUpsightConfiguration;
      try
      {
        Collection localCollection = this.mResponseParser.parse(paramEndpointResponse.getContent());
        this.mDataStore.fetch(UpsightConfiguration.class, new UpsightDataStoreListener()
        {
          public void onFailure(UpsightException paramAnonymousUpsightException) {}
          
          public void onSuccess(Set<UpsightConfiguration> paramAnonymousSet)
          {
            Iterator localIterator = paramAnonymousSet.iterator();
            while (localIterator.hasNext())
            {
              UpsightConfiguration localUpsightConfiguration = (UpsightConfiguration)localIterator.next();
              ConfigurationManager.this.mDataStore.remove(localUpsightConfiguration);
            }
          }
        });
        Iterator localIterator = localCollection.iterator();
        if (!localIterator.hasNext()) {
          break;
        }
        localUpsightConfiguration = (UpsightConfiguration)localIterator.next();
        if (localUpsightConfiguration.getScope().equals("upsight.configuration.configurationManager"))
        {
          if (!applyConfiguration(localUpsightConfiguration.getConfiguration())) {
            continue;
          }
          this.mDataStore.store(localUpsightConfiguration);
          continue;
        }
      }
      catch (IOException localIOException)
      {
        UpsightLogger localUpsightLogger = this.mLogger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localIOException;
        localUpsightLogger.e("Configurator", "Could not parse incoming configurations", arrayOfObject);
      }
      this.mDataStore.store(localUpsightConfiguration);
    }
  }
  
  public void terminate()
  {
    if (this.mDataStoreSubscription != null)
    {
      this.mDataStoreSubscription.unsubscribe();
      this.mDataStoreSubscription = null;
    }
    if (this.mWorkerSubscription != null)
    {
      this.mWorkerSubscription.unsubscribe();
      this.mWorkerSubscription = null;
    }
    this.mCurrentConfig = null;
    this.mIsLaunched = false;
  }
  
  public static final class Config
  {
    public final long requestInterval;
    
    Config(long paramLong)
    {
      this.requestInterval = paramLong;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if (this == paramObject) {}
      for (;;)
      {
        return bool;
        if ((paramObject == null) || (getClass() != paramObject.getClass())) {
          bool = false;
        } else if (((Config)paramObject).requestInterval != this.requestInterval) {
          bool = false;
        }
      }
    }
    
    public boolean isValid()
    {
      if (this.requestInterval > 0L) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/configuration/ConfigurationManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */