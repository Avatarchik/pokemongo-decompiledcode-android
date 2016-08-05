package com.google.android.gms.auth.api.proxy;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza
  implements Parcelable.Creator<ProxyGrpcRequest>
{
  static void zza(ProxyGrpcRequest paramProxyGrpcRequest, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramProxyGrpcRequest.hostname, false);
    zzb.zzc(paramParcel, 1000, paramProxyGrpcRequest.versionCode);
    zzb.zzc(paramParcel, 2, paramProxyGrpcRequest.port);
    zzb.zza(paramParcel, 3, paramProxyGrpcRequest.timeoutMillis);
    zzb.zza(paramParcel, 4, paramProxyGrpcRequest.body, false);
    zzb.zza(paramParcel, 5, paramProxyGrpcRequest.method, false);
    zzb.zzI(paramParcel, i);
  }
  
  public ProxyGrpcRequest zzL(Parcel paramParcel)
  {
    int i = 0;
    String str1 = null;
    int j = com.google.android.gms.common.internal.safeparcel.zza.zzap(paramParcel);
    long l = 0L;
    byte[] arrayOfByte = null;
    String str2 = null;
    int k = 0;
    while (paramParcel.dataPosition() < j)
    {
      int m = com.google.android.gms.common.internal.safeparcel.zza.zzao(paramParcel);
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(m))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, m);
        break;
      case 1: 
        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
        break;
      case 1000: 
        k = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        break;
      case 2: 
        i = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, m);
        break;
      case 3: 
        l = com.google.android.gms.common.internal.safeparcel.zza.zzi(paramParcel, m);
        break;
      case 4: 
        arrayOfByte = com.google.android.gms.common.internal.safeparcel.zza.zzs(paramParcel, m);
        break;
      case 5: 
        str1 = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, m);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new zza.zza("Overread allowed size end=" + j, paramParcel);
    }
    return new ProxyGrpcRequest(k, str2, i, l, arrayOfByte, str1);
  }
  
  public ProxyGrpcRequest[] zzaC(int paramInt)
  {
    return new ProxyGrpcRequest[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/proxy/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */