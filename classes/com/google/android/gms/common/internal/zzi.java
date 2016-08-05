package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi
  implements Parcelable.Creator<GetServiceRequest>
{
  static void zza(GetServiceRequest paramGetServiceRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramGetServiceRequest.version);
    zzb.zzc(paramParcel, 2, paramGetServiceRequest.zzafq);
    zzb.zzc(paramParcel, 3, paramGetServiceRequest.zzafr);
    zzb.zza(paramParcel, 4, paramGetServiceRequest.zzafs, false);
    zzb.zza(paramParcel, 5, paramGetServiceRequest.zzaft, false);
    zzb.zza(paramParcel, 6, paramGetServiceRequest.zzafu, paramInt, false);
    zzb.zza(paramParcel, 7, paramGetServiceRequest.zzafv, false);
    zzb.zza(paramParcel, 8, paramGetServiceRequest.zzafw, paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public GetServiceRequest zzak(Parcel paramParcel)
  {
    int i = 0;
    Account localAccount = null;
    int j = zza.zzap(paramParcel);
    Bundle localBundle = null;
    Scope[] arrayOfScope = null;
    IBinder localIBinder = null;
    String str = null;
    int k = 0;
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
        k = zza.zzg(paramParcel, n);
        break;
      case 3: 
        i = zza.zzg(paramParcel, n);
        break;
      case 4: 
        str = zza.zzp(paramParcel, n);
        break;
      case 5: 
        localIBinder = zza.zzq(paramParcel, n);
        break;
      case 6: 
        arrayOfScope = (Scope[])zza.zzb(paramParcel, n, Scope.CREATOR);
        break;
      case 7: 
        localBundle = zza.zzr(paramParcel, n);
        break;
      case 8: 
        localAccount = (Account)zza.zza(paramParcel, n, Account.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new GetServiceRequest(m, k, i, str, localIBinder, arrayOfScope, localBundle, localAccount);
  }
  
  public GetServiceRequest[] zzbD(int paramInt)
  {
    return new GetServiceRequest[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */