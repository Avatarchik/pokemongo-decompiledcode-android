package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc
  implements Parcelable.Creator<CredentialRequest>
{
  static void zza(CredentialRequest paramCredentialRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramCredentialRequest.getSupportsPasswordLogin());
    zzb.zzc(paramParcel, 1000, paramCredentialRequest.mVersionCode);
    zzb.zza(paramParcel, 2, paramCredentialRequest.getAccountTypes(), false);
    zzb.zza(paramParcel, 3, paramCredentialRequest.getCredentialPickerConfig(), paramInt, false);
    zzb.zza(paramParcel, 4, paramCredentialRequest.getCredentialHintPickerConfig(), paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public CredentialRequest zzG(Parcel paramParcel)
  {
    boolean bool = false;
    CredentialPickerConfig localCredentialPickerConfig1 = null;
    int i = zza.zzap(paramParcel);
    CredentialPickerConfig localCredentialPickerConfig2 = null;
    String[] arrayOfString = null;
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
        bool = zza.zzc(paramParcel, k);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
        break;
      case 2: 
        arrayOfString = zza.zzB(paramParcel, k);
        break;
      case 3: 
        localCredentialPickerConfig2 = (CredentialPickerConfig)zza.zza(paramParcel, k, CredentialPickerConfig.CREATOR);
        break;
      case 4: 
        localCredentialPickerConfig1 = (CredentialPickerConfig)zza.zza(paramParcel, k, CredentialPickerConfig.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new CredentialRequest(j, bool, arrayOfString, localCredentialPickerConfig2, localCredentialPickerConfig1);
  }
  
  public CredentialRequest[] zzax(int paramInt)
  {
    return new CredentialRequest[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/credentials/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */