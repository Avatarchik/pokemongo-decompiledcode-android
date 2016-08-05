package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;

public class zzb
  implements Parcelable.Creator<CredentialPickerConfig>
{
  static void zza(CredentialPickerConfig paramCredentialPickerConfig, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 1, paramCredentialPickerConfig.shouldShowAddAccountButton());
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1000, paramCredentialPickerConfig.mVersionCode);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 2, paramCredentialPickerConfig.shouldShowCancelButton());
    com.google.android.gms.common.internal.safeparcel.zzb.zzI(paramParcel, i);
  }
  
  public CredentialPickerConfig zzF(Parcel paramParcel)
  {
    boolean bool1 = false;
    int i = zza.zzap(paramParcel);
    boolean bool2 = false;
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
        bool2 = zza.zzc(paramParcel, k);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
        break;
      case 2: 
        bool1 = zza.zzc(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new CredentialPickerConfig(j, bool2, bool1);
  }
  
  public CredentialPickerConfig[] zzaw(int paramInt)
  {
    return new CredentialPickerConfig[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/credentials/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */