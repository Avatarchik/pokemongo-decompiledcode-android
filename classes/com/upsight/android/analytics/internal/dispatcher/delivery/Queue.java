package com.upsight.android.analytics.internal.dispatcher.delivery;

import android.text.TextUtils;
import com.upsight.android.analytics.internal.DataStoreRecord;
import com.upsight.android.analytics.internal.dispatcher.routing.Packet;
import com.upsight.android.analytics.internal.dispatcher.schema.Schema;
import com.upsight.android.analytics.internal.dispatcher.util.Selector;
import java.util.HashMap;
import java.util.Map;

public class Queue
{
  private BatchSender mBatchSender;
  private Batcher.Factory mBatcherFactory;
  private Map<Schema, Batcher> mBatchers;
  private String mName;
  private Selector<Schema> mSchemaSelectorByName;
  private Selector<Schema> mSchemaSelectorByType;
  
  Queue(String paramString, Selector<Schema> paramSelector1, Selector<Schema> paramSelector2, Batcher.Factory paramFactory, BatchSender paramBatchSender)
  {
    this.mName = paramString;
    this.mSchemaSelectorByName = paramSelector1;
    this.mSchemaSelectorByType = paramSelector2;
    this.mBatchers = new HashMap();
    this.mBatcherFactory = paramFactory;
    this.mBatchSender = paramBatchSender;
  }
  
  private Schema selectSchema(DataStoreRecord paramDataStoreRecord)
  {
    Schema localSchema = null;
    String str = paramDataStoreRecord.getIdentifiers();
    if (!TextUtils.isEmpty(str)) {
      localSchema = (Schema)this.mSchemaSelectorByName.select(str);
    }
    if (localSchema == null) {
      localSchema = (Schema)this.mSchemaSelectorByType.select(paramDataStoreRecord.getSourceType());
    }
    return localSchema;
  }
  
  public void enqueuePacket(Packet paramPacket)
  {
    Schema localSchema = selectSchema(paramPacket.getRecord());
    Batcher localBatcher = (Batcher)this.mBatchers.get(localSchema);
    if (localBatcher == null)
    {
      localBatcher = this.mBatcherFactory.create(localSchema, this.mBatchSender);
      this.mBatchers.put(localSchema, localBatcher);
    }
    localBatcher.addPacket(paramPacket);
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public void setOnDeliveryListener(OnDeliveryListener paramOnDeliveryListener)
  {
    this.mBatchSender.setDeliveryListener(paramOnDeliveryListener);
  }
  
  public void setOnResponseListener(OnResponseListener paramOnResponseListener)
  {
    this.mBatchSender.setResponseListener(paramOnResponseListener);
  }
  
  public static final class Trash
    extends Queue
  {
    public static final String NAME = "trash";
    private OnDeliveryListener mOnDeliveryListener;
    
    public Trash()
    {
      super(null, null, null, null);
    }
    
    public void enqueuePacket(Packet paramPacket)
    {
      paramPacket.markTrashed();
      OnDeliveryListener localOnDeliveryListener = this.mOnDeliveryListener;
      if (localOnDeliveryListener != null) {
        localOnDeliveryListener.onDelivery(paramPacket);
      }
    }
    
    public void setOnDeliveryListener(OnDeliveryListener paramOnDeliveryListener)
    {
      this.mOnDeliveryListener = paramOnDeliveryListener;
    }
    
    public void setOnResponseListener(OnResponseListener paramOnResponseListener) {}
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/delivery/Queue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */