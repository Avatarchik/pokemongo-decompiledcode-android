package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzx;
import java.lang.reflect.Method;

public class ProviderInstaller
{
  public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
  private static Method zzaUV = null;
  private static final GoogleApiAvailability zzacJ = ;
  private static final Object zzpy = new Object();
  
  public static void installIfNeeded(Context paramContext)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    zzx.zzb(paramContext, "Context must not be null");
    zzacJ.zzab(paramContext);
    Context localContext = GooglePlayServicesUtil.getRemoteContext(paramContext);
    if (localContext == null)
    {
      Log.e("ProviderInstaller", "Failed to get remote context");
      throw new GooglePlayServicesNotAvailableException(8);
    }
    synchronized (zzpy)
    {
      try
      {
        if (zzaUV == null) {
          zzaM(localContext);
        }
        Method localMethod = zzaUV;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localContext;
        localMethod.invoke(null, arrayOfObject);
        return;
      }
      catch (Exception localException)
      {
        Log.e("ProviderInstaller", "Failed to install provider: " + localException.getMessage());
        throw new GooglePlayServicesNotAvailableException(8);
      }
    }
  }
  
  public static void installIfNeededAsync(Context paramContext, final ProviderInstallListener paramProviderInstallListener)
  {
    zzx.zzb(paramContext, "Context must not be null");
    zzx.zzb(paramProviderInstallListener, "Listener must not be null");
    zzx.zzci("Must be called on the UI thread");
    new AsyncTask()
    {
      protected Integer zzc(Void... paramAnonymousVarArgs)
      {
        try
        {
          ProviderInstaller.installIfNeeded(ProviderInstaller.this);
          localInteger = Integer.valueOf(0);
        }
        catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
        {
          for (;;)
          {
            localInteger = Integer.valueOf(localGooglePlayServicesRepairableException.getConnectionStatusCode());
          }
        }
        catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
        {
          for (;;)
          {
            Integer localInteger = Integer.valueOf(localGooglePlayServicesNotAvailableException.errorCode);
          }
        }
        return localInteger;
      }
      
      protected void zze(Integer paramAnonymousInteger)
      {
        if (paramAnonymousInteger.intValue() == 0) {
          paramProviderInstallListener.onProviderInstalled();
        }
        for (;;)
        {
          return;
          Intent localIntent = ProviderInstaller.zzCd().zza(ProviderInstaller.this, paramAnonymousInteger.intValue(), "pi");
          paramProviderInstallListener.onProviderInstallFailed(paramAnonymousInteger.intValue(), localIntent);
        }
      }
    }.execute(new Void[0]);
  }
  
  private static void zzaM(Context paramContext)
    throws ClassNotFoundException, NoSuchMethodException
  {
    Class localClass = paramContext.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl");
    Class[] arrayOfClass = new Class[1];
    arrayOfClass[0] = Context.class;
    zzaUV = localClass.getMethod("insertProvider", arrayOfClass);
  }
  
  public static abstract interface ProviderInstallListener
  {
    public abstract void onProviderInstallFailed(int paramInt, Intent paramIntent);
    
    public abstract void onProviderInstalled();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/security/ProviderInstaller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */