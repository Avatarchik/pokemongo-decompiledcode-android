package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageInstaller.SessionInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.R.drawable;
import com.google.android.gms.R.string;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzml;
import com.google.android.gms.internal.zzmx;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public final class GooglePlayServicesUtil
{
  public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
  @Deprecated
  public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
  @Deprecated
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = ;
  public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  public static boolean zzaal = false;
  public static boolean zzaam = false;
  private static int zzaan = -1;
  private static String zzaao = null;
  private static Integer zzaap = null;
  static final AtomicBoolean zzaaq = new AtomicBoolean();
  private static final AtomicBoolean zzaar = new AtomicBoolean();
  private static final Object zzpy = new Object();
  
  @Deprecated
  public static Dialog getErrorDialog(int paramInt1, Activity paramActivity, int paramInt2)
  {
    return getErrorDialog(paramInt1, paramActivity, paramInt2, null);
  }
  
  @Deprecated
  public static Dialog getErrorDialog(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return zza(paramInt1, paramActivity, null, paramInt2, paramOnCancelListener);
  }
  
  @Deprecated
  public static PendingIntent getErrorPendingIntent(int paramInt1, Context paramContext, int paramInt2)
  {
    return GoogleApiAvailability.getInstance().getErrorResolutionPendingIntent(paramContext, paramInt1, paramInt2);
  }
  
  @Deprecated
  public static String getErrorString(int paramInt)
  {
    return ConnectionResult.getStatusString(paramInt);
  }
  
  /* Error */
  @Deprecated
  public static String getOpenSourceSoftwareLicenseInfo(Context paramContext)
  {
    // Byte code:
    //   0: new 98	android/net/Uri$Builder
    //   3: dup
    //   4: invokespecial 99	android/net/Uri$Builder:<init>	()V
    //   7: ldc 101
    //   9: invokevirtual 105	android/net/Uri$Builder:scheme	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   12: ldc 14
    //   14: invokevirtual 108	android/net/Uri$Builder:authority	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   17: ldc 110
    //   19: invokevirtual 113	android/net/Uri$Builder:appendPath	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   22: ldc 115
    //   24: invokevirtual 113	android/net/Uri$Builder:appendPath	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   27: invokevirtual 119	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   30: astore_1
    //   31: aload_0
    //   32: invokevirtual 125	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   35: aload_1
    //   36: invokevirtual 131	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   39: astore 4
    //   41: new 133	java/util/Scanner
    //   44: dup
    //   45: aload 4
    //   47: invokespecial 136	java/util/Scanner:<init>	(Ljava/io/InputStream;)V
    //   50: ldc -118
    //   52: invokevirtual 142	java/util/Scanner:useDelimiter	(Ljava/lang/String;)Ljava/util/Scanner;
    //   55: invokevirtual 146	java/util/Scanner:next	()Ljava/lang/String;
    //   58: astore 7
    //   60: aload 7
    //   62: astore_3
    //   63: aload 4
    //   65: ifnull +44 -> 109
    //   68: aload 4
    //   70: invokevirtual 151	java/io/InputStream:close	()V
    //   73: goto +36 -> 109
    //   76: astore 6
    //   78: aload 4
    //   80: ifnull +31 -> 111
    //   83: aload 4
    //   85: invokevirtual 151	java/io/InputStream:close	()V
    //   88: goto +23 -> 111
    //   91: astore 5
    //   93: aload 4
    //   95: ifnull +8 -> 103
    //   98: aload 4
    //   100: invokevirtual 151	java/io/InputStream:close	()V
    //   103: aload 5
    //   105: athrow
    //   106: astore_2
    //   107: aconst_null
    //   108: astore_3
    //   109: aload_3
    //   110: areturn
    //   111: aconst_null
    //   112: astore_3
    //   113: goto -4 -> 109
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	116	0	paramContext	Context
    //   30	6	1	localUri	android.net.Uri
    //   106	1	2	localException	Exception
    //   62	51	3	str1	String
    //   39	60	4	localInputStream	java.io.InputStream
    //   91	13	5	localObject	Object
    //   76	1	6	localNoSuchElementException	java.util.NoSuchElementException
    //   58	3	7	str2	String
    // Exception table:
    //   from	to	target	type
    //   41	60	76	java/util/NoSuchElementException
    //   41	60	91	finally
    //   31	41	106	java/lang/Exception
    //   68	106	106	java/lang/Exception
  }
  
  public static Context getRemoteContext(Context paramContext)
  {
    try
    {
      Context localContext2 = paramContext.createPackageContext("com.google.android.gms", 3);
      localContext1 = localContext2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Context localContext1 = null;
      }
    }
    return localContext1;
  }
  
  public static Resources getRemoteResource(Context paramContext)
  {
    try
    {
      Resources localResources2 = paramContext.getPackageManager().getResourcesForApplication("com.google.android.gms");
      localResources1 = localResources2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        Resources localResources1 = null;
      }
    }
    return localResources1;
  }
  
  @Deprecated
  public static int isGooglePlayServicesAvailable(Context paramContext)
  {
    int i = 9;
    if (com.google.android.gms.common.internal.zzd.zzaeK) {
      i = 0;
    }
    for (;;)
    {
      return i;
      PackageManager localPackageManager = paramContext.getPackageManager();
      PackageInfo localPackageInfo;
      zzd localzzd;
      try
      {
        paramContext.getResources().getString(R.string.common_google_play_services_unknown_issue);
        if (!"com.google.android.gms".equals(paramContext.getPackageName())) {
          zzad(paramContext);
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          try
          {
            localPackageInfo = localPackageManager.getPackageInfo("com.google.android.gms", 64);
            localzzd = zzd.zznu();
            if ((!zzml.zzcb(localPackageInfo.versionCode)) && (!zzml.zzan(paramContext))) {
              break label130;
            }
            if (localzzd.zza(localPackageInfo, zzc.zzbz.zzaak) != null) {
              break label215;
            }
            Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException1)
          {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            i = 1;
          }
          localThrowable = localThrowable;
          Log.e("GooglePlayServicesUtil", "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included.");
        }
      }
      continue;
      label130:
      zzc.zza localzza;
      try
      {
        localzza = localzzd.zza(localPackageManager.getPackageInfo("com.android.vending", 8256), zzc.zzbz.zzaak);
        if (localzza != null) {
          break label180;
        }
        Log.w("GooglePlayServicesUtil", "Google Play Store signature invalid.");
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException3)
      {
        Log.w("GooglePlayServicesUtil", "Google Play Store is neither installed nor updating.");
      }
      continue;
      label180:
      zzc.zza[] arrayOfzza = new zzc.zza[1];
      arrayOfzza[0] = localzza;
      if (localzzd.zza(localPackageInfo, arrayOfzza) == null)
      {
        Log.w("GooglePlayServicesUtil", "Google Play services signature invalid.");
      }
      else
      {
        label215:
        int j = zzml.zzca(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        if (zzml.zzca(localPackageInfo.versionCode) < j)
        {
          Log.w("GooglePlayServicesUtil", "Google Play services out of date.  Requires " + GOOGLE_PLAY_SERVICES_VERSION_CODE + " but found " + localPackageInfo.versionCode);
          i = 2;
        }
        else
        {
          Object localObject = localPackageInfo.applicationInfo;
          if (localObject == null) {}
          try
          {
            ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo("com.google.android.gms", 0);
            localObject = localApplicationInfo;
            if (((ApplicationInfo)localObject).enabled) {
              break label339;
            }
            i = 3;
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException2)
          {
            Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", localNameNotFoundException2);
            i = 1;
          }
          continue;
          label339:
          i = 0;
        }
      }
    }
  }
  
  @Deprecated
  public static boolean isUserRecoverableError(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  @Deprecated
  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, int paramInt2)
  {
    return showErrorDialogFragment(paramInt1, paramActivity, paramInt2, null);
  }
  
  @Deprecated
  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return showErrorDialogFragment(paramInt1, paramActivity, null, paramInt2, paramOnCancelListener);
  }
  
  public static boolean showErrorDialogFragment(int paramInt1, Activity paramActivity, Fragment paramFragment, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    Dialog localDialog = zza(paramInt1, paramActivity, paramFragment, paramInt2, paramOnCancelListener);
    if (localDialog == null) {}
    for (boolean bool = false;; bool = true)
    {
      return bool;
      zza(paramActivity, paramOnCancelListener, "GooglePlayServicesErrorDialog", localDialog);
    }
  }
  
  @Deprecated
  public static void showErrorNotification(int paramInt, Context paramContext)
  {
    if ((zzml.zzan(paramContext)) && (paramInt == 2)) {
      paramInt = 42;
    }
    if ((zzd(paramContext, paramInt)) || (zzf(paramContext, paramInt))) {
      zzae(paramContext);
    }
    for (;;)
    {
      return;
      zza(paramInt, paramContext);
    }
  }
  
  private static Dialog zza(int paramInt1, Activity paramActivity, Fragment paramFragment, int paramInt2, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    Object localObject = null;
    if (paramInt1 == 0) {
      return (Dialog)localObject;
    }
    if ((zzml.zzan(paramActivity)) && (paramInt1 == 2)) {
      paramInt1 = 42;
    }
    if (zzmx.zzqx())
    {
      TypedValue localTypedValue = new TypedValue();
      paramActivity.getTheme().resolveAttribute(16843529, localTypedValue, true);
      if ("Theme.Dialog.Alert".equals(paramActivity.getResources().getResourceEntryName(localTypedValue.resourceId))) {
        localObject = new AlertDialog.Builder(paramActivity, 5);
      }
    }
    if (localObject == null) {
      localObject = new AlertDialog.Builder(paramActivity);
    }
    ((AlertDialog.Builder)localObject).setMessage(zzg.zzc(paramActivity, paramInt1, zzaf(paramActivity)));
    if (paramOnCancelListener != null) {
      ((AlertDialog.Builder)localObject).setOnCancelListener(paramOnCancelListener);
    }
    Intent localIntent = GoogleApiAvailability.getInstance().zza(paramActivity, paramInt1, "d");
    if (paramFragment == null) {}
    for (zzh localzzh = new zzh(paramActivity, localIntent, paramInt2);; localzzh = new zzh(paramFragment, localIntent, paramInt2))
    {
      String str1 = zzg.zzh(paramActivity, paramInt1);
      if (str1 != null) {
        ((AlertDialog.Builder)localObject).setPositiveButton(str1, localzzh);
      }
      String str2 = zzg.zzg(paramActivity, paramInt1);
      if (str2 != null) {
        ((AlertDialog.Builder)localObject).setTitle(str2);
      }
      localObject = ((AlertDialog.Builder)localObject).create();
      break;
    }
  }
  
  private static void zza(int paramInt, Context paramContext)
  {
    zza(paramInt, paramContext, null);
  }
  
  private static void zza(int paramInt, Context paramContext, String paramString)
  {
    Resources localResources = paramContext.getResources();
    String str1 = zzaf(paramContext);
    String str2 = zzg.zzi(paramContext, paramInt);
    if (str2 == null) {
      str2 = localResources.getString(R.string.common_google_play_services_notification_ticker);
    }
    String str3 = zzg.zzd(paramContext, paramInt, str1);
    PendingIntent localPendingIntent = GoogleApiAvailability.getInstance().zza(paramContext, paramInt, 0, "n");
    Object localObject;
    int i;
    label168:
    NotificationManager localNotificationManager;
    if (zzml.zzan(paramContext))
    {
      zzx.zzZ(zzmx.zzqy());
      localObject = new Notification.Builder(paramContext).setSmallIcon(R.drawable.common_ic_googleplayservices).setPriority(2).setAutoCancel(true).setStyle(new Notification.BigTextStyle().bigText(str2 + " " + str3)).addAction(R.drawable.common_full_open_on_phone, localResources.getString(R.string.common_open_on_phone), localPendingIntent).build();
      if (!zzbk(paramInt)) {
        break label383;
      }
      zzaaq.set(false);
      i = 10436;
      localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
      if (paramString == null) {
        break label391;
      }
      localNotificationManager.notify(paramString, i, (Notification)localObject);
    }
    for (;;)
    {
      return;
      String str4 = localResources.getString(R.string.common_google_play_services_notification_ticker);
      if (zzmx.zzqu())
      {
        Notification.Builder localBuilder = new Notification.Builder(paramContext).setSmallIcon(17301642).setContentTitle(str2).setContentText(str3).setContentIntent(localPendingIntent).setTicker(str4).setAutoCancel(true);
        if (zzmx.zzqC()) {
          localBuilder.setLocalOnly(true);
        }
        if (zzmx.zzqy()) {
          localBuilder.setStyle(new Notification.BigTextStyle().bigText(str3));
        }
        for (Notification localNotification = localBuilder.build();; localNotification = localBuilder.getNotification())
        {
          if (Build.VERSION.SDK_INT == 19) {
            localNotification.extras.putBoolean("android.support.localOnly", true);
          }
          localObject = localNotification;
          break;
        }
      }
      localObject = new NotificationCompat.Builder(paramContext).setSmallIcon(17301642).setTicker(str4).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(localPendingIntent).setContentTitle(str2).setContentText(str3).build();
      break;
      label383:
      i = 39789;
      break label168;
      label391:
      localNotificationManager.notify(i, (Notification)localObject);
    }
  }
  
  public static void zza(Activity paramActivity, DialogInterface.OnCancelListener paramOnCancelListener, String paramString, Dialog paramDialog)
  {
    try
    {
      bool = paramActivity instanceof FragmentActivity;
      if (bool)
      {
        android.support.v4.app.FragmentManager localFragmentManager1 = ((FragmentActivity)paramActivity).getSupportFragmentManager();
        SupportErrorDialogFragment.newInstance(paramDialog, paramOnCancelListener).show(localFragmentManager1, paramString);
        return;
      }
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      for (;;)
      {
        boolean bool = false;
        continue;
        if (!zzmx.zzqu()) {
          break;
        }
        android.app.FragmentManager localFragmentManager = paramActivity.getFragmentManager();
        ErrorDialogFragment.newInstance(paramDialog, paramOnCancelListener).show(localFragmentManager, paramString);
      }
      throw new RuntimeException("This Activity does not support Fragments.");
    }
  }
  
  @Deprecated
  public static void zzaa(Context paramContext)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    int i = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(paramContext);
    if (i != 0)
    {
      Intent localIntent = GoogleApiAvailability.getInstance().zza(paramContext, i, "e");
      Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + i);
      if (localIntent == null) {
        throw new GooglePlayServicesNotAvailableException(i);
      }
      throw new GooglePlayServicesRepairableException(i, "Google Play Services not available", localIntent);
    }
  }
  
  @Deprecated
  public static void zzac(Context paramContext)
  {
    if (zzaaq.getAndSet(true)) {}
    for (;;)
    {
      return;
      try
      {
        ((NotificationManager)paramContext.getSystemService("notification")).cancel(10436);
      }
      catch (SecurityException localSecurityException) {}
    }
  }
  
  private static void zzad(Context paramContext)
  {
    if (zzaar.get()) {}
    Integer localInteger;
    do
    {
      return;
      for (;;)
      {
        synchronized (zzpy)
        {
          if (zzaao == null)
          {
            zzaao = paramContext.getPackageName();
            try
            {
              Bundle localBundle = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
              if (localBundle != null)
              {
                zzaap = Integer.valueOf(localBundle.getInt("com.google.android.gms.version"));
                localInteger = zzaap;
                if (localInteger != null) {
                  break;
                }
                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
              }
              zzaap = null;
              continue;
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException)
            {
              Log.wtf("GooglePlayServicesUtil", "This should never happen.", localNameNotFoundException);
              continue;
            }
          }
        }
        if (!zzaao.equals(paramContext.getPackageName())) {
          throw new IllegalArgumentException("isGooglePlayServicesAvailable should only be called with Context from your application's package. A previous call used package '" + zzaao + "' and this call used package '" + paramContext.getPackageName() + "'.");
        }
      }
    } while (localInteger.intValue() == GOOGLE_PLAY_SERVICES_VERSION_CODE);
    throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected " + GOOGLE_PLAY_SERVICES_VERSION_CODE + " but" + " found " + localInteger + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
  }
  
  private static void zzae(Context paramContext)
  {
    zza localzza = new zza(paramContext);
    localzza.sendMessageDelayed(localzza.obtainMessage(1), 120000L);
  }
  
  public static String zzaf(Context paramContext)
  {
    String str = paramContext.getApplicationInfo().name;
    PackageManager localPackageManager;
    if (TextUtils.isEmpty(str))
    {
      str = paramContext.getPackageName();
      localPackageManager = paramContext.getApplicationContext().getPackageManager();
    }
    try
    {
      ApplicationInfo localApplicationInfo2 = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
      localApplicationInfo1 = localApplicationInfo2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        ApplicationInfo localApplicationInfo1 = null;
      }
    }
    if (localApplicationInfo1 != null) {
      str = localPackageManager.getApplicationLabel(localApplicationInfo1).toString();
    }
    return str;
  }
  
  public static boolean zzag(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    if ((zzmx.zzqD()) && (localPackageManager.hasSystemFeature("com.google.sidewinder"))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean zzah(Context paramContext)
  {
    if (zzmx.zzqA())
    {
      Bundle localBundle = ((UserManager)paramContext.getSystemService("user")).getApplicationRestrictions(paramContext.getPackageName());
      if ((localBundle == null) || (!"true".equals(localBundle.getString("restricted_profile")))) {}
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean zzb(Context paramContext, int paramInt, String paramString)
  {
    boolean bool = false;
    AppOpsManager localAppOpsManager;
    if (zzmx.zzqB()) {
      localAppOpsManager = (AppOpsManager)paramContext.getSystemService("appops");
    }
    for (;;)
    {
      try
      {
        localAppOpsManager.checkPackage(paramInt, paramString);
        bool = true;
      }
      catch (SecurityException localSecurityException)
      {
        String[] arrayOfString;
        int i;
        continue;
      }
      return bool;
      arrayOfString = paramContext.getPackageManager().getPackagesForUid(paramInt);
      if ((paramString != null) && (arrayOfString != null))
      {
        i = 0;
        if (i < arrayOfString.length) {
          if (paramString.equals(arrayOfString[i])) {
            bool = true;
          } else {
            i++;
          }
        }
      }
    }
  }
  
  public static boolean zzb(PackageManager paramPackageManager)
  {
    for (boolean bool = true;; bool = false) {
      synchronized (zzpy)
      {
        int i = zzaan;
        if (i == -1) {}
        try
        {
          PackageInfo localPackageInfo = paramPackageManager.getPackageInfo("com.google.android.gms", 64);
          zzd localzzd = zzd.zznu();
          zzc.zza[] arrayOfzza = new zzc.zza[1];
          arrayOfzza[0] = zzc.zzaad[1];
          if (localzzd.zza(localPackageInfo, arrayOfzza) != null) {}
          for (zzaan = 1; zzaan != 0; zzaan = 0) {
            return bool;
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            zzaan = 0;
          }
        }
      }
    }
  }
  
  @Deprecated
  public static boolean zzb(PackageManager paramPackageManager, String paramString)
  {
    return zzd.zznu().zzb(paramPackageManager, paramString);
  }
  
  @Deprecated
  public static Intent zzbj(int paramInt)
  {
    return GoogleApiAvailability.getInstance().zza(null, paramInt, null);
  }
  
  private static boolean zzbk(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  public static boolean zzc(PackageManager paramPackageManager)
  {
    if ((zzb(paramPackageManager)) || (!zznt())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  @Deprecated
  public static boolean zzd(Context paramContext, int paramInt)
  {
    int i = 1;
    if (paramInt == 18) {}
    for (;;)
    {
      return i;
      boolean bool;
      if (paramInt == i) {
        bool = zzj(paramContext, "com.google.android.gms");
      } else {
        bool = false;
      }
    }
  }
  
  public static boolean zze(Context paramContext, int paramInt)
  {
    if ((zzb(paramContext, paramInt, "com.google.android.gms")) && (zzb(paramContext.getPackageManager(), "com.google.android.gms"))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  @Deprecated
  public static boolean zzf(Context paramContext, int paramInt)
  {
    if (paramInt == 9) {}
    for (boolean bool = zzj(paramContext, "com.android.vending");; bool = false) {
      return bool;
    }
  }
  
  static boolean zzj(Context paramContext, String paramString)
  {
    boolean bool;
    if (zzmx.zzqD())
    {
      Iterator localIterator = paramContext.getPackageManager().getPackageInstaller().getAllSessions().iterator();
      while (localIterator.hasNext()) {
        if (paramString.equals(((PackageInstaller.SessionInfo)localIterator.next()).getAppPackageName())) {
          bool = true;
        }
      }
    }
    for (;;)
    {
      return bool;
      if (zzah(paramContext))
      {
        bool = false;
      }
      else
      {
        PackageManager localPackageManager = paramContext.getPackageManager();
        try
        {
          bool = localPackageManager.getApplicationInfo(paramString, 8192).enabled;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          bool = false;
        }
      }
    }
  }
  
  private static int zzns()
  {
    return 8115000;
  }
  
  public static boolean zznt()
  {
    if (zzaal) {}
    for (boolean bool = zzaam;; bool = "user".equals(Build.TYPE)) {
      return bool;
    }
  }
  
  private static class zza
    extends Handler
  {
    private final Context zzqZ;
    
    zza(Context paramContext) {}
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        Log.w("GooglePlayServicesUtil", "Don't know how to handle this message: " + paramMessage.what);
      }
      for (;;)
      {
        return;
        int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzqZ);
        if (GooglePlayServicesUtil.isUserRecoverableError(i)) {
          GooglePlayServicesUtil.zzb(i, this.zzqZ);
        }
      }
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/common/GooglePlayServicesUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */