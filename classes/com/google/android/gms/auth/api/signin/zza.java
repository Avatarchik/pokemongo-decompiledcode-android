package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza
  implements Parcelable.Creator<EmailSignInConfig>
{
  static void zza(EmailSignInConfig paramEmailSignInConfig, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zzc(paramParcel, 1, paramEmailSignInConfig.versionCode);
    zzb.zza(paramParcel, 2, paramEmailSignInConfig.zzlO(), paramInt, false);
    zzb.zza(paramParcel, 3, paramEmailSignInConfig.zzlQ(), false);
    zzb.zza(paramParcel, 4, paramEmailSignInConfig.zzlP(), paramInt, false);
    zzb.zzI(paramParcel, i);
  }
  
  public EmailSignInConfig zzO(Parcel paramParcel)
  {
    Object localObject1 = null;
    int i = com.google.android.gms.common.internal.safeparcel.zza.zzap(paramParcel);
    int j = 0;
    Object localObject2 = null;
    Object localObject3 = null;
    if (paramParcel.dataPosition() < i)
    {
      int k = com.google.android.gms.common.internal.safeparcel.zza.zzao(paramParcel);
      Object localObject4;
      Object localObject5;
      Object localObject6;
      int m;
      switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(k))
      {
      default: 
        com.google.android.gms.common.internal.safeparcel.zza.zzb(paramParcel, k);
        localObject4 = localObject1;
        localObject5 = localObject2;
        localObject6 = localObject3;
        m = j;
      }
      for (;;)
      {
        j = m;
        localObject3 = localObject6;
        localObject2 = localObject5;
        localObject1 = localObject4;
        break;
        int n = com.google.android.gms.common.internal.safeparcel.zza.zzg(paramParcel, k);
        Object localObject9 = localObject1;
        localObject5 = localObject2;
        localObject6 = localObject3;
        m = n;
        localObject4 = localObject9;
        continue;
        Uri localUri = (Uri)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, k, Uri.CREATOR);
        m = j;
        Object localObject8 = localObject2;
        localObject6 = localUri;
        localObject4 = localObject1;
        localObject5 = localObject8;
        continue;
        String str = com.google.android.gms.common.internal.safeparcel.zza.zzp(paramParcel, k);
        localObject6 = localObject3;
        m = j;
        Object localObject7 = localObject1;
        localObject5 = str;
        localObject4 = localObject7;
        continue;
        localObject4 = (Uri)com.google.android.gms.common.internal.safeparcel.zza.zza(paramParcel, k, Uri.CREATOR);
        localObject5 = localObject2;
        localObject6 = localObject3;
        m = j;
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new EmailSignInConfig(j, (Uri)localObject3, (String)localObject2, (Uri)localObject1);
  }
  
  public EmailSignInConfig[] zzaF(int paramInt)
  {
    return new EmailSignInConfig[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/signin/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */