package com.google.android.gms.ads.internal.reward.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;
import com.google.android.gms.internal.zzem;
import com.google.android.gms.internal.zzem.zza;

public abstract interface zzc
  extends IInterface
{
  public abstract IBinder zza(zzd paramzzd, zzem paramzzem, int paramInt)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzc
  {
    public static zzc zzab(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzc)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
        if ((localIInterface != null) && ((localIInterface instanceof zzc))) {
          localObject = (zzc)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
        IBinder localIBinder = zza(zzd.zza.zzbk(paramParcel1.readStrongBinder()), zzem.zza.zzE(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeStrongBinder(localIBinder);
      }
    }
    
    private static class zza
      implements zzc
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
      
      /* Error */
      public IBinder zza(zzd paramzzd, zzem paramzzem, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 31
        //   17: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +84 -> 105
        //   24: aload_1
        //   25: invokeinterface 39 1 0
        //   30: astore 8
        //   32: aload 5
        //   34: aload 8
        //   36: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload_2
        //   40: ifnull +11 -> 51
        //   43: aload_2
        //   44: invokeinterface 45 1 0
        //   49: astore 4
        //   51: aload 5
        //   53: aload 4
        //   55: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   58: aload 5
        //   60: iload_3
        //   61: invokevirtual 49	android/os/Parcel:writeInt	(I)V
        //   64: aload_0
        //   65: getfield 18	com/google/android/gms/ads/internal/reward/client/zzc$zza$zza:zznJ	Landroid/os/IBinder;
        //   68: iconst_1
        //   69: aload 5
        //   71: aload 6
        //   73: iconst_0
        //   74: invokeinterface 55 5 0
        //   79: pop
        //   80: aload 6
        //   82: invokevirtual 58	android/os/Parcel:readException	()V
        //   85: aload 6
        //   87: invokevirtual 61	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   90: astore 10
        //   92: aload 6
        //   94: invokevirtual 64	android/os/Parcel:recycle	()V
        //   97: aload 5
        //   99: invokevirtual 64	android/os/Parcel:recycle	()V
        //   102: aload 10
        //   104: areturn
        //   105: aconst_null
        //   106: astore 8
        //   108: goto -76 -> 32
        //   111: astore 7
        //   113: aload 6
        //   115: invokevirtual 64	android/os/Parcel:recycle	()V
        //   118: aload 5
        //   120: invokevirtual 64	android/os/Parcel:recycle	()V
        //   123: aload 7
        //   125: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	126	0	this	zza
        //   0	126	1	paramzzd	zzd
        //   0	126	2	paramzzem	zzem
        //   0	126	3	paramInt	int
        //   1	53	4	localIBinder1	IBinder
        //   6	113	5	localParcel1	Parcel
        //   11	103	6	localParcel2	Parcel
        //   111	13	7	localObject	Object
        //   30	77	8	localIBinder2	IBinder
        //   90	13	10	localIBinder3	IBinder
        // Exception table:
        //   from	to	target	type
        //   13	92	111	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/reward/client/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */