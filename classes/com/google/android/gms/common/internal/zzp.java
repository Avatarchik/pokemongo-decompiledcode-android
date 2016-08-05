package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzp
  extends IInterface
{
  public abstract Account getAccount()
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzp
  {
    public static zzp zzaH(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzp)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
        if ((localIInterface != null) && ((localIInterface instanceof zzp))) {
          localObject = (zzp)localIInterface;
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
      int i = 1;
      switch (paramInt1)
      {
      default: 
        i = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return i;
        paramParcel2.writeString("com.google.android.gms.common.internal.IAccountAccessor");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.common.internal.IAccountAccessor");
        Account localAccount = getAccount();
        paramParcel2.writeNoException();
        if (localAccount != null)
        {
          paramParcel2.writeInt(i);
          localAccount.writeToParcel(paramParcel2, i);
        }
        else
        {
          paramParcel2.writeInt(0);
        }
      }
    }
    
    private static class zza
      implements zzp
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
      public Account getAccount()
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
        //   15: getfield 18	com/google/android/gms/common/internal/zzp$zza$zza:zznJ	Landroid/os/IBinder;
        //   18: iconst_2
        //   19: aload_1
        //   20: aload_2
        //   21: iconst_0
        //   22: invokeinterface 42 5 0
        //   27: pop
        //   28: aload_2
        //   29: invokevirtual 45	android/os/Parcel:readException	()V
        //   32: aload_2
        //   33: invokevirtual 49	android/os/Parcel:readInt	()I
        //   36: ifeq +28 -> 64
        //   39: getstatic 55	android/accounts/Account:CREATOR	Landroid/os/Parcelable$Creator;
        //   42: aload_2
        //   43: invokeinterface 61 2 0
        //   48: checkcast 51	android/accounts/Account
        //   51: astore 5
        //   53: aload_2
        //   54: invokevirtual 64	android/os/Parcel:recycle	()V
        //   57: aload_1
        //   58: invokevirtual 64	android/os/Parcel:recycle	()V
        //   61: aload 5
        //   63: areturn
        //   64: aconst_null
        //   65: astore 5
        //   67: goto -14 -> 53
        //   70: astore_3
        //   71: aload_2
        //   72: invokevirtual 64	android/os/Parcel:recycle	()V
        //   75: aload_1
        //   76: invokevirtual 64	android/os/Parcel:recycle	()V
        //   79: aload_3
        //   80: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	81	0	this	zza
        //   3	73	1	localParcel1	Parcel
        //   7	65	2	localParcel2	Parcel
        //   70	10	3	localObject	Object
        //   51	15	5	localAccount	Account
        // Exception table:
        //   from	to	target	type
        //   8	53	70	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */