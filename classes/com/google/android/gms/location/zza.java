package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.internal.ParcelableGeofence;
import java.util.ArrayList;

public class zza
  implements Parcelable.Creator<GeofencingRequest>
{
  static void zza(GeofencingRequest paramGeofencingRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramGeofencingRequest.zzwv(), false);
    zzb.zzc(paramParcel, 1000, paramGeofencingRequest.getVersionCode());
    zzb.zzc(paramParcel, 2, paramGeofencingRequest.getInitialTrigger());
    zzb.zzI(paramParcel, i);
  }
  
  public GeofencingRequest zzez(Parcel paramParcel)
  {
    int i = 0;
    int j = com.google.android.gms.common.internal.safeparcel.zza.zzap(paramParcel);
    ArrayList localArrayList = null;
    int k = 0;
    while (paramParcel.dataPosition() < j)
    {
      int m = com.google.android.gms.common.internal.safeparcel.zza.zzao(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(m))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, m);
        break;
      case 1: 
        localArrayList = com.google.android.gms.common.internal.safeparcel.zza.zzc(paramParcel, m, ParcelableGeofence.CREATOR);
        break;
      case 1000: 
        k = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        break;
      case 2: 
        i = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new GeofencingRequest(k, localArrayList, i);
  }
  
  public GeofencingRequest[] zzgN(int paramInt)
  {
    return new GeofencingRequest[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */