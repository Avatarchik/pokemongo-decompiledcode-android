package com.upsight.android.analytics.event;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.upsight.android.UpsightAnalyticsExtension;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.UpsightAnalyticsApi;
import com.upsight.android.analytics.internal.DynamicIdentifiers;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("com.upsight.dynamic")
public final class UpsightDynamicEvent
  extends UpsightAnalyticsEvent<JsonNode, JsonNode>
  implements DynamicIdentifiers
{
  private String identifiers;
  
  UpsightDynamicEvent() {}
  
  UpsightDynamicEvent(String paramString1, String paramString2, JsonNode paramJsonNode1, JsonNode paramJsonNode2)
  {
    super(paramString1, paramJsonNode1, paramJsonNode2);
    this.identifiers = paramString2;
  }
  
  public static Builder createBuilder(String paramString)
  {
    return new Builder(paramString, null);
  }
  
  public String getIdentifiersName()
  {
    return this.identifiers;
  }
  
  public static final class Builder
  {
    private String identifiers;
    private JsonNode publisherData = JsonNodeFactory.instance.objectNode();
    private final String type;
    private JsonNode upsightData = JsonNodeFactory.instance.objectNode();
    
    private Builder(String paramString)
    {
      this.type = paramString;
    }
    
    private UpsightDynamicEvent build()
    {
      return new UpsightDynamicEvent(this.type, this.identifiers, this.upsightData, this.publisherData);
    }
    
    public Builder putPublisherData(JsonNode paramJsonNode)
    {
      this.publisherData = paramJsonNode.deepCopy();
      return this;
    }
    
    public Builder putUpsightData(JsonNode paramJsonNode)
    {
      this.upsightData = paramJsonNode.deepCopy();
      return this;
    }
    
    public final UpsightDynamicEvent record(UpsightContext paramUpsightContext)
    {
      UpsightDynamicEvent localUpsightDynamicEvent = build();
      ((UpsightAnalyticsExtension)paramUpsightContext.getUpsightExtension("com.upsight.extension.analytics")).getApi().record(localUpsightDynamicEvent);
      return localUpsightDynamicEvent;
    }
    
    public Builder setDynamicIdentifiers(String paramString)
    {
      this.identifiers = paramString;
      return this;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/event/UpsightDynamicEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */