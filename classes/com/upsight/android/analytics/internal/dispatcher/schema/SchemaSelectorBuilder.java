package com.upsight.android.analytics.internal.dispatcher.schema;

import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.internal.dispatcher.util.ByFilterSelector;
import com.upsight.android.analytics.internal.dispatcher.util.ByNameSelector;
import com.upsight.android.analytics.internal.dispatcher.util.Selector;
import com.upsight.android.analytics.provider.UpsightDataProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SchemaSelectorBuilder
{
  private final Map<String, UpsightDataProvider> mDataProviders = new ConcurrentHashMap();
  private final Schema mDefaultSchema;
  
  SchemaSelectorBuilder(UpsightContext paramUpsightContext)
  {
    registerDefaultDataProviders(paramUpsightContext);
    this.mDefaultSchema = new Schema.Default(this.mDataProviders);
  }
  
  private void registerDefaultDataProviders(UpsightContext paramUpsightContext)
  {
    registerDataProvider(new AppBlockProvider(paramUpsightContext));
    registerDataProvider(new DeviceBlockProvider(paramUpsightContext));
    registerDataProvider(new AndroidIDBlockProvider(paramUpsightContext));
    registerDataProvider(new ScreenBlockProvider(paramUpsightContext));
    registerDataProvider(new SdkBlockProvider(paramUpsightContext));
    registerDataProvider(new SidProvider(paramUpsightContext));
    registerDataProvider(new LocationBlockProvider(paramUpsightContext));
  }
  
  public Selector<Schema> buildSelectorByName(IdentifierConfig paramIdentifierConfig)
  {
    Map localMap = paramIdentifierConfig.getIdentifiers();
    HashMap localHashMap = new HashMap(localMap.size());
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Set localSet = (Set)paramIdentifierConfig.getIdentifiers().get(str);
      if (localSet != null) {
        localHashMap.put(str, Schema.from(str, this.mDefaultSchema, localSet));
      }
    }
    return new ByNameSelector(localHashMap);
  }
  
  public Selector<Schema> buildSelectorByType(IdentifierConfig paramIdentifierConfig)
  {
    HashMap localHashMap = new HashMap(paramIdentifierConfig.getIdentifiers().size());
    Iterator localIterator = paramIdentifierConfig.getIdentifierFilters().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str1 = (String)localEntry.getKey();
      String str2 = (String)localEntry.getValue();
      Set localSet = (Set)paramIdentifierConfig.getIdentifiers().get(str2);
      if (localSet != null) {
        localHashMap.put(str1, Schema.from(str2, this.mDefaultSchema, localSet));
      }
    }
    return new ByFilterSelector(localHashMap, this.mDefaultSchema);
  }
  
  public void registerDataProvider(UpsightDataProvider paramUpsightDataProvider)
  {
    Iterator localIterator = paramUpsightDataProvider.availableKeys().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      UpsightDataProvider localUpsightDataProvider = (UpsightDataProvider)this.mDataProviders.put(str, paramUpsightDataProvider);
      if (localUpsightDataProvider != null)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramUpsightDataProvider.getClass().getName();
        arrayOfObject[1] = localUpsightDataProvider.getClass().getName();
        throw new IllegalStateException(String.format("Both %s and %s provide values for key.", arrayOfObject));
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/schema/SchemaSelectorBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */