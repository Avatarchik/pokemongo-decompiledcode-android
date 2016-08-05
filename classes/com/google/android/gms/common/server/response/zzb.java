package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;

public class zzb
  implements Parcelable.Creator<FieldMappingDictionary.FieldMapPair>
{
  static void zza(FieldMappingDictionary.FieldMapPair paramFieldMapPair, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, paramFieldMapPair.versionCode);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 2, paramFieldMapPair.key, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 3, paramFieldMapPair.zzahi, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzI(paramParcel, i);
  }
  
  public FieldMappingDictionary.FieldMapPair zzaw(Parcel paramParcel)
  {
    FastJsonResponse.Field localField = null;
    int i = zza.zzap(paramParcel);
    int j = 0;
    String str = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        j = zza.zzg(paramParcel, k);
        break;
      case 2: 
        str = zza.zzp(paramParcel, k);
        break;
      case 3: 
        localField = (FastJsonResponse.Field)zza.zza(paramParcel, k, FastJsonResponse.Field.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new FieldMappingDictionary.FieldMapPair(j, str, localField);
  }
  
  public FieldMappingDictionary.FieldMapPair[] zzbU(int paramInt)
  {
    return new FieldMappingDictionary.FieldMapPair[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/server/response/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */