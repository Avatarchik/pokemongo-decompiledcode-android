package com.google.android.gms.ads.internal.reward.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh
  implements Parcelable.Creator<RewardedVideoAdRequestParcel>
{
  static void zza(RewardedVideoAdRequestParcel paramRewardedVideoAdRequestParcel, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramRewardedVideoAdRequestParcel.versionCode);
    zzb.zza(paramParcel, 2, paramRewardedVideoAdRequestParcel.zzEn, paramInt, false);
    zzb.zza(paramParcel, 3, paramRewardedVideoAdRequestParcel.zzqh, false);
    zzb.zzI(paramParcel, i);
  }
  
  public RewardedVideoAdRequestParcel[] zzJ(int paramInt)
  {
    return new RewardedVideoAdRequestParcel[paramInt];
  }
  
  public RewardedVideoAdRequestParcel zzn(Parcel paramParcel)
  {
    Object localObject1 = null;
    int i = zza.zzap(paramParcel);
    int j = 0;
    Object localObject2 = null;
    if (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      Object localObject3;
      Object localObject4;
      int m;
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        localObject3 = localObject1;
        localObject4 = localObject2;
        m = j;
      }
      for (;;)
      {
        j = m;
        localObject2 = localObject4;
        localObject1 = localObject3;
        break;
        int n = zza.zzg(paramParcel, k);
        Object localObject5 = localObject1;
        localObject4 = localObject2;
        m = n;
        localObject3 = localObject5;
        continue;
        AdRequestParcel localAdRequestParcel = (AdRequestParcel)zza.zza(paramParcel, k, AdRequestParcel.CREATOR);
        m = j;
        localObject3 = localObject1;
        localObject4 = localAdRequestParcel;
        continue;
        localObject3 = zza.zzp(paramParcel, k);
        localObject4 = localObject2;
        m = j;
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new RewardedVideoAdRequestParcel(j, (AdRequestParcel)localObject2, (String)localObject1);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/reward/client/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */