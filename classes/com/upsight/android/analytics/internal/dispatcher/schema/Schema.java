package com.upsight.android.analytics.internal.dispatcher.schema;

import com.upsight.android.analytics.provider.UpsightDataProvider;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Schema
{
  private final Set<String> mAttributes;
  private final Map<String, UpsightDataProvider> mDataProviders;
  private final String mName;
  
  private Schema(String paramString, Set<String> paramSet, Map<String, UpsightDataProvider> paramMap)
  {
    this.mName = paramString;
    this.mAttributes = paramSet;
    this.mDataProviders = paramMap;
  }
  
  static Schema from(String paramString, Schema paramSchema, Set<String> paramSet)
  {
    HashSet localHashSet = new HashSet();
    localHashSet.addAll(paramSet);
    localHashSet.addAll(paramSchema.mAttributes);
    return new Schema(paramString, localHashSet, paramSchema.mDataProviders);
  }
  
  public Set<String> availableKeys()
  {
    return this.mAttributes;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public Object getValueFor(String paramString)
  {
    Object localObject = null;
    if (this.mAttributes.contains(paramString))
    {
      UpsightDataProvider localUpsightDataProvider = (UpsightDataProvider)this.mDataProviders.get(paramString);
      if (localUpsightDataProvider != null) {
        localObject = localUpsightDataProvider.get(paramString);
      }
    }
    return localObject;
  }
  
  public static class Default
    extends Schema
  {
    static final Set<String> DEFAULT_REQUEST_ATTRIBUTES = new HashSet() {};
    
    Default(Map<String, UpsightDataProvider> paramMap)
    {
      super(DEFAULT_REQUEST_ATTRIBUTES, paramMap, null);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/schema/Schema.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */