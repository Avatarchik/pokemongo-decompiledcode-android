package com.upsight.android;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.analytics.dispatcher.EndpointResponse;
import com.upsight.android.analytics.internal.action.ActionMapResponse;
import com.upsight.android.analytics.provider.UpsightDataProvider;
import com.upsight.android.logger.UpsightLogger;
import com.upsight.android.managedvariables.UpsightManagedVariablesApi;
import com.upsight.android.managedvariables.UpsightManagedVariablesComponent;
import com.upsight.android.managedvariables.internal.BaseManagedVariablesModule;
import com.upsight.android.managedvariables.internal.DaggerManagedVariablesComponent;
import com.upsight.android.managedvariables.internal.DaggerManagedVariablesComponent.Builder;
import com.upsight.android.managedvariables.internal.type.UxmBlockProvider;
import com.upsight.android.managedvariables.internal.type.UxmContent;
import com.upsight.android.managedvariables.internal.type.UxmContentFactory;
import com.upsight.android.persistence.UpsightDataStore;
import com.upsight.android.persistence.annotation.Created;
import java.io.IOException;
import javax.inject.Inject;

public class UpsightManagedVariablesExtension
  extends UpsightExtension<UpsightManagedVariablesComponent, UpsightManagedVariablesApi>
{
  public static final String EXTENSION_NAME = "com.upsight.extension.managedvariables";
  private static final String UPSIGHT_ACTION_MAP = "upsight.action_map";
  private UpsightLogger mLogger;
  @Inject
  UpsightManagedVariablesApi mManagedVariables;
  private ObjectMapper mMapper;
  @Inject
  UxmBlockProvider mUxmBlockProvider;
  @Inject
  UxmContentFactory mUxmContentFactory;
  
  public UpsightManagedVariablesApi getApi()
  {
    return this.mManagedVariables;
  }
  
  protected void onCreate(UpsightContext paramUpsightContext)
  {
    this.mMapper = paramUpsightContext.getCoreComponent().objectMapper();
    this.mLogger = paramUpsightContext.getLogger();
    UpsightDataProvider.register(paramUpsightContext, this.mUxmBlockProvider);
    paramUpsightContext.getDataStore().subscribe(this);
  }
  
  protected UpsightManagedVariablesComponent onResolve(UpsightContext paramUpsightContext)
  {
    return DaggerManagedVariablesComponent.builder().baseManagedVariablesModule(new BaseManagedVariablesModule(paramUpsightContext)).build();
  }
  
  @Created
  public void onResponse(EndpointResponse paramEndpointResponse)
  {
    if (!"upsight.action_map".equals(paramEndpointResponse.getType())) {}
    for (;;)
    {
      return;
      try
      {
        JsonNode localJsonNode = this.mMapper.readTree(paramEndpointResponse.getContent());
        ActionMapResponse localActionMapResponse = (ActionMapResponse)this.mMapper.treeToValue(localJsonNode, ActionMapResponse.class);
        if ("datastore_factory".equals(localActionMapResponse.getActionFactory()))
        {
          UxmContent localUxmContent = this.mUxmContentFactory.create(localActionMapResponse);
          if (localUxmContent != null) {
            localUxmContent.executeActions("content_received");
          }
        }
      }
      catch (IOException localIOException)
      {
        UpsightLogger localUpsightLogger = this.mLogger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localIOException;
        localUpsightLogger.w("Upsight", "Unable to parse action map", arrayOfObject);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/UpsightManagedVariablesExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */