package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class zzw
{
  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static int hashCode(Object... paramVarArgs)
  {
    return Arrays.hashCode(paramVarArgs);
  }
  
  public static zza zzv(Object paramObject)
  {
    return new zza(paramObject, null);
  }
  
  public static final class zza
  {
    private final Object zzJm;
    private final List<String> zzago;
    
    private zza(Object paramObject)
    {
      this.zzJm = zzx.zzw(paramObject);
      this.zzago = new ArrayList();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(100).append(this.zzJm.getClass().getSimpleName()).append('{');
      int i = this.zzago.size();
      for (int j = 0; j < i; j++)
      {
        localStringBuilder.append((String)this.zzago.get(j));
        if (j < i - 1) {
          localStringBuilder.append(", ");
        }
      }
      return '}';
    }
    
    public zza zzg(String paramString, Object paramObject)
    {
      this.zzago.add((String)zzx.zzw(paramString) + "=" + String.valueOf(paramObject));
      return this;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/zzw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */