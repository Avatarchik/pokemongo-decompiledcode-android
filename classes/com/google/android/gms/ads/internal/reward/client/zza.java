package com.google.android.gms.ads.internal.reward.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zza
  extends IInterface
{
  public abstract int getAmount()
    throws RemoteException;
  
  public abstract String getType()
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zza
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.reward.client.IRewardItem");
    }
    
    public static zza zzZ(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zza)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardItem");
        if ((localIInterface != null) && ((localIInterface instanceof zza))) {
          localObject = (zza)localIInterface;
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
      boolean bool = true;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.ads.internal.reward.client.IRewardItem");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardItem");
        String str = getType();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardItem");
        int i = getAmount();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(i);
      }
    }
    
    private static class zza
      implements zza
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
      
      public int getAmount()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardItem");
          this.zznJ.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String getType()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardItem");
          this.zznJ.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/reward/client/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */