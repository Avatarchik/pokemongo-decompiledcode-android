package com.google.android.gms.location.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.location.LocationSettingsResult;

public abstract interface zzj
  extends IInterface
{
  public abstract void zza(LocationSettingsResult paramLocationSettingsResult)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzj
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.location.internal.ISettingsCallbacks");
    }
    
    public static zzj zzcc(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzj)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.internal.ISettingsCallbacks");
        if ((localIInterface != null) && ((localIInterface instanceof zzj))) {
          localObject = (zzj)localIInterface;
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
          paramParcel2.writeString("com.google.android.gms.location.internal.ISettingsCallbacks");
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.location.internal.ISettingsCallbacks");
      if (paramParcel1.readInt() != 0) {}
      for (LocationSettingsResult localLocationSettingsResult = (LocationSettingsResult)LocationSettingsResult.CREATOR.createFromParcel(paramParcel1);; localLocationSettingsResult = null)
      {
        zza(localLocationSettingsResult);
        bool = true;
        break;
      }
    }
    
    private static class zza
      implements zzj
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
      public void zza(LocationSettingsResult paramLocationSettingsResult)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 29	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: aload_2
        //   5: ldc 31
        //   7: invokevirtual 35	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   10: aload_1
        //   11: ifnull +33 -> 44
        //   14: aload_2
        //   15: iconst_1
        //   16: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   19: aload_1
        //   20: aload_2
        //   21: iconst_0
        //   22: invokevirtual 45	com/google/android/gms/location/LocationSettingsResult:writeToParcel	(Landroid/os/Parcel;I)V
        //   25: aload_0
        //   26: getfield 18	com/google/android/gms/location/internal/zzj$zza$zza:zznJ	Landroid/os/IBinder;
        //   29: iconst_1
        //   30: aload_2
        //   31: aconst_null
        //   32: iconst_1
        //   33: invokeinterface 51 5 0
        //   38: pop
        //   39: aload_2
        //   40: invokevirtual 54	android/os/Parcel:recycle	()V
        //   43: return
        //   44: aload_2
        //   45: iconst_0
        //   46: invokevirtual 39	android/os/Parcel:writeInt	(I)V
        //   49: goto -24 -> 25
        //   52: astore_3
        //   53: aload_2
        //   54: invokevirtual 54	android/os/Parcel:recycle	()V
        //   57: aload_3
        //   58: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	59	0	this	zza
        //   0	59	1	paramLocationSettingsResult	LocationSettingsResult
        //   3	51	2	localParcel	Parcel
        //   52	6	3	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   4	39	52	finally
        //   44	49	52	finally
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/internal/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */