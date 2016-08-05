package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.zzc;
import com.google.android.gms.location.zzc.zza;
import com.google.android.gms.location.zzd;
import com.google.android.gms.location.zzd.zza;

public class LocationRequestUpdateData
  implements SafeParcelable
{
  public static final zzn CREATOR = new zzn();
  PendingIntent mPendingIntent;
  private final int mVersionCode;
  int zzaFJ;
  LocationRequestInternal zzaFK;
  zzd zzaFL;
  zzc zzaFM;
  zzg zzaFN;
  
  LocationRequestUpdateData(int paramInt1, int paramInt2, LocationRequestInternal paramLocationRequestInternal, IBinder paramIBinder1, PendingIntent paramPendingIntent, IBinder paramIBinder2, IBinder paramIBinder3)
  {
    this.mVersionCode = paramInt1;
    this.zzaFJ = paramInt2;
    this.zzaFK = paramLocationRequestInternal;
    zzd localzzd;
    zzc localzzc;
    if (paramIBinder1 == null)
    {
      localzzd = null;
      this.zzaFL = localzzd;
      this.mPendingIntent = paramPendingIntent;
      if (paramIBinder2 != null) {
        break label78;
      }
      localzzc = null;
      label50:
      this.zzaFM = localzzc;
      if (paramIBinder3 != null) {
        break label88;
      }
    }
    for (;;)
    {
      this.zzaFN = localzzg;
      return;
      localzzd = zzd.zza.zzbX(paramIBinder1);
      break;
      label78:
      localzzc = zzc.zza.zzbW(paramIBinder2);
      break label50;
      label88:
      localzzg = zzg.zza.zzbZ(paramIBinder3);
    }
  }
  
  public static LocationRequestUpdateData zza(LocationRequestInternal paramLocationRequestInternal, PendingIntent paramPendingIntent, zzg paramzzg)
  {
    if (paramzzg != null) {}
    for (IBinder localIBinder = paramzzg.asBinder();; localIBinder = null) {
      return new LocationRequestUpdateData(1, 1, paramLocationRequestInternal, null, paramPendingIntent, null, localIBinder);
    }
  }
  
  public static LocationRequestUpdateData zza(LocationRequestInternal paramLocationRequestInternal, zzc paramzzc, zzg paramzzg)
  {
    IBinder localIBinder1 = paramzzc.asBinder();
    if (paramzzg != null) {}
    for (IBinder localIBinder2 = paramzzg.asBinder();; localIBinder2 = null) {
      return new LocationRequestUpdateData(1, 1, paramLocationRequestInternal, null, null, localIBinder1, localIBinder2);
    }
  }
  
  public static LocationRequestUpdateData zza(LocationRequestInternal paramLocationRequestInternal, zzd paramzzd, zzg paramzzg)
  {
    IBinder localIBinder1 = paramzzd.asBinder();
    if (paramzzg != null) {}
    for (IBinder localIBinder2 = paramzzg.asBinder();; localIBinder2 = null) {
      return new LocationRequestUpdateData(1, 1, paramLocationRequestInternal, localIBinder1, null, null, localIBinder2);
    }
  }
  
  public static LocationRequestUpdateData zza(zzc paramzzc, zzg paramzzg)
  {
    IBinder localIBinder1 = paramzzc.asBinder();
    if (paramzzg != null) {}
    for (IBinder localIBinder2 = paramzzg.asBinder();; localIBinder2 = null) {
      return new LocationRequestUpdateData(1, 2, null, null, null, localIBinder1, localIBinder2);
    }
  }
  
  public static LocationRequestUpdateData zza(zzd paramzzd, zzg paramzzg)
  {
    IBinder localIBinder1 = paramzzd.asBinder();
    if (paramzzg != null) {}
    for (IBinder localIBinder2 = paramzzg.asBinder();; localIBinder2 = null) {
      return new LocationRequestUpdateData(1, 2, null, localIBinder1, null, null, localIBinder2);
    }
  }
  
  public static LocationRequestUpdateData zzb(PendingIntent paramPendingIntent, zzg paramzzg)
  {
    if (paramzzg != null) {}
    for (IBinder localIBinder = paramzzg.asBinder();; localIBinder = null) {
      return new LocationRequestUpdateData(1, 2, null, null, paramPendingIntent, null, localIBinder);
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzn.zza(this, paramParcel, paramInt);
  }
  
  IBinder zzwF()
  {
    if (this.zzaFL == null) {}
    for (IBinder localIBinder = null;; localIBinder = this.zzaFL.asBinder()) {
      return localIBinder;
    }
  }
  
  IBinder zzwG()
  {
    if (this.zzaFM == null) {}
    for (IBinder localIBinder = null;; localIBinder = this.zzaFM.asBinder()) {
      return localIBinder;
    }
  }
  
  IBinder zzwH()
  {
    if (this.zzaFN == null) {}
    for (IBinder localIBinder = null;; localIBinder = this.zzaFN.asBinder()) {
      return localIBinder;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/internal/LocationRequestUpdateData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */