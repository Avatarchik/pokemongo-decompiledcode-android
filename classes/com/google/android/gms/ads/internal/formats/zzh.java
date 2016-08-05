package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzn;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzan;
import com.google.android.gms.internal.zzbb;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zziz;
import com.google.android.gms.internal.zzja;
import com.google.android.gms.internal.zzja.zza;
import com.google.android.gms.internal.zzjb;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@zzgr
public class zzh
{
  private final Context mContext;
  private zziz zzoM;
  private final VersionInfoParcel zzpb;
  private final Object zzpd = new Object();
  private final zzn zzwF;
  private final JSONObject zzwI;
  private final zzbb zzwJ;
  private final zza zzwK;
  private final zzan zzwL;
  private boolean zzwM;
  private String zzwN;
  
  public zzh(Context paramContext, zzn paramzzn, zzbb paramzzbb, zzan paramzzan, JSONObject paramJSONObject, zza paramzza, VersionInfoParcel paramVersionInfoParcel)
  {
    this.mContext = paramContext;
    this.zzwF = paramzzn;
    this.zzwJ = paramzzbb;
    this.zzwL = paramzzan;
    this.zzwI = paramJSONObject;
    this.zzwK = paramzza;
    this.zzpb = paramVersionInfoParcel;
  }
  
  public Context getContext()
  {
    return this.mContext;
  }
  
  public void recordImpression()
  {
    zzx.zzci("recordImpression must be called on the main UI thread.");
    zzl(true);
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("ad", this.zzwI);
      this.zzwJ.zza("google.afma.nativeAds.handleImpressionPing", localJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to create impression JSON.", localJSONException);
      }
    }
  }
  
  public zzb zza(View.OnClickListener paramOnClickListener)
  {
    zza localzza = this.zzwK.zzdz();
    zzb localzzb;
    if (localzza == null) {
      localzzb = null;
    }
    for (;;)
    {
      return localzzb;
      localzzb = new zzb(this.mContext, localzza);
      localzzb.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
      localzzb.zzdu().setOnClickListener(paramOnClickListener);
      localzzb.zzdu().setContentDescription("Ad attribution icon");
    }
  }
  
  public void zza(View paramView, Map<String, WeakReference<View>> paramMap, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    zzx.zzci("performClick must be called on the main UI thread.");
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (paramView.equals((View)((WeakReference)localEntry.getValue()).get())) {
        zza((String)localEntry.getKey(), paramJSONObject1, paramJSONObject2);
      }
    }
  }
  
  public void zza(String paramString, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
  {
    zzx.zzci("performClick must be called on the main UI thread.");
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.put("asset", paramString);
      localJSONObject1.put("template", this.zzwK.zzdy());
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("ad", this.zzwI);
      localJSONObject2.put("click", localJSONObject1);
      if (this.zzwF.zzr(this.zzwK.getCustomTemplateId()) != null) {}
      for (boolean bool = true;; bool = false)
      {
        localJSONObject2.put("has_custom_click_handler", bool);
        if (paramJSONObject1 != null) {
          localJSONObject2.put("view_rectangles", paramJSONObject1);
        }
        if (paramJSONObject2 != null) {
          localJSONObject2.put("click_point", paramJSONObject2);
        }
        this.zzwJ.zza("google.afma.nativeAds.handleClickGmsg", localJSONObject2);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to create click JSON.", localJSONException);
      }
    }
  }
  
  public void zzb(MotionEvent paramMotionEvent)
  {
    this.zzwL.zza(paramMotionEvent);
  }
  
  public zziz zzdC()
  {
    this.zzoM = zzdD();
    this.zzoM.getView().setVisibility(8);
    this.zzwJ.zza("/loadHtml", new zzdk()
    {
      public void zza(zziz paramAnonymouszziz, final Map<String, String> paramAnonymousMap)
      {
        zzh.zze(zzh.this).zzhe().zza(new zzja.zza()
        {
          public void zza(zziz paramAnonymous2zziz, boolean paramAnonymous2Boolean)
          {
            zzh.zza(zzh.this, (String)paramAnonymousMap.get("id"));
            JSONObject localJSONObject = new JSONObject();
            try
            {
              localJSONObject.put("messageType", "htmlLoaded");
              localJSONObject.put("id", zzh.zzc(zzh.this));
              zzh.zzd(zzh.this).zzb("sendMessageToNativeJs", localJSONObject);
              return;
            }
            catch (JSONException localJSONException)
            {
              for (;;)
              {
                com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to dispatch sendMessageToNativeJsevent", localJSONException);
              }
            }
          }
        });
        String str1 = (String)paramAnonymousMap.get("overlayHtml");
        String str2 = (String)paramAnonymousMap.get("baseUrl");
        if (TextUtils.isEmpty(str2)) {
          zzh.zze(zzh.this).loadData(str1, "text/html", "UTF-8");
        }
        for (;;)
        {
          return;
          zzh.zze(zzh.this).loadDataWithBaseURL(str2, str1, "text/html", "UTF-8", null);
        }
      }
    });
    this.zzwJ.zza("/showOverlay", new zzdk()
    {
      public void zza(zziz paramAnonymouszziz, Map<String, String> paramAnonymousMap)
      {
        zzh.zze(zzh.this).getView().setVisibility(0);
      }
    });
    this.zzwJ.zza("/hideOverlay", new zzdk()
    {
      public void zza(zziz paramAnonymouszziz, Map<String, String> paramAnonymousMap)
      {
        zzh.zze(zzh.this).getView().setVisibility(8);
      }
    });
    this.zzoM.zzhe().zza("/hideOverlay", new zzdk()
    {
      public void zza(zziz paramAnonymouszziz, Map<String, String> paramAnonymousMap)
      {
        zzh.zze(zzh.this).getView().setVisibility(8);
      }
    });
    this.zzoM.zzhe().zza("/sendMessageToSdk", new zzdk()
    {
      public void zza(zziz paramAnonymouszziz, Map<String, String> paramAnonymousMap)
      {
        JSONObject localJSONObject = new JSONObject();
        try
        {
          Iterator localIterator = paramAnonymousMap.keySet().iterator();
          while (localIterator.hasNext())
          {
            String str = (String)localIterator.next();
            localJSONObject.put(str, paramAnonymousMap.get(str));
            continue;
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          com.google.android.gms.ads.internal.util.client.zzb.zzb("Unable to dispatch sendMessageToNativeJs event", localJSONException);
        }
        for (;;)
        {
          localJSONObject.put("id", zzh.zzc(zzh.this));
          zzh.zzd(zzh.this).zzb("sendMessageToNativeJs", localJSONObject);
        }
      }
    });
    return this.zzoM;
  }
  
  zziz zzdD()
  {
    return zzp.zzbw().zza(this.mContext, AdSizeParcel.zzs(this.mContext), false, false, this.zzwL, this.zzpb);
  }
  
  public void zzh(View paramView) {}
  
  public void zzi(View paramView)
  {
    synchronized (this.zzpd)
    {
      if (!this.zzwM) {
        if (paramView.isShown()) {}
      }
    }
  }
  
  protected void zzl(boolean paramBoolean)
  {
    this.zzwM = paramBoolean;
  }
  
  public static abstract interface zza
  {
    public abstract String getCustomTemplateId();
    
    public abstract void zza(zzh paramzzh);
    
    public abstract String zzdy();
    
    public abstract zza zzdz();
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/formats/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */