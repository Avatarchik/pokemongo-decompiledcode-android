package com.google.android.gms.location.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class ClientIdentity
  implements SafeParcelable
{
  public static final zzc CREATOR = new zzc();
  private final int mVersionCode;
  public final String packageName;
  public final int uid;
  
  ClientIdentity(int paramInt1, int paramInt2, String paramString)
  {
    this.mVersionCode = paramInt1;
    this.uid = paramInt2;
    this.packageName = paramString;
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
      if ((paramObject == null) || (!(paramObject instanceof ClientIdentity)))
      {
        bool = false;
      }
      else
      {
        ClientIdentity localClientIdentity = (ClientIdentity)paramObject;
        if ((localClientIdentity.uid != this.uid) || (!zzw.equal(localClientIdentity.packageName, this.packageName))) {
          bool = false;
        }
      }
    }
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return this.uid;
  }
  
  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(this.uid);
    arrayOfObject[1] = this.packageName;
    return String.format("%d:%s", arrayOfObject);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/internal/ClientIdentity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */