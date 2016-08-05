package com.google.android.gms.internal;

public final class zzsa
  implements Cloneable
{
  private static final zzsb zzbin = new zzsb();
  private int mSize;
  private boolean zzbio = false;
  private int[] zzbip;
  private zzsb[] zzbiq;
  
  zzsa()
  {
    this(10);
  }
  
  zzsa(int paramInt)
  {
    int i = idealIntArraySize(paramInt);
    this.zzbip = new int[i];
    this.zzbiq = new zzsb[i];
    this.mSize = 0;
  }
  
  private void gc()
  {
    int i = this.mSize;
    int[] arrayOfInt = this.zzbip;
    zzsb[] arrayOfzzsb = this.zzbiq;
    int j = 0;
    int k = 0;
    while (j < i)
    {
      zzsb localzzsb = arrayOfzzsb[j];
      if (localzzsb != zzbin)
      {
        if (j != k)
        {
          arrayOfInt[k] = arrayOfInt[j];
          arrayOfzzsb[k] = localzzsb;
          arrayOfzzsb[j] = null;
        }
        k++;
      }
      j++;
    }
    this.zzbio = false;
    this.mSize = k;
  }
  
  private int idealByteArraySize(int paramInt)
  {
    for (int i = 4;; i++) {
      if (i < 32)
      {
        if (paramInt <= -12 + (1 << i)) {
          paramInt = -12 + (1 << i);
        }
      }
      else {
        return paramInt;
      }
    }
  }
  
  private int idealIntArraySize(int paramInt)
  {
    return idealByteArraySize(paramInt * 4) / 4;
  }
  
  private boolean zza(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    boolean bool = false;
    int i = 0;
    if (i < paramInt) {
      if (paramArrayOfInt1[i] == paramArrayOfInt2[i]) {}
    }
    for (;;)
    {
      return bool;
      i++;
      break;
      bool = true;
    }
  }
  
  private boolean zza(zzsb[] paramArrayOfzzsb1, zzsb[] paramArrayOfzzsb2, int paramInt)
  {
    boolean bool = false;
    int i = 0;
    if (i < paramInt) {
      if (paramArrayOfzzsb1[i].equals(paramArrayOfzzsb2[i])) {}
    }
    for (;;)
    {
      return bool;
      i++;
      break;
      bool = true;
    }
  }
  
  private int zzlT(int paramInt)
  {
    int i = 0;
    int j = -1 + this.mSize;
    int m;
    for (;;)
    {
      if (i > j) {
        break label66;
      }
      m = i + j >>> 1;
      int n = this.zzbip[m];
      if (n < paramInt)
      {
        i = m + 1;
      }
      else
      {
        if (n <= paramInt) {
          break;
        }
        j = m - 1;
      }
    }
    label66:
    for (int k = m;; k = i ^ 0xFFFFFFFF) {
      return k;
    }
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof zzsa))
      {
        bool = false;
      }
      else
      {
        zzsa localzzsa = (zzsa)paramObject;
        if (size() != localzzsa.size()) {
          bool = false;
        } else if ((!zza(this.zzbip, localzzsa.zzbip, this.mSize)) || (!zza(this.zzbiq, localzzsa.zzbiq, this.mSize))) {
          bool = false;
        }
      }
    }
  }
  
  public int hashCode()
  {
    if (this.zzbio) {
      gc();
    }
    int i = 17;
    for (int j = 0; j < this.mSize; j++) {
      i = 31 * (i * 31 + this.zzbip[j]) + this.zzbiq[j].hashCode();
    }
    return i;
  }
  
  public boolean isEmpty()
  {
    if (size() == 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  int size()
  {
    if (this.zzbio) {
      gc();
    }
    return this.mSize;
  }
  
  public final zzsa zzFH()
  {
    int i = 0;
    int j = size();
    zzsa localzzsa = new zzsa(j);
    System.arraycopy(this.zzbip, 0, localzzsa.zzbip, 0, j);
    while (i < j)
    {
      if (this.zzbiq[i] != null) {
        localzzsa.zzbiq[i] = this.zzbiq[i].zzFI();
      }
      i++;
    }
    localzzsa.mSize = j;
    return localzzsa;
  }
  
  void zza(int paramInt, zzsb paramzzsb)
  {
    int i = zzlT(paramInt);
    if (i >= 0) {
      this.zzbiq[i] = paramzzsb;
    }
    for (;;)
    {
      return;
      int j = i ^ 0xFFFFFFFF;
      if ((j < this.mSize) && (this.zzbiq[j] == zzbin))
      {
        this.zzbip[j] = paramInt;
        this.zzbiq[j] = paramzzsb;
      }
      else
      {
        if ((this.zzbio) && (this.mSize >= this.zzbip.length))
        {
          gc();
          j = 0xFFFFFFFF ^ zzlT(paramInt);
        }
        if (this.mSize >= this.zzbip.length)
        {
          int k = idealIntArraySize(1 + this.mSize);
          int[] arrayOfInt = new int[k];
          zzsb[] arrayOfzzsb = new zzsb[k];
          System.arraycopy(this.zzbip, 0, arrayOfInt, 0, this.zzbip.length);
          System.arraycopy(this.zzbiq, 0, arrayOfzzsb, 0, this.zzbiq.length);
          this.zzbip = arrayOfInt;
          this.zzbiq = arrayOfzzsb;
        }
        if (this.mSize - j != 0)
        {
          System.arraycopy(this.zzbip, j, this.zzbip, j + 1, this.mSize - j);
          System.arraycopy(this.zzbiq, j, this.zzbiq, j + 1, this.mSize - j);
        }
        this.zzbip[j] = paramInt;
        this.zzbiq[j] = paramzzsb;
        this.mSize = (1 + this.mSize);
      }
    }
  }
  
  zzsb zzlR(int paramInt)
  {
    int i = zzlT(paramInt);
    if ((i < 0) || (this.zzbiq[i] == zzbin)) {}
    for (zzsb localzzsb = null;; localzzsb = this.zzbiq[i]) {
      return localzzsb;
    }
  }
  
  zzsb zzlS(int paramInt)
  {
    if (this.zzbio) {
      gc();
    }
    return this.zzbiq[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzsa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */