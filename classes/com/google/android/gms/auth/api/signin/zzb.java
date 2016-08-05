package com.google.android.gms.auth.api.signin;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import java.util.ArrayList;

public class zzb
  implements Parcelable.Creator<FacebookSignInConfig>
{
  static void zza(FacebookSignInConfig paramFacebookSignInConfig, Parcel paramParcel, int paramInt)
  {
    int i = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(paramParcel);
    com.google.android.gms.common.internal.safeparcel.zzb.zzc(paramParcel, 1, paramFacebookSignInConfig.versionCode);
    com.google.android.gms.common.internal.safeparcel.zzb.zza(paramParcel, 2, paramFacebookSignInConfig.zzlR(), paramInt, false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzb(paramParcel, 3, paramFacebookSignInConfig.zzlS(), false);
    com.google.android.gms.common.internal.safeparcel.zzb.zzI(paramParcel, i);
  }
  
  public FacebookSignInConfig zzP(Parcel paramParcel)
  {
    Object localObject1 = null;
    int i = zza.zzap(paramParcel);
    int j = 0;
    Object localObject2 = null;
    if (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      Object localObject3;
      Object localObject4;
      int m;
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        localObject3 = localObject1;
        localObject4 = localObject2;
        m = j;
      }
      for (;;)
      {
        j = m;
        localObject2 = localObject4;
        localObject1 = localObject3;
        break;
        int n = zza.zzg(paramParcel, k);
        Object localObject5 = localObject1;
        localObject4 = localObject2;
        m = n;
        localObject3 = localObject5;
        continue;
        Intent localIntent = (Intent)zza.zza(paramParcel, k, Intent.CREATOR);
        m = j;
        localObject3 = localObject1;
        localObject4 = localIntent;
        continue;
        localObject3 = zza.zzD(paramParcel, k);
        localObject4 = localObject2;
        m = j;
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new FacebookSignInConfig(j, (Intent)localObject2, (ArrayList)localObject1);
  }
  
  public FacebookSignInConfig[] zzaG(int paramInt)
  {
    return new FacebookSignInConfig[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/signin/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */