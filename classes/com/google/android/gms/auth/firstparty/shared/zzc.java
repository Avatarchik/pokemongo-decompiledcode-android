package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzc
  implements Parcelable.Creator<ScopeDetail>
{
  static void zza(ScopeDetail paramScopeDetail, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramScopeDetail.version);
    zzb.zza(paramParcel, 2, paramScopeDetail.description, false);
    zzb.zza(paramParcel, 3, paramScopeDetail.zzTH, false);
    zzb.zza(paramParcel, 4, paramScopeDetail.zzTI, false);
    zzb.zza(paramParcel, 5, paramScopeDetail.zzTJ, false);
    zzb.zza(paramParcel, 6, paramScopeDetail.zzTK, false);
    zzb.zzb(paramParcel, 7, paramScopeDetail.zzTL, false);
    zzb.zza(paramParcel, 8, paramScopeDetail.zzTM, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public ScopeDetail zzV(Parcel paramParcel)
  {
    FACLData localFACLData = null;
    int i = zza.zzap(paramParcel);
    int j = 0;
    ArrayList localArrayList = new ArrayList();
    String str1 = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
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
        str5 = zza.zzp(paramParcel, k);
        break;
      case 3: 
        str4 = zza.zzp(paramParcel, k);
        break;
      case 4: 
        str3 = zza.zzp(paramParcel, k);
        break;
      case 5: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 6: 
        str1 = zza.zzp(paramParcel, k);
        break;
      case 7: 
        localArrayList = zza.zzD(paramParcel, k);
        break;
      case 8: 
        localFACLData = (FACLData)zza.zza(paramParcel, k, FACLData.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new ScopeDetail(j, str5, str4, str3, str2, str1, localArrayList, localFACLData);
  }
  
  public ScopeDetail[] zzaM(int paramInt)
  {
    return new ScopeDetail[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/firstparty/shared/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */