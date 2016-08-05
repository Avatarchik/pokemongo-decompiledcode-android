package com.google.android.gms.gcm;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import java.util.concurrent.Executor;

public abstract class GcmListenerService
  extends Service
{
  private int zzaCe;
  private int zzaCf = 0;
  private final Object zzpd = new Object();
  
  /* Error */
  private final void zzk(Intent paramIntent)
  {
    // Byte code:
    //   0: ldc 32
    //   2: aload_1
    //   3: invokevirtual 38	android/content/Intent:getAction	()Ljava/lang/String;
    //   6: invokevirtual 44	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   9: istore 4
    //   11: iload 4
    //   13: ifne +9 -> 22
    //   16: aload_1
    //   17: invokestatic 50	com/google/android/gms/gcm/GcmReceiver:completeWakefulIntent	(Landroid/content/Intent;)Z
    //   20: pop
    //   21: return
    //   22: aload_1
    //   23: ldc 52
    //   25: invokevirtual 56	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   28: astore 5
    //   30: aload 5
    //   32: ifnonnull +263 -> 295
    //   35: ldc 58
    //   37: astore 6
    //   39: bipush -1
    //   41: istore 7
    //   43: aload 6
    //   45: invokevirtual 62	java/lang/String:hashCode	()I
    //   48: lookupswitch	default:+254->302, -2062414158:+133->181, 102161:+117->165, 814694033:+165->213, 814800675:+149->197
    //   92: ldc 64
    //   94: new 66	java/lang/StringBuilder
    //   97: dup
    //   98: invokespecial 67	java/lang/StringBuilder:<init>	()V
    //   101: ldc 69
    //   103: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: aload 6
    //   108: invokevirtual 73	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: invokevirtual 76	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   114: invokestatic 82	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   117: pop
    //   118: aload_0
    //   119: getfield 22	com/google/android/gms/gcm/GcmListenerService:zzpd	Ljava/lang/Object;
    //   122: astore 8
    //   124: aload 8
    //   126: monitorenter
    //   127: aload_0
    //   128: bipush -1
    //   130: aload_0
    //   131: getfield 24	com/google/android/gms/gcm/GcmListenerService:zzaCf	I
    //   134: iadd
    //   135: putfield 24	com/google/android/gms/gcm/GcmListenerService:zzaCf	I
    //   138: aload_0
    //   139: getfield 24	com/google/android/gms/gcm/GcmListenerService:zzaCf	I
    //   142: ifne +12 -> 154
    //   145: aload_0
    //   146: aload_0
    //   147: getfield 84	com/google/android/gms/gcm/GcmListenerService:zzaCe	I
    //   150: invokevirtual 88	com/google/android/gms/gcm/GcmListenerService:zzgA	(I)Z
    //   153: pop
    //   154: aload 8
    //   156: monitorexit
    //   157: aload_1
    //   158: invokestatic 50	com/google/android/gms/gcm/GcmReceiver:completeWakefulIntent	(Landroid/content/Intent;)Z
    //   161: pop
    //   162: goto -141 -> 21
    //   165: aload 6
    //   167: ldc 58
    //   169: invokevirtual 44	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   172: ifeq +130 -> 302
    //   175: iconst_0
    //   176: istore 7
    //   178: goto +124 -> 302
    //   181: aload 6
    //   183: ldc 90
    //   185: invokevirtual 44	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   188: ifeq +114 -> 302
    //   191: iconst_1
    //   192: istore 7
    //   194: goto +108 -> 302
    //   197: aload 6
    //   199: ldc 92
    //   201: invokevirtual 44	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   204: ifeq +98 -> 302
    //   207: iconst_2
    //   208: istore 7
    //   210: goto +92 -> 302
    //   213: aload 6
    //   215: ldc 94
    //   217: invokevirtual 44	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   220: ifeq +82 -> 302
    //   223: iconst_3
    //   224: istore 7
    //   226: goto +76 -> 302
    //   229: aload_0
    //   230: aload_1
    //   231: invokevirtual 98	android/content/Intent:getExtras	()Landroid/os/Bundle;
    //   234: invokespecial 102	com/google/android/gms/gcm/GcmListenerService:zzt	(Landroid/os/Bundle;)V
    //   237: goto -119 -> 118
    //   240: astore_2
    //   241: aload_1
    //   242: invokestatic 50	com/google/android/gms/gcm/GcmReceiver:completeWakefulIntent	(Landroid/content/Intent;)Z
    //   245: pop
    //   246: aload_2
    //   247: athrow
    //   248: aload_0
    //   249: invokevirtual 105	com/google/android/gms/gcm/GcmListenerService:onDeletedMessages	()V
    //   252: goto -134 -> 118
    //   255: aload_0
    //   256: aload_1
    //   257: ldc 107
    //   259: invokevirtual 56	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   262: invokevirtual 111	com/google/android/gms/gcm/GcmListenerService:onMessageSent	(Ljava/lang/String;)V
    //   265: goto -147 -> 118
    //   268: aload_0
    //   269: aload_1
    //   270: ldc 107
    //   272: invokevirtual 56	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   275: aload_1
    //   276: ldc 113
    //   278: invokevirtual 56	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   281: invokevirtual 117	com/google/android/gms/gcm/GcmListenerService:onSendError	(Ljava/lang/String;Ljava/lang/String;)V
    //   284: goto -166 -> 118
    //   287: astore 9
    //   289: aload 8
    //   291: monitorexit
    //   292: aload 9
    //   294: athrow
    //   295: aload 5
    //   297: astore 6
    //   299: goto -260 -> 39
    //   302: iload 7
    //   304: tableswitch	default:+-212->92, 0:+-75->229, 1:+-56->248, 2:+-49->255, 3:+-36->268
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	336	0	this	GcmListenerService
    //   0	336	1	paramIntent	Intent
    //   240	7	2	localObject1	Object
    //   9	3	4	bool	boolean
    //   28	268	5	str1	String
    //   37	261	6	str2	String
    //   41	262	7	i	int
    //   287	6	9	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   0	11	240	finally
    //   22	127	240	finally
    //   165	237	240	finally
    //   248	284	240	finally
    //   292	295	240	finally
    //   127	157	287	finally
    //   289	292	287	finally
  }
  
  private void zzt(Bundle paramBundle)
  {
    paramBundle.remove("message_type");
    paramBundle.remove("android.support.content.wakelockid");
    if (zza.zzu(paramBundle)) {
      zza.zzaz(this).zzv(paramBundle);
    }
    for (;;)
    {
      return;
      String str = paramBundle.getString("from");
      paramBundle.remove("from");
      onMessageReceived(str, paramBundle);
    }
  }
  
  public final IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onDeletedMessages() {}
  
  public void onMessageReceived(String paramString, Bundle paramBundle) {}
  
  public void onMessageSent(String paramString) {}
  
  public void onSendError(String paramString1, String paramString2) {}
  
  public final int onStartCommand(final Intent paramIntent, int paramInt1, int paramInt2)
  {
    for (;;)
    {
      synchronized (this.zzpd)
      {
        this.zzaCe = paramInt2;
        this.zzaCf = (1 + this.zzaCf);
        if (Build.VERSION.SDK_INT >= 11)
        {
          AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable()
          {
            public void run()
            {
              GcmListenerService.zza(GcmListenerService.this, paramIntent);
            }
          });
          return 3;
        }
      }
      new AsyncTask()
      {
        protected Void zzb(Void... paramAnonymousVarArgs)
        {
          GcmListenerService.zza(GcmListenerService.this, paramIntent);
          return null;
        }
      }.execute(new Void[0]);
    }
  }
  
  boolean zzgA(int paramInt)
  {
    return stopSelfResult(paramInt);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/gcm/GcmListenerService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */