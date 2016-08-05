package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc
  implements Parcelable.Creator<ProxyResponse>
{
  static void zza(ProxyResponse paramProxyResponse, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramProxyResponse.googlePlayServicesStatusCode);
    zzb.zzc(paramParcel, 1000, paramProxyResponse.versionCode);
    zzb.zza(paramParcel, 2, paramProxyResponse.recoveryAction, paramInt, false);
    zzb.zzc(paramParcel, 3, paramProxyResponse.statusCode);
    zzb.zza(paramParcel, 4, paramProxyResponse.zzSK, false);
    zzb.zza(paramParcel, 5, paramProxyResponse.body, false);
    zzb.zzI(paramParcel, i);
  }
  
  public ProxyResponse zzN(Parcel paramParcel)
  {
    byte[] arrayOfByte = null;
    int i = 0;
    int j = zza.zzap(paramParcel);
    Bundle localBundle = null;
    PendingIntent localPendingIntent = null;
    int k = 0;
    int m = 0;
    while (paramParcel.dataPosition() < j)
    {
      int n = zza.zzao(paramParcel);
      switch (zza.zzbM(n))
      {
      default: 
        zza.zzb(paramParcel, n);
        break;
      case 1: 
        k = zza.zzg(paramParcel, n);
        break;
      case 1000: 
        m = zza.zzg(paramParcel, n);
        break;
      case 2: 
        localPendingIntent = (PendingIntent)zza.zza(paramParcel, n, PendingIntent.CREATOR);
        break;
      case 3: 
        i = zza.zzg(paramParcel, n);
        break;
      case 4: 
        localBundle = zza.zzr(paramParcel, n);
        break;
      case 5: 
        arrayOfByte = zza.zzs(paramParcel, n);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new ProxyResponse(m, k, localPendingIntent, i, localBundle, arrayOfByte);
  }
  
  public ProxyResponse[] zzaE(int paramInt)
  {
    return new ProxyResponse[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/proxy/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */