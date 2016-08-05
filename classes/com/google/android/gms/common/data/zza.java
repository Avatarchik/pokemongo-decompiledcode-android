package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza
  implements Parcelable.Creator<BitmapTeleporter>
{
  static void zza(BitmapTeleporter paramBitmapTeleporter, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramBitmapTeleporter.mVersionCode);
    zzb.zza(paramParcel, 2, paramBitmapTeleporter.zzFc, paramInt, false);
    zzb.zzc(paramParcel, 3, paramBitmapTeleporter.zzWJ);
    zzb.zzI(paramParcel, i);
  }
  
  public BitmapTeleporter zzaf(Parcel paramParcel)
  {
    int i = 0;
    int j = com.google.android.gms.common.internal.safeparcel.zza.zzap(paramParcel);
    Object localObject1 = null;
    int k = 0;
    if (paramParcel.dataPosition() < j)
    {
      int m = com.google.android.gms.common.internal.safeparcel.zza.zzao(paramParcel);
      int n;
      Object localObject2;
      int i1;
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(m))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, m);
        n = i;
        localObject2 = localObject1;
        i1 = k;
      }
      for (;;)
      {
        k = i1;
        localObject1 = localObject2;
        i = n;
        break;
        int i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        int i3 = i;
        localObject2 = localObject1;
        i1 = i2;
        n = i3;
        continue;
        ParcelFileDescriptor localParcelFileDescriptor = (ParcelFileDescriptor)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, m, ParcelFileDescriptor.CREATOR);
        i1 = k;
        n = i;
        localObject2 = localParcelFileDescriptor;
        continue;
        n = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        localObject2 = localObject1;
        i1 = k;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new BitmapTeleporter(k, (ParcelFileDescriptor)localObject1, i);
  }
  
  public BitmapTeleporter[] zzbq(int paramInt)
  {
    return new BitmapTeleporter[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/data/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */