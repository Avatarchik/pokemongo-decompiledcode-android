package com.upsight.android.analytics.dispatcher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.upsight.android.persistence.annotation.UpsightStorableIdentifier;
import com.upsight.android.persistence.annotation.UpsightStorableType;

@UpsightStorableType("upsight.dispatcher.response")
public final class EndpointResponse
{
  @JsonProperty("id")
  @UpsightStorableIdentifier
  String id;
  @JsonProperty("content")
  private String mContent;
  @JsonProperty("type")
  private String mType;
  
  EndpointResponse() {}
  
  EndpointResponse(String paramString1, String paramString2)
  {
    this.mType = paramString1;
    this.mContent = paramString2;
  }
  
  public static EndpointResponse create(String paramString1, String paramString2)
  {
    return new EndpointResponse(paramString1, paramString2);
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
        EndpointResponse localEndpointResponse = (EndpointResponse)paramObject;
        if ((this.id == null) || (localEndpointResponse.id == null) || (!this.id.equals(localEndpointResponse.id))) {
          bool = false;
        }
      }
    }
  }
  
  public String getContent()
  {
    return this.mContent;
  }
  
  public String getType()
  {
    return this.mType;
  }
  
  public int hashCode()
  {
    if (this.id != null) {}
    for (int i = this.id.hashCode();; i = 0) {
      return i;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/dispatcher/EndpointResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */