package com.google.android.gms.ads.internal.client;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public class zzg
  implements Parcelable.Creator<AdRequestParcel>
{
  static void zza(AdRequestParcel paramAdRequestParcel, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramAdRequestParcel.versionCode);
    zzb.zza(paramParcel, 2, paramAdRequestParcel.zzsB);
    zzb.zza(paramParcel, 3, paramAdRequestParcel.extras, false);
    zzb.zzc(paramParcel, 4, paramAdRequestParcel.zzsC);
    zzb.zzb(paramParcel, 5, paramAdRequestParcel.zzsD, false);
    zzb.zza(paramParcel, 6, paramAdRequestParcel.zzsE);
    zzb.zzc(paramParcel, 7, paramAdRequestParcel.zzsF);
    zzb.zza(paramParcel, 8, paramAdRequestParcel.zzsG);
    zzb.zza(paramParcel, 9, paramAdRequestParcel.zzsH, false);
    zzb.zza(paramParcel, 10, paramAdRequestParcel.zzsI, paramInt, false);
    zzb.zza(paramParcel, 11, paramAdRequestParcel.zzsJ, paramInt, false);
    zzb.zza(paramParcel, 12, paramAdRequestParcel.zzsK, false);
    zzb.zza(paramParcel, 13, paramAdRequestParcel.zzsL, false);
    zzb.zza(paramParcel, 14, paramAdRequestParcel.zzsM, false);
    zzb.zzb(paramParcel, 15, paramAdRequestParcel.zzsN, false);
    zzb.zza(paramParcel, 17, paramAdRequestParcel.zzsP, false);
    zzb.zza(paramParcel, 16, paramAdRequestParcel.zzsO, false);
    zzb.zzI(paramParcel, i);
  }
  
  public AdRequestParcel zzb(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    long l = 0L;
    Bundle localBundle1 = null;
    int k = 0;
    ArrayList localArrayList1 = null;
    boolean bool1 = false;
    int m = 0;
    boolean bool2 = false;
    String str1 = null;
    SearchAdRequestParcel localSearchAdRequestParcel = null;
    Location localLocation = null;
    String str2 = null;
    Bundle localBundle2 = null;
    Bundle localBundle3 = null;
    ArrayList localArrayList2 = null;
    String str3 = null;
    String str4 = null;
    while (paramParcel.dataPosition() < i)
    {
      int n = zza.zzao(paramParcel);
      switch (zza.zzbM(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        j = zza.zzg(paramParcel, n);
        break;
      case 2: 
        l = zza.zzi(paramParcel, n);
        break;
      case 3: 
        localBundle1 = zza.zzr(paramParcel, n);
        break;
      case 4: 
        k = zza.zzg(paramParcel, n);
        break;
      case 5: 
        localArrayList1 = zza.zzD(paramParcel, n);
        break;
      case 6: 
        bool1 = zza.zzc(paramParcel, n);
        break;
      case 7: 
        m = zza.zzg(paramParcel, n);
        break;
      case 8: 
        bool2 = zza.zzc(paramParcel, n);
        break;
      case 9: 
        str1 = zza.zzp(paramParcel, n);
        break;
      case 10: 
        localSearchAdRequestParcel = (SearchAdRequestParcel)zza.zza(paramParcel, n, SearchAdRequestParcel.CREATOR);
        break;
      case 11: 
        localLocation = (Location)zza.zza(paramParcel, n, Location.CREATOR);
        break;
      case 12: 
        str2 = zza.zzp(paramParcel, n);
        break;
      case 13: 
        localBundle2 = zza.zzr(paramParcel, n);
        break;
      case 14: 
        localBundle3 = zza.zzr(paramParcel, n);
        break;
      case 15: 
        localArrayList2 = zza.zzD(paramParcel, n);
        break;
      case 17: 
        str4 = zza.zzp(paramParcel, n);
        break;
      case 16: 
        str3 = zza.zzp(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new AdRequestParcel(j, l, localBundle1, k, localArrayList1, bool1, m, bool2, str1, localSearchAdRequestParcel, localLocation, str2, localBundle2, localBundle3, localArrayList2, str3, str4);
  }
  
  public AdRequestParcel[] zzk(int paramInt)
  {
    return new AdRequestParcel[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */