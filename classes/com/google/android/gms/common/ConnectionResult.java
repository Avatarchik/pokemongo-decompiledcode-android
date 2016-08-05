package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public final class ConnectionResult
  implements SafeParcelable
{
  public static final int API_UNAVAILABLE = 16;
  public static final int CANCELED = 13;
  public static final Parcelable.Creator<ConnectionResult> CREATOR = new zzb();
  public static final int DEVELOPER_ERROR = 10;
  @Deprecated
  public static final int DRIVE_EXTERNAL_STORAGE_REQUIRED = 1500;
  public static final int INTERNAL_ERROR = 8;
  public static final int INTERRUPTED = 15;
  public static final int INVALID_ACCOUNT = 5;
  public static final int LICENSE_CHECK_FAILED = 11;
  public static final int NETWORK_ERROR = 7;
  public static final int RESOLUTION_REQUIRED = 6;
  public static final int SERVICE_DISABLED = 3;
  public static final int SERVICE_INVALID = 9;
  public static final int SERVICE_MISSING = 1;
  public static final int SERVICE_MISSING_PERMISSION = 19;
  public static final int SERVICE_UPDATING = 18;
  public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
  public static final int SIGN_IN_FAILED = 17;
  public static final int SIGN_IN_REQUIRED = 4;
  public static final int SUCCESS = 0;
  public static final int TIMEOUT = 14;
  public static final ConnectionResult zzZY = new ConnectionResult(0);
  private final PendingIntent mPendingIntent;
  final int mVersionCode;
  private final int zzYm;
  private final String zzZZ;
  
  public ConnectionResult(int paramInt)
  {
    this(paramInt, null, null);
  }
  
  ConnectionResult(int paramInt1, int paramInt2, PendingIntent paramPendingIntent, String paramString)
  {
    this.mVersionCode = paramInt1;
    this.zzYm = paramInt2;
    this.mPendingIntent = paramPendingIntent;
    this.zzZZ = paramString;
  }
  
  public ConnectionResult(int paramInt, PendingIntent paramPendingIntent)
  {
    this(paramInt, paramPendingIntent, null);
  }
  
  public ConnectionResult(int paramInt, PendingIntent paramPendingIntent, String paramString)
  {
    this(1, paramInt, paramPendingIntent, paramString);
  }
  
  static String getStatusString(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    case 12: 
    default: 
      str = "UNKNOWN_ERROR_CODE(" + paramInt + ")";
    }
    for (;;)
    {
      return str;
      str = "SUCCESS";
      continue;
      str = "SERVICE_MISSING";
      continue;
      str = "SERVICE_VERSION_UPDATE_REQUIRED";
      continue;
      str = "SERVICE_DISABLED";
      continue;
      str = "SIGN_IN_REQUIRED";
      continue;
      str = "INVALID_ACCOUNT";
      continue;
      str = "RESOLUTION_REQUIRED";
      continue;
      str = "NETWORK_ERROR";
      continue;
      str = "INTERNAL_ERROR";
      continue;
      str = "SERVICE_INVALID";
      continue;
      str = "DEVELOPER_ERROR";
      continue;
      str = "LICENSE_CHECK_FAILED";
      continue;
      str = "CANCELED";
      continue;
      str = "TIMEOUT";
      continue;
      str = "INTERRUPTED";
      continue;
      str = "API_UNAVAILABLE";
      continue;
      str = "SIGN_IN_FAILED";
      continue;
      str = "SERVICE_UPDATING";
      continue;
      str = "SERVICE_MISSING_PERMISSION";
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == this) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof ConnectionResult))
      {
        bool = false;
      }
      else
      {
        ConnectionResult localConnectionResult = (ConnectionResult)paramObject;
        if ((this.zzYm != localConnectionResult.zzYm) || (!zzw.equal(this.mPendingIntent, localConnectionResult.mPendingIntent)) || (!zzw.equal(this.zzZZ, localConnectionResult.zzZZ))) {
          bool = false;
        }
      }
    }
  }
  
  public int getErrorCode()
  {
    return this.zzYm;
  }
  
  public String getErrorMessage()
  {
    return this.zzZZ;
  }
  
  public PendingIntent getResolution()
  {
    return this.mPendingIntent;
  }
  
  public boolean hasResolution()
  {
    if ((this.zzYm != 0) && (this.mPendingIntent != null)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Integer.valueOf(this.zzYm);
    arrayOfObject[1] = this.mPendingIntent;
    arrayOfObject[2] = this.zzZZ;
    return zzw.hashCode(arrayOfObject);
  }
  
  public boolean isSuccess()
  {
    if (this.zzYm == 0) {}
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
    return zzw.zzv(this).zzg("statusCode", getStatusString(this.zzYm)).zzg("resolution", this.mPendingIntent).zzg("message", this.zzZZ).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/ConnectionResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */