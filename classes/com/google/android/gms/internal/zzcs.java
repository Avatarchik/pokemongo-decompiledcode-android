package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zzd.zza;
import java.util.ArrayList;
import java.util.List;

public abstract interface zzcs
  extends IInterface
{
  public abstract String getAdvertiser()
    throws RemoteException;
  
  public abstract String getBody()
    throws RemoteException;
  
  public abstract String getCallToAction()
    throws RemoteException;
  
  public abstract Bundle getExtras()
    throws RemoteException;
  
  public abstract String getHeadline()
    throws RemoteException;
  
  public abstract List getImages()
    throws RemoteException;
  
  public abstract zzcm zzdA()
    throws RemoteException;
  
  public abstract zzd zzdx()
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzcs
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.formats.client.INativeContentAd");
    }
    
    public static zzcs zzx(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzcs)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
        if ((localIInterface != null) && ((localIInterface instanceof zzcs))) {
          localObject = (zzcs)localIInterface;
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
      IBinder localIBinder = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
      case 2: 
      case 3: 
      case 4: 
      case 5: 
      case 6: 
      case 7: 
      case 8: 
        for (;;)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          zzd localzzd = zzdx();
          paramParcel2.writeNoException();
          if (localzzd != null) {
            localIBinder = localzzd.asBinder();
          }
          paramParcel2.writeStrongBinder(localIBinder);
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          String str4 = getHeadline();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str4);
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          List localList = getImages();
          paramParcel2.writeNoException();
          paramParcel2.writeList(localList);
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          String str3 = getBody();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str3);
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          zzcm localzzcm = zzdA();
          paramParcel2.writeNoException();
          if (localzzcm != null) {
            localIBinder = localzzcm.asBinder();
          }
          paramParcel2.writeStrongBinder(localIBinder);
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          String str2 = getCallToAction();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str2);
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          String str1 = getAdvertiser();
          paramParcel2.writeNoException();
          paramParcel2.writeString(str1);
          bool = true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
      Bundle localBundle = getExtras();
      paramParcel2.writeNoException();
      if (localBundle != null)
      {
        paramParcel2.writeInt(1);
        localBundle.writeToParcel(paramParcel2, 1);
      }
      for (;;)
      {
        bool = true;
        break;
        paramParcel2.writeInt(0);
      }
    }
    
    private static class zza
      implements zzcs
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
      
      public String getAdvertiser()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          this.zznJ.transact(8, localParcel1, localParcel2, 0);
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
      
      public String getBody()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          this.zznJ.transact(5, localParcel1, localParcel2, 0);
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
      
      public String getCallToAction()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          this.zznJ.transact(7, localParcel1, localParcel2, 0);
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
      
      /* Error */
      public Bundle getExtras()
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
        //   15: getfield 18	com/google/android/gms/internal/zzcs$zza$zza:zznJ	Landroid/os/IBinder;
        //   18: bipush 9
        //   20: aload_1
        //   21: aload_2
        //   22: iconst_0
        //   23: invokeinterface 42 5 0
        //   28: pop
        //   29: aload_2
        //   30: invokevirtual 45	android/os/Parcel:readException	()V
        //   33: aload_2
        //   34: invokevirtual 59	android/os/Parcel:readInt	()I
        //   37: ifeq +28 -> 65
        //   40: getstatic 65	android/os/Bundle:CREATOR	Landroid/os/Parcelable$Creator;
        //   43: aload_2
        //   44: invokeinterface 71 2 0
        //   49: checkcast 61	android/os/Bundle
        //   52: astore 5
        //   54: aload_2
        //   55: invokevirtual 51	android/os/Parcel:recycle	()V
        //   58: aload_1
        //   59: invokevirtual 51	android/os/Parcel:recycle	()V
        //   62: aload 5
        //   64: areturn
        //   65: aconst_null
        //   66: astore 5
        //   68: goto -14 -> 54
        //   71: astore_3
        //   72: aload_2
        //   73: invokevirtual 51	android/os/Parcel:recycle	()V
        //   76: aload_1
        //   77: invokevirtual 51	android/os/Parcel:recycle	()V
        //   80: aload_3
        //   81: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	zza
        //   3	74	1	localParcel1	Parcel
        //   7	66	2	localParcel2	Parcel
        //   71	10	3	localObject	Object
        //   52	15	5	localBundle	Bundle
        // Exception table:
        //   from	to	target	type
        //   8	54	71	finally
      }
      
      public String getHeadline()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          this.zznJ.transact(3, localParcel1, localParcel2, 0);
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
      
      public List getImages()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          this.zznJ.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          ArrayList localArrayList = localParcel2.readArrayList(getClass().getClassLoader());
          return localArrayList;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public zzcm zzdA()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          this.zznJ.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          zzcm localzzcm = zzcm.zza.zzt(localParcel2.readStrongBinder());
          return localzzcm;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public zzd zzdx()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.formats.client.INativeContentAd");
          this.zznJ.transact(2, localParcel1, localParcel2, 0);
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzcs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */