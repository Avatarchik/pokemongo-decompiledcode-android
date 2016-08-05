package com.google.android.gms.auth.firstparty.shared;

import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class FACLConfig
  implements SafeParcelable
{
  public static final zza CREATOR = new zza();
  final int version;
  boolean zzTA;
  boolean zzTB;
  boolean zzTC;
  boolean zzTx;
  String zzTy;
  boolean zzTz;
  
  FACLConfig(int paramInt, boolean paramBoolean1, String paramString, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5)
  {
    this.version = paramInt;
    this.zzTx = paramBoolean1;
    this.zzTy = paramString;
    this.zzTz = paramBoolean2;
    this.zzTA = paramBoolean3;
    this.zzTB = paramBoolean4;
    this.zzTC = paramBoolean5;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if ((paramObject instanceof FACLConfig))
    {
      FACLConfig localFACLConfig = (FACLConfig)paramObject;
      if ((this.zzTx == localFACLConfig.zzTx) && (TextUtils.equals(this.zzTy, localFACLConfig.zzTy)) && (this.zzTz == localFACLConfig.zzTz) && (this.zzTA == localFACLConfig.zzTA) && (this.zzTB == localFACLConfig.zzTB) && (this.zzTC == localFACLConfig.zzTC)) {
        bool = true;
      }
    }
    return bool;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = Boolean.valueOf(this.zzTx);
    arrayOfObject[1] = this.zzTy;
    arrayOfObject[2] = Boolean.valueOf(this.zzTz);
    arrayOfObject[3] = Boolean.valueOf(this.zzTA);
    arrayOfObject[4] = Boolean.valueOf(this.zzTB);
    arrayOfObject[5] = Boolean.valueOf(this.zzTC);
    return zzw.hashCode(arrayOfObject);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/firstparty/shared/FACLConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */