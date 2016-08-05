package com.upsight.android.analytics.internal.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class Action<T extends Actionable, U extends ActionContext>
{
  private U mActionContext;
  private JsonNode mParams;
  private String mType;
  
  protected Action(U paramU, String paramString, JsonNode paramJsonNode)
  {
    this.mActionContext = paramU;
    this.mType = paramString;
    this.mParams = paramJsonNode;
  }
  
  public abstract void execute(T paramT);
  
  public U getActionContext()
  {
    return this.mActionContext;
  }
  
  public String getType()
  {
    return this.mType;
  }
  
  protected int optParamInt(String paramString)
  {
    JsonNode localJsonNode;
    if (this.mParams != null)
    {
      localJsonNode = this.mParams.get(paramString);
      if ((localJsonNode == null) || (!localJsonNode.isInt())) {}
    }
    for (int i = localJsonNode.asInt();; i = 0) {
      return i;
    }
  }
  
  protected ArrayNode optParamJsonArray(String paramString)
  {
    JsonNode localJsonNode;
    if (this.mParams != null)
    {
      localJsonNode = this.mParams.get(paramString);
      if ((localJsonNode == null) || (!localJsonNode.isArray())) {}
    }
    for (ArrayNode localArrayNode = (ArrayNode)localJsonNode;; localArrayNode = null) {
      return localArrayNode;
    }
  }
  
  protected ObjectNode optParamJsonObject(String paramString)
  {
    JsonNode localJsonNode;
    if (this.mParams != null)
    {
      localJsonNode = this.mParams.get(paramString);
      if ((localJsonNode == null) || (!localJsonNode.isObject())) {}
    }
    for (ObjectNode localObjectNode = (ObjectNode)localJsonNode;; localObjectNode = null) {
      return localObjectNode;
    }
  }
  
  protected String optParamString(String paramString)
  {
    JsonNode localJsonNode;
    if (this.mParams != null)
    {
      localJsonNode = this.mParams.get(paramString);
      if ((localJsonNode == null) || (!localJsonNode.isTextual())) {}
    }
    for (String str = localJsonNode.asText();; str = null) {
      return str;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/action/Action.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */