package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.zzbb;
import com.google.android.gms.internal.zzbe;
import com.google.android.gms.internal.zzbr;
import com.google.android.gms.internal.zzbu;
import com.google.android.gms.internal.zzby;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.internal.zzdl;
import com.google.android.gms.internal.zzdp;
import com.google.android.gms.internal.zzdz;
import com.google.android.gms.internal.zzdz.zzb;
import com.google.android.gms.internal.zzdz.zzd;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzgu;
import com.google.android.gms.internal.zzgz;
import com.google.android.gms.internal.zzhs.zza;
import com.google.android.gms.internal.zzhz;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zzis.zza;
import com.google.android.gms.internal.zzis.zzc;
import com.google.android.gms.internal.zziz;
import com.google.android.gms.internal.zzmn;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public class zzm
  extends zzhz
{
  static final long zzFi = TimeUnit.SECONDS.toMillis(10L);
  private static boolean zzFj = false;
  private static zzdz zzFk = null;
  private static zzdl zzFl = null;
  private static zzdp zzFm = null;
  private static zzdk zzFn = null;
  private static final Object zzpy = new Object();
  private final Context mContext;
  private final Object zzDh = new Object();
  private final zza.zza zzEe;
  private final AdRequestInfoParcel.zza zzEf;
  private zzdz.zzd zzFo;
  
  public zzm(Context paramContext, AdRequestInfoParcel.zza paramzza, zza.zza paramzza1)
  {
    super(true);
    this.zzEe = paramzza1;
    this.mContext = paramContext;
    this.zzEf = paramzza;
    synchronized (zzpy)
    {
      if (!zzFj)
      {
        zzFm = new zzdp();
        zzFl = new zzdl(paramContext.getApplicationContext(), paramzza.zzqj);
        zzFn = new zzc();
        zzFk = new zzdz(this.mContext.getApplicationContext(), this.zzEf.zzqj, (String)zzby.zzul.get(), new zzb(), new zza());
        zzFj = true;
      }
      return;
    }
  }
  
  private JSONObject zza(AdRequestInfoParcel paramAdRequestInfoParcel, String paramString)
  {
    Object localObject = null;
    Bundle localBundle = paramAdRequestInfoParcel.zzEn.extras.getBundle("sdk_less_server_data");
    String str = paramAdRequestInfoParcel.zzEn.extras.getString("sdk_less_network_id");
    if (localBundle == null) {}
    for (;;)
    {
      return (JSONObject)localObject;
      JSONObject localJSONObject1 = zzgu.zza(this.mContext, paramAdRequestInfoParcel, zzp.zzbB().zzC(this.mContext), null, null, new zzbr((String)zzby.zzul.get()), null, null, new ArrayList());
      if (localJSONObject1 == null) {
        continue;
      }
      try
      {
        AdvertisingIdClient.Info localInfo2 = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
        localInfo1 = localInfo2;
        localHashMap = new HashMap();
        localHashMap.put("request_id", paramString);
        localHashMap.put("network_id", str);
        localHashMap.put("request_param", localJSONObject1);
        localHashMap.put("data", localBundle);
        if (localInfo1 != null)
        {
          localHashMap.put("adid", localInfo1.getId());
          if (!localInfo1.isLimitAdTrackingEnabled()) {
            break label223;
          }
          i = 1;
          localHashMap.put("lat", Integer.valueOf(i));
        }
      }
      catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
      {
        for (;;)
        {
          try
          {
            HashMap localHashMap;
            JSONObject localJSONObject2 = zzp.zzbv().zzz(localHashMap);
            localObject = localJSONObject2;
          }
          catch (JSONException localJSONException) {}
          localGooglePlayServicesRepairableException = localGooglePlayServicesRepairableException;
          zzb.zzd("Cannot get advertising id info", localGooglePlayServicesRepairableException);
          AdvertisingIdClient.Info localInfo1 = null;
          continue;
          int i = 0;
        }
      }
      catch (IllegalStateException localIllegalStateException)
      {
        for (;;) {}
      }
      catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
      {
        for (;;) {}
      }
      catch (IOException localIOException)
      {
        label223:
        for (;;) {}
      }
    }
  }
  
  protected static void zzc(zzbb paramzzbb)
  {
    paramzzbb.zza("/loadAd", zzFm);
    paramzzbb.zza("/fetchHttpRequest", zzFl);
    paramzzbb.zza("/invalidRequest", zzFn);
  }
  
  protected static void zzd(zzbb paramzzbb)
  {
    paramzzbb.zzb("/loadAd", zzFm);
    paramzzbb.zzb("/fetchHttpRequest", zzFl);
    paramzzbb.zzb("/invalidRequest", zzFn);
  }
  
  private AdResponseParcel zzf(AdRequestInfoParcel paramAdRequestInfoParcel)
  {
    final String str = UUID.randomUUID().toString();
    final JSONObject localJSONObject1 = zza(paramAdRequestInfoParcel, str);
    AdResponseParcel localAdResponseParcel;
    if (localJSONObject1 == null) {
      localAdResponseParcel = new AdResponseParcel(0);
    }
    for (;;)
    {
      return localAdResponseParcel;
      long l1 = zzp.zzbz().elapsedRealtime();
      Future localFuture = zzFm.zzY(str);
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          zzm.zza(zzm.this, zzm.zzfO().zzdO());
          zzm.zzb(zzm.this).zza(new zzis.zzc()new zzis.zza
          {
            public void zzb(zzbe paramAnonymous2zzbe)
            {
              try
              {
                paramAnonymous2zzbe.zza("AFMA_getAdapterLessMediationAd", zzm.2.this.zzFq);
                return;
              }
              catch (Exception localException)
              {
                for (;;)
                {
                  zzb.zzb("Error requesting an ad url", localException);
                  zzm.zzfN().zzZ(zzm.2.this.zzFr);
                }
              }
            }
          }, new zzis.zza()
          {
            public void run()
            {
              zzm.zzfN().zzZ(zzm.2.this.zzFr);
            }
          });
        }
      });
      long l2 = zzFi - (zzp.zzbz().elapsedRealtime() - l1);
      try
      {
        localJSONObject2 = (JSONObject)localFuture.get(l2, TimeUnit.MILLISECONDS);
        if (localJSONObject2 == null) {
          localAdResponseParcel = new AdResponseParcel(-1);
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        localAdResponseParcel = new AdResponseParcel(-1);
      }
      catch (TimeoutException localTimeoutException)
      {
        localAdResponseParcel = new AdResponseParcel(2);
      }
      catch (ExecutionException localExecutionException)
      {
        JSONObject localJSONObject2;
        localAdResponseParcel = new AdResponseParcel(0);
        continue;
        localAdResponseParcel = zzgu.zza(this.mContext, paramAdRequestInfoParcel, localJSONObject2.toString());
        if ((localAdResponseParcel.errorCode == -3) || (!TextUtils.isEmpty(localAdResponseParcel.body))) {
          continue;
        }
        localAdResponseParcel = new AdResponseParcel(3);
      }
      catch (CancellationException localCancellationException)
      {
        for (;;) {}
      }
    }
  }
  
  public void onStop()
  {
    synchronized (this.zzDh)
    {
      zza.zzJt.post(new Runnable()
      {
        public void run()
        {
          if (zzm.zzb(zzm.this) != null)
          {
            zzm.zzb(zzm.this).release();
            zzm.zza(zzm.this, null);
          }
        }
      });
      return;
    }
  }
  
  public void zzbn()
  {
    zzb.zzaF("SdkLessAdLoaderBackgroundTask started.");
    AdRequestInfoParcel localAdRequestInfoParcel = new AdRequestInfoParcel(this.zzEf, null, -1L);
    AdResponseParcel localAdResponseParcel = zzf(localAdRequestInfoParcel);
    long l = zzp.zzbz().elapsedRealtime();
    final zzhs.zza localzza = new zzhs.zza(localAdRequestInfoParcel, localAdResponseParcel, null, null, localAdResponseParcel.errorCode, l, localAdResponseParcel.zzEO, null);
    zza.zzJt.post(new Runnable()
    {
      public void run()
      {
        zzm.zza(zzm.this).zza(localzza);
        if (zzm.zzb(zzm.this) != null)
        {
          zzm.zzb(zzm.this).release();
          zzm.zza(zzm.this, null);
        }
      }
    });
  }
  
  public static class zza
    implements zzdz.zzb<zzbb>
  {
    public void zza(zzbb paramzzbb)
    {
      zzm.zzd(paramzzbb);
    }
  }
  
  public static class zzb
    implements zzdz.zzb<zzbb>
  {
    public void zza(zzbb paramzzbb)
    {
      zzm.zzc(paramzzbb);
    }
  }
  
  public static class zzc
    implements zzdk
  {
    public void zza(zziz paramzziz, Map<String, String> paramMap)
    {
      String str1 = (String)paramMap.get("request_id");
      String str2 = (String)paramMap.get("errors");
      zzb.zzaH("Invalid request: " + str2);
      zzm.zzfN().zzZ(str1);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/request/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */