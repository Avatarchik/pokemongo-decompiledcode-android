package com.upsight.android.analytics.internal.dispatcher.delivery;

import java.net.MalformedURLException;
import java.net.URL;

public class QueueConfig
{
  private BatchSender.Config mBatchSenderConfig;
  private Batcher.Config mBatcherConfig;
  private String mEndpointAddress;
  
  private QueueConfig(Builder paramBuilder)
  {
    this.mEndpointAddress = paramBuilder.mEndpointAddress;
    this.mBatchSenderConfig = paramBuilder.mBatchSenderConfig;
    this.mBatcherConfig = paramBuilder.mBatcherConfig;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    QueueConfig localQueueConfig;
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
      localQueueConfig = (QueueConfig)paramObject;
      if (this.mBatchSenderConfig != null)
      {
        if (this.mBatchSenderConfig.equals(localQueueConfig.mBatchSenderConfig)) {}
      }
      else {
        while (localQueueConfig.mBatchSenderConfig != null)
        {
          bool = false;
          break;
        }
      }
      if (this.mBatcherConfig != null)
      {
        if (this.mBatcherConfig.equals(localQueueConfig.mBatcherConfig)) {}
      }
      else {
        while (localQueueConfig.mBatcherConfig != null)
        {
          bool = false;
          break;
        }
      }
      if (this.mEndpointAddress == null) {
        break;
      }
    } while (this.mEndpointAddress.equals(localQueueConfig.mEndpointAddress));
    for (;;)
    {
      bool = false;
      break;
      if (localQueueConfig.mEndpointAddress == null) {
        break;
      }
    }
  }
  
  public BatchSender.Config getBatchSenderConfig()
  {
    return this.mBatchSenderConfig;
  }
  
  public Batcher.Config getBatcherConfig()
  {
    return this.mBatcherConfig;
  }
  
  public String getEndpointAddress()
  {
    return this.mEndpointAddress;
  }
  
  public int hashCode()
  {
    int i = 0;
    int j;
    int k;
    if (this.mEndpointAddress != null)
    {
      j = this.mEndpointAddress.hashCode();
      k = j * 31;
      if (this.mBatchSenderConfig == null) {
        break label72;
      }
    }
    label72:
    for (int m = this.mBatchSenderConfig.hashCode();; m = 0)
    {
      int n = 31 * (k + m);
      if (this.mBatcherConfig != null) {
        i = this.mBatcherConfig.hashCode();
      }
      return n + i;
      j = 0;
      break;
    }
  }
  
  public boolean isValid()
  {
    boolean bool1 = false;
    try
    {
      new URL(this.mEndpointAddress);
      if ((this.mBatcherConfig != null) && (this.mBatcherConfig.isValid()) && (this.mBatchSenderConfig != null))
      {
        boolean bool2 = this.mBatchSenderConfig.isValid();
        if (bool2) {
          bool1 = true;
        }
      }
    }
    catch (MalformedURLException localMalformedURLException)
    {
      for (;;) {}
    }
    return bool1;
  }
  
  public static class Builder
  {
    private BatchSender.Config mBatchSenderConfig;
    private Batcher.Config mBatcherConfig;
    private String mEndpointAddress;
    
    public QueueConfig build()
    {
      return new QueueConfig(this, null);
    }
    
    public Builder setBatchSenderConfig(BatchSender.Config paramConfig)
    {
      this.mBatchSenderConfig = paramConfig;
      return this;
    }
    
    public Builder setBatcherConfig(Batcher.Config paramConfig)
    {
      this.mBatcherConfig = paramConfig;
      return this;
    }
    
    public Builder setEndpointAddress(String paramString)
    {
      this.mEndpointAddress = paramString;
      return this;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/delivery/QueueConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */