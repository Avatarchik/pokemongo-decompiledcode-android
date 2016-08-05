package com.google.android.gms.ads.internal.client;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.internal.zzgr;
import java.util.List;

@zzgr
public final class AdRequestParcel
  implements SafeParcelable
{
  public static final zzg CREATOR = new zzg();
  public final Bundle extras;
  public final int versionCode;
  public final long zzsB;
  public final int zzsC;
  public final List<String> zzsD;
  public final boolean zzsE;
  public final int zzsF;
  public final boolean zzsG;
  public final String zzsH;
  public final SearchAdRequestParcel zzsI;
  public final Location zzsJ;
  public final String zzsK;
  public final Bundle zzsL;
  public final Bundle zzsM;
  public final List<String> zzsN;
  public final String zzsO;
  public final String zzsP;
  
  public AdRequestParcel(int paramInt1, long paramLong, Bundle paramBundle1, int paramInt2, List<String> paramList1, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, String paramString1, SearchAdRequestParcel paramSearchAdRequestParcel, Location paramLocation, String paramString2, Bundle paramBundle2, Bundle paramBundle3, List<String> paramList2, String paramString3, String paramString4)
  {
    this.versionCode = paramInt1;
    this.zzsB = paramLong;
    if (paramBundle1 == null) {
      paramBundle1 = new Bundle();
    }
    this.extras = paramBundle1;
    this.zzsC = paramInt2;
    this.zzsD = paramList1;
    this.zzsE = paramBoolean1;
    this.zzsF = paramInt3;
    this.zzsG = paramBoolean2;
    this.zzsH = paramString1;
    this.zzsI = paramSearchAdRequestParcel;
    this.zzsJ = paramLocation;
    this.zzsK = paramString2;
    this.zzsL = paramBundle2;
    this.zzsM = paramBundle3;
    this.zzsN = paramList2;
    this.zzsO = paramString3;
    this.zzsP = paramString4;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = false;
    if (!(paramObject instanceof AdRequestParcel)) {}
    for (;;)
    {
      return bool;
      AdRequestParcel localAdRequestParcel = (AdRequestParcel)paramObject;
      if ((this.versionCode == localAdRequestParcel.versionCode) && (this.zzsB == localAdRequestParcel.zzsB) && (zzw.equal(this.extras, localAdRequestParcel.extras)) && (this.zzsC == localAdRequestParcel.zzsC) && (zzw.equal(this.zzsD, localAdRequestParcel.zzsD)) && (this.zzsE == localAdRequestParcel.zzsE) && (this.zzsF == localAdRequestParcel.zzsF) && (this.zzsG == localAdRequestParcel.zzsG) && (zzw.equal(this.zzsH, localAdRequestParcel.zzsH)) && (zzw.equal(this.zzsI, localAdRequestParcel.zzsI)) && (zzw.equal(this.zzsJ, localAdRequestParcel.zzsJ)) && (zzw.equal(this.zzsK, localAdRequestParcel.zzsK)) && (zzw.equal(this.zzsL, localAdRequestParcel.zzsL)) && (zzw.equal(this.zzsM, localAdRequestParcel.zzsM)) && (zzw.equal(this.zzsN, localAdRequestParcel.zzsN)) && (zzw.equal(this.zzsO, localAdRequestParcel.zzsO)) && (zzw.equal(this.zzsP, localAdRequestParcel.zzsP))) {
        bool = true;
      }
    }
  }
  
  public int hashCode()
  {
    Object[] arrayOfObject = new Object[17];
    arrayOfObject[0] = Integer.valueOf(this.versionCode);
    arrayOfObject[1] = Long.valueOf(this.zzsB);
    arrayOfObject[2] = this.extras;
    arrayOfObject[3] = Integer.valueOf(this.zzsC);
    arrayOfObject[4] = this.zzsD;
    arrayOfObject[5] = Boolean.valueOf(this.zzsE);
    arrayOfObject[6] = Integer.valueOf(this.zzsF);
    arrayOfObject[7] = Boolean.valueOf(this.zzsG);
    arrayOfObject[8] = this.zzsH;
    arrayOfObject[9] = this.zzsI;
    arrayOfObject[10] = this.zzsJ;
    arrayOfObject[11] = this.zzsK;
    arrayOfObject[12] = this.zzsL;
    arrayOfObject[13] = this.zzsM;
    arrayOfObject[14] = this.zzsN;
    arrayOfObject[15] = this.zzsO;
    arrayOfObject[16] = this.zzsP;
    return zzw.hashCode(arrayOfObject);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzg.zza(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/AdRequestParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */