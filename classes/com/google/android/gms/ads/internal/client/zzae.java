package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzae
  implements Parcelable.Creator<SearchAdRequestParcel>
{
  static void zza(SearchAdRequestParcel paramSearchAdRequestParcel, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramSearchAdRequestParcel.versionCode);
    zzb.zzc(paramParcel, 2, paramSearchAdRequestParcel.zztP);
    zzb.zzc(paramParcel, 3, paramSearchAdRequestParcel.backgroundColor);
    zzb.zzc(paramParcel, 4, paramSearchAdRequestParcel.zztQ);
    zzb.zzc(paramParcel, 5, paramSearchAdRequestParcel.zztR);
    zzb.zzc(paramParcel, 6, paramSearchAdRequestParcel.zztS);
    zzb.zzc(paramParcel, 7, paramSearchAdRequestParcel.zztT);
    zzb.zzc(paramParcel, 8, paramSearchAdRequestParcel.zztU);
    zzb.zzc(paramParcel, 9, paramSearchAdRequestParcel.zztV);
    zzb.zza(paramParcel, 10, paramSearchAdRequestParcel.zztW, false);
    zzb.zzc(paramParcel, 11, paramSearchAdRequestParcel.zztX);
    zzb.zza(paramParcel, 12, paramSearchAdRequestParcel.zztY, false);
    zzb.zzc(paramParcel, 13, paramSearchAdRequestParcel.zztZ);
    zzb.zzc(paramParcel, 14, paramSearchAdRequestParcel.zzua);
    zzb.zza(paramParcel, 15, paramSearchAdRequestParcel.zzub, false);
    zzb.zzI(paramParcel, i);
  }
  
  public SearchAdRequestParcel zzd(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    int i5 = 0;
    String str1 = null;
    int i6 = 0;
    String str2 = null;
    int i7 = 0;
    int i8 = 0;
    String str3 = null;
    while (paramParcel.dataPosition() < i)
    {
      int i9 = zza.zzao(paramParcel);
      switch (zza.zzbM(i9))
      {
      default: 
        zza.zzb(paramParcel, i9);
        break;
      case 1: 
        j = zza.zzg(paramParcel, i9);
        break;
      case 2: 
        k = zza.zzg(paramParcel, i9);
        break;
      case 3: 
        m = zza.zzg(paramParcel, i9);
        break;
      case 4: 
        n = zza.zzg(paramParcel, i9);
        break;
      case 5: 
        i1 = zza.zzg(paramParcel, i9);
        break;
      case 6: 
        i2 = zza.zzg(paramParcel, i9);
        break;
      case 7: 
        i3 = zza.zzg(paramParcel, i9);
        break;
      case 8: 
        i4 = zza.zzg(paramParcel, i9);
        break;
      case 9: 
        i5 = zza.zzg(paramParcel, i9);
        break;
      case 10: 
        str1 = zza.zzp(paramParcel, i9);
        break;
      case 11: 
        i6 = zza.zzg(paramParcel, i9);
        break;
      case 12: 
        str2 = zza.zzp(paramParcel, i9);
        break;
      case 13: 
        i7 = zza.zzg(paramParcel, i9);
        break;
      case 14: 
        i8 = zza.zzg(paramParcel, i9);
        break;
      case 15: 
        str3 = zza.zzp(paramParcel, i9);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new SearchAdRequestParcel(j, k, m, n, i1, i2, i3, i4, i5, str1, i6, str2, i7, i8, str3);
  }
  
  public SearchAdRequestParcel[] zzn(int paramInt)
  {
    return new SearchAdRequestParcel[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */