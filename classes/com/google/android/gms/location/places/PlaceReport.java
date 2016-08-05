package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;

public class PlaceReport
  implements SafeParcelable
{
  public static final Parcelable.Creator<PlaceReport> CREATOR = new zzj();
  private final String mTag;
  final int mVersionCode;
  private final String zzaGt;
  private final String zzaGu;
  
  PlaceReport(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    this.mVersionCode = paramInt;
    this.zzaGt = paramString1;
    this.mTag = paramString2;
    this.zzaGu = paramString3;
  }
  
  public static PlaceReport create(String paramString1, String paramString2)
  {
    return zzi(paramString1, paramString2, "unknown");
  }
  
  private static boolean zzdy(String paramString)
  {
    int i = 1;
    int j = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (j)
      {
      default: 
        i = 0;
      }
      return i;
      if (paramString.equals("unknown"))
      {
        j = 0;
        continue;
        if (paramString.equals("userReported"))
        {
          j = i;
          continue;
          if (paramString.equals("inferredGeofencing"))
          {
            j = 2;
            continue;
            if (paramString.equals("inferredRadioSignals"))
            {
              j = 3;
              continue;
              if (paramString.equals("inferredReverseGeocoding"))
              {
                j = 4;
                continue;
                if (paramString.equals("inferredSnappedToRoad")) {
                  j = 5;
                }
              }
            }
          }
        }
      }
    }
  }
  
  public static PlaceReport zzi(String paramString1, String paramString2, String paramString3)
  {
    zzx.zzw(paramString1);
    zzx.zzcr(paramString2);
    zzx.zzcr(paramString3);
    zzx.zzb(zzdy(paramString3), "Invalid source");
    return new PlaceReport(1, paramString1, paramString2, paramString3);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (!(paramObject instanceof PlaceReport)) {}
    for (;;)
    {
      return bool;
      PlaceReport localPlaceReport = (PlaceReport)paramObject;
      if ((zzw.equal(this.zzaGt, localPlaceReport.zzaGt)) && (zzw.equal(this.mTag, localPlaceReport.mTag)) && (zzw.equal(this.zzaGu, localPlaceReport.zzaGu))) {
        bool = true;
      }
    }
  }
  
  public String getPlaceId()
  {
    return this.zzaGt;
  }
  
  public String getSource()
  {
    return this.zzaGu;
  }
  
  public String getTag()
  {
    return this.mTag;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = this.zzaGt;
    arrayOfObject[1] = this.mTag;
    arrayOfObject[2] = this.zzaGu;
    return zzw.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    zzw.zza localzza = zzw.zzv(this);
    localzza.zzg("placeId", this.zzaGt);
    localzza.zzg("tag", this.mTag);
    if (!"unknown".equals(this.zzaGu)) {
      localzza.zzg("source", this.zzaGu);
    }
    return localzza.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzj.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/places/PlaceReport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */