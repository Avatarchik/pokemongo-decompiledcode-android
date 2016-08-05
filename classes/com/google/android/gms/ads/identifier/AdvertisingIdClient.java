package com.google.android.gms.ads.identifier;

import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.zza;
import com.google.android.gms.internal.zzav;
import com.google.android.gms.internal.zzav.zza;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class AdvertisingIdClient
{
  private static boolean zzoh = false;
  private final Context mContext;
  zza zzob;
  zzav zzoc;
  boolean zzod;
  Object zzoe = new Object();
  zza zzof;
  final long zzog;
  
  public AdvertisingIdClient(Context paramContext)
  {
    this(paramContext, 30000L);
  }
  
  public AdvertisingIdClient(Context paramContext, long paramLong)
  {
    zzx.zzw(paramContext);
    this.mContext = paramContext;
    this.zzod = false;
    this.zzog = paramLong;
  }
  
  public static Info getAdvertisingIdInfo(Context paramContext)
    throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    AdvertisingIdClient localAdvertisingIdClient = new AdvertisingIdClient(paramContext, -1L);
    try
    {
      localAdvertisingIdClient.zzb(false);
      Info localInfo = localAdvertisingIdClient.getInfo();
      return localInfo;
    }
    finally
    {
      localAdvertisingIdClient.finish();
    }
  }
  
  public static void setShouldSkipGmsCoreVersionCheck(boolean paramBoolean)
  {
    zzoh = paramBoolean;
  }
  
  static zzav zza(Context paramContext, zza paramzza)
    throws IOException
  {
    try
    {
      zzav localzzav = zzav.zza.zzb(paramzza.zzno());
      return localzzav;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new IOException("Interrupted exception");
    }
    catch (Throwable localThrowable)
    {
      throw new IOException(localThrowable);
    }
  }
  
  private void zzaJ()
  {
    synchronized (this.zzoe)
    {
      if (this.zzof != null) {
        this.zzof.cancel();
      }
    }
    try
    {
      this.zzof.join();
      if (this.zzog > 0L) {
        this.zzof = new zza(this, this.zzog);
      }
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;) {}
    }
  }
  
  /* Error */
  static zza zzo(Context paramContext)
    throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 122	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   4: ldc 124
    //   6: iconst_0
    //   7: invokevirtual 130	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   10: pop
    //   11: getstatic 29	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzoh	Z
    //   14: ifeq +67 -> 81
    //   17: ldc -124
    //   19: ldc -122
    //   21: invokestatic 140	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   24: pop
    //   25: invokestatic 146	com/google/android/gms/common/GoogleApiAvailability:getInstance	()Lcom/google/android/gms/common/GoogleApiAvailability;
    //   28: aload_0
    //   29: invokevirtual 150	com/google/android/gms/common/GoogleApiAvailability:isGooglePlayServicesAvailable	(Landroid/content/Context;)I
    //   32: tableswitch	default:+28->60, 0:+53->85, 1:+28->60, 2:+53->85
    //   60: new 56	java/io/IOException
    //   63: dup
    //   64: ldc -104
    //   66: invokespecial 97	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   69: athrow
    //   70: astore_1
    //   71: new 60	com/google/android/gms/common/GooglePlayServicesNotAvailableException
    //   74: dup
    //   75: bipush 9
    //   77: invokespecial 155	com/google/android/gms/common/GooglePlayServicesNotAvailableException:<init>	(I)V
    //   80: athrow
    //   81: aload_0
    //   82: invokestatic 160	com/google/android/gms/common/GooglePlayServicesUtil:zzaa	(Landroid/content/Context;)V
    //   85: new 83	com/google/android/gms/common/zza
    //   88: dup
    //   89: invokespecial 161	com/google/android/gms/common/zza:<init>	()V
    //   92: astore 4
    //   94: new 163	android/content/Intent
    //   97: dup
    //   98: ldc -91
    //   100: invokespecial 166	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   103: astore 5
    //   105: aload 5
    //   107: ldc -88
    //   109: invokevirtual 172	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
    //   112: pop
    //   113: invokestatic 178	com/google/android/gms/common/stats/zzb:zzqh	()Lcom/google/android/gms/common/stats/zzb;
    //   116: aload_0
    //   117: aload 5
    //   119: aload 4
    //   121: iconst_1
    //   122: invokevirtual 181	com/google/android/gms/common/stats/zzb:zza	(Landroid/content/Context;Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   125: istore 8
    //   127: iload 8
    //   129: ifeq +28 -> 157
    //   132: aload 4
    //   134: areturn
    //   135: astore_3
    //   136: new 56	java/io/IOException
    //   139: dup
    //   140: aload_3
    //   141: invokespecial 100	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   144: athrow
    //   145: astore 7
    //   147: new 56	java/io/IOException
    //   150: dup
    //   151: aload 7
    //   153: invokespecial 100	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   156: athrow
    //   157: new 56	java/io/IOException
    //   160: dup
    //   161: ldc -73
    //   163: invokespecial 97	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   166: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	167	0	paramContext	Context
    //   70	1	1	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   135	6	3	localGooglePlayServicesNotAvailableException	GooglePlayServicesNotAvailableException
    //   92	41	4	localzza	zza
    //   103	15	5	localIntent	android.content.Intent
    //   145	7	7	localThrowable	Throwable
    //   125	3	8	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   0	11	70	android/content/pm/PackageManager$NameNotFoundException
    //   81	85	135	com/google/android/gms/common/GooglePlayServicesNotAvailableException
    //   113	127	145	java/lang/Throwable
  }
  
  protected void finalize()
    throws Throwable
  {
    finish();
    super.finalize();
  }
  
  /* Error */
  public void finish()
  {
    // Byte code:
    //   0: ldc -66
    //   2: invokestatic 193	com/google/android/gms/common/internal/zzx:zzcj	(Ljava/lang/String;)V
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 48	com/google/android/gms/ads/identifier/AdvertisingIdClient:mContext	Landroid/content/Context;
    //   11: ifnull +10 -> 21
    //   14: aload_0
    //   15: getfield 195	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzob	Lcom/google/android/gms/common/zza;
    //   18: ifnonnull +6 -> 24
    //   21: aload_0
    //   22: monitorexit
    //   23: return
    //   24: aload_0
    //   25: getfield 50	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzod	Z
    //   28: ifeq +17 -> 45
    //   31: invokestatic 178	com/google/android/gms/common/stats/zzb:zzqh	()Lcom/google/android/gms/common/stats/zzb;
    //   34: aload_0
    //   35: getfield 48	com/google/android/gms/ads/identifier/AdvertisingIdClient:mContext	Landroid/content/Context;
    //   38: aload_0
    //   39: getfield 195	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzob	Lcom/google/android/gms/common/zza;
    //   42: invokevirtual 198	com/google/android/gms/common/stats/zzb:zza	(Landroid/content/Context;Landroid/content/ServiceConnection;)V
    //   45: aload_0
    //   46: iconst_0
    //   47: putfield 50	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzod	Z
    //   50: aload_0
    //   51: aconst_null
    //   52: putfield 200	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzoc	Lcom/google/android/gms/internal/zzav;
    //   55: aload_0
    //   56: aconst_null
    //   57: putfield 195	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzob	Lcom/google/android/gms/common/zza;
    //   60: aload_0
    //   61: monitorexit
    //   62: goto -39 -> 23
    //   65: astore_1
    //   66: aload_0
    //   67: monitorexit
    //   68: aload_1
    //   69: athrow
    //   70: astore_2
    //   71: ldc -54
    //   73: ldc -52
    //   75: aload_2
    //   76: invokestatic 208	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   79: pop
    //   80: goto -35 -> 45
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	83	0	this	AdvertisingIdClient
    //   65	4	1	localObject	Object
    //   70	6	2	localIllegalArgumentException	IllegalArgumentException
    // Exception table:
    //   from	to	target	type
    //   7	23	65	finally
    //   24	45	65	finally
    //   45	68	65	finally
    //   71	80	65	finally
    //   24	45	70	java/lang/IllegalArgumentException
  }
  
  /* Error */
  public Info getInfo()
    throws IOException
  {
    // Byte code:
    //   0: ldc -66
    //   2: invokestatic 193	com/google/android/gms/common/internal/zzx:zzcj	(Ljava/lang/String;)V
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 50	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzod	Z
    //   11: ifne +91 -> 102
    //   14: aload_0
    //   15: getfield 40	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzoe	Ljava/lang/Object;
    //   18: astore 7
    //   20: aload 7
    //   22: monitorenter
    //   23: aload_0
    //   24: getfield 103	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzof	Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
    //   27: ifnull +13 -> 40
    //   30: aload_0
    //   31: getfield 103	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzof	Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$zza;
    //   34: invokevirtual 216	com/google/android/gms/ads/identifier/AdvertisingIdClient$zza:zzaK	()Z
    //   37: ifne +26 -> 63
    //   40: new 56	java/io/IOException
    //   43: dup
    //   44: ldc -38
    //   46: invokespecial 97	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   49: athrow
    //   50: astore 8
    //   52: aload 7
    //   54: monitorexit
    //   55: aload 8
    //   57: athrow
    //   58: astore_1
    //   59: aload_0
    //   60: monitorexit
    //   61: aload_1
    //   62: athrow
    //   63: aload 7
    //   65: monitorexit
    //   66: aload_0
    //   67: iconst_0
    //   68: invokevirtual 68	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzb	(Z)V
    //   71: aload_0
    //   72: getfield 50	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzod	Z
    //   75: ifne +27 -> 102
    //   78: new 56	java/io/IOException
    //   81: dup
    //   82: ldc -36
    //   84: invokespecial 97	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   87: athrow
    //   88: astore 9
    //   90: new 56	java/io/IOException
    //   93: dup
    //   94: ldc -36
    //   96: aload 9
    //   98: invokespecial 223	java/io/IOException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   101: athrow
    //   102: aload_0
    //   103: getfield 195	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzob	Lcom/google/android/gms/common/zza;
    //   106: invokestatic 46	com/google/android/gms/common/internal/zzx:zzw	(Ljava/lang/Object;)Ljava/lang/Object;
    //   109: pop
    //   110: aload_0
    //   111: getfield 200	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzoc	Lcom/google/android/gms/internal/zzav;
    //   114: invokestatic 46	com/google/android/gms/common/internal/zzx:zzw	(Ljava/lang/Object;)Ljava/lang/Object;
    //   117: pop
    //   118: new 6	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info
    //   121: dup
    //   122: aload_0
    //   123: getfield 200	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzoc	Lcom/google/android/gms/internal/zzav;
    //   126: invokeinterface 229 1 0
    //   131: aload_0
    //   132: getfield 200	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzoc	Lcom/google/android/gms/internal/zzav;
    //   135: iconst_1
    //   136: invokeinterface 233 2 0
    //   141: invokespecial 236	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:<init>	(Ljava/lang/String;Z)V
    //   144: astore 4
    //   146: aload_0
    //   147: monitorexit
    //   148: aload_0
    //   149: invokespecial 238	com/google/android/gms/ads/identifier/AdvertisingIdClient:zzaJ	()V
    //   152: aload 4
    //   154: areturn
    //   155: astore 5
    //   157: ldc -54
    //   159: ldc -16
    //   161: aload 5
    //   163: invokestatic 208	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   166: pop
    //   167: new 56	java/io/IOException
    //   170: dup
    //   171: ldc -14
    //   173: invokespecial 97	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   176: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	177	0	this	AdvertisingIdClient
    //   58	4	1	localObject1	Object
    //   144	9	4	localInfo	Info
    //   155	7	5	localRemoteException	android.os.RemoteException
    //   50	6	8	localObject3	Object
    //   88	9	9	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   23	55	50	finally
    //   63	66	50	finally
    //   7	23	58	finally
    //   55	61	58	finally
    //   66	71	58	finally
    //   71	118	58	finally
    //   118	146	58	finally
    //   146	148	58	finally
    //   157	177	58	finally
    //   66	71	88	java/lang/Exception
    //   118	146	155	android/os/RemoteException
  }
  
  public void start()
    throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    zzb(true);
  }
  
  protected void zzb(boolean paramBoolean)
    throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException
  {
    zzx.zzcj("Calling this from your main thread can lead to deadlock");
    try
    {
      if (this.zzod) {
        finish();
      }
      this.zzob = zzo(this.mContext);
      this.zzoc = zza(this.mContext, this.zzob);
      this.zzod = true;
      if (paramBoolean) {
        zzaJ();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static final class Info
  {
    private final String zzom;
    private final boolean zzon;
    
    public Info(String paramString, boolean paramBoolean)
    {
      this.zzom = paramString;
      this.zzon = paramBoolean;
    }
    
    public String getId()
    {
      return this.zzom;
    }
    
    public boolean isLimitAdTrackingEnabled()
    {
      return this.zzon;
    }
    
    public String toString()
    {
      return "{" + this.zzom + "}" + this.zzon;
    }
  }
  
  static class zza
    extends Thread
  {
    private WeakReference<AdvertisingIdClient> zzoi;
    private long zzoj;
    CountDownLatch zzok;
    boolean zzol;
    
    public zza(AdvertisingIdClient paramAdvertisingIdClient, long paramLong)
    {
      this.zzoi = new WeakReference(paramAdvertisingIdClient);
      this.zzoj = paramLong;
      this.zzok = new CountDownLatch(1);
      this.zzol = false;
      start();
    }
    
    private void disconnect()
    {
      AdvertisingIdClient localAdvertisingIdClient = (AdvertisingIdClient)this.zzoi.get();
      if (localAdvertisingIdClient != null)
      {
        localAdvertisingIdClient.finish();
        this.zzol = true;
      }
    }
    
    public void cancel()
    {
      this.zzok.countDown();
    }
    
    public void run()
    {
      try
      {
        if (!this.zzok.await(this.zzoj, TimeUnit.MILLISECONDS)) {
          disconnect();
        }
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          disconnect();
        }
      }
    }
    
    public boolean zzaK()
    {
      return this.zzol;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/ads/identifier/AdvertisingIdClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */