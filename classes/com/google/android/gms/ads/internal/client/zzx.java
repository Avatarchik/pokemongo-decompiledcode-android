package com.google.android.gms.ads.internal.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;

public abstract interface zzx
  extends IInterface
{
  public abstract IBinder zza(zzd paramzzd, int paramInt)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzx
  {
    public static zzx zzp(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzx)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManagerCreator");
        if ((localIInterface != null) && ((localIInterface instanceof zzx))) {
          localObject = (zzx)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.client.IMobileAdsSettingManagerCreator");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManagerCreator");
        IBinder localIBinder = zza(zzd.zza.zzbk(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeStrongBinder(localIBinder);
      }
    }
    
    private static class zza
      implements zzx
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
      public IBinder zza(zzd paramzzd, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 31
        //   12: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +61 -> 77
        //   19: aload_1
        //   20: invokeinterface 39 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_3
        //   34: iload_2
        //   35: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   38: aload_0
        //   39: getfield 18	com/google/android/gms/ads/internal/client/zzx$zza$zza:zznJ	Landroid/os/IBinder;
        //   42: iconst_1
        //   43: aload_3
        //   44: aload 4
        //   46: iconst_0
        //   47: invokeinterface 52 5 0
        //   52: pop
        //   53: aload 4
        //   55: invokevirtual 55	android/os/Parcel:readException	()V
        //   58: aload 4
        //   60: invokevirtual 58	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   63: astore 8
        //   65: aload 4
        //   67: invokevirtual 61	android/os/Parcel:recycle	()V
        //   70: aload_3
        //   71: invokevirtual 61	android/os/Parcel:recycle	()V
        //   74: aload 8
        //   76: areturn
        //   77: aconst_null
        //   78: astore 6
        //   80: goto -53 -> 27
        //   83: astore 5
        //   85: aload 4
        //   87: invokevirtual 61	android/os/Parcel:recycle	()V
        //   90: aload_3
        //   91: invokevirtual 61	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	97	0	this	zza
        //   0	97	1	paramzzd	zzd
        //   0	97	2	paramInt	int
        //   3	88	3	localParcel1	Parcel
        //   7	79	4	localParcel2	Parcel
        //   83	12	5	localObject	Object
        //   25	54	6	localIBinder1	IBinder
        //   63	12	8	localIBinder2	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	65	83	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */