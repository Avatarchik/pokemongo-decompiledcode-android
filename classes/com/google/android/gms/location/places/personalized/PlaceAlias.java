package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public class PlaceAlias
  implements SafeParcelable
{
  public static final zzc CREATOR = new zzc();
  public static final PlaceAlias zzaHY = new PlaceAlias(0, "Home");
  public static final PlaceAlias zzaHZ = new PlaceAlias(0, "Work");
  final int mVersionCode;
  private final String zzaIa;
  
  PlaceAlias(int paramInt, String paramString)
  {
    this.mVersionCode = paramInt;
    this.zzaIa = paramString;
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
      if (!(paramObject instanceof PlaceAlias))
      {
        bool = false;
      }
      else
      {
        PlaceAlias localPlaceAlias = (PlaceAlias)paramObject;
        bool = zzw.equal(this.zzaIa, localPlaceAlias.zzaIa);
      }
    }
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.zzaIa;
    return zzw.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    return zzw.zzv(this).zzg("alias", this.zzaIa).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public String zzxq()
  {
    return this.zzaIa;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/personalized/PlaceAlias.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */