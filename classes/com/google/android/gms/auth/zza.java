package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza
  implements Parcelable.Creator<AccountChangeEvent>
{
  static void zza(AccountChangeEvent paramAccountChangeEvent, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramAccountChangeEvent.mVersion);
    zzb.zza(paramParcel, 2, paramAccountChangeEvent.zzRr);
    zzb.zza(paramParcel, 3, paramAccountChangeEvent.zzRs, false);
    zzb.zzc(paramParcel, 4, paramAccountChangeEvent.zzRt);
    zzb.zzc(paramParcel, 5, paramAccountChangeEvent.zzRu);
    zzb.zza(paramParcel, 6, paramAccountChangeEvent.zzRv, false);
    zzb.zzI(paramParcel, i);
  }
  
  public AccountChangeEvent[] zzaq(int paramInt)
  {
    return new AccountChangeEvent[paramInt];
  }
  
  public AccountChangeEvent zzz(Parcel paramParcel)
  {
    String str1 = null;
    int i = 0;
    int j = com.google.android.gms.common.internal.safeparcel.zza.zzap(paramParcel);
    long l = 0L;
    int k = 0;
    String str2 = null;
    int m = 0;
    while (paramParcel.dataPosition() < j)
    {
      int n = com.google.android.gms.common.internal.safeparcel.zza.zzao(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(n))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, n);
        break;
      case 1: 
        m = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, n);
        break;
      case 2: 
        l = com.google.android.gms.common.internal.safeparcel.zza.zzi(paramParcel, n);
        break;
      case 3: 
        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, n);
        break;
      case 4: 
        k = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, n);
        break;
      case 5: 
        i = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, n);
        break;
      case 6: 
        str1 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new AccountChangeEvent(m, l, str2, k, i, str1);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */