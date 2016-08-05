package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.zza;
import java.util.Date;
import java.util.HashSet;

@zzgr
public final class zzfa
{
  public static int zza(AdRequest.ErrorCode paramErrorCode)
  {
    int i;
    switch (1.zzzU[paramErrorCode.ordinal()])
    {
    default: 
      i = 0;
    }
    for (;;)
    {
      return i;
      i = 1;
      continue;
      i = 2;
      continue;
      i = 3;
    }
  }
  
  public static AdSize zzb(AdSizeParcel paramAdSizeParcel)
  {
    int i = 0;
    AdSize[] arrayOfAdSize = new AdSize[6];
    arrayOfAdSize[i] = AdSize.SMART_BANNER;
    arrayOfAdSize[1] = AdSize.BANNER;
    arrayOfAdSize[2] = AdSize.IAB_MRECT;
    arrayOfAdSize[3] = AdSize.IAB_BANNER;
    arrayOfAdSize[4] = AdSize.IAB_LEADERBOARD;
    arrayOfAdSize[5] = AdSize.IAB_WIDE_SKYSCRAPER;
    if (i < arrayOfAdSize.length) {
      if ((arrayOfAdSize[i].getWidth() != paramAdSizeParcel.width) || (arrayOfAdSize[i].getHeight() != paramAdSizeParcel.height)) {}
    }
    for (AdSize localAdSize = arrayOfAdSize[i];; localAdSize = new AdSize(zza.zza(paramAdSizeParcel.width, paramAdSizeParcel.height, paramAdSizeParcel.zzte)))
    {
      return localAdSize;
      i++;
      break;
    }
  }
  
  public static MediationAdRequest zzh(AdRequestParcel paramAdRequestParcel)
  {
    if (paramAdRequestParcel.zzsD != null) {}
    for (HashSet localHashSet = new HashSet(paramAdRequestParcel.zzsD);; localHashSet = null) {
      return new MediationAdRequest(new Date(paramAdRequestParcel.zzsB), zzr(paramAdRequestParcel.zzsC), localHashSet, paramAdRequestParcel.zzsE, paramAdRequestParcel.zzsJ);
    }
  }
  
  public static AdRequest.Gender zzr(int paramInt)
  {
    AdRequest.Gender localGender;
    switch (paramInt)
    {
    default: 
      localGender = AdRequest.Gender.UNKNOWN;
    }
    for (;;)
    {
      return localGender;
      localGender = AdRequest.Gender.FEMALE;
      continue;
      localGender = AdRequest.Gender.MALE;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzfa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */