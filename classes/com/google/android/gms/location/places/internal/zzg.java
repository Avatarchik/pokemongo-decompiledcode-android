package com.google.android.gms.location.places.internal;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.NearbyAlertRequest;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.PlaceRequest;
import com.google.android.gms.location.places.UserDataType;
import com.google.android.gms.location.places.personalized.PlaceAlias;
import com.google.android.gms.location.places.zzm;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.zzd;
import java.util.ArrayList;
import java.util.List;

public abstract interface zzg
  extends IInterface
{
  public abstract void zza(AddPlaceRequest paramAddPlaceRequest, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(NearbyAlertRequest paramNearbyAlertRequest, PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(PlaceReport paramPlaceReport, PlacesParams paramPlacesParams)
    throws RemoteException;
  
  public abstract void zza(PlaceRequest paramPlaceRequest, PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(UserDataType paramUserDataType, LatLngBounds paramLatLngBounds, List<String> paramList, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zza(PlaceAlias paramPlaceAlias, String paramString1, String paramString2, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(LatLng paramLatLng, PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(LatLngBounds paramLatLngBounds, int paramInt, String paramString, PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(String paramString, int paramInt1, int paramInt2, int paramInt3, PlacesParams paramPlacesParams, zzh paramzzh)
    throws RemoteException;
  
  public abstract void zza(String paramString, int paramInt, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(String paramString, PlacesParams paramPlacesParams, zzh paramzzh)
    throws RemoteException;
  
  public abstract void zza(String paramString, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(String paramString, LatLngBounds paramLatLngBounds, AutocompleteFilter paramAutocompleteFilter, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zza(List<String> paramList, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zzb(PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zzb(PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
    throws RemoteException;
  
  public abstract void zzb(String paramString, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public abstract void zzb(List<String> paramList, PlacesParams paramPlacesParams, zzi paramzzi)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzg
  {
    public static zzg zzcf(IBinder paramIBinder)
    {
      Object localObject;
      if (paramIBinder == null) {
        localObject = null;
      }
      for (;;)
      {
        return (zzg)localObject;
        IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if ((localIInterface != null) && ((localIInterface instanceof zzg))) {
          localObject = (zzg)localIInterface;
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
        bool = super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      }
      for (;;)
      {
        return bool;
        paramParcel2.writeString("com.google.android.gms.location.places.internal.IGooglePlacesService");
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        LatLngBounds localLatLngBounds3;
        label224:
        int n;
        String str9;
        if (paramParcel1.readInt() != 0)
        {
          localLatLngBounds3 = LatLngBounds.CREATOR.zzfk(paramParcel1);
          n = paramParcel1.readInt();
          str9 = paramParcel1.readString();
          if (paramParcel1.readInt() == 0) {
            break label305;
          }
        }
        label305:
        for (PlaceFilter localPlaceFilter3 = PlaceFilter.CREATOR.zzeO(paramParcel1);; localPlaceFilter3 = null)
        {
          if (paramParcel1.readInt() != 0) {
            localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
          }
          zza(localLatLngBounds3, n, str9, localPlaceFilter3, localPlacesParams1, zzi.zza.zzch(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool = true;
          break;
          localLatLngBounds3 = null;
          break label224;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        String str8 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {
          localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
        }
        zza(str8, localPlacesParams1, zzi.zza.zzch(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        LatLng localLatLng;
        if (paramParcel1.readInt() != 0)
        {
          localLatLng = LatLng.CREATOR.zzfl(paramParcel1);
          label386:
          if (paramParcel1.readInt() == 0) {
            break label451;
          }
        }
        label451:
        for (PlaceFilter localPlaceFilter2 = PlaceFilter.CREATOR.zzeO(paramParcel1);; localPlaceFilter2 = null)
        {
          if (paramParcel1.readInt() != 0) {
            localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
          }
          zza(localLatLng, localPlaceFilter2, localPlacesParams1, zzi.zza.zzch(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool = true;
          break;
          localLatLng = null;
          break label386;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0) {}
        for (PlaceFilter localPlaceFilter1 = PlaceFilter.CREATOR.zzeO(paramParcel1);; localPlaceFilter1 = null)
        {
          if (paramParcel1.readInt() != 0) {
            localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
          }
          zzb(localPlaceFilter1, localPlacesParams1, zzi.zza.zzch(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        String str7 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {
          localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
        }
        zzb(str7, localPlacesParams1, zzi.zza.zzch(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        ArrayList localArrayList3 = paramParcel1.createStringArrayList();
        if (paramParcel1.readInt() != 0) {
          localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
        }
        zza(localArrayList3, localPlacesParams1, zzi.zza.zzch(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        ArrayList localArrayList2 = paramParcel1.createStringArrayList();
        if (paramParcel1.readInt() != 0) {
          localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
        }
        zzb(localArrayList2, localPlacesParams1, zzi.zza.zzch(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        UserDataType localUserDataType;
        label707:
        LatLngBounds localLatLngBounds2;
        label723:
        ArrayList localArrayList1;
        if (paramParcel1.readInt() != 0)
        {
          localUserDataType = UserDataType.CREATOR.zzeT(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label780;
          }
          localLatLngBounds2 = LatLngBounds.CREATOR.zzfk(paramParcel1);
          localArrayList1 = paramParcel1.createStringArrayList();
          if (paramParcel1.readInt() == 0) {
            break label786;
          }
        }
        label780:
        label786:
        for (PlacesParams localPlacesParams8 = PlacesParams.CREATOR.zzeY(paramParcel1);; localPlacesParams8 = null)
        {
          zza(localUserDataType, localLatLngBounds2, localArrayList1, localPlacesParams8, zzi.zza.zzch(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool = true;
          break;
          localUserDataType = null;
          break label707;
          localLatLngBounds2 = null;
          break label723;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        PlaceRequest localPlaceRequest;
        label819:
        PlacesParams localPlacesParams7;
        if (paramParcel1.readInt() != 0)
        {
          localPlaceRequest = (PlaceRequest)PlaceRequest.CREATOR.createFromParcel(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label882;
          }
          localPlacesParams7 = PlacesParams.CREATOR.zzeY(paramParcel1);
          label835:
          if (paramParcel1.readInt() == 0) {
            break label888;
          }
        }
        label882:
        label888:
        for (PendingIntent localPendingIntent4 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent4 = null)
        {
          zza(localPlaceRequest, localPlacesParams7, localPendingIntent4);
          paramParcel2.writeNoException();
          bool = true;
          break;
          localPlaceRequest = null;
          break label819;
          localPlacesParams7 = null;
          break label835;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        PlacesParams localPlacesParams6;
        if (paramParcel1.readInt() != 0)
        {
          localPlacesParams6 = PlacesParams.CREATOR.zzeY(paramParcel1);
          label916:
          if (paramParcel1.readInt() == 0) {
            break label961;
          }
        }
        label961:
        for (PendingIntent localPendingIntent3 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent3 = null)
        {
          zza(localPlacesParams6, localPendingIntent3);
          paramParcel2.writeNoException();
          bool = true;
          break;
          localPlacesParams6 = null;
          break label916;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        NearbyAlertRequest localNearbyAlertRequest;
        label989:
        PlacesParams localPlacesParams5;
        if (paramParcel1.readInt() != 0)
        {
          localNearbyAlertRequest = NearbyAlertRequest.CREATOR.zzeN(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1052;
          }
          localPlacesParams5 = PlacesParams.CREATOR.zzeY(paramParcel1);
          label1005:
          if (paramParcel1.readInt() == 0) {
            break label1058;
          }
        }
        label1052:
        label1058:
        for (PendingIntent localPendingIntent2 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent2 = null)
        {
          zza(localNearbyAlertRequest, localPlacesParams5, localPendingIntent2);
          paramParcel2.writeNoException();
          bool = true;
          break;
          localNearbyAlertRequest = null;
          break label989;
          localPlacesParams5 = null;
          break label1005;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        PlacesParams localPlacesParams4;
        if (paramParcel1.readInt() != 0)
        {
          localPlacesParams4 = PlacesParams.CREATOR.zzeY(paramParcel1);
          label1086:
          if (paramParcel1.readInt() == 0) {
            break label1131;
          }
        }
        label1131:
        for (PendingIntent localPendingIntent1 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel1);; localPendingIntent1 = null)
        {
          zzb(localPlacesParams4, localPendingIntent1);
          paramParcel2.writeNoException();
          bool = true;
          break;
          localPlacesParams4 = null;
          break label1086;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        String str6 = paramParcel1.readString();
        LatLngBounds localLatLngBounds1;
        label1165:
        AutocompleteFilter localAutocompleteFilter;
        if (paramParcel1.readInt() != 0)
        {
          localLatLngBounds1 = LatLngBounds.CREATOR.zzfk(paramParcel1);
          if (paramParcel1.readInt() == 0) {
            break label1232;
          }
          localAutocompleteFilter = AutocompleteFilter.CREATOR.zzeL(paramParcel1);
          label1181:
          if (paramParcel1.readInt() == 0) {
            break label1238;
          }
        }
        label1232:
        label1238:
        for (PlacesParams localPlacesParams3 = PlacesParams.CREATOR.zzeY(paramParcel1);; localPlacesParams3 = null)
        {
          zza(str6, localLatLngBounds1, localAutocompleteFilter, localPlacesParams3, zzi.zza.zzch(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool = true;
          break;
          localLatLngBounds1 = null;
          break label1165;
          localAutocompleteFilter = null;
          break label1181;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0) {}
        for (AddPlaceRequest localAddPlaceRequest = (AddPlaceRequest)AddPlaceRequest.CREATOR.createFromParcel(paramParcel1);; localAddPlaceRequest = null)
        {
          if (paramParcel1.readInt() != 0) {
            localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
          }
          zza(localAddPlaceRequest, localPlacesParams1, zzi.zza.zzch(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        if (paramParcel1.readInt() != 0) {}
        for (PlaceReport localPlaceReport = (PlaceReport)PlaceReport.CREATOR.createFromParcel(paramParcel1);; localPlaceReport = null)
        {
          if (paramParcel1.readInt() != 0) {
            localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
          }
          zza(localPlaceReport, localPlacesParams1);
          paramParcel2.writeNoException();
          bool = true;
          break;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        PlaceAlias localPlaceAlias;
        label1407:
        String str4;
        String str5;
        if (paramParcel1.readInt() != 0)
        {
          localPlaceAlias = PlaceAlias.CREATOR.zzfc(paramParcel1);
          str4 = paramParcel1.readString();
          str5 = paramParcel1.readString();
          if (paramParcel1.readInt() == 0) {
            break label1470;
          }
        }
        label1470:
        for (PlacesParams localPlacesParams2 = PlacesParams.CREATOR.zzeY(paramParcel1);; localPlacesParams2 = null)
        {
          zza(localPlaceAlias, str4, str5, localPlacesParams2, zzi.zza.zzch(paramParcel1.readStrongBinder()));
          paramParcel2.writeNoException();
          bool = true;
          break;
          localPlaceAlias = null;
          break label1407;
        }
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        String str3 = paramParcel1.readString();
        int m = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {
          localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
        }
        zza(str3, m, localPlacesParams1, zzi.zza.zzch(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        String str2 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {
          localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
        }
        zza(str2, localPlacesParams1, zzh.zza.zzcg(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
        continue;
        paramParcel1.enforceInterface("com.google.android.gms.location.places.internal.IGooglePlacesService");
        String str1 = paramParcel1.readString();
        int i = paramParcel1.readInt();
        int j = paramParcel1.readInt();
        int k = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0) {
          localPlacesParams1 = PlacesParams.CREATOR.zzeY(paramParcel1);
        }
        zza(str1, i, j, k, localPlacesParams1, zzh.zza.zzcg(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        bool = true;
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
      
      public void zza(AddPlaceRequest paramAddPlaceRequest, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramAddPlaceRequest != null)
            {
              localParcel1.writeInt(1);
              paramAddPlaceRequest.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label136;
                }
                localIBinder = paramzzi.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(14, localParcel1, localParcel2, 0);
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
      
      public void zza(NearbyAlertRequest paramNearbyAlertRequest, PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramNearbyAlertRequest != null)
            {
              localParcel1.writeInt(1);
              paramNearbyAlertRequest.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramPendingIntent == null) {
                  break label134;
                }
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zznJ.transact(11, localParcel1, localParcel2, 0);
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
          label134:
          localParcel1.writeInt(0);
        }
      }
      
      public void zza(PlaceReport paramPlaceReport, PlacesParams paramPlacesParams)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlaceReport != null)
            {
              localParcel1.writeInt(1);
              paramPlaceReport.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                this.zznJ.transact(15, localParcel1, localParcel2, 0);
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
      
      public void zza(PlaceRequest paramPlaceRequest, PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlaceRequest != null)
            {
              localParcel1.writeInt(1);
              paramPlaceRequest.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramPendingIntent == null) {
                  break label134;
                }
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zznJ.transact(9, localParcel1, localParcel2, 0);
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
          label134:
          localParcel1.writeInt(0);
        }
      }
      
      public void zza(UserDataType paramUserDataType, LatLngBounds paramLatLngBounds, List<String> paramList, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramUserDataType != null)
            {
              localParcel1.writeInt(1);
              paramUserDataType.writeToParcel(localParcel1, 0);
              if (paramLatLngBounds != null)
              {
                localParcel1.writeInt(1);
                paramLatLngBounds.writeToParcel(localParcel1, 0);
                localParcel1.writeStringList(paramList);
                if (paramPlacesParams == null) {
                  break label163;
                }
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label172;
                }
                localIBinder = paramzzi.asBinder();
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
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
          continue;
          label163:
          localParcel1.writeInt(0);
          continue;
          label172:
          IBinder localIBinder = null;
        }
      }
      
      public void zza(PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zznJ.transact(10, localParcel1, localParcel2, 0);
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
      
      public void zza(PlaceAlias paramPlaceAlias, String paramString1, String paramString2, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlaceAlias != null)
            {
              localParcel1.writeInt(1);
              paramPlaceAlias.writeToParcel(localParcel1, 0);
              localParcel1.writeString(paramString1);
              localParcel1.writeString(paramString2);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label152;
                }
                localIBinder = paramzzi.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(16, localParcel1, localParcel2, 0);
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
          label152:
          IBinder localIBinder = null;
        }
      }
      
      public void zza(LatLng paramLatLng, PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramLatLng != null)
            {
              localParcel1.writeInt(1);
              paramLatLng.writeToParcel(localParcel1, 0);
              if (paramPlaceFilter != null)
              {
                localParcel1.writeInt(1);
                paramPlaceFilter.writeToParcel(localParcel1, 0);
                if (paramPlacesParams == null) {
                  break label154;
                }
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
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
      
      public void zza(LatLngBounds paramLatLngBounds, int paramInt, String paramString, PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramLatLngBounds != null)
            {
              localParcel1.writeInt(1);
              paramLatLngBounds.writeToParcel(localParcel1, 0);
              localParcel1.writeInt(paramInt);
              localParcel1.writeString(paramString);
              if (paramPlaceFilter != null)
              {
                localParcel1.writeInt(1);
                paramPlaceFilter.writeToParcel(localParcel1, 0);
                if (paramPlacesParams == null) {
                  break label170;
                }
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label179;
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
          label170:
          localParcel1.writeInt(0);
          continue;
          label179:
          IBinder localIBinder = null;
        }
      }
      
      public void zza(String paramString, int paramInt1, int paramInt2, int paramInt3, PlacesParams paramPlacesParams, zzh paramzzh)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt1);
            localParcel1.writeInt(paramInt2);
            localParcel1.writeInt(paramInt3);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzh != null)
              {
                localIBinder = paramzzh.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(20, localParcel1, localParcel2, 0);
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
      
      public void zza(String paramString, int paramInt, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            localParcel1.writeInt(paramInt);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzi != null)
              {
                localIBinder = paramzzi.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(18, localParcel1, localParcel2, 0);
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
      
      public void zza(String paramString, PlacesParams paramPlacesParams, zzh paramzzh)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzh != null)
              {
                localIBinder = paramzzh.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(19, localParcel1, localParcel2, 0);
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
      
      public void zza(String paramString, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzi != null)
              {
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
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zza(String paramString, LatLngBounds paramLatLngBounds, AutocompleteFilter paramAutocompleteFilter, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            if (paramLatLngBounds != null)
            {
              localParcel1.writeInt(1);
              paramLatLngBounds.writeToParcel(localParcel1, 0);
              if (paramAutocompleteFilter != null)
              {
                localParcel1.writeInt(1);
                paramAutocompleteFilter.writeToParcel(localParcel1, 0);
                if (paramPlacesParams == null) {
                  break label163;
                }
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
                if (paramzzi == null) {
                  break label172;
                }
                localIBinder = paramzzi.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(13, localParcel1, localParcel2, 0);
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
          label163:
          localParcel1.writeInt(0);
          continue;
          label172:
          IBinder localIBinder = null;
        }
      }
      
      public void zza(List<String> paramList, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeStringList(paramList);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzi != null)
              {
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
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zzb(PlaceFilter paramPlaceFilter, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlaceFilter != null)
            {
              localParcel1.writeInt(1);
              paramPlaceFilter.writeToParcel(localParcel1, 0);
              if (paramPlacesParams != null)
              {
                localParcel1.writeInt(1);
                paramPlacesParams.writeToParcel(localParcel1, 0);
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
      
      public void zzb(PlacesParams paramPlacesParams, PendingIntent paramPendingIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramPendingIntent != null)
              {
                localParcel1.writeInt(1);
                paramPendingIntent.writeToParcel(localParcel1, 0);
                this.zznJ.transact(12, localParcel1, localParcel2, 0);
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
      
      public void zzb(String paramString, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeString(paramString);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzi != null)
              {
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
            IBinder localIBinder = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void zzb(List<String> paramList, PlacesParams paramPlacesParams, zzi paramzzi)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.location.places.internal.IGooglePlacesService");
            localParcel1.writeStringList(paramList);
            if (paramPlacesParams != null)
            {
              localParcel1.writeInt(1);
              paramPlacesParams.writeToParcel(localParcel1, 0);
              if (paramzzi != null)
              {
                localIBinder = paramzzi.asBinder();
                localParcel1.writeStrongBinder(localIBinder);
                this.zznJ.transact(17, localParcel1, localParcel2, 0);
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
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */