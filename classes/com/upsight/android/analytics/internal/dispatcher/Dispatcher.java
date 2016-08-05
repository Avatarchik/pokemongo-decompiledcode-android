package com.upsight.android.analytics.internal.dispatcher;

import com.upsight.android.UpsightException;
import com.upsight.android.analytics.configuration.UpsightConfiguration;
import com.upsight.android.analytics.dispatcher.AnalyticsEventDeliveryStatus;
import com.upsight.android.analytics.dispatcher.EndpointResponse;
import com.upsight.android.analytics.internal.AnalyticsContext;
import com.upsight.android.analytics.internal.DataStoreRecord;
import com.upsight.android.analytics.internal.DataStoreRecord.Action;
import com.upsight.android.analytics.internal.dispatcher.routing.Router;
import com.upsight.android.analytics.internal.dispatcher.routing.RouterBuilder;
import com.upsight.android.analytics.internal.dispatcher.routing.RoutingListener;
import com.upsight.android.analytics.internal.dispatcher.schema.SchemaSelectorBuilder;
import com.upsight.android.analytics.internal.dispatcher.util.Selector;
import com.upsight.android.analytics.internal.session.Session;
import com.upsight.android.analytics.internal.session.SessionManager;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.UpsightDataStoreListener;
import com.upsight.android.persistence.UpsightSubscription;
import com.upsight.android.persistence.annotation.Created;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Dispatcher
  implements RoutingListener
{
  public static final String CONFIGURATION_SUBTYPE = "upsight.configuration.dispatcher";
  static final int DISPATCHER_CONFIGURATION_MAX_SESSIONS = 2;
  private static final String LOG_TAG = "Dispatcher";
  private ConfigParser mConfigParser;
  private AnalyticsContext mContext;
  private Config mCurrentConfig;
  private volatile Router mCurrentRouter;
  private UpsightSubscription mDataStoreSubscription;
  private Collection<Router> mExpiredRouters;
  private boolean mIsLaunched = false;
  private UpsightLogger mLogger;
  private Set<DataStoreRecord> mPendingRecords;
  private RouterBuilder mRouterBuilder;
  private SchemaSelectorBuilder mSchemaSelectorBuilder;
  private SessionManager mSessionManager;
  private Queue<DataStoreRecord> mUnroutedRecords;
  private UpsightDataStore mUpsightDataStore;
  
  Dispatcher(AnalyticsContext paramAnalyticsContext, SessionManager paramSessionManager, UpsightDataStore paramUpsightDataStore, ConfigParser paramConfigParser, RouterBuilder paramRouterBuilder, SchemaSelectorBuilder paramSchemaSelectorBuilder, UpsightLogger paramUpsightLogger)
  {
    this.mContext = paramAnalyticsContext;
    this.mSessionManager = paramSessionManager;
    this.mUpsightDataStore = paramUpsightDataStore;
    this.mConfigParser = paramConfigParser;
    this.mRouterBuilder = paramRouterBuilder;
    this.mSchemaSelectorBuilder = paramSchemaSelectorBuilder;
    this.mLogger = paramUpsightLogger;
  }
  
  private boolean applyConfiguration(String paramString)
  {
    boolean bool = false;
    Config localConfig = parseConfiguration(paramString);
    if (localConfig == null) {}
    for (;;)
    {
      return bool;
      if (!localConfig.isValid())
      {
        this.mLogger.w("Dispatcher", "Incoming configuration is not valid", new Object[0]);
      }
      else if (localConfig.equals(this.mCurrentConfig))
      {
        bool = true;
      }
      else
      {
        this.mCurrentConfig = localConfig;
        Collection localCollection = this.mExpiredRouters;
        Router localRouter = this.mCurrentRouter;
        if ((localCollection != null) && (localRouter != null))
        {
          localCollection.add(localRouter);
          localRouter.finishRouting();
        }
        Selector localSelector1 = this.mSchemaSelectorBuilder.buildSelectorByName(localConfig.getIdentifierConfig());
        Selector localSelector2 = this.mSchemaSelectorBuilder.buildSelectorByType(localConfig.getIdentifierConfig());
        this.mCurrentRouter = this.mRouterBuilder.build(localConfig.getRoutingConfig(), localSelector1, localSelector2, this);
        Queue localQueue = this.mUnroutedRecords;
        if ((localQueue != null) && (this.mCurrentRouter != null)) {
          while (!localQueue.isEmpty()) {
            routeRecords((DataStoreRecord)localQueue.poll());
          }
        }
        fetchCreatedRecords();
        bool = true;
      }
    }
  }
  
  private void applyDefaultConfiguration()
  {
    applyConfiguration(this.mContext.getDefaultDispatcherConfiguration());
  }
  
  private void fetchCreatedRecords()
  {
    this.mUpsightDataStore.fetch(DataStoreRecord.class, new UpsightDataStoreListener()
    {
      public void onFailure(UpsightException paramAnonymousUpsightException)
      {
        UpsightLogger localUpsightLogger = Dispatcher.this.mLogger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramAnonymousUpsightException;
        localUpsightLogger.e("Dispatcher", "Could not fetch records from store.", arrayOfObject);
      }
      
      public void onSuccess(Set<DataStoreRecord> paramAnonymousSet)
      {
        Iterator localIterator = paramAnonymousSet.iterator();
        while (localIterator.hasNext())
        {
          DataStoreRecord localDataStoreRecord = (DataStoreRecord)localIterator.next();
          Dispatcher.this.routeRecords(localDataStoreRecord);
        }
      }
    });
  }
  
  private void fetchCurrentConfig()
  {
    this.mUpsightDataStore.fetch(UpsightConfiguration.class, new UpsightDataStoreListener()
    {
      public void onFailure(UpsightException paramAnonymousUpsightException)
      {
        UpsightLogger localUpsightLogger = Dispatcher.this.mLogger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramAnonymousUpsightException;
        localUpsightLogger.e("Dispatcher", "Could not fetch config from store.", arrayOfObject);
        if (Dispatcher.this.mCurrentConfig == null) {
          Dispatcher.this.applyDefaultConfiguration();
        }
      }
      
      public void onSuccess(Set<UpsightConfiguration> paramAnonymousSet)
      {
        if (Dispatcher.this.mCurrentConfig != null) {}
        for (;;)
        {
          return;
          boolean bool = false;
          Iterator localIterator = paramAnonymousSet.iterator();
          while (localIterator.hasNext())
          {
            UpsightConfiguration localUpsightConfiguration = (UpsightConfiguration)localIterator.next();
            if (("upsight.configuration.dispatcher".equals(localUpsightConfiguration.getScope())) && (Dispatcher.this.isUpsightConfigurationValid(localUpsightConfiguration))) {
              bool = Dispatcher.this.applyConfiguration(localUpsightConfiguration.getConfiguration());
            }
          }
          if (!bool) {
            Dispatcher.this.applyDefaultConfiguration();
          }
        }
      }
    });
  }
  
  private boolean isUpsightConfigurationValid(UpsightConfiguration paramUpsightConfiguration)
  {
    if (this.mSessionManager.getCurrentSession().getSessionNumber() - paramUpsightConfiguration.getSessionNumberCreated() <= 2) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private Config parseConfiguration(String paramString)
  {
    Object localObject = null;
    try
    {
      Config localConfig = this.mConfigParser.parseConfig(paramString);
      localObject = localConfig;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        UpsightLogger localUpsightLogger = this.mLogger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localIOException;
        localUpsightLogger.e("Dispatcher", "Could not apply incoming config", arrayOfObject);
      }
    }
    return (Config)localObject;
  }
  
  private void routeRecords(DataStoreRecord paramDataStoreRecord)
  {
    if (!DataStoreRecord.Action.Created.equals(paramDataStoreRecord.getAction())) {
      this.mUpsightDataStore.remove(paramDataStoreRecord);
    }
    for (;;)
    {
      return;
      Router localRouter = this.mCurrentRouter;
      Set localSet = this.mPendingRecords;
      if (localRouter == null)
      {
        Queue localQueue = this.mUnroutedRecords;
        if (!localQueue.contains(paramDataStoreRecord)) {
          localQueue.add(paramDataStoreRecord);
        }
      }
      else if ((localSet != null) && (!localSet.contains(paramDataStoreRecord)) && (localRouter.routeEvent(paramDataStoreRecord)))
      {
        localSet.add(paramDataStoreRecord);
      }
    }
  }
  
  public boolean hasPendingRecords()
  {
    Set localSet = this.mPendingRecords;
    if ((localSet == null) || (!localSet.isEmpty())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void launch()
  {
    if (this.mIsLaunched) {}
    for (;;)
    {
      return;
      this.mIsLaunched = true;
      this.mCurrentRouter = null;
      this.mExpiredRouters = new HashSet();
      this.mUnroutedRecords = new ConcurrentLinkedQueue();
      this.mPendingRecords = Collections.synchronizedSet(new HashSet());
      this.mCurrentConfig = null;
      this.mDataStoreSubscription = this.mUpsightDataStore.subscribe(this);
      fetchCurrentConfig();
    }
  }
  
  @Created
  public void onConfigurationCreated(UpsightConfiguration paramUpsightConfiguration)
  {
    if (("upsight.configuration.dispatcher".equals(paramUpsightConfiguration.getScope())) && (isUpsightConfigurationValid(paramUpsightConfiguration))) {
      applyConfiguration(paramUpsightConfiguration.getConfiguration());
    }
  }
  
  @Created
  public void onDataStoreRecordCreated(DataStoreRecord paramDataStoreRecord)
  {
    routeRecords(paramDataStoreRecord);
  }
  
  public void onDelivery(DataStoreRecord paramDataStoreRecord, boolean paramBoolean1, boolean paramBoolean2, String paramString)
  {
    if (paramBoolean1) {}
    for (AnalyticsEventDeliveryStatus localAnalyticsEventDeliveryStatus = AnalyticsEventDeliveryStatus.fromSuccess(paramDataStoreRecord.getID());; localAnalyticsEventDeliveryStatus = AnalyticsEventDeliveryStatus.fromFailure(paramDataStoreRecord.getID(), paramString))
    {
      this.mUpsightDataStore.store(localAnalyticsEventDeliveryStatus, new UpsightDataStoreListener()
      {
        public void onFailure(UpsightException paramAnonymousUpsightException)
        {
          Dispatcher.this.mLogger.e("Dispatcher", paramAnonymousUpsightException, "Could not store DeliveryStatus.", new Object[0]);
        }
        
        public void onSuccess(AnalyticsEventDeliveryStatus paramAnonymousAnalyticsEventDeliveryStatus)
        {
          Dispatcher.this.mUpsightDataStore.remove(paramAnonymousAnalyticsEventDeliveryStatus);
        }
      });
      if ((paramBoolean1) || (paramBoolean2)) {
        this.mUpsightDataStore.remove(paramDataStoreRecord);
      }
      Set localSet = this.mPendingRecords;
      if (localSet != null) {
        localSet.remove(paramDataStoreRecord);
      }
      return;
    }
  }
  
  public void onResponse(EndpointResponse paramEndpointResponse)
  {
    this.mUpsightDataStore.store(paramEndpointResponse, new UpsightDataStoreListener()
    {
      public void onFailure(UpsightException paramAnonymousUpsightException)
      {
        Dispatcher.this.mLogger.e("Dispatcher", paramAnonymousUpsightException, "Could not store EndpointResponse.", new Object[0]);
      }
      
      public void onSuccess(EndpointResponse paramAnonymousEndpointResponse)
      {
        Dispatcher.this.mUpsightDataStore.remove(paramAnonymousEndpointResponse);
      }
    });
  }
  
  public void onRoutingFinished(Router paramRouter)
  {
    Collection localCollection = this.mExpiredRouters;
    if (localCollection != null) {
      localCollection.remove(paramRouter);
    }
  }
  
  public void terminate()
  {
    if (this.mCurrentRouter != null)
    {
      this.mCurrentRouter.finishRouting();
      this.mCurrentRouter = null;
    }
    if (this.mDataStoreSubscription != null)
    {
      this.mDataStoreSubscription.unsubscribe();
      this.mDataStoreSubscription = null;
    }
    this.mCurrentConfig = null;
    this.mPendingRecords = null;
    this.mUnroutedRecords = null;
    this.mExpiredRouters = null;
    this.mCurrentRouter = null;
    this.mIsLaunched = false;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/Dispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */