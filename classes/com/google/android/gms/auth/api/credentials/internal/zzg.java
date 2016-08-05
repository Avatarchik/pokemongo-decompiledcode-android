package com.google.android.gms.auth.api.credentials.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

public abstract interface zzg
  extends IInterface
{
  public abstract void zza(Status paramStatus, Credential paramCredential)
    throws RemoteException;
  
  public abstract void zzg(Status paramStatus)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzg
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
    }
    
    public static zzg zzas(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzg)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
        if ((localIInterface != null) && ((localIInterface instanceof zzg))) {
          localObject = (zzg)localIInterface;
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
      case 1598968902: 
        for (bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool = true)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
        }
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
        Status localStatus2;
        if (paramParcel1.readInt() != 0)
        {
          localStatus2 = (Status)Status.CREATOR.createFromParcel(paramParcel1);
          label89:
          if (paramParcel1.readInt() == 0) {
            break label134;
          }
        }
        label134:
        for (Credential localCredential = (Credential)Credential.CREATOR.createFromParcel(paramParcel1);; localCredential = null)
        {
          zza(localStatus2, localCredential);
          paramParcel2.writeNoException();
          bool = true;
          break;
          localStatus2 = null;
          break label89;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
      if (paramParcel1.readInt() != 0) {}
      for (Status localStatus1 = (Status)Status.CREATOR.createFromParcel(paramParcel1);; localStatus1 = null)
      {
        zzg(localStatus1);
        paramParcel2.writeNoException();
        bool = true;
        break;
      }
    }
    
    private static class zza
      implements zzg
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
      
      public void zza(Status paramStatus, Credential paramCredential)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
            if (paramStatus != null)
            {
              localParcel1.writeInt(1);
              paramStatus.writeToParcel(localParcel1, 0);
              if (paramCredential != null)
              {
                localParcel1.writeInt(1);
                paramCredential.writeToParcel(localParcel1, 0);
                this.zznJ.transact(1, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void zzg(Status paramStatus)
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
        //   15: ifnull +41 -> 56
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 45	com/google/android/gms/common/api/Status:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/auth/api/credentials/internal/zzg$zza$zza:zznJ	Landroid/os/IBinder;
        //   33: iconst_2
        //   34: aload_2
        //   35: aload_3
        //   36: iconst_0
        //   37: invokeinterface 54 5 0
        //   42: pop
        //   43: aload_3
        //   44: invokevirtual 57	android/os/Parcel:readException	()V
        //   47: aload_3
        //   48: invokevirtual 60	android/os/Parcel:recycle	()V
        //   51: aload_2
        //   52: invokevirtual 60	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_2
        //   57: iconst_0
        //   58: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore 4
        //   66: aload_3
        //   67: invokevirtual 60	android/os/Parcel:recycle	()V
        //   70: aload_2
        //   71: invokevirtual 60	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	zza
        //   0	77	1	paramStatus	Status
        //   3	68	2	localParcel1	Parcel
        //   7	60	3	localParcel2	Parcel
        //   64	11	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	47	64	finally
        //   56	61	64	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/credentials/internal/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */