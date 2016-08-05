package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public final class Status
  implements Result, SafeParcelable
{
  public static final Parcelable.Creator<Status> CREATOR = new zzd();
  public static final Status zzabb = new Status(0);
  public static final Status zzabc = new Status(14);
  public static final Status zzabd = new Status(8);
  public static final Status zzabe = new Status(15);
  public static final Status zzabf = new Status(16);
  private final PendingIntent mPendingIntent;
  private final int mVersionCode;
  private final int zzYm;
  private final String zzZZ;
  
  public Status(int paramInt)
  {
    this(paramInt, null);
  }
  
  Status(int paramInt1, int paramInt2, String paramString, PendingIntent paramPendingIntent)
  {
    this.mVersionCode = paramInt1;
    this.zzYm = paramInt2;
    this.zzZZ = paramString;
    this.mPendingIntent = paramPendingIntent;
  }
  
  public Status(int paramInt, String paramString)
  {
    this(1, paramInt, paramString, null);
  }
  
  public Status(int paramInt, String paramString, PendingIntent paramPendingIntent)
  {
    this(1, paramInt, paramString, paramPendingIntent);
  }
  
  private String zznI()
  {
    if (this.zzZZ != null) {}
    for (String str = this.zzZZ;; str = CommonStatusCodes.getStatusCodeString(this.zzYm)) {
      return str;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (!(paramObject instanceof Status)) {}
    for (;;)
    {
      return bool;
      Status localStatus = (Status)paramObject;
      if ((this.mVersionCode == localStatus.mVersionCode) && (this.zzYm == localStatus.zzYm) && (zzw.equal(this.zzZZ, localStatus.zzZZ)) && (zzw.equal(this.mPendingIntent, localStatus.mPendingIntent))) {
        bool = true;
      }
    }
  }
  
  public PendingIntent getResolution()
  {
    return this.mPendingIntent;
  }
  
  public Status getStatus()
  {
    return this;
  }
  
  public int getStatusCode()
  {
    return this.zzYm;
  }
  
  public String getStatusMessage()
  {
    return this.zzZZ;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public boolean hasResolution()
  {
    if (this.mPendingIntent != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Integer.valueOf(this.mVersionCode);
    arrayOfObject[1] = Integer.valueOf(this.zzYm);
    arrayOfObject[2] = this.zzZZ;
    arrayOfObject[3] = this.mPendingIntent;
    return zzw.hashCode(arrayOfObject);
  }
  
  public boolean isCanceled()
  {
    if (this.zzYm == 16) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isInterrupted()
  {
    if (this.zzYm == 14) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean isSuccess()
  {
    if (this.zzYm <= 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void startResolutionForResult(Activity paramActivity, int paramInt)
    throws IntentSender.SendIntentException
  {
    if (!hasResolution()) {}
    for (;;)
    {
      return;
      paramActivity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), paramInt, null, 0, 0, 0);
    }
  }
  
  public String toString()
  {
    return zzw.zzv(this).zzg("statusCode", zznI()).zzg("resolution", this.mPendingIntent).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
  
  PendingIntent zznH()
  {
    return this.mPendingIntent;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/api/Status.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */