package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public final class zzee
{
  public final List<zzed> zzyW;
  public final long zzyX;
  public final List<String> zzyY;
  public final List<String> zzyZ;
  public final List<String> zzza;
  public final String zzzb;
  public final long zzzc;
  public final String zzzd;
  public final int zzze;
  public int zzzf;
  public int zzzg;
  
  public zzee(String paramString)
    throws JSONException
  {
    JSONObject localJSONObject1 = new JSONObject(paramString);
    if (zzb.zzN(2)) {
      zzb.v("Mediation Response JSON: " + localJSONObject1.toString(2));
    }
    JSONArray localJSONArray1 = localJSONObject1.getJSONArray("ad_networks");
    ArrayList localArrayList = new ArrayList(localJSONArray1.length());
    int i = -1;
    for (int j = 0; j < localJSONArray1.length(); j++)
    {
      zzed localzzed = new zzed(localJSONArray1.getJSONObject(j));
      localArrayList.add(localzzed);
      if ((i < 0) && (zza(localzzed))) {
        i = j;
      }
    }
    this.zzzf = i;
    this.zzzg = localJSONArray1.length();
    this.zzyW = Collections.unmodifiableList(localArrayList);
    this.zzzb = localJSONObject1.getString("qdata");
    JSONObject localJSONObject2 = localJSONObject1.optJSONObject("settings");
    long l2;
    JSONArray localJSONArray2;
    if (localJSONObject2 != null)
    {
      this.zzyX = localJSONObject2.optLong("ad_network_timeout_millis", -1L);
      this.zzyY = zzp.zzbH().zza(localJSONObject2, "click_urls");
      this.zzyZ = zzp.zzbH().zza(localJSONObject2, "imp_urls");
      this.zzza = zzp.zzbH().zza(localJSONObject2, "nofill_urls");
      long l1 = localJSONObject2.optLong("refresh", -1L);
      if (l1 > 0L)
      {
        l2 = l1 * 1000L;
        this.zzzc = l2;
        localJSONArray2 = localJSONObject2.optJSONArray("rewards");
        if ((localJSONArray2 != null) && (localJSONArray2.length() != 0)) {
          break label307;
        }
        this.zzzd = null;
        this.zzze = 0;
      }
    }
    for (;;)
    {
      return;
      l2 = -1L;
      break;
      label307:
      this.zzzd = localJSONArray2.getJSONObject(0).optString("rb_type");
      this.zzze = localJSONArray2.getJSONObject(0).optInt("rb_amount");
      continue;
      this.zzyX = -1L;
      this.zzyY = null;
      this.zzyZ = null;
      this.zzza = null;
      this.zzzc = -1L;
      this.zzzd = null;
      this.zzze = 0;
    }
  }
  
  private boolean zza(zzed paramzzed)
  {
    Iterator localIterator = paramzzed.zzyO.iterator();
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
    } while (!((String)localIterator.next()).equals("com.google.ads.mediation.admob.AdMobAdapter"));
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */