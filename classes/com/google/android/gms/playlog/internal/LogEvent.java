package com.google.android.gms.playlog.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;
import java.util.Set;

public class LogEvent
  implements SafeParcelable
{
  public static final zzc CREATOR = new zzc();
  public final String tag;
  public final int versionCode;
  public final long zzaRG;
  public final long zzaRH;
  public final byte[] zzaRI;
  public final Bundle zzaRJ;
  
  LogEvent(int paramInt, long paramLong1, long paramLong2, String paramString, byte[] paramArrayOfByte, Bundle paramBundle)
  {
    this.versionCode = paramInt;
    this.zzaRG = paramLong1;
    this.zzaRH = paramLong2;
    this.tag = paramString;
    this.zzaRI = paramArrayOfByte;
    this.zzaRJ = paramBundle;
  }
  
  public LogEvent(long paramLong1, long paramLong2, String paramString, byte[] paramArrayOfByte, String... paramVarArgs)
  {
    this.versionCode = 1;
    this.zzaRG = paramLong1;
    this.zzaRH = paramLong2;
    this.tag = paramString;
    this.zzaRI = paramArrayOfByte;
    this.zzaRJ = zzd(paramVarArgs);
  }
  
  private static Bundle zzd(String... paramVarArgs)
  {
    Bundle localBundle = null;
    if (paramVarArgs == null) {}
    for (;;)
    {
      return localBundle;
      if (paramVarArgs.length % 2 != 0) {
        throw new IllegalArgumentException("extras must have an even number of elements");
      }
      int i = paramVarArgs.length / 2;
      if (i != 0)
      {
        localBundle = new Bundle(i);
        for (int j = 0; j < i; j++) {
          localBundle.putString(paramVarArgs[(j * 2)], paramVarArgs[(1 + j * 2)]);
        }
      }
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("tag=").append(this.tag).append(",");
    localStringBuilder.append("eventTime=").append(this.zzaRG).append(",");
    localStringBuilder.append("eventUptime=").append(this.zzaRH).append(",");
    if ((this.zzaRJ != null) && (!this.zzaRJ.isEmpty()))
    {
      localStringBuilder.append("keyValues=");
      Iterator localIterator = this.zzaRJ.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localStringBuilder.append("(").append(str).append(",");
        localStringBuilder.append(this.zzaRJ.getString(str)).append(")");
        localStringBuilder.append(" ");
      }
    }
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/playlog/internal/LogEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */