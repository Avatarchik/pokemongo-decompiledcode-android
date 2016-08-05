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

public abstract interface zzt
  extends IInterface
{
  public abstract IBinder zza(zzd paramzzd, AdSizeParcel paramAdSizeParcel, String paramString, zzem paramzzem, int paramInt)
    throws RemoteException;
  
  public abstract IBinder zza(zzd paramzzd, AdSizeParcel paramAdSizeParcel, String paramString, zzem paramzzem, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzt
  {
    public static zzt zzl(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzt)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        if ((localIInterface != null) && ((localIInterface instanceof zzt))) {
          localObject = (zzt)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      AdSizeParcel localAdSizeParcel = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        zzd localzzd2 = zzd.zza.zzbk(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {
          localAdSizeParcel = AdSizeParcel.CREATOR.zzc(paramParcel1);
        }
        IBinder localIBinder2 = zza(localzzd2, localAdSizeParcel, paramParcel1.readString(), zzem.zza.zzE(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeStrongBinder(localIBinder2);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        zzd localzzd1 = zzd.zza.zzbk(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {
          localAdSizeParcel = AdSizeParcel.CREATOR.zzc(paramParcel1);
        }
        IBinder localIBinder1 = zza(localzzd1, localAdSizeParcel, paramParcel1.readString(), zzem.zza.zzE(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeStrongBinder(localIBinder1);
        bool = true;
      }
    }
    
    private static class zza
      implements zzt
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
      public IBinder zza(zzd paramzzd, AdSizeParcel paramAdSizeParcel, String paramString, zzem paramzzem, int paramInt)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 6
        //   3: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 7
        //   8: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 8
        //   13: aload 7
        //   15: ldc 31
        //   17: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +110 -> 131
        //   24: aload_1
        //   25: invokeinterface 39 1 0
        //   30: astore 10
        //   32: aload 7
        //   34: aload 10
        //   36: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload_2
        //   40: ifnull +97 -> 137
        //   43: aload 7
        //   45: iconst_1
        //   46: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   49: aload_2
        //   50: aload 7
        //   52: iconst_0
        //   53: invokevirtual 52	com/google/android/gms/ads/internal/client/AdSizeParcel:writeToParcel	(Landroid/os/Parcel;I)V
        //   56: aload 7
        //   58: aload_3
        //   59: invokevirtual 55	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   62: aload 4
        //   64: ifnull +12 -> 76
        //   67: aload 4
        //   69: invokeinterface 58 1 0
        //   74: astore 6
        //   76: aload 7
        //   78: aload 6
        //   80: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   83: aload 7
        //   85: iload 5
        //   87: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   90: aload_0
        //   91: getfield 18	com/google/android/gms/ads/internal/client/zzt$zza$zza:zznJ	Landroid/os/IBinder;
        //   94: iconst_1
        //   95: aload 7
        //   97: aload 8
        //   99: iconst_0
        //   100: invokeinterface 64 5 0
        //   105: pop
        //   106: aload 8
        //   108: invokevirtual 67	android/os/Parcel:readException	()V
        //   111: aload 8
        //   113: invokevirtual 70	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   116: astore 12
        //   118: aload 8
        //   120: invokevirtual 73	android/os/Parcel:recycle	()V
        //   123: aload 7
        //   125: invokevirtual 73	android/os/Parcel:recycle	()V
        //   128: aload 12
        //   130: areturn
        //   131: aconst_null
        //   132: astore 10
        //   134: goto -102 -> 32
        //   137: aload 7
        //   139: iconst_0
        //   140: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   143: goto -87 -> 56
        //   146: astore 9
        //   148: aload 8
        //   150: invokevirtual 73	android/os/Parcel:recycle	()V
        //   153: aload 7
        //   155: invokevirtual 73	android/os/Parcel:recycle	()V
        //   158: aload 9
        //   160: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	161	0	this	zza
        //   0	161	1	paramzzd	zzd
        //   0	161	2	paramAdSizeParcel	AdSizeParcel
        //   0	161	3	paramString	String
        //   0	161	4	paramzzem	zzem
        //   0	161	5	paramInt	int
        //   1	78	6	localIBinder1	IBinder
        //   6	148	7	localParcel1	Parcel
        //   11	138	8	localParcel2	Parcel
        //   146	13	9	localObject	Object
        //   30	103	10	localIBinder2	IBinder
        //   116	13	12	localIBinder3	IBinder
        // Exception table:
        //   from	to	target	type
        //   13	118	146	finally
        //   137	143	146	finally
      }
      
      /* Error */
      public IBinder zza(zzd paramzzd, AdSizeParcel paramAdSizeParcel, String paramString, zzem paramzzem, int paramInt1, int paramInt2)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 7
        //   3: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 8
        //   8: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 9
        //   13: aload 8
        //   15: ldc 31
        //   17: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +117 -> 138
        //   24: aload_1
        //   25: invokeinterface 39 1 0
        //   30: astore 11
        //   32: aload 8
        //   34: aload 11
        //   36: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload_2
        //   40: ifnull +104 -> 144
        //   43: aload 8
        //   45: iconst_1
        //   46: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   49: aload_2
        //   50: aload 8
        //   52: iconst_0
        //   53: invokevirtual 52	com/google/android/gms/ads/internal/client/AdSizeParcel:writeToParcel	(Landroid/os/Parcel;I)V
        //   56: aload 8
        //   58: aload_3
        //   59: invokevirtual 55	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   62: aload 4
        //   64: ifnull +12 -> 76
        //   67: aload 4
        //   69: invokeinterface 58 1 0
        //   74: astore 7
        //   76: aload 8
        //   78: aload 7
        //   80: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   83: aload 8
        //   85: iload 5
        //   87: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   90: aload 8
        //   92: iload 6
        //   94: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   97: aload_0
        //   98: getfield 18	com/google/android/gms/ads/internal/client/zzt$zza$zza:zznJ	Landroid/os/IBinder;
        //   101: iconst_2
        //   102: aload 8
        //   104: aload 9
        //   106: iconst_0
        //   107: invokeinterface 64 5 0
        //   112: pop
        //   113: aload 9
        //   115: invokevirtual 67	android/os/Parcel:readException	()V
        //   118: aload 9
        //   120: invokevirtual 70	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   123: astore 13
        //   125: aload 9
        //   127: invokevirtual 73	android/os/Parcel:recycle	()V
        //   130: aload 8
        //   132: invokevirtual 73	android/os/Parcel:recycle	()V
        //   135: aload 13
        //   137: areturn
        //   138: aconst_null
        //   139: astore 11
        //   141: goto -109 -> 32
        //   144: aload 8
        //   146: iconst_0
        //   147: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   150: goto -94 -> 56
        //   153: astore 10
        //   155: aload 9
        //   157: invokevirtual 73	android/os/Parcel:recycle	()V
        //   160: aload 8
        //   162: invokevirtual 73	android/os/Parcel:recycle	()V
        //   165: aload 10
        //   167: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	168	0	this	zza
        //   0	168	1	paramzzd	zzd
        //   0	168	2	paramAdSizeParcel	AdSizeParcel
        //   0	168	3	paramString	String
        //   0	168	4	paramzzem	zzem
        //   0	168	5	paramInt1	int
        //   0	168	6	paramInt2	int
        //   1	78	7	localIBinder1	IBinder
        //   6	155	8	localParcel1	Parcel
        //   11	145	9	localParcel2	Parcel
        //   153	13	10	localObject	Object
        //   30	110	11	localIBinder2	IBinder
        //   123	13	13	localIBinder3	IBinder
        // Exception table:
        //   from	to	target	type
        //   13	125	153	finally
        //   144	150	153	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */