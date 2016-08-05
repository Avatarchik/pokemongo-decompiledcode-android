package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.identity.intents.UserAddressRequest;

public abstract interface zzpa
  extends IInterface
{
  public abstract void zza(zzoz paramzzoz, UserAddressRequest paramUserAddressRequest, Bundle paramBundle)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzpa
  {
    public static zzpa zzbU(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzpa)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.identity.intents.internal.IAddressService");
        if ((localIInterface != null) && ((localIInterface instanceof zzpa))) {
          localObject = (zzpa)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool;
      switch (paramInt1)
      {
      default: 
      case 1598968902: 
        for (bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool = true)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.identity.intents.internal.IAddressService");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.identity.intents.internal.IAddressService");
      zzoz localzzoz = zzoz.zza.zzbT(paramParcel1.readStrongBinder());
      UserAddressRequest localUserAddressRequest;
      if (paramParcel1.readInt() != 0)
      {
        localUserAddressRequest = (UserAddressRequest)UserAddressRequest.CREATOR.createFromParcel(paramParcel1);
        label90:
        if (paramParcel1.readInt() == 0) {
          break label137;
        }
      }
      label137:
      for (Bundle localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; localBundle = null)
      {
        zza(localzzoz, localUserAddressRequest, localBundle);
        paramParcel2.writeNoException();
        bool = true;
        break;
        localUserAddressRequest = null;
        break label90;
      }
    }
    
    private static class zza
      implements zzpa
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
      
      public void zza(zzoz paramzzoz, UserAddressRequest paramUserAddressRequest, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        label132:
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.identity.intents.internal.IAddressService");
            IBinder localIBinder;
            if (paramzzoz != null)
            {
              localIBinder = paramzzoz.asBinder();
              localParcel1.writeStrongBinder(localIBinder);
              if (paramUserAddressRequest != null)
              {
                localParcel1.writeInt(1);
                paramUserAddressRequest.writeToParcel(localParcel1, 0);
                if (paramBundle == null) {
                  break label132;
                }
                localParcel1.writeInt(1);
                paramBundle.writeToParcel(localParcel1, 0);
                this.zznJ.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localIBinder = null;
              continue;
            }
            localParcel1.writeInt(0);
            continue;
            localParcel1.writeInt(0);
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzpa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */