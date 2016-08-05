package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class zzrz<M extends zzry<M>, T>
{
  public final int tag;
  protected final int type;
  protected final Class<T> zzbil;
  protected final boolean zzbim;
  
  private zzrz(int paramInt1, Class<T> paramClass, int paramInt2, boolean paramBoolean)
  {
    this.type = paramInt1;
    this.zzbil = paramClass;
    this.tag = paramInt2;
    this.zzbim = paramBoolean;
  }
  
  private T zzF(List<zzsg> paramList)
  {
    int i = 0;
    ArrayList localArrayList = new ArrayList();
    for (int j = 0; j < paramList.size(); j++)
    {
      zzsg localzzsg = (zzsg)paramList.get(j);
      if (localzzsg.zzbiw.length != 0) {
        zza(localzzsg, localArrayList);
      }
    }
    int k = localArrayList.size();
    Object localObject;
    if (k == 0) {
      localObject = null;
    }
    for (;;)
    {
      return (T)localObject;
      localObject = this.zzbil.cast(Array.newInstance(this.zzbil.getComponentType(), k));
      while (i < k)
      {
        Array.set(localObject, i, localArrayList.get(i));
        i++;
      }
    }
  }
  
  private T zzG(List<zzsg> paramList)
  {
    if (paramList.isEmpty()) {}
    zzsg localzzsg;
    for (Object localObject = null;; localObject = this.zzbil.cast(zzF(zzrw.zzB(localzzsg.zzbiw))))
    {
      return (T)localObject;
      localzzsg = (zzsg)paramList.get(-1 + paramList.size());
    }
  }
  
  public static <M extends zzry<M>, T extends zzse> zzrz<M, T> zza(int paramInt, Class<T> paramClass, long paramLong)
  {
    return new zzrz(paramInt, paramClass, (int)paramLong, false);
  }
  
  final T zzE(List<zzsg> paramList)
  {
    Object localObject;
    if (paramList == null) {
      localObject = null;
    }
    for (;;)
    {
      return (T)localObject;
      if (this.zzbim) {
        localObject = zzF(paramList);
      } else {
        localObject = zzG(paramList);
      }
    }
  }
  
  protected Object zzF(zzrw paramzzrw)
  {
    Class localClass;
    if (this.zzbim) {
      localClass = this.zzbil.getComponentType();
    }
    zzse localzzse;
    try
    {
      switch (this.type)
      {
      default: 
        throw new IllegalArgumentException("Unknown type " + this.type);
      }
    }
    catch (InstantiationException localInstantiationException)
    {
      for (;;)
      {
        throw new IllegalArgumentException("Error creating instance of class " + localClass, localInstantiationException);
        localClass = this.zzbil;
      }
      localzzse = (zzse)localClass.newInstance();
      paramzzrw.zza(localzzse, zzsh.zzlV(this.tag));
      break label195;
      localzzse = (zzse)localClass.newInstance();
      paramzzrw.zza(localzzse);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new IllegalArgumentException("Error creating instance of class " + localClass, localIllegalAccessException);
    }
    catch (IOException localIOException)
    {
      throw new IllegalArgumentException("Error reading extension field", localIOException);
    }
    label195:
    return localzzse;
  }
  
  int zzX(Object paramObject)
  {
    if (this.zzbim) {}
    for (int i = zzY(paramObject);; i = zzZ(paramObject)) {
      return i;
    }
  }
  
  protected int zzY(Object paramObject)
  {
    int i = 0;
    int j = Array.getLength(paramObject);
    for (int k = 0; k < j; k++) {
      if (Array.get(paramObject, k) != null) {
        i += zzZ(Array.get(paramObject, k));
      }
    }
    return i;
  }
  
  protected int zzZ(Object paramObject)
  {
    int i = zzsh.zzlV(this.tag);
    switch (this.type)
    {
    default: 
      throw new IllegalArgumentException("Unknown type " + this.type);
    }
    for (int j = zzrx.zzb(i, (zzse)paramObject);; j = zzrx.zzc(i, (zzse)paramObject)) {
      return j;
    }
  }
  
  protected void zza(zzsg paramzzsg, List<Object> paramList)
  {
    paramList.add(zzF(zzrw.zzB(paramzzsg.zzbiw)));
  }
  
  void zza(Object paramObject, zzrx paramzzrx)
    throws IOException
  {
    if (this.zzbim) {
      zzc(paramObject, paramzzrx);
    }
    for (;;)
    {
      return;
      zzb(paramObject, paramzzrx);
    }
  }
  
  protected void zzb(Object paramObject, zzrx paramzzrx)
  {
    try
    {
      paramzzrx.zzlN(this.tag);
      switch (this.type)
      {
      default: 
        throw new IllegalArgumentException("Unknown type " + this.type);
      }
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
    zzse localzzse = (zzse)paramObject;
    int i = zzsh.zzlV(this.tag);
    paramzzrx.zzb(localzzse);
    paramzzrx.zzC(i, 4);
    return;
    paramzzrx.zzc((zzse)paramObject);
  }
  
  protected void zzc(Object paramObject, zzrx paramzzrx)
  {
    int i = Array.getLength(paramObject);
    for (int j = 0; j < i; j++)
    {
      Object localObject = Array.get(paramObject, j);
      if (localObject != null) {
        zzb(localObject, paramzzrx);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzrz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */