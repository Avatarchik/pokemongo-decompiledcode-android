package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza
  implements Parcelable.Creator<HereContent.Action>
{
  static void zza(HereContent.Action paramAction, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramAction.getTitle(), false);
    zzb.zzc(paramParcel, 1000, paramAction.mVersionCode);
    zzb.zza(paramParcel, 2, paramAction.getUri(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public HereContent.Action zzfa(Parcel paramParcel)
  {
    String str1 = null;
    int i = com.google.android.gms.common.internal.safeparcel.zza.zzap(paramParcel);
    int j = 0;
    String str2 = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = com.google.android.gms.common.internal.safeparcel.zza.zzao(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, k);
        break;
      case 1: 
        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, k);
        break;
      case 1000: 
        j = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, k);
        break;
      case 2: 
        str1 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new HereContent.Action(j, str2, str1);
  }
  
  public HereContent.Action[] zzhy(int paramInt)
  {
    return new HereContent.Action[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/personalized/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */