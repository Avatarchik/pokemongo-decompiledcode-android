package com.upsight.android.analytics.internal.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.upsight.android.persistence.annotation.UpsightStorableIdentifier;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.action_map")
public final class ActionMapResponse
{
  @JsonProperty("action_factory")
  String actionFactory;
  @JsonProperty("action_map")
  JsonNode actionMap;
  @JsonProperty("id")
  String actionMapId;
  @UpsightStorableIdentifier
  String id;
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    ActionMapResponse localActionMapResponse;
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
      localActionMapResponse = (ActionMapResponse)paramObject;
      if (this.id == null) {
        break;
      }
    } while (this.id.equals(localActionMapResponse.id));
    for (;;)
    {
      bool = false;
      break;
      if (localActionMapResponse.id == null) {
        break;
      }
    }
  }
  
  public String getActionFactory()
  {
    return this.actionFactory;
  }
  
  public JsonNode getActionMap()
  {
    return this.actionMap;
  }
  
  public String getActionMapId()
  {
    return this.actionMapId;
  }
  
  public int hashCode()
  {
    if (this.id != null) {}
    for (int i = this.id.hashCode();; i = 0) {
      return i;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/action/ActionMapResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */