package com.upsight.android.analytics.internal.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.analytics.configuration.UpsightConfiguration;
import com.upsight.android.analytics.internal.session.Session;
import com.upsight.android.analytics.internal.session.SessionManager;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class ConfigurationResponseParser
{
  private ObjectMapper mMapper;
  private SessionManager mSessionManager;
  
  @Inject
  ConfigurationResponseParser(@Named("config-mapper") ObjectMapper paramObjectMapper, SessionManager paramSessionManager)
  {
    this.mMapper = paramObjectMapper;
    this.mSessionManager = paramSessionManager;
  }
  
  public Collection<UpsightConfiguration> parse(String paramString)
    throws IOException
  {
    ConfigResponseJson localConfigResponseJson = (ConfigResponseJson)this.mMapper.readValue(paramString, ConfigResponseJson.class);
    LinkedList localLinkedList = new LinkedList();
    for (ConfigJson localConfigJson : localConfigResponseJson.configs) {
      localLinkedList.add(UpsightConfiguration.create(localConfigJson.type, this.mMapper.writeValueAsString(localConfigJson.configuration), this.mSessionManager.getCurrentSession().getSessionNumber()));
    }
    return localLinkedList;
  }
  
  public static class ConfigJson
  {
    @JsonProperty("configuration")
    public JsonNode configuration;
    @JsonProperty("type")
    public String type;
  }
  
  public static class ConfigResponseJson
  {
    @JsonProperty("configurationList")
    public ConfigurationResponseParser.ConfigJson[] configs;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/configuration/ConfigurationResponseParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */