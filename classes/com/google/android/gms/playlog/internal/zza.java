package com.google.android.gms.playlog.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract interface zza
  extends IInterface
{
  public abstract void zza(String paramString, PlayLoggerContext paramPlayLoggerContext, LogEvent paramLogEvent)
    throws RemoteException;
  
  public abstract void zza(String paramString, PlayLoggerContext paramPlayLoggerContext, List<LogEvent> paramList)
    throws RemoteException;
  
  public abstract void zza(String paramString, PlayLoggerContext paramPlayLoggerContext, byte[] paramArrayOfByte)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zza
  {
    public static zza zzdz(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zza)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.playlog.internal.IPlayLogService");
        if ((localIInterface != null) && ((localIInterface instanceof zza))) {
          localObject = (zza)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.playlog.internal.IPlayLogService");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
        String str3 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (PlayLoggerContext localPlayLoggerContext = PlayLoggerContext.CREATOR.zzgj(paramParcel1);; localPlayLoggerContext = null)
        {
          if (paramParcel1.readInt() != 0) {
            localObject = LogEvent.CREATOR.zzgi(paramParcel1);
          }
          zza(str3, localPlayLoggerContext, (LogEvent)localObject);
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
        String str2 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {
          localObject = PlayLoggerContext.CREATOR.zzgj(paramParcel1);
        }
        zza(str2, (PlayLoggerContext)localObject, paramParcel1.createTypedArrayList(LogEvent.CREATOR));
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.playlog.internal.IPlayLogService");
        String str1 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {
          localObject = PlayLoggerContext.CREATOR.zzgj(paramParcel1);
        }
        zza(str1, (PlayLoggerContext)localObject, paramParcel1.createByteArray());
        bool = true;
      }
    }
    
    private static class zza
      implements zza
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
      
      public void zza(String paramString, PlayLoggerContext paramPlayLoggerContext, LogEvent paramLogEvent)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel.writeInterfaceToken("com.google.android.gms.playlog.internal.IPlayLogService");
            localParcel.writeString(paramString);
            if (paramPlayLoggerContext != null)
            {
              localParcel.writeInt(1);
              paramPlayLoggerContext.writeToParcel(localParcel, 0);
              if (paramLogEvent != null)
              {
                localParcel.writeInt(1);
                paramLogEvent.writeToParcel(localParcel, 0);
                this.zznJ.transact(2, localParcel, null, 1);
              }
            }
            else
            {
              localParcel.writeInt(0);
              continue;
            }
            localParcel.writeInt(0);
          }
          finally
          {
            localParcel.recycle();
          }
        }
      }
      
      /* Error */
      public void zza(String paramString, PlayLoggerContext paramPlayLoggerContext, List<LogEvent> paramList)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 31
        //   9: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload 4
        //   14: aload_1
        //   15: invokevirtual 38	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   18: aload_2
        //   19: ifnull +43 -> 62
        //   22: aload 4
        //   24: iconst_1
        //   25: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   28: aload_2
        //   29: aload 4
        //   31: iconst_0
        //   32: invokevirtual 48	com/google/android/gms/playlog/internal/PlayLoggerContext:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload 4
        //   37: aload_3
        //   38: invokevirtual 65	android/os/Parcel:writeTypedList	(Ljava/util/List;)V
        //   41: aload_0
        //   42: getfield 18	com/google/android/gms/playlog/internal/zza$zza$zza:zznJ	Landroid/os/IBinder;
        //   45: iconst_3
        //   46: aload 4
        //   48: aconst_null
        //   49: iconst_1
        //   50: invokeinterface 57 5 0
        //   55: pop
        //   56: aload 4
        //   58: invokevirtual 60	android/os/Parcel:recycle	()V
        //   61: return
        //   62: aload 4
        //   64: iconst_0
        //   65: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   68: goto -33 -> 35
        //   71: astore 5
        //   73: aload 4
        //   75: invokevirtual 60	android/os/Parcel:recycle	()V
        //   78: aload 5
        //   80: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	81	0	this	zza
        //   0	81	1	paramString	String
        //   0	81	2	paramPlayLoggerContext	PlayLoggerContext
        //   0	81	3	paramList	List<LogEvent>
        //   3	71	4	localParcel	Parcel
        //   71	8	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   5	56	71	finally
        //   62	68	71	finally
      }
      
      /* Error */
      public void zza(String paramString, PlayLoggerContext paramPlayLoggerContext, byte[] paramArrayOfByte)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 4
        //   5: aload 4
        //   7: ldc 31
        //   9: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   12: aload 4
        //   14: aload_1
        //   15: invokevirtual 38	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   18: aload_2
        //   19: ifnull +43 -> 62
        //   22: aload 4
        //   24: iconst_1
        //   25: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   28: aload_2
        //   29: aload 4
        //   31: iconst_0
        //   32: invokevirtual 48	com/google/android/gms/playlog/internal/PlayLoggerContext:writeToParcel	(Landroid/os/Parcel;I)V
        //   35: aload 4
        //   37: aload_3
        //   38: invokevirtual 70	android/os/Parcel:writeByteArray	([B)V
        //   41: aload_0
        //   42: getfield 18	com/google/android/gms/playlog/internal/zza$zza$zza:zznJ	Landroid/os/IBinder;
        //   45: iconst_4
        //   46: aload 4
        //   48: aconst_null
        //   49: iconst_1
        //   50: invokeinterface 57 5 0
        //   55: pop
        //   56: aload 4
        //   58: invokevirtual 60	android/os/Parcel:recycle	()V
        //   61: return
        //   62: aload 4
        //   64: iconst_0
        //   65: invokevirtual 42	android/os/Parcel:writeInt	(I)V
        //   68: goto -33 -> 35
        //   71: astore 5
        //   73: aload 4
        //   75: invokevirtual 60	android/os/Parcel:recycle	()V
        //   78: aload 5
        //   80: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	81	0	this	zza
        //   0	81	1	paramString	String
        //   0	81	2	paramPlayLoggerContext	PlayLoggerContext
        //   0	81	3	paramArrayOfByte	byte[]
        //   3	71	4	localParcel	Parcel
        //   71	8	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   5	56	71	finally
        //   62	68	71	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/playlog/internal/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */