package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzp.zza;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.internal.zzt.zza;

public abstract interface zzf
  extends IInterface
{
  public abstract void zza(int paramInt, Account paramAccount, zze paramzze)
    throws RemoteException;
  
  public abstract void zza(AuthAccountRequest paramAuthAccountRequest, zze paramzze)
    throws RemoteException;
  
  public abstract void zza(ResolveAccountRequest paramResolveAccountRequest, zzt paramzzt)
    throws RemoteException;
  
  public abstract void zza(zzp paramzzp, int paramInt, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void zza(CheckServerAuthResult paramCheckServerAuthResult)
    throws RemoteException;
  
  public abstract void zza(RecordConsentRequest paramRecordConsentRequest, zze paramzze)
    throws RemoteException;
  
  public abstract void zza(zze paramzze)
    throws RemoteException;
  
  public abstract void zzaq(boolean paramBoolean)
    throws RemoteException;
  
  public abstract void zzjq(int paramInt)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzf
  {
    public static zzf zzdN(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzf)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        if ((localIInterface != null) && ((localIInterface instanceof zzf))) {
          localObject = (zzf)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool1 = false;
      Object localObject = null;
      boolean bool2 = true;
      switch (paramInt1)
      {
      default: 
        bool2 = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool2;
        paramParcel2.writeString("com.google.android.gms.signin.internal.ISignInService");
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
        if (paramParcel1.readInt() != 0) {
          localObject = (AuthAccountRequest)AuthAccountRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((AuthAccountRequest)localObject, zze.zza.zzdM(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
        if (paramParcel1.readInt() != 0) {
          localObject = (CheckServerAuthResult)CheckServerAuthResult.CREATOR.createFromParcel(paramParcel1);
        }
        zza((CheckServerAuthResult)localObject);
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
        if (paramParcel1.readInt() != 0) {}
        for (boolean bool3 = bool2;; bool3 = false)
        {
          zzaq(bool3);
          paramParcel2.writeNoException();
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
        if (paramParcel1.readInt() != 0) {
          localObject = (ResolveAccountRequest)ResolveAccountRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((ResolveAccountRequest)localObject, zzt.zza.zzaL(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
        zzjq(paramParcel1.readInt());
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
        int j = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {
          localObject = (Account)Account.CREATOR.createFromParcel(paramParcel1);
        }
        zza(j, (Account)localObject, zze.zza.zzdM(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
        zzp localzzp = zzp.zza.zzaH(paramParcel1.readStrongBinder());
        int i = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {
          bool1 = bool2;
        }
        zza(localzzp, i, bool1);
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
        if (paramParcel1.readInt() != 0) {
          localObject = (RecordConsentRequest)RecordConsentRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza((RecordConsentRequest)localObject, zze.zza.zzdM(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.signin.internal.ISignInService");
        zza(zze.zza.zzdM(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
      }
    }
    
    private static class zza
      implements zzf
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
      
      public void zza(int paramInt, Account paramAccount, zze paramzze)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
            localParcel1.writeInt(paramInt);
            if (paramAccount != null)
            {
              localParcel1.writeInt(1);
              paramAccount.writeToParcel(localParcel1, 0);
              if (paramzze != null)
              {
                localIBinder = paramzze.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(8, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(AuthAccountRequest paramAuthAccountRequest, zze paramzze)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
            if (paramAuthAccountRequest != null)
            {
              localParcel1.writeInt(1);
              paramAuthAccountRequest.writeToParcel(localParcel1, 0);
              if (paramzze != null)
              {
                localIBinder = paramzze.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(ResolveAccountRequest paramResolveAccountRequest, zzt paramzzt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
            if (paramResolveAccountRequest != null)
            {
              localParcel1.writeInt(1);
              paramResolveAccountRequest.writeToParcel(localParcel1, 0);
              if (paramzzt != null)
              {
                localIBinder = paramzzt.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(5, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void zza(zzp paramzzp, int paramInt, boolean paramBoolean)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore 4
        //   3: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 31
        //   17: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: aload_1
        //   21: ifnull +71 -> 92
        //   24: aload_1
        //   25: invokeinterface 79 1 0
        //   30: astore 8
        //   32: aload 5
        //   34: aload 8
        //   36: invokevirtual 52	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   39: aload 5
        //   41: iload_2
        //   42: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   45: iload_3
        //   46: ifeq +6 -> 52
        //   49: iconst_1
        //   50: istore 4
        //   52: aload 5
        //   54: iload 4
        //   56: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   59: aload_0
        //   60: getfield 18	com/google/android/gms/signin/internal/zzf$zza$zza:zznJ	Landroid/os/IBinder;
        //   63: bipush 9
        //   65: aload 5
        //   67: aload 6
        //   69: iconst_0
        //   70: invokeinterface 58 5 0
        //   75: pop
        //   76: aload 6
        //   78: invokevirtual 61	android/os/Parcel:readException	()V
        //   81: aload 6
        //   83: invokevirtual 64	android/os/Parcel:recycle	()V
        //   86: aload 5
        //   88: invokevirtual 64	android/os/Parcel:recycle	()V
        //   91: return
        //   92: aconst_null
        //   93: astore 8
        //   95: goto -63 -> 32
        //   98: astore 7
        //   100: aload 6
        //   102: invokevirtual 64	android/os/Parcel:recycle	()V
        //   105: aload 5
        //   107: invokevirtual 64	android/os/Parcel:recycle	()V
        //   110: aload 7
        //   112: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	113	0	this	zza
        //   0	113	1	paramzzp	zzp
        //   0	113	2	paramInt	int
        //   0	113	3	paramBoolean	boolean
        //   1	54	4	i	int
        //   6	100	5	localParcel1	Parcel
        //   11	90	6	localParcel2	Parcel
        //   98	13	7	localObject	Object
        //   30	64	8	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   13	81	98	finally
      }
      
      /* Error */
      public void zza(CheckServerAuthResult paramCheckServerAuthResult)
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
        //   26: invokevirtual 83	com/google/android/gms/signin/internal/CheckServerAuthResult:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/signin/internal/zzf$zza$zza:zznJ	Landroid/os/IBinder;
        //   33: iconst_3
        //   34: aload_2
        //   35: aload_3
        //   36: iconst_0
        //   37: invokeinterface 58 5 0
        //   42: pop
        //   43: aload_3
        //   44: invokevirtual 61	android/os/Parcel:readException	()V
        //   47: aload_3
        //   48: invokevirtual 64	android/os/Parcel:recycle	()V
        //   51: aload_2
        //   52: invokevirtual 64	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_2
        //   57: iconst_0
        //   58: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore 4
        //   66: aload_3
        //   67: invokevirtual 64	android/os/Parcel:recycle	()V
        //   70: aload_2
        //   71: invokevirtual 64	android/os/Parcel:recycle	()V
        //   74: aload 4
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	zza
        //   0	77	1	paramCheckServerAuthResult	CheckServerAuthResult
        //   3	68	2	localParcel1	Parcel
        //   7	60	3	localParcel2	Parcel
        //   64	11	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   8	47	64	finally
        //   56	61	64	finally
      }
      
      public void zza(RecordConsentRequest paramRecordConsentRequest, zze paramzze)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
            if (paramRecordConsentRequest != null)
            {
              localParcel1.writeInt(1);
              paramRecordConsentRequest.writeToParcel(localParcel1, 0);
              if (paramzze != null)
              {
                localIBinder = paramzze.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(10, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void zza(zze paramzze)
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
        //   15: ifnull +45 -> 60
        //   18: aload_1
        //   19: invokeinterface 49 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 52	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/signin/internal/zzf$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: bipush 11
        //   38: aload_2
        //   39: aload_3
        //   40: iconst_0
        //   41: invokeinterface 58 5 0
        //   46: pop
        //   47: aload_3
        //   48: invokevirtual 61	android/os/Parcel:readException	()V
        //   51: aload_3
        //   52: invokevirtual 64	android/os/Parcel:recycle	()V
        //   55: aload_2
        //   56: invokevirtual 64	android/os/Parcel:recycle	()V
        //   59: return
        //   60: aconst_null
        //   61: astore 5
        //   63: goto -37 -> 26
        //   66: astore 4
        //   68: aload_3
        //   69: invokevirtual 64	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 64	android/os/Parcel:recycle	()V
        //   76: aload 4
        //   78: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	79	0	this	zza
        //   0	79	1	paramzze	zze
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        //   66	11	4	localObject	Object
        //   24	38	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	51	66	finally
      }
      
      public void zzaq(boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
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
      
      public void zzjq(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
          localParcel1.writeInt(paramInt);
          this.zznJ.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/signin/internal/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */