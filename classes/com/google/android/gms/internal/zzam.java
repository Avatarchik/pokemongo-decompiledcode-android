package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzam
  extends zzal
{
  private static AdvertisingIdClient zznq = null;
  private static CountDownLatch zznr = new CountDownLatch(1);
  private static volatile boolean zzns;
  private boolean zznt;
  
  protected zzam(Context paramContext, zzap paramzzap, zzaq paramzzaq, boolean paramBoolean)
  {
    super(paramContext, paramzzap, paramzzaq);
    this.zznt = paramBoolean;
  }
  
  public static zzam zza(String paramString, Context paramContext, boolean paramBoolean)
  {
    zzah localzzah = new zzah();
    zza(paramString, paramContext, localzzah);
    if (paramBoolean) {}
    try
    {
      if (zznq == null) {
        new Thread(new zzb(paramContext)).start();
      }
      return new zzam(paramContext, localzzah, new zzas(239), paramBoolean);
    }
    finally {}
  }
  
  zza zzY()
    throws IOException
  {
    try
    {
      if (!zznr.await(2L, TimeUnit.SECONDS))
      {
        localzza = new zza(null, false);
        return localzza;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        zza localzza = new zza(null, false);
        continue;
        AdvertisingIdClient.Info localInfo;
        try
        {
          if (zznq == null) {
            localzza = new zza(null, false);
          }
        }
        finally
        {
          throw ((Throwable)localObject);
          localInfo = zznq.getInfo();
        }
      }
    }
  }
  
  protected void zzc(Context paramContext)
  {
    super.zzc(paramContext);
    try
    {
      if ((zzns) || (!this.zznt))
      {
        zza(24, zze(paramContext));
      }
      else
      {
        zza localzza1 = zzY();
        String str = localzza1.getId();
        if (str != null) {
          if (localzza1.isLimitAdTrackingEnabled())
          {
            l = 1L;
            zza(28, l);
            zza(26, 5L);
            zza(24, str);
          }
        }
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        break;
        long l = 0L;
      }
    }
    catch (zzal.zza localzza) {}
  }
  
  private static final class zzb
    implements Runnable
  {
    private Context zznx;
    
    public zzb(Context paramContext)
    {
      this.zznx = paramContext.getApplicationContext();
      if (this.zznx == null) {
        this.zznx = paramContext;
      }
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: ldc 8
      //   2: monitorenter
      //   3: invokestatic 35	com/google/android/gms/internal/zzam:zzZ	()Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;
      //   6: ifnonnull +27 -> 33
      //   9: new 37	com/google/android/gms/ads/identifier/AdvertisingIdClient
      //   12: dup
      //   13: aload_0
      //   14: getfield 24	com/google/android/gms/internal/zzam$zzb:zznx	Landroid/content/Context;
      //   17: invokespecial 39	com/google/android/gms/ads/identifier/AdvertisingIdClient:<init>	(Landroid/content/Context;)V
      //   20: astore 7
      //   22: aload 7
      //   24: invokevirtual 42	com/google/android/gms/ads/identifier/AdvertisingIdClient:start	()V
      //   27: aload 7
      //   29: invokestatic 46	com/google/android/gms/internal/zzam:zza	(Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient;
      //   32: pop
      //   33: invokestatic 50	com/google/android/gms/internal/zzam:zzaa	()Ljava/util/concurrent/CountDownLatch;
      //   36: invokevirtual 55	java/util/concurrent/CountDownLatch:countDown	()V
      //   39: ldc 8
      //   41: monitorexit
      //   42: return
      //   43: astore 5
      //   45: iconst_1
      //   46: invokestatic 58	com/google/android/gms/internal/zzam:zza	(Z)Z
      //   49: pop
      //   50: invokestatic 50	com/google/android/gms/internal/zzam:zzaa	()Ljava/util/concurrent/CountDownLatch;
      //   53: invokevirtual 55	java/util/concurrent/CountDownLatch:countDown	()V
      //   56: goto -17 -> 39
      //   59: astore_2
      //   60: ldc 8
      //   62: monitorexit
      //   63: aload_2
      //   64: athrow
      //   65: astore 4
      //   67: invokestatic 50	com/google/android/gms/internal/zzam:zzaa	()Ljava/util/concurrent/CountDownLatch;
      //   70: invokevirtual 55	java/util/concurrent/CountDownLatch:countDown	()V
      //   73: goto -34 -> 39
      //   76: astore_3
      //   77: invokestatic 50	com/google/android/gms/internal/zzam:zzaa	()Ljava/util/concurrent/CountDownLatch;
      //   80: invokevirtual 55	java/util/concurrent/CountDownLatch:countDown	()V
      //   83: goto -44 -> 39
      //   86: astore_1
      //   87: invokestatic 50	com/google/android/gms/internal/zzam:zzaa	()Ljava/util/concurrent/CountDownLatch;
      //   90: invokevirtual 55	java/util/concurrent/CountDownLatch:countDown	()V
      //   93: aload_1
      //   94: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	95	0	this	zzb
      //   86	8	1	localObject1	Object
      //   59	5	2	localObject2	Object
      //   76	1	3	localGooglePlayServicesRepairableException	com.google.android.gms.common.GooglePlayServicesRepairableException
      //   65	1	4	localIOException	IOException
      //   43	1	5	localGooglePlayServicesNotAvailableException	com.google.android.gms.common.GooglePlayServicesNotAvailableException
      //   20	8	7	localAdvertisingIdClient	AdvertisingIdClient
      // Exception table:
      //   from	to	target	type
      //   3	33	43	com/google/android/gms/common/GooglePlayServicesNotAvailableException
      //   33	42	59	finally
      //   50	63	59	finally
      //   67	95	59	finally
      //   3	33	65	java/io/IOException
      //   3	33	76	com/google/android/gms/common/GooglePlayServicesRepairableException
      //   3	33	86	finally
      //   45	50	86	finally
    }
  }
  
  class zza
  {
    private String zznu;
    private boolean zznv;
    
    public zza(String paramString, boolean paramBoolean)
    {
      this.zznu = paramString;
      this.zznv = paramBoolean;
    }
    
    public String getId()
    {
      return this.zznu;
    }
    
    public boolean isLimitAdTrackingEnabled()
    {
      return this.zznv;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */