package com.google.android.gms.ads.internal.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;
import com.google.android.gms.internal.zzem;
import com.google.android.gms.internal.zzem.zza;

public abstract interface zzr
  extends IInterface
{
  public abstract IBinder zza(zzd paramzzd, String paramString, zzem paramzzem, int paramInt)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzr
  {
    public static zzr zzj(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzr)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
        if ((localIInterface != null) && ((localIInterface instanceof zzr))) {
          localObject = (zzr)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
        IBinder localIBinder = zza(zzd.zza.zzbk(paramParcel1.readStrongBinder()), paramParcel1.readString(), zzem.zza.zzE(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeStrongBinder(localIBinder);
      }
    }
    
    private static class zza
      implements zzr
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
      public IBinder zza(zzd paramzzd, String paramString, zzem paramzzem, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 5
        //   3: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 7
        //   13: aload 6
        //   15: ldc 31
        //   17: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +91 -> 112
        //   24: aload_1
        //   25: invokeinterface 39 1 0
        //   30: astore 9
        //   32: aload 6
        //   34: aload 9
        //   36: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 6
        //   41: aload_2
        //   42: invokevirtual 45	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   45: aload_3
        //   46: ifnull +11 -> 57
        //   49: aload_3
        //   50: invokeinterface 48 1 0
        //   55: astore 5
        //   57: aload 6
        //   59: aload 5
        //   61: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   64: aload 6
        //   66: iload 4
        //   68: invokevirtual 52	android/os/Parcel:writeInt	(I)V
        //   71: aload_0
        //   72: getfield 18	com/google/android/gms/ads/internal/client/zzr$zza$zza:zznJ	Landroid/os/IBinder;
        //   75: iconst_1
        //   76: aload 6
        //   78: aload 7
        //   80: iconst_0
        //   81: invokeinterface 58 5 0
        //   86: pop
        //   87: aload 7
        //   89: invokevirtual 61	android/os/Parcel:readException	()V
        //   92: aload 7
        //   94: invokevirtual 64	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   97: astore 11
        //   99: aload 7
        //   101: invokevirtual 67	android/os/Parcel:recycle	()V
        //   104: aload 6
        //   106: invokevirtual 67	android/os/Parcel:recycle	()V
        //   109: aload 11
        //   111: areturn
        //   112: aconst_null
        //   113: astore 9
        //   115: goto -83 -> 32
        //   118: astore 8
        //   120: aload 7
        //   122: invokevirtual 67	android/os/Parcel:recycle	()V
        //   125: aload 6
        //   127: invokevirtual 67	android/os/Parcel:recycle	()V
        //   130: aload 8
        //   132: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	133	0	this	zza
        //   0	133	1	paramzzd	zzd
        //   0	133	2	paramString	String
        //   0	133	3	paramzzem	zzem
        //   0	133	4	paramInt	int
        //   1	59	5	localIBinder1	IBinder
        //   6	120	6	localParcel1	Parcel
        //   11	110	7	localParcel2	Parcel
        //   118	13	8	localObject	Object
        //   30	84	9	localIBinder2	IBinder
        //   97	13	11	localIBinder3	IBinder
        // Exception table:
        //   from	to	target	type
        //   13	99	118	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */