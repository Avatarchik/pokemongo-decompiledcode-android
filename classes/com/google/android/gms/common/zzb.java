package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;

public class zzb
  implements Parcelable.Creator<ConnectionResult>
{
  static void zza(ConnectionResult paramConnectionResult, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, paramConnectionResult.mVersionCode);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 2, paramConnectionResult.getErrorCode());
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 3, paramConnectionResult.getResolution(), paramInt, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 4, paramConnectionResult.getErrorMessage(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzI(paramParcel, i);
  }
  
  public ConnectionResult zzac(Parcel paramParcel)
  {
    Object localObject1 = null;
    int i = 0;
    int j = zza.zzap(paramParcel);
    Object localObject2 = null;
    int k = 0;
    if (paramParcel.dataPosition() < j)
    {
      int m = zza.zzao(paramParcel);
      Object localObject3;
      Object localObject4;
      int n;
      int i1;
      switch (zza.zzbM(m))
      {
      default: 
        zza.zzb(paramParcel, m);
        localObject3 = localObject1;
        localObject4 = localObject2;
        n = i;
        i1 = k;
      }
      for (;;)
      {
        k = i1;
        i = n;
        localObject2 = localObject4;
        localObject1 = localObject3;
        break;
        int i3 = zza.zzg(paramParcel, m);
        Object localObject7 = localObject1;
        localObject4 = localObject2;
        n = i;
        i1 = i3;
        localObject3 = localObject7;
        continue;
        int i2 = zza.zzg(paramParcel, m);
        i1 = k;
        Object localObject6 = localObject2;
        n = i2;
        localObject3 = localObject1;
        localObject4 = localObject6;
        continue;
        PendingIntent localPendingIntent = (PendingIntent)zza.zza(paramParcel, m, PendingIntent.CREATOR);
        n = i;
        i1 = k;
        Object localObject5 = localObject1;
        localObject4 = localPendingIntent;
        localObject3 = localObject5;
        continue;
        localObject3 = zza.zzp(paramParcel, m);
        localObject4 = localObject2;
        n = i;
        i1 = k;
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new ConnectionResult(k, i, (PendingIntent)localObject2, (String)localObject1);
  }
  
  public ConnectionResult[] zzbh(int paramInt)
  {
    return new ConnectionResult[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */