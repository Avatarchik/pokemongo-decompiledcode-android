package com.upsight.android.analytics.internal.dispatcher.schema;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IdentifierConfig
{
  private Map<String, String> mIdentifierFilters;
  private Map<String, Set<String>> mIdentifiers;
  
  private IdentifierConfig(Builder paramBuilder)
  {
    this.mIdentifiers = paramBuilder.identifiers;
    this.mIdentifierFilters = paramBuilder.identifierFilters;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    IdentifierConfig localIdentifierConfig;
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
      localIdentifierConfig = (IdentifierConfig)paramObject;
      if (this.mIdentifierFilters != null)
      {
        if (this.mIdentifierFilters.equals(localIdentifierConfig.mIdentifierFilters)) {}
      }
      else {
        while (localIdentifierConfig.mIdentifierFilters != null)
        {
          bool = false;
          break;
        }
      }
      if (this.mIdentifiers == null) {
        break;
      }
    } while (this.mIdentifiers.equals(localIdentifierConfig.mIdentifiers));
    for (;;)
    {
      bool = false;
      break;
      if (localIdentifierConfig.mIdentifiers == null) {
        break;
      }
    }
  }
  
  public Map<String, String> getIdentifierFilters()
  {
    return Collections.unmodifiableMap(this.mIdentifierFilters);
  }
  
  public Map<String, Set<String>> getIdentifiers()
  {
    return Collections.unmodifiableMap(this.mIdentifiers);
  }
  
  public int hashCode()
  {
    int i = 0;
    if (this.mIdentifiers != null) {}
    for (int j = this.mIdentifiers.hashCode();; j = 0)
    {
      int k = j * 31;
      if (this.mIdentifierFilters != null) {
        i = this.mIdentifierFilters.hashCode();
      }
      return k + i;
    }
  }
  
  public static class Builder
  {
    private Map<String, String> identifierFilters = new HashMap();
    private Map<String, Set<String>> identifiers = new HashMap();
    
    public Builder addIdentifierFilter(String paramString1, String paramString2)
    {
      if (this.identifierFilters.containsKey(paramString1)) {
        throw new IllegalArgumentException("Identifier filter " + paramString1 + " already exists");
      }
      this.identifierFilters.put(paramString1, paramString2);
      return this;
    }
    
    public Builder addIdentifiers(String paramString, Set<String> paramSet)
    {
      if (this.identifiers.containsKey(paramString)) {
        throw new IllegalArgumentException("Identifiers name " + paramString + " already exists");
      }
      this.identifiers.put(paramString, paramSet);
      return this;
    }
    
    public IdentifierConfig build()
    {
      return new IdentifierConfig(this, null);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/schema/IdentifierConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */