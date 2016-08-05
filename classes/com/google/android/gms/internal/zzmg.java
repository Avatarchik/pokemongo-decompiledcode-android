package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class zzmg<K, V>
{
  private int size;
  private final LinkedHashMap<K, V> zzagB;
  private int zzagC;
  private int zzagD;
  private int zzagE;
  private int zzagF;
  private int zzagG;
  private int zzagH;
  
  public zzmg(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("maxSize <= 0");
    }
    this.zzagC = paramInt;
    this.zzagB = new LinkedHashMap(0, 0.75F, true);
  }
  
  private int zzc(K paramK, V paramV)
  {
    int i = sizeOf(paramK, paramV);
    if (i < 0) {
      throw new IllegalStateException("Negative size: " + paramK + "=" + paramV);
    }
    return i;
  }
  
  protected V create(K paramK)
  {
    return null;
  }
  
  protected void entryRemoved(boolean paramBoolean, K paramK, V paramV1, V paramV2) {}
  
  public final void evictAll()
  {
    trimToSize(-1);
  }
  
  public final V get(K paramK)
  {
    if (paramK == null) {
      throw new NullPointerException("key == null");
    }
    Object localObject3;
    try
    {
      localObject2 = this.zzagB.get(paramK);
      if (localObject2 != null)
      {
        this.zzagG = (1 + this.zzagG);
      }
      else
      {
        this.zzagH = (1 + this.zzagH);
        localObject3 = create(paramK);
        if (localObject3 == null) {
          localObject2 = null;
        }
      }
    }
    finally {}
    try
    {
      this.zzagE = (1 + this.zzagE);
      localObject2 = this.zzagB.put(paramK, localObject3);
      if (localObject2 != null) {
        this.zzagB.put(paramK, localObject2);
      }
      for (;;)
      {
        if (localObject2 == null) {
          break;
        }
        entryRemoved(false, paramK, localObject3, localObject2);
        return (V)localObject2;
        this.size += zzc(paramK, localObject3);
      }
      trimToSize(this.zzagC);
    }
    finally {}
    Object localObject2 = localObject3;
    return (V)localObject2;
  }
  
  public final V put(K paramK, V paramV)
  {
    if ((paramK == null) || (paramV == null)) {
      throw new NullPointerException("key == null || value == null");
    }
    try
    {
      this.zzagD = (1 + this.zzagD);
      this.size += zzc(paramK, paramV);
      Object localObject2 = this.zzagB.put(paramK, paramV);
      if (localObject2 != null) {
        this.size -= zzc(paramK, localObject2);
      }
      if (localObject2 != null) {
        entryRemoved(false, paramK, localObject2, paramV);
      }
      trimToSize(this.zzagC);
      return (V)localObject2;
    }
    finally {}
  }
  
  /**
   * @deprecated
   */
  public final int size()
  {
    try
    {
      int i = this.size;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected int sizeOf(K paramK, V paramV)
  {
    return 1;
  }
  
  /**
   * @deprecated
   */
  public final String toString()
  {
    int i = 0;
    try
    {
      int j = this.zzagG + this.zzagH;
      if (j != 0) {
        i = 100 * this.zzagG / j;
      }
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = Integer.valueOf(this.zzagC);
      arrayOfObject[1] = Integer.valueOf(this.zzagG);
      arrayOfObject[2] = Integer.valueOf(this.zzagH);
      arrayOfObject[3] = Integer.valueOf(i);
      String str = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", arrayOfObject);
      return str;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void trimToSize(int paramInt)
  {
    Object localObject2;
    Object localObject3;
    try
    {
      if ((this.size < 0) || ((this.zzagB.isEmpty()) && (this.size != 0))) {
        throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
      }
    }
    finally
    {
      throw ((Throwable)localObject1);
      if ((this.size <= paramInt) || (this.zzagB.isEmpty())) {
        return;
      }
      Map.Entry localEntry = (Map.Entry)this.zzagB.entrySet().iterator().next();
      localObject2 = localEntry.getKey();
      localObject3 = localEntry.getValue();
      this.zzagB.remove(localObject2);
      this.size -= zzc(localObject2, localObject3);
      this.zzagF = (1 + this.zzagF);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzmg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */