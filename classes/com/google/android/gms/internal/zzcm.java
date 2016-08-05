package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;

public abstract interface zzcm
  extends IInterface
{
  public abstract double getScale()
    throws RemoteException;
  
  public abstract Uri getUri()
    throws RemoteException;
  
  public abstract zzd zzdv()
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzcm
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeAdImage");
    }
    
    public static zzcm zzt(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzcm)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        if ((localIInterface != null) && ((localIInterface instanceof zzcm))) {
          localObject = (zzcm)localIInterface;
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
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        zzd localzzd = zzdv();
        paramParcel2.writeNoException();
        if (localzzd != null) {}
        for (IBinder localIBinder = localzzd.asBinder();; localIBinder = null)
        {
          paramParcel2.writeStrongBinder(localIBinder);
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        Uri localUri = getUri();
        paramParcel2.writeNoException();
        if (localUri != null)
        {
          paramParcel2.writeInt(1);
          localUri.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          bool = true;
          break;
          paramParcel2.writeInt(0);
        }
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        double d = getScale();
        paramParcel2.writeNoException();
        paramParcel2.writeDouble(d);
        bool = true;
      }
    }
    
    private static class zza
      implements zzcm
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
      
      public double getScale()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
          this.zznJ.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          double d = localParcel2.readDouble();
          return d;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public Uri getUri()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_1
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_1
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 18	com/google/android/gms/internal/zzcm$zza$zza:zznJ	Landroid/os/IBinder;
        //   18: iconst_2
        //   19: aload_1
        //   20: aload_2
        //   21: iconst_0
        //   22: invokeinterface 42 5 0
        //   27: pop
        //   28: aload_2
        //   29: invokevirtual 45	android/os/Parcel:readException	()V
        //   32: aload_2
        //   33: invokevirtual 57	android/os/Parcel:readInt	()I
        //   36: ifeq +28 -> 64
        //   39: getstatic 63	android/net/Uri:CREATOR	Landroid/os/Parcelable$Creator;
        //   42: aload_2
        //   43: invokeinterface 69 2 0
        //   48: checkcast 59	android/net/Uri
        //   51: astore 5
        //   53: aload_2
        //   54: invokevirtual 51	android/os/Parcel:recycle	()V
        //   57: aload_1
        //   58: invokevirtual 51	android/os/Parcel:recycle	()V
        //   61: aload 5
        //   63: areturn
        //   64: aconst_null
        //   65: astore 5
        //   67: goto -14 -> 53
        //   70: astore_3
        //   71: aload_2
        //   72: invokevirtual 51	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: invokevirtual 51	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	81	0	this	zza
        //   3	73	1	localParcel1	Parcel
        //   7	65	2	localParcel2	Parcel
        //   70	10	3	localObject	Object
        //   51	15	5	localUri	Uri
        // Exception table:
        //   from	to	target	type
        //   8	53	70	finally
      }
      
      public zzd zzdv()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
          this.zznJ.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          zzd localzzd = zzd.zza.zzbk(localParcel2.readStrongBinder());
          return localzzd;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */