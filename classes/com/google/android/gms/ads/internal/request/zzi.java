package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi
  implements Parcelable.Creator<CapabilityParcel>
{
  static void zza(CapabilityParcel paramCapabilityParcel, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramCapabilityParcel.versionCode);
    zzb.zza(paramParcel, 2, paramCapabilityParcel.zzFa);
    zzb.zza(paramParcel, 3, paramCapabilityParcel.zzFb);
    zzb.zzI(paramParcel, i);
  }
  
  public CapabilityParcel[] zzF(int paramInt)
  {
    return new CapabilityParcel[paramInt];
  }
  
  public CapabilityParcel zzk(Parcel paramParcel)
  {
    boolean bool1 = false;
    int i = zza.zzap(paramParcel);
    boolean bool2 = false;
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
        j = zza.zzg(paramParcel, k);
        break;
      case 2: 
        bool2 = zza.zzc(paramParcel, k);
        break;
      case 3: 
        bool1 = zza.zzc(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new CapabilityParcel(j, bool2, bool1);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/request/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */