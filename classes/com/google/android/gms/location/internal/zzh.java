package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface zzh
  extends IInterface
{
  public abstract void zza(int paramInt, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(int paramInt, String[] paramArrayOfString)
    throws RemoteException;
  
  public abstract void zzb(int paramInt, String[] paramArrayOfString)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzh
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.location.internal.IGeofencerCallbacks");
    }
    
    public static zzh zzca(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzh)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
        if ((localIInterface != null) && ((localIInterface instanceof zzh))) {
          localObject = (zzh)localIInterface;
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
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
      case 1: 
      case 2: 
        for (;;)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.location.internal.IGeofencerCallbacks");
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
          zza(paramParcel1.readInt(), paramParcel1.createStringArray());
          bool = true;
          continue;
          paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
          zzb(paramParcel1.readInt(), paramParcel1.createStringArray());
          bool = true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.location.internal.IGeofencerCallbacks");
      int i = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0) {}
      for (PendingIntent localPendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent = null)
      {
        zza(i, localPendingIntent);
        bool = true;
        break;
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
      public void zza(int paramInt, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: aload_3
        //   5: ldc 31
        //   7: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   10: aload_3
        //   11: iload_1
        //   12: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   15: aload_2
        //   16: ifnull +33 -> 49
        //   19: aload_3
        //   20: iconst_1
        //   21: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   24: aload_2
        //   25: aload_3
        //   26: iconst_0
        //   27: invokevirtual 45	android/app/PendingIntent:writeToParcel	(Landroid/os/Parcel;I)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/location/internal/zzh$zza$zza:zznJ	Landroid/os/IBinder;
        //   34: iconst_3
        //   35: aload_3
        //   36: aconst_null
        //   37: iconst_1
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:recycle	()V
        //   48: return
        //   49: aload_3
        //   50: iconst_0
        //   51: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   54: goto -24 -> 30
        //   57: astore 4
        //   59: aload_3
        //   60: invokevirtual 54	android/os/Parcel:recycle	()V
        //   63: aload 4
        //   65: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	66	0	this	zza
        //   0	66	1	paramInt	int
        //   0	66	2	paramPendingIntent	PendingIntent
        //   3	57	3	localParcel	Parcel
        //   57	7	4	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   4	44	57	finally
        //   49	54	57	finally
      }
      
      public void zza(int paramInt, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
          localParcel.writeInt(paramInt);
          localParcel.writeStringArray(paramArrayOfString);
          this.zznJ.transact(1, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void zzb(int paramInt, String[] paramArrayOfString)
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.location.internal.IGeofencerCallbacks");
          localParcel.writeInt(paramInt);
          localParcel.writeStringArray(paramArrayOfString);
          this.zznJ.transact(2, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/internal/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */