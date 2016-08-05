package com.upsight.android.analytics.internal.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class ManagerConfigParser
{
  private ObjectMapper mMapper;
  
  @Inject
  ManagerConfigParser(@Named("config-mapper") ObjectMapper paramObjectMapper)
  {
    this.mMapper = paramObjectMapper;
  }
  
  public ConfigurationManager.Config parse(String paramString)
    throws IOException
  {
    ConfigJson localConfigJson = (ConfigJson)this.mMapper.readValue(paramString, ConfigJson.class);
    return new ConfigurationManager.Config(TimeUnit.SECONDS.toMillis(localConfigJson.requestInterval));
  }
  
  public static class ConfigJson
  {
    @JsonProperty("requestInterval")
    public int requestInterval;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/configuration/ManagerConfigParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */