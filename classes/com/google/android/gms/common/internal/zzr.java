package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface zzr
  extends IInterface
{
  public abstract void zza(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    throws RemoteException;
  
  public abstract void zzb(int paramInt, Bundle paramBundle)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzr
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.common.internal.IGmsCallbacks");
    }
    
    public static zzr zzaJ(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzr)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
        if ((localIInterface != null) && ((localIInterface instanceof zzr))) {
          localObject = (zzr)localIInterface;
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
      Bundle localBundle = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.common.internal.IGmsCallbacks");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsCallbacks");
        int j = paramParcel1.readInt();
        IBinder localIBinder = paramParcel1.readStrongBinder();
        if (paramParcel1.readInt() != 0) {
          localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        }
        zza(j, localIBinder, localBundle);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IGmsCallbacks");
        int i = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {
          localBundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
        }
        zzb(i, localBundle);
        paramParcel2.writeNoException();
        bool = true;
      }
    }
    
    private static class zza
      implements zzr
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
      public void zza(int paramInt, IBinder paramIBinder, Bundle paramBundle)
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
        //   17: aload 4
        //   19: iload_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload 4
        //   25: aload_2
        //   26: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   29: aload_3
        //   30: ifnull +48 -> 78
        //   33: aload 4
        //   35: iconst_1
        //   36: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   39: aload_3
        //   40: aload 4
        //   42: iconst_0
        //   43: invokevirtual 48	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   46: aload_0
        //   47: getfield 18	com/google/android/gms/common/internal/zzr$zza$zza:zznJ	Landroid/os/IBinder;
        //   50: iconst_1
        //   51: aload 4
        //   53: aload 5
        //   55: iconst_0
        //   56: invokeinterface 54 5 0
        //   61: pop
        //   62: aload 5
        //   64: invokevirtual 57	android/os/Parcel:readException	()V
        //   67: aload 5
        //   69: invokevirtual 60	android/os/Parcel:recycle	()V
        //   72: aload 4
        //   74: invokevirtual 60	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aload 4
        //   80: iconst_0
        //   81: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   84: goto -38 -> 46
        //   87: astore 6
        //   89: aload 5
        //   91: invokevirtual 60	android/os/Parcel:recycle	()V
        //   94: aload 4
        //   96: invokevirtual 60	android/os/Parcel:recycle	()V
        //   99: aload 6
        //   101: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	102	0	this	zza
        //   0	102	1	paramInt	int
        //   0	102	2	paramIBinder	IBinder
        //   0	102	3	paramBundle	Bundle
        //   3	92	4	localParcel1	Parcel
        //   8	82	5	localParcel2	Parcel
        //   87	13	6	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   10	67	87	finally
        //   78	84	87	finally
      }
      
      /* Error */
      public void zzb(int paramInt, Bundle paramBundle)
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
        //   15: aload_3
        //   16: iload_1
        //   17: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   20: aload_2
        //   21: ifnull +44 -> 65
        //   24: aload_3
        //   25: iconst_1
        //   26: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   29: aload_2
        //   30: aload_3
        //   31: iconst_0
        //   32: invokevirtual 48	android/os/Bundle:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload_0
        //   36: getfield 18	com/google/android/gms/common/internal/zzr$zza$zza:zznJ	Landroid/os/IBinder;
        //   39: iconst_2
        //   40: aload_3
        //   41: aload 4
        //   43: iconst_0
        //   44: invokeinterface 54 5 0
        //   49: pop
        //   50: aload 4
        //   52: invokevirtual 57	android/os/Parcel:readException	()V
        //   55: aload 4
        //   57: invokevirtual 60	android/os/Parcel:recycle	()V
        //   60: aload_3
        //   61: invokevirtual 60	android/os/Parcel:recycle	()V
        //   64: return
        //   65: aload_3
        //   66: iconst_0
        //   67: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   70: goto -35 -> 35
        //   73: astore 5
        //   75: aload 4
        //   77: invokevirtual 60	android/os/Parcel:recycle	()V
        //   80: aload_3
        //   81: invokevirtual 60	android/os/Parcel:recycle	()V
        //   84: aload 5
        //   86: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	87	0	this	zza
        //   0	87	1	paramInt	int
        //   0	87	2	paramBundle	Bundle
        //   3	78	3	localParcel1	Parcel
        //   7	69	4	localParcel2	Parcel
        //   73	12	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   9	55	73	finally
        //   65	70	73	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/zzr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */