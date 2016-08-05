package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzd
  implements Parcelable.Creator<Status>
{
  static void zza(Status paramStatus, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramStatus.getStatusCode());
    zzb.zzc(paramParcel, 1000, paramStatus.getVersionCode());
    zzb.zza(paramParcel, 2, paramStatus.getStatusMessage(), false);
    zzb.zza(paramParcel, 3, paramStatus.zznH(), paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public Status zzae(Parcel paramParcel)
  {
    PendingIntent localPendingIntent = null;
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
        i = zza.zzg(paramParcel, m);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, m);
        break;
      case 2: 
        str = zza.zzp(paramParcel, m);
        break;
      case 3: 
        localPendingIntent = (PendingIntent)zza.zza(paramParcel, m, PendingIntent.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new Status(k, i, str, localPendingIntent);
  }
  
  public Status[] zzbm(int paramInt)
  {
    return new Status[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/api/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */