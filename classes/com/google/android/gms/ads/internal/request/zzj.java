package com.google.android.gms.ads.internal.request;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzj
  extends IInterface
{
  public abstract void zza(AdRequestInfoParcel paramAdRequestInfoParcel, zzk paramzzk)
    throws RemoteException;
  
  public abstract AdResponseParcel zze(AdRequestInfoParcel paramAdRequestInfoParcel)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzj
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }
    
    public static zzj zzX(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzj)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
        if ((localIInterface != null) && ((localIInterface instanceof zzj))) {
          localObject = (zzj)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      AdRequestInfoParcel localAdRequestInfoParcel = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.ads.internal.request.IAdRequestService");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
        if (paramParcel1.readInt() != 0) {
          localAdRequestInfoParcel = AdRequestInfoParcel.CREATOR.zzi(paramParcel1);
        }
        AdResponseParcel localAdResponseParcel = zze(localAdRequestInfoParcel);
        paramParcel2.writeNoException();
        if (localAdResponseParcel != null)
        {
          paramParcel2.writeInt(1);
          localAdResponseParcel.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          bool = true;
          break;
          paramParcel2.writeInt(0);
        }
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
        if (paramParcel1.readInt() != 0) {
          localAdRequestInfoParcel = AdRequestInfoParcel.CREATOR.zzi(paramParcel1);
        }
        zza(localAdRequestInfoParcel, zzk.zza.zzY(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
      }
    }
    
    private static class zza
      implements zzj
    {
      private IBinder zznJ;
      
      zza(IBinder paramIBinder)
      {
        this.zznJ = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.zznJ;
      }
      
      public void zza(AdRequestInfoParcel paramAdRequestInfoParcel, zzk paramzzk)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdRequestService");
            if (paramAdRequestInfoParcel != null)
            {
              localParcel1.writeInt(1);
              paramAdRequestInfoParcel.writeToParcel(localParcel1, 0);
              if (paramzzk != null)
              {
                localIBinder = paramzzk.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public AdResponseParcel zze(AdRequestInfoParcel paramAdRequestInfoParcel)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.request.IAdRequestService");
            if (paramAdRequestInfoParcel != null)
            {
              localParcel1.writeInt(1);
              paramAdRequestInfoParcel.writeToParcel(localParcel1, 0);
              this.zznJ.transact(1, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                AdResponseParcel localAdResponseParcel2 = AdResponseParcel.CREATOR.zzj(localParcel2);
                localAdResponseParcel1 = localAdResponseParcel2;
                return localAdResponseParcel1;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            AdResponseParcel localAdResponseParcel1 = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/request/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */