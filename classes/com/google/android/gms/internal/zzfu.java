package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;

public abstract interface zzfu
  extends IInterface
{
  public abstract IBinder zzf(zzd paramzzd)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzfu
  {
    public static zzfu zzR(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzfu)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
        if ((localIInterface != null) && ((localIInterface instanceof zzfu))) {
          localObject = (zzfu)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.purchase.client.IInAppPurchaseManagerCreator");
        IBinder localIBinder = zzf(zzd.zza.zzbk(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        paramParcel2.writeStrongBinder(localIBinder);
      }
    }
    
    private static class zza
      implements zzfu
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
      public IBinder zzf(zzd paramzzd)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +52 -> 67
        //   18: aload_1
        //   19: invokeinterface 40 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 43	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/internal/zzfu$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: iconst_1
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 49 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 52	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 55	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   54: astore 7
        //   56: aload_3
        //   57: invokevirtual 58	android/os/Parcel:recycle	()V
        //   60: aload_2
        //   61: invokevirtual 58	android/os/Parcel:recycle	()V
        //   64: aload 7
        //   66: areturn
        //   67: aconst_null
        //   68: astore 5
        //   70: goto -44 -> 26
        //   73: astore 4
        //   75: aload_3
        //   76: invokevirtual 58	android/os/Parcel:recycle	()V
        //   79: aload_2
        //   80: invokevirtual 58	android/os/Parcel:recycle	()V
        //   83: aload 4
        //   85: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	86	0	this	zza
        //   0	86	1	paramzzd	zzd
        //   3	77	2	localParcel1	Parcel
        //   7	69	3	localParcel2	Parcel
        //   73	11	4	localObject	Object
        //   24	45	5	localIBinder1	IBinder
        //   54	11	7	localIBinder2	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	56	73	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzfu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */