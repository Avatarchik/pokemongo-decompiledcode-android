package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzem
  extends IInterface
{
  public abstract zzen zzae(String paramString)
    throws RemoteException;
  
  public abstract boolean zzaf(String paramString)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzem
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }
    
    public static zzem zzE(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzem)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        if ((localIInterface != null) && ((localIInterface instanceof zzem))) {
          localObject = (zzem)localIInterface;
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
      int i = 1;
      switch (paramInt1)
      {
      default: 
        i = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        for (;;)
        {
          return i;
          paramParcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        }
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        zzen localzzen = zzae(paramParcel1.readString());
        paramParcel2.writeNoException();
        if (localzzen != null) {}
        for (IBinder localIBinder = localzzen.asBinder();; localIBinder = null)
        {
          paramParcel2.writeStrongBinder(localIBinder);
          break;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
      boolean bool = zzaf(paramParcel1.readString());
      paramParcel2.writeNoException();
      if (bool) {}
      int k;
      for (int j = i;; k = 0)
      {
        paramParcel2.writeInt(j);
        break;
      }
    }
    
    private static class zza
      implements zzem
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
      
      public zzen zzae(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
          localParcel1.writeString(paramString);
          this.zznJ.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          zzen localzzen = zzen.zza.zzF(localParcel2.readStrongBinder());
          return localzzen;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean zzaf(String paramString)
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
          localParcel1.writeString(paramString);
          this.zznJ.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          return bool;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */