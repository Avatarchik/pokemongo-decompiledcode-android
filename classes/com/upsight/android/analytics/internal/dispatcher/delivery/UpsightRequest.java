package com.upsight.android.analytics.internal.dispatcher.delivery;

import android.text.TextUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.internal.DataStoreRecord;
import com.upsight.android.analytics.internal.dispatcher.routing.Packet;
import com.upsight.android.analytics.internal.dispatcher.schema.Schema;
import com.upsight.android.analytics.internal.session.Clock;
import com.upsight.android.analytics.provider.UpsightOptOutStatus;
import com.upsight.android.internal.util.PreferencesHelper;
import com.upsight.android.logger.UpsightLogger;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@JsonSerialize(using=RequestSerializer.class)
class UpsightRequest
{
  private long mInstallTs;
  private final UpsightLogger mLogger;
  private final ObjectMapper mObjectMapper;
  private boolean mOptOut;
  private long mRequestTs;
  private Schema mSchema;
  private Session[] mSessions;
  private UpsightContext mUpsight;
  
  public UpsightRequest(UpsightContext paramUpsightContext, BatchSender.Request paramRequest, ObjectMapper paramObjectMapper, Clock paramClock, UpsightLogger paramUpsightLogger)
  {
    this.mUpsight = paramUpsightContext;
    this.mObjectMapper = paramObjectMapper;
    this.mLogger = paramUpsightLogger;
    this.mInstallTs = PreferencesHelper.getLong(paramUpsightContext, "install_ts", 0L);
    this.mSessions = getSessions(paramRequest.batch);
    this.mOptOut = UpsightOptOutStatus.get(this.mUpsight);
    this.mRequestTs = paramClock.currentTimeSeconds();
    this.mSchema = paramRequest.schema;
  }
  
  private Session[] getSessions(Batch paramBatch)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramBatch.getPackets().iterator();
    while (localIterator.hasNext())
    {
      DataStoreRecord localDataStoreRecord = ((Packet)localIterator.next()).getRecord();
      Session localSession = (Session)localHashMap.get(Long.valueOf(localDataStoreRecord.getSessionID()));
      if (localSession == null)
      {
        localSession = new Session(localDataStoreRecord, this.mObjectMapper, this.mLogger, this.mInstallTs);
        localHashMap.put(Long.valueOf(localDataStoreRecord.getSessionID()), localSession);
      }
      localSession.addEvent(localDataStoreRecord);
    }
    return (Session[])localHashMap.values().toArray(new Session[localHashMap.values().size()]);
  }
  
  static class RequestSerializer
    extends JsonSerializer<UpsightRequest>
  {
    private static final String IDENTIFIERS_KEY = "identifiers";
    private static final String LOCALE_KEY = "locale";
    private static final String OPT_OUT_KEY = "opt_out";
    private static final String REQUEST_TS_KEY = "request_ts";
    private static final String SESSIONS_KEY = "sessions";
    
    public void serialize(UpsightRequest paramUpsightRequest, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider)
      throws IOException
    {
      paramJsonGenerator.writeStartObject();
      Iterator localIterator = paramUpsightRequest.mSchema.availableKeys().iterator();
      while (localIterator.hasNext())
      {
        String str3 = (String)localIterator.next();
        Object localObject = paramUpsightRequest.mSchema.getValueFor(str3);
        if (localObject != null) {
          paramJsonGenerator.writeObjectField(str3, localObject);
        }
      }
      paramJsonGenerator.writeObjectField("request_ts", Long.valueOf(paramUpsightRequest.mRequestTs));
      paramJsonGenerator.writeObjectField("opt_out", Boolean.valueOf(paramUpsightRequest.mOptOut));
      Schema localSchema = paramUpsightRequest.mSchema;
      if (localSchema != null)
      {
        String str2 = localSchema.getName();
        if (!TextUtils.isEmpty(str2)) {
          paramJsonGenerator.writeObjectField("identifiers", str2);
        }
      }
      Locale localLocale = Locale.getDefault();
      if (localLocale != null)
      {
        String str1 = localLocale.toString();
        if (!TextUtils.isEmpty(str1)) {
          paramJsonGenerator.writeObjectField("locale", str1);
        }
      }
      paramJsonGenerator.writeArrayFieldStart("sessions");
      Session[] arrayOfSession = paramUpsightRequest.mSessions;
      int i = arrayOfSession.length;
      for (int j = 0; j < i; j++) {
        paramSerializerProvider.defaultSerializeValue(arrayOfSession[j], paramJsonGenerator);
      }
      paramJsonGenerator.writeEndArray();
      paramJsonGenerator.writeEndObject();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/delivery/UpsightRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */