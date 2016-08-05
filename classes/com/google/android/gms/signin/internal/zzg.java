package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzg
  implements Parcelable.Creator<RecordConsentRequest>
{
  static void zza(RecordConsentRequest paramRecordConsentRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramRecordConsentRequest.mVersionCode);
    zzb.zza(paramParcel, 2, paramRecordConsentRequest.getAccount(), paramInt, false);
    zzb.zza(paramParcel, 3, paramRecordConsentRequest.zzCj(), paramInt, false);
    zzb.zza(paramParcel, 4, paramRecordConsentRequest.zzmb(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public RecordConsentRequest zzgD(Parcel paramParcel)
  {
    Object localObject1 = null;
    int i = zza.zzap(paramParcel);
    int j = 0;
    Object localObject2 = null;
    Object localObject3 = null;
    if (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      Object localObject4;
      Object localObject5;
      Object localObject6;
      int m;
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        localObject4 = localObject1;
        localObject5 = localObject2;
        localObject6 = localObject3;
        m = j;
      }
      for (;;)
      {
        j = m;
        localObject3 = localObject6;
        localObject2 = localObject5;
        localObject1 = localObject4;
        break;
        int n = zza.zzg(paramParcel, k);
        Object localObject9 = localObject1;
        localObject5 = localObject2;
        localObject6 = localObject3;
        m = n;
        localObject4 = localObject9;
        continue;
        Account localAccount = (Account)zza.zza(paramParcel, k, Account.CREATOR);
        m = j;
        Object localObject8 = localObject2;
        localObject6 = localAccount;
        localObject4 = localObject1;
        localObject5 = localObject8;
        continue;
        Scope[] arrayOfScope = (Scope[])zza.zzb(paramParcel, k, Scope.CREATOR);
        localObject6 = localObject3;
        m = j;
        Object localObject7 = localObject1;
        localObject5 = arrayOfScope;
        localObject4 = localObject7;
        continue;
        localObject4 = zza.zzp(paramParcel, k);
        localObject5 = localObject2;
        localObject6 = localObject3;
        m = j;
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new RecordConsentRequest(j, (Account)localObject3, (Scope[])localObject2, (String)localObject1);
  }
  
  public RecordConsentRequest[] zzjr(int paramInt)
  {
    return new RecordConsentRequest[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/signin/internal/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */