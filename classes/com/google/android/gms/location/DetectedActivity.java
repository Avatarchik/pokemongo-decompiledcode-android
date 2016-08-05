package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import java.util.Comparator;

public class DetectedActivity
  implements SafeParcelable
{
  public static final DetectedActivityCreator CREATOR = new DetectedActivityCreator();
  public static final int IN_VEHICLE = 0;
  public static final int ON_BICYCLE = 1;
  public static final int ON_FOOT = 2;
  public static final int RUNNING = 8;
  public static final int STILL = 3;
  public static final int TILTING = 5;
  public static final int UNKNOWN = 4;
  public static final int WALKING = 7;
  public static final Comparator<DetectedActivity> zzaEf = new Comparator()
  {
    public int zza(DetectedActivity paramAnonymousDetectedActivity1, DetectedActivity paramAnonymousDetectedActivity2)
    {
      int i = Integer.valueOf(paramAnonymousDetectedActivity2.getConfidence()).compareTo(Integer.valueOf(paramAnonymousDetectedActivity1.getConfidence()));
      if (i == 0) {
        i = Integer.valueOf(paramAnonymousDetectedActivity1.getType()).compareTo(Integer.valueOf(paramAnonymousDetectedActivity2.getType()));
      }
      return i;
    }
  };
  private final int mVersionCode;
  int zzaEg;
  int zzaEh;
  
  public DetectedActivity(int paramInt1, int paramInt2)
  {
    this.mVersionCode = 1;
    this.zzaEg = paramInt1;
    this.zzaEh = paramInt2;
  }
  
  public DetectedActivity(int paramInt1, int paramInt2, int paramInt3)
  {
    this.mVersionCode = paramInt1;
    this.zzaEg = paramInt2;
    this.zzaEh = paramInt3;
  }
  
  private int zzgK(int paramInt)
  {
    if (paramInt > 15) {
      paramInt = 4;
    }
    return paramInt;
  }
  
  public static String zzgL(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    case 6: 
    default: 
      str = Integer.toString(paramInt);
    }
    for (;;)
    {
      return str;
      str = "IN_VEHICLE";
      continue;
      str = "ON_BICYCLE";
      continue;
      str = "ON_FOOT";
      continue;
      str = "STILL";
      continue;
      str = "UNKNOWN";
      continue;
      str = "TILTING";
      continue;
      str = "WALKING";
      continue;
      str = "RUNNING";
    }
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
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
      {
        bool = false;
      }
      else
      {
        DetectedActivity localDetectedActivity = (DetectedActivity)paramObject;
        if ((this.zzaEg != localDetectedActivity.zzaEg) || (this.zzaEh != localDetectedActivity.zzaEh)) {
          bool = false;
        }
      }
    }
  }
  
  public int getConfidence()
  {
    return this.zzaEh;
  }
  
  public int getType()
  {
    return zzgK(this.zzaEg);
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(this.zzaEg);
    arrayOfObject[1] = Integer.valueOf(this.zzaEh);
    return zzw.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    return "DetectedActivity [type=" + zzgL(getType()) + ", confidence=" + this.zzaEh + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    DetectedActivityCreator.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/DetectedActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */