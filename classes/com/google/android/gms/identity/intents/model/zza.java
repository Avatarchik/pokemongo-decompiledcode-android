package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza
  implements Parcelable.Creator<CountrySpecification>
{
  static void zza(CountrySpecification paramCountrySpecification, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramCountrySpecification.getVersionCode());
    zzb.zza(paramParcel, 2, paramCountrySpecification.zzGw, false);
    zzb.zzI(paramParcel, i);
  }
  
  public CountrySpecification zzew(Parcel paramParcel)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zza.zzap(paramParcel);
    int j = 0;
    String str = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = com.google.android.gms.common.internal.safeparcel.zza.zzao(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, k);
        break;
      case 1: 
        j = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, k);
        break;
      case 2: 
        str = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new CountrySpecification(j, str);
  }
  
  public CountrySpecification[] zzgG(int paramInt)
  {
    return new CountrySpecification[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/identity/intents/model/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */