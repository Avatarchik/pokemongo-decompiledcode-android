package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;

public abstract interface zzcp
  extends IInterface
{
  public abstract IBinder zza(zzd paramzzd1, zzd paramzzd2, zzd paramzzd3, int paramInt)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzcp
  {
    public static zzcp zzv(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzcp)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
        if ((localIInterface != null) && ((localIInterface instanceof zzcp))) {
          localObject = (zzcp)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewDelegateCreator");
        IBinder localIBinder = zza(zzd.zza.zzbk(paramParcel1.readStrongBinder()), zzd.zza.zzbk(paramParcel1.readStrongBinder()), zzd.zza.zzbk(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramParcel2.writeStrongBinder(localIBinder);
      }
    }
    
    private static class zza
      implements zzcp
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
      public IBinder zza(zzd paramzzd1, zzd paramzzd2, zzd paramzzd3, int paramInt)
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
        //   21: ifnull +104 -> 125
        //   24: aload_1
        //   25: invokeinterface 39 1 0
        //   30: astore 9
        //   32: aload 6
        //   34: aload 9
        //   36: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload_2
        //   40: ifnull +91 -> 131
        //   43: aload_2
        //   44: invokeinterface 39 1 0
        //   49: astore 10
        //   51: aload 6
        //   53: aload 10
        //   55: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   58: aload_3
        //   59: ifnull +11 -> 70
        //   62: aload_3
        //   63: invokeinterface 39 1 0
        //   68: astore 5
        //   70: aload 6
        //   72: aload 5
        //   74: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   77: aload 6
        //   79: iload 4
        //   81: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   84: aload_0
        //   85: getfield 18	com/google/android/gms/internal/zzcp$zza$zza:zznJ	Landroid/os/IBinder;
        //   88: iconst_1
        //   89: aload 6
        //   91: aload 7
        //   93: iconst_0
        //   94: invokeinterface 52 5 0
        //   99: pop
        //   100: aload 7
        //   102: invokevirtual 55	android/os/Parcel:readException	()V
        //   105: aload 7
        //   107: invokevirtual 58	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   110: astore 12
        //   112: aload 7
        //   114: invokevirtual 61	android/os/Parcel:recycle	()V
        //   117: aload 6
        //   119: invokevirtual 61	android/os/Parcel:recycle	()V
        //   122: aload 12
        //   124: areturn
        //   125: aconst_null
        //   126: astore 9
        //   128: goto -96 -> 32
        //   131: aconst_null
        //   132: astore 10
        //   134: goto -83 -> 51
        //   137: astore 8
        //   139: aload 7
        //   141: invokevirtual 61	android/os/Parcel:recycle	()V
        //   144: aload 6
        //   146: invokevirtual 61	android/os/Parcel:recycle	()V
        //   149: aload 8
        //   151: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	152	0	this	zza
        //   0	152	1	paramzzd1	zzd
        //   0	152	2	paramzzd2	zzd
        //   0	152	3	paramzzd3	zzd
        //   0	152	4	paramInt	int
        //   1	72	5	localIBinder1	IBinder
        //   6	139	6	localParcel1	Parcel
        //   11	129	7	localParcel2	Parcel
        //   137	13	8	localObject	Object
        //   30	97	9	localIBinder2	IBinder
        //   49	84	10	localIBinder3	IBinder
        //   110	13	12	localIBinder4	IBinder
        // Exception table:
        //   from	to	target	type
        //   13	112	137	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */