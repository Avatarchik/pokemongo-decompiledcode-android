package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyGrpcRequest;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

public abstract interface zzkk
  extends IInterface
{
  public abstract void zza(zzkj paramzzkj, ProxyGrpcRequest paramProxyGrpcRequest)
    throws RemoteException;
  
  public abstract void zza(zzkj paramzzkj, ProxyRequest paramProxyRequest)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzkk
  {
    public static zzkk zzaw(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzkk)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.auth.api.internal.IAuthService");
        if ((localIInterface != null) && ((localIInterface instanceof zzkk))) {
          localObject = (zzkk)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.auth.api.internal.IAuthService");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.auth.api.internal.IAuthService");
        zzkj localzzkj2 = zzkj.zza.zzav(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {
          localObject = (ProxyRequest)ProxyRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza(localzzkj2, (ProxyRequest)localObject);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.auth.api.internal.IAuthService");
        zzkj localzzkj1 = zzkj.zza.zzav(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {
          localObject = (ProxyGrpcRequest)ProxyGrpcRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza(localzzkj1, (ProxyGrpcRequest)localObject);
        paramParcel2.writeNoException();
        bool = true;
      }
    }
    
    private static class zza
      implements zzkk
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
      public void zza(zzkj paramzzkj, ProxyGrpcRequest paramProxyGrpcRequest)
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
        //   16: ifnull +62 -> 78
        //   19: aload_1
        //   20: invokeinterface 39 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +50 -> 84
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 52	com/google/android/gms/auth/api/proxy/ProxyGrpcRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 18	com/google/android/gms/internal/zzkk$zza$zza:zznJ	Landroid/os/IBinder;
        //   52: iconst_2
        //   53: aload_3
        //   54: aload 4
        //   56: iconst_0
        //   57: invokeinterface 58 5 0
        //   62: pop
        //   63: aload 4
        //   65: invokevirtual 61	android/os/Parcel:readException	()V
        //   68: aload 4
        //   70: invokevirtual 64	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 64	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aconst_null
        //   79: astore 6
        //   81: goto -54 -> 27
        //   84: aload_3
        //   85: iconst_0
        //   86: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   89: goto -41 -> 48
        //   92: astore 5
        //   94: aload 4
        //   96: invokevirtual 64	android/os/Parcel:recycle	()V
        //   99: aload_3
        //   100: invokevirtual 64	android/os/Parcel:recycle	()V
        //   103: aload 5
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	zza
        //   0	106	1	paramzzkj	zzkj
        //   0	106	2	paramProxyGrpcRequest	ProxyGrpcRequest
        //   3	97	3	localParcel1	Parcel
        //   7	88	4	localParcel2	Parcel
        //   92	12	5	localObject	Object
        //   25	55	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	68	92	finally
        //   84	89	92	finally
      }
      
      /* Error */
      public void zza(zzkj paramzzkj, ProxyRequest paramProxyRequest)
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
        //   16: ifnull +62 -> 78
        //   19: aload_1
        //   20: invokeinterface 39 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +50 -> 84
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 68	com/google/android/gms/auth/api/proxy/ProxyRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 18	com/google/android/gms/internal/zzkk$zza$zza:zznJ	Landroid/os/IBinder;
        //   52: iconst_1
        //   53: aload_3
        //   54: aload 4
        //   56: iconst_0
        //   57: invokeinterface 58 5 0
        //   62: pop
        //   63: aload 4
        //   65: invokevirtual 61	android/os/Parcel:readException	()V
        //   68: aload 4
        //   70: invokevirtual 64	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 64	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aconst_null
        //   79: astore 6
        //   81: goto -54 -> 27
        //   84: aload_3
        //   85: iconst_0
        //   86: invokevirtual 46	android/os/Parcel:writeInt	(I)V
        //   89: goto -41 -> 48
        //   92: astore 5
        //   94: aload 4
        //   96: invokevirtual 64	android/os/Parcel:recycle	()V
        //   99: aload_3
        //   100: invokevirtual 64	android/os/Parcel:recycle	()V
        //   103: aload 5
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	zza
        //   0	106	1	paramzzkj	zzkj
        //   0	106	2	paramProxyRequest	ProxyRequest
        //   3	97	3	localParcel1	Parcel
        //   7	88	4	localParcel2	Parcel
        //   92	12	5	localObject	Object
        //   25	55	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	68	92	finally
        //   84	89	92	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzkk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */