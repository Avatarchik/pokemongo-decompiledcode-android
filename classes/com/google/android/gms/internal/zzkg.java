package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.auth.api.consent.GetConsentIntentRequest;

public abstract interface zzkg
  extends IInterface
{
  public abstract Intent zza(GetConsentIntentRequest paramGetConsentIntentRequest)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzkg
  {
    public static zzkg zzaq(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzkg)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.auth.api.consent.internal.IConsentService");
        if ((localIInterface != null) && ((localIInterface instanceof zzkg))) {
          localObject = (zzkg)localIInterface;
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
          paramParcel2.writeString("com.google.android.gms.auth.api.consent.internal.IConsentService");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.auth.api.consent.internal.IConsentService");
      GetConsentIntentRequest localGetConsentIntentRequest;
      if (paramParcel1.readInt() != 0)
      {
        localGetConsentIntentRequest = (GetConsentIntentRequest)GetConsentIntentRequest.CREATOR.createFromParcel(paramParcel1);
        label81:
        Intent localIntent = zza(localGetConsentIntentRequest);
        paramParcel2.writeNoException();
        if (localIntent == null) {
          break label122;
        }
        paramParcel2.writeInt(1);
        localIntent.writeToParcel(paramParcel2, 1);
      }
      for (;;)
      {
        bool = true;
        break;
        localGetConsentIntentRequest = null;
        break label81;
        label122:
        paramParcel2.writeInt(0);
      }
    }
    
    private static class zza
      implements zzkg
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
      
      public Intent zza(GetConsentIntentRequest paramGetConsentIntentRequest)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.auth.api.consent.internal.IConsentService");
            if (paramGetConsentIntentRequest != null)
            {
              localParcel1.writeInt(1);
              paramGetConsentIntentRequest.writeToParcel(localParcel1, 0);
              this.zznJ.transact(1, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                localIntent = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
                return localIntent;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            Intent localIntent = null;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzkg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */