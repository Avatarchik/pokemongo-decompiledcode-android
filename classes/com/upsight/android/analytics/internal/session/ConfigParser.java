package com.upsight.android.analytics.internal.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Named;

class ConfigParser
{
  private ObjectMapper mMapper;
  
  @Inject
  public ConfigParser(@Named("config-mapper") ObjectMapper paramObjectMapper)
  {
    this.mMapper = paramObjectMapper;
  }
  
  public SessionManagerImpl.Config parseConfig(String paramString)
    throws IOException
  {
    return new SessionManagerImpl.Config(((ConfigJson)this.mMapper.readValue(paramString, ConfigJson.class)).session_gap);
  }
  
  public static class ConfigJson
  {
    @JsonProperty("session_gap")
    public int session_gap;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/session/ConfigParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */