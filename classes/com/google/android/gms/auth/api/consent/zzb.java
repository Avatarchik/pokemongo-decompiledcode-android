package com.google.android.gms.auth.api.consent;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.firstparty.shared.ScopeDetail;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;

public class zzb
  implements Parcelable.Creator<GetConsentIntentRequest>
{
  static void zza(GetConsentIntentRequest paramGetConsentIntentRequest, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, paramGetConsentIntentRequest.getVersionCode());
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 2, paramGetConsentIntentRequest.getCallingPackage(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 3, paramGetConsentIntentRequest.getCallingUid());
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 4, paramGetConsentIntentRequest.zzlF(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 5, paramGetConsentIntentRequest.getAccount(), paramInt, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 6, paramGetConsentIntentRequest.zzSe, paramInt, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 7, paramGetConsentIntentRequest.zzlG());
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 8, paramGetConsentIntentRequest.zzlH());
    com.google.android.gms.common.internal.safeparcel.zzb.zzI(paramParcel, i);
  }
  
  public GetConsentIntentRequest zzD(Parcel paramParcel)
  {
    ScopeDetail[] arrayOfScopeDetail = null;
    int i = 0;
    int j = zza.zzap(paramParcel);
    boolean bool = false;
    Account localAccount = null;
    String str1 = null;
    int k = 0;
    String str2 = null;
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
        m = zza.zzg(paramParcel, n);
        break;
      case 2: 
        str2 = zza.zzp(paramParcel, n);
        break;
      case 3: 
        k = zza.zzg(paramParcel, n);
        break;
      case 4: 
        str1 = zza.zzp(paramParcel, n);
        break;
      case 5: 
        localAccount = (Account)zza.zza(paramParcel, n, Account.CREATOR);
        break;
      case 6: 
        arrayOfScopeDetail = (ScopeDetail[])zza.zzb(paramParcel, n, ScopeDetail.CREATOR);
        break;
      case 7: 
        bool = zza.zzc(paramParcel, n);
        break;
      case 8: 
        i = zza.zzg(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new GetConsentIntentRequest(m, str2, k, str1, localAccount, arrayOfScopeDetail, bool, i);
  }
  
  public GetConsentIntentRequest[] zzau(int paramInt)
  {
    return new GetConsentIntentRequest[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/consent/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */