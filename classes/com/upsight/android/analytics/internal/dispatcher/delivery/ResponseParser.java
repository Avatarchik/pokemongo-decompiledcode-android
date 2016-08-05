package com.upsight.android.analytics.internal.dispatcher.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.analytics.dispatcher.EndpointResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

class ResponseParser
{
  private ObjectMapper mMapper;
  
  @Inject
  public ResponseParser(@Named("config-mapper") ObjectMapper paramObjectMapper)
  {
    this.mMapper = paramObjectMapper;
  }
  
  /**
   * @deprecated
   */
  public Response parse(String paramString)
    throws IOException
  {
    try
    {
      ResponseJson localResponseJson = (ResponseJson)this.mMapper.readValue(paramString, ResponseJson.class);
      LinkedList localLinkedList = new LinkedList();
      if (localResponseJson.response != null)
      {
        Iterator localIterator = localResponseJson.response.iterator();
        while (localIterator.hasNext())
        {
          ResponseElementJson localResponseElementJson = (ResponseElementJson)localIterator.next();
          localLinkedList.add(EndpointResponse.create(localResponseElementJson.type, this.mMapper.writeValueAsString(localResponseElementJson.content)));
        }
      }
      localResponse = new Response(localLinkedList, localResponseJson.error);
    }
    finally {}
    Response localResponse;
    return localResponse;
  }
  
  public static class ResponseElementJson
  {
    @JsonProperty("content")
    public JsonNode content;
    @JsonProperty("type")
    public String type;
  }
  
  public static class ResponseJson
  {
    @JsonProperty("error")
    public String error;
    @JsonProperty("response")
    public List<ResponseParser.ResponseElementJson> response;
  }
  
  public static class Response
  {
    public final String error;
    public final Collection<EndpointResponse> responses;
    
    public Response(Collection<EndpointResponse> paramCollection, String paramString)
    {
      this.responses = paramCollection;
      this.error = paramString;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/delivery/ResponseParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */