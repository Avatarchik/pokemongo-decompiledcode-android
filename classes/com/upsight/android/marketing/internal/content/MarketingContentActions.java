package com.upsight.android.marketing.internal.content;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.upsight.android.UpsightContext;
import com.upsight.android.UpsightException;
import com.upsight.android.analytics.event.UpsightDynamicEvent;
import com.upsight.android.analytics.event.UpsightDynamicEvent.Builder;
import com.upsight.android.analytics.event.datacollection.UpsightDataCollectionEvent;
import com.upsight.android.analytics.event.datacollection.UpsightDataCollectionEvent.Builder;
import com.upsight.android.analytics.internal.action.Action;
import com.upsight.android.analytics.internal.action.ActionContext;
import com.upsight.android.analytics.internal.action.ActionFactory;
import com.upsight.android.analytics.internal.association.Association;
import com.upsight.android.analytics.internal.session.Clock;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.marketing.R.id;
import com.upsight.android.marketing.R.layout;
import com.upsight.android.marketing.UpsightPurchase;
import com.upsight.android.marketing.UpsightReward;
import com.upsight.android.persistence.UpsightDataStore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;

public final class MarketingContentActions
{
  private static final Map<String, InternalFactory> FACTORY_MAP = new HashMap() {};
  
  public static class PurchasesEvent
  {
    public final String mId;
    public final List<UpsightPurchase> mPurchases;
    
    private PurchasesEvent(String paramString, List<UpsightPurchase> paramList)
    {
      this.mId = paramString;
      this.mPurchases = paramList;
    }
  }
  
  public static class RewardsEvent
  {
    public final String mId;
    public final List<UpsightReward> mRewards;
    
    private RewardsEvent(String paramString, List<UpsightReward> paramList)
    {
      this.mId = paramString;
      this.mRewards = paramList;
    }
  }
  
  public static class DestroyEvent
  {
    public final String mId;
    
    private DestroyEvent(String paramString)
    {
      this.mId = paramString;
    }
  }
  
  static class AssociateOnce
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String UPSIGHT_DATA = "upsight_data";
    public static final String UPSIGHT_DATA_FILTER = "upsight_data_filter";
    public static final String WITH = "with";
    
    private AssociateOnce(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(MarketingContent paramMarketingContent)
    {
      ActionContext localActionContext = getActionContext();
      String str = optParamString("with");
      ObjectNode localObjectNode1 = optParamJsonObject("upsight_data_filter");
      ObjectNode localObjectNode2 = optParamJsonObject("upsight_data");
      try
      {
        Association localAssociation = Association.from(str, localObjectNode1, localObjectNode2, localActionContext.mMapper, localActionContext.mClock);
        localActionContext.mUpsight.getDataStore().store(localAssociation);
        paramMarketingContent.signalActionCompleted(localActionContext.mBus);
        return;
      }
      catch (JsonProcessingException localJsonProcessingException)
      {
        for (;;)
        {
          localActionContext.mLogger.e(getClass().getSimpleName(), localJsonProcessingException, "Failed to parse Association with=" + str + " upsightDataFilter=" + localObjectNode1 + " upsightData" + localObjectNode2, new Object[0]);
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;) {}
      }
    }
  }
  
  static class NotifyPurchases
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String PURCHASES = "purchases";
    
    private NotifyPurchases(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(MarketingContent paramMarketingContent)
    {
      ArrayList localArrayList = new ArrayList();
      ArrayNode localArrayNode = optParamJsonArray("purchases");
      if ((localArrayNode != null) && (localArrayNode.isArray()))
      {
        ActionContext localActionContext = getActionContext();
        Iterator localIterator = localArrayNode.iterator();
        while (localIterator.hasNext())
        {
          JsonNode localJsonNode = null;
          try
          {
            localJsonNode = (JsonNode)localIterator.next();
            localArrayList.add(Purchase.from(localJsonNode, localActionContext.mMapper));
          }
          catch (IOException localIOException)
          {
            localActionContext.mLogger.e(getClass().getSimpleName(), localIOException, "Failed to parse Purchase purchaseJson=" + localJsonNode, new Object[0]);
          }
        }
      }
      Bus localBus = ((MarketingContentActions.MarketingContentActionContext)getActionContext()).mBus;
      localBus.post(new MarketingContentActions.PurchasesEvent(paramMarketingContent.getId(), localArrayList, null));
      paramMarketingContent.signalActionCompleted(localBus);
    }
  }
  
  static class NotifyRewards
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String REWARDS = "rewards";
    
    private NotifyRewards(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(MarketingContent paramMarketingContent)
    {
      ArrayList localArrayList = new ArrayList();
      ArrayNode localArrayNode = optParamJsonArray("rewards");
      if ((localArrayNode != null) && (localArrayNode.isArray()))
      {
        ActionContext localActionContext = getActionContext();
        Iterator localIterator = localArrayNode.iterator();
        while (localIterator.hasNext())
        {
          JsonNode localJsonNode = null;
          try
          {
            localJsonNode = (JsonNode)localIterator.next();
            localArrayList.add(Reward.from(localJsonNode, localActionContext.mMapper));
          }
          catch (IOException localIOException)
          {
            localActionContext.mLogger.e(getClass().getSimpleName(), localIOException, "Failed to parse Reward rewardJson=" + localJsonNode, new Object[0]);
          }
        }
      }
      Bus localBus = ((MarketingContentActions.MarketingContentActionContext)getActionContext()).mBus;
      localBus.post(new MarketingContentActions.RewardsEvent(paramMarketingContent.getId(), localArrayList, null));
      paramMarketingContent.signalActionCompleted(localBus);
    }
  }
  
  static class SendFormData
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String DATA_KEY = "data_key";
    public static final String STREAM_ID = "stream_id";
    
    private SendFormData(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(MarketingContent paramMarketingContent)
    {
      MarketingContentActions.MarketingContentActionContext localMarketingContentActionContext = (MarketingContentActions.MarketingContentActionContext)getActionContext();
      String str1 = optParamString("data_key");
      String str2 = optParamString("stream_id");
      if ((str1 != null) && (str2 != null))
      {
        String str3 = paramMarketingContent.getExtra(str1);
        if (str3 != null) {
          UpsightDataCollectionEvent.createBuilder(str3, str2).record(localMarketingContentActionContext.mUpsight);
        }
      }
      for (;;)
      {
        paramMarketingContent.signalActionCompleted(localMarketingContentActionContext.mBus);
        return;
        localMarketingContentActionContext.mLogger.e(getClass().getSimpleName(), "Action failed actionType=" + getType() + " dataKey=" + str1, new Object[0]);
      }
    }
  }
  
  static class SendEvent
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String EVENT = "event";
    public static final String IDENTIFIERS = "identifiers";
    public static final String PUB_DATA = "pub_data";
    public static final String TYPE = "type";
    public static final String UPSIGHT_DATA = "upsight_data";
    
    private SendEvent(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(MarketingContent paramMarketingContent)
    {
      MarketingContentActions.MarketingContentActionContext localMarketingContentActionContext = (MarketingContentActions.MarketingContentActionContext)getActionContext();
      ObjectNode localObjectNode = optParamJsonObject("event");
      JsonNode localJsonNode;
      if (localObjectNode != null)
      {
        localJsonNode = localObjectNode.path("type");
        if (localJsonNode.isTextual())
        {
          UpsightDynamicEvent.Builder localBuilder = UpsightDynamicEvent.createBuilder(localJsonNode.asText()).putUpsightData(localObjectNode.path("upsight_data"));
          if (!localObjectNode.path("pub_data").isMissingNode()) {
            localBuilder.putPublisherData(localObjectNode.path("pub_data"));
          }
          if (localObjectNode.path("identifiers").isTextual()) {
            localBuilder.setDynamicIdentifiers(localObjectNode.path("identifiers").asText());
          }
          localBuilder.record(localMarketingContentActionContext.mUpsight);
        }
      }
      for (;;)
      {
        paramMarketingContent.signalActionCompleted(localMarketingContentActionContext.mBus);
        return;
        localMarketingContentActionContext.mLogger.e(getClass().getSimpleName(), "Action failed actionType=" + getType() + " type=" + localJsonNode, new Object[0]);
        continue;
        localMarketingContentActionContext.mLogger.e(getClass().getSimpleName(), "Action failed actionType=" + getType() + " event=" + localObjectNode, new Object[0]);
      }
    }
  }
  
  static class OpenUrl
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String URL = "url";
    
    private OpenUrl(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(MarketingContent paramMarketingContent)
    {
      MarketingContentActions.MarketingContentActionContext localMarketingContentActionContext = (MarketingContentActions.MarketingContentActionContext)getActionContext();
      String str = optParamString("url");
      Intent localIntent;
      if (!TextUtils.isEmpty(str))
      {
        localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        localIntent.setFlags(268435456);
      }
      for (;;)
      {
        try
        {
          localMarketingContentActionContext.mUpsight.startActivity(localIntent);
          paramMarketingContent.signalActionCompleted(localMarketingContentActionContext.mBus);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          localMarketingContentActionContext.mLogger.e(getClass().getSimpleName(), localActivityNotFoundException, "Action execution failed actionType=" + getType() + " intent=" + localIntent, new Object[0]);
          continue;
        }
        localMarketingContentActionContext.mLogger.e(getClass().getSimpleName(), "Action execution failed actionType=" + getType() + " uri=" + str, new Object[0]);
      }
    }
  }
  
  static class Destroy
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    private Destroy(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(MarketingContent paramMarketingContent)
    {
      String str = paramMarketingContent.getId();
      MarketingContentActions.MarketingContentActionContext localMarketingContentActionContext = (MarketingContentActions.MarketingContentActionContext)getActionContext();
      if (!TextUtils.isEmpty(str))
      {
        localMarketingContentActionContext.mContentStore.remove(str);
        localMarketingContentActionContext.mBus.post(new MarketingContentActions.DestroyEvent(str, null));
      }
      Bus localBus = localMarketingContentActionContext.mBus;
      paramMarketingContent.signalActionCompleted(localBus);
      paramMarketingContent.signalActionMapCompleted(localBus);
    }
  }
  
  static class PresentCloseButton
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String DELAY_MS = "delay_ms";
    
    private PresentCloseButton(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(final MarketingContent paramMarketingContent)
    {
      long l = optParamInt("delay_ms");
      ((MarketingContentActions.MarketingContentActionContext)getActionContext()).mMainWorker.schedule(new Action0()
      {
        public void call()
        {
          View localView = paramMarketingContent.getContentView();
          if ((localView != null) && (localView.getRootView() != null)) {
            ((ImageView)localView.findViewById(R.id.upsight_marketing_content_view_close_button)).setVisibility(0);
          }
        }
      }, l, TimeUnit.MILLISECONDS);
      paramMarketingContent.signalActionCompleted(((MarketingContentActions.MarketingContentActionContext)getActionContext()).mBus);
    }
  }
  
  static class PresentScopelessContent
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String NEXT_ID = "next_id";
    public static final String SELF_ID = "self_id";
    
    private PresentScopelessContent(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(MarketingContent paramMarketingContent)
    {
      String str1 = optParamString("self_id");
      String str2 = optParamString("next_id");
      if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str2))) {
        ((MarketingContentActions.MarketingContentActionContext)getActionContext()).mContentStore.presentScopelessContent(str2, str1);
      }
      paramMarketingContent.signalActionCompleted(((MarketingContentActions.MarketingContentActionContext)getActionContext()).mBus);
    }
  }
  
  static class PresentScopedContent
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String ID = "id";
    public static final String SCOPE_LIST = "scope_list";
    
    private PresentScopedContent(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(MarketingContent paramMarketingContent)
    {
      String str = optParamString("id");
      ArrayNode localArrayNode = optParamJsonArray("scope_list");
      if ((!TextUtils.isEmpty(str)) && (localArrayNode != null) && (localArrayNode.isArray()))
      {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = localArrayNode.iterator();
        while (localIterator.hasNext())
        {
          JsonNode localJsonNode = (JsonNode)localIterator.next();
          if (localJsonNode.isTextual()) {
            localArrayList.add(localJsonNode.asText());
          }
        }
        ((MarketingContentActions.MarketingContentActionContext)getActionContext()).mContentStore.presentScopedContent(str, (String[])localArrayList.toArray(new String[localArrayList.size()]));
      }
      paramMarketingContent.signalActionCompleted(((MarketingContentActions.MarketingContentActionContext)getActionContext()).mBus);
    }
  }
  
  static class TriggerIfContentAvailable
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String CONDITION_PARAMETERS = "condition_parameters";
    public static final String ELSE_TRIGGER = "else_trigger";
    public static final String ID = "id";
    public static final String THEN_TRIGGER = "then_trigger";
    public static final String TIMEOUT_MS = "timeout_ms";
    private boolean isTriggerExecuted = false;
    private String mConditionalContentID;
    private MarketingContent mContent;
    private Subscription mSubscription;
    
    private TriggerIfContentAvailable(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(final MarketingContent paramMarketingContent)
    {
      localMarketingContentActionContext = (MarketingContentActions.MarketingContentActionContext)getActionContext();
      this.mContent = paramMarketingContent;
      l1 = 0L;
      try
      {
        ObjectNode localObjectNode = optParamJsonObject("condition_parameters");
        this.mConditionalContentID = localObjectNode.get("id").asText();
        long l2 = localObjectNode.get("timeout_ms").asLong();
        l1 = l2;
      }
      catch (NullPointerException localNullPointerException)
      {
        for (;;)
        {
          MarketingContent localMarketingContent;
          String str2;
          localMarketingContentActionContext.mLogger.e(getClass().getSimpleName(), localNullPointerException, "Action execution failed actionType=" + getType() + " invalid CONDITION_PARAMETERS", new Object[0]);
          continue;
          localMarketingContentActionContext.mBus.register(this);
          this.mSubscription = localMarketingContentActionContext.mMainWorker.schedule(new Action0()
          {
            public void call()
            {
              String str = MarketingContentActions.TriggerIfContentAvailable.this.optParamString("else_trigger");
              if ((!TextUtils.isEmpty(str)) && (!MarketingContentActions.TriggerIfContentAvailable.this.isTriggerExecuted))
              {
                paramMarketingContent.executeActions(str);
                MarketingContentActions.TriggerIfContentAvailable.access$1502(MarketingContentActions.TriggerIfContentAvailable.this, true);
              }
              localMarketingContentActionContext.mBus.unregister(this);
            }
          }, l1, TimeUnit.MILLISECONDS);
          continue;
          String str1 = optParamString("else_trigger");
          if ((!TextUtils.isEmpty(str1)) && (!this.isTriggerExecuted))
          {
            paramMarketingContent.executeActions(str1);
            this.isTriggerExecuted = true;
          }
        }
      }
      if (this.mConditionalContentID != null)
      {
        localMarketingContent = (MarketingContent)localMarketingContentActionContext.mContentStore.get(this.mConditionalContentID);
        if ((localMarketingContent != null) && (localMarketingContent.isLoaded()))
        {
          str2 = optParamString("then_trigger");
          if ((!TextUtils.isEmpty(str2)) && (!this.isTriggerExecuted))
          {
            paramMarketingContent.executeActions(str2);
            this.isTriggerExecuted = true;
          }
          paramMarketingContent.signalActionCompleted(((MarketingContentActions.MarketingContentActionContext)getActionContext()).mBus);
          return;
        }
      }
    }
    
    @Subscribe
    public void handleAvailabilityEvent(MarketingContent.ContentLoadedEvent paramContentLoadedEvent)
    {
      if (paramContentLoadedEvent.getId().equals(this.mConditionalContentID))
      {
        this.mSubscription.unsubscribe();
        ((MarketingContentActions.MarketingContentActionContext)getActionContext()).mBus.unregister(this);
        String str = optParamString("then_trigger");
        if ((!TextUtils.isEmpty(str)) && (!this.isTriggerExecuted))
        {
          this.mContent.executeActions(str);
          this.isTriggerExecuted = true;
        }
      }
    }
  }
  
  static class TriggerIfContentBuilt
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String CONDITION_PARAMETERS = "condition_parameters";
    public static final String CONTENT_MODEL = "content_model";
    public static final String ELSE_TRIGGER = "else_trigger";
    public static final String THEN_TRIGGER = "then_trigger";
    
    private TriggerIfContentBuilt(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(final MarketingContent paramMarketingContent)
    {
      int i = 0;
      localMarketingContentActionContext = (MarketingContentActions.MarketingContentActionContext)getActionContext();
      localObject = null;
      try
      {
        JsonNode localJsonNode = optParamJsonObject("condition_parameters").get("content_model");
        localObject = localJsonNode;
      }
      catch (NullPointerException localNullPointerException)
      {
        for (;;)
        {
          localMarketingContentActionContext.mLogger.e(getClass().getSimpleName(), localNullPointerException, "Action execution failed actionType=" + getType() + " invalid CONDITION_PARAMETERS", new Object[0]);
          continue;
          localMarketingContentActionContext.mLogger.e(getClass().getSimpleName(), "Action execution failed actionType=" + getType() + " model=" + localObject, new Object[0]);
          continue;
          String str1 = optParamString("else_trigger");
          if (!TextUtils.isEmpty(str1)) {
            paramMarketingContent.executeActions(str1);
          }
        }
      }
      if ((localObject != null) && (((JsonNode)localObject).isObject()))
      {
        try
        {
          localMarketingContentActionContext.mContentStore.put(paramMarketingContent.getId(), paramMarketingContent);
          MarketingContentModel localMarketingContentModel = MarketingContentModel.from((JsonNode)localObject, localMarketingContentActionContext.mMapper);
          View localView = LayoutInflater.from(localMarketingContentActionContext.mUpsight).inflate(R.layout.upsight_marketing_content_view, null);
          paramMarketingContent.setContentModel(localMarketingContentModel);
          paramMarketingContent.setContentView(localView);
          WebView localWebView = (WebView)localView.findViewById(R.id.upsight_marketing_content_view_web_view);
          ((ImageView)localView.findViewById(R.id.upsight_marketing_content_view_close_button)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              paramMarketingContent.executeActions("content_dismissed");
            }
          });
          localWebView.getSettings().setJavaScriptEnabled(true);
          localWebView.setWebViewClient(localMarketingContentActionContext.mContentTemplateWebViewClientFactory.create(paramMarketingContent));
          localWebView.loadUrl(paramMarketingContent.getContentModel().getTemplateUrl());
          i = 1;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            String str2;
            localMarketingContentActionContext.mLogger.e(getClass().getSimpleName(), localException, "Action execution failed actionType=" + getType() + " model=" + localObject, new Object[0]);
          }
        }
        if (i == 0) {
          break label378;
        }
        str2 = optParamString("then_trigger");
        if (!TextUtils.isEmpty(str2)) {
          paramMarketingContent.executeActions(str2);
        }
        paramMarketingContent.signalActionCompleted(((MarketingContentActions.MarketingContentActionContext)getActionContext()).mBus);
        return;
      }
    }
  }
  
  static class Trigger
    extends Action<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String TRIGGER = "trigger";
    
    private Trigger(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode)
    {
      super(paramString, paramJsonNode);
    }
    
    public void execute(MarketingContent paramMarketingContent)
    {
      String str = optParamString("trigger");
      if (!TextUtils.isEmpty(str)) {
        paramMarketingContent.executeActions(str);
      }
      paramMarketingContent.signalActionCompleted(((MarketingContentActions.MarketingContentActionContext)getActionContext()).mBus);
    }
  }
  
  private static abstract interface InternalFactory
  {
    public abstract Action<MarketingContent, MarketingContentActions.MarketingContentActionContext> create(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, String paramString, JsonNode paramJsonNode);
  }
  
  public static class MarketingContentActionFactory
    implements ActionFactory<MarketingContent, MarketingContentActions.MarketingContentActionContext>
  {
    public static final String TYPE = "marketing_content_factory";
    
    public Action<MarketingContent, MarketingContentActions.MarketingContentActionContext> create(MarketingContentActions.MarketingContentActionContext paramMarketingContentActionContext, JsonNode paramJsonNode)
      throws UpsightException
    {
      if (paramJsonNode == null) {
        throw new UpsightException("Failed to create Action. JSON is null.", new Object[0]);
      }
      String str = paramJsonNode.get("action_type").asText();
      JsonNode localJsonNode = paramJsonNode.get("parameters");
      MarketingContentActions.InternalFactory localInternalFactory = (MarketingContentActions.InternalFactory)MarketingContentActions.FACTORY_MAP.get(str);
      if (localInternalFactory == null) {
        throw new UpsightException("Failed to create Action. Unknown action type.", new Object[0]);
      }
      return localInternalFactory.create(paramMarketingContentActionContext, str, localJsonNode);
    }
  }
  
  public static class MarketingContentActionContext
    extends ActionContext
  {
    public final MarketingContentStore mContentStore;
    public final ContentTemplateWebViewClientFactory mContentTemplateWebViewClientFactory;
    
    public MarketingContentActionContext(UpsightContext paramUpsightContext, Bus paramBus, ObjectMapper paramObjectMapper, Clock paramClock, Scheduler.Worker paramWorker, UpsightLogger paramUpsightLogger, MarketingContentStore paramMarketingContentStore, ContentTemplateWebViewClientFactory paramContentTemplateWebViewClientFactory)
    {
      super(paramBus, paramObjectMapper, paramClock, paramWorker, paramUpsightLogger);
      this.mContentStore = paramMarketingContentStore;
      this.mContentTemplateWebViewClientFactory = paramContentTemplateWebViewClientFactory;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/marketing/internal/content/MarketingContentActions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */