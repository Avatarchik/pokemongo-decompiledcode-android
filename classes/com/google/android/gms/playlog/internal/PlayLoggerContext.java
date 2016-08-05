package com.google.android.gms.playlog.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;

public class PlayLoggerContext
  implements SafeParcelable
{
  public static final zze CREATOR = new zze();
  public final String packageName;
  public final int versionCode;
  public final int zzaRR;
  public final int zzaRS;
  public final String zzaRT;
  public final String zzaRU;
  public final boolean zzaRV;
  public final String zzaRW;
  public final boolean zzaRX;
  public final int zzaRY;
  
  public PlayLoggerContext(int paramInt1, String paramString1, int paramInt2, int paramInt3, String paramString2, String paramString3, boolean paramBoolean1, String paramString4, boolean paramBoolean2, int paramInt4)
  {
    this.versionCode = paramInt1;
    this.packageName = paramString1;
    this.zzaRR = paramInt2;
    this.zzaRS = paramInt3;
    this.zzaRT = paramString2;
    this.zzaRU = paramString3;
    this.zzaRV = paramBoolean1;
    this.zzaRW = paramString4;
    this.zzaRX = paramBoolean2;
    this.zzaRY = paramInt4;
  }
  
  @Deprecated
  public PlayLoggerContext(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, boolean paramBoolean)
  {
    this.versionCode = 1;
    this.packageName = ((String)zzx.zzw(paramString1));
    this.zzaRR = paramInt1;
    this.zzaRS = paramInt2;
    this.zzaRW = null;
    this.zzaRT = paramString2;
    this.zzaRU = paramString3;
    this.zzaRV = paramBoolean;
    this.zzaRX = false;
    this.zzaRY = 0;
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
      if ((paramObject instanceof PlayLoggerContext))
      {
        PlayLoggerContext localPlayLoggerContext = (PlayLoggerContext)paramObject;
        if ((this.versionCode != localPlayLoggerContext.versionCode) || (!this.packageName.equals(localPlayLoggerContext.packageName)) || (this.zzaRR != localPlayLoggerContext.zzaRR) || (this.zzaRS != localPlayLoggerContext.zzaRS) || (!zzw.equal(this.zzaRW, localPlayLoggerContext.zzaRW)) || (!zzw.equal(this.zzaRT, localPlayLoggerContext.zzaRT)) || (!zzw.equal(this.zzaRU, localPlayLoggerContext.zzaRU)) || (this.zzaRV != localPlayLoggerContext.zzaRV) || (this.zzaRX != localPlayLoggerContext.zzaRX) || (this.zzaRY != localPlayLoggerContext.zzaRY)) {
          bool = false;
        }
      }
      else
      {
        bool = false;
      }
    }
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[10];
    arrayOfObject[0] = Integer.valueOf(this.versionCode);
    arrayOfObject[1] = this.packageName;
    arrayOfObject[2] = Integer.valueOf(this.zzaRR);
    arrayOfObject[3] = Integer.valueOf(this.zzaRS);
    arrayOfObject[4] = this.zzaRW;
    arrayOfObject[5] = this.zzaRT;
    arrayOfObject[6] = this.zzaRU;
    arrayOfObject[7] = Boolean.valueOf(this.zzaRV);
    arrayOfObject[8] = Boolean.valueOf(this.zzaRX);
    arrayOfObject[9] = Integer.valueOf(this.zzaRY);
    return zzw.hashCode(arrayOfObject);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PlayLoggerContext[");
    localStringBuilder.append("versionCode=").append(this.versionCode).append(',');
    localStringBuilder.append("package=").append(this.packageName).append(',');
    localStringBuilder.append("packageVersionCode=").append(this.zzaRR).append(',');
    localStringBuilder.append("logSource=").append(this.zzaRS).append(',');
    localStringBuilder.append("logSourceName=").append(this.zzaRW).append(',');
    localStringBuilder.append("uploadAccount=").append(this.zzaRT).append(',');
    localStringBuilder.append("loggingId=").append(this.zzaRU).append(',');
    localStringBuilder.append("logAndroidId=").append(this.zzaRV).append(',');
    localStringBuilder.append("isAnonymous=").append(this.zzaRX).append(',');
    localStringBuilder.append("qosTier=").append(this.zzaRY);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/playlog/internal/PlayLoggerContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */