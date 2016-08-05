package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzd
  implements Parcelable.Creator<FieldMappingDictionary.Entry>
{
  static void zza(FieldMappingDictionary.Entry paramEntry, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramEntry.versionCode);
    zzb.zza(paramParcel, 2, paramEntry.className, false);
    zzb.zzc(paramParcel, 3, paramEntry.zzahh, false);
    zzb.zzI(paramParcel, i);
  }
  
  public FieldMappingDictionary.Entry zzay(Parcel paramParcel)
  {
    ArrayList localArrayList = null;
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
        localArrayList = zza.zzc(paramParcel, k, FieldMappingDictionary.FieldMapPair.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new FieldMappingDictionary.Entry(j, str, localArrayList);
  }
  
  public FieldMappingDictionary.Entry[] zzbW(int paramInt)
  {
    return new FieldMappingDictionary.Entry[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/server/response/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */