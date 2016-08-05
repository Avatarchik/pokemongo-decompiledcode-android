package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi
  implements Parcelable.Creator<PlacePhotoResult>
{
  static void zza(PlacePhotoResult paramPlacePhotoResult, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramPlacePhotoResult.getStatus(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramPlacePhotoResult.mVersionCode);
    zzb.zza(paramParcel, 2, paramPlacePhotoResult.zzaGs, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public PlacePhotoResult zzeQ(Parcel paramParcel)
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
        Status localStatus = (Status)zza.zza(paramParcel, k, Status.CREATOR);
        m = j;
        localObject3 = localObject1;
        localObject4 = localStatus;
        continue;
        int n = zza.zzg(paramParcel, k);
        Object localObject5 = localObject1;
        localObject4 = localObject2;
        m = n;
        localObject3 = localObject5;
        continue;
        localObject3 = (BitmapTeleporter)zza.zza(paramParcel, k, BitmapTeleporter.CREATOR);
        localObject4 = localObject2;
        m = j;
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new PlacePhotoResult(j, (Status)localObject2, (BitmapTeleporter)localObject1);
  }
  
  public PlacePhotoResult[] zzhm(int paramInt)
  {
    return new PlacePhotoResult[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */