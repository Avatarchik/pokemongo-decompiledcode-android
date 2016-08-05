package com.google.android.gms.ads.internal.overlay;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.InterstitialAdParameterParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzf
  implements Parcelable.Creator<AdOverlayInfoParcel>
{
  static void zza(AdOverlayInfoParcel paramAdOverlayInfoParcel, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramAdOverlayInfoParcel.versionCode);
    zzb.zza(paramParcel, 2, paramAdOverlayInfoParcel.zzBA, paramInt, false);
    zzb.zza(paramParcel, 3, paramAdOverlayInfoParcel.zzeK(), false);
    zzb.zza(paramParcel, 4, paramAdOverlayInfoParcel.zzeL(), false);
    zzb.zza(paramParcel, 5, paramAdOverlayInfoParcel.zzeM(), false);
    zzb.zza(paramParcel, 6, paramAdOverlayInfoParcel.zzeN(), false);
    zzb.zza(paramParcel, 7, paramAdOverlayInfoParcel.zzBF, false);
    zzb.zza(paramParcel, 8, paramAdOverlayInfoParcel.zzBG);
    zzb.zza(paramParcel, 9, paramAdOverlayInfoParcel.zzBH, false);
    zzb.zza(paramParcel, 10, paramAdOverlayInfoParcel.zzeP(), false);
    zzb.zzc(paramParcel, 11, paramAdOverlayInfoParcel.orientation);
    zzb.zzc(paramParcel, 12, paramAdOverlayInfoParcel.zzBJ);
    zzb.zza(paramParcel, 13, paramAdOverlayInfoParcel.url, false);
    zzb.zza(paramParcel, 14, paramAdOverlayInfoParcel.zzqj, paramInt, false);
    zzb.zza(paramParcel, 15, paramAdOverlayInfoParcel.zzeO(), false);
    zzb.zza(paramParcel, 17, paramAdOverlayInfoParcel.zzBM, paramInt, false);
    zzb.zza(paramParcel, 16, paramAdOverlayInfoParcel.zzBL, false);
    zzb.zzI(paramParcel, i);
  }
  
  public AdOverlayInfoParcel zzg(Parcel paramParcel)
  {
    int i = zza.zzap(paramParcel);
    int j = 0;
    AdLauncherIntentInfoParcel localAdLauncherIntentInfoParcel = null;
    IBinder localIBinder1 = null;
    IBinder localIBinder2 = null;
    IBinder localIBinder3 = null;
    IBinder localIBinder4 = null;
    String str1 = null;
    boolean bool = false;
    String str2 = null;
    IBinder localIBinder5 = null;
    int k = 0;
    int m = 0;
    String str3 = null;
    VersionInfoParcel localVersionInfoParcel = null;
    IBinder localIBinder6 = null;
    String str4 = null;
    InterstitialAdParameterParcel localInterstitialAdParameterParcel = null;
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
        localAdLauncherIntentInfoParcel = (AdLauncherIntentInfoParcel)zza.zza(paramParcel, n, AdLauncherIntentInfoParcel.CREATOR);
        break;
      case 3: 
        localIBinder1 = zza.zzq(paramParcel, n);
        break;
      case 4: 
        localIBinder2 = zza.zzq(paramParcel, n);
        break;
      case 5: 
        localIBinder3 = zza.zzq(paramParcel, n);
        break;
      case 6: 
        localIBinder4 = zza.zzq(paramParcel, n);
        break;
      case 7: 
        str1 = zza.zzp(paramParcel, n);
        break;
      case 8: 
        bool = zza.zzc(paramParcel, n);
        break;
      case 9: 
        str2 = zza.zzp(paramParcel, n);
        break;
      case 10: 
        localIBinder5 = zza.zzq(paramParcel, n);
        break;
      case 11: 
        k = zza.zzg(paramParcel, n);
        break;
      case 12: 
        m = zza.zzg(paramParcel, n);
        break;
      case 13: 
        str3 = zza.zzp(paramParcel, n);
        break;
      case 14: 
        localVersionInfoParcel = (VersionInfoParcel)zza.zza(paramParcel, n, VersionInfoParcel.CREATOR);
        break;
      case 15: 
        localIBinder6 = zza.zzq(paramParcel, n);
        break;
      case 17: 
        localInterstitialAdParameterParcel = (InterstitialAdParameterParcel)zza.zza(paramParcel, n, InterstitialAdParameterParcel.CREATOR);
        break;
      case 16: 
        str4 = zza.zzp(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new AdOverlayInfoParcel(j, localAdLauncherIntentInfoParcel, localIBinder1, localIBinder2, localIBinder3, localIBinder4, str1, bool, str2, localIBinder5, k, m, str3, localVersionInfoParcel, localIBinder6, str4, localInterstitialAdParameterParcel);
  }
  
  public AdOverlayInfoParcel[] zzw(int paramInt)
  {
    return new AdOverlayInfoParcel[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/overlay/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */