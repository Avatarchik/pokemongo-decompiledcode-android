package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;

public abstract interface zzcj
  extends IInterface
{
  public abstract String getContent()
    throws RemoteException;
  
  public abstract void recordClick()
    throws RemoteException;
  
  public abstract void recordImpression()
    throws RemoteException;
  
  public abstract void zza(zzd paramzzd)
    throws RemoteException;
  
  public abstract String zzdr()
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzcj
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
    }
    
    public static zzcj zzr(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzcj)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
        if ((localIInterface != null) && ((localIInterface instanceof zzcj))) {
          localObject = (zzcj)localIInterface;
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
      boolean bool = true;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
        String str2 = zzdr();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str2);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
        String str1 = getContent();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str1);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
        zza(zzd.zza.zzbk(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
        recordClick();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
        recordImpression();
        paramParcel2.writeNoException();
      }
    }
    
    private static class zza
      implements zzcj
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
      
      public String getContent()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
          this.zznJ.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void recordClick()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
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
      
      public void recordImpression()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
          this.zznJ.transact(5, localParcel1, localParcel2, 0);
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
      public void zza(zzd paramzzd)
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
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 58 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 61	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/internal/zzcj$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: iconst_3
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 42 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 45	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 51	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 51	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore 5
        //   62: goto -36 -> 26
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 51	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 51	android/os/Parcel:recycle	()V
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
      
      public String zzdr()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.customrenderedad.client.ICustomRenderedAd");
          this.zznJ.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */