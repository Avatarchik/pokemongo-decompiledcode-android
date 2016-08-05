package com.upsight.android.managedvariables.internal.type;

import android.text.TextUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.squareup.otto.Bus;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightCoreComponent;
import com.upsight.android.UpsightException;
import com.upsight.android.UpsightExtension;
import com.upsight.android.analytics.event.uxm.UpsightUxmEnumerateEvent;
import com.upsight.android.analytics.event.uxm.UpsightUxmEnumerateEvent.Builder;
import com.upsight.android.analytics.internal.action.Action;
import com.upsight.android.analytics.internal.action.ActionContext;
import com.upsight.android.analytics.internal.action.ActionFactory;
import com.upsight.android.analytics.internal.session.Clock;
import com.upsight.android.internal.util.PreferencesHelper;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.managedvariables.UpsightManagedVariablesComponent;
import com.upsight.android.persistence.UpsightDataStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import rx.Observable;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public final class UxmContentActions
{
  private static final Map<String, InternalFactory> FACTORY_MAP = new HashMap() {};
  
  public static class ScheduleSyncNotificationEvent
  {
    public final String mId;
    public final List<String> mTags;
    
    private ScheduleSyncNotificationEvent(String paramString, List<String> paramList)
    {
      this.mId = paramString;
      this.mTags = paramList;
    }
  }
  
  static class Destroy
    extends Action<UxmContent, UxmContentActions.UxmContentActionContext>
  {
    private Destroy(UxmContentActions.UxmContentActionContext paramUxmContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(UxmContent paramUxmContent)
    {
      Bus localBus = ((UxmContentActions.UxmContentActionContext)getActionContext()).mBus;
      paramUxmContent.signalActionCompleted(localBus);
      paramUxmContent.signalActionMapCompleted(localBus);
    }
  }
  
  static class NotifyUxmValuesSynchronized
    extends Action<UxmContent, UxmContentActions.UxmContentActionContext>
  {
    private static final String TAGS = "tags";
    
    private NotifyUxmValuesSynchronized(UxmContentActions.UxmContentActionContext paramUxmContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(UxmContent paramUxmContent)
    {
      ArrayList localArrayList = new ArrayList();
      ArrayNode localArrayNode = optParamJsonArray("tags");
      if ((paramUxmContent.shouldApplyBundle()) && (localArrayNode != null))
      {
        Iterator localIterator = localArrayNode.iterator();
        while (localIterator.hasNext())
        {
          JsonNode localJsonNode = (JsonNode)localIterator.next();
          if (localJsonNode.isTextual()) {
            localArrayList.add(localJsonNode.asText());
          }
        }
      }
      Bus localBus = ((UxmContentActions.UxmContentActionContext)getActionContext()).mBus;
      localBus.post(new UxmContentActions.ScheduleSyncNotificationEvent(paramUxmContent.getId(), localArrayList, null));
      paramUxmContent.signalActionCompleted(localBus);
    }
  }
  
  static class ModifyValue
    extends Action<UxmContent, UxmContentActions.UxmContentActionContext>
  {
    private static final String MATCH = "match";
    private static final String OPERATOR = "operator";
    private static final String OPERATOR_SET = "set";
    private static final String PROPERTY_NAME = "property_name";
    private static final String PROPERTY_VALUE = "property_value";
    private static final String TYPE = "type";
    private static final String VALUES = "values";
    
    private ModifyValue(UxmContentActions.UxmContentActionContext paramUxmContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    private <T> void modifyValue(final UxmContent paramUxmContent, final Class<T> paramClass, JsonNode paramJsonNode1, JsonNode paramJsonNode2)
    {
      final UxmContentActions.UxmContentActionContext localUxmContentActionContext = (UxmContentActions.UxmContentActionContext)getActionContext();
      final ObjectMapper localObjectMapper = localUxmContentActionContext.mMapper;
      final UpsightLogger localUpsightLogger = localUxmContentActionContext.mUpsight.getLogger();
      final UpsightDataStore localUpsightDataStore = localUxmContentActionContext.mUpsight.getDataStore();
      Observable localObservable1 = localUpsightDataStore.fetchObservable(paramClass).map(new Func1()
      {
        public JsonNode call(T paramAnonymousT)
        {
          return localObjectMapper.valueToTree(paramAnonymousT);
        }
      }).cast(ObjectNode.class);
      ObjectNode localObjectNode = localObjectMapper.createObjectNode();
      Iterator localIterator1 = paramJsonNode1.iterator();
      while (localIterator1.hasNext())
      {
        JsonNode localJsonNode3 = (JsonNode)localIterator1.next();
        final String str3 = localJsonNode3.path("property_name").asText();
        final JsonNode localJsonNode4 = localJsonNode3.path("property_value");
        Func1 local2 = new Func1()
        {
          public Boolean call(ObjectNode paramAnonymousObjectNode)
          {
            return Boolean.valueOf(paramAnonymousObjectNode.path(str3).equals(localJsonNode4));
          }
        };
        localObservable1 = localObservable1.filter(local2);
        localObjectNode.replace(str3, localJsonNode4);
      }
      Observable localObservable2 = localObservable1.defaultIfEmpty(localObjectNode);
      Iterator localIterator2 = paramJsonNode2.iterator();
      while (localIterator2.hasNext())
      {
        JsonNode localJsonNode1 = (JsonNode)localIterator2.next();
        String str1 = localJsonNode1.path("operator").asText();
        final String str2 = localJsonNode1.path("property_name").asText();
        final JsonNode localJsonNode2 = localJsonNode1.path("property_value");
        if ("set".equals(str1))
        {
          Func1 local3 = new Func1()
          {
            public ObjectNode call(ObjectNode paramAnonymousObjectNode)
            {
              paramAnonymousObjectNode.replace(str2, localJsonNode2);
              return paramAnonymousObjectNode;
            }
          };
          localObservable2 = localObservable2.map(local3);
        }
      }
      Scheduler localScheduler = localUxmContentActionContext.mUpsight.getCoreComponent().subscribeOnScheduler();
      localObservable2.subscribeOn(localScheduler).observeOn(localUxmContentActionContext.mUpsight.getCoreComponent().observeOnScheduler()).subscribe(new Action1()new Action1
      {
        public void call(final ObjectNode paramAnonymousObjectNode)
        {
          try
          {
            localUpsightDataStore.storeObservable(localObjectMapper.treeToValue(paramAnonymousObjectNode, paramClass)).subscribeOn(localUxmContentActionContext.mUpsight.getCoreComponent().subscribeOnScheduler()).observeOn(localUxmContentActionContext.mUpsight.getCoreComponent().observeOnScheduler()).subscribe(new Action1()new Action1
            {
              public void call(T paramAnonymous2T)
              {
                UxmContentActions.ModifyValue.4.this.val$logger.d("Upsight", "Modified managed variable of class " + UxmContentActions.ModifyValue.4.this.val$clazz + " with value " + paramAnonymousObjectNode, new Object[0]);
              }
            }, new Action1()new Action0
            {
              public void call(Throwable paramAnonymous2Throwable)
              {
                UxmContentActions.ModifyValue.4.this.val$logger.e("Upsight", paramAnonymous2Throwable, "Failed to modify managed variable of class " + UxmContentActions.ModifyValue.4.this.val$clazz, new Object[0]);
              }
            }, new Action0()
            {
              public void call()
              {
                UxmContentActions.ModifyValue.4.this.val$content.signalActionCompleted(UxmContentActions.ModifyValue.4.this.val$actionContext.mBus);
              }
            });
            return;
          }
          catch (JsonProcessingException localJsonProcessingException)
          {
            for (;;)
            {
              localUpsightLogger.e("Upsight", localJsonProcessingException, "Failed to parse managed variable of class " + paramClass, new Object[0]);
              paramUxmContent.signalActionCompleted(localUxmContentActionContext.mBus);
            }
          }
        }
      }, new Action1()
      {
        public void call(Throwable paramAnonymousThrowable)
        {
          localUpsightLogger.e("Upsight", paramAnonymousThrowable, "Failed to fetch managed variable of class " + paramClass, new Object[0]);
          paramUxmContent.signalActionCompleted(localUxmContentActionContext.mBus);
        }
      });
    }
    
    public void execute(UxmContent paramUxmContent)
    {
      int i = 1;
      ActionContext localActionContext = getActionContext();
      String str;
      Class localClass;
      if (paramUxmContent.shouldApplyBundle())
      {
        str = optParamString("type");
        ArrayNode localArrayNode1 = optParamJsonArray("match");
        ArrayNode localArrayNode2 = optParamJsonArray("values");
        if ((!TextUtils.isEmpty(str)) && (localArrayNode1 != null) && (localArrayNode2 != null))
        {
          localClass = null;
          if (!"com.upsight.uxm.string".equals(str)) {
            break label104;
          }
          localClass = ManagedString.Model.class;
          if (localClass == null) {
            break label155;
          }
          modifyValue(paramUxmContent, localClass, localArrayNode1, localArrayNode2);
          i = 0;
        }
      }
      for (;;)
      {
        if (i != 0) {
          paramUxmContent.signalActionCompleted(localActionContext.mBus);
        }
        return;
        label104:
        if ("com.upsight.uxm.boolean".equals(str))
        {
          localClass = ManagedBoolean.Model.class;
          break;
        }
        if ("com.upsight.uxm.integer".equals(str))
        {
          localClass = ManagedInt.Model.class;
          break;
        }
        if (!"com.upsight.uxm.float".equals(str)) {
          break;
        }
        localClass = ManagedFloat.Model.class;
        break;
        label155:
        localActionContext.mLogger.e("Upsight", "Failed to execute action_modify_value due to unknown managed variable type " + str, new Object[0]);
      }
    }
  }
  
  static class SetBundleId
    extends Action<UxmContent, UxmContentActions.UxmContentActionContext>
  {
    private static final String BUNDLE_ID = "bundle.id";
    
    private SetBundleId(UxmContentActions.UxmContentActionContext paramUxmContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(UxmContent paramUxmContent)
    {
      if (paramUxmContent.shouldApplyBundle()) {
        PreferencesHelper.putString(((UxmContentActions.UxmContentActionContext)getActionContext()).mUpsight, "uxmBundleId", optParamString("bundle.id"));
      }
      paramUxmContent.signalActionCompleted(((UxmContentActions.UxmContentActionContext)getActionContext()).mBus);
    }
  }
  
  static class UxmEnumerate
    extends Action<UxmContent, UxmContentActions.UxmContentActionContext>
  {
    private UxmEnumerate(UxmContentActions.UxmContentActionContext paramUxmContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(UxmContent paramUxmContent)
    {
      ActionContext localActionContext = getActionContext();
      String str = ((UpsightManagedVariablesComponent)localActionContext.mUpsight.getUpsightExtension("com.upsight.extension.managedvariables").getComponent()).uxmSchema().mSchemaJsonString;
      try
      {
        UpsightUxmEnumerateEvent.createBuilder(new JSONArray(str)).record(localActionContext.mUpsight);
        paramUxmContent.signalActionCompleted(localActionContext.mBus);
        return;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          localActionContext.mUpsight.getLogger().e("Upsight", localJSONException, "Failed to send UXM enumerate event", new Object[0]);
        }
      }
    }
  }
  
  private static abstract interface InternalFactory
  {
    public abstract Action<UxmContent, UxmContentActions.UxmContentActionContext> create(UxmContentActions.UxmContentActionContext paramUxmContentActionContext, String paramString, JsonNode paramJsonNode);
  }
  
  public static class UxmContentActionFactory
    implements ActionFactory<UxmContent, UxmContentActions.UxmContentActionContext>
  {
    public static final String TYPE = "datastore_factory";
    
    public Action<UxmContent, UxmContentActions.UxmContentActionContext> create(UxmContentActions.UxmContentActionContext paramUxmContentActionContext, JsonNode paramJsonNode)
      throws UpsightException
    {
      if (paramJsonNode == null) {
        throw new UpsightException("Failed to create Action. JSON is null.", new Object[0]);
      }
      String str = paramJsonNode.get("action_type").asText();
      JsonNode localJsonNode = paramJsonNode.get("parameters");
      UxmContentActions.InternalFactory localInternalFactory = (UxmContentActions.InternalFactory)UxmContentActions.FACTORY_MAP.get(str);
      if (localInternalFactory == null) {
        throw new UpsightException("Failed to create Action. Unknown action type.", new Object[0]);
      }
      return localInternalFactory.create(paramUxmContentActionContext, str, localJsonNode);
    }
  }
  
  public static class UxmContentActionContext
    extends ActionContext
  {
    public UxmContentActionContext(UpsightContext paramUpsightContext, Bus paramBus, ObjectMapper paramObjectMapper, Clock paramClock, Scheduler.Worker paramWorker, UpsightLogger paramUpsightLogger)
    {
      super(paramBus, paramObjectMapper, paramClock, paramWorker, paramUpsightLogger);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/managedvariables/internal/type/UxmContentActions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */