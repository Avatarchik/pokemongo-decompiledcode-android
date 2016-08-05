package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzm
  implements Parcelable.Creator<UserDataType>
{
  static void zza(UserDataType paramUserDataType, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramUserDataType.zzGq, false);
    zzb.zzc(paramParcel, 1000, paramUserDataType.mVersionCode);
    zzb.zzc(paramParcel, 2, paramUserDataType.zzaGM);
    zzb.zzI(paramParcel, i);
  }
  
  public UserDataType zzeT(Parcel paramParcel)
  {
    int i = 0;
    int j = zza.zzap(paramParcel);
    String str = null;
    int k = 0;
    while (paramParcel.dataPosition() < j)
    {
      int m = zza.zzao(paramParcel);
      switch (zza.zzbM(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1: 
        str = zza.zzp(paramParcel, m);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, m);
        break;
      case 2: 
        i = zza.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new UserDataType(k, str, i);
  }
  
  public UserDataType[] zzhq(int paramInt)
  {
    return new UserDataType[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */