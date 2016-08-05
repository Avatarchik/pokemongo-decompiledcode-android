package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class DetectedActivityCreator
  implements Parcelable.Creator<DetectedActivity>
{
  public static final int CONTENT_DESCRIPTION;
  
  static void zza(DetectedActivity paramDetectedActivity, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramDetectedActivity.zzaEg);
    zzb.zzc(paramParcel, 1000, paramDetectedActivity.getVersionCode());
    zzb.zzc(paramParcel, 2, paramDetectedActivity.zzaEh);
    zzb.zzI(paramParcel, i);
  }
  
  public DetectedActivity createFromParcel(Parcel paramParcel)
  {
    int i = 0;
    int j = zza.zzap(paramParcel);
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
        k = zza.zzg(paramParcel, n);
        break;
      case 1000: 
        m = zza.zzg(paramParcel, n);
        break;
      case 2: 
        i = zza.zzg(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new DetectedActivity(m, k, i);
  }
  
  public DetectedActivity[] newArray(int paramInt)
  {
    return new DetectedActivity[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/DetectedActivityCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */