package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class LocationRequestCreator
  implements Parcelable.Creator<LocationRequest>
{
  public static final int CONTENT_DESCRIPTION;
  
  static void zza(LocationRequest paramLocationRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramLocationRequest.mPriority);
    zzb.zzc(paramParcel, 1000, paramLocationRequest.getVersionCode());
    zzb.zza(paramParcel, 2, paramLocationRequest.zzaEE);
    zzb.zza(paramParcel, 3, paramLocationRequest.zzaEF);
    zzb.zza(paramParcel, 4, paramLocationRequest.zzasP);
    zzb.zza(paramParcel, 5, paramLocationRequest.zzaEj);
    zzb.zzc(paramParcel, 6, paramLocationRequest.zzaEG);
    zzb.zza(paramParcel, 7, paramLocationRequest.zzaEH);
    zzb.zza(paramParcel, 8, paramLocationRequest.zzaEI);
    zzb.zzI(paramParcel, i);
  }
  
  public LocationRequest createFromParcel(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    int k = 102;
    long l1 = 3600000L;
    long l2 = 600000L;
    boolean bool = false;
    long l3 = Long.MAX_VALUE;
    int m = Integer.MAX_VALUE;
    float f = 0.0F;
    long l4 = 0L;
    while (paramParcel.dataPosition() < i)
    {
      int n = zza.zzao(paramParcel);
      switch (zza.zzbM(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        k = zza.zzg(paramParcel, n);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, n);
        break;
      case 2: 
        l1 = zza.zzi(paramParcel, n);
        break;
      case 3: 
        l2 = zza.zzi(paramParcel, n);
        break;
      case 4: 
        bool = zza.zzc(paramParcel, n);
        break;
      case 5: 
        l3 = zza.zzi(paramParcel, n);
        break;
      case 6: 
        m = zza.zzg(paramParcel, n);
        break;
      case 7: 
        f = zza.zzl(paramParcel, n);
        break;
      case 8: 
        l4 = zza.zzi(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new LocationRequest(j, k, l1, l2, bool, l3, m, f, l4);
  }
  
  public LocationRequest[] newArray(int paramInt)
  {
    return new LocationRequest[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/LocationRequestCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */