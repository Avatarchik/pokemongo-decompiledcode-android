package com.upsight.android.analytics.internal.dispatcher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.internal.dispatcher.delivery.BatchSender.Config;
import com.upsight.android.analytics.internal.dispatcher.delivery.Batcher.Config;
import com.upsight.android.analytics.internal.dispatcher.delivery.QueueConfig;
import com.upsight.android.analytics.internal.dispatcher.delivery.QueueConfig.Builder;
import com.upsight.android.analytics.internal.dispatcher.routing.RoutingConfig;
import com.upsight.android.analytics.internal.dispatcher.routing.RoutingConfig.Builder;
import com.upsight.android.analytics.internal.dispatcher.schema.IdentifierConfig;
import com.upsight.android.analytics.internal.dispatcher.schema.IdentifierConfig.Builder;
import com.upsight.android.logger.UpsightLogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
class ConfigParser
{
  private static final String LOG_TAG = "Dispatcher";
  private UpsightLogger mLogger;
  private ObjectMapper mMapper;
  
  @Inject
  public ConfigParser(UpsightContext paramUpsightContext, @Named("config-mapper") ObjectMapper paramObjectMapper)
  {
    this.mMapper = paramObjectMapper;
    this.mLogger = paramUpsightContext.getLogger();
  }
  
  private IdentifierConfig parseIdentifierConfig(Config paramConfig)
  {
    IdentifierConfig.Builder localBuilder = new IdentifierConfig.Builder();
    if (paramConfig.identifiers != null)
    {
      Iterator localIterator1 = paramConfig.identifiers.iterator();
      while (localIterator1.hasNext())
      {
        Identifier localIdentifier = (Identifier)localIterator1.next();
        localBuilder.addIdentifiers(localIdentifier.name, new HashSet(localIdentifier.ids));
      }
      Iterator localIterator2 = paramConfig.identifierFilters.iterator();
      while (localIterator2.hasNext())
      {
        IdentifierFilter localIdentifierFilter = (IdentifierFilter)localIterator2.next();
        localBuilder.addIdentifierFilter(localIdentifierFilter.filter, localIdentifierFilter.identifiers);
      }
    }
    return localBuilder.build();
  }
  
  private QueueConfig parseQueueConfig(Queue paramQueue)
  {
    return new QueueConfig.Builder().setEndpointAddress(paramQueue.formatEndpointAddress()).setBatchSenderConfig(new BatchSender.Config(paramQueue.countNetworkFail, paramQueue.retryInterval, paramQueue.retryCount)).setBatcherConfig(new Batcher.Config(paramQueue.maxSize, paramQueue.maxAge)).build();
  }
  
  private RoutingConfig parseRoutingConfig(Config paramConfig)
  {
    RoutingConfig.Builder localBuilder = new RoutingConfig.Builder();
    if (paramConfig.queues != null)
    {
      ArrayList localArrayList1 = new ArrayList();
      Iterator localIterator1 = paramConfig.queues.iterator();
      while (localIterator1.hasNext())
      {
        Queue localQueue = (Queue)localIterator1.next();
        localBuilder.addQueueConfig(localQueue.name, parseQueueConfig(localQueue));
        localArrayList1.add(localQueue.name);
      }
      Iterator localIterator2 = paramConfig.routeFilters.iterator();
      while (localIterator2.hasNext())
      {
        RouteFilter localRouteFilter = (RouteFilter)localIterator2.next();
        ArrayList localArrayList2 = new ArrayList();
        Iterator localIterator3 = localRouteFilter.queues.iterator();
        while (localIterator3.hasNext())
        {
          String str = (String)localIterator3.next();
          if ((localArrayList1.contains(str)) || ("trash".equals(str))) {
            localArrayList2.add(str);
          } else {
            this.mLogger.w("Dispatcher", "Dispatcher ignored a route to a non-existent queue: " + str + " in the dispatcher configuration", new Object[0]);
          }
        }
        if (localArrayList2.size() > 0) {
          localBuilder.addRoute(localRouteFilter.filter, localArrayList2);
        }
      }
    }
    return localBuilder.build();
  }
  
  public Config parseConfig(String paramString)
    throws IOException
  {
    Config localConfig = (Config)this.mMapper.readValue(paramString, Config.class);
    IdentifierConfig localIdentifierConfig = parseIdentifierConfig(localConfig);
    RoutingConfig localRoutingConfig = parseRoutingConfig(localConfig);
    return new Config.Builder().setRoutingConfig(localRoutingConfig).setIdentifierConfig(localIdentifierConfig).build();
  }
  
  public static class IdentifierFilter
  {
    @JsonProperty("filter")
    public String filter;
    @JsonProperty("identifiers")
    public String identifiers;
  }
  
  public static class RouteFilter
  {
    @JsonProperty("filter")
    public String filter;
    @JsonProperty("queues")
    public List<String> queues;
  }
  
  public static class Identifier
  {
    @JsonProperty("ids")
    public List<String> ids;
    @JsonProperty("name")
    public String name;
  }
  
  public static class Queue
  {
    @JsonProperty("count_network_fail_retries")
    public boolean countNetworkFail;
    @JsonProperty("host")
    public String host;
    @JsonProperty("max_age")
    public int maxAge;
    @JsonProperty("max_size")
    public int maxSize;
    @JsonProperty("name")
    public String name;
    @JsonProperty("protocol")
    public String protocol;
    @JsonProperty("max_retry_count")
    public int retryCount;
    @JsonProperty("retry_interval")
    public int retryInterval;
    @JsonProperty("url_fmt")
    public String urlFormat;
    
    public String formatEndpointAddress()
    {
      return this.urlFormat.replace("{protocol}", this.protocol).replace("{host}", this.host);
    }
  }
  
  public static class Config
  {
    @JsonProperty("identifier_filters")
    public List<ConfigParser.IdentifierFilter> identifierFilters;
    @JsonProperty("identifiers")
    public List<ConfigParser.Identifier> identifiers;
    @JsonProperty("queues")
    public List<ConfigParser.Queue> queues;
    @JsonProperty("route_filters")
    public List<ConfigParser.RouteFilter> routeFilters;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/ConfigParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */