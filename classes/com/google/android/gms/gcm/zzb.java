package com.google.android.gms.gcm;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzb
  extends IInterface
{
  public abstract void zzgB(int paramInt)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzb
  {
    public static zzb zzbR(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzb)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.gcm.INetworkTaskCallback");
        if ((localIInterface != null) && ((localIInterface instanceof zzb))) {
          localObject = (zzb)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool = true;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.gcm.INetworkTaskCallback");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.gcm.INetworkTaskCallback");
        zzgB(paramParcel1.readInt());
        paramParcel2.writeNoException();
      }
    }
    
    private static class zza
      implements zzb
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
      
      public void zzgB(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.gcm.INetworkTaskCallback");
          localParcel1.writeInt(paramInt);
          this.zznJ.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/gcm/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */