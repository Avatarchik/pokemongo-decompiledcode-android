package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.iid.zzc;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GoogleCloudMessaging
{
  public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
  public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
  public static final String INSTANCE_ID_SCOPE = "GCM";
  @Deprecated
  public static final String MESSAGE_TYPE_DELETED = "deleted_messages";
  @Deprecated
  public static final String MESSAGE_TYPE_MESSAGE = "gcm";
  @Deprecated
  public static final String MESSAGE_TYPE_SEND_ERROR = "send_error";
  @Deprecated
  public static final String MESSAGE_TYPE_SEND_EVENT = "send_event";
  public static int zzaCs = 5000000;
  public static int zzaCt = 6500000;
  public static int zzaCu = 7000000;
  static GoogleCloudMessaging zzaCv;
  private static final AtomicInteger zzaCy = new AtomicInteger(1);
  private Context context;
  final Messenger zzaCA = new Messenger(new Handler(Looper.getMainLooper())
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if ((paramAnonymousMessage == null) || (!(paramAnonymousMessage.obj instanceof Intent))) {
        Log.w("GCM", "Dropping invalid message");
      }
      Intent localIntent = (Intent)paramAnonymousMessage.obj;
      if ("com.google.android.c2dm.intent.REGISTRATION".equals(localIntent.getAction())) {
        GoogleCloudMessaging.zza(GoogleCloudMessaging.this).add(localIntent);
      }
      for (;;)
      {
        return;
        if (!GoogleCloudMessaging.zza(GoogleCloudMessaging.this, localIntent))
        {
          localIntent.setPackage(GoogleCloudMessaging.zzb(GoogleCloudMessaging.this).getPackageName());
          GoogleCloudMessaging.zzb(GoogleCloudMessaging.this).sendBroadcast(localIntent);
        }
      }
    }
  });
  private PendingIntent zzaCw;
  private Map<String, Handler> zzaCx = Collections.synchronizedMap(new HashMap());
  private final BlockingQueue<Intent> zzaCz = new LinkedBlockingQueue();
  
  /**
   * @deprecated
   */
  public static GoogleCloudMessaging getInstance(Context paramContext)
  {
    try
    {
      if (zzaCv == null)
      {
        zzaCv = new GoogleCloudMessaging();
        zzaCv.context = paramContext.getApplicationContext();
      }
      GoogleCloudMessaging localGoogleCloudMessaging = zzaCv;
      return localGoogleCloudMessaging;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  static String zza(Intent paramIntent, String paramString)
    throws IOException
  {
    if (paramIntent == null) {
      throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    String str1 = paramIntent.getStringExtra(paramString);
    if (str1 != null) {
      return str1;
    }
    String str2 = paramIntent.getStringExtra("error");
    if (str2 != null) {
      throw new IOException(str2);
    }
    throw new IOException("SERVICE_NOT_AVAILABLE");
  }
  
  private void zza(String paramString1, String paramString2, long paramLong, int paramInt, Bundle paramBundle)
    throws IOException
  {
    if (paramString1 == null) {
      throw new IllegalArgumentException("Missing 'to'");
    }
    Intent localIntent = new Intent("com.google.android.gcm.intent.SEND");
    if (paramBundle != null) {
      localIntent.putExtras(paramBundle);
    }
    zzm(localIntent);
    localIntent.setPackage(zzaA(this.context));
    localIntent.putExtra("google.to", paramString1);
    localIntent.putExtra("google.message_id", paramString2);
    localIntent.putExtra("google.ttl", Long.toString(paramLong));
    localIntent.putExtra("google.delay", Integer.toString(paramInt));
    if (zzaA(this.context).contains(".gsf"))
    {
      Bundle localBundle = new Bundle();
      Iterator localIterator = paramBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        Object localObject = paramBundle.get(str);
        if ((localObject instanceof String)) {
          localBundle.putString("gcm." + str, (String)localObject);
        }
      }
      localBundle.putString("google.to", paramString1);
      localBundle.putString("google.message_id", paramString2);
      InstanceID.getInstance(this.context).zzc("GCM", "upstream", localBundle);
    }
    for (;;)
    {
      return;
      this.context.sendOrderedBroadcast(localIntent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }
  }
  
  public static String zzaA(Context paramContext)
  {
    return zzc.zzaE(paramContext);
  }
  
  public static int zzaB(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      i = localPackageManager.getPackageInfo(zzaA(paramContext), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        int i = -1;
      }
    }
  }
  
  private boolean zzl(Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("In-Reply-To");
    if ((str == null) && (paramIntent.hasExtra("error"))) {
      str = paramIntent.getStringExtra("google.message_id");
    }
    Handler localHandler;
    Message localMessage;
    if (str != null)
    {
      localHandler = (Handler)this.zzaCx.remove(str);
      if (localHandler != null)
      {
        localMessage = Message.obtain();
        localMessage.obj = paramIntent;
      }
    }
    for (boolean bool = localHandler.sendMessage(localMessage);; bool = false) {
      return bool;
    }
  }
  
  private String zzvX()
  {
    return "google.rpc" + String.valueOf(zzaCy.getAndIncrement());
  }
  
  public void close()
  {
    zzaCv = null;
    zza.zzaCi = null;
    zzvY();
  }
  
  public String getMessageType(Intent paramIntent)
  {
    String str;
    if (!"com.google.android.c2dm.intent.RECEIVE".equals(paramIntent.getAction())) {
      str = null;
    }
    for (;;)
    {
      return str;
      str = paramIntent.getStringExtra("message_type");
      if (str == null) {
        str = "gcm";
      }
    }
  }
  
  /* Error */
  @Deprecated
  public String register(String... paramVarArgs)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 343	com/google/android/gms/gcm/GoogleCloudMessaging:zzc	([Ljava/lang/String;)Ljava/lang/String;
    //   7: astore_3
    //   8: new 189	android/os/Bundle
    //   11: dup
    //   12: invokespecial 190	android/os/Bundle:<init>	()V
    //   15: astore 4
    //   17: aload_0
    //   18: getfield 115	com/google/android/gms/gcm/GoogleCloudMessaging:context	Landroid/content/Context;
    //   21: invokestatic 152	com/google/android/gms/gcm/GoogleCloudMessaging:zzaA	(Landroid/content/Context;)Ljava/lang/String;
    //   24: ldc -75
    //   26: invokevirtual 187	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   29: ifeq +38 -> 67
    //   32: aload 4
    //   34: ldc_w 345
    //   37: aload_3
    //   38: invokevirtual 230	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   41: aload_0
    //   42: getfield 115	com/google/android/gms/gcm/GoogleCloudMessaging:context	Landroid/content/Context;
    //   45: invokestatic 235	com/google/android/gms/iid/InstanceID:getInstance	(Landroid/content/Context;)Lcom/google/android/gms/iid/InstanceID;
    //   48: aload_3
    //   49: ldc 16
    //   51: aload 4
    //   53: invokevirtual 348	com/google/android/gms/iid/InstanceID:getToken	(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)Ljava/lang/String;
    //   56: astore 7
    //   58: aload 7
    //   60: astore 6
    //   62: aload_0
    //   63: monitorexit
    //   64: aload 6
    //   66: areturn
    //   67: aload 4
    //   69: ldc_w 350
    //   72: aload_3
    //   73: invokevirtual 230	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
    //   76: aload_0
    //   77: aload 4
    //   79: invokevirtual 353	com/google/android/gms/gcm/GoogleCloudMessaging:zzy	(Landroid/os/Bundle;)Landroid/content/Intent;
    //   82: ldc_w 355
    //   85: invokestatic 357	com/google/android/gms/gcm/GoogleCloudMessaging:zza	(Landroid/content/Intent;Ljava/lang/String;)Ljava/lang/String;
    //   88: astore 5
    //   90: aload 5
    //   92: astore 6
    //   94: goto -32 -> 62
    //   97: astore_2
    //   98: aload_0
    //   99: monitorexit
    //   100: aload_2
    //   101: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	102	0	this	GoogleCloudMessaging
    //   0	102	1	paramVarArgs	String[]
    //   97	4	2	localObject1	Object
    //   7	66	3	str1	String
    //   15	63	4	localBundle	Bundle
    //   88	3	5	str2	String
    //   60	33	6	localObject2	Object
    //   56	3	7	str3	String
    // Exception table:
    //   from	to	target	type
    //   2	58	97	finally
    //   67	90	97	finally
  }
  
  public void send(String paramString1, String paramString2, long paramLong, Bundle paramBundle)
    throws IOException
  {
    zza(paramString1, paramString2, paramLong, -1, paramBundle);
  }
  
  public void send(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    send(paramString1, paramString2, -1L, paramBundle);
  }
  
  @Deprecated
  public void unregister()
    throws IOException
  {
    try
    {
      if (Looper.getMainLooper() == Looper.myLooper()) {
        throw new IOException("MAIN_THREAD");
      }
    }
    finally {}
    InstanceID.getInstance(this.context).deleteInstanceID();
  }
  
  String zzc(String... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length == 0)) {
      throw new IllegalArgumentException("No senderIds");
    }
    StringBuilder localStringBuilder = new StringBuilder(paramVarArgs[0]);
    for (int i = 1; i < paramVarArgs.length; i++) {
      localStringBuilder.append(',').append(paramVarArgs[i]);
    }
    return localStringBuilder.toString();
  }
  
  /**
   * @deprecated
   */
  void zzm(Intent paramIntent)
  {
    try
    {
      if (this.zzaCw == null)
      {
        Intent localIntent = new Intent();
        localIntent.setPackage("com.google.example.invalidpackage");
        this.zzaCw = PendingIntent.getBroadcast(this.context, 0, localIntent, 0);
      }
      paramIntent.putExtra("app", this.zzaCw);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /**
   * @deprecated
   */
  void zzvY()
  {
    try
    {
      if (this.zzaCw != null)
      {
        this.zzaCw.cancel();
        this.zzaCw = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  @Deprecated
  Intent zzy(Bundle paramBundle)
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      throw new IOException("MAIN_THREAD");
    }
    if (zzaB(this.context) < 0) {
      throw new IOException("Google Play Services missing");
    }
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    Intent localIntent1 = new Intent("com.google.android.c2dm.intent.REGISTER");
    localIntent1.setPackage(zzaA(this.context));
    zzm(localIntent1);
    localIntent1.putExtra("google.message_id", zzvX());
    localIntent1.putExtras(paramBundle);
    localIntent1.putExtra("google.messenger", this.zzaCA);
    this.context.startService(localIntent1);
    try
    {
      Intent localIntent2 = (Intent)this.zzaCz.poll(30000L, TimeUnit.MILLISECONDS);
      return localIntent2;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new IOException(localInterruptedException.getMessage());
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/gcm/GoogleCloudMessaging.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */