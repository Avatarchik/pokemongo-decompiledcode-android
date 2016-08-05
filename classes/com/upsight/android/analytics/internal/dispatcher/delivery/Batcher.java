package com.upsight.android.analytics.internal.dispatcher.delivery;

import com.upsight.android.analytics.internal.dispatcher.routing.Packet;
import com.upsight.android.analytics.internal.dispatcher.schema.Schema;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Scheduler.Worker;
import rx.Subscription;
import rx.functions.Action0;

public class Batcher
{
  private static final int DISABLE_AGING_MAX_AGE;
  private Scheduler mAgingExecutor;
  private Action0 mAgingRunnable = new Action0()
  {
    public void call()
    {
      Batcher.this.sendCurrentBatch();
    }
  };
  private Subscription mAgingTask;
  private BatchSender mBatchSender;
  private Config mConfig;
  private Batch mCurrentBatch;
  private Schema mSchema;
  
  Batcher(Config paramConfig, Schema paramSchema, BatchSender paramBatchSender, Scheduler paramScheduler)
  {
    this.mSchema = paramSchema;
    this.mBatchSender = paramBatchSender;
    this.mConfig = paramConfig;
    this.mAgingExecutor = paramScheduler;
  }
  
  /**
   * @deprecated
   */
  private void sendCurrentBatch()
  {
    try
    {
      Batch localBatch = this.mCurrentBatch;
      if (localBatch != null)
      {
        this.mCurrentBatch = null;
        if (this.mAgingTask != null)
        {
          this.mAgingTask.unsubscribe();
          this.mAgingTask = null;
        }
        this.mBatchSender.submitRequest(new BatchSender.Request(localBatch, this.mSchema));
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  public void addPacket(Packet paramPacket)
  {
    try
    {
      if (this.mCurrentBatch == null)
      {
        this.mCurrentBatch = new Batch(this.mConfig.batchCapacity);
        if (this.mConfig.maxBatchAge != 0) {
          this.mAgingTask = this.mAgingExecutor.createWorker().schedule(this.mAgingRunnable, this.mConfig.maxBatchAge, TimeUnit.SECONDS);
        }
      }
      this.mCurrentBatch.addPacket(paramPacket);
      if (this.mCurrentBatch.capacityLeft() == 0) {
        sendCurrentBatch();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static class Config
  {
    public final int batchCapacity;
    public final int maxBatchAge;
    
    public Config(int paramInt1, int paramInt2)
    {
      this.batchCapacity = paramInt1;
      this.maxBatchAge = paramInt2;
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
          if ((localConfig.batchCapacity != this.batchCapacity) || (localConfig.maxBatchAge != this.maxBatchAge)) {
            bool = false;
          }
        }
      }
    }
    
    public boolean isValid()
    {
      if ((this.maxBatchAge >= 0) && (this.batchCapacity > 0)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
  
  public static abstract interface Factory
  {
    public abstract Batcher create(Schema paramSchema, BatchSender paramBatchSender);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/delivery/Batcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */