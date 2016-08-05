package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.List;

@Deprecated
public final class PlaceLocalization
  implements SafeParcelable
{
  public static final zzo CREATOR = new zzo();
  public final String address;
  public final String name;
  public final int versionCode;
  public final String zzaHC;
  public final String zzaHD;
  public final List<String> zzaHE;
  
  public PlaceLocalization(int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, List<String> paramList)
  {
    this.versionCode = paramInt;
    this.name = paramString1;
    this.address = paramString2;
    this.zzaHC = paramString3;
    this.zzaHD = paramString4;
    this.zzaHE = paramList;
  }
  
  public static PlaceLocalization zza(String paramString1, String paramString2, String paramString3, String paramString4, List<String> paramList)
  {
    return new PlaceLocalization(0, paramString1, paramString2, paramString3, paramString4, paramList);
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
      if (!(paramObject instanceof PlaceLocalization))
      {
        bool = false;
      }
      else
      {
        PlaceLocalization localPlaceLocalization = (PlaceLocalization)paramObject;
        if ((!zzw.equal(this.name, localPlaceLocalization.name)) || (!zzw.equal(this.address, localPlaceLocalization.address)) || (!zzw.equal(this.zzaHC, localPlaceLocalization.zzaHC)) || (!zzw.equal(this.zzaHD, localPlaceLocalization.zzaHD)) || (!zzw.equal(this.zzaHE, localPlaceLocalization.zzaHE))) {
          bool = false;
        }
      }
    }
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = this.name;
    arrayOfObject[1] = this.address;
    arrayOfObject[2] = this.zzaHC;
    arrayOfObject[3] = this.zzaHD;
    return zzw.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    return zzw.zzv(this).zzg("name", this.name).zzg("address", this.address).zzg("internationalPhoneNumber", this.zzaHC).zzg("regularOpenHours", this.zzaHD).zzg("attributions", this.zzaHE).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzo.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/internal/PlaceLocalization.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */