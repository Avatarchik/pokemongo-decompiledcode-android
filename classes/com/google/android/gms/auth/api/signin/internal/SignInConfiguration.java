package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.EmailSignInConfig;
import com.google.android.gms.auth.api.signin.FacebookSignInConfig;
import com.google.android.gms.auth.api.signin.GoogleSignInConfig;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;

public final class SignInConfiguration
  implements SafeParcelable
{
  public static final Parcelable.Creator<SignInConfiguration> CREATOR = new zzh();
  private static int zzTr = 31;
  final int versionCode;
  private String zzTl;
  private final String zzTs;
  private EmailSignInConfig zzTt;
  private GoogleSignInConfig zzTu;
  private FacebookSignInConfig zzTv;
  private String zzTw;
  
  SignInConfiguration(int paramInt, String paramString1, String paramString2, EmailSignInConfig paramEmailSignInConfig, GoogleSignInConfig paramGoogleSignInConfig, FacebookSignInConfig paramFacebookSignInConfig, String paramString3)
  {
    this.versionCode = paramInt;
    this.zzTs = zzx.zzcr(paramString1);
    this.zzTl = paramString2;
    this.zzTt = paramEmailSignInConfig;
    this.zzTu = paramGoogleSignInConfig;
    this.zzTv = paramFacebookSignInConfig;
    this.zzTw = paramString3;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = false;
    if (paramObject == null) {}
    for (;;)
    {
      return bool1;
      try
      {
        SignInConfiguration localSignInConfiguration = (SignInConfiguration)paramObject;
        if (!this.zzTs.equals(localSignInConfiguration.zzme())) {
          continue;
        }
        if (TextUtils.isEmpty(this.zzTl))
        {
          if (!TextUtils.isEmpty(localSignInConfiguration.zzmb())) {
            continue;
          }
          label50:
          if (!TextUtils.isEmpty(this.zzTw)) {
            break label137;
          }
          if (!TextUtils.isEmpty(localSignInConfiguration.zzmi())) {
            continue;
          }
          label71:
          if (this.zzTt != null) {
            break label155;
          }
          if (localSignInConfiguration.zzmf() != null) {
            continue;
          }
          label86:
          if (this.zzTv != null) {
            break label173;
          }
          if (localSignInConfiguration.zzmh() != null) {
            continue;
          }
        }
        for (;;)
        {
          if (this.zzTu != null) {
            break label191;
          }
          if (localSignInConfiguration.zzmg() != null) {
            break;
          }
          break label217;
          if (!this.zzTl.equals(localSignInConfiguration.zzmb())) {
            break;
          }
          break label50;
          label137:
          if (!this.zzTw.equals(localSignInConfiguration.zzmi())) {
            break;
          }
          break label71;
          label155:
          if (!this.zzTt.equals(localSignInConfiguration.zzmf())) {
            break;
          }
          break label86;
          label173:
          if (!this.zzTv.equals(localSignInConfiguration.zzmh())) {
            break;
          }
        }
        label191:
        boolean bool2 = this.zzTu.equals(localSignInConfiguration.zzmg());
        if (!bool2) {
          continue;
        }
      }
      catch (ClassCastException localClassCastException) {}
      continue;
      label217:
      bool1 = true;
    }
  }
  
  public int hashCode()
  {
    return new zzc().zzl(this.zzTs).zzl(this.zzTl).zzl(this.zzTw).zzl(this.zzTt).zzl(this.zzTu).zzl(this.zzTv).zzmd();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
  
  public String zzmb()
  {
    return this.zzTl;
  }
  
  public String zzme()
  {
    return this.zzTs;
  }
  
  public EmailSignInConfig zzmf()
  {
    return this.zzTt;
  }
  
  public GoogleSignInConfig zzmg()
  {
    return this.zzTu;
  }
  
  public FacebookSignInConfig zzmh()
  {
    return this.zzTv;
  }
  
  public String zzmi()
  {
    return this.zzTw;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/signin/internal/SignInConfiguration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */