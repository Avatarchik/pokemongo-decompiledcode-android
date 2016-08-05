package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class zzsb
  implements Cloneable
{
  private zzrz<?, ?> zzbir;
  private Object zzbis;
  private List<zzsg> zzbit = new ArrayList();
  
  private byte[] toByteArray()
    throws IOException
  {
    byte[] arrayOfByte = new byte[zzB()];
    zza(zzrx.zzC(arrayOfByte));
    return arrayOfByte;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = false;
    if (paramObject == this) {
      bool1 = true;
    }
    for (;;)
    {
      return bool1;
      if (!(paramObject instanceof zzsb)) {
        continue;
      }
      zzsb localzzsb = (zzsb)paramObject;
      if ((this.zzbis != null) && (localzzsb.zzbis != null))
      {
        if (this.zzbir != localzzsb.zzbir) {
          continue;
        }
        if (!this.zzbir.zzbil.isArray())
        {
          bool1 = this.zzbis.equals(localzzsb.zzbis);
          continue;
        }
        if ((this.zzbis instanceof byte[]))
        {
          bool1 = Arrays.equals((byte[])this.zzbis, (byte[])localzzsb.zzbis);
          continue;
        }
        if ((this.zzbis instanceof int[]))
        {
          bool1 = Arrays.equals((int[])this.zzbis, (int[])localzzsb.zzbis);
          continue;
        }
        if ((this.zzbis instanceof long[]))
        {
          bool1 = Arrays.equals((long[])this.zzbis, (long[])localzzsb.zzbis);
          continue;
        }
        if ((this.zzbis instanceof float[]))
        {
          bool1 = Arrays.equals((float[])this.zzbis, (float[])localzzsb.zzbis);
          continue;
        }
        if ((this.zzbis instanceof double[]))
        {
          bool1 = Arrays.equals((double[])this.zzbis, (double[])localzzsb.zzbis);
          continue;
        }
        if ((this.zzbis instanceof boolean[]))
        {
          bool1 = Arrays.equals((boolean[])this.zzbis, (boolean[])localzzsb.zzbis);
          continue;
        }
        bool1 = Arrays.deepEquals((Object[])this.zzbis, (Object[])localzzsb.zzbis);
        continue;
      }
      if ((this.zzbit != null) && (localzzsb.zzbit != null))
      {
        bool1 = this.zzbit.equals(localzzsb.zzbit);
        continue;
      }
      try
      {
        boolean bool2 = Arrays.equals(toByteArray(), localzzsb.toByteArray());
        bool1 = bool2;
      }
      catch (IOException localIOException)
      {
        throw new IllegalStateException(localIOException);
      }
    }
  }
  
  public int hashCode()
  {
    try
    {
      int i = Arrays.hashCode(toByteArray());
      return i + 527;
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
  }
  
  int zzB()
  {
    int i;
    if (this.zzbis != null) {
      i = this.zzbir.zzX(this.zzbis);
    }
    for (;;)
    {
      return i;
      Iterator localIterator = this.zzbit.iterator();
      i = 0;
      while (localIterator.hasNext()) {
        i += ((zzsg)localIterator.next()).zzB();
      }
    }
  }
  
  public final zzsb zzFI()
  {
    int i = 0;
    zzsb localzzsb = new zzsb();
    try
    {
      localzzsb.zzbir = this.zzbir;
      if (this.zzbit == null) {
        localzzsb.zzbit = null;
      }
      while (this.zzbis == null)
      {
        return localzzsb;
        localzzsb.zzbit.addAll(this.zzbit);
      }
      if (!(this.zzbis instanceof zzse)) {
        break label94;
      }
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
    localzzsb.zzbis = ((zzse)this.zzbis).zzFG();
    return localzzsb;
    label94:
    if ((this.zzbis instanceof byte[]))
    {
      localzzsb.zzbis = ((byte[])this.zzbis).clone();
    }
    else
    {
      if ((this.zzbis instanceof byte[][]))
      {
        byte[][] arrayOfByte1 = (byte[][])this.zzbis;
        byte[][] arrayOfByte2 = new byte[arrayOfByte1.length][];
        localzzsb.zzbis = arrayOfByte2;
        for (int j = 0; j < arrayOfByte1.length; j++) {
          arrayOfByte2[j] = ((byte[])arrayOfByte1[j].clone());
        }
      }
      if ((this.zzbis instanceof boolean[]))
      {
        localzzsb.zzbis = ((boolean[])this.zzbis).clone();
      }
      else if ((this.zzbis instanceof int[]))
      {
        localzzsb.zzbis = ((int[])this.zzbis).clone();
      }
      else if ((this.zzbis instanceof long[]))
      {
        localzzsb.zzbis = ((long[])this.zzbis).clone();
      }
      else if ((this.zzbis instanceof float[]))
      {
        localzzsb.zzbis = ((float[])this.zzbis).clone();
      }
      else if ((this.zzbis instanceof double[]))
      {
        localzzsb.zzbis = ((double[])this.zzbis).clone();
      }
      else if ((this.zzbis instanceof zzse[]))
      {
        zzse[] arrayOfzzse1 = (zzse[])this.zzbis;
        zzse[] arrayOfzzse2 = new zzse[arrayOfzzse1.length];
        localzzsb.zzbis = arrayOfzzse2;
        while (i < arrayOfzzse1.length)
        {
          arrayOfzzse2[i] = arrayOfzzse1[i].zzFG();
          i++;
        }
      }
    }
    return localzzsb;
  }
  
  void zza(zzrx paramzzrx)
    throws IOException
  {
    if (this.zzbis != null) {
      this.zzbir.zza(this.zzbis, paramzzrx);
    }
    for (;;)
    {
      return;
      Iterator localIterator = this.zzbit.iterator();
      while (localIterator.hasNext()) {
        ((zzsg)localIterator.next()).zza(paramzzrx);
      }
    }
  }
  
  void zza(zzsg paramzzsg)
  {
    this.zzbit.add(paramzzsg);
  }
  
  <T> T zzb(zzrz<?, T> paramzzrz)
  {
    if (this.zzbis != null)
    {
      if (this.zzbir != paramzzrz) {
        throw new IllegalStateException("Tried to getExtension with a differernt Extension.");
      }
    }
    else
    {
      this.zzbir = paramzzrz;
      this.zzbis = paramzzrz.zzE(this.zzbit);
      this.zzbit = null;
    }
    return (T)this.zzbis;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzsb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */