package com.google.android.gms.ads.internal.reward.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzd
  extends IInterface
{
  public abstract void onRewardedVideoAdClosed()
    throws RemoteException;
  
  public abstract void onRewardedVideoAdFailedToLoad(int paramInt)
    throws RemoteException;
  
  public abstract void onRewardedVideoAdLeftApplication()
    throws RemoteException;
  
  public abstract void onRewardedVideoAdLoaded()
    throws RemoteException;
  
  public abstract void onRewardedVideoAdOpened()
    throws RemoteException;
  
  public abstract void onRewardedVideoStarted()
    throws RemoteException;
  
  public abstract void zza(zza paramzza)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzd
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
    }
    
    public static zzd zzac(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzd)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        if ((localIInterface != null) && ((localIInterface instanceof zzd))) {
          localObject = (zzd)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        onRewardedVideoAdLoaded();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        onRewardedVideoAdOpened();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        onRewardedVideoStarted();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        onRewardedVideoAdClosed();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        zza(zza.zza.zzZ(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        onRewardedVideoAdLeftApplication();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
        onRewardedVideoAdFailedToLoad(paramParcel1.readInt());
        paramParcel2.writeNoException();
      }
    }
    
    private static class zza
      implements zzd
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
      
      public void onRewardedVideoAdClosed()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
          this.zznJ.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onRewardedVideoAdFailedToLoad(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
          localParcel1.writeInt(paramInt);
          this.zznJ.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onRewardedVideoAdLeftApplication()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
          this.zznJ.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onRewardedVideoAdLoaded()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
          this.zznJ.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onRewardedVideoAdOpened()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
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
      
      public void onRewardedVideoStarted()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener");
          this.zznJ.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void zza(zza paramzza)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 61 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 64	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/ads/internal/reward/client/zzd$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: iconst_5
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 41 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 44	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 47	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 47	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore 5
        //   62: goto -36 -> 26
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 47	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 47	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramzza	zza
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        //   24	37	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	50	65	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/reward/client/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */