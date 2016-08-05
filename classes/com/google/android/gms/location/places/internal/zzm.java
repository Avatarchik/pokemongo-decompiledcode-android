package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzm
  implements Parcelable.Creator<PlaceLikelihoodEntity>
{
  static void zza(PlaceLikelihoodEntity paramPlaceLikelihoodEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramPlaceLikelihoodEntity.zzaHA, paramInt, false);
    zzb.zzc(paramParcel, 1000, paramPlaceLikelihoodEntity.mVersionCode);
    zzb.zza(paramParcel, 2, paramPlaceLikelihoodEntity.zzaHB);
    zzb.zzI(paramParcel, i);
  }
  
  public PlaceLikelihoodEntity zzeW(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    Object localObject1 = null;
    float f1 = 0.0F;
    if (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      float f2;
      Object localObject2;
      int m;
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        f2 = f1;
        localObject2 = localObject1;
        m = j;
      }
      for (;;)
      {
        j = m;
        localObject1 = localObject2;
        f1 = f2;
        break;
        PlaceImpl localPlaceImpl = (PlaceImpl)zza.zza(paramParcel, k, PlaceImpl.CREATOR);
        m = j;
        f2 = f1;
        localObject2 = localPlaceImpl;
        continue;
        int n = zza.zzg(paramParcel, k);
        float f3 = f1;
        localObject2 = localObject1;
        m = n;
        f2 = f3;
        continue;
        f2 = zza.zzl(paramParcel, k);
        localObject2 = localObject1;
        m = j;
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new PlaceLikelihoodEntity(j, (PlaceImpl)localObject1, f1);
  }
  
  public PlaceLikelihoodEntity[] zzhu(int paramInt)
  {
    return new PlaceLikelihoodEntity[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */