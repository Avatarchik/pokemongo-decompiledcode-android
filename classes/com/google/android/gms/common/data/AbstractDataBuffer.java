package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T>
  implements DataBuffer<T>
{
  protected final DataHolder zzabq;
  
  protected AbstractDataBuffer(DataHolder paramDataHolder)
  {
    this.zzabq = paramDataHolder;
    if (this.zzabq != null) {
      this.zzabq.zzr(this);
    }
  }
  
  @Deprecated
  public final void close()
  {
    release();
  }
  
  public abstract T get(int paramInt);
  
  public int getCount()
  {
    if (this.zzabq == null) {}
    for (int i = 0;; i = this.zzabq.getCount()) {
      return i;
    }
  }
  
  @Deprecated
  public boolean isClosed()
  {
    if ((this.zzabq == null) || (this.zzabq.isClosed())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Iterator<T> iterator()
  {
    return new zzb(this);
  }
  
  public void release()
  {
    if (this.zzabq != null) {
      this.zzabq.close();
    }
  }
  
  public Iterator<T> singleRefIterator()
  {
    return new zzg(this);
  }
  
  public Bundle zzor()
  {
    return this.zzabq.zzor();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/data/AbstractDataBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */