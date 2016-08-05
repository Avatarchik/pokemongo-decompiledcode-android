package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzo
  implements Parcelable.Creator<ParcelableGeofence>
{
  static void zza(ParcelableGeofence paramParcelableGeofence, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramParcelableGeofence.getRequestId(), false);
    zzb.zzc(paramParcel, 1000, paramParcelableGeofence.getVersionCode());
    zzb.zza(paramParcel, 2, paramParcelableGeofence.getExpirationTime());
    zzb.zza(paramParcel, 3, paramParcelableGeofence.zzwI());
    zzb.zza(paramParcel, 4, paramParcelableGeofence.getLatitude());
    zzb.zza(paramParcel, 5, paramParcelableGeofence.getLongitude());
    zzb.zza(paramParcel, 6, paramParcelableGeofence.zzwJ());
    zzb.zzc(paramParcel, 7, paramParcelableGeofence.zzwK());
    zzb.zzc(paramParcel, 8, paramParcelableGeofence.getNotificationResponsiveness());
    zzb.zzc(paramParcel, 9, paramParcelableGeofence.zzwL());
    zzb.zzI(paramParcel, i);
  }
  
  public ParcelableGeofence zzeJ(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    String str = null;
    int k = 0;
    short s = 0;
    double d1 = 0.0D;
    double d2 = 0.0D;
    float f = 0.0F;
    long l = 0L;
    int m = 0;
    int n = -1;
    while (paramParcel.dataPosition() < i)
    {
      int i1 = zza.zzao(paramParcel);
      switch (zza.zzbM(i1))
      {
      default: 
        zza.zzb(paramParcel, i1);
        break;
      case 1: 
        str = zza.zzp(paramParcel, i1);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, i1);
        break;
      case 2: 
        l = zza.zzi(paramParcel, i1);
        break;
      case 3: 
        s = zza.zzf(paramParcel, i1);
        break;
      case 4: 
        d1 = zza.zzn(paramParcel, i1);
        break;
      case 5: 
        d2 = zza.zzn(paramParcel, i1);
        break;
      case 6: 
        f = zza.zzl(paramParcel, i1);
        break;
      case 7: 
        k = zza.zzg(paramParcel, i1);
        break;
      case 8: 
        m = zza.zzg(paramParcel, i1);
        break;
      case 9: 
        n = zza.zzg(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new ParcelableGeofence(j, str, k, s, d1, d2, f, l, m, n);
  }
  
  public ParcelableGeofence[] zzhe(int paramInt)
  {
    return new ParcelableGeofence[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/internal/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */