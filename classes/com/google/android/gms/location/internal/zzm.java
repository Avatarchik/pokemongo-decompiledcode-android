package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.LocationRequest;
import java.util.List;

public class zzm
  implements Parcelable.Creator<LocationRequestInternal>
{
  static void zza(LocationRequestInternal paramLocationRequestInternal, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramLocationRequestInternal.zzasN, paramInt, false);
    zzb.zzc(paramParcel, 1000, paramLocationRequestInternal.getVersionCode());
    zzb.zza(paramParcel, 2, paramLocationRequestInternal.zzaFE);
    zzb.zza(paramParcel, 3, paramLocationRequestInternal.zzaFF);
    zzb.zza(paramParcel, 4, paramLocationRequestInternal.zzaFG);
    zzb.zzc(paramParcel, 5, paramLocationRequestInternal.zzaFH, false);
    zzb.zza(paramParcel, 6, paramLocationRequestInternal.mTag, false);
    zzb.zza(paramParcel, 7, paramLocationRequestInternal.zzaFI);
    zzb.zzI(paramParcel, i);
  }
  
  public LocationRequestInternal zzeH(Parcel paramParcel)
  {
    String str = null;
    boolean bool1 = true;
    boolean bool2 = false;
    int i = zza.zzap(paramParcel);
    Object localObject = LocationRequestInternal.zzaFD;
    boolean bool3 = bool1;
    boolean bool4 = false;
    LocationRequest localLocationRequest = null;
    int j = 0;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localLocationRequest = (LocationRequest)zza.zza(paramParcel, k, LocationRequest.CREATOR);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
        break;
      case 2: 
        bool4 = zza.zzc(paramParcel, k);
        break;
      case 3: 
        bool3 = zza.zzc(paramParcel, k);
        break;
      case 4: 
        bool1 = zza.zzc(paramParcel, k);
        break;
      case 5: 
        localObject = zza.zzc(paramParcel, k, ClientIdentity.CREATOR);
        break;
      case 6: 
        str = zza.zzp(paramParcel, k);
        break;
      case 7: 
        bool2 = zza.zzc(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new LocationRequestInternal(j, localLocationRequest, bool4, bool3, bool1, (List)localObject, str, bool2);
  }
  
  public LocationRequestInternal[] zzha(int paramInt)
  {
    return new LocationRequestInternal[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/internal/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */