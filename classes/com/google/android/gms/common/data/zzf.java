package com.google.android.gms.common.data;

import java.util.ArrayList;

public abstract class zzf<T>
  extends AbstractDataBuffer<T>
{
  private boolean zzadD = false;
  private ArrayList<Integer> zzadE;
  
  protected zzf(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
  }
  
  private void zzoz()
  {
    Object localObject2;
    Object localObject3;
    try
    {
      if (!this.zzadD)
      {
        int i = this.zzabq.getCount();
        this.zzadE = new ArrayList();
        if (i > 0)
        {
          this.zzadE.add(Integer.valueOf(0));
          String str = zzoy();
          int j = this.zzabq.zzbt(0);
          localObject2 = this.zzabq.zzd(str, 0, j);
          k = 1;
          if (k < i)
          {
            int m = this.zzabq.zzbt(k);
            localObject3 = this.zzabq.zzd(str, k, m);
            if (localObject3 == null) {
              throw new NullPointerException("Missing value for markerColumn: " + str + ", at row: " + k + ", for window: " + m);
            }
          }
        }
      }
    }
    finally
    {
      int k;
      throw ((Throwable)localObject1);
      if (!((String)localObject3).equals(localObject2))
      {
        this.zzadE.add(Integer.valueOf(k));
        break label205;
        this.zzadD = true;
      }
      else
      {
        localObject3 = localObject2;
      }
      label205:
      k++;
    }
  }
  
  public final T get(int paramInt)
  {
    zzoz();
    return (T)zzj(zzbw(paramInt), zzbx(paramInt));
  }
  
  public int getCount()
  {
    zzoz();
    return this.zzadE.size();
  }
  
  int zzbw(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.zzadE.size())) {
      throw new IllegalArgumentException("Position " + paramInt + " is out of bounds for this buffer");
    }
    return ((Integer)this.zzadE.get(paramInt)).intValue();
  }
  
  protected int zzbx(int paramInt)
  {
    int i;
    if ((paramInt < 0) || (paramInt == this.zzadE.size())) {
      i = 0;
    }
    label141:
    for (;;)
    {
      return i;
      if (paramInt == -1 + this.zzadE.size()) {}
      for (i = this.zzabq.getCount() - ((Integer)this.zzadE.get(paramInt)).intValue();; i = ((Integer)this.zzadE.get(paramInt + 1)).intValue() - ((Integer)this.zzadE.get(paramInt)).intValue())
      {
        if (i != 1) {
          break label141;
        }
        int j = zzbw(paramInt);
        int k = this.zzabq.zzbt(j);
        String str = zzoA();
        if ((str == null) || (this.zzabq.zzd(str, j, k) != null)) {
          break;
        }
        i = 0;
        break;
      }
    }
  }
  
  protected abstract T zzj(int paramInt1, int paramInt2);
  
  protected String zzoA()
  {
    return null;
  }
  
  protected abstract String zzoy();
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/data/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */