package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface zzkd
  extends IInterface
{
  public abstract void zza(Account paramAccount, int paramInt, zzkc paramzzkc)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzkd
  {
    public static zzkd zzao(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzkd)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusService");
        if ((localIInterface != null) && ((localIInterface instanceof zzkd))) {
          localObject = (zzkd)localIInterface;
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
          paramParcel2.writeString("com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusService");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusService");
      if (paramParcel1.readInt() != 0) {}
      for (Account localAccount = (Account)Account.CREATOR.createFromParcel(paramParcel1);; localAccount = null)
      {
        zza(localAccount, paramParcel1.readInt(), zzkc.zza.zzan(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        break;
      }
    }
    
    private static class zza
      implements zzkd
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
      
      public void zza(Account paramAccount, int paramInt, zzkc paramzzkc)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusService");
            if (paramAccount != null)
            {
              localParcel1.writeInt(1);
              paramAccount.writeToParcel(localParcel1, 0);
              localParcel1.writeInt(paramInt);
              if (paramzzkc != null)
              {
                localIBinder = paramzzkc.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(1, localParcel1, localParcel2, 0);
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
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzkd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */