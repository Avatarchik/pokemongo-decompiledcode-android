package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import android.util.Patterns;
import com.google.android.gms.auth.api.signin.internal.zzc;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSignInConfig
  implements SafeParcelable
{
  public static final Parcelable.Creator<EmailSignInConfig> CREATOR = new zza();
  final int versionCode;
  private final Uri zzSU;
  private String zzSV;
  private Uri zzSW;
  
  EmailSignInConfig(int paramInt, Uri paramUri1, String paramString, Uri paramUri2)
  {
    zzx.zzb(paramUri1, "Server widget url cannot be null in order to use email/password sign in.");
    zzx.zzh(paramUri1.toString(), "Server widget url cannot be null in order to use email/password sign in.");
    zzx.zzb(Patterns.WEB_URL.matcher(paramUri1.toString()).matches(), "Invalid server widget url");
    this.versionCode = paramInt;
    this.zzSU = paramUri1;
    this.zzSV = paramString;
    this.zzSW = paramUri2;
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
        EmailSignInConfig localEmailSignInConfig = (EmailSignInConfig)paramObject;
        if (!this.zzSU.equals(localEmailSignInConfig.zzlO())) {
          continue;
        }
        if (this.zzSW == null) {
          if (localEmailSignInConfig.zzlP() != null) {
            continue;
          }
        }
        for (;;)
        {
          if (!TextUtils.isEmpty(this.zzSV)) {
            break label86;
          }
          if (!TextUtils.isEmpty(localEmailSignInConfig.zzlQ())) {
            break;
          }
          break label112;
          if (!this.zzSW.equals(localEmailSignInConfig.zzlP())) {
            break;
          }
        }
        label86:
        boolean bool2 = this.zzSV.equals(localEmailSignInConfig.zzlQ());
        if (!bool2) {
          continue;
        }
      }
      catch (ClassCastException localClassCastException) {}
      continue;
      label112:
      bool1 = true;
    }
  }
  
  public int hashCode()
  {
    return new zzc().zzl(this.zzSU).zzl(this.zzSW).zzl(this.zzSV).zzmd();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public Uri zzlO()
  {
    return this.zzSU;
  }
  
  public Uri zzlP()
  {
    return this.zzSW;
  }
  
  public String zzlQ()
  {
    return this.zzSV;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/signin/EmailSignInConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */