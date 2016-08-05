package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze
  implements Parcelable.Creator<FusedLocationProviderResult>
{
  static void zza(FusedLocationProviderResult paramFusedLocationProviderResult, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramFusedLocationProviderResult.getStatus(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramFusedLocationProviderResult.getVersionCode());
    zzb.zzI(paramParcel, i);
  }
  
  public FusedLocationProviderResult zzeG(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    Status localStatus = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localStatus = (Status)zza.zza(paramParcel, k, Status.CREATOR);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new FusedLocationProviderResult(j, localStatus);
  }
  
  public FusedLocationProviderResult[] zzgY(int paramInt)
  {
    return new FusedLocationProviderResult[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/internal/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */