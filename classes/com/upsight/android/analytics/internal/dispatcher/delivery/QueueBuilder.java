package com.upsight.android.analytics.internal.dispatcher.delivery;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.internal.dispatcher.schema.Schema;
import com.upsight.android.analytics.internal.dispatcher.util.Selector;
import com.upsight.android.analytics.internal.session.Clock;
import com.upsight.android.logger.UpsightLogger;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.inject.Provider;
import rx.Scheduler;

public class QueueBuilder
{
  private static final String CHARSET_UTF_8 = "UTF-8";
  public static final String MACRO_APP_TOKEN = "{app_token}";
  public static final String MACRO_APP_VERSION = "{app_version}";
  public static final String MACRO_HOST = "{host}";
  public static final String MACRO_PROTOCOL = "{protocol}";
  public static final String MACRO_PROTOCOL_VERSION = "{version}";
  public static final String MACRO_SDK_VERSION = "{sdk_version}";
  private static final String PROTOCOL_VERSION = "v1";
  private Clock mClock;
  private Map<String, String> mEndpointMacros;
  private UpsightLogger mLogger;
  private ObjectMapper mObjectMapper;
  private Provider<ResponseParser> mResponseParserProvider;
  private Scheduler mRetryExecutor;
  private Scheduler mSendExecutor;
  private SignatureVerifier mSignatureVerifier;
  private UpsightContext mUpsight;
  
  QueueBuilder(UpsightContext paramUpsightContext, ObjectMapper paramObjectMapper, Clock paramClock, UpsightLogger paramUpsightLogger, Scheduler paramScheduler1, Scheduler paramScheduler2, SignatureVerifier paramSignatureVerifier, Provider<ResponseParser> paramProvider)
  {
    this.mUpsight = paramUpsightContext;
    this.mObjectMapper = paramObjectMapper;
    this.mClock = paramClock;
    this.mLogger = paramUpsightLogger;
    this.mRetryExecutor = paramScheduler1;
    this.mSendExecutor = paramScheduler2;
    this.mSignatureVerifier = paramSignatureVerifier;
    this.mResponseParserProvider = paramProvider;
    createEndpointMacroMap();
  }
  
  private void createEndpointMacroMap()
  {
    this.mEndpointMacros = new HashMap();
    this.mEndpointMacros.put("{version}", "v1");
    this.mEndpointMacros.put("{app_token}", this.mUpsight.getApplicationToken());
    this.mEndpointMacros.put("{app_version}", getAppVersionName(this.mUpsight));
    this.mEndpointMacros.put("{sdk_version}", this.mUpsight.getSdkVersion());
  }
  
  private String getAppVersionName(Context paramContext)
  {
    Object localObject = "";
    try
    {
      PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      if ((localPackageInfo != null) && (localPackageInfo.versionName != null))
      {
        String str3 = URLEncoder.encode(localPackageInfo.versionName, "UTF-8");
        localObject = str3;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        UpsightLogger localUpsightLogger2 = this.mLogger;
        String str2 = QueueBuilder.class.getSimpleName();
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = localNameNotFoundException;
        localUpsightLogger2.e(str2, "Could not get package info", arrayOfObject2);
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;)
      {
        UpsightLogger localUpsightLogger1 = this.mLogger;
        String str1 = QueueBuilder.class.getSimpleName();
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = localUnsupportedEncodingException;
        localUpsightLogger1.e(str1, "UTF-8 encoding not supported", arrayOfObject1);
      }
    }
    return (String)localObject;
  }
  
  public Queue build(String paramString, QueueConfig paramQueueConfig, Selector<Schema> paramSelector1, Selector<Schema> paramSelector2)
  {
    UpsightEndpoint localUpsightEndpoint = new UpsightEndpoint(prepareEndpoint(paramQueueConfig.getEndpointAddress()), this.mSignatureVerifier, this.mObjectMapper, this.mLogger);
    BatchSender localBatchSender = new BatchSender(this.mUpsight, paramQueueConfig.getBatchSenderConfig(), this.mRetryExecutor, this.mSendExecutor, localUpsightEndpoint, (ResponseParser)this.mResponseParserProvider.get(), this.mObjectMapper, this.mClock, this.mLogger);
    return new Queue(paramString, paramSelector1, paramSelector2, new BatcherFactory(paramQueueConfig.getBatcherConfig()), localBatchSender);
  }
  
  String prepareEndpoint(String paramString)
  {
    Iterator localIterator = this.mEndpointMacros.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      paramString = paramString.replace((CharSequence)localEntry.getKey(), (CharSequence)localEntry.getValue());
    }
    return paramString;
  }
  
  private class BatcherFactory
    implements Batcher.Factory
  {
    private Batcher.Config mConfig;
    
    public BatcherFactory(Batcher.Config paramConfig)
    {
      this.mConfig = paramConfig;
    }
    
    public Batcher create(Schema paramSchema, BatchSender paramBatchSender)
    {
      return new Batcher(this.mConfig, paramSchema, paramBatchSender, QueueBuilder.this.mRetryExecutor);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/delivery/QueueBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */