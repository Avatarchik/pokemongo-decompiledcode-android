package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.ads.internal.formats.zza;
import com.google.android.gms.ads.internal.formats.zze;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public class zzgo
  implements zzgm.zza<zze>
{
  private final boolean zzEa;
  private final boolean zzEb;
  
  public zzgo(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.zzEa = paramBoolean1;
    this.zzEb = paramBoolean2;
  }
  
  public zze zzc(zzgm paramzzgm, JSONObject paramJSONObject)
    throws JSONException, InterruptedException, ExecutionException
  {
    List localList = paramzzgm.zza(paramJSONObject, "images", true, this.zzEa, this.zzEb);
    zziq localzziq1 = paramzzgm.zza(paramJSONObject, "secondary_image", false, this.zzEa);
    zziq localzziq2 = paramzzgm.zze(paramJSONObject);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(((zziq)localIterator.next()).get());
    }
    return new zze(paramJSONObject.getString("headline"), localArrayList, paramJSONObject.getString("body"), (zzcm)localzziq1.get(), paramJSONObject.getString("call_to_action"), paramJSONObject.getString("advertiser"), (zza)localzziq2.get(), new Bundle());
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */