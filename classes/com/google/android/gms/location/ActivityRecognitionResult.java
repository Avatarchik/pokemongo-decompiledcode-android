package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ActivityRecognitionResult
  implements SafeParcelable
{
  public static final ActivityRecognitionResultCreator CREATOR = new ActivityRecognitionResultCreator();
  private final int mVersionCode;
  List<DetectedActivity> zzaEb;
  long zzaEc;
  long zzaEd;
  int zzaEe;
  
  public ActivityRecognitionResult(int paramInt1, List<DetectedActivity> paramList, long paramLong1, long paramLong2, int paramInt2)
  {
    this.mVersionCode = paramInt1;
    this.zzaEb = paramList;
    this.zzaEc = paramLong1;
    this.zzaEd = paramLong2;
    this.zzaEe = paramInt2;
  }
  
  public ActivityRecognitionResult(DetectedActivity paramDetectedActivity, long paramLong1, long paramLong2)
  {
    this(paramDetectedActivity, paramLong1, paramLong2, 0);
  }
  
  public ActivityRecognitionResult(DetectedActivity paramDetectedActivity, long paramLong1, long paramLong2, int paramInt)
  {
    this(Collections.singletonList(paramDetectedActivity), paramLong1, paramLong2, paramInt);
  }
  
  public ActivityRecognitionResult(List<DetectedActivity> paramList, long paramLong1, long paramLong2)
  {
    this(paramList, paramLong1, paramLong2, 0);
  }
  
  public ActivityRecognitionResult(List<DetectedActivity> paramList, long paramLong1, long paramLong2, int paramInt)
  {
    boolean bool2;
    if ((paramList != null) && (paramList.size() > 0))
    {
      bool2 = bool1;
      zzx.zzb(bool2, "Must have at least 1 detected activity");
      if ((paramLong1 <= 0L) || (paramLong2 <= 0L)) {
        break label85;
      }
    }
    for (;;)
    {
      zzx.zzb(bool1, "Must set times");
      this.mVersionCode = 2;
      this.zzaEb = paramList;
      this.zzaEc = paramLong1;
      this.zzaEd = paramLong2;
      this.zzaEe = paramInt;
      return;
      bool2 = false;
      break;
      label85:
      bool1 = false;
    }
  }
  
  public static ActivityRecognitionResult extractResult(Intent paramIntent)
  {
    ActivityRecognitionResult localActivityRecognitionResult;
    if (!hasResult(paramIntent)) {
      localActivityRecognitionResult = null;
    }
    for (;;)
    {
      return localActivityRecognitionResult;
      Object localObject = paramIntent.getExtras().get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
      if ((localObject instanceof byte[]))
      {
        Parcel localParcel = Parcel.obtain();
        localParcel.unmarshall((byte[])localObject, 0, ((byte[])localObject).length);
        localParcel.setDataPosition(0);
        localActivityRecognitionResult = CREATOR.createFromParcel(localParcel);
      }
      else if ((localObject instanceof ActivityRecognitionResult))
      {
        localActivityRecognitionResult = (ActivityRecognitionResult)localObject;
      }
      else
      {
        localActivityRecognitionResult = null;
      }
    }
  }
  
  public static boolean hasResult(Intent paramIntent)
  {
    if (paramIntent == null) {}
    for (boolean bool = false;; bool = paramIntent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) {
      return bool;
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
        ActivityRecognitionResult localActivityRecognitionResult = (ActivityRecognitionResult)paramObject;
        if ((this.zzaEc != localActivityRecognitionResult.zzaEc) || (this.zzaEd != localActivityRecognitionResult.zzaEd) || (this.zzaEe != localActivityRecognitionResult.zzaEe) || (!zzw.equal(this.zzaEb, localActivityRecognitionResult.zzaEb))) {
          bool = false;
        }
      }
    }
  }
  
  public int getActivityConfidence(int paramInt)
  {
    Iterator localIterator = this.zzaEb.iterator();
    DetectedActivity localDetectedActivity;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localDetectedActivity = (DetectedActivity)localIterator.next();
    } while (localDetectedActivity.getType() != paramInt);
    for (int i = localDetectedActivity.getConfidence();; i = 0) {
      return i;
    }
  }
  
  public long getElapsedRealtimeMillis()
  {
    return this.zzaEd;
  }
  
  public DetectedActivity getMostProbableActivity()
  {
    return (DetectedActivity)this.zzaEb.get(0);
  }
  
  public List<DetectedActivity> getProbableActivities()
  {
    return this.zzaEb;
  }
  
  public long getTime()
  {
    return this.zzaEc;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Long.valueOf(this.zzaEc);
    arrayOfObject[1] = Long.valueOf(this.zzaEd);
    arrayOfObject[2] = Integer.valueOf(this.zzaEe);
    arrayOfObject[3] = this.zzaEb;
    return zzw.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    return "ActivityRecognitionResult [probableActivities=" + this.zzaEb + ", timeMillis=" + this.zzaEc + ", elapsedRealtimeMillis=" + this.zzaEd + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ActivityRecognitionResultCreator.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/ActivityRecognitionResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */