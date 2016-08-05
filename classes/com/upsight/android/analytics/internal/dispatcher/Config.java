package com.upsight.android.analytics.internal.dispatcher;

import com.upsight.android.analytics.internal.dispatcher.routing.RoutingConfig;
import com.upsight.android.analytics.internal.dispatcher.schema.IdentifierConfig;

class Config
{
  private IdentifierConfig mIdentifierConfig;
  private RoutingConfig mRoutingConfig;
  
  private Config(Builder paramBuilder)
  {
    this.mRoutingConfig = paramBuilder.mRoutingConfig;
    this.mIdentifierConfig = paramBuilder.mIdentifierConfig;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    Config localConfig;
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
      localConfig = (Config)paramObject;
      if (this.mIdentifierConfig != null)
      {
        if (this.mIdentifierConfig.equals(localConfig.mIdentifierConfig)) {}
      }
      else {
        while (localConfig.mIdentifierConfig != null)
        {
          bool = false;
          break;
        }
      }
      if (this.mRoutingConfig == null) {
        break;
      }
    } while (this.mRoutingConfig.equals(localConfig.mRoutingConfig));
    for (;;)
    {
      bool = false;
      break;
      if (localConfig.mRoutingConfig == null) {
        break;
      }
    }
  }
  
  public IdentifierConfig getIdentifierConfig()
  {
    return this.mIdentifierConfig;
  }
  
  public RoutingConfig getRoutingConfig()
  {
    return this.mRoutingConfig;
  }
  
  public int hashCode()
  {
    int i = 0;
    if (this.mRoutingConfig != null) {}
    for (int j = this.mRoutingConfig.hashCode();; j = 0)
    {
      int k = j * 31;
      if (this.mIdentifierConfig != null) {
        i = this.mIdentifierConfig.hashCode();
      }
      return k + i;
    }
  }
  
  public boolean isValid()
  {
    if ((this.mRoutingConfig != null) && (this.mRoutingConfig.isValid())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static class Builder
  {
    private IdentifierConfig mIdentifierConfig;
    private RoutingConfig mRoutingConfig;
    
    public Config build()
    {
      return new Config(this, null);
    }
    
    public Builder setIdentifierConfig(IdentifierConfig paramIdentifierConfig)
    {
      this.mIdentifierConfig = paramIdentifierConfig;
      return this;
    }
    
    public Builder setRoutingConfig(RoutingConfig paramRoutingConfig)
    {
      this.mRoutingConfig = paramRoutingConfig;
      return this;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/Config.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */