package com.google.android.gms.signin.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

public abstract interface zze
  extends IInterface
{
  public abstract void zza(ConnectionResult paramConnectionResult, AuthAccountResult paramAuthAccountResult)
    throws RemoteException;
  
  public abstract void zza(Status paramStatus, GoogleSignInAccount paramGoogleSignInAccount)
    throws RemoteException;
  
  public abstract void zzbd(Status paramStatus)
    throws RemoteException;
  
  public abstract void zzbe(Status paramStatus)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zze
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.signin.internal.ISignInCallbacks");
    }
    
    public static zze zzdM(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zze)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
        if ((localIInterface != null) && ((localIInterface instanceof zze))) {
          localObject = (zze)localIInterface;
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
          paramParcel2.writeString("com.google.android.gms.signin.internal.ISignInCallbacks");
        }
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
        ConnectionResult localConnectionResult;
        if (paramParcel1.readInt() != 0)
        {
          localConnectionResult = (ConnectionResult)ConnectionResult.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label150;
          }
        }
        for (AuthAccountResult localAuthAccountResult = (AuthAccountResult)AuthAccountResult.CREATOR.createFromParcel(paramParcel1);; localAuthAccountResult = null)
        {
          zza(localConnectionResult, localAuthAccountResult);
          paramParcel2.writeNoException();
          bool = true;
          break;
          localConnectionResult = null;
          break label105;
        }
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
        if (paramParcel1.readInt() != 0) {}
        for (Status localStatus3 = (Status)Status.CREATOR.createFromParcel(paramParcel1);; localStatus3 = null)
        {
          zzbd(localStatus3);
          paramParcel2.writeNoException();
          bool = true;
          break;
        }
      case 6: 
        label105:
        label150:
        paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
        if (paramParcel1.readInt() != 0) {}
        for (Status localStatus2 = (Status)Status.CREATOR.createFromParcel(paramParcel1);; localStatus2 = null)
        {
          zzbe(localStatus2);
          paramParcel2.writeNoException();
          bool = true;
          break;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
      Status localStatus1;
      if (paramParcel1.readInt() != 0)
      {
        localStatus1 = (Status)Status.CREATOR.createFromParcel(paramParcel1);
        label281:
        if (paramParcel1.readInt() == 0) {
          break label326;
        }
      }
      label326:
      for (GoogleSignInAccount localGoogleSignInAccount = (GoogleSignInAccount)GoogleSignInAccount.CREATOR.createFromParcel(paramParcel1);; localGoogleSignInAccount = null)
      {
        zza(localStatus1, localGoogleSignInAccount);
        paramParcel2.writeNoException();
        bool = true;
        break;
        localStatus1 = null;
        break label281;
      }
    }
    
    private static class zza
      implements zze
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
      
      public void zza(ConnectionResult paramConnectionResult, AuthAccountResult paramAuthAccountResult)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
            if (paramConnectionResult != null)
            {
              localParcel1.writeInt(1);
              paramConnectionResult.writeToParcel(localParcel1, 0);
              if (paramAuthAccountResult != null)
              {
                localParcel1.writeInt(1);
                paramAuthAccountResult.writeToParcel(localParcel1, 0);
                this.zznJ.transact(3, localParcel1, localParcel2, 0);
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
      
      public void zza(Status paramStatus, GoogleSignInAccount paramGoogleSignInAccount)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
            if (paramStatus != null)
            {
              localParcel1.writeInt(1);
              paramStatus.writeToParcel(localParcel1, 0);
              if (paramGoogleSignInAccount != null)
              {
                localParcel1.writeInt(1);
                paramGoogleSignInAccount.writeToParcel(localParcel1, 0);
                this.zznJ.transact(7, localParcel1, localParcel2, 0);
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
      public void zzbd(Status paramStatus)
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
        //   26: invokevirtual 64	com/google/android/gms/common/api/Status:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/signin/internal/zze$zza$zza:zznJ	Landroid/os/IBinder;
        //   33: iconst_4
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
      
      /* Error */
      public void zzbe(Status paramStatus)
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
        //   26: invokevirtual 64	com/google/android/gms/common/api/Status:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/signin/internal/zze$zza$zza:zznJ	Landroid/os/IBinder;
        //   33: bipush 6
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 54 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 57	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 60	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 60	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 60	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 60	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramStatus	Status
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	48	65	finally
        //   57	62	65	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/signin/internal/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */