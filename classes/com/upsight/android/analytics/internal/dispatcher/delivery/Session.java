package com.upsight.android.analytics.internal.dispatcher.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.upsight.android.analytics.internal.DataStoreRecord;
import com.upsight.android.logger.UpsightLogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class Session
{
  private Set<DataStoreRecord> mEvents = new HashSet();
  private long mInstallTs;
  private final UpsightLogger mLogger;
  private Integer mMsgCampaignId;
  private Integer mMsgId;
  private final ObjectMapper mObjectMapper;
  private long mPastSessionTime;
  private int mSessionNum;
  private long mSessionStart;
  
  public Session(DataStoreRecord paramDataStoreRecord, ObjectMapper paramObjectMapper, UpsightLogger paramUpsightLogger, long paramLong)
  {
    this.mSessionStart = paramDataStoreRecord.getSessionID();
    this.mObjectMapper = paramObjectMapper;
    this.mLogger = paramUpsightLogger;
    this.mInstallTs = paramLong;
    this.mMsgId = paramDataStoreRecord.getMessageID();
    this.mMsgCampaignId = paramDataStoreRecord.getCampaignID();
    this.mPastSessionTime = paramDataStoreRecord.getPastSessionTime();
    this.mSessionNum = paramDataStoreRecord.getSessionNumber();
  }
  
  public void addEvent(DataStoreRecord paramDataStoreRecord)
  {
    this.mEvents.add(paramDataStoreRecord);
  }
  
  @JsonProperty("events")
  public ObjectNode[] getEvents()
  {
    ArrayList localArrayList = new ArrayList(this.mEvents.size());
    Iterator localIterator = this.mEvents.iterator();
    while (localIterator.hasNext())
    {
      DataStoreRecord localDataStoreRecord = (DataStoreRecord)localIterator.next();
      try
      {
        String str = localDataStoreRecord.getSource();
        JsonNode localJsonNode = this.mObjectMapper.readTree(str);
        if (localJsonNode.isObject()) {
          localArrayList.add((ObjectNode)localJsonNode);
        }
      }
      catch (IOException localIOException)
      {
        this.mLogger.e(getClass().getSimpleName(), localIOException, "Error parsing JSON object.", new Object[0]);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return (ObjectNode[])localArrayList.toArray(new ObjectNode[this.mEvents.size()]);
  }
  
  @JsonProperty("install_ts")
  public long getInstallTs()
  {
    return this.mInstallTs;
  }
  
  @JsonProperty("msg_campaign_id")
  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  public Integer getMsgCampaignId()
  {
    return this.mMsgCampaignId;
  }
  
  @JsonProperty("msg_id")
  @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
  public Integer getMsgId()
  {
    return this.mMsgId;
  }
  
  @JsonProperty("past_session_time")
  public long getPastSessionTime()
  {
    return this.mPastSessionTime;
  }
  
  @JsonProperty("session_num")
  public int getSessionNum()
  {
    return this.mSessionNum;
  }
  
  @JsonProperty("session_start")
  public long getSessionStart()
  {
    return this.mSessionStart;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/delivery/Session.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */