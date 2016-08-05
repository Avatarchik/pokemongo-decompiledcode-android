package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

@zzgr
public class zzh
{
  public static final zzh zztd = new zzh();
  
  public static zzh zzcB()
  {
    return zztd;
  }
  
  public AdRequestParcel zza(Context paramContext, zzy paramzzy)
  {
    Date localDate = paramzzy.getBirthday();
    long l;
    String str1;
    int i;
    List localList;
    label57:
    boolean bool1;
    int j;
    Location localLocation;
    Bundle localBundle;
    boolean bool2;
    String str2;
    SearchAdRequest localSearchAdRequest;
    if (localDate != null)
    {
      l = localDate.getTime();
      str1 = paramzzy.getContentUrl();
      i = paramzzy.getGender();
      Set localSet = paramzzy.getKeywords();
      if (localSet.isEmpty()) {
        break label223;
      }
      localList = Collections.unmodifiableList(new ArrayList(localSet));
      bool1 = paramzzy.isTestDevice(paramContext);
      j = paramzzy.zzcQ();
      localLocation = paramzzy.getLocation();
      localBundle = paramzzy.getNetworkExtrasBundle(AdMobAdapter.class);
      bool2 = paramzzy.getManualImpressionsEnabled();
      str2 = paramzzy.getPublisherProvidedId();
      localSearchAdRequest = paramzzy.zzcN();
      if (localSearchAdRequest == null) {
        break label229;
      }
    }
    label223:
    label229:
    for (SearchAdRequestParcel localSearchAdRequestParcel = new SearchAdRequestParcel(localSearchAdRequest);; localSearchAdRequestParcel = null)
    {
      String str3 = null;
      Context localContext = paramContext.getApplicationContext();
      if (localContext != null)
      {
        String str4 = localContext.getPackageName();
        str3 = zzp.zzbv().zza(Thread.currentThread().getStackTrace(), str4);
      }
      return new AdRequestParcel(6, l, localBundle, i, localList, bool1, j, bool2, str2, localSearchAdRequestParcel, localLocation, str1, paramzzy.zzcP(), paramzzy.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(paramzzy.zzcR())), paramzzy.zzcM(), str3);
      l = -1L;
      break;
      localList = null;
      break label57;
    }
  }
  
  public RewardedVideoAdRequestParcel zza(Context paramContext, zzy paramzzy, String paramString)
  {
    return new RewardedVideoAdRequestParcel(zza(paramContext, paramzzy), paramString);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/client/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */