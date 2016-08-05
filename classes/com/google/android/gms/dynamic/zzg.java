package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzx;

public abstract class zzg<T>
{
  private final String zzapA;
  private T zzapB;
  
  protected zzg(String paramString)
  {
    this.zzapA = paramString;
  }
  
  protected final T zzas(Context paramContext)
    throws zzg.zza
  {
    ClassLoader localClassLoader;
    if (this.zzapB == null)
    {
      zzx.zzw(paramContext);
      Context localContext = GooglePlayServicesUtil.getRemoteContext(paramContext);
      if (localContext == null) {
        throw new zza("Could not get remote context.");
      }
      localClassLoader = localContext.getClassLoader();
    }
    try
    {
      this.zzapB = zzd((IBinder)localClassLoader.loadClass(this.zzapA).newInstance());
      return (T)this.zzapB;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new zza("Could not load creator class.", localClassNotFoundException);
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new zza("Could not instantiate creator.", localInstantiationException);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new zza("Could not access creator.", localIllegalAccessException);
    }
  }
  
  protected abstract T zzd(IBinder paramIBinder);
  
  public static class zza
    extends Exception
  {
    public zza(String paramString)
    {
      super();
    }
    
    public zza(String paramString, Throwable paramThrowable)
    {
      super(paramThrowable);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/dynamic/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */