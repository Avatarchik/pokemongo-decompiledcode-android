package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza
  implements Parcelable.Creator<ConnectionEvent>
{
  static void zza(ConnectionEvent paramConnectionEvent, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramConnectionEvent.mVersionCode);
    zzb.zza(paramParcel, 2, paramConnectionEvent.getTimeMillis());
    zzb.zza(paramParcel, 4, paramConnectionEvent.zzpX(), false);
    zzb.zza(paramParcel, 5, paramConnectionEvent.zzpY(), false);
    zzb.zza(paramParcel, 6, paramConnectionEvent.zzpZ(), false);
    zzb.zza(paramParcel, 7, paramConnectionEvent.zzqa(), false);
    zzb.zza(paramParcel, 8, paramConnectionEvent.zzqb(), false);
    zzb.zza(paramParcel, 10, paramConnectionEvent.zzqf());
    zzb.zza(paramParcel, 11, paramConnectionEvent.zzqe());
    zzb.zzc(paramParcel, 12, paramConnectionEvent.getEventType());
    zzb.zza(paramParcel, 13, paramConnectionEvent.zzqc(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public ConnectionEvent zzaA(Parcel paramParcel)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zza.zzap(paramParcel);
    int j = 0;
    long l1 = 0L;
    int k = 0;
    String str1 = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    String str6 = null;
    long l2 = 0L;
    long l3 = 0L;
    while (paramParcel.dataPosition() < i)
    {
      int m = com.google.android.gms.common.internal.safeparcel.zza.zzao(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(m))
      {
      case 3: 
      case 9: 
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, m);
        break;
      case 1: 
        j = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        break;
      case 2: 
        l1 = com.google.android.gms.common.internal.safeparcel.zza.zzi(paramParcel, m);
        break;
      case 4: 
        str1 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        break;
      case 5: 
        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        break;
      case 6: 
        str3 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        break;
      case 7: 
        str4 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        break;
      case 8: 
        str5 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        break;
      case 10: 
        l2 = com.google.android.gms.common.internal.safeparcel.zza.zzi(paramParcel, m);
        break;
      case 11: 
        l3 = com.google.android.gms.common.internal.safeparcel.zza.zzi(paramParcel, m);
        break;
      case 12: 
        k = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        break;
      case 13: 
        str6 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new ConnectionEvent(j, l1, k, str1, str2, str3, str4, str5, str6, l2, l3);
  }
  
  public ConnectionEvent[] zzbY(int paramInt)
  {
    return new ConnectionEvent[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/stats/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */