package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.SearchAdRequestParcel;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.CapabilityParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.appdatasearch.DocumentContents;
import com.google.android.gms.appdatasearch.DocumentSection;
import com.google.android.gms.appdatasearch.UsageInfo;
import com.google.android.gms.appindexing.AndroidAppUri;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public final class zzgu
{
  private static final SimpleDateFormat zzFN = new SimpleDateFormat("yyyyMMdd", Locale.US);
  
  private static String zzI(int paramInt)
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(0xFFFFFF & paramInt);
    return String.format(localLocale, "#%06x", arrayOfObject);
  }
  
  public static AdResponseParcel zza(Context paramContext, AdRequestInfoParcel paramAdRequestInfoParcel, String paramString)
  {
    JSONObject localJSONObject;
    String str1;
    String str3;
    boolean bool1;
    String str4;
    long l1;
    String str5;
    long l2;
    label100:
    int i;
    AdResponseParcel localAdResponseParcel1;
    String str8;
    AdResponseParcel localAdResponseParcel3;
    label252:
    Object localObject1;
    label290:
    int n;
    for (;;)
    {
      try
      {
        localJSONObject = new JSONObject(paramString);
        str1 = localJSONObject.optString("ad_base_url", null);
        String str2 = localJSONObject.optString("ad_url", null);
        str3 = localJSONObject.optString("ad_size", null);
        if ((paramAdRequestInfoParcel == null) || (paramAdRequestInfoParcel.zzEt == 0)) {
          break label875;
        }
        bool1 = true;
        String str6;
        if (bool1)
        {
          str4 = localJSONObject.optString("ad_json", null);
          l1 = -1L;
          str5 = localJSONObject.optString("debug_dialog", null);
          if (!localJSONObject.has("interstitial_timeout")) {
            break label881;
          }
          l2 = (1000.0D * localJSONObject.getDouble("interstitial_timeout"));
          str6 = localJSONObject.optString("orientation", null);
          i = -1;
          if ("portrait".equals(str6))
          {
            i = zzp.zzbx().zzgH();
            if (TextUtils.isEmpty(str4)) {
              continue;
            }
            if (!TextUtils.isEmpty(str1)) {
              break label862;
            }
            zzb.zzaH("Could not parse the mediation config: Missing required ad_base_url field");
            localAdResponseParcel1 = new AdResponseParcel(0);
            break label872;
          }
        }
        else
        {
          str4 = localJSONObject.optString("ad_html", null);
          continue;
        }
        if (!"landscape".equals(str6)) {
          continue;
        }
        i = zzp.zzbx().zzgG();
        continue;
        if (!TextUtils.isEmpty(str2))
        {
          AdResponseParcel localAdResponseParcel2 = zzgt.zza(paramAdRequestInfoParcel, paramContext, paramAdRequestInfoParcel.zzqj.zzJu, str2, null, null, null, null, null);
          str1 = localAdResponseParcel2.zzBF;
          str8 = localAdResponseParcel2.body;
          l1 = localAdResponseParcel2.zzEO;
          localAdResponseParcel3 = localAdResponseParcel2;
          JSONArray localJSONArray1 = localJSONObject.optJSONArray("click_urls");
          if (localAdResponseParcel3 != null) {
            break label433;
          }
          localObject1 = null;
          if (localJSONArray1 == null) {
            break;
          }
          if (localObject1 != null) {
            break label889;
          }
          localObject1 = new LinkedList();
          break label889;
          if (n >= localJSONArray1.length()) {
            break label895;
          }
          ((List)localObject1).add(localJSONArray1.getString(n));
          n++;
          continue;
        }
        StringBuilder localStringBuilder = new StringBuilder().append("Could not parse the mediation config: Missing required ");
        if (bool1)
        {
          str7 = "ad_json";
          zzb.zzaH(str7 + " or " + "ad_url" + " field.");
          localAdResponseParcel1 = new AdResponseParcel(0);
        }
      }
      catch (JSONException localJSONException)
      {
        zzb.zzaH("Could not parse the mediation config: " + localJSONException.getMessage());
        localAdResponseParcel1 = new AdResponseParcel(0);
      }
      String str7 = "ad_html";
      continue;
      label433:
      localObject1 = localAdResponseParcel3.zzyY;
    }
    label443:
    JSONArray localJSONArray2 = localJSONObject.optJSONArray("impression_urls");
    Object localObject3;
    if (localAdResponseParcel3 == null) {
      localObject3 = null;
    }
    label481:
    int m;
    label522:
    Object localObject5;
    label560:
    int k;
    while (localJSONArray2 != null)
    {
      if (localObject3 != null) {
        break label902;
      }
      localObject3 = new LinkedList();
      break label902;
      while (m < localJSONArray2.length())
      {
        ((List)localObject3).add(localJSONArray2.getString(m));
        m++;
      }
      localObject3 = localAdResponseParcel3.zzyZ;
      continue;
      JSONArray localJSONArray3 = localJSONObject.optJSONArray("manual_impression_urls");
      if (localAdResponseParcel3 == null) {}
      for (localObject5 = null; localJSONArray3 != null; localObject5 = localAdResponseParcel3.zzEM)
      {
        if (localObject5 != null) {
          break label915;
        }
        localObject5 = new LinkedList();
        break label915;
        while (k < localJSONArray3.length())
        {
          ((List)localObject5).add(localJSONArray3.getString(k));
          k++;
        }
      }
    }
    for (;;)
    {
      if (localAdResponseParcel3 != null)
      {
        if (localAdResponseParcel3.orientation != -1) {
          i = localAdResponseParcel3.orientation;
        }
        if (localAdResponseParcel3.zzEJ <= 0L) {}
      }
      for (long l3 = localAdResponseParcel3.zzEJ;; l3 = l2)
      {
        String str9 = localJSONObject.optString("active_view");
        String str10 = null;
        boolean bool2 = localJSONObject.optBoolean("ad_is_javascript", false);
        if (bool2) {
          str10 = localJSONObject.optString("ad_passback_url", null);
        }
        boolean bool3 = localJSONObject.optBoolean("mediation", false);
        boolean bool4 = localJSONObject.optBoolean("custom_render_allowed", false);
        boolean bool5 = localJSONObject.optBoolean("content_url_opted_out", true);
        boolean bool6 = localJSONObject.optBoolean("prefetch", false);
        int j = localJSONObject.optInt("oauth2_token_status", 0);
        long l4 = localJSONObject.optLong("refresh_interval_milliseconds", -1L);
        long l5 = localJSONObject.optLong("mediation_config_cache_time_milliseconds", -1L);
        String str11 = localJSONObject.optString("gws_query_id", "");
        boolean bool7 = "height".equals(localJSONObject.optString("fluid", ""));
        localAdResponseParcel1 = new AdResponseParcel(paramAdRequestInfoParcel, str1, str8, (List)localObject2, (List)localObject4, l3, bool3, l5, (List)localObject6, l4, i, str3, l1, str5, bool2, str10, str9, bool4, bool1, paramAdRequestInfoParcel.zzEv, bool5, bool6, j, str11, bool7);
        break;
      }
      Object localObject6 = localObject5;
      continue;
      Object localObject4 = localObject3;
      break label522;
      Object localObject2 = localObject1;
      break label443;
      label862:
      localAdResponseParcel3 = null;
      str8 = str4;
      break label252;
      label872:
      return localAdResponseParcel1;
      label875:
      bool1 = false;
      break;
      label881:
      l2 = -1L;
      break label100;
      label889:
      n = 0;
      break label290;
      label895:
      localObject2 = localObject1;
      break label443;
      label902:
      m = 0;
      break label481;
      localObject4 = localObject3;
      break label522;
      label915:
      k = 0;
      break label560;
      localObject6 = localObject5;
    }
  }
  
  public static JSONObject zza(Context paramContext, AdRequestInfoParcel paramAdRequestInfoParcel, zzgy paramzzgy, zzhb.zza paramzza, Location paramLocation, zzbr paramzzbr, String paramString1, String paramString2, List<String> paramList)
  {
    label242:
    label307:
    JSONObject localJSONObject;
    try
    {
      localHashMap = new HashMap();
      if (paramList.size() > 0) {
        localHashMap.put("eid", TextUtils.join(",", paramList));
      }
      if (paramAdRequestInfoParcel.zzEm != null) {
        localHashMap.put("ad_pos", paramAdRequestInfoParcel.zzEm);
      }
      zza(localHashMap, paramAdRequestInfoParcel.zzEn);
      localHashMap.put("format", paramAdRequestInfoParcel.zzqn.zzte);
      if (paramAdRequestInfoParcel.zzqn.width == -1) {
        localHashMap.put("smart_w", "full");
      }
      if (paramAdRequestInfoParcel.zzqn.height == -2) {
        localHashMap.put("smart_h", "auto");
      }
      if (paramAdRequestInfoParcel.zzqn.zzti) {
        localHashMap.put("fluid", "height");
      }
      if (paramAdRequestInfoParcel.zzqn.zztg != null)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        AdSizeParcel[] arrayOfAdSizeParcel = paramAdRequestInfoParcel.zzqn.zztg;
        int i = arrayOfAdSizeParcel.length;
        int j = 0;
        if (j < i)
        {
          AdSizeParcel localAdSizeParcel = arrayOfAdSizeParcel[j];
          if (localStringBuilder.length() != 0) {
            localStringBuilder.append("|");
          }
          int k;
          if (localAdSizeParcel.width == -1)
          {
            k = (int)(localAdSizeParcel.widthPixels / paramzzgy.zzEz);
            localStringBuilder.append(k);
            localStringBuilder.append("x");
            if (localAdSizeParcel.height != -2) {
              break label307;
            }
          }
          for (int m = (int)(localAdSizeParcel.heightPixels / paramzzgy.zzEz);; m = localAdSizeParcel.height)
          {
            localStringBuilder.append(m);
            j++;
            break;
            k = localAdSizeParcel.width;
            break label242;
          }
        }
        localHashMap.put("sz", localStringBuilder);
      }
      if (paramAdRequestInfoParcel.zzEt != 0)
      {
        localHashMap.put("native_version", Integer.valueOf(paramAdRequestInfoParcel.zzEt));
        localHashMap.put("native_templates", paramAdRequestInfoParcel.zzqD);
        localHashMap.put("native_image_orientation", zzc(paramAdRequestInfoParcel.zzqB));
        if (!paramAdRequestInfoParcel.zzEE.isEmpty()) {
          localHashMap.put("native_custom_templates", paramAdRequestInfoParcel.zzEE);
        }
      }
      localHashMap.put("slotname", paramAdRequestInfoParcel.zzqh);
      localHashMap.put("pn", paramAdRequestInfoParcel.applicationInfo.packageName);
      if (paramAdRequestInfoParcel.zzEo != null) {
        localHashMap.put("vc", Integer.valueOf(paramAdRequestInfoParcel.zzEo.versionCode));
      }
      localHashMap.put("ms", paramString2);
      localHashMap.put("seq_num", paramAdRequestInfoParcel.zzEq);
      localHashMap.put("session_id", paramAdRequestInfoParcel.zzEr);
      localHashMap.put("js", paramAdRequestInfoParcel.zzqj.zzJu);
      zza(localHashMap, paramzzgy, paramzza);
      localHashMap.put("fdz", Integer.valueOf(paramzzbr.zzdd()));
      localHashMap.put("platform", Build.MANUFACTURER);
      localHashMap.put("submodel", Build.MODEL);
      if ((paramAdRequestInfoParcel.zzEn.versionCode >= 2) && (paramAdRequestInfoParcel.zzEn.zzsJ != null)) {
        zza(localHashMap, paramAdRequestInfoParcel.zzEn.zzsJ);
      }
      if (paramAdRequestInfoParcel.versionCode >= 2) {
        localHashMap.put("quality_signals", paramAdRequestInfoParcel.zzEs);
      }
      if ((paramAdRequestInfoParcel.versionCode >= 4) && (paramAdRequestInfoParcel.zzEv)) {
        localHashMap.put("forceHttps", Boolean.valueOf(paramAdRequestInfoParcel.zzEv));
      }
      if ((paramAdRequestInfoParcel.versionCode >= 4) && (paramAdRequestInfoParcel.zzEu != null))
      {
        localBundle = paramAdRequestInfoParcel.zzEu;
        zza(paramContext, paramAdRequestInfoParcel, localBundle);
        localHashMap.put("content_info", localBundle);
        if (paramAdRequestInfoParcel.versionCode < 5) {
          break label998;
        }
        localHashMap.put("u_sd", Float.valueOf(paramAdRequestInfoParcel.zzEz));
        localHashMap.put("sh", Integer.valueOf(paramAdRequestInfoParcel.zzEy));
        localHashMap.put("sw", Integer.valueOf(paramAdRequestInfoParcel.zzEx));
        if (paramAdRequestInfoParcel.versionCode >= 6)
        {
          boolean bool = TextUtils.isEmpty(paramAdRequestInfoParcel.zzEA);
          if (bool) {}
        }
      }
    }
    catch (JSONException localJSONException1)
    {
      try
      {
        HashMap localHashMap;
        for (;;)
        {
          localHashMap.put("view_hierarchy", new JSONObject(paramAdRequestInfoParcel.zzEA));
          localHashMap.put("correlation_id", Long.valueOf(paramAdRequestInfoParcel.zzEB));
          if (paramAdRequestInfoParcel.versionCode >= 7) {
            localHashMap.put("request_id", paramAdRequestInfoParcel.zzEC);
          }
          if ((paramAdRequestInfoParcel.versionCode >= 11) && (paramAdRequestInfoParcel.zzEG != null)) {
            localHashMap.put("capability", paramAdRequestInfoParcel.zzEG.toBundle());
          }
          zza(localHashMap, paramString1);
          if ((paramAdRequestInfoParcel.versionCode >= 12) && (!TextUtils.isEmpty(paramAdRequestInfoParcel.zzEH))) {
            localHashMap.put("anchor", paramAdRequestInfoParcel.zzEH);
          }
          if (zzb.zzN(2))
          {
            String str = zzp.zzbv().zzz(localHashMap).toString(2);
            zzb.v("Ad Request JSON: " + str);
          }
          localJSONObject = zzp.zzbv().zzz(localHashMap);
          break;
          Bundle localBundle = new Bundle();
        }
        localJSONException1 = localJSONException1;
        zzb.zzaH("Problem serializing ad request to JSON: " + localJSONException1.getMessage());
        localJSONObject = null;
        break label1062;
        label998:
        localHashMap.put("u_sd", Float.valueOf(paramzzgy.zzEz));
        localHashMap.put("sh", Integer.valueOf(paramzzgy.zzEy));
        localHashMap.put("sw", Integer.valueOf(paramzzgy.zzEx));
      }
      catch (JSONException localJSONException2)
      {
        for (;;)
        {
          zzb.zzd("Problem serializing view hierarchy to JSON", localJSONException2);
        }
      }
    }
    label1062:
    return localJSONObject;
  }
  
  static void zza(Context paramContext, AdRequestInfoParcel paramAdRequestInfoParcel, Bundle paramBundle)
  {
    if (!((Boolean)zzby.zzuZ.get()).booleanValue()) {
      zzb.zzaG("App index is not enabled");
    }
    for (;;)
    {
      return;
      if (!zzmm.zzjA())
      {
        zzb.zzaG("Not on service, return");
      }
      else if (zzl.zzcF().zzgT())
      {
        zzb.zzaG("Cannot invoked on UI thread");
      }
      else if ((paramAdRequestInfoParcel == null) || (paramAdRequestInfoParcel.zzEo == null))
      {
        zzb.zzaH("Invalid ad request info");
      }
      else
      {
        String str = paramAdRequestInfoParcel.zzEo.packageName;
        if (TextUtils.isEmpty(str)) {
          zzb.zzaH("Fail to get package name");
        } else {
          try
          {
            zza(zzd(paramContext, str), str, paramBundle);
          }
          catch (RuntimeException localRuntimeException)
          {
            zzb.zzaG("Fail to add app index to content info");
          }
        }
      }
    }
  }
  
  static void zza(UsageInfo paramUsageInfo, String paramString, Bundle paramBundle)
  {
    if ((paramUsageInfo == null) || (paramUsageInfo.zzlu() == null)) {}
    for (;;)
    {
      return;
      DocumentContents localDocumentContents = paramUsageInfo.zzlu();
      String str = localDocumentContents.zzln();
      if (!TextUtils.isEmpty(str)) {
        paramBundle.putString("web_url", str);
      }
      try
      {
        DocumentSection localDocumentSection = localDocumentContents.zzbw("intent_data");
        if ((localDocumentSection != null) && (!TextUtils.isEmpty(localDocumentSection.zzQj))) {
          paramBundle.putString("app_uri", AndroidAppUri.newAndroidAppUri(paramString, Uri.parse(localDocumentSection.zzQj)).toString());
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        zzb.zzaH("Failed to parse the third-party Android App URI");
      }
    }
  }
  
  private static void zza(HashMap<String, Object> paramHashMap, Location paramLocation)
  {
    HashMap localHashMap = new HashMap();
    Float localFloat = Float.valueOf(1000.0F * paramLocation.getAccuracy());
    Long localLong1 = Long.valueOf(1000L * paramLocation.getTime());
    Long localLong2 = Long.valueOf((1.0E7D * paramLocation.getLatitude()));
    Long localLong3 = Long.valueOf((1.0E7D * paramLocation.getLongitude()));
    localHashMap.put("radius", localFloat);
    localHashMap.put("lat", localLong2);
    localHashMap.put("long", localLong3);
    localHashMap.put("time", localLong1);
    paramHashMap.put("uule", localHashMap);
  }
  
  private static void zza(HashMap<String, Object> paramHashMap, AdRequestParcel paramAdRequestParcel)
  {
    String str = zzhy.zzgy();
    if (str != null) {
      paramHashMap.put("abf", str);
    }
    if (paramAdRequestParcel.zzsB != -1L) {
      paramHashMap.put("cust_age", zzFN.format(new Date(paramAdRequestParcel.zzsB)));
    }
    if (paramAdRequestParcel.extras != null) {
      paramHashMap.put("extras", paramAdRequestParcel.extras);
    }
    if (paramAdRequestParcel.zzsC != -1) {
      paramHashMap.put("cust_gender", Integer.valueOf(paramAdRequestParcel.zzsC));
    }
    if (paramAdRequestParcel.zzsD != null) {
      paramHashMap.put("kw", paramAdRequestParcel.zzsD);
    }
    if (paramAdRequestParcel.zzsF != -1) {
      paramHashMap.put("tag_for_child_directed_treatment", Integer.valueOf(paramAdRequestParcel.zzsF));
    }
    if (paramAdRequestParcel.zzsE) {
      paramHashMap.put("adtest", "on");
    }
    if (paramAdRequestParcel.versionCode >= 2)
    {
      if (paramAdRequestParcel.zzsG) {
        paramHashMap.put("d_imp_hdr", Integer.valueOf(1));
      }
      if (!TextUtils.isEmpty(paramAdRequestParcel.zzsH)) {
        paramHashMap.put("ppid", paramAdRequestParcel.zzsH);
      }
      if (paramAdRequestParcel.zzsI != null) {
        zza(paramHashMap, paramAdRequestParcel.zzsI);
      }
    }
    if ((paramAdRequestParcel.versionCode >= 3) && (paramAdRequestParcel.zzsK != null)) {
      paramHashMap.put("url", paramAdRequestParcel.zzsK);
    }
    if (paramAdRequestParcel.versionCode >= 5)
    {
      if (paramAdRequestParcel.zzsM != null) {
        paramHashMap.put("custom_targeting", paramAdRequestParcel.zzsM);
      }
      if (paramAdRequestParcel.zzsN != null) {
        paramHashMap.put("category_exclusions", paramAdRequestParcel.zzsN);
      }
      if (paramAdRequestParcel.zzsO != null) {
        paramHashMap.put("request_agent", paramAdRequestParcel.zzsO);
      }
    }
    if ((paramAdRequestParcel.versionCode >= 6) && (paramAdRequestParcel.zzsP != null)) {
      paramHashMap.put("request_pkg", paramAdRequestParcel.zzsP);
    }
  }
  
  private static void zza(HashMap<String, Object> paramHashMap, SearchAdRequestParcel paramSearchAdRequestParcel)
  {
    Object localObject1 = null;
    if (Color.alpha(paramSearchAdRequestParcel.zztP) != 0) {
      paramHashMap.put("acolor", zzI(paramSearchAdRequestParcel.zztP));
    }
    if (Color.alpha(paramSearchAdRequestParcel.backgroundColor) != 0) {
      paramHashMap.put("bgcolor", zzI(paramSearchAdRequestParcel.backgroundColor));
    }
    if ((Color.alpha(paramSearchAdRequestParcel.zztQ) != 0) && (Color.alpha(paramSearchAdRequestParcel.zztR) != 0))
    {
      paramHashMap.put("gradientto", zzI(paramSearchAdRequestParcel.zztQ));
      paramHashMap.put("gradientfrom", zzI(paramSearchAdRequestParcel.zztR));
    }
    if (Color.alpha(paramSearchAdRequestParcel.zztS) != 0) {
      paramHashMap.put("bcolor", zzI(paramSearchAdRequestParcel.zztS));
    }
    paramHashMap.put("bthick", Integer.toString(paramSearchAdRequestParcel.zztT));
    Object localObject2;
    switch (paramSearchAdRequestParcel.zztU)
    {
    default: 
      localObject2 = null;
      if (localObject2 != null) {
        paramHashMap.put("btype", localObject2);
      }
      switch (paramSearchAdRequestParcel.zztV)
      {
      }
      break;
    }
    for (;;)
    {
      if (localObject1 != null) {
        paramHashMap.put("callbuttoncolor", localObject1);
      }
      if (paramSearchAdRequestParcel.zztW != null) {
        paramHashMap.put("channel", paramSearchAdRequestParcel.zztW);
      }
      if (Color.alpha(paramSearchAdRequestParcel.zztX) != 0) {
        paramHashMap.put("dcolor", zzI(paramSearchAdRequestParcel.zztX));
      }
      if (paramSearchAdRequestParcel.zztY != null) {
        paramHashMap.put("font", paramSearchAdRequestParcel.zztY);
      }
      if (Color.alpha(paramSearchAdRequestParcel.zztZ) != 0) {
        paramHashMap.put("hcolor", zzI(paramSearchAdRequestParcel.zztZ));
      }
      paramHashMap.put("headersize", Integer.toString(paramSearchAdRequestParcel.zzua));
      if (paramSearchAdRequestParcel.zzub != null) {
        paramHashMap.put("q", paramSearchAdRequestParcel.zzub);
      }
      return;
      localObject2 = "none";
      break;
      localObject2 = "dashed";
      break;
      localObject2 = "dotted";
      break;
      localObject2 = "solid";
      break;
      localObject1 = "dark";
      continue;
      localObject1 = "light";
      continue;
      localObject1 = "medium";
    }
  }
  
  private static void zza(HashMap<String, Object> paramHashMap, zzgy paramzzgy, zzhb.zza paramzza)
  {
    paramHashMap.put("am", Integer.valueOf(paramzzgy.zzGs));
    paramHashMap.put("cog", zzx(paramzzgy.zzGt));
    paramHashMap.put("coh", zzx(paramzzgy.zzGu));
    if (!TextUtils.isEmpty(paramzzgy.zzGv)) {
      paramHashMap.put("carrier", paramzzgy.zzGv);
    }
    paramHashMap.put("gl", paramzzgy.zzGw);
    if (paramzzgy.zzGx) {
      paramHashMap.put("simulator", Integer.valueOf(1));
    }
    if (paramzzgy.zzGy) {
      paramHashMap.put("is_sidewinder", Integer.valueOf(1));
    }
    paramHashMap.put("ma", zzx(paramzzgy.zzGz));
    paramHashMap.put("sp", zzx(paramzzgy.zzGA));
    paramHashMap.put("hl", paramzzgy.zzGB);
    if (!TextUtils.isEmpty(paramzzgy.zzGC)) {
      paramHashMap.put("mv", paramzzgy.zzGC);
    }
    paramHashMap.put("muv", Integer.valueOf(paramzzgy.zzGD));
    if (paramzzgy.zzGE != -2) {
      paramHashMap.put("cnt", Integer.valueOf(paramzzgy.zzGE));
    }
    paramHashMap.put("gnt", Integer.valueOf(paramzzgy.zzGF));
    paramHashMap.put("pt", Integer.valueOf(paramzzgy.zzGG));
    paramHashMap.put("rm", Integer.valueOf(paramzzgy.zzGH));
    paramHashMap.put("riv", Integer.valueOf(paramzzgy.zzGI));
    Bundle localBundle1 = new Bundle();
    localBundle1.putString("build", paramzzgy.zzGN);
    Bundle localBundle2 = new Bundle();
    localBundle2.putBoolean("is_charging", paramzzgy.zzGK);
    localBundle2.putDouble("battery_level", paramzzgy.zzGJ);
    localBundle1.putBundle("battery", localBundle2);
    Bundle localBundle3 = new Bundle();
    localBundle3.putInt("active_network_state", paramzzgy.zzGM);
    localBundle3.putBoolean("active_network_metered", paramzzgy.zzGL);
    if (paramzza != null)
    {
      Bundle localBundle4 = new Bundle();
      localBundle4.putInt("predicted_latency_micros", paramzza.zzGS);
      localBundle4.putLong("predicted_down_throughput_bps", paramzza.zzGT);
      localBundle4.putLong("predicted_up_throughput_bps", paramzza.zzGU);
      localBundle3.putBundle("predictions", localBundle4);
    }
    localBundle1.putBundle("network", localBundle3);
    paramHashMap.put("device", localBundle1);
  }
  
  private static void zza(HashMap<String, Object> paramHashMap, String paramString)
  {
    if (paramString != null)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("token", paramString);
      paramHashMap.put("pan", localHashMap);
    }
  }
  
  private static String zzc(NativeAdOptionsParcel paramNativeAdOptionsParcel)
  {
    int i;
    String str;
    if (paramNativeAdOptionsParcel != null)
    {
      i = paramNativeAdOptionsParcel.zzwS;
      switch (i)
      {
      default: 
        str = "any";
      }
    }
    for (;;)
    {
      return str;
      i = 0;
      break;
      str = "portrait";
      continue;
      str = "landscape";
    }
  }
  
  /* Error */
  private static UsageInfo zzd(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 1059	com/google/android/gms/common/api/GoogleApiClient$Builder
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 1062	com/google/android/gms/common/api/GoogleApiClient$Builder:<init>	(Landroid/content/Context;)V
    //   10: getstatic 1068	com/google/android/gms/appdatasearch/zza:zzPV	Lcom/google/android/gms/common/api/Api;
    //   13: invokevirtual 1072	com/google/android/gms/common/api/GoogleApiClient$Builder:addApi	(Lcom/google/android/gms/common/api/Api;)Lcom/google/android/gms/common/api/GoogleApiClient$Builder;
    //   16: invokevirtual 1075	com/google/android/gms/common/api/GoogleApiClient$Builder:build	()Lcom/google/android/gms/common/api/GoogleApiClient;
    //   19: astore 9
    //   21: aload 9
    //   23: astore 4
    //   25: aload 4
    //   27: invokevirtual 1080	com/google/android/gms/common/api/GoogleApiClient:connect	()V
    //   30: new 1082	com/google/android/gms/appdatasearch/GetRecentContextCall$Request$zza
    //   33: dup
    //   34: invokespecial 1083	com/google/android/gms/appdatasearch/GetRecentContextCall$Request$zza:<init>	()V
    //   37: iconst_1
    //   38: invokevirtual 1087	com/google/android/gms/appdatasearch/GetRecentContextCall$Request$zza:zzL	(Z)Lcom/google/android/gms/appdatasearch/GetRecentContextCall$Request$zza;
    //   41: aload_1
    //   42: invokevirtual 1091	com/google/android/gms/appdatasearch/GetRecentContextCall$Request$zza:zzby	(Ljava/lang/String;)Lcom/google/android/gms/appdatasearch/GetRecentContextCall$Request$zza;
    //   45: invokevirtual 1095	com/google/android/gms/appdatasearch/GetRecentContextCall$Request$zza:zzlr	()Lcom/google/android/gms/appdatasearch/GetRecentContextCall$Request;
    //   48: astore 11
    //   50: getstatic 1099	com/google/android/gms/appdatasearch/zza:zzPW	Lcom/google/android/gms/appdatasearch/zzk;
    //   53: aload 4
    //   55: aload 11
    //   57: invokeinterface 1104 3 0
    //   62: lconst_1
    //   63: getstatic 1110	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   66: invokevirtual 1116	com/google/android/gms/common/api/PendingResult:await	(JLjava/util/concurrent/TimeUnit;)Lcom/google/android/gms/common/api/Result;
    //   69: checkcast 1118	com/google/android/gms/appdatasearch/GetRecentContextCall$Response
    //   72: astore 12
    //   74: aload 12
    //   76: ifnull +14 -> 90
    //   79: aload 12
    //   81: invokevirtual 1122	com/google/android/gms/appdatasearch/GetRecentContextCall$Response:getStatus	()Lcom/google/android/gms/common/api/Status;
    //   84: invokevirtual 1127	com/google/android/gms/common/api/Status:isSuccess	()Z
    //   87: ifne +21 -> 108
    //   90: ldc_w 1129
    //   93: invokestatic 586	com/google/android/gms/ads/internal/util/client/zzb:zzaG	(Ljava/lang/String;)V
    //   96: aload 4
    //   98: ifnull +8 -> 106
    //   101: aload 4
    //   103: invokevirtual 1132	com/google/android/gms/common/api/GoogleApiClient:disconnect	()V
    //   106: aload_2
    //   107: areturn
    //   108: aload 12
    //   110: getfield 1135	com/google/android/gms/appdatasearch/GetRecentContextCall$Response:zzQB	Ljava/util/List;
    //   113: ifnull +16 -> 129
    //   116: aload 12
    //   118: getfield 1135	com/google/android/gms/appdatasearch/GetRecentContextCall$Response:zzQB	Ljava/util/List;
    //   121: invokeinterface 376 1 0
    //   126: ifeq +22 -> 148
    //   129: ldc_w 1137
    //   132: invokestatic 586	com/google/android/gms/ads/internal/util/client/zzb:zzaG	(Ljava/lang/String;)V
    //   135: aload 4
    //   137: ifnull -31 -> 106
    //   140: aload 4
    //   142: invokevirtual 1132	com/google/android/gms/common/api/GoogleApiClient:disconnect	()V
    //   145: goto -39 -> 106
    //   148: aload 12
    //   150: getfield 1135	com/google/android/gms/appdatasearch/GetRecentContextCall$Response:zzQB	Ljava/util/List;
    //   153: iconst_0
    //   154: invokeinterface 1140 2 0
    //   159: checkcast 623	com/google/android/gms/appdatasearch/UsageInfo
    //   162: astore 8
    //   164: aload 4
    //   166: ifnull +8 -> 174
    //   169: aload 4
    //   171: invokevirtual 1132	com/google/android/gms/common/api/GoogleApiClient:disconnect	()V
    //   174: aload 8
    //   176: astore_2
    //   177: goto -71 -> 106
    //   180: astore 5
    //   182: aconst_null
    //   183: astore 6
    //   185: ldc_w 1142
    //   188: invokestatic 118	com/google/android/gms/ads/internal/util/client/zzb:zzaH	(Ljava/lang/String;)V
    //   191: aload 6
    //   193: ifnull +55 -> 248
    //   196: aload 6
    //   198: invokevirtual 1132	com/google/android/gms/common/api/GoogleApiClient:disconnect	()V
    //   201: aconst_null
    //   202: astore 8
    //   204: goto -30 -> 174
    //   207: astore_3
    //   208: aconst_null
    //   209: astore 4
    //   211: aload 4
    //   213: ifnull +8 -> 221
    //   216: aload 4
    //   218: invokevirtual 1132	com/google/android/gms/common/api/GoogleApiClient:disconnect	()V
    //   221: aload_3
    //   222: athrow
    //   223: astore_3
    //   224: goto -13 -> 211
    //   227: astore 7
    //   229: aload 6
    //   231: astore 4
    //   233: aload 7
    //   235: astore_3
    //   236: goto -25 -> 211
    //   239: astore 10
    //   241: aload 4
    //   243: astore 6
    //   245: goto -60 -> 185
    //   248: aconst_null
    //   249: astore 8
    //   251: goto -77 -> 174
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	254	0	paramContext	Context
    //   0	254	1	paramString	String
    //   1	176	2	localObject1	Object
    //   207	15	3	localObject2	Object
    //   223	1	3	localObject3	Object
    //   235	1	3	localObject4	Object
    //   23	219	4	localObject5	Object
    //   180	1	5	localSecurityException1	SecurityException
    //   183	61	6	localObject6	Object
    //   227	7	7	localObject7	Object
    //   162	88	8	localUsageInfo	UsageInfo
    //   19	3	9	localGoogleApiClient	com.google.android.gms.common.api.GoogleApiClient
    //   239	1	10	localSecurityException2	SecurityException
    //   48	8	11	localRequest	com.google.android.gms.appdatasearch.GetRecentContextCall.Request
    //   72	77	12	localResponse	com.google.android.gms.appdatasearch.GetRecentContextCall.Response
    // Exception table:
    //   from	to	target	type
    //   2	21	180	java/lang/SecurityException
    //   2	21	207	finally
    //   25	96	223	finally
    //   108	135	223	finally
    //   148	164	223	finally
    //   185	191	227	finally
    //   25	96	239	java/lang/SecurityException
    //   108	135	239	java/lang/SecurityException
    //   148	164	239	java/lang/SecurityException
  }
  
  private static Integer zzx(boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0) {
      return Integer.valueOf(i);
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzgu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */