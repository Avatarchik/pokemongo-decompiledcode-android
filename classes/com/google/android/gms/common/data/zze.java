package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze
  implements Parcelable.Creator<DataHolder>
{
  static void zza(DataHolder paramDataHolder, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramDataHolder.zzow(), false);
    zzb.zzc(paramParcel, 1000, paramDataHolder.getVersionCode());
    zzb.zza(paramParcel, 2, paramDataHolder.zzox(), paramInt, false);
    zzb.zzc(paramParcel, 3, paramDataHolder.getStatusCode());
    zzb.zza(paramParcel, 4, paramDataHolder.zzor(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public DataHolder zzag(Parcel paramParcel)
  {
    int i = 0;
    Bundle localBundle = null;
    int j = zza.zzap(paramParcel);
    CursorWindow[] arrayOfCursorWindow = null;
    String[] arrayOfString = null;
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
        arrayOfString = zza.zzB(paramParcel, m);
        break;
      case 1000: 
        k = zza.zzg(paramParcel, m);
        break;
      case 2: 
        arrayOfCursorWindow = (CursorWindow[])zza.zzb(paramParcel, m, CursorWindow.CREATOR);
        break;
      case 3: 
        i = zza.zzg(paramParcel, m);
        break;
      case 4: 
        localBundle = zza.zzr(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    DataHolder localDataHolder = new DataHolder(k, arrayOfString, arrayOfCursorWindow, i, localBundle);
    localDataHolder.zzov();
    return localDataHolder;
  }
  
  public DataHolder[] zzbv(int paramInt)
  {
    return new DataHolder[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/data/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */