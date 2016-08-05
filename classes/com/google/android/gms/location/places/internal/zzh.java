package com.google.android.gms.location.places.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;

public abstract interface zzh
  extends IInterface
{
  public abstract void zza(PlacePhotoMetadataResult paramPlacePhotoMetadataResult)
    throws RemoteException;
  
  public abstract void zza(PlacePhotoResult paramPlacePhotoResult)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzh
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.location.places.internal.IPhotosCallbacks");
    }
    
    public static zzh zzcg(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzh)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.places.internal.IPhotosCallbacks");
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
        paramParcel2.writeString("com.google.android.gms.location.places.internal.IPhotosCallbacks");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IPhotosCallbacks");
        if (paramParcel1.readInt() != 0) {
          localObject = (PlacePhotoMetadataResult)PlacePhotoMetadataResult.CREATOR.createFromParcel(paramParcel1);
        }
        zza((PlacePhotoMetadataResult)localObject);
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IPhotosCallbacks");
        if (paramParcel1.readInt() != 0) {
          localObject = (PlacePhotoResult)PlacePhotoResult.CREATOR.createFromParcel(paramParcel1);
        }
        zza((PlacePhotoResult)localObject);
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
      public void zza(PlacePhotoMetadataResult paramPlacePhotoMetadataResult)
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
        //   22: invokevirtual 45	com/google/android/gms/location/places/PlacePhotoMetadataResult:writeToParcel	(Landroid/os/Parcel;I)V
        //   25: aload_0
        //   26: getfield 18	com/google/android/gms/location/places/internal/zzh$zza$zza:zznJ	Landroid/os/IBinder;
        //   29: iconst_3
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
        //   0	59	1	paramPlacePhotoMetadataResult	PlacePhotoMetadataResult
        //   3	51	2	localParcel	Parcel
        //   52	6	3	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   4	39	52	finally
        //   44	49	52	finally
      }
      
      /* Error */
      public void zza(PlacePhotoResult paramPlacePhotoResult)
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
        //   22: invokevirtual 58	com/google/android/gms/location/places/PlacePhotoResult:writeToParcel	(Landroid/os/Parcel;I)V
        //   25: aload_0
        //   26: getfield 18	com/google/android/gms/location/places/internal/zzh$zza$zza:zznJ	Landroid/os/IBinder;
        //   29: iconst_2
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
        //   0	59	1	paramPlacePhotoResult	PlacePhotoResult
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


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */