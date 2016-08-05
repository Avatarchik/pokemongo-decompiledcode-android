package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzid;

@zzgr
public class zza
{
  public boolean zza(Context paramContext, AdLauncherIntentInfoParcel paramAdLauncherIntentInfoParcel, zzn paramzzn)
  {
    boolean bool = false;
    if (paramAdLauncherIntentInfoParcel == null) {
      zzb.zzaH("No intent data for launcher overlay.");
    }
    for (;;)
    {
      return bool;
      Intent localIntent = new Intent();
      if (TextUtils.isEmpty(paramAdLauncherIntentInfoParcel.url))
      {
        zzb.zzaH("Open GMSG did not contain a URL.");
      }
      else
      {
        if (!TextUtils.isEmpty(paramAdLauncherIntentInfoParcel.mimeType)) {
          localIntent.setDataAndType(Uri.parse(paramAdLauncherIntentInfoParcel.url), paramAdLauncherIntentInfoParcel.mimeType);
        }
        String[] arrayOfString;
        for (;;)
        {
          localIntent.setAction("android.intent.action.VIEW");
          if (!TextUtils.isEmpty(paramAdLauncherIntentInfoParcel.packageName)) {
            localIntent.setPackage(paramAdLauncherIntentInfoParcel.packageName);
          }
          if (TextUtils.isEmpty(paramAdLauncherIntentInfoParcel.zzAL)) {
            break label184;
          }
          arrayOfString = paramAdLauncherIntentInfoParcel.zzAL.split("/", 2);
          if (arrayOfString.length >= 2) {
            break label170;
          }
          zzb.zzaH("Could not parse component name from open GMSG: " + paramAdLauncherIntentInfoParcel.zzAL);
          break;
          localIntent.setData(Uri.parse(paramAdLauncherIntentInfoParcel.url));
        }
        label170:
        localIntent.setClassName(arrayOfString[0], arrayOfString[1]);
        label184:
        String str = paramAdLauncherIntentInfoParcel.zzAM;
        if (!TextUtils.isEmpty(str)) {}
        try
        {
          int j = Integer.parseInt(str);
          i = j;
          localIntent.addFlags(i);
        }
        catch (NumberFormatException localNumberFormatException)
        {
          for (;;)
          {
            try
            {
              zzb.v("Launching an intent: " + localIntent.toURI());
              zzp.zzbv().zzb(paramContext, localIntent);
              if (paramzzn != null) {
                paramzzn.zzaO();
              }
              bool = true;
            }
            catch (ActivityNotFoundException localActivityNotFoundException)
            {
              int i;
              zzb.zzaH(localActivityNotFoundException.getMessage());
            }
            localNumberFormatException = localNumberFormatException;
            zzb.zzaH("Could not parse intent flags.");
            i = 0;
          }
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/internal/overlay/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */