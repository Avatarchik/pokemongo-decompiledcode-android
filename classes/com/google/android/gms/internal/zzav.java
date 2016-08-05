package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzav
  extends IInterface
{
  public abstract String getId()
    throws RemoteException;
  
  public abstract void zzb(String paramString, boolean paramBoolean)
    throws RemoteException;
  
  public abstract boolean zzc(boolean paramBoolean)
    throws RemoteException;
  
  public abstract String zzn(String paramString)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzav
  {
    public static zzav zzb(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzav)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        if ((localIInterface != null) && ((localIInterface instanceof zzav))) {
          localObject = (zzav)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
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
        paramParcel2.writeString("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        String str3 = getId();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str3);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        if (paramParcel1.readInt() != 0) {}
        for (boolean bool1 = j;; bool1 = false)
        {
          boolean bool2 = zzc(bool1);
          paramParcel2.writeNoException();
          if (bool2) {
            i = j;
          }
          paramParcel2.writeInt(i);
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        String str2 = zzn(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(str2);
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
        String str1 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {
          i = j;
        }
        zzb(str1, i);
        paramParcel2.writeNoException();
      }
    }
    
    private static class zza
      implements zzav
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
      
      public String getId()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
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
      
      public void zzb(String paramString, boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
          localParcel1.writeString(paramString);
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
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
      
      /* Error */
      public boolean zzc(boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_2
        //   2: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore_3
        //   6: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   9: astore 4
        //   11: aload_3
        //   12: ldc 32
        //   14: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: iload_1
        //   18: ifeq +55 -> 73
        //   21: iload_2
        //   22: istore 6
        //   24: aload_3
        //   25: iload 6
        //   27: invokevirtual 60	android/os/Parcel:writeInt	(I)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/internal/zzav$zza$zza:zznJ	Landroid/os/IBinder;
        //   34: iconst_2
        //   35: aload_3
        //   36: aload 4
        //   38: iconst_0
        //   39: invokeinterface 42 5 0
        //   44: pop
        //   45: aload 4
        //   47: invokevirtual 45	android/os/Parcel:readException	()V
        //   50: aload 4
        //   52: invokevirtual 66	android/os/Parcel:readInt	()I
        //   55: istore 8
        //   57: iload 8
        //   59: ifeq +20 -> 79
        //   62: aload 4
        //   64: invokevirtual 51	android/os/Parcel:recycle	()V
        //   67: aload_3
        //   68: invokevirtual 51	android/os/Parcel:recycle	()V
        //   71: iload_2
        //   72: ireturn
        //   73: iconst_0
        //   74: istore 6
        //   76: goto -52 -> 24
        //   79: iconst_0
        //   80: istore_2
        //   81: goto -19 -> 62
        //   84: astore 5
        //   86: aload 4
        //   88: invokevirtual 51	android/os/Parcel:recycle	()V
        //   91: aload_3
        //   92: invokevirtual 51	android/os/Parcel:recycle	()V
        //   95: aload 5
        //   97: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	98	0	this	zza
        //   0	98	1	paramBoolean	boolean
        //   1	80	2	i	int
        //   5	87	3	localParcel1	Parcel
        //   9	78	4	localParcel2	Parcel
        //   84	12	5	localObject	Object
        //   22	4	6	j	int
        //   74	1	6	k	int
        //   55	3	8	m	int
        // Exception table:
        //   from	to	target	type
        //   11	57	84	finally
      }
      
      public String zzn(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
          localParcel1.writeString(paramString);
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
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzav.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */