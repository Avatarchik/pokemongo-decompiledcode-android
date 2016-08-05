package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzx;
import java.io.IOException;
import java.net.URISyntaxException;

public final class GoogleAuthUtil
{
  public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
  public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
  public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
  public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
  public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
  public static final String KEY_ANDROID_PACKAGE_NAME;
  public static final String KEY_CALLER_UID;
  public static final String KEY_REQUEST_ACTIONS = "request_visible_actions";
  @Deprecated
  public static final String KEY_REQUEST_VISIBLE_ACTIVITIES = "request_visible_actions";
  public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
  private static final ComponentName zzRw;
  private static final ComponentName zzRx;
  
  static
  {
    String str1;
    if (Build.VERSION.SDK_INT >= 11)
    {
      str1 = "callerUid";
      KEY_CALLER_UID = str1;
      if (Build.VERSION.SDK_INT < 14) {
        break label65;
      }
    }
    label65:
    for (String str2 = "androidPackageName";; str2 = "androidPackageName")
    {
      KEY_ANDROID_PACKAGE_NAME = str2;
      zzRw = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
      zzRx = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");
      return;
      str1 = "callerUid";
      break;
    }
  }
  
  /* Error */
  public static void clearToken(Context paramContext, String paramString)
    throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 81	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: astore_2
    //   5: ldc 83
    //   7: invokestatic 89	com/google/android/gms/common/internal/zzx:zzcj	(Ljava/lang/String;)V
    //   10: aload_2
    //   11: invokestatic 93	com/google/android/gms/auth/GoogleAuthUtil:zzaa	(Landroid/content/Context;)V
    //   14: new 95	android/os/Bundle
    //   17: dup
    //   18: invokespecial 96	android/os/Bundle:<init>	()V
    //   21: astore_3
    //   22: aload_0
    //   23: invokevirtual 100	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   26: getfield 105	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   29: astore 4
    //   31: aload_3
    //   32: ldc 107
    //   34: aload 4
    //   36: invokevirtual 110	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   39: aload_3
    //   40: getstatic 45	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   43: invokevirtual 114	android/os/Bundle:containsKey	(Ljava/lang/String;)Z
    //   46: ifne +12 -> 58
    //   49: aload_3
    //   50: getstatic 45	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   53: aload 4
    //   55: invokevirtual 110	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: new 116	com/google/android/gms/common/zza
    //   61: dup
    //   62: invokespecial 117	com/google/android/gms/common/zza:<init>	()V
    //   65: astore 5
    //   67: aload_2
    //   68: invokestatic 123	com/google/android/gms/common/internal/zzl:zzal	(Landroid/content/Context;)Lcom/google/android/gms/common/internal/zzl;
    //   71: astore 6
    //   73: aload 6
    //   75: getstatic 57	com/google/android/gms/auth/GoogleAuthUtil:zzRw	Landroid/content/ComponentName;
    //   78: aload 5
    //   80: ldc 125
    //   82: invokevirtual 129	com/google/android/gms/common/internal/zzl:zza	(Landroid/content/ComponentName;Landroid/content/ServiceConnection;Ljava/lang/String;)Z
    //   85: ifeq +113 -> 198
    //   88: aload 5
    //   90: invokevirtual 133	com/google/android/gms/common/zza:zzno	()Landroid/os/IBinder;
    //   93: invokestatic 138	com/google/android/gms/internal/zzau$zza:zza	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/zzau;
    //   96: aload_1
    //   97: aload_3
    //   98: invokeinterface 143 3 0
    //   103: astore 11
    //   105: aload 11
    //   107: ldc -111
    //   109: invokevirtual 149	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   112: astore 12
    //   114: aload 11
    //   116: ldc -105
    //   118: invokevirtual 154	android/os/Bundle:getBoolean	(Ljava/lang/String;)Z
    //   121: ifne +52 -> 173
    //   124: new 69	com/google/android/gms/auth/GoogleAuthException
    //   127: dup
    //   128: aload 12
    //   130: invokespecial 156	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   133: athrow
    //   134: astore 9
    //   136: ldc 125
    //   138: ldc -98
    //   140: aload 9
    //   142: invokestatic 164	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   145: pop
    //   146: new 71	java/io/IOException
    //   149: dup
    //   150: ldc -90
    //   152: invokespecial 167	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   155: athrow
    //   156: astore 8
    //   158: aload 6
    //   160: getstatic 57	com/google/android/gms/auth/GoogleAuthUtil:zzRw	Landroid/content/ComponentName;
    //   163: aload 5
    //   165: ldc 125
    //   167: invokevirtual 171	com/google/android/gms/common/internal/zzl:zzb	(Landroid/content/ComponentName;Landroid/content/ServiceConnection;Ljava/lang/String;)V
    //   170: aload 8
    //   172: athrow
    //   173: aload 6
    //   175: getstatic 57	com/google/android/gms/auth/GoogleAuthUtil:zzRw	Landroid/content/ComponentName;
    //   178: aload 5
    //   180: ldc 125
    //   182: invokevirtual 171	com/google/android/gms/common/internal/zzl:zzb	(Landroid/content/ComponentName;Landroid/content/ServiceConnection;Ljava/lang/String;)V
    //   185: return
    //   186: astore 7
    //   188: new 69	com/google/android/gms/auth/GoogleAuthException
    //   191: dup
    //   192: ldc -83
    //   194: invokespecial 156	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   197: athrow
    //   198: new 71	java/io/IOException
    //   201: dup
    //   202: ldc -81
    //   204: invokespecial 167	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   207: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	208	0	paramContext	Context
    //   0	208	1	paramString	String
    //   4	64	2	localContext	Context
    //   21	77	3	localBundle1	Bundle
    //   29	25	4	str1	String
    //   65	114	5	localzza	com.google.android.gms.common.zza
    //   71	103	6	localzzl	com.google.android.gms.common.internal.zzl
    //   186	1	7	localInterruptedException	InterruptedException
    //   156	15	8	localObject	Object
    //   134	7	9	localRemoteException	android.os.RemoteException
    //   103	12	11	localBundle2	Bundle
    //   112	17	12	str2	String
    // Exception table:
    //   from	to	target	type
    //   88	134	134	android/os/RemoteException
    //   88	134	156	finally
    //   136	156	156	finally
    //   188	198	156	finally
    //   88	134	186	java/lang/InterruptedException
  }
  
  /* Error */
  public static java.util.List<AccountChangeEvent> getAccountChangeEvents(Context paramContext, int paramInt, String paramString)
    throws GoogleAuthException, IOException
  {
    // Byte code:
    //   0: aload_2
    //   1: ldc -77
    //   3: invokestatic 183	com/google/android/gms/common/internal/zzx:zzh	(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/String;
    //   6: pop
    //   7: ldc 83
    //   9: invokestatic 89	com/google/android/gms/common/internal/zzx:zzcj	(Ljava/lang/String;)V
    //   12: aload_0
    //   13: invokevirtual 81	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   16: astore 4
    //   18: aload 4
    //   20: invokestatic 93	com/google/android/gms/auth/GoogleAuthUtil:zzaa	(Landroid/content/Context;)V
    //   23: new 116	com/google/android/gms/common/zza
    //   26: dup
    //   27: invokespecial 117	com/google/android/gms/common/zza:<init>	()V
    //   30: astore 5
    //   32: aload 4
    //   34: invokestatic 123	com/google/android/gms/common/internal/zzl:zzal	(Landroid/content/Context;)Lcom/google/android/gms/common/internal/zzl;
    //   37: astore 6
    //   39: aload 6
    //   41: getstatic 57	com/google/android/gms/auth/GoogleAuthUtil:zzRw	Landroid/content/ComponentName;
    //   44: aload 5
    //   46: ldc 125
    //   48: invokevirtual 129	com/google/android/gms/common/internal/zzl:zza	(Landroid/content/ComponentName;Landroid/content/ServiceConnection;Ljava/lang/String;)Z
    //   51: ifeq +102 -> 153
    //   54: aload 5
    //   56: invokevirtual 133	com/google/android/gms/common/zza:zzno	()Landroid/os/IBinder;
    //   59: invokestatic 138	com/google/android/gms/internal/zzau$zza:zza	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/zzau;
    //   62: new 185	com/google/android/gms/auth/AccountChangeEventsRequest
    //   65: dup
    //   66: invokespecial 186	com/google/android/gms/auth/AccountChangeEventsRequest:<init>	()V
    //   69: aload_2
    //   70: invokevirtual 190	com/google/android/gms/auth/AccountChangeEventsRequest:setAccountName	(Ljava/lang/String;)Lcom/google/android/gms/auth/AccountChangeEventsRequest;
    //   73: iload_1
    //   74: invokevirtual 194	com/google/android/gms/auth/AccountChangeEventsRequest:setEventIndex	(I)Lcom/google/android/gms/auth/AccountChangeEventsRequest;
    //   77: invokeinterface 197 2 0
    //   82: invokevirtual 203	com/google/android/gms/auth/AccountChangeEventsResponse:getEvents	()Ljava/util/List;
    //   85: astore 11
    //   87: aload 6
    //   89: getstatic 57	com/google/android/gms/auth/GoogleAuthUtil:zzRw	Landroid/content/ComponentName;
    //   92: aload 5
    //   94: ldc 125
    //   96: invokevirtual 171	com/google/android/gms/common/internal/zzl:zzb	(Landroid/content/ComponentName;Landroid/content/ServiceConnection;Ljava/lang/String;)V
    //   99: aload 11
    //   101: areturn
    //   102: astore 9
    //   104: ldc 125
    //   106: ldc -98
    //   108: aload 9
    //   110: invokestatic 164	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   113: pop
    //   114: new 71	java/io/IOException
    //   117: dup
    //   118: ldc -90
    //   120: invokespecial 167	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   123: athrow
    //   124: astore 8
    //   126: aload 6
    //   128: getstatic 57	com/google/android/gms/auth/GoogleAuthUtil:zzRw	Landroid/content/ComponentName;
    //   131: aload 5
    //   133: ldc 125
    //   135: invokevirtual 171	com/google/android/gms/common/internal/zzl:zzb	(Landroid/content/ComponentName;Landroid/content/ServiceConnection;Ljava/lang/String;)V
    //   138: aload 8
    //   140: athrow
    //   141: astore 7
    //   143: new 69	com/google/android/gms/auth/GoogleAuthException
    //   146: dup
    //   147: ldc -83
    //   149: invokespecial 156	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   152: athrow
    //   153: new 71	java/io/IOException
    //   156: dup
    //   157: ldc -81
    //   159: invokespecial 167	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   162: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	163	0	paramContext	Context
    //   0	163	1	paramInt	int
    //   0	163	2	paramString	String
    //   16	17	4	localContext	Context
    //   30	102	5	localzza	com.google.android.gms.common.zza
    //   37	90	6	localzzl	com.google.android.gms.common.internal.zzl
    //   141	1	7	localInterruptedException	InterruptedException
    //   124	15	8	localObject	Object
    //   102	7	9	localRemoteException	android.os.RemoteException
    //   85	15	11	localList	java.util.List
    // Exception table:
    //   from	to	target	type
    //   54	87	102	android/os/RemoteException
    //   54	87	124	finally
    //   104	124	124	finally
    //   143	153	124	finally
    //   54	87	141	java/lang/InterruptedException
  }
  
  public static String getAccountId(Context paramContext, String paramString)
    throws GoogleAuthException, IOException
  {
    zzx.zzh(paramString, "accountName must be provided");
    zzx.zzcj("Calling this from your main thread can lead to deadlock");
    zzaa(paramContext.getApplicationContext());
    return getToken(paramContext, paramString, "^^_account_id_^^", new Bundle());
  }
  
  public static String getToken(Context paramContext, Account paramAccount, String paramString)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    return getToken(paramContext, paramAccount, paramString, new Bundle());
  }
  
  public static String getToken(Context paramContext, Account paramAccount, String paramString, Bundle paramBundle)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    return zza(paramContext, paramAccount, paramString, paramBundle).getToken();
  }
  
  @Deprecated
  public static String getToken(Context paramContext, String paramString1, String paramString2)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    return getToken(paramContext, new Account(paramString1, "com.google"), paramString2);
  }
  
  @Deprecated
  public static String getToken(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    return getToken(paramContext, new Account(paramString1, "com.google"), paramString2, paramBundle);
  }
  
  public static String getTokenWithNotification(Context paramContext, Account paramAccount, String paramString, Bundle paramBundle)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    return zzb(paramContext, paramAccount, paramString, paramBundle).getToken();
  }
  
  public static String getTokenWithNotification(Context paramContext, Account paramAccount, String paramString, Bundle paramBundle, Intent paramIntent)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    zzi(paramIntent);
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    paramBundle.putParcelable("callback_intent", paramIntent);
    paramBundle.putBoolean("handle_notification", true);
    return zzc(paramContext, paramAccount, paramString, paramBundle).getToken();
  }
  
  public static String getTokenWithNotification(Context paramContext, Account paramAccount, String paramString1, Bundle paramBundle1, String paramString2, Bundle paramBundle2)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    if (TextUtils.isEmpty(paramString2)) {
      throw new IllegalArgumentException("Authority cannot be empty or null.");
    }
    if (paramBundle1 == null) {
      paramBundle1 = new Bundle();
    }
    if (paramBundle2 == null) {
      paramBundle2 = new Bundle();
    }
    ContentResolver.validateSyncExtrasBundle(paramBundle2);
    paramBundle1.putString("authority", paramString2);
    paramBundle1.putBundle("sync_extras", paramBundle2);
    paramBundle1.putBoolean("handle_notification", true);
    return zzc(paramContext, paramAccount, paramString1, paramBundle1).getToken();
  }
  
  @Deprecated
  public static String getTokenWithNotification(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    return getTokenWithNotification(paramContext, new Account(paramString1, "com.google"), paramString2, paramBundle);
  }
  
  @Deprecated
  public static String getTokenWithNotification(Context paramContext, String paramString1, String paramString2, Bundle paramBundle, Intent paramIntent)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    return getTokenWithNotification(paramContext, new Account(paramString1, "com.google"), paramString2, paramBundle, paramIntent);
  }
  
  @Deprecated
  public static String getTokenWithNotification(Context paramContext, String paramString1, String paramString2, Bundle paramBundle1, String paramString3, Bundle paramBundle2)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    return getTokenWithNotification(paramContext, new Account(paramString1, "com.google"), paramString2, paramBundle1, paramString3, paramBundle2);
  }
  
  @Deprecated
  public static void invalidateToken(Context paramContext, String paramString)
  {
    AccountManager.get(paramContext).invalidateAuthToken("com.google", paramString);
  }
  
  /* Error */
  public static TokenData zza(Context paramContext, Account paramAccount, String paramString, Bundle paramBundle)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 81	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: astore 4
    //   6: ldc 83
    //   8: invokestatic 89	com/google/android/gms/common/internal/zzx:zzcj	(Ljava/lang/String;)V
    //   11: aload 4
    //   13: invokestatic 93	com/google/android/gms/auth/GoogleAuthUtil:zzaa	(Landroid/content/Context;)V
    //   16: aload_3
    //   17: ifnonnull +179 -> 196
    //   20: new 95	android/os/Bundle
    //   23: dup
    //   24: invokespecial 96	android/os/Bundle:<init>	()V
    //   27: astore 5
    //   29: aload_0
    //   30: invokevirtual 100	android/content/Context:getApplicationInfo	()Landroid/content/pm/ApplicationInfo;
    //   33: getfield 105	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   36: astore 6
    //   38: aload 5
    //   40: ldc 107
    //   42: aload 6
    //   44: invokevirtual 110	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   47: aload 5
    //   49: getstatic 45	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   52: invokevirtual 149	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   55: invokestatic 263	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   58: ifeq +13 -> 71
    //   61: aload 5
    //   63: getstatic 45	com/google/android/gms/auth/GoogleAuthUtil:KEY_ANDROID_PACKAGE_NAME	Ljava/lang/String;
    //   66: aload 6
    //   68: invokevirtual 110	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   71: aload 5
    //   73: ldc_w 302
    //   76: invokestatic 308	android/os/SystemClock:elapsedRealtime	()J
    //   79: invokevirtual 312	android/os/Bundle:putLong	(Ljava/lang/String;J)V
    //   82: new 116	com/google/android/gms/common/zza
    //   85: dup
    //   86: invokespecial 117	com/google/android/gms/common/zza:<init>	()V
    //   89: astore 7
    //   91: aload 4
    //   93: invokestatic 123	com/google/android/gms/common/internal/zzl:zzal	(Landroid/content/Context;)Lcom/google/android/gms/common/internal/zzl;
    //   96: astore 8
    //   98: aload 8
    //   100: getstatic 57	com/google/android/gms/auth/GoogleAuthUtil:zzRw	Landroid/content/ComponentName;
    //   103: aload 7
    //   105: ldc 125
    //   107: invokevirtual 129	com/google/android/gms/common/internal/zzl:zza	(Landroid/content/ComponentName;Landroid/content/ServiceConnection;Ljava/lang/String;)Z
    //   110: ifeq +218 -> 328
    //   113: aload 7
    //   115: invokevirtual 133	com/google/android/gms/common/zza:zzno	()Landroid/os/IBinder;
    //   118: invokestatic 138	com/google/android/gms/internal/zzau$zza:zza	(Landroid/os/IBinder;)Lcom/google/android/gms/internal/zzau;
    //   121: aload_1
    //   122: aload_2
    //   123: aload 5
    //   125: invokeinterface 315 4 0
    //   130: astore 13
    //   132: aload 13
    //   134: ifnonnull +75 -> 209
    //   137: ldc 125
    //   139: ldc_w 317
    //   142: invokestatic 321	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   145: pop
    //   146: new 69	com/google/android/gms/auth/GoogleAuthException
    //   149: dup
    //   150: ldc_w 323
    //   153: invokespecial 156	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   156: athrow
    //   157: astore 11
    //   159: ldc 125
    //   161: ldc -98
    //   163: aload 11
    //   165: invokestatic 164	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   168: pop
    //   169: new 71	java/io/IOException
    //   172: dup
    //   173: ldc -90
    //   175: invokespecial 167	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   178: athrow
    //   179: astore 10
    //   181: aload 8
    //   183: getstatic 57	com/google/android/gms/auth/GoogleAuthUtil:zzRw	Landroid/content/ComponentName;
    //   186: aload 7
    //   188: ldc 125
    //   190: invokevirtual 171	com/google/android/gms/common/internal/zzl:zzb	(Landroid/content/ComponentName;Landroid/content/ServiceConnection;Ljava/lang/String;)V
    //   193: aload 10
    //   195: athrow
    //   196: new 95	android/os/Bundle
    //   199: dup
    //   200: aload_3
    //   201: invokespecial 325	android/os/Bundle:<init>	(Landroid/os/Bundle;)V
    //   204: astore 5
    //   206: goto -177 -> 29
    //   209: aload 13
    //   211: ldc_w 327
    //   214: invokestatic 330	com/google/android/gms/auth/TokenData:zza	(Landroid/os/Bundle;Ljava/lang/String;)Lcom/google/android/gms/auth/TokenData;
    //   217: astore 14
    //   219: aload 14
    //   221: ifnull +18 -> 239
    //   224: aload 8
    //   226: getstatic 57	com/google/android/gms/auth/GoogleAuthUtil:zzRw	Landroid/content/ComponentName;
    //   229: aload 7
    //   231: ldc 125
    //   233: invokevirtual 171	com/google/android/gms/common/internal/zzl:zzb	(Landroid/content/ComponentName;Landroid/content/ServiceConnection;Ljava/lang/String;)V
    //   236: aload 14
    //   238: areturn
    //   239: aload 13
    //   241: ldc -111
    //   243: invokevirtual 149	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   246: astore 15
    //   248: aload 13
    //   250: ldc_w 332
    //   253: invokevirtual 336	android/os/Bundle:getParcelable	(Ljava/lang/String;)Landroid/os/Parcelable;
    //   256: checkcast 338	android/content/Intent
    //   259: astore 16
    //   261: aload 15
    //   263: invokestatic 344	com/google/android/gms/auth/firstparty/shared/zzd:zzbE	(Ljava/lang/String;)Lcom/google/android/gms/auth/firstparty/shared/zzd;
    //   266: astore 17
    //   268: aload 17
    //   270: invokestatic 347	com/google/android/gms/auth/firstparty/shared/zzd:zza	(Lcom/google/android/gms/auth/firstparty/shared/zzd;)Z
    //   273: ifeq +27 -> 300
    //   276: new 214	com/google/android/gms/auth/UserRecoverableAuthException
    //   279: dup
    //   280: aload 15
    //   282: aload 16
    //   284: invokespecial 350	com/google/android/gms/auth/UserRecoverableAuthException:<init>	(Ljava/lang/String;Landroid/content/Intent;)V
    //   287: athrow
    //   288: astore 9
    //   290: new 69	com/google/android/gms/auth/GoogleAuthException
    //   293: dup
    //   294: ldc -83
    //   296: invokespecial 156	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   299: athrow
    //   300: aload 17
    //   302: invokestatic 352	com/google/android/gms/auth/firstparty/shared/zzd:zzc	(Lcom/google/android/gms/auth/firstparty/shared/zzd;)Z
    //   305: ifeq +13 -> 318
    //   308: new 71	java/io/IOException
    //   311: dup
    //   312: aload 15
    //   314: invokespecial 167	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   317: athrow
    //   318: new 69	com/google/android/gms/auth/GoogleAuthException
    //   321: dup
    //   322: aload 15
    //   324: invokespecial 156	com/google/android/gms/auth/GoogleAuthException:<init>	(Ljava/lang/String;)V
    //   327: athrow
    //   328: new 71	java/io/IOException
    //   331: dup
    //   332: ldc -81
    //   334: invokespecial 167	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   337: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	338	0	paramContext	Context
    //   0	338	1	paramAccount	Account
    //   0	338	2	paramString	String
    //   0	338	3	paramBundle	Bundle
    //   4	88	4	localContext	Context
    //   27	178	5	localBundle1	Bundle
    //   36	31	6	str1	String
    //   89	141	7	localzza	com.google.android.gms.common.zza
    //   96	129	8	localzzl	com.google.android.gms.common.internal.zzl
    //   288	1	9	localInterruptedException	InterruptedException
    //   179	15	10	localObject	Object
    //   157	7	11	localRemoteException	android.os.RemoteException
    //   130	119	13	localBundle2	Bundle
    //   217	20	14	localTokenData	TokenData
    //   246	77	15	str2	String
    //   259	24	16	localIntent	Intent
    //   266	35	17	localzzd	com.google.android.gms.auth.firstparty.shared.zzd
    // Exception table:
    //   from	to	target	type
    //   113	157	157	android/os/RemoteException
    //   209	219	157	android/os/RemoteException
    //   239	288	157	android/os/RemoteException
    //   300	328	157	android/os/RemoteException
    //   113	157	179	finally
    //   159	179	179	finally
    //   209	219	179	finally
    //   239	288	179	finally
    //   290	300	179	finally
    //   300	328	179	finally
    //   113	157	288	java/lang/InterruptedException
    //   209	219	288	java/lang/InterruptedException
    //   239	288	288	java/lang/InterruptedException
    //   300	328	288	java/lang/InterruptedException
  }
  
  private static void zzaa(Context paramContext)
    throws GoogleAuthException
  {
    try
    {
      GooglePlayServicesUtil.zzaa(paramContext);
      return;
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      throw new GooglePlayServicesAvailabilityException(localGooglePlayServicesRepairableException.getConnectionStatusCode(), localGooglePlayServicesRepairableException.getMessage(), localGooglePlayServicesRepairableException.getIntent());
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      throw new GoogleAuthException(localGooglePlayServicesNotAvailableException.getMessage());
    }
  }
  
  public static TokenData zzb(Context paramContext, Account paramAccount, String paramString, Bundle paramBundle)
    throws IOException, UserRecoverableNotifiedException, GoogleAuthException
  {
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    paramBundle.putBoolean("handle_notification", true);
    return zzc(paramContext, paramAccount, paramString, paramBundle);
  }
  
  private static TokenData zzc(Context paramContext, Account paramAccount, String paramString, Bundle paramBundle)
    throws IOException, GoogleAuthException
  {
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    try
    {
      TokenData localTokenData = zza(paramContext, paramAccount, paramString, paramBundle);
      GooglePlayServicesUtil.zzac(paramContext);
      return localTokenData;
    }
    catch (GooglePlayServicesAvailabilityException localGooglePlayServicesAvailabilityException)
    {
      GooglePlayServicesUtil.showErrorNotification(localGooglePlayServicesAvailabilityException.getConnectionStatusCode(), paramContext);
      throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
    }
    catch (UserRecoverableAuthException localUserRecoverableAuthException)
    {
      GooglePlayServicesUtil.zzac(paramContext);
      throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
    }
  }
  
  private static void zzi(Intent paramIntent)
  {
    if (paramIntent == null) {
      throw new IllegalArgumentException("Callback cannot be null.");
    }
    String str = paramIntent.toUri(1);
    try
    {
      Intent.parseUri(str, 1);
      return;
    }
    catch (URISyntaxException localURISyntaxException)
    {
      throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/GoogleAuthUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */