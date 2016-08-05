package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;

public abstract interface zzu
  extends IInterface
{
  public abstract zzd zza(zzd paramzzd, int paramInt1, int paramInt2)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzu
  {
    public static zzu zzaM(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzu)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
        if ((localIInterface != null) && ((localIInterface instanceof zzu))) {
          localObject = (zzu)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool;
      switch (paramInt1)
      {
      default: 
      case 1598968902: 
        for (bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool = true)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.common.internal.ISignInButtonCreator");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.common.internal.ISignInButtonCreator");
      zzd localzzd = zza(zzd.zza.zzbk(paramParcel1.readStrongBinder()), paramParcel1.readInt(), paramParcel1.readInt());
      paramParcel2.writeNoException();
      if (localzzd != null) {}
      for (IBinder localIBinder = localzzd.asBinder();; localIBinder = null)
      {
        paramParcel2.writeStrongBinder(localIBinder);
        bool = true;
        break;
      }
    }
    
    private static class zza
      implements zzu
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
      public zzd zza(zzd paramzzd, int paramInt1, int paramInt2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 5
        //   10: aload 4
        //   12: ldc 31
        //   14: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_1
        //   18: ifnull +74 -> 92
        //   21: aload_1
        //   22: invokeinterface 39 1 0
        //   27: astore 7
        //   29: aload 4
        //   31: aload 7
        //   33: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   36: aload 4
        //   38: iload_2
        //   39: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   42: aload 4
        //   44: iload_3
        //   45: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   48: aload_0
        //   49: getfield 18	com/google/android/gms/common/internal/zzu$zza$zza:zznJ	Landroid/os/IBinder;
        //   52: iconst_1
        //   53: aload 4
        //   55: aload 5
        //   57: iconst_0
        //   58: invokeinterface 52 5 0
        //   63: pop
        //   64: aload 5
        //   66: invokevirtual 55	android/os/Parcel:readException	()V
        //   69: aload 5
        //   71: invokevirtual 58	android/os/Parcel:readStrongBinder	()Landroid/os/IBinder;
        //   74: invokestatic 64	com/google/android/gms/dynamic/zzd$zza:zzbk	(Landroid/os/IBinder;)Lcom/google/android/gms/dynamic/zzd;
        //   77: astore 9
        //   79: aload 5
        //   81: invokevirtual 67	android/os/Parcel:recycle	()V
        //   84: aload 4
        //   86: invokevirtual 67	android/os/Parcel:recycle	()V
        //   89: aload 9
        //   91: areturn
        //   92: aconst_null
        //   93: astore 7
        //   95: goto -66 -> 29
        //   98: astore 6
        //   100: aload 5
        //   102: invokevirtual 67	android/os/Parcel:recycle	()V
        //   105: aload 4
        //   107: invokevirtual 67	android/os/Parcel:recycle	()V
        //   110: aload 6
        //   112: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	113	0	this	zza
        //   0	113	1	paramzzd	zzd
        //   0	113	2	paramInt1	int
        //   0	113	3	paramInt2	int
        //   3	103	4	localParcel1	Parcel
        //   8	93	5	localParcel2	Parcel
        //   98	13	6	localObject	Object
        //   27	67	7	localIBinder	IBinder
        //   77	13	9	localzzd	zzd
        // Exception table:
        //   from	to	target	type
        //   10	79	98	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */