package com.upsight.android.analytics.internal.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.upsight.android.UpsightException;

public abstract interface ActionFactory<T extends Actionable, U extends ActionContext>
{
  public static final String KEY_ACTION_PARAMS = "parameters";
  public static final String KEY_ACTION_TYPE = "action_type";
  
  public abstract Action<T, U> create(U paramU, JsonNode paramJsonNode)
    throws UpsightException;
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/action/ActionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */