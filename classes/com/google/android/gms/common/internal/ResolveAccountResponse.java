package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ResolveAccountResponse
  implements SafeParcelable
{
  public static final Parcelable.Creator<ResolveAccountResponse> CREATOR = new zzz();
  final int mVersionCode;
  private boolean zzabG;
  IBinder zzaeH;
  private ConnectionResult zzagq;
  private boolean zzagr;
  
  public ResolveAccountResponse(int paramInt)
  {
    this(new ConnectionResult(paramInt, null));
  }
  
  ResolveAccountResponse(int paramInt, IBinder paramIBinder, ConnectionResult paramConnectionResult, boolean paramBoolean1, boolean paramBoolean2)
  {
    this.mVersionCode = paramInt;
    this.zzaeH = paramIBinder;
    this.zzagq = paramConnectionResult;
    this.zzabG = paramBoolean1;
    this.zzagr = paramBoolean2;
  }
  
  public ResolveAccountResponse(ConnectionResult paramConnectionResult)
  {
    this(1, null, paramConnectionResult, false, false);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {}
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof ResolveAccountResponse))
      {
        bool = false;
      }
      else
      {
        ResolveAccountResponse localResolveAccountResponse = (ResolveAccountResponse)paramObject;
        if ((!this.zzagq.equals(localResolveAccountResponse.zzagq)) || (!zzpq().equals(localResolveAccountResponse.zzpq()))) {
          bool = false;
        }
      }
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzz.zza(this, paramParcel, paramInt);
  }
  
  public zzp zzpq()
  {
    return zzp.zza.zzaH(this.zzaeH);
  }
  
  public ConnectionResult zzpr()
  {
    return this.zzagq;
  }
  
  public boolean zzps()
  {
    return this.zzabG;
  }
  
  public boolean zzpt()
  {
    return this.zzagr;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/internal/ResolveAccountResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */