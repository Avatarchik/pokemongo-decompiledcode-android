package com.google.android.gms.internal;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzp;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@zzgr
public final class zzdr
  implements zzdk
{
  private final zze zzxQ;
  private final zzfc zzxR;
  private final zzdm zzxT;
  
  public zzdr(zzdm paramzzdm, zze paramzze, zzfc paramzzfc)
  {
    this.zzxT = paramzzdm;
    this.zzxQ = paramzze;
    this.zzxR = paramzzfc;
  }
  
  private static void zza(Context paramContext, Map<String, String> paramMap)
  {
    if (TextUtils.isEmpty((String)paramMap.get("u"))) {
      zzb.zzaH("Destination url cannot be empty.");
    }
    for (;;)
    {
      return;
      Intent localIntent = new zzb().zzb(paramContext, paramMap);
      try
      {
        zzp.zzbv().zzb(paramContext, localIntent);
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        zzb.zzaH(localActivityNotFoundException.getMessage());
      }
    }
  }
  
  private static boolean zzc(Map<String, String> paramMap)
  {
    return "1".equals(paramMap.get("custom_close"));
  }
  
  private static int zzd(Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("o");
    int i;
    if (str != null) {
      if ("p".equalsIgnoreCase(str)) {
        i = zzp.zzbx().zzgH();
      }
    }
    for (;;)
    {
      return i;
      if ("l".equalsIgnoreCase(str)) {
        i = zzp.zzbx().zzgG();
      } else if ("c".equalsIgnoreCase(str)) {
        i = zzp.zzbx().zzgI();
      } else {
        i = -1;
      }
    }
  }
  
  private static void zze(zziz paramzziz, Map<String, String> paramMap)
  {
    String str = (String)paramMap.get("u");
    if (TextUtils.isEmpty(str)) {
      zzb.zzaH("Destination url cannot be empty.");
    }
    for (;;)
    {
      return;
      new zza(paramzziz, str).zzgz();
    }
  }
  
  private void zzm(boolean paramBoolean)
  {
    if (this.zzxR != null) {
      this.zzxR.zzn(paramBoolean);
    }
  }
  
  public void zza(zziz paramzziz, Map<String, String> paramMap)
  {
    String str1 = (String)paramMap.get("a");
    if (str1 == null) {
      zzb.zzaH("Action missing from an open GMSG.");
    }
    zzja localzzja;
    for (;;)
    {
      return;
      if ((this.zzxQ != null) && (!this.zzxQ.zzbe()))
      {
        this.zzxQ.zzp((String)paramMap.get("u"));
      }
      else
      {
        localzzja = paramzziz.zzhe();
        if ("expand".equalsIgnoreCase(str1))
        {
          if (paramzziz.zzhi())
          {
            zzb.zzaH("Cannot expand WebView that is already expanded.");
          }
          else
          {
            zzm(false);
            localzzja.zza(zzc(paramMap), zzd(paramMap));
          }
        }
        else if ("webapp".equalsIgnoreCase(str1))
        {
          String str6 = (String)paramMap.get("u");
          zzm(false);
          if (str6 != null) {
            localzzja.zza(zzc(paramMap), zzd(paramMap), str6);
          } else {
            localzzja.zza(zzc(paramMap), zzd(paramMap), (String)paramMap.get("html"), (String)paramMap.get("baseurl"));
          }
        }
        else if ("in_app_purchase".equalsIgnoreCase(str1))
        {
          String str4 = (String)paramMap.get("product_id");
          String str5 = (String)paramMap.get("report_urls");
          if (this.zzxT != null) {
            if ((str5 != null) && (!str5.isEmpty()))
            {
              String[] arrayOfString = str5.split(" ");
              this.zzxT.zza(str4, new ArrayList(Arrays.asList(arrayOfString)));
            }
            else
            {
              this.zzxT.zza(str4, new ArrayList());
            }
          }
        }
        else if (("app".equalsIgnoreCase(str1)) && ("true".equalsIgnoreCase((String)paramMap.get("play_store"))))
        {
          zze(paramzziz, paramMap);
        }
        else
        {
          if ((!"app".equalsIgnoreCase(str1)) || (!"true".equalsIgnoreCase((String)paramMap.get("system_browser")))) {
            break;
          }
          zza(paramzziz.getContext(), paramMap);
        }
      }
    }
    zzm(true);
    paramzziz.zzhg();
    String str2 = (String)paramMap.get("u");
    if (!TextUtils.isEmpty(str2)) {}
    for (String str3 = zzp.zzbv().zza(paramzziz, str2);; str3 = str2)
    {
      localzzja.zza(new AdLauncherIntentInfoParcel((String)paramMap.get("i"), str3, (String)paramMap.get("m"), (String)paramMap.get("p"), (String)paramMap.get("c"), (String)paramMap.get("f"), (String)paramMap.get("e")));
      break;
    }
  }
  
  public static class zzb
  {
    public Intent zza(Intent paramIntent, ResolveInfo paramResolveInfo)
    {
      Intent localIntent = new Intent(paramIntent);
      localIntent.setClassName(paramResolveInfo.activityInfo.packageName, paramResolveInfo.activityInfo.name);
      return localIntent;
    }
    
    public ResolveInfo zza(Context paramContext, Intent paramIntent)
    {
      return zza(paramContext, paramIntent, new ArrayList());
    }
    
    public ResolveInfo zza(Context paramContext, Intent paramIntent, ArrayList<ResolveInfo> paramArrayList)
    {
      Object localObject = null;
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager == null) {
        return (ResolveInfo)localObject;
      }
      List localList = localPackageManager.queryIntentActivities(paramIntent, 65536);
      ResolveInfo localResolveInfo1 = localPackageManager.resolveActivity(paramIntent, 65536);
      int i;
      if ((localList != null) && (localResolveInfo1 != null))
      {
        i = 0;
        label50:
        if (i < localList.size())
        {
          ResolveInfo localResolveInfo3 = (ResolveInfo)localList.get(i);
          if ((localResolveInfo1 == null) || (!localResolveInfo1.activityInfo.name.equals(localResolveInfo3.activityInfo.name))) {}
        }
      }
      for (ResolveInfo localResolveInfo2 = localResolveInfo1;; localResolveInfo2 = null)
      {
        paramArrayList.addAll(localList);
        localObject = localResolveInfo2;
        break;
        i++;
        break label50;
      }
    }
    
    public Intent zzb(Context paramContext, Map<String, String> paramMap)
    {
      Object localObject = null;
      ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
      String str = (String)paramMap.get("u");
      if (TextUtils.isEmpty(str)) {
        return (Intent)localObject;
      }
      Uri localUri1 = Uri.parse(str);
      boolean bool1 = Boolean.parseBoolean((String)paramMap.get("use_first_package"));
      boolean bool2 = Boolean.parseBoolean((String)paramMap.get("use_running_process"));
      Uri localUri2;
      if ("http".equalsIgnoreCase(localUri1.getScheme())) {
        localUri2 = localUri1.buildUpon().scheme("https").build();
      }
      for (;;)
      {
        ArrayList localArrayList = new ArrayList();
        Intent localIntent1 = zzd(localUri1);
        Intent localIntent2 = zzd(localUri2);
        ResolveInfo localResolveInfo1 = zza(paramContext, localIntent1, localArrayList);
        if (localResolveInfo1 != null)
        {
          localObject = zza(localIntent1, localResolveInfo1);
          break;
          if (!"https".equalsIgnoreCase(localUri1.getScheme())) {
            break label377;
          }
          localUri2 = localUri1.buildUpon().scheme("http").build();
          continue;
        }
        if (localIntent2 != null)
        {
          ResolveInfo localResolveInfo3 = zza(paramContext, localIntent2);
          if (localResolveInfo3 != null)
          {
            localObject = zza(localIntent1, localResolveInfo3);
            if (zza(paramContext, (Intent)localObject) != null) {
              break;
            }
          }
        }
        if (localArrayList.size() == 0)
        {
          localObject = localIntent1;
          break;
        }
        if ((bool2) && (localActivityManager != null))
        {
          List localList = localActivityManager.getRunningAppProcesses();
          if (localList != null)
          {
            Iterator localIterator1 = localArrayList.iterator();
            ResolveInfo localResolveInfo2;
            Iterator localIterator2;
            do
            {
              while (!localIterator2.hasNext())
              {
                if (!localIterator1.hasNext()) {
                  break;
                }
                localResolveInfo2 = (ResolveInfo)localIterator1.next();
                localIterator2 = localList.iterator();
              }
            } while (!((ActivityManager.RunningAppProcessInfo)localIterator2.next()).processName.equals(localResolveInfo2.activityInfo.packageName));
            localObject = zza(localIntent1, localResolveInfo2);
            break;
          }
        }
        if (bool1)
        {
          localObject = zza(localIntent1, (ResolveInfo)localArrayList.get(0));
          break;
        }
        localObject = localIntent1;
        break;
        label377:
        localUri2 = null;
      }
    }
    
    public Intent zzd(Uri paramUri)
    {
      Intent localIntent;
      if (paramUri == null) {
        localIntent = null;
      }
      for (;;)
      {
        return localIntent;
        localIntent = new Intent("android.intent.action.VIEW");
        localIntent.addFlags(268435456);
        localIntent.setData(paramUri);
        localIntent.setAction("android.intent.action.VIEW");
      }
    }
  }
  
  public static class zza
    extends zzhz
  {
    private final String zzF;
    private final zziz zzoM;
    private final String zzxU = "play.google.com";
    private final String zzxV = "market";
    private final int zzxW = 10;
    
    public zza(zziz paramzziz, String paramString)
    {
      this.zzoM = paramzziz;
      this.zzF = paramString;
    }
    
    public void onStop() {}
    
    public Intent zzaa(String paramString)
    {
      Uri localUri = Uri.parse(paramString);
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.addFlags(268435456);
      localIntent.setData(localUri);
      return localIntent;
    }
    
    public void zzbn()
    {
      int i = 0;
      Object localObject1 = this.zzF;
      int j;
      if (i < 10) {
        j = i + 1;
      }
      for (;;)
      {
        Object localObject2;
        try
        {
          localURL = new URL((String)localObject1);
          boolean bool = "play.google.com".equalsIgnoreCase(localURL.getHost());
          if (!bool) {
            continue;
          }
          localObject2 = localObject1;
        }
        catch (IndexOutOfBoundsException localIndexOutOfBoundsException1)
        {
          URL localURL;
          Intent localIntent;
          HttpURLConnection localHttpURLConnection;
          localObject5 = localIndexOutOfBoundsException1;
          localObject2 = localObject1;
          zzb.zzd("Error while parsing ping URL: " + (String)localObject2, (Throwable)localObject5);
          continue;
        }
        catch (IOException localIOException1)
        {
          localObject4 = localIOException1;
          localObject2 = localObject1;
          zzb.zzd("Error while pinging URL: " + (String)localObject2, (Throwable)localObject4);
          continue;
        }
        catch (RuntimeException localRuntimeException1)
        {
          localObject3 = localRuntimeException1;
          localObject2 = localObject1;
          zzb.zzd("Error while pinging URL: " + (String)localObject2, (Throwable)localObject3);
          continue;
          String str = "";
          continue;
        }
        localIntent = zzaa((String)localObject2);
        zzp.zzbv().zzb(this.zzoM.getContext(), localIntent);
        return;
        if ("market".equalsIgnoreCase(localURL.getProtocol()))
        {
          localObject2 = localObject1;
        }
        else
        {
          localHttpURLConnection = (HttpURLConnection)localURL.openConnection();
          try
          {
            zzp.zzbv().zza(this.zzoM.getContext(), this.zzoM.zzhh().zzJu, false, localHttpURLConnection);
            int k = localHttpURLConnection.getResponseCode();
            Map localMap = localHttpURLConnection.getHeaderFields();
            if ((k >= 300) && (k <= 399))
            {
              List localList = null;
              if (localMap.containsKey("Location"))
              {
                localList = (List)localMap.get("Location");
                if ((localList == null) || (localList.size() <= 0)) {
                  continue;
                }
                str = (String)localList.get(0);
                if (TextUtils.isEmpty(str))
                {
                  zzb.zzaH("Arrived at landing page, this ideally should not happen. Will open it in browser.");
                  localHttpURLConnection.disconnect();
                  localObject2 = localObject1;
                }
              }
              else
              {
                if (!localMap.containsKey("location")) {
                  continue;
                }
                localList = (List)localMap.get("location");
                continue;
              }
            }
          }
          finally
          {
            try
            {
              localHttpURLConnection.disconnect();
              i = j;
              localObject1 = str;
            }
            catch (RuntimeException localRuntimeException2)
            {
              localObject2 = str;
              Object localObject3 = localRuntimeException2;
              continue;
            }
            catch (IOException localIOException2)
            {
              localObject2 = str;
              Object localObject4 = localIOException2;
              continue;
            }
            catch (IndexOutOfBoundsException localIndexOutOfBoundsException2)
            {
              localObject2 = str;
              Object localObject5 = localIndexOutOfBoundsException2;
              continue;
            }
            localObject6 = finally;
            localHttpURLConnection.disconnect();
          }
          localObject2 = localObject1;
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzdr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */