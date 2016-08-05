package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzc
  implements Parcelable.Creator<CheckServerAuthResult>
{
  static void zza(CheckServerAuthResult paramCheckServerAuthResult, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramCheckServerAuthResult.mVersionCode);
    zzb.zza(paramParcel, 2, paramCheckServerAuthResult.zzaVi);
    zzb.zzc(paramParcel, 3, paramCheckServerAuthResult.zzaVj, false);
    zzb.zzI(paramParcel, i);
  }
  
  public CheckServerAuthResult zzgC(Parcel paramParcel)
  {
    boolean bool = false;
    int i = zza.zzap(paramParcel);
    ArrayList localArrayList = null;
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
        bool = zza.zzc(paramParcel, k);
        break;
      case 3: 
        localArrayList = zza.zzc(paramParcel, k, Scope.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new CheckServerAuthResult(j, bool, localArrayList);
  }
  
  public CheckServerAuthResult[] zzjp(int paramInt)
  {
    return new CheckServerAuthResult[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/signin/internal/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */