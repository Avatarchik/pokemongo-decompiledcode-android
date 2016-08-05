package com.upsight.android.managedvariables.internal.type;

import android.text.TextUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.upsight.android.analytics.internal.action.ActionMap;
import com.upsight.android.analytics.internal.action.ActionMapResponse;
import com.upsight.android.managedvariables.experience.UpsightUserExperience;
import com.upsight.android.managedvariables.experience.UpsightUserExperience.Handler;
import java.util.Iterator;

public final class UxmContentFactory
{
  private static final String ACTION_MODIFY_VALUE = "action_modify_value";
  private static final String ACTION_SET_BUNDLE_ID = "action_set_bundle_id";
  private static final String KEY_ACTIONS = "actions";
  private static final String KEY_ACTION_TYPE = "action_type";
  private static final UxmContentActions.UxmContentActionFactory sUxmContentActionFactory = new UxmContentActions.UxmContentActionFactory();
  private UxmContentActions.UxmContentActionContext mActionContext;
  private UpsightUserExperience mUserExperience;
  
  public UxmContentFactory(UxmContentActions.UxmContentActionContext paramUxmContentActionContext, UpsightUserExperience paramUpsightUserExperience)
  {
    this.mActionContext = paramUxmContentActionContext;
    this.mUserExperience = paramUpsightUserExperience;
  }
  
  public UxmContent create(ActionMapResponse paramActionMapResponse)
  {
    UxmContent localUxmContent = null;
    String str = paramActionMapResponse.getActionMapId();
    if ((!TextUtils.isEmpty(str)) && ("datastore_factory".equals(paramActionMapResponse.getActionFactory())))
    {
      boolean bool = false;
      JsonNode localJsonNode1 = paramActionMapResponse.getActionMap();
      if ((localJsonNode1 != null) && (localJsonNode1.isArray()))
      {
        Iterator localIterator1 = localJsonNode1.iterator();
        do
        {
          if (!localIterator1.hasNext()) {
            break;
          }
          JsonNode localJsonNode2 = ((JsonNode)localIterator1.next()).findPath("actions");
          if ((localJsonNode2 != null) && (localJsonNode2.isArray()))
          {
            Iterator localIterator2 = localJsonNode2.iterator();
            while (localIterator2.hasNext())
            {
              JsonNode localJsonNode3 = ((JsonNode)localIterator2.next()).findPath("action_type");
              if (("action_set_bundle_id".equals(localJsonNode3.asText())) || ("action_modify_value".equals(localJsonNode3.asText()))) {
                bool = this.mUserExperience.getHandler().onReceive();
              }
            }
          }
        } while (!bool);
      }
      localUxmContent = UxmContent.create(str, new ActionMap(sUxmContentActionFactory, this.mActionContext, localJsonNode1), bool);
    }
    return localUxmContent;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/UxmContentFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */