package com.upsight.android.analytics.internal.dispatcher.delivery;

import com.upsight.android.analytics.internal.dispatcher.routing.Packet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class Batch
{
  private int mCapacityLeft;
  private Set<Packet> mPackets;
  
  public Batch(int paramInt)
  {
    this.mCapacityLeft = paramInt;
    this.mPackets = new HashSet();
  }
  
  public void addPacket(Packet paramPacket)
  {
    this.mPackets.add(paramPacket);
    this.mCapacityLeft = (-1 + this.mCapacityLeft);
  }
  
  public int capacityLeft()
  {
    return this.mCapacityLeft;
  }
  
  public Set<Packet> getPackets()
  {
    return Collections.unmodifiableSet(this.mPackets);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/upsight/android/analytics/internal/dispatcher/delivery/Batch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */