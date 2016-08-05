package com.upsight.android.analytics.internal.dispatcher.delivery;

import android.text.TextUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.upsight.android.UpsightContext;
import com.upsight.android.analytics.internal.dispatcher.routing.Packet;
import com.upsight.android.analytics.internal.dispatcher.schema.Schema;
import com.upsight.android.analytics.internal.session.Clock;
import com.upsight.android.internal.util.NetworkHelper;
import com.upsight.android.logger.UpsightLogger;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.functions.Action0;

public class BatchSender
{
  private Scheduler mBatchSendExecutor;
  private final Clock mClock;
  private Config mConfig;
  private OnDeliveryListener mDeliveryListener;
  private UpsightEndpoint mEndpoint;
  private ReentrantLock mListenersLock;
  private final UpsightLogger mLogger;
  private ObjectMapper mObjectMapper;
  private OnResponseListener mResponseListener;
  private ResponseParser mResponseParser;
  private Scheduler mRetryExecutor;
  private ConcurrentMap<Request, Integer> mTryCounts;
  private UpsightContext mUpsight;
  
  BatchSender(UpsightContext paramUpsightContext, Config paramConfig, Scheduler paramScheduler1, Scheduler paramScheduler2, UpsightEndpoint paramUpsightEndpoint, ResponseParser paramResponseParser, ObjectMapper paramObjectMapper, Clock paramClock, UpsightLogger paramUpsightLogger)
  {
    this.mUpsight = paramUpsightContext;
    this.mEndpoint = paramUpsightEndpoint;
    this.mConfig = paramConfig;
    this.mRetryExecutor = paramScheduler1;
    this.mObjectMapper = paramObjectMapper;
    this.mBatchSendExecutor = paramScheduler2;
    this.mTryCounts = new ConcurrentHashMap();
    this.mListenersLock = new ReentrantLock();
    this.mResponseParser = paramResponseParser;
    this.mClock = paramClock;
    this.mLogger = paramUpsightLogger;
  }
  
  /* Error */
  private void notifyDeliveryListener(Batch paramBatch)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 76	com/upsight/android/analytics/internal/dispatcher/delivery/BatchSender:mListenersLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   4: invokevirtual 117	java/util/concurrent/locks/ReentrantLock:lock	()V
    //   7: aload_0
    //   8: getfield 119	com/upsight/android/analytics/internal/dispatcher/delivery/BatchSender:mDeliveryListener	Lcom/upsight/android/analytics/internal/dispatcher/delivery/OnDeliveryListener;
    //   11: ifnull +57 -> 68
    //   14: aload_1
    //   15: invokevirtual 125	com/upsight/android/analytics/internal/dispatcher/delivery/Batch:getPackets	()Ljava/util/Set;
    //   18: invokeinterface 131 1 0
    //   23: astore_3
    //   24: aload_3
    //   25: invokeinterface 137 1 0
    //   30: ifeq +38 -> 68
    //   33: aload_3
    //   34: invokeinterface 141 1 0
    //   39: checkcast 143	com/upsight/android/analytics/internal/dispatcher/routing/Packet
    //   42: astore 4
    //   44: aload_0
    //   45: getfield 119	com/upsight/android/analytics/internal/dispatcher/delivery/BatchSender:mDeliveryListener	Lcom/upsight/android/analytics/internal/dispatcher/delivery/OnDeliveryListener;
    //   48: aload 4
    //   50: invokeinterface 149 2 0
    //   55: goto -31 -> 24
    //   58: astore_2
    //   59: aload_0
    //   60: getfield 76	com/upsight/android/analytics/internal/dispatcher/delivery/BatchSender:mListenersLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   63: invokevirtual 152	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   66: aload_2
    //   67: athrow
    //   68: aload_0
    //   69: getfield 76	com/upsight/android/analytics/internal/dispatcher/delivery/BatchSender:mListenersLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   72: invokevirtual 152	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   75: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	76	0	this	BatchSender
    //   0	76	1	paramBatch	Batch
    //   58	9	2	localObject	Object
    //   23	11	3	localIterator	Iterator
    //   42	7	4	localPacket	Packet
    // Exception table:
    //   from	to	target	type
    //   7	55	58	finally
  }
  
  /* Error */
  private void notifyResponseListener(java.util.Collection<com.upsight.android.analytics.dispatcher.EndpointResponse> paramCollection)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 76	com/upsight/android/analytics/internal/dispatcher/delivery/BatchSender:mListenersLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   4: invokevirtual 117	java/util/concurrent/locks/ReentrantLock:lock	()V
    //   7: aload_0
    //   8: getfield 154	com/upsight/android/analytics/internal/dispatcher/delivery/BatchSender:mResponseListener	Lcom/upsight/android/analytics/internal/dispatcher/delivery/OnResponseListener;
    //   11: ifnull +54 -> 65
    //   14: aload_1
    //   15: invokeinterface 157 1 0
    //   20: astore_3
    //   21: aload_3
    //   22: invokeinterface 137 1 0
    //   27: ifeq +38 -> 65
    //   30: aload_3
    //   31: invokeinterface 141 1 0
    //   36: checkcast 159	com/upsight/android/analytics/dispatcher/EndpointResponse
    //   39: astore 4
    //   41: aload_0
    //   42: getfield 154	com/upsight/android/analytics/internal/dispatcher/delivery/BatchSender:mResponseListener	Lcom/upsight/android/analytics/internal/dispatcher/delivery/OnResponseListener;
    //   45: aload 4
    //   47: invokeinterface 165 2 0
    //   52: goto -31 -> 21
    //   55: astore_2
    //   56: aload_0
    //   57: getfield 76	com/upsight/android/analytics/internal/dispatcher/delivery/BatchSender:mListenersLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   60: invokevirtual 152	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   63: aload_2
    //   64: athrow
    //   65: aload_0
    //   66: getfield 76	com/upsight/android/analytics/internal/dispatcher/delivery/BatchSender:mListenersLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   69: invokevirtual 152	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   72: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	73	0	this	BatchSender
    //   0	73	1	paramCollection	java.util.Collection<com.upsight.android.analytics.dispatcher.EndpointResponse>
    //   55	9	2	localObject	Object
    //   20	11	3	localIterator	Iterator
    //   39	7	4	localEndpointResponse	com.upsight.android.analytics.dispatcher.EndpointResponse
    // Exception table:
    //   from	to	target	type
    //   7	52	55	finally
  }
  
  private void sendFailed(final Request paramRequest, FailReason paramFailReason, String paramString)
  {
    Integer localInteger = (Integer)this.mTryCounts.get(paramRequest);
    if (localInteger == null) {
      localInteger = Integer.valueOf(this.mConfig.maxRetryCount);
    }
    if (localInteger.intValue() > 0)
    {
      if ((paramFailReason != FailReason.NETWORK) || (this.mConfig.countNetworkFail)) {
        localInteger = Integer.valueOf(-1 + localInteger.intValue());
      }
      this.mTryCounts.put(paramRequest, localInteger);
      this.mRetryExecutor.createWorker().schedule(new Action0()
      {
        public void call()
        {
          new BatchSender.RetryTask(BatchSender.this, paramRequest).run();
        }
      }, this.mConfig.retryInterval, TimeUnit.SECONDS);
    }
    for (;;)
    {
      return;
      this.mTryCounts.remove(paramRequest);
      Iterator localIterator = paramRequest.batch.getPackets().iterator();
      while (localIterator.hasNext()) {
        ((Packet)localIterator.next()).failAndRoute(paramString);
      }
      notifyDeliveryListener(paramRequest.batch);
    }
  }
  
  private void sendSucceeded(Request paramRequest)
  {
    this.mTryCounts.remove(paramRequest);
    Iterator localIterator = paramRequest.batch.getPackets().iterator();
    while (localIterator.hasNext()) {
      ((Packet)localIterator.next()).markDelivered();
    }
    notifyDeliveryListener(paramRequest.batch);
  }
  
  public void setDeliveryListener(OnDeliveryListener paramOnDeliveryListener)
  {
    this.mListenersLock.lock();
    try
    {
      this.mDeliveryListener = paramOnDeliveryListener;
      return;
    }
    finally
    {
      this.mListenersLock.unlock();
    }
  }
  
  public void setResponseListener(OnResponseListener paramOnResponseListener)
  {
    this.mListenersLock.lock();
    try
    {
      this.mResponseListener = paramOnResponseListener;
      return;
    }
    finally
    {
      this.mListenersLock.unlock();
    }
  }
  
  public void submitRequest(final Request paramRequest)
  {
    this.mBatchSendExecutor.createWorker().schedule(new Action0()
    {
      public void call()
      {
        new BatchSender.BatchSendTask(BatchSender.this, paramRequest).run();
      }
    });
  }
  
  private class BatchSendTask
    implements Runnable
  {
    public static final String NETWORK_ERROR = "Network communication problems";
    private BatchSender.Request mRequest;
    
    public BatchSendTask(BatchSender.Request paramRequest)
    {
      this.mRequest = paramRequest;
    }
    
    public void run()
    {
      if (!NetworkHelper.isConnected(BatchSender.this.mUpsight)) {
        BatchSender.this.sendFailed(this.mRequest, BatchSender.FailReason.NETWORK, "Network communication problems");
      }
      UpsightEndpoint.Response localResponse;
      ResponseParser.Response localResponse1;
      for (;;)
      {
        return;
        try
        {
          localResponse = BatchSender.this.mEndpoint.send(new UpsightRequest(BatchSender.this.mUpsight, this.mRequest, BatchSender.this.mObjectMapper, BatchSender.this.mClock, BatchSender.this.mLogger));
          localResponse1 = null;
          if (!TextUtils.isEmpty(localResponse.body))
          {
            localResponse1 = BatchSender.this.mResponseParser.parse(localResponse.body);
            BatchSender.this.notifyResponseListener(localResponse1.responses);
          }
          if (!localResponse.isOk()) {
            break;
          }
          BatchSender.this.sendSucceeded(this.mRequest);
        }
        catch (IOException localIOException)
        {
          BatchSender.this.sendFailed(this.mRequest, BatchSender.FailReason.NETWORK, "Network communication problems");
        }
      }
      BatchSender.this.mLogger.e("BatchSender", "Received " + localResponse.statusCode + " HTTP response code from server", new Object[0]);
      BatchSender localBatchSender = BatchSender.this;
      BatchSender.Request localRequest = this.mRequest;
      BatchSender.FailReason localFailReason = BatchSender.FailReason.SERVER;
      if (localResponse1 != null) {}
      for (String str = localResponse1.error;; str = null)
      {
        localBatchSender.sendFailed(localRequest, localFailReason, str);
        break;
      }
    }
  }
  
  private class RetryTask
    implements Runnable
  {
    private BatchSender.Request mRequest;
    
    public RetryTask(BatchSender.Request paramRequest)
    {
      this.mRequest = paramRequest;
    }
    
    public void run()
    {
      BatchSender.this.submitRequest(this.mRequest);
    }
  }
  
  private static enum FailReason
  {
    static
    {
      NETWORK = new FailReason("NETWORK", 1);
      FailReason[] arrayOfFailReason = new FailReason[2];
      arrayOfFailReason[0] = SERVER;
      arrayOfFailReason[1] = NETWORK;
      $VALUES = arrayOfFailReason;
    }
    
    private FailReason() {}
  }
  
  public static class Config
  {
    public final boolean countNetworkFail;
    public final int maxRetryCount;
    public final int retryInterval;
    
    public Config(boolean paramBoolean, int paramInt1, int paramInt2)
    {
      this.countNetworkFail = paramBoolean;
      this.retryInterval = paramInt1;
      this.maxRetryCount = paramInt2;
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
          Config localConfig = (Config)paramObject;
          if ((localConfig.countNetworkFail != this.countNetworkFail) || (localConfig.retryInterval != this.retryInterval) || (localConfig.maxRetryCount != this.maxRetryCount)) {
            bool = false;
          }
        }
      }
    }
    
    public boolean isValid()
    {
      if ((this.retryInterval > 0) && (this.maxRetryCount >= 0)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
  
  public static final class Request
  {
    public final Batch batch;
    public final Schema schema;
    
    public Request(Batch paramBatch, Schema paramSchema)
    {
      this.batch = paramBatch;
      this.schema = paramSchema;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/delivery/BatchSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */