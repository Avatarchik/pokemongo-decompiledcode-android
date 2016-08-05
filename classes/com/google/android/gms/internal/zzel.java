package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Map;

@zzgr
public final class zzel
  extends zzem.zza
{
  private Map<Class<? extends com.google.android.gms.ads.mediation.NetworkExtras>, com.google.android.gms.ads.mediation.NetworkExtras> zzzH;
  
  private <NETWORK_EXTRAS extends com.google.ads.mediation.NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> zzen zzag(String paramString)
    throws RemoteException
  {
    Object localObject;
    try
    {
      Class localClass = Class.forName(paramString, false, zzel.class.getClassLoader());
      if (com.google.ads.mediation.MediationAdapter.class.isAssignableFrom(localClass))
      {
        com.google.ads.mediation.MediationAdapter localMediationAdapter = (com.google.ads.mediation.MediationAdapter)localClass.newInstance();
        localObject = new zzey(localMediationAdapter, (com.google.ads.mediation.NetworkExtras)this.zzzH.get(localMediationAdapter.getAdditionalParametersType()));
      }
      else if (com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(localClass))
      {
        localObject = new zzet((com.google.android.gms.ads.mediation.MediationAdapter)localClass.newInstance());
      }
      else
      {
        zzb.zzaH("Could not instantiate mediation adapter: " + paramString + " (not a valid adapter).");
        throw new RemoteException();
      }
    }
    catch (Throwable localThrowable)
    {
      zzb.zzaH("Could not instantiate mediation adapter: " + paramString + ". " + localThrowable.getMessage());
      throw new RemoteException();
    }
    return (zzen)localObject;
  }
  
  public zzen zzae(String paramString)
    throws RemoteException
  {
    return zzag(paramString);
  }
  
  public boolean zzaf(String paramString)
    throws RemoteException
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = CustomEvent.class.isAssignableFrom(Class.forName(paramString, false, zzel.class.getClassLoader()));
      bool1 = bool2;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        zzb.zzaH("Could not load custom event implementation class: " + paramString + ", assuming old implementation.");
      }
    }
    return bool1;
  }
  
  public void zze(Map<Class<? extends com.google.android.gms.ads.mediation.NetworkExtras>, com.google.android.gms.ads.mediation.NetworkExtras> paramMap)
  {
    this.zzzH = paramMap;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */