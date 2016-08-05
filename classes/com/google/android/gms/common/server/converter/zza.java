package com.google.android.gms.common.server.converter;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza
  implements Parcelable.Creator<ConverterWrapper>
{
  static void zza(ConverterWrapper paramConverterWrapper, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramConverterWrapper.getVersionCode());
    zzb.zza(paramParcel, 2, paramConverterWrapper.zzpy(), paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public ConverterWrapper zzas(Parcel paramParcel)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zza.zzap(paramParcel);
    int j = 0;
    StringToIntConverter localStringToIntConverter = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = com.google.android.gms.common.internal.safeparcel.zza.zzao(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, k);
        break;
      case 1: 
        j = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, k);
        break;
      case 2: 
        localStringToIntConverter = (StringToIntConverter)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, k, StringToIntConverter.CREATOR);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new ConverterWrapper(j, localStringToIntConverter);
  }
  
  public ConverterWrapper[] zzbQ(int paramInt)
  {
    return new ConverterWrapper[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/server/converter/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */