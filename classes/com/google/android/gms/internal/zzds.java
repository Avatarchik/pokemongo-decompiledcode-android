package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.overlay.zzk;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;
import java.util.WeakHashMap;
import org.json.JSONObject;

@zzgr
public final class zzds
  implements zzdk
{
  private final Map<zziz, Integer> zzxX = new WeakHashMap();
  
  private static int zza(Context paramContext, Map<String, String> paramMap, String paramString, int paramInt)
  {
    str = (String)paramMap.get(paramString);
    if (str != null) {}
    try
    {
      int i = zzl.zzcF().zzb(paramContext, Integer.parseInt(str));
      paramInt = i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        zzb.zzaH("Could not parse " + paramString + " in a video GMSG: " + str);
      }
    }
    return paramInt;
  }
  
  public void zza(zziz paramzziz, Map<String, String> paramMap)
  {
    String str1 = (String)paramMap.get("action");
    if (str1 == null) {
      zzb.zzaH("Action missing from video GMSG.");
    }
    for (;;)
    {
      return;
      if (zzb.zzN(3))
      {
        JSONObject localJSONObject = new JSONObject(paramMap);
        localJSONObject.remove("google.afma.Notify_dt");
        zzb.zzaF("Video GMSG: " + str1 + " " + localJSONObject.toString());
      }
      if ("background".equals(str1))
      {
        String str4 = (String)paramMap.get("color");
        if (TextUtils.isEmpty(str4))
        {
          zzb.zzaH("Color parameter missing from color video GMSG.");
        }
        else
        {
          int i5;
          try
          {
            i5 = Color.parseColor(str4);
            zziy localzziy2 = paramzziz.zzhl();
            if (localzziy2 == null) {
              break label172;
            }
            zzk localzzk3 = localzziy2.zzgX();
            if (localzzk3 == null) {
              break label172;
            }
            localzzk3.setBackgroundColor(i5);
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            zzb.zzaH("Invalid color parameter in video GMSG.");
          }
          continue;
          label172:
          this.zzxX.put(paramzziz, Integer.valueOf(i5));
        }
      }
      else
      {
        zziy localzziy1 = paramzziz.zzhl();
        if (localzziy1 == null)
        {
          zzb.zzaH("Could not get underlay container for a video GMSG.");
        }
        else
        {
          boolean bool1 = "new".equals(str1);
          boolean bool2 = "position".equals(str1);
          int i;
          int j;
          int k;
          int m;
          if ((bool1) || (bool2))
          {
            Context localContext1 = paramzziz.getContext();
            i = zza(localContext1, paramMap, "x", 0);
            j = zza(localContext1, paramMap, "y", 0);
            k = zza(localContext1, paramMap, "w", -1);
            m = zza(localContext1, paramMap, "h", -1);
            try
            {
              int i2 = Integer.parseInt((String)paramMap.get("player"));
              n = i2;
            }
            catch (NumberFormatException localNumberFormatException1)
            {
              for (;;)
              {
                int i1;
                zzk localzzk1;
                int n = 0;
              }
              localzziy1.zze(i, j, k, m);
            }
            if ((bool1) && (localzziy1.zzgX() == null))
            {
              localzziy1.zza(i, j, k, m, n);
              if (!this.zzxX.containsKey(paramzziz)) {
                continue;
              }
              i1 = ((Integer)this.zzxX.get(paramzziz)).intValue();
              localzzk1 = localzziy1.zzgX();
              localzzk1.setBackgroundColor(i1);
              localzzk1.zzeW();
            }
          }
          else
          {
            zzk localzzk2 = localzziy1.zzgX();
            if (localzzk2 == null)
            {
              zzk.zzd(paramzziz);
            }
            else if ("click".equals(str1))
            {
              Context localContext2 = paramzziz.getContext();
              int i3 = zza(localContext2, paramMap, "x", 0);
              int i4 = zza(localContext2, paramMap, "y", 0);
              long l = SystemClock.uptimeMillis();
              MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 0, i3, i4, 0);
              localzzk2.zzd(localMotionEvent);
              localMotionEvent.recycle();
            }
            else if ("currentTime".equals(str1))
            {
              String str3 = (String)paramMap.get("time");
              if (str3 == null) {
                zzb.zzaH("Time parameter missing from currentTime video GMSG.");
              } else {
                try
                {
                  localzzk2.seekTo((int)(1000.0F * Float.parseFloat(str3)));
                }
                catch (NumberFormatException localNumberFormatException3)
                {
                  zzb.zzaH("Could not parse time parameter from currentTime video GMSG: " + str3);
                }
              }
            }
            else if ("hide".equals(str1))
            {
              localzzk2.setVisibility(4);
            }
            else if ("load".equals(str1))
            {
              localzzk2.zzeV();
            }
            else if ("mimetype".equals(str1))
            {
              localzzk2.setMimeType((String)paramMap.get("mimetype"));
            }
            else if ("muted".equals(str1))
            {
              if (Boolean.parseBoolean((String)paramMap.get("muted"))) {
                localzzk2.zzex();
              } else {
                localzzk2.zzey();
              }
            }
            else if ("pause".equals(str1))
            {
              localzzk2.pause();
            }
            else if ("play".equals(str1))
            {
              localzzk2.play();
            }
            else if ("show".equals(str1))
            {
              localzzk2.setVisibility(0);
            }
            else if ("src".equals(str1))
            {
              localzzk2.zzan((String)paramMap.get("src"));
            }
            else if ("volume".equals(str1))
            {
              String str2 = (String)paramMap.get("volume");
              if (str2 == null) {
                zzb.zzaH("Level parameter missing from volume video GMSG.");
              } else {
                try
                {
                  localzzk2.zza(Float.parseFloat(str2));
                }
                catch (NumberFormatException localNumberFormatException2)
                {
                  zzb.zzaH("Could not parse volume parameter from volume video GMSG: " + str2);
                }
              }
            }
            else if ("watermark".equals(str1))
            {
              localzzk2.zzeW();
            }
            else
            {
              zzb.zzaH("Unknown video action: " + str1);
            }
          }
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */