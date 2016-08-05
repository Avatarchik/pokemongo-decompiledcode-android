package com.google.android.gms.gcm;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.MissingFormatArgumentException;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

class zza
{
  static zza zzaCi;
  private Context mContext;
  
  private zza(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
  }
  
  private void zza(String paramString, Notification paramNotification)
  {
    if (Log.isLoggable("GcmNotification", 3)) {
      Log.d("GcmNotification", "Showing notification");
    }
    NotificationManager localNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
    if (TextUtils.isEmpty(paramString)) {
      paramString = "GCM-Notification:" + SystemClock.uptimeMillis();
    }
    localNotificationManager.notify(paramString, 0, paramNotification);
  }
  
  /**
   * @deprecated
   */
  static zza zzaz(Context paramContext)
  {
    try
    {
      if (zzaCi == null) {
        zzaCi = new zza(paramContext);
      }
      zza localzza = zzaCi;
      return localzza;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  static String zzc(Bundle paramBundle, String paramString)
  {
    String str = paramBundle.getString(paramString);
    if (str == null) {
      str = paramBundle.getString(paramString.replace("gcm.n.", "gcm.notification."));
    }
    return str;
  }
  
  private String zzd(Bundle paramBundle, String paramString)
  {
    localObject = zzc(paramBundle, paramString);
    if (!TextUtils.isEmpty((CharSequence)localObject)) {}
    for (;;)
    {
      return (String)localObject;
      str1 = zzc(paramBundle, paramString + "_loc_key");
      if (TextUtils.isEmpty(str1))
      {
        localObject = null;
        continue;
      }
      localResources = this.mContext.getResources();
      i = localResources.getIdentifier(str1, "string", this.mContext.getPackageName());
      if (i == 0) {
        throw new zza(zzdj(new StringBuilder().append(paramString).append("_loc_key").toString()) + " resource not found: " + str1, null);
      }
      String str2 = zzc(paramBundle, paramString + "_loc_args");
      if (TextUtils.isEmpty(str2))
      {
        localObject = localResources.getString(i);
        continue;
      }
      try
      {
        JSONArray localJSONArray = new JSONArray(str2);
        String[] arrayOfString = new String[localJSONArray.length()];
        for (int j = 0; j < arrayOfString.length; j++) {
          arrayOfString[j] = localJSONArray.opt(j);
        }
        try
        {
          String str3 = localResources.getString(i, arrayOfString);
          localObject = str3;
        }
        catch (MissingFormatArgumentException localMissingFormatArgumentException)
        {
          throw new zza("Missing format argument for " + str1 + ": " + localMissingFormatArgumentException, null);
        }
      }
      catch (JSONException localJSONException)
      {
        throw new zza("Malformed " + zzdj(new StringBuilder().append(paramString).append("_loc_args").toString()) + ": " + str2, null);
      }
    }
  }
  
  private String zzdj(String paramString)
  {
    return paramString.substring("gcm.n.".length());
  }
  
  private int zzdk(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new zza("Missing icon", null);
    }
    Resources localResources = this.mContext.getResources();
    int i = localResources.getIdentifier(paramString, "drawable", this.mContext.getPackageName());
    if (i != 0) {}
    do
    {
      return i;
      i = localResources.getIdentifier(paramString, "mipmap", this.mContext.getPackageName());
    } while (i != 0);
    throw new zza("Icon resource not found: " + paramString, null);
  }
  
  private Uri zzdl(String paramString)
  {
    Uri localUri = null;
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return localUri;
      if (!"default".equals(paramString)) {
        break;
      }
      localUri = RingtoneManager.getDefaultUri(2);
    }
    throw new zza("Invalid sound: " + paramString, null);
  }
  
  static boolean zzu(Bundle paramBundle)
  {
    if (zzc(paramBundle, "gcm.n.icon") != null) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private int zzvW()
  {
    return (int)SystemClock.uptimeMillis();
  }
  
  private Notification zzw(Bundle paramBundle)
  {
    String str1 = zzd(paramBundle, "gcm.n.title");
    if (TextUtils.isEmpty(str1)) {
      throw new zza("Missing title", null);
    }
    String str2 = zzd(paramBundle, "gcm.n.body");
    int i = zzdk(zzc(paramBundle, "gcm.n.icon"));
    Uri localUri = zzdl(zzc(paramBundle, "gcm.n.sound"));
    PendingIntent localPendingIntent = zzx(paramBundle);
    Notification.Builder localBuilder1;
    Notification localNotification;
    if (Build.VERSION.SDK_INT >= 11)
    {
      localBuilder1 = new Notification.Builder(this.mContext).setAutoCancel(true).setSmallIcon(i).setContentTitle(str1).setContentText(str2);
      if (Build.VERSION.SDK_INT >= 21)
      {
        String str3 = zzc(paramBundle, "gcm.n.color");
        if (!TextUtils.isEmpty(str3)) {
          localBuilder1.setColor(Color.parseColor(str3));
        }
      }
      if (localUri != null) {
        localBuilder1.setSound(localUri);
      }
      if (localPendingIntent != null) {
        localBuilder1.setContentIntent(localPendingIntent);
      }
      if (Build.VERSION.SDK_INT >= 16) {
        localNotification = localBuilder1.build();
      }
    }
    for (;;)
    {
      return localNotification;
      localNotification = localBuilder1.getNotification();
      continue;
      if (localPendingIntent == null)
      {
        Intent localIntent = new Intent();
        localIntent.setPackage("com.google.example.invalidpackage");
        localPendingIntent = PendingIntent.getBroadcast(this.mContext, 0, localIntent, 0);
      }
      NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(this.mContext).setSmallIcon(i).setAutoCancel(true).setContentIntent(localPendingIntent).setContentTitle(str1).setContentText(str2);
      if (localUri != null) {
        localBuilder.setSound(localUri);
      }
      localNotification = localBuilder.build();
    }
  }
  
  private PendingIntent zzx(Bundle paramBundle)
  {
    String str1 = zzc(paramBundle, "gcm.n.click_action");
    if (TextUtils.isEmpty(str1)) {}
    Intent localIntent;
    for (PendingIntent localPendingIntent = null;; localPendingIntent = PendingIntent.getActivity(this.mContext, zzvW(), localIntent, 1073741824))
    {
      return localPendingIntent;
      localIntent = new Intent(str1);
      localIntent.setPackage(this.mContext.getPackageName());
      localIntent.setFlags(268435456);
      localIntent.putExtras(paramBundle);
      Iterator localIterator = paramBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str2 = (String)localIterator.next();
        if ((str2.startsWith("gcm.n.")) || (str2.startsWith("gcm.notification."))) {
          localIntent.removeExtra(str2);
        }
      }
    }
  }
  
  boolean zzv(Bundle paramBundle)
  {
    try
    {
      Notification localNotification = zzw(paramBundle);
      zza(zzc(paramBundle, "gcm.n.tag"), localNotification);
      bool = true;
    }
    catch (zza localzza)
    {
      for (;;)
      {
        Log.w("GcmNotification", "Failed to show notification: " + localzza.getMessage());
        boolean bool = false;
      }
    }
    return bool;
  }
  
  private class zza
    extends IllegalArgumentException
  {
    private zza(String paramString)
    {
      super();
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/gcm/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */