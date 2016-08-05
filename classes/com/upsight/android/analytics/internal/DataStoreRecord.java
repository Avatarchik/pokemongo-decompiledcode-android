package com.upsight.android.analytics.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.persistence.annotation.UpsightStorableIdentifier;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.datastore.record")
public final class DataStoreRecord
{
  @JsonProperty("action")
  Action action;
  @JsonProperty("campaign_id")
  Integer campaignID;
  @JsonProperty
  @UpsightStorableIdentifier
  String id;
  @JsonProperty("identifiers")
  String identifiers;
  @JsonProperty("message_id")
  Integer messageID;
  @JsonProperty("past_session_time")
  long pastSessionTime;
  @JsonProperty("session_id")
  long sessionID;
  @JsonProperty("session_num")
  int sessionNumber;
  @JsonProperty("source")
  String source;
  @JsonProperty("source_type")
  String sourceType;
  
  DataStoreRecord() {}
  
  private DataStoreRecord(Action paramAction, long paramLong1, Integer paramInteger1, Integer paramInteger2, int paramInt, long paramLong2, String paramString1, String paramString2)
  {
    this.action = paramAction;
    this.sessionID = paramLong1;
    this.messageID = paramInteger1;
    this.campaignID = paramInteger2;
    this.source = paramString1;
    this.sourceType = paramString2;
    this.sessionNumber = paramInt;
    this.pastSessionTime = paramLong2;
  }
  
  public static DataStoreRecord create(Action paramAction, long paramLong1, int paramInt, long paramLong2, String paramString1, String paramString2)
  {
    return create(paramAction, paramLong1, null, null, paramInt, paramLong2, paramString1, paramString2);
  }
  
  public static DataStoreRecord create(Action paramAction, long paramLong1, Integer paramInteger1, Integer paramInteger2, int paramInt, long paramLong2, String paramString1, String paramString2)
  {
    return new DataStoreRecord(paramAction, paramLong1, paramInteger1, paramInteger2, paramInt, paramLong2, paramString1, paramString2);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    DataStoreRecord localDataStoreRecord;
    do
    {
      for (;;)
      {
        return bool;
        if ((paramObject != null) && (getClass() == paramObject.getClass())) {
          break;
        }
        bool = false;
      }
      localDataStoreRecord = (DataStoreRecord)paramObject;
      if (this.id == null) {
        break;
      }
    } while (this.id.equals(localDataStoreRecord.id));
    for (;;)
    {
      bool = false;
      break;
      if (localDataStoreRecord.id == null) {
        break;
      }
    }
  }
  
  public Action getAction()
  {
    return this.action;
  }
  
  public Integer getCampaignID()
  {
    return this.campaignID;
  }
  
  public String getID()
  {
    return this.id;
  }
  
  public String getIdentifiers()
  {
    return this.identifiers;
  }
  
  public Integer getMessageID()
  {
    return this.messageID;
  }
  
  public long getPastSessionTime()
  {
    return this.pastSessionTime;
  }
  
  public long getSessionID()
  {
    return this.sessionID;
  }
  
  public int getSessionNumber()
  {
    return this.sessionNumber;
  }
  
  public String getSource()
  {
    return this.source;
  }
  
  public String getSourceType()
  {
    return this.sourceType;
  }
  
  public int hashCode()
  {
    if (this.id != null) {}
    for (int i = this.id.hashCode();; i = 0) {
      return i;
    }
  }
  
  public void setIdentifiers(String paramString)
  {
    this.identifiers = paramString;
  }
  
  public static enum Action
  {
    static
    {
      Removed = new Action("Removed", 2);
      Action[] arrayOfAction = new Action[3];
      arrayOfAction[0] = Created;
      arrayOfAction[1] = Updated;
      arrayOfAction[2] = Removed;
      $VALUES = arrayOfAction;
    }
    
    private Action() {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/DataStoreRecord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */