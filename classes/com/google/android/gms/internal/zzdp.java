package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public class zzdp
  implements zzdk
{
  final HashMap<String, zzin<JSONObject>> zzxP = new HashMap();
  
  public Future<JSONObject> zzY(String paramString)
  {
    zzin localzzin = new zzin();
    this.zzxP.put(paramString, localzzin);
    return localzzin;
  }
  
  public void zzZ(String paramString)
  {
    zzin localzzin = (zzin)this.zzxP.get(paramString);
    if (localzzin == null) {
      zzb.e("Could not find the ad request for the corresponding ad response.");
    }
    for (;;)
    {
      return;
      if (!localzzin.isDone()) {
        localzzin.cancel(true);
      }
      this.zzxP.remove(paramString);
    }
  }
  
  public void zza(zziz paramzziz, Map<String, String> paramMap)
  {
    zzf((String)paramMap.get("request_id"), (String)paramMap.get("fetched_ad"));
  }
  
  public void zzf(String paramString1, String paramString2)
  {
    zzb.zzaF("Received ad from the cache.");
    zzin localzzin = (zzin)this.zzxP.get(paramString1);
    if (localzzin == null) {
      zzb.e("Could not find the ad request for the corresponding ad response.");
    }
    for (;;)
    {
      return;
      try
      {
        localzzin.zzf(new JSONObject(paramString2));
        this.zzxP.remove(paramString1);
      }
      catch (JSONException localJSONException)
      {
        zzb.zzb("Failed constructing JSON object from value passed from javascript", localJSONException);
        localzzin.zzf(null);
        this.zzxP.remove(paramString1);
      }
      finally
      {
        this.zzxP.remove(paramString1);
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */