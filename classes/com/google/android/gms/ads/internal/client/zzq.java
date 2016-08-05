package com.google.android.gms.ads.internal.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.internal.zzcw;
import com.google.android.gms.internal.zzcw.zza;
import com.google.android.gms.internal.zzcx;
import com.google.android.gms.internal.zzcx.zza;
import com.google.android.gms.internal.zzcy;
import com.google.android.gms.internal.zzcy.zza;
import com.google.android.gms.internal.zzcz;
import com.google.android.gms.internal.zzcz.zza;

public abstract interface zzq
  extends IInterface
{
  public abstract void zza(NativeAdOptionsParcel paramNativeAdOptionsParcel)
    throws RemoteException;
  
  public abstract void zza(zzcw paramzzcw)
    throws RemoteException;
  
  public abstract void zza(zzcx paramzzcx)
    throws RemoteException;
  
  public abstract void zza(String paramString, zzcz paramzzcz, zzcy paramzzcy)
    throws RemoteException;
  
  public abstract void zzb(zzo paramzzo)
    throws RemoteException;
  
  public abstract zzp zzbk()
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzq
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }
    
    public static zzq zzi(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzq)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
        if ((localIInterface != null) && ((localIInterface instanceof zzq))) {
          localObject = (zzq)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
        zzp localzzp = zzbk();
        paramParcel2.writeNoException();
        if (localzzp != null) {
          localObject = localzzp.asBinder();
        }
        paramParcel2.writeStrongBinder((IBinder)localObject);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
        zzb(zzo.zza.zzg(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
        zza(zzcw.zza.zzz(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
        zza(zzcx.zza.zzA(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
        zza(paramParcel1.readString(), zzcz.zza.zzC(paramParcel1.readStrongBinder()), zzcy.zza.zzB(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
        if (paramParcel1.readInt() != 0) {
          localObject = NativeAdOptionsParcel.CREATOR.zze(paramParcel1);
        }
        zza((NativeAdOptionsParcel)localObject);
        paramParcel2.writeNoException();
        bool = true;
      }
    }
    
    private static class zza
      implements zzq
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
      public void zza(NativeAdOptionsParcel paramNativeAdOptionsParcel)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 45	com/google/android/gms/ads/internal/formats/NativeAdOptionsParcel:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/ads/internal/client/zzq$zza$zza:zznJ	Landroid/os/IBinder;
        //   33: bipush 6
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 57	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 57	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 57	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 57	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramNativeAdOptionsParcel	NativeAdOptionsParcel
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	48	65	finally
        //   57	62	65	finally
      }
      
      /* Error */
      public void zza(zzcw paramzzcw)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 62 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 65	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/ads/internal/client/zzq$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: iconst_3
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 51 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 54	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 57	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 57	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore 5
        //   62: goto -36 -> 26
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 57	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 57	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramzzcw	zzcw
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        //   24	37	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	50	65	finally
      }
      
      /* Error */
      public void zza(zzcx paramzzcx)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 69 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 65	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/ads/internal/client/zzq$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: iconst_4
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 51 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 54	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 57	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 57	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore 5
        //   62: goto -36 -> 26
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 57	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 57	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramzzcx	zzcx
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        //   24	37	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	50	65	finally
      }
      
      /* Error */
      public void zza(String paramString, zzcz paramzzcz, zzcy paramzzcy)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 4
        //   3: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 31
        //   17: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload 5
        //   22: aload_1
        //   23: invokevirtual 73	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   26: aload_2
        //   27: ifnull +69 -> 96
        //   30: aload_2
        //   31: invokeinterface 76 1 0
        //   36: astore 8
        //   38: aload 5
        //   40: aload 8
        //   42: invokevirtual 65	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   45: aload_3
        //   46: ifnull +11 -> 57
        //   49: aload_3
        //   50: invokeinterface 79 1 0
        //   55: astore 4
        //   57: aload 5
        //   59: aload 4
        //   61: invokevirtual 65	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   64: aload_0
        //   65: getfield 18	com/google/android/gms/ads/internal/client/zzq$zza$zza:zznJ	Landroid/os/IBinder;
        //   68: iconst_5
        //   69: aload 5
        //   71: aload 6
        //   73: iconst_0
        //   74: invokeinterface 51 5 0
        //   79: pop
        //   80: aload 6
        //   82: invokevirtual 54	android/os/Parcel:readException	()V
        //   85: aload 6
        //   87: invokevirtual 57	android/os/Parcel:recycle	()V
        //   90: aload 5
        //   92: invokevirtual 57	android/os/Parcel:recycle	()V
        //   95: return
        //   96: aconst_null
        //   97: astore 8
        //   99: goto -61 -> 38
        //   102: astore 7
        //   104: aload 6
        //   106: invokevirtual 57	android/os/Parcel:recycle	()V
        //   109: aload 5
        //   111: invokevirtual 57	android/os/Parcel:recycle	()V
        //   114: aload 7
        //   116: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	117	0	this	zza
        //   0	117	1	paramString	String
        //   0	117	2	paramzzcz	zzcz
        //   0	117	3	paramzzcy	zzcy
        //   1	59	4	localIBinder1	IBinder
        //   6	104	5	localParcel1	Parcel
        //   11	94	6	localParcel2	Parcel
        //   102	13	7	localObject	Object
        //   36	62	8	localIBinder2	IBinder
        // Exception table:
        //   from	to	target	type
        //   13	85	102	finally
      }
      
      /* Error */
      public void zzb(zzo paramzzo)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 31
        //   11: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +44 -> 59
        //   18: aload_1
        //   19: invokeinterface 84 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 65	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/ads/internal/client/zzq$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: iconst_2
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 51 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 54	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 57	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 57	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore 5
        //   62: goto -36 -> 26
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 57	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 57	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramzzo	zzo
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        //   24	37	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	50	65	finally
      }
      
      public zzp zzbk()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
          this.zznJ.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          zzp localzzp = zzp.zza.zzh(localParcel2.readStrongBinder());
          return localzzp;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */