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

public abstract interface zzer
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
  
  public abstract boolean getOverrideClickHandling()
    throws RemoteException;
  
  public abstract boolean getOverrideImpressionRecording()
    throws RemoteException;
  
  public abstract void recordImpression()
    throws RemoteException;
  
  public abstract void zzc(zzd paramzzd)
    throws RemoteException;
  
  public abstract void zzd(zzd paramzzd)
    throws RemoteException;
  
  public abstract zzcm zzdA()
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzer
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
    }
    
    public static zzer zzJ(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzer)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        if ((localIInterface != null) && ((localIInterface instanceof zzer))) {
          localObject = (zzer)localIInterface;
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
      int i = 0;
      int j = 1;
      switch (paramInt1)
      {
      default: 
        j = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return j;
        paramParcel2.writeString("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        String str4 = getHeadline();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str4);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        List localList = getImages();
        paramParcel2.writeNoException();
        paramParcel2.writeList(localList);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        String str3 = getBody();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str3);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        zzcm localzzcm = zzdA();
        paramParcel2.writeNoException();
        if (localzzcm != null) {}
        for (IBinder localIBinder = localzzcm.asBinder();; localIBinder = null)
        {
          paramParcel2.writeStrongBinder(localIBinder);
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        String str2 = getCallToAction();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str2);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        String str1 = getAdvertiser();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str1);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        recordImpression();
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        zzc(zzd.zza.zzbk(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        zzd(zzd.zza.zzbk(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        boolean bool2 = getOverrideImpressionRecording();
        paramParcel2.writeNoException();
        if (bool2) {
          i = j;
        }
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        boolean bool1 = getOverrideClickHandling();
        paramParcel2.writeNoException();
        if (bool1) {
          i = j;
        }
        paramParcel2.writeInt(i);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
        Bundle localBundle = getExtras();
        paramParcel2.writeNoException();
        if (localBundle != null)
        {
          paramParcel2.writeInt(j);
          localBundle.writeToParcel(paramParcel2, j);
        }
        else
        {
          paramParcel2.writeInt(0);
        }
      }
    }
    
    private static class zza
      implements zzer
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
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
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
      
      public String getBody()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
          this.zznJ.transact(4, localParcel1, localParcel2, 0);
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
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
          this.zznJ.transact(6, localParcel1, localParcel2, 0);
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
        //   15: getfield 18	com/google/android/gms/internal/zzer$zza$zza:zznJ	Landroid/os/IBinder;
        //   18: bipush 13
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
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
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
      
      public List getImages()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
          this.zznJ.transact(3, localParcel1, localParcel2, 0);
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
      
      public boolean getOverrideClickHandling()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
          this.zznJ.transact(12, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public boolean getOverrideImpressionRecording()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
          this.zznJ.transact(11, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0) {
            bool = true;
          }
          return bool;
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
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
          this.zznJ.transact(8, localParcel1, localParcel2, 0);
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
      public void zzc(zzd paramzzd)
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
        //   15: ifnull +45 -> 60
        //   18: aload_1
        //   19: invokeinterface 98 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 101	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/internal/zzer$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: bipush 9
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 42 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 45	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 51	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 51	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 51	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 51	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	zza
        //   0	79	1	paramzzd	zzd
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        //   66	11	4	localObject	Object
        //   24	38	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	51	66	finally
      }
      
      /* Error */
      public void zzd(zzd paramzzd)
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
        //   15: ifnull +45 -> 60
        //   18: aload_1
        //   19: invokeinterface 98 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 101	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/internal/zzer$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: bipush 10
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 42 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 45	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 51	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 51	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 51	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 51	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	zza
        //   0	79	1	paramzzd	zzd
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        //   66	11	4	localObject	Object
        //   24	38	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	51	66	finally
      }
      
      public zzcm zzdA()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
          this.zznJ.transact(5, localParcel1, localParcel2, 0);
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
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */