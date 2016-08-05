package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public final class LocationRequest
  implements SafeParcelable
{
  public static final LocationRequestCreator CREATOR = new LocationRequestCreator();
  public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
  public static final int PRIORITY_HIGH_ACCURACY = 100;
  public static final int PRIORITY_LOW_POWER = 104;
  public static final int PRIORITY_NO_POWER = 105;
  int mPriority;
  private final int mVersionCode;
  long zzaEE;
  long zzaEF;
  int zzaEG;
  float zzaEH;
  long zzaEI;
  long zzaEj;
  boolean zzasP;
  
  public LocationRequest()
  {
    this.mVersionCode = 1;
    this.mPriority = 102;
    this.zzaEE = 3600000L;
    this.zzaEF = 600000L;
    this.zzasP = false;
    this.zzaEj = Long.MAX_VALUE;
    this.zzaEG = Integer.MAX_VALUE;
    this.zzaEH = 0.0F;
    this.zzaEI = 0L;
  }
  
  LocationRequest(int paramInt1, int paramInt2, long paramLong1, long paramLong2, boolean paramBoolean, long paramLong3, int paramInt3, float paramFloat, long paramLong4)
  {
    this.mVersionCode = paramInt1;
    this.mPriority = paramInt2;
    this.zzaEE = paramLong1;
    this.zzaEF = paramLong2;
    this.zzasP = paramBoolean;
    this.zzaEj = paramLong3;
    this.zzaEG = paramInt3;
    this.zzaEH = paramFloat;
    this.zzaEI = paramLong4;
  }
  
  public static LocationRequest create()
  {
    return new LocationRequest();
  }
  
  private static void zzK(long paramLong)
  {
    if (paramLong < 0L) {
      throw new IllegalArgumentException("invalid interval: " + paramLong);
    }
  }
  
  private static void zzd(float paramFloat)
  {
    if (paramFloat < 0.0F) {
      throw new IllegalArgumentException("invalid displacement: " + paramFloat);
    }
  }
  
  private static void zzgP(int paramInt)
  {
    switch (paramInt)
    {
    case 101: 
    case 103: 
    default: 
      throw new IllegalArgumentException("invalid quality: " + paramInt);
    }
  }
  
  public static String zzgQ(int paramInt)
  {
    String str;
    switch (paramInt)
    {
    case 101: 
    case 103: 
    default: 
      str = "???";
    }
    for (;;)
    {
      return str;
      str = "PRIORITY_HIGH_ACCURACY";
      continue;
      str = "PRIORITY_BALANCED_POWER_ACCURACY";
      continue;
      str = "PRIORITY_LOW_POWER";
      continue;
      str = "PRIORITY_NO_POWER";
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
      if (!(paramObject instanceof LocationRequest))
      {
        bool = false;
      }
      else
      {
        LocationRequest localLocationRequest = (LocationRequest)paramObject;
        if ((this.mPriority != localLocationRequest.mPriority) || (this.zzaEE != localLocationRequest.zzaEE) || (this.zzaEF != localLocationRequest.zzaEF) || (this.zzasP != localLocationRequest.zzasP) || (this.zzaEj != localLocationRequest.zzaEj) || (this.zzaEG != localLocationRequest.zzaEG) || (this.zzaEH != localLocationRequest.zzaEH)) {
          bool = false;
        }
      }
    }
  }
  
  public long getExpirationTime()
  {
    return this.zzaEj;
  }
  
  public long getFastestInterval()
  {
    return this.zzaEF;
  }
  
  public long getInterval()
  {
    return this.zzaEE;
  }
  
  public long getMaxWaitTime()
  {
    long l = this.zzaEI;
    if (l < this.zzaEE) {
      l = this.zzaEE;
    }
    return l;
  }
  
  public int getNumUpdates()
  {
    return this.zzaEG;
  }
  
  public int getPriority()
  {
    return this.mPriority;
  }
  
  public float getSmallestDisplacement()
  {
    return this.zzaEH;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = Integer.valueOf(this.mPriority);
    arrayOfObject[1] = Long.valueOf(this.zzaEE);
    arrayOfObject[2] = Long.valueOf(this.zzaEF);
    arrayOfObject[3] = Boolean.valueOf(this.zzasP);
    arrayOfObject[4] = Long.valueOf(this.zzaEj);
    arrayOfObject[5] = Integer.valueOf(this.zzaEG);
    arrayOfObject[6] = Float.valueOf(this.zzaEH);
    return zzw.hashCode(arrayOfObject);
  }
  
  public LocationRequest setExpirationDuration(long paramLong)
  {
    long l = SystemClock.elapsedRealtime();
    if (paramLong > Long.MAX_VALUE - l) {}
    for (this.zzaEj = Long.MAX_VALUE;; this.zzaEj = (l + paramLong))
    {
      if (this.zzaEj < 0L) {
        this.zzaEj = 0L;
      }
      return this;
    }
  }
  
  public LocationRequest setExpirationTime(long paramLong)
  {
    this.zzaEj = paramLong;
    if (this.zzaEj < 0L) {
      this.zzaEj = 0L;
    }
    return this;
  }
  
  public LocationRequest setFastestInterval(long paramLong)
  {
    zzK(paramLong);
    this.zzasP = true;
    this.zzaEF = paramLong;
    return this;
  }
  
  public LocationRequest setInterval(long paramLong)
  {
    zzK(paramLong);
    this.zzaEE = paramLong;
    if (!this.zzasP) {
      this.zzaEF = ((this.zzaEE / 6.0D));
    }
    return this;
  }
  
  public LocationRequest setMaxWaitTime(long paramLong)
  {
    zzK(paramLong);
    this.zzaEI = paramLong;
    return this;
  }
  
  public LocationRequest setNumUpdates(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("invalid numUpdates: " + paramInt);
    }
    this.zzaEG = paramInt;
    return this;
  }
  
  public LocationRequest setPriority(int paramInt)
  {
    zzgP(paramInt);
    this.mPriority = paramInt;
    return this;
  }
  
  public LocationRequest setSmallestDisplacement(float paramFloat)
  {
    zzd(paramFloat);
    this.zzaEH = paramFloat;
    return this;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Request[").append(zzgQ(this.mPriority));
    if (this.mPriority != 105)
    {
      localStringBuilder.append(" requested=");
      localStringBuilder.append(this.zzaEE).append("ms");
    }
    localStringBuilder.append(" fastest=");
    localStringBuilder.append(this.zzaEF).append("ms");
    if (this.zzaEI > this.zzaEE)
    {
      localStringBuilder.append(" maxWait=");
      localStringBuilder.append(this.zzaEI).append("ms");
    }
    if (this.zzaEj != Long.MAX_VALUE)
    {
      long l = this.zzaEj - SystemClock.elapsedRealtime();
      localStringBuilder.append(" expireIn=");
      localStringBuilder.append(l).append("ms");
    }
    if (this.zzaEG != Integer.MAX_VALUE) {
      localStringBuilder.append(" num=").append(this.zzaEG);
    }
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    LocationRequestCreator.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/LocationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */