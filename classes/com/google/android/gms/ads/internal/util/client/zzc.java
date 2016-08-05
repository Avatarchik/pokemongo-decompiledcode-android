package com.google.android.gms.ads.internal.util.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc
  implements Parcelable.Creator<VersionInfoParcel>
{
  static void zza(VersionInfoParcel paramVersionInfoParcel, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramVersionInfoParcel.versionCode);
    zzb.zza(paramParcel, 2, paramVersionInfoParcel.zzJu, false);
    zzb.zzc(paramParcel, 3, paramVersionInfoParcel.zzJv);
    zzb.zzc(paramParcel, 4, paramVersionInfoParcel.zzJw);
    zzb.zza(paramParcel, 5, paramVersionInfoParcel.zzJx);
    zzb.zzI(paramParcel, i);
  }
  
  public VersionInfoParcel[] zzO(int paramInt)
  {
    return new VersionInfoParcel[paramInt];
  }
  
  public VersionInfoParcel zzp(Parcel paramParcel)
  {
    boolean bool = false;
    int i = zza.zzap(paramParcel);
    String str = null;
    int j = 0;
    int k = 0;
    int m = 0;
    while (paramParcel.dataPosition() < i)
    {
      int n = zza.zzao(paramParcel);
      switch (zza.zzbM(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        m = zza.zzg(paramParcel, n);
        break;
      case 2: 
        str = zza.zzp(paramParcel, n);
        break;
      case 3: 
        k = zza.zzg(paramParcel, n);
        break;
      case 4: 
        j = zza.zzg(paramParcel, n);
        break;
      case 5: 
        bool = zza.zzc(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new VersionInfoParcel(m, str, k, j, bool);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/util/client/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */