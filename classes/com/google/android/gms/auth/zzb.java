package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;

public class zzb
  implements Parcelable.Creator<AccountChangeEventsRequest>
{
  static void zza(AccountChangeEventsRequest paramAccountChangeEventsRequest, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, paramAccountChangeEventsRequest.mVersion);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 2, paramAccountChangeEventsRequest.zzRu);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 3, paramAccountChangeEventsRequest.zzRs, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 4, paramAccountChangeEventsRequest.zzQd, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzI(paramParcel, i);
  }
  
  public AccountChangeEventsRequest zzA(Parcel paramParcel)
  {
    Account localAccount = null;
    int i = 0;
    int j = zza.zzap(paramParcel);
    String str = null;
    int k = 0;
    while (paramParcel.dataPosition() < j)
    {
      int m = zza.zzao(paramParcel);
      switch (zza.zzbM(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        break;
      case 1: 
        k = zza.zzg(paramParcel, m);
        break;
      case 2: 
        i = zza.zzg(paramParcel, m);
        break;
      case 3: 
        str = zza.zzp(paramParcel, m);
        break;
      case 4: 
        localAccount = (Account)zza.zza(paramParcel, m, Account.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new AccountChangeEventsRequest(k, i, str, localAccount);
  }
  
  public AccountChangeEventsRequest[] zzar(int paramInt)
  {
    return new AccountChangeEventsRequest[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */