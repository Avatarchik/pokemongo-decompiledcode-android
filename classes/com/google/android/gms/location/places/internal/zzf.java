package com.google.android.gms.location.places.internal;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.location.places.NearbyAlertRequest;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.PlaceRequest;
import com.google.android.gms.location.places.zze;
import com.google.android.gms.location.places.zzg;

public abstract interface zzf
  extends IInterface
{
  public abstract void zza(NearbyAlertRequest paramNearbyAlertRequest, PlacesParams paramPlacesParams, PendingIntent paramPendingIntent, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(PlaceReport paramPlaceReport, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(PlaceRequest paramPlaceRequest, PlacesParams paramPlacesParams, PendingIntent paramPendingIntent, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(PlacesParams paramPlacesParams, PendingIntent paramPendingIntent, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zzb(PlacesParams paramPlacesParams, PendingIntent paramPendingIntent, zzi paramzzi)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzf
  {
    public static zzf zzce(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzf)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
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
      PlacesParams localPlacesParams1 = null;
      boolean bool;
      switch (paramInt1)
      {
      default: 
      case 1598968902: 
        for (bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);; bool = true)
        {
          return bool;
          paramParcel2.writeString("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        }
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        PlaceRequest localPlaceRequest;
        PlacesParams localPlacesParams5;
        if (paramParcel1.readInt() != 0)
        {
          localPlaceRequest = (PlaceRequest)PlaceRequest.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label195;
          }
          localPlacesParams5 = PlacesParams.CREATOR.zzeY(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label201;
          }
        }
        for (PendingIntent localPendingIntent4 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent4 = null)
        {
          zza(localPlaceRequest, localPlacesParams5, localPendingIntent4, zzi.zza.zzch(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool = true;
          break;
          localPlaceRequest = null;
          break label125;
          localPlacesParams5 = null;
          break label141;
        }
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        PlacesParams localPlacesParams4;
        if (paramParcel1.readInt() != 0)
        {
          localPlacesParams4 = PlacesParams.CREATOR.zzeY(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label281;
          }
        }
        for (PendingIntent localPendingIntent3 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent3 = null)
        {
          zza(localPlacesParams4, localPendingIntent3, zzi.zza.zzch(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool = true;
          break;
          localPlacesParams4 = null;
          break label229;
        }
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        NearbyAlertRequest localNearbyAlertRequest;
        PlacesParams localPlacesParams3;
        if (paramParcel1.readInt() != 0)
        {
          localNearbyAlertRequest = NearbyAlertRequest.CREATOR.zzeN(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label379;
          }
          localPlacesParams3 = PlacesParams.CREATOR.zzeY(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label385;
          }
        }
        for (PendingIntent localPendingIntent2 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent2 = null)
        {
          zza(localNearbyAlertRequest, localPlacesParams3, localPendingIntent2, zzi.zza.zzch(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool = true;
          break;
          localNearbyAlertRequest = null;
          break label309;
          localPlacesParams3 = null;
          break label325;
        }
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        PlacesParams localPlacesParams2;
        if (paramParcel1.readInt() != 0)
        {
          localPlacesParams2 = PlacesParams.CREATOR.zzeY(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label465;
          }
        }
        for (PendingIntent localPendingIntent1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent1 = null)
        {
          zzb(localPlacesParams2, localPendingIntent1, zzi.zza.zzch(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool = true;
          break;
          localPlacesParams2 = null;
          break label413;
        }
      case 6: 
        label125:
        label141:
        label195:
        label201:
        label229:
        label281:
        label309:
        label325:
        label379:
        label385:
        label413:
        label465:
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
        if (paramParcel1.readInt() != 0) {}
        for (PlaceFilter localPlaceFilter = PlaceFilter.CREATOR.zzeO(paramParcel1);; localPlaceFilter = null)
        {
          if (paramParcel1.readInt() != 0) {
            localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
          }
          zza(localPlaceFilter, localPlacesParams1, zzi.zza.zzch(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool = true;
          break;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
      if (paramParcel1.readInt() != 0) {}
      for (PlaceReport localPlaceReport = (PlaceReport)PlaceReport.CREATOR.createFromParcel(paramParcel1);; localPlaceReport = null)
      {
        if (paramParcel1.readInt() != 0) {
          localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
        }
        zza(localPlaceReport, localPlacesParams1, zzi.zza.zzch(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        break;
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
      
      public void zza(NearbyAlertRequest paramNearbyAlertRequest, PlacesParams paramPlacesParams, PendingIntent paramPendingIntent, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
            if (paramNearbyAlertRequest != null)
            {
              localParcel1.writeInt(1);
              paramNearbyAlertRequest.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramPendingIntent == null) {
                  break label154;
                }
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label163;
                }
                localIBinder = paramzzi.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(4, localParcel1, localParcel2, 0);
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
          continue;
          label154:
          localParcel1.writeInt(0);
          continue;
          label163:
          IBinder localIBinder = null;
        }
      }
      
      public void zza(PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
            if (paramPlaceFilter != null)
            {
              localParcel1.writeInt(1);
              paramPlaceFilter.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label136;
                }
                localIBinder = paramzzi.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(6, localParcel1, localParcel2, 0);
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
          continue;
          label136:
          IBinder localIBinder = null;
        }
      }
      
      public void zza(PlaceReport paramPlaceReport, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
            if (paramPlaceReport != null)
            {
              localParcel1.writeInt(1);
              paramPlaceReport.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label136;
                }
                localIBinder = paramzzi.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
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
          continue;
          label136:
          IBinder localIBinder = null;
        }
      }
      
      public void zza(PlaceRequest paramPlaceRequest, PlacesParams paramPlacesParams, PendingIntent paramPendingIntent, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
            if (paramPlaceRequest != null)
            {
              localParcel1.writeInt(1);
              paramPlaceRequest.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramPendingIntent == null) {
                  break label154;
                }
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label163;
                }
                localIBinder = paramzzi.asBinder();
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
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label154:
          localParcel1.writeInt(0);
          continue;
          label163:
          IBinder localIBinder = null;
        }
      }
      
      public void zza(PlacesParams paramPlacesParams, PendingIntent paramPendingIntent, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label135;
                }
                localIBinder = paramzzi.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
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
          continue;
          label135:
          IBinder localIBinder = null;
        }
      }
      
      public void zzb(PlacesParams paramPlacesParams, PendingIntent paramPendingIntent, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlaceDetectionService");
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label135;
                }
                localIBinder = paramzzi.asBinder();
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
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label135:
          IBinder localIBinder = null;
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */