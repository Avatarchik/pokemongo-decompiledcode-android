package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.location.places.personalized.internal.TestDataImpl;
import java.util.List;

public class PlaceUserData
  implements SafeParcelable
{
  public static final zze CREATOR = new zze();
  final int mVersionCode;
  private final String zzRs;
  private final String zzaGt;
  private final List<TestDataImpl> zzaIb;
  private final List<PlaceAlias> zzaIc;
  private final List<HereContent> zzaId;
  
  PlaceUserData(int paramInt, String paramString1, String paramString2, List<TestDataImpl> paramList, List<PlaceAlias> paramList1, List<HereContent> paramList2)
  {
    this.mVersionCode = paramInt;
    this.zzRs = paramString1;
    this.zzaGt = paramString2;
    this.zzaIb = paramList;
    this.zzaIc = paramList1;
    this.zzaId = paramList2;
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
      if (!(paramObject instanceof PlaceUserData))
      {
        bool = false;
      }
      else
      {
        PlaceUserData localPlaceUserData = (PlaceUserData)paramObject;
        if ((!this.zzRs.equals(localPlaceUserData.zzRs)) || (!this.zzaGt.equals(localPlaceUserData.zzaGt)) || (!this.zzaIb.equals(localPlaceUserData.zzaIb)) || (!this.zzaIc.equals(localPlaceUserData.zzaIc)) || (!this.zzaId.equals(localPlaceUserData.zzaId))) {
          bool = false;
        }
      }
    }
  }
  
  public String getPlaceId()
  {
    return this.zzaGt;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = this.zzRs;
    arrayOfObject[1] = this.zzaGt;
    arrayOfObject[2] = this.zzaIb;
    arrayOfObject[3] = this.zzaIc;
    arrayOfObject[4] = this.zzaId;
    return zzw.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    return zzw.zzv(this).zzg("accountName", this.zzRs).zzg("placeId", this.zzaGt).zzg("testDataImpls", this.zzaIb).zzg("placeAliases", this.zzaIc).zzg("hereContents", this.zzaId).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  public String zzxr()
  {
    return this.zzRs;
  }
  
  public List<PlaceAlias> zzxs()
  {
    return this.zzaIc;
  }
  
  public List<HereContent> zzxt()
  {
    return this.zzaId;
  }
  
  public List<TestDataImpl> zzxu()
  {
    return this.zzaIb;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/personalized/PlaceUserData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */