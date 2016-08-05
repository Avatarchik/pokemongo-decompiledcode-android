package com.upsight.android.analytics.internal;

import android.content.Intent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightCoreComponent;
import com.upsight.android.UpsightException;
import com.upsight.android.analytics.UpsightAnalyticsApi;
import com.upsight.android.analytics.UpsightGooglePlayHelper;
import com.upsight.android.analytics.event.UpsightAnalyticsEvent;
import com.upsight.android.analytics.event.UpsightPublisherData;
import com.upsight.android.analytics.internal.association.AssociationManager;
import com.upsight.android.analytics.internal.dispatcher.schema.SchemaSelectorBuilder;
import com.upsight.android.analytics.internal.session.Session;
import com.upsight.android.analytics.internal.session.SessionManager;
import com.upsight.android.analytics.provider.UpsightDataProvider;
import com.upsight.android.analytics.provider.UpsightLocationTracker;
import com.upsight.android.analytics.provider.UpsightLocationTracker.Data;
import com.upsight.android.analytics.provider.UpsightOptOutStatus;
import com.upsight.android.analytics.provider.UpsightUserAttributes;
import com.upsight.android.analytics.provider.UpsightUserAttributes.Entry;
import com.upsight.android.internal.util.PreferencesHelper;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.persistence.UpsightDataStore;
import java.util.Iterator;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class Analytics
  implements UpsightAnalyticsApi
{
  private static final String LOG_TAG = Analytics.class.getSimpleName();
  private static final String SEQUENCE_ID_FIELD_NAME = "seq_id";
  private static final String USER_ATTRIBUTES_FIELD_NAME = "user_attributes";
  private final AssociationManager mAssociationManager;
  private final UpsightDataStore mDataStore;
  private final Set<UpsightUserAttributes.Entry> mDefaultUserAttributes;
  private final UpsightGooglePlayHelper mGooglePlayHelper;
  private final UpsightLocationTracker mLocationTracker;
  private final UpsightLogger mLogger;
  private final ObjectMapper mObjectMapper;
  private final UpsightOptOutStatus mOptOutStatus;
  private final SchemaSelectorBuilder mSchemaSelector;
  private final SessionManager mSessionManager;
  private final UpsightContext mUpsight;
  private final UpsightUserAttributes mUserAttributes;
  
  @Inject
  public Analytics(UpsightContext paramUpsightContext, SessionManager paramSessionManager, SchemaSelectorBuilder paramSchemaSelectorBuilder, AssociationManager paramAssociationManager, UpsightOptOutStatus paramUpsightOptOutStatus, UpsightLocationTracker paramUpsightLocationTracker, UpsightUserAttributes paramUpsightUserAttributes, UpsightGooglePlayHelper paramUpsightGooglePlayHelper)
  {
    this.mUpsight = paramUpsightContext;
    this.mDataStore = paramUpsightContext.getDataStore();
    this.mSessionManager = paramSessionManager;
    this.mObjectMapper = paramUpsightContext.getCoreComponent().objectMapper();
    this.mLogger = paramUpsightContext.getLogger();
    this.mSchemaSelector = paramSchemaSelectorBuilder;
    this.mAssociationManager = paramAssociationManager;
    this.mOptOutStatus = paramUpsightOptOutStatus;
    this.mLocationTracker = paramUpsightLocationTracker;
    this.mUserAttributes = paramUpsightUserAttributes;
    this.mDefaultUserAttributes = this.mUserAttributes.getDefault();
    this.mGooglePlayHelper = paramUpsightGooglePlayHelper;
  }
  
  private void appendAssociationData(String paramString, ObjectNode paramObjectNode)
  {
    this.mAssociationManager.associate(paramString, paramObjectNode);
  }
  
  private JsonNode getAllAsJsonNode(Set<UpsightUserAttributes.Entry> paramSet)
  {
    ObjectNode localObjectNode = JsonNodeFactory.instance.objectNode();
    Iterator localIterator = paramSet.iterator();
    while (localIterator.hasNext())
    {
      UpsightUserAttributes.Entry localEntry = (UpsightUserAttributes.Entry)localIterator.next();
      if (String.class.equals(localEntry.getType())) {
        localObjectNode.put(localEntry.getKey(), PreferencesHelper.getString(this.mUpsight, "com.upsight.user_attribute." + localEntry.getKey(), (String)localEntry.getDefaultValue()));
      } else if (Integer.class.equals(localEntry.getType())) {
        localObjectNode.put(localEntry.getKey(), PreferencesHelper.getInt(this.mUpsight, "com.upsight.user_attribute." + localEntry.getKey(), ((Integer)localEntry.getDefaultValue()).intValue()));
      } else if (Boolean.class.equals(localEntry.getType())) {
        localObjectNode.put(localEntry.getKey(), PreferencesHelper.getBoolean(this.mUpsight, "com.upsight.user_attribute." + localEntry.getKey(), ((Boolean)localEntry.getDefaultValue()).booleanValue()));
      } else if (Float.class.equals(localEntry.getType())) {
        localObjectNode.put(localEntry.getKey(), PreferencesHelper.getFloat(this.mUpsight, "com.upsight.user_attribute." + localEntry.getKey(), ((Float)localEntry.getDefaultValue()).floatValue()));
      }
    }
    return localObjectNode;
  }
  
  private ObjectNode toJsonNode(UpsightAnalyticsEvent paramUpsightAnalyticsEvent)
    throws JsonProcessingException
  {
    ObjectNode localObjectNode = (ObjectNode)this.mObjectMapper.valueToTree(paramUpsightAnalyticsEvent);
    localObjectNode.put("seq_id", EventSequenceId.getAndIncrement(this.mUpsight));
    localObjectNode.put("user_attributes", getAllAsJsonNode(this.mDefaultUserAttributes));
    return localObjectNode;
  }
  
  public Boolean getBooleanUserAttribute(String paramString)
  {
    return this.mUserAttributes.getBoolean(paramString);
  }
  
  public Set<UpsightUserAttributes.Entry> getDefaultUserAttributes()
  {
    return this.mUserAttributes.getDefault();
  }
  
  public Float getFloatUserAttribute(String paramString)
  {
    return this.mUserAttributes.getFloat(paramString);
  }
  
  public Integer getIntUserAttribute(String paramString)
  {
    return this.mUserAttributes.getInt(paramString);
  }
  
  public boolean getOptOutStatus()
  {
    return this.mOptOutStatus.get();
  }
  
  public String getStringUserAttribute(String paramString)
  {
    return this.mUserAttributes.getString(paramString);
  }
  
  public void purgeLocation()
  {
    this.mLocationTracker.purge();
  }
  
  public void putUserAttribute(String paramString, Boolean paramBoolean)
  {
    this.mUserAttributes.put(paramString, paramBoolean);
  }
  
  public void putUserAttribute(String paramString, Float paramFloat)
  {
    this.mUserAttributes.put(paramString, paramFloat);
  }
  
  public void putUserAttribute(String paramString, Integer paramInteger)
  {
    this.mUserAttributes.put(paramString, paramInteger);
  }
  
  public void putUserAttribute(String paramString1, String paramString2)
  {
    this.mUserAttributes.put(paramString1, paramString2);
  }
  
  public void record(UpsightAnalyticsEvent paramUpsightAnalyticsEvent)
  {
    try
    {
      Session localSession = this.mSessionManager.getCurrentSession();
      long l1 = localSession.getTimeStamp();
      Integer localInteger1 = localSession.getMessageID();
      Integer localInteger2 = localSession.getCampaignID();
      int i = localSession.getSessionNumber();
      long l2 = localSession.getPreviousTos();
      ObjectNode localObjectNode = toJsonNode(paramUpsightAnalyticsEvent);
      appendAssociationData(paramUpsightAnalyticsEvent.getType(), localObjectNode);
      DataStoreRecord localDataStoreRecord = DataStoreRecord.create(DataStoreRecord.Action.Created, l1, localInteger1, localInteger2, i, l2, localObjectNode.toString(), paramUpsightAnalyticsEvent.getType());
      if ((paramUpsightAnalyticsEvent instanceof DynamicIdentifiers)) {
        localDataStoreRecord.setIdentifiers(((DynamicIdentifiers)paramUpsightAnalyticsEvent).getIdentifiersName());
      }
      this.mDataStore.store(localDataStoreRecord);
      return;
    }
    catch (JsonProcessingException localJsonProcessingException)
    {
      for (;;)
      {
        this.mLogger.e(LOG_TAG, localJsonProcessingException, "Failed to record event.", new Object[0]);
      }
    }
  }
  
  public void registerDataProvider(UpsightDataProvider paramUpsightDataProvider)
  {
    this.mSchemaSelector.registerDataProvider(paramUpsightDataProvider);
  }
  
  public void setOptOutStatus(boolean paramBoolean)
  {
    this.mOptOutStatus.set(paramBoolean);
  }
  
  public void trackLocation(UpsightLocationTracker.Data paramData)
  {
    this.mLocationTracker.track(paramData);
  }
  
  public void trackPurchase(int paramInt, String paramString1, double paramDouble1, double paramDouble2, String paramString2, Intent paramIntent, UpsightPublisherData paramUpsightPublisherData)
    throws UpsightException
  {
    this.mGooglePlayHelper.trackPurchase(paramInt, paramString1, paramDouble1, paramDouble2, paramString2, paramIntent, paramUpsightPublisherData);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/Analytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */