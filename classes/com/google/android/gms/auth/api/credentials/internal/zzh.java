package com.google.android.gms.auth.api.credentials.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

public abstract interface zzh
  extends IInterface
{
  public abstract void zza(zzg paramzzg)
    throws RemoteException;
  
  public abstract void zza(zzg paramzzg, CredentialRequest paramCredentialRequest)
    throws RemoteException;
  
  public abstract void zza(zzg paramzzg, DeleteRequest paramDeleteRequest)
    throws RemoteException;
  
  public abstract void zza(zzg paramzzg, SaveRequest paramSaveRequest)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzh
  {
    public static zzh zzat(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzh)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        if ((localIInterface != null) && ((localIInterface instanceof zzh))) {
          localObject = (zzh)localIInterface;
        } else {
          localObject = new zza(paramIBinder);
        }
      }
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
        paramParcel2.writeString("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        zzg localzzg3 = zzg.zza.zzas(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {
          localObject = (CredentialRequest)CredentialRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza(localzzg3, (CredentialRequest)localObject);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        zzg localzzg2 = zzg.zza.zzas(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {
          localObject = (SaveRequest)SaveRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza(localzzg2, (SaveRequest)localObject);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        zzg localzzg1 = zzg.zza.zzas(paramParcel1.readStrongBinder());
        if (paramParcel1.readInt() != 0) {
          localObject = (DeleteRequest)DeleteRequest.CREATOR.createFromParcel(paramParcel1);
        }
        zza(localzzg1, (DeleteRequest)localObject);
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        zza(zzg.zza.zzas(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
      }
    }
    
    private static class zza
      implements zzh
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
      public void zza(zzg paramzzg)
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
        //   19: invokeinterface 39 1 0
        //   24: astore 5
        //   26: aload_2
        //   27: aload 5
        //   29: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload_0
        //   33: getfield 18	com/google/android/gms/auth/api/credentials/internal/zzh$zza$zza:zznJ	Landroid/os/IBinder;
        //   36: iconst_4
        //   37: aload_2
        //   38: aload_3
        //   39: iconst_0
        //   40: invokeinterface 48 5 0
        //   45: pop
        //   46: aload_3
        //   47: invokevirtual 51	android/os/Parcel:readException	()V
        //   50: aload_3
        //   51: invokevirtual 54	android/os/Parcel:recycle	()V
        //   54: aload_2
        //   55: invokevirtual 54	android/os/Parcel:recycle	()V
        //   58: return
        //   59: aconst_null
        //   60: astore 5
        //   62: goto -36 -> 26
        //   65: astore 4
        //   67: aload_3
        //   68: invokevirtual 54	android/os/Parcel:recycle	()V
        //   71: aload_2
        //   72: invokevirtual 54	android/os/Parcel:recycle	()V
        //   75: aload 4
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	zza
        //   0	78	1	paramzzg	zzg
        //   3	69	2	localParcel1	Parcel
        //   7	61	3	localParcel2	Parcel
        //   65	11	4	localObject	Object
        //   24	37	5	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   8	50	65	finally
      }
      
      /* Error */
      public void zza(zzg paramzzg, CredentialRequest paramCredentialRequest)
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
        //   15: aload_1
        //   16: ifnull +62 -> 78
        //   19: aload_1
        //   20: invokeinterface 39 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +50 -> 84
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 59	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 65	com/google/android/gms/auth/api/credentials/CredentialRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 18	com/google/android/gms/auth/api/credentials/internal/zzh$zza$zza:zznJ	Landroid/os/IBinder;
        //   52: iconst_1
        //   53: aload_3
        //   54: aload 4
        //   56: iconst_0
        //   57: invokeinterface 48 5 0
        //   62: pop
        //   63: aload 4
        //   65: invokevirtual 51	android/os/Parcel:readException	()V
        //   68: aload 4
        //   70: invokevirtual 54	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 54	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aconst_null
        //   79: astore 6
        //   81: goto -54 -> 27
        //   84: aload_3
        //   85: iconst_0
        //   86: invokevirtual 59	android/os/Parcel:writeInt	(I)V
        //   89: goto -41 -> 48
        //   92: astore 5
        //   94: aload 4
        //   96: invokevirtual 54	android/os/Parcel:recycle	()V
        //   99: aload_3
        //   100: invokevirtual 54	android/os/Parcel:recycle	()V
        //   103: aload 5
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	zza
        //   0	106	1	paramzzg	zzg
        //   0	106	2	paramCredentialRequest	CredentialRequest
        //   3	97	3	localParcel1	Parcel
        //   7	88	4	localParcel2	Parcel
        //   92	12	5	localObject	Object
        //   25	55	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	68	92	finally
        //   84	89	92	finally
      }
      
      /* Error */
      public void zza(zzg paramzzg, DeleteRequest paramDeleteRequest)
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
        //   15: aload_1
        //   16: ifnull +62 -> 78
        //   19: aload_1
        //   20: invokeinterface 39 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +50 -> 84
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 59	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 69	com/google/android/gms/auth/api/credentials/internal/DeleteRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 18	com/google/android/gms/auth/api/credentials/internal/zzh$zza$zza:zznJ	Landroid/os/IBinder;
        //   52: iconst_3
        //   53: aload_3
        //   54: aload 4
        //   56: iconst_0
        //   57: invokeinterface 48 5 0
        //   62: pop
        //   63: aload 4
        //   65: invokevirtual 51	android/os/Parcel:readException	()V
        //   68: aload 4
        //   70: invokevirtual 54	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 54	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aconst_null
        //   79: astore 6
        //   81: goto -54 -> 27
        //   84: aload_3
        //   85: iconst_0
        //   86: invokevirtual 59	android/os/Parcel:writeInt	(I)V
        //   89: goto -41 -> 48
        //   92: astore 5
        //   94: aload 4
        //   96: invokevirtual 54	android/os/Parcel:recycle	()V
        //   99: aload_3
        //   100: invokevirtual 54	android/os/Parcel:recycle	()V
        //   103: aload 5
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	zza
        //   0	106	1	paramzzg	zzg
        //   0	106	2	paramDeleteRequest	DeleteRequest
        //   3	97	3	localParcel1	Parcel
        //   7	88	4	localParcel2	Parcel
        //   92	12	5	localObject	Object
        //   25	55	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	68	92	finally
        //   84	89	92	finally
      }
      
      /* Error */
      public void zza(zzg paramzzg, SaveRequest paramSaveRequest)
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
        //   15: aload_1
        //   16: ifnull +62 -> 78
        //   19: aload_1
        //   20: invokeinterface 39 1 0
        //   25: astore 6
        //   27: aload_3
        //   28: aload 6
        //   30: invokevirtual 42	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   33: aload_2
        //   34: ifnull +50 -> 84
        //   37: aload_3
        //   38: iconst_1
        //   39: invokevirtual 59	android/os/Parcel:writeInt	(I)V
        //   42: aload_2
        //   43: aload_3
        //   44: iconst_0
        //   45: invokevirtual 73	com/google/android/gms/auth/api/credentials/internal/SaveRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   48: aload_0
        //   49: getfield 18	com/google/android/gms/auth/api/credentials/internal/zzh$zza$zza:zznJ	Landroid/os/IBinder;
        //   52: iconst_2
        //   53: aload_3
        //   54: aload 4
        //   56: iconst_0
        //   57: invokeinterface 48 5 0
        //   62: pop
        //   63: aload 4
        //   65: invokevirtual 51	android/os/Parcel:readException	()V
        //   68: aload 4
        //   70: invokevirtual 54	android/os/Parcel:recycle	()V
        //   73: aload_3
        //   74: invokevirtual 54	android/os/Parcel:recycle	()V
        //   77: return
        //   78: aconst_null
        //   79: astore 6
        //   81: goto -54 -> 27
        //   84: aload_3
        //   85: iconst_0
        //   86: invokevirtual 59	android/os/Parcel:writeInt	(I)V
        //   89: goto -41 -> 48
        //   92: astore 5
        //   94: aload 4
        //   96: invokevirtual 54	android/os/Parcel:recycle	()V
        //   99: aload_3
        //   100: invokevirtual 54	android/os/Parcel:recycle	()V
        //   103: aload 5
        //   105: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	106	0	this	zza
        //   0	106	1	paramzzg	zzg
        //   0	106	2	paramSaveRequest	SaveRequest
        //   3	97	3	localParcel1	Parcel
        //   7	88	4	localParcel2	Parcel
        //   92	12	5	localObject	Object
        //   25	55	6	localIBinder	IBinder
        // Exception table:
        //   from	to	target	type
        //   9	68	92	finally
        //   84	89	92	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/credentials/internal/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */