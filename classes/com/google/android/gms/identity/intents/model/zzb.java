package com.google.android.gms.identity.intents.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;

public class zzb
  implements Parcelable.Creator<UserAddress>
{
  static void zza(UserAddress paramUserAddress, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, paramUserAddress.getVersionCode());
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 2, paramUserAddress.name, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 3, paramUserAddress.zzaDk, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 4, paramUserAddress.zzaDl, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 5, paramUserAddress.zzaDm, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 6, paramUserAddress.zzaDn, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 7, paramUserAddress.zzaDo, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 8, paramUserAddress.zzaDp, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 9, paramUserAddress.zzaDq, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 10, paramUserAddress.zzGw, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 11, paramUserAddress.zzaDr, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 12, paramUserAddress.zzaDs, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 13, paramUserAddress.phoneNumber, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 14, paramUserAddress.zzaDt);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 15, paramUserAddress.zzaDu, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 16, paramUserAddress.zzaDv, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzI(paramParcel, i);
  }
  
  public UserAddress zzex(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    String str1 = null;
    String str2 = null;
    String str3 = null;
    String str4 = null;
    String str5 = null;
    String str6 = null;
    String str7 = null;
    String str8 = null;
    String str9 = null;
    String str10 = null;
    String str11 = null;
    String str12 = null;
    boolean bool = false;
    String str13 = null;
    String str14 = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        j = zza.zzg(paramParcel, k);
        break;
      case 2: 
        str1 = zza.zzp(paramParcel, k);
        break;
      case 3: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 4: 
        str3 = zza.zzp(paramParcel, k);
        break;
      case 5: 
        str4 = zza.zzp(paramParcel, k);
        break;
      case 6: 
        str5 = zza.zzp(paramParcel, k);
        break;
      case 7: 
        str6 = zza.zzp(paramParcel, k);
        break;
      case 8: 
        str7 = zza.zzp(paramParcel, k);
        break;
      case 9: 
        str8 = zza.zzp(paramParcel, k);
        break;
      case 10: 
        str9 = zza.zzp(paramParcel, k);
        break;
      case 11: 
        str10 = zza.zzp(paramParcel, k);
        break;
      case 12: 
        str11 = zza.zzp(paramParcel, k);
        break;
      case 13: 
        str12 = zza.zzp(paramParcel, k);
        break;
      case 14: 
        bool = zza.zzc(paramParcel, k);
        break;
      case 15: 
        str13 = zza.zzp(paramParcel, k);
        break;
      case 16: 
        str14 = zza.zzp(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new UserAddress(j, str1, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, bool, str13, str14);
  }
  
  public UserAddress[] zzgH(int paramInt)
  {
    return new UserAddress[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/identity/intents/model/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */