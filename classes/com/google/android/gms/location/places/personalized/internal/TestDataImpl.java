package com.google.android.gms.location.places.personalized.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.location.places.personalized.zzf;

public class TestDataImpl
  extends zzf
  implements SafeParcelable
{
  public static final zza CREATOR = new zza();
  final int mVersionCode;
  private final String zzaIe;
  
  TestDataImpl(int paramInt, String paramString)
  {
    this.mVersionCode = paramInt;
    this.zzaIe = paramString;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool;
    if (this == paramObject) {
      bool = true;
    }
    for (;;)
    {
      return bool;
      if (!(paramObject instanceof TestDataImpl))
      {
        bool = false;
      }
      else
      {
        TestDataImpl localTestDataImpl = (TestDataImpl)paramObject;
        bool = this.zzaIe.equals(localTestDataImpl.zzaIe);
      }
    }
  }
  
  public int hashCode()
  {
    return this.zzaIe.hashCode();
  }
  
  public String toString()
  {
    return zzw.zzv(this).zzg("testName", this.zzaIe).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public String zzxv()
  {
    return this.zzaIe;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/personalized/internal/TestDataImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */