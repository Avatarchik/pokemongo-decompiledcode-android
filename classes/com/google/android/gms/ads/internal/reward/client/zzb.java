package com.google.android.gms.ads.internal.reward.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzb
  extends IInterface
{
  public abstract void destroy()
    throws RemoteException;
  
  public abstract boolean isLoaded()
    throws RemoteException;
  
  public abstract void pause()
    throws RemoteException;
  
  public abstract void resume()
    throws RemoteException;
  
  public abstract void setUserId(String paramString)
    throws RemoteException;
  
  public abstract void show()
    throws RemoteException;
  
  public abstract void zza(RewardedVideoAdRequestParcel paramRewardedVideoAdRequestParcel)
    throws RemoteException;
  
  public abstract void zza(zzd paramzzd)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzb
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }
    
    public static zzb zzaa(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzb)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        if ((localIInterface != null) && ((localIInterface instanceof zzb))) {
          localObject = (zzb)localIInterface;
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
      }
      for (;;)
      {
        return i;
        paramParcel2.writeString("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        if (paramParcel1.readInt() != 0) {}
        for (RewardedVideoAdRequestParcel localRewardedVideoAdRequestParcel = RewardedVideoAdRequestParcel.CREATOR.zzn(paramParcel1);; localRewardedVideoAdRequestParcel = null)
        {
          zza(localRewardedVideoAdRequestParcel);
          paramParcel2.writeNoException();
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        show();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        zza(zzd.zza.zzac(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        setUserId(paramParcel1.readString());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        boolean bool = isLoaded();
        paramParcel2.writeNoException();
        if (bool) {}
        int k;
        for (int j = i;; k = 0)
        {
          paramParcel2.writeInt(j);
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        pause();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        resume();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        destroy();
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
      
      public void destroy()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
          this.zznJ.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean isLoaded()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
          this.zznJ.transact(5, localParcel1, localParcel2, 0);
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
      
      public void pause()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
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
      
      public void resume()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
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
      
      public void setUserId(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
          localParcel1.writeString(paramString);
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
      
      public void show()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
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
      
      /* Error */
      public void zza(RewardedVideoAdRequestParcel paramRewardedVideoAdRequestParcel)
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
        //   15: ifnull +41 -> 56
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 65	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 71	com/google/android/gms/ads/internal/reward/client/RewardedVideoAdRequestParcel:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/ads/internal/reward/client/zzb$zza$zza:zznJ	Landroid/os/IBinder;
        //   33: iconst_1
        //   34: aload_2
        //   35: aload_3
        //   36: iconst_0
        //   37: invokeinterface 41 5 0
        //   42: pop
        //   43: aload_3
        //   44: invokevirtual 44	android/os/Parcel:readException	()V
        //   47: aload_3
        //   48: invokevirtual 47	android/os/Parcel:recycle	()V
        //   51: aload_2
        //   52: invokevirtual 47	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_2
        //   57: iconst_0
        //   58: invokevirtual 65	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore 4
        //   66: aload_3
        //   67: invokevirtual 47	android/os/Parcel:recycle	()V
        //   70: aload_2
        //   71: invokevirtual 47	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	zza
        //   0	77	1	paramRewardedVideoAdRequestParcel	RewardedVideoAdRequestParcel
        //   3	68	2	localParcel1	Parcel
        //   7	60	3	localParcel2	Parcel
        //   64	11	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	47	64	finally
        //   56	61	64	finally
      }
      
      /* Error */
      public void zza(zzd paramzzd)
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
        //   19: invokeinterface 76 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 79	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/ads/internal/reward/client/zzb$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: iconst_3
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
        //   0	78	1	paramzzd	zzd
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/reward/client/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */