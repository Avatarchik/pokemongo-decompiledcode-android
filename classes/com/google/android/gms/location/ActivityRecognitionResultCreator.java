package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class ActivityRecognitionResultCreator
  implements Parcelable.Creator<ActivityRecognitionResult>
{
  public static final int CONTENT_DESCRIPTION;
  
  static void zza(ActivityRecognitionResult paramActivityRecognitionResult, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramActivityRecognitionResult.zzaEb, false);
    zzb.zzc(paramParcel, 1000, paramActivityRecognitionResult.getVersionCode());
    zzb.zza(paramParcel, 2, paramActivityRecognitionResult.zzaEc);
    zzb.zza(paramParcel, 3, paramActivityRecognitionResult.zzaEd);
    zzb.zzc(paramParcel, 4, paramActivityRecognitionResult.zzaEe);
    zzb.zzI(paramParcel, i);
  }
  
  public ActivityRecognitionResult createFromParcel(Parcel paramParcel)
  {
    long l1 = 0L;
    int i = 0;
    int j = zza.zzap(paramParcel);
    ArrayList localArrayList = null;
    long l2 = l1;
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
        localArrayList = zza.zzc(paramParcel, m, DetectedActivity.CREATOR);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, m);
        break;
      case 2: 
        l2 = zza.zzi(paramParcel, m);
        break;
      case 3: 
        l1 = zza.zzi(paramParcel, m);
        break;
      case 4: 
        i = zza.zzg(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new ActivityRecognitionResult(k, localArrayList, l2, l1, i);
  }
  
  public ActivityRecognitionResult[] newArray(int paramInt)
  {
    return new ActivityRecognitionResult[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/ActivityRecognitionResultCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */