package com.google.android.gms.auth.api.credentials.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi
  implements Parcelable.Creator<SaveRequest>
{
  static void zza(SaveRequest paramSaveRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramSaveRequest.getCredential(), paramInt, false);
    zzb.zzc(paramParcel, 1000, paramSaveRequest.mVersionCode);
    zzb.zzI(paramParcel, i);
  }
  
  public SaveRequest zzK(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    Credential localCredential = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        localCredential = (Credential)zza.zza(paramParcel, k, Credential.CREATOR);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new SaveRequest(j, localCredential);
  }
  
  public SaveRequest[] zzaB(int paramInt)
  {
    return new SaveRequest[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/credentials/internal/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */