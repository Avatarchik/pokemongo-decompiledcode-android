package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public final class zzed
{
  public final String zzyM;
  public final String zzyN;
  public final List<String> zzyO;
  public final String zzyP;
  public final String zzyQ;
  public final List<String> zzyR;
  public final List<String> zzyS;
  public final String zzyT;
  public final List<String> zzyU;
  public final List<String> zzyV;
  
  public zzed(JSONObject paramJSONObject)
    throws JSONException
  {
    this.zzyN = paramJSONObject.getString("id");
    JSONArray localJSONArray = paramJSONObject.getJSONArray("adapters");
    ArrayList localArrayList = new ArrayList(localJSONArray.length());
    for (int i = 0; i < localJSONArray.length(); i++) {
      localArrayList.add(localJSONArray.getString(i));
    }
    this.zzyO = Collections.unmodifiableList(localArrayList);
    this.zzyP = paramJSONObject.optString("allocation_id", null);
    this.zzyR = zzp.zzbH().zza(paramJSONObject, "clickurl");
    this.zzyS = zzp.zzbH().zza(paramJSONObject, "imp_urls");
    this.zzyU = zzp.zzbH().zza(paramJSONObject, "video_start_urls");
    this.zzyV = zzp.zzbH().zza(paramJSONObject, "video_complete_urls");
    JSONObject localJSONObject1 = paramJSONObject.optJSONObject("ad");
    String str2;
    JSONObject localJSONObject2;
    if (localJSONObject1 != null)
    {
      str2 = localJSONObject1.toString();
      this.zzyM = str2;
      localJSONObject2 = paramJSONObject.optJSONObject("data");
      if (localJSONObject2 == null) {
        break label217;
      }
    }
    label217:
    for (String str3 = localJSONObject2.toString();; str3 = null)
    {
      this.zzyT = str3;
      if (localJSONObject2 != null) {
        str1 = localJSONObject2.optString("class_name");
      }
      this.zzyQ = str1;
      return;
      str2 = null;
      break;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */