package com.google.android.gms.auth;

import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import java.util.List;

public class TokenData
  implements SafeParcelable
{
  public static final zzd CREATOR = new zzd();
  final int mVersionCode;
  private final Long zzRA;
  private final boolean zzRB;
  private final boolean zzRC;
  private final List<String> zzRD;
  private final String zzRz;
  
  TokenData(int paramInt, String paramString, Long paramLong, boolean paramBoolean1, boolean paramBoolean2, List<String> paramList)
  {
    this.mVersionCode = paramInt;
    this.zzRz = zzx.zzcr(paramString);
    this.zzRA = paramLong;
    this.zzRB = paramBoolean1;
    this.zzRC = paramBoolean2;
    this.zzRD = paramList;
  }
  
  public static TokenData zza(Bundle paramBundle, String paramString)
  {
    paramBundle.setClassLoader(TokenData.class.getClassLoader());
    Bundle localBundle = paramBundle.getBundle(paramString);
    if (localBundle == null) {}
    for (TokenData localTokenData = null;; localTokenData = (TokenData)localBundle.getParcelable("TokenData"))
    {
      return localTokenData;
      localBundle.setClassLoader(TokenData.class.getClassLoader());
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (!(paramObject instanceof TokenData)) {}
    for (;;)
    {
      return bool;
      TokenData localTokenData = (TokenData)paramObject;
      if ((TextUtils.equals(this.zzRz, localTokenData.zzRz)) && (zzw.equal(this.zzRA, localTokenData.zzRA)) && (this.zzRB == localTokenData.zzRB) && (this.zzRC == localTokenData.zzRC) && (zzw.equal(this.zzRD, localTokenData.zzRD))) {
        bool = true;
      }
    }
  }
  
  public String getToken()
  {
    return this.zzRz;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = this.zzRz;
    arrayOfObject[1] = this.zzRA;
    arrayOfObject[2] = Boolean.valueOf(this.zzRB);
    arrayOfObject[3] = Boolean.valueOf(this.zzRC);
    arrayOfObject[4] = this.zzRD;
    return zzw.hashCode(arrayOfObject);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
  
  public Long zzlA()
  {
    return this.zzRA;
  }
  
  public boolean zzlB()
  {
    return this.zzRB;
  }
  
  public boolean zzlC()
  {
    return this.zzRC;
  }
  
  public List<String> zzlD()
  {
    return this.zzRD;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/TokenData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */