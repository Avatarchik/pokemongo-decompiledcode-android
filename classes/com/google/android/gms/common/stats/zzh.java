package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzh
  implements Parcelable.Creator<WakeLockEvent>
{
  static void zza(WakeLockEvent paramWakeLockEvent, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramWakeLockEvent.mVersionCode);
    zzb.zza(paramParcel, 2, paramWakeLockEvent.getTimeMillis());
    zzb.zza(paramParcel, 4, paramWakeLockEvent.zzqj(), false);
    zzb.zzc(paramParcel, 5, paramWakeLockEvent.zzql());
    zzb.zzb(paramParcel, 6, paramWakeLockEvent.zzqm(), false);
    zzb.zza(paramParcel, 8, paramWakeLockEvent.zzqf());
    zzb.zza(paramParcel, 10, paramWakeLockEvent.zzqk(), false);
    zzb.zzc(paramParcel, 11, paramWakeLockEvent.getEventType());
    zzb.zza(paramParcel, 12, paramWakeLockEvent.zzqc(), false);
    zzb.zza(paramParcel, 13, paramWakeLockEvent.zzqo(), false);
    zzb.zzc(paramParcel, 14, paramWakeLockEvent.zzqn());
    zzb.zza(paramParcel, 15, paramWakeLockEvent.zzqp());
    zzb.zza(paramParcel, 16, paramWakeLockEvent.zzqq());
    zzb.zzI(paramParcel, i);
  }
  
  public WakeLockEvent zzaB(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    long l1 = 0L;
    int k = 0;
    String str1 = null;
    int m = 0;
    ArrayList localArrayList = null;
    String str2 = null;
    long l2 = 0L;
    int n = 0;
    String str3 = null;
    String str4 = null;
    float f = 0.0F;
    long l3 = 0L;
    while (paramParcel.dataPosition() < i)
    {
      int i1 = zza.zzao(paramParcel);
      switch (zza.zzbM(i1))
      {
      case 3: 
      case 7: 
      case 9: 
      default: 
        zza.zzb(paramParcel, i1);
        break;
      case 1: 
        j = zza.zzg(paramParcel, i1);
        break;
      case 2: 
        l1 = zza.zzi(paramParcel, i1);
        break;
      case 4: 
        str1 = zza.zzp(paramParcel, i1);
        break;
      case 5: 
        m = zza.zzg(paramParcel, i1);
        break;
      case 6: 
        localArrayList = zza.zzD(paramParcel, i1);
        break;
      case 8: 
        l2 = zza.zzi(paramParcel, i1);
        break;
      case 10: 
        str3 = zza.zzp(paramParcel, i1);
        break;
      case 11: 
        k = zza.zzg(paramParcel, i1);
        break;
      case 12: 
        str2 = zza.zzp(paramParcel, i1);
        break;
      case 13: 
        str4 = zza.zzp(paramParcel, i1);
        break;
      case 14: 
        n = zza.zzg(paramParcel, i1);
        break;
      case 15: 
        f = zza.zzl(paramParcel, i1);
        break;
      case 16: 
        l3 = zza.zzi(paramParcel, i1);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new WakeLockEvent(j, l1, k, str1, m, localArrayList, str2, l2, n, str3, str4, f, l3);
  }
  
  public WakeLockEvent[] zzbZ(int paramInt)
  {
    return new WakeLockEvent[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/stats/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */