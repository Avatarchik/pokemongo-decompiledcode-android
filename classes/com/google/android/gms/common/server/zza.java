package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza
  implements Parcelable.Creator<FavaDiagnosticsEntity>
{
  static void zza(FavaDiagnosticsEntity paramFavaDiagnosticsEntity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramFavaDiagnosticsEntity.mVersionCode);
    zzb.zza(paramParcel, 2, paramFavaDiagnosticsEntity.zzagM, false);
    zzb.zzc(paramParcel, 3, paramFavaDiagnosticsEntity.zzagN);
    zzb.zzI(paramParcel, i);
  }
  
  public FavaDiagnosticsEntity zzar(Parcel paramParcel)
  {
    int i = 0;
    int j = com.google.android.gms.common.internal.safeparcel.zza.zzap(paramParcel);
    String str = null;
    int k = 0;
    while (paramParcel.dataPosition() < j)
    {
      int m = com.google.android.gms.common.internal.safeparcel.zza.zzao(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(m))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, m);
        break;
      case 1: 
        k = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        break;
      case 2: 
        str = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        break;
      case 3: 
        i = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new FavaDiagnosticsEntity(k, str, i);
  }
  
  public FavaDiagnosticsEntity[] zzbP(int paramInt)
  {
    return new FavaDiagnosticsEntity[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/server/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */