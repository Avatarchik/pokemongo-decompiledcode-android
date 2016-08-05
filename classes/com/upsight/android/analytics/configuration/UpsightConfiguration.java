package com.upsight.android.analytics.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.persistence.annotation.UpsightStorableIdentifier;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.configuration")
public final class UpsightConfiguration
{
  @JsonProperty("id")
  @UpsightStorableIdentifier
  String id;
  @JsonProperty("scope")
  private String mScope;
  @JsonProperty("session_num_created")
  private int mSessionNumCreated;
  @JsonProperty("value")
  private String mValue;
  
  UpsightConfiguration() {}
  
  UpsightConfiguration(String paramString1, String paramString2, int paramInt)
  {
    this.mScope = paramString1;
    this.mValue = paramString2;
    this.mSessionNumCreated = paramInt;
  }
  
  public static UpsightConfiguration create(String paramString1, String paramString2, int paramInt)
  {
    return new UpsightConfiguration(paramString1, paramString2, paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
      {
        bool = false;
      }
      else
      {
        UpsightConfiguration localUpsightConfiguration = (UpsightConfiguration)paramObject;
        if ((this.id == null) || (localUpsightConfiguration.id == null) || (!this.id.equals(localUpsightConfiguration.id))) {
          bool = false;
        }
      }
    }
  }
  
  public String getConfiguration()
  {
    return this.mValue;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public String getScope()
  {
    return this.mScope;
  }
  
  public int getSessionNumberCreated()
  {
    return this.mSessionNumCreated;
  }
  
  public int hashCode()
  {
    if (this.id != null) {}
    for (int i = this.id.hashCode();; i = 0) {
      return i;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/configuration/UpsightConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */