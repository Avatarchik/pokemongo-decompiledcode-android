package com.upsight.android.analytics.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.upsight.android.UpsightAnalyticsExtension;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.UpsightAnalyticsApi;
import com.upsight.android.analytics.internal.util.JacksonHelper.JSONObjectSerializer;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.persistence.annotation.UpsightStorableIdentifier;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public abstract class UpsightAnalyticsEvent<U, P>
{
  @JsonIgnore
  @UpsightStorableIdentifier
  protected String id;
  @JsonProperty("ts")
  protected long mCreationTsMs;
  @JsonProperty("pub_data")
  protected P mPublisherData;
  @JsonProperty("seq_id")
  protected long mSequenceId;
  @JsonProperty("type")
  protected String mType;
  @JsonProperty("upsight_data")
  protected U mUpsightData;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("user_attributes")
  protected ObjectNode mUserAttributes;
  
  protected UpsightAnalyticsEvent() {}
  
  protected UpsightAnalyticsEvent(String paramString, U paramU, P paramP)
  {
    this.mCreationTsMs = TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    this.mType = paramString;
    this.mUpsightData = paramU;
    this.mPublisherData = paramP;
  }
  
  public long getCreationTimestampMs()
  {
    return this.mCreationTsMs;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public P getPublisherData()
  {
    return (P)this.mPublisherData;
  }
  
  public long getSequenceId()
  {
    return this.mSequenceId;
  }
  
  public String getType()
  {
    return this.mType;
  }
  
  protected U getUpsightData()
  {
    return (U)this.mUpsightData;
  }
  
  public JSONObject getUserAttributes()
  {
    return JacksonHelper.JSONObjectSerializer.fromObjectNode(this.mUserAttributes);
  }
  
  public static abstract class Builder<T extends UpsightAnalyticsEvent<U, P>, U, P>
  {
    protected abstract T build();
    
    public final T record(UpsightContext paramUpsightContext)
    {
      UpsightAnalyticsEvent localUpsightAnalyticsEvent = build();
      UpsightAnalyticsExtension localUpsightAnalyticsExtension = (UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics");
      if (localUpsightAnalyticsExtension != null) {
        localUpsightAnalyticsExtension.getApi().record(localUpsightAnalyticsEvent);
      }
      for (;;)
      {
        return localUpsightAnalyticsEvent;
        paramUpsightContext.getLogger().e("Upsight", "com.upsight.extension.analytics must be registered in your Android Manifest", new Object[0]);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/UpsightAnalyticsEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */