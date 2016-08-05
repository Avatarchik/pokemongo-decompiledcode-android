package com.google.android.gms.location.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

public class ParcelableGeofence
  implements SafeParcelable, Geofence
{
  public static final zzo CREATOR = new zzo();
  private final int mVersionCode;
  private final String zzBY;
  private final int zzaEi;
  private final short zzaEk;
  private final double zzaEl;
  private final double zzaEm;
  private final float zzaEn;
  private final int zzaEo;
  private final int zzaEp;
  private final long zzaFO;
  
  public ParcelableGeofence(int paramInt1, String paramString, int paramInt2, short paramShort, double paramDouble1, double paramDouble2, float paramFloat, long paramLong, int paramInt3, int paramInt4)
  {
    zzdx(paramString);
    zze(paramFloat);
    zza(paramDouble1, paramDouble2);
    int i = zzhc(paramInt2);
    this.mVersionCode = paramInt1;
    this.zzaEk = paramShort;
    this.zzBY = paramString;
    this.zzaEl = paramDouble1;
    this.zzaEm = paramDouble2;
    this.zzaEn = paramFloat;
    this.zzaFO = paramLong;
    this.zzaEi = i;
    this.zzaEo = paramInt3;
    this.zzaEp = paramInt4;
  }
  
  public ParcelableGeofence(String paramString, int paramInt1, short paramShort, double paramDouble1, double paramDouble2, float paramFloat, long paramLong, int paramInt2, int paramInt3)
  {
    this(1, paramString, paramInt1, paramShort, paramDouble1, paramDouble2, paramFloat, paramLong, paramInt2, paramInt3);
  }
  
  private static void zza(double paramDouble1, double paramDouble2)
  {
    if ((paramDouble1 > 90.0D) || (paramDouble1 < -90.0D)) {
      throw new IllegalArgumentException("invalid latitude: " + paramDouble1);
    }
    if ((paramDouble2 > 180.0D) || (paramDouble2 < -180.0D)) {
      throw new IllegalArgumentException("invalid longitude: " + paramDouble2);
    }
  }
  
  private static void zzdx(String paramString)
  {
    if ((paramString == null) || (paramString.length() > 100)) {
      throw new IllegalArgumentException("requestId is null or too long: " + paramString);
    }
  }
  
  private static void zze(float paramFloat)
  {
    if (paramFloat <= 0.0F) {
      throw new IllegalArgumentException("invalid radius: " + paramFloat);
    }
  }
  
  private static int zzhc(int paramInt)
  {
    int i = paramInt & 0x7;
    if (i == 0) {
      throw new IllegalArgumentException("No supported transition specified: " + paramInt);
    }
    return i;
  }
  
  private static String zzhd(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (String str = null;; str = "CIRCLE") {
      return str;
    }
  }
  
  public static ParcelableGeofence zzn(byte[] paramArrayOfByte)
  {
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
    localParcel.setDataPosition(0);
    ParcelableGeofence localParcelableGeofence = CREATOR.zzeJ(localParcel);
    localParcel.recycle();
    return localParcelableGeofence;
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
      if (paramObject == null)
      {
        bool = false;
      }
      else if (!(paramObject instanceof ParcelableGeofence))
      {
        bool = false;
      }
      else
      {
        ParcelableGeofence localParcelableGeofence = (ParcelableGeofence)paramObject;
        if (this.zzaEn != localParcelableGeofence.zzaEn) {
          bool = false;
        } else if (this.zzaEl != localParcelableGeofence.zzaEl) {
          bool = false;
        } else if (this.zzaEm != localParcelableGeofence.zzaEm) {
          bool = false;
        } else if (this.zzaEk != localParcelableGeofence.zzaEk) {
          bool = false;
        }
      }
    }
  }
  
  public long getExpirationTime()
  {
    return this.zzaFO;
  }
  
  public double getLatitude()
  {
    return this.zzaEl;
  }
  
  public double getLongitude()
  {
    return this.zzaEm;
  }
  
  public int getNotificationResponsiveness()
  {
    return this.zzaEo;
  }
  
  public String getRequestId()
  {
    return this.zzBY;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    long l1 = Double.doubleToLongBits(this.zzaEl);
    int i = 31 + (int)(l1 ^ l1 >>> 32);
    long l2 = Double.doubleToLongBits(this.zzaEm);
    return 31 * (31 * (31 * (i * 31 + (int)(l2 ^ l2 >>> 32)) + Float.floatToIntBits(this.zzaEn)) + this.zzaEk) + this.zzaEi;
  }
  
  public String toString()
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = zzhd(this.zzaEk);
    arrayOfObject[1] = this.zzBY;
    arrayOfObject[2] = Integer.valueOf(this.zzaEi);
    arrayOfObject[3] = Double.valueOf(this.zzaEl);
    arrayOfObject[4] = Double.valueOf(this.zzaEm);
    arrayOfObject[5] = Float.valueOf(this.zzaEn);
    arrayOfObject[6] = Integer.valueOf(this.zzaEo / 1000);
    arrayOfObject[7] = Integer.valueOf(this.zzaEp);
    arrayOfObject[8] = Long.valueOf(this.zzaFO);
    return String.format(localLocale, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", arrayOfObject);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzo.zza(this, paramParcel, paramInt);
  }
  
  public short zzwI()
  {
    return this.zzaEk;
  }
  
  public float zzwJ()
  {
    return this.zzaEn;
  }
  
  public int zzwK()
  {
    return this.zzaEi;
  }
  
  public int zzwL()
  {
    return this.zzaEp;
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/location/internal/ParcelableGeofence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */