package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zze
  implements Parcelable.Creator<PasswordSpecification>
{
  static void zza(PasswordSpecification paramPasswordSpecification, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramPasswordSpecification.zzSv, false);
    zzb.zzc(paramParcel, 1000, paramPasswordSpecification.mVersionCode);
    zzb.zzb(paramParcel, 2, paramPasswordSpecification.zzSw, false);
    zzb.zza(paramParcel, 3, paramPasswordSpecification.zzSx, false);
    zzb.zzc(paramParcel, 4, paramPasswordSpecification.zzSy);
    zzb.zzc(paramParcel, 5, paramPasswordSpecification.zzSz);
    zzb.zzI(paramParcel, i);
  }
  
  public PasswordSpecification zzI(Parcel paramParcel)
  {
    ArrayList localArrayList1 = null;
    int i = 0;
    int j = zza.zzap(paramParcel);
    int k = 0;
    ArrayList localArrayList2 = null;
    String str = null;
    int m = 0;
    while (paramParcel.dataPosition() < j)
    {
      int n = zza.zzao(paramParcel);
      switch (zza.zzbM(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        str = zza.zzp(paramParcel, n);
        break;
      case 1000: 
        m = zza.zzg(paramParcel, n);
        break;
      case 2: 
        localArrayList2 = zza.zzD(paramParcel, n);
        break;
      case 3: 
        localArrayList1 = zza.zzC(paramParcel, n);
        break;
      case 4: 
        k = zza.zzg(paramParcel, n);
        break;
      case 5: 
        i = zza.zzg(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new PasswordSpecification(m, str, localArrayList2, localArrayList1, k, i);
  }
  
  public PasswordSpecification[] zzaz(int paramInt)
  {
    return new PasswordSpecification[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/credentials/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */